package com.ruler.auth.utils;

import java.util.Random;

public class RandomCodeGenerator {
    public static String generateSixDigitCode() {
        Random random = new Random();
        int num = random.nextInt(900000) + 100000;
        return String.valueOf(num);
    }
}
