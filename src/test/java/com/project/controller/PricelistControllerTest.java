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

	}

	@Test
	public void createPricelistListTooShort() throws Exception{
		ArrayList<PricelistItemDTO> plItemsDTO = PricelistConstants.PRICELIST_ITEMS_DTO;
		String json = TestUtil.json(plItemsDTO);
		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/create").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();
		
		assertFalse(result.getResponse().getContentAsString().contains("Pricelist successfully created."));

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

	}
	
	@Test
	public void createPricelistListIsNull() throws Exception{
		String json = TestUtil.json(null);
		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/create").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();
		
		assertFalse(result.getResponse().getContentAsString().contains("Pricelist successfully created."));

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

	}
	
	@Test
	public void getAllPricelists() throws Exception{
		MvcResult result = mockMvc.perform(get(URL_PREFIX + "/getAll"))
				.andExpect(status().isOk()).andReturn();
		
		assertTrue(result != null);

		
	}
	
	@Test
	@Rollback
	public void reactivatePricelistSuccess() throws Exception{
		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/reactivatePricelist/{id}", "999"))
				.andExpect(status().isOk()).andReturn();
		
		assertTrue(result.getResponse().getContentAsString().contains("Pricelist reactivated"));

	}
	
	@Test
	public void reactivatePricelistNotFound() throws Exception{
		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/reactivatePricelist/{id}", "900"))
				.andExpect(status().isBadRequest()).andReturn();
		
		assertFalse(result.getResponse().getContentAsString().contains("Pricelist reactivated"));

	}
	
	
	@Test
	@Rollback
	public void deletePricelistSuccess() throws Exception{
		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/deletePricelist/{id}", "994"))
				.andExpect(status().isOk()).andReturn();
		
		assertTrue(result.getResponse().getContentAsString().contains("Pricelist deleted."));
		

	}
	
	
	@Test
	public void deletePricelistNotFound() throws Exception{
		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/deletePricelist/{id}", "900"))
				.andExpect(status().isBadRequest()).andReturn();
		
		assertFalse(result.getResponse().getContentAsString().contains("Pricelist deleted."));

	}
	
	@Test
	public void deletePricelistIsActive() throws Exception{
		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/deletePricelist/{id}", "998"))
				.andExpect(status().isOk()).andReturn();
		
		assertFalse(result.getResponse().getContentAsString().contains("Pricelist deleted."));
	}
	
	@Test
	public void deletePricelistActiveTicketsUsingIt() throws Exception{
		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/deletePricelist/{id}", "997"))
				.andExpect(status().isOk()).andReturn();
		
		assertFalse(result.getResponse().getContentAsString().contains("Pricelist deleted."));
		
	}
}
