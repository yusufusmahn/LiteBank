package dev.litebank.service;

import dev.litebank.dto.requests.DepositRequest;
import dev.litebank.dto.responses.DepositResponse;

public interface AccountService {

    DepositResponse deposit(DepositRequest depositRequest);
}
