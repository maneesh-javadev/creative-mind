package in.nic.pes.lgd.forms;

public class ExceptionalReportsForm {
	
	private String fileName;
	
	private String reportName;
	
	private String captchaAnswer;
	
	private Integer slc;

	public int getSlc() {
		return slc;
	}

	public void setSlc(int slc) {
		this.slc = slc;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getCaptchaAnswer() {
		return captchaAnswer;
	}

	public void setCaptchaAnswer(String captchaAnswer) {
		this.captchaAnswer = captchaAnswer;
	}
	
	

}
