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

import com.project.constants.PassengerConstants;
import com.project.exceptions.EntityAlreadyExistsException;
import com.project.exceptions.InvalidDataException;
import com.project.web.dto.PassengerDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
@Transactional
public class UserServiceTestInt {
	@Autowired
	private UserService userService;
	
	@Test
	public void registerUsernameTaken(){
		try {
			PassengerDTO pass = new PassengerDTO();
			pass.setUsername(PassengerConstants.NEW_TAKEN_USERNAME);
			pass.setPassword(PassengerConstants.NEW_PASSWORD);
			pass.setName(PassengerConstants.NEW_NAME);
			pass.setSurname(PassengerConstants.NEW_SURNAME);
			pass.setBirthDate(PassengerConstants.NEW_BIRTHDATE);
			pass.setType(PassengerConstants.NEW_TYPE);
			userService.registerUser(pass);
			assertTrue(false);
		}catch(Exception e) {
			assertEquals("Username taken",e.getMessage());
		}
	}
	
	@Test
	public void registerUsernameShort() {
		try {
			PassengerDTO pass = new PassengerDTO();
			pass.setUsername(PassengerConstants.NEW_USERNAME_SHORT);
			pass.setPassword(PassengerConstants.NEW_PASSWORD);
			pass.setName(PassengerConstants.NEW_NAME);
			pass.setSurname(PassengerConstants.NEW_SURNAME);
			pass.setBirthDate(PassengerConstants.NEW_BIRTHDATE);
			pass.setType(PassengerConstants.NEW_TYPE);
			userService.registerUser(pass);
			assertTrue(false);
		}catch(Exception e) {
			assertEquals("Username format",e.getMessage());
		}
	}

	@Test
	public void registerUsernameLong() {
		try {
			PassengerDTO pass = new PassengerDTO();
			pass.setUsername(PassengerConstants.NEW_USERNAME_LONG);
			pass.setPassword(PassengerConstants.NEW_PASSWORD);
			pass.setName(PassengerConstants.NEW_NAME);
			pass.setSurname(PassengerConstants.NEW_SURNAME);
			pass.setBirthDate(PassengerConstants.NEW_BIRTHDATE);
			pass.setType(PassengerConstants.NEW_TYPE);
			userService.registerUser(pass);
			assertTrue(false);
		}catch(Exception e) {
			assertEquals("Username format",e.getMessage());
		}
	}

	@Test
	public void registerPasswordShort() {
		try {
			PassengerDTO pass = new PassengerDTO();
			pass.setUsername(PassengerConstants.NEW_USERNAME);
			pass.setPassword(PassengerConstants.NEW_PASSWORD_SHORT);
			pass.setName(PassengerConstants.NEW_NAME);
			pass.setSurname(PassengerConstants.NEW_SURNAME);
			pass.setBirthDate(PassengerConstants.NEW_BIRTHDATE);
			pass.setType(PassengerConstants.NEW_TYPE);
			userService.registerUser(pass);
			assertTrue(false);
		}catch(Exception e) {
			assertEquals("Password format",e.getMessage());
		}
	}

	@Test
	public void registerPasswordLong() {
		try {
			PassengerDTO pass = new PassengerDTO();
			pass.setUsername(PassengerConstants.NEW_USERNAME);
			pass.setPassword(PassengerConstants.NEW_PASSWORD_LONG);
			pass.setName(PassengerConstants.NEW_NAME);
			pass.setSurname(PassengerConstants.NEW_SURNAME);
			pass.setBirthDate(PassengerConstants.NEW_BIRTHDATE);
			pass.setType(PassengerConstants.NEW_TYPE);
			userService.registerUser(pass);
			assertTrue(false);
		}catch(Exception e) {
			assertEquals("Password format",e.getMessage());
		}
	}

	@Test
	public void registerNameShort() {
		try {
			PassengerDTO pass = new PassengerDTO();
			pass.setUsername(PassengerConstants.NEW_USERNAME);
			pass.setPassword(PassengerConstants.NEW_PASSWORD);
			pass.setName(PassengerConstants.NEW_NAME_SHORT);
			pass.setSurname(PassengerConstants.NEW_SURNAME);
			pass.setBirthDate(PassengerConstants.NEW_BIRTHDATE);
			pass.setType(PassengerConstants.NEW_TYPE);
			userService.registerUser(pass);
			assertTrue(false);
		}catch(Exception e) {
			assertEquals("Name format",e.getMessage());
		}
	}

