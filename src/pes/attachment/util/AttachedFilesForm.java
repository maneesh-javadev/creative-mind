package pes.attachment.util;

import java.util.List;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class AttachedFilesForm
{
  private String fileTitle;
  private String fileName;
  private long fileSize;
  private String fileType;
  private String fileLocation;
  private String uploadLocation;
  private List<CommonsMultipartFile> attachedFile;
  private String fileTimestampName;
  private String[] titleList;
  
  public AttachedFilesForm() {}
  
  public String getFileName()
  {
    return fileName;
  }
  
  public String[] getTitleList() {
    return titleList;
  }
  
  public void setTitleList(String[] titleList) {
    this.titleList = titleList;
  }
  
  public String getFileTimestampName() {
    return fileTimestampName;
  }
  
  public void setFileTimestampName(String fileTimestampName) {
    this.fileTimestampName = fileTimestampName;
  }
  
  public String getFileTitle() {
    return fileTitle;
  }
  
  public void setFileTitle(String fileTitle) {
    this.fileTitle = fileTitle;
  }
  
  public void setFileName(String fileName) {
    this.fileName = fileName;
  }
  
  public long getFileSize() {
    return fileSize;
  }
  
  public void setFileSize(long fileSize) {
    this.fileSize = fileSize;
  }
  
  public String getFileType() {
    return fileType;
  }
  
  public void setFileType(String fileType) {
    this.fileType = fileType;
  }
  
  public String getFileLocation() {
    return fileLocation;
  }
  
  public void setFileLocation(String fileLocation) {
    this.fileLocation = fileLocation;
  }
  
  public List<CommonsMultipartFile> getAttachedFile() {
    return attachedFile;
  }
  
  public void setAttachedFile(List<CommonsMultipartFile> attachedFile) {
    this.attachedFile = attachedFile;
  }
  
  public String getUploadLocation() {
    return uploadLocation;
  }
  
  public void setUploadLocation(String uploadLocation) {
    this.uploadLocation = uploadLocation;
  }
}