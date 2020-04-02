/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.nic.pes.lgd.bean;

//import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Chandan Soni
 */
@Entity
@Table(name = "flags")
public class Flags { // NO_UCD (use default)
  @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((flagCode == null) ? 0 : flagCode.hashCode());
		result = prime * result
				+ ((flagDescription == null) ? 0 : flagDescription.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj){	
			return true;
		}	
		if (obj == null){
			return false;
		}	
		if (getClass() != obj.getClass()){
			return false;
		}	
		Flags other = (Flags) obj;
		if (flagCode == null) {
			if (other.flagCode != null){
				return false;
			}	
		} else if (!flagCode.equals(other.flagCode)){
			return false;
		}	
		if (flagDescription == null) {
			if (other.flagDescription != null){
				return false;
			}	
		} else if (!flagDescription.equals(other.flagDescription)){
			return false;
		}	
		return true;
	}
private static final long serialVersionUID = 1L;
  //@NotNull
    
    private Integer flagCode;
  //@NotNull
    
    private String flagDescription;
    @Column(name = "flag_code")
	public Integer getFlagCode() {
		return flagCode;
	}
	public void setFlagCode(Integer flagCode) {
		this.flagCode = flagCode;
	}
	@Column(name = "flag_description")
	public String getFlagDescription() {
		return flagDescription;
	}
	public void setFlagDescription(String flagDescription) {
		this.flagDescription = flagDescription;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
