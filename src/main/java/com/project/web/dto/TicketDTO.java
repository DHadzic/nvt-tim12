package com.project.web.dto;

public class TicketDTO {

	String user;
	String type;

	public TicketDTO(String user, String type) {
		super();
		this.user = user;
		this.type = type;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
