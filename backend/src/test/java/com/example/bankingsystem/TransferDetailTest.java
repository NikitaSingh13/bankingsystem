package com.example.bankingsystem;

import com.example.bankingsystem.domain.model.TransferDetail;
import com.example.bankingsystem.domain.entity.TransferType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for {@link TransferDetail}.
 */
class TransferDetailTest {

    /**
     * Tests that the transfer amount is returned correctly.
     */
    @Test
    void testForGetAmount() {

        BigDecimal expectedAmount = BigDecimal.valueOf(
                ThreadLocalRandom.current().nextDouble(1.0, 100000.0));
        TransferDetail transferDetail = new TransferDetail();
        transferDetail.setAmount(expectedAmount);

        BigDecimal actualAmount = transferDetail.getAmount();

        assertEquals(expectedAmount, actualAmount);
    }

    /**
     * Tests that the transfer date is returned correctly.
     */
    @Test
    void testForGetDate() {

        Date expectedDate = new Date(ThreadLocalRandom.current().nextLong());
        TransferDetail transferDetail = new TransferDetail();
        transferDetail.setDate(expectedDate);

        Date actualDate = transferDetail.getDate();

        assertEquals(expectedDate, actualDate);
    }

    /**
     * Tests that the account number is returned correctly.
     */
    @Test
    void testForGetFromToAccountNumber() {

        String expectedAccountNumber = UUID.randomUUID().toString();
        TransferDetail transferDetail = new TransferDetail();
        transferDetail.setSourceAccountNumber(expectedAccountNumber);

        String actualAccountNumber = transferDetail.getFromToAccountNumber();

        assertEquals(expectedAccountNumber, actualAccountNumber);
    }

    /**
     * Tests that the transaction identifier is returned correctly.
     */
    @Test
    void testForGetTransactionId() {

        Long expectedTransactionId = ThreadLocalRandom.current().nextLong();
        TransferDetail transferDetail = new TransferDetail();
        transferDetail.setTransactionId(expectedTransactionId);

        Long actualTransactionId = transferDetail.getTransactionId();

        assertEquals(expectedTransactionId, actualTransactionId);
    }

    /**
     * Tests that the transfer type is returned correctly.
     */
    @Test
    void testForGetType() {

        TransferType expectedTransferType = TransferType.CREDIT;
        TransferDetail transferDetail = new TransferDetail();
        transferDetail.setType(expectedTransferType);

        TransferType actualTransferType = transferDetail.getType();

        assertEquals(expectedTransferType, actualTransferType);
    }

    /**
     * Tests that the transfer amount is updated correctly.
     */
    @Test
    void testForSetAmount() {

        BigDecimal expectedAmount = BigDecimal.valueOf(
                ThreadLocalRandom.current().nextDouble(1.0, 100000.0));
        TransferDetail transferDetail = new TransferDetail();

        transferDetail.setAmount(expectedAmount);

        assertEquals(expectedAmount, transferDetail.getAmount());
    }

    /**
     * Tests that the transfer date is updated correctly.
     */
    @Test
    void testForSetDate() {

        Date expectedDate = new Date(ThreadLocalRandom.current().nextLong());
        TransferDetail transferDetail = new TransferDetail();

        transferDetail.setDate(expectedDate);

        assertEquals(expectedDate, transferDetail.getDate());
    }

    /**
     * Tests that the account number is updated correctly.
     */
    @Test
    void testForSetSourceAccountNumber() {

        String expectedAccountNumber = UUID.randomUUID().toString();
        TransferDetail transferDetail = new TransferDetail();

        transferDetail.setSourceAccountNumber(expectedAccountNumber);

        assertEquals(expectedAccountNumber,
                transferDetail.getFromToAccountNumber());
    }

    /**
     * Tests that the transaction identifier is updated correctly.
     */
    @Test
    void testForSetTransactionId() {

        Long expectedTransactionId = ThreadLocalRandom.current().nextLong();
        TransferDetail transferDetail = new TransferDetail();

        transferDetail.setTransactionId(expectedTransactionId);

        assertEquals(expectedTransactionId,
                transferDetail.getTransactionId());
    }

    /**
     * Tests that the transfer type is updated correctly.
     */
    @Test
    void testForSetType() {

        TransferType expectedTransferType = TransferType.DEBIT;
        TransferDetail transferDetail = new TransferDetail();

        transferDetail.setType(expectedTransferType);

        assertEquals(expectedTransferType, transferDetail.getType());
    }
}