package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
//@NamedNativeQuery(query="SELECT * FROM get_landregionwise_lb_list_fn(:local_body_type,:district_id,:local_body_type_code)",name="landregionwise",resultClass=GetLandRegionWise.class)
@NamedNativeQuery(query="SELECT case when :district_id in (select * from get_draft_used_lb_lr_temp(:district_id,'D')) " +
		"then Cast ('F' as character) else cast('A' as character) end as operation_state,* " +
		"FROM get_landregionwise_lb_list_fn(:local_body_type,:district_id,:local_body_type_code) ",
		name="landregionwise",resultClass=GetLandRegionWise.class)

public class GetLandRegionWise
{
	private int localBodyTypeCode;
	private String localBodyTypeName;
	private int localBodyCode;
	private String localBodyNameEnglish;
	private String localBodyNameLocal;
	 private Character operation_state;
	
	@Column(name = "local_body_type_code")
	public int getLocalBodyTypeCode() {
		return localBodyTypeCode;
	}
	public void setLocalBodyTypeCode(int localBodyTypeCode) {
		this.localBodyTypeCode = localBodyTypeCode;
	}
	
	@Column(name = "local_body_type_name")
	public String getLocalBodyTypeName() {
		return localBodyTypeName;
	}
	public void setLocalBodyTypeName(String localBodyTypeName) {
		this.localBodyTypeName = localBodyTypeName;
	}
	
	@Id
	@Column(name = "local_body_code")
	public int getLocalBodyCode() {
		return localBodyCode;
	}
	public void setLocalBodyCode(int localBodyCode) {
		this.localBodyCode = localBodyCode;
	}
	
	@Column(name = "local_body_name_english")
	public String getLocalBodyNameEnglish() {
		return localBodyNameEnglish;
	}
	public void setLocalBodyNameEnglish(String localBodyNameEnglish) {
		this.localBodyNameEnglish = localBodyNameEnglish;
	}
	
	@Column(name = "local_body_name_local")
	public String getLocalBodyNameLocal() {
		return localBodyNameLocal;
	}
	public void setLocalBodyNameLocal(String localBodyNameLocal) {
		this.localBodyNameLocal = localBodyNameLocal;
	}
	
	@Column(name = "operation_state")
	public Character getOperation_state() {
		return operation_state;
	}
	public void setOperation_state(Character operation_state) {
		this.operation_state = operation_state;
	}
	
}
