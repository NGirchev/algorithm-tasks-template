package ru.girchev.tasks2021.sequence.result;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Girchev N.A.
 * Date: 25.01.2021
 */
public class Task1SequenceSolution1 {

    private static List<Integer> input = Arrays.asList(3, 6, 9, 2, 1, 5);

    public static void main(String[] args) {
        System.out.println("result: " + format(input)); // result: 1-3,5-6,9
    }

    public static String format(List<Integer> in) {
        Collections.sort(in);
        StringBuilder result = new StringBuilder();
        int begin = 0, end = 0, next = 0;
        for (int i = 0; i < in.size(); i++) {
            if (begin == 0) {
                begin = in.get(i);
            }
            if (next == 0) {
                next = in.get(i);
            }
            if (end == 0) {
                end = in.get(i);
            }
            int difference = in.get(i) - next;
            boolean isLast = i == in.size()-1;
            if (difference == 1) {
                next = in.get(i);
                if (isLast) {
                    result.append(begin).append("-").append(next);
                }
            } else if (difference >= 2) {
                end = next;
                result.append(begin).append("-").append(end).append(',');
                begin = in.get(i);
                next = in.get(i);
                end = in.get(i);
                if (isLast) {
                    result.append(in.get(i));
                }
            }
        }

        return result.toString();
    }

}
