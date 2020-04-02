package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Transient;

@Entity
@NamedNativeQuery(query="select row_number() over() as rid,* from report_districtwise_village_and_mapped_gp(:districtCode)",name="REPORT_DISTRICTWISE_LB_AND_MAPPED_VILLAGE",resultClass=ReportDistwiseVillagandMappedGP.class)


public class ReportDistwiseVillagandMappedGP {
	
	@Id
	@Column(name="rid")
	private Integer rowId;

    @Column(name="subdistrict_name")
	private String subdistrictName;
    
    @Column(name="village_code")
   	private Integer villageCode;
    
    @Column(name="village_name")
   	private String villageName;
    
    @Column(name="lbtype")
   	private String lbtype;

    @Column(name="local_body_code")
   	private Integer localbodyCode;
    
    @Column(name="lb_hierarchy")
   	private String lbHierarchy;
    
    @Transient
    private Integer paramDistrictCode;
    
    @Transient
    private String captchaAnswer;
    
    @Transient
    private String entitesForMessage;
    
    

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	public String getSubdistrictName() {
		return subdistrictName;
	}

	public void setSubdistrictName(String subdistrictName) {
		this.subdistrictName = subdistrictName;
	}

	public Integer getVillageCode() {
		return villageCode;
	}

	public void setVillageCode(Integer villageCode) {
		this.villageCode = villageCode;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	
	
	public String getLbtype() {
		return lbtype;
	}

	public void setLbtype(String lbtype) {
		this.lbtype = lbtype;
	}

	public Integer getLocalbodyCode() {
		return localbodyCode;
	}

	public void setLocalbodyCode(Integer localbodyCode) {
		this.localbodyCode = localbodyCode;
	}

	

	public String getLbHierarchy() {
		return lbHierarchy;
	}

	public void setLbHierarchy(String lbHierarchy) {
		this.lbHierarchy = lbHierarchy;
	}

	public String getCaptchaAnswer() {
		return captchaAnswer;
	}

	public void setCaptchaAnswer(String captchaAnswer) {
		this.captchaAnswer = captchaAnswer;
	}

	public Integer getParamDistrictCode() {
		return paramDistrictCode;
	}

	public void setParamDistrictCode(Integer paramDistrictCode) {
		this.paramDistrictCode = paramDistrictCode;
	}

	public String getEntitesForMessage() {
		return entitesForMessage;
	}

	public void setEntitesForMessage(String entitesForMessage) {
		this.entitesForMessage = entitesForMessage;
	}

	
    
    
}
