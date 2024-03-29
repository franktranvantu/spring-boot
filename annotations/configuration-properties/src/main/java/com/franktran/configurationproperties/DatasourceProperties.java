package com.franktran.configurationproperties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:datasource.yml")
@ConfigurationProperties(prefix = "datasource")
//@Data
public class DatasourceProperties {

  private String url;
  private String username;
  private String password;

  public DatasourceProperties() {
    System.out.println("DatasourceProperties");
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
