package in.nic.pes.lgd.bean;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity

@NamedNativeQueries({
@NamedNativeQuery(query="select * from get_userwise_entities_count(:entityCode,:entityType,:userId,:userType)",                 
name="getUserWiseEntityCount",resultClass=LgdDataConfirmation.class),	
})

@Table(name = "lgd_data_confirmation", schema = "public")
public class LgdDataConfirmation implements java.io.Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1563167740443561806L;

	@Id
	@Column(name="lgd_confirmation_id")
	@SequenceGenerator(name = "lgd_data_confirmation_id_seq_generator", initialValue=1, allocationSize=1,  sequenceName = "lgd_data_confirmation_lgd_confirmation_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lgd_data_confirmation_id_seq_generator")
	private Integer lgdConfirmationId;
	
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="user_type")
	private Character userType;
	
	@Transient
	private String userOTP; 
	
	@Column(name="no_of_district")
	private Integer noOfDistrict;
	
	@Column(name="no_of_subdistricts")
	private Integer noOfSubdistricts;
	
	@Column(name="no_of_villages")
	private Integer noOfVillages;
	
	@Column(name="no_of_blocks")
	private Integer noOfBlocks;
	
	@Column(name="no_of_pri")
	private Integer noOfPRI;
	
	@Column(name="no_of_traditional")
	private Integer noOfTraditional;
	
	@Column(name="no_of_urban")
	private Integer noOfUrban;
	
	@Column(name="updated_on")
	private Timestamp updatedOn;
	
	@Column(name="isactive")
	private Boolean isactive;
	
	@Column(name="status")
	private Character status;
	
	@Column(name="unfreeze_reason")
	private String unfreezeReason; 
	
	@Column(name="unfreese_user_id")
	private Long unfreeseUserId;
	
	@Transient
	private List<DistrictFreezeEntity> districtFreezeEntityList; 
	
	@Transient
	private List<FreezeLocalBodyEntity> districtFreezeEntityListULB; 
	
	@Transient
	private String entityName;
	
	@Transient
	private List<CommonsMultipartFile> uploadFiles;
	
	@Column(name="file_name")
	private String fileName;
	
	@Column(name="nodal_officer_id")
	private Integer nodalOfficerId;

	public Integer getLgdConfirmationId() {
		return lgdConfirmationId;
	}

	public void setLgdConfirmationId(Integer lgdConfirmationId) {
		this.lgdConfirmationId = lgdConfirmationId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Character getUserType() {
		return userType;
	}

	public void setUserType(Character userType) {
		this.userType = userType;
	}

	

	public String getUserOTP() {
		return userOTP;
	}

	public void setUserOTP(String userOTP) {
		this.userOTP = userOTP;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getNoOfDistrict() {
		return noOfDistrict;
	}

	public void setNoOfDistrict(Integer noOfDistrict) {
		this.noOfDistrict = noOfDistrict;
	}

	public Integer getNoOfSubdistricts() {
		return noOfSubdistricts;
	}

	public void setNoOfSubdistricts(Integer noOfSubdistricts) {
		this.noOfSubdistricts = noOfSubdistricts;
	}

	public Integer getNoOfVillages() {
		return noOfVillages;
	}

	public void setNoOfVillages(Integer noOfVillages) {
		this.noOfVillages = noOfVillages;
	}

	public Integer getNoOfBlocks() {
		return noOfBlocks;
	}

	public void setNoOfBlocks(Integer noOfBlocks) {
		this.noOfBlocks = noOfBlocks;
	}

	public Integer getNoOfPRI() {
		return noOfPRI;
	}

	public void setNoOfPRI(Integer noOfPRI) {
		this.noOfPRI = noOfPRI;
	}

	public Integer getNoOfTraditional() {
		return noOfTraditional;
	}

	public void setNoOfTraditional(Integer noOfTraditional) {
		this.noOfTraditional = noOfTraditional;
	}

	public Integer getNoOfUrban() {
		return noOfUrban;
	}

	public void setNoOfUrban(Integer noOfUrban) {
		this.noOfUrban = noOfUrban;
	}

	public Timestamp getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public String getUnfreezeReason() {
		return unfreezeReason;
	}

	public void setUnfreezeReason(String unfreezeReason) {
		this.unfreezeReason = unfreezeReason;
	}

	public Long getUnfreeseUserId() {
		return unfreeseUserId;
	}

	public void setUnfreeseUserId(Long unfreeseUserId) {
		this.unfreeseUserId = unfreeseUserId;
	}

	public List<DistrictFreezeEntity> getDistrictFreezeEntityList() {
		return districtFreezeEntityList;
	}

	public void setDistrictFreezeEntityList(List<DistrictFreezeEntity> districtFreezeEntityList) {
		this.districtFreezeEntityList = districtFreezeEntityList;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public List<CommonsMultipartFile> getUploadFiles() {
		return uploadFiles;
	}

	public void setUploadFiles(List<CommonsMultipartFile> uploadFiles) {
		this.uploadFiles = uploadFiles;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<FreezeLocalBodyEntity> getDistrictFreezeEntityListULB() {
		return districtFreezeEntityListULB;
	}

	public void setDistrictFreezeEntityListULB(List<FreezeLocalBodyEntity> districtFreezeEntityListULB) {
		this.districtFreezeEntityListULB = districtFreezeEntityListULB;
	}

	public Integer getNodalOfficerId() {
		return nodalOfficerId;
	}

	public void setNodalOfficerId(Integer nodalOfficerId) {
		this.nodalOfficerId = nodalOfficerId;
	}

	
	 
	
	 

}
