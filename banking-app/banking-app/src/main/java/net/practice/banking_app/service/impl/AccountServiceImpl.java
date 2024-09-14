package net.practice.banking_app.service.impl;

import net.practice.banking_app.dto.AccountDto;
import net.practice.banking_app.entity.Account;
import net.practice.banking_app.mapper.AccountMapper;
import net.practice.banking_app.repository.AccountRepository;
import net.practice.banking_app.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service  //to automatically create a  spring bean for this class
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDTO(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        //checking if the particular account is existing or not
        Account account = accountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Account does not exist"));
        return AccountMapper.mapToAccountDTO(account);
    }

    @Override
    public AccountDto DepositAccount(Long id, double balance) {
       Account account = accountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Account does not exist"));

       double total = account.getBalance() + balance;
       account.setBalance(total);
       Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDTO(savedAccount);
    }

    @Override
    public AccountDto Withdraw(Long id, double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't complete the request as Account with following details does not exist"));
        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDTO(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map((account) -> AccountMapper.mapToAccountDTO(account))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Can't complete the request as Account does not exist"));

        accountRepository.deleteById(id);
    }
}
