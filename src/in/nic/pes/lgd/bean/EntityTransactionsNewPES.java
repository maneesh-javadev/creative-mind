/*
 * Created on October 10, 2012
 * Entity Created in LGD schema of PES Database
 * DATABASE - pes1
 */
package in.nic.pes.lgd.bean;

import in.nic.pes.lgd.utils.ApplicationConstant;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * EntityTransactions New entity class under PES database.
 * LDG schema accessed from PES for update and fetch 
 * data for other application than LGD.
 * @author Vinay Yadav
 * @created October 12, 2012
 */
@Entity
@Table(name = "entity_transactions", schema = ApplicationConstant.ENTITY_TRANS_SCHEMA)
public class EntityTransactionsNewPES implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "tid")
	private Integer tId;

	@Column(name = "operation_code")
	private Integer operationCode;
	
	@Column(name = "level", length=1)
	private String level;
	
	@Column(name = "effective_date")
	private Timestamp effectiveDate;
	
	
	@Column(name = "isactive")
	private boolean isactive;
	
	@Column(name = "haserror")
	private boolean hasError;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "slc")
	private Integer stateCode;
	
	@Column(name = "status_flg")
	private String statusFlg;
	
	@Column(name = "scheduled_date")
	private Date scheduledDate;
	
	@Column(name = "task_created_on")
	private Date taskCreatedOn;
	
	@Column(name = "task_created_by")
	private BigInteger taskCreatedBy;
	
	
	// constructors -------------------
	public EntityTransactionsNewPES(){
	}
	
// TODO Remove unused code found by UCDetector
// 	public EntityTransactionsNewPES(Integer tId){
// 		this.tId = tId;
// 	}
	//---------------------------------
	
	// setters & getters
// TODO Remove unused code found by UCDetector
// 	public Integer gettId() {
// 		return tId;
// 	}

	public void settId(Integer tId) {
		this.tId = tId;
	}

	public Integer getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(Integer operationCode) {
		this.operationCode = operationCode;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Timestamp getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Timestamp effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public boolean isHasError() {
		return hasError;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStateCode() {
		return stateCode;
	}

	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}

	public String getStatusFlg() {
		return statusFlg;
	}

	public void setStatusFlg(String statusFlg) {
		this.statusFlg = statusFlg;
	}

	public Date getScheduledDate() {
		return scheduledDate;
	}

	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	public Date getTaskCreatedOn() {
		return taskCreatedOn;
	}

	public void setTaskCreatedOn(Date taskCreatedOn) {
		this.taskCreatedOn = taskCreatedOn;
	}

	public BigInteger getTaskCreatedBy() {
		return taskCreatedBy;
	}

	public void setTaskCreatedBy(BigInteger taskCreatedBy) {
		this.taskCreatedBy = taskCreatedBy;
	}
	
}
