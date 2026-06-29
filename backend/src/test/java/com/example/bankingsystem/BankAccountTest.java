package com.example.bankingsystem;

import com.example.bankingsystem.domain.entity.BankAccount;
import com.example.bankingsystem.domain.entity.Transfer;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

/**
 * Tests for {@link BankAccount}.
 */
class BankAccountTest {

    /**
     * Tests that a source transfer is added correctly.
     */
    @Test
    void testForAddSourceTransfer() {

        BankAccount bankAccount = new BankAccount();
        Transfer transfer = new Transfer();

        bankAccount.addSourceTransfer(transfer);

        assertEquals(1, bankAccount.getSourceTransfers().size());
    }

    /**
     * Tests that a target transfer is added correctly.
     */
    @Test
    void testForAddTargetTransfer() {

        BankAccount bankAccount = new BankAccount();
        Transfer transfer = new Transfer();

        bankAccount.addTargetTransfer(transfer);

        assertEquals(1, bankAccount.getTargetTransfers().size());
    }

    /**
     * Tests that the account holder name is returned correctly.
     */
    @Test
    void testForGetAccountHolderName() {

        String expectedAccountHolderName = UUID.randomUUID().toString();
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountHolderName(expectedAccountHolderName);

        String actualAccountHolderName = bankAccount.getAccountHolderName();

        assertEquals(expectedAccountHolderName, actualAccountHolderName);
    }

    /**
     * Tests that the account number is returned correctly.
     */
    @Test
    void testForGetAccountNumber() {

        String expectedAccountNumber =
                "ACC%08d".formatted(
                        ThreadLocalRandom.current().nextInt(100_000_000));
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountNumber(expectedAccountNumber);

        String actualAccountNumber = bankAccount.getAccountNumber();

        assertEquals(expectedAccountNumber, actualAccountNumber);
    }

    /**
     * Tests that the account balance is returned correctly.
     */
    @Test
    void testForGetBalance() {

        BigDecimal expectedBalance = BigDecimal.valueOf(
                ThreadLocalRandom.current().nextDouble(1.0, 100000.0));
        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(expectedBalance);

        BigDecimal actualBalance = bankAccount.getBalance();

        assertEquals(expectedBalance, actualBalance);
    }

    /**
     * Tests that the source transfers are returned correctly.
     */
    @Test
    void testForGetSourceTransfers() {

        BankAccount bankAccount = new BankAccount();
        bankAccount.addSourceTransfer(new Transfer());

        Collection<Transfer> sourceTransfers = bankAccount.getSourceTransfers();

        assertEquals(1, sourceTransfers.size());
    }

    /**
     * Tests that the source transfer collection is returned as a defensive copy.
     */
    @Test
    void testForGetSourceTransfersDefensiveCopy() {

        BankAccount bankAccount = new BankAccount();

        Collection<Transfer> firstCollection = bankAccount.getSourceTransfers();
        Collection<Transfer> secondCollection = bankAccount.getSourceTransfers();

        assertNotSame(firstCollection, secondCollection);
    }

    /**
     * Tests that the target transfers are returned correctly.
     */
    @Test
    void testForGetTargetTransfers() {

        BankAccount bankAccount = new BankAccount();
        bankAccount.addTargetTransfer(new Transfer());

        Collection<Transfer> targetTransfers = bankAccount.getTargetTransfers();

        assertEquals(1, targetTransfers.size());
    }

    /**
     * Tests that the target transfer collection is returned as a defensive copy.
     */
    @Test
    void testForGetTargetTransfersDefensiveCopy() {

        BankAccount bankAccount = new BankAccount();

        Collection<Transfer> firstCollection = bankAccount.getTargetTransfers();
        Collection<Transfer> secondCollection = bankAccount.getTargetTransfers();

        assertNotSame(firstCollection, secondCollection);
    }

    /**
     * Tests that the account holder name is updated correctly.
     */
    @Test
    void testForSetAccountHolderName() {

        String expectedAccountHolderName = UUID.randomUUID().toString();
        BankAccount bankAccount = new BankAccount();

        bankAccount.setAccountHolderName(expectedAccountHolderName);

        assertEquals(expectedAccountHolderName,
                bankAccount.getAccountHolderName());
    }

    /**
     * Tests that the account number is updated correctly.
     */
    @Test
    void testForSetAccountNumber() {

        String expectedAccountNumber =
                "ACC%08d".formatted(
                        ThreadLocalRandom.current().nextInt(100_000_000));
        BankAccount bankAccount = new BankAccount();

        bankAccount.setAccountNumber(expectedAccountNumber);

        assertEquals(expectedAccountNumber,
                bankAccount.getAccountNumber());
    }

    /**
     * Tests that the account balance is updated correctly.
     */
    @Test
    void testForSetBalance() {

        BigDecimal expectedBalance = BigDecimal.valueOf(
                ThreadLocalRandom.current().nextDouble(1.0, 100000.0));
        BankAccount bankAccount = new BankAccount();

        bankAccount.setBalance(expectedBalance);

        assertEquals(expectedBalance, bankAccount.getBalance());
    }
}
