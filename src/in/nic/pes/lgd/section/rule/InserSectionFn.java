package in.nic.pes.lgd.section.rule;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
@NamedNativeQuery(query = "Select * from create_section(:section_name_english,"
														  + ":parent_codes,"
														  + ":parent_type,"
														  + ":parent_child_flg,"
														  + ":p_effective_date,"
														  + ":slc,"
														  + ":userid,"
														  + ":section_name_local,"
														  + ":address1,"
														  + ":address2,"
														  + ":address3,"
														  + ":pin_code,"
														  + ":localaddress1,"
														  + ":localaddress2,"
														  + ":localaddress3,"
														  + ":sectionShortName)"
														  , name = "Insert_Section_Details", resultClass = InserSectionFn.class)})


public class InserSectionFn {
	
	@Id
	@Column(name = "create_section", nullable = false)
	private String createSection;

	public String getCreateSection() {
		return createSection;
	}

	public void setCreateSection(String createSection) {
		this.createSection = createSection;
	}

	
	
	
	

}
