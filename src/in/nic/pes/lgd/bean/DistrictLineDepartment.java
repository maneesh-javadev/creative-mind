package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
@NamedNativeQuery(query=" SELECT * FROM get_landregionwise_department_list_fn(:districtCode,:level)",name="getLineDepartmentDetails",resultClass=DistrictLineDepartment.class),
@NamedNativeQuery(query=" SELECT * FROM get_landregionwise_department_list_fn(:subDistrictCode,:level)",name="getLineDepartmentDetail",resultClass=DistrictLineDepartment.class),
@NamedNativeQuery(query=" SELECT * FROM get_landregionwise_department_list_fn(:villageCode,:level)",name="getLineDepartmentDetailsByVillage",resultClass=DistrictLineDepartment.class),
@NamedNativeQuery(query=" SELECT * FROM get_landregionwise_department_list_fn(:blockCode,:level)",name="getBlockLineDepartmentDetail",resultClass=DistrictLineDepartment.class),
@NamedNativeQuery(query=" SELECT * FROM get_landregionwise_organization_list_fn(:entityCode,:level,:olc)",name="getLandRegionLineDepartmentDetails",resultClass=DistrictLineDepartment.class),
@NamedNativeQuery(query=" SELECT * FROM get_landregionwise_organization_details_fn(:orgUnitCode)",name="getLandRegionLineDepartmentDetailsView",resultClass=DistrictLineDepartment.class),
@NamedNativeQuery(query=" SELECT * FROM get_landregionwise_department_list_new_fn(:entityCode,:level)",name="getNewLineDepartmentDetails",resultClass=DistrictLineDepartment.class),
@NamedNativeQuery(query=" SELECT * FROM get_landregionwise_department_list_new_fn(:entityCode,:level) where org_located_level_code=:orgLocatedLevelCode",name="getNewLineDepartmentDetail",resultClass=DistrictLineDepartment.class),

})
public class DistrictLineDepartment {
	/*private Integer stateCode;*/
	private String orgName;
	private String orgNameInLocal;
	private String shortDeptName;
	private int orgCode;

	@Column(name="org_level_specific_name")
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	@Column(name="org_level_specific_short_name")
	public String getShortDeptName() {
		return shortDeptName;
	}
	public void setShortDeptName(String shortDeptName) {
		this.shortDeptName = shortDeptName;
	}
	@Id
	@Column(name="org_located_level_code")
	public int getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(int orgCode) {
		this.orgCode = orgCode;
	}
	@Column(name="org_level_specific_name_local")
	public String getOrgNameInLocal() {
		return orgNameInLocal;
	}
	public void setOrgNameInLocal(String orgNameInLocal) {
		this.orgNameInLocal = orgNameInLocal;
	}	
}

