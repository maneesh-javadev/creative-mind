package in.nic.pes.lgd.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({ 
	@NamedNativeQuery(query = "select local_body_code ,local_body_name_english ,village_code ,village_name_english from get_gp_list_of_blockvillage(:blockCode,:villageCode)", name = "getLocalBodyDetail", resultClass = LocalBodyDto.class),
@NamedNativeQuery(query = "select local_body_code ,local_body_name_english ,village_code ,village_name_english from get_mapped_gp_list_of_blockvillage(:blockCode,:villageCode)", name = "getLbCoveredSelectedValue", resultClass = LocalBodyDto.class)
})

public class LocalBodyDto implements Serializable {

	private static final long serialVersionUID = 1L;

	
	
	@Id
	@Column(name = "local_body_code")
	private Integer lblc;
	
	@Column(name = "local_body_name_english")
	private String localBodyNameEnglish;

	@Column(name = "village_code")
	private Integer villageCode;
	
	@Column(name = "village_name_english")
	private String villageNameEnglish;

	public Integer getLblc() {
		return lblc;
	}

	public void setLblc(Integer lblc) {
		this.lblc = lblc;
	}

	public String getLocalBodyNameEnglish() {
		return localBodyNameEnglish;
	}

	public void setLocalBodyNameEnglish(String localBodyNameEnglish) {
		this.localBodyNameEnglish = localBodyNameEnglish;
	}

	public Integer getVillageCode() {
		return villageCode;
	}

	public void setVillageCode(Integer villageCode) {
		this.villageCode = villageCode;
	}

	public String getVillageNameEnglish() {
		return villageNameEnglish;
	}

	public void setVillageNameEnglish(String villageNameEnglish) {
		this.villageNameEnglish = villageNameEnglish;
	}
	

	
	
}
