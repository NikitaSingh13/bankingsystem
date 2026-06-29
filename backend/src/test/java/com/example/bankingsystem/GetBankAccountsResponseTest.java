package com.example.bankingsystem;

import com.example.bankingsystem.dto.response.GetBankAccountsResponse;
import com.example.bankingsystem.domain.model.BankAccountDetail;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

/**
 * Tests for {@link GetBankAccountsResponse}.
 */
class GetBankAccountsResponseTest {

    /**
     * Tests that a bank account is added correctly.
     */
    @Test
    void testForAddBankAccount() {

        String accountNumber =
                "ACC-%06d".formatted(ThreadLocalRandom.current().nextInt(1_000_000));
        String accountHolderName = UUID.randomUUID().toString();
        BigDecimal balance = BigDecimal.valueOf(
                ThreadLocalRandom.current().nextDouble(1.0, 100000.0));

        BankAccountDetail bankAccount = new BankAccountDetail();
        bankAccount.setAccountNumber(accountNumber);
        bankAccount.setAccountHolderName(accountHolderName);
        bankAccount.setBalance(balance);

        GetBankAccountsResponse response = new GetBankAccountsResponse();

        response.addBankAccount(bankAccount);

        assertEquals(1, response.getBankAccounts().size());
    }

    /**
     * Tests that the bank accounts are returned correctly.
     */
    @Test
    void testForGetBankAccounts() {

        String accountNumber =
                "ACC-%06d".formatted(ThreadLocalRandom.current().nextInt(1_000_000));
        String accountHolderName = UUID.randomUUID().toString();
        BigDecimal balance = BigDecimal.valueOf(
                ThreadLocalRandom.current().nextDouble(1.0, 100000.0));

        BankAccountDetail bankAccount = new BankAccountDetail();
        bankAccount.setAccountNumber(accountNumber);
        bankAccount.setAccountHolderName(accountHolderName);
        bankAccount.setBalance(balance);

        GetBankAccountsResponse response = new GetBankAccountsResponse();
        response.addBankAccount(bankAccount);

        Collection<BankAccountDetail> bankAccounts = response.getBankAccounts();

        assertEquals(1, bankAccounts.size());
    }

}