package de.fgbs.access.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("helloWorld")
public class HelloWorld {
  private static final Logger log = LoggerFactory.getLogger(HelloWorld.class);

  private String firstName = "";
  private String lastName = "";

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String showGreeting() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    return "Hello " + authentication.getName() + "!";
  }

  public void doLogOutput() {
    log.info("Button pressed!");
  }
}
