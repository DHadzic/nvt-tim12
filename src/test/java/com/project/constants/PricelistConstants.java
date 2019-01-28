package com.project.constants;

import java.util.ArrayList;

import com.project.web.dto.PricelistItemDTO;

public class PricelistConstants {

	public static final ArrayList<PricelistItemDTO> PRICELIST_ITEMS_DTO = PricelistConstants.createPlItemsDTOList();
	
	public static final String PRICELISTITEM_TRANS_TYPE_GOOD = "TROLLEYBUS";
	public static final String PRICELISTITEM_TICKET_TYPE_GOOD = "YEARLY";
	public static final double PRICELISTITEM_PRICE_GOOD = 6040;
	public static final String PRICELISTITEM_TRANS_TYPE_INVALID = "BIKE";
	public static final String PRICELISTITEM_TICKET_TYPE_INVALID = "TWO_DAYS";
	public static final String PRICELISTITEM_TICKET_TYPE_TAKEN = "MONTHLY";
	public static final double PRICELISTITEM_PRICE_NEGATIVE = -6040;
	public static final double PRICELISTITEM_PRICE_TOO_HIGH = 123456;
	
	public static ArrayList<PricelistItemDTO> createPlItemsDTOList() {
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
