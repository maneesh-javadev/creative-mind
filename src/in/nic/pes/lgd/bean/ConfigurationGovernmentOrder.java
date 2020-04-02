/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.nic.pes.lgd.bean;

//import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author
 */
@Entity
@Table(name = "configuration_government_order")
public class ConfigurationGovernmentOrder implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7766531742772716388L;

	private int id;
	/*private State state;*/	
	
	private boolean isgovtorderupload;
	private boolean isactive;
	private Date lastupdated;
	private Long lastupdatedby;
	private Date createdon;
	private Long createdby;
	private int slc;
    //private int stateCode;
    //private int stateVersion;
    private int operationCode;
   

	public ConfigurationGovernmentOrder() {
	}

	/*public ConfigurationGovernmentOrder(int id, State state, 
			Operations operations, boolean isgovtorderupload, int slc,boolean isactive) {
		this.id = id;
		this.isgovtorderupload = isgovtorderupload;
		this.isactive = isactive;
		this.slc=slc;
	}
*/
// TODO Remove unused code found by UCDetector
// 	public ConfigurationGovernmentOrder(int id, boolean isgovtorderupload, int slc,boolean isactive,Date lastupdated,Long lastupdatedby,Date createdon,Long createdby,int operationCode) {
// 		this.id = id;
// 		this.isgovtorderupload = isgovtorderupload;
// 		this.isactive = isactive;
// 		this.slc=slc;
// 		this.lastupdated=lastupdated;
// 		this.lastupdatedby=lastupdatedby;
// 		this.createdon=createdon;
// 		this.createdby=createdby;
// 		this.operationCode=operationCode;
// 	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "slc", referencedColumnName = "slc", nullable = false)
			//@JoinColumn(name = "isactive", referencedColumnName = "isactive", nullable = false)
			})
	@Where(clause="isactive = true")
	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}
*/

	

	@Column(name = "isgovtorderupload", nullable = false)
	public boolean isIsgovtorderupload() {
		return this.isgovtorderupload;
	}

	public void setIsgovtorderupload(boolean isgovtorderupload) {
		this.isgovtorderupload = isgovtorderupload;
	}

	@Column(name = "isactive")
	public boolean isIsactive() {
		return this.isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	@Column(name = "lastupdated", nullable = false)
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
	
	@Column(name = "createdon", nullable = false)
	public Date getCreatedon() {
		return createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}
	
	@Column(name = "createdby")
	public Long getCreatedby() {
		return createdby;
	}

	public void setCreatedby(Long createdby) {
		this.createdby = createdby;
	}

/*	@Column(name = "state_code",insertable=false,updatable=false)
	public int getStateCode() {
		return stateCode;
	}

	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}
	
	@Column(name = "state_version",insertable=false,updatable=false)
	public int getStateVersion() {
		return stateVersion;
	}

	public void setStateVersion(int stateVersion) {
		this.stateVersion = stateVersion;
	}*/
	
//	@Column(name = "operation_code",insertable=false,updatable=false)
	@Column(name = "operation_code",nullable = false)
	public int getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(int operationCode) {
		this.operationCode = operationCode;
	}

	@Column(name = "slc",nullable = false)//,insertable=false,updatable=false)
	public int getSlc() {
		return slc;
	}

	public void setSlc(int slc) {
		this.slc = slc;
	}

	
}

