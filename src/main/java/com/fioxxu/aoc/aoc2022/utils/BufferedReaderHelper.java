package com.fioxxu.aoc.aoc2022.utils;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BufferedReaderHelper {
    private static BufferedReader bufferedReader;

    private BufferedReaderHelper() {
    }

    public static BufferedReader build(String fileName) {
        return new BufferedReader(new InputStreamReader(getFileFromResources(fileName)));
    }

    private static InputStream getFileFromResources(String fileName) {
        ClassLoader classLoader = BufferedReaderHelper.class.getClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }
}
