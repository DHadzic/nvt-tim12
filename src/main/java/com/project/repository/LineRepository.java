package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.Line;

public interface LineRepository  extends  JpaRepository<Line, Long>{
	Line findByName(String name);
	
}
