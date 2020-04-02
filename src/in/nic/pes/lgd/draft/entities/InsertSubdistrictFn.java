package in.nic.pes.lgd.draft.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
@NamedNativeQuery(query = "Select * from create_subdistrict_fn(:districtCode,"
															+ ":userId,"
															+ ":subdistrictNameEnglish,"
															+ ":subdistrictNameLocal,"
															+ ":aliasEnglish,"
															+ ":aliasLocal,"
															+ ":census2011Code,"
															+ ":sscode,"
															+ ":coordinates,"
															+ ":headquarterNameEnglish,"
															+ ":headquarterNameLocal,"
															+ ":orderNo,"
															+ ":orderDate,"
															+ ":effectiveDate,"
															+ ":gazPubDate,"
															+ ":listofSubdistrictFull,"
															+ ":listofSubdistrictPart,"
															+ ":listofVillageFull,"
															+ ":mapLandregionCode)"
														    , name = "Insert_Subdistrict_Details", resultClass = InsertSubdistrictFn.class)})





public class InsertSubdistrictFn {
	
	@Id
	@Column(name = "create_subdistrict_fn", nullable = false)
	private String createSubdistrictFn;

	public String getCreateSubdistrictFn() {
		return createSubdistrictFn;
	}

	public void setCreateSubdistrictFn(String createSubdistrictFn) {
		this.createSubdistrictFn = createSubdistrictFn;
	}
	
	

}
