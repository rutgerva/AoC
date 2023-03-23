package com.fioxxu.aoc.aoc2022;

import com.fioxxu.aoc.aoc2022.utils.BufferedReaderHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AoCDay3 {
    private static final String INPUT_FILE = "aoc_day3_input.txt";
    private static Integer pointsOfCommonItem = 0;
    private static Integer pointsOfGroups = 0;

    public static void main(String... args) {
        try {
            BufferedReader bufferedReader = BufferedReaderHelper.build(INPUT_FILE);
            String line = bufferedReader.readLine();
            List<String> ruckSacksOfGroup = new ArrayList<>();
            while (null != line) {
                String firstCompartment = line.substring(0, line.length() / 2);
                String secondCompartment = line.substring(line.length() / 2);
                pointsOfCommonItem += findMatchingItem(firstCompartment, secondCompartment);
                ruckSacksOfGroup.add(line);
                if (ruckSacksOfGroup.size() == 3) {
                    pointsOfGroups += findCommonGroup(ruckSacksOfGroup);
                    ruckSacksOfGroup.clear();
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("All shared items their priorities sum to " + pointsOfCommonItem + ".");
        System.out.println("All group badges their priorities sum to " + pointsOfGroups + ".");
    }

    private static Integer findMatchingItem(String firstCompartment, String secondCompartment) {

        for (Character item : firstCompartment.toCharArray()) {
            if (secondCompartment.contains(item.toString())) {
                return Character.isUpperCase(item) ? item - 38 : item - 96;
            }
        }
        return 0;
    }

    private static Integer findCommonGroup(List<String> ruckSacks) {
        if (ruckSacks.size() < 3) {
            return 0;
        }
        for (Character item : ruckSacks.get(0).toCharArray()) {
            if (ruckSacks.get(1).contains(item.toString()) && ruckSacks.get(2).contains(item.toString())) {
                return Character.isUpperCase(item) ? item - 38 : item - 96;
            }
        }
        return 0;
    }
}
