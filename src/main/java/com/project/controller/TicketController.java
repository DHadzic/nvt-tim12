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
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.PricelistItem;
import com.project.domain.Ticket;
import com.project.exceptions.EntityDoesNotExistException;
import com.project.exceptions.InvalidDataException;
import com.project.service.TicketService;
import com.project.web.dto.PricelistItemDTO;
import com.project.web.dto.TicketDTO;

@RestController
@RequestMapping(value = "/ticket")
public class TicketController {

	@Autowired
	private TicketService ticketService;

	@PreAuthorize("permitAll()")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<String> createTicket(@RequestBody TicketDTO ticketDTO) {
        try {
        	
            ticketService.create(ticketDTO);
            
            return new ResponseEntity<String>(
            		"Ticket successfully created.", HttpStatus.OK);
        } catch (EntityDoesNotExistException | InvalidDataException edne) {
            return new ResponseEntity<String>("Ticket not created. "+edne.getMessage(), HttpStatus.BAD_REQUEST);
        }
	}
	
	@PreAuthorize("permitAll()")
	@RequestMapping(value = "/getAll/{username}", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Ticket>> getTickets(@PathVariable("username") String username){
		try{
			ArrayList<Ticket> tickets =  ticketService.getUserTickets(username);
			System.out.println(tickets);
			return new ResponseEntity<ArrayList<Ticket>>(tickets, HttpStatus.OK);
			
		} catch (EntityDoesNotExistException | InvalidDataException edne) {
			return new ResponseEntity<ArrayList<Ticket>>(new ArrayList<Ticket>(), HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("permitAll()")
	@RequestMapping(value = "/validate/{username}&{type}", method = RequestMethod.GET)
	public ResponseEntity<Ticket> validateTicket(@PathVariable("username") String username,
												 @PathVariable("type") String transportType){
		try{
			Ticket ticket = ticketService.getUserTicket(username, transportType);
			return new ResponseEntity<Ticket>(ticket, HttpStatus.OK);
		}catch (EntityDoesNotExistException | InvalidDataException edne){
			return new ResponseEntity<Ticket>(new Ticket(), HttpStatus.NOT_FOUND);
		}
	}
}
