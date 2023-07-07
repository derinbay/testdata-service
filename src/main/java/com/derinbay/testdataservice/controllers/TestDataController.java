package com.derinbay.testdataservice.controllers;

import com.derinbay.testdataservice.helpers.ImeiHelper;
import com.derinbay.testdataservice.models.CreditCard;
import com.derinbay.testdataservice.helpers.CreditCardHelper;
import com.derinbay.testdataservice.helpers.TestDataHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestDataController {

    private final TestDataHelper testDataHelper;
    private final CreditCardHelper creditCardHelper;

    private final ImeiHelper imeiHelper;

    @Autowired
    public TestDataController(TestDataHelper testDataHelper, CreditCardHelper creditCardHelper, ImeiHelper imeiHelper) {
        this.testDataHelper = testDataHelper;
        this.creditCardHelper = creditCardHelper;
        this.imeiHelper = imeiHelper;
    }

    @GetMapping("/email")
    public ResponseEntity<List<String>> getEmail(int length, int count) {
        List<String> emails = testDataHelper.generateRandomEmails(length, count);
        return new ResponseEntity<>(emails, HttpStatus.OK);
    }

    @GetMapping("/tc")
    public ResponseEntity<List<String>> getTcs(int count) {
        List<String> tcs = testDataHelper.generateRandomTcs(count);
        return new ResponseEntity<>(tcs, HttpStatus.OK);
    }

    @GetMapping("/mobile-number")
    public ResponseEntity<List<String>> getMobileNumbers(int count) {
        List<String> mobileNumbers = testDataHelper.generateMobileNumbers(count);
        return new ResponseEntity<>(mobileNumbers, HttpStatus.OK);
    }

    @GetMapping("/cc")
    public ResponseEntity<CreditCard> getCreditCard() {
        CreditCard creditCard = creditCardHelper.getCreditCard();
        return new ResponseEntity<>(creditCard, HttpStatus.OK);
    }

    @GetMapping("/imei")
    public ResponseEntity<List<String>> getImei(int count) {
        List<String> imeis = imeiHelper.generateIMEI(count);
        return new ResponseEntity<>(imeis, HttpStatus.OK);
    }
}
