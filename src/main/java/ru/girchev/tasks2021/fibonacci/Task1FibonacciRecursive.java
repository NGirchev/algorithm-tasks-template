package ru.girchev.tasks2021.fibonacci;

import static ru.girchev.core.Executor.start;

import lombok.SneakyThrows;
import ru.girchev.core.SolutionMethod;
import ru.girchev.core.Utils;

/**
 * Write a function fibonacci using recursive function (numbers is 0, 1, 1, 2, 3, 5, 8): For
 * example: If n=6 Result = 8
 *
 * @author Girchev N.A. Date: 10.03.2020
 */
public abstract class Task1FibonacciRecursive {

  @SneakyThrows
  public static void main(String[] args) {
    start(Utils.getExecutionType(args), Task1FibonacciRecursive.class, 267914296, 42);
  }

  @SolutionMethod
  protected abstract int fibonacciFunction(int n);
}
