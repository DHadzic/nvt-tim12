package com.project.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.domain.Pricelist;
import com.project.domain.PricelistItem;
import com.project.domain.Ticket;
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
	
	public Pricelist createPricelist(){
		Pricelist pl = new Pricelist();
		Pricelist old_pl = pricelistRepository.findTopByOrderByIdDesc();
		if (old_pl != null){
			old_pl.setDate_invalidated(new Date());
			pricelistRepository.save(old_pl);
		}
		pricelistRepository.save(pl);
		return pl;
	}
	
	public void createPricelistItem(Pricelist pl, PricelistItemDTO pliDTO){
		PricelistItem pli = new PricelistItem(pl, pliDTO);
		pricelistItemRepository.save(pli);
	}
	
	public void createPricelistItems(Pricelist pl, ArrayList<PricelistItemDTO> plItemsDTO){
		ArrayList<PricelistItem> plItems = new ArrayList<PricelistItem>();
		for (PricelistItemDTO plDTO : plItemsDTO){
			plItems.add(new PricelistItem(pl, plDTO));
		}
		pricelistItemRepository.saveAll(plItems);
	}
	
	public ArrayList<Pricelist> getPricelists(){
		ArrayList<Pricelist> pricelists = (ArrayList<Pricelist>) pricelistRepository.findAll();
		return pricelists;
	}
	
	public void deletePricelist(Long pricelistId) throws NoSuchElementException, InvalidDataException{
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
		return true;
	}
}
