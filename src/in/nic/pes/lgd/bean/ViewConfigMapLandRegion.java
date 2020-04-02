package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query = "select * from get_statewise_map_configuration(:stateCode)", name ="getConfigMapdetail",resultClass=ViewConfigMapLandRegion.class)
public class ViewConfigMapLandRegion {

	@Id
	@Column(name ="landregion_type")
	private char landregiontype;
	@Column(name ="configuration_map_landregion_code")
	private Integer configurationMapLandregionCode;
	@Column(name = "ismapupload")
	private Boolean ismapupload;
	@Column(name = "base_url")
	 private String isbaseUrl;
	@Column(name = "id")
	private Integer Id;

	
	public Integer getConfigurationMapLandregionCode() {
		return configurationMapLandregionCode;
	}
	public void setConfigurationMapLandregionCode(
			Integer configurationMapLandregionCode) {
		this.configurationMapLandregionCode = configurationMapLandregionCode;
	}
	public Boolean getIsmapupload() {
		return ismapupload;
	}
	public void setIsmapupload(Boolean ismapupload) {
		this.ismapupload = ismapupload;
	}

	public String getIsbaseUrl() {
		return isbaseUrl;
	}
	public void setIsbaseUrl(String isbaseUrl) {
		this.isbaseUrl = isbaseUrl;
	}
	public char getLandregiontype() {
		return landregiontype;
	}
	public void setLandregiontype(char landregiontype) {
		this.landregiontype = landregiontype;
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	
}
