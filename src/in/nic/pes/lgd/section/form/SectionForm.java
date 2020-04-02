package in.nic.pes.lgd.section.form;

import java.util.Date;
import java.util.List;

import in.nic.pes.lgd.section.rule.Section;

public class SectionForm {
	
	/**
	 * General variables
	 * @return
	 */
	
	private Integer sectionCode;
	
	private Integer sectionVersion;
	
	private String sectionNameEnglish;
	
	private String  sectionNameLocal;
	
	private String  sectionShortName;
	
	private String address1;
	
	private String address2;
	
	private String address3;
	
	private String localAddress1;
	
	private String localAddress2;
	
	private String localAddress3;
	
	private Integer pinCode;
	
	private String parentCode;
	
	private String parentType;
	
	private Date effectiveDate;
	
	private Integer userId;
	
	private Integer opeartionCode;
	
	private Integer slc;
	
	private String parentChildFlg;
	
	private String selectedLbList;
	
	
	/**
	 * lb Hierarchy variables
	 * @return
	 */
	
	private String lbTypeHierarchy;
	
	private String localBodyType;
	
	private String localBodyLevelCodes;
	
	private String lbSpcificFullRadio; 
	
	/**
	 * org variables
	 * @return
	 */
	
	private String orgLevelCodes;
	
	private String orgSpcificFullRadio;
	
	private String selectedOrgList;
	
	/**
	 * for manage 
	 */
	private String sectionNameEnglishChange;
	
	private List<Section> sectionList;
	
	private List<Section> sectionDeleteList;
	
	
	
	private String isCenterorstate;
	
	private Integer orgTypeCode;

	public Integer getSectionCode() {
		return sectionCode;
	}

	public void setSectionCode(Integer sectionCode) {
		this.sectionCode = sectionCode;
	}

	public Integer getSectionVersion() {
		return sectionVersion;
	}

	public void setSectionVersion(Integer sectionVersion) {
		this.sectionVersion = sectionVersion;
	}

	public String getSectionNameEnglish() {
		return sectionNameEnglish;
	}

	public void setSectionNameEnglish(String sectionNameEnglish) {
		this.sectionNameEnglish = sectionNameEnglish;
	}

	public String getSectionNameLocal() {
		return sectionNameLocal;
	}

	public void setSectionNameLocal(String sectionNameLocal) {
		this.sectionNameLocal = sectionNameLocal;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getLocalAddress1() {
		return localAddress1;
	}

	public void setLocalAddress1(String localAddress1) {
		this.localAddress1 = localAddress1;
	}

	public String getLocalAddress2() {
		return localAddress2;
	}

	public void setLocalAddress2(String localAddress2) {
		this.localAddress2 = localAddress2;
	}

	public String getLocalAddress3() {
		return localAddress3;
	}

	public void setLocalAddress3(String localAddress3) {
		this.localAddress3 = localAddress3;
	}

	public Integer getPinCode() {
		return pinCode;
	}

	public void setPinCode(Integer pinCode) {
		this.pinCode = pinCode;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getParentType() {
		return parentType;
	}

	public void setParentType(String parentType) {
		this.parentType = parentType;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getOpeartionCode() {
		return opeartionCode;
	}

	public void setOpeartionCode(Integer opeartionCode) {
		this.opeartionCode = opeartionCode;
	}

	public Integer getSlc() {
		return slc;
	}

	public void setSlc(Integer slc) {
		this.slc = slc;
	}

	public String getParentChildFlg() {
		return parentChildFlg;
	}

	public void setParentChildFlg(String parentChildFlg) {
		this.parentChildFlg = parentChildFlg;
	}

	public String getSelectedLbList() {
		return selectedLbList;
	}

	public void setSelectedLbList(String selectedLbList) {
		this.selectedLbList = selectedLbList;
	}

	public String getLbTypeHierarchy() {
		return lbTypeHierarchy;
	}

	public void setLbTypeHierarchy(String lbTypeHierarchy) {
		this.lbTypeHierarchy = lbTypeHierarchy;
	}

	public String getLocalBodyType() {
		return localBodyType;
	}

	public void setLocalBodyType(String localBodyType) {
		this.localBodyType = localBodyType;
	}

	public String getLocalBodyLevelCodes() {
		return localBodyLevelCodes;
	}

	public void setLocalBodyLevelCodes(String localBodyLevelCodes) {
		this.localBodyLevelCodes = localBodyLevelCodes;
	}

	public String getLbSpcificFullRadio() {
		return lbSpcificFullRadio;
	}

	public void setLbSpcificFullRadio(String lbSpcificFullRadio) {
		this.lbSpcificFullRadio = lbSpcificFullRadio;
	}

	public String getOrgLevelCodes() {
		return orgLevelCodes;
	}

	public void setOrgLevelCodes(String orgLevelCodes) {
		this.orgLevelCodes = orgLevelCodes;
	}

	public String getOrgSpcificFullRadio() {
		return orgSpcificFullRadio;
	}

	public void setOrgSpcificFullRadio(String orgSpcificFullRadio) {
		this.orgSpcificFullRadio = orgSpcificFullRadio;
	}

	public String getSelectedOrgList() {
		return selectedOrgList;
	}

	public void setSelectedOrgList(String selectedOrgList) {
		this.selectedOrgList = selectedOrgList;
	}

	public List<Section> getSectionList() {
		return sectionList;
	}

	public void setSectionList(List<Section> sectionList) {
		this.sectionList = sectionList;
	}

	public String getSectionNameEnglishChange() {
		return sectionNameEnglishChange;
	}

	public void setSectionNameEnglishChange(String sectionNameEnglishChange) {
		this.sectionNameEnglishChange = sectionNameEnglishChange;
	}

	public List<Section> getSectionDeleteList() {
		return sectionDeleteList;
	}

	public void setSectionDeleteList(List<Section> sectionDeleteList) {
		this.sectionDeleteList = sectionDeleteList;
	}

	public String getIsCenterorstate() {
		return isCenterorstate;
	}

	public void setIsCenterorstate(String isCenterorstate) {
		this.isCenterorstate = isCenterorstate;
	}

	public Integer getOrgTypeCode() {
		return orgTypeCode;
	}

	public void setOrgTypeCode(Integer orgTypeCode) {
		this.orgTypeCode = orgTypeCode;
	}

	public String getSectionShortName() {
		return sectionShortName;
	}

	public void setSectionShortName(String sectionShortName) {
		this.sectionShortName = sectionShortName;
	}
	
	
	

	
	
	
	

}
