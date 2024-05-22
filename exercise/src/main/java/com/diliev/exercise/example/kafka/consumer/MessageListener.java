package com.diliev.exercise.example.kafka.consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {

    @KafkaListener(topics = "${kafka.topic}", groupId = "group_id")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }
}