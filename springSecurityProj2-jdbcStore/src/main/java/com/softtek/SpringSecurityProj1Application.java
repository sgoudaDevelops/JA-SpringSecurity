package com.softtek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringSecurityProj1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityProj1Application.class, args);
	
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	String pwd1 = encoder.encode("softtek2021");
	String pwd2 = encoder.encode("softtek2023");
	System.out.println(pwd1);
	System.out.println(pwd2);
	}

}
