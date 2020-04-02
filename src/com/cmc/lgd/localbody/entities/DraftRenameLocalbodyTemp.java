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
 * @created 05/12/2014
 */
@Entity
@Table(name = "draft_rename_localbody_temp")
public class DraftRenameLocalbodyTemp {

	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "rename_id", nullable = false)
	private Integer id;

	@Column(name = "local_body_code_temp", nullable = false)
	private Integer localBodyCode;

	@Column(name = "local_body_name_english", nullable = false)
	private String localBodyNameEnglish;

	@Column(name = "local_body_name_local")
	private String localBodyNameLocal;

	@Column(name = "alias_english")
	private String localBodyAliasEnglish;

	@Column(name = "alias_local")
	private String localBodyAliasLocal;

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
	public DraftRenameLocalbodyTemp() {
		// TODO Auto-generated constructor stub
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
