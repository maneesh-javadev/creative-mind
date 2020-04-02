package in.nic.pes.lgd.forms;


import java.sql.Timestamp;
/**
 * @author Sunita Dagar
 * @Date 18-11-2016
 */
public class NodalOfficerForm {
	
	    private Integer nodalUserId;
	    private Integer nodalUserVersion;
        private String nodalUserName;
	    private String nodalUserEmail;
	    private String nodalUserMobile;
	    private String nodalUserDesignation;
	    private Boolean isactive;
	    private Integer nodalUserDistrict;
	    private Integer createdBy;
		private Timestamp createdOn;
		private Integer lastUpdatedBy;
		private Timestamp lastUpdatdOn;
		public Integer getNodalUserId() {
			return nodalUserId;
		}
		public void setNodalUserId(Integer nodalUserId) {
			this.nodalUserId = nodalUserId;
		}
		public Integer getNodalUserVersion() {
			return nodalUserVersion;
		}
		public void setNodalUserVersion(Integer nodalUserVersion) {
			this.nodalUserVersion = nodalUserVersion;
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