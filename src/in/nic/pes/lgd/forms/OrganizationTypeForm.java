package in.nic.pes.lgd.forms;

import in.nic.pes.lgd.bean.OrganizationType;

import java.util.ArrayList;
import java.util.List;

public class OrganizationTypeForm {

	private String orgTypeName;
	private String orgTypeName1;
	private String orgType;
	private int orgTypeCode;
	 private String orgNameLocal;
	 private int orgCode;
	 private String orgLevelSpecificName;
	 private String orgLevelSpecificNameLocal;
	 private String orgLevelSpecificShortName;
	 private String orgName;
	 private String orgNamech;
	 private char orgLevel;
	 private Integer orgVersion;
	 private String code;
	 private String orgTypeId;
	 private List<OrganizationType> organizationTypeDetails;
	 private int parentOrganization;
	 private String childOrganization;
	private String orgNameAndLevel;
	 private String shortName;
	 private int parentOrgCode;
	 private String childOrgCode;
	 //Code added by Arnab  Start
	 private Integer organizationId;
	 private Integer userId;
	 
	 
	 
	 public Integer getOrganizationId() 
	 {
			return organizationId;
	 }

	 public void setOrganizationId(Integer organizationId) 
	 {
			this.organizationId = organizationId;
	 }	
		//Code added by Arnab  End

	private List<OrganizationTypeForm> listOrgDetail = new ArrayList<OrganizationTypeForm>();
	public List<OrganizationTypeForm> getListOrgDetail() {
		return listOrgDetail;
	}

	public void setListOrgDetail(List<OrganizationTypeForm> listOrgDetail) {
		this.listOrgDetail = listOrgDetail;
	}

	
	public String getOrgTypeName1() {
		return orgTypeName1;
	}

	public void setOrgTypeName1(String orgTypeName1) {
		this.orgTypeName1 = orgTypeName1;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public int getOrgTypeCode() {
		return orgTypeCode;
	}

	public void setOrgTypeCode(int orgTypeCode) {
		this.orgTypeCode = orgTypeCode;
	}

	public String getOrgTypeName() {
		return orgTypeName;
	}

	public void setOrgTypeName(String orgTypeName) {
		this.orgTypeName = orgTypeName;
	}

	public String getOrgNameLocal() {
		return orgNameLocal;
	}

	public void setOrgNameLocal(String orgNameLocal) {
		this.orgNameLocal = orgNameLocal;
	}

	public int getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(int orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgLevelSpecificName() {
		return orgLevelSpecificName;
	}

	public void setOrgLevelSpecificName(String orgLevelSpecificName) {
		this.orgLevelSpecificName = orgLevelSpecificName;
	}

	public String getOrgLevelSpecificNameLocal() {
		return orgLevelSpecificNameLocal;
	}

	public void setOrgLevelSpecificNameLocal(String orgLevelSpecificNameLocal) {
		this.orgLevelSpecificNameLocal = orgLevelSpecificNameLocal;
	}

	public String getOrgLevelSpecificShortName() {
		return orgLevelSpecificShortName;
	}

	public void setOrgLevelSpecificShortName(String orgLevelSpecificShortName) {
		this.orgLevelSpecificShortName = orgLevelSpecificShortName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public char getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(char orgLevel) {
		this.orgLevel = orgLevel;
	}

	public Integer getOrgVersion() {
		return orgVersion;
	}

	public void setOrgVersion(Integer orgVersion) {
		this.orgVersion = orgVersion;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<OrganizationType> getOrganizationTypeDetails() {
		return organizationTypeDetails;
	}

	public void setOrganizationTypeDetails(List<OrganizationType> organizationTypeDetails) {
		this.organizationTypeDetails = organizationTypeDetails;
	}

	public String getOrgTypeId() {
		return orgTypeId;
	}

	public void setOrgTypeId(String orgTypeId) {
		this.orgTypeId = orgTypeId;
	}

	public String getOrgNamech() {
		return orgNamech;
	}

	public void setOrgNamech(String orgNamech) {
		this.orgNamech = orgNamech;
	}

	public int getParentOrganization() {
		return parentOrganization;
	}

	public void setParentOrganization(int parentOrganization) {
		this.parentOrganization = parentOrganization;
	}

	public String getChildOrganization() {
		return childOrganization;
	}

	public void setChildOrganization(String childOrganization) {
		this.childOrganization = childOrganization;
	}

	public int getParentOrgCode() {
		return parentOrgCode;
	}

	public void setParentOrgCode(int parentOrgCode) {
		this.parentOrgCode = parentOrgCode;
	}

	public String getChildOrgCode() {
		return childOrgCode;
	}

	public void setChildOrgCode(String childOrgCode) {
		this.childOrgCode = childOrgCode;
	}

	public String getOrgNameAndLevel() {
		return orgNameAndLevel;
	}

	public void setOrgNameAndLevel(String orgNameAndLevel) {
		this.orgNameAndLevel = orgNameAndLevel;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
	
}
