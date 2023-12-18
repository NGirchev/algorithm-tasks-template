package ru.girchev.algorithm.tasks2021.sequence;

import static ru.girchev.algorithm.core.Executor.start;

import lombok.SneakyThrows;
import ru.girchev.algorithm.core.Condition;
import ru.girchev.algorithm.core.SolutionMethod;
import ru.girchev.algorithm.core.Utils;

/**
 * You have a sorted array of Integer. Needs make sorted array of squares Integer numbers.
 * <p>
 * - do not use any other arrays
 * <p>
 * - complexity n^2
 *
 * @author Girchev N.A. Date: 26.01.2021
 */
public abstract class Task2SquareSeq {

  static int[] input = new int[]{2, 4, 7, 9};

  @SneakyThrows
  @SuppressWarnings("all")
  public static void main(String[] args) {
    start(Utils.getExecutionType(args), Task2SquareSeq.class,
        new Condition("", input));
  }

  @SolutionMethod
  protected abstract String toSquareArray(int[] array);
}
