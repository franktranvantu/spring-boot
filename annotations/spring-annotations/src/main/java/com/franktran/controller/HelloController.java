package com.franktran.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

  @RequestMapping("/get-cookie")
  public String welcome(Model model, @CookieValue("JSESSIONID") String cookie) {
    model.addAttribute("JSESSIONID", cookie);
    return "index";
  }
}
