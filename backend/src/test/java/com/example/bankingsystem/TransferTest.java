package com.example.bankingsystem;

import com.example.bankingsystem.domain.entity.BankAccount;
import com.example.bankingsystem.domain.entity.Transfer;
import com.example.bankingsystem.domain.entity.TransferType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for {@link Transfer}.
 */
class TransferTest {

    /**
     * Tests that the transfer amount is returned correctly.
     */
    @Test
    void testForGetAmount() {

        BigDecimal expectedAmount = BigDecimal.valueOf(
                ThreadLocalRandom.current().nextDouble(1.0, 100000.0));
        Transfer transfer = new Transfer(
                expectedAmount,
                new Date(),
                new BankAccount(),
                new BankAccount(),
                TransferType.CREDIT);

        BigDecimal actualAmount = transfer.getAmount();

        assertEquals(expectedAmount, actualAmount);
    }

    /**
     * Tests that the transfer date is returned correctly.
     */
    @Test
    void testForGetDate() {

        Date expectedDate = new Date(ThreadLocalRandom.current().nextLong());
        Transfer transfer = new Transfer(
                BigDecimal.ONE,
                expectedDate,
                new BankAccount(),
                new BankAccount(),
                TransferType.CREDIT);

        Date actualDate = transfer.getDate();

        assertEquals(expectedDate, actualDate);
    }

    /**
     * Tests that the source account is returned correctly.
     */
    @Test
    void testForGetSourceAccount() {

        BankAccount expectedSourceAccount = new BankAccount();
        expectedSourceAccount.setAccountNumber(UUID.randomUUID().toString());

        Transfer transfer = new Transfer(
                BigDecimal.ONE,
                new Date(),
                expectedSourceAccount,
                new BankAccount(),
                TransferType.CREDIT);

        BankAccount actualSourceAccount = transfer.getSourceAccount();

        assertEquals(expectedSourceAccount, actualSourceAccount);
    }

    /**
     * Tests that the target account is returned correctly.
     */
    @Test
    void testForGetTargetAccount() {

        BankAccount expectedTargetAccount = new BankAccount();
        expectedTargetAccount.setAccountNumber(UUID.randomUUID().toString());

        Transfer transfer = new Transfer(
                BigDecimal.ONE,
                new Date(),
                new BankAccount(),
                expectedTargetAccount,
                TransferType.CREDIT);

        BankAccount actualTargetAccount = transfer.getTargetAccount();

        assertEquals(expectedTargetAccount, actualTargetAccount);
    }

    /**
     * Tests that the transaction identifier is null before persistence.
     */
    @Test
    void testForGetTransactionId() {

        Transfer transfer = new Transfer();

        assertEquals(null, transfer.getTransactionId());
    }

    /**
     * Tests that the transfer type is returned correctly.
     */
    @Test
    void testForGetType() {

        TransferType expectedTransferType = ThreadLocalRandom.current().nextBoolean()
                ? TransferType.CREDIT
                : TransferType.DEBIT;

        Transfer transfer = new Transfer(
                BigDecimal.ONE,
                new Date(),
                new BankAccount(),
                new BankAccount(),
                expectedTransferType);

        TransferType actualTransferType = transfer.getType();

        assertEquals(expectedTransferType, actualTransferType);
    }
}