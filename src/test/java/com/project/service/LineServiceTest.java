package com.project.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.domain.Authority;
import com.project.domain.BusStation;
import com.project.domain.Line;
import com.project.exceptions.EntityAlreadyExistsException;
import com.project.exceptions.EntityDoesNotExistException;
import com.project.exceptions.InvalidDataException;
import com.project.repository.BusStationRepository;
import com.project.repository.LineRepository;
import com.project.web.dto.LineDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class LineServiceTest {

	@Autowired
	private LineService lineService;
	
	@MockBean
	private LineRepository lineRepository;
	
	@MockBean
	private BusStationRepository bsRepository;
	
	@Before
	public void setUp() {
		Authority auth = new Authority();
		auth.setUserAuthorities(new HashSet<>());
		
		Mockito.when(bsRepository.findByLat("123")).thenReturn(new BusStation("123","123"));
		Mockito.when(bsRepository.findByLat("NOT_PARSABALE")).thenReturn(null);
		Mockito.when(bsRepository.findByLat("1234")).thenReturn(null);
		Mockito.when(bsRepository.findByLat("12345")).thenReturn(new BusStation("12345","123"));
		Mockito.when(bsRepository.findByLat("12")).thenReturn(new BusStation("12","12"));
		Mockito.when(lineRepository.findByName("8a")).thenReturn(null);
		Mockito.when(lineRepository.findByName("taken")).thenReturn(new Line());
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
			lineService.addStation(bs);
		} catch (InvalidDataException | EntityAlreadyExistsException e) {
			assertEquals("Lat is null",e.getMessage());
		}
	}

	@Test
	public void addStationNullLngSent() {
		try {
			BusStation bs = new BusStation("123",null);
			lineService.addStation(bs);
		} catch (InvalidDataException | EntityAlreadyExistsException e) {
			assertEquals("Lng is null",e.getMessage());
		}
	}

	@Test
	public void addStationSameLngLat() {
		try {
			BusStation bs = new BusStation("123","123");
			lineService.addStation(bs);
		} catch (InvalidDataException | EntityAlreadyExistsException e) {
			assertEquals("Station already exists",e.getMessage());
		}
	}

	@Test
	public void addStationLatNotParsable() {
		try {
			BusStation bs = new BusStation("NOT_PARSABLE","123");
			lineService.addStation(bs);
		} catch (InvalidDataException | EntityAlreadyExistsException e) {
			assertEquals("Coords not numbers",e.getMessage());
		}
	}

	@Test
	public void addStationLngNotParsable() {
		try {
			BusStation bs = new BusStation("123","NOT_PARSABLE");
			lineService.addStation(bs);
		} catch (InvalidDataException | EntityAlreadyExistsException e) {
			assertEquals("Coords not numbers",e.getMessage());
		}
	}
	

	@Test
	public void addStationLatOutOfBounds1() {
		try {
			BusStation bs = new BusStation("45.171652105740415","19.82769419806313");
			lineService.addStation(bs);
		} catch (InvalidDataException | EntityAlreadyExistsException e) {
			assertEquals("Point not in the area",e.getMessage());
		}
	}

	@Test
	public void addStationLatOutOfBounds2() {
		try {
			BusStation bs = new BusStation("45.371652105740415","19.82769419806313");
			lineService.addStation(bs);
		} catch (InvalidDataException | EntityAlreadyExistsException e) {
			assertEquals("Point not in the area",e.getMessage());
		}
	}

	@Test
	public void addStationLngOutOfBounds1() {
		try {
			BusStation bs = new BusStation("45.291652105740415","19.72769419806313");
			lineService.addStation(bs);
		} catch (InvalidDataException | EntityAlreadyExistsException e) {
			assertEquals("Point not in the area",e.getMessage());
		}
	}

	@Test
	public void addStationLngOutOfBounds2() {
		try {
			BusStation bs = new BusStation("45.291652105740415","19.92769419806313");
			lineService.addStation(bs);
		} catch (InvalidDataException | EntityAlreadyExistsException e) {
			assertEquals("Point not in the area",e.getMessage());
		}
	}

	@Test
	public void addStationLatOutOfBounds3() {
		try {
			BusStation bs = new BusStation("45.12028630783431","19.795163978393484");
			lineService.addStation(bs);
		} catch (InvalidDataException | EntityAlreadyExistsException e) {
			assertEquals("Point not in the area",e.getMessage());
		}
	}

	@Test
	public void addStationLatOutOfBounds4() {
		try {
			BusStation bs = new BusStation("45.28028630783431","19.795163978393484");
			lineService.addStation(bs);
		} catch (InvalidDataException | EntityAlreadyExistsException e) {
			assertEquals("Point not in the area",e.getMessage());
		}
	}

	@Test
	public void addStationLngOutOfBounds3() {
		try {
		BusStation bs = new BusStation("45.23028630783431","19.765163978393484");
		lineService.addStation(bs);
		} catch (InvalidDataException | EntityAlreadyExistsException e) {
			assertEquals("Point not in the area",e.getMessage());
		}
	}

	@Test
	public void addStationLngOutOfBounds4() {
		try {
			BusStation bs = new BusStation("45.23028630783431","19.985163978393484");
			lineService.addStation(bs);
		} catch (InvalidDataException | EntityAlreadyExistsException e) {
			assertEquals("Point not in the area",e.getMessage());
		}
	}
    
    @Test
	public void addStationInsideFirstArea() {
		try {
			BusStation bs = new BusStation("45.291652105740415","19.836163978393484");
			lineService.addStation(bs);
			assertTrue(true);
		} catch (InvalidDataException | EntityAlreadyExistsException e) {
			assertTrue(false);
		}
	}
	
    @Test
    public void addStationInsideSecondArea() {
		try {
			BusStation bs = new BusStation("45.23028630783431","19.786163978393484");
			lineService.addStation(bs);
			assertTrue(true);
		} catch (InvalidDataException | EntityAlreadyExistsException e) {
			assertTrue(false);
		}
	}
	
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
			assertEquals("Unique bus stations required",e.getMessage());
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
}
