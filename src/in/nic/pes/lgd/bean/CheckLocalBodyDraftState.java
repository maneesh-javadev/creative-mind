package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

/**
 * 
 * @author Kirandeep 
 *
 */
@Entity(name = "in.nic.pes.lgd.bean.CheckLocalBodyDraftState")
@NamedNativeQueries({ 
	@NamedNativeQuery(query = "select * from does_lb_pri_child_in_draft_exist_fn(:districtCode)", name = "doeslbPriDraftExist", resultClass = CheckLocalBodyDraftState.class),
	@NamedNativeQuery(query = "select * from does_lb_trd_child_in_draft_exist_fn(:districtCode)", name = "doeslbPriDraftExistTRI", resultClass = CheckLocalBodyDraftState.class),
	@NamedNativeQuery(query = "select * from does_lb_urb_child_in_draft_exist_fn(:districtCode)", name = "doeslbPriDraftExistUrb", resultClass = CheckLocalBodyDraftState.class),
})
public class CheckLocalBodyDraftState {
	
	private Integer lblc;
	private String lbName;	
	
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
	

}
