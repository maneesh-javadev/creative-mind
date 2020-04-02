package in.nic.pes.lgd.bean;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query="SELECT * from push_organization_to_pes(:EntityCode)",name="pushOrgDepttoPES",resultClass=PushOrgDepToPes.class)


public class PushOrgDepToPes implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="push_organization_to_pes")
	private boolean push_organization_to_pes;
	
	public boolean isPush_organization_to_pes() {
		return push_organization_to_pes;
	}
	public void setPush_organization_to_pes(boolean push_organization_to_pes) {
		this.push_organization_to_pes = push_organization_to_pes;
	}
	
	
}


