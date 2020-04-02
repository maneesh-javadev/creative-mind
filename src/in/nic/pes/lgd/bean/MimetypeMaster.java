/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
@Table(name = "mimetype_master")
public class MimetypeMaster implements Serializable {

	    private static final long serialVersionUID = 1L;
	    @Id
	    @Basic(optional = false)
	    @Column(name = "mimetype_id")
	    private Long mimetypeId;
	    @Column(name = "mimetype_name")
	    private String mimetypeName;

	    public MimetypeMaster() {
	    }

// TODO Remove unused code found by UCDetector
// 	    public MimetypeMaster(Long mimetypeId) {
// 	        this.mimetypeId = mimetypeId;
// 	    }

	    public Long getMimetypeId() {
	        return mimetypeId;
	    }

	    public void setMimetypeId(Long mimetypeId) {
	        this.mimetypeId = mimetypeId;
	    }

	    public String getMimetypeName() {
	        return mimetypeName;
	    }

	    public void setMimetypeName(String mimetypeName) {
	        this.mimetypeName = mimetypeName;
	    }

	    @Override
	    public int hashCode() {
	        int hash = 0;
	        hash += (mimetypeId != null ? mimetypeId.hashCode() : 0);
	        return hash;
	    }

	    @Override
	    public boolean equals(Object object) {
	        // TODO: Warning - this method won't work in the case the id fields are not set
	        if (!(object instanceof MimetypeMaster)) {
	            return false;
	        }
	        MimetypeMaster other = (MimetypeMaster) object;
	        if ((this.mimetypeId == null && other.mimetypeId != null) || (this.mimetypeId != null && !this.mimetypeId.equals(other.mimetypeId))) {
	            return false;
	        }
	        return true;
	    }

	    @Override
	    public String toString() {
	        return "in.nic.pes.common.model.MimetypeMaster[ mimetypeId=" + mimetypeId + " ]";
	    }
}
