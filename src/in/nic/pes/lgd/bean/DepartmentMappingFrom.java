package in.nic.pes.lgd.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "dept_mapping_from")
public class DepartmentMappingFrom {


	/*@SequenceGenerator(name="deptmappingfrom_GENERATOR",sequenceName="mapped_txn_id",allocationSize=1,initialValue=1 )
	@GeneratedValue(generator="deptmappingfrom_GENERATOR",  strategy=GenerationType.SEQUENCE)*/
	@Id
	@Column(name = "mapping_from_id", unique = true, nullable = false)
	private Long mappingFromId;
	
	@Column(name = "from_level_category")
	private String fromLevelCategory;
	
	@Column(name = "from_level_type_code")
	private Integer fromLevelTypeCode;
	
	@Column(name = "from_level_code")
	private Long fromLevelCode;
	
	@Column(name = "mapped_on")
	private Timestamp mappedOn;

	@Column(name = "mapped_by")
	private Long mappedBy;

	@Column(name = "unmapped_on")
	private Timestamp unmappedOn;

	@Column(name = "unmapped_by")
	private Long unmappedBy;

	@Column(name = "isactive")
	private Boolean isactive;
	
	@Column(name = "mapped_txn_id")
	private Integer mappedTxnId;
	
	@Column(name = "unmapped_txn_id")
	private Integer unMappedTxnId;
	
	
	
	@ManyToOne(fetch =FetchType.LAZY)
	@JoinColumn(name = "mapping_id", nullable = false )
	private DepartmentMapping departmentMapping;

	public Long getMappingFromId() {
		return mappingFromId;
	}

	public void setMappingFromId(Long mappingFromId) {
		this.mappingFromId = mappingFromId;
	}

	public String getFromLevelCategory() {
		return fromLevelCategory;
	}

	public void setFromLevelCategory(String fromLevelCategory) {
		this.fromLevelCategory = fromLevelCategory;
	}

	public Integer getFromLevelTypeCode() {
		return fromLevelTypeCode;
	}

	public void setFromLevelTypeCode(Integer fromLevelTypeCode) {
		this.fromLevelTypeCode = fromLevelTypeCode;
	}

	public Long getFromLevelCode() {
		return fromLevelCode;
	}

	public void setFromLevelCode(Long fromLevelCode) {
		this.fromLevelCode = fromLevelCode;
	}


	public DepartmentMapping getDepartmentMapping() {
		return departmentMapping;
	}

	public void setDepartmentMapping(DepartmentMapping departmentMapping) {
		this.departmentMapping = departmentMapping;
	}
	
	public Timestamp getMappedOn() {
		return mappedOn;
	}

	public void setMappedOn(Timestamp mappedOn) {
		this.mappedOn = mappedOn;
	}

	public Long getMappedBy() {
		return mappedBy;
	}

	public void setMappedBy(Long mappedBy) {
		this.mappedBy = mappedBy;
	}

	public Timestamp getUnmappedOn() {
		return unmappedOn;
	}

	public void setUnmappedOn(Timestamp unmappedOn) {
		this.unmappedOn = unmappedOn;
	}

	public Long getUnmappedBy() {
		return unmappedBy;
	}

	public void setUnmappedBy(Long unmappedBy) {
		this.unmappedBy = unmappedBy;
	}

	public Boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}

	public Integer getMappedTxnId() {
		return mappedTxnId;
	}

	public void setMappedTxnId(Integer mappedTxnId) {
		this.mappedTxnId = mappedTxnId;
	}

	public Integer getUnMappedTxnId() {
		return unMappedTxnId;
	}

	public void setUnMappedTxnId(Integer unMappedTxnId) {
		this.unMappedTxnId = unMappedTxnId;
	}
	
	
}
