package com.project.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.project.constants.PricelistConstants;
import com.project.domain.Pricelist;
import com.project.exceptions.EntityDoesNotExistException;
import com.project.exceptions.InvalidDataException;
import com.project.web.dto.PricelistItemDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
@Transactional
public class PricelistServiceIntTest {
	@Autowired
	private PricelistService pricelistService;
	
	@Test
	@Rollback
	public void createPricelistSuccess(){
		ArrayList<PricelistItemDTO> plItemsDTO = PricelistConstants.PRICELIST_ITEMS_DTO;
		plItemsDTO.add(new PricelistItemDTO(PricelistConstants.PRICELISTITEM_TICKET_TYPE_GOOD,
				PricelistConstants.PRICELISTITEM_TRANS_TYPE_GOOD, PricelistConstants.PRICELISTITEM_PRICE_GOOD));
		try {
			pricelistService.createPricelistAndPricelistItems(plItemsDTO);
			PricelistConstants.PRICELIST_ITEMS_DTO.remove(PricelistConstants.PRICELIST_ITEMS_DTO.size()-1);
		} catch (InvalidDataException e) {
			assertTrue(true);
		}
	}

	@Test
	public void createPricelistListTooShort(){
		ArrayList<PricelistItemDTO> plItemsDTO = PricelistConstants.PRICELIST_ITEMS_DTO;
		try {
			pricelistService.createPricelistAndPricelistItems(plItemsDTO);
			assertTrue(false);
		} catch (InvalidDataException e) {
			assertEquals("Number of pricelist items too small or too big.", e.getMessage());
		}
	}
	
	@Test
	public void createPricelistListTooLong(){
		ArrayList<PricelistItemDTO> plItemsDTO = PricelistConstants.PRICELIST_ITEMS_DTO;
		plItemsDTO.add(new PricelistItemDTO(PricelistConstants.PRICELISTITEM_TICKET_TYPE_GOOD,
				PricelistConstants.PRICELISTITEM_TRANS_TYPE_GOOD, PricelistConstants.PRICELISTITEM_PRICE_GOOD));
		plItemsDTO.add(new PricelistItemDTO(PricelistConstants.PRICELISTITEM_TICKET_TYPE_GOOD,
				PricelistConstants.PRICELISTITEM_TRANS_TYPE_GOOD, PricelistConstants.PRICELISTITEM_PRICE_GOOD+1));
		try {
			pricelistService.createPricelistAndPricelistItems(plItemsDTO);
			assertTrue(false);
		} catch (InvalidDataException e) {
			PricelistConstants.PRICELIST_ITEMS_DTO.remove(PricelistConstants.PRICELIST_ITEMS_DTO.size()-1);
			PricelistConstants.PRICELIST_ITEMS_DTO.remove(PricelistConstants.PRICELIST_ITEMS_DTO.size()-1);
			assertEquals("Number of pricelist items too small or too big.", e.getMessage());
		}
	}
	
	@Test
	public void createPricelistListPriceNegative(){
		ArrayList<PricelistItemDTO> plItemsDTO = PricelistConstants.PRICELIST_ITEMS_DTO;
		plItemsDTO.add(new PricelistItemDTO(PricelistConstants.PRICELISTITEM_TICKET_TYPE_GOOD, 
				PricelistConstants.PRICELISTITEM_TRANS_TYPE_GOOD, PricelistConstants.PRICELISTITEM_PRICE_NEGATIVE));
		try {
			pricelistService.createPricelistAndPricelistItems(plItemsDTO);
			assertTrue(false);
		} catch (InvalidDataException e) {
			PricelistConstants.PRICELIST_ITEMS_DTO.remove(PricelistConstants.PRICELIST_ITEMS_DTO.size()-1);
			assertEquals("Price can not be negative value or zero.", e.getMessage());
		}
	}
	
