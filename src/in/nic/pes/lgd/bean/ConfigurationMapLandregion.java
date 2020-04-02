/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.nic.pes.lgd.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author 
 */
@Entity
@Table(name = "configuration_map_landregion", schema = "public")
public class ConfigurationMapLandregion implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int configurationMapLandregionCode;
	//private State state;
//	private String baseUrl;
	private boolean isactive;
//	private boolean ismapupload;
	private Date lastupdated;
	private Long lastupdatedby;
	private Date createdon;
	private long createdby;	
	private int slc;
	
	private Set<ConfigurationMapLandregionLevel> configurationMapLandregionLevels = new HashSet<ConfigurationMapLandregionLevel>(
			0);

	public ConfigurationMapLandregion() {
	}

/*	public ConfigurationMapLandregion(int configurationMapLandregionCode,
			State state, boolean isactive, boolean ismapupload, Date createdon,
			long createdby) {
		this.configurationMapLandregionCode = configurationMapLandregionCode;
		this.state = state;
		this.isactive = isactive;
		this.ismapupload = ismapupload;
		this.createdon = createdon;
		this.createdby = createdby;
	}*/

	
 	/*public ConfigurationMapLandregion(
			int configurationMapLandregionCode,
			State state,
			String baseUrl,
			boolean isactive,
			boolean ismapupload,
			Date lastupdated,
			Long lastupdatedby,
			Date createdon,
			long createdby,
			Set<ConfigurationMapLandregionLevel> configurationMapLandregionLevels) {
		super();
		this.configurationMapLandregionCode = configurationMapLandregionCode;
		this.state = state;
		this.baseUrl = baseUrl;
		this.isactive = isactive;
		this.ismapupload = ismapupload;
		this.lastupdated = lastupdated;
		this.lastupdatedby = lastupdatedby;
		this.createdon = createdon;
		this.createdby = createdby;
		this.configurationMapLandregionLevels = configurationMapLandregionLevels;
	}*/

 	@GenericGenerator
    (name = "sequence", strategy = "sequence", parameters={@Parameter(name="sequence",value="configmapland")})
	@GeneratedValue(generator = "sequence")
 	@Id	
	@Column(name = "configuration_map_landregion_code", unique = true, nullable = false)
 	@Basic(optional=false)
	public int getConfigurationMapLandregionCode() {
		return this.configurationMapLandregionCode;
	}

	public void setConfigurationMapLandregionCode(
			int configurationMapLandregionCode) {
		this.configurationMapLandregionCode = configurationMapLandregionCode;
	}
	/*@Basic(optional=false)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "slc", referencedColumnName = "slc", nullable = false)})
			//@JoinColumn(name = "state_version", referencedColumnName = "state_version", nullable = false) })
	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}*/
 
/*	@Column(name = "base_url", length = 100)
	public String getBaseUrl() {
		return this.baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}*/
	@Basic(optional=false)
	@Column(name = "isactive", nullable = false)
	public boolean isIsactive() {
		return this.isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
/*	@Basic(optional=false)
	@Column(name = "ismapupload", nullable = false)
	public boolean isIsmapupload() {
		return this.ismapupload;
	}

	public void setIsmapupload(boolean ismapupload) {
		this.ismapupload = ismapupload;
	}*/

	@Temporal(TemporalType.TIME)
	@Column(name = "lastupdated", length = 21)
	public Date getLastupdated() {
		return this.lastupdated;
	}

	public void setLastupdated(Date lastupdated) {
		this.lastupdated = lastupdated;
	}

	@Column(name = "lastupdatedby")
	public Long getLastupdatedby() {
		return this.lastupdatedby;
	}

	public void setLastupdatedby(Long lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}
	
	@Basic(optional=false)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdon", nullable = false, length = 35)
	public Date getCreatedon() {
		return this.createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}
	
	@Basic(optional=false)
	@Column(name = "createdby", nullable = false)
	public long getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(long createdby) {
		this.createdby = createdby;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "configurationMapLandregion", cascade={CascadeType.ALL} )
	public Set<ConfigurationMapLandregionLevel> getConfigurationMapLandregionLevels() {
		return this.configurationMapLandregionLevels;
	}

	public void setConfigurationMapLandregionLevels(
			Set<ConfigurationMapLandregionLevel> configurationMapLandregionLevels) {
		this.configurationMapLandregionLevels = configurationMapLandregionLevels;
	}

	@Column(name = "slc")
	public int getSlc() {
		return slc;
	}

	public void setSlc(int slc) {
		this.slc = slc;
	}


}

