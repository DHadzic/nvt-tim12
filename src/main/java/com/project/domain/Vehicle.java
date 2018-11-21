package com.project.domain;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private TransportType type;
	
	@Column
	private ArrayList<Ticket> tickets;
	
	@Column
	private ArrayList<BusStation> stations;
	
	@Column
	private Schedule schedule;
}
