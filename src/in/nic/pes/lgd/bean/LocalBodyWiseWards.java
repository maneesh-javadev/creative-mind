package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query = " select row_number() OVER () as row_num, local_body_code, local_body_name_english, local_body_name_local, lblc, ward_counts, parent_name, grandparent_name " +
						  " from get_ward_count_fn(:stateCode, :lbTypeCode, :search, :limit, :offset)", 
						  name = "getLocalBodyWiseWards", 
						  resultClass = LocalBodyWiseWards.class)
public class LocalBodyWiseWards {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 4675304883504927727L;
	
	@Id
	@Column(name = "local_body_code")
	private Integer localBodyCode;
	
	@Column(name = "row_num")
	private Integer rowNum;
	
	@Column(name = "local_body_name_english")
	private String localBodyNameEnglish;
	
	@Column(name = "local_body_name_local")
	private String localBodyNameLocal;
	
	@Column(name = "lblc")
	private Integer lblc;
	
	@Column(name = "ward_counts")
	private Integer wardCounts;
	
	@Column(name = "parent_name")
	private String parentName;
	
	@Column(name = "grandparent_name")
	private String grandparentName;

	public Integer getLocalBodyCode() {
		return localBodyCode;
	}

	public void setLocalBodyCode(Integer localBodyCode) {
		this.localBodyCode = localBodyCode;
	}

	public String getLocalBodyNameEnglish() {
		return localBodyNameEnglish;
	}

	public void setLocalBodyNameEnglish(String localBodyNameEnglish) {
		this.localBodyNameEnglish = localBodyNameEnglish;
	}

	public String getLocalBodyNameLocal() {
		return localBodyNameLocal;
	}

	public void setLocalBodyNameLocal(String localBodyNameLocal) {
		this.localBodyNameLocal = localBodyNameLocal;
	}

	public Integer getLblc() {
		return lblc;
	}

	public void setLblc(Integer lblc) {
		this.lblc = lblc;
	}

	public Integer getWardCounts() {
		return wardCounts;
	}

	public void setWardCounts(Integer wardCounts) {
		this.wardCounts = wardCounts;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getGrandparentName() {
		return grandparentName;
	}

	public void setGrandparentName(String grandparentName) {
		this.grandparentName = grandparentName;
	}
	
}
