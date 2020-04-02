package ws.in.nic.pes.lgdws.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@Entity
@NamedNativeQueries({
@NamedNativeQuery(query = "select row_number() OVER () as row_num, lt.level,ispartixgovt,count(local_body_code) from local_body_type lt, localbody l "
						+ "where l.slc=:stateCode and l.local_body_type_code=lt.local_body_type_code and l.isactive and category='R'"
						+ " group by lt.level,ispartixgovt	"
						, name = "GET_LEVEL_WISE_RURAL_LB_COUNT", resultClass = LevelwiseofRuralLbCount.class)})

@XmlRootElement(name = "LevelwiseofRuralLbCountML")
@XmlAccessorType (XmlAccessType.FIELD)
@XmlType(propOrder={"rowNum","level","isPartixGovt","count"})
public class LevelwiseofRuralLbCount implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="row_num")
	@XmlElement(name="row_num")
	private Integer rowNum;
	
	@Column(name="level")
	@XmlElement(name="level")
	private String level;
	
	@Column(name="ispartixgovt")
	@XmlElement(name="isPartixGovt")
	private Boolean isPartixGovt;
	
	@Column(name="count")
	@XmlElement(name="count")
	private Integer count;

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Boolean getIsPartixGovt() {
		return isPartixGovt;
	}

	public void setIsPartixGovt(Boolean isPartixGovt) {
		this.isPartixGovt = isPartixGovt;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getRowNum() {
		return rowNum;
	}

	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
