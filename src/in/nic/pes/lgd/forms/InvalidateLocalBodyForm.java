package in.nic.pes.lgd.forms;

import java.util.Date;

public class InvalidateLocalBodyForm {
	
	public Long userId;

	public int localBodyCode;
	
	public String description;
	
	public Date effectiveDate;
	
	public String localBodyName;
	
	public String orderNo;
	
	public Date orderDate;
	
	public int localBodyTypeCode;
	
	public int flagCode;

	public Date gazPubDate;
	
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

	public String orderPath;

	public int getLocalBodyCode() {
		return localBodyCode;
	}

	public void setLocalBodyCode(int localBodyCode) {
		this.localBodyCode = localBodyCode;
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

	public int getLocalBodyTypeCode() {
		return localBodyTypeCode;
	}

	public void setLocalBodyTypeCode(int localBodyTypeCode) {
		this.localBodyTypeCode = localBodyTypeCode;
	}

	public int getFlagCode() {
		return flagCode;
	}

	public void setFlagCode(int flagCode) {
		this.flagCode = flagCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getLocalBodyName() {
		return localBodyName;
	}

	public void setLocalBodyName(String localBodyName) {
		this.localBodyName = localBodyName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
