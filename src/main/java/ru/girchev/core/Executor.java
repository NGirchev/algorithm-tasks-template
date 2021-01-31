package ru.girchev.core;

import static ru.girchev.core.ReflectionUtils.getSolutionMethod;
import static ru.girchev.core.ReflectionUtils.getSubTypes;
import static ru.girchev.core.ReflectionUtils.invoke;
import static ru.girchev.core.Utils.createConditionOutput;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Main class for task execution.
 *
 * @author Girchev N.A. Date: 29.01.2021
 */
public class Executor {

  private final static ThreadFactory threadFactory;
  private final static ExecutorService executorService;

  static {
    threadFactory = new com.google.common.util.concurrent.ThreadFactoryBuilder()
        .setDaemon(true).build();
    executorService = Executors.newFixedThreadPool(1, threadFactory);
  }

  /**
   * Main method for execution and print result. Aggregate all logic.
   *
   * @param executionType - single task or all
   * @param mainTaskClass - abstract task class
   * @param conditions    - list of condition for task execution
   */
  @SuppressWarnings({"rawtypes"})
  public static void start(
      ExecutionType executionType,
      Class mainTaskClass,
      Condition... conditions) {
    if (executionType == ExecutionType.ALL) {
      boolean forAllConditionSuccess = Stream.of(conditions)
          .allMatch(c -> executeAndCheckResult(mainTaskClass, c));
      Utils.print(mainTaskClass.getSimpleName(), forAllConditionSuccess);
    } else if (executionType == ExecutionType.SINGLE) {
      for (final Condition condition : conditions) {
        Object[] args = Arrays.stream(condition.args())
            .map(Utils::castObjArrayToString)
            .toArray();
        System.out.println("Start all solutions for condition: " + createConditionOutput(args));
        executeAllSolutions(mainTaskClass, condition.args())
            .forEach((key, value) -> Utils.print("Solution " + key + ": ", value,
                String.valueOf(condition.expectedResult())
                    .equals(String.valueOf(value.getRight()))));
      }
    } else {
      throw new UnsupportedOperationException(
          "Not implemented for ExecutionType = " + executionType);
    }
  }

  @SuppressWarnings({"rawtypes"})
  private static boolean executeAndCheckResult(Class mainTaskClass, Condition condition) {
    return atLeastOneSuccess(
        executeAllSolutions(mainTaskClass, condition.args()),
        String.valueOf(condition.expectedResult())
    );
  }

  @SuppressWarnings({"rawtypes"})
  private static Map<String, Pair<Long, Object>> executeAllSolutions(Class mainTaskClass,
      Object... args) {
    return getSubTypes(mainTaskClass).stream()
        .collect(Collectors.toMap(Class::getSimpleName, sub -> executeSolution(sub, args)));
  }

  private static boolean atLeastOneSuccess(
      Map<String, Pair<Long, Object>> resultsMap,
      @NonNull String expectedResult) {
    return resultsMap.values().stream()
        .map(Pair::getRight)
        .map(String::valueOf)
        .anyMatch(expectedResult::equals);
  }

  @SneakyThrows
  private static Pair<Long, Object> executeSolution(Class<?> implementationClass, Object... args) {
    Method solutionMethod = getSolutionMethod(implementationClass.getSuperclass());
    solutionMethod.setAccessible(true);
    return executeInBenchmark(() -> invoke(solutionMethod, implementationClass, args));
  }

  /**
   * Method for execute some code, wrapped in time measurements.
   *
   * @param supplier some code
   * @param <T>      type of result
   * @return pair, where left is a time of execution, right is a result of execution. Right part
   * will be null, if the execution time exceeds 3 sec.
   */
  public static <T> Pair<Long, T> executeInBenchmark(Supplier<T> supplier) {
    Future<Pair<Long, T>> submit = executorService.submit(() -> wrapMeasurements(supplier));
    return extractResult(submit);
  }

  private static <T> Pair<Long, T> wrapMeasurements(Supplier<T> supplier) {
    long l1 = System.nanoTime();
    T result = supplier.get();
    long l2 = System.nanoTime();
    return Pair.of((l2 - l1) / 1000000, result);
  }

  private static <T> Pair<Long, T> extractResult(Future<Pair<Long, T>> tFuture) {
    Pair<Long, T> result = null;
    try {
      result = tFuture.get(3L, TimeUnit.SECONDS);
    } catch (TimeoutException e) {
      result = Pair.of(3000L, null);
      tFuture.cancel(true);
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
      tFuture.cancel(true);
    }
    return result;
  }
}
