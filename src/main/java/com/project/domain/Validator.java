package com.project.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Validator extends User {

	@Column
	private String name;
	
	@Column
	private String surname;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
}
