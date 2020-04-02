package in.nic.pes.lgd.service.impl;

import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.bean.ConfigurationGovernmentOrder;
import in.nic.pes.lgd.bean.GetGovernmentOrderDetail;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.GovernmentOrderTemplate;
import in.nic.pes.lgd.bean.LandRegionDetail;
import in.nic.pes.lgd.bean.MapAttachment;
import in.nic.pes.lgd.bean.MimetypeMaster;
import in.nic.pes.lgd.bean.Subdistrict;
import in.nic.pes.lgd.bean.ViewConfigMapLandRegion;
import in.nic.pes.lgd.bean.ViewConfigMapLocalBody;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.bean.VillageDraft;
import in.nic.pes.lgd.dao.BlockDAO;
import in.nic.pes.lgd.dao.DistrictDAO;
import in.nic.pes.lgd.dao.GovernmentOrderDAO;
import in.nic.pes.lgd.dao.LocalGovtBodyDAO;
import in.nic.pes.lgd.dao.ShiftDAO;
import in.nic.pes.lgd.dao.StateDAO;
import in.nic.pes.lgd.dao.SubDistrictDAO;
import in.nic.pes.lgd.dao.VillageDAO;
import in.nic.pes.lgd.forms.BlockDataForm;
import in.nic.pes.lgd.forms.BlockForm;
import in.nic.pes.lgd.forms.ConvertRLBtoTLBForm;
import in.nic.pes.lgd.forms.ConvertRLBtoULBForm;
import in.nic.pes.lgd.forms.ConvertTLBtoRLBForm;
import in.nic.pes.lgd.forms.DistrictDataForm;
import in.nic.pes.lgd.forms.DistrictForm;
import in.nic.pes.lgd.forms.GenerateDetails;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.forms.LocalGovtBodyForm;
import in.nic.pes.lgd.forms.ShiftBlockForm;
import in.nic.pes.lgd.forms.ShiftDistrictForm;
import in.nic.pes.lgd.forms.ShiftSubDistrictForm;
import in.nic.pes.lgd.forms.ShiftVillageForm;
import in.nic.pes.lgd.forms.StateDataForm;
import in.nic.pes.lgd.forms.StateForm;
import in.nic.pes.lgd.forms.SubDistrictForm;
import in.nic.pes.lgd.forms.SubdistrictDataForm;
import in.nic.pes.lgd.forms.VillageDataForm;
import in.nic.pes.lgd.forms.VillageForm;
import in.nic.pes.lgd.service.ConfigMapService;
import in.nic.pes.lgd.service.ConvertLocalbodyService;
import in.nic.pes.lgd.service.GovernmentOrderService;
import in.nic.pes.lgd.service.GovtOrderTemplateService;
import in.nic.pes.lgd.service.ReportService;
import in.nic.pes.lgd.service.VillageService;
import in.nic.pes.lgd.utils.CurrentDateTime;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import pes.attachment.util.AddAttachmentBean;
import pes.attachment.util.AttachedFilesForm;

import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

@Service
public class GovernmentOrderServiceImpl implements GovernmentOrderService {

	private static final Logger log = Logger.getLogger(GovernmentOrderServiceImpl.class);

	@Autowired
	ShiftDAO shiftDao;

	@Autowired
	DistrictDAO districtDAO;

	@Autowired
	StateDAO stateDao;

	@Autowired
	SubDistrictDAO subdistrictDAO;

	@Autowired
	BlockDAO blockDAO;

	@Autowired
	ConfigMapService configMapService;

	@Autowired
	VillageDAO villageDAO;

	@Autowired
	ConvertLocalbodyService convertLocalbodyService;

	@Autowired
	LocalGovtBodyDAO localGovtBodyDAO;

	@Autowired
	CurrentDateTime dateTimeUtil;

	@Autowired
	GovernmentOrderDAO govtOrderDAO;

	@Autowired
	VillageService villageService;

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	GovtOrderTemplateService govtOrderTemplateService;

	@Autowired
	ReportService reportService;

	public void saveGovernmentOrder(String orderNo, Date orderDate, Date effectiveDate, Date gazPubDate, String issuedBy, String orderPath, MultipartFile filename, HttpServletRequest request) throws Exception {
		long lastupadtedby = 4000;
		Date currentDate = dateTimeUtil.getCurrentDate();
		GovernmentOrder govtOrder = new GovernmentOrder();
		govtOrder.setOrderDate(orderDate);
		govtOrder.setEffectiveDate(effectiveDate);
		govtOrder.setOrderNo(orderNo);
		govtOrder.setGazPubDate(gazPubDate);
		govtOrder.setDescription("dfshj");
		govtOrder.setCreatedby(324);
		govtOrder.setCreatedon(currentDate);
		govtOrder.setLastupdated(currentDate);
		govtOrder.setLastupdatedby(lastupadtedby);
		govtOrder.setIssuedBy(issuedBy);
		govtOrder.setLevel("1");
		writeMap(filename, request);
		@SuppressWarnings("deprecation")
		String filePath = request.getRealPath("/") + filename.getOriginalFilename();
		govtOrder.setOrderPath(filePath);
		govtOrder.setUserId(1);
		govtOrder.setXmlDbPath("sdf");
		govtOrder.setXmlOrderPath("");
		govtOrderDAO.saveGovernmentOrder(govtOrder);
	}

	/*
	 * @Override public String getGovOrderPath(Integer orderCode)throws
	 * Exception { String path=null; Session hsession=null;
	 * hsession=sessionFactory.openSession(); Transaction
	 * tx=hsession.beginTransaction(); String SQL=
	 * "select file_location from attachment where announcement_id='"
	 * +orderCode+"'";
	 * 
	 * try {
	 * 
	 * path=govtOrderDAO.getGovOrderPathDAO(orderCode, SQL, hsession);
	 * 
	 * tx.commit();
	 * 
	 * } catch(Exception e) { log.debug("Exception" + e); tx.rollback(); }
	 * finally { if (hsession != null && hsession.isOpen()){ hsession.close(); }
	 * } return path; }
	 */

	@Override
	public void saveGovtOrder(String orderNo, Date orderDate, Date effectiveDate, Date gazPubDate, String issuedBy, String orderPath, String filePath, HttpServletRequest request) throws Exception {
		// long lastupadtedby = 125;
		Date currentDate = dateTimeUtil.getCurrentDate();
		GovernmentOrder govtOrder = new GovernmentOrder();
		govtOrder.setOrderDate(orderDate);
		govtOrder.setEffectiveDate(effectiveDate);
		govtOrder.setOrderNo(orderNo);
		govtOrder.setGazPubDate(gazPubDate);
		govtOrder.setDescription("Govt Order Created");
		govtOrder.setCreatedby(1000);
		govtOrder.setCreatedon(currentDate);
		/*
		 * govtOrder.setLastupdated(currentDate);
		 * govtOrder.setLastupdatedby(lastupadtedby);
		 */
		govtOrder.setIssuedBy(issuedBy);
		govtOrder.setLevel("1");
		govtOrder.setOrderPath(filePath);
		// System.out.println(filePath);
		govtOrder.setUserId(1);
		govtOrder.setXmlDbPath("sdf");
		govtOrder.setXmlOrderPath("");
		govtOrderDAO.saveGovernmentOrder(govtOrder);
	}

	/*
	 * public void saveGovernmentOrder(String orderNo, Date orderDate,Date
	 * effectiveDate, Date gazPubDate, String issuedBy, String
	 * orderPath,MultipartFile filename,String
	 * landRegionCodes,HttpServletRequest request) { long lastupadtedby=125;
	 * GovernmentOrderEntityWise govtorderEntity=new
	 * GovernmentOrderEntityWise(); Date
	 * currentDate=dateTimeUtil.getCurrentDate(); GovernmentOrder govtOrder =
	 * new GovernmentOrder(); govtOrder.setOrderDate(orderDate);
	 * govtOrder.setEffectiveDate(effectiveDate); govtOrder.setOrderNo(orderNo);
	 * govtOrder.setGazPubDate(gazPubDate); govtOrder.setDescription("dfshj");
	 * govtOrder.setCreatedby(324); govtOrder.setCreatedon(currentDate);
	 * govtOrder.setLastupdated(currentDate);
	 * govtOrder.setLastupdatedby(lastupadtedby);
	 * govtOrder.setIssuedBy(issuedBy); govtOrder.setLevel("1");
	 * writeMap(filename,request); String filePath= request.getRealPath("/")+
	 * filename.getOriginalFilename() ; govtOrder.setOrderPath(filePath);
	 * System.out.println(filePath); govtOrder.setUserId(1);
	 * govtOrder.setXmlDbPath("sdf"); govtOrder.setXmlOrderPath(""); int
	 * orderCode=govtOrderDAO.saveGovernmentOrder1(govtOrder); GovernmentOrder
	 * govtOrder1=new GovernmentOrder(orderCode);
	 * if(landRegionCodes.contains(",")) { for(int i=0;i<)
	 * govtorderEntity.setEntityVersion(entityVersion);
	 * govtorderEntity.setEntityCode(entityCode) } }
	 */

	public boolean writeMap(MultipartFile filePath, HttpServletRequest request) throws Exception {

		FileOutputStream fo = null;
		try {
			MultipartFile map = filePath;

			if (map.getBytes().length > 0) {

				@SuppressWarnings("deprecation")
				String savePath = request.getRealPath("/") + map.getOriginalFilename();

				// String savePath =request.getRealPath("/") + "uploadedimages";
				// System.out.println("SavePath in for uploadFiles --" +
				// savePath);
				File ff = new File(savePath);
				ff.createNewFile();
				fo = new FileOutputStream(ff);
				fo.write(map.getBytes());
				if (!ff.exists()) {
					ff.mkdirs();
				}
			}
		} catch (Exception e) {
			return false;
		} finally {
			try {
				if (fo != null) {
					fo.close();
				}
			} catch (IOException e) {

			}
		}
		return true;
	}

	@Override
	public void updateGovernmentOrder(String orderNo, int orderCode, Date orderDate, Date effectiveDate, Date gazPubDate, String issuedBy, String orderPath, MultipartFile filename, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		long lastupadtedby = 125;
		Date currentDate = dateTimeUtil.getCurrentDate();
		GovernmentOrder govtOrder = new GovernmentOrder();
		govtOrder.setOrderCode(orderCode);
		govtOrder.setOrderDate(orderDate);
		govtOrder.setEffectiveDate(effectiveDate);
		govtOrder.setOrderNo(orderNo);
		govtOrder.setGazPubDate(gazPubDate);
		govtOrder.setDescription("update");
		govtOrder.setCreatedby(324);
		govtOrder.setCreatedon(currentDate);
		govtOrder.setLastupdated(currentDate);
		govtOrder.setLastupdatedby(lastupadtedby);
		govtOrder.setIssuedBy(issuedBy);
		govtOrder.setLevel("1");
		writeMap(filename, request);

		@SuppressWarnings("deprecation")
		String filePath = request.getRealPath("/") + filename.getOriginalFilename();
		govtOrder.setOrderPath(filePath);

		govtOrder.setUserId(1);
		govtOrder.setXmlDbPath("sdf");
		govtOrder.setXmlOrderPath("");
		govtOrderDAO.updateGovernmentOrder(govtOrder);
	}

	@Override
	public GovernmentOrder updateGovernmentOrder(String orderNo, int orderCode, Date orderDate, Date effectiveDate, Date gazPubDate, String issuedBy, HttpServletRequest request, Session session) throws Exception {
		// TODO Auto-generated method stub
		long lastupadtedby = 125;
		Date currentDate = dateTimeUtil.getCurrentDate();
		GovernmentOrder govtOrder = new GovernmentOrder();
		govtOrder.setOrderCode(orderCode);
		govtOrder.setOrderDate(orderDate);
		govtOrder.setEffectiveDate(effectiveDate);
		govtOrder.setOrderNo(orderNo);
		govtOrder.setGazPubDate(gazPubDate);
		govtOrder.setDescription("update");
		govtOrder.setCreatedby(324);
		govtOrder.setCreatedon(currentDate);
		govtOrder.setLastupdated(currentDate);
		govtOrder.setLastupdatedby(lastupadtedby);
		govtOrder.setIssuedBy(issuedBy);
		govtOrder.setLevel("1");
		govtOrder.setUserId(1);
		govtOrder.setXmlDbPath("sdf");
		govtOrder.setXmlOrderPath("");
		govtOrderDAO.updateGovernmentOrder(govtOrder, session);
		return govtOrder;
	}

