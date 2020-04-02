package in.nic.pes.lgd.forms;

import in.nic.pes.lgd.bean.GazettePublication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class GovernmentOrderForm {

	private Integer orderCode;
	private String orderNo;
	private Date orderDate;
	private Date effectiveDate;
	
	private String orderNo1;
	private String orderDate1;
	private String effectiveDate1;
	private String gazPubDate1;
	private String orderNo2;
	private String orderDate2;
	private String effectiveDate2;
	private String gazPubDate2;
	private Date gazPubDate;

	private String templateList;
	private String govtOrderConfig;
	private String templateBodySrc;
	/*private int allowedNoOfAttachment;
	private long allowedFileSize;
	private String allowedFileType;
	private String location;
	private List<CommonsMultipartFile> attachedFile;
	
	private String allowedTotalFileSize;*/
	private String showTitle;
	private String requiredTitle1;
	private String maxFileLimit1;
	private String[] fileTitle1;
	private List<CommonsMultipartFile> attachFile1;
 
	private String requiredTitle;//Title Required Flag
	private String uniqueTitle;//Unique Title Required Flag
	private int allowedNoOfAttachment;//Allowed Number Of Attachment
	private long allowedTotalFileSize;//Allowed Total File Size
	private long allowedIndividualFileSize;//Allowed Individual File Size.
	private String allowedFileType;//Allowed File Type
	private String uploadLocation;//File Upload Location 
	private List<CommonsMultipartFile> attachedFile;//Attached File List
 	private Date GazettePublicationdate;
	 private List<GazettePublication> gazetteDateDetail = new ArrayList<GazettePublication>();
	 private String newAssemblyNameEnglish;
	 private String gazettePublicationCheckBox;
	 
	 //private Date gazpubDate;
	 	 
	 
	 public String getOrderNo1() {
		return orderNo1;
	}
	public void setOrderNo1(String orderNo1) {
		this.orderNo1 = orderNo1;
	}
	
	public String getOrderDate1() {
		return orderDate1;
	}
	public void setOrderDate1(String orderDate1) {
		this.orderDate1 = orderDate1;
	}
	public String getEffectiveDate1() {
		return effectiveDate1;
	}
	public void setEffectiveDate1(String effectiveDate1) {
		this.effectiveDate1 = effectiveDate1;
	}
	public String getGazPubDate1() {
		return gazPubDate1;
	}
	public void setGazPubDate1(String gazPubDate1) {
		this.gazPubDate1 = gazPubDate1;
	}
	public String getOrderNo2() {
		return orderNo2;
	}
	public void setOrderNo2(String orderNo2) {
		this.orderNo2 = orderNo2;
	}
	public String getOrderDate2() {
		return orderDate2;
	}
	public void setOrderDate2(String orderDate2) {
		this.orderDate2 = orderDate2;
	}
	public String getEffectiveDate2() {
		return effectiveDate2;
	}
	public void setEffectiveDate2(String effectiveDate2) {
		this.effectiveDate2 = effectiveDate2;
	}
	public String getGazPubDate2() {
		return gazPubDate2;
	}
	public void setGazPubDate2(String gazPubDate2) {
		this.gazPubDate2 = gazPubDate2;
	}
	public String getRequiredTitle() {
		return requiredTitle;
	}
	public void setRequiredTitle(String requiredTitle) {
		this.requiredTitle = requiredTitle;
	}
	public String getUniqueTitle() {
		return uniqueTitle;
	}
	public void setUniqueTitle(String uniqueTitle) {
		this.uniqueTitle = uniqueTitle;
	}
	public long getAllowedTotalFileSize() {
		return allowedTotalFileSize;
	}
	public void setAllowedTotalFileSize(long allowedTotalFileSize) {
		this.allowedTotalFileSize = allowedTotalFileSize;
	}
	public long getAllowedIndividualFileSize() {
		return allowedIndividualFileSize;
	}
	public void setAllowedIndividualFileSize(long allowedIndividualFileSize) {
		this.allowedIndividualFileSize = allowedIndividualFileSize;
	}
	public String getUploadLocation() {
		return uploadLocation;
	}
	public void setUploadLocation(String uploadLocation) {
		this.uploadLocation = uploadLocation;
	}
	 
	 
	 public String getShowTitle() {
			return showTitle;
		}
		public void setShowTitle(String showTitle) {
			this.showTitle = showTitle;
		}
		
	public Integer getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(Integer orderCode) {
		this.orderCode = orderCode;
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
	  public int getAllowedNoOfAttachment() {
		return allowedNoOfAttachment;
	}
	public void setAllowedNoOfAttachment(int allowedNoOfAttachment) {
		this.allowedNoOfAttachment = allowedNoOfAttachment;
	}
	
	public String getAllowedFileType() {
		return allowedFileType;
	}
	public void setAllowedFileType(String allowedFileType) {
		this.allowedFileType = allowedFileType;
	}
	
	public List<CommonsMultipartFile> getAttachedFile() {
		return attachedFile;
	}
	public void setAttachedFile(List<CommonsMultipartFile> attachedFile) {
		this.attachedFile = attachedFile;
	} 
	
	
	public String getTemplateList() {
		return templateList;
	}
	public String getRequiredTitle1() {
		return requiredTitle1;
	}
	public void setRequiredTitle1(String requiredTitle1) {
		this.requiredTitle1 = requiredTitle1;
	}
	public String getMaxFileLimit1() {
		return maxFileLimit1;
	}
	public void setMaxFileLimit1(String maxFileLimit1) {
		this.maxFileLimit1 = maxFileLimit1;
	}
	public String[] getFileTitle1() {
		return fileTitle1;
	}
	public void setFileTitle1(String[] fileTitle1) {
		this.fileTitle1 = fileTitle1;
	}
	public List<CommonsMultipartFile> getAttachFile1() {
		return attachFile1;
	}
	public void setAttachFile1(List<CommonsMultipartFile> attachFile1) {
		this.attachFile1 = attachFile1;
	}
	public void setTemplateList(String templateList) {
		this.templateList = templateList;
	}
	public String getGovtOrderConfig() {
		return govtOrderConfig;
	}
	public void setGovtOrderConfig(String govtOrderConfig) {
		this.govtOrderConfig = govtOrderConfig;
	}
	public String getTemplateBodySrc() {
		return templateBodySrc;
	}
	public void setTemplateBodySrc(String templateBodySrc) {
		this.templateBodySrc = templateBodySrc;
	}
	public List<GazettePublication> getGazetteDateDetail() {
		return gazetteDateDetail;
	}
	public void setGazetteDateDetail(List<GazettePublication> gazetteDateDetail) {
		this.gazetteDateDetail = gazetteDateDetail;
	}
	
	public String getNewAssemblyNameEnglish() {
		return newAssemblyNameEnglish;
	}
	public void setNewAssemblyNameEnglish(String newAssemblyNameEnglish) {
		this.newAssemblyNameEnglish = newAssemblyNameEnglish;
	}
	public String getGazettePublicationCheckBox() {
		return gazettePublicationCheckBox;
	}
	public void setGazettePublicationCheckBox(String gazettePublicationCheckBox) {
		this.gazettePublicationCheckBox = gazettePublicationCheckBox;
	}
	public Date getGazettePublicationdate() {
		return GazettePublicationdate;
	}
	public void setGazettePublicationdate(Date gazettePublicationdate) {
		GazettePublicationdate = gazettePublicationdate;
	}
	/*public Date getGazpubDate() {
		return gazpubDate;
	}
	public void setGazpubDate(Date gazpubDate) {
		this.gazpubDate = gazpubDate;
	}
	*/
	@Override
	public String toString() {
		return "GovernmentOrderForm [orderNo=" + orderNo + ", orderDate=" + orderDate + ", effectiveDate=" + effectiveDate + ", allowedTotalFileSize=" + allowedTotalFileSize + ", allowedIndividualFileSize=" + allowedIndividualFileSize
				+ ", allowedFileType=" + allowedFileType + ", uploadLocation=" + uploadLocation + ", attachedFile=" + attachedFile + "]";
	}
		
}