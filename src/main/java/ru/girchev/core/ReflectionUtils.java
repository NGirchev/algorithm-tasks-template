package ru.girchev.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Set;
import lombok.SneakyThrows;
import org.reflections8.Reflections;

/**
 * Utility class for reflection methods.
 *
 * @author Girchev N.A. Date: 29.01.2021
 */
class ReflectionUtils {

  @SneakyThrows
  static Object invoke(Method solutionMethod, Class<?> aClass, Object... args) {
    return solutionMethod.invoke(aClass.newInstance(), args);
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  static Set<Class> getSubTypes(Class aClass) {
    Reflections reflections = new Reflections(aClass.getPackage());
    return reflections.getSubTypesOf(aClass);
  }

  @SuppressWarnings({"rawtypes"})
  static Method getAbstractMethod(Class aClass) {
    return Arrays.stream(aClass.getDeclaredMethods())
        .filter(method -> Modifier.isAbstract(method.getModifiers()))
        .findFirst()
        .orElse(null);
  }

  @SuppressWarnings({"rawtypes"})
  static Method getSolutionMethod(Class aClass) {
    return Arrays.stream(aClass.getDeclaredMethods())
        .filter(method -> Arrays.stream(method.getAnnotations())
            .map(Annotation::annotationType)
            .anyMatch(a -> a.equals(SolutionMethod.class)))
        .findFirst()
        .orElse(null);
  }
}
