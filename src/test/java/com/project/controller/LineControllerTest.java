package com.project.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.project.TestUtil;
import com.project.constants.BusStopConstants;
import com.project.constants.LineConstants;
import com.project.domain.BusStation;
import com.project.exceptions.EntityDoesNotExistException;
import com.project.exceptions.InvalidDataException;
import com.project.web.dto.LineDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class LineControllerTest {

	private static final String URL_PREFIX = "/line";

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@PostConstruct
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void getLines() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/get_lines")).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(LineConstants.DB_SIZE + 1)));
	}

	@Test
	public void getStations() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/get_stations")).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(BusStopConstants.DB_SIZE)));
	}

	@Test
	@WithMockUser(authorities = { "ADMIN_ROLE" })
	public void addStationNullLatSent() throws Exception {
		BusStation bs = new BusStation();
		bs.setLat(null);
		bs.setLng(BusStopConstants.NEW_LNG_FIRST_AREA2);
		String json = TestUtil.json(bs);

		MvcResult result = mockMvc.perform(put(URL_PREFIX + "/add_station").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid adding of station.Lat is null", result.getResponse().getContentAsString());
	}

	@Test
	@WithMockUser(authorities = { "ADMIN_ROLE" })
	public void addStationNullLngSent() throws Exception {
		BusStation bs = new BusStation();
		bs.setLat(BusStopConstants.NEW_LAT_FIRST_AREA2);
		bs.setLng(null);
		String json = TestUtil.json(bs);

		MvcResult result = mockMvc.perform(put(URL_PREFIX + "/add_station").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid adding of station.Lng is null", result.getResponse().getContentAsString());
	}

	@Test
	@WithMockUser(authorities = { "ADMIN_ROLE" })
	public void addStationSameLngLat() throws Exception {
		BusStation bs = new BusStation();
		bs.setLat(BusStopConstants.NEW_LAT_TAKEN);
		bs.setLng(BusStopConstants.NEW_LNG_TAKEN);
		String json = TestUtil.json(bs);

		MvcResult result = mockMvc.perform(put(URL_PREFIX + "/add_station").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid adding of station.Station already exists", result.getResponse().getContentAsString());
	}

	@Test
	@WithMockUser(authorities = { "ADMIN_ROLE" })
	public void addStationLatNotParsable() throws Exception {
		BusStation bs = new BusStation();
		bs.setLat(BusStopConstants.NEW_LAT_NAN);
		bs.setLng(BusStopConstants.NEW_LNG_NAN);
		String json = TestUtil.json(bs);

		MvcResult result = mockMvc.perform(put(URL_PREFIX + "/add_station").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid adding of station.Coords not numbers", result.getResponse().getContentAsString());
	}

	@Test
	@WithMockUser(authorities = { "ADMIN_ROLE" })
	public void addStationLngNotParsable() throws Exception {
		BusStation bs = new BusStation();
		bs.setLat(BusStopConstants.NEW_LAT_FIRST_AREA2);
		bs.setLng(BusStopConstants.NEW_LNG_NAN);
		String json = TestUtil.json(bs);

		MvcResult result = mockMvc.perform(put(URL_PREFIX + "/add_station").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid adding of station.Coords not numbers", result.getResponse().getContentAsString());
	}

	@Test
	@WithMockUser(authorities = { "ADMIN_ROLE" })
	public void addStationLatOutOfBounds1() throws Exception {
		BusStation bs = new BusStation();
		bs.setLat(BusStopConstants.NEW_LAT_OOB1);
		bs.setLng(BusStopConstants.NEW_LNG_FIRST_AREA2);
		String json = TestUtil.json(bs);

		MvcResult result = mockMvc.perform(put(URL_PREFIX + "/add_station").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid adding of station.Point not in the area", result.getResponse().getContentAsString());
	}

	@Test
	@WithMockUser(authorities = { "ADMIN_ROLE" })
	public void addStationLatOutOfBounds2() throws Exception {
		BusStation bs = new BusStation();
		bs.setLat(BusStopConstants.NEW_LAT_OOB2);
		bs.setLng(BusStopConstants.NEW_LNG_FIRST_AREA2);
		String json = TestUtil.json(bs);

		MvcResult result = mockMvc.perform(put(URL_PREFIX + "/add_station").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid adding of station.Point not in the area", result.getResponse().getContentAsString());
	}

	@Test
	@WithMockUser(authorities = { "ADMIN_ROLE" })
	public void addStationLngOutOfBounds1() throws Exception {
		BusStation bs = new BusStation();
		bs.setLat(BusStopConstants.NEW_LAT_FIRST_AREA2);
		bs.setLng(BusStopConstants.NEW_LNG_OOB1);
		String json = TestUtil.json(bs);

		MvcResult result = mockMvc.perform(put(URL_PREFIX + "/add_station").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid adding of station.Point not in the area", result.getResponse().getContentAsString());
	}

	@Test
	@WithMockUser(authorities = { "ADMIN_ROLE" })
	public void addStationLngOutOfBounds2() throws Exception {
		BusStation bs = new BusStation();
		bs.setLat(BusStopConstants.NEW_LAT_FIRST_AREA2);
		bs.setLng(BusStopConstants.NEW_LNG_OOB2);
		String json = TestUtil.json(bs);

		MvcResult result = mockMvc.perform(put(URL_PREFIX + "/add_station").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid adding of station.Point not in the area", result.getResponse().getContentAsString());
	}

	@Test
	@WithMockUser(authorities = { "ADMIN_ROLE" })
	public void addStationLatOutOfBounds3() throws Exception {
		BusStation bs = new BusStation();
		bs.setLat(BusStopConstants.NEW_LAT_OOB3);
		bs.setLng(BusStopConstants.NEW_LNG_SECOND_AREA2);
		String json = TestUtil.json(bs);

		MvcResult result = mockMvc.perform(put(URL_PREFIX + "/add_station").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid adding of station.Point not in the area", result.getResponse().getContentAsString());
	}

	@Test
	@WithMockUser(authorities = { "ADMIN_ROLE" })
	public void addStationLatOutOfBounds4() throws Exception {
		BusStation bs = new BusStation();
		bs.setLat(BusStopConstants.NEW_LAT_OOB4);
		bs.setLng(BusStopConstants.NEW_LNG_SECOND_AREA2);
		String json = TestUtil.json(bs);

		MvcResult result = mockMvc.perform(put(URL_PREFIX + "/add_station").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid adding of station.Point not in the area", result.getResponse().getContentAsString());
	}

	@Test
	@WithMockUser(authorities = { "ADMIN_ROLE" })
	public void addStationLngOutOfBounds3() throws Exception {
		BusStation bs = new BusStation();
		bs.setLat(BusStopConstants.NEW_LAT_SECOND_AREA2);
		bs.setLng(BusStopConstants.NEW_LNG_OOB3);
		String json = TestUtil.json(bs);

		MvcResult result = mockMvc.perform(put(URL_PREFIX + "/add_station").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid adding of station.Point not in the area", result.getResponse().getContentAsString());
	}

	@Test
	@WithMockUser(authorities = { "ADMIN_ROLE" })
	public void addStationLngOutOfBounds4() throws Exception {
		BusStation bs = new BusStation();
		bs.setLat(BusStopConstants.NEW_LAT_SECOND_AREA2);
		bs.setLng(BusStopConstants.NEW_LNG_OOB4);
		String json = TestUtil.json(bs);

		MvcResult result = mockMvc.perform(put(URL_PREFIX + "/add_station").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid adding of station.Point not in the area", result.getResponse().getContentAsString());
	}

	@Test
	@WithMockUser(authorities = { "ADMIN_ROLE" })
	@Rollback
	public void addStationInsideFirstArea() throws Exception {
		BusStation bs = new BusStation();
		bs.setLat(BusStopConstants.NEW_LAT_FIRST_AREA2);
		bs.setLng(BusStopConstants.NEW_LNG_FIRST_AREA2);
		String json = TestUtil.json(bs);

		MvcResult result = mockMvc.perform(put(URL_PREFIX + "/add_station").contentType(contentType).content(json))
				.andExpect(status().isOk()).andReturn();

		assertEquals("Station added.", result.getResponse().getContentAsString());
	}

	@Test
	@WithMockUser(authorities = { "ADMIN_ROLE" })
	@Rollback
	public void addStationInsideSecondArea() throws Exception {
		BusStation bs = new BusStation();
		bs.setLat(BusStopConstants.NEW_LAT_SECOND_AREA2);
		bs.setLng(BusStopConstants.NEW_LNG_SECOND_AREA2);
		String json = TestUtil.json(bs);

		MvcResult result = mockMvc.perform(put(URL_PREFIX + "/add_station").contentType(contentType).content(json))
				.andExpect(status().isOk()).andReturn();

		assertEquals("Station added.", result.getResponse().getContentAsString());
	}

	@Test
	@WithMockUser(authorities = { "ADMIN_ROLE" })
	public void addLineNameNull() throws Exception {
		LineDTO line = new LineDTO();
		String json = TestUtil.json(line);

		MvcResult result = mockMvc.perform(put(URL_PREFIX + "/add_line").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid adding of line.Name is null", result.getResponse().getContentAsString());
	}

	@Test
	@WithMockUser(authorities = { "ADMIN_ROLE" })
	public void addLineStationsNull() throws Exception {
		LineDTO line = new LineDTO();
		line.setName(LineConstants.NEW_NAME_FOR_INVALID);
		String json = TestUtil.json(line);

		MvcResult result = mockMvc.perform(put(URL_PREFIX + "/add_line").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid adding of line.Stations are null", result.getResponse().getContentAsString());
	}

	@Test
	@WithMockUser(authorities = { "ADMIN_ROLE" })
	public void addLineNameTaken() throws Exception {
		LineDTO line = new LineDTO();
		line.setName(LineConstants.NEW_NAME_TAKEN);
		line.setStations(LineConstants.NEW_NOT_ENOUGH_STATIONS0);
		String json = TestUtil.json(line);

		MvcResult result = mockMvc.perform(put(URL_PREFIX + "/add_line").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid adding of line.Line name taken", result.getResponse().getContentAsString());
	}

	@Test
	@WithMockUser(authorities = { "ADMIN_ROLE" })
	public void addLineStationsNrLow1() throws Exception {
		LineDTO line = new LineDTO();
		line.setName(LineConstants.NEW_NAME_FOR_INVALID);
		line.setStations(LineConstants.NEW_NOT_ENOUGH_STATIONS0);
		String json = TestUtil.json(line);

		MvcResult result = mockMvc.perform(put(URL_PREFIX + "/add_line").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid adding of line.Need atleast 2 stations", result.getResponse().getContentAsString());
	}

	@Test
	@WithMockUser(authorities = { "ADMIN_ROLE" })
	public void addLineStationsNrLow2() throws Exception {
		LineDTO line = new LineDTO();
		line.setName(LineConstants.NEW_NAME_FOR_INVALID);
		line.setStations(LineConstants.NEW_NOT_ENOUGH_STATIONS1);
		String json = TestUtil.json(line);

		MvcResult result = mockMvc.perform(put(URL_PREFIX + "/add_line").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid adding of line.Need atleast 2 stations", result.getResponse().getContentAsString());
	}

	@Test
	@WithMockUser(authorities = { "ADMIN_ROLE" })
	public void addLineStationIsNull() throws Exception {
		LineDTO line = new LineDTO();
		line.setName(LineConstants.NEW_NAME_FOR_INVALID);
		line.setStations(LineConstants.NEW_STATIONS_ONE_NULL);
		String json = TestUtil.json(line);

		MvcResult result = mockMvc.perform(put(URL_PREFIX + "/add_line").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid adding of line.Station is null", result.getResponse().getContentAsString());
	}

	@Test
	@WithMockUser(authorities = { "ADMIN_ROLE" })
	public void addLineStationLatIsNull() throws Exception {
		LineDTO line = new LineDTO();
		line.setName(LineConstants.NEW_NAME_FOR_INVALID);
		line.setStations(LineConstants.NEW_STATIONS_LAT_NULL);
		String json = TestUtil.json(line);

		MvcResult result = mockMvc.perform(put(URL_PREFIX + "/add_line").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid adding of line.Station lat is null", result.getResponse().getContentAsString());
	}

	@Test
	@WithMockUser(authorities = { "ADMIN_ROLE" })
	public void addLineStationLngIsNull() throws Exception {
		LineDTO line = new LineDTO();
		line.setName(LineConstants.NEW_NAME_FOR_INVALID);
		line.setStations(LineConstants.NEW_STATIONS_LNG_NULL);
		String json = TestUtil.json(line);

		MvcResult result = mockMvc.perform(put(URL_PREFIX + "/add_line").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid adding of line.Station lng is null", result.getResponse().getContentAsString());
	}

	@Test
	@WithMockUser(authorities = { "ADMIN_ROLE" })
	public void addLineStationNotFound1() throws Exception {
		LineDTO line = new LineDTO();
		line.setName(LineConstants.NEW_NAME_FOR_INVALID);
		line.setStations(LineConstants.NEW_STATIONS_LAT_INVALID);
		String json = TestUtil.json(line);

		MvcResult result = mockMvc.perform(put(URL_PREFIX + "/add_line").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid adding of line.Station not in a system", result.getResponse().getContentAsString());
	}

	@Test
	@WithMockUser(authorities = { "ADMIN_ROLE" })
	public void addLineStationNotFound2() throws Exception {
		LineDTO line = new LineDTO();
		line.setName(LineConstants.NEW_NAME_FOR_INVALID);
		line.setStations(LineConstants.NEW_STATIONS_LNG_INVALID);
		String json = TestUtil.json(line);

		MvcResult result = mockMvc.perform(put(URL_PREFIX + "/add_line").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid adding of line.Station not in a system", result.getResponse().getContentAsString());
	}

	@Test
	@WithMockUser(authorities = { "ADMIN_ROLE" })
	public void addLineStationNotUnique() throws Exception {
		LineDTO line = new LineDTO();
		line.setName(LineConstants.NEW_NAME_FOR_INVALID);
		line.setStations(LineConstants.NEW_STATIONS_NOT_UNIQUE);
		String json = TestUtil.json(line);

		MvcResult result = mockMvc.perform(put(URL_PREFIX + "/add_line").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid adding of line.Unique bus stations required", result.getResponse().getContentAsString());
	}

	@Test
	@WithMockUser(authorities = { "ADMIN_ROLE" })
	@Rollback
	public void addLineAllGood() throws Exception {
		LineDTO line = new LineDTO();
		line.setName(LineConstants.NEW_NAME1);
		line.setStations(LineConstants.NEW_STATIONS);
		String json = TestUtil.json(line);

		MvcResult result = mockMvc.perform(put(URL_PREFIX + "/add_line").contentType(contentType).content(json))
				.andExpect(status().isOk()).andReturn();

		assertEquals("Line added.", result.getResponse().getContentAsString());
	}
}
