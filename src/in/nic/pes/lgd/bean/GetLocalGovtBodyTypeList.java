package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
@NamedNativeQuery(query = "SELECT * FROM  get_local_gov_setup_fn(:stateCode)", name = "getlocalgovtbodytypelist", resultClass = GetLocalGovtBodyTypeList.class)
})

public class GetLocalGovtBodyTypeList {
	private Integer localBodyTypeCode;
	private String typeNameEng;
	private char level;
	
	@Column(name = "level")
	public char getLevel() {
		return level;
	}
	public void setLevel(char level) {
		this.level = level;
	}
	@Id
	@Column(name = "local_body_type_code")
	public Integer getLocalBodyTypeCode() {
		return localBodyTypeCode;
	}
	public void setLocalBodyTypeCode(Integer localBodyTypeCode) {
		this.localBodyTypeCode = localBodyTypeCode;
	}
	
	@Column(name = "local_body_type_name")
	public String getTypeNameEng() {
		return typeNameEng;
	}
	public void setTypeNameEng(String typeNameEng) {
		this.typeNameEng = typeNameEng;
	}
	
	
	
	
	

	
	
}
