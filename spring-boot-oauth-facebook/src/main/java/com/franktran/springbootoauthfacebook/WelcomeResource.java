package com.franktran.springbootoauthfacebook;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/facebook")
public class WelcomeResource {

  @GetMapping
  public String welcome(Principal principal) {
    Map<String, String> authDetails = (Map<String, String>) ((OAuth2Authentication) principal)
        .getUserAuthentication()
        .getDetails();

    String userName = authDetails.get("name");

    return "Hello " + userName + ", Welcome to Spring Boot Oauth2 with Facebook!";
  }
}