package ru.girchev.tasks2020.number.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static ru.girchev.Utils.executeInBenchmark;
import static ru.girchev.Utils.print;

public class Task1FindFirstMissingNumberSolution1 {

    public static void main(String[] args) {
        System.out.println("TEST1");
        int[] a = IntStream.range(-1000000, 1000000).toArray();
        a[10005 + 1000000] = -1001;
        startAllTasks(a);

        System.out.println("TEST2");
        a = IntStream.range(-1000000, 1000000).toArray();
        startAllTasks(a);

        System.out.println("TEST3");
        List<Integer> collect = IntStream.range(-1000000, 1000000).boxed().collect(Collectors.toList());
        Collections.shuffle(collect);
        a = collect.stream().mapToInt(Integer::intValue).toArray();
        a[10005 + 1000000] = -1001;
        startAllTasks(a);

        System.out.println("TEST4");
        collect = IntStream.range(-1000000, 1000000).boxed().collect(Collectors.toList());
        Collections.shuffle(collect);
        a = collect.stream().mapToInt(Integer::intValue).toArray();
        startAllTasks(a);
    }

    public static void startAllTasks(int[] a) {
        print("solution1 without stream:", executeInBenchmark(() -> solution1(a)));
        print("solution2 set:", executeInBenchmark(() -> solution2(a)));
        print("solution3 list:", executeInBenchmark(() -> solution3(a)));
    }

    public static int solution1(int[] a) {
        Set<Integer> set1 = new HashSet<>();
        for (int i : a) {
            if (i > 0) {
                set1.add(i);
            }
        }
        int i=0;
        while (!Thread.currentThread().isInterrupted()) {
            i++;
            if (!set1.contains(i)) {
                return i;
            }
        }
        return -1;
    }

    public static int solution2(int[] a) {
        Set<Integer> set2 = Arrays.stream(a).filter(i -> i > 0).boxed().collect(Collectors.toSet());
        int i=0;
        while (!Thread.currentThread().isInterrupted()) {
            i++;
            if (!set2.contains(i)) {
                return i;
            }
        }
        return -1;
    }

    public static int solution3(int[] a) {
        List<Integer> collect = Arrays.stream(a).filter(i -> i > 0).boxed().collect(Collectors.toList());
        int i=0;
        while (!Thread.currentThread().isInterrupted()) {
            i++;
            if (!collect.contains(i)) {
                return i;
            }
        }
        return -1;
    }

//    TEST1
//    solution1 without stream:10005 time:435
//    solution2 set:10005 time:449
//    solution3 list:10005 time:89
//    TEST2
//    solution1 without stream:1000000 time:68
//    solution2 set:1000000 time:69
//    solution3 list:null time:3000
//    TEST3
//    solution1 without stream:1000000 time:244
//    solution2 set:1000000 time:203
//    solution3 list:null time:3000
//    TEST4
//    solution1 without stream:1000000 time:1028
//    solution2 set:1000000 time:197
//    solution3 list:null time:3000
}
