package com.project.domain;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Passenger extends User {

	@Column
	private String name;
	
	@Column
	private String surname;
	
	@Column
	private Date birthDate;
	
	@Column
	private PassengerType type;
	
	@Column
	private String documentID;
	
	@Column
	private ArrayList<Ticket> tikcet;
	
	public Passenger() {
		
	}

	public Passenger(String name, String surname, Date birthDate, PassengerType type, String documentID,
			ArrayList<Ticket> tikcet) {
		super();
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
		this.type = type;
		this.documentID = documentID;
		this.tikcet = tikcet;
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public PassengerType getType() {
		return type;
	}

	public void setType(PassengerType type) {
		this.type = type;
	}

	public String getDocumentID() {
		return documentID;
	}

	public void setDocumentID(String documentID) {
		this.documentID = documentID;
	}

	public ArrayList<Ticket> getTikcet() {
		return tikcet;
	}

	public void setTikcet(ArrayList<Ticket> tikcet) {
		this.tikcet = tikcet;
	}
}
