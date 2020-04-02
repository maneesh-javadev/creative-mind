package in.nic.pes.lgd.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;


@Entity
@NamedNativeQueries({
@NamedNativeQuery(query="SELECT case when lb.local_body_code  in (select * from get_draft_used_lb_lr_temp(lb.local_body_code,'G')) then  cast ('F' as character) else cast('A' as character) end as operation_state,* from getlblist(:lbTypeCode,:stateCode)lb",name="getDistrictPanchayatListbyState",resultClass=LocalbodyListbyState.class),
//@NamedNativeQuery(query="SELECT * from get_existing_lblist(:lbTypeCode,:stateCode)",name="getExistingPanchayatListbyState",resultClass=LocalbodyListbyState.class),
@NamedNativeQuery(query="SELECT  case when lb.local_body_code  in (select * from get_draft_used_lb_lr_temp(lb.local_body_code,'G')) then  cast ('F' as character) else cast('A' as character) end as operation_state,* from get_lb_list_fn(:lbTypeCode,:stateCode) lb",name="getExistingPanchayatListbyState",resultClass=LocalbodyListbyState.class),
//@NamedNativeQuery(query="SELECT * from get_lb_list_fn(:lbTypeCode,:stateCode) where local_body_code !=:parentLbCode",name="getNewPanchayatListbyStateChangeTier",resultClass=LocalbodyListbyState.class),
@NamedNativeQuery(query="SELECT  case when lb.local_body_code  in (select * from get_draft_used_lb_lr_temp(lb.local_body_code,'G')) then  cast ('F' as character) else cast('A' as character) end as operation_state,* from get_lb_list_fn(:lbTypeCode,:stateCode) lb",name="getNewPanchayatListbyState",resultClass=LocalbodyListbyState.class),
@NamedNativeQuery(query="SELECT  case when lb.local_body_code  in (select * from get_draft_used_lb_lr_temp(lb.local_body_code,'G')) then  cast ('F' as character) else cast('A' as character) end as operation_state,* from get_lb_list_fn(:lbTypeCode,:stateCode) lb where local_body_code != :lbCode",name="getNewPanchayatListbyStateChangeCoverage",resultClass=LocalbodyListbyState.class),
@NamedNativeQuery(query="SELECT case when lb.local_body_code  in (select * from get_draft_used_lb_lr_temp(lb.local_body_code,'G')) then  cast ('F' as character) else cast('A' as character) end as operation_state,* FROM get_landregionwise_lb_list_fn(:local_body_type,:district_id,:local_body_type_code)lb",name="landregionwisedistric",resultClass=LocalbodyListbyState.class),
@NamedNativeQuery(query="SELECT case when lb.local_body_code  in (select * from get_draft_used_lb_lr_temp(lb.local_body_code,'G')) then  cast ('F' as character) else cast('A' as character) end as operation_state,* from get_lb_list_fn(:lbTypeCode,:stateCode) lb where local_body_code not in(:localbodyCode)",name="getNewPanchayatListbyStateforselected",resultClass=LocalbodyListbyState.class),
@NamedNativeQuery(query="SELECT case when a.local_body_code  in (select * from get_draft_used_lb_lr_temp(a.local_body_code,'G')) then  cast ('F' as character) else cast('A' as character) end as operation_state," +
		                " a.local_body_type_code,a.local_body_type_name,a.local_body_code,"+
						"Coalesce(a.local_body_name_english||'(Z.P.-'||(select local_body_name_english from get_lb_parent_list(a.local_body_code))||')',"+
						"a.local_body_name_english) as local_body_name_english,"+
						"a.local_body_name_local from get_lb_list_fn(:lbTypeCode,:stateCode) a where local_body_code !=:parentLbCode", name="getNewPanchayatListbyStateChangeTier",resultClass=LocalbodyListbyState.class),
						/* query added for the  GTA list in manage GTA (parent) author Ashish Dhupia , Date : 23/07/2014*/
@NamedNativeQuery(query="SELECT a.local_body_type_code,a.local_body_type_name,a.local_body_code,"+
						"Coalesce(a.local_body_name_english||'(Z.P.-'||(select local_body_name_english from get_lb_parent_list(a.local_body_code))||')',"+
						"a.local_body_name_english) as local_body_name_english,"+
						"a.local_body_name_local,cast('' as character) as operation_state from get_lb_list_fn(:lbTypeCodeGta,:stateCode) a where local_body_code !=:parentLbCode " +
						" union SELECT a.local_body_type_code,a.local_body_type_name,a.local_body_code,"+
						"Coalesce(a.local_body_name_english||'(Z.P.-'||(select local_body_name_english from get_lb_parent_list(a.local_body_code))||')',"+
						"a.local_body_name_english) as local_body_name_english,"+
						"a.local_body_name_local ,cast('' as character) as operation_state from get_lb_list_fn(:lbTypeCodeZp,:stateCode) a where local_body_code !=:parentLbCode", name="getNewPanchayatListbyStateChangeTierforGta",resultClass=LocalbodyListbyState.class),
						/**
						 * add operation_state column in Query for mapping in Pojo by Maneesh Kumar
						 */
				/* query added for the  district localbody freeze author Anchal Todariya , Date : 18/03/2015*/
@NamedNativeQuery(query="select l.local_body_code,l.local_body_name_english,l.local_body_name_local,l.local_body_type_code,'' as local_body_type_name,cast('' as character) as operation_state from localbody l,localbody_districts ld where l.local_body_code=ld.local_body_code and l.local_body_version=ld.local_body_version and ld.district_code=:districtCode and l.local_body_type_code=:type and l.isactive",name="getIntermediatePanchayatListbyDistrict",resultClass=LocalbodyListbyState.class),
@NamedNativeQuery(query="SELECT  case when lb.local_body_code  in (select * from get_draft_used_lb_lr_temp(lb.local_body_code,'G')) then  cast ('F' as character) else cast('A' as character) end "
					  + " as operation_state,* from get_lb_list_fn_char(:lbTypeCode,:stateCode) lb ",name="GET_URBAN_LIST_BY_LOCAL_BODY_TYPE_LIST",resultClass=LocalbodyListbyState.class),
})
public class LocalbodyListbyState  implements Serializable {

	
	 /**
	 * 
	 */
	private static final long serialVersionUID = -2679753219652950115L;
	private Integer localBodyCode; 
	 private Integer localBodyTypeCode;   
     private String localBodyTypeName;   
     private String localBodyNameEnglish;
 	 private String localBodyNameLocal;
 	// private Integer childCount;
 	 
 	
 	private Character operation_state; 
 	@Id
 	@Column(name="local_body_code")
	public Integer getLocalBodyCode() {
		return localBodyCode;
	}
	public void setLocalBodyCode(Integer localBodyCode) {
		this.localBodyCode = localBodyCode;
	}
	
