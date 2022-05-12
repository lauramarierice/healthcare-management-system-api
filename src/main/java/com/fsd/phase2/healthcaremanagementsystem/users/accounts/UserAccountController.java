package com.fsd.phase2.healthcaremanagementsystem.users.accounts;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserAccountController {

    private final UserAccountService userAccountService;

    @GetMapping(value = "/users/{userid}/accounts")
    public UserAccountDTO getUserAccountInformation(@PathVariable("userid") Long userId) {
        return userAccountService.getUserAccountDetails(userId);
    }

    @PutMapping(value = "/users/{userid}/accounts/{accountid}", params = "funds")
    public UserAccountDTO addFundsToUserAccount(@PathVariable("userid") Long userId, @PathVariable("accountid") Long accountId, @RequestParam("funds") Double funds) {
        return userAccountService.addFundsToAccountNumber(userId, accountId, funds);
    }
}
