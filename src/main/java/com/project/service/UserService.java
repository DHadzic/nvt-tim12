package com.project.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.domain.Authority;
import com.project.domain.Passenger;
import com.project.domain.PassengerType;
import com.project.domain.Ticket;
import com.project.domain.User;
import com.project.domain.UserAuthority;
import com.project.domain.Validator;
import com.project.exceptions.EntityAlreadyExistsException;
import com.project.exceptions.EntityDoesNotExistException;
import com.project.exceptions.InvalidDataException;
import com.project.repository.AuthorityRepository;
import com.project.repository.UserAuthorityRepository;
import com.project.repository.UserRepository;
import com.project.repository.ValidatorRepository;
import com.project.web.dto.PassengerDTO;
import com.project.web.dto.ValidatorDTO;
import com.project.web.dto.VerifyRequestDTO;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthorityRepository authRepository;

	@Autowired
	private UserAuthorityRepository userAuthRepository;
	
	@Autowired
	private ValidatorRepository validatorRepository;
	
	private ArrayList<VerifyRequestDTO> req = new ArrayList<VerifyRequestDTO>();

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
		passenger.setTikcets(new ArrayList<Ticket>());
		
		if (passenger.getType() == PassengerType.REGULAR){
			passenger.setVerified(true);
		}else{
			passenger.setVerified(false);
		}
		
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
	
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public boolean checkVerification(String username) throws EntityDoesNotExistException {
		
		Passenger passenger = (Passenger) userRepository.findByUsername(username);
		
		if(passenger == null){
			throw new EntityDoesNotExistException("Passenger not found.");
		}

		if (passenger.getDocumentID() == null && passenger.getType() != PassengerType.REGULAR){
			return false;
		}else{
			return true;
		}
	}
	
	public void setUserIdDocument(String username, String image) throws EntityDoesNotExistException, InvalidDataException {
		if (username == null || image == null || username == "" || image == "") throw new InvalidDataException("Parameters can not be null or empty string.");
		Passenger passenger = findPassenger(username);
		passenger.setDocumentID(image);
		sendVerificationRequest(username, passenger.getType(), image);
		userRepository.save(passenger);
	}
	
	public ArrayList<VerifyRequestDTO> getVerifyRequests(String username) throws EntityDoesNotExistException{
		Validator validator = (Validator) userRepository.findByUsername(username);
		if (validator == null) throw new EntityDoesNotExistException("Validator does no exist.");
		ArrayList<String> requests = validator.getVerificationRequest();
		if (requests == null) return new ArrayList<VerifyRequestDTO>();
		return req;
	}
	
	public void verifyPassenger(String username) throws EntityDoesNotExistException, InvalidDataException {
		if (username == null || username == "") throw new InvalidDataException("Username can not be null or empty string.");
		Passenger passenger = findPassenger(username);
		passenger.setVerified(true);
		closeVerificationRequest(username);
		userRepository.save(passenger);
	}
	
	public void rejectPassengerVerification(String username) throws EntityDoesNotExistException, InvalidDataException {
		if (username == null || username == "") throw new InvalidDataException("Username can not be null or empty string.");
		Passenger passenger = findPassenger(username);
		passenger.setDocumentID(null);
		closeVerificationRequest(username);
		userRepository.save(passenger);
	}
	
	private Passenger findPassenger(String username) throws EntityDoesNotExistException{
		Passenger passenger = (Passenger) userRepository.findByUsername(username);
		if (passenger == null) throw new EntityDoesNotExistException("Passenger not found");
		return passenger;
	}
	
	private void sendVerificationRequest(String username, PassengerType type, String image){
		ArrayList<Validator> validators = (ArrayList<Validator>) validatorRepository.findAll();
		ArrayList<VerifyRequestDTO> dtos = new ArrayList<VerifyRequestDTO>();
		for (Validator v : validators){
			VerifyRequestDTO vr = new VerifyRequestDTO();
			vr.setUsername(username);
			vr.setImage(image);
			vr.setType(type);
			ArrayList<String> requests = v.getVerificationRequest();
			if (requests == null){
				requests = new ArrayList<String>();
			}
			req.add(vr);
			requests.add(username);
			v.setVerificationRequest(requests);
		}
		userRepository.saveAll(validators);
	}
	
	private void closeVerificationRequest(String username) {
		ArrayList<Validator> validators = (ArrayList<Validator>) validatorRepository.findAll();
		for (Validator v : validators){
			if (v.getVerificationRequest() == null) continue;
			v.getVerificationRequest().remove(username);
		}
		userRepository.saveAll(validators);
	}
}
