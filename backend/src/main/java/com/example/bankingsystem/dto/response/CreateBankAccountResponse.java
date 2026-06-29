package com.example.bankingsystem.dto.response;

import com.example.bankingsystem.domain.model.BankAccountDetail;

/**
 * Represents the response returned after successfully creating a
 * new bank account.
 *
 * <p>This response object contains the details of the newly created
 * bank account, including information such as the account number,
 * account holder name, and current balance.</p>
 */
public class CreateBankAccountResponse {

    private BankAccountDetail bankAccount;

    /**
     * Returns the details of the newly created bank account.
     *
     * @return the created bank account
     */
    public BankAccountDetail getBankAccount() {
        return bankAccount;
    }

    /**
     * Sets the details of the newly created bank account.
     *
     * @param responseBankAccount the created bank account
     */
    public void setBankAccount(
            final BankAccountDetail responseBankAccount) {

        this.bankAccount = responseBankAccount;
    }
}