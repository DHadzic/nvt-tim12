package com.project.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.Passenger;
import com.project.domain.Ticket;
import com.project.domain.TicketType;
import com.project.domain.User;

public interface TicketRepository extends  JpaRepository<Ticket, Long>{
	Ticket findByUserAndType(Passenger p, TicketType tt);
	ArrayList<Ticket> findByUser(User u);
	ArrayList<Ticket> findByIsActiveTrue();
}
