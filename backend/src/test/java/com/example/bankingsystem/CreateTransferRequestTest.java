package com.example.bankingsystem;

import com.example.bankingsystem.dto.request.CreateTransferRequest;
import com.example.bankingsystem.domain.entity.TransferType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for {@link CreateTransferRequest}.
 */
class CreateTransferRequestTest {

    /**
     * Tests that the transfer amount is returned correctly.
     */
    @Test
    void testForGetAmount() {

        BigDecimal expectedAmount = BigDecimal.valueOf(
                ThreadLocalRandom.current().nextDouble(1.0, 100000.0));
        CreateTransferRequest request = new CreateTransferRequest();
        request.setAmount(expectedAmount);

        BigDecimal actualAmount = request.getAmount();

        assertEquals(expectedAmount, actualAmount);
    }

    /**
     * Tests that the source account number is returned correctly.
     */
    @Test
    void testForGetSourceAccountNumber() {

        String expectedSourceAccountNumber = UUID.randomUUID().toString();
        CreateTransferRequest request = new CreateTransferRequest();
        request.setSourceAccountNumber(expectedSourceAccountNumber);

        String actualSourceAccountNumber = request.getSourceAccountNumber();

        assertEquals(expectedSourceAccountNumber, actualSourceAccountNumber);
    }

    /**
     * Tests that the target account number is returned correctly.
     */
    @Test
    void testForGetTargetAccountNumber() {

        String expectedTargetAccountNumber = UUID.randomUUID().toString();
        CreateTransferRequest request = new CreateTransferRequest();
        request.setTargetAccountNumber(expectedTargetAccountNumber);

        String actualTargetAccountNumber = request.getTargetAccountNumber();

        assertEquals(expectedTargetAccountNumber, actualTargetAccountNumber);
    }

    /**
     * Tests that the transfer type is returned correctly.
     */
    @Test
    void testForGetType() {

        TransferType expectedTransferType = TransferType.CREDIT;
        CreateTransferRequest request = new CreateTransferRequest();
        request.setType(expectedTransferType);

        TransferType actualTransferType = request.getType();

        assertEquals(expectedTransferType, actualTransferType);
    }

    /**
     * Tests that the transfer amount is updated correctly.
     */
    @Test
    void testForSetAmount() {

        BigDecimal expectedAmount = BigDecimal.valueOf(
                ThreadLocalRandom.current().nextDouble(1.0, 100000.0));
        CreateTransferRequest request = new CreateTransferRequest();

        request.setAmount(expectedAmount);

        assertEquals(expectedAmount, request.getAmount());
    }

    /**
     * Tests that the source account number is updated correctly.
     */
    @Test
    void testForSetSourceAccountNumber() {

        String expectedSourceAccountNumber = UUID.randomUUID().toString();
        CreateTransferRequest request = new CreateTransferRequest();

        request.setSourceAccountNumber(expectedSourceAccountNumber);

        assertEquals(expectedSourceAccountNumber,
                request.getSourceAccountNumber());
    }

    /**
     * Tests that the target account number is updated correctly.
     */
    @Test
    void testForSetTargetAccountNumber() {

        String expectedTargetAccountNumber = UUID.randomUUID().toString();
        CreateTransferRequest request = new CreateTransferRequest();

        request.setTargetAccountNumber(expectedTargetAccountNumber);

        assertEquals(expectedTargetAccountNumber,
                request.getTargetAccountNumber());
    }

    /**
     * Tests that the transfer type is updated correctly.
     */
    @Test
    void testForSetType() {

        TransferType expectedTransferType = TransferType.DEBIT;
        CreateTransferRequest request = new CreateTransferRequest();

        request.setType(expectedTransferType);

        assertEquals(expectedTransferType, request.getType());
    }
}