package com.example.bankingsystem;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/**
 * Request used to create a bank account.
 */
public class CreateBankAccountRequest {

    @NotBlank
    private String accountHolderName;

    @NotNull
    @DecimalMin("0.00")
    private BigDecimal balance;

    /**
     * Returns the account holder name.
     *
     * @return account holder name
     */
    public String getAccountHolderName() {
        return accountHolderName;
    }

    /**
     * Returns the account balance.
     *
     * @return account balance
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Sets the account holder name.
     *
     * @param bankAccountHolderName account holder name
     */
    public void setAccountHolderName(
            final String bankAccountHolderName) {

        accountHolderName = bankAccountHolderName;
    }

    /**
     * Sets the account balance.
     *
     * @param bankAccountBalance account balance
     */
    public void setBalance(
            final BigDecimal bankAccountBalance) {

        balance = bankAccountBalance;
    }
}