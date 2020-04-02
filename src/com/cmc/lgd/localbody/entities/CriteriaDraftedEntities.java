package com.cmc.lgd.localbody.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Transient;

@Entity
@NamedNativeQueries({ 
	@NamedNativeQuery(query = "SELECT row_number() over() as count, * FROM search_draft_lb_list_fn(:PANCHAYAT_TYPE, :localBodyType, :stateCode,  :localBodyName, :draftOperationCode, :districtCode);", name = "Fetch_Drafted_Entities", resultClass = CriteriaDraftedEntities.class),
})
public class CriteriaDraftedEntities {

	@Id
	@Column(name = "count", nullable = false)
	private Integer count;
	
	@Column(name = "draft_temp_id", nullable = false)
	private Integer entityTempId;
	
	@Column(name = "local_body_code")
	private Integer localBodyCode;

	@Column(name = "local_body_name_english")
	private String localBodyNameEnglish;

	@Column(name = "local_body_name_local")
	private String localBodyNameLocal;

	@Column(name = "local_body_type_code")
	private Integer localBodyTypeCode;
	
	@Column(name = "local_body_type_name")
	private String localBodyTypeName;
	
	@Column(name = "process_name")
	private String processName;
	
	@Column(name = "view_url")
	private String viewUrl;
	
	@Column(name = "modify_url")
	private String modifyUrl;
	
	/*
	 * Input parameters for fetching drafted local body details.
	 */
	@Transient
	private String paramLocalBodyName;

	@Transient
	private Integer paramLocalBodyTypeCode;
	
	@Transient
	private Integer paramActionProcessCode;

	@Transient
	private String localBodyCreationType;

	/*
	 * Default Constructor.
	 */
	public CriteriaDraftedEntities() {
		// TODO Auto-generated constructor stub
	}

	public CriteriaDraftedEntities(String localBodyCreationType) {
		// TODO Auto-generated constructor stub
		this.localBodyCreationType = localBodyCreationType;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getEntityTempId() {
		return entityTempId;
	}

	public void setEntityTempId(Integer entityTempId) {
		this.entityTempId = entityTempId;
	}

	public Integer getLocalBodyCode() {
		return localBodyCode;
	}

	public void setLocalBodyCode(Integer localBodyCode) {
		this.localBodyCode = localBodyCode;
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

	public Integer getLocalBodyTypeCode() {
		return localBodyTypeCode;
	}

	public void setLocalBodyTypeCode(Integer localBodyTypeCode) {
		this.localBodyTypeCode = localBodyTypeCode;
	}

	public String getLocalBodyTypeName() {
		return localBodyTypeName;
	}

	public void setLocalBodyTypeName(String localBodyTypeName) {
		this.localBodyTypeName = localBodyTypeName;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getViewUrl() {
		return viewUrl;
	}

	public void setViewUrl(String viewUrl) {
		this.viewUrl = viewUrl;
	}

	public String getModifyUrl() {
		return modifyUrl;
	}

	public void setModifyUrl(String modifyUrl) {
		this.modifyUrl = modifyUrl;
	}

	public String getParamLocalBodyName() {
		return paramLocalBodyName;
	}

	public void setParamLocalBodyName(String paramLocalBodyName) {
		this.paramLocalBodyName = paramLocalBodyName;
	}

	public Integer getParamLocalBodyTypeCode() {
		return paramLocalBodyTypeCode;
	}

	public void setParamLocalBodyTypeCode(Integer paramLocalBodyTypeCode) {
		this.paramLocalBodyTypeCode = paramLocalBodyTypeCode;
	}

	public Integer getParamActionProcessCode() {
		return paramActionProcessCode;
	}

	public void setParamActionProcessCode(Integer paramActionProcessCode) {
		this.paramActionProcessCode = paramActionProcessCode;
	}

	public String getLocalBodyCreationType() {
		return localBodyCreationType;
	}

	public void setLocalBodyCreationType(String localBodyCreationType) {
		this.localBodyCreationType = localBodyCreationType;
	}
}
