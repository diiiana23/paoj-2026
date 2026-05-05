package com.pao.laboratory09.exercise2;

import com.pao.test.IOTest;

public class Checker {
    public static void main(String[] args) {
        IOTest.runFlat(
                "src/com/pao/laboratory09/exercise2/tests",
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