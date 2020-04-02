package com.cmc.lgd.localbody.entities;

import java.io.Serializable;
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
@Table(name = "draft_change_coverage_temp")
public class DraftChangeCoverageTemp implements Serializable{

	/**
	 * Default Serial Version Id
	 */
	private static final long serialVersionUID = 1L;

	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", nullable = false)
	private Integer id;			
	
	@Column(name = "local_body_code", nullable = false)
	private Integer localBodyCode;	
	
	@Column(name = "local_body_type_code", nullable = false)
	private Integer localBodyTypeCode;
	
	@Column(name = "removed_lr_codes", nullable = false)
	private String removedLangRegionCodes;
	
	@Column(name = "head_quarter_code")
	private String headQuarterCode;
	
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
	
	/*
	 * Existing Local Body and Land Region Coverage related variables defined.
	 */
	@Column(name = "contributing_localbodies")
	private String contributingLBCodes;
	
	@Column(name = "contributing_unmapped_lr")
	private String contributingLandRegionCodes;			
	
	
	/*
	 *  Common Attributes
	 */
	@Column(name = "operation_code")
	private Integer operationCode;
	
	/*@Column(name = "state_code")
	private Integer stateCode;*/
	
	@Column(name = "created_by")
	private Integer createdBy;			
	
	@Column(name = "created_date")
	private Date createdDate = new Date();			
	
	@Column(name = "updated_by")
	private Integer updatedBy;			
	
	@Column(name = "updated_date")
	private Date updatedDate;
	
	@Column(name = "template_id")
	private Integer templateId;	
	
	@Column(name = "template_data")
	private String editorTemplateDetails;	
	
	/*
	 * List Child field (storing coverage of local body and land regions)
	 */
	 @OneToMany(fetch =  FetchType.LAZY, mappedBy = "draftChangeCoverageTemp", cascade={CascadeType.ALL} )
	 private List<DraftUsedChangeCoverageLbLrTemp> usedLBLRList;
	
	/*
	 * Default Constructor.
	 */
	public DraftChangeCoverageTemp() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLocalBodyCode() {
		return localBodyCode;
	}

	public void setLocalBodyCode(Integer localBodyCode) {
		this.localBodyCode = localBodyCode;
	}

	public Integer getLocalBodyTypeCode() {
		return localBodyTypeCode;
	}

	public void setLocalBodyTypeCode(Integer localBodyTypeCode) {
		this.localBodyTypeCode = localBodyTypeCode;
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

	public Integer getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(Integer operationCode) {
		this.operationCode = operationCode;
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

	public List<DraftUsedChangeCoverageLbLrTemp> getUsedLBLRList() {
		return usedLBLRList;
	}

	public void setUsedLBLRList(List<DraftUsedChangeCoverageLbLrTemp> usedLBLRList) {
		this.usedLBLRList = usedLBLRList;
	}

	public String getRemovedLangRegionCodes() {
		return removedLangRegionCodes;
	}

	public void setRemovedLangRegionCodes(String removedLangRegionCodes) {
		this.removedLangRegionCodes = removedLangRegionCodes;
	}

	public String getHeadQuarterCode() {
		return headQuarterCode;
	}

	public void setHeadQuarterCode(String headQuarterCode) {
		this.headQuarterCode = headQuarterCode;
	}
}
