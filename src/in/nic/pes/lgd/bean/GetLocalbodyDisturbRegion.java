package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query = "select * from get_localbody_Disturb_Region_fn(:localbodycode)", name ="GetLocalbodyDisturbedRegion",resultClass=GetLocalbodyDisturbRegion.class)
public class GetLocalbodyDisturbRegion {
	
	@Id
	@Column(name = "get_localbody_Disturb_Region_fn")
	private String getLocalbodyDisturbRegionfn;
	
	public String getGetLocalbodyDisturbRegionfn() {
		return getLocalbodyDisturbRegionfn;
	}

	public void setGetLocalbodyDisturbRegionfn(String getLocalbodyDisturbRegionfn) {
		this.getLocalbodyDisturbRegionfn = getLocalbodyDisturbRegionfn;
	}

}
