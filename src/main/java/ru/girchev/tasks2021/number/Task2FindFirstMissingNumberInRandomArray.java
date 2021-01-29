package ru.girchev.tasks2021.number;

import static ru.girchev.core.Executor.start;

import java.util.Random;
import lombok.SneakyThrows;
import ru.girchev.core.SolutionMethod;
import ru.girchev.core.Utils;

/**
 * Write a function:
 *
 * <b>public int solution(int[] array);</b>
 * <p>
 * that, given an array A of N integers, returns the smallest positive integer (greater than 0) that
 * does not occur in A.
 * <p>
 * Write an efficient algorithm for the following assumptions: - N is an integer within the range
 * [1..100,000]; - each element of array A is an integer within the range [−1,000,000..1,000,000].
 * <p>
 * For example: given A = [1, 3, 6, 4, 1, 2], the function should return 5. given A = [1, 2, 3], the
 * function should return 4. given A = [−1, −3], the function should return 1.
 *
 * @author Girchev N.A. Date: 10.03.2020
 */
public abstract class Task2FindFirstMissingNumberInRandomArray {

  protected static int[] input;

  static {
    Random r = new Random();
    r.ints(r.nextInt(100_000), -1_000_000, 1_000_000);
  }

  @SneakyThrows
  @SuppressWarnings("all")
  public static void main(String[] args) {
    start(Utils.getExecutionType(args), Task2FindFirstMissingNumberInRandomArray.class, 0, input);
  }

  @SolutionMethod
  protected abstract int solution(int[] array);
}
