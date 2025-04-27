package com.nijen.instagram.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nijen.instagram.DTO.PhotoUploadedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendNewPostEvent(PhotoUploadedEvent photoUploadedEvent){

        try {

            String json = objectMapper.writeValueAsString(photoUploadedEvent);
            kafkaTemplate.send("new-post", json);
            System.out.println("Message sent to topic => " + json);

        } catch (JsonProcessingException e){
            e.printStackTrace();
        }
    }
}
