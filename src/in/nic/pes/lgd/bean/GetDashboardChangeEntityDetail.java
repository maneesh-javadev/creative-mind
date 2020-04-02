package in.nic.pes.lgd.bean;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Transient;

@Entity
@NamedNativeQueries({ 
@NamedNativeQuery(query = "select row_number() OVER () as id, * from get_dashboard_entity_change_deatil(:stateCode,:userType,:flag,:finYear)", name = "DashboardChangeEntityDetailWithFinYearDTO", resultClass = GetDashboardChangeEntityDetail.class),
@NamedNativeQuery(query = "select row_number() OVER () as id, * from get_dashboard_entity_change_deatil(:stateCode,:userType,:flag)", name = "DashboardChangeEntityDetailWithoutFinYearDTO", resultClass = GetDashboardChangeEntityDetail.class)
})
public class GetDashboardChangeEntityDetail {
	

	
	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="state_code")
	private Integer stateCode;
	
	@Column(name="state_name_english")
	private String stateNameEnglish;
	
	@Column(name="entity_code")
	private Integer entityCode;
	
	@Column(name="entity_name")
	private String entityName;
	
	@Column(name="entity_version")
	private Integer entityVersion;
	
	@Column(name="minor_version")
	private Integer minorVersion;
	
	@Transient
	private String flag;
	
	@Transient
	private String finYear;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStateCode() {
		return stateCode;
	}

	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateNameEnglish() {
		return stateNameEnglish;
	}

	public void setStateNameEnglish(String stateNameEnglish) {
		this.stateNameEnglish = stateNameEnglish;
	}

	public Integer getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(Integer entityCode) {
		this.entityCode = entityCode;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public Integer getEntityVersion() {
		return entityVersion;
	}

	public void setEntityVersion(Integer entityVersion) {
		this.entityVersion = entityVersion;
	}

	public Integer getMinorVersion() {
		return minorVersion;
	}

	public void setMinorVersion(Integer minorVersion) {
		this.minorVersion = minorVersion;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFinYear() {
		return finYear;
	}

	public void setFinYear(String finYear) {
		this.finYear = finYear;
	}

	
	
	
	
	
}
