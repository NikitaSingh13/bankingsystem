package com.example.bankingsystem.repository;

import com.example.bankingsystem.domain.entity.BankAccount;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository used to perform database operations
 * for {@link BankAccount} entities.
 */
@Repository
public interface BankAccountRepository
        extends CrudRepository<BankAccount, Long>,
        JpaSpecificationExecutor<BankAccount> {

    /**
     * Returns the bank account while acquiring a pessimistic
     * write lock on it.
     *
     * @param accountNumber account number
     * @return locked bank account if found
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints(
            @QueryHint(
                    name = "jakarta.persistence.lock.timeout",
                    value = "5000"))
    @Query("""
        select bankAccount
        from BankAccount bankAccount
        where bankAccount.accountNumber = :accountNumber
        """)
    Optional<BankAccount> findAndLock(
            @Param("accountNumber")
            String accountNumber);

    /**
     * Returns the bank account with the given public account number.
     *
     * @param accountNumber public account number
     * @return bank account if found
     */
    Optional<BankAccount> findByAccountNumber(
            String accountNumber);
}
