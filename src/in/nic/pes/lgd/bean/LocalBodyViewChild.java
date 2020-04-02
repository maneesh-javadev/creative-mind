package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query=" SELECT * FROM get_parentwise_lb_list(:localBodyCode)",name="getLocalGovtBodyViewChild",resultClass=LocalBodyViewChild.class)
public class LocalBodyViewChild {



//private int localBodyTypeCode;
//private String localBodyTypeName; 
private int localBodyCode; 
private String localBodyNameInEnglish;
private String localBodyNameInLocal;
private String totalChilds;

@Column(name="child_count")
public String getTotalChilds() {
	return totalChilds;
}
public void setTotalChilds(String totalChilds) {
	this.totalChilds = totalChilds;
}


/*//@Id
@Column(name="local_body_type_code")
public int getLocalBodyTypeCode() {
	return localBodyTypeCode;
}
public void setLocalBodyTypeCode(int localBodyTypeCode) {
	this.localBodyTypeCode = localBodyTypeCode;
}
@Column(name="local_body_type_name")
public String getLocalBodyTypeName() {
	return localBodyTypeName;
}
public void setLocalBodyTypeName(String localBodyTypeName) {
	this.localBodyTypeName = localBodyTypeName;
}*/
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
@Column(name="local_body_name_local")
public String getLocalBodyNameInLocal() {
	return localBodyNameInLocal;
}
public void setLocalBodyNameInLocal(String localBodyNameInLocal) {
	this.localBodyNameInLocal = localBodyNameInLocal;
}
}