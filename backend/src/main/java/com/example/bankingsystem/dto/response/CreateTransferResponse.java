package com.example.bankingsystem.dto.response;

import com.example.bankingsystem.domain.model.TransferDetail;
/**
 * Represents the response returned after successfully creating a
 * transfer between two bank accounts.
 *
 * <p>This response object contains the details of the completed
 * transfer, including information such as the transfer amount,
 * source account, target account, transfer type, and the date
 * on which the transfer was performed.</p>
 */
public class CreateTransferResponse {

    private TransferDetail transfer;

    /**
     * Returns the details of the completed transfer.
     *
     * @return the created transfer
     */
    public TransferDetail getTransfer() {
        return transfer;
    }

    /**
     * Sets the details of the completed transfer.
     *
     * @param responseTransfer the created transfer
     */
    public void setTransfer(
            final TransferDetail responseTransfer) {

        transfer = responseTransfer;
    }
}
