package com.project.controller;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.Pricelist;
import com.project.domain.PricelistItem;
import com.project.exceptions.EntityDoesNotExistException;
import com.project.exceptions.InvalidDataException;
import com.project.service.PricelistService;
import com.project.web.dto.PricelistItemDTO;

@RestController
@RequestMapping(value = "/pricelist")
public class PricelistController {
	
	@Autowired
	private PricelistService pricelistService;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<String> createPricelist(@RequestBody ArrayList<PricelistItemDTO> plItems){
		try {
			pricelistService.createPricelistAndPricelistItems(plItems);
			return new ResponseEntity<String>("Pricelist successfully created.", HttpStatus.OK);
		} catch (InvalidDataException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Pricelist>> getPricelists() {
		ArrayList<Pricelist> pricelists;
		try {
			pricelists = pricelistService.getPricelists();
			return new ResponseEntity<ArrayList<Pricelist>>(pricelists, HttpStatus.OK);
		} catch (EntityDoesNotExistException e) {
			return new ResponseEntity<ArrayList<Pricelist>>(new ArrayList<Pricelist>(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(value = "/getPrices", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<PricelistItemDTO>> getPrices(){
		ArrayList<PricelistItem> prices = pricelistService.getPrices();
		ArrayList<PricelistItemDTO> pricesToReturn = new ArrayList<PricelistItemDTO>();
		for (PricelistItem pli : prices){
			PricelistItemDTO pliDTO = new PricelistItemDTO(pli.getTicketType().toString(), pli.getTransportType().toString(), pli.getPrice());
			pricesToReturn.add(pliDTO);
		}
		return new ResponseEntity<ArrayList<PricelistItemDTO>>(pricesToReturn, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deletePricelist", method = RequestMethod.POST)
	public ResponseEntity<String> deletePricelist(Long pricelistId){
		try{
			pricelistService.deletePricelist(pricelistId);
			return new ResponseEntity<String>("Pricelist deleted.", HttpStatus.OK);
		}catch (NoSuchElementException nsee){
			return new ResponseEntity<String>("Pricelist not found.", HttpStatus.BAD_REQUEST);
		} catch (InvalidDataException ide) {
			return new ResponseEntity<String>(ide.getMessage(), HttpStatus.OK);
		}
	}

}
