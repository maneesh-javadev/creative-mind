package in.nic.pes.lgd.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query="SELECT * from designation_pes_delete(:designationCode)",name="checkDesignationPesDelete",resultClass=DesignationPesDelete.class)

public class DesignationPesDelete {

	@Id
	@Column(name = "designation_pes_delete", nullable = false)
	private boolean pesflag;

	public boolean isPesflag() {
		return pesflag;
	}

	public void setPesflag(boolean pesflag) {
		this.pesflag = pesflag;
	}
}
