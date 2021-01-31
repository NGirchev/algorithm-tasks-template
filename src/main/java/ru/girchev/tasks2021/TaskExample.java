package ru.girchev.tasks2021;

import static ru.girchev.core.Executor.start;

import lombok.SneakyThrows;
import ru.girchev.core.Condition;
import ru.girchev.core.SolutionMethod;
import ru.girchev.core.Utils;

/**
 * Write a solution for a+b
 *
 * @author Girchev N.A. Date: 31.01.2021
 */
public abstract class TaskExample {

  @SneakyThrows
  public static void main(String[] args) {
    start(Utils.getExecutionType(args), TaskExample.class,
        new Condition(4, 2, 2),
        new Condition(6, 3, 3));
  }

  @SolutionMethod
  protected abstract String sum(int a, int b);
}
