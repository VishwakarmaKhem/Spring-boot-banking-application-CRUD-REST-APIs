package net.practice.banking_app.controller;

import net.practice.banking_app.dto.AccountDto;
import net.practice.banking_app.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //building add account REST API
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    //get accounts by id REST API
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable("id") Long id){
        return new ResponseEntity<>(accountService.getAccountById(id), HttpStatus.OK);
    }

    //deposit REST API
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable("id") Long id,
                                               @RequestBody Map<String, Double> request){
        return new ResponseEntity<>(accountService.DepositAccount(id, request.get("balance")), HttpStatus.OK);
    }

    //withdraw REST API
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable("id") Long id,
                                                @RequestBody Map<String, Double> request){
        return new ResponseEntity<>(accountService.Withdraw(id, request.get("amount")), HttpStatus.OK);
    }

    //get all accounts rest api
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
    }

    //delete account Rest API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account has been successfully deleted");
    }
}
