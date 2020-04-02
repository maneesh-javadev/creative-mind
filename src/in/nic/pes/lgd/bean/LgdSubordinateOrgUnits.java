package in.nic.pes.lgd.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "get_subordinate_org_units")
public class LgdSubordinateOrgUnits implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="org_unit_code")
	private Integer orgUnitCode;
	
	@Column(name="org_unit_name")
	private String orgUnitName;
	
	public Integer getOrgUnitCode() {
		return orgUnitCode;
	}

	public void setOrgUnitCode(Integer orgUnitCode) {
		this.orgUnitCode = orgUnitCode;
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


}
