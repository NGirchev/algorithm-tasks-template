package ru.girchev.tasks2020.binary;

import static ru.girchev.Utils.executeInBenchmark;
import static ru.girchev.Utils.print;

/**
 * BinaryGap
 * Find longest sequence of zeros in binary representation of an integer.
 *
 * @author Girchev N.A.
 * Date: 11.03.2020
 */
public class Test1BinaryGap {

    public static void main(String[] args) {
        print("BinaryGap:", executeInBenchmark(() -> solution(529))); //1000010001
    }

    public static int solution(int n) {
        int result = 0;
        return result;
    }
}
