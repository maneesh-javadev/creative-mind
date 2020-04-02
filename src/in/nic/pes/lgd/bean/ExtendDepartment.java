

package in.nic.pes.lgd.bean;

import java.util.List;




public class ExtendDepartment implements java.io.Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 9071626046096487347L;
	private String orgLevelSpecificName;
	private String orgLevelSpecificNameLocal;
	private String orgLevelSpecificShortName;
	private char coverage;
	private Integer coverageDetailCode;
	private Integer orgLocatedLevelCode;
	private List<Integer> entityCode;
	private int orgCoverageId;
	private char locatedAtLevel;
	private Integer dentityCode;
	
	/** added by ripunj on 30-09-2014 */
	private Integer orglocatedlevelcode;
	private Integer adminunitlevelcode;
	private String unitlevelnameenglish;
	private Integer parentOrgLocatedLevelCode;
	
	public String getOrgLevelSpecificName() {
		return orgLevelSpecificName;
	}
	public void setOrgLevelSpecificName(String orgLevelSpecificName) {
		this.orgLevelSpecificName = orgLevelSpecificName;
	}
	public String getOrgLevelSpecificNameLocal() {
		return orgLevelSpecificNameLocal;
	}
	public void setOrgLevelSpecificNameLocal(String orgLevelSpecificNameLocal) {
		this.orgLevelSpecificNameLocal = orgLevelSpecificNameLocal;
	}
	public String getOrgLevelSpecificShortName() {
		return orgLevelSpecificShortName;
	}
	public void setOrgLevelSpecificShortName(String orgLevelSpecificShortName) {
		this.orgLevelSpecificShortName = orgLevelSpecificShortName;
	}
	public char getCoverage() {
		return coverage;
	}
	public void setCoverage(char coverage) {
		this.coverage = coverage;
	}
	public Integer getCoverageDetailCode() {
		return coverageDetailCode;
	}
	public void setCoverageDetailCode(Integer coverageDetailCode) {
		this.coverageDetailCode = coverageDetailCode;
	}
	public List<Integer> getEntityCode() {
		return entityCode;
	}
	public void setEntityCode(List<Integer> entityCode) {
		this.entityCode = entityCode;
	}
	public Integer getOrgLocatedLevelCode() {
		return orgLocatedLevelCode;
	}
	public void setOrgLocatedLevelCode(Integer orgLocatedLevelCode) {
		this.orgLocatedLevelCode = orgLocatedLevelCode;
	}
	public int getOrgCoverageId() {
		return orgCoverageId;
	}
	public void setOrgCoverageId(int orgCoverageId) {
		this.orgCoverageId = orgCoverageId;
	}
	public char getLocatedAtLevel() {
		return locatedAtLevel;
	}
	public void setLocatedAtLevel(char locatedAtLevel) {
		this.locatedAtLevel = locatedAtLevel;
	}
	public Integer getDentityCode() {
		return dentityCode;
	}
	public void setDentityCode(Integer dentityCode) {
		this.dentityCode = dentityCode;
	}
	
	public Integer getAdminunitlevelcode() {
		return adminunitlevelcode;
	}
	public void setAdminunitlevelcode(Integer adminunitlevelcode) {
		this.adminunitlevelcode = adminunitlevelcode;
	}
	public String getUnitlevelnameenglish() {
		return unitlevelnameenglish;
	}
	public void setUnitlevelnameenglish(String unitlevelnameenglish) {
		this.unitlevelnameenglish = unitlevelnameenglish;
	}
	public Integer getOrglocatedlevelcode() {
		return orglocatedlevelcode;
	}
	public void setOrglocatedlevelcode(Integer orglocatedlevelcode) {
		this.orglocatedlevelcode = orglocatedlevelcode;
	}
	public Integer getParentOrgLocatedLevelCode() {
		return parentOrgLocatedLevelCode;
	}
	public void setParentOrgLocatedLevelCode(Integer parentOrgLocatedLevelCode) {
		this.parentOrgLocatedLevelCode = parentOrgLocatedLevelCode;
	}
	
	
}
	