/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.nic.pes.lgd.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @author
 */
@Entity
@Table(name = "local_body_type")
// , catalog = "lgd_db", schema = "public")
@NamedQueries({ @NamedQuery(name = "LocalBodyType.findAll", query = "SELECT l FROM LocalBodyType l") })
public class LocalBodyType implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer localBodyTypeCode;
	private String localBodyTypeName;
	private char category;
	private char level;
	private Date lastupdated;
	private long lastupdatedby;
	private Date createdon;
	private long createdby;
	private boolean ispartixgovt;
	private boolean isactive;
	private String temp;

	private Set<LocalBodyTiersSetup> localBodyTiersSetup = new HashSet<LocalBodyTiersSetup>(
			0);
	private Set<Localbody> localbodies = new HashSet<Localbody>(0);

	private Set<LocalBodyTypeHistory> localBodyTypeHistory = new HashSet<LocalBodyTypeHistory>(
			0);

	public LocalBodyType() {
	}

	public LocalBodyType(Integer localBodyTypeCode) {
		this.localBodyTypeCode = localBodyTypeCode;
	}

// TODO Remove unused code found by UCDetector
// 	public LocalBodyType(Integer localBodyTypeCode, String localBodyTypeName,
// 			char category, char level, Date lastupdated, long lastupdatedby,
// 			Date createdon, long createdby, boolean ispartixgovt,
// 			boolean isactive) {
// 		this.localBodyTypeCode = localBodyTypeCode;
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

// TODO Remove unused code found by UCDetector
// 	public LocalBodyType(Integer localBodyTypeCode, String localBodyTypeName,
// 			char category, char level, Date lastupdated, long lastupdatedby,
// 			Date createdon, long createdby, boolean ispartixgovt,
// 			boolean isactive, String temp,
// 			Set<LocalBodyTiersSetup> localBodyTiersSetup,
// 			Set<Localbody> localbodies,
// 			Set<LocalBodyTypeHistory> localBodyTypeHistory) {
// 		super();
// 		this.localBodyTypeCode = localBodyTypeCode;
// 		this.localBodyTypeName = localBodyTypeName;
// 		this.category = category;
// 		this.level = level;
// 		this.lastupdated = lastupdated;
// 		this.lastupdatedby = lastupdatedby;
// 		this.createdon = createdon;
// 		this.createdby = createdby;
// 		this.ispartixgovt = ispartixgovt;
// 		this.isactive = isactive;
// 		this.temp = temp;
// 		this.localBodyTiersSetup = localBodyTiersSetup;
// 		this.localbodies = localbodies;
// 		this.localBodyTypeHistory = localBodyTypeHistory;
// 	}

	@GenericGenerator(name = "generator", strategy = "increment", parameters = {})
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "local_body_type_code", nullable = false)
	public Integer getLocalBodyTypeCode() {
		return localBodyTypeCode;
	}

	public void setLocalBodyTypeCode(Integer localBodyTypeCode) {
		this.localBodyTypeCode = localBodyTypeCode;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "localBodyType")
	public Set<LocalBodyTypeHistory> getLocalBodyTypeHistory() {
		return localBodyTypeHistory;
	}

	public void setLocalBodyTypeHistory(
			Set<LocalBodyTypeHistory> localBodyTypeHistory) {
		this.localBodyTypeHistory = localBodyTypeHistory;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "localBodyType")
	public Set<Localbody> getLocalbodies() {
		return localbodies;
	}

	public void setLocalbodies(Set<Localbody> localbodies) {
		this.localbodies = localbodies;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "localbodytype")
	public Set<LocalBodyTiersSetup> getLocalBodyTiersSetup() {
		return localBodyTiersSetup;
	}

	public void setLocalBodyTiersSetup(
			Set<LocalBodyTiersSetup> localBodyTiersSetup) {
		this.localBodyTiersSetup = localBodyTiersSetup;
	}

	/*
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "localBodyType") public
	 * Set<LocalBodySubtype> getLocalBodySubtypes() { return
	 * this.localBodySubtypes; }
	 * 
	 * public void setLocalBodySubtypes(Set<LocalBodySubtype> localBodySubtypes)
	 * { this.localBodySubtypes = localBodySubtypes; }
	 */

	@Basic(optional = false)
	@Column(name = "local_body_type_name", nullable = false, length = 50)
	public String getLocalBodyTypeName() {
		return localBodyTypeName;
	}

	public void setLocalBodyTypeName(String localBodyTypeName) {
		this.localBodyTypeName = localBodyTypeName;
	}

	@Basic(optional = false)
	@Column(name = "category", nullable = false)
	public char getCategory() {
		return category;
	}

	public void setCategory(char category) {
		this.category = category;
	}

	@Basic(optional = false)
	@Column(name = "level", nullable = false)
	public char getLevel() {
		return level;
	}

	public void setLevel(char level) {
		this.level = level;
	}

	@Basic(optional = false)
	@Column(name = "lastupdated", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(Date lastupdated) {
		this.lastupdated = lastupdated;
	}

	@Basic(optional = false)
	@Column(name = "lastupdatedby", nullable = false)
	public long getLastupdatedby() {
		return lastupdatedby;
	}

	public void setLastupdatedby(long lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}

	@Basic(optional = false)
	@Column(name = "createdon", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedon() {
		return createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	@Basic(optional = false)
	@Column(name = "createdby", nullable = false)
	public long getCreatedby() {
		return createdby;
	}

	public void setCreatedby(long createdby) {
		this.createdby = createdby;
	}

	@Basic(optional = false)
	@Column(name = "ispartixgovt", nullable = false)
	public boolean getIspartixgovt() {
		return ispartixgovt;
	}

	public void setIspartixgovt(boolean ispartixgovt) {
		this.ispartixgovt = ispartixgovt;
	}

	@Basic(optional = false)
	@Column(name = "isactive", nullable = false)
	public boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (localBodyTypeCode != null ? localBodyTypeCode.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof LocalBodyType)) {
			return false;
		}
		LocalBodyType other = (LocalBodyType) object;
		if ((this.localBodyTypeCode == null && other.localBodyTypeCode != null)
				|| (this.localBodyTypeCode != null && !this.localBodyTypeCode
						.equals(other.localBodyTypeCode))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "in.nic.pes.lgd.bean.LocalBodyType[ localBodyTypeCode="
				+ localBodyTypeCode + " ]";
	}

	@Transient
	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

}
