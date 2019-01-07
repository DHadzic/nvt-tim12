package com.project.domain;

import java.util.Date;
import java.util.HashMap;

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
	private Date dateCreated;

	@Column
	private boolean isActive;

	@ManyToOne
	private Passenger user;

	@Column(nullable = false)
	private TicketType type;

	@ManyToOne
	private PricelistItem price;

	@Column
	private HashMap<Long, String> useHistory;

	public Ticket() {

	}

	public Ticket(Long id, Date dateCreated, boolean isActive, Passenger user, TicketType type) {
		super();
		this.id = id;
		this.dateCreated = dateCreated;
		this.isActive = isActive;
		this.user = user;
		this.type = type;
	}
	
	

	public Ticket(Passenger user, TicketType type, PricelistItem price) {
		super();
		this.user = user;
		this.type = type;
		this.price = price;
		this.isActive = true;
		this.dateCreated = new Date();
		this.useHistory = new HashMap<Long, String>();
	}

	public Ticket(TicketDTO ticketDTO) {
		super();
		this.isActive = true;
		this.user = null;
		this.type = TicketType.valueOf(ticketDTO.getType());
		this.dateCreated = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public PricelistItem getPrice() {
		return price;
	}

	public void setPrice(PricelistItem price) {
		this.price = price;
	}

	public HashMap<Long, String> getUseHistory() {
		return useHistory;
	}

	public void setUseHistory(HashMap<Long, String> useHistory) {
		this.useHistory = useHistory;
	}

}