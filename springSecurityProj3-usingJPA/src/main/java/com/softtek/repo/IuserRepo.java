package com.softtek.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softtek.entity.UserInfo;

public interface IuserRepo  extends JpaRepository<UserInfo, Integer>{
	
	public Optional<UserInfo> findByuname(String uname);

}
