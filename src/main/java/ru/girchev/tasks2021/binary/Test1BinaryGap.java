package ru.girchev.tasks2021.binary;

import static ru.girchev.core.Executor.executeAllSolutions;

import lombok.SneakyThrows;
import ru.girchev.core.SolutionMethod;

/**
 * BinaryGap Find longest sequence of zeros in binary representation of an integer.
 *
 * @author Girchev N.A. Date: 11.03.2020
 */
public abstract class Test1BinaryGap {

  @SneakyThrows
  public static void main(String[] args) {
    System.out.println("Solution for n=529 (1000010001)");
    executeAllSolutions(Test1BinaryGap.class, 529);
  }

  @SolutionMethod
  protected abstract int fibonacciFunction(int n);
}
