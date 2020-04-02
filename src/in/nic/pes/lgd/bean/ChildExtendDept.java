package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query="select * from get_lb_extend_dept_hierarchy_new(:slc,:parentAdminLevelCode) where adminunitcode not in(:existAdminCodeList)",name="FETCH_CHILD_OF_PARENT_ADMIN_UNIT_LEVELS",resultClass=ChildExtendDept.class)
public class ChildExtendDept {
	
	@Id
	@Column(name="adminunitcode")
	private Integer adminUnitCode;
	
	@Column(name="adminLevelNameEng")
	private String adminLevelNameEng;
	
	@Column(name="type")
	private String entityType;
	
	@Column(name="rank")
	private Integer rank;

	public Integer getAdminUnitCode() {
		return adminUnitCode;
	}

	public void setAdminUnitCode(Integer adminUnitCode) {
		this.adminUnitCode = adminUnitCode;
	}

	public String getAdminLevelNameEng() {
		return adminLevelNameEng;
	}

	public void setAdminLevelNameEng(String adminLevelNameEng) {
		this.adminLevelNameEng = adminLevelNameEng;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}
	
	
	
	
	

}
