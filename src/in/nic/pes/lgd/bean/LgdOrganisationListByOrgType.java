package in.nic.pes.lgd.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="get_organization_list_by_org_type_fn")
public class LgdOrganisationListByOrgType implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	@Id
	@Column(name="org_unit_code")
	private Integer orgUnitCode;
	
	@Column(name="org_code")
	private Integer orgCode;
	
	@Column(name="org_unit_name")
	private String orgUnitName;
	
	@Column(name="org_unit_name_local")
	private String orgUnitNameLocal;
	
	
	public Integer getOrgUnitCode() {
		return orgUnitCode;
	}

	public void setOrgUnitCode(Integer orgUnitCode) {
		this.orgUnitCode = orgUnitCode;
	}

	public Integer getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(Integer orgCode) {
		this.orgCode = orgCode;
	}

	
	

	public String getOrgUnitName() {
		return orgUnitName;
	}

	public void setOrgUnitName(String orgUnitName) {
		this.orgUnitName = orgUnitName;
	}

// TODO Remove unused code found by UCDetector
// 	public static long getSerialversionuid() {
// 		return serialVersionUID;
// 	}

	public String getOrgUnitNameLocal() {
		return orgUnitNameLocal;
	}

	public void setOrgUnitNameLocal(String orgUnitNameLocal) {
		this.orgUnitNameLocal = orgUnitNameLocal;
	}

	
	



}
