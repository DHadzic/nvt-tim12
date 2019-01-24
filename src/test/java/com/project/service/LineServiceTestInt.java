package com.project.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.project.constants.BusStopConstants;
import com.project.domain.BusStation;
import com.project.exceptions.EntityAlreadyExistsException;
import com.project.exceptions.InvalidDataException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
@Transactional
public class LineServiceTestInt {

	@Autowired
	private LineService lineService;

	@Test
	public void addStationNullDataSent(){
		BusStation bs = null;
		try {
			lineService.addStation(bs);
		} catch (InvalidDataException | EntityAlreadyExistsException e) {
			assertEquals("Data is null",e.getMessage());
		}
	}

	@Test
	public void addStationNullLatSent() {
		try {
			BusStation bs = new BusStation();
			bs.setLat(null);
			bs.setLng(BusStopConstants.NEW_LNG_FIRST_AREA);
			lineService.addStation(bs);
			assertTrue(false);
		} catch (InvalidDataException | EntityAlreadyExistsException e) {
			assertEquals("Lat is null",e.getMessage());
		}
	}

	@Test
	public void addStationNullLngSent() {
		try {
			BusStation bs = new BusStation(BusStopConstants.NEW_LAT_FIRST_AREA,null);
			bs.setLat(BusStopConstants.NEW_LAT_FIRST_AREA);
			bs.setLng(null);
			lineService.addStation(bs);
			assertTrue(false);
		} catch (InvalidDataException | EntityAlreadyExistsException e) {
			assertEquals("Lng is null",e.getMessage());
		}
	}

	@Test
	public void addStationSameLngLat() {
		try {
			BusStation bs = new BusStation();
			bs.setLat(BusStopConstants.NEW_LAT_TAKEN);
			bs.setLng(BusStopConstants.NEW_LNG_TAKEN);
			lineService.addStation(bs);
			assertTrue(false);
		} catch (InvalidDataException | EntityAlreadyExistsException e) {
			assertEquals("Station already exists",e.getMessage());
		}
	}

	@Test
	public void addStationLatNotParsable() {
		try {
			BusStation bs = new BusStation();
			bs.setLat(BusStopConstants.NEW_LAT_NAN);
			bs.setLng(BusStopConstants.NEW_LNG_NAN);
			lineService.addStation(bs);
			assertTrue(false);
		} catch (InvalidDataException | EntityAlreadyExistsException e) {
			assertEquals("Coords not numbers",e.getMessage());
		}
	}

	@Test
	public void addStationLngNotParsable() {
		try {
			BusStation bs = new BusStation();
			bs.setLat(BusStopConstants.NEW_LAT_FIRST_AREA);
			bs.setLng(BusStopConstants.NEW_LNG_NAN);
			lineService.addStation(bs);
			assertTrue(false);
		} catch (InvalidDataException | EntityAlreadyExistsException e) {
			assertEquals("Coords not numbers",e.getMessage());
		}
	}
	

	@Test
	public void addStationLatOutOfBounds1() {
		try {
			BusStation bs = new BusStation();
			bs.setLat(BusStopConstants.NEW_LAT_OOB1);
			bs.setLng(BusStopConstants.NEW_LNG_FIRST_AREA);
			lineService.addStation(bs);
			assertTrue(false);
		} catch (InvalidDataException | EntityAlreadyExistsException e) {
			assertEquals("Point not in the area",e.getMessage());
		}
	}

	@Test
	public void addStationLatOutOfBounds2() {
		try {
			BusStation bs = new BusStation();
			bs.setLat(BusStopConstants.NEW_LAT_OOB2);
			bs.setLng(BusStopConstants.NEW_LNG_FIRST_AREA);
			lineService.addStation(bs);
			assertTrue(false);
		} catch (InvalidDataException | EntityAlreadyExistsException e) {
			assertEquals("Point not in the area",e.getMessage());
		}
	}

	@Test
	public void addStationLngOutOfBounds1() {
		try {
			BusStation bs = new BusStation();
			bs.setLat(BusStopConstants.NEW_LAT_FIRST_AREA);
			bs.setLng(BusStopConstants.NEW_LNG_OOB1);
			lineService.addStation(bs);
			assertTrue(false);
		} catch (InvalidDataException | EntityAlreadyExistsException e) {
			assertEquals("Point not in the area",e.getMessage());
		}
	}

