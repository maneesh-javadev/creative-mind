/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.nic.pes.lgd.bean;

//import java.io.Serializable;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author
 */
@Entity
@Table(name = "landregion_replacedby")
public class LandregionReplacedby implements Serializable{

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//@NotNull
    @Id
    @Column(name = "id")
    private Integer id;
  //@NotNull
    @Column(name = "lr_replacedby")
    private int lrReplacedby;
  //@NotNull
    @Column(name = "entity_code")
    private int entityCode;
    @Column(name = "entity_version")
    private int entityVersion;
    @Column(name = "entity_type")
    private char entityType;
    


    public LandregionReplacedby() {
    }

// TODO Remove unused code found by UCDetector
//     public LandregionReplacedby(Integer id) {
//         this.id = id;
//     }

// TODO Remove unused code found by UCDetector
//     public LandregionReplacedby(Integer id, int lrReplacedby) {
//         this.id = id;
//         this.lrReplacedby = lrReplacedby;
//     }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LandregionReplacedby)) {
            return false;
        }
        LandregionReplacedby other = (LandregionReplacedby) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "in.nic.pes.lgd.bean.LandregionReplacedby[ id=" + id + " ]";
    }

	public int getLrReplacedby() {
		return lrReplacedby;
	}

	public void setLrReplacedby(int lrReplacedby) {
		this.lrReplacedby = lrReplacedby;
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

// TODO Remove unused code found by UCDetector
// 	public static long getSerialversionuid() {
// 		return serialVersionUID;
// 	}

	
}
