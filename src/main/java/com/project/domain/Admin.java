package com.project.domain;

import javax.persistence.Entity;

@Entity
public class Admin extends User {

	public Admin(){}
	
	public Admin(Long id,String username,String password) {
		super(id,username,password);
	}
	
}
