package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;



@Entity
@NamedNativeQuery(query="select * from push_covered_area_to_pes (:landregionlistfor)",name="pushcoveredareatopes",resultClass=Pushcoveragetopes.class)

public class Pushcoveragetopes {
	
	@Id 
	private boolean push_covered_area_to_pes;
	@Column(name = "push_covered_area_to_pes", nullable = false)

	public boolean isPush_covered_area_to_pes() {
		return push_covered_area_to_pes;
	}

	public void setPush_covered_area_to_pes(boolean push_covered_area_to_pes) {
		this.push_covered_area_to_pes = push_covered_area_to_pes;
	}

	
	

	
	
	
	
	

	
}
