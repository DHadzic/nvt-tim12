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
import com.project.repository.LineRepository;
import com.project.repository.ScheduleRepository;
import com.project.repository.VehicleRepository;
import com.project.web.dto.AddVehicleDTO;
import com.project.web.dto.LineInfo;
import com.project.web.dto.LinesPerTypeDTO;

@Service
public class AddVehicleServiceImpl {
	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	@Autowired
	private LineRepository lineRepository;
	
	public boolean create(AddVehicleDTO addVehicleDTO) throws EntityDoesNotExistException{
		
		Line l = lineRepository.findByName(addVehicleDTO.getLine());
		if(l == null) throw new EntityNotFoundException("Line not exists");
		TransportType tt = TransportType.valueOf(addVehicleDTO.getType());
		if(tt == null) throw new EntityNotFoundException("Type not exists");
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		Schedule schedule = new Schedule(addVehicleDTO.getSchedule());
		Vehicle v = new Vehicle(tt,tickets,l,schedule);
		System.out.println(v.getLine().getName());
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
	
	public LinesPerTypeDTO getLinesPerType() {
		LinesPerTypeDTO linesPT = new LinesPerTypeDTO();
		ArrayList<Vehicle> found_vehicles;
		found_vehicles = (ArrayList<Vehicle>) vehicleRepository.findByType(TransportType.BUS);
		for (Vehicle vehicle : found_vehicles) {
			if(vehicle.getLine() != null) {
				linesPT.busAddLine(vehicle.getLine().getName());
			}
		}
		found_vehicles = (ArrayList<Vehicle>) vehicleRepository.findByType(TransportType.TRAM);
		for (Vehicle vehicle : found_vehicles) {
			if(vehicle.getLine() != null) {
				linesPT.tramAddLine(vehicle.getLine().getName());
			}
		}
		found_vehicles = (ArrayList<Vehicle>) vehicleRepository.findByType(TransportType.TROLLEYBUS);
		for (Vehicle vehicle : found_vehicles) {
			if(vehicle.getLine() != null) {
				linesPT.trolleybusAddLine(vehicle.getLine().getName());
			}
		}
		return linesPT;
	}
	
	public LineInfo getLineInfo(TransportType type,String line_name) throws EntityDoesNotExistException {
		LineInfo line_info = new LineInfo();

		Line line = lineRepository.findByName(line_name);

		if(line == null) {
			throw new EntityDoesNotExistException();
		}

		line_info.setLine(line);
		ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) vehicleRepository.findByType(type);
		
		for (Vehicle vehicle : vehicles) {
			if(vehicle.getLine() == null) {
				continue;
			}
			
			if(vehicle.getLine().getName() == null) {
				continue;
			}
			
			if(!vehicle.getLine().getName().equals(line_name)) {
				continue;
			}
			
			line_info.addStationAt(vehicle.getAtStation());			
		}
		
		return line_info;
	}
	
}
