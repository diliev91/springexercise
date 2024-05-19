package com.diliev.exercise.example.controller;


import com.diliev.exercise.example.entity.Person;
import com.diliev.exercise.example.service.ExampleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("v1/person")
public class PersonController {
    @Autowired
    ExampleService service;

    @GetMapping
    public List<Person> getPersons() {
        log.info("Getting all Persons.");
        return service.getPersons();
    }

    @PostMapping
    public Person create(@RequestBody Person person) {
        String msg = "Creating person:" + person.toString();
        log.info(msg);
        return service.addPerson(person);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") UUID personId) {
        String msg = "Removing Person with id: " + personId;
        log.info(msg);
        service.deletePerson(personId);
    }

    @PutMapping("/{id}")
    public Person update(@RequestBody Person newPerson, @PathVariable UUID id) {

        return service.updatePerson(newPerson, id);
    }

}
