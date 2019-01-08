package com.project.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PricelistItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Pricelist pricelist;
	
	@Column
	private TicketType ticketType;
	
	@Column
	private double price;

	public PricelistItem(Pricelist pricelist, TicketType ticketType, double price) {
		super();
		this.pricelist = pricelist;
		this.ticketType = ticketType;
		this.price = price;
	}

	public PricelistItem() {
		super();
	}

}