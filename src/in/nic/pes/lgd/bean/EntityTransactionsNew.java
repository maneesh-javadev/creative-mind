/*
 * Created on October 10, 2012
 * Entity Created in LGD public schema
 * DATABASE - lgd1
 */
package in.nic.pes.lgd.bean;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * EntityTransactions entity class.
 * LDG Database, public schema 
 * @author Vinay Yadav
 */
@Entity
@Table(name = "entity_transactions", schema = "public")
@NamedNativeQueries({
	@NamedNativeQuery(query = "select * from entity_transactions  where isactive and slc=:stateCode and status_flg in ('P','S');", name ="fetch_entity_transactions", resultClass = EntityTransactionsNew.class),
	@NamedNativeQuery(query = "select aue.transaction_id AS tid, null as operation_code ,  null as level , null as effective_date , true as isactive ,false as haserror, et.description AS description , null as slc ,null as status_flg , 	null as scheduled_date    ,null as task_created_on   ,null as task_created_by ,null as entity_type , null as entity_code\r\n" + 
			" from public.entity_transactions  et inner join public.administration_unit_entity aue on aue.transaction_id = et.tid where et.isactive and aue.isactive and aue.slc =:stateCode", name ="fetch_entity_transactions_for_admin_unit_entity", resultClass = EntityTransactionsNew.class),

})
public class EntityTransactionsNew implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "tid")
	/*@SequenceGenerator(name = "tid_seq_generator", initialValue=1, allocationSize=1,  sequenceName = "entitytransactions_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tid_seq_generator")*/
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
	
	@Column(name = "entity_type")
	private String entityType;
	
	@Column(name = "entity_code")
	private String entityCode;
	
	/** This Column not exist in demo and live by MK  02/08/2017
	/*@Column(name = "script")
	private String script;*/
	
	// constructors -------------------
	public EntityTransactionsNew(){
	}
	
// TODO Remove unused code found by UCDetector
// 	public EntityTransactionsNew(Integer tId){
// 		this.tId = tId;
// 	}
	//---------------------------------
	
	// setters & getters
	public Integer gettId() {
		return tId;
	}

// TODO Remove unused code found by UCDetector
// 	public void settId(Integer tId) {
// 		this.tId = tId;
// 	}

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

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public String getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}

	/** This Column not exist in demo and live by MK  02/08/2017
	/*public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}*/

	public void settId(Integer tId) {
		this.tId = tId;
	}
	
	
	
}
