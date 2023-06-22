package com.softtek.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.softtek.entity.UserInfo;

public interface IuserService extends UserDetailsService {
	public String register(UserInfo details);
	//public UserDetails loadUserByName(String UserName) ;

}
