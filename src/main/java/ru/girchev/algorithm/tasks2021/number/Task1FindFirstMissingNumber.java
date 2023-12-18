package ru.girchev.algorithm.tasks2021.number;

import static ru.girchev.algorithm.core.Executor.start;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.SneakyThrows;
import ru.girchev.algorithm.core.Condition;
import ru.girchev.algorithm.core.SolutionMethod;
import ru.girchev.algorithm.core.Utils;

/**
 * Write a function:
 *
 * <b>public int solution(int[] array);</b>
 * <p>
 * that, given an array A of N integers, returns the smallest positive integer (greater than 0) that
 * does not occur in A.
 * <p>
 * Write an efficient algorithm for the following assumptions: <br> - N is an integer within the
 * range [1..10,000,000]; <br> - each element of array A is an integer within the range
 * [−1,000,000..1,000,000].
 * <p>
 * For example:<br> given A = [1, 3, 6, 4, 1, 2], the function should return 5.<br> given A = [1, 2,
 * 3], the function should return 4.<br> given A = [−1, −3], the function should return 1.
 *
 * @author Girchev N.A. Date: 10.03.2020
 */
public abstract class Task1FindFirstMissingNumber {

  static int[] input1, input2, input3, input4, input5;
  static int result4;

  static {
    input1 = new int[]{1, 3, 6, 4, 1, 2};
    input2 = new int[]{1, 2, 3};
    input3 = new int[]{-1, -3};
    //------------
    Random r = new Random();
    input4 = r.ints(r.nextInt(10_000_000), -1_000_000, 1_000_000).toArray();
    result4 = getAnswer(input4);
    List<Integer> input5List = IntStream.range(-1_000_000, 1_000_000).boxed()
        .collect(Collectors.toList());
    input5List.remove(10_005 + 1_000_000);
    Collections.shuffle(input5List);
    //------------
    input5 = new int[input5List.size()];
    for (int i = 0; i < input5List.size(); i++) {
      input5[i] = input5List.get(i);
    }
  }

  @SneakyThrows
  @SuppressWarnings("all")
  public static void main(String[] args) {
    start(Utils.getExecutionType(args), Task1FindFirstMissingNumber.class,
        new Condition(5, input1),
        new Condition(4, input2),
        new Condition(1, input3),
        new Condition(result4, input4),
        new Condition(10005, input5));
  }

  @SolutionMethod
  protected abstract int solution(int[] array);

  private static int getAnswer(int[] a) {
    Set<Integer> set1 = new HashSet<>();
    for (int i : a) {
      if (i > 0) {
        set1.add(i);
      }
    }
    int i = 0;
    while (true) {
      i++;
      if (!set1.contains(i)) {
        return i;
      }
    }
  }
}
