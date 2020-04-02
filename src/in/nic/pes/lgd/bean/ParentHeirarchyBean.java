package in.nic.pes.lgd.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

/** 
 * 
 * @author Kirandeep
 * date: 29/04/2015
 * used for getting the childs of any entity in is pesa landregion
 *
 */

@Entity
@NamedNativeQuery(query=" SELECT * FROM get_heirarchy_for_pesa_forPageLoad(:entityCodes,:entityType,:afterPost)",name="getheirarchyofentity",resultClass=ParentHeirarchyBean.class)

public class ParentHeirarchyBean {
	
	@Id 
	@Column(name="specific_code")
	private Integer specificCode;
	
	@Column(name = "subdistrct_name")
	private String subDistrictName;
	
	@Column(name="type")
	private Character type;
	
	@Column(name="ispesa")
	private Character ispesa;	
	
	@Column(name="parentName")
	private String parentName;

	public Integer getSpecificCode() {
		return specificCode;
	}

	public void setSpecificCode(Integer specificCode) {
		this.specificCode = specificCode;
	}

	public String getSubDistrictName() {
		return subDistrictName;
	}

	public void setSubDistrictName(String subDistrictName) {
		this.subDistrictName = subDistrictName;
	}

	public Character getType() {
		return type;
	}

	public void setType(Character type) {
		this.type = type;
	}

	public Character getIspesa() {
		return ispesa;
	}

	public void setIspesa(Character ispesa) {
		this.ispesa = ispesa;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}	
	
}
