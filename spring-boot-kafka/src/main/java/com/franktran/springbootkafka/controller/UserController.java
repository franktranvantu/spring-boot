package com.franktran.springbootkafka.controller;

import com.franktran.springbootkafka.model.User;
import com.franktran.springbootkafka.service.MessageService;
import com.franktran.springbootkafka.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka/user")
public class UserController {

  private final UserService service;

  public UserController(UserService service) {
    this.service = service;
  }

  @PostMapping("/publish")
  public String publish(@RequestBody User user) {
    service.publish(user);
    return "Publish success!";
  }
}
