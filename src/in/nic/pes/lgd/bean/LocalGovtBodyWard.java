package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
	
	@NamedNativeQuery(query="SELECT * FROM get_local_body_ward_list(:lblc)",name="getLocalGovtBodyWardListView",resultClass=LocalGovtBodyWard.class),
	@NamedNativeQuery(query="SELECT * FROM get_local_body_ward_listtemp(:lblc)",name="getLocalGovtBodyWardListViewtemp",resultClass=LocalGovtBodyWard.class),

})
public class LocalGovtBodyWard {
	private String wardNameInEnglish; 
	private String wardNameInLocal;
	private Integer wardCode;
	private String wardNumber;
	private Integer wardVersion;
	private Boolean ischeck  ;
	
	@Column(name="ward_name_english")
	public String getWardNameInEnglish() {
		return wardNameInEnglish;
	}
	public void setWardNameInEnglish(String wardNameInEnglish) {
		this.wardNameInEnglish = wardNameInEnglish;
	}
	@Column(name="ward_name_local")
	public String getWardNameInLocal() {
		return wardNameInLocal;
	}
	public void setWardNameInLocal(String wardNameInLocal) {
		this.wardNameInLocal = wardNameInLocal;
	}
	@Id
	@Column(name="ward_code")
	public Integer getWardCode() {
		return wardCode;
	}
	public void setWardCode(Integer wardCode) {
		this.wardCode = wardCode;
	}
	@Column(name="ward_number")
	public String getWardNumber() {
		return wardNumber;
	}
	public void setWardNumber(String wardNumber) {
		this.wardNumber = wardNumber;
	}
	
	@Column(name="ward_version")
	public Integer getWardVersion()
	{
		return wardVersion;
	}
	public void setWardVersion(Integer wardVersion)
	{
		this.wardVersion = wardVersion;
	}
	public Boolean getIscheck()
	{
		return ischeck;
	}
	public void setIscheck(Boolean ischeck)
	{
		this.ischeck = ischeck;
	}
	
	
	
}
