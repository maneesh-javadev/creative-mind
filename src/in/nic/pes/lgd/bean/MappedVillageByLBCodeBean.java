package in.nic.pes.lgd.bean;



public class MappedVillageByLBCodeBean
{

	private int villageCode;
	private int censusCode;
	private String villageNameEnglish;
	private boolean isMainRegion;
	private String coverageType;
	public int getVillageCode() {
		return villageCode;
	}
	public void setVillageCode(int villageCode) {
		this.villageCode = villageCode;
	}
	public int getCensusCode() {
		return censusCode;
	}
	public void setCensusCode(int censusCode) {
		this.censusCode = censusCode;
	}
	public String getVillageNameEnglish() {
		return villageNameEnglish;
	}
	public void setVillageNameEnglish(String villageNameEnglish) {
		this.villageNameEnglish = villageNameEnglish;
	}
	public boolean isMainRegion() {
		return isMainRegion;
	}
	public void setMainRegion(boolean isMainRegion) {
		this.isMainRegion = isMainRegion;
	}
	public String getCoverageType() {
		return coverageType;
	}
	public void setCoverageType(String coverageType) {
		this.coverageType = coverageType;
	}
	
	
	
}