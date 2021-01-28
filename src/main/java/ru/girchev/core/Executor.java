package ru.girchev.core;

import static ru.girchev.core.ReflectionUtils.getSolutionMethod;
import static ru.girchev.core.ReflectionUtils.getSubTypes;
import static ru.girchev.core.ReflectionUtils.invoke;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;
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
  public static void executeAllSolutions(Class aClass, Object... args) {
    getSubTypes(aClass).forEach(sub -> executeSolution(sub, args));
  }

  @SneakyThrows
  public static void executeSolution(Class<?> aClass, Object... args) {
    Method solutionMethod = getSolutionMethod(aClass.getSuperclass());
    solutionMethod.setAccessible(true);
    Utils.print("Solution " + aClass.getSimpleName() + ": ",
        executeInBenchmark(() -> invoke(solutionMethod, aClass, args)));
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
