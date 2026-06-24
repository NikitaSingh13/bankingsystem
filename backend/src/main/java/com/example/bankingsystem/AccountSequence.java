package com.example.bankingsystem;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "account_sequence")
public class AccountSequence {

    @Id
    private Long id;

    @Column(name = "next_account_number",
            nullable = false)
    private Long nextAccountNumber;

    public AccountSequence(
            final Long accountSequenceId,
            final Long accountSequenceNextAccountNumber) {

        id = accountSequenceId;
        nextAccountNumber =
                accountSequenceNextAccountNumber;
    }

    public AccountSequence() {

    }

    public Long getId() {
        return id;
    }

    public Long getNextAccountNumber() {
        return nextAccountNumber;
    }

    public void setNextAccountNumber(
            final Long accountSequenceNextAccountNumber) {

        nextAccountNumber =
                accountSequenceNextAccountNumber;
    }
}