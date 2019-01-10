package com.project.domain;

import java.util.Date;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
	private Passenger user;

	@Column(nullable = false)
	private TicketType type;
	
	@Column(nullable = false)
	private TransportType transportType;

	@ManyToOne
	private PricelistItem price;

	@Column
	private HashMap<Long, String> useHistory;

	public Ticket() {

	}

	public Ticket(Long id, Date dateCreated, boolean isActive, Passenger user, TicketType type, TransportType transportType) {
		super();
		this.id = id;
		this.dateCreated = dateCreated;
		this.isActive = isActive;
		this.user = user;
		this.type = type;
		this.transportType = transportType;
	}
	
	

	public Ticket(Passenger user, TicketType type, TransportType transportType,PricelistItem price) {
		super();
		this.user = user;
		this.type = type;
		this.transportType = transportType;
		this.price = price;
		this.isActive = true;
		this.dateCreated = new Date();
		this.useHistory = new HashMap<Long, String>();
	}

	public Ticket(TicketDTO ticketDTO) {
		super();
		this.isActive = true;
		this.user = null;
		this.type = TicketType.valueOf(ticketDTO.getTicketType());
		this.transportType = TransportType.valueOf(ticketDTO.getTransportType());
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

	public TransportType getTransportType() {
		return transportType;
	}

	public void setTransportType(TransportType transportType) {
		this.transportType = transportType;
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