package com.example.bankingsystem.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

/**
 * Represents the request payload used to create a new bank account.
 *
 * <p>This request object contains the information required by the client
 * to create a bank account. It is received by the REST controller and
 * validated before being processed by the service layer.</p>
 *
 * <p>The following validation rules are applied:</p>
 * <ul>
 *     <li>The account holder name must not be null,
 *     empty, or contain only whitespace.</li>
 *     <li>The initial account balance must not be null.</li>
 *     <li>The initial balance must be greater than or equal to zero.</li>
 * </ul>
 */
public class CreateBankAccountRequest {

    @NotBlank(
            message = "Account holder name is required")
    @Size(max = 100,
            message = "Account holder name must not exceed 100 characters")
    private String accountHolderName;

    @NotNull(message = "Initial balance is required")
    @DecimalMin(
            value = "0.00",
            message = "Initial balance must be greater than or equal to 0.00")
    private BigDecimal balance;

    /**
     * Returns the name of the account holder.
     *
     * @return the account holder's full name
     */
    public String getAccountHolderName() {
        return accountHolderName;
    }

    /**
     * Returns the initial balance of the bank account.
     *
     * @return the account's initial balance
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Sets the name of the account holder.
     *
     * @param bankAccountHolderName the full name of the account holder
     */
    public void setAccountHolderName(
            final String bankAccountHolderName) {

        accountHolderName = bankAccountHolderName;
    }

    /**
     * Sets the initial balance for the bank account.
     *
     * @param bankAccountBalance the initial balance to assign to the account
     */
    public void setBalance(
            final BigDecimal bankAccountBalance) {

        balance = bankAccountBalance;
    }
}
