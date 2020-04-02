package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query = "select * from set_parliament_constituency_fn(:stateCode,:userId,:pcNameEnglish,:pcList,:pcPartList,:acList,:pcNameLocal,:eciCode,:coordinates,:mapAttachmentCode)", name ="saveParliamentConstituency",resultClass=SaveParliamentConstituency.class) 
public class SaveParliamentConstituency {

		@Id
		private Integer set_parliament_constituency_fn;

		@Column(name = "set_parliament_constituency_fn", nullable = false)
		public Integer getSet_parliament_constituency_fn() {
			return set_parliament_constituency_fn;
		}

		public void setSet_parliament_constituency_fn(Integer set_parliament_constituency_fn) {
			this.set_parliament_constituency_fn = set_parliament_constituency_fn;
		}

	
		 

		
		

}
