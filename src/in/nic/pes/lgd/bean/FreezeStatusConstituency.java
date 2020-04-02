package in.nic.pes.lgd.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="freeze_status_constituency",schema="public")
public class FreezeStatusConstituency implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
@Id
@Column(name="district_code")	
private Integer districtCode;

@Column(name="district_status")
private String districtStatus;

@Column(name="nodal_user_id")
private Integer nodalUserId;

@Column(name="createdon")
private Timestamp createdOn;

@Column(name="createdby")
private Integer createdBy;

@Column(name="lastupdated")
private Timestamp lastUpdated;

@Column(name="lastupdatedby")
private Integer lastUpdatedBy;

@Column(name="document_id")
private long documentId;

public Integer getDistrictCode() {
	return districtCode;
}
public void setDistrictCode(Integer districtCode) {
	this.districtCode = districtCode;
}
public String getDistrict_status() {
	return districtStatus;
}
public void setDistrict_status(String districtStatus) {
	this.districtStatus = districtStatus;
}
public Integer getNodalUserId() {
	return nodalUserId;
}
public void setNodalUserId(Integer nodalUserId) {
	this.nodalUserId = nodalUserId;
}
public Timestamp getCreatedOn() {
	return createdOn;
}
public void setCreatedOn(Timestamp createdOn) {
	this.createdOn = createdOn;
}
public Integer getCreatedBy() {
	return createdBy;
}
public void setCreatedBy(Integer createdBy) {
	this.createdBy = createdBy;
}
public Timestamp getLastUpdated() {
	return lastUpdated;
}
public void setLastUpdated(Timestamp lastUpdated) {
	this.lastUpdated = lastUpdated;
}
public Integer getLastUpdatedBy() {
	return lastUpdatedBy;
}
public void setLastUpdatedBy(Integer lastUpdatedBy) {
	this.lastUpdatedBy = lastUpdatedBy;
}
public long getDocumentId() {
	return documentId;
}
public void setDocumentId(long documentId) {
	this.documentId = documentId;
}
}
