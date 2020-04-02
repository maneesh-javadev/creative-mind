package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
@NamedNativeQuery(query=" SELECT * FROM get_local_body_types_by_category(:category) where UPPER(TRIM(level)) LIKE 'D' order by local_body_type_name",name="getlbtypebyCategory",resultClass=GetLocalBodyTypeCode.class),
@NamedNativeQuery(query=" SELECT * FROM get_local_body_types_by_category(:category) where UPPER(TRIM(level)) LIKE 'I' order by local_body_type_name",name="getlbtypebyCategoryforInter",resultClass=GetLocalBodyTypeCode.class),
@NamedNativeQuery(query=" SELECT * FROM get_local_body_types_by_category(:category) where UPPER(TRIM(level)) LIKE 'V' order by local_body_type_name",name="getlbtypebyCategoryforVillage",resultClass=GetLocalBodyTypeCode.class),
@NamedNativeQuery(query=" SELECT * FROM get_local_body_types_by_category(:category) order by local_body_type_name",name="getUrbanlbtypebyCategory",resultClass=GetLocalBodyTypeCode.class)
})

public class GetLocalBodyTypeCode {

	
	private Integer localBodyTypeCode ;
	private String localBodyTypeName ;
	private char level;
	private String typeCode ;

	@Id
	@Column(name="local_body_type_code")
	public Integer getLocalBodyTypeCode() {
		return localBodyTypeCode;
	}

	public void setLocalBodyTypeCode(Integer localBodyTypeCode) {
		this.localBodyTypeCode = localBodyTypeCode;
	}

	@Column(name="local_body_type_name")
	public String getLocalBodyTypeName() {
		return localBodyTypeName;
	}

	public void setLocalBodyTypeName(String localBodyTypeName) {
		this.localBodyTypeName = localBodyTypeName;
	}

	@Column(name="level")
	public char getLevel() {
		return level;
	}

	public void setLevel(char level) {
		this.level = level;
	}
	
	@Column(name="type_code")
	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	

	
	
	
}
