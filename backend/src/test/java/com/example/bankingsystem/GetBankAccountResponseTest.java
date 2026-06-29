package com.example.bankingsystem;

import com.example.bankingsystem.dto.response.GetBankAccountResponse;
import com.example.bankingsystem.domain.model.BankAccountDetail;
import com.example.bankingsystem.domain.model.TransferDetail;
import com.example.bankingsystem.domain.entity.TransferType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

/**
 * Tests for {@link GetBankAccountResponse}.
 */
class GetBankAccountResponseTest {

    /**
     * Tests that a transfer is added correctly.
     */
    @Test
    void testForAddTransfer() {

        Long transactionId = ThreadLocalRandom.current().nextLong();
        BigDecimal amount = BigDecimal.valueOf(
                ThreadLocalRandom.current().nextDouble(1.0, 100000.0));
        Date date = new Date(ThreadLocalRandom.current().nextLong());
        String accountNumber = UUID.randomUUID().toString();
        TransferType transferType = ThreadLocalRandom.current().nextBoolean()
                ? TransferType.CREDIT
                : TransferType.DEBIT;

        TransferDetail transfer = new TransferDetail();
        transfer.setTransactionId(transactionId);
        transfer.setAmount(amount);
        transfer.setDate(date);
        transfer.setSourceAccountNumber(accountNumber);
        transfer.setType(transferType);

        GetBankAccountResponse response = new GetBankAccountResponse();

        response.addTransfer(transfer);

        assertEquals(1, response.getTransfers().size());
    }


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

        GetBankAccountResponse response = new GetBankAccountResponse();
        response.setBankAccount(expectedBankAccount);

        BankAccountDetail actualBankAccount = response.getBankAccount();

        assertEquals(expectedBankAccount, actualBankAccount);
    }

    /**
     * Tests that the transfers are returned correctly.
     */
    @Test
    void testForGetTransfers() {

        Long transactionId = ThreadLocalRandom.current().nextLong();
        BigDecimal amount = BigDecimal.valueOf(
                ThreadLocalRandom.current().nextDouble(1.0, 100000.0));
        Date date = new Date(ThreadLocalRandom.current().nextLong());
        String accountNumber = UUID.randomUUID().toString();
        TransferType transferType = ThreadLocalRandom.current().nextBoolean()
                ? TransferType.CREDIT
                : TransferType.DEBIT;

        TransferDetail transfer = new TransferDetail();
        transfer.setTransactionId(transactionId);
        transfer.setAmount(amount);
        transfer.setDate(date);
        transfer.setSourceAccountNumber(accountNumber);
        transfer.setType(transferType);

        GetBankAccountResponse response = new GetBankAccountResponse();
        response.addTransfer(transfer);

        Collection<TransferDetail> transfers = response.getTransfers();

        assertEquals(1, transfers.size());
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

        GetBankAccountResponse response = new GetBankAccountResponse();

        response.setBankAccount(expectedBankAccount);

        assertEquals(expectedBankAccount, response.getBankAccount());
    }



}