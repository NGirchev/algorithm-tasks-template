package ru.girchev;

import ru.girchev.tasks2021.TaskExample;
import ru.girchev.tasks2021.binary.Task1BinaryGap;
import ru.girchev.tasks2021.fibonacci.Task1FibonacciRecursive;
import ru.girchev.tasks2021.fibonacci.Task2FibonacciIteration;
import ru.girchev.tasks2021.fibonacci.Task3FibonacciStream;
import ru.girchev.tasks2021.number.Task1FindFirstMissingNumber;
import ru.girchev.tasks2021.sequence.Task1Sequence;

import static ru.girchev.core.Constants.ALL_TYPE_PROPERTY;

/**
 * @author Girchev N.A. Date: 29.01.2021
 */
public class AllTasksExecutor {

  protected static final String[] START_ARGS = new String[]{ALL_TYPE_PROPERTY};

  public static void main(String[] args) {
    TaskExample.main(START_ARGS);
    Task1BinaryGap.main(START_ARGS);
    Task1FibonacciRecursive.main(START_ARGS);
    Task2FibonacciIteration.main(START_ARGS);
    Task3FibonacciStream.main(START_ARGS);
    Task1FindFirstMissingNumber.main(START_ARGS);
    Task1Sequence.main(START_ARGS);
//    Task2SquareSeq.main(START_ARGS);
  }

}
