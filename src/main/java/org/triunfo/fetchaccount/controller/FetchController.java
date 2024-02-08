package org.triunfo.fetchaccount.controller;


import org.triunfo.fetchaccount.model.entities.Account;
import org.triunfo.fetchaccount.service.FetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fetch")
public class FetchController {
    private final FetchService fetchService;

    @Autowired
    public FetchController(FetchService fetchService) {
        this.fetchService = fetchService;
    }

    @GetMapping()
    public ResponseEntity<Account> getAccount() {
        Account selectedAccount = fetchService.getAccount();
        return ResponseEntity.ok(selectedAccount);
    }
}
