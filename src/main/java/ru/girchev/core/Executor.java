package ru.girchev.core;

import static ru.girchev.core.ReflectionUtils.getSolutionMethod;
import static ru.girchev.core.ReflectionUtils.getSubTypes;
import static ru.girchev.core.ReflectionUtils.invoke;

import java.lang.reflect.Method;
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
import lombok.NonNull;
import lombok.SneakyThrows;
import org.apache.commons.lang3.tuple.Pair;

/**
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

  @SuppressWarnings({"rawtypes"})
  public static void start(
      ExecutionType executionType,
      Class mainTaskClass,
      Object expectedResult,
      Object... args) {
    Map<String, Pair<Long, Object>> resultMap = executeAllSolutions(mainTaskClass, args);
    if (executionType == ExecutionType.ALL) {
      handleResults(resultMap, mainTaskClass, expectedResult);
    } else if (executionType == ExecutionType.SINGLE) {
      handleResults(resultMap);
    } else {
      throw new UnsupportedOperationException("Not implemented for ExecutionType = " + executionType);
    }
  }

  @SuppressWarnings({"rawtypes"})
  public static void handleResults(
      Map<String, Pair<Long, Object>> resultsMap,
      Class mainTaskClass,
      @NonNull Object expectedResult) {
    Utils.print(
        mainTaskClass.getSimpleName(),
        resultsMap.values().stream()
            .map(Pair::getValue)
            .anyMatch(expectedResult::equals)
    );
  }

  public static void handleResults(Map<String, Pair<Long, Object>> resultsMap) {
    resultsMap.forEach((key, value) -> Utils.print("Solution " + key + ": ", value));
  }

  @SuppressWarnings({"rawtypes"})
  public static Map<String, Pair<Long, Object>> executeAllSolutions(Class mainTaskClass,
      Object... args) {
    return getSubTypes(mainTaskClass).stream()
        .collect(Collectors.toMap(Class::getSimpleName, sub -> executeSolution(sub, args)));
  }

  @SneakyThrows
  public static Pair<Long, Object> executeSolution(Class<?> implementationClass, Object... args) {
    Method solutionMethod = getSolutionMethod(implementationClass.getSuperclass());
    solutionMethod.setAccessible(true);
    return executeInBenchmark(() -> invoke(solutionMethod, implementationClass, args));
  }

  public static <T> Pair<Long, T> executeInBenchmark(Supplier<T> supplier) {
    Future<Pair<Long, T>> submit = executorService.submit(() -> wrapMeasurements(supplier));
    return extractResult(submit);
  }

  public static <T> Pair<Long, T> wrapMeasurements(Supplier<T> supplier) {
    long l1 = System.nanoTime();
    T result = supplier.get();
    long l2 = System.nanoTime();
    return Pair.of((l2 - l1) / 1000000, result);
  }

  public static <T> Pair<Long, T> extractResult(Future<Pair<Long, T>> tFuture) {
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
