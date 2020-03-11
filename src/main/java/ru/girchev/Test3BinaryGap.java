package ru.girchev;

import static ru.girchev.Utils.executeInBenchmark;
import static ru.girchev.Utils.print;

/**
 * @author Girchev N.A.
 * Date: 11.03.2020
 */
public class Test3BinaryGap {

    public static void main(String[] args) {
        print("Test", executeInBenchmark(() -> solution(529))); //1000010001
    }

    public static int solution(int n) {
        int result = 0;
        int count = 0;
        String s = Integer.toBinaryString(n);
        for (int i=0; i< s.length(); i++) {
            if (s.charAt(i) == '0') {
                count++;
            }
            if (s.charAt(i) == '1') {
                if (result < count) {
                    result = count;
                }
                count = 0;
            }
        }
        return result;
    }
}
