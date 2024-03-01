package org.triunfo.fetchaccount.DTO;

import org.triunfo.fetchaccount.model.entities.Person;

public record PersonCreationDto(
        String username,
        String password,
        String role
) {

    public Person toEntity() {
        return new Person(null, username, password, role);
    }
}