package com.cmc.lgd.localbody.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
		@NamedNativeQuery(query = "select row_number() over() as id, * from get_draft_contributing_dtls_fn(:draftedLBCode, :paramTableName)", name = "Drafted_LB_Coverage_Details", resultClass = DraftedLBCoverageDetails.class),
		@NamedNativeQuery(query = "select row_number() over() as id, lb_lr_code, lb_lr_name_english, lb_lr_name_local, lb_lr_version, coverage_type, lb_lr_type, isheadquarter as headquarter from get_lr_of_lb_level_fn(:localBodyCode, :headquarterCode)", name = "Fetch_Headquarter_Details", resultClass = DraftedLBCoverageDetails.class), })
public class DraftedLBCoverageDetails {

	@Id
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "lb_lr_code", nullable = false)
	private Integer lbLrCode;

	@Column(name = "lb_lr_name_english")
	private String lbLrNameEnglish;

	@Column(name = "lb_lr_name_local")
	private String lbLrNameLocal;

	@Column(name = "lb_lr_version")
	private Integer lbLrVersion;

	@Column(name = "coverage_type")
	private String coverageType;

	@Column(name = "lb_lr_type")
	private String lbLrType;

	@Column(name = "headquarter")
	private Boolean headquarter;

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

	public String getLbLrNameEnglish() {
		return lbLrNameEnglish;
	}

	public void setLbLrNameEnglish(String lbLrNameEnglish) {
		this.lbLrNameEnglish = lbLrNameEnglish;
	}

	public String getLbLrNameLocal() {
		return lbLrNameLocal;
	}

	public void setLbLrNameLocal(String lbLrNameLocal) {
		this.lbLrNameLocal = lbLrNameLocal;
	}

	public Integer getLbLrVersion() {
		return lbLrVersion;
	}

	public void setLbLrVersion(Integer lbLrVersion) {
		this.lbLrVersion = lbLrVersion;
	}

	public String getCoverageType() {
		return coverageType;
	}

	public void setCoverageType(String coverageType) {
		this.coverageType = coverageType;
	}

	public String getLbLrType() {
		return lbLrType;
	}

	public void setLbLrType(String lbLrType) {
		this.lbLrType = lbLrType;
	}

	public Boolean getHeadquarter() {
		return headquarter;
	}

	public void setHeadquarter(Boolean headquarter) {
		this.headquarter = headquarter;
	}

}
