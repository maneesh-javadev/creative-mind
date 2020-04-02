package in.nic.pes.lgd.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
@Entity
@NamedNativeQueries({
@NamedNativeQuery(	query=" SELECT * FROM get_new_LB_hierarchy_details(:stateCode,:fromDate,:toDate)",name="newLBHierarcyDetail",resultClass=LbByTypeofModification.class),
@NamedNativeQuery(query=" SELECT * FROM get_namechage_localbody_details(:stateCode,:fromDate,:toDate)",name="changedNameLBHierarcyDetail",resultClass=LbByTypeofModification.class),
@NamedNativeQuery(query=" SELECT * FROM get_invalidated_localbody_details(:stateCode,:fromDate,:toDate)",name="invalidatedBHierarcyDetail",resultClass=LbByTypeofModification.class)
})
public class LbByTypeofModification {
	@Column(name="state_code")
	private Integer stateCode;
	@Column(name="state_name_english")
	private String stateNameEnglish;
	@Id
    @Column(name="local_body_code")
	private Integer lbCode;
	@Column(name="local_body_name_english")
	private String lbName;
	@Column(name="local_body_type_name")
	private String lbType;
	@Column(name="district_code")
	private Integer disLbCode;
	@Column(name="district_name")
	private String disLbName;
	@Column(name="intermediate_code")
	private Integer interLbCode;
	@Column(name="intermediate_name")
	private String interLbName;
	@Column(name="old_local_body_name")
	private String lbNameOld;
	
	public Integer getStateCode() {
		return stateCode;
	}
	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}
	public String getStateNameEnglish() {
		return stateNameEnglish;
	}
	public void setStateNameEnglish(String stateNameEnglish) {
		this.stateNameEnglish = stateNameEnglish;
	}
	public Integer getLbCode() {
		return lbCode;
	}
	public void setLbCode(Integer lbCode) {
		this.lbCode = lbCode;
	}
	public String getLbName() {
		return lbName;
	}
	public void setLbName(String lbName) {
		this.lbName = lbName;
	}
	public String getLbType() {
		return lbType;
	}
	public void setLbType(String lbType) {
		this.lbType = lbType;
	}
	public Integer getDisLbCode() {
		if(disLbCode!=null)
		return disLbCode;
		else
			return 0;
	}
	public void setDisLbCode(Integer disLbCode) {
		this.disLbCode = disLbCode;
	}
	public String getDisLbName() {
		if(disLbName!=null)
		return disLbName;
		else
			return "";
				
	}
	public void setDisLbName(String disLbName) {
		this.disLbName = disLbName;
	}
	public Integer getInterLbCode() {
		if(interLbCode!=null)
		return interLbCode;
		else 
			return 0;
	}
	public void setInterLbCode(Integer interLbCode) {
		this.interLbCode = interLbCode;
	}
	public String getInterLbName() {
		if(interLbName!=null)
		return interLbName;
		else 
			return "";
	}
	public void setInterLbName(String interLbName) {
		this.interLbName = interLbName;
	}
	public String getLbNameOld() {
		return lbNameOld;
	}
	public void setLbNameOld(String lbNameOld) {
		this.lbNameOld = lbNameOld;
	}
	
	
	
}
