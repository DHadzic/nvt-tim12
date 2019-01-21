package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.BusStation;

public interface BusStationRepository  extends JpaRepository<BusStation, Long>{
	BusStation findByLat(String lat);

}
