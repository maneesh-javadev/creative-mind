package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
@NamedNativeQuery(query=" SELECT * FROM get_lb_list_by_search_text_fn(:entityName,:entityCode) ",name="getLocalGovtBodySearchDetails",resultClass=SearchLocalGovtBody.class),
@NamedNativeQuery(query=" SELECT * FROM get_lb_list_by_code_fn(:entityCodeForSearch,:entityCode) ",name="getLocalGovtBodySearchDetailsByCode",resultClass=SearchLocalGovtBody.class)
})
public class SearchLocalGovtBody {

	private char category;
	private Integer localBodyCode;
    private String localBodyNameEnglish;
    private Integer parentLocalBodyCode;
    private String parentLocalBodyNameEnglish;
    private String stateNameEnglish;
    private String localBodyTypeName;

    @Column(name="local_body_type_name")
	public String getLocalBodyTypeName() {
		return localBodyTypeName;
	}
	public void setLocalBodyTypeName(String localBodyTypeName) {
		this.localBodyTypeName = localBodyTypeName;
	}
	@Column(name="category")
	public char getCategory() {
		return category;
	}
	public void setCategory(char category) {
		this.category = category;
	}
	@Id
	@Column(name="local_body_code")
	public Integer getLocalBodyCode() {
		return localBodyCode;
	}
	public void setLocalBodyCode(Integer localBodyCode) {
		if (localBodyCode!=null){
			this.localBodyCode = localBodyCode;
		}	
	}
	@Column(name="local_body_name_english")
	public String getLocalBodyNameEnglish() {
		return localBodyNameEnglish;
	}
	public void setLocalBodyNameEnglish(String localBodyNameEnglish) {
		this.localBodyNameEnglish = localBodyNameEnglish;
	}
	@Column(name="parent_local_body_code")
	public Integer getParentLocalBodyCode() {
		return parentLocalBodyCode;
	}
	public void setParentLocalBodyCode(Integer parentLocalBodyCode) {
		if (parentLocalBodyCode!=null){
			this.parentLocalBodyCode = parentLocalBodyCode;
		}	
	}
	@Column(name="parent_local_body_name_english")
	public String getParentLocalBodyNameEnglish() {
		return parentLocalBodyNameEnglish;
	}
	public void setParentLocalBodyNameEnglish(String parentLocalBodyNameEnglish) {
		this.parentLocalBodyNameEnglish = parentLocalBodyNameEnglish;
	}
	@Column(name="state_name_english")
	public String getStateNameEnglish() {
		return stateNameEnglish;
	}
	public void setStateNameEnglish(String stateNameEnglish) {
		this.stateNameEnglish = stateNameEnglish;
	}
    
    
}
