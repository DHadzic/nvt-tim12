package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.project.domain.TransportType;
import com.project.domain.Vehicle;

public interface VehicleRepository extends  JpaRepository<Vehicle, Long>{
	List<Vehicle> findByLineIsNull();
	List<Vehicle> findByLineNotNull();
	List<Vehicle> findByType(TransportType type);
	Vehicle findByName(String name);
	@Transactional
	Long deleteByName(String name);
}
