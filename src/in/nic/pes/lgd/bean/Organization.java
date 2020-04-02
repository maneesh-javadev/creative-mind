package in.nic.pes.lgd.bean;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/*import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Where;*/
 

@Entity
@Table(name = "organization")
@XmlRootElement
public class Organization implements Serializable {
	private static final long serialVersionUID = 1L;

	private OrganizationPK organizationPK;
	private LocalBodyType localBodyType;
	private OrganizationType organizationType;
	private String orgName;
	private String orgNameLocal;
	private String shortName;
	private Integer orgLevel;
	private boolean isactive;
	private Integer parentorgcode;
	private Integer orgCode;
	private Integer orgVersion;
	private Integer orgTypeCode;
	private Integer localBodyTypeCode;
	private Integer olc;
	private Integer slc;
	
	@Column(name = "org_type_code", nullable = false,insertable=false,updatable=false)
	public Integer getOrgTypeCode() {
		return orgTypeCode;
	}

	public void setOrgTypeCode(Integer orgTypeCode) {
		this.orgTypeCode = orgTypeCode;
	}
	
	@Column(name = "local_body_type_code", nullable = false,insertable=false,updatable=false)
	public Integer getLocalBodyTypeCode()
	{
		return localBodyTypeCode;
	}

	public void setLocalBodyTypeCode(Integer localBodyTypeCode) {
		this.localBodyTypeCode = localBodyTypeCode;
	}

	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "olc")
	private Set<OrgLocatedAtLevels> orgLocatedAtLevels=new HashSet<OrgLocatedAtLevels>(0);*/
	
	//private Set<Organization> organizations;
	//private Integer stateCode;
	//private State state;
	private Integer localBodyCode;
	
	/*@JoinColumn(name = "slc", referencedColumnName = "slc")
    @ManyToOne
	private State state;*/
	
	
	
	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "olc")
	private Set<OrganizationDesignation> organizationDesignation=new HashSet<OrganizationDesignation>(0);*/
	
	public Organization() {}
	
	/*public Organization(Integer olc) {
		this.olc=olc;
	}*/

	public Organization(OrganizationPK organizationPK) {
		this.organizationPK = organizationPK;
	}

	
	public Organization(OrganizationPK organizationPK,
			LocalBodyType localBodyType, OrganizationType organizationType,
			String orgName, String orgNameLocal, String shortName,
			int orgLevel, boolean isactive, Integer parentorgcode,
			Integer localBodyCode,
			Set<OrgLocatedAtLevels> orgLocatedAtLevels,
			Set<Organization> organizations,Integer olc) {
		super();
		this.organizationPK = organizationPK;
		//this.localBodyType = localBodyType;
		this.organizationType = organizationType;
		this.orgName = orgName;
		this.orgNameLocal = orgNameLocal;
		this.shortName = shortName;
		this.orgLevel = orgLevel;
		this.isactive = isactive;
		this.parentorgcode = parentorgcode;
		this.localBodyCode = localBodyCode;
		//this.setOrgLocatedAtLevels(orgLocatedAtLevels);
		//this.organizations = organizations;
		//this.state=state;
		this.olc=olc;
	}

	public Organization(OrganizationPK organizationPK, String orgName,
			String shortName, int orgLevel, boolean isactive,Integer olc) {
		this.organizationPK = organizationPK;
		this.orgName = orgName;
		this.shortName = shortName;
		this.orgLevel = orgLevel;
		this.isactive = isactive;
		//this.state=state;
		this.olc=olc;
	}

// TODO Remove unused code found by UCDetector
// 	public Organization(OrganizationPK organizationPK,
// 			OrganizationType organizationType, LocalBodyType localBodyType,
// 			String orgName, String orgNameLocal, String shortName, Integer olc,
// 			int orgLevel, boolean isactive) {
// 		this.organizationPK = organizationPK;
// 		this.organizationType = organizationType;
// 		//this.localBodyType = localBodyType;
// 		this.orgName = orgName;
// 		this.orgNameLocal = orgNameLocal;
// 		this.shortName = shortName;
// 		this.orgLevel = orgLevel;
// 		this.isactive = isactive;
// 		//this.state=state;
// 		this.olc=olc;
// 	}

