package ru.girchev.algorithm.core;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Data wrapper for task condition.
 *
 * @author Girchev N.A. Date: 31.01.2021
 */
@Data
@Accessors(fluent = true)
public class Condition {

  private Object expectedResult;
  private Object[] args;

  public Condition(Object expectedResult, Object... args) {
    this.expectedResult = expectedResult;
    this.args = args;
  }
}
