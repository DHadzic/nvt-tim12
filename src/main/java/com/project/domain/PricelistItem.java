package com.project.domain;

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
import com.project.web.dto.PricelistItemDTO;

@Entity
public class PricelistItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pricelist_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
	private Pricelist pricelist;
	
	@Column
	private TicketType ticketType;
	
	@Column
	private TransportType transportType;
	
	@Column
	private Double price;

	public PricelistItem(Pricelist pricelist, TicketType ticketType, TransportType transportType, double price) {
		super();
		this.pricelist = pricelist;
		this.ticketType = ticketType;
		this.transportType = transportType;
		this.price = price;
	}

	public PricelistItem() {
		super();
	}
	
	public PricelistItem (Pricelist pl, PricelistItemDTO pliDTO){
		super();
		this.pricelist = pl;
		this.ticketType = TicketType.valueOf(pliDTO.getTicketType());
		this.transportType = TransportType.valueOf(pliDTO.getTransportType());
		this.price = pliDTO.getPrice();
	}

	public Pricelist getPricelist() {
		return pricelist;
	}

	public void setPricelist(Pricelist pricelist) {
		this.pricelist = pricelist;
	}

	public TicketType getTicketType() {
		return ticketType;
	}

	public void setTicketType(TicketType ticketType) {
		this.ticketType = ticketType;
	}

	public TransportType getTransportType() {
		return transportType;
	}

	public void setTransportType(TransportType transportType) {
		this.transportType = transportType;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}