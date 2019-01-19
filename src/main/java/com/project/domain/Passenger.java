package com.project.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class Passenger extends User {

	@Column
	private String name;
	
	@Column
	private String surname;
	
	@Column
	private GregorianCalendar birthDate;
	
	@Column
	private PassengerType type;
	
	@Column
	private String documentID;
	
	@OneToMany(mappedBy="user")
    @OrderBy("name ASC")
	private List<Ticket> tikcets;
	
	public Passenger() {
		
	}

	public Passenger(String name, String surname, GregorianCalendar birthDate, PassengerType type, String documentID,
			ArrayList<Ticket> tikcet) {
		super();
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
		this.type = type;
		this.documentID = documentID;
		this.tikcets = tikcet;
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

	public String getDocumentID() {
		return documentID;
	}

	public void setDocumentID(String documentID) {
		this.documentID = documentID;
	}

	public List<Ticket> getTikcet() {
		return tikcets;
	}

	public void setTikcet(ArrayList<Ticket> tikcet) {
		this.tikcets = tikcet;
	}
}
