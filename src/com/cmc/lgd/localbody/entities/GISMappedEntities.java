package com.cmc.lgd.localbody.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "gis_mapped_entities", schema = "public")
public class GISMappedEntities implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@SequenceGenerator(name = "gis_seq_generator", sequenceName = "gis_entity_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gis_seq_generator")
	private Integer id;

	@Column(name = "entity_code")
	private Integer entityCode;
	
	@Column(name = "status", length=1)
	private String status;

	
	/*
	 * Default Controller
	 */
	public GISMappedEntities() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(Integer entityCode) {
		this.entityCode = entityCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
