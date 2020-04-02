


package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
@NamedNativeQuery(query="SELECT  * from get_statewise_total_mapped_mappedpercent_villages()",name="getStatewiseGPtoVillageMapping",resultClass=StatewiseGPtoVillageMapped.class),
@NamedNativeQuery(query="SELECT  * from get_statewise_total_mapped_mappedpercent_villages_panchayats()",name="getStatewiseGPtoVillageMappingB",resultClass=StatewiseGPtoVillageMapped.class)})
public class StatewiseGPtoVillageMapped {
	 
	/**
	 * 
	 */
	/*private static final long serialVersionUID = 1L;*/
	/**
	 * 
	 */
	
	

	@Id
	@Column(name = "state_name", nullable = false)
	private String stateName;
	
	@Column(name = "total_village")
	private Long totalVillage;
	
	@Column(name = "mapped_village")
	private Long mappedVillage;
	
	@Column(name = "mapped_percent")
	private float mappedPercent;
	
	
	@Column(name = "total_inhabitant_village")
	private Long totalInhabitantVillage;
	
	@Column(name = "mapped_inhabitant_village")
	private Long mappedInhabitantVillage;
	
	@Column(name = "inhabitant_village_mapped_percent")
	private float inhabitantVillageMappedPercent;

	
	public Long getTotalVillage() {
		return totalVillage;
	}

	public void setTotalVillage(Long totalVillage) {
		this.totalVillage = totalVillage;
	}

	public Long getMappedVillage() {
		return mappedVillage;
	}

	public void setMappedVillage(Long mappedVillage) {
		this.mappedVillage = mappedVillage;
	}

	public float getMappedPercent() {
		return mappedPercent;
	}

	public void setMappedPercent(float mappedPercent) {
		this.mappedPercent = mappedPercent;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Long getTotalInhabitantVillage() {
		return totalInhabitantVillage;
	}

	public void setTotalInhabitantVillage(Long totalInhabitantVillage) {
		this.totalInhabitantVillage = totalInhabitantVillage;
	}

	public Long getMappedInhabitantVillage() {
		return mappedInhabitantVillage;
	}

	public void setMappedInhabitantVillage(Long mappedInhabitantVillage) {
		this.mappedInhabitantVillage = mappedInhabitantVillage;
	}

	public float getInhabitantVillageMappedPercent() {
		return inhabitantVillageMappedPercent;
	}

	public void setInhabitantVillageMappedPercent(float inhabitantVillageMappedPercent) {
		this.inhabitantVillageMappedPercent = inhabitantVillageMappedPercent;
	}
	
	
	
	
}

