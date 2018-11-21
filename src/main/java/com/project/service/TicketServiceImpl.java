package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.domain.Ticket;
import com.project.repository.TicketRepository;
import com.project.web.dto.TicketDTO;

@Service
public class TicketServiceImpl {

	@Autowired
	private TicketRepository ticketRepository;
	
	public boolean create(TicketDTO ticketDTO){
		
		Ticket ticket = new Ticket(ticketDTO);
		ticketRepository.save(ticket);
		return true;
		
	}
}
