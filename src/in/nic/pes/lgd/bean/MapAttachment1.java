package in.nic.pes.lgd.bean;


import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the map_attachment database table.
 * 
 */
@Entity
@Table(name="map_attachment")
public class MapAttachment1 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="map_attachment_code")
	private Long mapAttachmentCode;

	@Column(name="file_content_type")
	private String fileContentType;

	@Column(name="file_location")
	private String fileLocation;

	@Column(name="file_name")
	private String fileName;

	@Column(name="file_size")
	private Long fileSize;

	@Column(name="file_timestamp")
	private String fileTimestamp;

	@Column(name="map_entity_type")
	private String mapEntityType;

	@Column(name="map_lc")
	private Integer mapLc;

    public MapAttachment1() {
    }

	public Long getMapAttachmentCode() {
		return this.mapAttachmentCode;
	}

	public void setMapAttachmentCode(Long mapAttachmentCode) {
		this.mapAttachmentCode = mapAttachmentCode;
	}

	public String getFileContentType() {
		return this.fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileLocation() {
		return this.fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileTimestamp() {
		return this.fileTimestamp;
	}

	public void setFileTimestamp(String fileTimestamp) {
		this.fileTimestamp = fileTimestamp;
	}

	public String getMapEntityType() {
		return this.mapEntityType;
	}

	public void setMapEntityType(String mapEntityType) {
		this.mapEntityType = mapEntityType;
	}

	public Integer getMapLc() {
		return this.mapLc;
	}

	public void setMapLc(Integer mapLc) {
		this.mapLc = mapLc;
	}

}