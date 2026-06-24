package com.example.bankingsystem;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountSequenceRepository
        extends CrudRepository<AccountSequence, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints(
            @QueryHint(
                    name = "jakarta.persistence.lock.timeout",
                    value = "5000"))
    @Query("""
        select accountSequence
        from AccountSequence accountSequence
        where accountSequence.id = 1
        """)
    AccountSequence findAndLock();
}