	@Test
	public void createPricelistListPriceZero(){
		ArrayList<PricelistItemDTO> plItemsDTO = PricelistConstants.PRICELIST_ITEMS_DTO;
		plItemsDTO.add(new PricelistItemDTO(PricelistConstants.PRICELISTITEM_TICKET_TYPE_GOOD, 
				PricelistConstants.PRICELISTITEM_TRANS_TYPE_GOOD, 0));
		try {
			pricelistService.createPricelistAndPricelistItems(plItemsDTO);
			assertTrue(false);
		} catch (InvalidDataException e) {
			PricelistConstants.PRICELIST_ITEMS_DTO.remove(PricelistConstants.PRICELIST_ITEMS_DTO.size()-1);
			assertEquals("Price can not be negative value or zero.", e.getMessage());
		}
	}
	
	@Test
	public void createPricelistListPriceTooHigh(){
		ArrayList<PricelistItemDTO> plItemsDTO = PricelistConstants.PRICELIST_ITEMS_DTO;
		plItemsDTO.add(new PricelistItemDTO(PricelistConstants.PRICELISTITEM_TICKET_TYPE_GOOD, 
				PricelistConstants.PRICELISTITEM_TRANS_TYPE_GOOD, PricelistConstants.PRICELISTITEM_PRICE_TOO_HIGH));
		try {
			pricelistService.createPricelistAndPricelistItems(plItemsDTO);
			assertTrue(false);
		} catch (InvalidDataException e) {
			PricelistConstants.PRICELIST_ITEMS_DTO.remove(PricelistConstants.PRICELIST_ITEMS_DTO.size()-1);
			assertEquals("Price is too high.", e.getMessage());
		}
	}
	
	@Test
	public void createPricelistListIsNull(){
		try {
			pricelistService.createPricelistAndPricelistItems(null);
			assertTrue(false);
		} catch (InvalidDataException e) {
			assertEquals("List is null.", e.getMessage());
		}
	}
	
	@Test
	public void createPricelistListTicketTypeNull(){
		ArrayList<PricelistItemDTO> plItemsDTO = PricelistConstants.PRICELIST_ITEMS_DTO;
		plItemsDTO.add(new PricelistItemDTO(null, PricelistConstants.PRICELISTITEM_TRANS_TYPE_GOOD, 
				PricelistConstants.PRICELISTITEM_PRICE_GOOD));
		try {
			pricelistService.createPricelistAndPricelistItems(plItemsDTO);
			assertTrue(false);
		} catch (InvalidDataException e) {
			PricelistConstants.PRICELIST_ITEMS_DTO.remove(PricelistConstants.PRICELIST_ITEMS_DTO.size()-1);
			assertEquals("Null can not be Transport type or Ticket type.", e.getMessage());
		}
	}
	
	@Test
	public void createPricelistListTickettTypeInvalid(){
		ArrayList<PricelistItemDTO> plItemsDTO = PricelistConstants.PRICELIST_ITEMS_DTO;
		plItemsDTO.add(new PricelistItemDTO(PricelistConstants.PRICELISTITEM_TICKET_TYPE_INVALID, 
				PricelistConstants.PRICELISTITEM_TRANS_TYPE_GOOD, PricelistConstants.PRICELISTITEM_PRICE_GOOD));
		try {
			pricelistService.createPricelistAndPricelistItems(plItemsDTO);
			assertTrue(false);
		} catch (InvalidDataException e) {
			PricelistConstants.PRICELIST_ITEMS_DTO.remove(PricelistConstants.PRICELIST_ITEMS_DTO.size()-1);
			assertEquals("Invalid value for Transport type or Ticket type.", e.getMessage());
		}
	}
	
	@Test
	public void createPricelistListTransportTypeNull(){
		ArrayList<PricelistItemDTO> plItemsDTO = PricelistConstants.PRICELIST_ITEMS_DTO;
		plItemsDTO.add(new PricelistItemDTO(PricelistConstants.PRICELISTITEM_TICKET_TYPE_GOOD,
				null, PricelistConstants.PRICELISTITEM_PRICE_GOOD));
		try {
			pricelistService.createPricelistAndPricelistItems(plItemsDTO);
			assertTrue(false);
		} catch (InvalidDataException e) {
			PricelistConstants.PRICELIST_ITEMS_DTO.remove(PricelistConstants.PRICELIST_ITEMS_DTO.size()-1);
			assertEquals("Null can not be Transport type or Ticket type.", e.getMessage());
		}
	}
	
