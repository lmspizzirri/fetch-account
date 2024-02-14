package org.triunfo.fetchaccount.service;

import org.triunfo.fetchaccount.model.entities.Account;
import org.triunfo.fetchaccount.model.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.triunfo.fetchaccount.service.exceptions.AccountNotFoundException;

import java.util.concurrent.atomic.AtomicInteger;


@Service
public class FetchService {
    private final AccountRepository accountRepository;
    static AtomicInteger atomicInteger = new AtomicInteger(1);

    @Autowired
    public FetchService (AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccount() throws AccountNotFoundException {
        int index = atomicInteger.getAndUpdate(x -> {
            if (x >= 29)
                return 0;
            else
                return x+1;
                });
        return accountRepository.findById(index).
                orElseThrow(() -> new AccountNotFoundException("Account Not Found"));
    }
}