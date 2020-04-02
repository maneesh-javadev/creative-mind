package in.nic.pes.lgd.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
@NamedNativeQuery(query = "SELECT * FROM get_state_wise_lb_type_wise_local_body_rpt(:type,:level,:stateCode)", name = "getLocalBodyListbyLBtypebyState", resultClass = GetLocalBodyListbyLBtypebyState.class),
@NamedNativeQuery(query = "SELECT * FROM get_state_wise_lb_type_wise_local_body_rpt(:type,:level,:stateCode,:parentLBCode)", name = "getLBListbyLBtypebyParentCbyState", resultClass = GetLocalBodyListbyLBtypebyState.class),
@NamedNativeQuery(query = "SELECT * FROM get_state_wise_lb_type_wise_local_body_rpt_by_finyear(:type,:level,:stateCode,:parentLBCode,:financialYear)", name = "getLBListbyLBtypebyParentCbyFinYear", resultClass = GetLocalBodyListbyLBtypebyState.class),
@NamedNativeQuery(query = "SELECT * FROM get_state_wise_lb_type_wise_local_body_rpt_by_finyear_pes(:type,:level,:stateCode,:parentLBCode,:financialYear)", name = "getLBListbyLBtypebyParentCbyFinYearPES", resultClass = GetLocalBodyListbyLBtypebyState.class),
@NamedNativeQuery(query = "SELECT * FROM get_state_wise_lb_type_wise_local_body_rpt_by_finyear(:type,:level,:stateCode,:parentLBCode)", name = "getLBListbyLBtypebyParentCbyWithoutFinYear", resultClass = GetLocalBodyListbyLBtypebyState.class),
@NamedNativeQuery(query = "SELECT * FROM get_state_wise_lb_type_wise_local_body_rpt_by_finyear_pes(:type,:level,:stateCode,:parentLBCode)", name = "getLBListbyLBtypebyParentCbyWithoutFinYearPES", resultClass = GetLocalBodyListbyLBtypebyState.class),
@NamedNativeQuery(query = "SELECT * FROM get_state_wise_lb_type_wise_local_body_rpt_new(:type,:level,:stateCode)", name = "getLBListbyLBtypebyParentCbyStateCombined", resultClass = GetLocalBodyListbyLBtypebyState.class),
@NamedNativeQuery(query = "SELECT * FROM get_state_wise_lb_type_wise_local_body_rpt_new(:type,:level,:stateCode,:parentLBCode)", name = "getLBListbyLBtypebyParentCbyStateCombinedCb", resultClass = GetLocalBodyListbyLBtypebyState.class)
})
public class GetLocalBodyListbyLBtypebyState implements Serializable {
	private static final long serialVersionUID = 2714259262610566609L;
	private int localBodyCode;
	private String localbodyNameEnglish;
	private String localbodyNameLocal;
	private String localbodyTypeName;
	private Integer childCount;
	private Integer grandChildCount;
	private String sscode;
	private Integer villageCouncelCount;
	
	@Id
	@Column(name="local_body_code")
	public int getLocalBodyCode() {
		return localBodyCode;
	}
	public void setLocalBodyCode(int localBodyCode) {
		this.localBodyCode = localBodyCode;
	}
	
	@Column(name="local_body_name_english")
	public String getLocalbodyNameEnglish() {
		return localbodyNameEnglish;
	}
	public void setLocalbodyNameEnglish(String localbodyNameEnglish) {
		this.localbodyNameEnglish = localbodyNameEnglish;
	}
	
	@Column(name="local_body_name_local")
	public String getLocalbodyNameLocal() {
		return localbodyNameLocal;
	}
	public void setLocalbodyNameLocal(String localbodyNameLocal) {
		this.localbodyNameLocal = localbodyNameLocal;
	}
	
	@Column(name="local_body_type_name")
	public String getLocalbodyTypeName() {
		return localbodyTypeName;
	}
	public void setLocalbodyTypeName(String localbodyTypeName) {
		this.localbodyTypeName = localbodyTypeName;
	}
	
	@Column(name="child_count")
	public Integer getChildCount() {
		return childCount;
	}
	public void setChildCount(Integer childCount) {
		this.childCount = childCount;
	}
	
	@Column(name="grand_child_count")
	public Integer getGrandChildCount() {
		return grandChildCount;
	}
	public void setGrandChildCount(Integer grandChildCount) {
		this.grandChildCount = grandChildCount;
	}

	@Column(name="sscode")
	public String getSscode() {
		return sscode;
	}
	public void setSscode(String sscode) {
		this.sscode = sscode;
	}
	
	@Column(name="grand_child_count_trd")
	public Integer getVillageCouncelCount() {
		return villageCouncelCount;
	}
	public void setVillageCouncelCount(Integer villageCouncelCount) {
		this.villageCouncelCount = villageCouncelCount;
	}
	
}