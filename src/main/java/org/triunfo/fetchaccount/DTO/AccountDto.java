package org.triunfo.fetchaccount.DTO;

import org.triunfo.fetchaccount.model.entities.Account;

public record AccountDto(Integer id, String login, String password) {

    public static AccountDto fromAccount(Account account) {
        return new AccountDto(account.getId(), account.getLogin(), account.getPassword());
    }
}
