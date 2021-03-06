package com.project.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

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
import com.project.constants.LineConstants;
import com.project.domain.BusStation;
import com.project.domain.Line;
import com.project.exceptions.EntityAlreadyExistsException;
import com.project.exceptions.EntityDoesNotExistException;
import com.project.exceptions.InvalidDataException;
import com.project.web.dto.LineDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
@Transactional
public class LineServiceTestInt {

	@Autowired
	private LineService lineService;

	// Rollback ne radi ispravno, ne mogu da namestim
	@Test
	public void getLines() {
		assertTrue(lineService.getLines().size() >= LineConstants.DB_SIZE);
	}

	@Test
	public void getStations() {
		assertTrue(lineService.getStations().size() >= BusStopConstants.DB_SIZE);
	}

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
			bs.setLng(BusStopConstants.NEW_LNG_FIRST_AREA1);
			lineService.addStation(bs);
			assertTrue(false);
		} catch (InvalidDataException | EntityAlreadyExistsException e) {
			assertEquals("Lat is null",e.getMessage());
		}
	}

	@Test
	public void addStationNullLngSent() {
		try {
			BusStation bs = new BusStation(BusStopConstants.NEW_LAT_FIRST_AREA1,null);
			bs.setLat(BusStopConstants.NEW_LAT_FIRST_AREA1);
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
			bs.setLat(BusStopConstants.NEW_LAT_FIRST_AREA1);
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
			bs.setLng(BusStopConstants.NEW_LNG_FIRST_AREA1);
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
			bs.setLng(BusStopConstants.NEW_LNG_FIRST_AREA1);
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
			bs.setLat(BusStopConstants.NEW_LAT_FIRST_AREA1);
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
			bs.setLat(BusStopConstants.NEW_LAT_FIRST_AREA1);
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
			bs.setLng(BusStopConstants.NEW_LNG_SECOND_AREA1);
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
			bs.setLng(BusStopConstants.NEW_LNG_SECOND_AREA1);
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
			bs.setLat(BusStopConstants.NEW_LAT_SECOND_AREA1);
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
			bs.setLat(BusStopConstants.NEW_LAT_SECOND_AREA1);
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
			bs.setLat(BusStopConstants.NEW_LAT_FIRST_AREA1);
			bs.setLng(BusStopConstants.NEW_LNG_FIRST_AREA1);
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
			bs.setLat(BusStopConstants.NEW_LAT_SECOND_AREA1);
			bs.setLng(BusStopConstants.NEW_LNG_SECOND_AREA1);
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
    	
    	assertTrue(BusStopConstants.DB_SIZE < stations_number);
    }

    @Test
    public void addLineNullDataSent() {
		try {
	    	lineService.addLine(null);
	    	assertTrue(false);
		} catch (InvalidDataException | EntityDoesNotExistException e) {
			assertEquals("Data is null",e.getMessage());
		}
    }

	@Test
    public void addLineNameNull(){
		try {
	    	LineDTO line = new LineDTO();
	    	lineService.addLine(line);
	    	assertTrue(false);
		} catch (InvalidDataException | EntityDoesNotExistException e) {
			assertEquals("Name is null",e.getMessage());
		}
    }

	@Test
    public void addLineStationsNull() {
		try {
	    	LineDTO line = new LineDTO();
	    	line.setName(LineConstants.NEW_NAME_FOR_INVALID);
	    	lineService.addLine(line);
	    	assertTrue(false);
		} catch (InvalidDataException | EntityDoesNotExistException e) {
			assertEquals("Stations are null",e.getMessage());
		}
    }

	@Test
    public void addLineNameTaken() {
		try {
	    	LineDTO line = new LineDTO();
	    	line.setName(LineConstants.NEW_NAME_TAKEN);
	    	line.setStations(LineConstants.NEW_NOT_ENOUGH_STATIONS0);
	    	lineService.addLine(line);
	    	assertTrue(false);
		} catch (InvalidDataException | EntityDoesNotExistException e) {
			assertEquals("Line name taken",e.getMessage());
		}
    }

	@Test
    public void addLineStationsNrLow1() {
		try {
	    	LineDTO line = new LineDTO();
	    	line.setName(LineConstants.NEW_NAME_FOR_INVALID);
	    	line.setStations(LineConstants.NEW_NOT_ENOUGH_STATIONS0);
	    	lineService.addLine(line);
	    	assertTrue(false);
		} catch (InvalidDataException | EntityDoesNotExistException e) {
			assertEquals("Need atleast 2 stations",e.getMessage());
		}
    }

	@Test
    public void addLineStationsNrLow2() {
		try {
	    	LineDTO line = new LineDTO();
	    	line.setName(LineConstants.NEW_NAME_FOR_INVALID);
	    	line.setStations(LineConstants.NEW_NOT_ENOUGH_STATIONS1);
	    	lineService.addLine(line);
	    	assertTrue(false);
		} catch (InvalidDataException | EntityDoesNotExistException e) {
			assertEquals("Need atleast 2 stations",e.getMessage());
		}
    }

	@Test
    public void addLineStationIsNull() {
		try {
	    	LineDTO line = new LineDTO();
	    	line.setName(LineConstants.NEW_NAME_FOR_INVALID);
	    	line.setStations(LineConstants.NEW_STATIONS_ONE_NULL);
	    	lineService.addLine(line);
	    	assertTrue(false);
		} catch (InvalidDataException | EntityDoesNotExistException e) {
			assertEquals("Station is null",e.getMessage());
		}
    }

	@Test
    public void addLineStationLatIsNull() {
		try {
	    	LineDTO line = new LineDTO();
	    	line.setName(LineConstants.NEW_NAME_FOR_INVALID);
	    	line.setStations(LineConstants.NEW_STATIONS_LAT_NULL);
	    	lineService.addLine(line);
	    	assertTrue(false);
		} catch (InvalidDataException | EntityDoesNotExistException e) {
			assertEquals("Station lat is null",e.getMessage());
		}
    }

	@Test
    public void addLineStationLngIsNull() {
		try {
	    	LineDTO line = new LineDTO();
	    	line.setName(LineConstants.NEW_NAME_FOR_INVALID);
	    	line.setStations(LineConstants.NEW_STATIONS_LNG_NULL);
	    	lineService.addLine(line);
	    	assertTrue(false);
		} catch (InvalidDataException | EntityDoesNotExistException e) {
			assertEquals("Station lng is null",e.getMessage());
		}
    }

	@Test
    public void addLineStationNotFound1() {
		try {
	    	LineDTO line = new LineDTO();
	    	line.setName(LineConstants.NEW_NAME_FOR_INVALID);
	    	line.setStations(LineConstants.NEW_STATIONS_LAT_INVALID);
	    	lineService.addLine(line);
	    	assertTrue(false);
		} catch (InvalidDataException | EntityDoesNotExistException e) {
			assertEquals("Station not in a system",e.getMessage());
		}
    }

	@Test
    public void addLineStationNotFound2() {
		try {
	    	LineDTO line = new LineDTO();
	    	line.setName(LineConstants.NEW_NAME_FOR_INVALID);
	    	line.setStations(LineConstants.NEW_STATIONS_LNG_INVALID);
	    	lineService.addLine(line);
	    	assertTrue(false);
		} catch (InvalidDataException | EntityDoesNotExistException e) {
			assertEquals("Station not in a system",e.getMessage());
		}
    }

	@Test
    public void addLineStationNotUnique() {
		try {
	    	LineDTO line = new LineDTO();
	    	line.setName(LineConstants.NEW_NAME_FOR_INVALID);
	    	line.setStations(LineConstants.NEW_STATIONS_NOT_UNIQUE);
	    	lineService.addLine(line);
	    	assertTrue(false);
		} catch (InvalidDataException | EntityDoesNotExistException e) {
			assertEquals("Unique bus stations required",e.getMessage());
		}
    }

	@Test
	@Rollback
    public void addLineAllGood() {
		try {
	    	LineDTO line = new LineDTO();
	    	int before = lineService.getLines().size();
	    	line.setName(LineConstants.NEW_NAME2);
	    	line.setStations(LineConstants.NEW_STATIONS);
	    	lineService.addLine(line);
	    	int after = lineService.getLines().size();
	    	assertEquals(after,before+1);
		} catch (InvalidDataException | EntityDoesNotExistException e) {
			assertTrue(false);
		}
    }
	
	@Test(expected = EntityDoesNotExistException.class)
    public void deleteStationNotExist() throws EntityDoesNotExistException {
    	LineDTO line = new LineDTO();
    	lineService.deleteBusStation(LineConstants.DELETE_ID_WRONG);
    }

	@Test
	@Rollback
    public void deleteStationGood() throws EntityDoesNotExistException {
		int before = lineService.getStations().size();
    	LineDTO line = new LineDTO();
    	lineService.deleteBusStation(BusStopConstants.DELETE_ID);
		int after = lineService.getStations().size();
		assertEquals(before - 1,after);
    }

	@Test(expected = EntityDoesNotExistException.class)
    public void deleteLineNotExist() throws EntityDoesNotExistException {
    	LineDTO line = new LineDTO();
    	lineService.deleteLine(LineConstants.DELETE_ID_WRONG);
    }

	@Test
	@Rollback
    public void deleteLineGood() throws EntityDoesNotExistException {
		int before = lineService.getLines().size();
    	LineDTO line = new LineDTO();
    	lineService.deleteLine(LineConstants.DELETE_ID);
		int after = lineService.getLines().size();
		assertEquals(before - 1,after);
    }

	
}
