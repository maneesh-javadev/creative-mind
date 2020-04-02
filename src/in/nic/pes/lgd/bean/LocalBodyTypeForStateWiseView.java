package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query="SELECT * FROM get_local_gov_setup_fn(:stateCode)",name="getLocalBodylistStateWiseView",resultClass=LocalBodyTypeForStateWiseView.class)
public class LocalBodyTypeForStateWiseView {
	
	
	private int localBodyTypeCode;
	private String nomenclatureEnglish; 
	private String nomenclatureLocal; 
	private String category;
	private String level;
	private int parentTierSetupCode;
	
	@Id
	@Column(name="local_body_type_code")
	public int getLocalBodyTypeCode() {
		return localBodyTypeCode;
	}
	public void setLocalBodyTypeCode(int localBodyTypeCode) {
		this.localBodyTypeCode = localBodyTypeCode;
	}
	
	@Column(name="nomenclature_english")
	public String getNomenclatureEnglish() {
		return nomenclatureEnglish;
	}
	public void setNomenclatureEnglish(String nomenclatureEnglish) {
		this.nomenclatureEnglish = nomenclatureEnglish;
	}
	
	@Column(name="nomenclature_local")
	public String getNomenclatureLocal() {
		return nomenclatureLocal;
	}
	public void setNomenclatureLocal(String nomenclatureLocal) {
		this.nomenclatureLocal = nomenclatureLocal;
	}
	
	@Column(name="category")
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	@Column(name="level")
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	@Column(name="parent_tier_setup_code")
	public int getParentTierSetupCode() {
		return parentTierSetupCode;
	}
	public void setParentTierSetupCode(int parentTierSetupCode) {
		this.parentTierSetupCode = parentTierSetupCode;
	}
	

}
