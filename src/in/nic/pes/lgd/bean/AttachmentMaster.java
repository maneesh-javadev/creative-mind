package in.nic.pes.lgd.bean;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Hemant Gupta
 */
@Entity
@Table(name = "attachment_master")
public class AttachmentMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "file_master_id")
    private Long fileMasterId;
    @Column(name = "require_title")
    private String requireTitle;
    @Column(name = "unique_title")
    private String uniqueTitle;
    @Column(name = "total_file_size")
    private Long totalFileSize;
    @Column(name = "individual_file_size")
    private Long individualFileSize;
    @Column(name = "file_limit")
    private Integer fileLimit;
    @Column(name = "file_type")
    private String fileType;
    @Column(name = "file_location")
    private String fileLocation;

    public AttachmentMaster() {
    }

// TODO Remove unused code found by UCDetector
//     public AttachmentMaster(Long fileMasterId) {
//         this.fileMasterId = fileMasterId;
//     }

    public Long getFileMasterId() {
        return fileMasterId;
    }

    public void setFileMasterId(Long fileMasterId) {
        this.fileMasterId = fileMasterId;
    }

    public String getRequireTitle() {
        return requireTitle;
    }

    public void setRequireTitle(String requireTitle) {
        this.requireTitle = requireTitle;
    }

    public String getUniqueTitle() {
        return uniqueTitle;
    }

    public void setUniqueTitle(String uniqueTitle) {
        this.uniqueTitle = uniqueTitle;
    }

    public Long getTotalFileSize() {
        return totalFileSize;
    }

    public void setTotalFileSize(Long totalFileSize) {
        this.totalFileSize = totalFileSize;
    }

    public Long getIndividualFileSize() {
        return individualFileSize;
    }

    public void setIndividualFileSize(Long individualFileSize) {
        this.individualFileSize = individualFileSize;
    }

    public Integer getFileLimit() {
        return fileLimit;
    }

    public void setFileLimit(Integer fileLimit) {
        this.fileLimit = fileLimit;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fileMasterId != null ? fileMasterId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AttachmentMaster)) {
            return false;
        }
        AttachmentMaster other = (AttachmentMaster) object;
        if ((this.fileMasterId == null && other.fileMasterId != null) || (this.fileMasterId != null && !this.fileMasterId.equals(other.fileMasterId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "in.nic.pes.common.model.AttachmentMaster[ fileMasterId=" + fileMasterId + " ]";
    }
    
}
