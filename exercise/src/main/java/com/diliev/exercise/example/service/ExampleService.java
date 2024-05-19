package com.diliev.exercise.example.service;

import com.diliev.exercise.example.entity.Person;
import com.diliev.exercise.example.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ExampleService {
    @Autowired
    PersonRepository personRepo;

    public Person addPerson(Person person) {
        return personRepo.save(person);
    }

    public void deletePerson(UUID personId) {
        personRepo.deleteById(personId);
    }

    public List<Person> getPersons() {
        return personRepo.findAll();
    }

    public Person updatePerson(Person newPerson, UUID id) {
        return personRepo.findById(id)
                .map(p -> {
                    p.setName(newPerson.getName());
                    p.setEmail(newPerson.getNumber());
                    p.setEmail(newPerson.getEmail());
                    return personRepo.save(p);
                })
                .orElseGet(() ->
                        personRepo.save(newPerson)
                );
    }
}

