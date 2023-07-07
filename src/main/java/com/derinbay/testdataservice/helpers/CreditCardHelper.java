package com.derinbay.testdataservice.helpers;

import com.derinbay.testdataservice.models.CreditCard;
import org.springframework.stereotype.Service;

@Service
public class CreditCardHelper {

    public CreditCard getCreditCard() {
        return new CreditCard("5890040000000016", "Akbank", "12/26", 000);
    }
}
