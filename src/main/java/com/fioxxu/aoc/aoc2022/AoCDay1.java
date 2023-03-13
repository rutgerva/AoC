package com.fioxxu.aoc.aoc2022;

import com.fioxxu.aoc.aoc2022.utils.BufferedReaderHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class AoCDay1 {

    private static BufferedReader bufferedReader;
    private static Boolean fileEnded = FALSE;
    private static Integer currentHighestCalories = 0;
    private static Integer topThreeSumCalories;
    private static final ArrayList<Integer> highestCaloriesCarried = new ArrayList<>();
    private static final String INPUT_FILE = "aoc_day1_input.txt";

    public static void main(String... args) {
        compute();
        System.out.println("The Elf that holds the most calories is holding: " + currentHighestCalories + " calories!");
        System.out.println("The sum of the top 3 calories is: " + topThreeSumCalories + " calories!");
    }

    private static void compute() {
        try {
            bufferedReader = BufferedReaderHelper.build(INPUT_FILE);
            while (fileEnded.equals(FALSE)) {
                Integer result = readElfsCalories();
                currentHighestCalories = currentHighestCalories < result ? result : currentHighestCalories;
                updateTopCaloriesArray(result);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        topThreeSumCalories = highestCaloriesCarried.stream().mapToInt(i -> i)
                .sum();
    }

    private static Integer readElfsCalories() throws IOException {
        Integer result = 0;
        String line = bufferedReader.readLine();

        while (null != line) {
            result += Integer.parseInt(line);
            line = bufferedReader.readLine();
            if ("".equals(line))
                return result;
        }
        fileEnded = TRUE;
        return result;
    }

    private static void updateTopCaloriesArray(Integer replacementCandidate) {
        if (highestCaloriesCarried.size() != 3) {
            highestCaloriesCarried.add(replacementCandidate);
        } else {
            int indexToReplace = -1;
            int currentCompare = replacementCandidate;
            for (int i = 0; i < highestCaloriesCarried.size(); i++) {
                if (highestCaloriesCarried.get(i) < currentCompare) {
                    indexToReplace = i;
                    currentCompare = highestCaloriesCarried.get(i);
                }
            }
            if (-1 != indexToReplace)
                highestCaloriesCarried.set(indexToReplace, replacementCandidate);
        }
    }
}
