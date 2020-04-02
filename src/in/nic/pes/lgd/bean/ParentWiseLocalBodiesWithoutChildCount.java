package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;


//
@Entity
@NamedNativeQuery(query="SELECT case when lb.local_body_code  in (select * from get_draft_used_lb_lr_temp(lb.local_body_code,'G')) then  cast ('F' as character) else cast('A' as character) end as operation_state,* FROM get_parentwise_lb_list_without_child_count(:localBodyCode) lb",name="getLocalGovtBodyforParentWithoutChild",resultClass=ParentWiseLocalBodiesWithoutChildCount.class)
public class ParentWiseLocalBodiesWithoutChildCount{
	/**
	 * 
	 */

    private String localBodyNameEnglish;
    private String localBodyNameLocal;
    private String localBodyCode;
    
    /*added on 30/12/2014 by kirandeep for localbody impact*/
    private Character operation_state;

    @Id
    @Column(name="local_body_code")
    public String getLocalBodyCode() {
		return localBodyCode;
	}
	public void setLocalBodyCode(String localBodyCode) {
		this.localBodyCode = localBodyCode;
	}
    
      
    @Column(name="local_body_name_english")    
	public String getLocalBodyNameEnglish() {
		return localBodyNameEnglish;
	}
	public void setLocalBodyNameEnglish(String localBodyNameEnglish) {
		this.localBodyNameEnglish = localBodyNameEnglish;
	}
	
	  @Column(name="local_body_name_local")
	public String getLocalBodyNameLocal() {
		return localBodyNameLocal;
	}
	public void setLocalBodyNameLocal(String localBodyNameLocal) {
		this.localBodyNameLocal = localBodyNameLocal;
	}
	public Character getOperation_state() {
		return operation_state;
	}
	public void setOperation_state(Character operation_state) {
		this.operation_state = operation_state;
	}
	
	
	


	
	


  
  

  	

}
