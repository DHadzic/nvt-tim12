package com.project.service;

import java.util.ArrayList;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.domain.Line;
import com.project.domain.Schedule;
import com.project.domain.Ticket;
import com.project.domain.TransportType;
import com.project.domain.Vehicle;
import com.project.exceptions.EntityDoesNotExistException;
import com.project.exceptions.InvalidDataException;
import com.project.repository.LineRepository;
import com.project.repository.ScheduleRepository;
import com.project.repository.VehicleRepository;
import com.project.web.dto.AddVehicleDTO;
import com.project.web.dto.ResponseDTO;
import com.project.web.dto.VehicleNameDTO;

@Service
public class AddVehicleServiceImpl {
	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	@Autowired
	private LineRepository lineRepository;
	
	public boolean create(AddVehicleDTO addVehicleDTO) throws EntityDoesNotExistException, InvalidDataException{
		
		Line l = lineRepository.findByName(addVehicleDTO.getLine().getName());
		String name = addVehicleDTO.getName();
		if(vehicleRepository.findByName(addVehicleDTO.getName()) != null) {
			throw new InvalidDataException("Line name taken");
		}
		TransportType tt = TransportType.valueOf(addVehicleDTO.getType());
		if(tt == null) throw new EntityNotFoundException("Type not exists");
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		Schedule schedule = new Schedule(addVehicleDTO.getSchedule());
		Vehicle v = new Vehicle(tt,tickets,l,schedule,name);
		scheduleRepository.save(schedule);
		vehicleRepository.save(v);
		return true;
	}
	
	
	public ArrayList<Line> getLines() throws EntityDoesNotExistException {
		ArrayList<Line> lines = new ArrayList<Line>();
		lines = (ArrayList<Line>) lineRepository.findAll();
		if (lines == null) throw new EntityDoesNotExistException("User does not exist.");
		System.out.println(lines.size()  + "OVO JE");
		return lines;
	}
	
	public ArrayList<Vehicle> getVehiclesWithoutLines(){
		return (ArrayList<Vehicle>) vehicleRepository.findByLineIsNull();
	}

	public ArrayList<Vehicle> getVehiclesWithLines(){
		return (ArrayList<Vehicle>) vehicleRepository.findByLineNotNull();
	}


	public ArrayList<Line> getAvailableLines() {
		ArrayList<Line> lines = new ArrayList<Line>();
		lines = (ArrayList<Line>) lineRepository.findAll();
		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
		vehicles = getVehiclesWithLines();
		System.out.println(vehicles.size());
		ArrayList<Line> avalaibleLines = new ArrayList<Line>();
		ArrayList<Line> takenLines = new ArrayList<Line>();
		for(Vehicle v:vehicles) {
			for(Line l:lines) {
				if(v.getLine().getName().equalsIgnoreCase(l.getName())) {
					System.out.println("usao");
					takenLines.add(l);
				}
			}
		}
		
		for(Line l1:lines) {
			if(!(takenLines.contains(l1))) {
				avalaibleLines.add(l1);
			}
		}
		return avalaibleLines;
	}


	public void assignLine(ResponseDTO responseDTO) throws EntityDoesNotExistException {
		System.out.println("stigao");
		System.out.println(responseDTO.getVehicle());
		Vehicle v = vehicleRepository.findByName(responseDTO.getVehicle());
		if(v == null) throw new EntityDoesNotExistException();
		Line l = lineRepository.findByName(responseDTO.getLine());
		v.setLine(l);
		vehicleRepository.save(v);
		
	}


	public Vehicle getVehicleByName(String vehicleDTO) {
		String cut = vehicleDTO.substring(1, vehicleDTO.length()-1);
		Vehicle v = vehicleRepository.findByName(cut);
		System.out.println(v);
		return v;
	}


	public void update(AddVehicleDTO vehicleDTO) throws EntityDoesNotExistException, InvalidDataException {
		vehicleRepository.deleteByName(vehicleDTO.getName());
		System.out.println("uradio");
		create(vehicleDTO);
	}


	



}
