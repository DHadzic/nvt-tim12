package com.project.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.domain.BusStation;
import com.project.domain.Line;
import com.project.domain.Vehicle;
import com.project.exceptions.EntityAlreadyExistsException;
import com.project.exceptions.EntityDoesNotExistException;
import com.project.exceptions.InvalidDataException;
import com.project.repository.BusStationRepository;
import com.project.repository.LineRepository;
import com.project.repository.VehicleRepository;
import com.project.web.dto.LineDTO;

@Service
public class LineService {

	@Autowired
	private BusStationRepository bsRepository;
	
	@Autowired
	private LineRepository lineRepository;
	
	@Autowired
	private VehicleRepository vehicleRepository;

	public ArrayList<BusStation> getStations(){
		return (ArrayList<BusStation>) this.bsRepository.findAll();
	}
	
	public void addStation(BusStation bs) throws InvalidDataException, EntityAlreadyExistsException{
		if(bs == null) {
			throw new InvalidDataException("Data is null");
		}if(bs.getLat() == null) {
			throw new InvalidDataException("Lat is null");
		}if(bs.getLng() == null) {
			throw new InvalidDataException("Lng is null");
		}
		
		BusStation foundBs = bsRepository.findByLat(bs.getLat());
		
		if(foundBs != null) {
			if(foundBs.getLng().equals(bs.getLng())) {
				throw new EntityAlreadyExistsException("Station already exists");
			}
		}
		
		Float lat = (float) 0;
		Float lng = (float) 0;
		
		try {
			lat = Float.parseFloat(bs.getLat());
			lng = Float.parseFloat(bs.getLng());
		}catch(NumberFormatException e){
			throw new InvalidDataException("Coords not numbers");
		}
		
		boolean isInside = false;
		
	    if((lat > 45.271652105740415 && lat < 45.301403448327434 && lng > 19.807694198063132 && lng < 19.85146784918618) == true) {
	    	isInside = true;
	    }
	    if((lat > 45.22028630783431 && lat < 45.271652105740415 && lng > 19.785163978393484 && lng < 19.893825630004812) == true) {
	    	isInside = true;
	    }
	    
	    if(!isInside) {
	    	throw new InvalidDataException("Point not in the area");
	    }
		
	    bsRepository.flush();
		bsRepository.save(bs);
	}
	
	public void addLine(LineDTO line) throws InvalidDataException, EntityDoesNotExistException {
		
		if(line == null) {
			throw new InvalidDataException("Data is null");
		}
		
		if(line.getName() == null) {
			throw new InvalidDataException("Name is null");
		}
		
		if(line.getStations() == null) {
			throw new InvalidDataException("Stations are null");
		}
		
		if(lineRepository.findByName(line.getName()) != null) {
			throw new InvalidDataException("Line name taken");
		}

		if(line.getStations().size() < 2) {
			throw new InvalidDataException("Need atleast 2 stations");
		}
		
		BusStation foundBs;
		
		for (BusStation bs : line.getStations()) {
			if(bs == null) {
				throw new InvalidDataException("Station is null");
			}if(bs.getLat() == null) {
				throw new InvalidDataException("Station lat is null");
			}if(bs.getLng() == null) {
				throw new InvalidDataException("Station lng is null");
			}

			foundBs = bsRepository.findByLat(bs.getLat());
			
			if(foundBs != null) {
				if(!foundBs.equals(bs)) {
					throw new EntityDoesNotExistException("Station not in a system");
				}
			}else {
				throw new EntityDoesNotExistException("Station not in a system");
			}
			
		}
		
		for (int i = 0; i < line.getStations().size();i++) {
			for(int j = i+1; j<line.getStations().size();j++) {
				if(line.getStations().get(i).equals(line.getStations().get(j))) {
					throw new InvalidDataException("Unique bus stations required");
				}
			}
		}
		
		Line new_line = new Line();
		
		new_line.setName(line.getName());
		new_line.setStations(new ArrayList<BusStation>());
		
		for (int i = 0;i < line.getStations().size();i++) {
			new_line.getStations().add(i, bsRepository.findByLat(line.getStations().get(i).getLat()));
		}

		lineRepository.save(new_line);
		
	}
	
	public Line getLine(String line_name) throws EntityDoesNotExistException{
		Line line = lineRepository.findByName(line_name);
		
		if(line == null) {
			throw new EntityDoesNotExistException();
		}

		return line;
	}
	
	public ArrayList<Line> getLines(){
		return (ArrayList<Line>) lineRepository.findAll();
	}
	
	public int getLinesSize() {
		return lineRepository.findAll().size();
	}
	
	public void deleteBusStation(Long id) throws EntityDoesNotExistException {
		if(!bsRepository.findById(id).isPresent()) {
			throw new EntityDoesNotExistException();
		}
		
		ArrayList<Line> lines = (ArrayList<Line>) lineRepository.findAll();
		
		int index_to_remove = -1;
		for (Line line : lines) {
			index_to_remove = -1;
			for (BusStation station : line.getStations()) {
				if(station.getId() == id) {
					index_to_remove = line.getStations().indexOf(station);
					break;
				}
			}
			if(index_to_remove != -1l) {
				line.getStations().remove(index_to_remove);
				lineRepository.save(line);
			}
		}
		
		bsRepository.deleteById(id);	
	}

	public void deleteLine(Long id) throws EntityDoesNotExistException {
		if(!lineRepository.findById(id).isPresent()) {
			throw new EntityDoesNotExistException();
		}
		
		ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) vehicleRepository.findByLineNotNull();
		
		for (Vehicle vehicle : vehicles) {
			if(vehicle.getLine().getId() == id) {
				vehicle.setLine(null);
				vehicleRepository.save(vehicle);
			}
		}
		
		lineRepository.deleteById(id);	
	}

}
