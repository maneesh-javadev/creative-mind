package com.cmc.lgd.localbody.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
	@NamedNativeQuery(query = "SELECT *, null as child_count from get_lb_list_fn_temp(:lbTypeCode, :stateCode, :draftTempCode, :processId);", name ="Local_Body_List_By_Type_State", resultClass = LocalBodyEntityDetails.class),
	@NamedNativeQuery(query = "SELECT *, null as child_count from get_lb_list_fn_temp_district(:lbTypeCode, :districtCode, :draftTempCode, :processId);", name ="Local_Body_List_By_Type_District", resultClass = LocalBodyEntityDetails.class),
	@NamedNativeQuery(query = "SELECT *, null as local_body_type_code , null as local_body_type_name FROM get_parentwise_lb_list_fn(:localBodyCode, :draftTempCode, :processId)", name = "Local_Body_List_By_Parent", resultClass = LocalBodyEntityDetails.class),
	@NamedNativeQuery(query = "SELECT local_body_code, local_body_version as child_count, local_body_name_english, local_body_name_local, null as local_body_type_code, null as local_body_type_name, null as isdisturbed, null as isused from localbody where isactive and local_body_code = :localBodyCode", name = "View_Local_Body_Details", resultClass = LocalBodyEntityDetails.class),
	@NamedNativeQuery(query = "SELECT a.local_body_type_code, a.local_body_type_name, a.local_body_code, " + 
							  "Coalesce(a.local_body_name_english||' ( Z.P. - '||(select local_body_name_english from get_lb_parent_list(a.local_body_code)) || ')', a.local_body_name_english) as local_body_name_english, "+
							  "a.local_body_name_local, null as child_count, null as isdisturbed, null as isused " +
							  "from get_lb_list_fn(:parentLBType, :stateCode) a where local_body_code != :parentLbCode", name = "List_Parent_Local_Bodies", resultClass = LocalBodyEntityDetails.class),
	@NamedNativeQuery(query = "SELECT *, null as local_body_type_code , null as local_body_type_name FROM get_parentwise_lb_list_fn_without_disturbed(:localBodyCode, :draftTempCode, :processId)", name = "Local_Body_List_By_Parent_RPT", resultClass = LocalBodyEntityDetails.class),
	@NamedNativeQuery(query = "SELECT *, null as child_count from get_lb_list_fn_temp_without_disturbed(:lbTypeCode, :stateCode, :draftTempCode, :processId);", name ="Local_Body_List_By_Type_State_RPT", resultClass = LocalBodyEntityDetails.class),
	@NamedNativeQuery(query = "SELECT *, null as child_count from get_lb_list_fn_temp(:lbTypeCode, :stateCode, null, null) where local_body_code not in(:existlbList);", name ="DISTRICT_PANCHYAT_LIST_FOR_DEPT", resultClass = LocalBodyEntityDetails.class),
	@NamedNativeQuery(query = "SELECT *, null as child_count from get_lb_list_fn_temp(:lbTypeCode, :stateCode, null, null) where local_body_code  in(:existlbList);", name ="EXIST_DISTRICT_PANCHYAT_LIST_FOR_DEPT", resultClass = LocalBodyEntityDetails.class),
	
	@NamedNativeQuery(query = "SELECT *, null as local_body_type_code , null as local_body_type_name FROM get_parentwise_lb_list_fn(:localBodyCode, null, null) where local_body_code not in(:existlbList);", name = "LBLIST_By_Parent_FOR_DEPT", resultClass = LocalBodyEntityDetails.class),
	@NamedNativeQuery(query = "SELECT *, null as local_body_type_code , null as local_body_type_name FROM get_parentwise_lb_list_fn(:localBodyCode, null, null) where local_body_code in(:existlbList);", name = "EXIST_LBLIST_By_Parent_FOR_DEPT", resultClass = LocalBodyEntityDetails.class),

})
public class LocalBodyEntityDetails {
	
	@Id
	@Column(name="local_body_code")
	private Integer localBodyCode; 
	
	@Column(name="local_body_type_code")
	private Integer localBodyTypeCode;   
    
	@Column(name="local_body_type_name")
	private String localBodyTypeName;   
    
	@Column(name="local_body_name_english")
	private String localBodyNameEnglish;
	
	@Column(name="local_body_name_local")
	private String localBodyNameLocal;
	
	@Column(name="child_count")
	private Integer childCount;
	
	@Column(name="isdisturbed")
	private Boolean isDisturbed;
	
	@Column(name="isused")
	private Boolean isUsed;
	
	

	public Integer getLocalBodyCode() {
		return localBodyCode;
	}

	public void setLocalBodyCode(Integer localBodyCode) {
		this.localBodyCode = localBodyCode;
	}

	public Integer getLocalBodyTypeCode() {
		return localBodyTypeCode;
	}

	public void setLocalBodyTypeCode(Integer localBodyTypeCode) {
		this.localBodyTypeCode = localBodyTypeCode;
	}

	public String getLocalBodyTypeName() {
		return localBodyTypeName;
	}

	public void setLocalBodyTypeName(String localBodyTypeName) {
		this.localBodyTypeName = localBodyTypeName;
	}

	public String getLocalBodyNameEnglish() {
		return localBodyNameEnglish;
	}

	public void setLocalBodyNameEnglish(String localBodyNameEnglish) {
		this.localBodyNameEnglish = localBodyNameEnglish;
	}

	public String getLocalBodyNameLocal() {
		return localBodyNameLocal;
	}

	public void setLocalBodyNameLocal(String localBodyNameLocal) {
		this.localBodyNameLocal = localBodyNameLocal;
	}

	public Integer getChildCount() {
		return childCount;
	}

	public void setChildCount(Integer childCount) {
		this.childCount = childCount;
	}

	public Boolean getIsDisturbed() {
		return isDisturbed;
	}

	public void setIsDisturbed(Boolean isDisturbed) {
		this.isDisturbed = isDisturbed;
	}

	public Boolean getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Boolean isUsed) {
		this.isUsed = isUsed;
	}
}