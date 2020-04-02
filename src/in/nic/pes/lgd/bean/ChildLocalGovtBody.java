package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query=" SELECT * FROM get_parentwise_lb_list(:localBodyTypeCode)",name="getChildLocalBodyListForSelectedBody",resultClass=ChildLocalGovtBody.class)

public class ChildLocalGovtBody {

	private int localBodyCode;
	private String localBodyNameInEnglish;
	private String totalChilds;
	
    	
	@Id
	@Column(name="local_body_code")
	public int getLocalBodyCode() {
		return localBodyCode;
	}
	public void setLocalBodyCode(int localBodyCode) {
		this.localBodyCode = localBodyCode;
	}
	
	@Column(name="local_body_name_english")
	public String getLocalBodyNameInEnglish() {
		return localBodyNameInEnglish;
	}
	public void setLocalBodyNameInEnglish(String localBodyNameInEnglish) {
		this.localBodyNameInEnglish = localBodyNameInEnglish;
	}
	@Column(name="child_count")
	public String getTotalChilds() {
		return totalChilds;
	}
	public void setTotalChilds(String totalChilds) {
		this.totalChilds = totalChilds;
	}

    
	
}


