package dev.litebank.repository;

import dev.litebank.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface TransactionRepository extends JpaRepository<Transaction, String> {
//    Page<Transaction> readTransactionsByAccountNumber(String accountNumber, Pageable pageable);

    //Jakarta persistence query language
    @Query("select t from Transaction t where t.accountNumber =:accountNumber")
    Page<Transaction> retrieveTransactionsByAccountNumber(String accountNumber, Pageable pageable);


    List<Transaction> findTransactionByAccountNumber(String accountNumber);
}
