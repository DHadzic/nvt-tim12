package com.project.constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.assertj.core.util.Arrays;

import com.project.domain.BusStation;

public class LineConstants {
    public static final String NEW_NAME_TAKEN = "taken_line";
    public static final String NEW_NAME1 = "100a";
    public static final String NEW_NAME2 = "100b";
    public static final String NEW_NAME_FOR_INVALID = "8aa";
    public static final ArrayList<BusStation> NEW_NOT_ENOUGH_STATIONS0 = new ArrayList<BusStation>();
    public static final ArrayList<BusStation> NEW_NOT_ENOUGH_STATIONS1 = new ArrayList<BusStation>() {{
    	add(new BusStation());
    }};
    public static final ArrayList<BusStation> NEW_STATIONS = new ArrayList<BusStation>() {{
    			add(new BusStation("45.264054514190796","19.83022916394043"));
    			add(new BusStation("45.26042973161276","19.832632423217774"));
    }};
    public static final ArrayList<BusStation> NEW_STATIONS_ONE_NULL = new ArrayList<BusStation>() {{
    			add(null);
    			add(new BusStation("45.26042973161276","19.832632423217774"));
    }};
    public static final ArrayList<BusStation> NEW_STATIONS_LAT_NULL = new ArrayList<BusStation>() {{
		add(new BusStation(null,"19.83022916394043"));
		add(new BusStation("45.26042973161276","19.832632423217774"));
    }};
    public static final ArrayList<BusStation> NEW_STATIONS_LNG_NULL = new ArrayList<BusStation>() {{
		add(new BusStation("45.26042973161276",null));
		add(new BusStation("45.26042973161276","19.832632423217774"));
    }};
    public static final ArrayList<BusStation> NEW_STATIONS_LAT_INVALID = new ArrayList<BusStation>() {{
    			add(new BusStation("45.26242973161276","19.83022916394043"));
    			add(new BusStation("45.26042973161276","19.832632423217774"));
    }};
    public static final ArrayList<BusStation> NEW_STATIONS_LNG_INVALID = new ArrayList<BusStation>() {{
    			add(new BusStation("45.264054514190796","19.83122916394043"));
    			add(new BusStation("45.26042973161276","19.832632423217774"));
    }};
    public static final ArrayList<BusStation> NEW_STATIONS_NOT_UNIQUE = new ArrayList<BusStation>() {{
		add(new BusStation("45.26042973161276","19.832632423217774"));
		add(new BusStation("45.26042973161276","19.832632423217774"));
    }};
    
    public static final int DB_SIZE = 1;
    public static final Long DELETE_ID = 40l;
    public static final Long DELETE_ID2 = 41l;
    public static final Long DELETE_ID_WRONG = 39l;

}
