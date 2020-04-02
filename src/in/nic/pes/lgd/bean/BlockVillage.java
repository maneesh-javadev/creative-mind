package in.nic.pes.lgd.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @Ram
 */
@Entity
@Table(name = "block_village")
public class BlockVillage implements Serializable{
	private static final long serialVersionUID = 1L;
    
	
  @GenericGenerator(name="sequence", strategy="sequence", parameters={@org.hibernate.annotations.Parameter(name="sequence", value="blockvillseq")})
	@Id
	@GeneratedValue(generator = "sequence")
	@Column(name = "id")
    private Integer id;
	
	@Column(name = "blc")
	private Integer blc;
	
	@Column(name = "vlc")
	private Integer vlc;
	
	@Column(name = "isactive")
	private boolean isactive;
	
	@Column(name = "blc_version")
	private Integer blc_version;
	
	
	@Column(name = "vlc_version")
	private Integer vlc_version;
	
	
	@Column(name = "mapped_on")
	private Date mappedOn;
  
	@Column(name="mapped_by")
   private long mappedBy;
	
	@Column(name="unmapped_on")
	private Date unmappedOn;
  
	@Column(name="unmapped_by")
	private long unmappedBy;
	
	@Column(name="coverage_type")
	private Character coverageType;
	
	@Column(name = "local_body_code")
	private Integer lblc;
	
	@Column(name="lb_unmapped_on")
	private Date lbUnmappedOn;
  
	@Column(name="lb_unmapped_by")
	private long lbUnmappedBy;
	
	@Column(name="lb_mapped_on")
	private Date lbMappedOn;
  
	@Column(name="lb_mapped_by")
	private long lbMappedBy;
	
	
	@Transient
	private String blockCode;
	
	@Transient
	private Long userId;
	
	@Transient
	private String villageMappedListNew;
	
	@Transient
	private String villageMappedListDel;
	
	@Transient
	private String lbCoverageVillageList;
	
	@Transient
	private String lbCoverageVillageDel;
	
  public String getBlockCode()
  {
    return this.blockCode;
  }
  
  public void setBlockCode(String blockCode)
  {
    this.blockCode = blockCode;
  }
  
  public String getVillageMappedListNew()
  {
    return this.villageMappedListNew;
  }
  
  public void setVillageMappedListNew(String villageMappedListNew)
  {
    this.villageMappedListNew = villageMappedListNew;
  }
  
  public String getVillageMappedListDel()
  {
    return this.villageMappedListDel;
  }
  
  public void setVillageMappedListDel(String villageMappedListDel)
  {
    this.villageMappedListDel = villageMappedListDel;
  }
  
  public Integer getBlc_version()
  {
    return this.blc_version;
  }
  
  public void setBlc_version(Integer blc_version)
  {
    this.blc_version = blc_version;
  }
  
  public Integer getVlc_version()
  {
    return this.vlc_version;
  }
  
  public void setVlc_version(Integer vlc_version)
  {
    this.vlc_version = vlc_version;
  }
  
  public Integer getBlc()
  {
    return this.blc;
  }
  
  public void setBlc(Integer blc)
  {
    this.blc = blc;
  }
  
  public Integer getVlc()
  {
    return this.vlc;
  }
  
  public void setVlc(Integer vlc)
  {
    this.vlc = vlc;
  }
  
  public Integer getId()
  {
    return this.id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public int hashCode()
  {
    int hash = 0;
    hash += (this.id != null ? this.id.hashCode() : 0);
    return hash;
  }
  
  public boolean equals(Object object)
  {
    if (!(object instanceof BlockVillage)) {
      return false;
    }
    BlockVillage other = (BlockVillage)object;
    if (((this.id == null) && (other.id != null)) || ((this.id != null) && (!this.id.equals(other.id)))) {
      return false;
    }
    return true;
  }
  
  public String toString()
  {
    return "in.nic.pes.lgd.bean.BlockVillage[ id=" + this.id + " ]";
  }
  
  public boolean isIsactive()
  {
    return this.isactive;
  }
  
  public void setIsactive(boolean isactive)
  {
    this.isactive = isactive;
  }
  
  public Date getMappedOn()
  {
    return this.mappedOn;
  }
  
  public void setMappedOn(Date mappedOn)
  {
    this.mappedOn = mappedOn;
  }
  
  public Date getUnmappedOn()
  {
    return this.unmappedOn;
  }
  
  public void setUnmappedOn(Date unmappedOn)
  {
    this.unmappedOn = unmappedOn;
  }
  
  public long getMappedBy()
  {
    return this.mappedBy;
  }
  
  public void setMappedBy(long mappedBy)
  {
    this.mappedBy = mappedBy;
  }
  
  public long getUnmappedBy()
  {
    return this.unmappedBy;
  }
  
  public void setUnmappedBy(long unmappedBy)
  {
    this.unmappedBy = unmappedBy;
  }

public Long getUserId() {
	return userId;
}

public void setUserId(Long userId) {
	this.userId = userId;
}

public String getLbCoverageVillageList() {
	return lbCoverageVillageList;
}

public void setLbCoverageVillageList(String lbCoverageVillageList) {
	this.lbCoverageVillageList = lbCoverageVillageList;
}

public Character getCoverageType() {
	return coverageType;
}

public void setCoverageType(Character coverageType) {
	this.coverageType = coverageType;
}

public Integer getLblc() {
	return lblc;
}

public void setLblc(Integer lblc) {
	this.lblc = lblc;
}

public Date getLbUnmappedOn() {
	return lbUnmappedOn;
}

public void setLbUnmappedOn(Date lbUnmappedOn) {
	this.lbUnmappedOn = lbUnmappedOn;
}

public long getLbUnmappedBy() {
	return lbUnmappedBy;
}

public void setLbUnmappedBy(long lbUnmappedBy) {
	this.lbUnmappedBy = lbUnmappedBy;
}

public Date getLbMappedOn() {
	return lbMappedOn;
}

public void setLbMappedOn(Date lbMappedOn) {
	this.lbMappedOn = lbMappedOn;
}

public long getLbMappedBy() {
	return lbMappedBy;
}

public void setLbMappedBy(long lbMappedBy) {
	this.lbMappedBy = lbMappedBy;
}

public String getLbCoverageVillageDel() {
	return lbCoverageVillageDel;
}

public void setLbCoverageVillageDel(String lbCoverageVillageDel) {
	this.lbCoverageVillageDel = lbCoverageVillageDel;
}









  
}
