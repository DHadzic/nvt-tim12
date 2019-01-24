package com.project.web.dto;

public class AddVehicleDTO {
	String type;
	ScheduleDTO schedule;
	
	AddVehicleDTO(){
		
	}
	
	
	public AddVehicleDTO(String type, ScheduleDTO schedule) {
		super();
		this.type = type;
		this.schedule = schedule;
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
