package com.franktran.producer.controller;

import com.franktran.producer.config.RabbitMQConfig;
import com.franktran.producer.model.CustomMessage;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/producer")
public class MessageController {

  private final AmqpTemplate template;

  public MessageController(AmqpTemplate template) {
    this.template = template;
  }

  @PostMapping("/publish")
  public String publishMessage(@RequestBody CustomMessage message) {
    message.setId(UUID.randomUUID().toString());
    message.setDate(new Date());

    template.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, message);

    return "Message published!";
  }
}
