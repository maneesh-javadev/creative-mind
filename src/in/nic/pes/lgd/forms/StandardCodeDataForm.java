package in.nic.pes.lgd.forms;


public class StandardCodeDataForm {
 
	private int entityCode;
	private String entityNameEnglish;
	private String entityNameLocal;
	private String entityNameLocalch;
	private String census2011Code;
	private String census2011Codech;
	private String ssCode="";
	private String ssCodech="";
	
	public int getEntityCode() {
		return entityCode;
	}
	public void setEntityCode(int entityCode) {
		this.entityCode = entityCode;
	}
	public String getEntityNameEnglish() {
		return entityNameEnglish;
	}
	public void setEntityNameEnglish(String entityNameEnglish) {
		this.entityNameEnglish = entityNameEnglish;
	}
	public String getEntityNameLocal() {
		return entityNameLocal;
	}
	public void setEntityNameLocal(String entityNameLocal) {
		this.entityNameLocal = entityNameLocal;
	}
	public String getEntityNameLocalch() {
		return entityNameLocalch;
	}
	public void setEntityNameLocalch(String entityNameLocalch) {
		this.entityNameLocalch = entityNameLocalch;
	}
	public String getCensus2011Code() {
		return census2011Code;
	}
	public void setCensus2011Code(String census2011Code) {
		this.census2011Code = census2011Code;
	}
	public String getCensus2011Codech() {
		return census2011Codech;
	}
	public void setCensus2011Codech(String census2011Codech) {
		this.census2011Codech = census2011Codech;
	}
	public String getSsCode() {
		return ssCode;
	}
	public void setSsCode(String ssCode) {
		if(ssCode==null)
			ssCode="";
		this.ssCode = ssCode;
	}
	public String getSsCodech() {
		return ssCodech;
	}
	public void setSsCodech(String ssCodech) {
		if(ssCodech==null)
			ssCodech="";
		this.ssCodech = ssCodech;
	}
	
	
}
