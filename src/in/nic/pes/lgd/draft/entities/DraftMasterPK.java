package in.nic.pes.lgd.draft.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DraftMasterPK implements java.io.Serializable {
	
	
	@Column(name="lb_lr_code")
	private Integer lbLRCode;
	
	@Column(name="lb_lr_type")
	private String lbLRType;
	
	@Column(name="draft_temp_id")
	private Integer draftTempId;

	public Integer getLbLRCode() {
		return lbLRCode;
	}

	public void setLbLRCode(Integer lbLRCode) {
		this.lbLRCode = lbLRCode;
	}

	public String getLbLRType() {
		return lbLRType;
	}

	public void setLbLRType(String lbLRType) {
		this.lbLRType = lbLRType;
	}

	public Integer getDraftTempId() {
		return draftTempId;
	}

	public void setDraftTempId(Integer draftTempId) {
		this.draftTempId = draftTempId;
	}
	
	
	

}
