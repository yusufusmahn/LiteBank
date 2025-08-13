package dev.litebank.dto.responses;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TransactionResponse {
    private String id;
    private String amount;

}
