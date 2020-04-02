package in.nic.pes.lgd.bean;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
*
* @author Arnab Sen Gupta
*/
@Embeddable
public class LocalbodyDistrictPK implements java.io.Serializable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 3786191121076163955L;
	private Integer localbodycode;
	 private Integer localbodyversion;
	 private Integer districtcode;
	 private Integer districtversion;
	 
	 @Column(name = "local_body_code", nullable = false)
	 public Integer getLocalbodycode() {
		return localbodycode;
	}
	public void setLocalbodycode(Integer localbodycode) {
		this.localbodycode = localbodycode;
	}
	 @Column(name = "local_body_version", nullable = false)
	public Integer getLocalbodyversion() {
		return localbodyversion;
	}
	public void setLocalbodyversion(Integer localbodyversion) {
		this.localbodyversion = localbodyversion;
	}
	@Column(name = "district_code", nullable = false)
	public Integer getDistrictcode() {
		return districtcode;
	}
	public void setDistrictcode(Integer districtcode) {
		this.districtcode = districtcode;
	}
	@Column(name = "district_version", nullable = false)
	public Integer getDistrictversion() {
		return districtversion;
	}
	public void setDistrictversion(Integer districtversion) {
		this.districtversion = districtversion;
	}
}
