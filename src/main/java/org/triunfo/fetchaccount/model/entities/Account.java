package org.triunfo.fetchaccount.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * The type Account.
 */
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    private Integer id;
    private String login;
    private String password;

    /**
     * Instantiates a new Account.
     */
    public Account () {}

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets login.
     *
     * @param login the login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Instantiates a new Account.
     *
     * @param id       the id
     * @param login    the login
     * @param password the password
     */
    public Account(Integer id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

}

