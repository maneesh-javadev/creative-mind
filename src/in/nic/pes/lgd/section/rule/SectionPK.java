package in.nic.pes.lgd.section.rule;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class SectionPK implements java.io.Serializable{
	
	@Column(name="Section_Code")
	private Integer sectionCode;
	
	@Column(name="Section_Version")
	private Integer sectionVersion;

	public Integer getSectionCode() {
		return sectionCode;
	}

	public void setSectionCode(Integer sectionCode) {
		this.sectionCode = sectionCode;
	}

	public Integer getSectionVersion() {
		return sectionVersion;
	}

	public void setSectionVersion(Integer sectionVersion) {
		this.sectionVersion = sectionVersion;
	}
	
	

}
