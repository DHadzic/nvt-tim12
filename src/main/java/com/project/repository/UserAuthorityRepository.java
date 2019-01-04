package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.UserAuthority;

public interface UserAuthorityRepository extends JpaRepository<UserAuthority, Long>{

}
