package dev.litebank.service;

import dev.litebank.dto.TransactionStatus;
import dev.litebank.dto.TransactionType;
import dev.litebank.dto.requests.CreateTransactionRequest;
import dev.litebank.dto.requests.DepositRequest;
import dev.litebank.dto.requests.createAccountRequest;
import dev.litebank.dto.responses.*;
import dev.litebank.repository.AccountRepository;
import dev.litebank.exception.AccountNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ZERO;

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
        return buildDepositResponse(transactionResponse);
    }

    @Override
    public ViewAccountResponse viewDetailsFor(String accountNumber) {
        List<TransactionResponse> transactions =
                transactionService.getTransactionsFor(accountNumber);
        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setAmount(ZERO.toString());
        TransactionResponse response = transactions.stream()
                .reduce(transactionResponse,(a,b)->
                        computeAccountBalanceFrom(a, b, transactionResponse));

        ViewAccountResponse viewAccountResponse = new ViewAccountResponse();
        viewAccountResponse.setBalance(response.getAmount());
        return viewAccountResponse;
    }

    @Override
    public CreateAccountResponse createAccount(createAccountRequest createAccountRequest) {
        return null;
    }

    private static TransactionResponse computeAccountBalanceFrom(TransactionResponse a, TransactionResponse b, TransactionResponse transactionResponse) {
        BigDecimal total = ZERO;
        if (b.getTransactionType() == TransactionType.DEPOSIT)
            total = total.add(new BigDecimal(b.getAmount()));

         else
            total = total.subtract(new BigDecimal(b.getAmount()));
        transactionResponse.setAmount(
                   new BigDecimal(a.getAmount())
                           .add(total).toString()
        );
        return transactionResponse;
    }

    private static DepositResponse buildDepositResponse(CreateTransactionResponse transactionResponse) {
        var depositResponse = new DepositResponse();
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


    public ViewAccountResponse viewDetailsForAccount(String accountNumber) {
        List<TransactionResponse> transactions =
                transactionService.getTransactionsFor(accountNumber);
        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setAmount(ZERO.toString());
        TransactionResponse response = transactions.stream()
                .reduce(transactionResponse,(a,b)-> {
                    BigDecimal total = ZERO;
                    if (b.getTransactionType() == TransactionType.DEPOSIT)
                        total = total.add(new BigDecimal(b.getAmount()));

                    else
                        total = total.subtract(new BigDecimal(b.getAmount()));
                    transactionResponse.setAmount(
                            new BigDecimal(a.getAmount())
                                    .add(total).toString()
                    );
                    return transactionResponse;
                });

        ViewAccountResponse viewAccountResponse = new ViewAccountResponse();
        viewAccountResponse.setBalance(response.getAmount());
        return viewAccountResponse;
    }


}
