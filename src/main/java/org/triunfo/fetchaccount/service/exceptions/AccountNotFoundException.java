package org.triunfo.fetchaccount.service.exceptions;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(String messageError) {
        super(messageError);
    }
}
