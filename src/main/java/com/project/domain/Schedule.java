package com.project.domain;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.project.web.dto.ScheduleDTO;

@Entity
public class Schedule {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;


	@Column
	private ArrayList<String> workDay;
	
	@Column
	private ArrayList<String> saturday;
	
	@Column
	private ArrayList<String> sunday;
	
	

	public Schedule(ScheduleDTO schedule) {
		this.workDay = schedule.getWorkDay();
		this.sunday = schedule.getSunday();
		this.saturday = schedule.getSaturday();
	}
	
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
