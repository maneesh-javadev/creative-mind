/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.nic.pes.lgd.bean;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * LocalbodyWard generated by hbm2java
 */
@SuppressWarnings("rawtypes")
@Entity
@NamedNativeQueries({
	@NamedNativeQuery(query = "select distinct lw.ward_code,lw.ward_version,lw.ward_number,lw.ward_name_english,lw.ward_name_local,lw.lblc,lw.ward_number"
	+ ",lw.effective_date,lw.lastupdated,lw.lastupdatedby,lw.createdon,lw.createdby,lw.isactive,lw.transaction_id,lw.flag_code,lw.minor_version,lw.ward_covered_region_code,lw.ward_seq_id  "
	+ " from  localbody_ward lw"
	+ " inner join temp_covered_ward_landregion tcwl on lw.ward_code=tcwl.ward_code and  lw.ward_version=tcwl.ward_version"
	+ "  where lw.lblc=:lblc and lw.isactive and tcwl.isactive",
	name = "fetchDraftWardCoverage", resultClass = LocalbodyWard.class),
	
})
@Table(name = "localbody_ward", schema = "public")
public class LocalbodyWard implements java.io.Serializable, Comparator {

	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = 2352028583998879099L;
	private Integer wardSeqId;
	private Integer wardCode;
	private Integer wardVersion;
	private Integer wardMinorVersion;
	private String wardNameEnglish;
	private String wardNameLocal;
	private String wardNumber;
	private Date effectiveDate;
	private Date lastupdated;
	private Long lastupdatedby;
	private Date createdon;
	private long createdby;
	private boolean isactive;
	private Integer lblc;
	private Integer wardCoveredRegionCode;
	private Integer transactionId; 
	private Integer flagCode; 
	
	
	 
	
	private Set<CoveredWardLandregion> coveredWardLandregions = new HashSet<CoveredWardLandregion>(0);

	
	@Id
	@Column(name = "ward_seq_id")
	public Integer getWardSeqId() {
		return wardSeqId;
	}

	public void setWardSeqId(Integer wardSeqId) {
		this.wardSeqId = wardSeqId;
	}

	@Column(name = "minor_version")
	public Integer getWardMinorVersion() {
		return wardMinorVersion;
	}

	public void setWardMinorVersion(Integer wardMinorVersion) {
		this.wardMinorVersion = wardMinorVersion;
	}
	
	
	@Column(name = "ward_covered_region_code")
	public Integer getWardCoveredRegionCode() {
		return wardCoveredRegionCode;
	}

	public void setWardCoveredRegionCode(Integer wardCoveredRegionCode) {
		this.wardCoveredRegionCode = wardCoveredRegionCode;
	}

	@Column(name = "ward_name_english", nullable = false, length = 50)
	public String getWardNameEnglish() {
		return this.wardNameEnglish;
	}

	public void setWardNameEnglish(String wardNameEnglish) {
		this.wardNameEnglish = wardNameEnglish;
	}

	@Column(name = "ward_name_local", length = 50)
	public String getWardNameLocal() {
		return this.wardNameLocal;
	}

	public void setWardNameLocal(String wardNameLocal) {
		this.wardNameLocal = wardNameLocal;
	}

	@Column(name = "ward_number", length = 10)
	public String getWardNumber() {
		return this.wardNumber;
	}

	public void setWardNumber(String wardNumber) {
		this.wardNumber = wardNumber;
	}

	

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "effective_date", nullable = false, length = 35)
	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
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
	public Long getLastupdatedby() {
		return this.lastupdatedby;
	}

	public void setLastupdatedby(Long lastupdatedby) {
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

	@Column(name = "isactive", nullable = false)
	public boolean isIsactive() {
		return this.isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "localbodyWard")
	public Set<CoveredWardLandregion> getCoveredWardLandregions() {
		return this.coveredWardLandregions;
	}

	public void setCoveredWardLandregions(
			Set<CoveredWardLandregion> coveredWardLandregions) {
		this.coveredWardLandregions = coveredWardLandregions;
	}

	@Column(name = "lblc")
	public Integer getLblc() {
		return lblc;
	}

	public void setLblc(Integer lblc) {
		this.lblc = lblc;
	}
	@Column(name = "ward_code",insertable=false,updatable=false)
	public Integer getWardCode() {
		return wardCode;
	}

	public void setWardCode(Integer wardCode) {
		this.wardCode = wardCode;
	}
	@Column(name = "ward_version",insertable=false,updatable=false)
	public Integer getWardVersion() {
		return wardVersion;
	}

	public void setWardVersion(Integer wardVersion) {
		this.wardVersion = wardVersion;
	}
	
	
	
	
	@Column(name = "transaction_id",insertable=false,updatable=false)
	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	@Column(name = "flag_code",insertable=false,updatable=false)
	public Integer getFlagCode() {
		return flagCode;
	}

	public void setFlagCode(Integer flagCode) {
		this.flagCode = flagCode;
	}

	public int compare(Object firstObjToCompare, Object secondObjToCompare) {
		
		
		String firstString = ((LocalbodyWard) firstObjToCompare).getWardNumber().trim(); 
		String secondString = ((LocalbodyWard) secondObjToCompare).getWardNumber().trim(); 

		if (secondString == null || firstString == null) {
			return 0;
		}

		int lengthFirstStr = firstString.length();
		int lengthSecondStr = secondString.length();

		int index1 = 0;
		int index2 = 0;

		while (index1 < lengthFirstStr && index2 < lengthSecondStr) {
			char ch1 = firstString.charAt(index1);
			char ch2 = secondString.charAt(index2);

			char[] space1 = new char[lengthFirstStr];
			char[] space2 = new char[lengthSecondStr];

			int loc1 = 0;
			int loc2 = 0;

			do {
				space1[loc1++] = ch1;
				index1++;

				if (index1 < lengthFirstStr) {
					ch1 = firstString.charAt(index1);
				} else {
					break;
				}
			} while (Character.isDigit(ch1) == Character.isDigit(space1[0]));

			do {
				space2[loc2++] = ch2;
				index2++;

				if (index2 < lengthSecondStr) {
					ch2 = secondString.charAt(index2);
				} else {
					break;
				}
			} while (Character.isDigit(ch2) == Character.isDigit(space2[0]));

			String str1 = new String(space1);
			String str2 = new String(space2);

			int result;

			if (Character.isDigit(space1[0]) && Character.isDigit(space2[0])) {
				Integer firstNumberToCompare = new Integer(Integer
						.parseInt(str1.trim()));
				Integer secondNumberToCompare = new Integer(Integer
						.parseInt(str2.trim()));
				result = firstNumberToCompare.compareTo(secondNumberToCompare);
			} else {
				result = str1.compareTo(str2);
			}

			if (result != 0) {
				return result;
			}
		}
		return lengthFirstStr - lengthSecondStr;
	}
	
}
