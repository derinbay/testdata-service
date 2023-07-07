package com.derinbay.testdataservice.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ImeiHelper {

    private final Logger logger = LoggerFactory.getLogger(ImeiHelper.class);

    private static final Random rand = new SecureRandom();

    public static List<String> generateIMEI(int imeiCount) {
        List<String> imeiNumbers = new ArrayList<>();
        for (int i = 0; i < imeiCount; i++) {
            String imei = generateIMEI();
            imeiNumbers.add(imei);
        }
        return imeiNumbers;
    }

    public static String generateIMEI() {
        String randomDigits = generateRandomDigits(14);
        int checkDigit = calculateCheckDigit(randomDigits);
        return randomDigits + checkDigit;
    }

    private static String generateRandomDigits(int digitCount) {
        StringBuilder digits = new StringBuilder(digitCount);
        for (int i = 0; i < digitCount; i++) {
            digits.append(rand.nextInt(10));
        }
        return digits.toString();
    }

    private static int calculateCheckDigit(String imei) {
        int sum = 0;
        for (int i = 0; i < imei.length(); i++) {
            int digit = Integer.parseInt(imei.substring(i, i + 1));
            sum += calculateSum(i, digit);
        }
        return calculateCheckDigit(sum);
    }

    private static int calculateSum(int index, int digit) {
        if (index % 2 == 0) {
            return digit;
        } else {
            return ((digit * 2) > 9) ? (digit * 2) - 9 : (digit * 2);
        }
    }

    private static int calculateCheckDigit(int sum) {
        return (sum % 10 == 0) ? 0 : 10 - (sum % 10);
    }
}
