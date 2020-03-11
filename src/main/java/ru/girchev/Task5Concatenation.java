package ru.girchev;

import java.util.ArrayList;
import java.util.List;

import static ru.girchev.Utils.executeInBenchmark;
import static ru.girchev.Utils.print;

/**
 * JOIN AND SB is FASTER, but at different times they work differently
 *
 * @author Girchev N.A.
 * Date: 11.03.2020
 */
public class Task5Concatenation {

    private static List<String> createBigArray(int size) {
        List<String> strings = new ArrayList<>();
        for (int i=0; i<size; i++) {
            strings.add(String.valueOf(i));
        }
        return strings;
    }

    public static void main(String[] args) {
        System.out.println("TEST1 small data");
        final List<String> smallArray = createBigArray(10000);
        print("SB: ", executeInBenchmark(() -> concatStrings1(smallArray)));
        print("JOIN: ", executeInBenchmark(() -> concatStrings2(smallArray)));
        print("Concatination: ", executeInBenchmark(() -> concatStrings3(smallArray)));
        print("Concatination2: ", executeInBenchmark(() -> concatStrings4(smallArray)));
        System.out.println();
        System.out.println("TEST2 big data");
        final List<String> bigArray = createBigArray(1000000);
        print("SB: ", executeInBenchmark(() -> concatStrings1(bigArray)));
        print("JOIN: ", executeInBenchmark(() -> concatStrings2(bigArray)));
        print("Concatination: ", executeInBenchmark(() -> concatStrings3(bigArray)));
        print("Concatination2: ", executeInBenchmark(() -> concatStrings4(bigArray)));
    }

    public static String concatStrings1(List<String> strings) {
        StringBuilder returnValue = new StringBuilder();
        for (String s : strings) {
            returnValue.append(s);
        }
        return (returnValue.length() < 6) ? returnValue.toString() : returnValue.toString().substring(0, 5) + "...";

    }

    public static String concatStrings2(List<String> strings) {
        String result = String.join("", strings);
        return (result.length() < 6) ? result : result.substring(0, 5) + "...";
    }

    public static String concatStrings3(List<String> strings) {
        String result = "";
        for (String s : strings) {
            result += s;
        }
        return (result.length() < 6) ? result : result.substring(0, 5) + "...";
    }

    public static String concatStrings4(List<String> strings) {
        String result = "";
        for (String s : strings) {
            result = result.concat(s);
        }
        return (result.length() < 6) ? result : result.substring(0, 5) + "...";
    }

//    TEST1 small data
//    SB: 01234... time:0
//    JOIN: 01234... time:0
//    Concatination: 01234... time:6
//    Concatination2: 01234... time:1
//
//    TEST2 big data
//    SB: 01234... time:341
//    JOIN: 01234... time:36
//    Concatination: null time:3000
//    Concatination2: null time:3000

    //ANOTHER EXECUTIONS

//    TEST1 small data
//    SB: 01234... time:6
//    JOIN: 01234... time:3
//    Concatination: 01234... time:408
//    Concatination2: 01234... time:64
//
//    TEST2 big data
//    SB: 01234... time:26
//    JOIN: 01234... time:34
//    Concatination: null time:3000
//    Concatination2: null time:3000

}
