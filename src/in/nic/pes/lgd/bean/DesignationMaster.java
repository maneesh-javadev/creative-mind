package in.nic.pes.lgd.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "designation_master")
public class DesignationMaster implements Serializable{

	private static final long serialVersionUID = 1L;

	@GenericGenerator
  	(name = "sequence", strategy = "sequence", parameters={@Parameter(name="sequence",value="map_designation_master")})
	@Id
	@GeneratedValue(generator = "sequence")
	@Column(name = "designation_code")
    private Integer designationCode;
	
	@Column(name = "designation_name")
	private String designationName;
	
	public Integer getDesignationCode() {
		return designationCode;
	}

	public void setDesignationCode(Integer designationCode) {
		this.designationCode = designationCode;
	}

	public String getDesignationName() {
		return designationName;
	}

	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}

}
