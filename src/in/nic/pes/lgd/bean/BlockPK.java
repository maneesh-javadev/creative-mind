/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.nic.pes.lgd.bean;

//import java.io.Serializable;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author
 */
@Embeddable
public class BlockPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "block_code")
    private int blockCode;

    @Column(name = "block_version")
    private int blockVersion;
    
    @Column(name="minor_version")
  	private Integer minorVersion;

    public BlockPK() {
    }

    public BlockPK(int blockCode, int blockVersion) {
        this.blockCode = blockCode;
        this.blockVersion = blockVersion;
    }

    public int getBlockCode() {
        return blockCode;
    }

    public void setBlockCode(int blockCode) {
        this.blockCode = blockCode;
    }

    public int getBlockVersion() {
        return blockVersion;
    }

    public void setBlockVersion(int blockVersion) {
        this.blockVersion = blockVersion;
    }
    
    

    public Integer getMinorVersion() {
		return minorVersion;
	}

	public void setMinorVersion(Integer minorVersion) {
		this.minorVersion = minorVersion;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (int) blockCode;
        hash += (int) blockVersion;
        hash += (int) minorVersion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BlockPK)) {
            return false;
        }
        BlockPK other = (BlockPK) object;
        if (this.blockCode != other.blockCode) {
            return false;
        }
        if (this.blockVersion != other.blockVersion) {
            return false;
        }
        if (this.minorVersion != other.minorVersion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "in.nic.pes.lgd.bean.BlockPK[ blockCode=" + blockCode + ", blockVersion=" + blockVersion + "minorVersion="+minorVersion+" ]";
    }
    
}
