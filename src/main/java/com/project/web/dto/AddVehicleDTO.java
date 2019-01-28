package com.project.web.dto;

public class AddVehicleDTO {
	
	String type;
	ScheduleDTO schedule;
	LineDTO line;
	String name;
	
	AddVehicleDTO(){
		
	}
	
	
	public AddVehicleDTO(String type, ScheduleDTO schedule,LineDTO line) {
		super();
		this.type = type;
		this.schedule = schedule;
		this.line = line;
	}
	
	public AddVehicleDTO(String type, ScheduleDTO schedule,LineDTO line,String name) {
		super();
		this.type = type;
		this.schedule = schedule;
		this.line = line;
		this.name = name;
	}

	public LineDTO getLine() {
		return line;
	}


	public void setLine(LineDTO line) {
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


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	

}
