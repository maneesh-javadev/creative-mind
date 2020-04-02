package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query = "select h.habitation_code,h.habitation_version,lb.lbclr_code,true as isactive "
		+ "from habitation h inner join lb_covered_landregion lb on lb.lrlc=h.parent_code  where h.parent_type ='V'  "
		+ "and h.habitation_code in(:habitationList) and h.isactive and lb.lb_covered_region_code=(select lb_covered_region_code from "
		+ "localbody where local_body_code =:localBodyCode and isactive) and lb.isactive", name = "Fetch_LOCALBODY_HABIATION_Details", resultClass = FetchLOCALBODYHABIATION.class)
public class FetchLOCALBODYHABIATION {

	@Id
	@Column(name="habitation_code",nullable = false)
	private Integer habitationCode;
	
	@Column(name="habitation_version")
	private Integer habitationVersion;
	
	@Column(name="lbclr_code")
	private Integer lbclrCode;
	
	@Column(name="isactive")
	private boolean isactive;

	public Integer getHabitationCode() {
		return habitationCode;
	}

	public void setHabitationCode(Integer habitationCode) {
		this.habitationCode = habitationCode;
	}

	public Integer getHabitationVersion() {
		return habitationVersion;
	}

	public void setHabitationVersion(Integer habitationVersion) {
		this.habitationVersion = habitationVersion;
	}

	public Integer getLbclrCode() {
		return lbclrCode;
	}

	public void setLbclrCode(Integer lbclrCode) {
		this.lbclrCode = lbclrCode;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	
	
	

}
