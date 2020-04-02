package in.nic.pes.lgd.response;

import java.util.Date;

public class InvalidatedVillageResponse{

	public Long userId;

	public int villageCode;
	
	public String description;
	
	public Date effectiveDate;
	
	public String villageName;
	
	public String districtName;
	
	public int districtCode;
	
	public int localBodyTypeCode;
	
	public int flagCode;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getVillageCode() {
		return villageCode;
	}

	public void setVillageCode(int villageCode) {
		this.villageCode = villageCode;
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

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public int getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(int districtCode) {
		this.districtCode = districtCode;
	}

}
