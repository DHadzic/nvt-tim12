package com.project.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BusStation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
}
