package dev.litebank.dto.responses;


import dev.litebank.model.AccountType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccountResponse {
    private String accountNumber;
    private String accountHolderName;
    private AccountType accountType;
    private String id;
}
