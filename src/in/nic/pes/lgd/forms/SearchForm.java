package in.nic.pes.lgd.forms;

import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.Search;
import in.nic.pes.lgd.bean.State;
import in.nic.pes.lgd.bean.Subdistrict;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SearchForm {

	public String getMultipleStateNameEnglish() {
		return multipleStateNameEnglish;
	}

	public void setMultipleStateNameEnglish(String multipleStateNameEnglish) {
		this.multipleStateNameEnglish = multipleStateNameEnglish;
	}

	private char type;
	private String entityName;
	private boolean stateChecked;
	private boolean districtChecked;
	private boolean subDistrictChecked;
	private boolean blockChecked;
	private boolean villageChecked;
	private boolean ruralChecked;
	private boolean panchayatChecked;
	private boolean traditionalChecked;
	private boolean urbanChecked;
	private boolean acChecked;
	private boolean pcChecked;
	private boolean organistionChecked;
	private boolean orgUnitChecked;
	private String stateNameEnglish;
	private String districtNameEnglish;
	private String subdistrictNameEnglish;
	private String parlimentNameEnglish;
	private String acNameEnglish;
    private String multipleStateNameEnglish;
	private String villageNameEnglish;
	private List<State> listStateDetails = new ArrayList<State>();
	private List<District> listDistrictDetails = new ArrayList<District>();
	private List<Subdistrict> listSubdistrictDetails = new ArrayList<Subdistrict>();
	private List<Search> stateSearchDetail = new ArrayList<Search>();
	private List<Search> districtSearchDetail = new ArrayList<Search>();
	private List<Search> subdistrictSearchDetail = new ArrayList<Search>();
	private List<Search> villageSearchDetail = new ArrayList<Search>();
	private String downloadOption;
	private String entity;
	private String entityItem;
	private boolean onlyModification;
	private Date fromDate;
	private Date toDate;
	private String selectedVal;
	private String captchaAnswer;
	private String districtNameEnglishvill;
	private String subdistrictNameEnglishvill;
	private Integer entityCode;
	public String[] getCheckedValues() {
		return checkedValues;
	}

	public void setCheckedValues(String[] checkedValues) {
		this.checkedValues = checkedValues;
	}

	private String searchValueOption;
	private String[] checkedValues;
	//Added by Arnab  Start
	  

	public Integer getEntityCode() {
		return entityCode;
	}

	public String getSearchValueOption() {
		return searchValueOption;
	}

	public void setSearchValueOption(String searchValueOption) {
		this.searchValueOption = searchValueOption;
	}

	public void setEntityCode(Integer entityCode) {
		this.entityCode = entityCode;
	}

	public String getCaptchaAnswer() {
		return captchaAnswer;
	}

	public void setCaptchaAnswer(String captchaAnswer) {
		this.captchaAnswer = captchaAnswer;
	}

	private Integer globalstateId;
		  
	public Integer getGlobalstateId() 
	{
		return globalstateId;
	}

	public void setGlobalstateId(Integer globalstateId) 
	{
		this.globalstateId = globalstateId;
	}
	
	private Integer globaldistrictId;
	
	public Integer getGlobaldistrictId() {
		return globaldistrictId;
	}

	public void setGlobaldistrictId(Integer globaldistrictId) {
		this.globaldistrictId = globaldistrictId;
	}
	
	private Integer globalsubdistrictId;
	public Integer getGlobalsubdistrictId() 
    {
		return globalsubdistrictId;
	}
	public void setGlobalsubdistrictId(Integer globalsubdistrictId) 
	{
		this.globalsubdistrictId = globalsubdistrictId;
	}
	
	private Integer globalvillageId;
	
	public Integer getGlobalvillageId() {
		return globalvillageId;
	}
	public void setGlobalvillageId(Integer globalvillageId) {
		this.globalvillageId = globalvillageId;
	}
	
	//Added by Arnab  End
	   
	public String getSelectedVal() {
		return selectedVal;
	}
	public void setSelectedVal(String selectedVal) {
		this.selectedVal = selectedVal;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	public String getSubdistrictNameEnglish() {
		return subdistrictNameEnglish;
	}
	public void setSubdistrictNameEnglish(String subdistrictNameEnglish) {
		this.subdistrictNameEnglish = subdistrictNameEnglish;
	}
	public String getVillageNameEnglish() {
		return villageNameEnglish;
	}
	public void setVillageNameEnglish(String villageNameEnglish) {
		this.villageNameEnglish = villageNameEnglish;
	}
	public List<Search> getDistrictSearchDetail() {
		return districtSearchDetail;
	}
	public void setDistrictSearchDetail(List<Search> districtSearchDetail) {
		this.districtSearchDetail = districtSearchDetail;
	}
	public List<Search> getSubdistrictSearchDetail() {
		return subdistrictSearchDetail;
	}
	public void setSubdistrictSearchDetail(List<Search> subdistrictSearchDetail) {
		this.subdistrictSearchDetail = subdistrictSearchDetail;
	}
	public List<Search> getVillageSearchDetail() {
		return villageSearchDetail;
	}
	public void setVillageSearchDetail(List<Search> villageSearchDetail) {
		this.villageSearchDetail = villageSearchDetail;
	}
	public List<Search> getStateSearchDetail() {
		return stateSearchDetail;
	}
	public void setStateSearchDetail(List<Search> stateSearchDetail) {
		this.stateSearchDetail = stateSearchDetail;
	}
	public List<Subdistrict> getListSubdistrictDetails() {
		return listSubdistrictDetails;
	}
	public void setListSubdistrictDetails(List<Subdistrict> listSubdistrictDetails) {
		this.listSubdistrictDetails = listSubdistrictDetails;
	}
	public String getDistrictNameEnglish() {
		return districtNameEnglish;
	}
	public void setDistrictNameEnglish(String districtNameEnglish) {
		this.districtNameEnglish = districtNameEnglish;
	}
	public List<District> getListDistrictDetails() {
		return listDistrictDetails;
	}
	public void setListDistrictDetails(List<District> listDistrictDetails) {
		this.listDistrictDetails = listDistrictDetails;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public boolean isStateChecked() {
		return stateChecked;
	}
	public void setStateChecked(boolean stateChecked) {
		this.stateChecked = stateChecked;
	}
	public boolean isDistrictChecked() {
		return districtChecked;
	}
	public void setDistrictChecked(boolean districtChecked) {
		this.districtChecked = districtChecked;
	}
	public boolean isSubDistrictChecked() {
		return subDistrictChecked;
	}
	public void setSubDistrictChecked(boolean subDistrictChecked) {
		this.subDistrictChecked = subDistrictChecked;
	}
	public boolean isBlockChecked() {
		return blockChecked;
	}
	public void setBlockChecked(boolean blockChecked) {
		this.blockChecked = blockChecked;
	}
	public boolean isVillageChecked() {
		return villageChecked;
	}
	public void setVillageChecked(boolean villageChecked) {
		this.villageChecked = villageChecked;
	}
	public boolean isRuralChecked() {
		return ruralChecked;
	}
	public void setRuralChecked(boolean ruralChecked) {
		this.ruralChecked = ruralChecked;
	}
	public boolean isPanchayatChecked() {
		return panchayatChecked;
	}
	public void setPanchayatChecked(boolean panchayatChecked) {
		this.panchayatChecked = panchayatChecked;
	}
	public boolean isTraditionalChecked() {
		return traditionalChecked;
	}
	public void setTraditionalChecked(boolean traditionalChecked) {
		this.traditionalChecked = traditionalChecked;
	}
	public boolean isUrbanChecked() {
		return urbanChecked;
	}
	public void setUrbanChecked(boolean urbanChecked) {
		this.urbanChecked = urbanChecked;
	}
	public boolean isAcChecked() {
		return acChecked;
	}
	public void setAcChecked(boolean acChecked) {
		this.acChecked = acChecked;
	}
	public boolean isPcChecked() {
		return pcChecked;
	}
	public void setPcChecked(boolean pcChecked) {
		this.pcChecked = pcChecked;
	}
	public String getStateNameEnglish() {
		return stateNameEnglish;
	}
	public void setStateNameEnglish(String stateNameEnglish) {
		this.stateNameEnglish = stateNameEnglish;
	}
	public List<State> getListStateDetails() {
		return listStateDetails;
	}
	public void setListStateDetails(List<State> listStateDetails) {
		this.listStateDetails = listStateDetails;
	}
	public String getDownloadOption() {
		return downloadOption;
	}
	public void setDownloadOption(String downloadOption) {
		this.downloadOption = downloadOption;
	}
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public String getEntityItem() {
		return entityItem;
	}
	public void setEntityItem(String entityItem) {
		this.entityItem = entityItem;
	}
	public boolean isOnlyModification() {
		return onlyModification;
	}
	public void setOnlyModification(boolean onlyModification) {
		this.onlyModification = onlyModification;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
	
	public String getDistrictNameEnglishvill() {
		return districtNameEnglishvill;
	}
	public void setDistrictNameEnglishvill(String districtNameEnglishvill) {
		this.districtNameEnglishvill = districtNameEnglishvill;
	}

	public String getSubdistrictNameEnglishvill() {
		return subdistrictNameEnglishvill;
	}

	public void setSubdistrictNameEnglishvill(String subdistrictNameEnglishvill) {
		this.subdistrictNameEnglishvill = subdistrictNameEnglishvill;
	}

	public String getParlimentNameEnglish() {
		return parlimentNameEnglish;
	}

	public void setParlimentNameEnglish(String parlimentNameEnglish) {
		this.parlimentNameEnglish = parlimentNameEnglish;
	}

	public String getAcNameEnglish() {
		return acNameEnglish;
	}

	public void setAcNameEnglish(String acNameEnglish) {
		this.acNameEnglish = acNameEnglish;
	}

	public boolean isOrganistionChecked() {
		return organistionChecked;
	}

	public void setOrganistionChecked(boolean organistionChecked) {
		this.organistionChecked = organistionChecked;
	}

	public boolean isOrgUnitChecked() {
		return orgUnitChecked;
	}

	public void setOrgUnitChecked(boolean orgUnitChecked) {
		this.orgUnitChecked = orgUnitChecked;
	}
	
	
	
	
	
	
}
