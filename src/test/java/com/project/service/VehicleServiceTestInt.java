package com.project.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.project.constants.VehicleConstants;
import com.project.domain.TransportType;
import com.project.exceptions.EntityDoesNotExistException;
import com.project.web.dto.LineInfo;
import com.project.web.dto.LinesPerTypeDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
@Transactional
public class VehicleServiceTestInt {
	@Autowired
	private AddVehicleServiceImpl vehicleService;

	// Zbog rollbacka ,za Suit je potrebno staviti velicinu 1 , za posebne tetove 2
	// Zbog rollbacka ,za Suit je potrebno staviti index 0, za posebne 1
	@Test
	public void getLinesPerTypeTest() {
		LinesPerTypeDTO lpt = vehicleService.getLinesPerType();
		
		assertTrue(lpt.getBusLines().size() == 1);
		assertTrue(lpt.getTramLines().size() == 1);
		assertTrue(lpt.getTrolleybusLines().size() == 1);
		
		assertEquals("line_to_get",lpt.getBusLines().get(0));
		assertEquals("line_to_get",lpt.getTramLines().get(0));
		assertEquals("line_to_get",lpt.getTrolleybusLines().get(0));
	}
	
	@Test
	public void getLineInfoBus() throws EntityDoesNotExistException {
		LineInfo lineInfo = vehicleService.getLineInfo(TransportType.BUS, VehicleConstants.FIND_LINE_NAME);
		
		assertTrue(lineInfo.getAtStations().size() == 1);
		assertEquals(VehicleConstants.FIND_LINE_NAME,lineInfo.getLine().getName());
		assertEquals(new Long(20l),lineInfo.getLine().getId());
	}

	@Test
	public void getLineInfoTram() throws EntityDoesNotExistException {
		LineInfo lineInfo = vehicleService.getLineInfo(TransportType.TRAM,VehicleConstants.FIND_LINE_NAME);
		
		assertTrue(lineInfo.getAtStations().size() == 1);
		assertEquals(VehicleConstants.FIND_LINE_NAME,lineInfo.getLine().getName());
		assertEquals(new Long(20l),lineInfo.getLine().getId());
	}

	@Test
	public void getLineInfoTrolleybus() throws EntityDoesNotExistException {
		LineInfo lineInfo = vehicleService.getLineInfo(TransportType.TROLLEYBUS,VehicleConstants.FIND_LINE_NAME);
		
		assertTrue(lineInfo.getAtStations().size() == 1);
		assertEquals(VehicleConstants.FIND_LINE_NAME,lineInfo.getLine().getName());
		assertEquals(new Long(20l),lineInfo.getLine().getId());
	}

	@Test(expected = EntityDoesNotExistException.class)
	public void getLineInfoBusBad() throws EntityDoesNotExistException {
		LineInfo lineInfo = vehicleService.getLineInfo(TransportType.BUS, VehicleConstants.FIND_LINE_NAME_WRONG);
	}

}
