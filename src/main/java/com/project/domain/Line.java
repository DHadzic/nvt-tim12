package com.project.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Line {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	//treba dodati da je jedinstveno
	@Column
	private String name;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "line_station_relation",
    joinColumns = { @JoinColumn(name = "line_id") },
    inverseJoinColumns = { @JoinColumn(name = "station_id") })
	private List<BusStation> stations;

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

	public List<BusStation> getStations() {
		return stations;
	}

	public void setStations(ArrayList<BusStation> stations) {
		this.stations = stations;
	}

	
}
