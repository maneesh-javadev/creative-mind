package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;


@Entity
@NamedNativeQuery(query=" SELECT entity_code,entity_name_english, image_path from get_entitywise_map_details_fn(:entityType,:entityCode)",name="getEntityWiseMapDetailsFn",resultClass=EntityWiseMapDetailsPojo.class)

public class EntityWiseMapDetailsPojo {
	
	
	@Id
	@Column(name="entity_code")
	private Integer entityCode;
	
	@Column(name = "entity_name_english")
	private String entityNameEnglish;
	
	@Column(name = "image_path")
	private String imagePath;

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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	
	
}
