package ws.in.nic.pes.lgdws.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@NamedNativeQueries({
@NamedNativeQuery(query = "select l.local_body_code,cast(trim(local_body_name_english)as character varying)local_body_name_english from village v left join lb_covered_landregion lb on lb.lrlc=v.vlc and "
						+ " lb.land_region_type='V' left join localbody l on l.lb_covered_region_code=lb.lb_covered_region_code where v.isactive  and "
						+ " lb.isactive  and l.isactive  and v.vlc=:villageCode order by local_body_name_english ", name = "GP_LGD_Detail_RD_DEPT", resultClass = GPLGDDetailRDDEPT.class)})

@XmlRootElement(name = "GPLGDDetailRDDEPT")
@XmlAccessorType (XmlAccessType.FIELD)
@XmlType(propOrder={"localBodyCode","localBodyNameEnglish"})
public class GPLGDDetailRDDEPT {
	
	@Id
	@Column(name="local_body_code")
	@XmlElement(name="local-body-code")
	private Integer localBodyCode;
	
	@Column(name="local_body_name_english")
	@XmlElement(name="local-body-name-english")
	private String localBodyNameEnglish;

	public Integer getLocalBodyCode() {
		return localBodyCode;
	}

	public void setLocalBodyCode(Integer localBodyCode) {
		this.localBodyCode = localBodyCode;
	}

	public String getLocalBodyNameEnglish() {
		return localBodyNameEnglish;
	}

	public void setLocalBodyNameEnglish(String localBodyNameEnglish) {
		this.localBodyNameEnglish = localBodyNameEnglish;
	}
	
	
	
	
	

}
