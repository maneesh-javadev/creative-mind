package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
@NamedNativeQuery(query = "select * from get_statewise_lb_map_config(:stateCode) where category='P'", name ="getConfigMapdetailforPLB",resultClass=ViewConfigMapLocalBody.class),
@NamedNativeQuery(query = "select * from get_statewise_lb_map_config(:stateCode) where category='T'", name ="getConfigMapdetailforTLB",resultClass=ViewConfigMapLocalBody.class),
@NamedNativeQuery(query = "select * from get_statewise_lb_map_config(:stateCode) where category='U'", name ="getConfigMapdetailforULB",resultClass=ViewConfigMapLocalBody.class)
})
public class ViewConfigMapLocalBody {

	@Id
	@Column(name = "id")
	private Integer Id;
	
	@Column(name ="configuration_map_localbody_code")
	private Integer configurationMapLandregionCode;
	
	@Column(name = "ismapupload")
	private Boolean ismapupload;
	
	@Column(name = "base_url")
	 private String isbaseUrl;
	
	
	@Column(name = "category")
	 private String category;	
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
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
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	
}
