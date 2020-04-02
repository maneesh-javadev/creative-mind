package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query = "select * from set_assembly_constituency_fn(:pcCode,:userId,:acNameEnglish,:acList,:acNameLocal,:eciCode,:coordinates,:uploadMap,:acListPart,:mapAttachmentid)", name ="saveAssemblyConstituency",resultClass=SaveAssemblyConstituency.class)
public class SaveAssemblyConstituency {

	
	@Id
	private Integer set_assembly_constituency_fn;

	@Column(name = "set_assembly_constituency_fn", nullable = false)
	public Integer getSet_assembly_constituency_fn() {
		return set_assembly_constituency_fn;
	}

	public void setSet_assembly_constituency_fn(Integer set_assembly_constituency_fn) {
		this.set_assembly_constituency_fn = set_assembly_constituency_fn;
	}

}
