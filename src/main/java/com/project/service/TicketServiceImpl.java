package com.project.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.domain.Passenger;
import com.project.domain.Pricelist;
import com.project.domain.PricelistItem;
import com.project.domain.Ticket;
import com.project.domain.TicketType;
import com.project.domain.TransportType;
import com.project.domain.User;
import com.project.exceptions.EntityDoesNotExistException;
import com.project.repository.PricelistItemRepository;
import com.project.repository.PricelistRepository;
import com.project.repository.TicketRepository;
import com.project.repository.UserRepository;
import com.project.web.dto.TicketDTO;

@Service
public class TicketServiceImpl {

	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private PricelistRepository pricelistRepository;
	
	@Autowired
	private PricelistItemRepository pricelistItemRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public boolean create(TicketDTO ticketDTO) throws EntityDoesNotExistException{
		
		Passenger u = (Passenger) userRepository.findByUsername(ticketDTO.getUser());
		if (u == null) throw new EntityDoesNotExistException("User does not exist.");	
		TicketType ticketT = TicketType.valueOf(ticketDTO.getTicketType());
		TransportType transportT = TransportType.valueOf(ticketDTO.getTransportType());
//		System.out.println("Tip je " + tt);
		Pricelist pl = pricelistRepository.findTopByOrderByIdDesc();
		if (pl == null) throw new EntityDoesNotExistException("Pricelist does not exist.");
//		System.out.println("Pricelist je " + pl);
		PricelistItem pli = pricelistItemRepository.findByPricelistAndTicketType(pl, ticketT);
		if (pli == null) throw new EntityDoesNotExistException("Price list item does not exist.");
//		System.out.println("Pricelist item je " + pli);
		Ticket ticket = new Ticket(u, ticketT, transportT, pli);
		
		ticketRepository.save(ticket);
		return true;
		
	}
	
	public ArrayList<Ticket> getUserTickets(String username) throws EntityDoesNotExistException {
		Passenger u = (Passenger) userRepository.findByUsername(username);
		ArrayList<Ticket> tickets = ticketRepository.findByUser(u);
		return tickets;
	}
}
