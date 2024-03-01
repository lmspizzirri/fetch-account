package org.triunfo.fetchaccount.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.triunfo.fetchaccount.DTO.AccountDto;
import org.triunfo.fetchaccount.model.entities.Account;
import org.triunfo.fetchaccount.service.FetchService;
import org.triunfo.fetchaccount.service.exceptions.AccountNotFoundException;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/fetch")
public class FetchController {
    private final FetchService fetchService;
    @Autowired
    public FetchController(FetchService fetchService) {
        this.fetchService = fetchService;
    }

    @GetMapping
    public ResponseEntity<Account> getAccount() {
        Account selectedAccount = fetchService.getAccount();
        System.out.println(selectedAccount);
        return ResponseEntity.ok(selectedAccount);
    }

    @GetMapping("/accounts")
    @Secured({"VIEWER", "USER", "ADMIN"})
    public ResponseEntity<List<Account>> getAllAccount() {
        List<Account> allAccount = fetchService.getAllAccount();
        return ResponseEntity.ok(allAccount);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createAccount(@RequestBody Account account) {
        try {
            Account savedAccount = fetchService.createAccount(account);
            return ResponseEntity.status(HttpStatus.CREATED).body(AccountDto.fromAccount(savedAccount));
        } catch(AccountNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteAccount(@RequestBody Integer id) {
        fetchService.deleteAccount(id);
    }

    @PutMapping("/change")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void changePassword(@RequestBody Map<String, Object> info) {
        Integer id = (Integer) info.get("id");
        String newPassword = (String) info.get("password");
        fetchService.changePassword(id, newPassword);
    }
}
