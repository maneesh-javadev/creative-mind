package in.nic.pes.lgd.forms;

public class DesignationReportingForm {
	private int tierSetupCode;
	private String designation;
	private String reportTo;
	private String desig;
	private String report;
	private String lgTypeName;
	private String lgTypeCode;
	private int orgCode;
	private int orgParentCode;
	private int locatedAtLevel;
	private int orgTypeCode;
	
	public String getDesig() {
		return desig;
	}
	public void setDesig(String desig) {
		this.desig = desig;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public String getLgTypeName() {
		return lgTypeName;
	}
	public void setLgTypeName(String lgTypeName) {
		this.lgTypeName = lgTypeName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getReportTo() {
		return reportTo;
	}
	public void setReportTo(String reportTo) {
		this.reportTo = reportTo;
	}
	public int getTierSetupCode() {
		return tierSetupCode;
	}
	public void setTierSetupCode(int lgTypeCode) {
		this.tierSetupCode = lgTypeCode;
	}
	public String getLgTypeCode() {
		return lgTypeCode;
	}
	public void setLgTypeCode(String lgTypeCode) {
		this.lgTypeCode = lgTypeCode;
	}
	public int getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(int orgCode) {
		this.orgCode = orgCode;
	}
	public int getOrgParentCode() {
		return orgParentCode;
	}
	public void setOrgParentCode(int orgParentCode) {
		this.orgParentCode = orgParentCode;
	}
	public int getLocatedAtLevel() {
		return locatedAtLevel;
	}
	public void setLocatedAtLevel(int locatedAtLevel) {
		this.locatedAtLevel = locatedAtLevel;
	}
	public int getOrgTypeCode() {
		return orgTypeCode;
	}
	public void setOrgTypeCode(int orgTypeCode) {
		this.orgTypeCode = orgTypeCode;
	}
	
}
