package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.Conductor;

public interface ConductorRepository extends  JpaRepository<Conductor, Long>{

}
