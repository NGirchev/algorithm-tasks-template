package ru.girchev.algorithm.tasks2021.binary;

import lombok.SneakyThrows;
import ru.girchev.algorithm.core.Condition;
import ru.girchev.algorithm.core.SolutionMethod;
import ru.girchev.algorithm.core.Utils;

import static ru.girchev.algorithm.core.Executor.start;

/**
 * BinaryGap Find longest sequence of zeros in binary representation of an integer.
 * <p>
 * For example: 137 = 10001001 Result = 3
 *
 * @author Girchev N.A. Date: 11.03.2020
 */
public abstract class Task1BinaryGap {

  static int input = 529;
  static int result = 4;

  @SneakyThrows
  public static void main(String[] args) {
    start(Utils.getExecutionType(args), Task1BinaryGap.class,
            new Condition(result, input));
  }

  @SolutionMethod
  protected abstract int findGap(int n);
}
