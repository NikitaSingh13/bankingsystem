package com.example.bankingsystem;

import com.example.bankingsystem.dto.request.GetBankAccountRequest;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for {@link GetBankAccountRequest}.
 */
class GetBankAccountRequestTest {

    /**
     * Tests that the account number is returned correctly.
     */
    @Test
    void testForGetAccountNumber() {

        String expectedAccountNumber = UUID.randomUUID().toString();
        GetBankAccountRequest request = new GetBankAccountRequest();
        request.setAccountNumber(expectedAccountNumber);

        String actualAccountNumber = request.getAccountNumber();

        assertEquals(expectedAccountNumber, actualAccountNumber);
    }

    /**
     * Tests that the account number is updated correctly.
     */
    @Test
    void testForSetAccountNumber() {

        String expectedAccountNumber = UUID.randomUUID().toString();
        GetBankAccountRequest request = new GetBankAccountRequest();

        request.setAccountNumber(expectedAccountNumber);

        assertEquals(expectedAccountNumber, request.getAccountNumber());
    }
}