	@Column(name="local_body_type_code")
	public Integer getLocalBodyTypeCode() {
		return localBodyTypeCode;
	}	
	public void setLocalBodyTypeCode(Integer localBodyTypeCode) {
		this.localBodyTypeCode = localBodyTypeCode;
	}
	
	@Column(name="local_body_type_name")
	public String getLocalBodyTypeName() {
		return localBodyTypeName;
	}
	public void setLocalBodyTypeName(String localBodyTypeName) {
		this.localBodyTypeName = localBodyTypeName;
	}
	
	@Column(name="local_body_name_english")
	public String getLocalBodyNameEnglish() {
		return localBodyNameEnglish;
	}
	public void setLocalBodyNameEnglish(String localBodyNameEnglish) {
		this.localBodyNameEnglish = localBodyNameEnglish;
	}
	
	@Column(name="local_body_name_local")
	public String getLocalBodyNameLocal() {
		return localBodyNameLocal;
	}
	public void setLocalBodyNameLocal(String localBodyNameLocal) {
		this.localBodyNameLocal = localBodyNameLocal;
	}
	public Character getOperation_state() {
		return operation_state;
	}
	public void setOperation_state(Character operation_state) {
		this.operation_state = operation_state;
	}
	
//	@Column(name="child_count")
//	public Integer getChildCount() {
//		return childCount;
//	}
//	public void setChildCount(Integer childCount) {
//		this.childCount = childCount;
//	}
 	
	
}
