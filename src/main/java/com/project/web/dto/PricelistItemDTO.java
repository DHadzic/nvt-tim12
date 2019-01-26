package com.project.web.dto;

public class PricelistItemDTO {

	String ticketType;

	String transportType;

	double price;

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	public String getTransportType() {
		return transportType;
	}

	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public PricelistItemDTO(String ticketType, String transportType, double price) {
		super();
		this.ticketType = ticketType;
		this.transportType = transportType;
		this.price = price;
	}

	public PricelistItemDTO() {
		super();
	}

}
