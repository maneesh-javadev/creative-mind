/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.nic.pes.lgd.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;



@Entity
@Table(name = "configuration_map_constituency", schema = "public")
public class ConfigurationMapConstituency implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int configurationMapConstituencyCode;
	//private State state;
	private char constituencyType;
	private String baseUrl;
	private boolean isactive;
	private boolean ismapupload;
	private Date lastupdated;
	private Long lastupdatedby;
	private Date createdon;
	private long createdby;
	private Integer slc;

	
	/*configuration_map_constituency_code integer NOT NULL,
	  constituency_type character(1) NOT NULL,
	  base_url character varying(100),
	  slc integer NOT NULL,
	  isactive boolean NOT NULL DEFAULT true,
	  ismapupload boolean NOT NULL DEFAULT true,
	  lastupdated time with time zone,
	  lastupdatedby bigint,
	  createdon timestamp with time zone NOT NULL,
	  createdby bigint NOT NULL,*/
	
	public ConfigurationMapConstituency() {
	}

// TODO Remove unused code found by UCDetector
// 	public ConfigurationMapConstituency(int configurationMapConstituencyCode,
// 			Integer slc, char constituencyType, boolean isactive,
// 			boolean ismapupload, Date createdon, long createdby) {
// 		this.configurationMapConstituencyCode = configurationMapConstituencyCode;
// 		
// 		this.constituencyType = constituencyType;
// 		this.isactive = isactive;
// 		this.ismapupload = ismapupload;
// 		this.createdon = createdon;
// 		this.createdby = createdby;
// 		this.slc=slc;
// 	}

// TODO Remove unused code found by UCDetector
// 	public ConfigurationMapConstituency(int configurationMapConstituencyCode,
// 			Integer slc, char constituencyType, String baseUrl,
// 			boolean isactive, boolean ismapupload, Date lastupdated,
// 			Long lastupdatedby, Date createdon, long createdby) {
// 		this.configurationMapConstituencyCode = configurationMapConstituencyCode;
// 		this.slc=slc;
// 		this.constituencyType = constituencyType;
// 		this.baseUrl = baseUrl;
// 		this.isactive = isactive;
// 		this.ismapupload = ismapupload;
// 		this.lastupdated = lastupdated;
// 		this.lastupdatedby = lastupdatedby;
// 		this.createdon = createdon;
// 		this.createdby = createdby;
// 	}

 	@GenericGenerator
    (name = "sequence", strategy = "sequence", parameters={@Parameter(name="sequence",value="configmapconstituency")})
 	@GeneratedValue(generator = "sequence")
 	@Id
 	@Column(name = "configuration_map_constituency_code")
	public int getConfigurationMapConstituencyCode() {
		return this.configurationMapConstituencyCode;
	}
	public void setConfigurationMapConstituencyCode(
			int configurationMapConstituencyCode) {
		this.configurationMapConstituencyCode = configurationMapConstituencyCode;
	}

	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "slc", referencedColumnName = "slc", nullable = false)})
	@Where(clause="isactive=true")
	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}*/

	@Column(name = "constituency_type", nullable = false, length = 1)
	public char getConstituencyType() {
		return this.constituencyType;
	}

	public void setConstituencyType(char constituencyType) {
		this.constituencyType = constituencyType;
	}

	@Column(name = "base_url", length = 100)
	public String getBaseUrl() {
		return this.baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	@Column(name = "isactive", nullable = false)
	public boolean isIsactive() {
		return this.isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	@Column(name = "ismapupload", nullable = false)
	public boolean isIsmapupload() {
		return this.ismapupload;
	}

	public void setIsmapupload(boolean ismapupload) {
		this.ismapupload = ismapupload;
	}

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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdon", nullable = false, length = 35)
	public Date getCreatedon() {
		return this.createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	@Column(name = "createdby", nullable = false)
	public long getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(long createdby) {
		this.createdby = createdby;
	}

	@Column(name = "slc")
	public Integer getSlc() {
		return slc;
	}

	public void setSlc(Integer slc) {
		this.slc = slc;
	}
		
}
