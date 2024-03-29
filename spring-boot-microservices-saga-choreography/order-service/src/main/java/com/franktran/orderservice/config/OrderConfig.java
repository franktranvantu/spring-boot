package com.franktran.orderservice.config;

import com.franktran.commondto.event.order.OrderEvent;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

@Configuration
public class OrderConfig {

  @Bean
  public Sinks.Many<OrderEvent> orderSink() {
    return Sinks.many().unicast().onBackpressureBuffer();
  }

  @Bean
  public Supplier<Flux<OrderEvent>> orderSupplier(Sinks.Many<OrderEvent> sink) {
    return sink::asFlux;
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

}
