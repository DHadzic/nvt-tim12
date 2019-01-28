package com.project.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
	@ElementCollection(targetClass=String.class)
	private List<String> workDay;
	
	@Column
	@ElementCollection(targetClass=String.class)
	private List<String> saturday;
	
	@Column
	@ElementCollection(targetClass=String.class)
	private List<String> sunday;
	
	

	public Schedule() {
		
	}
	public Schedule(ScheduleDTO schedule) {
		this.workDay = schedule.getWorkDay();
		this.sunday = schedule.getSunday();
		this.saturday = schedule.getSaturday();
	}
	
	public List<String> getWorkDay() {
		return workDay;
	}



	public void setWorkDay(List<String> workDay) {
		this.workDay = workDay;
	}



	public List<String> getSaturday() {
		return saturday;
	}



	public void setSaturday(List<String> saturday) {
		this.saturday = saturday;
	}



	public List<String>  getSunday() {
		return sunday;
	}



	public void setSunday(List<String> sunday) {
		this.sunday = sunday;
	}

}
