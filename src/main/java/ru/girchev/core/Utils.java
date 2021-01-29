package ru.girchev.core;

import java.util.Arrays;
import org.apache.commons.lang3.tuple.Pair;

/**
 * @author Girchev N.A. Date: 11.03.2020
 */
public class Utils {

  public static void print(String tag, Pair<Long, Object> pair) {
    System.out.println(tag + pair.getRight() + " time:" + pair.getLeft());
  }

  public static void print(String tag, Boolean success) {
    if (success) {
      System.out.println("✔" + tag);
    } else {
      System.out.println("⚠" + tag);
    }
  }

  public static ExecutionType getExecutionType(String[] args) {
    ExecutionType result = ExecutionType.SINGLE;
    if (args != null && args.length != 0) {
      result = Arrays.stream(args)
          .map(String::toLowerCase)
          .filter(ExecutionType.VALUES_AS_STRING::contains)
          .map(arg -> arg.substring(1))
          .map(String::toUpperCase)
          .map(ExecutionType::valueOf)
          .findFirst()
          .orElse(ExecutionType.SINGLE);
    }
    return result;
  }
}
