package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
@NamedNativeQuery(query = "select * from get_lb_list_by_category_fn(:categoryType)", name ="GetLBListBYCategory",resultClass=GetLBListBYCategory.class),
@NamedNativeQuery(query = "select * from get_lb_list_by_category_fn(:categoryType, :stateCode)", name ="GetLBListBYCategoryState",resultClass=GetLBListBYCategory.class),
@NamedNativeQuery(query = "select * from get_lb_list_by_category_and_time_fn(:categoryType, :fromDate, :toDate, :stateCode)", 
   name ="GetLBListBYCategoryStateTime",resultClass=GetLBListBYCategory.class),
@NamedNativeQuery(query = "select * from get_lb_list_by_category_and_time_fn(:categoryType, :fromDate, :toDate)", 
   name ="GetLBListBYCategoryTime",resultClass=GetLBListBYCategory.class)
})
public class GetLBListBYCategory {

	private Integer localBodyCode;
	private Integer localBodyTypeCode;
	private String localBodyNameEnglish;
	private String localBodyNameLocal;
	private String localBodyTypeName;
	
	@Id
	@Column(name="local_body_code")
	public Integer getLocalBodyCode() {
		return localBodyCode;
	}
	
	public void setLocalBodyCode(Integer localBodyCode) {
		this.localBodyCode = localBodyCode;
	}
	
	@Column(name="local_body_type_code")
	public Integer getLocalBodyTypeCode() {
		return localBodyTypeCode;
	}
	
	public void setLocalBodyTypeCode(Integer localBodyTypeCode) {
		this.localBodyTypeCode = localBodyTypeCode;
	}
	
	@Column(name="local_body_name_english")
	public String getLocalBodyNameEnglish() {
		return localBodyNameEnglish;
	}
	
	public void setLocalBodyNameEnglish(String localBodyNameEnglish) {
		this.localBodyNameEnglish = localBodyNameEnglish;
	}
	
	@Column(name="local_body_type_name")
	public String getLocalBodyTypeName() {
		return localBodyTypeName;
	}
	
	public void setLocalBodyTypeName(String localBodyTypeName) {
		this.localBodyTypeName = localBodyTypeName;
	}

	@Column(name="local_body_name_local")
	public String getLocalBodyNameLocal() {
		return localBodyNameLocal;
	}

	public void setLocalBodyNameLocal(String localBodyNameLocal) {
		this.localBodyNameLocal = localBodyNameLocal;
	}
	
	
}
