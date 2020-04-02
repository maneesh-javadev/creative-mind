package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;



@Entity



@NamedNativeQuery(query="select * from set_gazette_date_fn(:ordercode,:gazPubDate)",name="getGazettePublicationDateDetailsSave",resultClass=GazettePublicationDateSave.class)

public class GazettePublicationDateSave {
	@Id
	private int orderCode;
	    
		@Column(name="order_Code")
	public int getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}
}
