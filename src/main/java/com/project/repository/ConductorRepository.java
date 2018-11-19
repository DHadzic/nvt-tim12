package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.Validator;

public interface ConductorRepository extends  JpaRepository<Validator, Long>{

}
