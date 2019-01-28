package com.project.domain;

import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.project.web.dto.VerifyRequestDTO;

@Entity
public class Validator extends User {

	@Column
	private String name;
	
	@Column
	private String surname;
	
	@Column
	private ArrayList<String> verificationRequest;

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

	public ArrayList<String> getVerificationRequest() {
		return verificationRequest;
	}

	public void setVerificationRequest(ArrayList<String> verificationRequest) {
		this.verificationRequest = verificationRequest;
	}


	
}
