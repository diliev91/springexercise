package com.diliev.exercise.example.service;

import com.diliev.exercise.example.entity.Person;
import com.diliev.exercise.example.kafka.producer.MessageProducer;
import com.diliev.exercise.example.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ExampleService {
    private final PersonRepository personRepo;
    private final MessageProducer messageProducer;

    @Autowired
    public ExampleService(PersonRepository personRepo, MessageProducer messageProducer) {
        this.personRepo = personRepo;
        this.messageProducer = messageProducer;
    }



    @KafkaListener(topics = "${kafka.topic}", groupId = "group_id")
    public void topicListener(Person p) {
       log.info("Received Message in group foo: " + p);
        personRepo.save(p);
    }

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

