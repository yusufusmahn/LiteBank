package dev.litebank.dto.responses;

import dev.litebank.model.TransactionStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DepositResponse {
    private TransactionStatus transactionStatus;
    private BigDecimal amount;
    private String transactionId;
}
