package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query="SELECT * from delete_designation(:designationCode)",name="deleteDesignation",resultClass=DesignationDelete.class)

public class DesignationDelete {

	@Id
	@Column(name = "delete_designation", nullable = false)
	private boolean pesflag;

	public boolean isPesflag() {
		return pesflag;
	}

	public void setPesflag(boolean pesflag) {
		this.pesflag = pesflag;
	}
}
