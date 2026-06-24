package com.example.bankingsystem;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository used to perform database operations
 * for BankAccount entities.
 */
@Repository
public interface BankAccountRepository
        extends CrudRepository<BankAccount, String>,
        JpaSpecificationExecutor<BankAccount> {

    /**
     * Returns the account while acquiring a write lock.
     *
     * @param accountNumber account number
     * @return locked account
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints(
            @QueryHint(
                    name = "jakarta.persistence.lock.timeout",
                    value = "5000"))
    @Query("""
        select bankAccount
        from BankAccount bankAccount
        where bankAccount.accountNumber =
              :accountNumber
        """)
    BankAccount findAndLock(
            String accountNumber);
}