	@Override
	public List<GovernmentOrder> getOrderDetailsbyOrderCode(int orderCode) throws Exception {
		List<GovernmentOrder> goList = null;
		goList = new ArrayList<GovernmentOrder>();

		try {
			goList = govtOrderDAO.getOrderDetailsbyOrderCode(orderCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
		}
		return goList;
	}

	@Override
	public boolean saveMetaData(List<AttachedFilesForm> attachedList, GovernmentOrderForm govtOrder) throws Exception {
		Session session = null;
		Transaction txn = null;
		try {

			session = sessionFactory.openSession();
			txn = session.beginTransaction();

			govtOrderDAO.updateAttachmentDetail(attachedList, govtOrder, session);
			txn.commit();
			return true;
		} catch (Exception e) {
			log.debug("Exception" + e);
			if (txn != null) {
				txn.rollback();
			}
			return false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

	}

	@Override
	public List<Attachment> getMetaData() throws Exception {
		List<Attachment> list = null;
		Session session = null;
		Transaction txn = null;
		try {

			session = sessionFactory.openSession();
			txn = session.beginTransaction();
			list = govtOrderDAO.getAttachmentDetails(session);
			txn.commit();
		} catch (Exception e) {
			log.debug("Exception" + e);
			if (txn != null) {
				txn.rollback();
			}
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return list;
	}

	@Override
	public List<String> getMimeTypeList() throws Exception {
		List<String> allowedMimeTypeList = new ArrayList<String>();
		Session session = null;
		Transaction txn = null;
		try {
			session = sessionFactory.openSession();
			txn = session.beginTransaction();
			List<MimetypeMaster> mimeTypeList = govtOrderDAO.getMimeTypeList(session);
			Iterator<MimetypeMaster> iterator = mimeTypeList.iterator();
			while (iterator.hasNext()) {
				MimetypeMaster mtm = (MimetypeMaster) iterator.next();
				String mimeType = mtm.getMimetypeName();
				allowedMimeTypeList.add(mimeType);
			}
			txn.commit();
		} catch (Exception e) {
			log.debug("Exception" + e);
			if (txn != null) {
				txn.rollback();
			}
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return allowedMimeTypeList;
	}

	public AttachmentMaster getGenerateFileLocation(long filemasterid) throws Exception {
		List<AttachmentMaster> fileMasterList = null;
		AttachmentMaster attachmentMaster = new AttachmentMaster();
		try {
			fileMasterList = govtOrderDAO.getGenerateFileLocation(filemasterid);

			Iterator<AttachmentMaster> iterator = fileMasterList.iterator();
			while (iterator.hasNext()) {
				AttachmentMaster master = (AttachmentMaster) iterator.next();
				attachmentMaster.setRequireTitle(master.getRequireTitle());
				attachmentMaster.setUniqueTitle(master.getUniqueTitle());
				attachmentMaster.setFileLocation(master.getFileLocation());
				attachmentMaster.setFileLimit(master.getFileLimit());
				attachmentMaster.setFileType(master.getFileType());
				attachmentMaster.setIndividualFileSize(master.getIndividualFileSize());
				attachmentMaster.setTotalFileSize(master.getTotalFileSize());
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return attachmentMaster;
	}

	/*
	 * @Override public void saveDataInAttachment(GovernmentOrder govtOrder,
	 * GovernmentOrderForm govtForm, List<AttachedFilesForm> attachedList,
	 * Session session) throws Exception {
	 * 
	 * Attachment attachment = null; try { Iterator<AttachedFilesForm> it =
	 * attachedList.iterator(); while (it.hasNext()) { AttachedFilesForm
	 * filesForm = (AttachedFilesForm) it.next(); attachment = new Attachment();
	 * attachment.setGovernmentOrder(govtOrder);
	 * attachment.setFileName(filesForm.getFileName());
	 * attachment.setFileSize(filesForm.getFileSize());
	 * attachment.setFileContentType(filesForm.getFileType());
	 * attachment.setFileLocation(filesForm.getFileLocation());
	 * attachment.setFileTimestamp(filesForm.getFileTimestampName());
	 * session.save(attachment); session.flush(); if
	 * (session.contains(attachment)){ session.evict(attachment); } } } catch
	 * (Exception e) { // TODO Auto-generated catch block log.debug("Exception"
	 * + e); } }
	 */

	@Override
	public void saveDataInAttachmentWithOrderCodeInGenrate(Integer orderCode, GenerateDetails filesForm, Session session) throws Exception {
		GovernmentOrder govtOrder = new GovernmentOrder();
		govtOrder.setOrderCode(orderCode);
		Attachment attachment = null;
		try {
			if (filesForm != null) {

				attachment = new Attachment();
				attachment.setGovernmentOrder(govtOrder);
				// attachment.setAnnouncement_id(orderCode);
				attachment.setFileName(filesForm.getFileName());
				attachment.setFileSize(filesForm.getFileSize());
				attachment.setFileContentType(filesForm.getFileContentType());
				attachment.setFileLocation(filesForm.getFileLocation());
				attachment.setFileTimestamp(filesForm.getFileTimestamp());
				session.save(attachment);
			}
			/*
			 * session.flush(); if (session.contains(attachment))
			 * session.evict(attachment);
			 */

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
		}
	}

	@Override
	public void saveDataInAttachmentWithOrderCode(Integer orderCode, List<AttachedFilesForm> attachedList, Session session) throws Exception {
		GovernmentOrder govtOrder = new GovernmentOrder();
		govtOrder.setOrderCode(orderCode);
		Attachment attachment = null;
		try {
			if (attachedList != null) {
				Iterator<AttachedFilesForm> it = attachedList.iterator();
				while (it.hasNext()) {
					AttachedFilesForm filesForm = (AttachedFilesForm) it.next();
					attachment = new Attachment();
					attachment.setGovernmentOrder(govtOrder);
					// attachment.setAnnouncement_id(orderCode);
					attachment.setFileName(filesForm.getFileName());
					attachment.setFileSize(filesForm.getFileSize());
					attachment.setFileContentType(filesForm.getFileType());
					attachment.setFileLocation(filesForm.getFileLocation());
					attachment.setFileTimestamp(filesForm.getFileTimestampName());
					session.save(attachment);
				}
				/*
				 * session.flush(); if (session.contains(attachment))
				 * session.evict(attachment);
				 */
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
		}
	}

	/*
	 * @Override public boolean saveDataInGovtOrderEntityWise(GovernmentOrder
	 * govtOrder, String codes, char entityType, Session session) throws
	 * Exception {
	 * 
	 * GovernmentOrderEntityWise govtOrderEWise = new
	 * GovernmentOrderEntityWise(); int version = 0; int code = 0; String[]
	 * codeList = codes.split(",");
	 * 
	 * try {
	 * 
	 * for (int i = 0; i < codeList.length; i++) { if (codeList[i] != null) {
	 * code = Integer.parseInt(codeList[i].toString()); if (code > 0) { if
	 * (entityType == 'D') { version = districtDAO
	 * .getMaxDistrictVersionbyCode(code); } else if (entityType == 'T') {
	 * version = subdistrictDAO .getMaxSubdistrictVersion(code); } else if
	 * (entityType == 'B') { version = blockDAO.getMaxBlockVersionbyCode(code);
	 * } else if (entityType == 'V') { version = villageDAO
	 * .getMaxVillageVersionbyCode(code); }
	 * govtOrderEWise.setGovernmentOrder(govtOrder);
	 * govtOrderEWise.setEntityCode(code);
	 * govtOrderEWise.setEntityVersion(version);
	 * govtOrderEWise.setEntityType(entityType); } } govtOrderDAO
	 * .saveOrderDetailsEntityWise(govtOrderEWise, session); } } catch
	 * (Exception e) {
	 * 
	 * } return true; }
	 */

	public AttachmentMaster getUploadFileConfigurationDetails(long fileMasterId) throws Exception {
		List<AttachmentMaster> fileMasterList = govtOrderDAO.getUploadFileConfigurationDetails(fileMasterId);
		AttachmentMaster attachmentMaster = new AttachmentMaster();
		Iterator<AttachmentMaster> iterator = fileMasterList.iterator();
		while (iterator.hasNext()) {
			AttachmentMaster master = (AttachmentMaster) iterator.next();
			attachmentMaster.setRequireTitle(master.getRequireTitle());
			attachmentMaster.setUniqueTitle(master.getUniqueTitle());
			attachmentMaster.setFileLocation(master.getFileLocation());
			attachmentMaster.setFileLimit(master.getFileLimit());
			attachmentMaster.setFileType(master.getFileType());
			attachmentMaster.setIndividualFileSize(master.getIndividualFileSize());
			attachmentMaster.setTotalFileSize(master.getTotalFileSize());
		}
		return attachmentMaster;

	}

	public List<Attachment> getAttachmentsbyOrderCode(int orderCode) throws Exception {
		return subdistrictDAO.getAttacmentdetail(orderCode);
	}

	public List<GetGovernmentOrderDetail> getGovtDetailsbyEntityCodeandVersion(Integer localBodyCode, Integer localBodyVersion, char type) throws Exception {
		return subdistrictDAO.GovOrderDetail(type, localBodyCode, localBodyVersion);
	}

	public List<GovernmentOrder> getGovtOrederDetails() {
		return localGovtBodyDAO.GovtOrederDetails();
	}

	@Override
	public String previewTemplate(int templCode, HttpSession httpSession) throws Exception {
		// TODO previewTemplate
		List<GovernmentOrderTemplate> templateList = null;
		templateList = new ArrayList<GovernmentOrderTemplate>();
		String templateBodySrc = null;
		String stateNameEng=httpSession.getAttribute("stateNameEng")!=null?httpSession.getAttribute("stateNameEng").toString():null;
		try {

			templateList = govtOrderTemplateService.getTemplateDetailsByTemplateCode(templCode);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}

		if (httpSession.getAttribute("formbean") instanceof VillageForm) {
			VillageForm villageFormBean = null;
			villageFormBean = new VillageForm();
			villageFormBean = (VillageForm) httpSession.getAttribute("formbean");
			char operation = villageFormBean.getOperation();
			if (templateList != null) {
				if (templateList.size() == 1) {

					if (templateList.get(0).getTemplateDescription() != null) {
						templateBodySrc = templateList.get(0).getTemplateDescription();
					}
					if (templateList.get(0).getTemplateRegional() != null) {
						templateBodySrc = templateList.get(0).getTemplateRegional();
					}
					Format sdf;
					sdf = new SimpleDateFormat("dd/MM/yyyy");
					if (operation == 'C') {

						if (villageFormBean.getOrderNo() != null) {
							templateBodySrc = templateBodySrc.replace("{OrderNo}", villageFormBean.getOrderNo());
						}

						if (villageFormBean.getOrderDate() != null) {
							templateBodySrc = templateBodySrc.replace("{OrderDate}", sdf.format(villageFormBean.getOrderDate()));
						}

						if (villageFormBean.getNewVillageNameEnglish() != null) {
							templateBodySrc = templateBodySrc.replace("{NameofnewVillage}", villageFormBean.getNewVillageNameEnglish());
						}

						if (villageFormBean.getEffectiveDate() != null) {
							templateBodySrc = templateBodySrc.replace("{EffectiveDate}", sdf.format(villageFormBean.getEffectiveDate()));
						}

						if (villageFormBean.getGazPubDate() != null) {
							templateBodySrc = templateBodySrc.replace("{GazettedPublicationDate}", sdf.format(villageFormBean.getGazPubDate()));
						}
					}

					else if (operation == 'I') {
						List<List<Village>> villageList = null;
						villageList = new ArrayList<List<Village>>();
						villageList = villageService.getVillageViewList(villageFormBean);
						String villagesNameofInvalidatedVillage = "";
						// Village villageBean = null;
						for (int i = 0; i < villageList.size(); i++) {
							// villageBean = new Village();
							villagesNameofInvalidatedVillage += villageList.get(i).get(0).getVillageNameEnglish() + ",";
						}

						villagesNameofInvalidatedVillage = villagesNameofInvalidatedVillage.substring(0, villagesNameofInvalidatedVillage.length() - 1);
						if (villageFormBean.getOrderNo() != null) {
							templateBodySrc = templateBodySrc.replace("{OrderNo}", villageFormBean.getOrderNo());
						}

						if (villageFormBean.getOrderDate() != null) {
							templateBodySrc = templateBodySrc.replace("{OrderDate}", sdf.format(villageFormBean.getOrderDate()));
						}

						if (villagesNameofInvalidatedVillage != null) {
							templateBodySrc = templateBodySrc.replace("{NameoftheVillagesInvalidated}", villagesNameofInvalidatedVillage);
						}

						if (villageFormBean.getEffectiveDate() != null) {
							templateBodySrc = templateBodySrc.replace("{EffectiveDate}", sdf.format(villageFormBean.getEffectiveDate()));
						}

						if (villageFormBean.getGazPubDate() != null) {
							templateBodySrc = templateBodySrc.replace("{GazettedPublicationDate}", sdf.format(villageFormBean.getGazPubDate()));
						}
					} else if (operation == 'M') {
						List<VillageDataForm> listVillages = new ArrayList<VillageDataForm>();
						listVillages = villageFormBean.getListVillageDetails();
						Iterator<VillageDataForm> itr = listVillages.iterator();
						while (itr.hasNext()) {
							VillageDataForm element = (VillageDataForm) itr.next();
							// int villageCode = element.getVillageCode();
							// int villageVersion =
							// element.getVillage_version();
							villageFormBean.setNewVillageNameEnglish(element.getVillageNameEnglishCh());
							villageFormBean.setNewVillageAliasEnglish(element.getAliasEnglishCh());
						}
						templateBodySrc = templateBodySrc.replace("{OrderNo}", villageFormBean.getOrderNo());
						sdf = new SimpleDateFormat("dd/MM/yyyy");

						templateBodySrc = templateBodySrc.replace("{NewNameofVillage}", villageFormBean.getNewVillageNameEnglish());
						templateBodySrc = templateBodySrc.replace("{AliasoftheVillagename}", villageFormBean.getNewVillageAliasEnglish());
						if (villageFormBean.getOrderDate() != null) {
							templateBodySrc = templateBodySrc.replace("{OrderDate}", sdf.format(villageFormBean.getOrderDate()));
						}

						if (villageFormBean.getEffectiveDate() != null) {
							templateBodySrc = templateBodySrc.replace("{EffectiveDate}", sdf.format(villageFormBean.getEffectiveDate()));
						}

					}
				}
			}
		}
		if (httpSession.getAttribute("formbean") instanceof LocalGovtBodyForm) {
			LocalGovtBodyForm localForm = (LocalGovtBodyForm) httpSession.getAttribute("formbean");
			String operationLb = localForm.getOperation();
			// String operationcode = localForm.getOperation();

			if (templateList != null) {
				if (templateList.size() == 1) {
					if (templateList.get(0).getTemplateDescription() != null) {
						templateBodySrc = templateList.get(0).getTemplateDescription();
					}
					if (templateList.get(0).getTemplateRegional() != null) {
						templateBodySrc = templateList.get(0).getTemplateRegional();
					}

					if (operationLb == "PC") {

						templateBodySrc = templateBodySrc.replace("{NameoftheNewLocalGovtBody}", localForm.getLgd_LBNameInEn());
						templateBodySrc = templateBodySrc.replace("{OrderNo}", localForm.getLgd_LBorderNo());
						Format sdf = new SimpleDateFormat("dd/MM/yyyy");
						templateBodySrc = templateBodySrc.replace("{OrderDate}", sdf.format(localForm.getLgd_LBorderDate()));
						templateBodySrc = templateBodySrc.replace("{NewVillageName}", localForm.getLgd_LBNameInEn());
						templateBodySrc = templateBodySrc.replace("{EffectiveDate}", sdf.format(localForm.getLgd_LBeffectiveDate()));
						if (localForm.getLgd_LBgazPubDate() != null) {
							templateBodySrc = templateBodySrc.replace("{GazettedPublicationDate}", sdf.format(localForm.getLgd_LBgazPubDate()));
						}
						if (localForm.getLgd_LBAliasInEn() != null) {
							templateBodySrc = templateBodySrc.replace("{AliasNameoftheNewLocalGovtBody}", localForm.getLgd_LBAliasInEn());
						}

						// templateBodySrc =
						// templateBodySrc.replace("{PESAActImplemented(Yes/No)}",pesavalue);
						if (localForm.getLbType() == 'P') {
							templateBodySrc = templateBodySrc.replace("{PESAActImplemented(Yes/No)}", localForm.getLgd_LBPesaAct());
						}

						if (localForm.getLbType() == 'P' || localForm.getLbType() == 'T') {
							if (localForm.getContDistrict() != null) {
								templateBodySrc = templateBodySrc.replace("{ContributingDistricts}", localForm.getContDistrict());
							}
							if (localForm.getContSubDistrict() != null) {
								templateBodySrc = templateBodySrc.replace("{ContributingSubDistricts}", localForm.getContSubDistrict());
							}
							if (localForm.getContVillage() != null) {
								templateBodySrc = templateBodySrc.replace("{ContributingVillages}", localForm.getContVillage());
							}
						}

						if (localForm.getLbType() == 'U') {
							if (localForm.getContSubDistrict() != null) {
								templateBodySrc = templateBodySrc.replace("{ContributingSubDistricts}", localForm.getContSubDistrict());
							}
						}

						if (localForm.getLgd_LBDistrictAtInter() != null && localForm.getLgd_LBTypeName().charAt(2) == 'I') {
							if (localForm.getParentLocalbodyName() != null) {
								templateBodySrc = templateBodySrc.replace("{ParentofNewLocalGovtBody}", localForm.getParentLocalbodyName());
							}
						}

						if (localForm.getLgd_LBIntermediateAtVillage() != null && localForm.getLgd_LBTypeName().charAt(2) == 'V') {
							if (localForm.getParentLocalbodyName() != null) {
								templateBodySrc = templateBodySrc.replace("{ParentofNewLocalGovtBody}", localForm.getParentLocalbodyName());
							}
						}

						templateBodySrc = templateBodySrc.replace("{LocalGovtType}", localForm.getLgd_LocalBodyTypeName());

						templateBodySrc = templateBodySrc.replace("{ContributingState}", localForm.getStateName());

					}

					if (operationLb == "IPRI" || operationLb == "ITRI") {
						/*
						 * Integer orderNo = localForm.getLocalBodyVersion();
						 * orderNo=orderNo+1;
						 */
						templateBodySrc = templateBodySrc.replace("{LocalBodyName}", localForm.getLocalBodyNameEnglish());
						if (localForm.getLocalBodyNameLocal() != null)
							templateBodySrc = templateBodySrc.replace("{LocalBodyNameLocal}", " Not Available");
						else
							templateBodySrc = templateBodySrc.replace("{LocalBodyNameLocal}", localForm.getLocalBodyNameLocal());

						// templateBodySrc =
						// templateBodySrc.replace("{LbVersion}",orderNo.toString());
						templateBodySrc = templateBodySrc.replace("{OrderNo}", localForm.getLgd_LBorderNo());

						Format sdf = new SimpleDateFormat("dd/MM/yyyy");
						templateBodySrc = templateBodySrc.replace("{OrderDate}", sdf.format(localForm.getLgd_LBorderDate()));
						templateBodySrc = templateBodySrc.replace("{EffectiveDate}", sdf.format(localForm.getLgd_LBeffectiveDate()));
						if (localForm.getLgd_LBgazPubDate() != null) {
							templateBodySrc = templateBodySrc.replace("{GazettedPublicationDate}", sdf.format(localForm.getLgd_LBgazPubDate()));
						}
						String lblevel = (String) httpSession.getAttribute("lblevel");
						int type = Integer.parseInt(lblevel);
						if (localForm.getInvalidatedlbcode() == null) {
							if (type == 1) {
								templateBodySrc = templateBodySrc.replace("{Lbid}", localForm.getDistrictpanlbid());
							} else if (type == 2) {
								templateBodySrc = templateBodySrc.replace("{Lbid}", localForm.getIntermediatepanlbid());
							} else if (type == 3) {
								templateBodySrc = templateBodySrc.replace("{Lbid}", localForm.getGrampanlbid());
							}
						} else
							templateBodySrc = templateBodySrc.replace("{Lbid}", localForm.getInvalidatedlbcode());

					}
					if (operationLb == "IURB") {

						templateBodySrc = templateBodySrc.replace("{LocalBodyName}", localForm.getLocalBodyNameEnglish());
						if (localForm.getLocalBodyNameLocal() != null)
							templateBodySrc = templateBodySrc.replace("{LocalBodyNameLocal}", " Not Available");
						else
							templateBodySrc = templateBodySrc.replace("{LocalBodyNameLocal}", localForm.getLocalBodyNameLocal());

						templateBodySrc = templateBodySrc.replace("{OrderNo}", localForm.getLgd_LBorderNo());
						Format sdf = new SimpleDateFormat("dd/MM/yyyy");
						templateBodySrc = templateBodySrc.replace("{OrderDate}", sdf.format(localForm.getLgd_LBorderDate()));
						// templateBodySrc =
						// templateBodySrc.replace("{NewVillageName}",localForm.getLgd_LBNameInEn());
						templateBodySrc = templateBodySrc.replace("{EffectiveDate}", sdf.format(localForm.getLgd_LBeffectiveDate()));
						if (localForm.getLgd_LBgazPubDate() != null) {
							templateBodySrc = templateBodySrc.replace("{GazettedPublicationDate}", sdf.format(localForm.getLgd_LBgazPubDate()));
						}
						if (localForm.getDistrictpanlbid() != null) {
							if (!localForm.getDistrictpanlbid().equals("0")) {
								templateBodySrc = templateBodySrc.replace("{Lbid}", localForm.getDistrictpanlbid());
							} else if (!localForm.getIntermediatepanlbid().equals("0")) {
								templateBodySrc = templateBodySrc.replace("{Lbid}", localForm.getIntermediatepanlbid());
							} else if (!localForm.getGrampanlbid().equals("0")) {
								templateBodySrc = templateBodySrc.replace("{Lbid}", localForm.getGrampanlbid());
							}
						} else {
							templateBodySrc = templateBodySrc.replace("{Lbid}", localForm.getInvalidatedlbcode());
						}
					}
					if (operationLb == "LBTC") {
						templateBodySrc = templateBodySrc.replace("{OrderNo}", localForm.getLgd_LBorderNo());
						Format sdf = new SimpleDateFormat("dd/MM/yyyy");
						templateBodySrc = templateBodySrc.replace("{OrderDate}", sdf.format(localForm.getLgd_LBorderDate()));
						templateBodySrc = templateBodySrc.replace("{EffectiveDate}", sdf.format(localForm.getLgd_LBeffectiveDate()));
						if (localForm.getLgd_LBgazPubDate() != null) {
							templateBodySrc = templateBodySrc.replace("{GazettedPublicationDate}", sdf.format(localForm.getLgd_LBgazPubDate()));
						}
						templateBodySrc = templateBodySrc.replace("{NewNameoftheLocalGovtType}", localForm.getNewlocalbodyTypeNamehidden());
						templateBodySrc = templateBodySrc.replace("{PreviousNameoftheLocalGovtType}", localForm.getOldlocalbodyTypeNamehidden());

					}
					if (operationLb == "LBHRC") {
						templateBodySrc = templateBodySrc.replace("{OrderNo}", localForm.getLgd_LBorderNo());
						Format sdf = new SimpleDateFormat("dd/MM/yyyy");
						templateBodySrc = templateBodySrc.replace("{OrderDate}", sdf.format(localForm.getLgd_LBorderDate()));
						templateBodySrc = templateBodySrc.replace("{EffectiveDate}", sdf.format(localForm.getLgd_LBeffectiveDate()));
						if (localForm.getLgd_LBgazPubDate() != null) {
							templateBodySrc = templateBodySrc.replace("{GazettedPublicationDate}", sdf.format(localForm.getLgd_LBgazPubDate()));
						}
						if (localForm.getNewlocalbodyParentNamehidden() != null) {
							templateBodySrc = templateBodySrc.replace("{NewHierarchy}", localForm.getNewlocalbodyParentNamehidden());
						}
						if (localForm.getOldlocalbodyParentNamehidden() != null) {
							templateBodySrc = templateBodySrc.replace("{ExistingHierarchy}", localForm.getOldlocalbodyParentNamehidden());
						}
					}
					if (operationLb == "LBCCNM") {
						templateBodySrc = templateBodySrc.replace("{OrderNo}", localForm.getLgd_LBorderNo());
						Format sdf = new SimpleDateFormat("dd/MM/yyyy");
						templateBodySrc = templateBodySrc.replace("{OrderDate}", sdf.format(localForm.getLgd_LBorderDate()));
						templateBodySrc = templateBodySrc.replace("{EffectiveDate}", sdf.format(localForm.getLgd_LBeffectiveDate()));
						if (localForm.getLgd_LBgazPubDate() != null) {
							templateBodySrc = templateBodySrc.replace("{GazettedPublicationDate}", sdf.format(localForm.getLgd_LBgazPubDate()));
						}
						if (localForm.getLocalbodyNameEnghidden() != null) {
							templateBodySrc = templateBodySrc.replace("{NameoftheNewLocalGovtBody}", localForm.getLocalbodyNameEnghidden());
						}
						if (localForm.getLocalbodyNameEnghidden() != null) {
							templateBodySrc = templateBodySrc.replace("{AliasNameoftheNewLocalGovtBody}", localForm.getLocalbodyNameAliasEnghidden());
						}
					}
					if (operationLb == "LBCCH") {

						if (localForm.getLocalBodyNameEnglish() != null) {
							templateBodySrc = templateBodySrc.replace("{NameoftheNewLocalGovtBody}", localForm.getLocalBodyNameEnglish());
						}

						templateBodySrc = templateBodySrc.replace("{OrderNo}", localForm.getLgd_LBorderNo());
						Format sdf = new SimpleDateFormat("dd/MM/yyyy");
						templateBodySrc = templateBodySrc.replace("{OrderDate}", sdf.format(localForm.getLgd_LBorderDate()));
						templateBodySrc = templateBodySrc.replace("{EffectiveDate}", sdf.format(localForm.getLgd_LBeffectiveDate()));
						if (localForm.getLgd_LBgazPubDate() != null) {
							templateBodySrc = templateBodySrc.replace("{GazettedPublicationDate}", sdf.format(localForm.getLgd_LBgazPubDate()));
						}

						if (localForm.getLbType() == 'U') {
							if (localForm.getContSubDistrict() != null) {
								templateBodySrc = templateBodySrc.replace("{NewCoveredLandRegion}", localForm.getContSubDistrict());
							}
							if (localForm.getExistingCovLanRegion() != null) {
								templateBodySrc = templateBodySrc.replace("{ExistingCoveredLandRegion}", localForm.getExistingCovLanRegion());
							}
						}
						if (localForm.getLbType() == 'P' || localForm.getLbType() == 'T') {
							if (localForm.getFinalLandRegion() != null) {
								templateBodySrc = templateBodySrc.replace("{NewCoveredLandRegion}", localForm.getFinalLandRegion());
							}

							if (localForm.getExistingDistrict() != null && localForm.getExistingSubDistrict() != null && localForm.getExistingVillage() != null) {
								templateBodySrc = templateBodySrc.replace("{ExistingCoveredLandRegion}", localForm.getExistingDistrict() + "," + localForm.getExistingSubDistrict() + "," + localForm.getExistingVillage());
							}
							if (localForm.getExistingDistrict() != null && localForm.getExistingSubDistrict() != null && localForm.getExistingVillage() == null) {
								templateBodySrc = templateBodySrc.replace("{ExistingCoveredLandRegion}", localForm.getExistingDistrict() + "," + localForm.getExistingSubDistrict());
							}
							if (localForm.getExistingDistrict() != null && localForm.getExistingSubDistrict() == null && localForm.getExistingVillage() == null) {
								templateBodySrc = templateBodySrc.replace("{ExistingCoveredLandRegion}", localForm.getExistingDistrict());
							}
							if (localForm.getExistingDistrict() != null && localForm.getExistingSubDistrict() == null && localForm.getExistingVillage() != null) {
								templateBodySrc = templateBodySrc.replace("{ExistingCoveredLandRegion}", localForm.getExistingDistrict() + "," + localForm.getExistingVillage());
							}
							if (localForm.getExistingDistrict() == null && localForm.getExistingSubDistrict() == null && localForm.getExistingVillage() != null) {
								templateBodySrc = templateBodySrc.replace("{ExistingCoveredLandRegion}", localForm.getExistingVillage());
							}
							if (localForm.getExistingDistrict() == null && localForm.getExistingSubDistrict() != null && localForm.getExistingVillage() != null) {
								templateBodySrc = templateBodySrc.replace("{ExistingCoveredLandRegion}", localForm.getExistingSubDistrict() + "," + localForm.getExistingVillage());
							}
						}

						if (localForm.getLocalbodyNameEnghidden() != null) {
							templateBodySrc = templateBodySrc.replace("{NameoftheNewLocalGovtBody}", localForm.getLocalbodyNameEnghidden());
						}
						if (localForm.getLocalbodyNameEnghidden() != null) {
							templateBodySrc = templateBodySrc.replace("{AliasNameoftheNewLocalGovtBody}", localForm.getLocalbodyNameAliasEnghidden());
						}
					}
					if (operationLb == "DLBR") {

						if (localForm.getLocalBodyNameEnglish() != null) {
							templateBodySrc = templateBodySrc.replace("{NameoftheNewLocalGovtBody}", localForm.getLocalBodyNameEnglish());
						}

						templateBodySrc = templateBodySrc.replace("{OrderNo}", localForm.getLgd_LBorderNo());
						Format sdf = new SimpleDateFormat("dd/MM/yyyy");
						templateBodySrc = templateBodySrc.replace("{OrderDate}", sdf.format(localForm.getLgd_LBorderDate()));
						templateBodySrc = templateBodySrc.replace("{EffectiveDate}", sdf.format(localForm.getLgd_LBeffectiveDate()));
						if (localForm.getLgd_LBgazPubDate() != null) {
							templateBodySrc = templateBodySrc.replace("{GazettedPublicationDate}", sdf.format(localForm.getLgd_LBgazPubDate()));
						}

						if (localForm.getLbType() == 'U') {
							if (localForm.getContSubDistrict() != null) {
								templateBodySrc = templateBodySrc.replace("{NewCoveredLandRegion}", localForm.getContSubDistrict());
							}
							if (localForm.getExistingCovLanRegion() != null) {
								templateBodySrc = templateBodySrc.replace("{ExistingCoveredLandRegion}", localForm.getExistingCovLanRegion());
							}
						}
						if (localForm.getLbType() == 'P' || localForm.getLbType() == 'T') {
							if (localForm.getFinalLandRegion() != null) {
								templateBodySrc = templateBodySrc.replace("{NewCoveredLandRegion}", localForm.getFinalLandRegion());
							}

							if (localForm.getExistingDistrict() != null && localForm.getExistingSubDistrict() != null && localForm.getExistingVillage() != null) {
								templateBodySrc = templateBodySrc.replace("{ExistingCoveredLandRegion}", localForm.getExistingDistrict() + "," + localForm.getExistingSubDistrict() + "," + localForm.getExistingVillage());
							}
							if (localForm.getExistingDistrict() != null && localForm.getExistingSubDistrict() != null && localForm.getExistingVillage() == null) {
								templateBodySrc = templateBodySrc.replace("{ExistingCoveredLandRegion}", localForm.getExistingDistrict() + "," + localForm.getExistingSubDistrict());
							}
							if (localForm.getExistingDistrict() != null && localForm.getExistingSubDistrict() == null && localForm.getExistingVillage() == null) {
								templateBodySrc = templateBodySrc.replace("{ExistingCoveredLandRegion}", localForm.getExistingDistrict());
							}
							if (localForm.getExistingDistrict() != null && localForm.getExistingSubDistrict() == null && localForm.getExistingVillage() != null) {
								templateBodySrc = templateBodySrc.replace("{ExistingCoveredLandRegion}", localForm.getExistingDistrict() + "," + localForm.getExistingVillage());
							}
							if (localForm.getExistingDistrict() == null && localForm.getExistingSubDistrict() == null && localForm.getExistingVillage() != null) {
								templateBodySrc = templateBodySrc.replace("{ExistingCoveredLandRegion}", localForm.getExistingVillage());
							}
							if (localForm.getExistingDistrict() == null && localForm.getExistingSubDistrict() != null && localForm.getExistingVillage() != null) {
								templateBodySrc = templateBodySrc.replace("{ExistingCoveredLandRegion}", localForm.getExistingSubDistrict() + "," + localForm.getExistingVillage());
							}
						}

						if (localForm.getLocalbodyNameEnghidden() != null) {
							templateBodySrc = templateBodySrc.replace("{NameoftheNewLocalGovtBody}", localForm.getLocalbodyNameEnghidden());
						}
						if (localForm.getLocalbodyNameEnghidden() != null) {
							templateBodySrc = templateBodySrc.replace("{AliasNameoftheNewLocalGovtBody}", localForm.getLocalbodyNameAliasEnghidden());
						}
					}
					if (operationLb == "LBGOCHN") {

						templateBodySrc = templateBodySrc.replace("{OrderNo}", localForm.getLgd_LBorderNo());
						Format sdf = new SimpleDateFormat("dd/MM/yyyy");
						templateBodySrc = templateBodySrc.replace("{OrderDate}", sdf.format(localForm.getLgd_LBorderDate()));
						templateBodySrc = templateBodySrc.replace("{EffectiveDate}", sdf.format(localForm.getLgd_LBeffectiveDate()));
						if (localForm.getLgd_LBgazPubDate() != null) {
							templateBodySrc = templateBodySrc.replace("{GazettedPublicationDate}", sdf.format(localForm.getLgd_LBgazPubDate()));
						}
						templateBodySrc = templateBodySrc.replace("{NameoftheNewLocalGovtBody}", localForm.getLocalbodyNameEnghidden());
						templateBodySrc = templateBodySrc.replace("{AliasNameoftheNewLocalGovtBody}", localForm.getLocalbodyNameAliasEnghidden());

					}

				}
			}

		} else if (httpSession.getAttribute("formbean") instanceof StateForm) {
			
			StateForm stateForm =(StateForm) httpSession.getAttribute("formbean");
		    if(stateForm!=null && !stateForm.getListStateDetails().isEmpty()){
		    	StateDataForm stateDataForm=stateForm.getListStateDetails().get(0);
		    	Character operation = stateForm.getOperation();
		    	
				if (templateList != null && !templateList.isEmpty()) {
					templateBodySrc=templateList.get(0).getTemplateDescription()!=null?templateList.get(0).getTemplateDescription():templateList.get(0).getTemplateRegional();
					Format sdf = new SimpleDateFormat("dd/MM/yyyy");
						if (operation == 'M') {
							
							if (stateDataForm.getStateNameEnglish() != null) {
								templateBodySrc = templateBodySrc.replace("{PreviousNameofthestate}", stateDataForm.getStateNameEnglish());
							} else {
								templateBodySrc = templateBodySrc.replace("{PreviousNameofthestate}", "");
							}

							if (stateDataForm.getStateNameEnglish() != null) {
								templateBodySrc = templateBodySrc.replace("{NewNameofStateorUT}", stateDataForm.getStateNameEnglishch());
							} else {
								templateBodySrc = templateBodySrc.replace("{NewNameofStateorUT}", "");
							}

							if (stateDataForm.getStateNameLocal() != null) {
								templateBodySrc = templateBodySrc.replace("{LocalNameofState}", stateDataForm.getStateNameLocal());
							} else {
								templateBodySrc = templateBodySrc.replace("{LocalNameofState}", "");
							}

							if (stateDataForm.getShortName() != null) {
								templateBodySrc = templateBodySrc.replace("{ShortoftheStatename}", stateDataForm.getShortName());
							} else {
								templateBodySrc = templateBodySrc.replace("{ShortoftheStatename}", "");
							}

							if (stateDataForm.getStateOrUtch() == 'S' || stateDataForm.getStateOrUtch() == 'U') {
								templateBodySrc = templateBodySrc.replace("{StateorUT}", String.valueOf(stateDataForm.getStateOrUtch() == 'S' ? "State" : "Union Territory"));
							} else {
								templateBodySrc = templateBodySrc.replace("{StateorUT}", "");
							}

							if (stateForm.getOrderNo() != null) {
								templateBodySrc = templateBodySrc.replace("{OrderNo}", stateForm.getOrderNo());
							} else {
								templateBodySrc = templateBodySrc.replace("{OrderNo}", "");
							}
							if (stateForm.getOrderDate() != null) {
								templateBodySrc = templateBodySrc.replace("{OrderDate}", sdf.format(stateForm.getOrderDate()));
							} else {
								templateBodySrc = templateBodySrc.replace("{OrderDate}", "");
							}
							if (stateForm.getEffectiveDate() != null) {
								templateBodySrc = templateBodySrc.replace("{EffectiveDate}", sdf.format(stateForm.getEffectiveDate()));
							} else {
								templateBodySrc = templateBodySrc.replace("{EffectiveDate}", "");
							}

						}

						if (operation == 'C') {
							if (stateForm.getOrderNo() != null) {
								templateBodySrc = templateBodySrc.replace("{OrderNo}", stateForm.getOrderNo());
							} else {
								templateBodySrc = templateBodySrc.replace("{OrderNo}", "");
							}
							if (stateForm.getOrderDate() != null) {
								templateBodySrc = templateBodySrc.replace("{OrderDate}", sdf.format(stateForm.getOrderDate()));
							} else {
								templateBodySrc = templateBodySrc.replace("{OrderDate}", "");
							}
							if (stateForm.getEffectiveDate() != null) {
								templateBodySrc = templateBodySrc.replace("{EffectiveDate}", sdf.format(stateForm.getEffectiveDate()));
							} else {
								templateBodySrc = templateBodySrc.replace("{EffectiveDate}", "");
							}
						}

					}
		    }
			

			}

		else if (httpSession.getAttribute("formbean") instanceof DistrictForm) {

			DistrictForm districtForm = null;
			districtForm = new DistrictForm();
			districtForm = (DistrictForm) httpSession.getAttribute("formbean");
			int operation = districtForm.getOperation();
			if (templateList != null) {
				if (templateList.size() == 1) {

					if (templateList.get(0).getTemplateDescription() != null) {
						templateBodySrc = templateList.get(0).getTemplateDescription();
					}
					if (templateList.get(0).getTemplateRegional() != null) {
						templateBodySrc = templateList.get(0).getTemplateRegional();
					}

					List<DistrictDataForm> districtList = new ArrayList<DistrictDataForm>();
					districtList = districtForm.getListDistrictDetails();
					if (districtList.size() > 0) {
						districtForm.setDistrictNameEnglish(districtList.get(0).getDistrictNameEnglish());
						districtForm.setDistrictNameEnglishch(districtList.get(0).getDistrictNameEnglishch());
						districtForm.setAliasEnglish(districtList.get(0).getAliasEnglish());
					}

					if (districtForm.getDistrictNameEnglish() != null) {
						templateBodySrc = templateBodySrc.replace("{PreviousNameoftheDistrict}", districtForm.getDistrictNameEnglish());
					} else {
						templateBodySrc = templateBodySrc.replace("{PreviousNameoftheDistrict}", "");
					}

					if (districtForm.getDistrictNameEnglishch() != null) {
						templateBodySrc = templateBodySrc.replace("{NewNameofDistrict}", districtForm.getDistrictNameEnglishch());
					} else {
						templateBodySrc = templateBodySrc.replace("{NewNameofDistrict}", "");
					}

					if (districtForm.getAliasEnglish() != null)
						templateBodySrc = templateBodySrc.replace("{Aliasofthedistrictname}", districtForm.getAliasEnglish());
					else
						templateBodySrc = templateBodySrc.replace("{Aliasofthedistrictname}", "");
					/*
					 * templateBodySrc =
					 * templateBodySrc.replace("{Aliasofthedistrictname}",
					 * districtForm.getStateCode());
					 */

					if (operation == 'C') {
						
						if (districtForm.getDistrictNameList() != null) {
							templateBodySrc = templateBodySrc.replace("{NameofNewDistrict}", districtForm.getDistrictNameList() );
						} else {
							templateBodySrc = templateBodySrc.replace("{NameofNewDistrict}", "");
						}
						
						if (stateNameEng != null) {
							templateBodySrc = templateBodySrc.replace("{StateName}", stateNameEng.trim() );
						} else {
							templateBodySrc = templateBodySrc.replace("{StateName}", "");
						}
						
						if (districtForm.getContributedSubDistrictsTemp() != null) {
							templateBodySrc = templateBodySrc.replace("{ContributingSubDistricts}", districtForm.getContributedSubDistrictsTemp() );
						} else {
							templateBodySrc = templateBodySrc.replace("{ContributingSubDistricts}", "");
						}
						
						templateBodySrc = templateBodySrc.replace("{OrderNo}", districtForm.getOrderNo());
						Format sdf = new SimpleDateFormat("dd/MM/yyyy");
						templateBodySrc = templateBodySrc.replace("{OrderDate}", sdf.format(districtForm.getOrderDate()));

						/*
						 * templateBodySrc = templateBodySrc.replace(
						 * "{NewDistrictName}",
						 * districtForm.getDistrictAliasInEn());
						 */

						templateBodySrc = templateBodySrc.replace("{EffectiveDate}", sdf.format(districtForm.getEffectiveDate()));
						if (districtForm.getGazPubDate() != null) {
							templateBodySrc = templateBodySrc.replace("{GazettedPublicationDate}", sdf.format(districtForm.getGazPubDate()));
						}
					}

					else if (operation == 'M') {
						List<DistrictDataForm> listDistrict = new ArrayList<DistrictDataForm>();
						Integer districtCode = null;
						// Integer districtVersion=null;
						listDistrict = districtForm.getListDistrictDetails();
						Iterator<DistrictDataForm> itr = listDistrict.iterator();
						while (itr.hasNext()) {
							DistrictDataForm element = (DistrictDataForm) itr.next();
							districtCode = element.getDistrictCode();
							// districtVersion = element.getDistrictVersion();
							districtForm.setDistrictNameEnglish(element.getDistrictNameEnglish());
							districtForm.setDistrictNameEnglishch(element.getDistrictNameEnglishch());
							districtForm.setDistrictAliasInEn(element.getDistrictNameLocalch());

						}

						List<LandRegionDetail> landregionDetail = new ArrayList<LandRegionDetail>();
						// Session hsession=null;
						// hsession=sessionFactory.openSession();
						landregionDetail = reportService.landRegionDetail('D', districtCode);

						Format sdf = new SimpleDateFormat("dd/MM/yyyy");
						if (districtForm.getDistrictNameEnglish() != null) {
							templateBodySrc = templateBodySrc.replace("{PreviousNameoftheDistrict}", districtForm.getDistrictNameEnglish());
						} else {
							templateBodySrc = templateBodySrc.replace("{PreviousNameoftheDistrict}", "");
						}

						if (districtForm.getDistrictNameEnglishch() != null) {
							templateBodySrc = templateBodySrc.replace("{NewNameofDistrict}", districtForm.getDistrictNameEnglishch());
						} else {
							templateBodySrc = templateBodySrc.replace("{NewNameofDistrict}", "");
						}

						if (districtForm.getAliasEnglish() != null) {
							templateBodySrc = templateBodySrc.replace("{Aliasofthedistrictname}",districtForm.getAliasEnglish());
						} else {
							templateBodySrc = templateBodySrc.replace("{Aliasofthedistrictname}", "");
						}

						if (landregionDetail.size() > 0) {
							templateBodySrc = templateBodySrc.replace("{StateName}", landregionDetail.get(0).getStateNameEnglish());
						} else {
							templateBodySrc = templateBodySrc.replace("{StateName}", "");
						}

						if (districtForm.getOrderNo() != null) {
							templateBodySrc = templateBodySrc.replace("{OrderNo}", districtForm.getOrderNo());
						} else {
							templateBodySrc = templateBodySrc.replace("{OrderNo}", "");
						}

						if (districtForm.getOrderDate() != null) {
							templateBodySrc = templateBodySrc.replace("{OrderDate}", sdf.format(districtForm.getOrderDate()));
						}

						if (districtForm.getEffectiveDate() != null) {
							templateBodySrc = templateBodySrc.replace("{EffectiveDate}", sdf.format(districtForm.getEffectiveDate()));
						}
					} else if (operation == 'I') {
						Integer districtCode = null;// ,districtVersion=null;
						List<DistrictDataForm> listDistrict = new ArrayList<DistrictDataForm>();

						listDistrict = districtForm.getListDistrictDetails();
						Iterator<DistrictDataForm> itr = listDistrict.iterator();
						while (itr.hasNext()) {
							DistrictDataForm element = (DistrictDataForm) itr.next();
							districtCode = element.getDistrictCode();
							// districtVersion = element.getDistrictVersion();
							districtForm.setDistrictNameEnglish(element.getDistrictNameEnglishch());
							districtForm.setDistrictAliasInEn(element.getDistrictNameLocalch());

						}
						LandRegionDetail lr = new LandRegionDetail();
						if (districtForm.getDistrictNameEnglish() != null) {
							districtCode = Integer.parseInt(districtForm.getDistrictNameEnglish());
						}
						List<LandRegionDetail> landregionDetail = new ArrayList<LandRegionDetail>();
						// Session hsession=null;
						// hsession=sessionFactory.openSession();
						if (districtCode == null) {
							districtCode = 0;
						}
						landregionDetail = reportService.landRegionDetail('D', districtCode);
						if (landregionDetail.size() > 0) {
							lr = landregionDetail.get(0);
							templateBodySrc = templateBodySrc.replace("{NameoftheDistrictInvalidated}", lr.getDistrictNameEnglish());
						} else {
							templateBodySrc = templateBodySrc.replace("{NameoftheDistrictInvalidated}", "");
						}
						templateBodySrc = templateBodySrc.replace("{OrderNo}", districtForm.getOrderNo());
						Format sdf = new SimpleDateFormat("dd/MM/yyyy");
						// String sourcedistrict=

						/*
						 * templateBodySrc = templateBodySrc.replace(
						 * "{ReasonforInvalidation}",
						 * districtForm.getDistrictAliasInEn());
						 */
						if (districtForm.getOrderDate() != null) {
							templateBodySrc = templateBodySrc.replace("{OrderDate}", sdf.format(districtForm.getOrderDate()));
						}

						if (districtForm.getEffectiveDate() != null) {
							templateBodySrc = templateBodySrc.replace("{EffectiveDate}", sdf.format(districtForm.getEffectiveDate()));
						}

					}
				}
			}

		} else if (httpSession.getAttribute("formbean") instanceof SubDistrictForm) {

			SubDistrictForm SDForm = null;
			SDForm = new SubDistrictForm();
			SDForm = (SubDistrictForm) httpSession.getAttribute("formbean");
			int operation = SDForm.getOperation();
			String districtNameEng=httpSession.getAttribute("districtNameEng")!=null?httpSession.getAttribute("districtNameEng").toString():null;
			if (templateList != null) {
				if (templateList.size() == 1) {

					if (templateList.get(0).getTemplateDescription() != null) {
						templateBodySrc = templateList.get(0).getTemplateDescription();
					}
					if (templateList.get(0).getTemplateRegional() != null) {
						templateBodySrc = templateList.get(0).getTemplateRegional();
					}

					if (operation == 'C') {
						if (stateNameEng != null) {
							templateBodySrc = templateBodySrc.replace("{StateName}", stateNameEng.trim() );
						} else {
							templateBodySrc = templateBodySrc.replace("{StateName}", "");
						}
						
						if (districtNameEng != null) {
							templateBodySrc = templateBodySrc.replace("{DistrictName}", districtNameEng.trim() );
						} else {
							templateBodySrc = templateBodySrc.replace("{DistrictName}", "");
						}
						
						if(SDForm.getSubdistrictNameEnglish()!=null){
							
							templateBodySrc = templateBodySrc.replace("{NameofnewSubDistrict}", SDForm.getSubdistrictNameEnglish().trim() );
						} else {
							templateBodySrc = templateBodySrc.replace("{NameofnewSubDistrict}", "");
						}
					
							if(SDForm.getAliasEnglish()!=null){
							
							templateBodySrc = templateBodySrc.replace("{AliasoftheSubDistrictname}", SDForm.getAliasEnglish().trim() );
						} else {
							templateBodySrc = templateBodySrc.replace("{AliasoftheSubDistrictname}", "");
						}
					
							if(SDForm.getContributedVillages()!=null){
								
								templateBodySrc = templateBodySrc.replace("{ContributingVillages}", SDForm.getContributedVillages().trim() );
							} else {
								templateBodySrc = templateBodySrc.replace("{ContributingVillages}", "");
							}
						
						templateBodySrc = templateBodySrc.replace("{OrderNo}", SDForm.getOrderNo());
						Format sdf = new SimpleDateFormat("dd/MM/yyyy");
						templateBodySrc = templateBodySrc.replace("{OrderDate}", sdf.format(SDForm.getOrderDate()));
						
						templateBodySrc = templateBodySrc.replace("{EffectiveDate}", sdf.format(SDForm.getEffectiveDate()));
						if (SDForm.getGazPubDate() != null) {
							templateBodySrc = templateBodySrc.replace("{GazettedPublicationDate}", sdf.format(SDForm.getGazPubDate()));
						}
					} else if (operation == 'I') {
						templateBodySrc = templateBodySrc.replace("{OrderNo}", SDForm.getOrderNo());
						Format sdf = new SimpleDateFormat("dd/MM/yyyy");
						templateBodySrc = templateBodySrc.replace("{OrderDate}", sdf.format(SDForm.getOrderDate()));
						templateBodySrc = templateBodySrc.replace("{EffectiveDate}", sdf.format(SDForm.getEffectiveDate()));
						String sdcode = SDForm.getSubdistrictNameEnglish();
						List<Subdistrict> sd = subdistrictDAO.getSubDistrictListby(Integer.parseInt(sdcode));
						String subdistrictname = sd.get(0).getSubdistrictNameEnglish();
						templateBodySrc = templateBodySrc.replace("{NameoftheSubDistrictInvalidated}", subdistrictname);

					} else if (operation == 'M') {
						List<SubdistrictDataForm> listSubdistrict = new ArrayList<SubdistrictDataForm>();
						listSubdistrict = SDForm.getListSubdistrictDetails();
						int subdistrictCode = 0;
						// int subdistrictVersion=0;
						Iterator<SubdistrictDataForm> itr = listSubdistrict.iterator();
						while (itr.hasNext()) {
							SubdistrictDataForm element = (SubdistrictDataForm) itr.next();
							subdistrictCode = element.getSubdistrictCode();
							// subdistrictVersion =
							// element.getSubdistrictVersion();
							SDForm.setSubdistrictNameEnglish(element.getSubdistrictNameEnglish());
							SDForm.setAliasEnglish(element.getAliasEnglishch());
							SDForm.setSubdistrictNameEnglishch(element.getSubdistrictNameEnglishch());
						}

						List<LandRegionDetail> landregionDetail = new ArrayList<LandRegionDetail>();
						// Session hsession=null;
						// hsession=sessionFactory.openSession();
						landregionDetail = reportService.landRegionDetail('T', subdistrictCode);
						templateBodySrc = templateBodySrc.replace("{OrderNo}", SDForm.getOrderNo());
						Format sdf = new SimpleDateFormat("dd/MM/yyyy");

						if (SDForm.getSubdistrictNameEnglish() != null) {
							templateBodySrc = templateBodySrc.replace("{PreviousNameoftheSubDistrict}", SDForm.getSubdistrictNameEnglish());
						} else {
							templateBodySrc = templateBodySrc.replace("{PreviousNameoftheSubDistrict}", "");
						}

						if (SDForm.getSubdistrictNameEnglishch() != null) {
							templateBodySrc = templateBodySrc.replace("{NewNameofSubDistrict}", SDForm.getSubdistrictNameEnglishch());
						} else {
							templateBodySrc = templateBodySrc.replace("{NewNameofSubDistrict}", "");
						}

						if (landregionDetail.size() > 0) {
							templateBodySrc = templateBodySrc.replace("{StateName}", landregionDetail.get(0).getStateNameEnglish());
							templateBodySrc = templateBodySrc.replace("{DistrictName}", landregionDetail.get(0).getDistrictNameEnglish());
						} else {
							templateBodySrc = templateBodySrc.replace("{StateName}", "");
							templateBodySrc = templateBodySrc.replace("{DistrictName}", "");
						}

						if (SDForm.getAliasEnglish() != null) {
							templateBodySrc = templateBodySrc.replace("{AliasoftheSubDistrictname}", SDForm.getAliasEnglish());
						} else {
							templateBodySrc = templateBodySrc.replace("{AliasoftheSubDistrictname}", "");
						}
						if (SDForm.getOrderDate() != null) {
							templateBodySrc = templateBodySrc.replace("{OrderDate}", sdf.format(SDForm.getOrderDate()));
						}

						if (SDForm.getEffectiveDate() != null) {
							templateBodySrc = templateBodySrc.replace("{EffectiveDate}", sdf.format(SDForm.getEffectiveDate()));
						}

					}

				}
			}

		} else if (httpSession.getAttribute("formbean") instanceof ShiftDistrictForm) {
			ShiftDistrictForm shiftForm = (ShiftDistrictForm) httpSession.getAttribute("formbean");

			if (templateList != null) {
				if (templateList.size() == 1) {
					if (templateList.get(0).getTemplateDescription() != null) {
						templateBodySrc = templateList.get(0).getTemplateDescription();
					}
					if (templateList.get(0).getTemplateRegional() != null) {
						templateBodySrc = templateList.get(0).getTemplateRegional();
					}

					templateBodySrc = templateBodySrc.replace("{OrderNo}", shiftForm.getOrderNo());
					Format sdf = new SimpleDateFormat("dd/MM/yyyy");
					templateBodySrc = templateBodySrc.replace("{OrderDate}", sdf.format(shiftForm.getOrderDate()));
					templateBodySrc = templateBodySrc.replace("{NewVillageName}", shiftForm.getDistrictNameEnglish());
					templateBodySrc = templateBodySrc.replace("{EffectiveDate}", sdf.format(shiftForm.getEffectiveDate()));
					templateBodySrc = templateBodySrc.replace("{ExistingStateoftheDistricts}", shiftForm.getFinalsourceStateName());
					templateBodySrc = templateBodySrc.replace("{TargetstateoftheDistricts}", shiftForm.getFinaltargetStateName());
					templateBodySrc = templateBodySrc.replace("{ShiftDistricts}", shiftForm.getFinalDestName());
					if (shiftForm.getGazPubDate() != null) {
						templateBodySrc = templateBodySrc.replace("{GazettedPublicationDate}", sdf.format(shiftForm.getGazPubDate()));
					}
				}

			}

		} else if (httpSession.getAttribute("formbean") instanceof ShiftBlockForm) {
			ShiftBlockForm shiftForm = (ShiftBlockForm) httpSession.getAttribute("formbean");

			if (templateList != null) {
				if (templateList.size() == 1) {
					if (templateList.get(0).getTemplateDescription() != null) {
						templateBodySrc = templateList.get(0).getTemplateDescription();
					}
					if (templateList.get(0).getTemplateRegional() != null) {
						templateBodySrc = templateList.get(0).getTemplateRegional();
					}

					templateBodySrc = templateBodySrc.replace("{OrderNo}", shiftForm.getOrderNo());
					Format sdf = new SimpleDateFormat("dd/MM/yyyy");
					templateBodySrc = templateBodySrc.replace("{OrderDate}", sdf.format(shiftForm.getOrderDate()));
					// templateBodySrc =
					// templateBodySrc.replace("{NameoftheNewBlock}",shiftForm.getBlockNameEnglish());
					templateBodySrc = templateBodySrc.replace("{EffectiveDate}", sdf.format(shiftForm.getEffectiveDate()));
					if (shiftForm.getGazPubDate() != null) {
						templateBodySrc = templateBodySrc.replace("{GazettedPublicationDate}", sdf.format(shiftForm.getGazPubDate()));
					}
					if (shiftForm.getFinalsourceDistName() != null) {
						templateBodySrc = templateBodySrc.replace("{ExistingDistrict}", shiftForm.getFinalsourceDistName());
					}
					if (shiftForm.getFinaltargetDistName() != null) {
						templateBodySrc = templateBodySrc.replace("{TargetDistrict }", shiftForm.getFinaltargetDistName());
					}
					if (shiftForm.getFinaltargetBlockName() != null) {
						templateBodySrc = templateBodySrc.replace("{Shiftblocksfromonedistricttoanother}", shiftForm.getFinaltargetBlockName());
					}
				}

			}

		} else if (httpSession.getAttribute("formbean") instanceof ShiftSubDistrictForm) {
			ShiftSubDistrictForm shiftForm = (ShiftSubDistrictForm) httpSession.getAttribute("formbean");

			if (templateList != null) {
				if (templateList.size() == 1) {
					if (templateList.get(0).getTemplateDescription() != null) {
						templateBodySrc = templateList.get(0).getTemplateDescription();
					}
					if (templateList.get(0).getTemplateRegional() != null) {
						templateBodySrc = templateList.get(0).getTemplateRegional();
					}

					templateBodySrc = templateBodySrc.replace("{OrderNo}", shiftForm.getOrderNo());
					Format sdf = new SimpleDateFormat("dd/MM/yyyy");
					templateBodySrc = templateBodySrc.replace("{OrderDate}", sdf.format(shiftForm.getOrderDate()));
					templateBodySrc = templateBodySrc.replace("{ContributingSub-Districts}", shiftForm.getSubdistrictNameEnglish());
					templateBodySrc = templateBodySrc.replace("{EffectiveDate}", sdf.format(shiftForm.getEffectiveDate()));

					if (shiftForm.getFinalsourceStateName() != null) {
						templateBodySrc = templateBodySrc.replace("{ExistingStateoftheSubDistricts}", shiftForm.getFinalsourceStateName());
					}
					if (shiftForm.getFinaltargetStateName() != null) {
						templateBodySrc = templateBodySrc.replace("{TargetStateoftheSubDistricts}", shiftForm.getFinaltargetStateName());
					}
					templateBodySrc = templateBodySrc.replace("{ExistingDistrictoftheSubDistricts}", shiftForm.getFinalsourceDistName());
					templateBodySrc = templateBodySrc.replace("{TargetDistrictoftheSubDistricts}", shiftForm.getFinaltargetDistName());
					templateBodySrc = templateBodySrc.replace("{ShiftSubDistricts}", shiftForm.getFinalSubdistName());

					if (shiftForm.getGazPubDate() != null) {
						templateBodySrc = templateBodySrc.replace("{GazettedPublicationDate}", sdf.format(shiftForm.getGazPubDate()));
					}

				}
			}

		}

		else if (httpSession.getAttribute("formbean") instanceof ShiftVillageForm) {

			ShiftVillageForm shiftForm = (ShiftVillageForm) httpSession.getAttribute("formbean");

			if (templateList != null) {
				if (templateList.size() == 1) {
					if (templateList.get(0).getTemplateDescription() != null) {
						templateBodySrc = templateList.get(0).getTemplateDescription();
					}
					if (templateList.get(0).getTemplateRegional() != null) {
						templateBodySrc = templateList.get(0).getTemplateRegional();
					}

					templateBodySrc = templateBodySrc.replace("{OrderNo}", shiftForm.getOrderNo());
					Format sdf = new SimpleDateFormat("dd/MM/yyyy");
					templateBodySrc = templateBodySrc.replace("{OrderDate}", sdf.format(shiftForm.getOrderDate()));
					templateBodySrc = templateBodySrc.replace("{ContributingVillages}", shiftForm.getVillageNameEnglish());
					templateBodySrc = templateBodySrc.replace("{EffectiveDate}", sdf.format(shiftForm.getEffectiveDate()));
					if (shiftForm.getGazPubDate() != null) {
						templateBodySrc = templateBodySrc.replace("{GazettedPublicationDate}", sdf.format(shiftForm.getGazPubDate()));
					}
					if (shiftForm.getFinalsourceDistName() != null) {
						templateBodySrc = templateBodySrc.replace("{ExistingDistrict}", shiftForm.getFinalsourceDistName());
					}
					if (shiftForm.getFinaltargetDistName() != null) {
						templateBodySrc = templateBodySrc.replace("{TargetDistrict}", shiftForm.getFinaltargetDistName());
					}
					if (shiftForm.getFinalsourceSubdistName() != null) {
						templateBodySrc = templateBodySrc.replace("{ExistingSubDistrict}", shiftForm.getFinalsourceSubdistName());
					}
					if (shiftForm.getFinalsourceStateName() != null) {
						templateBodySrc = templateBodySrc.replace("{ExistingState}", shiftForm.getFinalsourceStateName());
					}
					if (shiftForm.getFinaltargetStateName() != null) {
						templateBodySrc = templateBodySrc.replace("{TargetState}", shiftForm.getFinaltargetStateName());
					}

					if (shiftForm.getFinaltargetSubdistName() != null) {
						templateBodySrc = templateBodySrc.replace("{TargetSubDistrict}", shiftForm.getFinaltargetSubdistName());
					}
					if (shiftForm.getFinalVillageName() != null) {
						templateBodySrc = templateBodySrc.replace("{ShiftvillagesfromoneSubDistricttoanother}", shiftForm.getFinalVillageName());
						templateBodySrc = templateBodySrc.replace("{Shiftvillagesfromoneblocktoanother}", shiftForm.getFinalVillageName());
					}

					if (shiftForm.getBlockSName() != null) {
						templateBodySrc = templateBodySrc.replace("{ExistingBlockoftheVillage}", shiftForm.getBlockSName());
					}

					if (shiftForm.getBlockDName() != null) {
						templateBodySrc = templateBodySrc.replace("{TargetBlockoftheVillage}", shiftForm.getBlockDName());
					}

					// "{TargetBlockoftheVillage}","{TargetDistrict
					// }","{ExistingBlockoftheVillage}","{Shiftvillagesfromoneblocktoanother}"

					// "{TargetState}","{ExistingState}"
				}
			}

		}

		else if (httpSession.getAttribute("formbean") instanceof ConvertRLBtoULBForm) {

			ConvertRLBtoULBForm convertForm = (ConvertRLBtoULBForm) httpSession.getAttribute("formbean");

			if (templateList != null) {
				if (templateList.size() == 1) {

					if (templateList.get(0).getTemplateDescription() != null) {
						templateBodySrc = templateList.get(0).getTemplateDescription();
					}
					if (templateList.get(0).getTemplateRegional() != null) {
						templateBodySrc = templateList.get(0).getTemplateRegional();
					}

					templateBodySrc = templateBodySrc.replace("{OrderNo}", convertForm.getOrderNo());
					Format sdf = new SimpleDateFormat("dd/MM/yyyy");
					templateBodySrc = templateBodySrc.replace("{OrderDate}", sdf.format(convertForm.getOrderDate()));
					templateBodySrc = templateBodySrc.replace("{ContributingVillages}", convertForm.getUrbanlocalBodyNameEnglish());
					templateBodySrc = templateBodySrc.replace("{EffectiveDate}", sdf.format(convertForm.getEffectiveDate()));
					if (convertForm.getGazPubDate() != null) {
						templateBodySrc = templateBodySrc.replace("{GazettedPublicationDate}", sdf.format(convertForm.getGazPubDate()));
					}
				}
			}
		}

		else if (httpSession.getAttribute("formbean") instanceof ConvertRLBtoTLBForm) {
			ConvertRLBtoTLBForm convertForm = (ConvertRLBtoTLBForm) httpSession.getAttribute("formbean");

			if (templateList != null) {
				if (templateList.size() == 1) {
					if (templateList.get(0).getTemplateDescription() != null) {
						templateBodySrc = templateList.get(0).getTemplateDescription();
					}
					if (templateList.get(0).getTemplateRegional() != null) {
						templateBodySrc = templateList.get(0).getTemplateRegional();
					}

					templateBodySrc = templateBodySrc.replace("{OrderNo}", convertForm.getOrderNo());
					Format sdf = new SimpleDateFormat("dd/MM/yyyy");
					templateBodySrc = templateBodySrc.replace("{OrderDate}", sdf.format(convertForm.getOrderDate()));
					// templateBodySrc =
					// templateBodySrc.replace("{ContributingVillages}",
					// convertForm.getUrbanlocalBodyNameEnglish());
					templateBodySrc = templateBodySrc.replace("{EffectiveDate}", sdf.format(convertForm.getEffectiveDate()));
					if (convertForm.getGazPubDate() != null) {
						templateBodySrc = templateBodySrc.replace("{GazettedPublicationDate}", sdf.format(convertForm.getGazPubDate()));
					}
					if (convertForm.getLocalBodyNameInEn() != null) {
						templateBodySrc = templateBodySrc.replace("{NewTLBFormedNameEng}", convertForm.getLocalBodyNameInEn());
					}
					if (convertForm.getLocalBodyNameInLcl() != null) {
						templateBodySrc = templateBodySrc.replace("{NewTLBFormedNameLocal}", convertForm.getLocalBodyNameInLcl());
					}
					if (convertForm.getLocalBodyNameInEn() != null) {
						templateBodySrc = templateBodySrc.replace("{NewTLBFormedNameAliasinEng}", convertForm.getLocalBodyAliasInEn());
					}
					if (convertForm.getLocalBodyAliasInLcl() != null) {
						templateBodySrc = templateBodySrc.replace("{NewTLBFormedNameAliasinLocal}", convertForm.getLocalBodyAliasInLcl());
					}

					if (convertForm.getContVPforPRI() != null) {
						templateBodySrc = templateBodySrc.replace("{NameofthPRIsConverted}", convertForm.getContVPforPRI());
					}
					if (convertForm.getContIPforPRI() != null) {
						templateBodySrc = templateBodySrc.replace("{ImmediateTierofPRIConverted}", convertForm.getContIPforPRI());
					}
					if (convertForm.getContDPForPRI() != null) {
						templateBodySrc = templateBodySrc.replace("{TopTierofPRIConverted}", convertForm.getContDPForPRI());
					}
					if (convertForm.getContVillageList() != null) {
						templateBodySrc = templateBodySrc.replace("{NameofthVillagedsConverted}", convertForm.getContVillageList());
					}
					if (convertForm.getContPrilbtype() != null) {
						templateBodySrc = templateBodySrc.replace("{TypeofPRIConverted}", convertForm.getContPrilbtype());
					}
					if (convertForm.getContTradlbtypeNew() != null) {
						templateBodySrc = templateBodySrc.replace("{TypeofNewTLBFormed}", convertForm.getContTradlbtypeNew());
					}

					if (convertForm.getContTradlbtypeMerged() != null) {
						templateBodySrc = templateBodySrc.replace("{TypeofNewTLBMerged}", convertForm.getContTradlbtypeMerged());
					}

					if (convertForm.getContDPForTrad() != null) {
						templateBodySrc = templateBodySrc.replace("{TopTierofTLB}", convertForm.getContDPForTrad());
					}

					if (convertForm.getContIPforTrad() != null) {
						templateBodySrc = templateBodySrc.replace("{ImmediateTierofTLB}", convertForm.getContIPforTrad());
					}

					if (convertForm.getContDPForTradMerged() != null) {
						templateBodySrc = templateBodySrc.replace("{TopTierofTLB}", convertForm.getContDPForTradMerged());
					}
					if (convertForm.getContIPforTradMerged() != null) {
						templateBodySrc = templateBodySrc.replace("{ImmediateTierofTLB}", convertForm.getContIPforTradMerged());
					}
					if (convertForm.getContVPforTradMerged() != null) {
						templateBodySrc = templateBodySrc.replace("{NameofExistingTLB}", convertForm.getContVPforTradMerged());
					}

				}
			}
		}

		else if (httpSession.getAttribute("formbean") instanceof ConvertTLBtoRLBForm) {
			ConvertTLBtoRLBForm convertForm = (ConvertTLBtoRLBForm) httpSession.getAttribute("formbean");

			if (templateList != null) {
				if (templateList.size() == 1) {
					if (templateList.get(0).getTemplateDescription() != null) {
						templateBodySrc = templateList.get(0).getTemplateDescription();
					}
					if (templateList.get(0).getTemplateRegional() != null) {
						templateBodySrc = templateList.get(0).getTemplateRegional();
					}

					templateBodySrc = templateBodySrc.replace("{OrderNo}", convertForm.getOrderNo());
					Format sdf = new SimpleDateFormat("dd/MM/yyyy");
					templateBodySrc = templateBodySrc.replace("{OrderDate}", sdf.format(convertForm.getOrderDate()));
					// templateBodySrc =
					// templateBodySrc.replace("{ContributingVillages}",convertForm.getUrbanlocalBodyNameEnglish());
					templateBodySrc = templateBodySrc.replace("{EffectiveDate}", sdf.format(convertForm.getEffectiveDate()));
					if (convertForm.getGazPubDate() != null) {
						templateBodySrc = templateBodySrc.replace("{GazettedPublicationDate}", sdf.format(convertForm.getGazPubDate()));
					}
					if (convertForm.getLocalBodyNameInEn() != null) {
						templateBodySrc = templateBodySrc.replace("{NewRLBFormedNameEng}", convertForm.getLocalBodyNameInEn());
					}
					if (convertForm.getLocalBodyNameInLcl() != null) {
						templateBodySrc = templateBodySrc.replace("{NewRLBFormedNameLocal}", convertForm.getLocalBodyNameInLcl());
					}
					if (convertForm.getLocalBodyNameInEn() != null) {
						templateBodySrc = templateBodySrc.replace("{NewRLBFormedNameAliasinEng}", convertForm.getLocalBodyAliasInEn());
					}
					if (convertForm.getLocalBodyAliasInLcl() != null) {
						templateBodySrc = templateBodySrc.replace("{NewRLBFormedNameAliasinLocal}", convertForm.getLocalBodyAliasInLcl());
					}

					if (convertForm.getContVPforPRI() != null) {
						templateBodySrc = templateBodySrc.replace("{NameofthTLBsConverted}", convertForm.getContVPforPRI());
					}
					if (convertForm.getContIPforPRI() != null) {
						templateBodySrc = templateBodySrc.replace("{ImmediateTierofTLBConverted}", convertForm.getContIPforPRI());
					}
					if (convertForm.getContDPForPRI() != null) {
						templateBodySrc = templateBodySrc.replace("{TopTierofTLBConverted}", convertForm.getContDPForPRI());
					}
					if (convertForm.getContVillageList() != null) {
						templateBodySrc = templateBodySrc.replace("{NameofthVillagedsConverted}", convertForm.getContVillageList());
					}
					if (convertForm.getContPrilbtype() != null) {
						templateBodySrc = templateBodySrc.replace("{TypeofTLBConverted}", convertForm.getContPrilbtype());
					}
					if (convertForm.getContTradlbtypeNew() != null) {
						templateBodySrc = templateBodySrc.replace("{TypeofNewRLBFormed}", convertForm.getContTradlbtypeNew());
					}

					if (convertForm.getContTradlbtypeMerged() != null) {
						templateBodySrc = templateBodySrc.replace("{TypeofNewRLBMerged}", convertForm.getContTradlbtypeMerged());
					}

					if (convertForm.getContDPForTrad() != null) {
						templateBodySrc = templateBodySrc.replace("{TopTierofRLB}", convertForm.getContDPForTrad());
					}

					if (convertForm.getContIPforTrad() != null) {
						templateBodySrc = templateBodySrc.replace("{ImmediateTierofRLB}", convertForm.getContIPforTrad());
					}

					if (convertForm.getContDPForTradMerged() != null) {
						templateBodySrc = templateBodySrc.replace("{TopTierofRLB}", convertForm.getContDPForTradMerged());
					}
					if (convertForm.getContIPforTradMerged() != null) {
						templateBodySrc = templateBodySrc.replace("{ImmediateTierofRLB}", convertForm.getContIPforTradMerged());
					}
					if (convertForm.getContVPforTradMerged() != null) {
						templateBodySrc = templateBodySrc.replace("{NameofExistingRLB}", convertForm.getContVPforTradMerged());
					}

				}
			}
		}

		else if (httpSession.getAttribute("formbean") instanceof BlockForm) {

			BlockForm blockForm = null;
			blockForm = new BlockForm();
			blockForm = (BlockForm) httpSession.getAttribute("formbean");
			int operation = blockForm.getOperation();
			if (templateList != null) {
				if (templateList.size() == 1) {

					if (templateList.get(0).getTemplateDescription() != null) {
						templateBodySrc = templateList.get(0).getTemplateDescription();
					}
					if (templateList.get(0).getTemplateRegional() != null) {
						templateBodySrc = templateList.get(0).getTemplateRegional();
					}
					if (operation == 'C') {
						templateBodySrc = templateBodySrc.replace("{OrderNo}", blockForm.getOrderNo());
						Format sdf = new SimpleDateFormat("dd/MM/yyyy");
						templateBodySrc = templateBodySrc.replace("{OrderDate}", sdf.format(blockForm.getOrderDate()));
						templateBodySrc = templateBodySrc.replace("{NameoftheNewBlock}", blockForm.getBlockNameEnglish());
						templateBodySrc = templateBodySrc.replace("{DistrictCode}", blockForm.getDistrictCode());
						templateBodySrc = templateBodySrc.replace("{EffectiveDate}", sdf.format(blockForm.getEffectiveDate()));
						if (blockForm.getGazPubDate() != null) {
							templateBodySrc = templateBodySrc.replace("{GazettedPublicationDate}", sdf.format(blockForm.getGazPubDate()));
						}
					}
				}

				if (operation == 'M') {
					List<BlockDataForm> blockDataForm = new ArrayList<BlockDataForm>();
					blockDataForm = blockForm.getListBlockDetails();
					if (!blockDataForm.isEmpty()) {
						BlockDataForm element = blockDataForm.get(0);
						String blockNameEng = element.getBlockNameEnglishch();
						if (blockNameEng != null) {
							templateBodySrc = templateBodySrc.replace("{NameoftheNewBlock}", blockNameEng);
						} else {
							templateBodySrc = templateBodySrc.replace("{NameoftheNewBlock}", "");
						}

					}
					String orderNo = blockForm.getOrderNo();
					if (orderNo != null) {
						templateBodySrc = templateBodySrc.replace("{OrderNo}", orderNo);
					} else {
						templateBodySrc = templateBodySrc.replace("{OrderNo}", "");
					}
					Format sdf = new SimpleDateFormat("dd/MM/yyyy");

					Date orderDate = blockForm.getOrderDate();
					if (orderDate != null) {
						templateBodySrc = templateBodySrc.replace("{OrderDate}", sdf.format(orderDate));
					} else {
						templateBodySrc = templateBodySrc.replace("{OrderDate}", "");
					}

					Date effectiveDate = blockForm.getEffectiveDate();
					if (effectiveDate != null) {
						templateBodySrc = templateBodySrc.replace("{EffectiveDate}", sdf.format(effectiveDate));
					} else {
						templateBodySrc = templateBodySrc.replace("{EffectiveDate}", "");
					}
					Date guzPubDate = blockForm.getGazPubDate();
					if (guzPubDate != null) {
						templateBodySrc = templateBodySrc.replace("{GazettedPublicationDate}", sdf.format(guzPubDate));
					} else {
						templateBodySrc = templateBodySrc.replace("{GazettedPublicationDate}", "");
					}
				}
			}

		}

		return templateBodySrc;
	}

	public Map<String, String> getMapAndGovtOrderConfiguration(int stateCode, int operationCode, char type) throws Exception {
		Map<String, String> hMap = new HashMap<String, String>();
		List<ViewConfigMapLocalBody> mapConfigureList = new ArrayList<ViewConfigMapLocalBody>();
		List<ConfigurationGovernmentOrder> govtOrderConfigureList = new ArrayList<ConfigurationGovernmentOrder>();
		// check map configuration
		try {

			govtOrderConfigureList = getGovtOrderConfiguration(stateCode, operationCode);
			if (type != ' ') {
				mapConfigureList = govtOrderDAO.getMapConfigByState(stateCode, type);
			}
			if (govtOrderConfigureList != null && mapConfigureList != null) {

				if (govtOrderConfigureList != null && !govtOrderConfigureList.isEmpty()) {

					for (int i = 0; i < govtOrderConfigureList.size(); i++) {
						if (govtOrderConfigureList.get(i) != null && govtOrderConfigureList.get(i).getOperationCode() == operationCode) {
							if (govtOrderConfigureList.get(i).isIsgovtorderupload()) {
								hMap.put("govtOrder", "govtOrderUpload");
							} else if (!govtOrderConfigureList.get(i).isIsgovtorderupload()) {
								hMap.put("govtOrder", "govtOrderGenerate");
							} else {
								hMap.put("govtOrder", null);
							}
						}
					}
				}
				// check Map configuration

				if (mapConfigureList != null && !mapConfigureList.isEmpty()) {
					for (int j = 0; j < mapConfigureList.size(); j++) {

						if (mapConfigureList.get(0) != null) {
							if (mapConfigureList.get(0).getIsmapupload()) {
								hMap.put("mapUpload", "true");

							} else if (!mapConfigureList.get(0).getIsmapupload()) {
								hMap.put("mapUpload", "false");

							} else {
								hMap.put("mapUpload", null);
							}
						}
					}
				}
			} else if (govtOrderConfigureList == null && mapConfigureList != null) {
				String msgid = "Configuration government order is not defined in the state please defined the setup first !";
				hMap.put("message", msgid);
			} else if (govtOrderConfigureList != null && mapConfigureList == null) {
				String msgid = "Map configuration is not defined in the state !";
				hMap.put("message", msgid);
			} else if (govtOrderConfigureList == null && mapConfigureList == null) {
				String msgid = "Neither configuration government order is defined nor map configuration. Please configure the system";
				hMap.put("message", msgid);
			} else {
				if (govtOrderConfigureList == null) {
					String msgid = "Unknown Server ,Try again !";
					hMap.put("message", msgid);
				}

			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return hMap;
	}

	public Map<String, String> getGovtOrderConfigurationConvert(int stateCode, int operationCode, char type) throws Exception {
		Map<String, String> hMap = new HashMap<String, String>();
		List<ConfigurationGovernmentOrder> govtOrderConfigureList = new ArrayList<ConfigurationGovernmentOrder>();
		// check map configuration
		try {
			govtOrderConfigureList = getGovtOrderConfiguration(stateCode, operationCode);
			if (govtOrderConfigureList != null) {
				if (govtOrderConfigureList != null && !govtOrderConfigureList.isEmpty()) {
					for (int i = 0; i < govtOrderConfigureList.size(); i++) {
						if (govtOrderConfigureList.get(i) != null && govtOrderConfigureList.get(i).getOperationCode() == operationCode) {
							if (govtOrderConfigureList.get(i).isIsgovtorderupload()) {
								hMap.put("govtOrder", "govtOrderUpload");
							} else if (!govtOrderConfigureList.get(i).isIsgovtorderupload()) {
								hMap.put("govtOrder", "govtOrderGenerate");
							} else {
								hMap.put("govtOrder", null);
							}
						}
					}
				}
			} else if (govtOrderConfigureList == null) {
				String msgid = "Configuration government order is not defined in the state please defined the setup first !";
				hMap.put("message", msgid);
			}
			/*
			 * else if (govtOrderConfigureList == null && mapConfigureList ==
			 * null) { String msgid =
			 * "Configuration government order is not defined. Please configure the system"
			 * ; hMap.put("message", msgid); }
			 */

			/*
			 * if (govtOrderConfigureList == null) { String msgid =
			 * "Unknown Server ,Try again !"; hMap.put("message", msgid); }
			 */

		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return hMap;
	}

	@SuppressWarnings("unchecked")
	public List<ConfigurationGovernmentOrder> getGovtOrderConfiguration(int stateCode, int operationCode) throws Exception {
		Session session = null;
		Query query = null;
		List<ConfigurationGovernmentOrder> configGovtOrderList = new ArrayList<ConfigurationGovernmentOrder>();
		// int stateVersion = 0;
		try {
			session = sessionFactory.openSession();

			// stateVersion = stateDao.getVersionByActiveState(stateCode);
			/*
			 * String Hsql =
			 * "from ConfigurationGovernmentOrder where operationCode =" +
			 * operationCode + " and state.statePK.stateCode='" + stateCode +
			 * "' and state.statePK.stateVersion='" + stateVersion +
			 * "' order by operationCode";
			 */
			String Hsql = "from ConfigurationGovernmentOrder where operationCode =" + operationCode + " and slc=" + stateCode + " and isactive=" + true + " order by operationCode";

			query = session.createQuery(Hsql);

			configGovtOrderList = query.list();
			if (configGovtOrderList != null && !configGovtOrderList.isEmpty()) {
				return configGovtOrderList;
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return null;
	}

	public Map<String, String> getGovtOrderConfiguration(int stateCode, int operationCode, char type) throws Exception {
		Map<String, String> hMap = new HashMap<String, String>();
		List<ConfigurationGovernmentOrder> govtOrderConfigureList = new ArrayList<ConfigurationGovernmentOrder>();
		// check Order configuration
		try {

			govtOrderConfigureList = getGovtOrderConfiguration(stateCode, operationCode);
			if (govtOrderConfigureList != null && !govtOrderConfigureList.isEmpty()) {

				for (int i = 0; i < govtOrderConfigureList.size(); i++) {
					if (govtOrderConfigureList.get(i) != null && govtOrderConfigureList.get(i).getOperationCode() == operationCode) {
						if (govtOrderConfigureList.get(i).isIsgovtorderupload()) {
							hMap.put("govtOrder", "govtOrderUpload");
						} else if (!govtOrderConfigureList.get(i).isIsgovtorderupload()) {
							hMap.put("govtOrder", "govtOrderGenerate");
						} else {
							hMap.put("govtOrder", null);
						}
					}
				}

			} else if (govtOrderConfigureList == null) {
				String msgid = "configuration government order is not defined. Please configure the system";
				hMap.put("message", msgid);
			} else {
				if (govtOrderConfigureList.isEmpty()) {
					String msgid = "Unknown Server ,Try again !";
					hMap.put("message", msgid);
				}

			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return hMap;
	}

	public Map<String, String> getMapConfiguration(int stateCode, int operationCode, char type) throws Exception {
		Map<String, String> hMap = new HashMap<String, String>();
		List<ViewConfigMapLandRegion> mapConfigureList = null;
		mapConfigureList = new ArrayList<ViewConfigMapLandRegion>();
		// check Map configuration
		try {

			mapConfigureList = configMapService.checkMapConfigurationModify(stateCode, operationCode, type);

			if (mapConfigureList == null || mapConfigureList.isEmpty()) {
				String msgid = "configuration Map is not defined. Please configure the system";
				hMap.put("message", msgid);
			} else if (mapConfigureList.get(0) != null) {
				if (mapConfigureList.get(0).getIsmapupload()) {
					hMap.put("mapUpload", "true");
				} else if (!mapConfigureList.get(0).getIsmapupload()) {
					hMap.put("mapUpload", "false");
				} else {
					hMap.put("mapUpload", null);
				}
			} else {
				String msgid = "configuration Map is not defined. Please configure the system";
				hMap.put("message", msgid);
			}
			/*
			 * else if (mapConfigureList.get(1) != null) { if
			 * (mapConfigureList.get(1).getIsmapupload()) {
			 * hMap.put("mapUpload", "true"); } else if
			 * (!mapConfigureList.get(1).getIsmapupload()) {
			 * hMap.put("mapUpload", "false"); } else { hMap.put("mapUpload",
			 * null); } }
			 * 
			 * else if (mapConfigureList.get(3) != null) { if
			 * (mapConfigureList.get(3).getIsmapupload()) {
			 * hMap.put("mapUpload", "true"); } else if
			 * (!mapConfigureList.get(3).getIsmapupload()) {
			 * hMap.put("mapUpload", "false"); } else { hMap.put("mapUpload",
			 * null); } }
			 */

		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return hMap;
	}

	@Override
	public String deleteMetaData(String attachmentId[], char type) throws Exception {
		String deleteSuccess = null;

		try {
			deleteSuccess = govtOrderDAO.deleteAttachment(attachmentId, type);
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return deleteSuccess;
	}

	public AddAttachmentBean getAttachmentBeanforMap(List<CommonsMultipartFile> attachedFile, String[] titles, HttpServletRequest request) throws Exception {
		String fileUploadLocation = null;
		int allowedTotalNoOfAttachment = 5;
		long allowedTotalFileSize;
		long allowedIndividualFileSize;
		AttachmentMaster attachmentList = null;
		AddAttachmentBean addAttachmentBean = new AddAttachmentBean();

		try {
			attachmentList = getUploadFileConfigurationDetails(2);// 2 is
																	// file_masterId
																	// to get
																	// details
																	// of Map
			if (attachmentList != null) {
				fileUploadLocation = attachmentList.getFileLocation();
				allowedTotalNoOfAttachment = attachmentList.getFileLimit();
				allowedTotalFileSize = attachmentList.getTotalFileSize();
				allowedIndividualFileSize = attachmentList.getIndividualFileSize();

				List<String> allowedMimeTypeList = getMimeTypeList();
				int noOFAlreadyAttachedFiles1 = 0; // Already Attach Number Of
													// File.
				long totalSizeOFAlreadyAttachedFiles1 = 0L;
				int noOFMandatoryFile2 = 1;

				addAttachmentBean.setCurrentlyUploadFileList(attachedFile);// 1.List
																			// Of
																			// File
																			// To
																			// Be
																			// Upload.
				addAttachmentBean.setUploadLocation(fileUploadLocation);// 2.Location
																		// For
																		// File
																		// Upload
																		// In
																		// File
																		// System.
				addAttachmentBean.setFileTitle(titles);// 3.File Title Array.
				addAttachmentBean.setAllowedTotalNoOfAttachment(allowedTotalNoOfAttachment);// 4.Allowed
																							// Total
																							// Number
																							// Of
																							// Attachment.
				addAttachmentBean.setAllowedTotalFileSize(allowedTotalFileSize);// 5.Allowed
																				// Total
																				// File
																				// Size.
				addAttachmentBean.setAllowedIndividualFileSize(allowedIndividualFileSize);// 6.Allowed
																							// Individual
																							// File
																							// Size.
				addAttachmentBean.setNoOFAlreadyAttachedFiles(noOFAlreadyAttachedFiles1);// 7.Number
																							// Of
																							// Already
																							// Attached
																							// File.
				addAttachmentBean.setTotalSizeOFAlreadyAttachedFiles(totalSizeOFAlreadyAttachedFiles1);// 8.Already
																										// attached
																										// file
																										// total
																										// size.
				addAttachmentBean.setMimeTypeList(allowedMimeTypeList);// 9.File
																		// Mime
																		// Type
																		// List.
				addAttachmentBean.setDeletedFileID(request.getParameterValues("deletedFileID2"));// 10.File
																									// Id
																									// array
																									// to
																									// be
																									// deleted
				addAttachmentBean.setDeletedFileName(request.getParameterValues("deletedFileName2"));// 11.File
																										// Name
																										// Array
																										// To
																										// Be
																										// Deleted.
				addAttachmentBean.setDeletedFileList(request.getParameterValues("deletedFileSizeList2"));// 12.Deleted
																											// File
																											// Array.
				addAttachmentBean.setNoOFMandatoryFile(noOFMandatoryFile2);// 13.Number
																			// of
																			// Mandatory
																			// file.
			}

		} catch (Exception e) {
			log.debug("Exception" + e);

		}
		return addAttachmentBean;
	}

	public List<MapAttachment> getMapAttachmentListbyEntity(Integer entityCode, char entityType) throws Exception {
		return govtOrderDAO.getMapAttachmentListbyEntity(entityCode, entityType);
	}

	public void saveDatainMapAttachment(List<AttachedFilesForm> attachedMapList, Integer localBodyCode, char entityType, Session session) throws Exception {
		MapAttachment mapAttachment = null;
		try {

			if (attachedMapList != null && !attachedMapList.isEmpty()) {
				Iterator<AttachedFilesForm> it = attachedMapList.iterator();
				while (it.hasNext()) {
					AttachedFilesForm filesForm = (AttachedFilesForm) it.next();
					mapAttachment = new MapAttachment();
					mapAttachment.setMapEntityCode(localBodyCode);
					mapAttachment.setMapEntityType(entityType);
					mapAttachment.setFileName(filesForm.getFileName());
					mapAttachment.setFileSize(filesForm.getFileSize());
					mapAttachment.setFileContentType(filesForm.getFileType());
					mapAttachment.setFileLocation(filesForm.getFileLocation());
					mapAttachment.setFileTimestamp(filesForm.getFileTimestampName());
					session.save(mapAttachment);
					session.flush();
					if (session.contains(mapAttachment)) {
						session.evict(mapAttachment);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
		}

	}

	public boolean deleteMapAttachementforLandRegion(Integer entityCode, char entityType, Session session) throws Exception {
		try {
			Query query = session.createQuery("delete from MapAttachment where mapEntityType = :mapEntityType and map_lc=:map_lc");
			query.setParameter("mapEntityType", entityType);
			query.setParameter("map_lc", entityCode);
			int result = query.executeUpdate();
			session.clear();
			session.flush();
			if (result > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			log.debug("Exception" + e);
			return false;

		}

	}

	public Integer saveDatainMapAttachmentforLandregion(List<AttachedFilesForm> attachedMapList, Integer entityCode, char entityType, Session session) throws Exception {
		MapAttachment mapAttachment = null;
		Integer mapCode = null;
		try {

			if (attachedMapList != null && !attachedMapList.isEmpty()) {
				Iterator<AttachedFilesForm> it = attachedMapList.iterator();
				while (it.hasNext()) {
					AttachedFilesForm filesForm = (AttachedFilesForm) it.next();
					mapAttachment = new MapAttachment();
					mapAttachment.setMapEntityCode(entityCode);
					mapAttachment.setMapEntityType(entityType);
					mapAttachment.setFileName(filesForm.getFileName());
					mapAttachment.setFileSize(filesForm.getFileSize());
					mapAttachment.setFileContentType(filesForm.getFileType());
					mapAttachment.setFileLocation(filesForm.getFileLocation());
					mapAttachment.setFileTimestamp(filesForm.getFileTimestampName());
					session.save(mapAttachment);
					session.flush();
					session.refresh(mapAttachment);
					String mapCodeStr = String.valueOf(mapAttachment.getAttachmentId());
					mapCode = Integer.parseInt(mapCodeStr);

					if (session.contains(mapAttachment)) {
						session.evict(mapAttachment);
					}
				}
			}

			return mapCode;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
			return mapCode;
		}

	}

	/**
	 * Delete attached files.
	 * 
	 * @param deletedFileIDOne
	 *            the deleted file id one
	 * @param request
	 */
	public String deleteAttachedFiles(String deletedFileIDOne[], HttpServletRequest request, char type) { // M
																											// for
																											// Map
																											// &
																											// O
																											// for
																											// GovtOrder
		try {
			if (deletedFileIDOne != null && deletedFileIDOne.length > 0) {
				String deleteSuccessOne = deleteMetaData(deletedFileIDOne, type);
				return deleteSuccessOne;
			}
		} catch (Exception e) {
			IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
			expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
			return null;
		}
		return null;
	}

	public AddAttachmentBean getAttachmentBeanforMapState(List<CommonsMultipartFile> attachedFile, String[] titles, HttpServletRequest request) throws Exception {
		String fileUploadLocation = null;
		int allowedTotalNoOfAttachment = 5;
		long allowedTotalFileSize;
		long allowedIndividualFileSize;
		AttachmentMaster attachmentList = null;
		AddAttachmentBean addAttachmentBean = new AddAttachmentBean();

		try {
			attachmentList = getUploadFileConfigurationDetails(1);// 2 is
																	// file_masterId
																	// to get
																	// details
																	// of Map
			if (attachmentList != null) {
				fileUploadLocation = attachmentList.getFileLocation();
				allowedTotalNoOfAttachment = attachmentList.getFileLimit();
				allowedTotalFileSize = attachmentList.getTotalFileSize();
				allowedIndividualFileSize = attachmentList.getIndividualFileSize();

				List<String> allowedMimeTypeList = getMimeTypeList();
				int noOFAlreadyAttachedFiles1 = 0; // Already Attach Number Of
													// File.
				long totalSizeOFAlreadyAttachedFiles1 = 0L;
				int noOFMandatoryFile2 = 1;

				addAttachmentBean.setCurrentlyUploadFileList(attachedFile);// 1.List
																			// Of
																			// File
																			// To
																			// Be
																			// Upload.
				addAttachmentBean.setUploadLocation(fileUploadLocation);// 2.Location
																		// For
																		// File
																		// Upload
																		// In
																		// File
																		// System.
				addAttachmentBean.setFileTitle(titles);// 3.File Title Array.
				addAttachmentBean.setAllowedTotalNoOfAttachment(allowedTotalNoOfAttachment);// 4.Allowed
																							// Total
																							// Number
																							// Of
																							// Attachment.
				addAttachmentBean.setAllowedTotalFileSize(allowedTotalFileSize);// 5.Allowed
																				// Total
																				// File
																				// Size.
				addAttachmentBean.setAllowedIndividualFileSize(allowedIndividualFileSize);// 6.Allowed
																							// Individual
																							// File
																							// Size.
				addAttachmentBean.setNoOFAlreadyAttachedFiles(noOFAlreadyAttachedFiles1);// 7.Number
																							// Of
																							// Already
																							// Attached
																							// File.
				addAttachmentBean.setTotalSizeOFAlreadyAttachedFiles(totalSizeOFAlreadyAttachedFiles1);// 8.Already
																										// attached
																										// file
																										// total
																										// size.
				addAttachmentBean.setMimeTypeList(allowedMimeTypeList);// 9.File
																		// Mime
																		// Type
																		// List.
				addAttachmentBean.setDeletedFileID(request.getParameterValues("deletedFileID2"));// 10.File
																									// Id
																									// array
																									// to
																									// be
																									// deleted
				addAttachmentBean.setDeletedFileName(request.getParameterValues("deletedFileName2"));// 11.File
																										// Name
																										// Array
																										// To
																										// Be
																										// Deleted.
				addAttachmentBean.setDeletedFileList(request.getParameterValues("deletedFileSizeList2"));// 12.Deleted
																											// File
																											// Array.
				addAttachmentBean.setNoOFMandatoryFile(noOFMandatoryFile2);// 13.Number
																			// of
																			// Mandatory
																			// file.
			}

		} catch (Exception e) {
			log.debug("Exception" + e);

		}
		return addAttachmentBean;
	}

	/*
	 * public GovernmentOrder
	 * saveDataInGovtOrderForInvalidSubD(GovernmentOrderForm govtForm, Session
	 * session, int userId) throws Exception{
	 * 
	 * GovernmentOrder governmentOrder = new GovernmentOrder(); try {
	 * governmentOrder.setOrderDate(govtForm.getOrderDate());
	 * governmentOrder.setEffectiveDate(govtForm.getEffectiveDate());
	 * governmentOrder.setGazPubDate(govtForm.getGazPubDate());
	 * governmentOrder.setCreatedon(new Date()); governmentOrder.setDescription(
	 * "LGD DETAILS"); governmentOrder.setIssuedBy("GOVERNOR");
	 * //governmentOrder.setCreatedby(createdBy);
	 * governmentOrder.setLastupdated(new Date());
	 * //governmentOrder.setLastupdatedby(createdBy);
	 * governmentOrder.setLevel("U");
	 * governmentOrder.setOrderNo(govtForm.getOrderNo());
	 * governmentOrder.setStatus('A'); governmentOrder.setUserId(userId);
	 * localGovtBodyDAO.saveOrderDetails(governmentOrder, session); } catch
	 * (Exception e) { log.debug("Exception" + e); } return governmentOrder; }
	 */

	@Override
	public void saveDataInAttachmentForInvalidSubD(GovernmentOrderForm govtForm, List<AttachedFilesForm> attachedList, int maxCode, Session session) throws Exception {

		Attachment attachment = null;
		try {
			Iterator<AttachedFilesForm> it = attachedList.iterator();
			GovernmentOrder govorder = new GovernmentOrder();
			while (it.hasNext()) {
				AttachedFilesForm filesForm = (AttachedFilesForm) it.next();
				attachment = new Attachment();
				// attachment.setGovernmentOrder(govtOrder);
				attachment.setFileName(filesForm.getFileName());
				attachment.setFileSize(filesForm.getFileSize());
				attachment.setFileContentType(filesForm.getFileType());
				attachment.setFileLocation(filesForm.getFileLocation());
				attachment.setFileTimestamp(filesForm.getFileTimestampName());
				// attachment.setAnnouncement_id(maxCode);
				govorder.setOrderCode(maxCode);
				attachment.setGovernmentOrder(govorder);
				session.save(attachment);
				session.flush();
				if (session.contains(attachment)) {
					session.evict(attachment);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
		}
	}

	@Override
	public boolean saveDataInAttachment(GovernmentOrderForm governmentOrderForm, LocalGovtBodyForm LBForm, HttpSession session, int getordercode, Session hsession) throws Exception {
		return localGovtBodyDAO.saveDataInAttachmentGenerate(governmentOrderForm, LBForm, session, getordercode, hsession);
	}

	@SuppressWarnings("unchecked")
	public AddAttachmentBean getAttachmentBeanforMapLandregion(List<CommonsMultipartFile> attachedFile, String[] titles, HttpServletRequest request) throws Exception {
		AddAttachmentBean addAttachmentBean = new AddAttachmentBean();
		List<MapAttachment> mapAttachmentList = new ArrayList<MapAttachment>();
		try {
			if (request.getAttribute("mapAttachmentList") != null) {
				mapAttachmentList = (List<MapAttachment>) request.getAttribute("mapAttachmentList");
			}
			List<String> allowedMimeTypeList = this.getMimeTypeList();
			AttachmentMaster attachmentList = this.getUploadFileConfigurationDetails(1);
			String fileUploadLocation = attachmentList.getFileLocation();
			int allowedTotalNoOfAttachment = attachmentList.getFileLimit();
			long allowedTotalFileSize = attachmentList.getTotalFileSize();
			long allowedIndividualFileSize = attachmentList.getIndividualFileSize();
			int noOFAlreadyAttachedFiles1 = mapAttachmentList.size(); // Already
																		// Attach
																		// Number
																		// Of
																		// File.
			long totalSizeOFAlreadyAttachedFiles1 = 0L;
			if (!mapAttachmentList.isEmpty()) {
				totalSizeOFAlreadyAttachedFiles1 = mapAttachmentList.get(0).getFileSize();
			}
			int noOFMandatoryFile2 = 0;// Explicitly defined 0
			/*
			 * ================Getting the values from Application and Setting
			 * IN AddAttachmentBeanClass For Two==================
			 */

			addAttachmentBean = new AddAttachmentBean();
			addAttachmentBean.setCurrentlyUploadFileList(attachedFile);// 1.List
																		// Of
																		// File
																		// To Be
																		// Upload.
			addAttachmentBean.setUploadLocation(fileUploadLocation);// 2.Location
			// For
			// File
			// Upload
			// In
			// File
			// System.
			addAttachmentBean.setFileTitle(titles);// 3.File
			// Title
			// Array.
			addAttachmentBean.setAllowedTotalNoOfAttachment(allowedTotalNoOfAttachment);// 4.Allowed
			// Total
			// Number
			// Of
			// Attachment.
			addAttachmentBean.setAllowedTotalFileSize(allowedTotalFileSize);// 5.Allowed
			// Total
			// File
			// Size.
			addAttachmentBean.setAllowedIndividualFileSize(allowedIndividualFileSize);// 6.Allowed
			// Individual
			// File
			// Size.
			addAttachmentBean.setNoOFAlreadyAttachedFiles(noOFAlreadyAttachedFiles1);// 7.Number
			// Of
			// Already
			// Attached
			// File.
			addAttachmentBean.setTotalSizeOFAlreadyAttachedFiles(totalSizeOFAlreadyAttachedFiles1);// 8.Already
			// attached
			// file
			// total
			// size.
			addAttachmentBean.setMimeTypeList(allowedMimeTypeList);// 9.File
			// Mime
			// Type
			// List.
			addAttachmentBean.setDeletedFileID(request.getParameterValues("deletedFileID1"));// 10.File
																								// Id
																								// array
			// to be deleted
			addAttachmentBean.setDeletedFileName(request.getParameterValues("deletedFileName1"));// 11.File
																									// Name
			// Array To Be
			// Deleted.
			addAttachmentBean.setDeletedFileList(request.getParameterValues("deletedFileSizeList1"));// 12.Deleted
			// File
			// Array.
			addAttachmentBean.setNoOFMandatoryFile(noOFMandatoryFile2);// 13.Number
			// of
			// Mandatory
			// file.

		} catch (Exception e) {
			log.debug("Exception" + e);

		}
		return addAttachmentBean;
	}

	@SuppressWarnings("unchecked")
	public AddAttachmentBean getAttachmentBeanforGovLandregion(List<CommonsMultipartFile> attachedFile, String[] titles, HttpServletRequest request) throws Exception {
		AddAttachmentBean addAttachmentBean = new AddAttachmentBean();
		// List<MapAttachment> mapAttachmentList = new
		// ArrayList<MapAttachment>();

		List<Attachment> govattachmentList = new ArrayList<Attachment>();
		try {
			if (request.getAttribute("attachmentList") != null) {
				govattachmentList = (List<Attachment>) request.getAttribute("attachmentList");
			}
			List<String> allowedMimeTypeList = this.getMimeTypeList();
			AttachmentMaster attachmentList = this.getUploadFileConfigurationDetails(1);
			String fileUploadLocation = attachmentList.getFileLocation();
			int allowedTotalNoOfAttachment = attachmentList.getFileLimit();
			long allowedTotalFileSize = attachmentList.getTotalFileSize();
			long allowedIndividualFileSize = attachmentList.getIndividualFileSize();
			int noOFAlreadyAttachedFiles1 = govattachmentList.size(); // Already
																		// Attach
																		// Number
																		// Of
																		// File.
			long totalSizeOFAlreadyAttachedFiles1 = 0L;
			if (!govattachmentList.isEmpty()) {
				totalSizeOFAlreadyAttachedFiles1 = govattachmentList.get(0).getFileSize();
			}
			int noOFMandatoryFile2 = 0;// explicitly defined 0
			/*
			 * ================Getting the values from Application and Setting
			 * IN AddAttachmentBeanClass For Two==================
			 */

			addAttachmentBean = new AddAttachmentBean();
			addAttachmentBean.setCurrentlyUploadFileList(attachedFile);// 1.List
																		// Of
																		// File
																		// To Be
																		// Upload.
			addAttachmentBean.setUploadLocation(fileUploadLocation);// 2.Location
			// For
			// File
			// Upload
			// In
			// File
			// System.
			addAttachmentBean.setFileTitle(titles);// 3.File
			// Title
			// Array.
			addAttachmentBean.setAllowedTotalNoOfAttachment(allowedTotalNoOfAttachment);// 4.Allowed
			// Total
			// Number
			// Of
			// Attachment.
			addAttachmentBean.setAllowedTotalFileSize(allowedTotalFileSize);// 5.Allowed
			// Total
			// File
			// Size.
			addAttachmentBean.setAllowedIndividualFileSize(allowedIndividualFileSize);// 6.Allowed
			// Individual
			// File
			// Size.
			addAttachmentBean.setNoOFAlreadyAttachedFiles(noOFAlreadyAttachedFiles1);// 7.Number
			// Of
			// Already
			// Attached
			// File.
			addAttachmentBean.setTotalSizeOFAlreadyAttachedFiles(totalSizeOFAlreadyAttachedFiles1);// 8.Already
			// attached
			// file
			// total
			// size.
			addAttachmentBean.setMimeTypeList(allowedMimeTypeList);// 9.File
			// Mime
			// Type
			// List.
			addAttachmentBean.setDeletedFileID(request.getParameterValues("deletedFileID1"));// 10.File
																								// Id
																								// array
			// to be deleted
			addAttachmentBean.setDeletedFileName(request.getParameterValues("deletedFileName1"));// 11.File
																									// Name
			// Array To Be
			// Deleted.
			addAttachmentBean.setDeletedFileList(request.getParameterValues("deletedFileSizeList1"));// 12.Deleted
			// File
			// Array.
			addAttachmentBean.setNoOFMandatoryFile(noOFMandatoryFile2);// 13.Number
			// of
			// Mandatory
			// file.

		} catch (Exception e) {
			log.debug("Exception" + e);

		}
		return addAttachmentBean;
	}

	/*
	 * public AddAttachmentBean getAttachmentBeanforGovLandregion(
	 * List<CommonsMultipartFile> attachedFile, String[] titles,
	 * HttpServletRequest request) throws Exception { String fileUploadLocation
	 * = null; int allowedTotalNoOfAttachment = 5; long allowedTotalFileSize;
	 * long allowedIndividualFileSize; AttachmentMaster attachmentList = null;
	 * AddAttachmentBean addAttachmentBean = new AddAttachmentBean();
	 * List<Attachment> govattachmentList = new ArrayList<Attachment>(); try {
	 * if(request.getAttribute("attachmentList")!=null) {
	 * govattachmentList=(List<Attachment>)request.getAttribute("attachmentList"
	 * ); }
	 * 
	 * attachmentList = getUploadFileConfigurationDetails(2);// 2 is //
	 * file_masterId // to get // details // of Map if (attachmentList != null)
	 * { fileUploadLocation = attachmentList.getFileLocation();
	 * allowedTotalNoOfAttachment = attachmentList.getFileLimit();
	 * allowedTotalFileSize = attachmentList.getTotalFileSize();
	 * allowedIndividualFileSize = attachmentList .getIndividualFileSize();
	 * 
	 * List<String> allowedMimeTypeList = getMimeTypeList(); int
	 * noOFAlreadyAttachedFiles1 = govattachmentList.size(); // Already Attach
	 * Number Of // File. long totalSizeOFAlreadyAttachedFiles1 = 0L; int
	 * noOFMandatoryFile2 = 1;
	 * 
	 * addAttachmentBean.setCurrentlyUploadFileList(attachedFile);// 1.List //
	 * Of // File // To // Be // Upload.
	 * addAttachmentBean.setUploadLocation(fileUploadLocation);// 2.Location //
	 * For // File // Upload // In // File // System.
	 * addAttachmentBean.setFileTitle(titles);// 3.File Title Array.
	 * addAttachmentBean
	 * .setAllowedTotalNoOfAttachment(allowedTotalNoOfAttachment);// 4.Allowed
	 * // Total // Number // Of // Attachment.
	 * addAttachmentBean.setAllowedTotalFileSize(allowedTotalFileSize);//
	 * 5.Allowed // Total // File // Size. addAttachmentBean
	 * .setAllowedIndividualFileSize(allowedIndividualFileSize);// 6.Allowed //
	 * Individual // File // Size. addAttachmentBean
	 * .setNoOFAlreadyAttachedFiles(noOFAlreadyAttachedFiles1);// 7.Number // Of
	 * // Already // Attached // File. addAttachmentBean
	 * .setTotalSizeOFAlreadyAttachedFiles(totalSizeOFAlreadyAttachedFiles1);//
	 * 8.Already // attached // file // total // size.
	 * addAttachmentBean.setMimeTypeList(allowedMimeTypeList);// 9.File // Mime
	 * // Type // List. addAttachmentBean.setDeletedFileID(request
	 * .getParameterValues("deletedFileID2"));// 10.File Id // array to be //
	 * deleted addAttachmentBean.setDeletedFileName(request
	 * .getParameterValues("deletedFileName2"));// 11.File Name // Array To //
	 * Be // Deleted. addAttachmentBean.setDeletedFileList(request
	 * .getParameterValues("deletedFileSizeList2"));// 12.Deleted // File //
	 * Array. addAttachmentBean.setNoOFMandatoryFile(noOFMandatoryFile2);//
	 * 13.Number // of // Mandatory // file. }
	 * 
	 * } catch (Exception e) { log.debug("Exception" + e);
	 * 
	 * } return addAttachmentBean; }
	 */

	// To Retrive the attachment Details for Draft Village in Manange Draft
	// VillageForm.
	public List<VillageDraft> getAttachmentsbyOrderCodeForDraftVil(int villageCode) throws Exception {
		return govtOrderDAO.getAttachmentsbyOrderCodeForDraftVil(villageCode);
	}

	/*
	 * @Override public List<GovernmentOrderDetails>
	 * fetchExistingGovernmentOrder(String orderNo, String rangeFrom, String
	 * rangeTo) throws HibernateException { // TODO Auto-generated method stub
	 * return govtOrderDAO.fetchExistingGovernmentOrder(orderNo, rangeFrom,
	 * rangeTo); }
	 */
}
