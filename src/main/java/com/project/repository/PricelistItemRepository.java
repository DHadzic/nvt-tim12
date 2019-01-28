package com.project.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.Pricelist;
import com.project.domain.PricelistItem;
import com.project.domain.TicketType;
import com.project.domain.TransportType;

public interface PricelistItemRepository  extends  JpaRepository<PricelistItem, Long> {
	PricelistItem findByPricelistAndTicketTypeAndTransportType(Pricelist pl, TicketType tt, TransportType transT);
	ArrayList<PricelistItem> findByPricelist(Pricelist pl);
//	PricelistItem findByTicketType(TicketType tt);
}