	@Test
	public void addStationLngOutOfBounds2() {
		try {
			BusStation bs = new BusStation();
			bs.setLat(BusStopConstants.NEW_LAT_FIRST_AREA);
			bs.setLng(BusStopConstants.NEW_LNG_OOB2);
			lineService.addStation(bs);
			assertTrue(false);
		} catch (InvalidDataException | EntityAlreadyExistsException e) {
			assertEquals("Point not in the area",e.getMessage());
		}
	}

	@Test
	public void addStationLatOutOfBounds3() {
		try {
			BusStation bs = new BusStation();
			bs.setLat(BusStopConstants.NEW_LAT_OOB3);
			bs.setLng(BusStopConstants.NEW_LNG_SECOND_AREA);
			lineService.addStation(bs);
			assertTrue(false);
		} catch (InvalidDataException | EntityAlreadyExistsException e) {
			assertEquals("Point not in the area",e.getMessage());
		}
	}

	@Test
	public void addStationLatOutOfBounds4() {
		try {
			BusStation bs = new BusStation();
			bs.setLat(BusStopConstants.NEW_LAT_OOB4);
			bs.setLng(BusStopConstants.NEW_LNG_SECOND_AREA);
			lineService.addStation(bs);
			assertTrue(false);
		} catch (InvalidDataException | EntityAlreadyExistsException e) {
			assertEquals("Point not in the area",e.getMessage());
		}
	}

	@Test
	public void addStationLngOutOfBounds3() {
		try {
			BusStation bs = new BusStation();
			bs.setLat(BusStopConstants.NEW_LAT_SECOND_AREA);
			bs.setLng(BusStopConstants.NEW_LNG_OOB3);
			lineService.addStation(bs);
			assertTrue(false);
		} catch (InvalidDataException | EntityAlreadyExistsException e) {
			assertEquals("Point not in the area",e.getMessage());
		}
	}

	@Test
	public void addStationLngOutOfBounds4() {
		try {
			BusStation bs = new BusStation();
			bs.setLat(BusStopConstants.NEW_LAT_SECOND_AREA);
			bs.setLng(BusStopConstants.NEW_LNG_OOB4);
			lineService.addStation(bs);
			assertTrue(false);
		} catch (InvalidDataException | EntityAlreadyExistsException e) {
			assertEquals("Point not in the area",e.getMessage());
		}
	}
    
    @Test
    @Rollback
	public void addStationInsideFirstArea() {
		try {
			int size_before = lineService.getStations().size();
			BusStation bs = new BusStation();
			bs.setLat(BusStopConstants.NEW_LAT_FIRST_AREA);
			bs.setLng(BusStopConstants.NEW_LNG_FIRST_AREA);
			lineService.addStation(bs);
			int size_after = lineService.getStations().size();
			assertEquals(size_before + 1,size_after);
		} catch (InvalidDataException | EntityAlreadyExistsException e) {
			assertTrue(false);
		}
	}
	
    @Test
    @Rollback
    public void addStationInsideSecondArea() {
		try {
			int size_before = lineService.getStations().size();
			BusStation bs = new BusStation();
			bs.setLat(BusStopConstants.NEW_LAT_SECOND_AREA);
			bs.setLng(BusStopConstants.NEW_LNG_SECOND_AREA);
			lineService.addStation(bs);
			int size_after = lineService.getStations().size();
			assertEquals(size_before + 1,size_after);
		} catch (InvalidDataException | EntityAlreadyExistsException e) {
			assertTrue(false);
		}
	}
    
