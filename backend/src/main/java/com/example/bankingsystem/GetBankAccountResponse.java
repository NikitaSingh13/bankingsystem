package com.example.bankingsystem;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Response containing a bank account
 * and its transfers.
 */
public class GetBankAccountResponse {

    private BankAccountDetail bankAccount;

    private final Collection<TransferDetail> transfers;

    /**
     * Constructs a response with default values.
     */
    public GetBankAccountResponse() {
        transfers = new ArrayList<>();
    }

    /**
     * Adds a transfer to the response.
     *
     * @param transfer transfer
     */
    public void addTransfer(
            final TransferDetail transfer) {

        transfers.add(transfer);
    }

    /**
     * Returns the bank account.
     *
     * @return bank account
     */
    public BankAccountDetail getBankAccount() {
        return bankAccount;
    }

    /**
     * Returns the transfers.
     *
     * @return transfers
     */
    public Collection<TransferDetail> getTransfers() {
        return new ArrayList<>(transfers);
    }

    /**
     * Sets the bank account.
     *
     * @param responseBankAccount bank account
     */
    public void setBankAccount(
            final BankAccountDetail responseBankAccount) {

        bankAccount = responseBankAccount;
    }
}