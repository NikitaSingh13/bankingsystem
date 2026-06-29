package com.example.bankingsystem.service;

import com.example.bankingsystem.domain.entity.*;
import com.example.bankingsystem.domain.model.BankAccountDetail;
import com.example.bankingsystem.domain.model.TransferDetail;
import com.example.bankingsystem.dto.request.CreateBankAccountRequest;
import com.example.bankingsystem.dto.request.CreateTransferRequest;
import com.example.bankingsystem.dto.request.GetBankAccountRequest;
import com.example.bankingsystem.dto.request.GetBankAccountsRequest;
import com.example.bankingsystem.dto.response.CreateBankAccountResponse;
import com.example.bankingsystem.dto.response.CreateTransferResponse;
import com.example.bankingsystem.dto.response.GetBankAccountResponse;
import com.example.bankingsystem.dto.response.GetBankAccountsResponse;
import com.example.bankingsystem.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Provides the business logic for managing bank accounts and processing
 * money transfers.
 *
 * <p>This service is responsible for creating bank accounts, retrieving
 * account information, processing transfers between accounts, and
 * converting persistence entities into response objects returned to
 * the client.</p>
 *
 * <p>Database operations that modify application data are executed
 * within transactions to ensure data consistency and integrity.</p>
 */
@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository repository;

    /**
     * Creates a new bank account.
     *
     * <p>The new account is persisted first so the database can generate
     * the internal identifier. The public account number is then generated
     * from that identifier and persisted on the account.</p>
     *
     * @param request contains the information required to create
     *                a new bank account
     * @return a response containing the details of the created
     * bank account
     */
    @Transactional
    public CreateBankAccountResponse createBankAccount(
            final CreateBankAccountRequest request) {

        final BankAccount bankAccount = new BankAccount(
                request.getAccountHolderName(),
                request.getBalance());

        repository.save(bankAccount);

        bankAccount.setAccountNumber(generateAccountNumber(
                bankAccount.getId()));
        repository.save(bankAccount);

        final CreateBankAccountResponse response =
                new CreateBankAccountResponse();

        response.setBankAccount(
                createBankAccountDetailHelper(
                        bankAccount));

        return response;
    }

    /**
     * Creates a {@link BankAccountDetail} object from a
     * {@link BankAccount} entity.
     *
     * <p>This helper method converts the entity retrieved from the
     * database into a DTO that can be safely returned to the client.</p>
     *
     * @param bankAccount the bank account entity to convert
     * @return the corresponding bank account detail object
     */
    private BankAccountDetail createBankAccountDetailHelper(
            final BankAccount bankAccount) {

        return new BankAccountDetail(
                bankAccount.getAccountNumber(),
                bankAccount.getAccountHolderName(),
                bankAccount.getBalance());
    }

    /**
     * Processes a transfer between two bank accounts.
     *
     * <p>The source and target accounts are retrieved using pessimistic
     * locking to prevent concurrent updates. The service validates that
     * the accounts are different and that the source account has sufficient
     * funds before performing the transfer.</p>
     *
     * <p>After the transfer is completed, the account balances and transfer
     * history are updated, and the transfer details are returned.</p>
     *
     * @param request contains the information required to perform
     *                the transfer
     * @return a response containing the completed transfer details
     * @throws IllegalArgumentException if the source and target accounts
     *                                  are the same or if the source
     *                                  account has insufficient funds
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public CreateTransferResponse createTransfer(
            final CreateTransferRequest request) {

        final String firstAccountNumber;
        final String secondAccountNumber;

        if (request.getSourceAccountNumber().compareTo(
                request.getTargetAccountNumber()) < 0) {

            firstAccountNumber = request.getSourceAccountNumber();
            secondAccountNumber = request.getTargetAccountNumber();
        } else {

            firstAccountNumber = request.getTargetAccountNumber();
            secondAccountNumber = request.getSourceAccountNumber();
        }

        final BankAccount firstAccount = repository.findAndLock(
                firstAccountNumber).orElseThrow();
        final BankAccount secondAccount = repository.findAndLock(
                secondAccountNumber).orElseThrow();

        final BankAccount sourceAccount;
        final BankAccount targetAccount;

        if (firstAccount.getAccountNumber().equals(
                request.getSourceAccountNumber())) {

            sourceAccount = firstAccount;
            targetAccount = secondAccount;
        } else {

            sourceAccount = secondAccount;
            targetAccount = firstAccount;
        }

        if (sourceAccount.getAccountNumber().equals(
                targetAccount.getAccountNumber())) {

            throw new IllegalArgumentException(
                    "Source and target accounts must be different");
        }

        if (sourceAccount.getBalance().compareTo(request.getAmount()) < 0) {

            throw new IllegalArgumentException("Insufficient balance");
        }

        final Transfer transfer = new Transfer(
                request.getAmount(),
                new java.util.Date(),
                sourceAccount,
                targetAccount,
                request.getType());

        sourceAccount.addSourceTransfer(transfer);
        targetAccount.addTargetTransfer(transfer);

        sourceAccount.setBalance(sourceAccount.getBalance().subtract(
                request.getAmount()));
        targetAccount.setBalance(targetAccount.getBalance().add(
                request.getAmount()));

        final CreateTransferResponse response = new CreateTransferResponse();

        response.setTransfer(createTransferDetailHelper(
                transfer,
                sourceAccount.getAccountNumber()));

        return response;
    }

    /**
     * Creates a {@link TransferDetail} object from a
     * {@link Transfer} entity.
     *
     * <p>The transfer is represented from the perspective of the specified
     * account. Depending on whether the account is the sender or recipient,
     * the transfer is classified as either a debit or a credit transaction.</p>
     *
     * @param transfer      the transfer entity to convert
     * @param accountNumber the account number whose perspective is used
     *                      when determining the transfer type
     * @return the corresponding transfer detail object
     */
    private TransferDetail createTransferDetailHelper(
            final Transfer transfer,
            final String accountNumber) {

        final TransferType type;
        final String fromToAccountNumber;

        if (transfer.getSourceAccount()
                .getAccountNumber()
                .equals(accountNumber)) {

            type = TransferType.DEBIT;

            fromToAccountNumber =
                    transfer.getTargetAccount()
                            .getAccountNumber();
        } else {

            type = TransferType.CREDIT;

            fromToAccountNumber =
                    transfer.getSourceAccount()
                            .getAccountNumber();
        }

        return new TransferDetail(
                transfer.getTransactionId(),
                transfer.getAmount(),
                transfer.getDate(),
                fromToAccountNumber,
                type);
    }

    /**
     * Generates a bank account number from the internal database identifier.
     *
     * <p>The generated account number follows the format
     * <code>ACC00000001</code>.</p>
     *
     * @param id internal database identifier
     * @return bank account number
     */
    private String generateAccountNumber(
            final Long id) {

        return String.format("%08d", id);
    }

    /**
     * Retrieves the details of a specific bank account.
     *
     * <p>The bank account is located using its account number. The response
     * includes both the account information and the complete transfer
     * history associated with the account.</p>
     *
     * @param request contains the account number of the bank account
     *                to retrieve
     * @return a response containing the bank account details and
     * associated transfers
     */
    public GetBankAccountResponse getBankAccount(
            final GetBankAccountRequest request) {

        final BankAccount bankAccount =
                repository.findByAccountNumber(
                        request.getAccountNumber()).orElseThrow();

        final GetBankAccountResponse response = new GetBankAccountResponse();

        response.setBankAccount(createBankAccountDetailHelper(
                bankAccount));

        for (final Transfer transfer : bankAccount.getSourceTransfers()) {

            response.addTransfer(createTransferDetailHelper(
                    transfer,
                    bankAccount.getAccountNumber()));
        }

        for (final Transfer transfer : bankAccount.getTargetTransfers()) {

            response.addTransfer(createTransferDetailHelper(
                    transfer,
                    bankAccount.getAccountNumber()));
        }

        return response;
    }

    /**
     * Retrieves all bank accounts stored in the system.
     *
     * <p>Each bank account entity is converted into a response object
     * before being included in the returned collection.</p>
     *
     * @param request the request used to retrieve all bank accounts
     * @return a response containing all bank accounts
     */
    public GetBankAccountsResponse getBankAccounts(
            final GetBankAccountsRequest request) {

        final GetBankAccountsResponse response = new GetBankAccountsResponse();

        for (final BankAccount bankAccount : repository.findAll()) {
            response.addBankAccount(createBankAccountDetailHelper(
                    bankAccount));
        }

        return response;
    }
}
