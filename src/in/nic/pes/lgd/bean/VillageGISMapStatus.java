package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;



@Entity
@NamedNativeQueries({
@NamedNativeQuery(query="select * from get_contrtibuting_landregion(:entityCode,:entityType) as result_value",name="GET_CONTRIBUTING_LANDREGION",resultClass=VillageGISMapStatus.class),
@NamedNativeQuery(query="select * from get_tree_progress_details(:stateCode,:parentHierarchy) as result_value",name="GET_LEVELWISE_TREE_DETAILS",resultClass=VillageGISMapStatus.class),
@NamedNativeQuery(query="Select * from insert_village_gismap_status(:villageCode) as result_value",name="INSERT_VILLAGE_GISMAP_STATUS",resultClass=VillageGISMapStatus.class)
})
public class VillageGISMapStatus {
	
	@Id 
	@Column(name = "result_value", nullable = false)
	private String  message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	

	
	
	

}
