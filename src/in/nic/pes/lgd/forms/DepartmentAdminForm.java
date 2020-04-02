package in.nic.pes.lgd.forms;

import java.util.Date;

import javax.persistence.Column;

public class DepartmentAdminForm {
	private Integer adminUnitCode;
	private Integer adminUnitEntityCode;
 	private String  adminLevelNameEnglish;
 	private String  adminLevelNameLocal;
 	private Integer administrationUnit;
 	private Integer parentAdministrationUnit;
 	private Integer adminUnitLevelCode;
 	private String  adminLevelNameShort;
 	private String  parentAdminLevelName;
 	private String  adminEntityNameEnglish;
 	private String  adminEntityNameLocal;
 	private Integer parentAdminUnitEntityCode; 
 	private Integer oldParentAdminUnitEntityCode;
 	private String  adminCoverage;
 	private String  wardCoverage;
 	private String  deletedCoverage;
 	private boolean coverageExist;
 	private boolean revenueEntity;
 	private Integer parentAdminCode;
 	private Integer slc;
 	private Integer createdby; 
 	private Date createdon;
 	private boolean isactive;
 	private int limit;
	private int offset;
	private Integer childRecords;
	private String entityType;
	private boolean childExist;
	
	private Character parentCategory;
	
	
	
	/**
 	 * Added by ripunj on 20-04-2015 for Administrative Unit Level Draft Mode
 	 */
 	private Character buttonClicked;
 	
 	private Character publishOrSave;
 	/**
 	 * Added by ripunj on 20-04-2015 for Administrative Unit Entity District User
 	 */
 	private Integer districtCode;
 	
 	/*added by deepti on 06-05-2016*/
	private Character overlapUnit;
	
	public Character getOverlapUnit() {
		return overlapUnit;
	}
	public void setOverlapUnit(Character overlapUnit) {
		this.overlapUnit = overlapUnit;
	}
	/*endded by deepti on 06-05-2016*/
	public Integer getAdminUnitCode() {
		return adminUnitCode;
	}
	public void setAdminUnitCode(Integer adminUnitCode) {
		this.adminUnitCode = adminUnitCode;
	}
	public String getAdminLevelNameEnglish() {
		return adminLevelNameEnglish;
	}
	public void setAdminLevelNameEnglish(String adminLevelNameEnglish) {
		this.adminLevelNameEnglish = adminLevelNameEnglish;
	}
	public String getAdminLevelNameLocal() {
		return adminLevelNameLocal;
	}
	public void setAdminLevelNameLocal(String adminLevelNameLocal) {
		this.adminLevelNameLocal = adminLevelNameLocal;
	}
	public Integer getAdministrationUnit() {
		return administrationUnit;
	}
	public void setAdministrationUnit(Integer administrationUnit) {
		this.administrationUnit = administrationUnit;
	}
	public Integer getParentAdministrationUnit() {
		return parentAdministrationUnit;
	}
	public void setParentAdministrationUnit(Integer parentAdministrationUnit) {
		this.parentAdministrationUnit = parentAdministrationUnit;
	}
	public String getAdminLevelNameShort() {
		return adminLevelNameShort;
	}
	public void setAdminLevelNameShort(String adminLevelNameShort) {
		this.adminLevelNameShort = adminLevelNameShort;
	}
	public boolean isCoverageExist() {
		return coverageExist;
	}
	public void setCoverageExist(boolean coverageExist) {
		this.coverageExist = coverageExist;
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
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getOffset() {
		return offset;
	}
	public Integer getAdminUnitEntityCode() {
		return adminUnitEntityCode;
	}
	public void setAdminUnitEntityCode(Integer adminUnitEntityCode) {
		this.adminUnitEntityCode = adminUnitEntityCode;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public String getParentAdminLevelName() {
		return parentAdminLevelName;
	}
	public void setParentAdminLevelName(String parentAdminLevelName) {
		this.parentAdminLevelName = parentAdminLevelName;
	}
	public Integer getChildRecords() {
		return childRecords;
	}
	public void setChildRecords(Integer childRecords) {
		this.childRecords = childRecords;
	}
	public Integer getAdminUnitLevelCode() {
		return adminUnitLevelCode;
	}
	public void setAdminUnitLevelCode(Integer adminUnitLevelCode) {
		this.adminUnitLevelCode = adminUnitLevelCode;
	}
	public String getEntityType() {
		return entityType;
	}
	public void setEntityType(String entityType) {
		this.entityType = entityType;
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
	
	public Integer getParentAdminUnitEntityCode() {
		return parentAdminUnitEntityCode;
	}
	public void setParentAdminUnitEntityCode(Integer parentAdminUnitEntityCode) {
		this.parentAdminUnitEntityCode = parentAdminUnitEntityCode;
	}
	
	public String getWardCoverage() {
		return wardCoverage;
	}
	public void setWardCoverage(String wardCoverage) {
		this.wardCoverage = wardCoverage;
	}
	public String getAdminCoverage() {
		return adminCoverage;
	}
	public void setAdminCoverage(String adminCoverage) {
		this.adminCoverage = adminCoverage;
	}
	public boolean isChildExist() {
		return childExist;
	}
	public void setChildExist(boolean childExist) {
		this.childExist = childExist;
	}
	public String getDeletedCoverage() {
		return deletedCoverage;
	}
	public void setDeletedCoverage(String deletedCoverage) {
		this.deletedCoverage = deletedCoverage;
	}
	public Character getButtonClicked() {
		return buttonClicked;
	}
	public void setButtonClicked(Character buttonClicked) {
		this.buttonClicked = buttonClicked;
	}
	public Character getPublishOrSave() {
		return publishOrSave;
	}
	public void setPublishOrSave(Character publishOrSave) {
		this.publishOrSave = publishOrSave;
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
	public Integer getOldParentAdminUnitEntityCode() {
		return oldParentAdminUnitEntityCode;
	}
	public void setOldParentAdminUnitEntityCode(Integer oldParentAdminUnitEntityCode) {
		this.oldParentAdminUnitEntityCode = oldParentAdminUnitEntityCode;
	}

     
   
}
