package in.nic.pes.lgd.section.dao;

import java.util.List;

import in.nic.pes.lgd.section.form.SectionForm;
import in.nic.pes.lgd.section.rule.Section;

public interface SectionDao {

	public String saveSectionDetails(SectionForm sectionForm) throws Exception;
	
	public List<Section> getSectionList(Integer parentCode,String parentType)throws Exception;
	
	public List<Section> getSectionListbyId(Integer sectionCode)throws Exception;
	
	public boolean sectionNameUniquewithParent(String sectionNameEng,Integer parentCode,String parentType)throws Exception;
	
	public boolean updateSectionNameEnglish(SectionForm sectionForm)throws Exception;
	
	public boolean deleteSection(Integer sectionCode) throws Exception;
	
	public  Object[] getManageSectionObject(Integer parentCode,String parentType)throws Exception;
	
	public boolean reActiveSection(SectionForm sectionForm)throws Exception;

}
