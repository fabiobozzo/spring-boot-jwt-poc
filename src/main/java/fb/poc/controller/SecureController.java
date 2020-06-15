package fb.poc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecureController {

  @RequestMapping({"/hello"})
  public String greet() {
    return "Hello!";
  }

}