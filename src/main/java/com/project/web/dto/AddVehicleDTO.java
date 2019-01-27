package com.project.web.dto;

public class AddVehicleDTO {
	String type;
	ScheduleDTO schedule;
	String line;
	
	AddVehicleDTO(){
		
	}
	
	
	public AddVehicleDTO(String type, ScheduleDTO schedule,String line) {
		super();
		this.type = type;
		this.schedule = schedule;
		this.line = line;
	}

	public String getLine() {
		return line;
	}


	public void setLine(String line) {
		this.line = line;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ScheduleDTO getSchedule() {
		return schedule;
	}
	public void setSchedule(ScheduleDTO schedule) {
		this.schedule = schedule;
	}
	

}
