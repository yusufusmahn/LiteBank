package dev.litebank.dto.requests;


import dev.litebank.model.AccountType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccountRequest {
    private String name;
    private String username;
    private String password;
    private AccountType accountType;
}
