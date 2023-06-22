package com.softtek.entity;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="securityUsers")
@Data
public class UserInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String unid;
	@Column(length=20,unique = true,nullable = false)
	private String uname;
	@Column(length=150,nullable = false)
	private String pwd;
	@Column(length=150,nullable = false)
	private String email;
	private boolean status=true;
	
	@ElementCollection
	@CollectionTable(name="securityRoles",joinColumns =@JoinColumn(name="user_id",referencedColumnName = "unid"))
	private Set<String> roles;
	

}
