package org.triunfo.fetchaccount.service;

import org.triunfo.fetchaccount.model.entities.Account;
import org.triunfo.fetchaccount.model.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.triunfo.fetchaccount.service.exceptions.AccountNotFoundException;


@Service
public class FetchService {
    private final AccountRepository accountRepository;
    static Integer index;

    @Autowired
    public FetchService (AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccount() throws AccountNotFoundException {
        Account returnedAccount = accountRepository.findById(index).
                orElseThrow(() -> new AccountNotFoundException("Account Not Found"));
        if(index == 30) {
            index = 0;
        } else {
            index += 1;
        }
        return returnedAccount;
    }
}