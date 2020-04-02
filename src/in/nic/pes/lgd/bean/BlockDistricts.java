package in.nic.pes.lgd.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @Ram
 */
@Entity
@Table(name = "block_districts")
public class BlockDistricts implements Serializable{
  private static final long serialVersionUID = 1L;
  
  	@GenericGenerator
  	(name = "sequence", strategy = "sequence", parameters={@Parameter(name="sequence",value="blockdistseq")})
	@Id
	@GeneratedValue(generator = "sequence")
  	@Column(name = "bdid")
    private Integer bdid;
    @Column(name = "block_district_code")
    private int blockDistrictCode;
    @JoinColumns({
      @JoinColumn(name = "district_code", referencedColumnName = "district_code"),
      @JoinColumn(name = "district_version", referencedColumnName = "district_version")})
    @ManyToOne(optional = false)
    private District district;

    public BlockDistricts() {
    }

// TODO Remove unused code found by UCDetector
//     public BlockDistricts(Integer bdid) {
//         this.bdid = bdid;
//     }

// TODO Remove unused code found by UCDetector
//     public BlockDistricts(Integer bdid, int blockDistrictCode) {
//         this.bdid = bdid;
//         this.blockDistrictCode = blockDistrictCode;
//     }

    public Integer getBdid() {
        return bdid;
    }

    public void setBdid(Integer bdid) {
        this.bdid = bdid;
    }

    public int getBlockDistrictCode() {
        return blockDistrictCode;
    }

    public void setBlockDistrictCode(int blockDistrictCode) {
        this.blockDistrictCode = blockDistrictCode;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bdid != null ? bdid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BlockDistricts)) {
            return false;
        }
        BlockDistricts other = (BlockDistricts) object;
        if ((this.bdid == null && other.bdid != null) || (this.bdid != null && !this.bdid.equals(other.bdid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "in.nic.pes.lgd.bean.BlockDistricts[ bdid=" + bdid + " ]";
    }
    
}
