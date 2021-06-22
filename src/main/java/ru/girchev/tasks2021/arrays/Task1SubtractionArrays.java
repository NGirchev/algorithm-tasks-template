package ru.girchev.tasks2021.arrays;

import lombok.SneakyThrows;
import ru.girchev.core.Condition;
import ru.girchev.core.SolutionMethod;
import ru.girchev.core.Utils;

import static ru.girchev.core.Executor.start;

/**
 * Write a function that takes two arrays, and subtract array B from array A.
 * The result array must contains elements in original order of array A.
 * If array A has some element several times and array B contains that element, then
 * all occurrences in array A must be removed.
 *
 * @author Girchev N.A.
 * Date: 22.06.2021
 */
public abstract class Task1SubtractionArrays {

    static int[] arrA1 = {1, 2}, arrB1 = {1};
    static int[] result1 = {2};

    static int[] arrA2 = {1, 2, 2}, arrB2 = {1};
    static int[] result2 = {2, 2};

    static int[] arrA3 = {1, 2, 2}, arrB3 = {2};
    static int[] result3 = {1};

    static int[] arrA4 = {1, 2, 2}, arrB4 = {};
    static int[] result4 = {1, 2, 2};

    static int[] arrA5 = {}, arrB5 = {1, 2};
    static int[] result5 = {};

    @SneakyThrows
    public static void main(String[] args) {
        start(Utils.getExecutionType(args), Task1SubtractionArrays.class,
                new Condition(result1, arrA1, arrB1),
                new Condition(result2, arrA2, arrB2),
                new Condition(result3, arrA3, arrB3),
                new Condition(result4, arrA4, arrB4),
                new Condition(result5, arrA5, arrB5));
    }

    @SolutionMethod
    protected abstract int[] subtract(int[] arrA, int[] arrB);
}
