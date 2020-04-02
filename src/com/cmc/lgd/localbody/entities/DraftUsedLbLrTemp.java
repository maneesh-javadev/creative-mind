package com.cmc.lgd.localbody.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "draft_used_lb_lr_temp")
public class DraftUsedLbLrTemp {
	
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@ManyToOne(fetch =  FetchType.LAZY)
	@JoinColumn(name = "draft_lb_temp_id")
	private DraftLocalbodyTemp draftLocalbodyTemp;
		
	@Column(name = "lb_lr_code")
	private  Integer lbLrCode;			
	
	@Column(name = "lb_lr_type")
	private String lbLrType;

	//Default Constructor
	public DraftUsedLbLrTemp() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DraftLocalbodyTemp getDraftLocalbodyTemp() {
		return draftLocalbodyTemp;
	}

	public void setDraftLocalbodyTemp(DraftLocalbodyTemp draftLocalbodyTemp) {
		this.draftLocalbodyTemp = draftLocalbodyTemp;
	}

	public Integer getLbLrCode() {
		return lbLrCode;
	}

	public void setLbLrCode(Integer lbLrCode) {
		this.lbLrCode = lbLrCode;
	}

	public String getLbLrType() {
		return lbLrType;
	}

	public void setLbLrType(String lbLrType) {
		this.lbLrType = lbLrType;
	}
}