	@Test
	public void registerNameLong() {
		try {
			PassengerDTO pass = new PassengerDTO();
			pass.setUsername(PassengerConstants.NEW_USERNAME);
			pass.setPassword(PassengerConstants.NEW_PASSWORD);
			pass.setName(PassengerConstants.NEW_NAME_LONG);
			pass.setSurname(PassengerConstants.NEW_SURNAME);
			pass.setBirthDate(PassengerConstants.NEW_BIRTHDATE);
			pass.setType(PassengerConstants.NEW_TYPE);
			userService.registerUser(pass);
			assertTrue(false);
		}catch(Exception e) {
			assertEquals("Name format",e.getMessage());
		}
	}
	
	@Test
	public void registerSurnameShort() {
		try {
			PassengerDTO pass = new PassengerDTO();
			pass.setUsername(PassengerConstants.NEW_USERNAME);
			pass.setPassword(PassengerConstants.NEW_PASSWORD);
			pass.setName(PassengerConstants.NEW_NAME);
			pass.setSurname(PassengerConstants.NEW_SURNAME_SHORT);
			pass.setBirthDate(PassengerConstants.NEW_BIRTHDATE);
			pass.setType(PassengerConstants.NEW_TYPE);
			userService.registerUser(pass);
			assertTrue(false);
		}catch(Exception e) {
			assertEquals("Surname format",e.getMessage());
		}
	}

	@Test
	public void registerSurnameLong() {
		try {
			PassengerDTO pass = new PassengerDTO();
			pass.setUsername(PassengerConstants.NEW_USERNAME);
			pass.setPassword(PassengerConstants.NEW_PASSWORD);
			pass.setName(PassengerConstants.NEW_NAME);
			pass.setSurname(PassengerConstants.NEW_SURNAME_LONG);
			pass.setBirthDate(PassengerConstants.NEW_BIRTHDATE);
			pass.setType(PassengerConstants.NEW_TYPE);
			userService.registerUser(pass);
			assertTrue(false);
		}catch(Exception e) {
			assertEquals("Surname format",e.getMessage());
		}
	}

	@Test
	public void registerBirthDateBefore() {
		try {
			PassengerDTO pass = new PassengerDTO();
			pass.setUsername(PassengerConstants.NEW_USERNAME);
			pass.setPassword(PassengerConstants.NEW_PASSWORD);
			pass.setName(PassengerConstants.NEW_NAME);
			pass.setSurname(PassengerConstants.NEW_SURNAME);
			pass.setBirthDate(PassengerConstants.NEW_BIRTHDATE_BEFORE);
			pass.setType(PassengerConstants.NEW_TYPE);
			userService.registerUser(pass);
			assertTrue(false);
		}catch(Exception e) {
			assertEquals("Date format",e.getMessage());
		}
	}

	@Test
	public void registerBirthDateAfter() {
		try {
			PassengerDTO pass = new PassengerDTO();
			pass.setUsername(PassengerConstants.NEW_USERNAME);
			pass.setPassword(PassengerConstants.NEW_PASSWORD);
			pass.setName(PassengerConstants.NEW_NAME);
			pass.setSurname(PassengerConstants.NEW_SURNAME);
			pass.setBirthDate(PassengerConstants.NEW_BIRTHDATE_AFTER);
			pass.setType(PassengerConstants.NEW_TYPE);
			userService.registerUser(pass);
			assertTrue(false);
		}catch(Exception e) {
			assertEquals("Date format",e.getMessage());
		}
	}
	/*	
	// If there are not exceptions , register is successful
	@Test
	@Transactional
	@Rollback(true)
	public void registerSuccessful() throws EntityAlreadyExistsException, InvalidDataException {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername(PassengerConstants.NEW_USERNAME);
		pass.setPassword(PassengerConstants.NEW_PASSWORD);
		pass.setName(PassengerConstants.NEW_NAME);
		pass.setSurname(PassengerConstants.NEW_SURNAME);
		pass.setBirthDate(PassengerConstants.NEW_BIRTHDATE);
		pass.setType(PassengerConstants.NEW_TYPE);
		userService.registerUser(pass);
			
		// asertacije za findByUsername
	}
	*/
	
