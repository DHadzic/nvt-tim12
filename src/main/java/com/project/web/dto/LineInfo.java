package com.project.web.dto;

import java.util.ArrayList;

import com.project.domain.Line;

public class LineInfo {
	
	private Line line;
	private ArrayList<Integer> atStations;
	
	public LineInfo() {
		this.atStations = new ArrayList<Integer>();
	}
	
	public Line getLine() {
		return line;
	}
	public void setLine(Line line) {
		this.line = line;
	}
	public ArrayList<Integer> getAtStations() {
		return atStations;
	}
	public void setAtStations(ArrayList<Integer> atStations) {
		this.atStations = atStations;
	}
	
	public void addStationAt(Integer stationAt) {
		for (Integer statAt : this.atStations) {
			if(statAt.equals(stationAt)) {
				return;
			}
		}
		this.atStations.add(stationAt);
		return;
	}


}
