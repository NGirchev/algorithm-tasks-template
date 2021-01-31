package ru.girchev.core;

import static ru.girchev.core.Constants.LEFT;
import static ru.girchev.core.Constants.RIGHT;
import static ru.girchev.core.Constants.SEPARATOR;
import static ru.girchev.core.Constants.THREE_DOTS;
import static ru.girchev.core.Constants.TICK;
import static ru.girchev.core.Constants.WARN;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Common utility.
 *
 * @author Girchev N.A. Date: 11.03.2020
 */
public class Utils {

  private static final int MAX_COUNT = 8;

  public static void print(String tag, Pair<Long, Object> pair, Boolean success) {
    String icon = success ? TICK : WARN;
    System.out.println(icon + tag + pair.getRight() + " time:" + pair.getLeft());
  }

  public static void print(String tag, Pair<Long, Object> pair) {
    System.out.println(tag + pair.getRight() + " time:" + pair.getLeft());
  }

  public static void print(String tag, Boolean success) {
    String icon = success ? TICK : WARN;
    System.out.println(icon + tag);
  }

  public static String castObjArrayToString(Object arg) {
    String result;
    if (arg.getClass().isArray()) {
      int length = Array.getLength(arg);
      List<String> list = new ArrayList<>(length);
      for (int i = 0; i < length; i++) {
        list.add(String.valueOf(Array.get(arg, i)));
      }
      if (list.size() > MAX_COUNT) {
        List<String> sublist = list.subList(0, MAX_COUNT);
        result = wrapInBrackets(StringUtils.join(sublist.toArray(), SEPARATOR),
            list.size() - MAX_COUNT);
      } else {
        result = list.toString();
      }
    } else {
      result = String.valueOf(arg);
    }
    return result;
  }

  public static String createConditionOutput(Object[] args) {
    String result;
    if (args.length > MAX_COUNT) {
      result = wrapInBrackets(
          StringUtils.join(Arrays.copyOf(args, MAX_COUNT), SEPARATOR),
          args.length - MAX_COUNT
      );
    } else {
      result = StringUtils.join(args, SEPARATOR);
    }
    return result;
  }

  public static String wrapInBrackets(String s, int count) {
    return LEFT.concat(s).concat(THREE_DOTS).concat(" and " + count + " more").concat(RIGHT);
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
