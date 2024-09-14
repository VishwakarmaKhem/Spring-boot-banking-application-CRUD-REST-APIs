package net.practice.banking_app.service;

import net.practice.banking_app.dto.AccountDto;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);

    AccountDto DepositAccount(Long id, double balance);

    AccountDto Withdraw(Long id, double amount);

    List<AccountDto> getAllAccounts();

    void deleteAccount(Long id);
}
