package com.example.bankingsystem;

import com.example.bankingsystem.domain.model.BankAccountDetail;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for {@link BankAccountDetail}.
 */
class BankAccountDetailTest {

    /**
     * Tests that the account holder name is returned correctly.
     */
    @Test
    void testForGetAccountHolderName() {

        String expectedAccountHolderName = UUID.randomUUID().toString();
        BankAccountDetail bankAccountDetail = new BankAccountDetail();
        bankAccountDetail.setAccountHolderName(expectedAccountHolderName);

        String actualAccountHolderName = bankAccountDetail.getAccountHolderName();

        assertEquals(expectedAccountHolderName, actualAccountHolderName);
    }

    /**
     * Tests that the account number is returned correctly.
     */
    @Test
    void testForGetAccountNumber() {

        String expectedAccountNumber =
                "ACC-%06d".formatted(ThreadLocalRandom.current().nextInt(1_000_000));
        BankAccountDetail bankAccountDetail = new BankAccountDetail();
        bankAccountDetail.setAccountNumber(expectedAccountNumber);

        String actualAccountNumber = bankAccountDetail.getAccountNumber();
        assertEquals(expectedAccountNumber, actualAccountNumber);
    }

    /**
     * Tests that the account balance is returned correctly.
     */
    @Test
    void testForGetBalance() {

        BigDecimal expectedBalance =
                BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(1.0, 100000.0));
        BankAccountDetail bankAccountDetail = new BankAccountDetail();
        bankAccountDetail.setBalance(expectedBalance);

        BigDecimal actualBalance = bankAccountDetail.getBalance();

        assertEquals(expectedBalance, actualBalance);
    }

    /**
     * Tests that the account holder name is updated correctly.
     */
    @Test
    void testForSetAccountHolderName() {

        String expectedAccountHolderName = UUID.randomUUID().toString();
        BankAccountDetail bankAccountDetail = new BankAccountDetail();

        bankAccountDetail.setAccountHolderName(expectedAccountHolderName);

        assertEquals(expectedAccountHolderName,
                bankAccountDetail.getAccountHolderName());
    }

    /**
     * Tests that the account number is updated correctly.
     */
    @Test
    void testForSetAccountNumber() {

        String expectedAccountNumber =
                "ACC-%06d".formatted(ThreadLocalRandom.current().nextInt(1_000_000));
        BankAccountDetail bankAccountDetail = new BankAccountDetail();

        bankAccountDetail.setAccountNumber(expectedAccountNumber);

        assertEquals(expectedAccountNumber,
                bankAccountDetail.getAccountNumber());
    }

    /**
     * Tests that the account balance is updated correctly.
     */
    @Test
    void testForSetBalance() {

        BigDecimal expectedBalance =
                BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(1.0, 100000.0));
        BankAccountDetail bankAccountDetail = new BankAccountDetail();

        bankAccountDetail.setBalance(expectedBalance);

        assertEquals(expectedBalance,
                bankAccountDetail.getBalance());
    }
}