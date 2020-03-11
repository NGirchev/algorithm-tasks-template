package ru.girchev;

import java.util.stream.IntStream;

import static ru.girchev.Utils.executeInBenchmark;
import static ru.girchev.Utils.print;

/**
 * Write a function:
 *
 * @author Girchev N.A.
 * Date: 10.03.2020
 */
public class Task2Fibonacci {

    public static void main(String[] args) {
        System.out.println("TEST1 get 6 number");
        print("Fibonacci recursive:", executeInBenchmark(() -> fibonacci1(6)));
        print("Fibonacci iteration:", executeInBenchmark(() -> fibonacci2(6)));
        print("Fibonacci stream:", executeInBenchmark(() -> fibonacci3(6)));

        System.out.println("TEST1 get 42 number");
        print("Fibonacci recursive:", executeInBenchmark(() -> fibonacci1(42)));
        print("Fibonacci iteration:", executeInBenchmark(() -> fibonacci2(42)));
        print("Fibonacci stream:", executeInBenchmark(() -> fibonacci3(42)));
    }


    public static int fibonacci1(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fibonacci1(n - 1) +
                    fibonacci1(n - 2);
        }
    }

    public static int fibonacci2(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1 || n == 2) {
            return 1;
        } else {
            int a = 1;
            int b = 1;
            int sum = 0;
            for (int i = 1; i < n-1; i++) {
                sum = a + b;
                a = b;
                b = sum;
            }
            return sum;
        }
    }

    public static int fibonacci3(int n) {
        if (n == 0) {
            return 0;
        } else {
            int[] arr = new int[2];
            arr[1] = 1;
            return IntStream.iterate(1, i -> {
                int sum = arr[0] + arr[1];
                arr[0] = arr[1];
                arr[1] = sum;
                return sum;
            }).limit(n).reduce((a, b) -> b).getAsInt();
        }
    }

//    TEST1 get 6 number
//    Fibonacci recursive:8 time:0
//    Fibonacci iteration:8 time:0
//    Fibonacci stream:8 time:6
//    TEST1 get 42 number
//    Fibonacci recursive:267914296 time:1548
//    Fibonacci iteration:267914296 time:0
//    Fibonacci stream:267914296 time:0
}
