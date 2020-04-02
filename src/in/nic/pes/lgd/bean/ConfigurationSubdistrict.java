/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.nic.pes.lgd.bean;

//import java.io.Serializable;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author chandan soni
 */
@Entity
@Table(name = "configuration_subdistrict")
public class ConfigurationSubdistrict implements Serializable{
	
    private static final long serialVersionUID = 1L;

    private Integer id;
    
    private boolean issubdistrictblocksame;

    private State state;
    
    private int stateCode;
    
    private int stateVersion;
    
    private Date lastupdated;
    
    private long lastupdatedby;

    private Date createdon;
    
    private long createdby;

// TODO Remove unused code found by UCDetector
// 	public ConfigurationSubdistrict(Integer id, boolean issubdistrictblocksame,
// 			State state) {
// 		super();
// 		this.id = id;
// 		this.issubdistrictblocksame = issubdistrictblocksame;
// 		this.state = state;
// 	}

	public ConfigurationSubdistrict() {
    }

// TODO Remove unused code found by UCDetector
//     public ConfigurationSubdistrict(Integer id) {
//         this.id = id;
//     }

// TODO Remove unused code found by UCDetector
//     public ConfigurationSubdistrict(Integer id, boolean issubdistrictblocksame) {
//         this.id = id;
//         this.issubdistrictblocksame = issubdistrictblocksame;
//     }

// TODO Remove unused code found by UCDetector
//     public ConfigurationSubdistrict(Integer id, boolean issubdistrictblocksame,
// 			State state, int stateCode, int stateVersion) {
// 		super();
// 		this.id = id;
// 		this.issubdistrictblocksame = issubdistrictblocksame;
// 		this.state = state;
// 		this.stateCode = stateCode;
// 		this.stateVersion = stateVersion;
// 	}

// TODO Remove unused code found by UCDetector
// 	public ConfigurationSubdistrict(Integer id, boolean issubdistrictblocksame,
// 			State state, int stateCode, int stateVersion, Date lastupdated,
// 			long lastupdatedby, Date createdon, long createdby) {
// 		super();
// 		this.id = id;
// 		this.issubdistrictblocksame = issubdistrictblocksame;
// 		this.state = state;
// 		this.stateCode = stateCode;
// 		this.stateVersion = stateVersion;
// 		this.lastupdated = lastupdated;
// 		this.lastupdatedby = lastupdatedby;
// 		this.createdon = createdon;
// 		this.createdby = createdby;
// 	}

// TODO Remove unused code found by UCDetector
// 	public ConfigurationSubdistrict(Integer id, boolean issubdistrictblocksame,
// 			State state, Date lastupdated, long lastupdatedby, Date createdon,
// 			long createdby) {
// 		super();
// 		this.id = id;
// 		this.issubdistrictblocksame = issubdistrictblocksame;
// 		this.state = state;
// 		this.lastupdated = lastupdated;
// 		this.lastupdatedby = lastupdatedby;
// 		this.createdon = createdon;
// 		this.createdby = createdby;
// 	}

	/**
	 * @return the state
	 */
    @JoinColumns({
        @JoinColumn(name = "state_code", referencedColumnName = "state_code"),
        @JoinColumn(name = "state_version", referencedColumnName = "state_version")})
      @ManyToOne(optional = false)
	public State getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(State state) {
		this.state = state;
	}
	
    @Id
    @Column(name = "id")
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "issubdistrictblocksame")
    public boolean getIssubdistrictblocksame() {
        return issubdistrictblocksame;
    }

    public void setIssubdistrictblocksame(boolean issubdistrictblocksame) {
        this.issubdistrictblocksame = issubdistrictblocksame;
    }

    @Column(name = "state_code",insertable=false,updatable=false)
    public int getStateCode() {
		return stateCode;
	}

	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}

	@Column(name = "state_version",insertable=false,updatable=false)
	public int getStateVersion() {
		return stateVersion;
	}

	public void setStateVersion(int stateVersion) {
		this.stateVersion = stateVersion;
	}
	
    @Column(name = "lastupdated")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastupdated() {
        return lastupdated;
    }

    public void setLastupdated(Date lastupdated) {
        this.lastupdated = lastupdated;
    }

    @Column(name = "lastupdatedby")
    public long getLastupdatedby() {
        return lastupdatedby;
    }

    public void setLastupdatedby(long lastupdatedby) {
        this.lastupdatedby = lastupdatedby;
    }

    @Column(name = "createdon")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreatedon() {
        return createdon;
    }

    public void setCreatedon(Date createdon) {
        this.createdon = createdon;
    }

    @Column(name = "createdby")
    public long getCreatedby() {
        return createdby;
    }

    public void setCreatedby(long createdby) {
        this.createdby = createdby;
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
        if (!(object instanceof ConfigurationSubdistrict)) {
            return false;
        }
        ConfigurationSubdistrict other = (ConfigurationSubdistrict) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "in.nic.pes.lgd.bean.ConfigurationSubdistrict[ id=" + id + " ]";
    }
    
}
