package com.derinbay.testdataservice.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

@Service
public class TestDataHelper {

    private final Logger logger = LoggerFactory.getLogger(TestDataHelper.class);

    SecureRandom secureRandom = new SecureRandom();

    public List<String> generateRandomEmails(int length, int count) {
        return IntStream
                .range(0, count)
                .mapToObj(i -> randomAlphanumeric(length) + "@mailinator.com")
                .collect(Collectors.toList());
    }

    public List<String> generateRandomTcs(int count) {
        logger.info("Generating " + count + " tc numbers");
        return IntStream
                .range(0, count)
                .map(i -> secureRandom
                        .nextInt(100_000_000, 1_000_000_000))
                .mapToObj(TestDataHelper::generateIdFromValue)
                .collect(Collectors.toList());
    }

    private static String generateIdFromValue(int x) {
        var d1 = x / 100_000_000;
        var d2 = x / 10_000_000 % 10;
        var d3 = x / 1_000_000 % 10;
        var d4 = x / 100_000 % 10;
        var d5 = x / 10_000 % 10;
        var d6 = x / 1000 % 10;
        var d7 = x / 100 % 10;
        var d8 = x / 10 % 10;
        var d9 = x % 10;
        var oddSum = d1 + d3 + d5 + d7 + d9;
        var evenSum = d2 + d4 + d6 + d8;
        var firstChecksum = ((oddSum * 7) - evenSum) % 10;

        if (firstChecksum < 0) {
            firstChecksum += 10;
        }
        int secondChecksum = (oddSum + evenSum + firstChecksum) % 10;

        return IntStream
                .of(x, firstChecksum, secondChecksum)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining());
    }

    public List<String> generateMobileNumbers(int count) {
        return IntStream
                .range(0, count)
                .mapToObj(i -> "599" + randomNumeric(7))
                .collect(Collectors.toList());
    }
}
