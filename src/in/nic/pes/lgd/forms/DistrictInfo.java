package in.nic.pes.lgd.forms;

import java.io.Serializable;
import java.util.List;

public class DistrictInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8238088003423450612L;
	private String districtName;
	private List<String> contributeDistrictList;
	private List<String> contributeSubDistrictList;
	private List<String> contributeNewCreatedSubDistrictList;	
	private String villageMergeList;
	private String villageNewList;
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public List<String> getContributeDistrictList() {
		return contributeDistrictList;
	}
	public void setContributeDistrictList(List<String> contributeDistrictList) {
		this.contributeDistrictList = contributeDistrictList;
	}
	public List<String> getContributeSubDistrictList() {
		return contributeSubDistrictList;
	}
	public void setContributeSubDistrictList(List<String> contributeSubDistrictList) {
		this.contributeSubDistrictList = contributeSubDistrictList;
	}
	public List<String> getContributeNewCreatedSubDistrictList() {
		return contributeNewCreatedSubDistrictList;
	}
	public void setContributeNewCreatedSubDistrictList(
			List<String> contributeNewCreatedSubDistrictList) {
		this.contributeNewCreatedSubDistrictList = contributeNewCreatedSubDistrictList;
	}
	public String getVillageMergeList() {
		return villageMergeList;
	}
	public void setVillageMergeList(String villageMergeList) {
		this.villageMergeList = villageMergeList;
	}
	public String getVillageNewList() {
		return villageNewList;
	}
	public void setVillageNewList(String villageNewList) {
		this.villageNewList = villageNewList;
	}


}
