package com.project.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.MissingRequestCookieException;

import com.project.domain.Passenger;
import com.project.domain.Pricelist;
import com.project.domain.PricelistItem;
import com.project.domain.Ticket;
import com.project.domain.TicketType;
import com.project.domain.TransportType;
import com.project.exceptions.EntityDoesNotExistException;
import com.project.repository.PricelistItemRepository;
import com.project.repository.PricelistRepository;
import com.project.repository.TicketRepository;
import com.project.repository.UserRepository;
import com.project.web.dto.TicketDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class TicketServiceImplTest {

	@Autowired
	private TicketServiceImpl ticketService;
	
	@MockBean
	private TicketRepository ticketRepository;
	
	@MockBean
	private PricelistRepository pricelistRepository;
	
	@MockBean
	private PricelistItemRepository pricelistItemRepository;
	
	@MockBean
	private UserRepository userRepository;
	
	@Before
	public void setUp() {
		Passenger p1 = new Passenger();
		p1.setUsername("p1");
		p1.setPassword("p11");
		Pricelist pl1 = new Pricelist();
		PricelistItem pli1 = new PricelistItem(pl1, TicketType.ONE_TIME, TransportType.BUS, 500);
		Ticket t1 = new Ticket(p1, TicketType.MONTHLY, TransportType.BUS, pli1);
		Mockito.when(userRepository.findByUsername("p1")).thenReturn(p1);
		Mockito.when(userRepository.findByUsername("p2")).thenReturn(null);
		Mockito.when(pricelistRepository.findTopByOrderByIdDesc()).thenReturn(pl1);
		Mockito.when(pricelistItemRepository.findByPricelistAndTicketType(pl1, TicketType.ONE_TIME)).thenReturn(pli1);
		Mockito.when(ticketRepository.findByUserAndType(p1, TicketType.MONTHLY)).thenReturn(t1);
	}
	
	@Test
	public void create_allGood() throws EntityDoesNotExistException {
		TicketDTO t1dto = new TicketDTO("p1", "ONE_TIME", "BUS");
		boolean answer = ticketService.create(t1dto);
		assertTrue(answer);
	}
	
	@Test(expected = EntityDoesNotExistException.class)
	public void create_userNotFound() throws EntityDoesNotExistException {
		TicketDTO t1dto = new TicketDTO("p2", "ONE_TIME", "BUS");
		ticketService.create(t1dto);
	}
}
