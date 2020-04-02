package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;


@Entity
@NamedNativeQuery(query = "select * from change_subdistrict_fn(:subdistrictCode,:userId,:subdistrictNameEnglishch,:orderNo,:orderDate,:effectiveDate,:govOrder,:subdistrictNameEnglish,:govPubDate,:subdistrictNameLocal,:aliasEnglish,:aliasLocal,:slc)", name ="SubDistrictQuery",resultClass=ChangeSubDistrict.class)
public class ChangeSubDistrict {
	@Id
	@Column(name = "change_subdistrict_fn", nullable = false)
	private String change_subdistrict_fn;

	public String getChange_subdistrict_fn() {
		return change_subdistrict_fn;
	}

	public void setChange_subdistrict_fn(String change_subdistrict_fn) {
		this.change_subdistrict_fn = change_subdistrict_fn;
	}

	
	
	
}

/*1st - source_subdistrict_code (Mandatory)
2nd - subdistrict_name_english (Mandatory)
3rd - user_id (Mandatory)
4rd - subdistrict_name_local (Optional)
5th - alias_english (Optional)
6th - alias_local (Optional)';
*/