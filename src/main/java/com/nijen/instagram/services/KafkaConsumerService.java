package com.nijen.instagram.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nijen.instagram.DTO.PhotoUploadedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @Autowired
    private FeedService feedService;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "new-post", groupId = "Instagram", autoStartup = "true", containerFactory = "kafkaListenerFactory")
    public void consumeNewPosts(PhotoUploadedEvent message){
        try {
            System.out.println("Message from topic => " + message);
            feedService.handlePhotoUpload(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
