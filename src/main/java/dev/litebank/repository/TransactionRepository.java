package dev.litebank.repository;

import dev.litebank.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    Page<Transaction> getByAccountNumber(String accountNumber, Pageable pageable);
}
