package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
@NamedNativeQuery(query="select * from get_exist_dept_heirarchy(:olc,:slc)",name="GET_EXIST_HIERARCHY_DEPT",resultClass=ExistDeptHierarchy.class),
@NamedNativeQuery(query="select * from get_exist_dept_form(:olc)",name="GET_EXIST_HIERARCHY_FORM",resultClass=ExistDeptHierarchy.class),
@NamedNativeQuery(query="select distinct child_id as rno,chid_name,null as child_id,null as parent_id,null as parent_code from get_exist_dept_heirarchy(:olc,:slc)",name="GET_EXIST_ORG_LEVEL",resultClass=ExistDeptHierarchy.class),

})

public class ExistDeptHierarchy {
	
	
	@Id
	@Column(name = "rno")
	private Integer rno;
	
	@Column(name="child_id")
	private Integer childId;
	
	@Column(name="chid_name")
	private String childName;
	
	@Column(name="parent_id")
	private String parentId;
	
	@Column(name="parent_code")
	private Integer parentCode;

	public Integer getChildId() {
		return childId;
	}

	public Integer getParentCode() {
		return parentCode;
	}

	public void setParentCode(Integer parentCode) {
		this.parentCode = parentCode;
	}

	public void setChildId(Integer childId) {
		this.childId = childId;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	public Integer getRno() {
		return rno;
	}

	public void setRno(Integer rno) {
		this.rno = rno;
	}
	
}
