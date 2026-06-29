package com.example.bankingsystem;

import com.example.bankingsystem.dto.request.CreateBankAccountRequest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for {@link CreateBankAccountRequest}.
 */
class CreateBankAccountRequestTest {

    /**
     * Tests that the account holder name is returned correctly.
     */
    @Test
    void testForGetAccountHolderName() {

        String expectedAccountHolderName = UUID.randomUUID().toString();
        CreateBankAccountRequest request = new CreateBankAccountRequest();
        request.setAccountHolderName(expectedAccountHolderName);

        String actualAccountHolderName = request.getAccountHolderName();

        assertEquals(expectedAccountHolderName, actualAccountHolderName);
    }

    /**
     * Tests that the account balance is returned correctly.
     */
    @Test
    void testForGetBalance() {

        BigDecimal expectedBalance = BigDecimal.valueOf(
                ThreadLocalRandom.current().nextDouble(1.0, 100000.0));
        CreateBankAccountRequest request = new CreateBankAccountRequest();
        request.setBalance(expectedBalance);

        BigDecimal actualBalance = request.getBalance();

        assertEquals(expectedBalance, actualBalance);
    }

    /**
     * Tests that the account holder name is updated correctly.
     */
    @Test
    void testForSetAccountHolderName() {

        String expectedAccountHolderName = UUID.randomUUID().toString();
        CreateBankAccountRequest request = new CreateBankAccountRequest();

        request.setAccountHolderName(expectedAccountHolderName);

        assertEquals(expectedAccountHolderName,
                request.getAccountHolderName());
    }

    /**
     * Tests that the account balance is updated correctly.
     */
    @Test
    void testForSetBalance() {

        BigDecimal expectedBalance = BigDecimal.valueOf(
                ThreadLocalRandom.current().nextDouble(1.0, 100000.0));
        CreateBankAccountRequest request = new CreateBankAccountRequest();

        request.setBalance(expectedBalance);

        assertEquals(expectedBalance, request.getBalance());
    }
}