package ws.in.nic.pes.lgdws.webservice;

public class LGDWebServiceForm { // NO_UCD (use default)
	
	private String outputFormat;
	
	private String entityCode;
	
	private String entityType;
	
	private String outputType;
	
	private String lbTypeCode;
	
	public String getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	private String authKey;
	
	public String getOutputFormat() {
		return outputFormat;
	}

	public void setOutputFormat(String outputFormat) {
		this.outputFormat = outputFormat;
	}


	public String getAuthKey() {
		return authKey;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

	public String getOutputType() {
		return outputType;
	}

	public void setOutputType(String outputType) {
		this.outputType = outputType;
	}

	public String getLbTypeCode() {
		return lbTypeCode;
	}

	public void setLbTypeCode(String lbTypeCode) {
		this.lbTypeCode = lbTypeCode;
	}

	

	

}
