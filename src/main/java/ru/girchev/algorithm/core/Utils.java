package ru.girchev.algorithm.core;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;

import lombok.experimental.UtilityClass;

/**
 * Common utility.
 *
 * @author Girchev N.A. Date: 11.03.2020
 */
@UtilityClass
public class Utils {

    public static ExecutionType getExecutionType(String[] args) {
        if (args == null || args.length == 0) {
            return ExecutionType.SINGLE;
        }
        return Arrays.stream(args)
                    .map(String::toLowerCase)
                    .filter(ExecutionType.VALUES_AS_STRING::contains)
                    .map(arg -> arg.substring(1))
                    .map(String::toUpperCase)
                    .map(ExecutionType::valueOf)
                    .findFirst()
                    .orElse(ExecutionType.SINGLE);
    }

    public static Date convertToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static void printMemoryUsage() {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        System.out.print("Initial heap: " + heapMemoryUsage.getInit() / (1024 * 1024) + " MB");
        System.out.print(" | Used heap: " + heapMemoryUsage.getUsed() / (1024 * 1024) + " MB");
        System.out.print(" | Max heap: " + heapMemoryUsage.getMax() / (1024 * 1024) + " MB");
        System.out.println(" | Committed heap: " + heapMemoryUsage.getCommitted() / (1024 * 1024) + " MB");
    }
}
