package in.nic.pes.lgd.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "map_org_coverage_seq", initialValue=1, allocationSize=1, sequenceName = "map_org_coverage")
@Table(name="org_coverage")
public class OrganizationCoverage implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int orgCoverageId;
	private Integer orgCoverageEntityType;
	private char coverage;
	private Integer coverageDetailCode;
	private boolean isactive;
	private Integer orgLocatedLevelCode;
	private boolean orguntDone;
	
	public OrganizationCoverage() {
	}

	
// TODO Remove unused code found by UCDetector
// 	public OrganizationCoverage(int orgCoverageId, int orgCoverageEntityType,
// 			char coverage, Integer coverageDetailCode, boolean isactive,
// 			Integer orgLocatedLevelCode) {
// 		super();
// 		this.orgCoverageId = orgCoverageId;
// 		this.orgCoverageEntityType = orgCoverageEntityType;
// 		this.coverage = coverage;
// 		this.setCoverageDetailCode(coverageDetailCode);
// 		this.isactive = isactive;
// 		this.orgLocatedLevelCode = orgLocatedLevelCode;
// 	}

	/*@GenericGenerator
    (name = "sequence", strategy = "sequence", parameters={@Parameter(name="sequence",value="map_org_coverage")})*/
    /*@GeneratedValue(generator = "sequence")*/
	@Id
	@GeneratedValue(generator = "map_org_coverage_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "org_coverage_id", unique = true, nullable = false)
	public int getOrgCoverageId() {
		return orgCoverageId;
	}
	public void setOrgCoverageId(int orgCoverageId) {
		this.orgCoverageId = orgCoverageId;
	}
	
	@Column(name="org_coverage_entity_type")
	public int getOrgCoverageEntityType() {
		return orgCoverageEntityType;
	}
	public void setOrgCoverageEntityType(int orgCoverageEntityType) {
		this.orgCoverageEntityType = orgCoverageEntityType;
	}
	@Column(name="coverage")
	public char getCoverage() {
		return coverage;
	}
	public void setCoverage(char coverage) {
		this.coverage = coverage;
	}
	@Column(name="isactive")
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	@Column(name="coverage_detail_code", nullable=true)
	public Integer getCoverageDetailCode() {
		return coverageDetailCode;
	}


	public void setCoverageDetailCode(Integer coverageDetailCode) {
		this.coverageDetailCode = coverageDetailCode;
	}

	@Column(name="org_located_level_code")
	public Integer getOrgLocatedLevelCode() {
		return orgLocatedLevelCode;
	}

	public void setOrgLocatedLevelCode(Integer orgLocatedLevelCode) {
		this.orgLocatedLevelCode = orgLocatedLevelCode;
	}


	@Column(name = "org_unt_done", nullable=false)
	public boolean isOrguntDone() {
		return orguntDone;
	}


	public void setOrguntDone(boolean orguntDone) {
		this.orguntDone = orguntDone;
	}


	
}
