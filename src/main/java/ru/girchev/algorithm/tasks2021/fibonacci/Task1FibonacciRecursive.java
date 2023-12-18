package ru.girchev.algorithm.tasks2021.fibonacci;

import static ru.girchev.algorithm.core.Executor.start;

import lombok.SneakyThrows;
import ru.girchev.algorithm.core.Condition;
import ru.girchev.algorithm.core.SolutionMethod;
import ru.girchev.algorithm.core.Utils;

/**
 * Write a function fibonacci using recursive function (numbers is 0, 1, 1, 2, 3, 5, 8): For
 * example: If n=6 Result = 8
 *
 * @author Girchev N.A. Date: 10.03.2020
 */
public abstract class Task1FibonacciRecursive {

  static int input = 42;
  static int result = 267914296;

  @SneakyThrows
  public static void main(String[] args) {
    start(Utils.getExecutionType(args), Task1FibonacciRecursive.class,
        new Condition(result, input));
  }

  @SolutionMethod
  protected abstract int fibonacciFunction(int n);
}
