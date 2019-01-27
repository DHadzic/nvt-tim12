package com.project.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.project.TestUtil;
import com.project.constants.PassengerConstants;
import com.project.domain.Passenger;
import com.project.exceptions.EntityAlreadyExistsException;
import com.project.exceptions.InvalidDataException;
import com.project.web.dto.LoginDTO;
import com.project.web.dto.PassengerDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class UserControllerTest {

	private static final String URL_PREFIX = "/user";

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
	public void successfulLogin() throws Exception {
		LoginDTO login = new LoginDTO();
		login.setUsername(PassengerConstants.SUCC_LOGIN_USERNAME);
		login.setPassword(PassengerConstants.SUCC_LOGIN_PASSWORD);
		String json = TestUtil.json(login);

		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/login").contentType(contentType).content(json))
				.andExpect(status().isOk()).andReturn();

		assertFalse(result.getResponse().getContentAsString().contains("Invalid login"));
	}

	@Test
	public void badLogin() throws Exception {
		LoginDTO login = new LoginDTO();
		login.setUsername(PassengerConstants.SUCC_LOGIN_USERNAME);
		login.setPassword(PassengerConstants.BAD_LOGIN_PASSWORD);
		String json = TestUtil.json(login);

		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/login").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertTrue(result.getResponse().getContentAsString().contains("Invalid login"));
	}

	@Test
	@Transactional
	@Rollback
	public void successfulRegister() throws Exception {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername(PassengerConstants.NEW_USERNAME1);
		pass.setPassword(PassengerConstants.NEW_PASSWORD);
		pass.setName(PassengerConstants.NEW_NAME);
		pass.setSurname(PassengerConstants.NEW_SURNAME);
		pass.setBirthDate(PassengerConstants.NEW_BIRTHDATE);
		pass.setType(PassengerConstants.NEW_TYPE);

		String json = TestUtil.json(pass);

		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/register").contentType(contentType).content(json))
				.andExpect(status().isOk()).andReturn();

		assertEquals("Successful register", result.getResponse().getContentAsString());
	}

	@Test
	public void registerUsernameTaken() throws Exception {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername(PassengerConstants.NEW_TAKEN_USERNAME);
		pass.setPassword(PassengerConstants.NEW_PASSWORD);
		pass.setName(PassengerConstants.NEW_NAME);
		pass.setSurname(PassengerConstants.NEW_SURNAME);
		pass.setBirthDate(PassengerConstants.NEW_BIRTHDATE);
		pass.setType(PassengerConstants.NEW_TYPE);

		String json = TestUtil.json(pass);

		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/register").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid register..Username taken", result.getResponse().getContentAsString());
	}

	@Test
	public void registerUsernameShort() throws Exception {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername(PassengerConstants.NEW_USERNAME_SHORT);
		pass.setPassword(PassengerConstants.NEW_PASSWORD);
		pass.setName(PassengerConstants.NEW_NAME);
		pass.setSurname(PassengerConstants.NEW_SURNAME);
		pass.setBirthDate(PassengerConstants.NEW_BIRTHDATE);
		pass.setType(PassengerConstants.NEW_TYPE);
		String json = TestUtil.json(pass);

		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/register").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid register..Username format", result.getResponse().getContentAsString());

	}

	@Test
	public void registerUsernameLong() throws Exception {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername(PassengerConstants.NEW_USERNAME_LONG);
		pass.setPassword(PassengerConstants.NEW_PASSWORD);
		pass.setName(PassengerConstants.NEW_NAME);
		pass.setSurname(PassengerConstants.NEW_SURNAME);
		pass.setBirthDate(PassengerConstants.NEW_BIRTHDATE);
		pass.setType(PassengerConstants.NEW_TYPE);
		String json = TestUtil.json(pass);

		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/register").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid register..Username format", result.getResponse().getContentAsString());
	}

	@Test
	public void registerPasswordShort() throws Exception {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername(PassengerConstants.NEW_USERNAME_FOR_INVALID);
		pass.setPassword(PassengerConstants.NEW_PASSWORD_SHORT);
		pass.setName(PassengerConstants.NEW_NAME);
		pass.setSurname(PassengerConstants.NEW_SURNAME);
		pass.setBirthDate(PassengerConstants.NEW_BIRTHDATE);
		pass.setType(PassengerConstants.NEW_TYPE);
		String json = TestUtil.json(pass);

		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/register").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid register..Password format", result.getResponse().getContentAsString());
	}

	@Test
	public void registerPasswordLong() throws Exception {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername(PassengerConstants.NEW_USERNAME_FOR_INVALID);
		pass.setPassword(PassengerConstants.NEW_PASSWORD_LONG);
		pass.setName(PassengerConstants.NEW_NAME);
		pass.setSurname(PassengerConstants.NEW_SURNAME);
		pass.setBirthDate(PassengerConstants.NEW_BIRTHDATE);
		pass.setType(PassengerConstants.NEW_TYPE);
		String json = TestUtil.json(pass);

		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/register").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid register..Password format", result.getResponse().getContentAsString());
	}

	@Test
	public void registerNameShort() throws Exception {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername(PassengerConstants.NEW_USERNAME_FOR_INVALID);
		pass.setPassword(PassengerConstants.NEW_PASSWORD);
		pass.setName(PassengerConstants.NEW_NAME_SHORT);
		pass.setSurname(PassengerConstants.NEW_SURNAME);
		pass.setBirthDate(PassengerConstants.NEW_BIRTHDATE);
		pass.setType(PassengerConstants.NEW_TYPE);
		String json = TestUtil.json(pass);

		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/register").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid register..Name format", result.getResponse().getContentAsString());
	}

	@Test
	public void registerNameLong() throws Exception {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername(PassengerConstants.NEW_USERNAME_FOR_INVALID);
		pass.setPassword(PassengerConstants.NEW_PASSWORD);
		pass.setName(PassengerConstants.NEW_NAME_LONG);
		pass.setSurname(PassengerConstants.NEW_SURNAME);
		pass.setBirthDate(PassengerConstants.NEW_BIRTHDATE);
		pass.setType(PassengerConstants.NEW_TYPE);
		String json = TestUtil.json(pass);

		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/register").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid register..Name format", result.getResponse().getContentAsString());
	}

	@Test
	public void registerSurnameShort() throws Exception {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername(PassengerConstants.NEW_USERNAME_FOR_INVALID);
		pass.setPassword(PassengerConstants.NEW_PASSWORD);
		pass.setName(PassengerConstants.NEW_NAME);
		pass.setSurname(PassengerConstants.NEW_SURNAME_SHORT);
		pass.setBirthDate(PassengerConstants.NEW_BIRTHDATE);
		pass.setType(PassengerConstants.NEW_TYPE);
		String json = TestUtil.json(pass);

		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/register").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid register..Surname format", result.getResponse().getContentAsString());
	}

	@Test
	public void registerSurnameLong() throws Exception {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername(PassengerConstants.NEW_USERNAME_FOR_INVALID);
		pass.setPassword(PassengerConstants.NEW_PASSWORD);
		pass.setName(PassengerConstants.NEW_NAME);
		pass.setSurname(PassengerConstants.NEW_SURNAME_LONG);
		pass.setBirthDate(PassengerConstants.NEW_BIRTHDATE);
		pass.setType(PassengerConstants.NEW_TYPE);
		String json = TestUtil.json(pass);

		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/register").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid register..Surname format", result.getResponse().getContentAsString());
	}

	@Test
	public void registerBirthDateBefore() throws Exception {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername(PassengerConstants.NEW_USERNAME_FOR_INVALID);
		pass.setPassword(PassengerConstants.NEW_PASSWORD);
		pass.setName(PassengerConstants.NEW_NAME);
		pass.setSurname(PassengerConstants.NEW_SURNAME);
		pass.setBirthDate(PassengerConstants.NEW_BIRTHDATE_BEFORE);
		pass.setType(PassengerConstants.NEW_TYPE);
		String json = TestUtil.json(pass);

		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/register").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid register..Date format", result.getResponse().getContentAsString());
	}

	@Test
	public void registerBirthDateAfter() throws Exception {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername(PassengerConstants.NEW_USERNAME_FOR_INVALID);
		pass.setPassword(PassengerConstants.NEW_PASSWORD);
		pass.setName(PassengerConstants.NEW_NAME);
		pass.setSurname(PassengerConstants.NEW_SURNAME);
		pass.setBirthDate(PassengerConstants.NEW_BIRTHDATE_AFTER);
		pass.setType(PassengerConstants.NEW_TYPE);
		String json = TestUtil.json(pass);

		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/register").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid register..Date format", result.getResponse().getContentAsString());
	}

	@Test
	public void registerUsernameNull() throws Exception {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername(null);
		pass.setPassword(PassengerConstants.NEW_PASSWORD);
		pass.setName(PassengerConstants.NEW_NAME);
		pass.setSurname(PassengerConstants.NEW_SURNAME);
		pass.setBirthDate(PassengerConstants.NEW_BIRTHDATE);
		pass.setType(PassengerConstants.NEW_TYPE);
		String json = TestUtil.json(pass);

		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/register").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid register..Username is null", result.getResponse().getContentAsString());
	}

	@Test
	public void registerPasswordNull() throws Exception {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername(PassengerConstants.NEW_USERNAME_FOR_INVALID);
		pass.setPassword(null);
		pass.setName(PassengerConstants.NEW_NAME);
		pass.setSurname(PassengerConstants.NEW_SURNAME);
		pass.setBirthDate(PassengerConstants.NEW_BIRTHDATE);
		pass.setType(PassengerConstants.NEW_TYPE);
		String json = TestUtil.json(pass);

		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/register").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid register..Password is null", result.getResponse().getContentAsString());
	}

	@Test
	public void registerNameNull() throws Exception {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername(PassengerConstants.NEW_USERNAME_FOR_INVALID);
		pass.setPassword(PassengerConstants.NEW_PASSWORD);
		pass.setName(null);
		pass.setSurname(PassengerConstants.NEW_SURNAME);
		pass.setBirthDate(PassengerConstants.NEW_BIRTHDATE);
		pass.setType(PassengerConstants.NEW_TYPE);
		String json = TestUtil.json(pass);

		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/register").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid register..Name is null", result.getResponse().getContentAsString());
	}

	@Test
	public void registerSurnameNull() throws Exception {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername(PassengerConstants.NEW_USERNAME_FOR_INVALID);
		pass.setPassword(PassengerConstants.NEW_PASSWORD);
		pass.setName(PassengerConstants.NEW_NAME);
		pass.setSurname(null);
		pass.setBirthDate(PassengerConstants.NEW_BIRTHDATE);
		pass.setType(PassengerConstants.NEW_TYPE);
		String json = TestUtil.json(pass);

		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/register").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid register..Surname is null", result.getResponse().getContentAsString());
	}

	@Test
	public void registerBirthDateNull() throws Exception {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername(PassengerConstants.NEW_USERNAME_FOR_INVALID);
		pass.setPassword(PassengerConstants.NEW_PASSWORD);
		pass.setName(PassengerConstants.NEW_NAME);
		pass.setSurname(PassengerConstants.NEW_SURNAME);
		pass.setBirthDate(null);
		pass.setType(PassengerConstants.NEW_TYPE);
		String json = TestUtil.json(pass);

		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/register").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid register..BirthDate is null", result.getResponse().getContentAsString());
	}

	@Test
	public void registerTypeNull() throws Exception {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername(PassengerConstants.NEW_USERNAME_FOR_INVALID);
		pass.setPassword(PassengerConstants.NEW_PASSWORD);
		pass.setName(PassengerConstants.NEW_NAME);
		pass.setSurname(PassengerConstants.NEW_SURNAME);
		pass.setBirthDate(PassengerConstants.NEW_BIRTHDATE);
		pass.setType(null);
		String json = TestUtil.json(pass);

		MvcResult result = mockMvc.perform(post(URL_PREFIX + "/register").contentType(contentType).content(json))
				.andExpect(status().isBadRequest()).andReturn();

		assertEquals("Invalid register..Type is null", result.getResponse().getContentAsString());
	}
}
