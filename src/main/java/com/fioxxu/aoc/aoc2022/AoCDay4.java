package com.fioxxu.aoc.aoc2022;

import com.fioxxu.aoc.aoc2022.utils.BufferedReaderHelper;

import java.io.BufferedReader;
import java.io.IOException;

public class AoCDay4 {

    private static final String INPUT_FILE = "aoc_day4_input.txt";
    private static Integer fullyContainingPairs = 0;

    public static void main(String... args) {
        try {
            BufferedReader bufferedReader = BufferedReaderHelper.build(INPUT_FILE);
            String line = bufferedReader.readLine();

            while (null != line) {
                String[] pairs = line.split(",");
                fullyContainingPairs += fullyContainsOtherPair(pairs[0], pairs[1]) ? 1 : 0;
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("The assignments contain " + fullyContainingPairs + " fully containing pairs.");
    }

    private static Boolean fullyContainsOtherPair(String pair1, String pair2) {
        String[] sectionsPair1 = pair1.split("-");
        String[] sectionsPair2 = pair2.split("-");
        int sectionOnePair1 = Integer.parseInt(sectionsPair1[0]);
        int sectionTwoPair1 = Integer.parseInt(sectionsPair1[1]);
        int sectionOnePair2 = Integer.parseInt(sectionsPair2[0]);
        int sectionTwoPair2 = Integer.parseInt(sectionsPair2[1]);

        if ((sectionOnePair1 >= sectionOnePair2 && sectionTwoPair1 <= sectionTwoPair2)
                || (sectionOnePair2 >= sectionOnePair1 && sectionTwoPair2 <= sectionTwoPair1)) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

}
