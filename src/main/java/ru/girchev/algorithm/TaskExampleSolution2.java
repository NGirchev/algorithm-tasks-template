package ru.girchev.algorithm;

import lombok.SneakyThrows;

/**
 * @author Girchev N.A. Date: 31.01.2021
 */
public class TaskExampleSolution2 extends TaskExample {

  @SneakyThrows
  @Override
  protected String sum(int a, int b) {
    Thread.sleep(1000L);
    return "" + (a - b);
  }
}
