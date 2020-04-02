package in.nic.pes.lgd.response;

public class Response{

	private int responseCode;
	
	private String responseMessage;
	
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

	public Object getReponseObject() {
		return reponseObject;
	}

	public void setReponseObject(Object reponseObject) {
		this.reponseObject = reponseObject;
	}
	
	

}
