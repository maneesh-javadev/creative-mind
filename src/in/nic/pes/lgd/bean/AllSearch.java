package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries(
		{
@NamedNativeQuery(query=" SELECT * FROM get_entity_list_by_search_text_fn(:entityCodeForSearch,:entityCode) ",name="getAllSearchDetails",resultClass=AllSearch.class),
@NamedNativeQuery(query=" SELECT * FROM get_entity_list_by_search_bycode_fn(:entityCodeForSearch,:entityCode) ",name="getAllSearchDetailsByCode",resultClass=AllSearch.class)
		}
)
public class AllSearch {
	
	private Integer id;
	
	
	private Integer entityCode;
	
	
	private String entityType;
	
	
	private String entityName;
	

	private String hierarchy;
	@Id
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="entity_code")
	public Integer getEntityCode() {
		return entityCode;
	}
	public void setEntityCode(Integer entityCode) {
		this.entityCode = entityCode;
	}
	@Column(name="entity_type")
	public String getEntityType() {
		return entityType;
	}
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	@Column(name="entity_name")
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	@Column(name="hierarchy")
	public String getHierarchy() {
		return hierarchy;
	}
	public void setHierarchy(String hierarchy) {
		this.hierarchy = hierarchy;
	}
	
}
