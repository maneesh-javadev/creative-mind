package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;


@Entity
@NamedNativeQuery(query = "select * from set_constituency_fn(:constituencyType,:constituencyCode,:constituencyNameEnglish,:userId,:constituencyNameLocal,:eciCode)", name ="ParliamentConstituencyQuery",resultClass=ChangeParliamentConstituency.class)
public class ChangeParliamentConstituency {
	@Id
	
	private boolean set_constituency_fn;
	
	@Column(name = "set_constituency_fn", nullable = false)
	public boolean isSet_constituency_fn() {
		return set_constituency_fn;
	}
	public void setSet_constituency_fn(boolean set_constituency_fn) {
		this.set_constituency_fn = set_constituency_fn;
	}
	
	
	
	
	
	
	
}
