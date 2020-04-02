package in.nic.pes.lgd.bean;
/**
 * @author Sushil
 * @created on 16-01-2013
 */
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query = "select * from fn_get_govt_order_data(:lbCode)", name = "getLocalBodyGODetail", resultClass = LocalGovtBodyDTO.class)
public class LocalGovtBodyDTO  implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "file_name")
	private String govtOrderFileName;
	
	@Column(name = "file_location")
	private String govtOrderFileLocation;

	@Column(name = "order_no")
	private String orderNo;
	
	@Id
	@Column(name = "order_code")
	private Integer orderCode;
	
	@Column(name = "order_date")
	private Date orderDate;
	
	@Column(name = "effective_date")
	private Date govOrderEffectiveDate;
	
	@Column(name = "gaz_pub_date")
	private Date gazPubDate;
		
		
	public String getGovtOrderFileName() {
		return govtOrderFileName;
	}
	
	public void setGovtOrderFileName(String govtOrderFileName) {
		this.govtOrderFileName = govtOrderFileName;
	}
	
	public String getGovtOrderFileLocation() {
		return govtOrderFileLocation;
	}
	
	public void setGovtOrderFileLocation(String govtOrderFileLocation) {
		this.govtOrderFileLocation = govtOrderFileLocation;
	}
	
	public String getOrderNo() {
		return orderNo;
	}
	
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	public Integer getOrderCode() {
		return orderCode;
	}
	
	public void setOrderCode(Integer orderCode) {
		this.orderCode = orderCode;
	}
	
	public Date getOrderDate() {
		return orderDate;
	}
	
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	public Date getGovOrderEffectiveDate() {
		return govOrderEffectiveDate;
	}
	
	public void setGovOrderEffectiveDate(Date govOrderEffectiveDate) {
		this.govOrderEffectiveDate = govOrderEffectiveDate;
	}
	
	public Date getGazPubDate() {
		return gazPubDate;
	}
	
	public void setGazPubDate(Date gazPubDate) {
		this.gazPubDate = gazPubDate;
	}
}