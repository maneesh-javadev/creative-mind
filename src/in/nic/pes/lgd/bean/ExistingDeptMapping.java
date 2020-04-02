package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({ 
@NamedNativeQuery(query="Select * from get_existing_mapping(:orgUnitCode,:orgLocatedCode,:slc,:mappingType)",name="ExistingDepartmentMapping",resultClass=ExistingDeptMapping.class),
})
//@NamedNativeQuery(query="Select * from remove_existingMapping_fn(:mappingFromId,:mappingId)",name="deleteExistingDepartmentMapping",resultClass=ExistingDeptMapping.class),
public class ExistingDeptMapping {
	
	@Id
	@Column (name="mapping_from_id")
	private Integer mappingFromId;
	
	@Column (name="mapping_id")
	private Integer mappingId;
	
	@Column (name="line_dept")
	private String lineDept;
	
	@Column (name="dept_level")
	private String deptLevel;
	
	@Column (name="child_hierarchy")
	private String childHierarchy;
	
	@Column (name="to_orgunit_code")
	private Integer toOrgUnitCode;

	public Integer getMappingFromId() {
		return mappingFromId;
	}

	public void setMappingFromId(Integer mappingFromId) {
		this.mappingFromId = mappingFromId;
	}

	public Integer getMappingId() {
		return mappingId;
	}

	public void setMappingId(Integer mappingId) {
		this.mappingId = mappingId;
	}

	public String getLineDept() {
		return lineDept;
	}

	public void setLineDept(String lineDept) {
		this.lineDept = lineDept;
	}

	public String getDeptLevel() {
		return deptLevel;
	}

	public void setDeptLevel(String deptLevel) {
		this.deptLevel = deptLevel;
	}

	public String getChildHierarchy() {
		return childHierarchy;
	}

	public void setChildHierarchy(String childHierarchy) {
		this.childHierarchy = childHierarchy;
	}

	public Integer getToOrgUnitCode() {
		return toOrgUnitCode;
	}

	public void setToOrgUnitCode(Integer toOrgUnitCode) {
		this.toOrgUnitCode = toOrgUnitCode;
	}
	

	
}
