package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.Passenger;
import com.project.domain.Ticket;
import com.project.domain.TicketType;

public interface TicketRepository extends  JpaRepository<Ticket, Long>{
	Ticket findByUserAndType(Passenger p, TicketType tt);
}
