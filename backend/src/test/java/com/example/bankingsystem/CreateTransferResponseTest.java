package com.example.bankingsystem;

import com.example.bankingsystem.dto.response.CreateTransferResponse;
import com.example.bankingsystem.domain.model.TransferDetail;
import com.example.bankingsystem.domain.entity.TransferType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for {@link CreateTransferResponse}.
 */
class CreateTransferResponseTest {

    /**
     * Tests that the transfer is returned correctly.
     */
    @Test
    void testForGetTransfer() {

        Long expectedTransactionId = ThreadLocalRandom.current().nextLong();
        BigDecimal expectedAmount = BigDecimal.valueOf(
                ThreadLocalRandom.current().nextDouble(1.0, 100000.0));
        Date expectedDate = new Date(ThreadLocalRandom.current().nextLong());
        String expectedAccountNumber = UUID.randomUUID().toString();
        TransferType expectedTransferType = ThreadLocalRandom.current().nextBoolean()
                ? TransferType.CREDIT
                : TransferType.DEBIT;

        TransferDetail expectedTransfer = new TransferDetail();
        expectedTransfer.setTransactionId(expectedTransactionId);
        expectedTransfer.setAmount(expectedAmount);
        expectedTransfer.setDate(expectedDate);
        expectedTransfer.setSourceAccountNumber(expectedAccountNumber);
        expectedTransfer.setType(expectedTransferType);

        CreateTransferResponse response = new CreateTransferResponse();
        response.setTransfer(expectedTransfer);

        TransferDetail actualTransfer = response.getTransfer();

        assertEquals(expectedTransfer, actualTransfer);
    }

    /**
     * Tests that the transfer is updated correctly.
     */
    @Test
    void testForSetTransfer() {

        Long expectedTransactionId = ThreadLocalRandom.current().nextLong();
        BigDecimal expectedAmount = BigDecimal.valueOf(
                ThreadLocalRandom.current().nextDouble(1.0, 100000.0));
        Date expectedDate = new Date(ThreadLocalRandom.current().nextLong());
        String expectedAccountNumber = UUID.randomUUID().toString();
        TransferType expectedTransferType = ThreadLocalRandom.current().nextBoolean()
                ? TransferType.CREDIT
                : TransferType.DEBIT;

        TransferDetail expectedTransfer = new TransferDetail();
        expectedTransfer.setTransactionId(expectedTransactionId);
        expectedTransfer.setAmount(expectedAmount);
        expectedTransfer.setDate(expectedDate);
        expectedTransfer.setSourceAccountNumber(expectedAccountNumber);
        expectedTransfer.setType(expectedTransferType);

        CreateTransferResponse response = new CreateTransferResponse();

        response.setTransfer(expectedTransfer);

        assertEquals(expectedTransfer, response.getTransfer());
    }
}