	@Test
	public void registerDataNull(){
		try {
			userService.registerUser(null);
			assertTrue(false);
		}catch(Exception e) {
			assertEquals("Data is null",e.getMessage());
		}
	}
	

	@Test
	public void registerUsernameNull(){
		try {
			PassengerDTO pass = new PassengerDTO();
			pass.setUsername(null);
			pass.setPassword(PassengerConstants.NEW_PASSWORD);
			pass.setName(PassengerConstants.NEW_NAME);
			pass.setSurname(PassengerConstants.NEW_SURNAME);
			pass.setBirthDate(PassengerConstants.NEW_BIRTHDATE);
			pass.setType(PassengerConstants.NEW_TYPE);
			userService.registerUser(pass);
			assertTrue(false);
		}catch(Exception e) {
			assertEquals("Username is null",e.getMessage());
		}
	}

	@Test
	public void registerPasswordNull(){
		try {
			PassengerDTO pass = new PassengerDTO();
			pass.setUsername(PassengerConstants.NEW_USERNAME);
			pass.setPassword(null);
			pass.setName(PassengerConstants.NEW_NAME);
			pass.setSurname(PassengerConstants.NEW_SURNAME);
			pass.setBirthDate(PassengerConstants.NEW_BIRTHDATE);
			pass.setType(PassengerConstants.NEW_TYPE);
			userService.registerUser(pass);
			assertTrue(false);
		}catch(Exception e) {
			assertEquals("Password is null",e.getMessage());
		}
	}

	@Test
	public void registerNameNull(){
		try {
			PassengerDTO pass = new PassengerDTO();
			pass.setUsername(PassengerConstants.NEW_USERNAME);
			pass.setPassword(PassengerConstants.NEW_PASSWORD);
			pass.setName(null);
			pass.setSurname(PassengerConstants.NEW_SURNAME);
			pass.setBirthDate(PassengerConstants.NEW_BIRTHDATE);
			pass.setType(PassengerConstants.NEW_TYPE);
			userService.registerUser(pass);
			assertTrue(false);
		}catch(Exception e) {
			assertEquals("Name is null",e.getMessage());
		}
	}

	@Test
	public void registerSurnameNull(){
		try {
			PassengerDTO pass = new PassengerDTO();
			pass.setUsername(PassengerConstants.NEW_USERNAME);
			pass.setPassword(PassengerConstants.NEW_PASSWORD);
			pass.setName(PassengerConstants.NEW_NAME);
			pass.setSurname(null);
			pass.setBirthDate(PassengerConstants.NEW_BIRTHDATE);
			pass.setType(PassengerConstants.NEW_TYPE);
			userService.registerUser(pass);
			assertTrue(false);
		}catch(Exception e) {
			assertEquals("Surname is null",e.getMessage());
		}
	}

	@Test
	public void registerBirthDateNull(){
		try {
			PassengerDTO pass = new PassengerDTO();
			pass.setUsername(PassengerConstants.NEW_USERNAME);
			pass.setPassword(PassengerConstants.NEW_PASSWORD);
			pass.setName(PassengerConstants.NEW_NAME);
			pass.setSurname(PassengerConstants.NEW_SURNAME);
			pass.setBirthDate(null);
			pass.setType(PassengerConstants.NEW_TYPE);
			userService.registerUser(pass);
			assertTrue(false);
		}catch(Exception e) {
			assertEquals("BirthDate is null",e.getMessage());
		}
	}

	@Test
	public void registerTypeNull(){
		try {
			PassengerDTO pass = new PassengerDTO();
			pass.setUsername(PassengerConstants.NEW_USERNAME);
			pass.setPassword(PassengerConstants.NEW_PASSWORD);
			pass.setName(PassengerConstants.NEW_NAME);
			pass.setSurname(PassengerConstants.NEW_SURNAME);
			pass.setBirthDate(PassengerConstants.NEW_BIRTHDATE);
			pass.setType(null);
			userService.registerUser(pass);
			assertTrue(false);
		}catch(Exception e) {
			assertEquals("Type is null",e.getMessage());
		}
	}
	
}
