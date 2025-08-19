package dev.litebank.repository;

import dev.litebank.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransactionRepository extends JpaRepository<Transaction, String> {
//    Page<Transaction> getByAccountNumber(String accountNumber, Pageable pageable);

    Page<Transaction> getByAccountNumber(String accountNumber, Pageable pageable);

}
