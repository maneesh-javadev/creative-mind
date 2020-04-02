package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
@NamedNativeQuery(query = "SELECT * FROM  get_subtype_list_by_typecode_statecode(:stateCode,:localBodyTypeCode)", name = "getsubtypelist", resultClass = GetSubTypeList.class)
})


public class GetSubTypeList {

	private Integer local_body_subtype_code;
	private String subtype_name_eng;

	@Id
	@Column(name = "local_body_subtype_code")
	public Integer getLocal_body_subtype_code() {
		return local_body_subtype_code;
	}

	public void setLocal_body_subtype_code(Integer local_body_subtype_code) {
		this.local_body_subtype_code = local_body_subtype_code;
	}

	@Column(name = "subtype_name_eng")
	public String getSubtype_name_eng() {
		return subtype_name_eng;
	}

	public void setSubtype_name_eng(String subtype_name_eng) {
		this.subtype_name_eng = subtype_name_eng;
	}

}
