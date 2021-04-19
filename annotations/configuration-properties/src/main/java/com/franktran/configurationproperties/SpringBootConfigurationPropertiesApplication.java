package com.franktran.configurationproperties;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties
public class SpringBootConfigurationPropertiesApplication {

  private final DatasourceProperties datasourceProperties;

  public SpringBootConfigurationPropertiesApplication(DatasourceProperties datasourceProperties) {
    this.datasourceProperties = datasourceProperties;
  }

  public static void main(String[] args) {
    SpringApplication.run(SpringBootConfigurationPropertiesApplication.class, args);
  }

  @Bean
  public CommandLineRunner runner() {
    return args -> {
      System.out.println(datasourceProperties.getUrl());
      System.out.println(datasourceProperties.getUsername());
      System.out.println(datasourceProperties.getPassword());
    };
  }

}
