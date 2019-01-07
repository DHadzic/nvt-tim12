package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.domain.Passenger;
import com.project.domain.Pricelist;
import com.project.domain.PricelistItem;
import com.project.domain.Ticket;
import com.project.domain.TicketType;
import com.project.domain.User;
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
	
	public boolean create(TicketDTO ticketDTO){
		
		Passenger u = (Passenger) userRepository.findByUsername(ticketDTO.getUser());
		if (u == null) {
			System.out.println("NEMA USERA");
			return false;
		}
		TicketType tt = TicketType.valueOf(ticketDTO.getType());
		System.out.println("Tip je " + tt);
		Pricelist pl = pricelistRepository.findTopByOrderByIdDesc();
		System.out.println("Pricelist je " + pl);
		PricelistItem pli = pricelistItemRepository.findByPricelistAndTicketType(pl, tt);
		if (pli == null){
			System.out.println("NEMA CENE");
			return false;
		}
		System.out.println("Pricelist item je " + pli);
		Ticket ticket = new Ticket(u, tt, pli);
		
		ticketRepository.save(ticket);
		return true;
		
	}
}
