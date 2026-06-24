package com.example.bankingsystem;

/**
 * Response returned after creating a transfer.
 */
public class CreateTransferResponse {

    private TransferDetail transfer;

    /**
     * Returns the created transfer.
     *
     * @return created transfer
     */
    public TransferDetail getTransfer() {
        return transfer;
    }

    /**
     * Sets the created transfer.
     *
     * @param responseTransfer transfer
     */
    public void setTransfer(
            final TransferDetail responseTransfer) {

        transfer = responseTransfer;
    }
}