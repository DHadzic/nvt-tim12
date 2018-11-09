package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.Vehicle;

public interface VehicleRepository extends  JpaRepository<Vehicle, Long>{

}
