/**
 * For Department Local Body Ward Mapping.
 * @author Pooja 30-07-2015
 * 
 */

package in.nic.pes.lgd.bean;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dept_localbody_ward_mapping")
public class DepartmentLBWardMapping {
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="mapping_id", insertable=false, updatable=false)
	private DepartmentMapping departmentMapping;
	
	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "mappingId", column = @Column(name = "mapping_id", nullable = false)), @AttributeOverride(name = "wardCode", column = @Column(name = "ward_code", nullable = false)) })
	private DepartmentMappingPK departmentMappingPK;

	public DepartmentMappingPK getDepartmentMappingPK() {
		return departmentMappingPK;
	}

	public void setDepartmentMappingPK(DepartmentMappingPK departmentMappingPK) {
		this.departmentMappingPK = departmentMappingPK;
	}

	public DepartmentMapping getDepartmentMapping() {
		return departmentMapping;
	}

	public void setDepartmentMapping(DepartmentMapping departmentMapping) {
		this.departmentMapping = departmentMapping;
	}
}