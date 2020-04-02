package in.nic.pes.lgd.bean;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Attachment generated
 */
@Entity
@Table(name = "attachment")
public class Attachment implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private long attachmentId;
	private GovernmentOrder governmentOrder;
	private String fileTitle;
	private String fileName;
	private Long fileSize;
	private String fileContentType;
	private String fileLocation;
	private String fileTimestamp;

	public Attachment() {
	}

	public Attachment(long attachmentId) { // NO_UCD (unused code)
		this.attachmentId = attachmentId;
	}

	public Attachment(long attachmentId, GovernmentOrder governmentOrder, // NO_UCD (unused code)
			String fileTitle, String fileName, Long fileSize,
			String fileContentType, String fileLocation, String fileTimestamp) {
		this.attachmentId = attachmentId;
		this.governmentOrder = governmentOrder;
		this.fileTitle = fileTitle;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.fileContentType = fileContentType;
		this.fileLocation = fileLocation;
		this.fileTimestamp = fileTimestamp;
	}

	
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "public.attachment_seq") })
	@Id
	@GeneratedValue(generator = "sequence")
	@Basic(optional = false)
	@Column(name = "attachment_id", unique = true, nullable = false)
	public long getAttachmentId() {
		return this.attachmentId;
	}

	public void setAttachmentId(long attachmentId) {
		this.attachmentId = attachmentId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "announcement_id")
	public GovernmentOrder getGovernmentOrder() {
		return this.governmentOrder;
	}

	public void setGovernmentOrder(GovernmentOrder governmentOrder) {
		this.governmentOrder = governmentOrder;
	}

	@Column(name = "file_title", length = 200)
	public String getFileTitle() {
		return this.fileTitle;
	}

	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}

	@Column(name = "file_name", length = 200)
	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "file_size")
	public Long getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	@Column(name = "file_content_type", length = 100)
	public String getFileContentType() {
		return this.fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	@Column(name = "file_location", length = 200)
	public String getFileLocation() {
		return this.fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	@Column(name = "file_timestamp", length = 200)
	public String getFileTimestamp() {
		return this.fileTimestamp;
	}

	public void setFileTimestamp(String fileTimestamp) {
		this.fileTimestamp = fileTimestamp;
	}
}