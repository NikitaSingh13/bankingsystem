package com.example.bankingsystem.dto.response;

import com.example.bankingsystem.domain.model.TransferDetail;
import com.example.bankingsystem.domain.model.BankAccountDetail;

import java.util.ArrayList;
import java.util.Collection;
/**
 * Represents the response returned when retrieving a specific
 * bank account.
 *
 * <p>This response object contains the details of the requested
 * bank account along with the collection of transfers associated
 * with that account. It is returned to the client after the
 * requested account information has been successfully retrieved.</p>
 */
public class GetBankAccountResponse {

    private BankAccountDetail bankAccount;

    private final Collection<TransferDetail> transfers;

    /**
     * Constructs a new response with an empty collection of transfers.
     *
     * <p>The transfer collection is initialized to ensure that the
     * response always contains a non-null collection, even when the
     * bank account has no associated transfers.</p>
     */
    public GetBankAccountResponse() {
        transfers = new ArrayList<>();
    }

    /**
     * Adds a transfer to the collection of transfers associated
     * with the bank account.
     *
     * @param transfer the transfer to add to the response
     */
    public void addTransfer(final TransferDetail transfer) {

        transfers.add(transfer);
    }

    /**
     * Returns the details of the requested bank account.
     *
     * @return the bank account details
     */
    public BankAccountDetail getBankAccount() {
        return bankAccount;
    }

    /**
     * Returns the collection of transfers associated with the
     * requested bank account.
     *
     * <p>A defensive copy of the internal collection is returned
     * to prevent external modification of the response object's
     * internal state.</p>
     *
     * @return a collection containing the bank account transfers
     */
    public Collection<TransferDetail> getTransfers() {
        return new ArrayList<>(transfers);
    }

    /**
     * Sets the details of the requested bank account.
     *
     * @param responseBankAccount the bank account details to include
     *                            in the response
     */
    public void setBankAccount(
            final BankAccountDetail responseBankAccount) {

        bankAccount = responseBankAccount;
    }
}
