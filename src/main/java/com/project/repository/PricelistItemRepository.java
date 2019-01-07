package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.Pricelist;
import com.project.domain.PricelistItem;
import com.project.domain.TicketType;

public interface PricelistItemRepository  extends  JpaRepository<PricelistItem, Long> {
	PricelistItem findByPricelistAndTicketType(Pricelist pl, TicketType tt);
//	PricelistItem findByPricelist(Pricelist pl);
//	PricelistItem findByTicketType(TicketType tt);
}
