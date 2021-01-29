package ru.girchev.tasks2021.sequence;

import static ru.girchev.core.Executor.executeAllSolutions;
import static ru.girchev.core.Executor.start;

import java.util.Arrays;
import java.util.List;
import lombok.SneakyThrows;
import ru.girchev.core.SolutionMethod;
import ru.girchev.core.Utils;

/**
 * Write "input" sequence like below result: 1-3,5-6,9
 *
 * @author Girchev N.A. Date: 25.01.2021
 */
public abstract class Task1Sequence {

  protected static List<Integer> input = Arrays.asList(3, 6, 9, 2, 1, 5);

  @SneakyThrows
  public static void main(String[] args) {
    start(Utils.getExecutionType(args), Task1Sequence.class, "1-3,5-6,9", input);
  }

  @SolutionMethod
  protected abstract String format(List<Integer> input);
}
