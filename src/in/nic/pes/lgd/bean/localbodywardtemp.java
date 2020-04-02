package in.nic.pes.lgd.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "localbody_ward_temp", schema = "public")
public class localbodywardtemp implements java.io.Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//private Localbody localbody;

	private Integer temp_ward_code;
	private Integer wardCode;
	private Integer wardVersion;
	
	private String wardNameEnglish;
	private String wardNameLocal;
	private Integer lblc;
	private String wardNumber;	
	private Integer mapCode;
	
	private Date effectiveDate;
	private Date lastupdated;
	private Long lastupdatedby;
	private Date createdon;
	private long createdby;
	private boolean isactive;
	

	
	
	private boolean isdelete;
	private boolean isupdate;
	private boolean isnew;
	
	
	@Id
	@GeneratedValue	 
	@Column(name = "temp_ward_code", nullable = false,insertable=false,updatable=false)
	public Integer getTemp_ward_code() {
		return temp_ward_code;
	}
	public void setTemp_ward_code(Integer temp_ward_code) {
		this.temp_ward_code = temp_ward_code;
	}
	
	@Column(name = "ward_code")
	public Integer getWardCode() {
		return wardCode;
	}
	public void setWardCode(Integer wardCode) {
		this.wardCode = wardCode;
	}
	
	@Column(name = "ward_version")
	public Integer getWardVersion() {
		return wardVersion;
	}
	public void setWardVersion(Integer wardVersion) {
		this.wardVersion = wardVersion;
	}
	
	@Column(name = "ward_name_english", nullable = false, length = 50)
	public String getWardNameEnglish() {
		return wardNameEnglish;
	}
	public void setWardNameEnglish(String wardNameEnglish) {
		this.wardNameEnglish = wardNameEnglish;
	}
	
	@Column(name = "ward_name_local", length = 50)
	public String getWardNameLocal() {
		return wardNameLocal;
	}
	public void setWardNameLocal(String wardNameLocal) {
		this.wardNameLocal = wardNameLocal;
	}
	
	
	@Column(name = "lblc")
	public Integer getLblc() {
		return lblc;
	}
	public void setLblc(Integer lblc) {
		this.lblc = lblc;
	}
	
	
	@Column(name = "ward_number", length = 10)
	public String getWardNumber() {
		return wardNumber;
	}
	public void setWardNumber(String wardNumber) {
		this.wardNumber = wardNumber;
	}
	
	
	@Column(name = "map_code")
	public Integer getMapCode() {
		return mapCode;
	}
	public void setMapCode(Integer mapCode) {
		this.mapCode = mapCode;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "effective_date", nullable = false, length = 35)
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lastupdated", length = 35)
	public Date getLastupdated() {
		return lastupdated;
	}
	public void setLastupdated(Date lastupdated) {
		this.lastupdated = lastupdated;
	}
	
	@Column(name = "lastupdatedby")
	public Long getLastupdatedby() {
		return lastupdatedby;
	}
	public void setLastupdatedby(Long lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdon", nullable = false, length = 35)
	public Date getCreatedon() {
		return createdon;
	}
	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}
	
	
	@Column(name = "createdby", nullable = false)
	public long getCreatedby() {
		return createdby;
	}
	public void setCreatedby(long createdby) {
		this.createdby = createdby;
	}
	
	@Column(name = "isactive", nullable = false)
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	
	@Column(name = "isdelete", nullable = false)
	public boolean isIsdelete() {
		return isdelete;
	}
	public void setIsdelete(boolean isdelete) {
		this.isdelete = isdelete;
	}
	
	@Column(name = "isupdate", nullable = false)
	public boolean isIsupdate() {
		return isupdate;
	}
	public void setIsupdate(boolean isupdate) {
		this.isupdate = isupdate;
	}
	@Column(name = "isnew", nullable = false)
	public boolean isIsnew() {
		return isnew;
	}
	public void setIsnew(boolean isnew) {
		this.isnew = isnew;
	}
	

}
