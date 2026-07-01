import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import {
  CreateBankAccountRequest,
  CreateBankAccountResponse,
  GetBankAccountResponse,
  GetBankAccountsResponse,
} from '../models/bank-account.model';

import { CreateTransferRequest, CreateTransferResponse } from '../models/transfer.model';

/**
 * Service responsible for communicating with the banking system backend.
 *
 * Provides methods for creating bank accounts, performing transfers,
 * and retrieving bank account information through REST API endpoints.
 */
@Injectable({
  providedIn: 'root',
})
export class BankAccountService {
  private readonly API_URL = 'http://localhost:8080/accounts';

  constructor(private http: HttpClient) {}

  /**
   * Sends a request to create a new bank account.
   *
   * @param request the details required to create the bank account
   * @returns an observable containing the created bank account details
   */
  createBankAccount(request: CreateBankAccountRequest): Observable<CreateBankAccountResponse> {
    return this.http.put<CreateBankAccountResponse>(this.API_URL, request);
  }

  /**
   * Sends a request to transfer funds between two bank accounts.
   *
   * @param request the information required to perform the transfer
   * @returns an observable containing the created transfer details
   */
  createTransfer(request: CreateTransferRequest): Observable<CreateTransferResponse> {
    return this.http.put<CreateTransferResponse>(`${this.API_URL}/transfers`, request);
  }

  /**
   * Downloads all bank accounts as a CSV file.
   *
   * @returns an observable containing the generated CSV file
   */
  downloadBankAccounts(): Observable<Blob> {
    return this.http.get(`${this.API_URL}/downloads`, {
      responseType: 'blob',
    });
  }

  /**
   * Downloads the transfers of a specific bank account
   * as a CSV file.
   *
   * @param accountNumber the account number whose transfers
   *                      should be downloaded
   * @returns an observable containing the generated CSV file
   */
  downloadTransfers(accountNumber: string): Observable<Blob> {
    return this.http.get(`${this.API_URL}/${accountNumber}/transfers/downloads`, {
      responseType: 'blob',
    });
  }

  /**
   * Retrieves the details of a bank account using its account number.
   *
   * @param accountNumber the unique account number of the bank account
   * @returns an observable containing the requested bank account details
   */
  getBankAccount(accountNumber: string): Observable<GetBankAccountResponse> {
    return this.http.get<GetBankAccountResponse>(`${this.API_URL}/${accountNumber}`);
  }

  /**
   * Retrieves all bank accounts available in the system.
   *
   * @returns an observable containing the collection of bank accounts
   */
  getBankAccounts(): Observable<GetBankAccountsResponse> {
    return this.http.get<GetBankAccountsResponse>(this.API_URL);
  }
}
