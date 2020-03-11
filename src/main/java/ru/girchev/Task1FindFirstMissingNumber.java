package ru.girchev;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static ru.girchev.Utils.executeInBenchmark;
import static ru.girchev.Utils.print;

/**
 * Write a function:
 *
 * class Solution { public int solution(int[] A); }
 *
 * that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.
 *
 * For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.
 *
 * Given A = [1, 2, 3], the function should return 4.
 *
 * Given A = [−1, −3], the function should return 1.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N is an integer within the range [1..100,000];
 * each element of array A is an integer within the range [−1,000,000..1,000,000].
 *
 * @author Girchev N.A.
 * Date: 10.03.2020
 */
public class Task1FindFirstMissingNumber {

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
