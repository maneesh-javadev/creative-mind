package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Pooja
 */
@Embeddable
public class DepartmentMappingPK implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8414347149299386906L;

	@Column(name = "mapping_id", nullable = false)
	private Long mappingId;

	@Column(name = "ward_code", nullable = false)
	private Integer wardCode;

	public DepartmentMappingPK() {
	}

	public DepartmentMappingPK(Long mappingId, Integer wardCode) {
		this.mappingId = mappingId;
		this.wardCode = wardCode;
	}
	public Long getMappingId() {
		return mappingId;
	}

	public void setMappingId(Long mappingId) {
		this.mappingId = mappingId;
	}

	public Integer getWardCode() {
		return wardCode;
	}

	public void setWardCode(Integer wardCode) {
		this.wardCode = wardCode;
	}

}