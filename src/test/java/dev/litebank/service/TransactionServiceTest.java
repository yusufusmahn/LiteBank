package dev.litebank.service;


import dev.litebank.dto.TransactionType;
import dev.litebank.dto.requests.CreateTransactionRequest;
import dev.litebank.dto.responses.CreateTransactionResponse;
import dev.litebank.dto.responses.TransactionResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @Test
    void testTransactionService(){
        CreateTransactionRequest transactionRequest = new CreateTransactionRequest();
        transactionRequest.setTransactionType(TransactionType.CREDIT);
        transactionRequest.setAmount(new BigDecimal("20000.00"));
        transactionRequest.setAccountNumber("1234567890");

        CreateTransactionResponse transactionResponse = transactionService.create(transactionRequest);
        assertNotNull(transactionResponse);
        TransactionResponse transaction = transactionService.getTransactionById(transactionResponse.getId());
        assertThat(transaction).isNotNull();
        assertThat(transaction.getAmount()).isEqualTo(transactionRequest.getAmount().toString());

    }

}