// TODO Remove unused code found by UCDetector
// 	public Organization(int orgCode, int orgVersion,Integer olc) {
// 		this.organizationPK = new OrganizationPK(orgCode, orgVersion);
// 		this.orgCode=orgCode;
// 		this.orgVersion=orgVersion;
// 		//this.state=state;
// 		this.olc=olc;
// 	}
	
	public Organization(int orgVersion) {
		this.organizationPK = new OrganizationPK(orgVersion);	
		this.orgVersion=orgVersion;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "orgCode", column = @Column(name = "org_code", nullable = false)),
			@AttributeOverride(name = "orgVersion", column = @Column(name = "org_version", nullable = false)) })	
	public OrganizationPK getOrganizationPK() {
		return organizationPK;
	}

	public void setOrganizationPK(OrganizationPK organizationPK) {
		this.organizationPK = organizationPK;
		
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "org_type_code", nullable = false)
	public OrganizationType getOrganizationType() {
		return this.organizationType;
	}

	public void setOrganizationType(OrganizationType organizationType) {
		this.organizationType = organizationType;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "local_body_type_code")
	public LocalBodyType getLocalBodyType() {
		return this.localBodyType;
	}

	public void setLocalBodyType(LocalBodyType localBodyType) {
		this.localBodyType = localBodyType;
	}

	

	@Column(name = "org_code", nullable = false,insertable=false,updatable=false)	
	public Integer getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(Integer orgCode) {
		this.orgCode = orgCode;
	}
	
	
	@Column(name = "org_version", nullable = false,insertable=false,updatable=false)
	public Integer getOrgVersion() {
		return orgVersion;
	}

	public void setOrgVersion(Integer orgVersion) {
		this.orgVersion = orgVersion;
	}

	@Column(name = "org_name", nullable = false, length = 60)
	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Column(name = "org_name_local", length = 80)
	public String getOrgNameLocal() {
		return this.orgNameLocal;
	}

	public void setOrgNameLocal(String orgNameLocal) {
		this.orgNameLocal = orgNameLocal;
	}

	@Column(name = "parent_org_code")
	public Integer getParentorgcode() {
		return parentorgcode;
	}

	public void setParentorgcode(Integer parentorgcode) {
		this.parentorgcode = parentorgcode;
	}

	@Column(name = "short_name", nullable = false, length = 10)
	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Column(name = "org_level", nullable = false, length = 1)
	public int getOrgLevel() {
		return this.orgLevel;
	}

	public void setOrgLevel(int orgLevel) {
		this.orgLevel = orgLevel;
	}

	@Column(name = "isactive", nullable = false)
	public boolean isIsactive() {
		return this.isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	
	

	
/*	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizations")
	
	public Set<Organization> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(Set<Organization> organizations) {
		this.organizations = organizations;
	}*/
	/*@Column(name = "state_code", insertable=false,updatable=false)
	public Integer getStateCode() {
		return stateCode;
	}

	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}*/

	/*@Column(name = "state_version", insertable=false,updatable=false)
	public Integer getStateVersion() {
		return stateVersion;
	}

	public void setStateVersion(Integer stateVersion) {
		this.stateVersion = stateVersion;
	}*/

/*	@ManyToOne(fetch = FetchType.LAZY)
	@Filter(name = "stateFilter",condition="isactive = :isactive")
	@JoinColumns({
			@JoinColumn(name = "slc", referencedColumnName = "slc")
			//,@JoinColumn(name = "isactive", referencedColumnName = "isactive") 
			
			})
	@Where (clause= "isactive=true")*/
	      

	@Column(name="local_body_code")
	public Integer getLocalBodyCode() {
		return localBodyCode;
	}

	public void setLocalBodyCode(Integer localBodyCode) {
		this.localBodyCode = localBodyCode;
	}
	
	/*public State getSlc() {
		return state;
	}

	public void setSlc(State state) {
		this.state = state;
	}*/
	
	@Column(name = "olc")
	public Integer getOlc() {
		return olc;
	}

	public void setOlc(Integer olc) {
		this.olc = olc;
	}
    @Column(name = "slc")
	public Integer getSlc() {
		return slc;
	}

	public void setSlc(Integer slc) {
		this.slc = slc;
	}
	
/*	@XmlTransient
*/	/*public Set<OrgLocatedAtLevels> getOrgLocatedAtLevels() {
		return orgLocatedAtLevels;
	}
	public void setOrgLocatedAtLevels(Set<OrgLocatedAtLevels> orgLocatedAtLevels) {
		this.orgLocatedAtLevels = orgLocatedAtLevels;
	}*/
/*	@XmlTransient
*/	/*public Set<OrganizationDesignation> getOrganizationDesignation() {
		return organizationDesignation;
	}
	public void setOrganizationDesignation(Set<OrganizationDesignation> organizationDesignation) {
		this.organizationDesignation = organizationDesignation;
	}*/

	
}
