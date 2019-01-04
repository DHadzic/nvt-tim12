package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long>{
	Authority findByName(String name);
}
