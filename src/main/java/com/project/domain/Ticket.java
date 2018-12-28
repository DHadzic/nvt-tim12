package com.project.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.project.web.dto.TicketDTO;

@Entity
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private Date travel_time;
	
	@Column
	private boolean isActive;
	
	@OneToOne
	private Passenger user;
	
	@Column
	private TicketType type;
	
	@ManyToOne
	private PricelistItem price;
	
	public Ticket() {
		
	}
	
	public Ticket(Long id, Date travel_time, boolean isActive, Passenger user, TicketType type) {
		super();
		this.id = id;
		this.travel_time = travel_time;
		this.isActive = isActive;
		this.user = user;
		this.type = type;
	}
	
	public Ticket(TicketDTO ticketDTO) {
		super();
		this.isActive = true;
		this.user = null;
		this.type = TicketType.valueOf(ticketDTO.getType());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTravel_time() {
		return travel_time;
	}

	public void setTravel_time(Date travel_time) {
		this.travel_time = travel_time;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Passenger getUser() {
		return user;
	}

	public void setUser(Passenger user) {
		this.user = user;
	}

	public TicketType getType() {
		return type;
	}

	public void setType(TicketType type) {
		this.type = type;
	}

	public void Activate() {
		isActive = true;
	}
	
	
}