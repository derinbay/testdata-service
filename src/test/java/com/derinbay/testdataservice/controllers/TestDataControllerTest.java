package com.derinbay.testdataservice.controllers;

import com.derinbay.testdataservice.helpers.ImeiHelper;
import com.derinbay.testdataservice.models.CreditCard;
import com.derinbay.testdataservice.helpers.CreditCardHelper;
import com.derinbay.testdataservice.helpers.TestDataHelper;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TestDataControllerTest {

    private final TestDataHelper testDataHelper = mock(TestDataHelper.class);
    private final CreditCardHelper creditCardHelper = mock(CreditCardHelper.class);
    private final ImeiHelper imeiHelper = mock(ImeiHelper.class);
    private final TestDataController controller = new TestDataController(testDataHelper, creditCardHelper, imeiHelper);

    @Test
    void testGetEmail() {
        // Arrange
        List<String> expectedEmails = Arrays.asList("email1", "email2", "email3");

        // Act
        when(testDataHelper.generateRandomEmails(anyInt(), anyInt())).thenReturn(expectedEmails);
        ResponseEntity<List<String>> response = controller.getEmail(anyInt(), anyInt());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedEmails, response.getBody());
    }

    @Test
    void getTcs() {
        // Arrange
        List<String> expectedTcs = Arrays.asList("11111111111", "22222222222");
        when(testDataHelper.generateRandomTcs(anyInt())).thenReturn(expectedTcs);

        // Act
        ResponseEntity<List<String>> response = controller.getTcs(2);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedTcs, response.getBody());
    }

    @Test
    void getMobileNumbers() {
        // Arrange
        List<String> expectedMobileNumbers = Arrays.asList("5555555555", "5556666666");
        when(testDataHelper.generateMobileNumbers(anyInt())).thenReturn(expectedMobileNumbers);

        // Act
        ResponseEntity<List<String>> response = controller.getMobileNumbers(2);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedMobileNumbers, response.getBody());
    }

    @Test
    void getCreditCard() {
        // Arrange
        CreditCard expectedCreditCard = new CreditCard("1123", "name", "12/26", 123);
        when(creditCardHelper.getCreditCard()).thenReturn(expectedCreditCard);

        // Act
        ResponseEntity<CreditCard> response = controller.getCreditCard();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedCreditCard, response.getBody());
    }
}
