package in.nic.pes.lgd.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name = "lgd_designation", schema = "public")
public class LgdDesignation implements Serializable, Cloneable{

	/**
	 * 
	 */
	
	
	
	private static final long serialVersionUID = 1L;

/*	@GenericGenerator(name 		= "sequence", 
					  strategy 	= "sequence", 
					  parameters={@Parameter(name="sequence",value="seq_lgd_designation")})
    @GeneratedValue(generator = "sequence")*/
	
	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "designationCode", column = @Column(name = "designation_code")),
		@AttributeOverride(name = "designationVersion", column = @Column(name = "designation_version")) 
	})
	LgdDesignationPK lgdDesignationPK;
	
	
	
	@Column(name = "designation_code",insertable=false,updatable=false)
	private Integer designationCode;
	
	@Column(name = "designation_version",insertable=false,updatable=false)
	private Integer designationVersion;
	
	@Column(name = "designation_name", nullable=false)
	private String designationName;
	
	@Column(name = "designation_name_local")
	private String designationNameLocal;
	
	@Column(name = "designation_type", length=1)
	private String designationType;
	
	@Column(name = "istopdesignation", nullable=false)
	private Boolean isTopDesignation;
	
	@Column(name = "ismultiple", nullable=false)
	private Boolean isMultiple;
	
	@Column(name = "tier_setup_code")
	private Integer tierSetupCode;
	
	@Column(name = "olc")
	private Integer olc;
	
	/*Modified by Pooja on 07-05-2015*/
		
	@OneToMany(fetch =  FetchType.EAGER,  mappedBy = "lgdDesignationCode", cascade={CascadeType.ALL} )
	private List<DesignationLevelSortorder> designationLevelList = new ArrayList<DesignationLevelSortorder>(0);; 
	
	@Column(name = "isactive", nullable=false)
	private Boolean isActive;
	
	@Column(name = "iscontractpermanent")
	private Boolean isContractPermanent;
	
	@Column(name="sort_order")
	private Integer sortOrder;
	
	/**
	 * Column add for handling version in lgd_designation table 
	 * @author Maneesh Kumar Since 14-Dec-2016
	 */
	
	
	@Column(name = "effective_date")
	private Date effectiveDate;
	 
	@Column(name = "lastupdated")
	private Date lastupdated;
	
	
	@Column(name = "lastupdatedby")
	private Integer lastupdatedby;
	
	@Column(name = "createdon")
	private Date createdon;
	
	
	@Column(name = "createdby")
	private Integer createdby;
	

	@Column(name = "transaction_id")
	private Integer transactionId;
	
	@Column(name="designation_added_by")
	private String designationAddedBy;
	
	@Transient
	private boolean nextVersion;
	
	@Transient
	private Integer opeationCode;
	
	@Transient
	private Integer stateCode;
	
	@Transient
	private String  description;
	
	@Transient
	private String otherDesignations;
	
	@Transient
	private Integer orgType;
	
	@Transient
	private String flowName;
	
	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public Integer getOrgType() {
		return orgType;
	}

	public void setOrgType(Integer orgType) {
		this.orgType = orgType;
	}

	public String getOtherDesignations() {
		return otherDesignations;
	}

	public void setOtherDesignations(String otherDesignations) {
		this.otherDesignations = otherDesignations;
	}

	/**
	 * Default Constructor
	 */
	public LgdDesignation() {
		super();
	}

	

	public String getDesignationName() {
		return designationName;
	}

	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}

	public String getDesignationNameLocal() {
		return designationNameLocal;
	}

	public void setDesignationNameLocal(String designationNameLocal) {
		this.designationNameLocal = designationNameLocal;
	}

	public String getDesignationType() {
		return designationType;
	}

	public void setDesignationType(String designationType) {
		this.designationType = designationType;
	}

	public Boolean getIsTopDesignation() {
		return isTopDesignation;
	}

	public void setIsTopDesignation(Boolean isTopDesignation) {
		this.isTopDesignation = isTopDesignation;
	}

	public Boolean getIsMultiple() {
		return isMultiple;
	}

	public void setIsMultiple(Boolean isMultiple) {
		this.isMultiple = isMultiple;
	}

	public Integer getTierSetupCode() {
		return tierSetupCode;
	}

	public void setTierSetupCode(Integer tierSetupCode) {
		this.tierSetupCode = tierSetupCode;
	}

	public Integer getOlc() {
		return olc;
	}

	public void setOlc(Integer olc) {
		this.olc = olc;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public List<DesignationLevelSortorder> getDesignationLevelList() {
		return designationLevelList;
	}

	public void setDesignationLevelList(List<DesignationLevelSortorder> designationLevelList) {
		this.designationLevelList = designationLevelList;
	}

	public Boolean getIsContractPermanent() {
		return isContractPermanent;
	}

	public void setIsContractPermanent(Boolean isContractPermanent) {
		this.isContractPermanent = isContractPermanent;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(Date lastupdated) {
		this.lastupdated = lastupdated;
	}

	public Integer getLastupdatedby() {
		return lastupdatedby;
	}

	public void setLastupdatedby(Integer lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}

	public Date getCreatedon() {
		return createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	public Integer getCreatedby() {
		return createdby;
	}

	public void setCreatedby(Integer createdby) {
		this.createdby = createdby;
	}

	public LgdDesignationPK getLgdDesignationPK() {
		return lgdDesignationPK;
	}

	public void setLgdDesignationPK(LgdDesignationPK lgdDesignationPK) {
		this.lgdDesignationPK = lgdDesignationPK;
	}

	public boolean isNextVersion() {
		return nextVersion;
	}

	public void setNextVersion(boolean nextVersion) {
		this.nextVersion = nextVersion;
	}

	public Integer getDesignationCode() {
		return designationCode;
	}

	public void setDesignationCode(Integer designationCode) {
		this.designationCode = designationCode;
	}

	public Integer getDesignationVersion() {
		return designationVersion;
	}

	public void setDesignationVersion(Integer designationVersion) {
		this.designationVersion = designationVersion;
	}

	public Integer getOpeationCode() {
		return opeationCode;
	}

	public void setOpeationCode(Integer opeationCode) {
		this.opeationCode = opeationCode;
	}

	public Integer getStateCode() {
		return stateCode;
	}

	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDesignationAddedBy() {
		return designationAddedBy;
	}

	public void setDesignationAddedBy(String designationAddedBy) {
		this.designationAddedBy = designationAddedBy;
	}
	
	
	
}

