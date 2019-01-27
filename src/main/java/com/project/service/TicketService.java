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
import com.project.exceptions.EntityDoesNotExistException;
import com.project.exceptions.InvalidDataException;
import com.project.repository.PricelistItemRepository;
import com.project.repository.PricelistRepository;
import com.project.repository.TicketRepository;
import com.project.repository.UserRepository;
import com.project.web.dto.TicketDTO;

@Service
public class TicketService {

	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private PricelistRepository pricelistRepository;
	
	@Autowired
	private PricelistItemRepository pricelistItemRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public boolean create(TicketDTO ticketDTO) throws EntityDoesNotExistException, InvalidDataException{
		try{
			if (ticketDTO == null) throw new InvalidDataException("Ticket is null.");
			Passenger u = (Passenger) userRepository.findByUsername(ticketDTO.getUser());
			if (u == null) throw new EntityDoesNotExistException("User does not exist.");	
			TicketType ticketT = TicketType.valueOf(ticketDTO.getTicketType());
			TransportType transportT = TransportType.valueOf(ticketDTO.getTransportType());
			Pricelist pl = pricelistRepository.findTopByOrderByIdDesc();
			PricelistItem pli = pricelistItemRepository.findByPricelistAndTicketTypeAndTransportType(pl, ticketT, transportT);
			if (pli == null) throw new EntityDoesNotExistException("Price list item does not exist.");
	//		System.out.println("Pricelist item je " + pli);
			Ticket ticket = new Ticket(u, ticketT, transportT, pli);
			u.getTikcets().add(ticket);
			userRepository.save(u);
			ticketRepository.save(ticket);
			return true;
		}catch (IllegalArgumentException iae){
			throw new InvalidDataException("Invalid value for Transport type or Ticket type.");
		}catch (NullPointerException npe){
			throw new InvalidDataException("Null can not be Transport type or Ticket type.");
		}
		
	}
	
	public ArrayList<Ticket> getUserTickets(String username) throws EntityDoesNotExistException, InvalidDataException {
		if (username == null) throw new InvalidDataException("Username is null.");
		Passenger p = (Passenger) userRepository.findByUsername(username);
		if (p == null) throw new EntityDoesNotExistException("User does not exist.");
		return (ArrayList<Ticket>) p.getTikcets();
	}
	
	public Ticket getUserTicket(String username, String transportType) throws EntityDoesNotExistException, InvalidDataException {
		try{
			ArrayList<Ticket> tickets = getUserTickets(username);
			TransportType ttype = TransportType.valueOf(transportType);
			for (Ticket t : tickets){
				if (t.getTransportType() == ttype && t.isActive() == true){
					return t;
				}
			}
			throw new EntityDoesNotExistException("Active ticket not found");
		}catch (IllegalArgumentException iae){
			throw new InvalidDataException("Invalid value for Transport type.");
		}catch (NullPointerException npe){
			throw new InvalidDataException("Null can not be Transport type.");
		}
	}
}
