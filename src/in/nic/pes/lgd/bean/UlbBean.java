package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;


@Entity
@NamedNativeQuery(query=" SELECT case when lb.local_body_code  in (select * from get_draft_used_lb_lr_temp(lb.local_body_code,'G')) then  cast ('F' as character) else cast('A' as character) end as operation_state," +
						" * FROM get_ulb_list_by_district_code_fn(:districtCode,:localbodytypecode) lb",name="getUlbListByDistrictCodeFn",resultClass=UlbBean.class)
public class UlbBean {
	
	private Integer localBodyCode;
	private String localBodyNameEnglish;
	private String localBodyNameLocal;
 	private Character operation_state;
	@Id
	@Column(name="local_body_code")
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
	
	@Column(name="local_body_name_local")
	public String getLocalBodyNameLocal() {
		return localBodyNameLocal;
	}
	public void setLocalBodyNameLocal(String localBodyNameLocal) {
		this.localBodyNameLocal = localBodyNameLocal;
	}
	
	@Column(name="operation_state")
	public Character getOperation_state() {
		return operation_state;
	}
	public void setOperation_state(Character operation_state) {
		this.operation_state = operation_state;
	}


	

}
