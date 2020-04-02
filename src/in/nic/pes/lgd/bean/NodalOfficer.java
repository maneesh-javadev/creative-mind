package in.nic.pes.lgd.bean;

import java.sql.Timestamp;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Sunita Dagar
 * @Date 18-11-2016
 */
@Entity
@Table(name = "nodal_user_constituency", schema = "public")
public class NodalOfficer {

	@EmbeddedId
	@AttributeOverrides({
	@AttributeOverride(name = "nodal_user_id", column = @Column(name = "nodal_user_id", nullable = false)),
	@AttributeOverride(name = "nodal_user_version", column = @Column(name = "nodal_user_version", nullable = false)) })
	private NodalOfficerPK nodalOfficerPK;
	
	@Column(name = "nodal_user_name")
	private String nodalUserName;
    
	@Column(name = "nodal_user_email")
	private String nodalUserEmail;
    
	@Column(name = "nodal_user_mobile")
	private String nodalUserMobile;
   
	@Column(name = "nodal_user_designation")
	private String nodalUserDesignation;
   
	@Column(name = "isactive")
	private Boolean isactive;
   
	@Column(name = "nodal_user_district")
	private Integer nodalUserDistrict;
    
	@Column(name = "createdby")
	private Integer createdBy;
	
	@Column(name = "createdon")
	private Timestamp createdOn;
	
	@Column(name = "lastupdatedby")
	private Integer lastUpdatedBy;
	
	@Column(name = "lastupdated")
	private Timestamp lastUpdatdOn;
	
	public NodalOfficerPK getNodalOfficerPK() {
		return nodalOfficerPK;
	}
	public void setNodalOfficerPK(NodalOfficerPK nodalOfficerPK) {
		this.nodalOfficerPK = nodalOfficerPK;
	}
	public Boolean getIsactive() {
		return isactive;
	}
	public String getNodalUserName() {
		return nodalUserName;
	}
	public void setNodalUserName(String nodalUserName) {
		this.nodalUserName = nodalUserName;
	}
	public String getNodalUserEmail() {
		return nodalUserEmail;
	}
	public void setNodalUserEmail(String nodalUserEmail) {
		this.nodalUserEmail = nodalUserEmail;
	}
	public String getNodalUserMobile() {
		return nodalUserMobile;
	}
	public void setNodalUserMobile(String nodalUserMobile) {
		this.nodalUserMobile = nodalUserMobile;
	}
	public String getNodalUserDesignation() {
		return nodalUserDesignation;
	}
	public void setNodalUserDesignation(String nodalUserDesignation) {
		this.nodalUserDesignation = nodalUserDesignation;
	}
	public Boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}
	public Integer getNodalUserDistrict() {
		return nodalUserDistrict;
	}
	public void setNodalUserDistrict(Integer nodalUserDistrict) {
		this.nodalUserDistrict = nodalUserDistrict;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Timestamp getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}
	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public Timestamp getLastUpdatdOn() {
		return lastUpdatdOn;
	}
	public void setLastUpdatdOn(Timestamp lastUpdatdOn) {
		this.lastUpdatdOn = lastUpdatdOn;
	}
	
}
