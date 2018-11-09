package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.Passenger;

public interface PassangerRepository extends  JpaRepository<Passenger, Long>{

}
