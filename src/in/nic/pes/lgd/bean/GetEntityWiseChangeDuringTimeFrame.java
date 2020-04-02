package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
@NamedNativeQuery(query = "select * from get_entitywise_changes_during_timeframe_fn(:type,:fromdt,:todt,:code)", name ="GetEntityWiseChangeDuringTimeFrame",resultClass=GetEntityWiseChangeDuringTimeFrame.class),
@NamedNativeQuery(query = "select * from get_entitywise_changes_during_timeframe_fn(:type,:fromdt,:todt)", name ="GetEntityWiseChangeDuringTimeFrameNoState",resultClass=GetEntityWiseChangeDuringTimeFrame.class)
})
public class GetEntityWiseChangeDuringTimeFrame {

	private Integer id;
	private String entityType;
	private Integer entityCode;
	private Integer entityVersion;
	private String entityNameEnglish;
	private boolean isactive;
	private String createdFrom;
	private String createdTo;
	
	@Id
	@Column(name="id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="entity_type")
	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	@Column(name="entity_code")
	public Integer getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(Integer entityCode) {
		this.entityCode = entityCode;
	}

	@Column(name="entity_name_english")
	public String getEntityNameEnglish() {
		return entityNameEnglish;
	}

	public void setEntityNameEnglish(String entityNameEnglish) {
		this.entityNameEnglish = entityNameEnglish;
	}

	@Column(name="replacedBy")
	public String getCreatedFrom() {
		return createdFrom;
	}

	public void setCreatedFrom(String createdFrom) {
		this.createdFrom = createdFrom;
	}

	@Column(name="replaces")
	public String getCreatedTo() {
		return createdTo;
	}

	public void setCreatedTo(String createdTo) {
		this.createdTo = createdTo;
	}

	@Column(name="entity_version")
	public Integer getEntityVersion() {
		return entityVersion;
	}

	public void setEntityVersion(Integer entityVersion) {
		this.entityVersion = entityVersion;
	}

	@Column(name="isactive")
	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

}
