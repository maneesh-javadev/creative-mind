package in.nic.pes.lgd.draft.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="goverment_detail_draft") 
public class GovermentDetailDraft {
	
	@Id
	@Column(name="group_id")
	@SequenceGenerator(name = "group_id_seq_generator", initialValue=1, allocationSize=1,  sequenceName = "goverment_detail_draft_group_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_id_seq_generator")
	private Integer groupId;
	
	@Column(name="entity_type")
	private Character entityType;
	
	
	@Column(name="order_no")
	private String orderNo;
	
	@Column(name="order_date")
	private Date orderDate;
	
	@Column(name="effective_date")
	private Date effectiveDate;
	
	@Column(name="gaz_pub_date")
	private Date gazPubDate;
	
	@Column(name="user_id")
	private long userId;
	
	@Column(name="file_name")
	private String fileName;
	
	@Column(name="file_size")
	private Long fileSize;
	
	@Column(name="file_content_type")
	private String fileContentType;
	
	@Column(name="file_location")
	private String fileLocation;
	
	@Column(name="file_timestamp")
	private String fileTimestamp;
	
	@Column(name="template_id")
	private Integer templateId;
	
	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Character getEntityType() {
		return entityType;
	}

	public void setEntityType(Character entityType) {
		this.entityType = entityType;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getGazPubDate() {
		return gazPubDate;
	}

	public void setGazPubDate(Date gazPubDate) {
		this.gazPubDate = gazPubDate;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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
