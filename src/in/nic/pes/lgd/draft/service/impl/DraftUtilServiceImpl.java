package in.nic.pes.lgd.draft.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.draft.dao.DraftUtilDao;
import in.nic.pes.lgd.draft.entities.LandRegionAttribute;
import in.nic.pes.lgd.draft.form.DraftGovermentOrderForm;
import in.nic.pes.lgd.draft.form.DraftSubdistrictForm;
import in.nic.pes.lgd.draft.service.DraftUtilService;

@Service 
public class DraftUtilServiceImpl implements DraftUtilService {
	
	
	@Autowired
	DraftUtilDao draftUtilDao;

	@Override
	public LandRegionAttribute onLoadLandregionEntity(Integer stateCode, String entityType, Integer operationCode)throws Exception {
		return draftUtilDao.onLoadLandregionEntity(stateCode, entityType, operationCode);
	}

	@Override
	public AttachmentMaster getUploadFileConfigurationDetails(Long fileMasterId) throws HibernateException {
		return draftUtilDao.getUploadFileConfigurationDetails(fileMasterId);
	}

	@Override
	public void saveGovermentOrderinAttachement(DraftGovermentOrderForm draftGovermentOrderForm, Session session) throws Exception {
		 draftUtilDao.saveGovermentOrderinAttachement(draftGovermentOrderForm, session);
	}

	@Override
	public DraftGovermentOrderForm getDraftGovermentOrderDetails(DraftGovermentOrderForm draftGovermentOrderForm)throws Exception {
		return draftUtilDao.getDraftGovermentOrderDetails(draftGovermentOrderForm);
	}

	@Override
	public Integer updateDraftGovermentOrderDetails(DraftGovermentOrderForm draftGovermentOrderForm,HttpServletRequest request,HttpSession httpSession) throws Exception {
		return draftUtilDao.updateDraftGovermentOrderDetails(draftGovermentOrderForm,request,httpSession);
	}

	@Override
	public String fillTemplateDetailsByEntity(DraftGovermentOrderForm draftGovermentOrderForm, HttpSession httpSession) throws Exception {
		return draftUtilDao.fillTemplateDetailsByEntity(draftGovermentOrderForm, httpSession);
	}

}
