package com.derinbay.testdataservice.models;

public record CreditCard(String number, String holderName, String expiryDate, int cvc) {
}