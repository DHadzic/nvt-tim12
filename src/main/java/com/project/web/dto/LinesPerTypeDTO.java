package com.project.web.dto;

import java.util.ArrayList;

import com.project.domain.Line;

public class LinesPerTypeDTO {
	
	private ArrayList<String> tramLines;
	private ArrayList<String> busLines;
	private ArrayList<String> trolleybusLines;
	
	/*
	private ArrayList<Integer> tramAtStations;
	private ArrayList<Integer> busAtStations;
	private ArrayList<Integer> trolleybusAtStations;
	*/
	public LinesPerTypeDTO() {
		this.tramLines = new ArrayList<String>();
		this.busLines = new ArrayList<String>();
		this.trolleybusLines = new ArrayList<String>();
	}
	
	public ArrayList<String> getTramLines() {
		return tramLines;
	}
	public void setTramLines(ArrayList<String> tramLines) {
		this.tramLines = tramLines;
	}
	public ArrayList<String> getBusLines() {
		return busLines;
	}
	public void setBusLines(ArrayList<String> busLines) {
		this.busLines = busLines;
	}
	public ArrayList<String> getTrolleybusLines() {
		return trolleybusLines;
	}
	public void setTrolleybusLines(ArrayList<String> trolleybusLines) {
		this.trolleybusLines = trolleybusLines;
	}

	public void tramAddLine(String line_name) {
		for (String l_name : this.tramLines) {
			if(l_name.equals(line_name)) {
				return;
			}
		}
		this.tramLines.add(line_name);
		return;
	}

	public void busAddLine(String line_name) {
		for (String l_name : this.busLines) {
			if(l_name.equals(line_name)) {
				return;
			}
		}
		this.busLines.add(line_name);
		return;
	}

	public void trolleybusAddLine(String line_name) {
		for (String l_name : this.trolleybusLines) {
			if(l_name.equals(line_name)) {
				return;
			}
		}
		this.trolleybusLines.add(line_name);
		return;
	}
}
