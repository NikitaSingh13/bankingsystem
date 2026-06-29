package com.example.bankingsystem.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Represents a transfer between two bank accounts.
 */
@Entity
@Table(name = "transfer")
public class Transfer {

    @Column(name = "amount", nullable = false)
    @DecimalMin(value = "0.01",
            message = "Transfer amount must be greater than zero")
    @NotNull(message = "Transfer amount is required")
    private BigDecimal amount;

    @Column(name = "date", nullable = false)
    @NotNull(message = "Transfer date is required")
    private Date date;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "source_account_number",
            nullable = false,
            referencedColumnName = "account_number",
            updatable = false
    )
    @NotNull(message = "Source account is required")
    private BankAccount sourceAccount;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "target_account_number",
            nullable = false,
            referencedColumnName = "account_number",
            updatable = false
    )
    @NotNull(message = "Target account is required")
    private BankAccount targetAccount;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "transaction_id",
            nullable = false,
            updatable = false
    )
    private Long transactionId;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Transfer type is required")
    private TransferType type;

    /**
     * Constructs a transfer using the given information.
     *
     * @param transferAmount        transfer amount
     * @param transferDate          transfer date
     * @param transferSourceAccount source account
     * @param transferTargetAccount target account
     * @param transferType          transfer type
     */
    public Transfer(
            final BigDecimal transferAmount,
            final Date transferDate,
            final BankAccount transferSourceAccount,
            final BankAccount transferTargetAccount,
            final TransferType transferType) {

        this.amount = transferAmount;
        this.date = transferDate;
        this.sourceAccount = transferSourceAccount;
        this.targetAccount = transferTargetAccount;
        this.type = transferType;
    }

    /**
     * Constructs a transfer with default values.
     */
    public Transfer() {

    }

    /**
     * Returns the transfer amount.
     *
     * @return transfer amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Returns the transfer date.
     *
     * @return transfer date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Returns the source account.
     *
     * @return source account
     */
    public BankAccount getSourceAccount() {
        return sourceAccount;
    }

    /**
     * Returns the target account.
     *
     * @return target account
     */
    public BankAccount getTargetAccount() {
        return targetAccount;
    }

    /**
     * Returns the transactionId number.
     *
     * @return transactionId number
     */
    public Long getTransactionId() {
        return transactionId;
    }

    /**
     * Returns type of the transfer.
     *
     * @return type of the transfer
     */
    public TransferType getType() {
        return type;
    }
}
