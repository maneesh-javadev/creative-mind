package in.nic.pes.lgd.bean;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query="SELECT * from push_new_localbody_pes(:localBodyCode)",name="pushLBtoPES",resultClass=PushLBtoPES.class)


public class PushLBtoPES implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="push_new_localbody_pes")
	private boolean push_new_localbody_pes;
	
	public boolean isPush_new_localbody_pes() {
		return push_new_localbody_pes;
	}
	public void setPush_new_localbody_pes(boolean push_new_localbody_pes) {
		this.push_new_localbody_pes = push_new_localbody_pes;
	}

}
