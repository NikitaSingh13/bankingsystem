package com.example.bankingsystem.controller;

import com.example.bankingsystem.dto.request.CreateBankAccountRequest;
import com.example.bankingsystem.dto.request.CreateTransferRequest;
import com.example.bankingsystem.dto.request.GetBankAccountRequest;
import com.example.bankingsystem.dto.request.GetBankAccountsRequest;
import com.example.bankingsystem.dto.response.CreateBankAccountResponse;
import com.example.bankingsystem.dto.response.CreateTransferResponse;
import com.example.bankingsystem.dto.response.GetBankAccountResponse;
import com.example.bankingsystem.dto.response.GetBankAccountsResponse;
import com.example.bankingsystem.service.BankAccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller that exposes endpoints for managing bank accounts and
 * performing money transfers.
 *
 * <p>This controller accepts HTTP requests from the client application,
 * validates the incoming request data, delegates the business logic to
 * {@link BankAccountService}, and returns the appropriate HTTP response.</p>
 *
 * <p>All endpoints in this controller are accessible under the
 * <code>/accounts</code> base path.</p>
 */
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/accounts")
@RestController
public class BankAccountController {

    private final BankAccountService service;

    /**
     * Constructs the bank account controller.
     *
     * @param controllerService bank account service
     */
    public BankAccountController(
            final BankAccountService controllerService) {

        service = controllerService;
    }

    /**
     * Creates a new bank account.
     *
     * <p>The request body must contain all required account information,
     * including the account holder's details and the initial balance.
     * The request is validated before being processed.</p>
     *
     * @param request contains the information required to create a new
     *                bank account
     * @return a {@link ResponseEntity} containing the details of the
     *         newly created bank account
     */
    @PutMapping
    public ResponseEntity<CreateBankAccountResponse> createBankAccount(
            @Valid
            @RequestBody
            final CreateBankAccountRequest request) {

        final CreateBankAccountResponse response =
                service.createBankAccount(request);

        return ResponseEntity.ok(response);
    }

    /**
     * Creates a money transfer between two bank accounts.
     *
     * <p>The request must specify the source account, destination account,
     * transfer amount, and transfer type. The request is validated before
     * the transfer is processed.</p>
     *
     * @param request contains the details required to perform the transfer
     * @return a {@link ResponseEntity} containing the details of the
     *         completed transfer
     */
    @PutMapping("/transfers")
    public ResponseEntity<CreateTransferResponse> createTransfer(
            @Valid
            @RequestBody
            final CreateTransferRequest request) {

        final CreateTransferResponse response =
                service.createTransfer(request);

        return ResponseEntity.ok(response);
    }

    /**
     * Downloads all bank accounts as a CSV file.
     *
     * @return a {@link ResponseEntity} containing the CSV response
     */
    @GetMapping(value = "/downloads", produces = "text/csv")
    public ResponseEntity<GetBankAccountsResponse> downloadBankAccountsCsv() {

        final GetBankAccountsRequest request =
                new GetBankAccountsRequest();

        final GetBankAccountsResponse response =
                service.getBankAccounts(request);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"bank-accounts.csv\"")
                .contentType(new MediaType("text", "csv"))
                .body(response);
    }

    /**
     * Downloads the transfers for a single bank account as a CSV file.
     *
     * @param accountNumber the account number whose transfers are downloaded
     * @return a {@link ResponseEntity} containing the CSV response
     */
    @GetMapping(value = "{accountNumber}/transfers/downloads",
            produces = "text/csv")
    public ResponseEntity<GetBankAccountResponse> downloadTransfersCsv(
            @PathVariable
            final String accountNumber) {

        final GetBankAccountRequest request =
                new GetBankAccountRequest();

        request.setAccountNumber(accountNumber);

        final GetBankAccountResponse response =
                service.getBankAccount(request);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"transfers.csv\"")
                .contentType(new MediaType("text", "csv"))
                .body(response);
    }

    /**
     * Retrieves the details of a specific bank account.
     *
     * <p>The account number is provided as a path variable and is used
     * to locate the corresponding bank account.</p>
     *
     * @param accountNumber the unique account number identifying the
     *                      requested bank account
     * @return a {@link ResponseEntity} containing the details of the
     *         requested bank account
     */
    @GetMapping("{accountNumber}")
    public ResponseEntity<GetBankAccountResponse> getBankAccount(
            @PathVariable
            final String accountNumber) {

        final GetBankAccountRequest request =
                new GetBankAccountRequest();

        request.setAccountNumber(accountNumber);

        final GetBankAccountResponse response =
                service.getBankAccount(request);

        return ResponseEntity.ok(response);
    }

    /**
     * Retrieves all bank accounts available in the system.
     *
     * <p>This endpoint returns a collection of bank accounts along with
     * their associated information.</p>
     *
     * @return a {@link ResponseEntity} containing all bank accounts
     */
    @GetMapping
    public ResponseEntity<GetBankAccountsResponse> getBankAccounts() {

        final GetBankAccountsResponse response =
                service.getBankAccounts(new GetBankAccountsRequest());

        return ResponseEntity.ok(response);
    }
}
