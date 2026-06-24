package com.example.bankingsystem;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 * Stores the next available transaction identifier.
 */
@Entity
@Table(name = "transaction_sequence")
public class TransactionSequence {

    @Column(
            name = "id",
            nullable = false,
            updatable = false)
    @Id
    @NotNull(message = "Sequence identifier is required")
    private Long id;

    @Column(
            name = "next_transaction_id",
            nullable = false)
    @NotNull(message = "Next transaction id is required")
    private Long nextTransactionId;

    /**
     * Constructs a transaction sequence using
     * the given information.
     *
     * @param transactionSequenceId sequence identifier
     * @param transactionSequenceNextTransactionId
     * next transaction identifier
     */
    public TransactionSequence(
            final Long transactionSequenceId,
            final Long transactionSequenceNextTransactionId) {

        id = transactionSequenceId;
        nextTransactionId =
                transactionSequenceNextTransactionId;
    }

    /**
     * Constructs a transaction sequence
     * with default values.
     */
    public TransactionSequence() {

    }

    /**
     * Returns the sequence identifier.
     *
     * @return sequence identifier
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns the next transaction identifier.
     *
     * @return next transaction identifier
     */
    public Long getNextTransactionId() {
        return nextTransactionId;
    }

    /**
     * Sets the next transaction identifier.
     *
     * @param transactionSequenceNextTransactionId
     * next transaction identifier
     */
    public void setNextTransactionId(
            final Long transactionSequenceNextTransactionId) {

        nextTransactionId =
                transactionSequenceNextTransactionId;
    }
}