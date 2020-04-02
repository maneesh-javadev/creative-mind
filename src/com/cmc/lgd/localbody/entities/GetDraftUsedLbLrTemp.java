package com.cmc.lgd.localbody.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
/**
 * 
 * @author Ripunj
 *
 */
@Entity
@NamedNativeQueries({ @NamedNativeQuery(query = "select row_number() over() as id, * from get_draft_used_lb_lr_temp(:draftedLBCode)", name = "Get_Draft_Used_Lb_Lr_Temp", resultClass = GetDraftUsedLbLrTemp.class), })
public class GetDraftUsedLbLrTemp {

	@Id
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "lb_lr_code", nullable = false)
	private Integer lbLrCode;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLbLrCode() {
		return lbLrCode;
	}

	public void setLbLrCode(Integer lbLrCode) {
		this.lbLrCode = lbLrCode;
	}
	
	
}
