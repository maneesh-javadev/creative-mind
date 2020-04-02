package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Id;

public class DistrictDetails { // NO_UCD (unused code)
    
	@Id
    @Column(name="district_code")
	private Integer dlc;
	@Column(name="district_name_english")
	private String districtNameEnglish;
    @Column(name="state_code")
	private Integer slc;
	@Column(name="state_name_english")
	private String stateNameEnglish;
	@Column(name="census_2001_code", length=2)
	private String census2001Code;
	
	@Column(name="census_2011_code")
	private String census2011Code;
	
	public Integer getDlc() {
		return dlc;
	}
	public void setDlc(Integer dlc) {
		this.dlc = dlc;
	}
	public String getDistrictNameEnglish() {
		return districtNameEnglish;
	}
	public void setDistrictNameEnglish(String districtNameEnglish) {
		this.districtNameEnglish = districtNameEnglish;
	}
	public Integer getSlc() {
		return slc;
	}
	public void setSlc(Integer slc) {
		this.slc = slc;
	}
	public String getStateNameEnglish() {
		return stateNameEnglish;
	}
	public void setStateNameEnglish(String stateNameEnglish) {
		this.stateNameEnglish = stateNameEnglish;
	}
	public String getCensus2001Code() {
		if(census2001Code!=null)
		return census2001Code;
		else
			return "";
	}
	public void setCensus2001Code(String census2001Code) {
		this.census2001Code = census2001Code;
	}
	public String getCensus2011Code() {
		return census2011Code;
	}
	public void setCensus2011Code(String census2011Code) {
		this.census2011Code = census2011Code;
	}
		

}	