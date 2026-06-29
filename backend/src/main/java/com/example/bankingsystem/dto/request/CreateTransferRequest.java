package com.example.bankingsystem.dto.request;

import com.example.bankingsystem.domain.entity.TransferType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/**
 * Represents the request payload used to create a transfer between two
 * bank accounts.
 *
 * <p>This request object is received from the client application and
 * contains all information required to perform a transfer. The request
 * is validated before it is processed by the service layer.</p>
 *
 * <p>The following validation rules are applied:</p>
 * <ul>
 *     <li>The transfer amount must not be null.</li>
 *     <li>The transfer amount must be greater than zero.</li>
 *     <li>The source account number must not be blank.</li>
 *     <li>The target account number must not be blank.</li>
 * </ul>
 */
public class CreateTransferRequest {

    @NotNull(message = "Transfer amount is required")
    @DecimalMin(
            value = "0.01",
            message = "Transfer amount must be greater than 0.00")
    private BigDecimal amount;

    @NotBlank(message = "Source account number is required")
    private String sourceAccountNumber;

    @NotBlank(message = "Target account number is required")
    private String targetAccountNumber;

    @NotNull(message = "Transfer type is required")
    private TransferType type;

    /**
     * Returns the amount to be transferred.
     *
     * @return the transfer amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Returns the account number of the source account.
     *
     * @return the source account number
     */
    public String getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    /**
     * Returns the account number of the destination account.
     *
     * @return the target account number
     */
    public String getTargetAccountNumber() {
        return targetAccountNumber;
    }

    /**
     * Returns the type of transfer.
     *
     * @return the transfer type
     */
    public TransferType getType() {
        return type;
    }

    /**
     * Sets the amount to be transferred.
     *
     * @param transferAmount the monetary amount to transfer
     */
    public void setAmount(
            final BigDecimal transferAmount) {

        amount = transferAmount;
    }

    /**
     * Sets the account number from which the funds will be transferred.
     *
     * @param transferSourceAccountNumber the source account number
     */
    public void setSourceAccountNumber(
            final String transferSourceAccountNumber) {

        sourceAccountNumber = transferSourceAccountNumber;
    }

    /**
     * Sets the account number that will receive the transferred funds.
     *
     * @param transferTargetAccountNumber the destination account number
     */
    public void setTargetAccountNumber(
            final String transferTargetAccountNumber) {

        targetAccountNumber = transferTargetAccountNumber;
    }

    /**
     * Sets the type of transfer.
     *
     * @param transferType the transfer type
     */
    public void setType(
            final TransferType transferType) {

        type = transferType;
    }
}