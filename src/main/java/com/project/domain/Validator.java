package com.project.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Validator extends User {

	@Column
	private String name;
	
	@Column
	private String surname;
	
}
