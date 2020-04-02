package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;


@Entity
@NamedNativeQuery(query = "select * from set_govt_order(:entityCode,:entityType)", name ="GovOrderEntityWiseQuery",resultClass=SetGovermentOrderEntity.class)
public class SetGovermentOrderEntity {
	@Id
	private boolean set_govt_order;
	@Column(name = "set_govt_order", nullable = false)
	public boolean isSet_govt_order() {
		return set_govt_order;
	}

	public void setSet_govt_order(boolean set_govt_order) {
		this.set_govt_order = set_govt_order;
	}

}
