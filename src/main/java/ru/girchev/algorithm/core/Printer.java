package ru.girchev.algorithm.core;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static ru.girchev.algorithm.core.Constants.*;

/**
 * @author ngirchev@gmail.com on 18.12.2023.
 */
public class Printer {

    public static void print(String tag, Pair<Long, Object> pair, Object expectedResult) {
        boolean success = Objects.deepEquals(expectedResult, pair.getRight());
        String icon = success ? TICK : WARN;
        System.out.println(icon + tag + pair.getRight() + " time:" + pair.getLeft());
        if (!success) {
            System.out.println("Expected : " + expectedResult);
            System.out.println("Actual   : " + pair.getRight());
            showDifferences(expectedResult.toString(), pair.getRight().toString());
        }
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
            if (list.size() > MAX_ARG_COUNT_IN_PRINT) {
                List<String> sublist = list.subList(0, MAX_ARG_COUNT_IN_PRINT);
                result = wrapInBrackets(StringUtils.join(sublist.toArray(), SEPARATOR),
                        list.size() - MAX_ARG_COUNT_IN_PRINT);
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
        if (args.length > MAX_ARG_COUNT_IN_PRINT) {
            result = wrapInBrackets(
                    StringUtils.join(Arrays.copyOf(args, MAX_ARG_COUNT_IN_PRINT), SEPARATOR),
                    args.length - MAX_ARG_COUNT_IN_PRINT
            );
        } else {
            result = StringUtils.join(args, SEPARATOR);
            if (result.length() > MAX_SYMBOL_COUNT_IN_ARG) {
                return LEFT.concat(result.substring(0, MAX_SYMBOL_COUNT_IN_ARG) + THREE_DOTS).concat(RIGHT);
            }
        }
        return result;
    }

    public static String wrapInBrackets(String s, int count) {
        return LEFT.concat(s).concat(THREE_DOTS).concat(" and " + count + " more").concat(RIGHT);
    }

    public static void showDifferences(String str1, String str2) {
        int length = Math.max(str1.length(), str2.length());
        StringBuilder diff = new StringBuilder();

        for (int i = 0; i < length; i++) {
            char ch1 = i < str1.length() ? str1.charAt(i) : ' ';
            char ch2 = i < str2.length() ? str2.charAt(i) : ' ';

            if (ch1 != ch2) {
                diff.append(String.format("Position %d: '%c' vs '%c'%n", i, ch1, ch2));
            }
        }

        if (diff.length() == 0) {
            System.out.println("No differences.");
        } else {
            System.out.println("Differences found:\n" + diff);
        }
    }
}
