package in.nic.pes.lgd.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query="SELECT local_body_code,local_body_name_english,local_body_type_name,parent_name from rpt_statewise_unmapped_localbodies(:stateCode,:lbTypeCode)",name="getViewUnMappedLBdetails",resultClass=ViewUnMappedLocalBodies.class)


public class ViewUnMappedLocalBodies {
	
	@Id
	@Column(name = "local_body_code" ,nullable = false)
	private Integer localBodyCode;
	
	@Column(name = "local_body_name_english")
	private String localBodyNameEnglish;
	
	@Column(name = "local_body_type_name")
	private String localBodyTypeName;
	
	@Column(name = "parent_name")
	private String parentname;

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

	public String getLocalBodyTypeName() {
		return localBodyTypeName;
	}

	public void setLocalBodyTypeName(String localBodyTypeName) {
		this.localBodyTypeName = localBodyTypeName;
	}

	public String getparentname() {
		return parentname;
	}

	public void setParentname(String parentname) {
		this.parentname = parentname;
	}


}
