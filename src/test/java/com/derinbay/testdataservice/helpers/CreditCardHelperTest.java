package com.derinbay.testdataservice.helpers;

import com.derinbay.testdataservice.models.CreditCard;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.hamcrest.MatcherAssert.assertThat;

@Execution(ExecutionMode.CONCURRENT)
public class CreditCardHelperTest {

    CreditCardHelper creditCardHelper = new CreditCardHelper();

    @Test
    void testGetCreditCard() {
        CreditCard creditCard = creditCardHelper.getCreditCard();

        assertThat(creditCard.number(), Matchers.notNullValue());
        assertThat(creditCard.holderName(), Matchers.notNullValue());
        assertThat(creditCard.expiryDate(), Matchers.notNullValue());
        assertThat(creditCard.cvc(), Matchers.notNullValue());
    }
}
