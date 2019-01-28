package com.project.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.project.constants.TicketConstants;
import com.project.exceptions.EntityDoesNotExistException;
import com.project.exceptions.InvalidDataException;
import com.project.web.dto.TicketDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
@Transactional
public class TicketServiceIntTest {
	@Autowired
	private TicketService ticketService;
	
	@Test
	@Rollback
	public void createSuccess(){
		TicketDTO t1dto = new TicketDTO(TicketConstants.USERNAME_GOOD, 
				TicketConstants.TICKET_TYPE_GOOD, TicketConstants.TRANSPORT_TYPE_GOOD);
		try {
			boolean answer = ticketService.create(t1dto);
			assertTrue(answer);
		} catch (EntityDoesNotExistException e) {
			System.out.println("\n\n\n"+e.getMessage());
			assertTrue(false);
		} catch (InvalidDataException e) {
			assertTrue(false);

		}
	}
	
	@Test
	public void createUserNotFound(){
		TicketDTO t1dto = new TicketDTO(TicketConstants.USERNAME_NOT_FOUND, 
				TicketConstants.TICKET_TYPE_GOOD, TicketConstants.TRANSPORT_TYPE_GOOD);		
		try {
			ticketService.create(t1dto);
			assertTrue(false);

		} catch (EntityDoesNotExistException e) {
			assertTrue(true);

		} catch (InvalidDataException e) {
			assertTrue(false);

		}
	}
	
	@Test
	public void createTicketDTOIsNull(){
		try {
			ticketService.create(null);
			assertTrue(false);
		} catch (EntityDoesNotExistException e) {
			assertTrue(false);
		} catch (InvalidDataException e) {
			assertTrue(true);

		}
	}
	
	@Test
	public void createTransportTypeNull(){
		TicketDTO t1dto = new TicketDTO(TicketConstants.USERNAME_GOOD, 
				TicketConstants.TICKET_TYPE_GOOD, null);
		try {
			ticketService.create(t1dto);
			assertTrue(false);
		} catch (EntityDoesNotExistException e) {
			assertTrue(false);
		} catch (InvalidDataException e) {
			assertTrue(true);

		}
	}
	
	@Test
	public void createTransportTypeInvalid(){
		TicketDTO t1dto = new TicketDTO(TicketConstants.USERNAME_GOOD, 
				TicketConstants.TICKET_TYPE_GOOD, TicketConstants.TRANSPORT_TYPE_INVALID);	
		try {
			ticketService.create(t1dto);
			assertTrue(false);
		} catch (EntityDoesNotExistException e) {
			assertTrue(false);
		} catch (InvalidDataException e) {
			assertTrue(true);

		}
	}
	
	@Test
	public void createTicketTypeNull(){
		TicketDTO t1dto = new TicketDTO(TicketConstants.USERNAME_GOOD, 
				null , TicketConstants.TRANSPORT_TYPE_INVALID);	
		try {
			ticketService.create(t1dto);
			assertTrue(false);
		} catch (EntityDoesNotExistException e) {
			assertTrue(false);
		} catch (InvalidDataException e) {
			assertTrue(true);

		}
	}
	
	@Test
	public void createTicketTypeInvalid() {
		TicketDTO t1dto = new TicketDTO(TicketConstants.USERNAME_GOOD, 
				TicketConstants.TICKET_TYPE_INVALID, TicketConstants.TRANSPORT_TYPE_INVALID);	
		try {
			ticketService.create(t1dto);
			assertTrue(false);
		} catch (EntityDoesNotExistException e) {
			assertTrue(false);
		} catch (InvalidDataException e) {
			assertTrue(true);

		}
	}
	
	@Test
	public void createPricelistItemNotFound(){
		TicketDTO t1dto = new TicketDTO(TicketConstants.USERNAME_GOOD, 
				TicketConstants.TICKET_TYPE_PLI_NOT_FOUND, TicketConstants.TRANSPORT_TYPE_GOOD);	

		try {
			ticketService.create(t1dto);
			assertTrue(false);
		} catch (EntityDoesNotExistException e) {
			assertTrue(true);

		} catch (InvalidDataException e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void getUserTicketsSuccess(){
		try {
			ticketService.getUserTickets(TicketConstants.USERNAME_GOOD);
		} catch (EntityDoesNotExistException e) {
			assertTrue(false);
		} catch (InvalidDataException e) {
			assertTrue(false);		}
	}
	
	@Test
	public void getUserTicketsUserNotFound(){
		try {
			ticketService.getUserTickets(TicketConstants.USERNAME_NOT_FOUND);
			assertTrue(false);
		} catch (EntityDoesNotExistException e) {
			assertTrue(true);

		} catch (InvalidDataException e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void getUserTicketsUsrenameNull(){
		try {
			ticketService.getUserTickets(null);
			assertTrue(false);
		} catch (EntityDoesNotExistException e) {
			assertTrue(false);
		} catch (InvalidDataException e) {
			assertTrue(true);

		}
	}
	
	@Test
	public void getUserTicketSuccess(){
		try {
			ticketService.getUserTicket(TicketConstants.USERNAME_GOOD, TicketConstants.TRANSPORT_TYPE_GOOD);
		} catch (EntityDoesNotExistException e) {
			assertTrue(true);
		} catch (InvalidDataException e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void getUserTicketUserNotFound(){
		try {
			ticketService.getUserTicket(TicketConstants.USERNAME_NOT_FOUND, TicketConstants.TRANSPORT_TYPE_GOOD);
			assertTrue(false);
		} catch (EntityDoesNotExistException e) {
			assertTrue(true);

		} catch (InvalidDataException e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void getUserTicketUsernameNull(){
		try {
			ticketService.getUserTicket(null, TicketConstants.TRANSPORT_TYPE_GOOD);
			assertTrue(false);
		} catch (EntityDoesNotExistException e) {
			assertTrue(false);
		} catch (InvalidDataException e) {
			assertTrue(true);

		}
	}
	
	@Test
	public void getUserTicketTransportTypeNull(){
		try {
			ticketService.getUserTicket(TicketConstants.USERNAME_GOOD, null);
			assertTrue(false);
		} catch (EntityDoesNotExistException e) {
			assertTrue(false);
		} catch (InvalidDataException e) {
			assertTrue(true);

		}
	}
	
	@Test
	public void getUserTicketTransporTypeInvalid(){
		try {
			ticketService.getUserTicket(TicketConstants.USERNAME_GOOD, TicketConstants.TRANSPORT_TYPE_INVALID);
			assertTrue(false);
		} catch (EntityDoesNotExistException e) {
			assertTrue(false);
		} catch (InvalidDataException e) {
			assertTrue(true);

		}
	}
	
	@Test
	public void getUserTicketNotActive(){
		try {
			ticketService.getUserTicket(TicketConstants.USERNAME_GOOD, TicketConstants.TRANSPORT_TYPE_INACTIVE_TICKET);
			assertTrue(false);
		} catch (EntityDoesNotExistException e) {
			assertTrue(true);

		} catch (InvalidDataException e) {
			assertTrue(false);
		}
	}
}
