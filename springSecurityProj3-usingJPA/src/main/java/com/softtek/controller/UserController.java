package com.softtek.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softtek.entity.UserInfo;
import com.softtek.repo.IuserRepo;
import com.softtek.service.IuserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IuserService service;
	
	@GetMapping("/")
	public String showhome()
	{
		return "home";
	}
	
	@GetMapping("/register")
	public String showUserRegistration(@ModelAttribute("userInfo") UserInfo details)
	{
		return "user_register";
	}
	
	@PostMapping("/register")
	public String registerUser(Map<String, Object>map, @ModelAttribute("userinfo") UserInfo details)
	{
		String register = service.register(details);
		map.put("message", register);
		return "sucess";
	}
	@GetMapping("/showLogin")
	public String showLoginPage()
	{
		return "custom_login";
	}
	

}
