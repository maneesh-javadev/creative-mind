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
public class LocalbodyPK implements Serializable {
   
  
    /**
	 * 
	 */
	private static final long serialVersionUID = 2799400153792192615L;

	private Integer localBodyCode;  
   
    private Integer localBodyVersion;

    public LocalbodyPK() {
    }

    public LocalbodyPK(Integer localBodyCode, Integer localBodyVersion) {
        this.localBodyCode = localBodyCode;
        this.localBodyVersion = localBodyVersion;
    }
    
// TODO Remove unused code found by UCDetector
//     public LocalbodyPK(Integer localBodyVersion)
//     {
//     	this.localBodyVersion = localBodyVersion;
//     }

    @Column(name = "local_body_code", nullable = false)
	public Integer getLocalBodyCode() {
		return this.localBodyCode;
	}

	public void setLocalBodyCode(Integer localBodyCode) {
		this.localBodyCode = localBodyCode;
	}

	@Column(name = "local_body_version", nullable = false)
	public Integer getLocalBodyVersion() {
		return this.localBodyVersion;
	}

	public void setLocalBodyVersion(Integer localBodyVersion) {
		this.localBodyVersion = localBodyVersion;
	}
	
	@Override
	public int hashCode() {
	      int hash = 0;
	      hash += (Integer) localBodyCode;
	      hash += (Integer) localBodyVersion;
	      return hash;
	}
	
	@Override
	public boolean equals(Object other) {
		if ((this == other)){
			return true;
		}	
		if ((other == null)){
			return false;
		}	
		if (!(other instanceof LocalbodyPK)){
			return false;
		}	
		LocalbodyPK castOther = (LocalbodyPK) other;

		return (this.getLocalBodyCode() == castOther.getLocalBodyCode())
				&& (this.getLocalBodyVersion() == castOther
						.getLocalBodyVersion());
	}


}
