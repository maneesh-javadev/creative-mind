package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Transient;



@Entity
@NamedNativeQueries({
@NamedNativeQuery(query="SELECT district_code as entityCode,district_name_english as entityNameEnglish,district_name_local as entityNameLocal,census_2011_code as census2011Code,sscode as ssCode FROM district where slc=:entityCode and isactive=true order by district_name_english",name="getStandardCodesDistrict",resultClass=StandardCodes.class),
@NamedNativeQuery(query="SELECT subdistrict_code as entityCode,subdistrict_name_english as entityNameEnglish,subdistrict_name_local as entityNameLocal,census_2011_code as census2011Code,sscode as ssCode FROM subdistrict where dlc=:entityCode and isactive=true order by subdistrict_name_english",name="getStandardCodesSubDistrict",resultClass=StandardCodes.class),
@NamedNativeQuery(query="SELECT village_code as entityCode,village_name_english as entityNameEnglish,village_name_local as entityNameLocal,census_2011_code as census2011Code,sscode as ssCode FROM village where tlc=:entityCode and isactive=true order by village_name_english",name="getStandardCodesVillage",resultClass=StandardCodes.class),
@NamedNativeQuery(query="SELECT district_code as entityCode,district_name_english as entityNameEnglish,district_name_local as entityNameLocal,census_2011_code as census2011Code,sscode as ssCode FROM district where dlc=:entityCode and isactive=true order by district_name_english",name="getStandardCodesDistrictforDistUser",resultClass=StandardCodes.class),
@NamedNativeQuery(query="SELECT subdistrict_code as entityCode,subdistrict_name_english as entityNameEnglish,subdistrict_name_local as entityNameLocal,census_2011_code as census2011Code,sscode as ssCode FROM subdistrict where dlc=:entityCode and isactive=true order by subdistrict_name_english",name="getStandardCodesSubDistrictforDistUser",resultClass=StandardCodes.class),
@NamedNativeQuery(query="SELECT village_code as entityCode,village_name_english as entityNameEnglish,village_name_local as entityNameLocal,census_2011_code as census2011Code,sscode as ssCode FROM village where tlc=:entityCode and isactive=true order by village_name_english",name="getStandardCodesVillageforDistUser",resultClass=StandardCodes.class),
@NamedNativeQuery(query="SELECT local_body_code as entityCode,local_body_name_english as entityNameEnglish,local_body_name_local as entityNameLocal,census_2011_code as census2011Code,sscode as ssCode FROM localbody where slc=:entityCode and local_body_type_code=:type and isactive=true order by local_body_name_english",name="getStandardCodesforLB",resultClass=StandardCodes.class),
@NamedNativeQuery(query="SELECT local_body_code as entityCode,local_body_name_english as entityNameEnglish,local_body_name_local as entityNameLocal,census_2011_code as census2011Code,sscode as ssCode FROM localbody where slc=:entityCode and local_body_type_code=:type and parent_lblc=:parent and isactive=true order by local_body_name_english",name="getStandardCodesforLBWithParent",resultClass=StandardCodes.class),
@NamedNativeQuery(query="SELECT state_code as entityCode,state_name_english as entityNameEnglish,state_name_local as entityNameLocal,census_2011_code as census2011Code,'' as ssCode FROM state where slc=:entityCode and isactive=true order by state_name_english",name="getStandardCodesState",resultClass=StandardCodes.class),
// added by anchal todariya
@NamedNativeQuery(query="select l.local_body_code as entityCode,l.local_body_name_english as entityNameEnglish,l.local_body_name_local as entityNameLocal,l.census_2011_code as census2011Code,l.sscode as ssCode from localbody l,localbody_districts ld where l.local_body_code=ld.local_body_code and l.local_body_version=ld.local_body_version and ld.district_code=:districtCode and l.local_body_type_code=:type and l.isactive",name="getStandardCodesforLBDistricts",resultClass=StandardCodes.class),
// added by pooja on 09-06-2015
@NamedNativeQuery(query="SELECT block_code as entityCode,block_name_english as entityNameEnglish,block_name_local as entityNameLocal,cast(null as character varying) census2011Code,ss_code as ssCode FROM block where dlc=:entityCode and isactive=true order by block_name_english",name="getStandardCodesblock",resultClass=StandardCodes.class),
@NamedNativeQuery(query="SELECT admin_unit_level_code as entityCode,unit_level_name_english as entityNameEnglish,unit_level_name_local as entityNameLocal,cast(null as character varying) census2011Code,"
		+ "cast(null as integer) ssCode from administration_unit_level  where ispublish = 'P' and slc=:entityCode and isactive order by unit_level_name_english",name="getStandardCodesAdminLevel",resultClass=StandardCodes.class),
@NamedNativeQuery(query="select admin_unit_entity_code as entityCode,admin_entity_name_english as entityNameEnglish,admin_entity_name_local as entityNameLocal,cast(null as character varying) census2011Code,"
					   + "cast(null as integer) ssCode from administration_unit_entity where slc=:entityCode and isactive and admin_unit_level_code=:adminCode and parent_unit_entity_code=:parentAdminCode"
					   + " order by admin_entity_name_english",name="getStandardCodesAdminEntityState",resultClass=StandardCodes.class),
@NamedNativeQuery(query="select admin_unit_entity_code as entityCode,admin_entity_name_english as entityNameEnglish,admin_entity_name_local as entityNameLocal,cast(null as character varying) census2011Code,"
		   + "cast(null as integer) ssCode from administration_unit_entity where districtcode=:entityCode and isactive and admin_unit_level_code=:adminCode and parent_unit_entity_code=:parentAdminCode"
		   + " order by admin_entity_name_english",name="getStandardCodesAdminEntityDistrict",resultClass=StandardCodes.class),
@NamedNativeQuery(query="SELECT org_located_level_code as entityCode,org_level_specific_name as entityNameEnglish,org_level_specific_name_local as  entityNameLocal,cast(null as character varying) census2011Code,"
		+ "cast(null as integer) ssCode FROM get_landregionwise_department_list_new_fn(:entityCode,:entityType)",name="getDeptList",resultClass=StandardCodes.class),
@NamedNativeQuery(query="select org_code as entityCode,org_name as entityNameEnglish,org_name_local as  entityNameLocal,cast(null as character varying) census2011Code,cast(null as integer) ssCode  from organization "
					+ " where  parent_org_code =:entityCode",name="getOrgStateList",resultClass=StandardCodes.class),
@NamedNativeQuery(query="select ol.org_located_level_code as entityCode,ol.org_level_specific_name as entityNameEnglish,ol.org_level_specific_name_local as  entityNameLocal,cast(null as character varying) "
		+ "census2011Code,cast(null as integer) ssCode  from org_located_at_levels ol inner join org_units  ou on ol.org_located_level_code=ou.org_located_level_code where ou.entity_lc=:entityCode and "
		+ " ou.entity_type=:entityType and ol.olc in(select olc from organization  where  parent_org_code =:orgCode)",name="getOrgLevelList",resultClass=StandardCodes.class),

@NamedNativeQuery(query="select org_unit_code as entityCode,org_unit_name as entityNameEnglish,org_unit_name_local as entityNameLocal,cast(null as character varying) census2011Code,cast(null as integer) ssCode"
		+ " from org_units where org_located_level_code  in (select org_located_level_code from org_located_at_levels where olc =:entityCode and located_at_level =:entityType and isactive) and headoffice='H' "
		+ " and isactive order by entityNameEnglish",name="getOrgUnitsList",resultClass=StandardCodes.class),

})

