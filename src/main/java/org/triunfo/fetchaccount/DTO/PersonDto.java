package org.triunfo.fetchaccount.DTO;

import org.triunfo.fetchaccount.model.entities.Person;

public record PersonDto(
        Integer id,
        String username,
        String password,
        String role
) {

    public static PersonDto fromEntity(Person person) {
        return new PersonDto(
                person.getId(),
                person.getUsername(),
                person.getPassword(),
                person.getRole()
        );
    }
}