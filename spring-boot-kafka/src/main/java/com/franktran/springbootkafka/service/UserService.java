package com.franktran.springbootkafka.service;

import com.franktran.springbootkafka.model.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private static final String TOPIC = "user";

  private final KafkaTemplate<String, User> template;

  public UserService(KafkaTemplate<String, User> template) {
    this.template = template;
  }

  public void publish(User user) {
    template.send(TOPIC, user);
  }

  @KafkaListener(topics = TOPIC)
  public void consume(User user) {
    System.out.println(String.format("Received user: %s", user));
  }
}
