package com.project.web.dto;

import java.util.Date;
import java.util.GregorianCalendar;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.domain.PassengerType;

public class PassengerDTO {
	private String username;
	private String password;
	private String name;
	private String surname;
	@JsonFormat(pattern="yyyy-MM-dd")
	private GregorianCalendar birthDate;
	private PassengerType type;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
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
	public GregorianCalendar getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(GregorianCalendar birthDate) {
		this.birthDate = birthDate;
	}
	public PassengerType getType() {
		return type;
	}
	public void setType(PassengerType type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "PassengerDTO [username=" + username + ", password=" + password + ", name=" + name + ", surname="
				+ surname + ", birthDate=" + birthDate + ", type=" + type + "]";
	}
	
	
}
