package com.project.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.nio.charset.Charset;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.project.constants.VehicleConstants;
import com.project.domain.TransportType;
import com.project.domain.Vehicle;
import com.project.exceptions.EntityDoesNotExistException;
import com.project.web.dto.LineInfo;
import com.project.web.dto.LinesPerTypeDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class VehicleControllerTest {

	private static final String URL_PREFIX = "/addVehicle";

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@PostConstruct
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	// Zbog rollbacka ,za Suit je potrebno staviti velicinu 1 , za posebne tetove 2
	// Zbog rollbacka ,za Suit je potrebno staviti index 0, za posebne 1
	@Test
	public void getLinesPerTypeTest() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/getLinesPerType"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.busLines", hasSize(1)))
				.andExpect(jsonPath("$.tramLines",hasSize(1)))
				.andExpect(jsonPath("$.trolleybusLines", hasSize(1)))
				.andExpect(jsonPath("$.busLines.[0]").value(VehicleConstants.FIND_LINE_NAME))
				.andExpect(jsonPath("$.tramLines.[0]").value(VehicleConstants.FIND_LINE_NAME))
				.andExpect(jsonPath("$.trolleybusLines.[0]").value(VehicleConstants.FIND_LINE_NAME));
	}
	
	@Test
	public void getLineInfoBus() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/getLineInfo/BUS/" + VehicleConstants.FIND_LINE_NAME))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.atStations", hasSize(1)))
				.andExpect(jsonPath("$.line.name").value(VehicleConstants.FIND_LINE_NAME))
				.andExpect(jsonPath("$.line.id").value(new Long(20)));
	}

	@Test
	public void getLineInfoTram() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/getLineInfo/TRAM/" + VehicleConstants.FIND_LINE_NAME))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.atStations", hasSize(1)))
				.andExpect(jsonPath("$.line.name").value(VehicleConstants.FIND_LINE_NAME))
				.andExpect(jsonPath("$.line.id").value(new Long(20)));
	}

	@Test
	public void getLineInfoTrolleybus() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/getLineInfo/TROLLEYBUS/" + VehicleConstants.FIND_LINE_NAME))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.atStations", hasSize(1)))
				.andExpect(jsonPath("$.line.name").value(VehicleConstants.FIND_LINE_NAME))
				.andExpect(jsonPath("$.line.id").value(new Long(20)));
		
	}

	@Test
	public void getLineInfoBusBad() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/getLineInfo/TRAM/" + VehicleConstants.FIND_LINE_NAME_WRONG))
		.andExpect(status().isBadRequest());
	}

}
