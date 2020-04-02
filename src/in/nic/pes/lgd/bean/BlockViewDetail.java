package in.nic.pes.lgd.bean;

import javax.persistence.Entity;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query=" SELECT * FROM get_landregion_details(:blockCode)",name="getBlockHistoryDetails",resultClass=BlockViewDetail.class)
public class BlockViewDetail { // NO_UCD (unused code)
	
	private int blockCode;
	private String blockNameEnglish;
	private String blockNameLocal;
	private String aliasEnglish;
	private String aliasLocal;
	private String ssCode;
	private int blockVersion;
	private Integer mapCode;
	public int getBlockCode() {
		return blockCode;
	}
	public void setBlockCode(int blockCode) {
		this.blockCode = blockCode;
	}
	public String getBlockNameEnglish() {
		return blockNameEnglish;
	}
	public void setBlockNameEnglish(String blockNameEnglish) {
		this.blockNameEnglish = blockNameEnglish;
	}
	public String getBlockNameLocal() {
		return blockNameLocal;
	}
	public void setBlockNameLocal(String blockNameLocal) {
		this.blockNameLocal = blockNameLocal;
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
	public String getSsCode() {
		return ssCode;
	}
	public void setSsCode(String ssCode) {
		this.ssCode = ssCode;
	}
	public int getBlockVersion() {
		return blockVersion;
	}
	public void setBlockVersion(int blockVersion) {
		this.blockVersion = blockVersion;
	}
	public Integer getMapCode() {
		return mapCode;
	}
	public void setMapCode(Integer mapCode) {
		this.mapCode = mapCode;
	}
    
	

}
