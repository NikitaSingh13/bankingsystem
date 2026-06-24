package com.example.bankingsystem;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository used to perform database operations
 * for TransactionSequence entities.
 */
@Repository
public interface TransactionSequenceRepository
        extends CrudRepository<TransactionSequence, Long> {

    /**
     * Returns the transaction sequence row
     * while acquiring a write lock.
     *
     * @return locked transaction sequence
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints(
            @QueryHint(
                    name = "jakarta.persistence.lock.timeout",
                    value = "5000"))
    @Query("""
        select transactionSequence
        from TransactionSequence transactionSequence
        where transactionSequence.id = 1
        """)
    TransactionSequence findAndLock();
}