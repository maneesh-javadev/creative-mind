package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query = "select * from change_district_fn(:districtCode,:districtNameEnglishch,:userId,:orderNo,:Orderdate,:effectiveDate,:orderpath,:districtNameEnglish,:gazpubDate,:districtNameLocal,:aliasEnglish,:aliasLocal,:slc)", name ="DistrictChangeQuery",resultClass=ChangeDistrict.class)
public class ChangeDistrict {
	@Id
	private String change_district_fn;

	@Column(name = "change_district_fn", nullable = false)
	public String getChange_district_fn() {
		return change_district_fn;
	}

	public void setChange_district_fn(String change_district_fn) {
		this.change_district_fn = change_district_fn;
	}
	
	
	
	

}


