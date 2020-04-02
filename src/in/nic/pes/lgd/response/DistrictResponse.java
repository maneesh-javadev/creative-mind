package in.nic.pes.lgd.response;

import java.util.Date;
import java.util.List;
import java.util.Set;

import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.DistrictPK;
import in.nic.pes.lgd.bean.Subdistrict;

public class DistrictResponse{

	private DistrictPK districtPK;
	private String districtNameEnglish;
	private String districtNameLocal;
	private String aliasEnglish;
	private String aliasLocal;
	private Integer slc;
	private Integer dlc;
	private String coordinates;
	private Boolean warningflag;
	private Integer mapCode;
	private String census2001Code;
	private String census2011Code;
	private String sscode;
	private Integer lrReplaces;
	private Integer lrReplacedby;
	private Date effectiveDate;
	private boolean isactive;
	private Date lastupdated;
	private long lastupdatedby;
	private Date createdon;
	private long createdby;
	private Set<Subdistrict> subdistricts;
	private int districtCode;
	private int districtVersion;
	private Character operation_state;
	private Character is_pesa;
	private Character operation_extend_flag;
	private String stateNameEnglish;
	
	public DistrictPK getDistrictPK() {
		return districtPK;
	}
	public void setDistrictPK(DistrictPK districtPK) {
		this.districtPK = districtPK;
	}
	public String getDistrictNameEnglish() {
		return districtNameEnglish;
	}
	public void setDistrictNameEnglish(String districtNameEnglish) {
		this.districtNameEnglish = districtNameEnglish;
	}
	public String getDistrictNameLocal() {
		return districtNameLocal;
	}
	public void setDistrictNameLocal(String districtNameLocal) {
		this.districtNameLocal = districtNameLocal;
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
	public Integer getSlc() {
		return slc;
	}
	public void setSlc(Integer slc) {
		this.slc = slc;
	}
	public Integer getDlc() {
		return dlc;
	}
	public void setDlc(Integer dlc) {
		this.dlc = dlc;
	}
	public String getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}
	public Boolean getWarningflag() {
		return warningflag;
	}
	public void setWarningflag(Boolean warningflag) {
		this.warningflag = warningflag;
	}
	public Integer getMapCode() {
		return mapCode;
	}
	public void setMapCode(Integer mapCode) {
		this.mapCode = mapCode;
	}
	public String getCensus2001Code() {
		return census2001Code;
	}
	public void setCensus2001Code(String census2001Code) {
		this.census2001Code = census2001Code;
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
	public Integer getLrReplaces() {
		return lrReplaces;
	}
	public void setLrReplaces(Integer lrReplaces) {
		this.lrReplaces = lrReplaces;
	}
	public Integer getLrReplacedby() {
		return lrReplacedby;
	}
	public void setLrReplacedby(Integer lrReplacedby) {
		this.lrReplacedby = lrReplacedby;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public Date getLastupdated() {
		return lastupdated;
	}
	public void setLastupdated(Date lastupdated) {
		this.lastupdated = lastupdated;
	}
	public long getLastupdatedby() {
		return lastupdatedby;
	}
	public void setLastupdatedby(long lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}
	public Date getCreatedon() {
		return createdon;
	}
	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}
	public long getCreatedby() {
		return createdby;
	}
	public void setCreatedby(long createdby) {
		this.createdby = createdby;
	}
	public Set<Subdistrict> getSubdistricts() {
		return subdistricts;
	}
	public void setSubdistricts(Set<Subdistrict> subdistricts) {
		this.subdistricts = subdistricts;
	}
	public int getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(int districtCode) {
		this.districtCode = districtCode;
	}
	public int getDistrictVersion() {
		return districtVersion;
	}
	public void setDistrictVersion(int districtVersion) {
		this.districtVersion = districtVersion;
	}
	public Character getOperation_state() {
		return operation_state;
	}
	public void setOperation_state(Character operation_state) {
		this.operation_state = operation_state;
	}
	public Character getIs_pesa() {
		return is_pesa;
	}
	public void setIs_pesa(Character is_pesa) {
		this.is_pesa = is_pesa;
	}
	public Character getOperation_extend_flag() {
		return operation_extend_flag;
	}
	public void setOperation_extend_flag(Character operation_extend_flag) {
		this.operation_extend_flag = operation_extend_flag;
	}
	public String getStateNameEnglish() {
		return stateNameEnglish;
	}
	public void setStateNameEnglish(String stateNameEnglish) {
		this.stateNameEnglish = stateNameEnglish;
	}
}
