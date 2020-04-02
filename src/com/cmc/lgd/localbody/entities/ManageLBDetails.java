package com.cmc.lgd.localbody.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Transient;

@Entity
@NamedNativeQueries({ 
	@NamedNativeQuery(query = "SELECT * FROM get_lb_child_list_by_category_fn(:lbCreationType, :localBodyType, :stateCode , :parentLBCode, :districtCode);", name = "Fetch_Published_Local_Bodies", resultClass = ManageLBDetails.class),
})
public class ManageLBDetails {
	/*
	 * Output columns to display local body details. 
	 */
	@Id
	@Column(name = "local_body_code", nullable = false)
	private Integer localBodyCode;
	
	@Column(name = "local_body_name_english")
	private String localBodyNameEnglish;
	
	@Column(name = "local_body_name_local")
	private String localBodyNameLocal;
	
	@Column(name = "local_body_type_code")
	private String localBodyTypeCode;
	
	@Column(name = "local_body_type_name")
	private String localBodyTypeName;
	
	@Column(name = "is_drafted")
	private Boolean isDrafted;
	
	@Column(name = "is_land_region")
	private Boolean isLandRegion;
	
	/*
	 * Input parameters for fetching drafted local body details. 
	 */
	@Transient
	private String lbTypeHierarchy;
	
	@Transient
	private String paramLocalBodyTypeCode;
	
	@Transient
	private String localBodyCreationType;
	
	@Transient
	private String localBodyLevelCodes;
	
	@Transient
	private String isGISCoverage;
	
	@Transient
	private boolean updateApprovedGP;
	

	//Default Constructor
	public ManageLBDetails() {
		super();
	}

	public Integer getLocalBodyCode() {
		return localBodyCode;
	}

	public void setLocalBodyCode(Integer localBodyCode) {
		this.localBodyCode = localBodyCode;
	}

	public String getLocalBodyNameEnglish() {
		return localBodyNameEnglish;
	}

	public String getLocalBodyNameLocal() {
		return localBodyNameLocal;
	}

	public void setLocalBodyNameLocal(String localBodyNameLocal) {
		this.localBodyNameLocal = localBodyNameLocal;
	}

	public String getLocalBodyTypeCode() {
		return localBodyTypeCode;
	}

	public void setLocalBodyTypeCode(String localBodyTypeCode) {
		this.localBodyTypeCode = localBodyTypeCode;
	}

	public String getLocalBodyTypeName() {
		return localBodyTypeName;
	}

	public void setLocalBodyTypeName(String localBodyTypeName) {
		this.localBodyTypeName = localBodyTypeName;
	}

	public Boolean getIsDrafted() {
		return isDrafted;
	}

	public void setIsDrafted(Boolean isDrafted) {
		this.isDrafted = isDrafted;
	}

	public void setLocalBodyNameEnglish(String localBodyNameEnglish) {
		this.localBodyNameEnglish = localBodyNameEnglish;
	}

	public String getParamLocalBodyTypeCode() {
		return paramLocalBodyTypeCode;
	}

	public void setParamLocalBodyTypeCode(String paramLocalBodyTypeCode) {
		this.paramLocalBodyTypeCode = paramLocalBodyTypeCode;
	}

	public String getLocalBodyCreationType() {
		return localBodyCreationType;
	}

	public void setLocalBodyCreationType(String localBodyCreationType) {
		this.localBodyCreationType = localBodyCreationType;
	}

	public String getLocalBodyLevelCodes() {
		return localBodyLevelCodes;
	}

	public void setLocalBodyLevelCodes(String localBodyLevelCodes) {
		this.localBodyLevelCodes = localBodyLevelCodes;
	}

	public String getLbTypeHierarchy() {
		return lbTypeHierarchy;
	}

	public void setLbTypeHierarchy(String lbTypeHierarchy) {
		this.lbTypeHierarchy = lbTypeHierarchy;
	}

	public Boolean getIsLandRegion() {
		return isLandRegion;
	}

	public void setIsLandRegion(Boolean isLandRegion) {
		this.isLandRegion = isLandRegion;
	}

	public String getIsGISCoverage() {
		return isGISCoverage;
	}

	public void setIsGISCoverage(String isGISCoverage) {
		this.isGISCoverage = isGISCoverage;
	}

	public boolean isUpdateApprovedGP() {
		return updateApprovedGP;
	}

	public void setUpdateApprovedGP(boolean updateApprovedGP) {
		this.updateApprovedGP = updateApprovedGP;
	}
	
	
}
