package com.project.util;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.project.domain.Vehicle;
import com.project.repository.VehicleRepository;

@Component
public class TravelScheduler {

	@Autowired
	VehicleRepository vehicleRepository;
	
	@Scheduled(fixedRate = 12000)
	public void moveVehiclesFirst() {
		ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) vehicleRepository.findByLineNotNull();
		int line_size = 0;
		for(int i=0; i < vehicles.size(); i=i+3) {
			if(vehicles.get(i).getStartToEnd()) {
				line_size = vehicles.get(i).getLine().getStations().size() - 1;
				if(line_size == vehicles.get(i).getAtStation()) {
					vehicles.get(i).setStartToEnd(false);
					vehicles.get(i).setAtStation(vehicles.get(i).getAtStation() - 1);
				}else {
					vehicles.get(i).setAtStation(vehicles.get(i).getAtStation() + 1);
				}
			}else {
				if(0 == vehicles.get(i).getAtStation()) {
					vehicles.get(i).setStartToEnd(true);
					vehicles.get(i).setAtStation(vehicles.get(i).getAtStation() + 1);
				}else {
					vehicles.get(i).setAtStation(vehicles.get(i).getAtStation() - 1);
				}
			}
			
			vehicleRepository.save(vehicles.get(0));
		}
	}

	@Scheduled(fixedRate = 90000)
	public void moveVehiclesSecond() {
		ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) vehicleRepository.findByLineNotNull();
		int line_size = 0;
		for(int i=1; i < vehicles.size(); i=i+3) {
			if(vehicles.get(i).getStartToEnd()) {
				line_size = vehicles.get(i).getLine().getStations().size() - 1;
				if(line_size == vehicles.get(i).getAtStation()) {
					vehicles.get(i).setStartToEnd(false);
					vehicles.get(i).setAtStation(vehicles.get(i).getAtStation() - 1);
				}else {
					vehicles.get(i).setAtStation(vehicles.get(i).getAtStation() + 1);
				}
			}else {
				if(0 == vehicles.get(i).getAtStation()) {
					vehicles.get(i).setStartToEnd(true);
					vehicles.get(i).setAtStation(vehicles.get(i).getAtStation() + 1);
				}else {
					vehicles.get(i).setAtStation(vehicles.get(i).getAtStation() - 1);
				}
			}
			
			vehicleRepository.save(vehicles.get(0));
		}
	}

	@Scheduled(fixedRate = 100000)
	public void moveVehiclesThird() {
		ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) vehicleRepository.findByLineNotNull();
		int line_size = 0;
		for(int i=2; i < vehicles.size(); i=i+3) {
			if(vehicles.get(i).getStartToEnd()) {
				line_size = vehicles.get(i).getLine().getStations().size() - 1;
				if(line_size == vehicles.get(i).getAtStation()) {
					vehicles.get(i).setStartToEnd(false);
					vehicles.get(i).setAtStation(vehicles.get(i).getAtStation() - 1);
				}else {
					vehicles.get(i).setAtStation(vehicles.get(i).getAtStation() + 1);
				}
			}else {
				if(0 == vehicles.get(i).getAtStation()) {
					vehicles.get(i).setStartToEnd(true);
					vehicles.get(i).setAtStation(vehicles.get(i).getAtStation() + 1);
				}else {
					vehicles.get(i).setAtStation(vehicles.get(i).getAtStation() - 1);
				}
			}
			
			vehicleRepository.save(vehicles.get(0));
		}
	}
}
