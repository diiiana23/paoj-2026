package com.pao.test;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class IOTest {

    public static void runParts(String basePath, java.util.function.Consumer<String[]> mainMethod) {
        runPart(basePath, "partA", mainMethod);
        runPart(basePath, "partB", mainMethod);
        runPart(basePath, "partC", mainMethod);
    }

    public static void runPart(String basePath, String part, java.util.function.Consumer<String[]> mainMethod) {
        File dir = new File(basePath + "/" + part);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".in"));

        if (files == null) return;

        Arrays.sort(files);

        for (File inputFile : files) {
            try {
                String input = Files.readString(inputFile.toPath());
                String expectedOutput = Files.readString(
                        Path.of(inputFile.getAbsolutePath().replace(".in", ".out"))
                ).trim();

                InputStream originalIn = System.in;
                PrintStream originalOut = System.out;

                ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
                ByteArrayOutputStream out = new ByteArrayOutputStream();

                System.setIn(in);
                System.setOut(new PrintStream(out));

                mainMethod.accept(new String[]{});

                System.setIn(originalIn);
                System.setOut(originalOut);

                String actualOutput = out.toString().trim();

                if (actualOutput.equals(expectedOutput)) {
                    System.out.println(inputFile.getName() + " ✅ PASS");
                } else {
                    System.out.println(inputFile.getName() + " ❌ FAIL");
                    System.out.println("Expected:\n" + expectedOutput);
                    System.out.println("Got:\n" + actualOutput);
                }

            } catch (Exception e) {
                System.out.println(inputFile.getName() + " ❌ ERROR");
                e.printStackTrace();
            }
        }
    }

    public static void runFlat(String basePath, java.util.function.Consumer<String[]> mainMethod) {
        File dir = new File(basePath);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".in"));

        if (files == null) return;

        Arrays.sort(files);

        for (File inputFile : files) {
            try {
                String input = Files.readString(inputFile.toPath());
                String expectedOutput = Files.readString(
                        Path.of(inputFile.getAbsolutePath().replace(".in", ".out"))
                ).trim();

                InputStream originalIn = System.in;
                PrintStream originalOut = System.out;

                ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
                ByteArrayOutputStream out = new ByteArrayOutputStream();

                System.setIn(in);
                System.setOut(new PrintStream(out));

                mainMethod.accept(new String[]{});

                System.setIn(originalIn);
                System.setOut(originalOut);

                String actualOutput = out.toString().trim();

                if (actualOutput.equals(expectedOutput)) {
                    System.out.println(inputFile.getName() + " ✅ PASS");
                } else {
                    System.out.println(inputFile.getName() + " ❌ FAIL");
                    System.out.println("Expected:\n" + expectedOutput);
                    System.out.println("Got:\n" + actualOutput);
                }

            } catch (Exception e) {
                System.out.println(inputFile.getName() + " ❌ ERROR");
                e.printStackTrace();
            }
        }
    }
}