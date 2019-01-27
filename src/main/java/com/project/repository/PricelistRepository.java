package com.project.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.Pricelist;

public interface PricelistRepository  extends  JpaRepository<Pricelist, Long> {
	Pricelist findTopByOrderByIdDesc();
	ArrayList<Pricelist> findByDateInvalidatedIsNull();
	Optional<Pricelist> findById(Long id);
}
