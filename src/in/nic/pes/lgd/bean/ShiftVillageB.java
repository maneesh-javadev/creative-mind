package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query = "select * from shift_village_fn(:blockSourceCode,:blockTargetCode,:villageCode,:userId,:effectiveDate,:level)", name ="VillageBlockQuery",resultClass=ShiftVillageB.class)

public class ShiftVillageB {
	
	@Id
	private boolean shift_village_fn;
	
	@Column(name = "shift_village_fn", nullable = false)
	public boolean isShift_village_fn() {
		return shift_village_fn;
	}

	public void setShift_village_fn(boolean shift_village_fn) {
		this.shift_village_fn = shift_village_fn;
	}

	

}
