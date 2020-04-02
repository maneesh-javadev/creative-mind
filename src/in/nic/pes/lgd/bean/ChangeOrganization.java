package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query = "select * from change_organization_fn(:orgCode,:orgNameEnglish)", name ="OrganizationChangeQuery",resultClass=ChangeOrganization.class)
public class ChangeOrganization {
	@Id
	private boolean change_organization_fn;
	
	@Column(name = "change_organization_fn", nullable = false)
	public boolean isChange_organization_fn() {
		return change_organization_fn;
	}

	public void setChange_organization_fn(boolean change_organization_fn) {
		this.change_organization_fn = change_organization_fn;
	}
	
}
