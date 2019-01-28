package com.project.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.Line;
import com.project.domain.Schedule;
import com.project.domain.Ticket;
import com.project.domain.Vehicle;
import com.project.exceptions.EntityDoesNotExistException;
import com.project.exceptions.InvalidDataException;
import com.project.repository.ScheduleRepository;
import com.project.repository.VehicleRepository;
import com.project.service.AddVehicleServiceImpl;
import com.project.web.dto.AddVehicleDTO;
import com.project.web.dto.ResponseDTO;
import com.project.web.dto.TicketDTO;
import com.project.web.dto.VehicleNameDTO;

@RestController
@RequestMapping(value = "/addVehicle")
public class AddVehicleController {
	
	@Autowired
	private AddVehicleServiceImpl addVehicleService;
	
	@PreAuthorize("permitAll()")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<String> createTicket(@RequestBody AddVehicleDTO ticketDTO) {
        try {
        	System.out.println(ticketDTO);
            addVehicleService.create(ticketDTO);
            
            return new ResponseEntity<String>(
            		"Vehicle successfully created.", HttpStatus.OK);
        } catch (InvalidDataException | EntityDoesNotExistException edne) {
        	System.out.println(ticketDTO.getName());
            return new ResponseEntity<String>("Ticket not created. "+edne.getMessage(), HttpStatus.BAD_REQUEST);
        }
	}
	
	
	@PreAuthorize("permitAll()")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<String> update(@RequestBody AddVehicleDTO ticketDTO) throws EntityDoesNotExistException, InvalidDataException {
		addVehicleService.update(ticketDTO);
        return new ResponseEntity<String>(
				"Vehicle successfully created.", HttpStatus.OK);
	}
	
	
	@PreAuthorize("permitAll()")
	@RequestMapping(value = "/getLines", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Line>> getLines() {
		try{
			ArrayList<Line> lines = addVehicleService.getLines();
			System.out.println(lines);
			return new ResponseEntity<ArrayList<Line>>(lines, HttpStatus.OK);
			
		} catch (EntityDoesNotExistException edne) {
			return new ResponseEntity<ArrayList<Line>>(new ArrayList<Line>(), HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("permitAll()")
	@RequestMapping(value = "/getVehiclesWithLine", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Vehicle>> getVehiclesWithLine() {
		return new ResponseEntity<ArrayList<Vehicle>>(addVehicleService.getVehiclesWithLines(), HttpStatus.OK);
	}

	@PreAuthorize("permitAll()")
	@RequestMapping(value = "/getVehiclesWithoutLine", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Vehicle>> getVehiclesWithoutLine() {
		return new ResponseEntity<ArrayList<Vehicle>>(addVehicleService.getVehiclesWithoutLines(), HttpStatus.OK);
	}
	
	@PreAuthorize("permitAll()")
	@RequestMapping(value = "/getAvailableLines", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Line>> getAvailableLines() {
		return new ResponseEntity<ArrayList<Line>>(addVehicleService.getAvailableLines(), HttpStatus.OK);
	}
	
	@PreAuthorize("permitAll()")
	@RequestMapping(value = "/getVehicleByName", method = RequestMethod.POST)
	public ResponseEntity<Vehicle> getVehicleByName(@RequestBody String vehicle) {
		return new ResponseEntity<Vehicle>(addVehicleService.getVehicleByName(vehicle), HttpStatus.OK);
	}
	
	@PreAuthorize("permitAll()")
	@RequestMapping(value = "/assign", method = RequestMethod.POST)
	public ResponseEntity<String> assignLine(@RequestBody ResponseDTO responseDTO) {
        try {
        	System.out.println("dosao");
        	
            addVehicleService.assignLine(responseDTO);
            
            return new ResponseEntity<String>(
            		"Line successfully assigned.", HttpStatus.OK);
        } catch (EntityDoesNotExistException edne) {
            return new ResponseEntity<String>("Line not assigned. "+edne.getMessage(), HttpStatus.BAD_REQUEST);
        }
	}
	
	@RequestMapping(value = "/getLinesPerType", method = RequestMethod.GET)
	public ResponseEntity<LinesPerTypeDTO> getLinesPerType() {
		return new ResponseEntity<LinesPerTypeDTO>(addVehicleService.getLinesPerType(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getLineInfo/{type}/{line_name}", method = RequestMethod.GET)
	public ResponseEntity<LineInfo> getLineInfo(@PathVariable TransportType type,@PathVariable String line_name) {
		try {
			return new ResponseEntity<LineInfo>(addVehicleService.getLineInfo(type, line_name), HttpStatus.OK);
		} catch (EntityDoesNotExistException e) {
			return new ResponseEntity<LineInfo>(new LineInfo(), HttpStatus.BAD_REQUEST);
		}
	}
	
}
