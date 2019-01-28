package com.project.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.domain.BusStation;
import com.project.domain.Line;
import com.project.domain.TransportType;
import com.project.domain.Vehicle;
import com.project.exceptions.EntityDoesNotExistException;
import com.project.repository.LineRepository;
import com.project.repository.VehicleRepository;
import com.project.web.dto.LineInfo;
import com.project.web.dto.LinesPerTypeDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class VehicleServiceTest {

	@Autowired
	private AddVehicleServiceImpl vehicleService;
	
	@MockBean
	private LineRepository lineRepository;
	
	@MockBean
	private VehicleRepository vehicleRepository;

	@Before
	public void setUp() {
		Line line = new Line();
		line.setName("8a");
		line.setStations(new ArrayList<BusStation>());
		line.setId(20l);
		
		Vehicle v1 = new Vehicle();
		v1.setLine(line);
		v1.setAtStation(0);
		
		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
		vehicles.add(v1);
		
		Mockito.when(vehicleRepository.findByType(TransportType.BUS)).thenReturn(vehicles);
		Mockito.when(vehicleRepository.findByType(TransportType.TRAM)).thenReturn(vehicles);
		Mockito.when(vehicleRepository.findByType(TransportType.TROLLEYBUS)).thenReturn(vehicles);
		Mockito.when(lineRepository.findByName("8a")).thenReturn(line);
		Mockito.when(lineRepository.findByName("8aa")).thenReturn(null);
	}
	
	@Test
	public void getLinesPerTypeTest() {
		LinesPerTypeDTO lpt = vehicleService.getLinesPerType();
		
		assertTrue(lpt.getBusLines().size() == 1);
		assertTrue(lpt.getTramLines().size() == 1);
		assertTrue(lpt.getTrolleybusLines().size() == 1);
		
		assertEquals("8a",lpt.getBusLines().get(0));
		assertEquals("8a",lpt.getTramLines().get(0));
		assertEquals("8a",lpt.getTrolleybusLines().get(0));
	}
	
	@Test
	public void getLineInfoBus() throws EntityDoesNotExistException {
		LineInfo lineInfo = vehicleService.getLineInfo(TransportType.BUS, "8a");
		
		assertTrue(lineInfo.getAtStations().size() == 1);
		assertEquals("8a",lineInfo.getLine().getName());
		assertEquals(new Long(20l),lineInfo.getLine().getId());
	}

	@Test
	public void getLineInfoTram() throws EntityDoesNotExistException {
		LineInfo lineInfo = vehicleService.getLineInfo(TransportType.TRAM, "8a");
		
		assertTrue(lineInfo.getAtStations().size() == 1);
		assertEquals("8a",lineInfo.getLine().getName());
		assertEquals(new Long(20l),lineInfo.getLine().getId());
	}

	@Test
	public void getLineInfoTrolleybus() throws EntityDoesNotExistException {
		LineInfo lineInfo = vehicleService.getLineInfo(TransportType.TROLLEYBUS, "8a");
		
		assertTrue(lineInfo.getAtStations().size() == 1);
		assertEquals("8a",lineInfo.getLine().getName());
		assertEquals(new Long(20l),lineInfo.getLine().getId());
	}

	@Test(expected = EntityDoesNotExistException.class)
	public void getLineInfoBusBad() throws EntityDoesNotExistException {
		LineInfo lineInfo = vehicleService.getLineInfo(TransportType.BUS, "8aa");
		
		assertTrue(lineInfo.getAtStations().size() == 1);
		assertEquals("8a",lineInfo.getLine().getName());
		assertEquals(new Long(20l),lineInfo.getLine().getId());
	}
}
