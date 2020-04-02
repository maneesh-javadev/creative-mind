package in.nic.pes.lgd.bean;
import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

import javax.persistence.Table;

@Entity
@NamedNativeQuery(query=" select * from manual.get_generated_file_details(8,:documentId,:documentType, 'HTML', 'F',:fName, 1)",name="getfiledetails",resultClass=GeneratedFileDetails.class)
@Table(catalog = "pes", schema = "manual")

public class GeneratedFileDetails implements Serializable{
	
private static final long serialVersionUID = 1L;
@Id
@Column(name = "file_id")
private Integer fileId;

@Column(name = "application_id")
private Integer applicationId;

@Column(name = "document_type")
private Integer documentType;

@Column(name = "document_name")
private String documentName ;

@Column(name = "format_type")
private String formatType ;

@Column(name = "file_type")
private String fileType;

@Column(name = "form_name")
private String formName;

@Column(name = "form_content_html")
private String formContenthtml;

@Column(name = "form_id")
private Integer formId;

@Column(name = "last_updated_on")
private String lastupdatedon;

public Integer getFileId() {
	return fileId;
}

public void setFileId(Integer fileId) {
	this.fileId = fileId;
}

public Integer getApplicationId() {
	return applicationId;
}

public void setApplicationId(Integer applicationId) {
	this.applicationId = applicationId;
}

public Integer getDocumentType() {
	return documentType;
}

public void setDocumentType(Integer documentType) {
	this.documentType = documentType;
}

public String getDocumentName() {
	return documentName;
}

public void setDocumentName(String documentName) {
	this.documentName = documentName;
}

public String getFormatType() {
	return formatType;
}

public void setFormatType(String formatType) {
	this.formatType = formatType;
}

public String getFileType() {
	return fileType;
}

public void setFileType(String fileType) {
	this.fileType = fileType;
}

public String getFormName() {
	return formName;
}

public void setFormName(String formName) {
	this.formName = formName;
}

public String getFormContenthtml() {
	return formContenthtml;
}

public void setFormContenthtml(String formContenthtml) {
	this.formContenthtml = formContenthtml;
}

public Integer getFormId() {
	return formId;
}

public void setFormId(Integer formId) {
	this.formId = formId;
}

public String getLastupdatedon() {
	return lastupdatedon;
}

public void setLastupdatedon(String lastupdatedon) {
	this.lastupdatedon = lastupdatedon;
}


}
