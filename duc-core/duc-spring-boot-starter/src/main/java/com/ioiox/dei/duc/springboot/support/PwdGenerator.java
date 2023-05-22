package com.ioiox.dei.duc.springboot.support;

import org.apache.commons.lang3.StringUtils;

import java.util.Random;

public class PwdGenerator {

    public static final String PWD_LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final Random random = new Random();

    public static String randomPassword(final int lengthOfPwd) {
        final StringBuilder randomPassword = new StringBuilder();
        final int numOfPwdLetters = PWD_LETTERS.length();
        for (int i = 0 ; i < lengthOfPwd ; i++) {
            final int randomIdx = random.nextInt(numOfPwdLetters);
            randomPassword.append(StringUtils.substring(PWD_LETTERS, randomIdx, randomIdx + 1));
        }
        return randomPassword.toString();
    }

    public static void main(String [] args) {
        System.out.println(randomPassword(6));
    }
}
