package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

/*
 * Query changed by kirandeep on 21/05/2015 for ulb list not coming in block form
 */
@Entity
@NamedNativeQuery(query=" Select case when u.lblc  in (select * from get_draft_used_lb_lr_temp(u.lblc,'G')) then  cast ('F' as character) else cast('A' as character) end as operation_state,* FROM get_ulb_list_for_block(:districtCode) u",name="UnmapUlbListByDistrictCode",resultClass=UlbunmapBlockBean.class)
public class UlbunmapBlockBean {
	
	
	
	
	private Integer localBodyCode;
	private String localBodyNameEnglish;
	//private String localBodyNameLocal;
	
	
	/**
	 * Added by Ripunj on 07-01-2015
	 * For LocalBody Draft Mode
	 */
	private Character operation_state;
	
	@Id
	@Column(name="lblc")
	public Integer getLocalBodyCode() {
		return localBodyCode;
	}
	public void setLocalBodyCode(Integer localBodyCode) {
		this.localBodyCode = localBodyCode;
	}
	
	@Column(name="local_body_name_english")
	public String getLocalBodyNameEnglish() {
		return localBodyNameEnglish;
	}
	public void setLocalBodyNameEnglish(String localBodyNameEnglish) {
		this.localBodyNameEnglish = localBodyNameEnglish;
	}
	@Column(name="operation_state")
	public Character getOperation_state() {
		return operation_state;
	}
	public void setOperation_state(Character operation_state) {
		this.operation_state = operation_state;
	}
	
	/*@Column(name="local_body_name_local")
	public String getLocalBodyNameLocal() {
		return localBodyNameLocal;
	}
	public void setLocalBodyNameLocal(String localBodyNameLocal) {
		this.localBodyNameLocal = localBodyNameLocal;
	}
*/

	
	
	

}


