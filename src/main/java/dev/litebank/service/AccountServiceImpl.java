package dev.litebank.service;

import dev.litebank.dto.TransactionStatus;
import dev.litebank.dto.TransactionType;
import dev.litebank.dto.requests.CreateTransactionRequest;
import dev.litebank.dto.requests.DepositRequest;
import dev.litebank.dto.responses.CreateTransactionResponse;
import dev.litebank.dto.responses.DepositResponse;
import dev.litebank.dto.responses.TransactionResponse;
import dev.litebank.dto.responses.ViewAccountResponse;
import dev.litebank.model.Account;
import dev.litebank.repository.AccountRepository;
import dev.litebank.exception.AccountNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final TransactionService transactionService;

    @Override
    public DepositResponse deposit(DepositRequest depositRequest) {
       accountRepository.findByAccountNumber(depositRequest.getAccountNumber())
                        .orElseThrow(() -> new AccountNotFoundException("account not found"));

        //TODO: create transaction record
        CreateTransactionRequest createTransactionRequest = getCreateTransactionRequest(depositRequest);

        var transactionResponse = transactionService.create(createTransactionRequest);
        return getDepositResponse(transactionResponse);
    }

    @Override
    public ViewAccountResponse viewDetailsFor(String number) {
        return null;
    }


    private static DepositResponse getDepositResponse(CreateTransactionResponse transactionResponse) {
        DepositResponse depositResponse = new DepositResponse();
        depositResponse.setAmount(new BigDecimal(transactionResponse.getAmount()));
        depositResponse.setTransactionId(transactionResponse.getId());
        depositResponse.setTransactionStatus(TransactionStatus.SUCCESS);
        return depositResponse;
    }

    private static CreateTransactionRequest getCreateTransactionRequest(DepositRequest depositRequest) {
        CreateTransactionRequest createTransactionRequest = new CreateTransactionRequest();
        createTransactionRequest.setAmount(depositRequest.getAmount());
        createTransactionRequest.setAccountNumber(depositRequest.getAccountNumber());
        createTransactionRequest.setTransactionType(TransactionType.CREDIT);
        return createTransactionRequest;
    }

}
