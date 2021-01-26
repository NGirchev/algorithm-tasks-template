package ru.girchev;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import lombok.SneakyThrows;
import org.apache.commons.lang3.tuple.Pair;

import java.util.concurrent.*;
import java.util.function.Supplier;
import org.reflections.Reflections;
import ru.girchev.tasks2021.sequence.Task2SquareSeq;

/**
 * @author Girchev N.A. Date: 11.03.2020
 */
public class Utils {

  private final static ThreadFactory threadFactory;
  private final static ExecutorService executorService;

  static {
    threadFactory = new com.google.common.util.concurrent.ThreadFactoryBuilder()
        .setDaemon(true).build();
    executorService = Executors.newFixedThreadPool(1, threadFactory);
  }

  @SuppressWarnings({"rawtypes"})
  public static void executeAllSolutions(Class aClass, Object ...args) {
    getSubTypes(aClass).forEach(sub -> executeSolution(sub, args));
  }

  @SneakyThrows
  public static void executeSolution(Class<?> aClass, Object ...args) {
    Method solutionMethod = getSolutionMethod(aClass.getSuperclass());
    solutionMethod.setAccessible(true);
    print("Solution " + aClass.getSimpleName() + ": ",
        executeInBenchmark(() -> invoke(solutionMethod, aClass, args)));
  }

  @SneakyThrows
  public static Object invoke(Method solutionMethod, Class<?> aClass, Object ...args) {
    return solutionMethod.invoke(aClass.newInstance(), args);
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  public static Set<Class> getSubTypes(Class aClass) {
    Reflections reflections = new Reflections(aClass.getPackage());
    return reflections.getSubTypesOf(aClass);
  }

  @SuppressWarnings({"rawtypes"})
  public static Method getAbstractMethod(Class aClass) {
    return Arrays.stream(aClass.getDeclaredMethods())
        .filter(method -> Modifier.isAbstract(method.getModifiers()))
        .findFirst()
        .orElse(null);
  }

  @SuppressWarnings({"rawtypes"})
  public static Method getSolutionMethod(Class aClass) {
    return Arrays.stream(aClass.getDeclaredMethods())
        .filter(method -> Arrays.stream(method.getAnnotations())
            .map(Annotation::annotationType)
            .anyMatch( a-> a.equals(SolutionMethod.class)))
        .findFirst()
        .orElse(null);
  }

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

  public static void print(String tag, Pair<Long, Object> pair) {
    System.out.println(tag + pair.getRight() + " time:" + pair.getLeft());
  }
}
