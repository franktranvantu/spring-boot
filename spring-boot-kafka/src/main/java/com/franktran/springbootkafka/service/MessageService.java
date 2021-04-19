package com.franktran.springbootkafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

  private static final String TOPIC = "message";

  private final KafkaTemplate<String, String> template;

  public MessageService(KafkaTemplate<String, String> template) {
    this.template = template;
  }

  public void publish(String message) {
    template.send(TOPIC, message);
  }

  @KafkaListener(topics = TOPIC)
  public void consume(String message) {
    System.out.println(String.format("Received message: %s", message));
//    if (message.startsWith("Error")) {
    throw new RuntimeException("failed");
//    }
  }
}
