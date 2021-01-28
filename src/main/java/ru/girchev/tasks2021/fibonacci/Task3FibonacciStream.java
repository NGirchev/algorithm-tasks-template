package ru.girchev.tasks2021.fibonacci;

import static ru.girchev.core.Executor.executeAllSolutions;

import lombok.SneakyThrows;
import ru.girchev.core.SolutionMethod;

/**
 * Write a function:
 *
 * @author Girchev N.A. Date: 10.03.2020
 */
public abstract class Task3FibonacciStream {

  @SneakyThrows
  public static void main(String[] args) {
    System.out.println("Solution for n=6");
    executeAllSolutions(Task3FibonacciStream.class, 6);
    System.out.println("Solution for n=42");
    executeAllSolutions(Task3FibonacciStream.class, 42);
  }

  @SolutionMethod
  protected abstract int fibonacciFunction(int n);
}
