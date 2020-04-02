package in.nic.pes.lgd.bean;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * LocalBodyTiersSetup
 */
@Entity
@NamedNativeQuery(query = "select * from get_local_body_tier_setup_parent_list(:tierSetupCode) where tier_setup_code <> :tierSetupCode", name ="getLocalBodyTierSetupParentList",resultClass=LocalBodyTiersSetup.class)
@Table(name = "local_body_tiers_setup", schema = "public")
public class LocalBodyTiersSetup implements Serializable{


	private static final long serialVersionUID = 1L;
	private Integer tierSetupCode;
	private Integer localBodyTypeCode;
	private String nomenclatureEnglish;
	private String nomenclatureLocal;
	private Integer parentTierSetupCode;
	private boolean isactive;
	private Integer localBodySetupCode;
	private Integer localBodySetupVersion;
	private LocalBodyType localbodytype;
	private LocalBodySetup localBodySetup;
	
	private Set<LocalBodySubtype> localBodySubtypes= new HashSet<LocalBodySubtype>(0);
	
	/**
	 * @return the localBodySubtypes
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "localBodyTiersSetup")
	public Set<LocalBodySubtype> getLocalBodySubtypes() {
		return localBodySubtypes;
	}
	/**
	 * @param localBodySubtypes the localBodySubtypes to set
	 */
	public void setLocalBodySubtypes(Set<LocalBodySubtype> localBodySubtypes) {
		this.localBodySubtypes = localBodySubtypes;
	}
	public LocalBodyTiersSetup() {
		
	}
	public LocalBodyTiersSetup(Integer tierSetupCode) {
		super();
		this.tierSetupCode = tierSetupCode;
	}
	
// TODO Remove unused code found by UCDetector
// 	public LocalBodyTiersSetup(Integer tierSetupCode,
// 			Integer localBodyTypeCode, String nomenclatureEnglish,
// 			String nomenclatureLocal, Integer parentTierSetupCode,
// 			boolean isactive, Integer localBodySetupCode,
// 			Integer localBodySetupVersion, LocalBodyType localbodytype,
// 			LocalBodySetup localBodySetup) {
// 		super();
// 		this.tierSetupCode = tierSetupCode;
// 		this.localBodyTypeCode = localBodyTypeCode;
// 		this.nomenclatureEnglish = nomenclatureEnglish;
// 		this.nomenclatureLocal = nomenclatureLocal;
// 		this.parentTierSetupCode = parentTierSetupCode;
// 		this.isactive = isactive;
// 		this.localBodySetupCode = localBodySetupCode;
// 		this.localBodySetupVersion = localBodySetupVersion;
// 		this.localbodytype = localbodytype;
// 		this.localBodySetup = localBodySetup;
// 	}
	
// TODO Remove unused code found by UCDetector
// 	public LocalBodyTiersSetup(Integer tierSetupCode,
// 			Integer localBodyTypeCode, String nomenclatureEnglish,
// 			String nomenclatureLocal, Integer parentTierSetupCode,
// 			boolean isactive, Integer localBodySetupCode,
// 			Integer localBodySetupVersion, LocalBodyType localbodytype,
// 			LocalBodySetup localBodySetup,
// 			Set<LocalBodySubtype> localBodySubtypes) {
// 		super();
// 		this.tierSetupCode = tierSetupCode;
// 		this.localBodyTypeCode = localBodyTypeCode;
// 		this.nomenclatureEnglish = nomenclatureEnglish;
// 		this.nomenclatureLocal = nomenclatureLocal;
// 		this.parentTierSetupCode = parentTierSetupCode;
// 		this.isactive = isactive;
// 		this.localBodySetupCode = localBodySetupCode;
// 		this.localBodySetupVersion = localBodySetupVersion;
// 		this.localbodytype = localbodytype;
// 		this.localBodySetup = localBodySetup;
// 		this.localBodySubtypes = localBodySubtypes;
// 	}
	/**
	 * @return the tierSetupCode
	 */
	@Id
	@Column(name="tier_setup_code", nullable = false)
	public Integer getTierSetupCode() {
		return tierSetupCode;
	}
	/**
	 * @param tierSetupCode the tierSetupCode to set
	 */
	public void setTierSetupCode(Integer tierSetupCode) {
		this.tierSetupCode = tierSetupCode;
	}
	/**
	 * @return the localBodyTypeCode
	 */
	@Column(name = "local_body_type_code", insertable=false,updatable=false) //nullable = false ,
	public Integer getLocalBodyTypeCode() {
		return localBodyTypeCode;
	}
	/**
	 * @param localBodyTypeCode the localBodyTypeCode to set
	 */
	public void setLocalBodyTypeCode(Integer localBodyTypeCode) {
		this.localBodyTypeCode = localBodyTypeCode;
	}
	/**
	 * @return the nomenclatureEnglish
	 */
	@Column(name = "nomenclature_english", nullable = false, length = 50)
	public String getNomenclatureEnglish() {
		return nomenclatureEnglish;
	}
	/**
	 * @param nomenclatureEnglish the nomenclatureEnglish to set
	 */
	public void setNomenclatureEnglish(String nomenclatureEnglish) {
		this.nomenclatureEnglish = nomenclatureEnglish;
	}
	/**
	 * @return the nomenclatureLocal
	 */
	@Column(name = "nomenclature_local", length = 50)
	public String getNomenclatureLocal() {
		return nomenclatureLocal;
	}
	/**
	 * @param nomenclatureLocal the nomenclatureLocal to set
	 */
	public void setNomenclatureLocal(String nomenclatureLocal) {
		this.nomenclatureLocal = nomenclatureLocal;
	}
	/**
	 * @return the parentTierSetupCode
	 */
	@Column(name = "parent_tier_setup_code")
	public Integer getParentTierSetupCode() {
		return parentTierSetupCode;
	}
	/**
	 * @param parentTierSetupCode the parentTierSetupCode to set
	 */
	public void setParentTierSetupCode(Integer parentTierSetupCode) {
		this.parentTierSetupCode = parentTierSetupCode;
	}
	/**
	 * @return the isactive
	 */
	@Column(name = "isactive", nullable = false)
	public boolean isIsactive() {
		return isactive;
	}
	/**
	 * @param isactive the isactive to set
	 */
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	/**
	 * @return the localBodySetupCode
	 */
	@Column(name = "local_body_setup_code",nullable = false, insertable=false, updatable=false)
	public Integer getLocalBodySetupCode() {
		return localBodySetupCode;
	}
	/**
	 * @param localBodySetupCode the localBodySetupCode to set
	 */
	public void setLocalBodySetupCode(Integer localBodySetupCode) {
		this.localBodySetupCode = localBodySetupCode;
	}
	/**
	 * @return the localBodySetupVersion
	 */
	@Column(name = "local_body_setup_version",nullable = false, insertable=false,updatable=false)
	public Integer getLocalBodySetupVersion() {
		return localBodySetupVersion;
	}
	/**
	 * @param localBodySetupVersion the localBodySetupVersion to set
	 */
	public void setLocalBodySetupVersion(Integer localBodySetupVersion) {
		this.localBodySetupVersion = localBodySetupVersion;
	}
	/**
	 * @return the localbodytype
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "local_body_type_code", referencedColumnName = "local_body_type_code", nullable = false)
	public LocalBodyType getLocalbodytype() {
		return localbodytype;
	}
	/**
	 * @param localbodytype the localbodytype to set
	 */
	public void setLocalbodytype(LocalBodyType localbodytype) {
		this.localbodytype = localbodytype;
	}
	/**
	 * @return the localBodySetup
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "local_body_setup_code", referencedColumnName = "local_body_setup_code", nullable = false),
			@JoinColumn(name = "local_body_setup_version", referencedColumnName = "local_body_setup_version", nullable = false) })
	public LocalBodySetup getLocalBodySetup() {
		return this.localBodySetup;
	}
	/**
	 * @param localBodySetup the localBodySetup to set
	 */
	public void setLocalBodySetup(LocalBodySetup localBodySetup) {
		this.localBodySetup = localBodySetup;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LocalBodyTiersSetup [tierSetupCode=" + tierSetupCode
				+ ", localBodyTypeCode=" + localBodyTypeCode
				+ ", nomenclatureEnglish=" + nomenclatureEnglish
				+ ", nomenclatureLocal=" + nomenclatureLocal
				+ ", parentTierSetupCode=" + parentTierSetupCode
				+ ", isactive=" + isactive + ", localBodySetupCode="
				+ localBodySetupCode + ", localBodySetupVersion="
				+ localBodySetupVersion + ", localbodytype=" + localbodytype
				+ ", localBodySetup=" + localBodySetup + "]";
	}
	






}
