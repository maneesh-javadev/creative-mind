package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
@NamedNativeQuery(query="SELECT * FROM get_local_gov_setup_fn(:stateCode,:category) order by level",name="getLocalGovSetupFn",resultClass=GetLocalGovtSetup.class),
@NamedNativeQuery(query="SELECT * FROM get_local_gov_setup_fn(:stateCode) order by level",name="getLocalGovSetupFnF",resultClass=GetLocalGovtSetup.class),
@NamedNativeQuery(query="SELECT * FROM get_local_gov_setup_fn(:stateCode) where category= :localGovtListFlag order by level",name="getLocalGovSetupFnFP",resultClass=GetLocalGovtSetup.class),
@NamedNativeQuery(query="SELECT * FROM get_local_gov_setup_fn(:stateCode,:category) order by level",name="getLocalGovSetupFnFCategory",resultClass=GetLocalGovtSetup.class),
@NamedNativeQuery(query="select distinct on (local_body_type_name,local_body_type_code) get_local_gov_setup_fn.* from get_local_gov_setup_fn(:stateCode) order by local_body_type_code",name="getLocalGovSetupForDeptMapping",resultClass=GetLocalGovtSetup.class),
@NamedNativeQuery(query="SELECT * FROM get_local_gov_setup_fn(:stateCode,:category) where level='D' order by level",name="getLocalGovSetupDPanchayat",resultClass=GetLocalGovtSetup.class),
@NamedNativeQuery(query="SELECT * FROM get_local_gov_setup_fn(:stateCode,:category) where level='I' order by level",name="getLocalGovSetupIPanchayat",resultClass=GetLocalGovtSetup.class),
@NamedNativeQuery(query="SELECT * FROM get_local_gov_setup_fn(:stateCode,:category) where level='V' order by level",name="getLocalGovSetupVPanchayat",resultClass=GetLocalGovtSetup.class),
@NamedNativeQuery(query="SELECT * FROM get_local_gov_setup_fn(:stateCode,:category) where level=:level order by level",name="getLocalGovSetupFnDisturbed",resultClass=GetLocalGovtSetup.class),
@NamedNativeQuery(query = "SELECT * FROM get_local_gov_setup_fn(:stateCode,:category) where local_body_type_code=:lbtcode", name = "getULBLocalGovtSetup", resultClass = GetLocalGovtSetup.class),
@NamedNativeQuery(query = "SELECT second.local_body_type_code,second.local_body_type_name,second.tier_setup_code,second.nomenclature_english,second.nomenclature_local,second.category," +
							"second.level,second.parent_tier_setup_code,second.parent_local_body_type_name from get_local_gov_setup_fn(:stateCode,:type)first,get_local_gov_setup_fn(:stateCode,:type) second " +
							"where first.tier_setup_code=second.parent_tier_setup_code and  first.level=:level", name = "findChildDetail", resultClass = GetLocalGovtSetup.class),
@NamedNativeQuery(query="SELECT * FROM get_local_gov_setup_fn(:stateCode) where tier_setup_code=:tierSetupCode ",name="getLocalGovSetupbyTierSetupCode",resultClass=GetLocalGovtSetup.class),
@NamedNativeQuery(query="SELECT * FROM get_local_gov_setup_fn(:stateCode) where level='V' order by level",name="getLocalGovSetupFnFPopulation",resultClass=GetLocalGovtSetup.class),
@NamedNativeQuery(query="SELECT * FROM get_local_gov_setup_fn_Cantonment(:stateCode) order by level",name="getLocalGovSetupFnCantonment",resultClass=GetLocalGovtSetup.class)
})

public class GetLocalGovtSetup {
	  private Integer localBodyTypeCode;
	  private String localBodyTypeName;
	  private String nomenclatureEnglish;
	  private String nomenclatureLocal;
	  private Integer tierSetupCode;
	  private Integer parentTierSetupCode;
	  private char category;
	  private char level;
	  private String parentLocalBodyTypeName;
	@Id
	@Column(name="local_body_type_code") 
	public Integer getLocalBodyTypeCode() {
		return localBodyTypeCode;
	}
	@Column(name="local_body_type_name")
	public String getLocalBodyTypeName() {
		return localBodyTypeName;
	}
	@Column(name="nomenclature_english")
	public String getNomenclatureEnglish() {
		return nomenclatureEnglish;
	}
	@Column(name="nomenclature_local")
	public String getNomenclatureLocal() {
		return nomenclatureLocal;
	}
	@Column(name="tier_setup_code")
	public Integer getTierSetupCode() {
		return tierSetupCode;
	}
	@Column(name="parent_tier_setup_code")
	public Integer getParentTierSetupCode() {
		return parentTierSetupCode;
	}
	@Column(name="category")
	public char getCategory() {
		return category;
	}
	@Column(name="level")
	public char getLevel() {
		return level;
	}
	public void setLocalBodyTypeCode(Integer localBodyTypeCode) {
		this.localBodyTypeCode = localBodyTypeCode;
	}
	public void setLocalBodyTypeName(String localBodyTypeName) {
		this.localBodyTypeName = localBodyTypeName;
	}
	public void setNomenclatureEnglish(String nomenclatureEnglish) {
		this.nomenclatureEnglish = nomenclatureEnglish;
	}
	public void setNomenclatureLocal(String nomenclatureLocal) {
		this.nomenclatureLocal = nomenclatureLocal;
	}
	public void setTierSetupCode(Integer tierSetupCode) {
		this.tierSetupCode = tierSetupCode;
	}
	public void setParentTierSetupCode(Integer parentTierSetupCode) {
		this.parentTierSetupCode = parentTierSetupCode;
	}
	public void setCategory(char category) {
		this.category = category;
	}
	public void setLevel(char level) {
		this.level = level;
	}
	/**
	 * @return the parentLocalBodyTypeName
	 */
	@Column(name="parent_local_body_type_name")
	public String getParentLocalBodyTypeName() {
		return parentLocalBodyTypeName;
	}
	/**
	 * @param parentLocalBodyTypeName the parentLocalBodyTypeName to set
	 */
	public void setParentLocalBodyTypeName(String parentLocalBodyTypeName) {
		this.parentLocalBodyTypeName = parentLocalBodyTypeName;
	}

}
