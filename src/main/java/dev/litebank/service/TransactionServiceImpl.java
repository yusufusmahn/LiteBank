package dev.litebank.service;

import dev.litebank.dto.requests.CreateTransactionRequest;
import dev.litebank.dto.responses.CreateTransactionResponse;
import dev.litebank.dto.responses.TransactionResponse;
import dev.litebank.model.Transaction;
import dev.litebank.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService{

    private final TransactionRepository transactionRepository;


    @Override
    public CreateTransactionResponse create(CreateTransactionRequest transactionRequest) {
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionRequest.getAmount());
        transaction.setTransactionType(transactionRequest.getTransactionType());
        transaction.setAccountNumber(transactionRequest.getAccountNumber());
        transaction = transactionRepository.save(transaction);

        CreateTransactionResponse createTransactionResponse = new CreateTransactionResponse();
        createTransactionResponse.setId(transaction.getId());
        createTransactionResponse.setAmount(transactionRequest.getAmount().toString());
        createTransactionResponse.setTransactionType(transactionRequest.getTransactionType().toString());

        return createTransactionResponse;
    }

    @Override
    public TransactionResponse getTransactionById(String id) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(()-> new RuntimeException("Transaction not found"));

        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setAmount(transaction.getAmount().toString());
        return transactionResponse;
    }
}
