package com.cmc.lgd.localbody.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "draft_operations", schema = "public")
public class DraftOperations implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "process_id", nullable = false)
	private Integer processId;

	@Column(name = "process_name", nullable = false, unique = true)
	private String processName;
	
	@Column(name = "process_entity", length=1)
	private String processEntity;

	public DraftOperations() {
		// TODO Auto-generated constructor stub
	}

	public Integer getProcessId() {
		return processId;
	}

	public void setProcessId(Integer processId) {
		this.processId = processId;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getProcessEntity() {
		return processEntity;
	}

	public void setProcessEntity(String processEntity) {
		this.processEntity = processEntity;
	}
}
