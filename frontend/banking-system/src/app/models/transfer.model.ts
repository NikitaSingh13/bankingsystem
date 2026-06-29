/**
 * Represents the details of a transfer transaction.
 *
 * Contains the transaction identifier, transfer amount,
 * date, transfer type, and the related account number.
 */
export interface Transfer {
  transactionId: number;
  amount: number;
  date: string;
  fromToAccountNumber: string;
  type: string;
}

/**
 * Represents the request used to create a new transfer.
 *
 * Contains the amount to transfer, the source and target
 * account numbers, and the transfer type.
 */
export interface CreateTransferRequest {
  amount: number;
  sourceAccountNumber: string;
  targetAccountNumber: string;
  type: string;
}

/**
 * Represents the response returned after successfully
 * creating a transfer.
 *
 * Contains the details of the completed transfer
 * transaction.
 */
export interface CreateTransferResponse {
  transfer: Transfer;
}
