package in.nic.pes.lgd.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="map_attachment")
public class MapAttachment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9206832080738046034L;
	private long map_attachment_code;
	private String fileName;
	private Long fileSize;
	private String fileContentType;
	private String fileLocation;
	private String fileTimestamp;
	private char mapEntityType;
	private int map_lc;	
	
	
	public MapAttachment() {
	}

 

	
// TODO Remove unused code found by UCDetector
//     public MapAttachment(long map_attachment_code) {
// 		super();
// 		this.map_attachment_code = map_attachment_code;
// 	}




// TODO Remove unused code found by UCDetector
// 	public MapAttachment(long map_attachment_code, String fileName, Long fileSize,
// 			String fileContentType, String fileLocation, String fileTimestamp,
// 			char mapEntityType, int map_lc) {
// 		super();
// 		this.map_attachment_code = map_attachment_code;
// 		this.fileName = fileName;
// 		this.fileSize = fileSize;
// 		this.fileContentType = fileContentType;
// 		this.fileLocation = fileLocation;
// 		this.fileTimestamp = fileTimestamp;
// 		this.mapEntityType = mapEntityType;
// 		this.map_lc = map_lc;
// 	}

	

	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "public.map_seq") })
	@Id
	@GeneratedValue(generator = "sequence")
	@Column(name = "map_attachment_code", unique = true, nullable = false)
	public long getAttachmentId() {
		return this.map_attachment_code;
	}

	public void setAttachmentId(long map_attachment_code) {
		this.map_attachment_code = map_attachment_code;
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
	
	@Column(name = "map_entity_type")
	public char getMapEntityType() {
		return mapEntityType;
	}
	public void setMapEntityType(char mapEntityType) {
		this.mapEntityType = mapEntityType;
	}
	
	@Column(name = "map_lc")
	public int getMapEntityCode() {
		return map_lc;
	}
	public void setMapEntityCode(int mapEntityCode) {
		this.map_lc = mapEntityCode;
	}
	
	
	
}
