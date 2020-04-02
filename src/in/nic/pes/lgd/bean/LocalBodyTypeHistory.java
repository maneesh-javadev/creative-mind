/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.nic.pes.lgd.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author HCL
 */
@Entity
@Table(name = "local_body_type_history")
@NamedQueries({
    @NamedQuery(name = "LocalBodyTypeHistory.findAll", query = "SELECT l FROM LocalBodyTypeHistory l")})
public class LocalBodyTypeHistory implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private int localBodyTypeHId;
	private LocalBodyType localBodyType;
	private String localBodyTypeName;
	private char category;
	private char level;
	private Date lastupdated;
	private long lastupdatedby;
	private Date createdon;
	private long createdby;
	private boolean ispartixgovt;
	private boolean isactive;
	private Integer localBodyTypeCode;

	public LocalBodyTypeHistory() {
		
	}

// TODO Remove unused code found by UCDetector
// 	public LocalBodyTypeHistory(int localBodyTypeHId,
// 			LocalBodyType localBodyType, String localBodyTypeName,
// 			char category, char level, Date createdon, long createdby,
// 			boolean ispartixgovt, boolean isactive) {
// 		this.localBodyTypeHId = localBodyTypeHId;
// 		this.localBodyType = localBodyType;
// 		this.localBodyTypeName = localBodyTypeName;
// 		this.category = category;
// 		this.level = level;
// 		this.createdon = createdon;
// 		this.createdby = createdby;
// 		this.ispartixgovt = ispartixgovt;
// 		this.isactive = isactive;
// 	}

// TODO Remove unused code found by UCDetector
// 	public LocalBodyTypeHistory(int localBodyTypeHId,
// 			LocalBodyType localBodyType, String localBodyTypeName,
// 			char category, char level, Date lastupdated, Long lastupdatedby,
// 			Date createdon, long createdby, boolean ispartixgovt,
// 			boolean isactive) {
// 		this.localBodyTypeHId = localBodyTypeHId;
// 		this.localBodyType = localBodyType;
// 		this.localBodyTypeName = localBodyTypeName;
// 		this.category = category;
// 		this.level = level;
// 		this.lastupdated = lastupdated;
// 		this.lastupdatedby = lastupdatedby;
// 		this.createdon = createdon;
// 		this.createdby = createdby;
// 		this.ispartixgovt = ispartixgovt;
// 		this.isactive = isactive;
// 	}
	@GenericGenerator
    (name = "sequence", strategy = "sequence", parameters={@Parameter(name="sequence",value="localgovttypehistory")})
	@Id
    @GeneratedValue(generator = "sequence")
   	@Column(name = "local_body_type_h_id", unique = true, nullable = false)
	public int getLocalBodyTypeHId() {
		return this.localBodyTypeHId;
	}

	public void setLocalBodyTypeHId(int localBodyTypeHId) {
		this.localBodyTypeHId = localBodyTypeHId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "local_body_type_code", nullable = false)
	public LocalBodyType getLocalBodyType() {
		return this.localBodyType;
	}

	public void setLocalBodyType(LocalBodyType localBodyType) {
		this.localBodyType = localBodyType;
	}

	@Column(name = "local_body_type_name", nullable = false, length = 50)
	public String getLocalBodyTypeName() {
		return this.localBodyTypeName;
	}

	public void setLocalBodyTypeName(String localBodyTypeName) {
		this.localBodyTypeName = localBodyTypeName;
	}

	@Column(name = "category", nullable = false, length = 1)
	public char getCategory() {
		return this.category;
	}

	public void setCategory(char category) {
		this.category = category;
	}

	@Column(name = "level", nullable = false, length = 1)
	public char getLevel() {
		return this.level;
	}

	public void setLevel(char level) {
		this.level = level;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lastupdated", length = 35)
	public Date getLastupdated() {
		return this.lastupdated;
	}

	public void setLastupdated(Date lastupdated) {
		this.lastupdated = lastupdated;
	}

	@Column(name = "lastupdatedby")
	public long getLastupdatedby() {
		return this.lastupdatedby;
	}

	public void setLastupdatedby(long lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdon", nullable = false, length = 35)
	public Date getCreatedon() {
		return this.createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	@Column(name = "createdby", nullable = false)
	public long getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(long createdby) {
		this.createdby = createdby;
	}

	@Column(name = "ispartixgovt", nullable = false)
	public boolean isIspartixgovt() {
		return this.ispartixgovt;
	}

	public void setIspartixgovt(boolean ispartixgovt) {
		this.ispartixgovt = ispartixgovt;
	}

	@Column(name = "isactive", nullable = false)
	public boolean isIsactive() {
		return this.isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	@Column(name = "local_body_type_code",insertable=false,updatable=false, nullable = false)
	public Integer getLocalBodyTypeCode() {
		return localBodyTypeCode;
	}

	public void setLocalBodyTypeCode(Integer localBodyTypeCode) {
		this.localBodyTypeCode = localBodyTypeCode;
	}

}

