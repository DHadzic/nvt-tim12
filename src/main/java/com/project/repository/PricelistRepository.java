package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.Pricelist;

public interface PricelistRepository  extends  JpaRepository<Pricelist, Long> {
	Pricelist findTopByOrderByIdDesc();
}
