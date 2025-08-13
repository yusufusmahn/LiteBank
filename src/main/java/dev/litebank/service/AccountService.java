package dev.litebank.service;

import dev.litebank.dto.requests.DepositRequest;
import dev.litebank.dto.responses.DepositResponse;
import dev.litebank.dto.responses.ViewAccountResponse;

public interface AccountService {

    DepositResponse deposit(DepositRequest depositRequest);

    ViewAccountResponse viewDetailsFor(String number);
}
