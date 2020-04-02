package in.nic.pes.lgd.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query="SELECT row_number() OVER () as rid,  * from get_unmapped_landregionsandlocalbodiesandwards_for_pcandac(:entity_code)",name="unmappedLbPcAcWardBean",resultClass=UnmappedLbPcAcWard.class)
public class UnmappedLbPcAcWard {
	
	@Id
	@Column(name = "rid", nullable = false)
	private Integer rid;

	@Column(name = "entity_code", nullable = false)
	private Integer entityCode;
	
	@Column(name = "entity_name_english")
	private String entityNameEnglish;
	
	@Column(name = "entity_type_name")
	private String entityTypeName;

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public Integer getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(Integer entityCode) {
		this.entityCode = entityCode;
	}

	
	

	public String getEntityNameEnglish() {
		return entityNameEnglish;
	}

	public void setEntityNameEnglish(String entityNameEnglish) {
		this.entityNameEnglish = entityNameEnglish;
	}

	public String getEntityTypeName() {
		return entityTypeName;
	}

	public void setEntityTypeName(String entityTypeName) {
		this.entityTypeName = entityTypeName;
	}
	
	

}
