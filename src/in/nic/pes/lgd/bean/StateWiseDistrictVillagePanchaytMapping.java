package in.nic.pes.lgd.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query="SELECT  * from get_state_districtpanchayatiwse_mapunmap_villlage_rpt(:entity_code)",name="DistrictVillagePanchaytMapping",resultClass=StateWiseDistrictVillagePanchaytMapping.class)

public class StateWiseDistrictVillagePanchaytMapping {
	 
	/**
	 * 
	 */
	/*private static final long serialVersionUID = 1L;*/
	/**
	 * 
	 */
	
	
     /**
      * database function 'get_state_districtpanchayatiwse_mapunmap_villlage_rpt 'not return local_body_code in return type so hide this coloumn from Pojo by Maneesh Kumar 26June2015
      */
	/*@Id
	@Column(name="local_body_code")
	private Integer lbCode;*/ 
	
	@Id
	@Column(name = "local_body_name", nullable = false)
	private String localbodyname;
	
	@Column(name = "total_gp")
	private Long totalgrampan;
	
	@Column(name = "total_village")
	private Long toalVillage;
	
	@Column(name = "total_mapped_village")
	private Long mappedVillage;
	
	@Column(name = "mapped_percent")
	private float mappedPercent;

	

	public String getLocalbodyname() {
		return localbodyname;
	}

	public void setLocalbodyname(String localbodyname) {
		this.localbodyname = localbodyname;
	}

	public Long getTotalgrampan() {
		return totalgrampan;
	}

	public void setTotalgrampan(Long totalgrampan) {
		this.totalgrampan = totalgrampan;
	}

	public Long getToalVillage() {
		return toalVillage;
	}

	public void setToalVillage(Long toalVillage) {
		this.toalVillage = toalVillage;
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

	public void setMappedPercent(Long mappedPercent) {
		this.mappedPercent = mappedPercent;
	}

	/*public Integer getLbCode() {
		return lbCode;
	}

	public void setLbCode(Integer lbCode) {
		this.lbCode = lbCode;
	}*/

	
	
	
	
}

