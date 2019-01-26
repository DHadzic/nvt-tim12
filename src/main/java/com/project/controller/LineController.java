package com.project.controller;

import java.util.ArrayList;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.BusStation;
import com.project.domain.Line;
import com.project.exceptions.EntityAlreadyExistsException;
import com.project.exceptions.EntityDoesNotExistException;
import com.project.exceptions.InvalidDataException;
import com.project.service.LineService;
import com.project.web.dto.LineDTO;

@RestController
@RequestMapping(value = "/line")
public class LineController {
	
	@Autowired
	private LineService lineService;
	
	@RequestMapping(value = "/get_stations", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<BusStation>> getStations(){
		return new ResponseEntity<ArrayList<BusStation>>( this.lineService.getStations() ,HttpStatus.OK);
	}

	@RequestMapping(value = "/get_lines", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Line>> getLines(){
		return new ResponseEntity<ArrayList<Line>>( this.lineService.getLines() ,HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN_ROLE')")
	@RequestMapping(value = "/add_station", method = RequestMethod.PUT)
	public ResponseEntity<String> addStation(@RequestBody BusStation station){
		try {
			this.lineService.addStation(station);
			return new ResponseEntity<String>("Station added.",HttpStatus.OK);
		}catch(InvalidDataException | EntityAlreadyExistsException e) {
			return new ResponseEntity<String>("Invalid adding of station." + e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasAuthority('ADMIN_ROLE')")
	@RequestMapping(value = "/delete_station/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteStation(@PathVariable Long id){
		try {
			this.lineService.deleteBusStation(id);
			return new ResponseEntity<String>("Station added.",HttpStatus.OK);
		}catch(EntityDoesNotExistException e) {
			return new ResponseEntity<String>("Station doesn't exist." + e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasAuthority('ADMIN_ROLE')")
	@RequestMapping(value = "/add_line", method = RequestMethod.PUT)
	public ResponseEntity<String> addLine(@RequestBody LineDTO line){
		try {
			this.lineService.addLine(line);
			return new ResponseEntity<String>("Line added.",HttpStatus.OK);
		}catch(InvalidDataException | EntityDoesNotExistException e) {
			return new ResponseEntity<String>("Invalid adding of line."+e.getMessage(),HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	// proveriti PathParam("id") <--
	@RequestMapping(value = "/get_line/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> getLine(@PathParam("id") String line_name){
		try {
			this.lineService.getLine(line_name);
			return new ResponseEntity<String>("Line added.",HttpStatus.OK);
		}catch(EntityDoesNotExistException e) {
			return new ResponseEntity<String>("Invalid adding of line.",HttpStatus.BAD_REQUEST);
		}
	}
}
