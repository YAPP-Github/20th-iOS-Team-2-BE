package com.yapp.supporter.util;

import java.util.Random;

/**
 * Author : daehwan2yo
 * Date : 2022/08/02
 * Info :
 **/
public class CodeGenerateUtils {
    private final static int START_LETTER_ASCII = 97;
    private final static int END_LETTER_ASCII = 122;
    private final static int LENGTH = 10;

    public static String code() {
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(LENGTH);

        for (int i = 0; i < LENGTH; i++) {
            int randomLimitedInt = START_LETTER_ASCII + (int) (random.nextFloat() * (END_LETTER_ASCII - START_LETTER_ASCII + 1));
            buffer.append((char) randomLimitedInt);
        }

        return buffer.toString();
    }
}
