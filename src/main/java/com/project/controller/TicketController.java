package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.service.TicketServiceImpl;
import com.project.web.dto.TicketDTO;

@RestController
@RequestMapping(value = "/ticket")
public class TicketController {

	@Autowired
	private TicketServiceImpl ticketService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<String> createTicket(@RequestBody TicketDTO ticketDTO) {
        try {
        	
            ticketService.create(ticketDTO);
            
            return new ResponseEntity<String>(
            		"Ticket successfully created.", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<String>("Invalid login", HttpStatus.BAD_REQUEST);
        }
	}
}
