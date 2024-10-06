package com.mahi.kafkaproducer.controller;

import com.mahi.kafkaproducer.service.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer-app")
public class EventController {

    @Autowired
    KafkaMessagePublisher publisher;

    @GetMapping("publish/{message}")
    public ResponseEntity<?> sendMessage( @PathVariable String message) {
        try {
            for(int i = 0; i<=100; i++) {
                publisher.sendMessageToTopic(message + ":"+i);
            }
            return ResponseEntity.ok("message published");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
