package dev.litebank.service;

import dev.litebank.dto.requests.CreateTransactionRequest;
import dev.litebank.dto.responses.CreateTransactionResponse;
import dev.litebank.dto.responses.TransactionResponse;
import dev.litebank.model.Transaction;
import dev.litebank.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService{

    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;


    @Override
    public CreateTransactionResponse create(CreateTransactionRequest transactionRequest) {
        Transaction transaction = createTransactionFrom(transactionRequest);
        transaction = transactionRepository.save(transaction);
        return buildTransactionResponseFrom(transaction);
    }

    private CreateTransactionResponse buildTransactionResponseFrom(Transaction transaction) {
//        CreateTransactionResponse createTransactionResponse = new CreateTransactionResponse();
//        createTransactionResponse.setId(transaction.getId());
//        createTransactionResponse.setAmount(transaction.getAmount().toString());
//        createTransactionResponse.setTransactionType(transaction.getTransactionType().toString());
//        return createTransactionResponse;


        return modelMapper.map(transaction, CreateTransactionResponse.class);

    }

    private Transaction createTransactionFrom(CreateTransactionRequest transactionRequest) {
//        Transaction transaction = new Transaction();
//        transaction.setAmount(transactionRequest.getAmount());
//        transaction.setTransactionType(transactionRequest.getTransactionType());
//        transaction.setAccountNumber(transactionRequest.getAccountNumber());
//        return transaction;


        return modelMapper.map(transactionRequest, Transaction.class);

    }

    @Override
    public TransactionResponse getTransactionById(String id) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(()-> new RuntimeException("transaction not found"));
        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setAmount(transaction.getAmount().toString());
        return transactionResponse;
    }
}
