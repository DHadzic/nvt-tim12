package com.project.service;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.domain.Pricelist;
import com.project.domain.PricelistItem;
import com.project.domain.Ticket;
import com.project.exceptions.InvalidDataException;
import com.project.repository.PricelistItemRepository;
import com.project.repository.PricelistRepository;
import com.project.repository.TicketRepository;
import com.project.web.dto.PricelistItemDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class PricelistServiceTest {
	
	@Autowired
	private PricelistService pricelistService;
	
	@MockBean
	private PricelistRepository pricelistRepository;
	
	@MockBean
	private PricelistItemRepository pricelistItemRepository;
	
	@MockBean
	private TicketRepository ticketRepository;
	
	@Before
	public void setUp(){
		Mockito.when(pricelistRepository.findTopByOrderByIdDesc()).thenReturn(null);
		ArrayList<Pricelist> pricelists = new ArrayList<Pricelist>();
		Pricelist pl1 = new Pricelist();
		pl1.setDate_invalidated(new Date());
		Pricelist pl3 = new Pricelist();
		Pricelist pl4 = new Pricelist();
		pricelists.add(pl1);
		pricelists.add(pl3);
		pricelists.add(pl4);
		PricelistItem pli = new PricelistItem();
		pli.setPricelist(pl4);
		ArrayList<PricelistItem> plItems = new ArrayList<PricelistItem>();
		plItems.add(pli);
		Ticket t = new Ticket();
		t.setPrice(pli);
		t.setActive(true);
		ArrayList<Ticket> activeTickets = new ArrayList<Ticket>();
		activeTickets.add(t);
		Mockito.when(pricelistRepository.findById(1l)).thenReturn(Optional.of(pl1));
		Mockito.when(pricelistRepository.findById(2l)).thenThrow(new NoSuchElementException());
		Mockito.when(pricelistRepository.findById(3l)).thenReturn(Optional.of(pl3));
		Mockito.when(pricelistRepository.findById(4l)).thenReturn(Optional.of(pl4));
		Mockito.when(pricelistRepository.findAll()).thenReturn(pricelists);
		Mockito.when(ticketRepository.findByIsActiveTrue()).thenReturn(activeTickets);
		Mockito.when(pricelistItemRepository.findByPricelist(pl4)).thenReturn(plItems);
	}
	
	
	@Test
	public void createPricelistSuccess() throws InvalidDataException{
		ArrayList<PricelistItemDTO> plItemsDTO = createPlItemsDTOList();
		plItemsDTO.add(new PricelistItemDTO("YEARLY", "TROLLEYBUS", 6040));
		pricelistService.createPricelistAndPricelistItems(plItemsDTO);
	}

	@Test(expected = InvalidDataException.class)
	public void createPricelistListTooShort() throws InvalidDataException{
		ArrayList<PricelistItemDTO> plItemsDTO = createPlItemsDTOList();
		pricelistService.createPricelistAndPricelistItems(plItemsDTO);
	}
	
	@Test(expected = InvalidDataException.class)
	public void createPricelistListTooLong() throws InvalidDataException{
		ArrayList<PricelistItemDTO> plItemsDTO = createPlItemsDTOList();
		plItemsDTO.add(new PricelistItemDTO("YEARLY", "TROLLEYBUS", 6040));
		plItemsDTO.add(new PricelistItemDTO("YEARLY", "TROLLEYBUS", 6140));
		pricelistService.createPricelistAndPricelistItems(plItemsDTO);
	}
	
	@Test(expected = InvalidDataException.class)
	public void createPricelistListPriceNegative() throws InvalidDataException{
		ArrayList<PricelistItemDTO> plItemsDTO = createPlItemsDTOList();
		plItemsDTO.add(new PricelistItemDTO("YEARLY", "TROLLEYBUS", -100));
		pricelistService.createPricelistAndPricelistItems(plItemsDTO);
	}
	
	@Test(expected = InvalidDataException.class)
	public void createPricelistListPriceZero() throws InvalidDataException{
		ArrayList<PricelistItemDTO> plItemsDTO = createPlItemsDTOList();
		plItemsDTO.add(new PricelistItemDTO("YEARLY", "TROLLEYBUS", 0));
		pricelistService.createPricelistAndPricelistItems(plItemsDTO);
	}
	
	@Test(expected = InvalidDataException.class)
	public void createPricelistListPriceTooHigh() throws InvalidDataException{
		ArrayList<PricelistItemDTO> plItemsDTO = createPlItemsDTOList();
		plItemsDTO.add(new PricelistItemDTO("YEARLY", "TROLLEYBUS", 100000));
		pricelistService.createPricelistAndPricelistItems(plItemsDTO);
	}
	
	@Test(expected = InvalidDataException.class)
	public void createPricelistListIsNull() throws InvalidDataException{
		pricelistService.createPricelistAndPricelistItems(null);
	}
	
	@Test(expected = InvalidDataException.class)
	public void createPricelistListTicketTypeNull() throws InvalidDataException{
		ArrayList<PricelistItemDTO> plItemsDTO = createPlItemsDTOList();
		plItemsDTO.add(new PricelistItemDTO(null, "TROLLEYBUS", 6040));
		pricelistService.createPricelistAndPricelistItems(plItemsDTO);
	}
	
	@Test(expected = InvalidDataException.class)
	public void createPricelistListTickettTypeInvalid() throws InvalidDataException{
		ArrayList<PricelistItemDTO> plItemsDTO = createPlItemsDTOList();
		plItemsDTO.add(new PricelistItemDTO("TWO_DAYS", "TROLLEYBUS", 6040));
		pricelistService.createPricelistAndPricelistItems(plItemsDTO);
	}
	
	@Test(expected = InvalidDataException.class)
	public void createPricelistListTransportTypeNull() throws InvalidDataException{
		ArrayList<PricelistItemDTO> plItemsDTO = createPlItemsDTOList();
		plItemsDTO.add(new PricelistItemDTO("YEARLY", null, 6040));
		pricelistService.createPricelistAndPricelistItems(plItemsDTO);
	}
	
	@Test(expected = InvalidDataException.class)
	public void createPricelistListTransportTypeInvalid() throws InvalidDataException{
		ArrayList<PricelistItemDTO> plItemsDTO = createPlItemsDTOList();
		plItemsDTO.add(new PricelistItemDTO("YEARLY", "BIKE", 6040));
		pricelistService.createPricelistAndPricelistItems(plItemsDTO);
	}
	
	@Test(expected = InvalidDataException.class)
	public void createPricelistListHasDuplicates() throws InvalidDataException{
		ArrayList<PricelistItemDTO> plItemsDTO = createPlItemsDTOList();
		plItemsDTO.add(new PricelistItemDTO("MONTHLY", "TROLLEYBUS", 6040));
		pricelistService.createPricelistAndPricelistItems(plItemsDTO);
	}
	
	@Test
	public void getAllPricelists(){
		ArrayList<Pricelist> pricelists = pricelistService.getPricelists();
		assertNotNull(pricelists);
	}
	
	
	@Test
	public void deletePricelistSuccess() throws NoSuchElementException, InvalidDataException{
		pricelistService.deletePricelist(1L);
	}
	
	@Test(expected = InvalidDataException.class)
	public void deletePricelistIdIsNull() throws NoSuchElementException, InvalidDataException{
		pricelistService.deletePricelist(null);

	}
	
	@Test(expected = NoSuchElementException.class)
	public void deletePricelistNotFound() throws NoSuchElementException, InvalidDataException{
		pricelistService.deletePricelist(2L);

	}
	
	@Test(expected = InvalidDataException.class)
	public void deletePricelistIsActive() throws NoSuchElementException, InvalidDataException{
		pricelistService.deletePricelist(3l);
	}
	
	@Test(expected = InvalidDataException.class)
	public void deletePricelistActiveTicketsUsingIt() throws NoSuchElementException, InvalidDataException{
		pricelistService.deletePricelist(4L);
	}

	private ArrayList<PricelistItemDTO> createPlItemsDTOList() {
		ArrayList<PricelistItemDTO> items = new ArrayList<PricelistItemDTO>();
		items.add(new PricelistItemDTO("ONE_TIME", "BUS", 40));
		items.add(new PricelistItemDTO("ONE_DAY", "BUS", 140));
		items.add(new PricelistItemDTO("MONTHLY", "BUS", 1040));
		items.add(new PricelistItemDTO("YEARLY", "BUS", 6040));
		items.add(new PricelistItemDTO("ONE_TIME", "TRAM", 40));
		items.add(new PricelistItemDTO("ONE_DAY", "TRAM", 140));
		items.add(new PricelistItemDTO("MONTHLY", "TRAM", 1040));
		items.add(new PricelistItemDTO("YEARLY", "TRAM", 6040));
		items.add(new PricelistItemDTO("ONE_TIME", "TROLLEYBUS", 40));
		items.add(new PricelistItemDTO("ONE_DAY", "TROLLEYBUS", 140));
		items.add(new PricelistItemDTO("MONTHLY", "TROLLEYBUS", 1040));
		return items;
	}
	
}
