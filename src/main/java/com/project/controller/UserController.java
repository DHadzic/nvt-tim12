package com.project.controller;

import java.util.ArrayList;

import javax.naming.AuthenticationException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.PassengerType;
import com.project.exceptions.EntityAlreadyExistsException;
import com.project.exceptions.EntityDoesNotExistException;
import com.project.exceptions.InvalidDataException;
import com.project.security.TokenUtils;
import com.project.service.UserService;
import com.project.web.dto.LoginDTO;
import com.project.web.dto.PassengerDTO;
import com.project.web.dto.ValidatorDTO;
import com.project.web.dto.VerifyRequestDTO;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UserService userService;
		
	@Autowired
	TokenUtils tokenUtils;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        try {
        	// Perform the authentication
        	UsernamePasswordAuthenticationToken token = 
        			new UsernamePasswordAuthenticationToken(
					loginDTO.getUsername(), loginDTO.getPassword());
            Authentication authentication = authenticationManager.authenticate(token);            
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Reload user details so we can generate token
            UserDetails details = userDetailsService.
            		loadUserByUsername(loginDTO.getUsername());
            return new ResponseEntity<String>(
            		tokenUtils.generateToken(details), HttpStatus.OK);
        } catch (Exception ex) {
        	System.out.println(ex.getMessage());
            return new ResponseEntity<String>("Invalid login", HttpStatus.BAD_REQUEST);
        }
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody PassengerDTO passengerDTO) {
		
		try {
			userService.registerUser(passengerDTO);
		} catch (InvalidDataException | EntityAlreadyExistsException e) {
			return new ResponseEntity<String>("Invalid register.." + e.getMessage() ,HttpStatus.BAD_REQUEST);
		}
		
        return new ResponseEntity<String>("Successful register",HttpStatus.OK);
	}

	@RequestMapping(value = "/change_profile", method = RequestMethod.POST)
	public ResponseEntity<String> changeProfile(@RequestBody PassengerDTO passengerDTO) {
		
		if(!userService.changePassenger(passengerDTO)) {
			return new ResponseEntity<String>("Profile change invalid",HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<String>("Profile changed successfully",HttpStatus.OK);
	}

	@RequestMapping(value = "/delete_passenger{username}", method = RequestMethod.POST)
	public ResponseEntity<String> deletePassenger(@PathVariable("username") String username) {
		
		if(!userService.deletePassenger(username)) {
			return new ResponseEntity<String>("Passenger removal invalid",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Passenger removal successful",HttpStatus.OK);
	}
	
	@RequestMapping(value = "/add_validator", method = RequestMethod.POST)
	public ResponseEntity<String> addValidator(@RequestBody ValidatorDTO validatorDTO) {
		
		if(!userService.addValidator(validatorDTO)) {
			return new ResponseEntity<String>("Invalid validator add request",HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<String>("Validator added successfully",HttpStatus.OK);
	}

	@RequestMapping(value = "/delete_validator{username}", method = RequestMethod.POST)
	public ResponseEntity<String> changeProfile(@PathVariable("username") String username) {
		
		if(!userService.deleteValidator(username)) {
			return new ResponseEntity<String>("Passenger removal invalid",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Passenger removal successful",HttpStatus.OK);
	}
	
	@RequestMapping(value = "/checkVerification/{username}", method = RequestMethod.GET)
	public ResponseEntity<String> checkVerification(@PathVariable("username") String username) {
		try{
			if (userService.checkVerification(username)){
				return new ResponseEntity<String>("Verified", HttpStatus.OK);
			}else{
				return new ResponseEntity<String>("Not verified", HttpStatus.OK);
			}
		}catch (EntityDoesNotExistException edne){
			return new ResponseEntity<String>("Passenger not found.", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/saveUserDocument/{username}", method = RequestMethod.POST)
	public ResponseEntity<String> saveUserId(@PathVariable("username") String username, @RequestBody String image){
		try{
			userService.setUserIdDocument(username, image);
			return new ResponseEntity<String>("Document image added.", HttpStatus.OK);
		}catch (EntityDoesNotExistException edne){
			return new ResponseEntity<String>("Document image not added	.", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/getVerifyRequests/{username}", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<VerifyRequestDTO>> getVerificationRequests(@PathVariable("username") String username) throws EntityDoesNotExistException {
		try {
			ArrayList<VerifyRequestDTO> requests = userService.getVerifyRequests(username);
			return new ResponseEntity<ArrayList<VerifyRequestDTO>>(requests, HttpStatus.OK);
		}catch (EntityDoesNotExistException edne){
			return new ResponseEntity<ArrayList<VerifyRequestDTO>>(new ArrayList<VerifyRequestDTO>(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@RequestMapping(value = "/verify/{username}", method = RequestMethod.POST)
	public ResponseEntity<String> verifyPassenger(@PathVariable("username") String username){
		try{
			userService.verifyPassenger(username);
			return new ResponseEntity<String>("Passenger verified.", HttpStatus.OK);
		}catch (EntityDoesNotExistException edne) {
			return new ResponseEntity<String>(edne.getMessage(), HttpStatus.BAD_REQUEST);

		}
	}
	
	@RequestMapping(value = "/rejectVerification/{username}", method = RequestMethod.POST)
	public ResponseEntity<String> rejectPassengerVerification(@PathVariable("username") String username){
		try{
			userService.rejectPassengerVerification(username);
			return new ResponseEntity<String>("Verification rejected.", HttpStatus.OK);
		}catch (EntityDoesNotExistException edne) {
			return new ResponseEntity<String>(edne.getMessage(), HttpStatus.BAD_REQUEST);

		}
	}
}