package com.project.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.Line;
import com.project.domain.Schedule;
import com.project.domain.Ticket;
import com.project.exceptions.EntityDoesNotExistException;
import com.project.repository.ScheduleRepository;
import com.project.repository.VehicleRepository;
import com.project.service.AddVehicleServiceImpl;
import com.project.web.dto.AddVehicleDTO;
import com.project.web.dto.TicketDTO;

@RestController
@RequestMapping(value = "/addVehicle")
public class AddVehicleController {
	
	@Autowired
	private AddVehicleServiceImpl addVehicleService;
	
	@PreAuthorize("permitAll()")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<String> createTicket(@RequestBody AddVehicleDTO ticketDTO) {
        try {
        	
            addVehicleService.create(ticketDTO);
            
            return new ResponseEntity<String>(
            		"Ticket successfully created.", HttpStatus.OK);
        } catch (EntityDoesNotExistException edne) {
            return new ResponseEntity<String>("Ticket not created. "+edne.getMessage(), HttpStatus.BAD_REQUEST);
        }
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
}
