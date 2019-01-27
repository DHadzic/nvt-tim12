package com.project.domain;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private TransportType type;
	


	@Column
	private ArrayList<Ticket> tickets;
	
	@ManyToOne
	private Line line;

	@OneToOne
	private Schedule schedule;
	
	public Vehicle(TransportType type, ArrayList<Ticket> tickets, Line line, Schedule schedule) {
		super();
		this.type = type;
		this.tickets = tickets;
		this.line = line;
		this.schedule = schedule;
	}

	public Vehicle(TransportType type, ArrayList<Ticket> tickets,  Schedule schedule) {
		// TODO Auto-generated constructor stub
		super();
		this.type = type;
		this.tickets = tickets;
		this.schedule = schedule;
	}

	public TransportType getType() {
		return type;
	}

	public void setType(TransportType type) {
		this.type = type;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	
	public ArrayList<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(ArrayList<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}
}