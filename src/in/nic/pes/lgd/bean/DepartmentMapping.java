package in.nic.pes.lgd.bean;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "dept_mapping")
public class DepartmentMapping {

	
	
   /* @SequenceGenerator(name="deptmapping_GENERATOR",sequenceName= "dept_mapping_id",allocationSize=1,initialValue=1 )
	@GeneratedValue(generator="deptmapping_GENERATOR",  strategy=GenerationType.SEQUENCE)*/
	
	 /*@SequenceGenerator(name="deptmapping_GENERATOR",sequenceName= "unmapped_txn_id",allocationSize=1,initialValue=1 )
	 @GeneratedValue(generator="deptmapping_GENERATOR",  strategy=GenerationType.SEQUENCE)*/
	@Id
	@GeneratedValue
	@Column(name = "mapping_id", unique = true, nullable = false)
	private Long mappingId;

	@Column(name = "state_id")
	private Integer stateId;

	@Column(name = "mapping_type")
	private String mappingType;

	@Column(name = "to_level_category")
	private String toLevelCategory;

	@Column(name = "to_level_type_code")
	private Integer toLevelTypeCode;

	@Column(name = "to_level_code")
	private Long toLevelCode;

	@Column(name = "mapping_doneby_userid")
	private Long mappingDonebyUserid;

	@Column(name = "last_updated")
	private Timestamp lastUpdated;
	
	@Column(name = "isactive") 
	private Boolean isactive;
	 

	/*
	 * @Column(name = "map_type_flg") private Character mapTypeFlag;
	 */

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "departmentMapping")
	private Set<DepartmentMappingFrom> departmentMappingFrom;

	/*
	 * List Child field ( Department localbody ward mapping )
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "departmentMapping")
	private List<DepartmentLBWardMapping> lbWardMappingList;

	public Long getMappingId() {
		return mappingId;
	}

	public void setMappingId(Long mappingId) {
		this.mappingId = mappingId;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public String getMappingType() {
		return mappingType;
	}

	public void setMappingType(String mappingType) {
		this.mappingType = mappingType;
	}

	public String getToLevelCategory() {
		return toLevelCategory;
	}

	public void setToLevelCategory(String toLevelCategory) {
		this.toLevelCategory = toLevelCategory;
	}

	public Integer getToLevelTypeCode() {
		return toLevelTypeCode;
	}

	public void setToLevelTypeCode(Integer toLevelTypeCode) {
		this.toLevelTypeCode = toLevelTypeCode;
	}

	public Long getToLevelCode() {
		return toLevelCode;
	}

	public void setToLevelCode(Long toLevelCode) {
		this.toLevelCode = toLevelCode;
	}

	public Long getMappingDonebyUserid() {
		return mappingDonebyUserid;
	}

	public void setMappingDonebyUserid(Long mappingDonebyUserid) {
		this.mappingDonebyUserid = mappingDonebyUserid;
	}

	public Timestamp getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Set<DepartmentMappingFrom> getDepartmentMappingFrom() {
		return departmentMappingFrom;
	}

	public void setDepartmentMappingFrom(Set<DepartmentMappingFrom> departmentMappingFrom) {
		this.departmentMappingFrom = departmentMappingFrom;
	}

	/*
	 * public Character getMapTypeFlag() { return mapTypeFlag; }
	 * 
	 * public void setMapTypeFlag(Character mapTypeFlag) { this.mapTypeFlag =
	 * mapTypeFlag; }
	 */

	public List<DepartmentLBWardMapping> getLbWardMappingList() {
		return lbWardMappingList;
	}

	public void setLbWardMappingList(List<DepartmentLBWardMapping> lbWardMappingList) {
		this.lbWardMappingList = lbWardMappingList;
	}

public Boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}

}
