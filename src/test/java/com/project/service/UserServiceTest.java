package com.project.service;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.domain.Passenger;
import com.project.exceptions.EntityAlreadyExistsException;
import com.project.exceptions.InvalidDataException;
import com.project.repository.UserRepository;
import com.project.web.dto.PassengerDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)


public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@MockBean
	private UserRepository userRepository;

	@Before
	public void setUp() {
		Mockito.when(userRepository.findByUsername("TakenUser")).thenReturn(new Passenger());
		Mockito.when(userRepository.findByUsername("cc")).thenReturn(null);
		Mockito.when(userRepository.findByUsername("123456789012345678901")).thenReturn(null);
		Mockito.when(userRepository.findByUsername("TestUser1")).thenReturn(null);
	}
	
	@Test(expected = EntityAlreadyExistsException.class)
	public void registerUsernameTaken() throws EntityAlreadyExistsException, InvalidDataException {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername("TakenUser");
		userService.registerUser(pass);
	}

	@Test(expected = InvalidDataException.class)
	public void registerUsernameShort() throws EntityAlreadyExistsException, InvalidDataException {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername("cc");
		userService.registerUser(pass);
	}

	@Test(expected = InvalidDataException.class)
	public void registerUsernameLong() throws EntityAlreadyExistsException, InvalidDataException {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername("123456789012345678901");
		userService.registerUser(pass);
	}

	@Test(expected = InvalidDataException.class)
	public void registerPasswordShort() throws EntityAlreadyExistsException, InvalidDataException {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername("TestUser1");
		pass.setPassword("cc");
		userService.registerUser(pass);
	}

	@Test(expected = InvalidDataException.class)
	public void registerPasswordLong() throws EntityAlreadyExistsException, InvalidDataException {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername("TestUser1");
		pass.setPassword("123456789012345678901");
		userService.registerUser(pass);
	}

	@Test(expected = InvalidDataException.class)
	public void registerNameShort() throws EntityAlreadyExistsException, InvalidDataException {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername("TestUser1");
		pass.setPassword("TestUser1");
		pass.setName("cc");
		userService.registerUser(pass);
	}

	@Test(expected = InvalidDataException.class)
	public void registerNameLong() throws EntityAlreadyExistsException, InvalidDataException {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername("TestUser1");
		pass.setPassword("TestUser1");
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
		userService.registerUser(pass);
	}

	@Test(expected = InvalidDataException.class)
	public void registerSurnameLong() throws EntityAlreadyExistsException, InvalidDataException {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername("TestUser1");
		pass.setPassword("TestUser1");
		pass.setName("TestUser1");
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
		pass.setBirthDate(new Date(5));
		userService.registerUser(pass);
	}

	@Test(expected = InvalidDataException.class)
	public void registerBirthDateAfter() throws EntityAlreadyExistsException, InvalidDataException {
		PassengerDTO pass = new PassengerDTO();
		pass.setUsername("TestUser1");
		pass.setPassword("TestUser1");
		pass.setName("TestUser1");
		pass.setSurname("TestUser1");
		pass.setBirthDate(new Date(2011, 1, 1));
		userService.registerUser(pass);
	}
	
	//@Test()
	//public void registerSuccessful
}
