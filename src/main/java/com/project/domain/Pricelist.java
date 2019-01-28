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
	private Date formed;

	@Column
	private Date reactivated;
	
	@Column
	private Date invalidated;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate_formed() {
		return formed;
	}

	public void setDate_formed(Date date_formed) {
		this.formed = date_formed;
	}

	public Date getDate_invalidated() {
		return invalidated;
	}

	public Date getDate_reactivated() {
		return reactivated;
	}

	public void setDate_reactivated(Date date_reactivated) {
		this.reactivated = date_reactivated;
	}

	public void setDate_invalidated(Date date_invalidated) {
		this.invalidated = date_invalidated;
	}

	public Pricelist() {
		super();
		this.formed = new Date();
		this.invalidated = null;
	}

	public Pricelist(Long id, Date formed, Date reactivated, Date invalidated) {
		super();
		this.id = id;
		this.formed = formed;
		this.reactivated = reactivated;
		this.invalidated = invalidated;
	}
	
}
