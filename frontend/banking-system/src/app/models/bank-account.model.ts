import { Transfer } from './transfer.model';

/**
 * Represents the basic information of a bank account.
 *
 * Contains the account holder's name, account number,
 * and the current available balance.
 */
export interface BankAccount {
  accountHolderName: string;
  accountNumber: string;
  balance: number;
}

/**
 * Represents the request used to create a new bank account.
 *
 * Contains the information required by the backend
 * to create a bank account.
 */
export interface CreateBankAccountRequest {
  accountHolderName: string;
  balance: number;
}

/**
 * Represents the response returned after successfully
 * creating a new bank account.
 *
 * Contains the details of the created bank account.
 */
export interface CreateBankAccountResponse {
  bankAccount: BankAccount;
}

/**
 * Represents the response returned when retrieving
 * a specific bank account.
 *
 * Contains the account details along with its
 * complete transfer history.
 */
export interface GetBankAccountResponse {
  bankAccount: BankAccount;
  transfers: Transfer[];
}

/**
 * Represents the response returned when retrieving
 * all bank accounts.
 *
 * Contains the collection of bank accounts
 * available in the system.
 */
export interface GetBankAccountsResponse {
  bankAccounts: BankAccount[];
}
