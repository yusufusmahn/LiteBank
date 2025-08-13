package dev.litebank.dto.requests;


import dev.litebank.dto.TransactionType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateTransactionRequest {
    private TransactionType transactionType;
    private BigDecimal amount;
    private String accountNumber;
}
