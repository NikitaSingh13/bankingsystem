package com.example.bankingsystem.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.NaturalId;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a bank account that can send and receive transfers.
 */
@Entity
@Table(name = "bank_account")
public class BankAccount {

    @Column(name = "account_holder_name", length = 50, nullable = false)
    @NotBlank(message = "Account holder name cannot be blank")
    @Size(min = 1, max = 50,
            message = "Account holder name "
                    + "must contain between 1 and 50 characters")
    @NaturalId
    private String accountHolderName;

    @Column(name = "account_number", length = 8, unique = true)
    @Size(min = 8, max = 8,
            message = "Account number must contain exactly 8 characters")
    private String accountNumber;

    @Column(name = "balance", nullable = false)
    @DecimalMin(value = "0.00", message = "Balance cannot be negative")
    @NotNull(message = "Balance is required")
    private BigDecimal balance;

    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @OneToMany(mappedBy = "sourceAccount", cascade = CascadeType.ALL)
    private Set<Transfer> sourceTransfers;

    @OneToMany(mappedBy = "targetAccount", cascade = CascadeType.ALL)
    private Set<Transfer> targetTransfers;

    /**
     * Constructs a bank account using the given information.
     *
     * @param bankAccountHolderName account holder name
     * @param bankAccountBalance    account balance
     */
    public BankAccount(
            final String bankAccountHolderName,
            final BigDecimal bankAccountBalance) {

        accountHolderName = bankAccountHolderName;
        balance = bankAccountBalance;
        sourceTransfers = new HashSet<>();
        targetTransfers = new HashSet<>();
    }

    /**
     * Constructs a bank account with default values.
     */
    public BankAccount() {
        sourceTransfers = new HashSet<>();
        targetTransfers = new HashSet<>();
    }

    /**
     * Adds transfer done by source account.
     *
     * @param transfer transfer done from source account.
     */
    public void addSourceTransfer(final Transfer transfer) {
        sourceTransfers.add(transfer);
    }

    /**
     * Adds transfer done to target account.
     *
     * @param transfer transfer done to target account
     */
    public void addTargetTransfer(final Transfer transfer) {
        targetTransfers.add(transfer);
    }

    /**
     * Returns the account holder name.
     *
     * @return account holder name
     */
    public String getAccountHolderName() {
        return accountHolderName;
    }

    /**
     * Returns the account number.
     *
     * @return account number
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Returns the account balance.
     *
     * @return account balance
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Returns the internal database identifier.
     *
     * @return internal database identifier
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns all the transfers done by source account.
     *
     * @return set of transfers done by source account
     */
    public Collection<Transfer> getSourceTransfers() {
        return new HashSet<>(sourceTransfers);
    }

    /**
     * Returns all the transfers done to target account.
     *
     * @return set of transfers done to target account
     */
    public Collection<Transfer> getTargetTransfers() {
        return new HashSet<>(targetTransfers);
    }

    /**
     * Sets the account holder name.
     *
     * @param bankAccountHolderName account holder name
     */
    public void setAccountHolderName(final String bankAccountHolderName) {

        accountHolderName = bankAccountHolderName;
    }

    /**
     * Sets the account number.
     *
     * @param bankAccountNumber account number
     */
    public void setAccountNumber(final String bankAccountNumber) {

        accountNumber = bankAccountNumber;
    }

    /**
     * Sets the account balance.
     *
     * @param bankAccountBalance account balance
     */
    public void setBalance(final BigDecimal bankAccountBalance) {

        balance = bankAccountBalance;
    }
}
