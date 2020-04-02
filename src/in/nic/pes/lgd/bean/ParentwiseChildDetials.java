package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
		/*@NamedNativeQuery(query = "select l.local_body_code as entity_code,l.local_body_name_english entity_name_english,l.local_body_type_code as census_2001_code,l.census_2011_code,l.lblc,(select count(1) from "
				+ " localbody_ward lw where lw.isactive and lw.lblc=l.lblc) as no_of_tlc, null as no_of_vlc from localbody l, localbody_districts ld,local_body_type lt where l.local_body_code=ld.local_body_code and "
				+ " l.local_body_version=ld.local_body_version and l.isactive and ld.district_code=:districtCode and lt.local_body_type_code=l.local_body_type_code and lt.isactive and lt.category='U'", name = "list_of_ulbs", resultClass = ParentwiseChildDetials.class),*/
		@NamedNativeQuery(query = "Select * from get_parentwise_child_entity_detials(:parent_type,:parent_code,:category,:level)", name = "parentwiseChildDetailsFn", resultClass = ParentwiseChildDetials.class),
		
		@NamedNativeQuery(query = "Select  entity_code as entity_code ,entity_name_english as entity_name_english , count_of_BP as no_of_tlc, count_of_GP as no_of_vlc,cast(null as varchar) census_2011_code,cast(null as varchar) census_2001_code from parentwise_count_of_BPandGP(:districtCode,'D')", name = "parentwisecountofBPandGP", resultClass = ParentwiseChildDetials.class)

})
        





public class ParentwiseChildDetials {

	@Id
	@Column(name = "entity_code")
	private Integer entityCode;

	@Column(name = "entity_name_english")
	private String entityNameEnglish;

	@Column(name = "census_2001_code")
	private String census2001Code;

	@Column(name = "census_2011_code")
	private String census2011Code;

	@Column(name = "no_of_tlc")
	private Integer noOfTlc;

	@Column(name = "no_of_vlc")
	private Integer noOfVlc;

	public Integer getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(Integer entityCode) {
		this.entityCode = entityCode;
	}

	public String getEntityNameEnglish() {
		return entityNameEnglish;
	}

	public void setEntityNameEnglish(String entityNameEnglish) {
		this.entityNameEnglish = entityNameEnglish;
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

	public Integer getNoOfTlc() {
		return noOfTlc;
	}

	public void setNoOfTlc(Integer noOfTlc) {
		this.noOfTlc = noOfTlc;
	}

	public Integer getNoOfVlc() {
		return noOfVlc;
	}

	public void setNoOfVlc(Integer noOfVlc) {
		this.noOfVlc = noOfVlc;
	}

}
