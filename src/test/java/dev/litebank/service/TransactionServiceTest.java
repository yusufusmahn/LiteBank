package dev.litebank.service;


import dev.litebank.model.TransactionType;
import dev.litebank.dto.requests.CreateTransactionRequest;
import dev.litebank.dto.responses.CreateTransactionResponse;
import dev.litebank.dto.responses.TransactionResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountService accountService;

    @Test
    void testCreateTransactionService(){
        CreateTransactionRequest transactionRequest = new CreateTransactionRequest();
        transactionRequest.setTransactionType(TransactionType.CREDIT);
        transactionRequest.setAmount(new BigDecimal("20000.00"));
        transactionRequest.setAccountNumber("1234567890");

        CreateTransactionResponse transactionResponse = transactionService.create(transactionRequest);
        assertNotNull(transactionResponse);
        TransactionResponse transaction = transactionService.getTransactionById(transactionResponse.getId());

        log.info("transaction response---> {}", transaction);
        assertThat(transaction).isNotNull();
        assertThat(transaction.getAmount()).isEqualTo(transactionRequest.getAmount().toString());

    }


    @Test
    @Sql(scripts = {"/db/data.sql"})
    void testCanGetTransactionByAccountNumber(){
        List<TransactionResponse> transactions =
                transactionService.getTransactionsFor("0123456789");
        assertThat(transactions).isNotNull();
        assertThat(transactions.size()).isEqualTo(5);
    }


}
