package in.nic.pes.lgd.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;



@Entity
@NamedNativeQuery(query=" SELECT * FROM get_ungazetted_entity_list_fn()",name="getGazettePublicationDateDetails",resultClass=GazettePublication.class)

public class GazettePublication {

	private int orderCode;
	private String orderNo;
	private Date orderDate;
	private Date effectiveDate;
	private String orderPath;
	private String entityType;
	private String entityNameEnglish;

	/*private String description;
	private String status;*/

	@Id
	@Column(name="order_no")
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	@Column(name="order_date")
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	@Column(name="effective_date")
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
	
	
	
	/*@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}*/
	
	@Column(name="order_Code")
	public int getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}
	
	@Column(name="order_Path")
	public String getOrderPath() {
		return orderPath;
	}
	public void setOrderPath(String orderPath) {
		this.orderPath = orderPath;
	}
	
	@Column(name="entity_type")
	public String getEntityType() {
		return entityType;
	}
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	@Column(name="entity_name_english")
	public String getEntityNameEnglish() {
		return entityNameEnglish;
	}
	public void setEntityNameEnglish(String entityNameEnglish) {
		this.entityNameEnglish = entityNameEnglish;
	}
	
	/*@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}*/
	
	

	
	
	
	

	
}
