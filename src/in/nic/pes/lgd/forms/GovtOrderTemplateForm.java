package in.nic.pes.lgd.forms;

import org.hibernate.validator.constraints.NotEmpty;


public class GovtOrderTemplateForm {
	
	private String  templateBodySrc;
	@NotEmpty(message = "Please enter template name")
//	@Pattern(regexp="([1-9][0-9]*)$", message="Please Select Sub-District")
	private String templateNameEnglish;
	@NotEmpty(message = "Please Select operation")
//	@Pattern(regexp="([1-9][0-9]*)$", message="Please Select Sub-District")
	private String operations;
	@NotEmpty(message = "Please Select operation name")
//	@Pattern(regexp="([1-9][0-9]*)$", message="Please Select Sub-District")
	private String operationName;
	@NotEmpty(message = "Please Select Sub-District")
//	@Pattern(regexp="([1-9][0-9]*)$", message="Please Select Sub-District")
	private String orderType;
	@NotEmpty(message = "Please Select Template Type")
//	@Pattern(regexp="([1-9][0-9]*)$", message="Please Select Sub-District")
	private String templateLanguage;
	private int templateCode;
	private char category;
	//Code added by Arnab    Start
	
	public char getCategory() {
		return category;
	}

	public void setCategory(char category) {
		this.category = category;
	}

	private Integer templateId;
	
	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
	//Code added by Arnab   End
	
	/**
	 * @return the templateNameEnglish
	 */
	public String getTemplateNameEnglish() {
		return templateNameEnglish;
	}

	/**
	 * @param templateNameEnglish the templateNameEnglish to set
	 */
	public void setTemplateNameEnglish(String templateNameEnglish) {
		this.templateNameEnglish = templateNameEnglish;
	}

	/**
	 * @return the templateBodySrc
	 */
	public String getTemplateBodySrc() {
		return templateBodySrc;
	}

	/**
	 * @param templateBodySrc the templateBodySrc to set
	 */
	public void setTemplateBodySrc(String templateBodySrc) {
		this.templateBodySrc = templateBodySrc;
	}

	/**
	 * @return the operations
	 */
	public String getOperations() {
		return operations;
	}

	/**
	 * @param operations the operations to set
	 */
	public void setOperations(String operations) {
		this.operations = operations;
	}

	/**
	 * @return the operationName
	 */
	public String getOperationName() {
		return operationName;
	}

	/**
	 * @param operationName the operationName to set
	 */
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	/**
	 * @return the orderType
	 */
	public String getOrderType() {
		return orderType;
	}

	/**
	 * @param orderType the orderType to set
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	/**
	 * @return the templateLanguage
	 */
	public String getTemplateLanguage() {
		return templateLanguage;
	}

	/**
	 * @param templateLanguage the templateLanguage to set
	 */
	public void setTemplateLanguage(String templateLanguage) {
		this.templateLanguage = templateLanguage;
	}

	/**
	 * @return the templateCode
	 */
	public int getTemplateCode() {
		return templateCode;
	}

	/**
	 * @param templateCode the templateCode to set
	 */
	public void setTemplateCode(int templateCode) {
		this.templateCode = templateCode;
	}	


}
