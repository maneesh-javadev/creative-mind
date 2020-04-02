package in.nic.pes.lgd.bean;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query="select * from pc_ac_mapping(:entity_code,:entity_type)", name="getDownloadDirectoryPCACMap", resultClass=DownloadDirectoryPCACMap.class)

public class DownloadDirectoryPCACMap implements Serializable

{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3503193852258513162L;

	@Basic(optional=false)
	@Column(name="parliament_constituency")
	private String parliamentConstituency;
	
	@Column(name="assembly_constituency")
	private String assembleyConstituency;
	
	@Column(name="classsification")
	private String classification;
	
	@Id
	@Column(name="local_body_name_english")
	private String localBodyNameEnglish;
	
	@Column(name="coverage_type")
	private String coverageType;
	
	public String getParliamentConstituency() {
		return parliamentConstituency;
	}
	public void setParliamentConstituency(String parliamentConstituency) {
		this.parliamentConstituency = parliamentConstituency;
	}
	
	public String getAssembleyConstituency() {
		return assembleyConstituency;
	}
	public void setAssembleyConstituency(String assembleyConstituency) {
		this.assembleyConstituency = assembleyConstituency;
	}
	
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	
	public String getLocalBodyNameEnglish() {
		return localBodyNameEnglish;
	}
	public void setLocalBodyNameEnglish(String localBodyNameEnglish) {
		this.localBodyNameEnglish = localBodyNameEnglish;
	}
	
	public String getCoverageType() {
		return coverageType;
	}
	public void setCoverageType(String coverageType) {
		this.coverageType = coverageType;
	}

}
