package in.nic.pes.lgd.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="habitation")
public class Habitation implements Serializable{

	@Id
	@SequenceGenerator(name="habitation_seq_gen",sequenceName="habitation_seq",initialValue=1,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="habitation_seq_gen")
	@Column(name="habitation_code",nullable = false)
	private Integer habitationCode;
	@Column(name="habitation_version")
	private Integer habitationVersion;
	@Column(name="habitation_name_english")
	private String habitationNameEnglish;
	@Column(name="habitation_name_local")
	private String habitationNameLocal;
	@Column(name="parent_type")
	private char parentType;
	@Column(name="parent_code")
	private Integer parentCode;
	@Column(name="isactive")
	private boolean isactive;
	@Column(name="effective_date")
	private Date effectiveDate;
	@Column(name="createdby")
	private long createdby;
	@Column(name="createdon")
	private Date createdon;
	@Column(name="lastupdatedby")
	private long lastupdatedby;
	@Column(name="lastupdated")
	private Date lastupdated;
	@Column(name="flag_code")
	private Integer flagcode;
	@Column(name="transaction_id")
	private Integer transactionId;
	@Column(name="ss_code")
	private String sscode;
	
	@Column(name="slc")
	private Integer slc;
	
	
	public Integer getSlc() {
		return slc;
	}

	public void setSlc(Integer slc) {
		this.slc = slc;
	}

	/*@Transient
	private String setErrorMsg;*/
	public Integer getHabitationCode() {
		return habitationCode;
	}

	public void setHabitationCode(Integer habitationCode) {
		this.habitationCode = habitationCode;
	}

	public Integer getHabitationVersion() {
		return habitationVersion;
	}

	public void setHabitationVersion(Integer habitationVersion) {
		this.habitationVersion = habitationVersion;
	}

	public String getHabitationNameEnglish() {
		return habitationNameEnglish;
	}

	public void setHabitationNameEnglish(String habitationNameEnglish) {
		this.habitationNameEnglish = habitationNameEnglish;
	}

	public String getHabitationNameLocal() {
		return habitationNameLocal;
	}

	public void setHabitationNameLocal(String habitationNameLocal) {
		this.habitationNameLocal = habitationNameLocal;
	}

	public char getParentType() {
		return parentType;
	}

	public void setParentType(char parentType) {
		this.parentType = parentType;
	}

	public Integer getParentCode() {
		return parentCode;
	}

	public void setParentCode(Integer parentCode) {
		this.parentCode = parentCode;
	}

	public boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public long getCreatedby() {
		return createdby;
	}

	public void setCreatedby(long createdby) {
		this.createdby = createdby;
	}

	public Date getCreatedon() {
		return createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	public long getLastupdatedby() {
		return lastupdatedby;
	}

	public void setLastupdatedby(long lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}

	public Date getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(Date lastupdated) {
		this.lastupdated = lastupdated;
	}

	public Integer getFlagcode() {
		return flagcode;
	}

	public void setFlagcode(Integer flagcode) {
		this.flagcode = flagcode;
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public String getSscode() {
		return sscode;
	}

	public void setSscode(String sscode) {
		this.sscode = sscode;
	}

	/*public String getSetErrorMsg() {
		return setErrorMsg;
	}

	public void setSetErrorMsg(String setErrorMsg) {
		this.setErrorMsg = setErrorMsg;
	}*/


}
