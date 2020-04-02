package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
	/**
	 * Modified by pooja on 28-10-2015
	 * remove parameter slc
	 */
@NamedNativeQuery(query = "select * from get_admin_coverage_Detail(:adminEntityCode)", name = "adminCoverageDetails", resultClass = AdministrativeEntityCoverage.class),

/**
 * Named Query added for the draft coverages
 */
@NamedNativeQuery(query = "select * from get_admin_coverage_detail_for_Draft(:adminEntityCode)", name = "adminCoverageDetailsForDraft", resultClass = AdministrativeEntityCoverage.class),
})
public class AdministrativeEntityCoverage {
	@Id
	@Column(name = "entity_code")
	private Integer entityCode;
	@Column(name="entity_name")
	private String entityName;
	@Column(name="entity_type")
	private char entiyType;
	@Column(name="entity_type_name")
	private String entityTypeName;
	@Column(name="coverage_type")
	private String coverageType;
	@Column(name = "lb_code_for_ward")
	private Integer lbCodeForWard;
	
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
	public char getEntiyType() {
		return entiyType;
	}
	public void setEntiyType(char entiyType) {
		this.entiyType = entiyType;
	}
	public String getEntityTypeName() {
		return entityTypeName;
	}
	public void setEntityTypeName(String entityTypeName) {
		this.entityTypeName = entityTypeName;
	}
	public String getCoverageType() {
		return coverageType;
	}
	public void setCoverageType(String coverageType) {
		this.coverageType = coverageType;
	}
	public Integer getLbCodeForWard() {
		return lbCodeForWard;
	}
	public void setLbCodeForWard(Integer lbCodeForWard) {
		this.lbCodeForWard = lbCodeForWard;
	}
	
	
	
	
}
