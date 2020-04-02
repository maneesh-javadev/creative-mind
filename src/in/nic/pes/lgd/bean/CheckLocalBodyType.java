package in.nic.pes.lgd.bean;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;




	@Entity
	@NamedNativeQuery(query="select DISTINCT a.slc,c.state_name_english from local_body_setup a,local_body_tiers_setup b, state c where a.local_body_setup_code=b.local_body_setup_code and a.local_body_setup_version=b.local_body_setup_version and a.slc=c.slc and b.local_body_type_code=:localBodyTypeCode order by c.state_name_english",name="checkLocalBodyType",resultClass=CheckLocalBodyType.class)
	
public class CheckLocalBodyType implements Serializable  
{

		/**
		 * 
		 */
		private static final long serialVersionUID = -1137606808379574851L;

		@Id
	    @Basic(optional = false)
		 @Column(name = "slc", nullable = false)
	    private Integer slc;
		
	    @Column(name = "state_name_english")
	    private String stateNameEnglish;

		public Integer getSlc() {
			return slc;
		}

		public void setSlc(Integer slc) {
			this.slc = slc;
		}

		public String getStateNameEnglish() {
			return stateNameEnglish;
		}

		public void setStateNameEnglish(String stateNameEnglish) {
			this.stateNameEnglish = stateNameEnglish;
		}
}
