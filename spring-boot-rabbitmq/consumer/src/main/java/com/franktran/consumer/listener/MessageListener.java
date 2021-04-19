package com.franktran.consumer.listener;

import com.franktran.consumer.config.RabbitMQConfig;
import com.franktran.consumer.model.CustomMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

  @RabbitListener(queues = RabbitMQConfig.QUEUE)
  public void listen(CustomMessage message) {
    System.out.println(message);
  }
}
