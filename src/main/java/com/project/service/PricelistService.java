package com.project.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.project.web.dto.PricelistItemDTO;

@Service
public class PricelistService {

	@Autowired
	private PricelistRepository pricelistRepository;
	
	@Autowired
	private PricelistItemRepository pricelistItemRepository;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	public void createPricelistAndPricelistItems(ArrayList<PricelistItemDTO> plItemsDTO) throws InvalidDataException{
		if (plItemsDTO == null) throw new InvalidDataException("List is null.");
		Pricelist pl = new Pricelist();
		ArrayList<PricelistItem> plItems = createPricelistItems(pl, plItemsDTO);
		
		ArrayList<Pricelist> activePl = pricelistRepository.findByInvalidatedIsNull();
		if (activePl.size() > 1) throw new InvalidDataException("There is more than one active pricelist.");
		if (activePl.get(0) != null){
			activePl.get(0).setDate_invalidated(new Date());
			pricelistRepository.saveAll(activePl);
		}
		
		pricelistRepository.save(pl);
		pricelistItemRepository.saveAll(plItems);
	}
	
	private ArrayList<PricelistItem> createPricelistItems(Pricelist pl, ArrayList<PricelistItemDTO> plItemsDTO) throws InvalidDataException{
		int requiredNumOfPrices = TicketType.values().length * TransportType.values().length;
		if (plItemsDTO.size() != requiredNumOfPrices) throw new InvalidDataException("Number of pricelist items too small or too big.");
		checkPlItemListItems(plItemsDTO);
		ArrayList<PricelistItem> plItems = new ArrayList<PricelistItem>();
		for (PricelistItemDTO plDTO : plItemsDTO){
			if (plDTO.getPrice() <= 0) throw new InvalidDataException("Price can not be negative value or zero.");
			if (plDTO.getPrice() > 99999) throw new InvalidDataException("Price is too high.");
			plItems.add(new PricelistItem(pl, plDTO));
		}
		return plItems;
	}


	public ArrayList<Pricelist> getPricelists() throws EntityDoesNotExistException{
		ArrayList<Pricelist> pricelists = (ArrayList<Pricelist>) pricelistRepository.findAll();
		if (pricelists.size() == 0) throw new EntityDoesNotExistException("No pricelists found. There must be at least one pricelist.");
		return pricelists;
	}
	
	public void reactivatePricelist(Long plId) throws EntityDoesNotExistException{
		try{
			Pricelist pl = pricelistRepository.findById(plId).get();
			ArrayList<Pricelist> activePl = pricelistRepository.findByInvalidatedIsNull();
//			if (activePl.size() > 10) throw new InvalidDataException("There is more than one active pricelist.");
			Pricelist old_pl = activePl.get(0);
			pl.setDate_reactivated(new Date());
			old_pl.setDate_invalidated(new Date());
			pricelistRepository.save(old_pl);
			pricelistRepository.save(pl);
			
		}catch (NoSuchElementException e){
			throw new EntityDoesNotExistException("Pricelist not found.");
		}
	}
	
	public void deletePricelist(Long pricelistId) throws NoSuchElementException, InvalidDataException{
		if (pricelistId == null) throw new InvalidDataException("Id can not be null.");
		Pricelist pl = pricelistRepository.findById(pricelistId).get();
		if (pl.getDate_invalidated() == null) throw new InvalidDataException("Pricelist is currently active. Can not be deleted.");
		if (checkActiveTickets(pl)){
			pricelistRepository.delete(pl);
		}else{
			throw new InvalidDataException("There are active tickets using this pricelist. Can not be deleted.");
		}
	}
	
	private boolean checkActiveTickets(Pricelist pl){
		ArrayList<Ticket> activeTickets = ticketRepository.findByIsActiveTrue();
		ArrayList<PricelistItem> plItems = pricelistItemRepository.findByPricelist(pl);
		for (PricelistItem pli : plItems){
			for (Ticket t : activeTickets){
				if (t.getPrice() == pli) return false;
			}
		}
		pricelistItemRepository.deleteAll(plItems);
		return true;
	}

	private void checkPlItemListItems(ArrayList<PricelistItemDTO> plItemsDTO) throws InvalidDataException {
		try{
			ArrayList<Integer> types = new ArrayList<Integer>();
			for (PricelistItemDTO pliDTO : plItemsDTO){
				TicketType ticketT = TicketType.valueOf(pliDTO.getTicketType());
				TransportType transportT = TransportType.valueOf(pliDTO.getTransportType());
				if (transportT == TransportType.BUS){
					if (ticketT == TicketType.ONE_TIME){
						types.add(1);
					}else if (ticketT == TicketType.ONE_DAY){
						types.add(2);
					}else if (ticketT == TicketType.MONTHLY){
						types.add(3);
					}else if (ticketT == TicketType.YEARLY){
						types.add(4);
					}
				}else if (transportT == TransportType.TRAM){
					if (ticketT == TicketType.ONE_TIME){
						types.add(5);
					}else if (ticketT == TicketType.ONE_DAY){
						types.add(6);
					}else if (ticketT == TicketType.MONTHLY){
						types.add(7);
					}else if (ticketT == TicketType.YEARLY){
						types.add(8);
					}
				}else if (transportT == TransportType.TROLLEYBUS){
					if (ticketT == TicketType.ONE_TIME){
						types.add(9);
					}else if (ticketT == TicketType.ONE_DAY){
						types.add(10);
					}else if (ticketT == TicketType.MONTHLY){
						types.add(11);
					}else if (ticketT == TicketType.YEARLY){
						types.add(12);
					}
				}
			}
			Set<Integer> set = new HashSet<Integer>(types);
			if(set.size() < types.size()){
			   throw new InvalidDataException("List contains multiple prices for same ticket and transport type.");
			}

		}catch (IllegalArgumentException iae){
			throw new InvalidDataException("Invalid value for Transport type or Ticket type.");
		}catch (NullPointerException npe){
			throw new InvalidDataException("Null can not be Transport type or Ticket type.");
		}
	}
}
