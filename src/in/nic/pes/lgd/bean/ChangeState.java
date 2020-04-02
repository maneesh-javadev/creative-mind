package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
@Entity
@NamedNativeQuery(query = "select * from change_state_fn(:stateCode,:stateNameEnglishch,:userId,:orderNo,:Orderdate,:effectiveDate,:orderpath,:stateOrUt,:slc,:stateNameEnglish,:gazpubDate,:stateNameLocal,:sortName)", name ="StateChangeQuery",resultClass=ChangeState.class)
public class ChangeState {
	
	@Id
	@Column(name = "change_state_fn", nullable = false)
	private String change_state_fn;

	public String getChange_state_fn() {
		return change_state_fn;
	}

	public void setChange_state_fn(String change_state_fn) {
		this.change_state_fn = change_state_fn;
	}
	
	
	
}

	