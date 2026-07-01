package com.example.bankingsystem.dto.response;

import com.example.bankingsystem.domain.model.BankAccountDetail;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents the response returned when retrieving all bank accounts.
 *
 * <p>This response object contains a collection of bank account details
 * available in the system. It is returned to the client after all bank
 * accounts have been successfully retrieved.</p>
 */
public class GetBankAccountsResponse implements CsvResponse {

    private final Collection<BankAccountDetail> bankAccounts;

    /**
     * Constructs a new response with an empty collection of bank accounts.
     *
     * <p>The collection is initialized to ensure that the response always
     * contains a non-null collection, even when no bank accounts are
     * available.</p>
     */
    public GetBankAccountsResponse() {
        bankAccounts = new ArrayList<>();
    }

    /**
     * Adds a bank account to the collection of bank accounts included
     * in the response.
     *
     * @param bankAccount the bank account to add to the response
     */
    public void addBankAccount(
            final BankAccountDetail bankAccount) {

        bankAccounts.add(bankAccount);
    }

    /**
     * Returns the collection of bank accounts.
     *
     * <p>A defensive copy of the internal collection is returned to
     * prevent external modification of the response object's internal
     * state.</p>
     *
     * @return a collection containing all bank accounts
     */
    public Collection<BankAccountDetail> getBankAccounts() {
        return new ArrayList<>(bankAccounts);
    }

    /**
     * Returns the bank account CSV row type.
     *
     * @return CSV row type
     */
    @Override
    public Class<?> getCsvRowType() {
        return BankAccountDetail.class;
    }

    /**
     * Returns bank accounts as CSV rows.
     *
     * @return CSV row objects
     */
    @Override
    public Collection<?> getCsvRows() {
        return getBankAccounts();
    }
}
