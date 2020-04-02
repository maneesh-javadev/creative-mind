package com.cmc.lgd.localbody.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({ @NamedNativeQuery(query = "select * from get_draft_lb_parent_list(:lbParentCode) order by lb_type_code asc", name = "Local_Body_Parent_Levels", resultClass = LocalBodyParentLevels.class), })
public class LocalBodyParentLevels {

	@Id
	@Column(name = "lbcode", nullable = false)
	private Integer lbCode;

	@Column(name = "lbname_english")
	private String lbNameEnglish;

	@Column(name = "lbname_local")
	private String lbNameLocal;
	
	@Column(name = "parent_code")
	private Integer parentCode;
	
	@Column(name = "lb_type_code")
	private Integer lbTypeCode;
	
	@Column(name = "lb_type_name")
	private String lbTypeName;

	public Integer getLbCode() {
		return lbCode;
	}

	public String getLbNameEnglish() {
		return lbNameEnglish;
	}

	public void setLbNameEnglish(String lbNameEnglish) {
		this.lbNameEnglish = lbNameEnglish;
	}

	public String getLbNameLocal() {
		return lbNameLocal;
	}

	public void setLbNameLocal(String lbNameLocal) {
		this.lbNameLocal = lbNameLocal;
	}

	public Integer getParentCode() {
		return parentCode;
	}

	public void setParentCode(Integer parentCode) {
		this.parentCode = parentCode;
	}

	public Integer getLbTypeCode() {
		return lbTypeCode;
	}

	public void setLbTypeCode(Integer lbTypeCode) {
		this.lbTypeCode = lbTypeCode;
	}

	public void setLbCode(Integer lbCode) {
		this.lbCode = lbCode;
	}

	public String getLbTypeName() {
		return lbTypeName;
	}

	public void setLbTypeName(String lbTypeName) {
		this.lbTypeName = lbTypeName;
	}
}
