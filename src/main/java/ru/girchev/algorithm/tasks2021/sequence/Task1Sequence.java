package ru.girchev.algorithm.tasks2021.sequence;

import static ru.girchev.algorithm.core.Executor.start;

import java.util.Arrays;
import java.util.List;
import lombok.SneakyThrows;
import ru.girchev.algorithm.core.Condition;
import ru.girchev.algorithm.core.SolutionMethod;
import ru.girchev.algorithm.core.Utils;

/**
 * Write "input" sequence like below result: 1-3,5-6,9
 *
 * @author Girchev N.A. Date: 25.01.2021
 */
public abstract class Task1Sequence {

  static List<Integer> input = Arrays.asList(3, 6, 9, 2, 1, 5);

  @SneakyThrows
  public static void main(String[] args) {
    start(Utils.getExecutionType(args), Task1Sequence.class,
        new Condition("1-3,5-6,9", input));
  }

  @SolutionMethod
  protected abstract String format(List<Integer> input);
}
