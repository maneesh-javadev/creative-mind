package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
@NamedNativeQuery(query="SELECT  lb.local_body_code,lb.local_body_name_english,case when lb.local_body_code  in (select * from get_draft_used_lb_lr_temp"
					   + "(lb.local_body_code,'G')) then  cast ('F' as character) else (case when lb.local_body_code  in(:lbFull) then cast ('S' as character) else "
					   + "cast('A' as character) end ) end as operation_state,case when lb.local_body_code  in(:lbPart) then cast('T' as character) else cast('F' as character) end as isPart from "
					   + "get_lb_child_list_by_category_for_constituency_fn(:lbType,:lbTypeCode,:stateCode,:parentlbCode,:districtCode) lb "
					   + " order by lb.local_body_name_english ",name="getParentLevelLocalbody",resultClass=ConstituencyLocalbody.class),





})

public class ConstituencyLocalbody {
	
	@Id
	@Column(name="local_body_code")
	private Integer localBodyCode; 
	
	
	@Column(name="local_body_name_english")
	private String localBodyNameEnglish;
	
	@Column(name="operation_state")
	private Character operation_state; 
	
	@Column(name="isPart")
	private Character isPart;

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

	public Character getOperation_state() {
		return operation_state;
	}

	public void setOperation_state(Character operation_state) {
		this.operation_state = operation_state;
	}

	public Character getIsPart() {
		return isPart;
	}

	public void setIsPart(Character isPart) {
		this.isPart = isPart;
	}
	
	
	public ConstituencyLocalbody(){
		super();
	}
	
	

}
