package com.softtek;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringSecurityProj1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityProj1Application.class, args);
	}
  @Bean
  public BCryptPasswordEncoder bcencoder()
  {
	  return  new BCryptPasswordEncoder();
  }
	
	
}
