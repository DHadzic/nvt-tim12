package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>{

}
