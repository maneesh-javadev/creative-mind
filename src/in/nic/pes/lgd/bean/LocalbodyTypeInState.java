package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;


@Entity
@NamedNativeQuery(query=" SELECT * FROM get_local_body_type_details_by_state_fn(:stateCode,:category)",name="getLocalBodyTypeDetailsByStateFn",resultClass=LocalbodyTypeInState.class)
public class LocalbodyTypeInState {
	
	  private Integer localbodyCode;
	  private String localbodyName;
	  private String localbodyTypeCode;
	  
	@Id
	@Column(name="tier_setup_code") 
	public Integer getLocalbodyCode() {
		return localbodyCode;
	}
	public void setLocalbodyCode(Integer localbodyCode) {
		this.localbodyCode = localbodyCode;
	}
	
	@Column(name="local_body_type_name")
	public String getLocalbodyName() {
		return localbodyName;
	}
	public void setLocalbodyName(String localbodyName) {
		this.localbodyName = localbodyName;
	}
	/**
	 * @return the localbodyTypeCode
	 */
	@Column(name="local_body_type_code")
	public String getLocalbodyTypeCode() {
		return localbodyTypeCode;
	}
	/**
	 * @param localbodyTypeCode the localbodyTypeCode to set
	 */
	public void setLocalbodyTypeCode(String localbodyTypeCode) {
		this.localbodyTypeCode = localbodyTypeCode;
	}
	


	

}
