package com.softtek.controller;

import java.security.Principal;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedBusUserOperationController {
	@GetMapping("/home")
	public String showHome()
	{
		return "<h1> welcome to the redbus home page </h1>";
	}
	@GetMapping("/after")
	public String afterLoginpage()
	{
		return  "<h1> page login after activity </h1>";
		
	}
	@GetMapping("/user")

	public Authentication showUserDetails(Principal principal)

	{
	   Authentication auth=SecurityContextHolder.getContext().getAuthentication();
	   return auth;
	}
	
	

	
}
