package com.example.bankingsystem;

import com.example.bankingsystem.dto.response.CreateBankAccountResponse;
import com.example.bankingsystem.domain.model.BankAccountDetail;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for {@link CreateBankAccountResponse}.
 */
class CreateBankAccountResponseTest {

    /**
     * Tests that the bank account is returned correctly.
     */
    @Test
    void testForGetBankAccount() {

        String expectedAccountNumber =
                "ACC-%06d".formatted(ThreadLocalRandom.current().nextInt(1_000_000));
        String expectedAccountHolderName = UUID.randomUUID().toString();
        BigDecimal expectedBalance = BigDecimal.valueOf(
                ThreadLocalRandom.current().nextDouble(1.0, 100000.0));

        BankAccountDetail expectedBankAccount = new BankAccountDetail();
        expectedBankAccount.setAccountNumber(expectedAccountNumber);
        expectedBankAccount.setAccountHolderName(expectedAccountHolderName);
        expectedBankAccount.setBalance(expectedBalance);

        CreateBankAccountResponse response = new CreateBankAccountResponse();
        response.setBankAccount(expectedBankAccount);

        BankAccountDetail actualBankAccount = response.getBankAccount();

        assertEquals(expectedBankAccount, actualBankAccount);
    }

    /**
     * Tests that the bank account is updated correctly.
     */
    @Test
    void testForSetBankAccount() {

        String expectedAccountNumber =
                "ACC-%06d".formatted(ThreadLocalRandom.current().nextInt(1_000_000));
        String expectedAccountHolderName = UUID.randomUUID().toString();
        BigDecimal expectedBalance = BigDecimal.valueOf(
                ThreadLocalRandom.current().nextDouble(1.0, 100000.0));

        BankAccountDetail expectedBankAccount = new BankAccountDetail();
        expectedBankAccount.setAccountNumber(expectedAccountNumber);
        expectedBankAccount.setAccountHolderName(expectedAccountHolderName);
        expectedBankAccount.setBalance(expectedBalance);

        CreateBankAccountResponse response = new CreateBankAccountResponse();

        response.setBankAccount(expectedBankAccount);

        assertEquals(expectedBankAccount, response.getBankAccount());
    }
}