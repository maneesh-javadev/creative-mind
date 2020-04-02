package in.nic.pes.lgd.draft.form;

import java.util.Date;

public class LocalBodyDetailDto {


	
	private Integer localBodyCode;
	
	
	private String fileTitle;
	
	
	private String fileName;
	
	
	
	private String fileContentType;

		private Integer fileSize;

	
	private String fileLocation;
	
	private String fileTimestamp;
	
	private Integer getPreviousAttachedFiles;

    private Date orderDate;
	
	private Date gazPubDate;
	
	private String orderNo ;

	

	
	

	public Date getOrderDate()
	{
		return orderDate;
	}

	public void setOrderDate(Date orderDate)
	{
		this.orderDate = orderDate;
	}

	public Date getGazPubDate()
	{
		return gazPubDate;
	}

	public void setGazPubDate(Date gazPubDate)
	{
		this.gazPubDate = gazPubDate;
	}


	public String getOrderNo()
	{
		return orderNo;
	}

	public void setOrderNo(String orderNo)
	{
		this.orderNo = orderNo;
	}

	public Integer getGetPreviousAttachedFiles()
	{
		return getPreviousAttachedFiles;
	}

	public void setGetPreviousAttachedFiles(Integer getPreviousAttachedFiles)
	{
		this.getPreviousAttachedFiles = getPreviousAttachedFiles;
	}

	public Integer getLocalBodyCode() {
		return localBodyCode;
	}

	public void setLocalBodyCode(Integer localBodyCode) {
		this.localBodyCode = localBodyCode;
	}

	public String getFileTitle() {
		return fileTitle;
	}

	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public Integer getFileSize() {
		return fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	public String getFileTimestamp() {
		return fileTimestamp;
	}

	public void setFileTimestamp(String fileTimestamp) {
		this.fileTimestamp = fileTimestamp;
	}

	 
}
