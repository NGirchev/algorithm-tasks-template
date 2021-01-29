package ru.girchev.tasks2021.binary;

import static ru.girchev.core.Executor.start;

import lombok.SneakyThrows;
import ru.girchev.core.SolutionMethod;
import ru.girchev.core.Utils;

/**
 * BinaryGap Find longest sequence of zeros in binary representation of an integer.
 * <p>
 * For example: 137 = 10001001 Result = 3
 *
 * @author Girchev N.A. Date: 11.03.2020
 */
public abstract class Test1BinaryGap {

  @SneakyThrows
  public static void main(String[] args) {
    start(Utils.getExecutionType(args), Test1BinaryGap.class, "4", 529);
  }

  @SolutionMethod
  protected abstract int findGap(int n);
}
