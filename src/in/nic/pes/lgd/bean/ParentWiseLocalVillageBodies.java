package in.nic.pes.lgd.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;


//
@Entity
@NamedNativeQuery(query=" SELECT * FROM get_parentwise_lb_list(:localBodyCode)",name="getLocalGovtBodyforVillage",resultClass=ParentWiseLocalVillageBodies.class)
public class ParentWiseLocalVillageBodies{
	/**
	 * 
	 */


    private String localBodyNameEnglish;
    private String localBodyNameLocal;
    private String localBodyCode;
    
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
	


	
	


  
  

  	

}
