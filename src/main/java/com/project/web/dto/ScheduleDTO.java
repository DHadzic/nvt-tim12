package com.project.web.dto;

import java.util.ArrayList;

public class ScheduleDTO {
	ArrayList<String> workDay;
	ArrayList<String> saturday;
	ArrayList<String> sunday;
	
	public ArrayList<String> getWorkDay() {
		return workDay;
	}
	public void setWorkDay(ArrayList<String> workDay) {
		this.workDay = workDay;
	}
	public ArrayList<String> getSaturday() {
		return saturday;
	}
	public void setSaturday(ArrayList<String> saturday) {
		this.saturday = saturday;
	}
	public ArrayList<String> getSunday() {
		return sunday;
	}
	public void setSunday(ArrayList<String> sunday) {
		this.sunday = sunday;
	}

	
}
