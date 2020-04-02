package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
/**
 * govtOrderConfig param added by pooja on 27-08-2015
 */
@Entity
@NamedNativeQuery(query = "select * from change_village_fn(:villageCode,:userId,:villageNameEnglish,:effectiveDate,:order_no,:order_date,:flag_code,:operationState,:isExistGovtOrder,:orderCode,:villageNameLocal,:aliasEnglish,:aliasLocal,:gaz_pubdate,:govtOrderConfig)", name ="VillageChangeQuery",resultClass=ChangeVillage.class)
                               
public class ChangeVillage {
	@Id
	private String change_village_fn;
	@Column(name = "change_village_fn", nullable = false)
	public String getChange_village_fn() {
		return change_village_fn;
	}

	public void setChange_village_fn(String change_village_fn) {
		this.change_village_fn = change_village_fn;
	}
	
	
}
