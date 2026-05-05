package com.pao.laboratory09.exercise1;

import com.pao.test.IOTest;

public class Checker {
    public static void main(String[] args) {
        IOTest.runParts(
                "src/com/pao/laboratory09/exercise1/tests",
                a -> {
                    try {
                        Main.main(a);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }
}