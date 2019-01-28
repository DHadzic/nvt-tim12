package com.project.service;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
import com.project.exceptions.InvalidDataException;
import com.project.repository.PricelistItemRepository;
import com.project.repository.PricelistRepository;
import com.project.repository.TicketRepository;
import com.project.repository.UserRepository;
import com.project.web.dto.TicketDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class TicketServiceTest {

	@Autowired
	private TicketService ticketService;
	
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
		Ticket t2 = new Ticket();
		t2.setTransportType(TransportType.TRAM);
		t2.setUser(p1);
		t2.setActive(false);
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		tickets.add(t1);
		tickets.add(t2);
		p1.setTikcets(tickets);
		Mockito.when(userRepository.findByUsername("p1")).thenReturn(p1);
		Mockito.when(userRepository.findByUsername("p2")).thenReturn(null);
		Mockito.when(pricelistRepository.findTopByOrderByIdDesc()).thenReturn(pl1);
		Mockito.when(pricelistItemRepository.findByPricelistAndTicketTypeAndTransportType(pl1, TicketType.ONE_TIME, TransportType.BUS)).thenReturn(pli1);
		Mockito.when(pricelistItemRepository.findByPricelistAndTicketTypeAndTransportType(pl1, TicketType.ONE_DAY, TransportType.BUS)).thenReturn(null);
		Mockito.when(ticketRepository.findByUser(p1)).thenReturn(tickets);
//		Mockito.when(ticketRepository.findByUserAndType(p1, TicketType.MONTHLY)).thenReturn(t1);
	}
	
	@Test
	public void createSuccess() throws EntityDoesNotExistException, InvalidDataException {
		TicketDTO t1dto = new TicketDTO("p1", "ONE_TIME", "BUS");
		boolean answer = ticketService.create(t1dto);
		assertTrue(answer);
	}
	
	@Test(expected = EntityDoesNotExistException.class)
	public void createUserNotFound() throws EntityDoesNotExistException, InvalidDataException {
		TicketDTO t1dto = new TicketDTO("p2", "ONE_TIME", "BUS");
		ticketService.create(t1dto);
	}
	
	@Test(expected = InvalidDataException.class)
	public void createTicketDTOIsNull() throws EntityDoesNotExistException, InvalidDataException {
		ticketService.create(null);
	}
	
	@Test(expected = InvalidDataException.class)
	public void createTransportTypeNull() throws EntityDoesNotExistException, InvalidDataException {
		TicketDTO t1dto = new TicketDTO("p2", "ONE_TIME", null);
		ticketService.create(t1dto);
	}
	
	@Test(expected = InvalidDataException.class)
	public void createTransportTypeInvalid() throws EntityDoesNotExistException, InvalidDataException {
		TicketDTO t1dto = new TicketDTO("p2", "ONE_TIME", " ");
		ticketService.create(t1dto);
	}
	
	@Test(expected = InvalidDataException.class)
	public void createTicketTypeNull() throws EntityDoesNotExistException, InvalidDataException {
		TicketDTO t1dto = new TicketDTO("p2", null, "BUS");
		ticketService.create(t1dto);
	}
	
	@Test(expected = InvalidDataException.class)
	public void createTicketTypeInvalid() throws EntityDoesNotExistException, InvalidDataException {
		TicketDTO t1dto = new TicketDTO("p2", " ", "BUS");
		ticketService.create(t1dto);
	}
	
	@Test(expected = EntityDoesNotExistException.class)
	public void createPricelistItemNotFound() throws EntityDoesNotExistException, InvalidDataException {
		TicketDTO t1dto = new TicketDTO("p2", "ONE_DAY", "BUS");
		ticketService.create(t1dto);
	}
	
	@Test
	public void getUserTicketsSuccess() throws EntityDoesNotExistException, InvalidDataException{
		ticketService.getUserTickets("p1");
	}
	
	@Test(expected = EntityDoesNotExistException.class)
	public void getUserTicketsUserNotFound() throws EntityDoesNotExistException, InvalidDataException{
		ticketService.getUserTickets("p2");
	}
	
	@Test(expected = InvalidDataException.class)
	public void getUserTicketsUsrenameNull() throws EntityDoesNotExistException, InvalidDataException{
		ticketService.getUserTickets(null);
	}
	
	@Test
	public void getUserTicketSuccess() throws EntityDoesNotExistException, InvalidDataException{
		ticketService.getUserTicket("p1", "BUS");
	}
	
	@Test(expected = EntityDoesNotExistException.class)
	public void getUserTicketUserNotFound() throws EntityDoesNotExistException, InvalidDataException{
		ticketService.getUserTicket("p2", "BUS");
	}
	
	@Test(expected = InvalidDataException.class)
	public void getUserTicketUsernameNull() throws EntityDoesNotExistException, InvalidDataException{
		ticketService.getUserTicket(null, "BUS");
	}
	
	@Test(expected = InvalidDataException.class)
	public void getUserTicketTransportTypeNull() throws EntityDoesNotExistException, InvalidDataException{
		ticketService.getUserTicket("p1", null);
	}
	
	@Test(expected = InvalidDataException.class)
	public void getUserTicketTransporTypeInvalid() throws EntityDoesNotExistException, InvalidDataException{
		ticketService.getUserTicket("p1", " ");
	}
	
	@Test(expected = EntityDoesNotExistException.class)
	public void getUserTicketNotActive() throws EntityDoesNotExistException, InvalidDataException{
		ticketService.getUserTicket("p1", "TRAM");
	}
}
