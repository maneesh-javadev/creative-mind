/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.nic.pes.lgd.bean;

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
 *
 * @author HCL
 */
@Entity
@Table(name = "configuration_map_landregion_level", schema = "public")
public class ConfigurationMapLandregionLevel implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private ConfigurationMapLandregion configurationMapLandregion;
	private char landregionType;
	private boolean ismapupload;
	private String baseUrl;
	private boolean isactive;
   
	    
	
	public ConfigurationMapLandregionLevel() {
	}

// TODO Remove unused code found by UCDetector
// 	public ConfigurationMapLandregionLevel(int id,
// 			ConfigurationMapLandregion configurationMapLandregion,
// 			char landregionType, boolean ismapupload, boolean isactive) {
// 		this.id = id;
// 		this.configurationMapLandregion = configurationMapLandregion;
// 		this.landregionType = landregionType;
// 		this.ismapupload = ismapupload;
// 		this.isactive = isactive;
// 	}

// TODO Remove unused code found by UCDetector
// 	public ConfigurationMapLandregionLevel(int id,
// 			ConfigurationMapLandregion configurationMapLandregion,
// 			char landregionType, boolean ismapupload, String baseUrl,
// 			boolean isactive) {
// 		this.id = id;
// 		this.configurationMapLandregion = configurationMapLandregion;
// 		this.landregionType = landregionType;
// 		this.ismapupload = ismapupload;
// 		this.baseUrl = baseUrl;
// 		this.isactive = isactive;
// 	}
	@GenericGenerator
    (name = "sequence", strategy = "sequence", parameters={@Parameter(name="sequence",value="configuremaplevel")})
	@GeneratedValue(generator = "sequence")
 	@Id
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "configuration_map_landregion_code", nullable = false)
	public ConfigurationMapLandregion getConfigurationMapLandregion() {
		return this.configurationMapLandregion;
	}

	public void setConfigurationMapLandregion(
			ConfigurationMapLandregion configurationMapLandregion) {
		this.configurationMapLandregion = configurationMapLandregion;
	}

	@Column(name = "landregion_type", nullable = false, length = 1)
	public char getLandregionType() {
		return this.landregionType;
	}

	public void setLandregionType(char landregionType) {
		this.landregionType = landregionType;
	}

	@Column(name = "ismapupload", nullable = false)
	public boolean isIsmapupload() {
		return this.ismapupload;
	}

	public void setIsmapupload(boolean ismapupload) {
		this.ismapupload = ismapupload;
	}

	@Column(name = "base_url", length = 100)
	public String getBaseUrl() {
		return this.baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	@Column(name = "isactive", nullable = false)
	public boolean isIsactive() {
		return this.isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}



}
