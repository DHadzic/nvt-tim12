package com.project.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.Passenger;
import com.project.domain.Ticket;
import com.project.repository.UserRepository;
import com.project.security.TokenUtils;
import com.project.web.dto.LoginDTO;
import com.project.web.dto.PassengerDTO;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UserRepository userRepository;
	
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
            return new ResponseEntity<String>("Invalid login", HttpStatus.BAD_REQUEST);
        }
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody PassengerDTO passengerDTO) {
		
		if(userRepository.findByUsername(passengerDTO.getUsername()) == null) {
			return new ResponseEntity<String>("Invalid register",HttpStatus.BAD_REQUEST);
		}
		
		Passenger passenger = new Passenger();
		passenger.setUsername(passengerDTO.getUsername());
		passenger.setPassword(passengerDTO.getPassword());
		passenger.setName(passengerDTO.getName());
		passenger.setSurname(passenger.getSurname());
		passenger.setType(passengerDTO.getType());
		passenger.setBirthDate(passengerDTO.getBirthDate());
		passenger.setTikcet(new ArrayList<Ticket>());
		
		userRepository.save(passenger);
		
    	UsernamePasswordAuthenticationToken token = 
    			new UsernamePasswordAuthenticationToken(
				passenger.getUsername(), passenger.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);            
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload user details so we can generate token
        UserDetails details = userDetailsService.
        		loadUserByUsername(passenger.getUsername());

        return new ResponseEntity<String>(
				tokenUtils.generateToken(details),HttpStatus.OK);
	}
}
