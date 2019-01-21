package com.project.service;

import static org.junit.Assert.assertEquals;

import java.util.GregorianCalendar;
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
import com.project.domain.Passenger;
import com.project.domain.PassengerType;
import com.project.domain.UserAuthority;
import com.project.exceptions.EntityAlreadyExistsException;
import com.project.exceptions.InvalidDataException;
import com.project.repository.AuthorityRepository;
import com.project.repository.UserRepository;
import com.project.web.dto.PassengerDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private AuthorityRepository authRepository;

	@Before
	public void setUp() {
		Authority auth = new Authority();
		auth.setUserAuthorities(new HashSet<>());
		
		Mockito.when(userRepository.findByUsername("TakenUser")).thenReturn(new Passenger());
		Mockito.when(userRepository.findByUsername("cc")).thenReturn(null);
		Mockito.when(userRepository.findByUsername("123456789012345678901")).thenReturn(null);
		Mockito.when(userRepository.findByUsername("TestUser1")).thenReturn(null);
		Mockito.when(authRepository.findByName("PASSENGER_ROLE")).thenReturn(auth);
	}
	
	@Test
	public void registerUsernameTaken(){
		try {
			PassengerDTO pass = new PassengerDTO();
			pass.setUsername("TakenUser");
			pass.setPassword("TestUser1");
			pass.setName("TestUser1");
			pass.setSurname("TestUser1");
			pass.setBirthDate(new GregorianCalendar(1995,10,5));
			pass.setType(PassengerType.REGULAR);
			userService.registerUser(pass);
		}catch(Exception e) {
			assertEquals("Username taken",e.getMessage());
		}
	}

	@Test
	public void registerUsernameShort() {
		try {
			PassengerDTO pass = new PassengerDTO();
			pass.setUsername("cc");
			pass.setPassword("TestUser1");
			pass.setName("TestUser1");
			pass.setSurname("TestUser1");
			pass.setBirthDate(new GregorianCalendar(1995,10,5));
			pass.setType(PassengerType.REGULAR);
			userService.registerUser(pass);
		}catch(Exception e) {
			assertEquals("Username format",e.getMessage());
		}
	}

	@Test
	public void registerUsernameLong() {
		try {
			PassengerDTO pass = new PassengerDTO();
			pass.setUsername("123456789012345678901");
			pass.setPassword("TestUser1");
			pass.setName("TestUser1");
			pass.setSurname("TestUser1");
			pass.setBirthDate(new GregorianCalendar(1995,10,5));
			pass.setType(PassengerType.REGULAR);
		userService.registerUser(pass);
		}catch(Exception e) {
			assertEquals("Username format",e.getMessage());
		}
	}

	@Test
	public void registerPasswordShort() {
		try {
			PassengerDTO pass = new PassengerDTO();
			pass.setUsername("TestUser1");
			pass.setPassword("cc");
			pass.setName("TestUser1");
			pass.setSurname("TestUser1");
			pass.setBirthDate(new GregorianCalendar(1995,10,5));
			pass.setType(PassengerType.REGULAR);
			userService.registerUser(pass);
		}catch(Exception e) {
			assertEquals("Password format",e.getMessage());
		}
	}

	@Test
	public void registerPasswordLong() {
		try {
			PassengerDTO pass = new PassengerDTO();
			pass.setUsername("TestUser1");
			pass.setPassword("123456789012345678901");
			pass.setName("TestUser1");
			pass.setSurname("TestUser1");
			pass.setBirthDate(new GregorianCalendar(1995,10,5));
			pass.setType(PassengerType.REGULAR);
			userService.registerUser(pass);
		}catch(Exception e) {
			assertEquals("Password format",e.getMessage());
		}
	}

	@Test
	public void registerNameShort() {
		try {
			PassengerDTO pass = new PassengerDTO();
			pass.setUsername("TestUser1");
			pass.setPassword("TestUser1");
			pass.setName("cc");
			pass.setSurname("TestUser1");
			pass.setBirthDate(new GregorianCalendar(1995,10,5));
			pass.setType(PassengerType.REGULAR);
			userService.registerUser(pass);
		}catch(Exception e) {
			assertEquals("Name format",e.getMessage());
		}
	}

	@Test
	public void registerNameLong() {
		try {
			PassengerDTO pass = new PassengerDTO();
			pass.setUsername("TestUser1");
			pass.setPassword("TestUser1");
			pass.setSurname("TestUser1");
			pass.setBirthDate(new GregorianCalendar(1995,10,5));
			pass.setType(PassengerType.REGULAR);
			pass.setName("123456789012345678901");
			userService.registerUser(pass);
		}catch(Exception e) {
			assertEquals("Name format",e.getMessage());
		}
	}
	
	@Test
	public void registerSurnameShort() {
		try {
			PassengerDTO pass = new PassengerDTO();
			pass.setUsername("TestUser1");
			pass.setPassword("TestUser1");
			pass.setName("TestUser1");
			pass.setSurname("cc");
			pass.setBirthDate(new GregorianCalendar(1995,10,5));
			pass.setType(PassengerType.REGULAR);
			userService.registerUser(pass);
		}catch(Exception e) {
			assertEquals("Surname format",e.getMessage());
		}
	}

	@Test
	public void registerSurnameLong() {
		try {
			PassengerDTO pass = new PassengerDTO();
			pass.setUsername("TestUser1");
			pass.setPassword("TestUser1");
			pass.setName("TestUser1");
			pass.setBirthDate(new GregorianCalendar(1995,10,5));
			pass.setType(PassengerType.REGULAR);
			pass.setSurname("123456789012345678901");
			userService.registerUser(pass);
		}catch(Exception e) {
			assertEquals("Surname format",e.getMessage());
		}
	}

	@Test
	public void registerBirthDateBefore() {
		try {
			PassengerDTO pass = new PassengerDTO();
			pass.setUsername("TestUser1");
			pass.setPassword("TestUser1");
			pass.setName("TestUser1");
			pass.setSurname("TestUser1");
			pass.setType(PassengerType.REGULAR);
			pass.setBirthDate(new GregorianCalendar(1900,10,5));
			userService.registerUser(pass);
		}catch(Exception e) {
			assertEquals("Date format",e.getMessage());
		}
	}

	@Test
	public void registerBirthDateAfter() {
		try {
			PassengerDTO pass = new PassengerDTO();
			pass.setUsername("TestUser1");
			pass.setPassword("TestUser1");
			pass.setName("TestUser1");
			pass.setSurname("TestUser1");
			pass.setBirthDate(new GregorianCalendar(2011,10,5));
			pass.setType(PassengerType.REGULAR);
			userService.registerUser(pass);
		}catch(Exception e) {
			assertEquals("Date format",e.getMessage());
		}
	}
	
	// If there are not exceptions , register is successful
	@Test
	public void registerSuccessful() throws EntityAlreadyExistsException, InvalidDataException {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername("TestUser1");
		pass.setPassword("TestUser1");
		pass.setName("TestUser1");
		pass.setSurname("TestUser1");
		pass.setBirthDate(new GregorianCalendar(1995,10,5));
		pass.setType(PassengerType.REGULAR);
		userService.registerUser(pass);
	}
	
	@Test
	public void registerDataNull(){
		try {
			userService.registerUser(null);
		}catch(Exception e) {
			assertEquals("Data is null",e.getMessage());
		}
	}
	

	@Test
	public void registerUsernameNull(){
		try {
			PassengerDTO pass = new PassengerDTO();
			pass.setUsername(null);
			pass.setPassword("TestUser1");
			pass.setName("TestUser1");
			pass.setSurname("TestUser1");
			pass.setBirthDate(new GregorianCalendar(1995,10,5));
			pass.setType(PassengerType.REGULAR);
			userService.registerUser(pass);
		}catch(Exception e) {
			assertEquals("Username is null",e.getMessage());
		}
	}

	@Test
	public void registerPasswordNull(){
		try {
			PassengerDTO pass = new PassengerDTO();
			pass.setUsername("TestUser1");
			pass.setPassword(null);
			pass.setName("TestUser1");
			pass.setSurname("TestUser1");
			pass.setBirthDate(new GregorianCalendar(1995,10,5));
			pass.setType(PassengerType.REGULAR);
			userService.registerUser(pass);
		}catch(Exception e) {
			assertEquals("Password is null",e.getMessage());
		}
	}

	@Test
	public void registerNameNull(){
		try {
			PassengerDTO pass = new PassengerDTO();
			pass.setUsername("TestUser1");
			pass.setPassword("TestUser1");
			pass.setName(null);
			pass.setSurname("TestUser1");
			pass.setBirthDate(new GregorianCalendar(1995,10,5));
			pass.setType(PassengerType.REGULAR);
			userService.registerUser(pass);
		}catch(Exception e) {
			assertEquals("Name is null",e.getMessage());
		}
	}

	@Test
	public void registerSurnameNull(){
		try {
			PassengerDTO pass = new PassengerDTO();
			pass.setUsername("TestUser1");
			pass.setPassword("TestUser1");
			pass.setName("TestUser1");
			pass.setSurname(null);
			pass.setBirthDate(new GregorianCalendar(1995,10,5));
			pass.setType(PassengerType.REGULAR);
			userService.registerUser(pass);
		}catch(Exception e) {
			assertEquals("Surname is null",e.getMessage());
		}
	}

	@Test
	public void registerBirthDateNull(){
		try {
			PassengerDTO pass = new PassengerDTO();
			pass.setUsername("TestUser1");
			pass.setPassword("TestUser1");
			pass.setName("TestUser1");
			pass.setSurname("TestUser1");
			pass.setBirthDate(null);
			pass.setType(PassengerType.REGULAR);
			userService.registerUser(pass);
		}catch(Exception e) {
			assertEquals("BirthDate is null",e.getMessage());
		}
	}

	@Test
	public void registerTypeNull(){
		try {
			PassengerDTO pass = new PassengerDTO();
			pass.setUsername("TestUser1");
			pass.setPassword("TestUser1");
			pass.setName("TestUser1");
			pass.setSurname("TestUser1");
			pass.setBirthDate(new GregorianCalendar(1995,10,5));
			pass.setType(null);
			userService.registerUser(pass);
		}catch(Exception e) {
			assertEquals("Type is null",e.getMessage());
		}
	}
}
