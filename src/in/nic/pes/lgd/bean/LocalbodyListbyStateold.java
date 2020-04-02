package in.nic.pes.lgd.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;


@Entity
@NamedNativeQueries({
@NamedNativeQuery(query="SELECT * from getlblist(:lbTypeCode,:stateCode)",name="getDistrictPanchayatListbyStateold",resultClass=LocalbodyListbyStateold.class),
//@NamedNativeQuery(query="SELECT * from get_existing_lblist(:lbTypeCode,:stateCode)",name="getExistingPanchayatListbyState",resultClass=LocalbodyListbyState.class),
@NamedNativeQuery(query="SELECT * from get_lb_list_fn(:lbTypeCode,:stateCode)",name="getExistingPanchayatListbyStateold",resultClass=LocalbodyListbyStateold.class),
//@NamedNativeQuery(query="SELECT * from get_lb_list_fn(:lbTypeCode,:stateCode) where local_body_code !=:parentLbCode",name="getNewPanchayatListbyStateChangeTier",resultClass=LocalbodyListbyState.class),
@NamedNativeQuery(query="SELECT * from get_lb_list_fn(:lbTypeCode,:stateCode)",name="getNewPanchayatListbyStateold",resultClass=LocalbodyListbyStateold.class),
@NamedNativeQuery(query="SELECT * from get_lb_list_fn(:lbTypeCode,:stateCode) where local_body_code != :lbCode",name="getNewPanchayatListbyStateChangeCoverageold",resultClass=LocalbodyListbyStateold.class),
@NamedNativeQuery(query="SELECT * FROM get_landregionwise_lb_list_fn(:local_body_type,:district_id,:local_body_type_code)",name="landregionwisedistricold",resultClass=LocalbodyListbyStateold.class),
@NamedNativeQuery(query="SELECT * from get_lb_list_fn(:lbTypeCode,:stateCode) where local_body_code not in(:localbodyCode)",name="getNewPanchayatListbyStateforselectedold",resultClass=LocalbodyListbyStateold.class),
@NamedNativeQuery(query="SELECT a.local_body_type_code,a.local_body_type_name,a.local_body_code,"+
						"Coalesce(a.local_body_name_english||'(Z.P.-'||(select local_body_name_english from get_lb_parent_list(a.local_body_code))||')',"+
						"a.local_body_name_english) as local_body_name_english,"+
						"a.local_body_name_local from get_lb_list_fn(:lbTypeCode,:stateCode) a where local_body_code !=:parentLbCode", name="getNewPanchayatListbyStateChangeTierold",resultClass=LocalbodyListbyStateold.class),
						/* query added for the  GTA list in manage GTA (parent) author Ashish Dhupia , Date : 23/07/2014*/
						@NamedNativeQuery(query="SELECT a.local_body_type_code,a.local_body_type_name,a.local_body_code,"+
						"Coalesce(a.local_body_name_english||'(Z.P.-'||(select local_body_name_english from get_lb_parent_list(a.local_body_code))||')',"+
						"a.local_body_name_english) as local_body_name_english,"+
						"a.local_body_name_local from get_lb_list_fn(:lbTypeCodeGta,:stateCode) a where local_body_code !=:parentLbCode " +
						" union SELECT a.local_body_type_code,a.local_body_type_name,a.local_body_code,"+
						"Coalesce(a.local_body_name_english||'(Z.P.-'||(select local_body_name_english from get_lb_parent_list(a.local_body_code))||')',"+
						"a.local_body_name_english) as local_body_name_english,"+
						"a.local_body_name_local from get_lb_list_fn(:lbTypeCodeZp,:stateCode) a where local_body_code !=:parentLbCode", name="getNewPanchayatListbyStateChangeTierforGtaold",resultClass=LocalbodyListbyStateold.class),


})
public class LocalbodyListbyStateold  implements Serializable {

	
	 /**
	 * 
	 */
	private static final long serialVersionUID = -7314628039523613624L;
	private Integer localBodyCode; 
	 private Integer localBodyTypeCode;   
     private String localBodyTypeName;   
     private String localBodyNameEnglish;
 	 private String localBodyNameLocal;
 	// private Integer childCount;
 	 
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
	
//	@Column(name="child_count")
//	public Integer getChildCount() {
//		return childCount;
//	}
//	public void setChildCount(Integer childCount) {
//		this.childCount = childCount;
//	}
 	
	
}
