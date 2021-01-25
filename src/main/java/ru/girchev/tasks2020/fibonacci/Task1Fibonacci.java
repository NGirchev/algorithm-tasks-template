package ru.girchev.tasks2020.fibonacci;

import static ru.girchev.Utils.executeInBenchmark;
import static ru.girchev.Utils.print;

/**
 * Write a function:
 *
 * @author Girchev N.A.
 * Date: 10.03.2020
 */
public class Task1Fibonacci {

    public static void main(String[] args) {
        System.out.println("TEST1 get 6 number");
        print("Fibonacci recursive:", executeInBenchmark(() -> fibonacciRecursive(6)));
        print("Fibonacci iteration:", executeInBenchmark(() -> fibonacciIteration(6)));
        print("Fibonacci stream:", executeInBenchmark(() -> fibonacciStream(6)));

        System.out.println("TEST1 get 42 number");
        print("Fibonacci recursive:", executeInBenchmark(() -> fibonacciRecursive(42)));
        print("Fibonacci iteration:", executeInBenchmark(() -> fibonacciIteration(42)));
        print("Fibonacci stream:", executeInBenchmark(() -> fibonacciStream(42)));
    }


    public static int fibonacciRecursive(int n) {
        return 0;
    }

    public static int fibonacciIteration(int n) {
        return 0;
    }

    public static int fibonacciStream(int n) {
        return 0;
    }
}
