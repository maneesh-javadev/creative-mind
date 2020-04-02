package in.nic.pes.lgd.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "landregion_replaces")
public class LandregionReplaces implements Serializable {

	private static final long serialVersionUID = 1L;
	// @NotNull
	@Id
	@Column(name = "id")
	private int id;
	// @NotNull
	@Column(name = "lr_replaces")
	private int lrReplaces;
	// @NotNull
	@Column(name = "entity_code")
	private int entityCode;
	@Column(name = "entity_version")
	private int entityVersion;
	@Column(name = "entity_type")
	private char entityType;

	public int getId() {
		return id;
	}

	public LandregionReplaces() {
		super();
	}

// TODO Remove unused code found by UCDetector
// 	public LandregionReplaces(int id, int lrReplaces, int entityCode,
// 			int entityVersion, char entityType) {
// 		super();
// 		this.id = id;
// 		this.lrReplaces = lrReplaces;
// 		this.entityCode = entityCode;
// 		this.entityVersion = entityVersion;
// 		this.entityType = entityType;
// 	}

// TODO Remove unused code found by UCDetector
// 	public LandregionReplaces(int id, int lrReplaces) {
// 		super();
// 		this.id = id;
// 		this.lrReplaces = lrReplaces;
// 	}

	public void setId(int id) {
		this.id = id;
	}

// TODO Remove unused code found by UCDetector
// 	public static long getSerialversionuid() {
// 		return serialVersionUID;
// 	}

	public int getLrReplaces() {
		return lrReplaces;
	}

	public void setLrReplaces(int lrReplaces) {
		this.lrReplaces = lrReplaces;
	}

	public int getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(int entityCode) {
		this.entityCode = entityCode;
	}

	public int getEntityVersion() {
		return entityVersion;
	}

	public void setEntityVersion(int entityVersion) {
		this.entityVersion = entityVersion;
	}

	public char getEntityType() {
		return entityType;
	}

	public void setEntityType(char entityType) {
		this.entityType = entityType;
	}

}
