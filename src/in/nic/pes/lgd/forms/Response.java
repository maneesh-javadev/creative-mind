package in.nic.pes.lgd.forms;

public class Response {
	
	private int responseCode;
	
	private String responseMessage;
	
	private String responseResult;
	
	private Object reponseObject;

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getResponseResult() {
		return responseResult;
	}

	public void setResponseResult(String responseResult) {
		this.responseResult = responseResult;
	}

	public Object getReponseObject() {
		return reponseObject;
	}

	public void setReponseObject(Object reponseObject) {
		this.reponseObject = reponseObject;
	}
	
	

}
