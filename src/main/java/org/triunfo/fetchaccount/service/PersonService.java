package org.triunfo.fetchaccount.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.triunfo.fetchaccount.model.entities.Person;
import org.triunfo.fetchaccount.model.repositories.PersonRepository;


@Service
public class PersonService implements UserDetailsService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person create(Person person) {
        String hashedPassword = new BCryptPasswordEncoder()
                .encode(person.getPassword());

        person.setPassword(hashedPassword);

        return personRepository.save(person);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return personRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}