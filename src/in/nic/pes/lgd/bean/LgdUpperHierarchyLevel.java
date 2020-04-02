package in.nic.pes.lgd.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "get_upper_hierarchy_level")
@NamedNativeQueries(
		{
			@NamedNativeQuery(
					name="getUpperHierarchyLevel",
					query="select * from get_upper_hierarchy_level(:org_located_level_code) order by level_code",
					resultClass=LgdUpperHierarchyLevel.class
				
			)
		}
)
public class LgdUpperHierarchyLevel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="level_code")
	private Integer levelCode;
	
	@Column(name="level_name")
	private String levelName;

	public Integer getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(Integer levelCode) {
		this.levelCode = levelCode;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	
	@Transient
	List<OrganizationUnit> list;

	public List<OrganizationUnit> getList() {
		return list;
	}

	public void setList(List<OrganizationUnit> list) {
		this.list = list;
	}
	
	

}
