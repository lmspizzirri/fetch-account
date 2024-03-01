package org.triunfo.fetchaccount.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.triunfo.fetchaccount.DTO.PersonCreationDto;
import org.triunfo.fetchaccount.DTO.PersonDto;
import org.triunfo.fetchaccount.model.entities.Person;
import org.triunfo.fetchaccount.service.PersonService;
import org.triunfo.fetchaccount.service.exceptions.AccountNotFoundException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/persons")
public class PersonController {
    private final PersonService personService;
    @Autowired
    private PersonController(PersonService personService) {
        this.personService = personService;
    }
    @PostMapping
    public ResponseEntity<?> createPerson(@RequestBody PersonCreationDto personDto) {
        try {
            Person savedPerson = personService.create(personDto.toEntity());
            return ResponseEntity.status(HttpStatus.CREATED).body(PersonDto.fromEntity(savedPerson));
        } catch(AccountNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
}