    @Test
    public void getStationsExpectedSize() {
    	int stations_number = lineService.getStations().size();
    	
    	assertEquals(BusStopConstants.DB_SIZE,stations_number);
    }
	/*
	@Test
    public void addLineNullDataSent() {
		try {
	    	lineService.addLine(null);
		} catch (InvalidDataException | EntityDoesNotExistException e) {
			assertEquals("Data is null",e.getMessage());
		}
    }

	@Test
    public void addLineNameNull(){
		try {
	    	LineDTO line = new LineDTO();
	    	lineService.addLine(line);
		} catch (InvalidDataException | EntityDoesNotExistException e) {
			assertEquals("Name is null",e.getMessage());
		}
    }

	@Test
    public void addLineStationsNull() {
		try {
	    	LineDTO line = new LineDTO();
	    	line.setName("8a");
	    	lineService.addLine(line);
		} catch (InvalidDataException | EntityDoesNotExistException e) {
			assertEquals("Stations are null",e.getMessage());
		}
    }

	@Test
    public void addLineNameTaken() {
		try {
	    	LineDTO line = new LineDTO();
	    	line.setName("taken");
	    	line.setStations(new ArrayList<BusStation>());
	    	lineService.addLine(line);
		} catch (InvalidDataException | EntityDoesNotExistException e) {
			assertEquals("Line name taken",e.getMessage());
		}
    }

	@Test
    public void addLineStationsNrLow1() {
		try {
	    	LineDTO line = new LineDTO();
	    	line.setName("8a");
	    	line.setStations(new ArrayList<BusStation>());
	    	lineService.addLine(line);
		} catch (InvalidDataException | EntityDoesNotExistException e) {
			assertEquals("Need atleast 2 stations",e.getMessage());
		}
    }

	@Test
    public void addLineStationsNrLow2() {
		try {
	    	LineDTO line = new LineDTO();
	    	line.setName("8a");
	    	line.setStations(new ArrayList<BusStation>());
	    	line.getStations().add(null);
	    	lineService.addLine(line);
		} catch (InvalidDataException | EntityDoesNotExistException e) {
			assertEquals("Need atleast 2 stations",e.getMessage());
		}
    }

	@Test
    public void addLineStationIsNull() {
		try {
	    	LineDTO line = new LineDTO();
	    	line.setName("8a");
	    	line.setStations(new ArrayList<BusStation>());
	    	line.getStations().add(null);
	    	line.getStations().add(null);
	    	lineService.addLine(line);
		} catch (InvalidDataException | EntityDoesNotExistException e) {
			assertEquals("Station is null",e.getMessage());
		}
    }

	@Test
    public void addLineStationLatIsNull() {
		try {
	    	LineDTO line = new LineDTO();
	    	line.setName("8a");
	    	line.setStations(new ArrayList<BusStation>());
	    	line.getStations().add(new BusStation());
	    	line.getStations().add(null);
	    	lineService.addLine(line);
		} catch (InvalidDataException | EntityDoesNotExistException e) {
			assertEquals("Station lat is null",e.getMessage());
		}
    }

	@Test
    public void addLineStationLngIsNull() {
		try {
	    	LineDTO line = new LineDTO();
	    	line.setName("8a");
	    	line.setStations(new ArrayList<BusStation>());
	    	line.getStations().add(new BusStation("123", null));
	    	line.getStations().add(null);
	    	lineService.addLine(line);
		} catch (InvalidDataException | EntityDoesNotExistException e) {
			assertEquals("Station lng is null",e.getMessage());
		}
    }

	@Test
    public void addLineStationNotFound1() {
		try {
	    	LineDTO line = new LineDTO();
	    	line.setName("8a");
	    	line.setStations(new ArrayList<BusStation>());
	    	line.getStations().add(new BusStation("1234", "1234"));
	    	line.getStations().add(new BusStation("1234","1234"));
	    	lineService.addLine(line);
		} catch (InvalidDataException | EntityDoesNotExistException e) {
			assertEquals("Station not in a system",e.getMessage());
		}
    }

	@Test
    public void addLineStationNotFound2() {
		try {
	    	LineDTO line = new LineDTO();
	    	line.setName("8a");
	    	line.setStations(new ArrayList<BusStation>());
	    	line.getStations().add(new BusStation("12345", "12345"));
	    	line.getStations().add(new BusStation("12345","12345"));
	    	lineService.addLine(line);
		} catch (InvalidDataException | EntityDoesNotExistException e) {
			assertEquals("Station not in a system",e.getMessage());
		}
    }

	@Test
    public void addLineStationNotUnique() {
		try {
	    	LineDTO line = new LineDTO();
	    	line.setName("8a");
	    	line.setStations(new ArrayList<BusStation>());
	    	line.getStations().add(new BusStation("123", "123"));
	    	line.getStations().add(new BusStation("123","123"));
	    	lineService.addLine(line);
		} catch (InvalidDataException | EntityDoesNotExistException e) {
			assertEquals("Uqinue bus stations required",e.getMessage());
		}
    }

	@Test
    public void addLineAllGood() {
		try {
	    	LineDTO line = new LineDTO();
	    	line.setName("8a");
	    	line.setStations(new ArrayList<BusStation>());
	    	line.getStations().add(new BusStation("123", "123"));
	    	line.getStations().add(new BusStation("12","12"));
	    	lineService.addLine(line);
	    	assertTrue(true);
		} catch (InvalidDataException | EntityDoesNotExistException e) {
			assertTrue(false);
		}
    }
	*/
}
