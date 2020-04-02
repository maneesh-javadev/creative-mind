package in.nic.pes.lgd.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@SequenceGenerator(name = "map_org_coverage_detail_seq", initialValue=1, allocationSize=1, sequenceName = "map_org_coverage_detail")
@Table(name="org_coverage_detail")
public class OrganizationCoverageDetail implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int orgCoverageDetailId;
	private int coverageCode;
	private int entityCode;
	private boolean isactive;
	private boolean orguntDone;
	private Integer orgCoverageEntityType;
	private Integer parentEntityCode;
	public OrganizationCoverageDetail() {
		super();
	}

// TODO Remove unused code found by UCDetector
// 	public OrganizationCoverageDetail(int orgCoverageDetailId,
// 			int coverageCode, int entityCode, boolean isactive) {
// 		super();
// 		this.orgCoverageDetailId = orgCoverageDetailId;
// 		this.coverageCode = coverageCode;
// 		this.entityCode = entityCode;
// 		this.isactive = isactive;
// 	}
	
	/*@GenericGenerator
    (name = "sequence", strategy = "sequence", parameters={@Parameter(name="sequence",value="map_org_coverage_detail")})
	@GeneratedValue(generator = "sequence")*/
    @Id
	@GeneratedValue(generator = "map_org_coverage_detail_seq", strategy = GenerationType.SEQUENCE)
    @Column(name="org_coverage_detail_id", unique = true, nullable = false)
	public int getOrgCoverageDetailId() {
		return orgCoverageDetailId;
	}
	public void setOrgCoverageDetailId(int orgCoverageDetailId) {
		this.orgCoverageDetailId = orgCoverageDetailId;
	}
	@Column(name="coverage_code")
	public int getCoverageCode() {
		return coverageCode;
	}
	public void setCoverageCode(int coverageCode) {
		this.coverageCode = coverageCode;
	}
	@Column(name="entity_code")
	public int getEntityCode() {
		return entityCode;
	}
	public void setEntityCode(int entityCode) {
		this.entityCode = entityCode;
	}
	@Column(name="isactive")
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	@Column(name = "org_unt_done", nullable=false)
	public boolean isOrguntDone() {
		return orguntDone;
	}

	public void setOrguntDone(boolean orguntDone) {
		this.orguntDone = orguntDone;
	}

	@Transient
	public Integer getOrgCoverageEntityType() {
		return orgCoverageEntityType;
	}

	public void setOrgCoverageEntityType(Integer orgCoverageEntityType) {
		this.orgCoverageEntityType = orgCoverageEntityType;
	}

	@Transient
	public Integer getParentEntityCode() {
		return parentEntityCode;
	}

	public void setParentEntityCode(Integer parentEntityCode) {
		this.parentEntityCode = parentEntityCode;
	}
}
