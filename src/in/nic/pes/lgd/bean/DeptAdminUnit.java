package in.nic.pes.lgd.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "Administration_Unit_Level")
public class DeptAdminUnit implements Serializable {
	private static final long serialVersionUID = 1L;
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "Administration_Unit_Seq") })
	@Id
	@GeneratedValue(generator = "sequence")
	@Column(name = "Admin_unit_level_code", unique = true, nullable = false)
	private Integer adminUnitCode;

	@Column(name = "unit_level_name_english")
	private String adminLevelNameEng;

	@Column(name = "unit_level_name_local")
	private String adminLevelNameLocal;

	@Column(name = "mdds_Entity")
	private boolean revenueEntity;

	@Column(name = "Parent_admin_unit_level_code")
	private Integer parentAdminCode;

	@Column(name = "Slc")
	private Integer slc;

	@Column(name = "Createdby")
	private Integer createdby;

	@Column(name = "Createdon")
	private Date createdon;

	@Column(name = "isActive")
	private boolean isactive;

	/* added by deepti on 06-05-2016 */
	@Column(name = "overlapping_unit")
	private Character overlapUnit;
	
	/**
	 * parent category define Administration Unit Level Lendregion(L),Administration Unit(A) and Localbody (G)
	 */
	@Column(name = "parent_category")
	private Character parentCategory;

	public Character getOverlapUnit() {
		return overlapUnit;
	}

	public void setOverlapUnit(Character overlapUnit) {
		this.overlapUnit = overlapUnit;
	}

	/* ended by deepti */
	/**
	 * Added by ripunj on 20-04-2015 for Administrative Unit Level Draft Mode
	 */
	@Column(name = "isPublish", unique = true, nullable = false)
	private Character ispublish;
	@Transient
	private Character buttonClicked;

	@Transient
	private Integer orgLocatedLevelCode;

	@Transient
	private Integer parentOrgLocatedLevelCode;

	@Transient
	private Integer seqLevel;

	@Transient
	private Integer sortOrder;
	@Transient
	private OrgLocatedAtLevels orgLocatedAtLevels;
	@Transient
	private String hierarchy;
	@Transient
	
	private String unitLevelType;
	@Transient
	
   private String localBodyLevel; 
	

	public Integer getParentOrgLocatedLevelCode() {
		return parentOrgLocatedLevelCode;
	}

	public void setParentOrgLocatedLevelCode(Integer parentOrgLocatedLevelCode) {
		this.parentOrgLocatedLevelCode = parentOrgLocatedLevelCode;
	}

	/**
	 * Added on 10/12/2014 for localbody impact
	 *
	 */
	@Transient
	private Character operation_state;

	public Integer getAdminUnitCode() {
		return adminUnitCode;
	}

	public void setAdminUnitCode(Integer adminUnitCode) {
		this.adminUnitCode = adminUnitCode;
	}

	public boolean isRevenueEntity() {
		return revenueEntity;
	}

	public void setRevenueEntity(boolean revenueEntity) {
		this.revenueEntity = revenueEntity;
	}

	public Integer getParentAdminCode() {
		return parentAdminCode;
	}

	public void setParentAdminCode(Integer parentAdminCode) {
		this.parentAdminCode = parentAdminCode;
	}

	public Integer getSlc() {
		return slc;
	}

	public void setSlc(Integer slc) {
		this.slc = slc;
	}

	// TODO Remove unused code found by UCDetector
	// public static long getSerialversionuid() {
	// return serialVersionUID;
	// }

	public Integer getCreatedby() {
		return createdby;
	}

	public void setCreatedby(Integer createdby) {
		this.createdby = createdby;
	}

	public String getAdminLevelNameEng() {
		return adminLevelNameEng;
	}

	public void setAdminLevelNameEng(String adminLevelNameEng) {
		this.adminLevelNameEng = adminLevelNameEng;
	}

	public String getAdminLevelNameLocal() {
		return adminLevelNameLocal;
	}

	public void setAdminLevelNameLocal(String adminLevelNameLocal) {
		this.adminLevelNameLocal = adminLevelNameLocal;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public Date getCreatedon() {
		return createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	public Character getOperation_state() {
		return operation_state;
	}

	public void setOperation_state(Character operation_state) {
		this.operation_state = operation_state;
	}

	public Character getIspublish() {
		return ispublish;
	}

	public void setIspublish(Character ispublish) {
		this.ispublish = ispublish;
	}

	public Character getButtonClicked() {
		return buttonClicked;
	}

	public void setButtonClicked(Character buttonClicked) {
		this.buttonClicked = buttonClicked;
	}

	public Integer getOrgLocatedLevelCode() {
		return orgLocatedLevelCode;
	}

	public void setOrgLocatedLevelCode(Integer orgLocatedLevelCode) {
		this.orgLocatedLevelCode = orgLocatedLevelCode;
	}

	public Integer getSeqLevel() {
		return seqLevel;
	}

	public void setSeqLevel(Integer seqLevel) {
		this.seqLevel = seqLevel;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	/*added by Sunita on 24-11-2016*/
	/*@Column(name = "isrevenue_level")
	private boolean isRevenuelevel;

	public boolean isRevenuelevel() {
		return isRevenuelevel;
	}

	public void setRevenuelevel(boolean isRevenuelevel) {
		this.isRevenuelevel = isRevenuelevel;
	}*/

	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public OrgLocatedAtLevels getOrgLocatedAtLevels() {
		return orgLocatedAtLevels;
	}

	public void setOrgLocatedAtLevels(OrgLocatedAtLevels orgLocatedAtLevels) {
		this.orgLocatedAtLevels = orgLocatedAtLevels;
	}

	public String getHierarchy() {
		return hierarchy;
	}

	public void setHierarchy(String hierarchy) {
		this.hierarchy = hierarchy;
	}

	public String getLocalBodyLevel() {
		return localBodyLevel;
	}

	public void setLocalBodyLevel(String localBodyLevel) {
		this.localBodyLevel = localBodyLevel;
	}

	public Character getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Character parentCategory) {
		this.parentCategory = parentCategory;
	}

	public String getUnitLevelType() {
		return unitLevelType;
	}

	public void setUnitLevelType(String unitLevelType) {
		this.unitLevelType = unitLevelType;
	}
	
	

}
