package com.mahi.kafkaproducer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessageToTopic( String message) {
       CompletableFuture<SendResult<String,Object>>future =   kafkaTemplate.send("kraft-topic", message);
       future.whenComplete((result,ex)->{
           if(ex == null) {
               System.out.println("Sent message =[" + message + "] with Offset=[" + result.getRecordMetadata().offset() + "]");
           }else{
               System.out.println("unable to send message=[" + message + "] due to =[" + ex.getMessage());
           }
       });
    }

}
