package com.derinbay.testdataservice.helpers;

import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;

import java.security.SecureRandom;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.matchesPattern;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@Execution(ExecutionMode.CONCURRENT)
class TestDataHelperTest {

    private final TestDataHelper testDataHelper = new TestDataHelper();

    @Mock
    private SecureRandom secureRandom = mock(SecureRandom.class);

    @Test
    void generateRandomEmailsTest() {
        // Arrange
        int count = 5;

        // Act
        mockStatic(RandomStringUtils.class);
        when(RandomStringUtils.randomAlphanumeric(anyInt())).thenReturn("qwertqwert");
        List<String> actualEmails = testDataHelper.generateRandomEmails(anyInt(), count);

        // Assert
        assertEquals(actualEmails.size(), 5);
        assertTrue(actualEmails.stream().anyMatch(item -> "qwertqwert@mailinator.com".equals(item)));
    }

    @ParameterizedTest
    @CsvSource({"123456789, 12345678950", "221806080, 22180608074"})
    void generateRandomTcsTest(int randomNumber, String expected) {
        // Arrange
        int count = 1;

        // Act
        mock(SecureRandom.class);
        when(secureRandom.nextInt(100_000_000, 1_000_000_000)).thenReturn(randomNumber);
        testDataHelper.secureRandom = secureRandom;
        List<String> actualTcs = testDataHelper.generateRandomTcs(count);

        // Assert
        assertEquals(1, actualTcs.size());
        assertEquals(expected, actualTcs.get(0));
    }

    @Test
    void generateRandomTcsWithCountTest() {
        // Arrange
        int count = 5;
        int expectedSize = 5;

        // Act
        var result = testDataHelper.generateRandomTcs(count);

        // Assert
        assertEquals(expectedSize, result.size());
        for (String tc : result) {
            Assertions.assertNotNull(tc);
            assertEquals(11, tc.length());
            Assertions.assertTrue(tc.matches("\\d{11}"));
        }
    }

    @Test
    void generateMobileNumbersTest() {
        int count = 5;
        List<String> mobileNumbers = testDataHelper.generateMobileNumbers(count);
        assertThat(mobileNumbers, hasSize(count));
        mobileNumbers.forEach(number -> {
            assertThat(number, Matchers.startsWith("599"));
            assertThat(number.substring(3), matchesPattern("[0-9]{7}"));
        });
    }
}
