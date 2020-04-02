package in.nic.pes.lgd.draft.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;


@Entity
@NamedNativeQueries({
@NamedNativeQuery(query = "Select * from change_subdistrict_fn(:subdistrictCode,"
															+ ":userId,"
															+ ":changeSubdistrictNameEnglish,"
															+ ":orderNo,"
															+ ":orderDate,"
															+ ":effectiveDate,"
															+ ":govOrder,"
															+ ":subdistrictNameEnglish,"
															+ ":gazPubDate,"
															+ ":subdistrictNameLocal,"
															+ ":aliasEnglish,"
															+ ":aliasLocal,"
															+ ":stateCode)"
														    , name = "Change_Subdistrict_Details", resultClass = ChangeSubdistrictFn.class)})




public class ChangeSubdistrictFn {
	
	@Id
	@Column(name = "change_subdistrict_fn", nullable = false)
	private String changeSubdistrictFn;

	public String getChangeSubdistrictFn() {
		return changeSubdistrictFn;
	}

	public void setChangeSubdistrictFn(String changeSubdistrictFn) {
		this.changeSubdistrictFn = changeSubdistrictFn;
	}

	
	
	

}
