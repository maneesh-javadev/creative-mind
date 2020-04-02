package com.cmc.lgd.localbody.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
@NamedNativeQuery(query = "SELECT * from get_dynamic_lb_hierarchy_dtl_with_type_category(:setupCode, :setupVersion);", name ="Dynamic_Local_body_Type_Details_with_Category", resultClass = LBTypeDetailsWithCategory.class),
@NamedNativeQuery(query = "SELECT * from get_dynamic_lb_hierarchy_dtl_with_type_category_Cantonment(:setupCode, :setupVersion);", name ="Dynamic_LBTYPE_Details_With_Cantonment_RPT", resultClass = LBTypeDetailsWithCategory.class)
})
public class LBTypeDetailsWithCategory {

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
	
   @Column(name="lbcategory")
   private Character lbCategory; 
	
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

	public Character getLbCategory() {
		return lbCategory;
	}

	public void setLbCategory(Character lbCategory) {
		this.lbCategory = lbCategory;
	}

	
	
	
}
