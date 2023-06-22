package com.softtek.serviceImpl;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.softtek.entity.UserInfo;
import com.softtek.repo.IuserRepo;
import com.softtek.service.IuserService;



@Service
public class UserServiceImpl implements IuserService {

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired 
	private IuserRepo userrepo;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> opt = userrepo.findByuname(username);
		if(opt.isEmpty())
			throw new IllegalArgumentException("user not find");
		else
		{
			UserInfo Info = opt.get();
			User user = new User(Info.getUname(),Info.getPwd(),Info.getRoles().stream().map(role->new SimpleGrantedAuthority(role)).collect(Collectors.toSet()));
			return user;
		}
	}

	@Override
	public String register(UserInfo details) {
		details.setPwd(encoder.encode(details.getPwd()));
		
		return userrepo.save(details).getUnid()+"userId is registered";
	}

  

}
