package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;


@Entity
@NamedNativeQuery(query=" SELECT * FROM get_local_body_tier_setup_parent_list(:localBodyTypeCode)",name="getLocalGovtBodyParentList",resultClass=LocalGovtBodyParentWise.class)
public class LocalGovtBodyParentWise {	
	

	private int tierSetupCode;
	private int localBodyTypeCode;
	private String nomenclatureEnglish;
	private String nomenclatureLocal;
//	private int parentLocalBodyTypeCode;
	private boolean isactive;
	private int localBodySetupCode;
	private int localBodySetupVersion;



	@Column(name = "local_body_type_code", nullable = false)
	public int getLocalBodyTypeCode() {
		return this.localBodyTypeCode;
	}

	public void setLocalBodyTypeCode(int localBodyTypeCode) {
		this.localBodyTypeCode = localBodyTypeCode;
	}

	@Column(name = "nomenclature_english", nullable = false, length = 50)
	public String getNomenclatureEnglish() {
		return this.nomenclatureEnglish;
	}

	public void setNomenclatureEnglish(String nomenclatureEnglish) {
		this.nomenclatureEnglish = nomenclatureEnglish;
	}

	@Column(name = "nomenclature_local", length = 50)
	public String getNomenclatureLocal() {
		return this.nomenclatureLocal;
	}

	public void setNomenclatureLocal(String nomenclatureLocal) {
		this.nomenclatureLocal = nomenclatureLocal;
	}

	/*@Column(name = "parent_local_body_type_code")
	public Integer getParentLocalBodyTypeCode() {
		return this.parentLocalBodyTypeCode;
	}

	public void setParentLocalBodyTypeCode(Integer parentLocalBodyTypeCode) {
		this.parentLocalBodyTypeCode = parentLocalBodyTypeCode;
	}*/

	@Column(name = "isactive", nullable = false)
	public boolean isIsactive() {
		return this.isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}


	
	@Column(name = "local_body_setup_code")
	public int getLocalBodySetupCode() {
		return localBodySetupCode;
	}

	public void setLocalBodySetupCode(int localBodySetupCode) {
		this.localBodySetupCode = localBodySetupCode;
	}
	
	@Column(name = "local_body_setup_version")
	public int getLocalBodySetupVersion() {
		return localBodySetupVersion;
	}

	public void setLocalBodySetupVersion(int localBodySetupVersion) {
		this.localBodySetupVersion = localBodySetupVersion;
	}

	@Id
	@Column(name="tier_setup_code")
	public int getTierSetupCode() {
		return tierSetupCode;
	}

	public void setTierSetupCode(int tierSetupCode) {
		this.tierSetupCode = tierSetupCode;
	}


}
