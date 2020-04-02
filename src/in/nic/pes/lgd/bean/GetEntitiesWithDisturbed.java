package in.nic.pes.lgd.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Transient;

@Entity
@NamedNativeQuery(query = "select * from get_localbody_with_Disturb_Flag_fn(:entityType,:parent_entity_type,:parent_entity_code,:category)", name ="GetEntitiesWithDisturbed",resultClass=GetEntitiesWithDisturbed.class)
public class GetEntitiesWithDisturbed 
{
	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "local_body_code")
	private String localbodycode;
	@Column(name = "local_body_name_english")
	private String localbodynameenglish;
	@Column(name = "local_body_type_name")
	private String localbodytypename;
	
	@Transient
	private String actionInvalidateLB;
	@Transient
	private String actionChangeParent;
	@Transient
	private String actionChangeCoveredArea;
	@Transient
	private String actionChangeName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLocalbodycode() {
		return localbodycode;
	}
	public void setLocalbodycode(String localbodycode) {
		this.localbodycode = localbodycode;
	}
	public String getLocalbodynameenglish() {
		return localbodynameenglish;
	}
	public void setLocalbodynameenglish(String localbodynameenglish) {
		this.localbodynameenglish = localbodynameenglish;
	}
	public String getLocalbodytypename() {
		return localbodytypename;
	}
	public void setLocalbodytypename(String localbodytypename) {
		this.localbodytypename = localbodytypename;
	}
	
	public String getActionInvalidateLB() {
		return actionInvalidateLB;
	}
	public void setActionInvalidateLB(String actionInvalidateLB) {
		this.actionInvalidateLB = actionInvalidateLB;
	}
	public String getActionChangeParent() {
		return actionChangeParent;
	}
	public void setActionChangeParent(String actionChangeParent) {
		this.actionChangeParent = actionChangeParent;
	}
	public String getActionChangeCoveredArea() {
		return actionChangeCoveredArea;
	}
	public void setActionChangeCoveredArea(String actionChangeCoveredArea) {
		this.actionChangeCoveredArea = actionChangeCoveredArea;
	}
	public String getActionChangeName() {
		return actionChangeName;
	}
	public void setActionChangeName(String actionChangeName) {
		this.actionChangeName = actionChangeName;
	}
	
}

