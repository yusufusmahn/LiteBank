package dev.litebank.service;

import dev.litebank.dto.requests.CreateTransactionRequest;
import dev.litebank.dto.responses.CreateTransactionResponse;
import dev.litebank.dto.responses.TransactionResponse;

public interface TransactionService {
    CreateTransactionResponse create(CreateTransactionRequest transactionRequest);

    TransactionResponse getTransactionById(String id);
}
