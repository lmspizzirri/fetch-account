package org.triunfo.fetchaccount.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.triunfo.fetchaccount.DTO.AccountDto;
import org.triunfo.fetchaccount.model.entities.Account;
import org.triunfo.fetchaccount.service.FetchService;
import org.triunfo.fetchaccount.service.exceptions.AccountNotFoundException;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/fetch")
public class FetchController {
    private final FetchService fetchService;
    @Autowired
    private FetchController(FetchService fetchService) {
        this.fetchService = fetchService;
    }

    @GetMapping
    public ResponseEntity<Account> getAccount() {
        Account selectedAccount = fetchService.getAccount();
        return ResponseEntity.ok(selectedAccount);
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAccount() {
        List<Account> allAccount = fetchService.getAllAccount();
        return ResponseEntity.ok(allAccount);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody Account account) {
        try {
            Account savedAccount = fetchService.createAccount(account);
            return ResponseEntity.status(HttpStatus.CREATED).body(AccountDto.fromAccount(savedAccount));
        } catch(AccountNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
}
