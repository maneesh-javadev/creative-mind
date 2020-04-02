package in.nic.pes.lgd.forms;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "announcement", schema = "pescommon")
@NamedNativeQueries({ @NamedNativeQuery(query = "select * from pescommon.get_citizen_announcement(:appid)", name = "GetAnnouncementList", resultClass = AnnouncementData.class) })
public class AnnouncementData implements Serializable {
	private static final long serialVersionUID = 1L;

	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "pescommon.announcement_announcement_id_seq") })
	@Id
	@GeneratedValue(generator = "sequence")
	@Basic(optional = false)
	@Column(name = "announcement_id")
	private Long announcementId;

	@Column(name = "reasion")
	private String reasion;
	@Column(name = "display_start_date")
	private Date displayStartDate;
	@Column(name = "display_end_date")
	private Date displayEndDate;
	@Column(name = "features")
	private String features;
	@Column(name = "feature_wise_description")
	private String featureWiseDescription;

	public AnnouncementData() {
	}

// TODO Remove unused code found by UCDetector
// 	public static long getSerialversionuid() {
// 		return serialVersionUID;
// 	}

// TODO Remove unused code found by UCDetector
// 	public AnnouncementData(Long announcementId) {
// 		this.announcementId = announcementId;
// 	}

	public Long getAnnouncementId() {
		return announcementId;
	}

	public void setAnnouncementId(Long announcementId) {
		this.announcementId = announcementId;
	}

	public String getReasion() {
		return reasion;
	}

	public void setReasion(String reasion) {
		this.reasion = reasion;
	}

	public Date getDisplayStartDate() {
		return displayStartDate;
	}

	public void setDisplayStartDate(Date displayStartDate) {
		this.displayStartDate = displayStartDate;
	}

	public Date getDisplayEndDate() {
		return displayEndDate;
	}

	public void setDisplayEndDate(Date displayEndDate) {
		this.displayEndDate = displayEndDate;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public String getFeatureWiseDescription() {
		return featureWiseDescription;
	}

	public void setFeatureWiseDescription(String featureWiseDescription) {
		this.featureWiseDescription = featureWiseDescription;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (announcementId != null ? announcementId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof AnnouncementData)) {
			return false;
		}
		AnnouncementData other = (AnnouncementData) object;
		if ((this.announcementId == null && other.announcementId != null) || (this.announcementId != null && !this.announcementId.equals(other.announcementId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "in.nic.pes.common.model.Announcement[ announcementId=" + announcementId + " ]";
	}
}
