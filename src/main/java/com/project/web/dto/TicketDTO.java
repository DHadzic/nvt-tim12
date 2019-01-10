package com.project.web.dto;

public class TicketDTO {

	String user;
	String ticketType;
	String transportType;
	
	public TicketDTO(String user, String ticketType, String transportType) {
		super();
		this.user = user;
		this.ticketType = ticketType;
		this.transportType = transportType;
	}

	public TicketDTO() {
		super();
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

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
	
}
