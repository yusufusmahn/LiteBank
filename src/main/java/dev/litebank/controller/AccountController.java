package dev.litebank.controller;


import dev.litebank.dto.requests.DepositRequest;
import dev.litebank.dto.responses.DepositResponse;
import dev.litebank.dto.responses.ErrorResponse;
import dev.litebank.exception.AccountNotFoundException;
import dev.litebank.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/account")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;


    @PostMapping
    public ResponseEntity<?> deposit(@RequestBody DepositRequest depositRequest){
        try {
            DepositResponse depositResponse = accountService.deposit(depositRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(depositResponse);

        }catch (AccountNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse<>(e.getMessage()));
        }

    }
}
