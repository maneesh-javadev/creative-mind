package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;


@Entity
@NamedNativeQueries({
@NamedNativeQuery(query="select lw.ward_code,lw.ward_name_english,lw.lblc,l.local_body_name_english from localbody_ward lw left join localbody l on l.lblc=lw.lblc and l.isactive "
					  + "and lw.isactive where lw.lblc in( select lblc from localbody where local_body_code  in( :lblcCodeList) and isactive) and"
					  + " ward_code not in(:wardCodeList)",name="getWardListbylblc",resultClass=ConstituencyWard.class),




})
public class ConstituencyWard {
	
	@Id
	@Column(name="ward_code")
	private Integer wardCode;
	
	@Column(name="lblc")
	private Integer lblc;
	
	
	@Column(name="ward_name_english")
	private String wardNameEnglish;
	
	@Column(name="local_body_name_english")
	private String localBodyNameEnglish;

	public Integer getLblc() {
		return lblc;
	}

	public void setLblc(Integer lblc) {
		this.lblc = lblc;
	}

	public Integer getWardCode() {
		return wardCode;
	}

	public void setWardCode(Integer wardCode) {
		this.wardCode = wardCode;
	}

	public String getWardNameEnglish() {
		return wardNameEnglish;
	}

	public void setWardNameEnglish(String wardNameEnglish) {
		this.wardNameEnglish = wardNameEnglish;
	}

	public String getLocalBodyNameEnglish() {
		return localBodyNameEnglish;
	}

	public void setLocalBodyNameEnglish(String localBodyNameEnglish) {
		this.localBodyNameEnglish = localBodyNameEnglish;
	}
	
	
	

}
