/**
 * 
 */
package com.cmc.lgd.localbody.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Vinay Yadav
 * @created 17/12/2014
 */
@Entity
@Table(name = "draft_change_parent_localbody_temp")
public class DraftChangeParentLocalbodyTemp {

	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "temp_pk_id", nullable = false)
	private Integer tempPkId;

	@Column(name = "local_body_code_temp", nullable = false)
	private Integer localBodyCode;

	@Column(name = "parent_lblc", nullable = false)
	private Integer parentLocalBodyCode;

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

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_date")
	private Date createdDate = new Date();

	@Column(name = "template_id")
	private Integer templateId;

	@Column(name = "template_data")
	private String editorTemplateDetails;

	/**
	 * 
	 */
	public DraftChangeParentLocalbodyTemp() {
		// TODO Auto-generated constructor stub
	}

	public Integer getTempPkId() {
		return tempPkId;
	}

	public void setTempPkId(Integer tempPkId) {
		this.tempPkId = tempPkId;
	}

	public Integer getLocalBodyCode() {
		return localBodyCode;
	}

	public void setLocalBodyCode(Integer localBodyCode) {
		this.localBodyCode = localBodyCode;
	}

	public Integer getParentLocalBodyCode() {
		return parentLocalBodyCode;
	}

	public void setParentLocalBodyCode(Integer parentLocalBodyCode) {
		this.parentLocalBodyCode = parentLocalBodyCode;
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

	
}
