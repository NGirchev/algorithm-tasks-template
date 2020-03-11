package ru.girchev;

import org.apache.commons.lang3.tuple.Pair;

import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * @author Girchev N.A.
 * Date: 11.03.2020
 */
public class Utils {

    private final static ThreadFactory threadFactory;
    private final static ExecutorService executorService;

    static {
        threadFactory = new com.google.common.util.concurrent.ThreadFactoryBuilder().setDaemon(true).build();
        executorService = Executors.newFixedThreadPool(1, threadFactory);
    }

    public static <T> Pair<Long, T> executeInBenchmark(Supplier<T> supplier) {
        Future<Pair<Long, T>> submit = executorService.submit(() -> wrapMeasurements(supplier));
        return extractResult(submit);
    }

    private static <T> Pair<Long, T> wrapMeasurements(Supplier<T> supplier) {
        long l1 = System.nanoTime();
        T result = supplier.get();
        long l2 = System.nanoTime();
        return Pair.of((l2-l1)/1000000, result);
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
