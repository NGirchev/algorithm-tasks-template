package ru.girchev.tasks2021.binary;

import lombok.SneakyThrows;

/**
 * @author Girchev N.A. Date: 31.01.2021
 */
public class Test1BinaryGapImpl extends Test1BinaryGap {

  @SneakyThrows
  @Override
  protected int findGap(int n) {
    Thread.sleep(4000);
    return 4;
  }
}
