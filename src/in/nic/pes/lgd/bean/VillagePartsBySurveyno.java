package in.nic.pes.lgd.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "village_parts_by_surveyno")
public class VillagePartsBySurveyno implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 @GenericGenerator (name = "sequence", strategy = "sequence", parameters={@Parameter(name="sequence",value="villagepartsbyssurveyno")})
	 @Id
	 @GeneratedValue(generator = "sequence")
     @Column(name = "survey_code", nullable = false)
	private int surveyCode;

	@Column(name = "survey_number")
	private String surveyNumber;
		
	@Column(name = "village_code",insertable=false,updatable=false)
	private int villageCode;
		
	@Column(name = "village_version",insertable=false,updatable=false)
	private int villageVersion;
		
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumns({
		@JoinColumn(name = "village_code", referencedColumnName = "village_code"),
		@JoinColumn(name = "village_version", referencedColumnName = "village_version") })
	private Village village;

   
	public int getSurveyCode() {
		return surveyCode;
	}

	public void setSurveyCode(Integer surveyCode) {
		this.surveyCode = surveyCode;
	}

	public String getSurveyNumber() {
		return surveyNumber;
	}

	public void setSurveyNumber(String surveyNumber) {
		this.surveyNumber = surveyNumber;
	}

	
	public int getVillageCode() {
		return villageCode;
	}

	public void setVillageCode(int villageCode) {
		this.villageCode = villageCode;
	}

	
	public int getVillageVersion() {
		return villageVersion;
	}

	public void setVillageVersion(int villageVersion) {
		this.villageVersion = villageVersion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	public Village getVillage() {
		return village;
	}

	public void setVillage(Village village) {
		this.village = village;
	}

	public void setSurveyCode(int surveyCode) {
		this.surveyCode = surveyCode;
	}

}
