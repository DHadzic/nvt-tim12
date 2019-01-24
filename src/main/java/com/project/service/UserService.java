package com.project.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.domain.Authority;
import com.project.domain.Passenger;
import com.project.domain.Ticket;
import com.project.domain.UserAuthority;
import com.project.domain.Validator;
import com.project.exceptions.EntityAlreadyExistsException;
import com.project.exceptions.InvalidDataException;
import com.project.repository.AuthorityRepository;
import com.project.repository.UserAuthorityRepository;
import com.project.repository.UserRepository;
import com.project.web.dto.PassengerDTO;
import com.project.web.dto.ValidatorDTO;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthorityRepository authRepository;

	@Autowired
	private UserAuthorityRepository userAuthRepository;

	public void registerUser(PassengerDTO passengerDTO) throws EntityAlreadyExistsException, InvalidDataException {
		if(passengerDTO == null) {
			throw new InvalidDataException("Data is null");
		}
				
		Passenger passenger = new Passenger();
		
		if(passengerDTO.getUsername() == null) {
			throw new InvalidDataException("Username is null");
		}
		if(passengerDTO.getPassword() == null) {
			throw new InvalidDataException("Password is null");
		}
		if(passengerDTO.getName() == null) {
			throw new InvalidDataException("Name is null");
		}
		if(passengerDTO.getSurname() == null) {
			throw new InvalidDataException("Surname is null");
		}
		if(passengerDTO.getBirthDate() == null) {
			throw new InvalidDataException("BirthDate is null");
		}
		if(passengerDTO.getType() == null) {
			throw new InvalidDataException("Type is null");
		}

		if(userRepository.findByUsername(passengerDTO.getUsername()) != null) {
			throw new EntityAlreadyExistsException("Username taken");
		}
		
		if(passengerDTO.getUsername().length() < 3 || passengerDTO.getUsername().length() > 20)
			throw new InvalidDataException("Username format");
		if(passengerDTO.getPassword().length() < 3 || passengerDTO.getPassword().length() > 20)
			throw new InvalidDataException("Password format");
		if(passengerDTO.getName().length() < 3 || passengerDTO.getName().length() > 20)
			throw new InvalidDataException("Name format");
		if(passengerDTO.getSurname().length() < 3 || passengerDTO.getSurname().length() > 20)
			throw new InvalidDataException("Surname format");
		if(passengerDTO.getBirthDate().get(Calendar.YEAR) < 1993 || passengerDTO.getBirthDate().get(Calendar.YEAR) > 2010) {
			throw new InvalidDataException("Date format");
		}
		
		passenger.setUsername(passengerDTO.getUsername());
		passenger.setPassword(passengerDTO.getPassword());
		passenger.setName(passengerDTO.getName());
		passenger.setSurname(passengerDTO.getSurname());
		passenger.setType(passengerDTO.getType());
		passenger.setBirthDate(passengerDTO.getBirthDate());
		passenger.setTikcet(new ArrayList<Ticket>());
		
		UserAuthority authorities = new UserAuthority();
		
		Authority auth = authRepository.findByName("PASSENGER_ROLE");
		
		auth.getUserAuthorities().add(authorities);
		authorities.setAuthority(auth);
		authorities.setUser(passenger);
		passenger.getUserAuthorities().add(authorities);
		BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
		System.out.println(enc.encode(passenger.getPassword()));
		passenger.setPassword(enc.encode(passenger.getPassword()));
		userRepository.save(passenger);
		
		return;
		
	}
	
	public boolean changePassenger(PassengerDTO passengerDTO) {
		
		Passenger passenger = (Passenger) userRepository.findByUsername(passengerDTO.getUsername());
		
		if(passenger == null) {
			return false;
		}
		
		passenger.setPassword(passengerDTO.getPassword());
		passenger.setName(passengerDTO.getName());
		passenger.setSurname(passengerDTO.getSurname());
		passenger.setType(passengerDTO.getType());
		passenger.setBirthDate(passenger.getBirthDate());
		
		try {
			userRepository.save(passenger);			
		}catch(Exception e) {
			return false;
		}
		
		return true;
	}
	
	public boolean deletePassenger(String username) {
		
		Passenger passenger = (Passenger) userRepository.findByUsername(username);
		
		if(passenger == null) {
			return false;
		}
		
		try {
			userRepository.delete(passenger);
		}catch(Exception e) {
			return false;
		}
		
		return true;
	}
	
	public boolean addValidator(ValidatorDTO validatorDTO) {
		
		Validator validator = (Validator) userRepository.findByUsername(validatorDTO.getUsername());
		
		if(validator != null) {
			return false;
		}
		
		validator = new Validator();
		
		validator.setUsername(validatorDTO.getUsername());
		validator.setPassword(validatorDTO.getPassword());
		validator.setName(validatorDTO.getName());
		validator.setSurname(validatorDTO.getSurname());
		
		return true;
	}

	public boolean deleteValidator(String username) {
		
		Validator validator = (Validator) userRepository.findByUsername(username);
		
		if(validator == null) {
			return false;
		}
		
		try {
			userRepository.delete(validator);
		}catch(Exception e) {
			return false;
		}
		
		return true;
	}
}
