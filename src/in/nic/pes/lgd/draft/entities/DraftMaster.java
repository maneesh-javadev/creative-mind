package in.nic.pes.lgd.draft.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;



@Entity
@Table(name="draft_master")
public class DraftMaster implements java.io.Serializable  {
	
	
	
	/*@AttributeOverrides({
		@AttributeOverride(name = "lbLRCode", column = @Column(name = "lb_lr_code", nullable = false)),
		@AttributeOverride(name = "lbLRType", column = @Column(name = "lb_lr_type", nullable = false))
	})*/
	@EmbeddedId
	private DraftMasterPK draftMasterPK;
	
	@Column(name="process_id")
	private Integer ProcessId;
	
	

	public DraftMasterPK getDraftMasterPK() {
		return draftMasterPK;
	}

	public void setDraftMasterPK(DraftMasterPK draftMasterPK) {
		this.draftMasterPK = draftMasterPK;
	}

	public Integer getProcessId() {
		return ProcessId;
	}

	public void setProcessId(Integer processId) {
		ProcessId = processId;
	}

	
	
	

}
