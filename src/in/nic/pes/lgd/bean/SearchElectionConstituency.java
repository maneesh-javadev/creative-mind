package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
@NamedNativeQuery(query=" SELECT * FROM get_constituencies_list_by_search_text_fn(:entityName,:entityCode) ",name="getElectionConstituencySearchDetails",resultClass=SearchElectionConstituency.class),
@NamedNativeQuery(query=" SELECT * FROM get_constituencies_list_by_code_fn(:entityCodeForSearch,:entityCode) ",name="getElectionConstituencySearchDetailsByCode",resultClass=SearchElectionConstituency.class)
})
public class SearchElectionConstituency {

	private Integer id;
	private char type;
	private String acNameEnglish;
	private Integer acCode;
	private String stateNameEnglish;
	private Integer stateCode;
	private String pcNameEnglish;
	private Integer pcCode;
	
	@Id
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		if (id!=null){
			this.id = id;
		}	
	}
	@Column(name="type")
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	@Column(name="ac_name_english")
	public String getAcNameEnglish() {
		return acNameEnglish;
	}
	public void setAcNameEnglish(String acNameEnglish) {
		this.acNameEnglish = acNameEnglish;
	}
	@Column(name="ac_code")
	public Integer getAcCode() {
		return acCode;
	}
	public void setAcCode(Integer acCode) {
		if (acCode!=null){
			this.acCode = acCode;
		}	
	}
	@Column(name="state_name_english")
	public String getStateNameEnglish() {
		return stateNameEnglish;
	}
	public void setStateNameEnglish(String stateNameEnglish) {
		this.stateNameEnglish = stateNameEnglish;
	}
	@Column(name="state_code")
	public Integer getStateCode() {
		return stateCode;
	}
	public void setStateCode(Integer stateCode) {
		if (stateCode!=null){
			this.stateCode = stateCode;
		}	
	}
	@Column(name="pc_name_english")
	public String getPcNameEnglish() {
		return pcNameEnglish;
	}
	public void setPcNameEnglish(String pcNameEnglish) {
		this.pcNameEnglish = pcNameEnglish;
	}
	@Column(name="pc_code")
	public Integer getPcCode() {
		return pcCode;
	}
	public void setPcCode(Integer pcCode) {
		if (acCode!=null){
			this.pcCode = pcCode;
		}	
	}
	
}
