package com.example.bankingsystem;

/**
 * Request used to retrieve a bank account.
 */
public class GetBankAccountRequest {

    private String accountNumber;

    /**
     * Returns the account number.
     *
     * @return account number
     */
    public String getAccountNumber() {
        return accountNumber;
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