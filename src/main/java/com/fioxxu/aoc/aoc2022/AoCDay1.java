package com.fioxxu.aoc.aoc2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class AoCDay1 {

    private static BufferedReader bufferedReader;
    private static Boolean fileEnded = FALSE;
    private static Integer currentHighestCalories = 0;
    private static final String INPUT_FILE = "aoc_day1_input.txt";

    public static void main(String... args) {
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(getFileFromResources()));
            while (fileEnded.equals(FALSE)) {
                Integer result = readElfsCalories();
                currentHighestCalories = result > currentHighestCalories ? result : currentHighestCalories;
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("The Elf that holds the most calories is holding: " + currentHighestCalories + " calories!");
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

    private static InputStream getFileFromResources() {
        ClassLoader classLoader = AoCDay1.class.getClassLoader();
        return classLoader.getResourceAsStream(INPUT_FILE);
    }

}
