package com.project.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.project.TestUtil;
import com.project.constants.PricelistConstants;
import com.project.domain.Pricelist;
import com.project.exceptions.EntityDoesNotExistException;
import com.project.exceptions.InvalidDataException;
import com.project.web.dto.PricelistItemDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class PricelistControllerTest {

	private static final String URL_PREFIX = "/pricelist";

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@PostConstruct
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	@Rollback
	public void createPricelistSuccess() throws Exception{
		ArrayList<PricelistItemDTO> plItemsDTO = PricelistConstants.PRICELIST_ITEMS_DTO;
		plItemsDTO.add(new PricelistItemDTO(PricelistConstants.PRICELISTITEM_TICKET_TYPE_GOOD,
				PricelistConstants.PRICELISTITEM_TRANS_TYPE_GOOD, PricelistConstants.PRICELISTITEM_PRICE_GOOD));
		String json = TestUtil.json(plItemsDTO);
		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/create").contentType(contentType).content(json))
				.andExpect(status().isOk()).andReturn();
		
		PricelistConstants.PRICELIST_ITEMS_DTO.remove(PricelistConstants.PRICELIST_ITEMS_DTO.size()-1);
		assertTrue(result.getResponse().getContentAsString().contains("Pricelist successfully created."));
//		try {
//			pricelistService.createPricelistAndPricelistItems(plItemsDTO);
//			PricelistConstants.PRICELIST_ITEMS_DTO.remove(PricelistConstants.PRICELIST_ITEMS_DTO.size()-1);
//		} catch (InvalidDataException e) {
//			assertTrue(true);
//		}
	}

	@Test
	public void createPricelistListTooShort() throws Exception{
		ArrayList<PricelistItemDTO> plItemsDTO = PricelistConstants.PRICELIST_ITEMS_DTO;
		String json = TestUtil.json(plItemsDTO);
		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/create").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();
		
		assertFalse(result.getResponse().getContentAsString().contains("Pricelist successfully created."));
//		
//		try {
//			pricelistService.createPricelistAndPricelistItems(plItemsDTO);
//			assertTrue(false);
//		} catch (InvalidDataException e) {
//			assertEquals("Number of pricelist items too small or too big.", e.getMessage());
//		}
	}
	
	@Test
	public void createPricelistListTooLong() throws Exception{
		
		
		ArrayList<PricelistItemDTO> plItemsDTO = PricelistConstants.PRICELIST_ITEMS_DTO;
		plItemsDTO.add(new PricelistItemDTO(PricelistConstants.PRICELISTITEM_TICKET_TYPE_GOOD,
				PricelistConstants.PRICELISTITEM_TRANS_TYPE_GOOD, PricelistConstants.PRICELISTITEM_PRICE_GOOD));
		plItemsDTO.add(new PricelistItemDTO(PricelistConstants.PRICELISTITEM_TICKET_TYPE_GOOD,
				PricelistConstants.PRICELISTITEM_TRANS_TYPE_GOOD, PricelistConstants.PRICELISTITEM_PRICE_GOOD+1));
		String json = TestUtil.json(plItemsDTO);
		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/create").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();
		
		PricelistConstants.PRICELIST_ITEMS_DTO.remove(PricelistConstants.PRICELIST_ITEMS_DTO.size()-1);
		PricelistConstants.PRICELIST_ITEMS_DTO.remove(PricelistConstants.PRICELIST_ITEMS_DTO.size()-1);
		assertFalse(result.getResponse().getContentAsString().contains("Pricelist successfully created."));
//		try {
//			pricelistService.createPricelistAndPricelistItems(plItemsDTO);
//			assertTrue(false);
//		} catch (InvalidDataException e) {
//			PricelistConstants.PRICELIST_ITEMS_DTO.remove(PricelistConstants.PRICELIST_ITEMS_DTO.size()-1);
//			PricelistConstants.PRICELIST_ITEMS_DTO.remove(PricelistConstants.PRICELIST_ITEMS_DTO.size()-1);
//			assertEquals("Number of pricelist items too small or too big.", e.getMessage());
//		}
	}
	
	@Test
	public void createPricelistListPriceNegative() throws Exception{
		ArrayList<PricelistItemDTO> plItemsDTO = PricelistConstants.PRICELIST_ITEMS_DTO;
		plItemsDTO.add(new PricelistItemDTO(PricelistConstants.PRICELISTITEM_TICKET_TYPE_GOOD, 
				PricelistConstants.PRICELISTITEM_TRANS_TYPE_GOOD, PricelistConstants.PRICELISTITEM_PRICE_NEGATIVE));
		String json = TestUtil.json(plItemsDTO);
		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/create").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();
		
		PricelistConstants.PRICELIST_ITEMS_DTO.remove(PricelistConstants.PRICELIST_ITEMS_DTO.size()-1);
		assertFalse(result.getResponse().getContentAsString().contains("Pricelist successfully created."));
//		try {
//			pricelistService.createPricelistAndPricelistItems(plItemsDTO);
//			assertTrue(false);
//		} catch (InvalidDataException e) {
//			PricelistConstants.PRICELIST_ITEMS_DTO.remove(PricelistConstants.PRICELIST_ITEMS_DTO.size()-1);
//			assertEquals("Price can not be negative value or zero.", e.getMessage());
//		}
	}
	
	@Test
	public void createPricelistListPriceZero() throws Exception{
		ArrayList<PricelistItemDTO> plItemsDTO = PricelistConstants.PRICELIST_ITEMS_DTO;
		plItemsDTO.add(new PricelistItemDTO(PricelistConstants.PRICELISTITEM_TICKET_TYPE_GOOD, 
				PricelistConstants.PRICELISTITEM_TRANS_TYPE_GOOD, 0));
		String json = TestUtil.json(plItemsDTO);
		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/create").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();
		
		PricelistConstants.PRICELIST_ITEMS_DTO.remove(PricelistConstants.PRICELIST_ITEMS_DTO.size()-1);
		assertFalse(result.getResponse().getContentAsString().contains("Pricelist successfully created."));
//		try {
//			pricelistService.createPricelistAndPricelistItems(plItemsDTO);
//			assertTrue(false);
//		} catch (InvalidDataException e) {
//			PricelistConstants.PRICELIST_ITEMS_DTO.remove(PricelistConstants.PRICELIST_ITEMS_DTO.size()-1);
//			assertEquals("Price can not be negative value or zero.", e.getMessage());
//		}
	}
	
	@Test
	public void createPricelistListPriceTooHigh() throws Exception{
		ArrayList<PricelistItemDTO> plItemsDTO = PricelistConstants.PRICELIST_ITEMS_DTO;
		plItemsDTO.add(new PricelistItemDTO(PricelistConstants.PRICELISTITEM_TICKET_TYPE_GOOD, 
				PricelistConstants.PRICELISTITEM_TRANS_TYPE_GOOD, PricelistConstants.PRICELISTITEM_PRICE_TOO_HIGH));
		String json = TestUtil.json(plItemsDTO);
		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/create").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();
		
		PricelistConstants.PRICELIST_ITEMS_DTO.remove(PricelistConstants.PRICELIST_ITEMS_DTO.size()-1);
		assertFalse(result.getResponse().getContentAsString().contains("Pricelist successfully created."));
//		try {
//			pricelistService.createPricelistAndPricelistItems(plItemsDTO);
//			assertTrue(false);
//		} catch (InvalidDataException e) {
//			PricelistConstants.PRICELIST_ITEMS_DTO.remove(PricelistConstants.PRICELIST_ITEMS_DTO.size()-1);
//			assertEquals("Price is too high.", e.getMessage());
//		}
	}
	
	@Test
	public void createPricelistListIsNull() throws Exception{
		String json = TestUtil.json(null);
		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/create").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();
		
		assertFalse(result.getResponse().getContentAsString().contains("Pricelist successfully created."));
//		try {
//			pricelistService.createPricelistAndPricelistItems(null);
//			assertTrue(false);
//		} catch (InvalidDataException e) {
//			assertEquals("List is null.", e.getMessage());
//		}
	}
	
	@Test
	public void createPricelistListTicketTypeNull() throws Exception{
		ArrayList<PricelistItemDTO> plItemsDTO = PricelistConstants.PRICELIST_ITEMS_DTO;
		plItemsDTO.add(new PricelistItemDTO(null, PricelistConstants.PRICELISTITEM_TRANS_TYPE_GOOD, 
				PricelistConstants.PRICELISTITEM_PRICE_GOOD));
		String json = TestUtil.json(plItemsDTO);
		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/create").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();
		
		PricelistConstants.PRICELIST_ITEMS_DTO.remove(PricelistConstants.PRICELIST_ITEMS_DTO.size()-1);
		assertFalse(result.getResponse().getContentAsString().contains("Pricelist successfully created."));
//		try {
//			pricelistService.createPricelistAndPricelistItems(plItemsDTO);
//			assertTrue(false);
//		} catch (InvalidDataException e) {
//			PricelistConstants.PRICELIST_ITEMS_DTO.remove(PricelistConstants.PRICELIST_ITEMS_DTO.size()-1);
//			assertEquals("Null can not be Transport type or Ticket type.", e.getMessage());
//		}
	}
	
	@Test
	public void createPricelistListTickettTypeInvalid() throws Exception{
		ArrayList<PricelistItemDTO> plItemsDTO = PricelistConstants.PRICELIST_ITEMS_DTO;
		plItemsDTO.add(new PricelistItemDTO(PricelistConstants.PRICELISTITEM_TICKET_TYPE_INVALID, 
				PricelistConstants.PRICELISTITEM_TRANS_TYPE_GOOD, PricelistConstants.PRICELISTITEM_PRICE_GOOD));
		String json = TestUtil.json(plItemsDTO);
		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/create").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();
		
		PricelistConstants.PRICELIST_ITEMS_DTO.remove(PricelistConstants.PRICELIST_ITEMS_DTO.size()-1);
		assertFalse(result.getResponse().getContentAsString().contains("Pricelist successfully created."));
//		try {
//			pricelistService.createPricelistAndPricelistItems(plItemsDTO);
//			assertTrue(false);
//		} catch (InvalidDataException e) {
//			PricelistConstants.PRICELIST_ITEMS_DTO.remove(PricelistConstants.PRICELIST_ITEMS_DTO.size()-1);
//			assertEquals("Invalid value for Transport type or Ticket type.", e.getMessage());
//		}
	}
	
	@Test
	public void createPricelistListTransportTypeNull() throws Exception{
		ArrayList<PricelistItemDTO> plItemsDTO = PricelistConstants.PRICELIST_ITEMS_DTO;
		plItemsDTO.add(new PricelistItemDTO(PricelistConstants.PRICELISTITEM_TICKET_TYPE_GOOD,
				null, PricelistConstants.PRICELISTITEM_PRICE_GOOD));
		String json = TestUtil.json(plItemsDTO);
		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/create").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();
		
		PricelistConstants.PRICELIST_ITEMS_DTO.remove(PricelistConstants.PRICELIST_ITEMS_DTO.size()-1);
		assertFalse(result.getResponse().getContentAsString().contains("Pricelist successfully created."));
//		try {
//			pricelistService.createPricelistAndPricelistItems(plItemsDTO);
//			assertTrue(false);
//		} catch (InvalidDataException e) {
//			PricelistConstants.PRICELIST_ITEMS_DTO.remove(PricelistConstants.PRICELIST_ITEMS_DTO.size()-1);
//			assertEquals("Null can not be Transport type or Ticket type.", e.getMessage());
//		}
	}
	
	@Test
	public void createPricelistListTransportTypeInvalid() throws Exception{
		ArrayList<PricelistItemDTO> plItemsDTO = PricelistConstants.PRICELIST_ITEMS_DTO;
		plItemsDTO.add(new PricelistItemDTO(PricelistConstants.PRICELISTITEM_TICKET_TYPE_GOOD, 
				PricelistConstants.PRICELISTITEM_TRANS_TYPE_INVALID, PricelistConstants.PRICELISTITEM_PRICE_GOOD));
		String json = TestUtil.json(plItemsDTO);
		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/create").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();
		
		PricelistConstants.PRICELIST_ITEMS_DTO.remove(PricelistConstants.PRICELIST_ITEMS_DTO.size()-1);
		assertFalse(result.getResponse().getContentAsString().contains("Pricelist successfully created."));
//		try {
//			pricelistService.createPricelistAndPricelistItems(plItemsDTO);
//			assertTrue(false);
//		} catch (InvalidDataException e) {
//			PricelistConstants.PRICELIST_ITEMS_DTO.remove(PricelistConstants.PRICELIST_ITEMS_DTO.size()-1);
//			assertEquals("Invalid value for Transport type or Ticket type.", e.getMessage());
//		}
	}
	
	@Test
	public void createPricelistListHasDuplicates() throws Exception{
		ArrayList<PricelistItemDTO> plItemsDTO = PricelistConstants.PRICELIST_ITEMS_DTO;
		plItemsDTO.add(new PricelistItemDTO(PricelistConstants.PRICELISTITEM_TICKET_TYPE_TAKEN, 
				PricelistConstants.PRICELISTITEM_TRANS_TYPE_GOOD, PricelistConstants.PRICELISTITEM_PRICE_GOOD));
		String json = TestUtil.json(plItemsDTO);
		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/create").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();
		
		PricelistConstants.PRICELIST_ITEMS_DTO.remove(PricelistConstants.PRICELIST_ITEMS_DTO.size()-1);
		assertFalse(result.getResponse().getContentAsString().contains("Pricelist successfully created."));
//		try {
//			pricelistService.createPricelistAndPricelistItems(plItemsDTO);
//			assertTrue(false);
//		} catch (InvalidDataException e) {
//			PricelistConstants.PRICELIST_ITEMS_DTO.remove(PricelistConstants.PRICELIST_ITEMS_DTO.size()-1);
//			assertEquals("List contains multiple prices for same ticket and transport type.", e.getMessage());
//		}
	}
	
	@Test
	public void getAllPricelists() throws Exception{
		MvcResult result = mockMvc.perform(get(URL_PREFIX + "/getAll"))
				.andExpect(status().isOk()).andReturn();
		
		assertTrue(result != null);
//		try {
//			pricelists = pricelistService.getPricelists();
//			assertNotNull(pricelists);
//		} catch (EntityDoesNotExistException e) {
//			assertTrue(false);
//		}
		
	}
	
	@Test
	@Rollback
	public void reactivatePricelistSuccess() throws Exception{
		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/reactivatePricelist").contentType(MediaType.TEXT_PLAIN_VALUE).content("999"))
				.andExpect(status().isOk()).andReturn();
		
		assertTrue(result.getResponse().getContentAsString().contains("Pricelist reactivated"));
//		try {
//			pricelistService.reactivatePricelist(999l);
//		} catch (EntityDoesNotExistException e) {
//			assertTrue(false);
//		}
	}
	
	@Test
	public void reactivatePricelistNotFound() throws Exception{
		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/reactivatePricelist").contentType(MediaType.TEXT_PLAIN_VALUE).content("900"))
				.andExpect(status().isBadRequest()).andReturn();
		
		assertFalse(result.getResponse().getContentAsString().contains("Pricelist reactivated"));
		
//		try {
//			pricelistService.reactivatePricelist(900l);
//			assertTrue(false);
//		} catch (EntityDoesNotExistException e) {
//			assertEquals("Pricelist not found.", e.getMessage());
//		}
	}
	
	
	@Test
	@Rollback
	public void deletePricelistSuccess() throws Exception{
		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/deletePricelist").contentType(MediaType.TEXT_PLAIN_VALUE).content("999"))
				.andExpect(status().isOk()).andReturn();
		
		assertTrue(result.getResponse().getContentAsString().contains("Pricelist deleted."));
		
		
//		try {
//			pricelistService.deletePricelist(999L);
//		} catch (NoSuchElementException e) {
//			assertTrue(false);
//		} catch (InvalidDataException e) {
//			assertTrue(false);
//		}
	}
	
	
	@Test
	public void deletePricelistNotFound() throws Exception{
		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/deletePricelist").contentType(MediaType.TEXT_PLAIN_VALUE).content("900"))
				.andExpect(status().isBadRequest()).andReturn();
		
		assertFalse(result.getResponse().getContentAsString().contains("Pricelist deleted."));

	}
	
	@Test
	public void deletePricelistIsActive() throws Exception{
		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/deletePricelist").contentType(MediaType.TEXT_PLAIN_VALUE).content("998"))
				.andExpect(status().isOk()).andReturn();
		
		assertFalse(result.getResponse().getContentAsString().contains("Pricelist deleted."));
	}
	
	@Test
	public void deletePricelistActiveTicketsUsingIt() throws Exception{
		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/deletePricelist").contentType(MediaType.TEXT_PLAIN_VALUE).content("997"))
				.andExpect(status().isOk()).andReturn();
		
		assertFalse(result.getResponse().getContentAsString().contains("Pricelist deleted."));
		
	}
}
