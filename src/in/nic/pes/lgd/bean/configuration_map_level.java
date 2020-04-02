package in.nic.pes.lgd.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "configuration_map_level")
public class configuration_map_level implements Serializable { // NO_UCD (unused code)

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "ismapupload")
	private Boolean ismapupload;
	@Column(name = "isbase_url")
    private Boolean isbaseUrl;
	public Boolean getIsmapupload() {
		return ismapupload;
	}
	public void setIsmapupload(Boolean ismapupload) {
		this.ismapupload = ismapupload;
	}
	public Boolean getIsbaseUrl() {
		return isbaseUrl;
	}
	public void setIsbaseUrl(Boolean isbaseUrl) {
		this.isbaseUrl = isbaseUrl;
	}
	
}
