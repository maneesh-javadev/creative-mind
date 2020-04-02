package in.nic.pes.lgd.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

/**
 * Invalidate Village
 */

@SuppressWarnings("serial")
@Entity
@NamedNativeQuery(query="SELECT * FROM invalidate_village_fn(:villageCode,:userId,:orderNumber,:orderDate,:effectiveDate,:govtOrder,:gzbDate,:destinationVillageList,:distinationSubdistrict,:orderCode,:isExistGovtOrder,:operationState)",name="invalidate_village_fn",resultClass=InvalidateVillage.class)

public class InvalidateVillage implements Serializable
{
	
	@Id
	private String invalidate_village_fn;

	@Column(name = "invalidate_village_fn", nullable = false)
	public String getInvalidate_village_fn() {
		return invalidate_village_fn;
	}

	public void setInvalidate_village_fn(String invalidate_village_fn) {
		this.invalidate_village_fn = invalidate_village_fn;
	}
	

}
