package org.triunfo.fetchaccount.model.repositories;

import org.triunfo.fetchaccount.model.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
}
