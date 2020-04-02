package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;


//
@Entity
@NamedNativeQuery(query="SELECT * FROM get_parentwise_lb_list_without_child_count(:localBodyCode)",name="getLocalGovtBodyParentWise",resultClass=ParentWiseLBList.class)
public class ParentWiseLBList{
	/**
	 * 
	 */

    private String localBodyNameEng;
    private String localBodyNameLocal;
    private String localBodyCode;
    //private String nomenclatureLocal;
    
    @Column(name="local_body_name_english")
    public String getLocalBodyNameEng() {
		return localBodyNameEng;
	}
	public void setLocalBodyNameEng(String localBodyNameEng) {
		this.localBodyNameEng = localBodyNameEng;
	}
	
	/*@Column(name="nomenclature_local")
	public String getNomenclatureLocal() {
		return nomenclatureLocal;
	}
	public void setNomenclatureLocal(String nomenclatureLocal) {
		this.nomenclatureLocal = nomenclatureLocal;
	}*/
	@Id
    @Column(name="local_body_code")
    public String getLocalBodyCode() {
		return localBodyCode;
	}
	public void setLocalBodyCode(String localBodyCode) {
		this.localBodyCode = localBodyCode;
	}
	
	  @Column(name="local_body_name_local")
	public String getLocalBodyNameLocal() {
		return localBodyNameLocal;
	}
	public void setLocalBodyNameLocal(String localBodyNameLocal) {
		this.localBodyNameLocal = localBodyNameLocal;
	}
	

}
