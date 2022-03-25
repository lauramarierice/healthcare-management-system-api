package com.fsd.phase2.healthcaremanagementsystem.users.accounts;

import com.fsd.phase2.healthcaremanagementsystem.commons.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    private final UserAccountMapper userAccountMapper;

    public UserAccountDTO addFundsToAccountNumber(Long userId, Long accountNumber, Double funds) {

        return userAccountMapper.map(userAccountRepository.findByUserIdAndAccountNumber(userId, accountNumber)
                .map(userAccount -> {
                    double balance = userAccount.getBalance();
                    balance = Precision.round(balance + funds, 2);

                    userAccount.setBalance(balance);
                    userAccountRepository.save(userAccount);
                    return userAccount;
                })
                .orElseThrow(() -> new EntityNotFoundException("Account not found for the user, please try again.")));

    }

    public Double getUserBalanceByUserId(Long userId, Long accountNumber) {
        UserAccountDTO userAccountDTO = userAccountMapper.map(userAccountRepository.findByUserIdAndAccountNumber(userId, accountNumber)
                .orElseThrow(() -> new EntityNotFoundException("User account details not found")));
        return userAccountDTO.getBalance();
    }

    public void updateUserBalance(Long userId, Long accountNumber, Double funds) {
        userAccountMapper.map(userAccountRepository.findByUserIdAndAccountNumber(userId, accountNumber)
                .map(userAccount -> {
                    double balance = userAccount.getBalance();
                    balance = Precision.round(balance - funds, 2);

                    userAccount.setBalance(balance);
                    userAccountRepository.save(userAccount);
                    return userAccount;
                })
                .orElseThrow(() -> new EntityNotFoundException("Account not found for the user, please try again.")));
    }
}
