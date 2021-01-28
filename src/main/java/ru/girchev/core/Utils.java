package ru.girchev.core;

import org.apache.commons.lang3.tuple.Pair;

/**
 * @author Girchev N.A. Date: 11.03.2020
 */
public class Utils {

  public static void print(String tag, Pair<Long, Object> pair) {
    System.out.println(tag + pair.getRight() + " time:" + pair.getLeft());
  }
}
