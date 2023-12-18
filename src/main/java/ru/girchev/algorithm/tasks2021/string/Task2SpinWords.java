package ru.girchev.algorithm.tasks2021.string;

import lombok.SneakyThrows;
import ru.girchev.algorithm.core.Condition;
import ru.girchev.algorithm.core.Executor;
import ru.girchev.algorithm.core.SolutionMethod;
import ru.girchev.algorithm.core.Utils;

/**
 * Write a function that takes in a string of one or more words,
 * and returns the same string, but with all five or more letter
 * words reversed.
 * <p>
 * Strings passed in will consist of only letters and spaces.
 * Spaces will be included only when more than one word is present.
 * Examples:
 * spinWords("Hey fellow warriors") => "Hey wollef sroirraw"
 * spinWords("This is a test") => "This is a test"
 * spinWords("This is another test") => "This is rehtona test"
 *
 * @author Girchev N.A. Date: 21.06.2021
 */
public abstract class Task2SpinWords {

  static String input1 = "Hey fellow warriors";
  static String input2 = "Warriors";
  static String input3 = "This is another test";

  @SneakyThrows
  public static void main(String[] args) {
    Executor.start(Utils.getExecutionType(args), Task2SpinWords.class,
            new Condition("Hey wollef sroirraw", input1),
            new Condition("sroirraW", input2),
            new Condition("This is rehtona test", input3));
  }

  @SolutionMethod
  protected abstract String spinWords(String sentence);
}
