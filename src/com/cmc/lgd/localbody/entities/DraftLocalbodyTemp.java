package com.cmc.lgd.localbody.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "draft_localbody_temp")
public class DraftLocalbodyTemp {

	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", nullable = false)
	private Integer id;			
	
	@Column(name = "local_body_name_english", nullable = false)
	private String localBodyNameEnglish;			
	
	@Column(name = "local_body_name_local")
	private String localBodyNameLocal;		
	
	@Column(name = "alias_english")
	private String localBodyAliasEnglish;
	
	@Column(name = "alias_local")
	private String localBodyAliasLocal;	
	
	@Column(name = "is_pesa")
	private Boolean pesaActImpl;
	
	@Column(name = "local_body_type_code")
	private Integer localBodyTypeCode;			
	
	@Column(name = "coordinates")
	private String coordinates;			
		
	/*
	 * Government Order related variables defined
	 */
	@Column(name = "order_code")
	private Integer orderCode;			
	
	@Column(name = "order_no")
	private String orderNo;			
	
	@Column(name = "order_date")
	private Date orderDate;			
	
	@Column(name = "effective_date")
	private Date effectiveDate;			
	
	@Column(name = "gaz_pub_date")
	private Date gazPubDate;			
	
	@Column(name = "order_path")
	private String orderPath;	
	
	@Column(name = "map_upload_path")
	private String mapUploadPath;
	
	/*
	 * Existing Local Body and Land Region Coverage related variables defined.
	 */
	@Column(name = "contributing_localbodies")
	private String contributingLBCodes;
	
	@Column(name = "contributing_unmapped_lr")
	private String contributingLandRegionCodes;			
	
	@Column(name = "local_body_subtype_code")
	private Integer localBodySubtypeCode;		
	
	@Column(name = "parent_local_body_code")
	private Integer parentLocalBodyCode;			
	
	/*
	 *  Common Attributes
	 */
	@Column(name = "operation_code")
	private Integer operationCode;
	
	@Column(name = "ss_code")
	private String stateSpecificCode;			
	
	@Column(name = "flag_code")
	private Integer flagCode;			
	
	@Column(name = "description")
	private String description;	
	
	@Column(name = "state_code")
	private Integer stateCode;
	
	@Column(name = "district_code")
	private Integer districtCode;
	
	@Column(name = "created_by")
	private Integer createdBy;			
	
	@Column(name = "created_date")
	private Date createdDate = new Date();			
	
	@Column(name = "updated_by")
	private Integer updatedBy;			
	
	@Column(name = "updated_date")
	private Date updatedDate;
	
	@Column(name = "lb_type_hierarchy")
	private String lbTypeHierarchy;
	
	@Column(name = "local_body_type_panchayat")
	private String localBodyTypePanchayat;	
	
	@Column(name = "template_id")
	private Integer templateId;	
	
	@Column(name = "template_data")
	private String editorTemplateDetails;	
	
	@Column(name = "local_body_level_codes")
	private String localBodyLevelCodes;
	 
	
	/*
	 * List Child field (storing coverage of local body and land regions)
	 */
	@OneToMany(fetch =  FetchType.LAZY, mappedBy = "draftLocalbodyTemp", cascade={CascadeType.ALL} )
	/*@Cascade(value = {org.hibernate.annotations.CascadeType.ALL,
			org.hibernate.annotations.CascadeType.DELETE_ORPHAN})*/
	private List<DraftUsedLbLrTemp> usedLBLRList;
	
	/*
	 * Default Constructor.
	 */
	public DraftLocalbodyTemp() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLocalBodyNameEnglish() {
		return localBodyNameEnglish;
	}

	public void setLocalBodyNameEnglish(String localBodyNameEnglish) {
		this.localBodyNameEnglish = localBodyNameEnglish;
	}

	public String getLocalBodyNameLocal() {
		return localBodyNameLocal;
	}

	public void setLocalBodyNameLocal(String localBodyNameLocal) {
		this.localBodyNameLocal = localBodyNameLocal;
	}

	public String getLocalBodyAliasEnglish() {
		return localBodyAliasEnglish;
	}

