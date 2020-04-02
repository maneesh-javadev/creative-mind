package com.cmc.lgd.localbody.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
@NamedNativeQuery(query = "SELECT * from get_dynamic_lb_hierarchy_dtl_with_type(:setupCode, :setupVersion);", name ="Dynamic_Local_body_Type_Details", resultClass = LBTypeDetails.class),
@NamedNativeQuery(query = "Select * from get_statewise_lb_type_details(:stateCode, :panchayatType);", name ="Urban_Local_body_Type_Details", resultClass = LBTypeDetails.class),
@NamedNativeQuery(query = "SELECT tier_setup_code, local_body_type_code, local_body_type_name as name, parent_tier_setup_code, level as lblevel, category as lbType FROM get_local_gov_setup_fn(:stateCode, :panchayatType) where local_body_type_code <> :selectedLBType", name ="Excluded_Selection_Urban_LB_Type", resultClass = LBTypeDetails.class),
})
public class LBTypeDetails {

	@Id
	@Column(name = "tier_setup_code", nullable = false)
	private Integer tierSetupCode;
	
	@Column(name = "local_body_type_code", nullable = false)
	private Integer localBodyTypeCode;
	
	@Column(name = "lbtype")
	private Character lbType;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "parent_tier_setup_code")
	private Integer parentTierSetupCode;

	@Column(name = "lblevel")
	private Character lbLevel;
	
	public Integer getTierSetupCode() {
		return tierSetupCode;
	}

	public void setTierSetupCode(Integer tierSetupCode) {
		this.tierSetupCode = tierSetupCode;
	}

	public Integer getLocalBodyTypeCode() {
		return localBodyTypeCode;
	}

	public void setLocalBodyTypeCode(Integer localBodyTypeCode) {
		this.localBodyTypeCode = localBodyTypeCode;
	}

	public Character getLbType() {
		return lbType;
	}

	public void setLbType(Character lbType) {
		this.lbType = lbType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentTierSetupCode() {
		return parentTierSetupCode;
	}

	public void setParentTierSetupCode(Integer parentTierSetupCode) {
		this.parentTierSetupCode = parentTierSetupCode;
	}

	public Character getLbLevel() {
		return lbLevel;
	}

	public void setLbLevel(Character lbLevel) {
		this.lbLevel = lbLevel;
	}
}
