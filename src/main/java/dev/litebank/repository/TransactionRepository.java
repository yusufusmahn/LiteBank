package dev.litebank.repository;

import dev.litebank.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface TransactionRepository extends JpaRepository<Transaction, String> {
//    Page<Transaction> readTransactionsByAccountNumber(String accountNumber, Pageable pageable);

    @Query("select t from transaction t where t.account_number;accountNumber")
    Page<Transaction> retrieveTransactionsByAccountNumber(String accountNumber, Pageable pageable);

}
