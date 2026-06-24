package com.example.bankingsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Handles requests related to bank accounts.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/accounts")
@RestController
public class BankAccountController {

    @Autowired
    private BankAccountService service;

    /**
     * Creates a bank account.
     *
     * @param request create bank account request
     * @return created bank account response
     */
    @PutMapping
    public ResponseEntity<CreateBankAccountResponse> createBankAccount(
            @RequestBody
            final CreateBankAccountRequest request) {

        final CreateBankAccountResponse response =
                service.createBankAccount(request);

        return ResponseEntity.ok(response);
    }

    /**
     * Creates a transfer.
     *
     * @param request create transfer request
     * @return created transfer response
     */
    @PutMapping("/transfers")
    public ResponseEntity<CreateTransferResponse>
    createTransfer(
            @RequestBody
            final CreateTransferRequest request) {

        final CreateTransferResponse response =
                service.createTransfer(
                        request);

        return ResponseEntity.ok(
                response);
    }

    /**
     * Returns a bank account.
     *
     * @param accountNumber account number
     * @return bank account response
     */
    @GetMapping("{accountNumber}")
    public ResponseEntity<GetBankAccountResponse> getBankAccount(
            @PathVariable final String accountNumber) {

        final GetBankAccountRequest request =
                new GetBankAccountRequest();

        request.setAccountNumber(accountNumber);

        final GetBankAccountResponse response = service.getBankAccount(request);

        return ResponseEntity.ok(response);
    }


    /**
     * Returns all bank accounts.
     *
     * @return bank accounts response
     */
    @GetMapping
    public ResponseEntity<GetBankAccountsResponse> getBankAccounts() {

        final GetBankAccountsResponse response =
                service.getBankAccounts(new GetBankAccountsRequest());

        return ResponseEntity.ok(response);
    }
}