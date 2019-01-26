package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.Vehicle;

public interface VehicleRepository extends  JpaRepository<Vehicle, Long>{
	List<Vehicle> findByLineIsNull();
	List<Vehicle> findByLineNotNull();
}
