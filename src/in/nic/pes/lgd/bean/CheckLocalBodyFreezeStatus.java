package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;



@Entity(name = "in.nic.pes.lgd.bean.CheckLocalBodyFreezeStatus")
@NamedNativeQueries({ 
	@NamedNativeQuery(query = "select * from does_lb_pri_in_freeze_exist_fn(:districtCode)", name = "doeslbPriFreezExist", resultClass = CheckLocalBodyFreezeStatus.class),
	@NamedNativeQuery(query = "select * from does_lb_trd_in_freeze_exist_fn(:districtCode)", name = "doeslbTriFreezExist", resultClass = CheckLocalBodyFreezeStatus.class),
	@NamedNativeQuery(query = "select * from does_lb_urb_in_freeze_exist_fn(:districtCode)", name = "doeslbUrbFreezExist", resultClass = CheckLocalBodyFreezeStatus.class),
})
public class CheckLocalBodyFreezeStatus {
	
	private Integer lblc;
	private String lbName;	
	private String status;
	
	@Id
	@Column(name="lblc")
	public Integer getLblc() {
		return lblc;
	}
	public void setLblc(Integer lblc) {
		this.lblc = lblc;
	}
	
	
	@Column(name="lb_name")
	public String getLbName() {
		return lbName;
	}
	public void setLbName(String lbName) {
		this.lbName = lbName;
	}
	
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}
