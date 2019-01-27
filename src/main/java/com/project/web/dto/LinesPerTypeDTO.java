package com.project.web.dto;

import java.util.ArrayList;

import com.project.domain.Line;

public class LinesPerTypeDTO {
	
	private ArrayList<Line> tramLines;
	private ArrayList<Line> busLines;
	private ArrayList<Line> trolleybusLines;
	
	private ArrayList<Integer> tramAtStations;
	private ArrayList<Integer> busAtStations;
	private ArrayList<Integer> trolleybusAtStations;
	
	public LinesPerTypeDTO() {
		this.tramLines = new ArrayList<Line>();
		this.busLines = new ArrayList<Line>();
		this.trolleybusLines = new ArrayList<Line>();
		this.tramAtStations = new ArrayList<Integer>();
		this.busAtStations = new ArrayList<Integer>();
		this.trolleybusAtStations = new ArrayList<Integer>();
	}
	
	public ArrayList<Line> getTramLines() {
		return tramLines;
	}
	public void setTramLines(ArrayList<Line> tramLines) {
		this.tramLines = tramLines;
	}
	public ArrayList<Line> getBusLines() {
		return busLines;
	}
	public void setBusLines(ArrayList<Line> busLines) {
		this.busLines = busLines;
	}
	public ArrayList<Line> getTrolleybusLines() {
		return trolleybusLines;
	}
	public void setTrolleybusLines(ArrayList<Line> trolleybusLines) {
		this.trolleybusLines = trolleybusLines;
	}
	public ArrayList<Integer> getTramAtStations() {
		return tramAtStations;
	}
	public void setTramAtStations(ArrayList<Integer> tramAtStations) {
		this.tramAtStations = tramAtStations;
	}
	public ArrayList<Integer> getBusAtStations() {
		return busAtStations;
	}
	public void setBusAtStations(ArrayList<Integer> busAtStations) {
		this.busAtStations = busAtStations;
	}
	public ArrayList<Integer> getTrolleybusAtStations() {
		return trolleybusAtStations;
	}
	public void setTrolleybusAtStations(ArrayList<Integer> trolleybusAtStations) {
		this.trolleybusAtStations = trolleybusAtStations;
	}
	
	public void tramAddLine(Line line) {
		for (Line l : this.tramLines) {
			if(l.getName().equals(line.getName())) {
				return;
			}
		}
		this.tramLines.add(line);
		return;
	}

	public void busAddLine(Line line) {
		for (Line l : this.busLines) {
			if(l.getName().equals(line.getName())) {
				return;
			}
		}
		this.busLines.add(line);
		return;
	}

	public void trolleybusAddLine(Line line) {
		for (Line l : this.trolleybusLines) {
			if(l.getName().equals(line.getName())) {
				return;
			}
		}
		this.trolleybusLines.add(line);
		return;
	}

	public void tramAddStationAt(Integer stationAt) {
		for (Integer statAt : this.tramAtStations) {
			if(statAt.equals(stationAt)) {
				return;
			}
		}
		this.tramAtStations.add(stationAt);
		return;
	}

	public void busAddStationAt(Integer stationAt) {
		for (Integer statAt : this.busAtStations) {
			if(statAt.equals(stationAt)) {
				return;
			}
		}
		this.busAtStations.add(stationAt);
		return;
	}

	public void trolleybusAddStationAt(Integer stationAt) {
		for (Integer statAt : this.trolleybusAtStations) {
			if(statAt.equals(stationAt)) {
				return;
			}
		}
		this.trolleybusAtStations.add(stationAt);
		return;
	}

}
