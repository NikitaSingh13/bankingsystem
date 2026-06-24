package com.example.bankingsystem;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Request used to create a transfer.
 */
public class CreateTransferRequest {

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal amount;

    @NotBlank
    private String sourceAccountNumber;

    @NotBlank
    private String targetAccountNumber;

    private TransferType type;

    /**
     * Returns the transfer amount.
     *
     * @return transfer amount
     */
    public BigDecimal getAmount() {
        return amount;
    }


    /**
     * Returns the source account number.
     *
     * @return source account number
     */
    public String getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    /**
     * Returns the target account number.
     *
     * @return target account number
     */
    public String getTargetAccountNumber() {
        return targetAccountNumber;
    }

    /**
     * Returns the transfer type.
     *
     * @return transfer type
     */
    public TransferType getType() {
        return type;
    }

    /**
     * Sets the transfer amount.
     *
     * @param transferAmount transfer amount
     */
    public void setAmount(
            final BigDecimal transferAmount) {

        amount = transferAmount;
    }

    /**
     * Sets the source account number.
     *
     * @param transferSourceAccountNumber source account number
     */
    public void setSourceAccountNumber(
            final String transferSourceAccountNumber) {

        sourceAccountNumber = transferSourceAccountNumber;
    }

    /**
     * Sets the target account number.
     *
     * @param transferTargetAccountNumber target account number
     */
    public void setTargetAccountNumber(
            final String transferTargetAccountNumber) {

        targetAccountNumber = transferTargetAccountNumber;
    }

    /**
     * Sets the transfer type.
     *
     * @param transferType transfer type
     */
    public void setType(
            final TransferType transferType) {

        type = transferType;
    }
}