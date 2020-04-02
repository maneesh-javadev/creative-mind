package com.cmc.lgd.localbody.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
@NamedNativeQuery(query = "select * from get_dynamic_lb_hierarchy(:stateCode, :localBodyType)", name ="Dynamic_Local_body_Hierarchy", resultClass = LBTypeHierarchy.class),
@NamedNativeQuery(query = "select * from get_dynamic_lb_hierarchy(:stateCode)", name ="Dynamic_Local_body_Hierarchy_ALL_TYPE", resultClass = LBTypeHierarchy.class)

})
public class LBTypeHierarchy {
	
	@Id
	@Column(name = "plocal_body_setup_code", nullable = false)
	private Integer localBodySetupCode;
	
	@Column(name = "plocal_body_setup_version")
	private Integer localBodySetupVersion;
	
	@Column(name = "phierarchy_name")
	private String localBodyTypeHierarchy;
	
	@Column(name="phierarchy_type")
	private Character hierarchyType;

	public Integer getLocalBodySetupCode() {
		return localBodySetupCode;
	}

	public void setLocalBodySetupCode(Integer localBodySetupCode) {
		this.localBodySetupCode = localBodySetupCode;
	}

	public Integer getLocalBodySetupVersion() {
		return localBodySetupVersion;
	}

	public void setLocalBodySetupVersion(Integer localBodySetupVersion) {
		this.localBodySetupVersion = localBodySetupVersion;
	}

	public String getLocalBodyTypeHierarchy() {
		return localBodyTypeHierarchy;
	}

	public void setLocalBodyTypeHierarchy(String localBodyTypeHierarchy) {
		this.localBodyTypeHierarchy = localBodyTypeHierarchy;
	}

	public Character getHierarchyType() {
		return hierarchyType;
	}

	public void setHierarchyType(Character hierarchyType) {
		this.hierarchyType = hierarchyType;
	}

	
	
	

}
