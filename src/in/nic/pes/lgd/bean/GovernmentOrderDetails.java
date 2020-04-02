package in.nic.pes.lgd.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({ 
	//@NamedNativeQuery(query = "SELECT order_code, order_no, order_date, effective_date, gaz_pub_date, order_path  FROM government_order limit 10;", name = "Fetch_Government_Order_Details", resultClass = GovernmentOrderDetails.class),
	@NamedNativeQuery(query = "SELECT * from get_order_details (:orderNo, :rangeFrom, :rangeTo)", name = "Fetch_Government_Order_Details", resultClass = GovernmentOrderDetails.class),
})
public class GovernmentOrderDetails {

	@Id
	@Column(name = "order_code")
	private Integer orderCode;
	
	@Column(name = "order_no")
	private String orderNo;
	
	@Column(name = "order_date")
	private Date orderDate;
	
	@Column(name = "effective_date")
	private Date effectiveDate;

	@Column(name = "gaz_pub_date")
	private Date gazPubDate;
	
	@Column(name = "order_path")
	private String orderPath;

	public Integer getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(Integer orderCode) {
		this.orderCode = orderCode;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getGazPubDate() {
		return gazPubDate;
	}

	public void setGazPubDate(Date gazPubDate) {
		this.gazPubDate = gazPubDate;
	}

	public String getOrderPath() {
		return orderPath;
	}

	public void setOrderPath(String orderPath) {
		this.orderPath = orderPath;
	}
	
	
}
