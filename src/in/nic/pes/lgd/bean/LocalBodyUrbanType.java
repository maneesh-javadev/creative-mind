package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query=" SELECT * FROM getlblist(:localBodyTypeCode, :localBodyType, :stateCode) where local_body_code=:localBodyCode",name="getLocalBodyUrbanName",resultClass=LocalBodyUrbanType.class)
public class LocalBodyUrbanType {
	
	
	private int localBodyTypeCode;
	private int localBodyCode;
	private String localBodyNameEnglish; 
	private String localBodyNameLocal; 
	private String aliasNameEnglish;
	private String alisNameLocal;
	private int stateCode;
	private String localBodyTypeName; 
	
	
	@Id
	@Column(name="local_body_type_code")
	public int getLocalBodyTypeCode() {
		return localBodyTypeCode;
	}
	public void setLocalBodyTypeCode(int localBodyTypeCode) {
		this.localBodyTypeCode = localBodyTypeCode;
	}
	
	@Column(name="local_body_name_english")
	public String getLocalBodyNameEnglish() {
		return localBodyNameEnglish;
	}
	public void setLocalBodyNameEnglish(String localBodyNameEnglish) {
		this.localBodyNameEnglish = localBodyNameEnglish;
	}
	
	@Column(name="local_body_name_local")
	public String getLocalBodyNameLocal() {
		return localBodyNameLocal;
	}
	public void setLocalBodyNameLocal(String localBodyNameLocal) {
		this.localBodyNameLocal = localBodyNameLocal;
	}
	
	
	@Column(name="alias_english")
	public String getAliasNameEnglish() {
		return aliasNameEnglish;
	}
	public void setAliasNameEnglish(String aliasNameEnglish) {
		this.aliasNameEnglish = aliasNameEnglish;
	}
	
	@Column(name="alias_local")
	public String getAlisNameLocal() {
		return alisNameLocal;
	}
	public void setAlisNameLocal(String alisNameLocal) {
		this.alisNameLocal = alisNameLocal;
	}
	
	@Column(name="state_code")
	public int getStateCode() {
		return stateCode;
	}
	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}
	
	@Column(name="local_body_type_name")
	public String getLocalBodyTypeName() {
		return localBodyTypeName;
	}
	public void setLocalBodyTypeName(String localBodyTypeName) {
		this.localBodyTypeName = localBodyTypeName;
	}
	@Column(name="local_body_code")
	public int getLocalBodyCode() {
		return localBodyCode;
	}
	public void setLocalBodyCode(int localBodyCode) {
		this.localBodyCode = localBodyCode;
	}
	
	
	
	

}

