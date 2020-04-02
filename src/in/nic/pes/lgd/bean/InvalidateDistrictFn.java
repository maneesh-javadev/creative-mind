package in.nic.pes.lgd.bean;
import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query="SELECT * FROM invalidate_district_fn(:source_district_code,:user_id,:listformat,:effactive_date,:orderno,:orderdate,:guz_pub_date,:order_path)",name="insertInvalidateDistrictFn",resultClass=InvalidateDistrictFn.class)
public class InvalidateDistrictFn implements Serializable  {

	
	  /**
	 * 
	 */
	private static final long serialVersionUID = -3199081923789540562L;
	private String OrderCode;
	@Id 
	@Basic(optional = false)
	@Column(name = "invalidate_district_fn", nullable = false)
	public String getOrderCode() {
		return OrderCode;
	}

	public void setOrderCode(String orderCode) {
		OrderCode = orderCode;
	}
	
		}


