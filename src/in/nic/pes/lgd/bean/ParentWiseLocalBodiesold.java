/**
 * Save Local Body Freeze/Un-freeze.
 * @author Ashish Dhupia on 15-02-2015
 * 
 */
package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;


//
@Entity
@NamedNativeQueries
({
	@NamedNativeQuery(query="SELECT * FROM get_parentwise_lb_list(:localBodyCode)",name="getLocalGovtBodyforParentold",resultClass=ParentWiseLocalBodiesold.class),
	@NamedNativeQuery(query="SELECT * FROM get_parentwise_lb_list(:localBodyCode) where local_body_code != :lbCode",name="getLocalGovtBodyforParentChangeCoveragea",resultClass=ParentWiseLocalBodiesold.class),
})

public class ParentWiseLocalBodiesold{
	/**
	 * 
	 */

    private String localBodyNameEnglish;
    private String localBodyNameLocal;
    private String localBodyCode;
    private Integer childCount;
    
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
	
	 @Column(name="child_count")
	public Integer getChildCount() {
		return childCount;
	}
	public void setChildCount(Integer childCount) {
		this.childCount = childCount;
	}
	
}
