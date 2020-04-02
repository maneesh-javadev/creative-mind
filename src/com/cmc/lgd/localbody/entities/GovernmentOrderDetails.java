package com.cmc.lgd.localbody.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity(name = "com.cmc.lgd.localbody.entities.GovernmentOrderDetails")
@NamedNativeQueries({ 
	@NamedNativeQuery(query = "SELECT *, null as file_size, null as file_content_type from get_order_details (:stateCode, :orderNo, :rangeFrom, :rangeTo)", name = "LB_Fetch_Government_Order_Details", resultClass = GovernmentOrderDetails.class),//, :stateCode
	@NamedNativeQuery(query = "SELECT order_code, order_no, CAST(order_date AS date), CAST(effective_date AS date), " +
			 				  "CAST(gaz_pub_date AS date), order_path, file_name, file_location, file_timestamp, null as file_size, null as file_content_type " +
			 				  "FROM government_order LEFT JOIN attachment ON announcement_id = order_code WHERE ORDER_CODE = (:paramOderCode)", name = "Fetch_GO_Details_By_Order_Code", resultClass = GovernmentOrderDetails.class),
    @NamedNativeQuery(query = "select *, null as file_location from get_lb_govt_order_details_fn(:entityType, :entityCode, :entityVersion,:minorVersion)", name = "Entity_Wise_GO_Details", resultClass = GovernmentOrderDetails.class),
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

	@Column(name = "file_name")
	private String fileName;
	
	@Column(name = "file_location")
	private String fileLocation;
	
	@Column(name = "file_timestamp")
	private String fileTimestamp;
	
	@Column(name = "file_size")
	private Long fileSize;
	
	@Column(name = "file_content_type")
	private String fileContentType;  
	
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	public String getFileTimestamp() {
		return fileTimestamp;
	}

	public void setFileTimestamp(String fileTimestamp) {
		this.fileTimestamp = fileTimestamp;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
}
