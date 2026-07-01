import {
  Component,
  OnInit,
  ChangeDetectorRef
} from '@angular/core';

import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';

import { BankAccountService }
from '../../services/bank-account.service';

import {
  BankAccount,
  CreateBankAccountRequest
} from '../../models/bank-account.model';

import {
  CreateTransferRequest
} from '../../models/transfer.model';

@Component({
  selector: 'app-accounts',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule
  ],
  templateUrl: './accounts.html',
  styleUrl: './accounts.css'
})
export class Accounts implements OnInit {

  bankAccounts: BankAccount[] = [];

  pageError = '';

  // Create Account Dialog

  showAddAccountDialog = false;

  accountHolderName = '';

  balance = 0;

  createAccountError = '';

  // Transfer Dialog

  showTransferDialog = false;

  isCreditTransfer = true;

  selectedAccount?: BankAccount;

  selectedOtherAccountNumber = '';

  transferAmount = 0;

  transferError = '';

  isTransferring = false;

  constructor(
    private service: BankAccountService,
    private router: Router,
    private changeDetectorRef: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.loadAccounts();
  }

  loadAccounts(): void {

    this.pageError = '';

    this.service
      .getBankAccounts()
      .subscribe({

        next: response => {

          this.bankAccounts =
            response.bankAccounts;

          this.changeDetectorRef.detectChanges();
        },

        error: error => {

          console.error(error);

          this.pageError =
            this.getErrorMessage(
              error,
              'Unable to load accounts. Please check the backend service.'
            );

          this.changeDetectorRef.detectChanges();
        }
      });
  }

  /**
 * Downloads all bank accounts as a CSV file.
 */
downloadBankAccounts(): void {

  this.service
    .downloadBankAccounts()
    .subscribe({

      next: blob => {

        const url =
          window.URL.createObjectURL(blob);

        const anchor =
          document.createElement('a');

        anchor.href = url;
        anchor.download = 'bank-accounts.csv';

        anchor.click();

        anchor.remove();

        window.URL.revokeObjectURL(url);
      },

      error: error => {

        console.error(error);

        this.pageError =
          this.getErrorMessage(
            error,
            'Unable to download bank accounts.'
          );

        this.changeDetectorRef.detectChanges();
      }
    });
}

  openAccount(
    accountNumber: string
  ): void {

    this.router.navigate([
      '/accounts',
      accountNumber
    ]);
  }

  // -----------------------------
  // Create Account
  // -----------------------------

  openCreateAccountDialog(): void {

    this.createAccountError = '';

    this.showAddAccountDialog = true;
  }

  closeCreateAccountDialog(): void {

    this.showAddAccountDialog = false;

    this.accountHolderName = '';

    this.balance = 0;

    this.createAccountError = '';
  }

  createAccount(): void {

    this.createAccountError = '';

    if (!this.accountHolderName.trim()) {

      this.createAccountError =
        'Please enter the account holder name.';

      return;
    }

    if (this.balance < 0) {

      this.createAccountError =
        'Opening balance cannot be negative.';

      return;
    }

    const request:
      CreateBankAccountRequest = {

      accountHolderName:
        this.accountHolderName,

      balance:
        this.balance
    };

    this.service
      .createBankAccount(request)
      .subscribe({

        next: () => {

          this.loadAccounts();

          this.closeCreateAccountDialog();
        },

        error: error => {

          console.error(error);

          this.createAccountError =
            this.getErrorMessage(
              error,
              'Failed to create account.'
            );

          this.changeDetectorRef.detectChanges();
        }
      });
  }

  // -----------------------------
  // Transfer validation
  // -----------------------------

  private validateTransfer(): string {

    if (!this.selectedAccount) {

      return 'Please select an account first.';
    }

    if (!this.selectedOtherAccountNumber) {

      return this.isCreditTransfer
        ? 'Please select the source account.'
        : 'Please select the target account.';
    }

    if (this.transferAmount <= 0) {

      return 'Transfer amount must be greater than zero.';
    }

    const sourceAccount =
      this.isCreditTransfer
        ? this.bankAccounts.find(
            account =>
              account.accountNumber ===
              this.selectedOtherAccountNumber
          )
        : this.selectedAccount;

    if (
      sourceAccount &&
      this.transferAmount > sourceAccount.balance
    ) {

      return 'Insufficient balance. Please enter an amount within the available balance.';
    }

    return '';
  }

  private getErrorMessage(
    error: unknown,
    fallbackMessage: string
  ): string {

    if (error instanceof HttpErrorResponse) {

      if (error.status === 0) {

        return 'Cannot connect to the backend service. Please make sure it is running.';
      }

      const backendMessage =
        this.readBackendMessage(error.error) ||
        error.message;

      if (
        backendMessage
          .toLowerCase()
          .includes('insufficient')
      ) {

        return 'Insufficient balance. Please enter a smaller transfer amount.';
      }

      return backendMessage || fallbackMessage;
    }

    return fallbackMessage;
  }

  private readBackendMessage(
    responseBody: unknown
  ): string {

    if (typeof responseBody === 'string') {

      return responseBody;
    }

    if (
      responseBody &&
      typeof responseBody === 'object'
    ) {

      const body =
        responseBody as {
          error?: string;
          message?: string;
          detail?: string;
        };

      return (
        body.message ||
        body.error ||
        body.detail ||
        ''
      );
    }

    return '';
  }

  // -----------------------------
  // Credit Transfer
  // -----------------------------

  openCreditDialog(
    account: BankAccount
  ): void {

    this.selectedAccount =
      account;

    this.isCreditTransfer = true;

    this.transferAmount = 0;

    this.selectedOtherAccountNumber = '';

    this.transferError = '';

    this.showTransferDialog = true;
  }

  // -----------------------------
  // Debit Transfer
  // -----------------------------

  openDebitDialog(
    account: BankAccount
  ): void {

    this.selectedAccount =
      account;

    this.isCreditTransfer = false;

    this.transferAmount = 0;

    this.selectedOtherAccountNumber = '';

    this.transferError = '';

    this.showTransferDialog = true;
  }

  closeTransferDialog(): void {

    this.showTransferDialog = false;

    this.transferAmount = 0;

    this.selectedOtherAccountNumber = '';

    this.transferError = '';
  }

  createTransfer(): void {

    this.transferError =
      this.validateTransfer();

    if (this.transferError || !this.selectedAccount) {
      return;
    }

    const request:
      CreateTransferRequest = {

      amount:
        this.transferAmount,

      sourceAccountNumber:
        this.isCreditTransfer
          ? this.selectedOtherAccountNumber
          : this.selectedAccount.accountNumber,

      targetAccountNumber:
        this.isCreditTransfer
          ? this.selectedAccount.accountNumber
          : this.selectedOtherAccountNumber,

      type:
        this.isCreditTransfer
          ? 'CREDIT'
          : 'DEBIT'
    };

    this.isTransferring = true;

    this.service
      .createTransfer(request)
      .subscribe({

        next: () => {

          this.isTransferring = false;

          this.loadAccounts();

          this.closeTransferDialog();
        },

        error: error => {

          this.isTransferring = false;

          console.error(error);

          this.transferError =
            this.getErrorMessage(
              error,
              'Transfer failed. Please try again.'
            );

          this.changeDetectorRef.detectChanges();
        }
      });
  }
}
