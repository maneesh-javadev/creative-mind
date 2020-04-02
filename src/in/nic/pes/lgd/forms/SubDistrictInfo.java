package in.nic.pes.lgd.forms;

import java.io.Serializable;
import java.util.List;

public class SubDistrictInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8238088003423450612L;
	private String subdistrictNameEnglish;
	private String subdistrictNameLocal;
	private String aliasEnglish;
	private String aliasLocal;
	private String census2011Code;
	private String sscode;
	private int villageNumber;
	private List<String> contributeVillages;
	private List<String> contributesubDistrict;
	
	
	public String getSubdistrictNameEnglish() {
		return subdistrictNameEnglish;
	}
	public void setSubdistrictNameEnglish(String subdistrictNameEnglish) {
		this.subdistrictNameEnglish = subdistrictNameEnglish;
	}
	public List<String> getContributeVillages() {
		return contributeVillages;
	}
	public void setContributeVillages(List<String> contributeVillages) {
		this.contributeVillages = contributeVillages;
	}
	public List<String> getContributesubDistrict() {
		return contributesubDistrict;
	}
	public void setContributesubDistrict(List<String> contributesubDistrict) {
		this.contributesubDistrict = contributesubDistrict;
	}
	public String getSubdistrictNameLocal() {
		return subdistrictNameLocal;
	}
	public void setSubdistrictNameLocal(String subdistrictNameLocal) {
		this.subdistrictNameLocal = subdistrictNameLocal;
	}
	public String getAliasEnglish() {
		return aliasEnglish;
	}
	public void setAliasEnglish(String aliasEnglish) {
		this.aliasEnglish = aliasEnglish;
	}
	public String getAliasLocal() {
		return aliasLocal;
	}
	public void setAliasLocal(String aliasLocal) {
		this.aliasLocal = aliasLocal;
	}
	public String getCensus2011Code() {
		return census2011Code;
	}
	public void setCensus2011Code(String census2011Code) {
		this.census2011Code = census2011Code;
	}
	public String getSscode() {
		return sscode;
	}
	public void setSscode(String sscode) {
		this.sscode = sscode;
	}
	public int getVillageNumber() {
		return villageNumber;
	}
	public void setVillageNumber(int villageNumber) {
		this.villageNumber = villageNumber;
	}
	

}