	public void setLocalBodyAliasEnglish(String localBodyAliasEnglish) {
		this.localBodyAliasEnglish = localBodyAliasEnglish;
	}

	public String getLocalBodyAliasLocal() {
		return localBodyAliasLocal;
	}

	public void setLocalBodyAliasLocal(String localBodyAliasLocal) {
		this.localBodyAliasLocal = localBodyAliasLocal;
	}

	public Boolean getPesaActImpl() {
		return pesaActImpl;
	}

	public void setPesaActImpl(Boolean pesaActImpl) {
		this.pesaActImpl = pesaActImpl;
	}

	public Integer getLocalBodyTypeCode() {
		return localBodyTypeCode;
	}

	public void setLocalBodyTypeCode(Integer localBodyType) {
		this.localBodyTypeCode = localBodyType;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}
	
	public Integer getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(Integer orderCode) {
		this.orderCode = orderCode;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getGazPubDate() {
		return gazPubDate;
	}

	public void setGazPubDate(Date gazPubDate) {
		this.gazPubDate = gazPubDate;
	}

	public String getOrderPath() {
		return orderPath;
	}

	public void setOrderPath(String orderPath) {
		this.orderPath = orderPath;
	}
	
	public String getMapUploadPath() {
		return mapUploadPath;
	}

	public void setMapUploadPath(String mapUploadPath) {
		this.mapUploadPath = mapUploadPath;
	}

	public String getContributingLBCodes() {
		return contributingLBCodes;
	}

	public void setContributingLBCodes(String contributingLBCodes) {
		this.contributingLBCodes = contributingLBCodes;
	}

	public String getContributingLandRegionCodes() {
		return contributingLandRegionCodes;
	}

	public void setContributingLandRegionCodes(String contributingLandRegionCodes) {
		this.contributingLandRegionCodes = contributingLandRegionCodes;
	}

	public Integer getLocalBodySubtypeCode() {
		return localBodySubtypeCode;
	}

	public void setLocalBodySubtypeCode(Integer localBodySubtypeCode) {
		this.localBodySubtypeCode = localBodySubtypeCode;
	}

	public Integer getParentLocalBodyCode() {
		return parentLocalBodyCode;
	}

	public void setParentLocalBodyCode(Integer parentLocalBodyCode) {
		this.parentLocalBodyCode = parentLocalBodyCode;
	}

	public Integer getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(Integer operationCode) {
		this.operationCode = operationCode;
	}

	public String getStateSpecificCode() {
		return stateSpecificCode;
	}

	public void setStateSpecificCode(String stateSpecificCode) {
		this.stateSpecificCode = stateSpecificCode;
	}

	public Integer getFlagCode() {
		return flagCode;
	}

	public void setFlagCode(Integer flagCode) {
		this.flagCode = flagCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStateCode() {
		return stateCode;
	}

	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}
	
	public Integer getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(Integer districtCode) {
		this.districtCode = districtCode;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List<DraftUsedLbLrTemp> getUsedLBLRList() {
		return usedLBLRList;
	}

	public void setUsedLBLRList(List<DraftUsedLbLrTemp> usedLBLRList) {
		this.usedLBLRList = usedLBLRList;
	}
	
	public String getLbTypeHierarchy() {
		return lbTypeHierarchy;
	}

	public void setLbTypeHierarchy(String lbTypeHierarchy) {
		this.lbTypeHierarchy = lbTypeHierarchy;
	}

	public String getLocalBodyTypePanchayat() {
		return localBodyTypePanchayat;
	}

	public void setLocalBodyTypePanchayat(String localBodyTypePanchayat) {
		this.localBodyTypePanchayat = localBodyTypePanchayat;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public String getEditorTemplateDetails() {
		return editorTemplateDetails;
	}

	public void setEditorTemplateDetails(String editorTemplateDetails) {
		this.editorTemplateDetails = editorTemplateDetails;
	}

	public String getLocalBodyLevelCodes() {
		return localBodyLevelCodes;
	}

	public void setLocalBodyLevelCodes(String localBodyLevelCodes) {
		this.localBodyLevelCodes = localBodyLevelCodes;
	}	
}
