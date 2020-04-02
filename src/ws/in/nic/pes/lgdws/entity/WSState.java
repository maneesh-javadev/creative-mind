package ws.in.nic.pes.lgdws.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({@NamedNativeQuery(query="select state_code,state_name_english,state_name_local,census_2001_code,census_2011_code from state "
											+ "where isactive=true order by state_name_english",name="getStateList",resultClass=WSState.class),
					@NamedNativeQuery(query="select state_code,state_name_english,state_name_local,census_2001_code,census_2011_code  from state "
											+ "where isactive=true and state_code=:stateCode",name="getState",resultClass=WSState.class)})

public class WSState implements java.io.Serializable {
// TODO Remove unused code found by UCDetector
// 	public WSState(Integer stateCode, String stateNameEnglish) {
// 		super();
// 		this.stateCode = stateCode;
// 		this.stateNameEnglish = stateNameEnglish;
// 	}
	public WSState(){
		
	}
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="state_code")
	private Integer stateCode;
	
	@Column(name="state_name_english")
	private String stateNameEnglish;
	
	@Column(name="state_name_local")
	private String stateNameLocal;
	
	@Column(name="census_2001_code")
    private String census2001Code;
	
	@Column(name="census_2011_code")
	private String census2011Code;

	public Integer getStateCode() {
		return stateCode;
	}

	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateNameEnglish() {
		return (stateNameEnglish!=null?stateNameEnglish.trim():null);
	}

	public void setStateNameEnglish(String stateNameEnglish) {
		this.stateNameEnglish =stateNameEnglish ;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getStateNameLocal() {
		return (stateNameLocal!=null?stateNameLocal.trim():null);
	}
	public void setStateNameLocal(String stateNameLocal) {
		this.stateNameLocal =stateNameLocal ;
	}

	public String getCensus2001Code() {
		return census2001Code;
	}

	public void setCensus2001Code(String census2001Code) {
		this.census2001Code = census2001Code;
	}

	public String getCensus2011Code() {
		return census2011Code;
	}

	public void setCensus2011Code(String census2011Code) {
		this.census2011Code = census2011Code;
	}
	
	
	
}
