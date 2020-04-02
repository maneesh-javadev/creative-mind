package in.nic.pes.lgd.bean;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
*
* @author Arnab Sen Gupta
*/
@Entity
@Table(name = "localbody_districts")
public class LocalbodyDistrict implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private LocalbodyDistrictPK localbodyDistrictPK;
	
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "localbodycode", column = @Column(name = "local_body_code", nullable = false)),
			@AttributeOverride(name = "localbodyversion", column = @Column(name = "local_body_version", nullable = false)),
			@AttributeOverride(name = "districtcode", column = @Column(name = "district_code", nullable = false)),
			@AttributeOverride(name = "districtversion", column = @Column(name = "district_version", nullable = false))})
	public LocalbodyDistrictPK getLocalbodyDistrictPK() {
		return localbodyDistrictPK;
	}

	public void setLocalbodyDistrictPK(LocalbodyDistrictPK localbodyDistrictPK) {
		this.localbodyDistrictPK = localbodyDistrictPK;
	}
	
}
