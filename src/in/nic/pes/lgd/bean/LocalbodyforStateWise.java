package in.nic.pes.lgd.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;


@Entity

@NamedNativeQueries({
@NamedNativeQuery(query="SELECT * FROM get_local_gov_setup_fn(:stateCode, :localBodyType)",name="getLocalBodylistStateWise",resultClass=LocalbodyforStateWise.class),
@NamedNativeQuery(query="SELECT * FROM get_local_gov_setup_fn(:stateCode, :localBodyType) where local_body_type_code != :lbtypeCode",name="getLocalBodylistStateWiseChangeType",resultClass=LocalbodyforStateWise.class),
@NamedNativeQuery(query="SELECT * FROM get_local_gov_setup_fn(:stateCode, :localBodyType) where level='D'",name="getLocalBodylistStateWiseForTraditionalD",resultClass=LocalbodyforStateWise.class),
@NamedNativeQuery(query="SELECT * FROM get_local_gov_setup_fn(:stateCode, :localBodyType) where level='I'",name="getLocalBodylistStateWiseForTraditionalI",resultClass=LocalbodyforStateWise.class),
@NamedNativeQuery(query="SELECT * FROM get_local_gov_setup_fn(:stateCode, :localBodyType) where level='V'",name="getLocalBodylistStateWiseForTraditionalV",resultClass=LocalbodyforStateWise.class),
@NamedNativeQuery(query="SELECT * FROM get_local_gov_setup_for_constituency_fn(:stateCode, :localBodyType) ",name="getLocalBodylistStateWiseForConstituencies",resultClass=LocalbodyforStateWise.class),
@NamedNativeQuery(query="SELECT * FROM get_local_gov_setup_fn(:stateCode, :localBodyType) WHERE tier_setup_code = (:tiersetup)",name="getLocalBodytiersetupwise",resultClass=LocalbodyforStateWise.class),
/*added by Ashish Dhupia on 20/1/2015 for Habitation use case*/
@NamedNativeQuery(query="SELECT * FROM get_local_gov_setup_fn(:stateCode, :localBodyType) where level='V'",name="getLocalBodylistStateWiseForTraditionalforP",resultClass=LocalbodyforStateWise.class),
@NamedNativeQuery(query="SELECT * FROM get_local_gov_setup_fn(:stateCode, :LbTypeCategory) where local_body_type_code  in(select local_body_type_code from localbody where local_body_code  in"
					   + "(select entity_link_code from administrative_entity_coverage where admin_coverage_code =(select admin_coverage_code from administration_unit_entity "
					   + " where admin_unit_entity_code =:adminEntityCode and isactive and ispublish='P') and entity_type='G' and isactive) and isactive)"
					   ,name="GET_ULB_TYPE_LIST_WITH_COVERAGE",resultClass=LocalbodyforStateWise.class),
@NamedNativeQuery(query="SELECT * FROM get_local_gov_setup_fn(:stateCode, :LbTypeCategory) where local_body_type_code not in(select local_body_type_code from localbody where local_body_code  in"
					   + "(select entity_link_code from administrative_entity_coverage where admin_coverage_code =(select admin_coverage_code from administration_unit_entity "
					   + " where admin_unit_entity_code =:adminEntityCode and isactive and ispublish='P') and entity_type='G' and isactive) and isactive)"
					   ,name="GET_ULB_TYPE_LIST_WITHOUT_COVERAGE",resultClass=LocalbodyforStateWise.class),
})

public class LocalbodyforStateWise implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6213052190575954621L;
	private int localBodyTypeCode;
	private int tierSetupCode;
	private String nomenclatureEnglish; 
	private String localBodyTypeName;
	private String nomenclatureLocal; 
	
	private String category;
	private String level;
	
	private int parentLocalBodyTypeCode;
	
	@Id
	@Column(name="local_body_type_code")
	public int getLocalBodyTypeCode() {
		return localBodyTypeCode;
	}
	public void setLocalBodyTypeCode(int localBodyTypeCode) {
		this.localBodyTypeCode = localBodyTypeCode;
	}
	@Column(name="tier_setup_code")
	public int getTierSetupCode() {
		return tierSetupCode;
	}
	public void setTierSetupCode(int tierSetupCode) {
		this.tierSetupCode = tierSetupCode;
	}
	@Column(name="local_body_type_name")
	public String getLocalBodyTypeName() {
		return localBodyTypeName;
	}
	public void setLocalBodyTypeName(String localBodyTypeName) {
		this.localBodyTypeName = localBodyTypeName;
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
	public int getParentLocalBodyTypeCode() {
		return parentLocalBodyTypeCode;
	}
	public void setParentLocalBodyTypeCode(int parentLocalBodyTypeCode) {
		this.parentLocalBodyTypeCode = parentLocalBodyTypeCode;
	}
	

}
