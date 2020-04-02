package  ws.in.nic.pes.lgdws.webservice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



@Entity
@Table(name="villageMappedPercent" ,schema="lgd")
@XmlRootElement(name="entityMappedPercent" ,namespace="xmlnsi")
@XmlAccessorType
@XmlType(propOrder={"stateName","mappedPercent"})
@NamedNativeQueries({
			@NamedNativeQuery(query="SELECT  * from get_statewise_total_mapped_mappedpercent_villages(:stateCode,:LBGroup)",name="getStatewiseGPVillageMapping",resultClass=StateWiseGPVillageMappedEntity.class),
			@NamedNativeQuery(name="getstateBlocWiseMapped", query=  "SELECT * from percent_of_village_mapped_to_block_as_on_31march_fn(:stateCode ,:finYear)",resultClass=StateWiseGPVillageMappedEntity.class),
			@NamedNativeQuery(name="percentCensusVillage", query=  "SELECT * from percent_of_census_village(:stateCode ,:finYear)",resultClass=StateWiseGPVillageMappedEntity.class) ,
			@NamedNativeQuery(name="percentGPIntegratedwithGIS", query=  "SELECT * from percent_of_lb_using_gis_tool(:stateCode)",resultClass=StateWiseGPVillageMappedEntity.class) 
			
})

public class StateWiseGPVillageMappedEntity { // NO_UCD (use default)
	 
	@Id
	@Column(name = "state_name", nullable = false)
	private String stateName;
	
	@Column(name = "mapped_percent")
	private float mappedPercent;

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public float getMappedPercent() {
		return mappedPercent;
	}

	public void setMappedPercent(float mappedPercent) {
		this.mappedPercent = mappedPercent;
	}

	
	
	
}
