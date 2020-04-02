package in.nic.pes.lgd.forms;

import java.util.List;

import in.nic.pes.lgd.bean.GetEntityEffectiveDate;

public class EffectiveDateForm {
	
	private List<GetEntityEffectiveDate> getEntityEffectiveDateList;
	
	private Integer userId;

	public List<GetEntityEffectiveDate> getGetEntityEffectiveDateList() {
		return getEntityEffectiveDateList;
	}

	public void setGetEntityEffectiveDateList(List<GetEntityEffectiveDate> getEntityEffectiveDateList) {
		this.getEntityEffectiveDateList = getEntityEffectiveDateList;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	

}
