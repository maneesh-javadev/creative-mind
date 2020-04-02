package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({ @NamedNativeQuery(query = "SELECT row_number() over() as count, * from get_entity_freeze_unfreeze_sts_fn(:entityCode, :entityType);", name = "Check_Freeze_Unfreeze_Status", resultClass = FreezeUnfreezeStatus.class), })
public class FreezeUnfreezeStatus {

	@Id
	@Column(name = "count", nullable = false)
	private Integer count;
	
	@Column(name = "user_level")
	private String userLevel;
	
	@Column(name = "land_region_status")
	private Boolean landRegionStatus;
	
	@Column(name = "pri_status")
	private Boolean priStatus;
	
	@Column(name = "traditional_status")
	private Boolean traditionalStatus;

	@Column(name = "urban_status")
	private Boolean urbanStatus;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	public Boolean getLandRegionStatus() {
		return landRegionStatus;
	}

	public void setLandRegionStatus(Boolean landRegionStatus) {
		this.landRegionStatus = landRegionStatus;
	}

	public Boolean getPriStatus() {
		return priStatus;
	}

	public void setPriStatus(Boolean priStatus) {
		this.priStatus = priStatus;
	}

	public Boolean getTraditionalStatus() {
		return traditionalStatus;
	}

	public void setTraditionalStatus(Boolean traditionalStatus) {
		this.traditionalStatus = traditionalStatus;
	}

	public Boolean getUrbanStatus() {
		return urbanStatus;
	}

	public void setUrbanStatus(Boolean urbanStatus) {
		this.urbanStatus = urbanStatus;
	}
}
