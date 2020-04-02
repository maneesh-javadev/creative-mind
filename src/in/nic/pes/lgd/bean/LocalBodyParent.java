package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;


@Entity
@NamedNativeQueries({
@NamedNativeQuery(query="select a.local_body_type_code,a.local_body_type_name from local_body_type a,local_body_setup b ,local_body_tiers_setup c where b.local_body_setup_code=c.local_body_setup_code and b.local_body_setup_version=c.local_body_setup_version and c.local_body_type_code=a.local_body_type_code and b.slc=:slc and a.level='D' and a.isactive and b.isactive and c.isactive",name="getStateTopHierarchywise",resultClass=LocalBodyParent.class),
})
public class LocalBodyParent {
	
	/**
	 * 
	 */
	@Id
	@Column(name="local_body_type_code")
	private String localBodyTypeCode;
	
    @Column(name="local_body_type_name")
	private String localBodyName;

	public String getLocalBodyName() {
		return localBodyName;
	}

	public void setLocalBodyName(String localBodyName) {
		this.localBodyName = localBodyName;
	}

	public String getLocalBodyTypeCode() {
		return localBodyTypeCode;
	}

	public void setLocalBodyTypeCode(String localBodyTypeCode) {
		this.localBodyTypeCode = localBodyTypeCode;
	}

	

	

}