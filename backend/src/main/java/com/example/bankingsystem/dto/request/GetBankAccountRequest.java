package com.example.bankingsystem.dto.request;

import jakarta.validation.constraints.NotBlank;

/**
 * Represents the request used to retrieve the details of a specific
 * bank account.
 *
 * <p>This request object contains the unique account number that
 * identifies the bank account to be retrieved. It is typically created
 * by the controller and passed to the service layer for processing.</p>
 */
public class GetBankAccountRequest {

    @NotBlank(message = "Account number is required")
    private String accountNumber;
    /**
     * Returns the account number of the requested bank account.
     *
     * @return the unique account number
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the account number of the bank account to retrieve.
     *
     * @param bankAccountNumber the unique account number of the
     *                          requested bank account
     */
    public void setAccountNumber(
            final String bankAccountNumber) {

        accountNumber = bankAccountNumber;
    }
}
