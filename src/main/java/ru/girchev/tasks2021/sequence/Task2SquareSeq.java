package ru.girchev.tasks2021.sequence;

import static ru.girchev.Utils.executeAllSolutions;

import lombok.SneakyThrows;
import ru.girchev.SolutionMethod;

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

  protected static int[] input = new int[]{2, 4, 7, 9};

  @SneakyThrows
  public static void main(String[] args) {
    executeAllSolutions(Task2SquareSeq.class, input);
  }

  @SolutionMethod
  protected abstract String toSquareArray(int[] array);
}
