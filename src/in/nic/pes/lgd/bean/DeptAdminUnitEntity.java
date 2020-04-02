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
@Table(name="administration_unit_entity")
public class DeptAdminUnitEntity implements Serializable {
	private static final long serialVersionUID = 1L;
 	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "administration_unit_entity_seq") })
	@Id
	@GeneratedValue(generator = "sequence")	
    @Column(name = "admin_unit_entity_code", unique = true, nullable = false)
 	private Integer adminUnitEntityCode; 
 	
    @Column(name = "admin_unit_level_code")
 	private Integer adminUnitLevelCode; 
    
	@Column(name="parent_unit_entity_code")
 	private Integer parentAdminUnitEntityCode; 
	
 	@Column(name="admin_entity_name_english")
 	private String adminEntityNameEnglish; 
 	
 	@Column(name="admin_entity_name_local")
 	private String adminEntityNameLocal; 	
 	
 	@Column(name="Slc")
 	private Integer slc; 
 	
 	@Column(name="Createdby")
 	private Integer createdby;
 	
 	@Column(name="Createdon")
 	private Date createdon; 
 	
 	@Column(name="isActive")
 	private boolean isactive; 
 	
 	@Column(name="coverage_exist")
 	private boolean coverageExist;
 
    @Column(name = "admin_coverage_code", unique = true)
 	private Integer adminCoverageCode; 
    
    /**
     * Coloum added for the Draft changes in admin unit entity
     */
	@Column(name = "isPublish", nullable = false )
 	private Character isPublish; 
	
	
	/**
	 * parent category define Administration Unit Level Lendregion(L),Administration Unit(A) and Localbody (G)
	 */
	@Column(name = "parent_category")
	private Character parentCategory;
 	
	@Transient
	private String parentAdminUnitEntityName;
   
    /**
     * Add entity operationState for extended functionality of department.
     * @since on 06-10-2014
     */
    @Transient
    private Character operation_state;
    
    /**
     * Varaiable added for the Draft use case of admin unit entity
     */
    
    @Transient
    private Character publishOrSave;
    /**Added by ripunj on 28-04-2015 for admin unit entity for district User**/
    @Column(name="districtCode")
    private Integer districtCode;
    
	public Integer getAdminUnitEntityCode() {
		return adminUnitEntityCode;
	}
	public void setAdminUnitEntityCode(Integer adminUnitEntityCode) {
		this.adminUnitEntityCode = adminUnitEntityCode;
	}
	public Integer getAdminUnitLevelCode() {
		return adminUnitLevelCode;
	}
	public void setAdminUnitLevelCode(Integer adminUnitLevelCode) {
		this.adminUnitLevelCode = adminUnitLevelCode;
	}
	public Integer getParentAdminUnitEntityCode() {
		return parentAdminUnitEntityCode;
	}
	public void setParentAdminUnitEntityCode(Integer parentAdminUnitEntityCode) {
		this.parentAdminUnitEntityCode = parentAdminUnitEntityCode;
	}
	public String getAdminEntityNameEnglish() {
		return adminEntityNameEnglish;
	}
	public void setAdminEntityNameEnglish(String adminEntityNameEnglish) {
		this.adminEntityNameEnglish = adminEntityNameEnglish;
	}
	public String getAdminEntityNameLocal() {
		return adminEntityNameLocal;
	}
	public void setAdminEntityNameLocal(String adminEntityNameLocal) {
		this.adminEntityNameLocal = adminEntityNameLocal;
	}
	public Integer getSlc() {
		return slc;
	}
	public void setSlc(Integer slc) {
		this.slc = slc;
	}
	public Integer getCreatedby() {
		return createdby;
	}
	public void setCreatedby(Integer createdby) {
		this.createdby = createdby;
	}
	public Date getCreatedon() {
		return createdon;
	}
	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public boolean isCoverageExist() {
		return coverageExist;
	}
	public void setCoverageExist(boolean coverageExist) {
		this.coverageExist = coverageExist;
	}
// TODO Remove unused code found by UCDetector
// 	public static long getSerialversionuid() {
// 		return serialVersionUID;
// 	}
	public Integer getAdminCoverageCode() {
		return adminCoverageCode;
	}
	public void setAdminCoverageCode(Integer adminCoverageCode) {
		this.adminCoverageCode = adminCoverageCode;
	}
	public Character getOperation_state() {
		return operation_state;
	}
	public void setOperation_state(Character operation_state) {
		this.operation_state = operation_state;
	}
	public Character getIsPublish() {
		return isPublish;
	}
	public void setIsPublish(Character isPublish) {
		this.isPublish = isPublish;
	}
	public Character getPublishOrSave() {
		return publishOrSave;
	}
	public void setPublishOrSave(Character publishOrSave) {
		this.publishOrSave = publishOrSave;
	}
	public String getParentAdminUnitEntityName() {
		return parentAdminUnitEntityName;
	}
	public void setParentAdminUnitEntityName(String parentAdminUnitEntityName) {
		this.parentAdminUnitEntityName = parentAdminUnitEntityName;
	}
	public Integer getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(Integer districtCode) {
		this.districtCode = districtCode;
	}
	public Character getParentCategory() {
		return parentCategory;
	}
	public void setParentCategory(Character parentCategory) {
		this.parentCategory = parentCategory;
	}
	
	
}