public class StandardCodes {
	
	@Id
	@Column(name = "entityCode")
     private int entityCode;
	@Column(name = "entityNameEnglish")
	 private String entityNameEnglish;
	@Column(name = "entityNameLocal")
	 private String entityNameLocal;
/*	@Column(name = "entityNameLocalch")
	 private String entityNameLocalch;*/
	@Column(name = "census2011Code") 
	private String census2011Code;
	/*@Column(name = "census2011Codech") 
	private Integer census2011Codech;*/
	@Column(name = "ssCode")
	 private String ssCode;
	/*@Column(name = "ssCodech")
	 private String ssCodech;*/
	
	/**
	 * Save Local Body Freeze/Un-freeze.
	 * @author Ashish Dhupia on 15-02-2015
	 * 
	 */

	@Transient
	private String coverage;
	
	@Transient
	private Integer lblc;
	
	@Transient
	private Integer status;

	public int getEntityCode() {
		return entityCode;
	}
	public void setEntityCode(int entityCode) {
		this.entityCode = entityCode;
	}
	public String getEntityNameEnglish() {
		return entityNameEnglish;
	}
	public void setEntityNameEnglish(String entityNameEnglish) {
		this.entityNameEnglish = entityNameEnglish;
	}
	public String getEntityNameLocal() {
		return entityNameLocal;
	}
	public void setEntityNameLocal(String entityNameLocal) {
		this.entityNameLocal = entityNameLocal;
	}
	
	public String getSsCode() {
		return ssCode;
	}
	public void setSsCode(String ssCode) {
		this.ssCode = ssCode;
	}
	
/*	public String getEntityNameLocalch() {
		return entityNameLocalch;
	}
	public void setEntityNameLocalch(String entityNameLocalch) {
		this.entityNameLocalch = entityNameLocalch;
	}
	*/
/*	public String getSsCodech() {
		return ssCodech;
	}
	public void setSsCodech(String ssCodech) {
		this.ssCodech = ssCodech;
	}*/
	public String getCensus2011Code() {
		return census2011Code;
	}
	public void setCensus2011Code(String census2011Code) {
		this.census2011Code = census2011Code;
	}
	/*public Integer getCensus2011Codech() {
		return census2011Codech;
	}
	public void setCensus2011Codech(Integer census2011Codech) {
		this.census2011Codech = census2011Codech;
	}*/
	public String getCoverage() {
		return coverage;
	}
	public void setCoverage(String coverage) {
		this.coverage = coverage;
	}
	
	public Integer getLblc() {
		return lblc;
	}
	public void setLblc(Integer lblc) {
		this.lblc = lblc;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "StandardCodes [entityCode="
				+ entityCode
				+ ", "
				+ (entityNameEnglish != null ? "entityNameEnglish="
						+ entityNameEnglish + ", " : "")
				+ (entityNameLocal != null ? "entityNameLocal="
						+ entityNameLocal + ", " : "")
				+ (census2011Code != null ? "census2011Code=" + census2011Code
						+ ", " : "")
				+ (ssCode != null ? "ssCode=" + ssCode + ", " : "")
				+ (coverage != null ? "coverage=" + coverage + ", " : "")
				+ (lblc != null ? "lblc=" + lblc + ", " : "")
				+ (status != null ? "status=" + status : "") + "]";
	}
		
	
	
}
