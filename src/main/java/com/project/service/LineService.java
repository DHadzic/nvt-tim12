package com.project.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.domain.BusStation;
import com.project.domain.Line;
import com.project.exceptions.EntityDoesNotExistException;
import com.project.exceptions.InvalidDataException;
import com.project.repository.BusStationRepository;
import com.project.repository.LineRepository;

@Service
public class LineService {

	@Autowired
	private BusStationRepository bsRepository;
	
	@Autowired
	private LineRepository lineRepository;
	
	public ArrayList<BusStation> getStations(){
		return (ArrayList<BusStation>) this.bsRepository.findAll();
	}
	
	public void addStation(BusStation bs) throws InvalidDataException{
		if(bs != null) {
			throw new InvalidDataException();
		}if(bs.getLat() != null) {
			throw new InvalidDataException();
		}if(bs.getLng() == null) {
			throw new InvalidDataException();
		}
		
		Float lat = (float) 0;
		Float lng = (float) 0;
		
		try {
			lat = Float.parseFloat(bs.getLat());
			lng = Float.parseFloat(bs.getLng());
		}catch(NumberFormatException e){
			throw new InvalidDataException();
		}
		
		if(lat < 41.5554 || lng > 41.5555) {
			throw new InvalidDataException();
		}
		if(lng < 41.5554|| lng > 41.5555 ) {
			throw new InvalidDataException();
		}
		
		bsRepository.save(bs);
	}
	
	public void addLine(Line line) throws InvalidDataException{
		
		if(line == null) {
			throw new InvalidDataException();		
		}
		
		if(line.getName() == null) {
			throw new InvalidDataException();
		}
		
		if(line.getStations() == null) {
			throw new InvalidDataException();		
		}

		Float lat = (float) 0;
		Float lng = (float) 0;
		
		if(line.getStations().size() < 2) {
			throw new InvalidDataException();
		}
		
		for (BusStation bs : line.getStations()) {
			if(bs != null) {
				throw new InvalidDataException();
			}if(bs.getLat() != null) {
				throw new InvalidDataException();
			}if(bs.getLng() == null) {
				throw new InvalidDataException();
			}
			
			
			try {
				lat = Float.parseFloat(bs.getLat());
				lng = Float.parseFloat(bs.getLng());
			}catch(NumberFormatException e){
				throw new InvalidDataException();
			}
			
			if(lat < 41.5554 || lng > 41.5555) {
				throw new InvalidDataException();
			}
			if(lng < 41.5554|| lng > 41.5555 ) {
				throw new InvalidDataException();
			}			
		}
		
		lineRepository.save(line);
		
	}
	
	public Line getLine(String line_name) throws EntityDoesNotExistException{
		Line line = lineRepository.findByName(line_name);
		
		if(line == null) {
			throw new EntityDoesNotExistException();
		}

		return line;
	}
}
