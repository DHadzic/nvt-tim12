package com.project.service;

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
	
	@Test(expected = EntityAlreadyExistsException.class)
	public void registerUsernameTaken() throws EntityAlreadyExistsException, InvalidDataException {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername("TakenUser");
		pass.setPassword("TestUser1");
		pass.setName("TestUser1");
		pass.setSurname("TestUser1");
		pass.setBirthDate(new GregorianCalendar(1995,10,5));
		pass.setType(PassengerType.REGULAR);
		userService.registerUser(pass);
	}

	@Test(expected = InvalidDataException.class)
	public void registerUsernameShort() throws EntityAlreadyExistsException, InvalidDataException {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername("cc");
		pass.setPassword("TestUser1");
		pass.setName("TestUser1");
		pass.setSurname("TestUser1");
		pass.setBirthDate(new GregorianCalendar(1995,10,5));
		pass.setType(PassengerType.REGULAR);
		userService.registerUser(pass);
	}

	@Test(expected = InvalidDataException.class)
	public void registerUsernameLong() throws EntityAlreadyExistsException, InvalidDataException {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername("123456789012345678901");
		pass.setPassword("TestUser1");
		pass.setName("TestUser1");
		pass.setSurname("TestUser1");
		pass.setBirthDate(new GregorianCalendar(1995,10,5));
		pass.setType(PassengerType.REGULAR);
		userService.registerUser(pass);
	}

	@Test(expected = InvalidDataException.class)
	public void registerPasswordShort() throws EntityAlreadyExistsException, InvalidDataException {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername("TestUser1");
		pass.setPassword("cc");
		pass.setName("TestUser1");
		pass.setSurname("TestUser1");
		pass.setBirthDate(new GregorianCalendar(1995,10,5));
		pass.setType(PassengerType.REGULAR);
		userService.registerUser(pass);
	}

	@Test(expected = InvalidDataException.class)
	public void registerPasswordLong() throws EntityAlreadyExistsException, InvalidDataException {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername("TestUser1");
		pass.setPassword("123456789012345678901");
		pass.setName("TestUser1");
		pass.setSurname("TestUser1");
		pass.setBirthDate(new GregorianCalendar(1995,10,5));
		pass.setType(PassengerType.REGULAR);
		userService.registerUser(pass);
	}

	@Test(expected = InvalidDataException.class)
	public void registerNameShort() throws EntityAlreadyExistsException, InvalidDataException {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername("TestUser1");
		pass.setPassword("TestUser1");
		pass.setName("cc");
		pass.setSurname("TestUser1");
		pass.setBirthDate(new GregorianCalendar(1995,10,5));
		pass.setType(PassengerType.REGULAR);
		userService.registerUser(pass);
	}

	@Test(expected = InvalidDataException.class)
	public void registerNameLong() throws EntityAlreadyExistsException, InvalidDataException {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername("TestUser1");
		pass.setPassword("TestUser1");
		pass.setSurname("TestUser1");
		pass.setBirthDate(new GregorianCalendar(1995,10,5));
		pass.setType(PassengerType.REGULAR);
		pass.setName("123456789012345678901");
		userService.registerUser(pass);
	}
	
	@Test(expected = InvalidDataException.class)
	public void registerSurnameShort() throws EntityAlreadyExistsException, InvalidDataException {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername("TestUser1");
		pass.setPassword("TestUser1");
		pass.setName("TestUser1");
		pass.setSurname("cc");
		pass.setBirthDate(new GregorianCalendar(1995,10,5));
		pass.setType(PassengerType.REGULAR);
		userService.registerUser(pass);
	}

	@Test(expected = InvalidDataException.class)
	public void registerSurnameLong() throws EntityAlreadyExistsException, InvalidDataException {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername("TestUser1");
		pass.setPassword("TestUser1");
		pass.setName("TestUser1");
		pass.setBirthDate(new GregorianCalendar(1995,10,5));
		pass.setType(PassengerType.REGULAR);
		pass.setSurname("123456789012345678901");
		userService.registerUser(pass);
	}

	@Test(expected = InvalidDataException.class)
	public void registerBirthDateBefore() throws EntityAlreadyExistsException, InvalidDataException {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername("TestUser1");
		pass.setPassword("TestUser1");
		pass.setName("TestUser1");
		pass.setSurname("TestUser1");
		pass.setType(PassengerType.REGULAR);
		pass.setBirthDate(new GregorianCalendar(1900,10,5));
		userService.registerUser(pass);
	}

	@Test(expected = InvalidDataException.class)
	public void registerBirthDateAfter() throws EntityAlreadyExistsException, InvalidDataException {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername("TestUser1");
		pass.setPassword("TestUser1");
		pass.setName("TestUser1");
		pass.setSurname("TestUser1");
		pass.setBirthDate(new GregorianCalendar(2011,10,5));
		pass.setType(PassengerType.REGULAR);
		userService.registerUser(pass);
	}
	
	// If there are not exceptions , register is successful
	@Test()
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

	@Test(expected = InvalidDataException.class)
	public void registerUsernameNull() throws EntityAlreadyExistsException, InvalidDataException {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername(null);
		pass.setPassword("TestUser1");
		pass.setName("TestUser1");
		pass.setSurname("TestUser1");
		pass.setBirthDate(new GregorianCalendar(1995,10,5));
		pass.setType(PassengerType.REGULAR);
		userService.registerUser(pass);
	}

	@Test(expected = InvalidDataException.class)
	public void registerPasswordNull() throws EntityAlreadyExistsException, InvalidDataException {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername("TestUser1");
		pass.setPassword(null);
		pass.setName("TestUser1");
		pass.setSurname("TestUser1");
		pass.setBirthDate(new GregorianCalendar(1995,10,5));
		pass.setType(PassengerType.REGULAR);
		userService.registerUser(pass);
	}

	@Test(expected = InvalidDataException.class)
	public void registerNameNull() throws EntityAlreadyExistsException, InvalidDataException {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername("TestUser1");
		pass.setPassword("TestUser1");
		pass.setName(null);
		pass.setSurname("TestUser1");
		pass.setBirthDate(new GregorianCalendar(1995,10,5));
		pass.setType(PassengerType.REGULAR);
		userService.registerUser(pass);
	}

	@Test(expected = InvalidDataException.class)
	public void registerSurnameNull() throws EntityAlreadyExistsException, InvalidDataException {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername("TestUser1");
		pass.setPassword("TestUser1");
		pass.setName("TestUser1");
		pass.setSurname(null);
		pass.setBirthDate(new GregorianCalendar(1995,10,5));
		pass.setType(PassengerType.REGULAR);
		userService.registerUser(pass);
	}

	@Test(expected = InvalidDataException.class)
	public void registerBirthDateNull() throws EntityAlreadyExistsException, InvalidDataException {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername("TestUser1");
		pass.setPassword("TestUser1");
		pass.setName("TestUser1");
		pass.setSurname("TestUser1");
		pass.setBirthDate(null);
		pass.setType(PassengerType.REGULAR);
		userService.registerUser(pass);
	}

	@Test(expected = InvalidDataException.class)
	public void registerTypeNull() throws EntityAlreadyExistsException, InvalidDataException {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername("TestUser1");
		pass.setPassword("TestUser1");
		pass.setName("TestUser1");
		pass.setSurname("TestUser1");
		pass.setBirthDate(new GregorianCalendar(1995,10,5));
		pass.setType(null);
		userService.registerUser(pass);
	}
}
