package com.project.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pricelist {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private Date date_formed;

	@Column
	private Date date_invalidated;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate_formed() {
		return date_formed;
	}

	public void setDate_formed(Date date_formed) {
		this.date_formed = date_formed;
	}

	public Date getDate_invalidated() {
		return date_invalidated;
	}

	public void setDate_invalidated(Date date_invalidated) {
		this.date_invalidated = date_invalidated;
	}

	public Pricelist() {
		super();
		this.date_formed = new Date();
		this.date_invalidated = null;
	}	
	
}
