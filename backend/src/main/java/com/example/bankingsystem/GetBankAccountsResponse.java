package com.example.bankingsystem;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Response containing all bank accounts.
 */
public class GetBankAccountsResponse {

    private final Collection<BankAccountDetail> bankAccounts;

    /**
     * Constructs a response with default values.
     */
    public GetBankAccountsResponse() {
        bankAccounts = new ArrayList<>();
    }

    /**
     * Adds a bank account to the response.
     *
     * @param bankAccount bank account
     */
    public void addBankAccount(
            final BankAccountDetail bankAccount) {

        bankAccounts.add(bankAccount);
    }

    /**
     * Returns all bank accounts.
     *
     * @return bank accounts
     */
    public Collection<BankAccountDetail> getBankAccounts() {
        return new ArrayList<>(bankAccounts);
    }
}