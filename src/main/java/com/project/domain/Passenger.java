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
	
	@Column
	private Boolean isVerified;
	
	@OneToMany(mappedBy="user")
	private List<Ticket> tikcets;
	
	public Passenger() {
		
	}

	public Passenger(String name, String surname, GregorianCalendar birthDate, PassengerType type, String documentID,
			boolean isVerified ,ArrayList<Ticket> tikcet) {
		super();
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
		this.type = type;
		this.documentID = documentID;
		this.isVerified = isVerified;
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

	public List<Ticket> getTikcets() {
		return tikcets;
	}

	public void setTikcets(ArrayList<Ticket> tikcets) {
		this.tikcets = tikcets;
	}

	public Boolean isVerified() {
		return isVerified;
	}

	public void setVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}
	
	
}
