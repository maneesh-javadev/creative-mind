package in.nic.pes.lgd.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "get_organization_at_levels")
public class LgdOrganizationAtLevels implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column(name="org_located_level_code")
	private Integer key;
	
	@Column(name="org_located_level_name")
	private String value;
	
	@Column(name="level_wise_office_name")
	private String levelWiseOfficeName;

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	/*public static long getSerialversionuid() {
		return serialVersionUID;
	}*/

	public String getLevelWiseOfficeName() {
		return levelWiseOfficeName;
	}

	public void setLevelWiseOfficeName(String levelWiseOfficeName) {
		this.levelWiseOfficeName = levelWiseOfficeName;
	}
	

}
