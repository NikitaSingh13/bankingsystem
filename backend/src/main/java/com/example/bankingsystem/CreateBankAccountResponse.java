package com.example.bankingsystem;

/**
 * Response returned after creating a bank account.
 */
public class CreateBankAccountResponse {

    private BankAccountDetail bankAccount;

    /**
     * Returns the created bank account.
     *
     * @return created bank account
     */
    public BankAccountDetail getBankAccount() {
        return bankAccount;
    }

    /**
     * Sets the created bank account.
     *
     * @param responseBankAccount bank account
     */
    public void setBankAccount(
            final BankAccountDetail responseBankAccount) {

        this.bankAccount = responseBankAccount;
    }
}