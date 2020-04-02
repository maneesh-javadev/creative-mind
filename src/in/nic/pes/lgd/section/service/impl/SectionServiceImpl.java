package in.nic.pes.lgd.section.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nic.pes.lgd.section.dao.SectionDao;
import in.nic.pes.lgd.section.form.SectionForm;
import in.nic.pes.lgd.section.rule.Section;
import in.nic.pes.lgd.section.service.SectionService;

@Service
public class SectionServiceImpl implements SectionService{

	@Autowired
	private SectionDao sectionDao;
	
	@Override
	public String saveSectionDetails(SectionForm sectionForm) throws Exception {
		return sectionDao.saveSectionDetails(sectionForm);
	}

	@Override
	public List<Section> getSectionList(Integer parentCode, String parentType) throws Exception {
		return sectionDao.getSectionList(parentCode, parentType);
	}

	@Override
	public List<Section> getSectionListbyId(Integer sectionCode) throws Exception {
		return sectionDao.getSectionListbyId(sectionCode);
	}

	@Override
	public boolean sectionNameUniquewithParent(String sectionNameEng, Integer parentCode, String parentType) throws Exception {
		return sectionDao.sectionNameUniquewithParent(sectionNameEng, parentCode, parentType);
	}

	@Override
	public boolean updateSectionNameEnglish(SectionForm sectionForm) throws Exception {
		return sectionDao.updateSectionNameEnglish(sectionForm);
	}

	@Override
	public boolean deleteSection(Integer sectionCode) throws Exception {
		return sectionDao.deleteSection(sectionCode);
	}

	@Override
	public Object[] getManageSectionObject(Integer parentCode, String parentType) throws Exception {
		return sectionDao.getManageSectionObject(parentCode, parentType);
	}

	@Override
	public boolean reActiveSection(SectionForm sectionForm) throws Exception {
		return sectionDao.reActiveSection(sectionForm);
	}

}
