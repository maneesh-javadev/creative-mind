package in.nic.pes.lgd.forms;

public class GenerateDetails 
{
	private long attachmentId;
	//private GovernmentOrder governmentOrder;
	private String fileTitle;
	private String fileName;
	private Long fileSize;
	private String fileContentType;
	private String fileLocation;
	private String fileTimestamp;
	//private long announcement_id;
	public long getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(long attachmentId) {
		this.attachmentId = attachmentId;
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
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
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