	@Test
	public void createPricelistListTransportTypeInvalid(){
		ArrayList<PricelistItemDTO> plItemsDTO = PricelistConstants.PRICELIST_ITEMS_DTO;
		plItemsDTO.add(new PricelistItemDTO(PricelistConstants.PRICELISTITEM_TICKET_TYPE_GOOD, 
				PricelistConstants.PRICELISTITEM_TRANS_TYPE_INVALID, PricelistConstants.PRICELISTITEM_PRICE_GOOD));
		try {
			pricelistService.createPricelistAndPricelistItems(plItemsDTO);
			assertTrue(false);
		} catch (InvalidDataException e) {
			PricelistConstants.PRICELIST_ITEMS_DTO.remove(PricelistConstants.PRICELIST_ITEMS_DTO.size()-1);
			assertEquals("Invalid value for Transport type or Ticket type.", e.getMessage());
		}
	}
	
	@Test
	public void createPricelistListHasDuplicates(){
		ArrayList<PricelistItemDTO> plItemsDTO = PricelistConstants.PRICELIST_ITEMS_DTO;
		plItemsDTO.add(new PricelistItemDTO(PricelistConstants.PRICELISTITEM_TICKET_TYPE_TAKEN, 
				PricelistConstants.PRICELISTITEM_TRANS_TYPE_GOOD, PricelistConstants.PRICELISTITEM_PRICE_GOOD));
		try {
			pricelistService.createPricelistAndPricelistItems(plItemsDTO);
			assertTrue(false);
		} catch (InvalidDataException e) {
			PricelistConstants.PRICELIST_ITEMS_DTO.remove(PricelistConstants.PRICELIST_ITEMS_DTO.size()-1);
			assertEquals("List contains multiple prices for same ticket and transport type.", e.getMessage());
		}
	}
	
	@Test
	public void getAllPricelists(){
		ArrayList<Pricelist> pricelists;
		try {
			pricelists = pricelistService.getPricelists();
			assertNotNull(pricelists);
		} catch (EntityDoesNotExistException e) {
			assertTrue(false);
		}
		
	}
	
	@Test
	@Rollback
	public void reactivatePricelistSuccess(){
		try {
			pricelistService.reactivatePricelist(999l);
		} catch (EntityDoesNotExistException e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void reactivatePricelistNotFound(){
		try {
			pricelistService.reactivatePricelist(900l);
			assertTrue(false);
		} catch (EntityDoesNotExistException e) {
			assertEquals("Pricelist not found.", e.getMessage());
		}
	}
	
	
	@Test
	@Rollback
	public void deletePricelistSuccess(){
		try {
			pricelistService.deletePricelist(999L);
		} catch (NoSuchElementException e) {
			assertTrue(false);
		} catch (InvalidDataException e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void deletePricelistIdIsNull(){
		try {
			pricelistService.deletePricelist(null);
			assertTrue(false);
		} catch (NoSuchElementException e) {
			assertTrue(false);
		} catch (InvalidDataException e) {
			assertEquals("Id can not be null.", e.getMessage());
		}

	}
	
	@Test
	public void deletePricelistNotFound(){
		try {
			pricelistService.deletePricelist(900l);
			assertTrue(false);
		} catch (NoSuchElementException e) {
			assertTrue(true);
		} catch (InvalidDataException e) {
			assertTrue(false);
		}

	}
	
	@Test
	public void deletePricelistIsActive(){
		try {
			pricelistService.deletePricelist(996l);
			assertTrue(false);
		} catch (NoSuchElementException e) {
			assertTrue(false);
		} catch (InvalidDataException e) {
			assertEquals("Pricelist is currently active. Can not be deleted.", e.getMessage());
		}
	}
	
	@Test
	public void deletePricelistActiveTicketsUsingIt(){
		try {
			pricelistService.deletePricelist(997L);
			assertTrue(false);
		} catch (NoSuchElementException e) {
			assertTrue(false);
		} catch (InvalidDataException e) {
			assertEquals("There are active tickets using this pricelist. Can not be deleted.", e.getMessage());
		}
	}
	
}
