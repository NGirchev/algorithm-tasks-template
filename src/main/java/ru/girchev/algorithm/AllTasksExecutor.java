package ru.girchev.algorithm;

import ru.girchev.algorithm.tasks2021.binary.Task1BinaryGap;
import ru.girchev.algorithm.tasks2021.fibonacci.Task1FibonacciRecursive;
import ru.girchev.algorithm.tasks2021.fibonacci.Task2FibonacciIteration;
import ru.girchev.algorithm.tasks2021.fibonacci.Task3FibonacciStream;
import ru.girchev.algorithm.tasks2021.number.Task1FindFirstMissingNumber;
import ru.girchev.algorithm.tasks2021.sequence.Task1Sequence;

import static ru.girchev.algorithm.core.Constants.ALL_TYPE_PROPERTY;
import static ru.girchev.algorithm.core.Constants.EACH_SOLUTION_PROPERTY;

/**
 * @author Girchev N.A. Date: 29.01.2021
 */
public class AllTasksExecutor {

  protected static final String[] ALL_TYPE_ARGS = new String[]{ALL_TYPE_PROPERTY};
  protected static final String[] EACH_SOLUTION_ARGS = new String[]{EACH_SOLUTION_PROPERTY};

  public static void main(String[] args) {
    TaskExample.main(ALL_TYPE_ARGS);
    Task1BinaryGap.main(ALL_TYPE_ARGS);
    Task1FibonacciRecursive.main(ALL_TYPE_ARGS);
    Task2FibonacciIteration.main(ALL_TYPE_ARGS);
    Task3FibonacciStream.main(ALL_TYPE_ARGS);
    Task1FindFirstMissingNumber.main(ALL_TYPE_ARGS);
    Task1Sequence.main(ALL_TYPE_ARGS);
//    Task2SquareSeq.main(START_ARGS);
  }
}
