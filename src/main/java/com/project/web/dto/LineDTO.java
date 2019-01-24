package com.project.web.dto;

import java.util.ArrayList;

import javax.persistence.Column;

import com.project.domain.BusStation;

public class LineDTO {

	private String name;
	
	private ArrayList<BusStation> stations;

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
