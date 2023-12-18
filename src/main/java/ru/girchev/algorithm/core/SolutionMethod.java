package ru.girchev.algorithm.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marked annotation for methods, which need to implement in some solution.
 *
 * @author Girchev N.A. Date: 27.01.2021
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SolutionMethod {
}
