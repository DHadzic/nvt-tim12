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
	private HashMap<String, VerifyRequestDTO> verificationRequest;

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

	public HashMap<String, VerifyRequestDTO> getVerificationRequest() {
		return verificationRequest;
	}

	public void setVerificationRequest(HashMap<String, VerifyRequestDTO> verificationRequest) {
		this.verificationRequest = verificationRequest;
	}
	
}
