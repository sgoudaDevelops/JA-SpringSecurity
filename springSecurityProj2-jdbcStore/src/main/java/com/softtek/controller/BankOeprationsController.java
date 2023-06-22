package com.softtek.controller;

import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BankOeprationsController {
    @GetMapping("/")
	public String showhome()
	{
		return "home";
	}
    @GetMapping("/balance")
    public String showbalance(Map<String, Object> map) {
    	map.put("account_balance", new Random().nextInt(1000000));
    	return "showBalance";
    }
    @GetMapping("/loanApprove")
    public String approveBalance(Map<String , Object> map)
    {
    	map.put("pay", 100000);
    	return "loanapprove";
    }
    @GetMapping("/denied")
    public String showdeniedPage(Map<String, Object> map)
    {
    	return "/authorisation_failed";
    }
	@GetMapping("/offers")
	public String showOffers() {
		return "showOffer";
	}
	
}
