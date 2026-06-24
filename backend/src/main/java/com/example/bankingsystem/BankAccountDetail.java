package com.example.bankingsystem;

import java.math.BigDecimal;

/**
 * Represents bank account information exposed outside
 * the service layer.
 */
public class BankAccountDetail {

    private String accountHolderName;

    private String accountNumber;

    private BigDecimal balance;

    /**
     * Constructs bank account detail using the given information.
     *
     * @param bankAccountNumber account number
     * @param bankAccountHolderName account holder name
     * @param bankAccountBalance account balance
     */
    public BankAccountDetail(
            final String bankAccountNumber,
            final String bankAccountHolderName,
            final BigDecimal bankAccountBalance) {

        accountHolderName = bankAccountHolderName;
        accountNumber = bankAccountNumber;
        balance = bankAccountBalance;
    }

    /**
     * Constructs a bank account detail with default values.
     */
    public BankAccountDetail() {

    }

    /**
     * Returns the account holder name.
     *
     * @return account holder name
     */
    public String getAccountHolderName() {
        return accountHolderName;
    }

    /**
     * Returns the account number.
     *
     * @return account number
     */
    public String getAccountNumber() {
        return accountNumber;
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

    /**
     * Sets the account number.
     *
     * @param bankAccountNumber account number
     */
    public void setAccountNumber(
            final String bankAccountNumber) {

        accountNumber = bankAccountNumber;
    }
}