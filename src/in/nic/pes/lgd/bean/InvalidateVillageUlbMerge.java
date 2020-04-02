/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.nic.pes.lgd.bean;

//import java.io.Serializable;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ulb_village_mapping")
public class InvalidateVillageUlbMerge implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer ulbCode;
	private Integer ulbVersion;
	private Integer villageCode;
	private Integer villageVersion;
	private Integer id;
	
	@Id
	    @GeneratedValue
	    public Integer getId()
	    {
	        return id;
	    }

	    public void setId(Integer id)
	    {
	        this.id = id;
	    }
	@Column(name = "ulb_code")
	public Integer getUlbCode() {
		return ulbCode;
	}
	public void setUlbCode(Integer ulbCode) {
		this.ulbCode = ulbCode;
	}
	@Column(name = "ulb_version")
	public Integer getUlbVersion() {
		return ulbVersion;
	}
	public void setUlbVersion(Integer ulbVersion) {
		this.ulbVersion = ulbVersion;
	}
	@Column(name = "village_code")
	public Integer getVillageCode() {
		return villageCode;
	}
	public void setVillageCode(Integer villageCode) {
		this.villageCode = villageCode;
	}
	@Column(name = "village_version")
	public Integer getVillageVersion() {
		return villageVersion;
	}
	public void setVillageVersion(Integer villageVersion) {
		this.villageVersion = villageVersion;
	}
// TODO Remove unused code found by UCDetector
// 	public static long getSerialversionuid() {
// 		return serialVersionUID;
// 	}
	
	

}
