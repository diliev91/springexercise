package com.diliev.exercise.example.service;

import com.diliev.exercise.example.entity.Person;
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
    @Autowired
    PersonRepository personRepo;

    @Value(value = "${kafka.topic}")
    private String kafkaTopic;
    @Autowired
    private KafkaTemplate<String, Person> kafkaTemplate;

    public void sendMessage(Person p) {
        kafkaTemplate.send(kafkaTopic, p);
    }
    @KafkaListener(topics = "${kafka.topic}", groupId = "1")
    public void topicListener(Person p) {
       log.info("Received Message in group foo: " + p);
        personRepo.save(p);
    }

    public Person addPerson(Person person) {
        this.sendMessage(person);
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

