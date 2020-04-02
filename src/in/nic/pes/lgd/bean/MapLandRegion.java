package in.nic.pes.lgd.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
*
* @Author: Ram
*/

@Entity
@Table(name = "map_landregion")
public class MapLandRegion implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@GenericGenerator
    (name = "sequence", strategy = "sequence", parameters={@Parameter(name="sequence",value="map_seq")})
    @Id
   @GeneratedValue(generator = "sequence")
    @Column(name = "map_attachment_code")
	private Integer mapLandregionCode;
	
	@Column(name = "landregion_code")
	private int landregionCode;
	@Column(name = "landregion_version")
	private int landregionVersion;
	@Column(name = "landregion_type")
	private char landregionType;
	@Column(name = "coordinates")
	private String coordinates;
	@Column(name = "image_path")
	private String imagePath;
	@Column(name = "effective_date")
	private Date effectiveDate;
	@Column(name = "lastupdated")
	private Date lastupdated;
	@Column(name = "lastupdatedby")
	private long lastupdatedby;
	@Column(name = "createdon")
	private Date createdon;
	@Column(name = "createdby")
	private long createdby;
	@Column(name = "warningflag")
	private boolean warningflag;

	public MapLandRegion() {
	}

// TODO Remove unused code found by UCDetector
// 	public MapLandRegion(Integer mapLandregionCode) {
// 		this.mapLandregionCode = mapLandregionCode;
// 	}

// TODO Remove unused code found by UCDetector
// 	public MapLandRegion(Integer mapLandregionCode, int landregionCode,
// 			int landregionVersion, char landregionType, Date effectiveDate,
// 			Date lastupdated, long lastupdatedby, Date createdon,
// 			long createdby, boolean warningflag) {
// 		this.mapLandregionCode = mapLandregionCode;
// 		this.landregionCode = landregionCode;
// 		this.landregionVersion = landregionVersion;
// 		this.landregionType = landregionType;
// 		this.effectiveDate = effectiveDate;
// 		this.lastupdated = lastupdated;
// 		this.lastupdatedby = lastupdatedby;
// 		this.createdon = createdon;
// 		this.createdby = createdby;
// 		this.warningflag = warningflag;
// 	}

	public Integer getMapLandregionCode() {
		return mapLandregionCode;
	}

	public void setMapLandregionCode(Integer mapLandregionCode) {
		this.mapLandregionCode = mapLandregionCode;
	}

	public int getLandregionCode() {
		return landregionCode;
	}

	public void setLandregionCode(int landregionCode) {
		this.landregionCode = landregionCode;
	}

	public int getLandregionVersion() {
		return landregionVersion;
	}

	public void setLandregionVersion(int landregionVersion) {
		this.landregionVersion = landregionVersion;
	}

	public char getLandregionType() {
		return landregionType;
	}

	public void setLandregionType(char landregionType) {
		this.landregionType = landregionType;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(Date lastupdated) {
		this.lastupdated = lastupdated;
	}

	public long getLastupdatedby() {
		return lastupdatedby;
	}

	public void setLastupdatedby(long lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}

	public Date getCreatedon() {
		return createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	public long getCreatedby() {
		return createdby;
	}

	public void setCreatedby(long createdby) {
		this.createdby = createdby;
	}

	public boolean getWarningflag() {
		return warningflag;
	}

	public void setWarningflag(boolean warningflag) {
		this.warningflag = warningflag;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (mapLandregionCode != null ? mapLandregionCode.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof MapLandRegion)) {
			return false;
		}
		MapLandRegion other = (MapLandRegion) object;
		if ((this.mapLandregionCode == null && other.mapLandregionCode != null)
				|| (this.mapLandregionCode != null && !this.mapLandregionCode
						.equals(other.mapLandregionCode))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.test.MapLandregion[ mapLandregionCode=" + mapLandregionCode
				+ " ]";
	}
}
