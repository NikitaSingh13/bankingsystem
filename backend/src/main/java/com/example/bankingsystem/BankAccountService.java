package com.example.bankingsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Contains all the business logic for bank accounts.
 */
@Service
public class BankAccountService {

    @Autowired
    private AccountSequenceRepository accountSequenceRepository;

    @Autowired
    private BankAccountRepository repository;

    @Autowired
    private TransactionSequenceRepository transactionSequenceRepository;

    /**
     * Creates a bank account.
     *
     * @param request create bank account request
     * @return created bank account response
     */
    @Transactional
    public CreateBankAccountResponse createBankAccount(
            final CreateBankAccountRequest request) {

        final AccountSequence accountSequence = accountSequenceRepository.findAndLock();

        final Long accountNumber = accountSequence.getNextAccountNumber();
        accountSequence.setNextAccountNumber(accountNumber + 1);

        final String generatedAccountNumber =
                String.format("ACC-%06d", accountNumber);

        final BankAccount bankAccount = new BankAccount(
                        generatedAccountNumber,
                        request.getAccountHolderName(),
                        request.getBalance());

        repository.save(bankAccount);

        final CreateBankAccountResponse response = new CreateBankAccountResponse();

        response.setBankAccount(createBankAccountDetailHelper(
                        bankAccount));

        return response;
    }
    /**
     * Converts a bank account entity into a bank account detail.
     *
     * @param bankAccount bank account entity
     * @return bank account detail
     */
    private BankAccountDetail createBankAccountDetailHelper(
            final BankAccount bankAccount) {

        return new BankAccountDetail(
                bankAccount.getAccountNumber(),
                bankAccount.getAccountHolderName(),
                bankAccount.getBalance());
    }

    /**
     * Creates a transfer between two bank accounts.
     *
     * @param request create transfer request
     * @return created transfer response
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public CreateTransferResponse createTransfer(
            final CreateTransferRequest request) {

        final TransactionSequence transactionSequence =
                transactionSequenceRepository.findAndLock();

        final Long transactionId =
                transactionSequence.getNextTransactionId();

        transactionSequence.setNextTransactionId(transactionId + 1);

        //start account locking
        final String firstAccountNumber;
        final String secondAccountNumber;

        if (request.getSourceAccountNumber().compareTo(
                request.getTargetAccountNumber()) < 0) {

            firstAccountNumber = request.getSourceAccountNumber();
            secondAccountNumber = request.getTargetAccountNumber();
        }
        else {

            firstAccountNumber = request.getTargetAccountNumber();
            secondAccountNumber = request.getSourceAccountNumber();
        }

        repository.findAndLock(firstAccountNumber);
        repository.findAndLock(secondAccountNumber);

        final BankAccount sourceAccount = repository.findById(
                                request.getSourceAccountNumber()).orElseThrow();
        final BankAccount targetAccount = repository.findById(
                                request.getTargetAccountNumber()).orElseThrow();
        // ends locking account

        if (sourceAccount.getAccountNumber().equals(targetAccount.getAccountNumber())) {
            throw new IllegalArgumentException(
                    "Source and target accounts must be different");
        }

        if (sourceAccount.getBalance().compareTo(request.getAmount()) < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        final Transfer transfer = new Transfer(
                        transactionId,
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

        repository.save(sourceAccount);
        repository.save(targetAccount);

        final CreateTransferResponse response = new CreateTransferResponse();

        response.setTransfer(createTransferDetailHelper(
                        transfer,
                        sourceAccount.getAccountNumber()));

        return response;
    }

    /**
     * Converts a transfer entity into a transfer detail.
     *
     * @param transfer transfer entity
     * @return transfer detail
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

            fromToAccountNumber = transfer.getTargetAccount()
                            .getAccountNumber();
        } else {

            type = TransferType.CREDIT;

            fromToAccountNumber = transfer.getSourceAccount()
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
     * Returns the bank account matching the given account number.
     *
     * @param request get bank account request
     * @return bank account response
     */
    public GetBankAccountResponse getBankAccount(
            final GetBankAccountRequest request) {

        final GetBankAccountResponse response =
                new GetBankAccountResponse();

        final BankAccount bankAccount =
                repository.findById(
                                request.getAccountNumber())
                        .orElseThrow();

        response.setBankAccount(
                createBankAccountDetailHelper(
                        bankAccount));
        for (final Transfer transfer
                : bankAccount.getSourceTransfers()) {

            response.addTransfer(
                    createTransferDetailHelper(
                            transfer,
                            bankAccount.getAccountNumber()));
        }

        for (final Transfer transfer
                : bankAccount.getTargetTransfers()) {

            response.addTransfer(
                    createTransferDetailHelper(
                            transfer,
                            bankAccount.getAccountNumber()));
        }

        return response;
    }

    /**
     * Returns all bank accounts.
     *
     * @param request get bank accounts request
     * @return bank accounts response
     */
    public GetBankAccountsResponse getBankAccounts(
            final GetBankAccountsRequest request) {

        final GetBankAccountsResponse response =
                new GetBankAccountsResponse();

        for (final BankAccount bankAccount
                : repository.findAll()) {

            response.addBankAccount(
                    createBankAccountDetailHelper(
                            bankAccount));
        }

        return response;
    }
}