package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.Ticket;

public interface TicketRepository extends  JpaRepository<Ticket, Long>{

}
