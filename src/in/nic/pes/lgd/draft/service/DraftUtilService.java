package in.nic.pes.lgd.draft.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.bean.WsUserRegistrationForm;
import in.nic.pes.lgd.draft.entities.LandRegionAttribute;
import in.nic.pes.lgd.draft.form.DraftGovermentOrderForm;

public interface DraftUtilService {
	
	public LandRegionAttribute onLoadLandregionEntity(Integer stateCode,String entityType,Integer operationCode)throws Exception;
	public AttachmentMaster getUploadFileConfigurationDetails(Long fileMasterId) throws HibernateException;
	public void saveGovermentOrderinAttachement(DraftGovermentOrderForm draftGovermentOrderForm,Session session)throws Exception;
	public DraftGovermentOrderForm getDraftGovermentOrderDetails(DraftGovermentOrderForm draftGovermentOrderForm) throws Exception;
	public Integer updateDraftGovermentOrderDetails(DraftGovermentOrderForm draftGovermentOrderForm,HttpServletRequest request,HttpSession httpSession) throws Exception;
	public String fillTemplateDetailsByEntity(DraftGovermentOrderForm draftGovermentOrderForm,HttpSession httpSession)throws Exception;

}
