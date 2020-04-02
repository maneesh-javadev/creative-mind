/**
 *  @author Maneesh Kumar 01-July-2015
 */
package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
@NamedNativeQuery(query="select *,null as population from get_freeze_data_for_localbody(:parentLocalbodyCode,:lbTypeCode,:stateCode)",name="GetFreezeDataForLocalbodyFn", resultClass=GetFreezeDataForLocalbody.class),
@NamedNativeQuery(query="select *,null as population from get_freeze_data_for_localbody(:parentLocalbodyCode,:lbTypeCode,:stateCode,:districtCode)",name="GetFreezeDataForLocalbodyFnDistrictUser", resultClass=GetFreezeDataForLocalbody.class),
@NamedNativeQuery(query="select * from get_freeze_data_for_localbody_population(:parentLocalbodyCode,:lbTypeCode,:stateCode,:districtCode)",name="GetFreezeDataForLocalbodyFnPopulation", resultClass=GetFreezeDataForLocalbody.class)
})
public class GetFreezeDataForLocalbody {
	
	@Id
	@Column(name="local_body_code")
	 private Integer localBodyCode;
	 
	 @Column(name="lblc")
	 private Integer lblc;
	 
	 @Column(name="local_body_name")
	 private String localBodyName;
	 
	 @Column(name="localbody_coverage")
	 private String localBodyCoverage;
	 
	 @Column(name="status")
	 private Integer status;
	 
	 @Column(name="population")
	 private Integer population;

	public Integer getLocalBodyCode() {
		return localBodyCode;
	}

	public void setLocalBodyCode(Integer localBodyCode) {
		this.localBodyCode = localBodyCode;
	}

	public Integer getLblc() {
		return lblc;
	}

	public void setLblc(Integer lblc) {
		this.lblc = lblc;
	}



	public String getLocalBodyCoverage() {
		return localBodyCoverage;
	}

	public void setLocalBodyCoverage(String localBodyCoverage) {
		this.localBodyCoverage = localBodyCoverage;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getLocalBodyName() {
		return localBodyName;
	}

	public void setLocalBodyName(String localBodyName) {
		this.localBodyName = localBodyName;
	}

	public Integer getPopulation() {
		return population;
	}

	public void setPopulation(Integer population) {
		this.population = population;
	}
	 
	 

}
