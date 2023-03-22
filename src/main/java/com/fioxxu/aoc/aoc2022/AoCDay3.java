package com.fioxxu.aoc.aoc2022;

import com.fioxxu.aoc.aoc2022.utils.BufferedReaderHelper;

import java.io.BufferedReader;
import java.io.IOException;

public class AoCDay3 {
    private static final String INPUT_FILE = "aoc_day3_input.txt";
    private static Integer points = 0;

    public static void main(String... args) {
        try {
            BufferedReader bufferedReader = BufferedReaderHelper.build(INPUT_FILE);
            String line = bufferedReader.readLine();
            while (null != line) {
                String firstCompartment = line.substring(0, line.length() / 2);
                String secondCompartment = line.substring(line.length() / 2);
                points += findMatchingItem(firstCompartment, secondCompartment);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("All shared items their priorities sum to " + points + ".");
    }

    private static Integer findMatchingItem(String firstCompartment, String secondCompartment) {

        for (Character item : firstCompartment.toCharArray()) {
            if (secondCompartment.contains(item.toString())) {
                return Character.isUpperCase(item) ? item - 38 : item - 96;
            }
        }
        return 0;
    }


}
