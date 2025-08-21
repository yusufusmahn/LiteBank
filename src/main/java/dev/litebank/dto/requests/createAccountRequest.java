package dev.litebank.dto.requests;


import dev.litebank.dto.AccountType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class createAccountRequest {
    private String name;
    private String username;
    private String password;
    private AccountType accountType;
}
