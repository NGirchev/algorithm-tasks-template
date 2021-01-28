package ru.girchev.tasks2021.number;

import static ru.girchev.core.Executor.executeAllSolutions;

import java.util.stream.IntStream;
import lombok.SneakyThrows;
import ru.girchev.core.SolutionMethod;

/**
 * Write a function:
 *
 * <b>public int solution(int[] array);</b>
 * <p>
 * that, given an array A of N integers, returns the smallest positive integer (greater than 0) that
 * does not occur in A.
 * <p>
 * Write an efficient algorithm for the following assumptions: - N = 2,000,000; - each element of
 * array A is an integer within the range [−1,000,000..1,000,000].
 * <p>
 * For example: given A = [1, 3, 6, 4, 1, 2], the function should return 5. given A = [1, 2, 3], the
 * function should return 4. given A = [−1, −3], the function should return 1.
 *
 * @author Girchev N.A. Date: 10.03.2020
 */
public abstract class Task1FindFirstMissingNumberInStaticArray {

  protected static int[] input;

  static {
    input = IntStream.range(-1_000_000, 1_000_000).toArray();
    input[1_000_000 + 10005] = 0;
  }

  @SneakyThrows
  @SuppressWarnings("all")
  public static void main(String[] args) {
    executeAllSolutions(Task1FindFirstMissingNumberInStaticArray.class, input);
  }

  @SolutionMethod
  protected abstract int solution(int[] array);
}
