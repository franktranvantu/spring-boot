package com.franktran.springbootkafka.controller;

import com.franktran.springbootkafka.service.MessageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class MessageController {

  private final MessageService service;

  public MessageController(MessageService service) {
    this.service = service;
  }

  @PostMapping("/publish")
  public String publish(@RequestBody String message) {
    service.publish(message);
    return "Publish success!";
  }
}
