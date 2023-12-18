package ru.girchev.algorithm.core;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import ru.girchev.algorithm.AllTasksExecutor;

/**
 * Supports two execution type:<br> - ALL tasks from AllTasksExecutor<br> - SINGLE task, but all
 * solutions for that
 *
 * @author Girchev N.A. Date: 30.01.2021
 * @see AllTasksExecutor
 * @see Executor
 */
public enum ExecutionType {
  SINGLE, ALL;

  public static final Set<String> VALUES_AS_STRING = Arrays
      .stream(ExecutionType.values())
      .map(Objects::toString)
      .map(Constants.DASH::concat)
      .map(String::toLowerCase)
      .collect(Collectors.toSet());
}
