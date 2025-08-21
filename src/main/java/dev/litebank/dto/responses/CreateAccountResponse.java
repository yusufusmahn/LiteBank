package dev.litebank.dto.responses;


import dev.litebank.dto.AccountType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccountResponse {
    private String accountNumber;
    private String accountHolderName;
    private AccountType accountType;
}
