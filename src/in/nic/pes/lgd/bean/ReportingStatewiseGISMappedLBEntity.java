package in.nic.pes.lgd.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Transient;

@Entity
@NamedNativeQueries({
	@NamedNativeQuery(query = "select * from get_statewise_gis_mapped_localbody()", name="globalViewStateGISMappedLocalBody", resultClass = ReportingStatewiseGISMappedLBEntity.class)
	
})

public class ReportingStatewiseGISMappedLBEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "state_name")
	private String stateName;
	
	@Column(name = "total_localbody")
	private long totalLocalBody;
	
	@Column(name = "mapped_localbody")
	private long mappedLocalBody;
	
	@Column(name = "mapped_percent")
	private BigDecimal mappedPercent;
	
	@Transient
	private String captchaAnswer;
	
	@Transient
	private String entitesForMessage;

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public long getTotalLocalBody() {
		return totalLocalBody;
	}

	public void setTotalLocalBody(long totalLocalBody) {
		this.totalLocalBody = totalLocalBody;
	}

	public long getMappedLocalBody() {
		return mappedLocalBody;
	}

	public void setMappedLocalBody(long mappedLocalBody) {
		this.mappedLocalBody = mappedLocalBody;
	}

	public BigDecimal getMappedPercent() {
		return mappedPercent;
	}

	public void setMappedPercent(BigDecimal mappedPercent) {
		this.mappedPercent = mappedPercent;
	}

	public String getCaptchaAnswer() {
		return captchaAnswer;
	}

	public void setCaptchaAnswer(String captchaAnswer) {
		this.captchaAnswer = captchaAnswer;
	}

	public String getEntitesForMessage() {
		return entitesForMessage;
	}

	public void setEntitesForMessage(String entitesForMessage) {
		this.entitesForMessage = entitesForMessage;
	}

	
}
