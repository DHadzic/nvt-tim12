package com.project.domain;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Line {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	//treba dodati da je jedinstveno
	@Column
	private String name;
	
	@Column
	private ArrayList<BusStation> stations;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<BusStation> getStations() {
		return stations;
	}

	public void setStations(ArrayList<BusStation> stations) {
		this.stations = stations;
	}

	
}
