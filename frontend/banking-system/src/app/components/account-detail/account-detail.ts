import {
  Component,
  OnInit,
  ChangeDetectorRef
} from '@angular/core';

import {
  CommonModule
} from '@angular/common';

import {
  HttpErrorResponse
} from '@angular/common/http';

import {
  ActivatedRoute,
  Router
} from '@angular/router';

import {
  BankAccountService
} from '../../services/bank-account.service';

import {
  BankAccount,
  GetBankAccountResponse
} from '../../models/bank-account.model';

import {
  Transfer
} from '../../models/transfer.model';

@Component({
  selector: 'app-account-detail',
  standalone: true,
  imports: [
    CommonModule
  ],
  templateUrl: './account-detail.html',
  styleUrl: './account-detail.css'
})
export class AccountDetail implements OnInit {

  account?: BankAccount;

  transfers: Transfer[] = [];

  pageError = '';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: BankAccountService,
    private changeDetectorRef: ChangeDetectorRef
  ) {}

  ngOnInit(): void {

    const accountNumber =
      this.route.snapshot
        .paramMap
        .get('accountNumber');

    if (accountNumber) {

      this.loadAccount(
        accountNumber
      );
    }
  }

  loadAccount(
    accountNumber: string
  ): void {

    this.pageError = '';

    this.service
      .getBankAccount(
        accountNumber
      )
      .subscribe({

        next: (
          response:
          GetBankAccountResponse
        ) => {

          this.account =
            response.bankAccount;

          this.transfers =
            [...response.transfers]
              .sort(
                (a, b) =>
                  b.transactionId -
                  a.transactionId
              );

          this.changeDetectorRef
            .detectChanges();
        },

        error: error => {

          console.error(
            error
          );

          this.pageError =
            this.getErrorMessage(
              error,
              'Failed to load account.'
            );

          this.changeDetectorRef
            .detectChanges();
        }
      });
  }

  private getErrorMessage(
    error: unknown,
    fallbackMessage: string
  ): string {

    if (error instanceof HttpErrorResponse) {

      if (error.status === 0) {

        return 'Cannot connect to the backend service. Please make sure it is running.';
      }

      if (typeof error.error === 'string') {

        return error.error;
      }

      if (
        error.error &&
        typeof error.error === 'object'
      ) {

        const body =
          error.error as {
            error?: string;
            message?: string;
            detail?: string;
          };

        return (
          body.message ||
          body.error ||
          body.detail ||
          fallbackMessage
        );
      }

      return error.message || fallbackMessage;
    }

    return fallbackMessage;
  }

  goBack(): void {

    this.router.navigate([
      '/accounts'
    ]);
  }

  openCreditDialog(): void {

    console.log(
      'credit'
    );
  }

  openDebitDialog(): void {

    console.log(
      'debit'
    );
  }
}
