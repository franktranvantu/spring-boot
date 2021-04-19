package com.franktran.orderservice.config;

import com.google.common.collect.ImmutableMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ProductPriceConfig {

  @Bean
  public Map<Integer, Integer> productPrice() {
    return ImmutableMap.of(
        1, 100,
        2, 200,
        3, 300
    );
  }
}
