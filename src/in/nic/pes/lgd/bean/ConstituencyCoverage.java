/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.nic.pes.lgd.bean;

//import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author
 */
@Entity
@Table(name = "constituency_coverage")
public class ConstituencyCoverage {
    //@NotNull
	@Id
	@Column(name = "cc_code")
	@SequenceGenerator(name = "cc_code_seq_generator", initialValue=1, allocationSize=1,  sequenceName = "constituency_coverage_cc_code_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cc_code_seq_generator")
    
    private int ccCode;
  //@NotNull
   /* @Column(name = "constituency_code")
    private int constituencyCode;*/
  //@NotNull
   /* @Column(name = "constituency_version")
    private int constituencyVersion;*/
  //@NotNull
    @Column(name = "constituency_type")
    private char constituencyType;
  /*//@NotNull
    @Column(name = "constituency_id")
    private int constituencyId;*/
/*  //@NotNull
    @Column(name = "coverage_body_code")
    private int coverageBodyCode;*/
  //@NotNull
    @Column(name = "lastupdated")
  //@Temporal(TemporalType.TIMESTAMP)
    private Date lastupdated;
  //@NotNull
    @Column(name = "lastupdatedby")
    private long lastupdatedby;
  //@NotNull
    @Column(name = "createdon")
  //@Temporal(TemporalType.TIMESTAMP)
    private Date createdon;
  //@NotNull
    @Column(name = "createdby")
    private long createdby;
    
    @Column(name = "isactive", nullable = false)
	private boolean isactive;

    @Column(name = "constituency_lc")
    private int constituencyLc;
    
  //@OneToMany(cascade = CascadeType.ALL, mappedBy = "ccCode")
/*    private Collection<CoveredConstituencyBody> coveredConstituencyBodyCollection;
*/
    public ConstituencyCoverage() {
    }

// TODO Remove unused code found by UCDetector
//     public ConstituencyCoverage(int ccCode) {
//         this.ccCode = ccCode;
//     }

// TODO Remove unused code found by UCDetector
//     public ConstituencyCoverage(int ccCode, char constituencyType, int constituencyId, char coverageType, int coverageBodyCode, Date lastupdated, long lastupdatedby, Date createdon, long createdby) {
//         this.ccCode = ccCode;
//         //this.constituencyCode = constituencyCode;
//        // this.constituencyVersion = constituencyVersion;
//         this.constituencyType = constituencyType;
//        /* this.constituencyId = constituencyId;
//      */
//        /* this.coverageBodyCode = coverageBodyCode;*/
//         this.lastupdated = lastupdated;
//         this.lastupdatedby = lastupdatedby;
//         this.createdon = createdon;
//         this.createdby = createdby;
//     }

    public int getCcCode() {
        return ccCode;
    }

    public void setCcCode(int ccCode) {
        this.ccCode = ccCode;
    }

    /*public int getConstituencyCode() {
        return constituencyCode;
    }

    public void setConstituencyCode(int constituencyCode) {
        this.constituencyCode = constituencyCode;
    }*/

    /*public int getConstituencyVersion() {
        return constituencyVersion;
    }

    public void setConstituencyVersion(int constituencyVersion) {
        this.constituencyVersion = constituencyVersion;
    }*/

    public char getConstituencyType() {
        return constituencyType;
    }

    public void setConstituencyType(char constituencyType) {
        this.constituencyType = constituencyType;
    }

 /*   public int getConstituencyId() {
        return constituencyId;
    }

    public void setConstituencyId(int constituencyId) {
        this.constituencyId = constituencyId;
    }*/

    

  /*  public int getCoverageBodyCode() {
        return coverageBodyCode;
    }

    public void setCoverageBodyCode(int coverageBodyCode) {
        this.coverageBodyCode = coverageBodyCode;
    }
*/
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

    /*public Collection<CoveredConstituencyBody> getCoveredConstituencyBodyCollection() {
        return coveredConstituencyBodyCollection;
    }

    public void setCoveredConstituencyBodyCollection(Collection<CoveredConstituencyBody> coveredConstituencyBodyCollection) {
        this.coveredConstituencyBodyCollection = coveredConstituencyBodyCollection;
    }
*/
   /* @Override
    public int hashCode() {
        int hash = 0;
        hash += (ccCode != null ? ccCode.hashCode() : 0);
        return hash;
    }*/

   
    public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public int getConstituencyLc() {
		return constituencyLc;
	}

	public void setConstituencyLc(int constituencyLc) {
		this.constituencyLc = constituencyLc;
	}

	/*@Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConstituencyCoverage)) {
            return false;
        }
        ConstituencyCoverage other = (ConstituencyCoverage) object;
        if ((this.ccCode == null && other.ccCode != null) || (this.ccCode != null && !this.ccCode.equals(other.ccCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "in.nic.pes.lgd.bean.ConstituencyCoverage[ ccCode=" + ccCode + " ]";
    }*/
    
}
