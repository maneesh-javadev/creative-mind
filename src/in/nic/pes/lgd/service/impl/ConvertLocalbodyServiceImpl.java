package in.nic.pes.lgd.service.impl;

import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.ConvertRLBtoTLB;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.GovernmentOrderEntityWise;
import in.nic.pes.lgd.bean.LocalbodyforStateWise;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.dao.ConvertLocalbodyDAO;
import in.nic.pes.lgd.dao.LocalGovtBodyDAO;
import in.nic.pes.lgd.forms.ConvertRLBtoTLBForm;
import in.nic.pes.lgd.forms.ConvertRLBtoULBForm;
import in.nic.pes.lgd.forms.ConvertTLBtoRLBForm;
import in.nic.pes.lgd.forms.GenerateDetails;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.service.ConvertLocalbodyService;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pes.attachment.util.AttachedFilesForm;

@Service
public class ConvertLocalbodyServiceImpl implements ConvertLocalbodyService {
	private static final Logger LOG = Logger.getLogger(ConvertLocalbodyServiceImpl.class);

	@Autowired
	private ConvertLocalbodyDAO convertLocalbodyDAO;

	@Autowired
	private LocalGovtBodyDAO localGovtBodyDAO;

	@Autowired
	private SessionFactory sessionFactory;

	private Session session = null;
	private Transaction tx = null;

	/**
	 * Method for converting RLB to ULB
	 * 
	 * @Modified by Sushil Shakya
	 * 
	 */
	public Integer saveConversionRLBtoULB(ConvertRLBtoULBForm convertform, GovernmentOrderForm govtForm, List<AttachedFilesForm> attachedList, int stateCode, HttpSession httpSession) throws Exception {
		int urbanbodyTypecode = 0;
		int urbanbodyTypecode1 = 0;
		String lbFULLlist = null;
		String lbPARTlist = null;
		String lbFULLlist1 = null;
		String lbPARTlist1 = null;
		Integer retValue = null;
		String[] lbList =null;
        int lbCode=0; 

		SessionObject sessionObject = (SessionObject) httpSession.getAttribute("sessionObject");
        Integer userId = sessionObject.getUserId()!=null?sessionObject.getUserId().intValue():null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			String lbNameEnglish = convertform.getVillagelocalbodyNameDest();
			String lbUrbanEnglish = convertform.getUrbanlocalBodyNameEnglish();

			if (convertform != null && convertform.getLocalBodyLandRegionAreaDest() != null) {
				String tmp = convertform.getLocalBodyLandRegionAreaDest();
				String[] arr = tmp.split(",");
				tmp = "";
				for (String string : arr) {
					if (string.contains("_P")) {
						String tmpStr = string.substring(0, string.indexOf("_"));
						tmp += tmpStr + ",";
					}
				}
				if (!"".equals(tmp)) {
					convertform.setLocalBodyLandRegionAreaDest(tmp.substring(0, tmp.length() - 1));
				}
			}

			urbanbodyTypecode = Integer.parseInt(convertform.getUrbanLgTypeName());
			if (convertform.getUrbanLgTypeNameNew() != null && !convertform.getUrbanLgTypeNameNew().equals("")) {
				urbanbodyTypecode1 = Integer.parseInt(convertform.getUrbanLgTypeNameNew());
			}
			
			if(convertform.getCoverage()!=null){
				if(!convertform.getCoverage().isEmpty()){
					lbCode=	Integer.parseInt(convertform.getIntermediateLocalBodies()!=null?convertform.getIntermediateLocalBodies():"0");
					lbFULLlist1=convertform.getCoverage();
				}
			}
			
			
			if (lbNameEnglish != null) {
				lbList = lbNameEnglish.split(",");
				for (int i = 0; i < lbList.length; i++) {
					if (lbList[i].contains("FULL")) {
						if (lbFULLlist == null) {
							lbFULLlist = lbList[i].replace("_FULL", "") + ",";
						} else {
							lbFULLlist = lbFULLlist
									+ lbList[i].replace("_FULL", "") + ",";
						}
					} else if (lbList[i].contains("PART")) {
						if (lbPARTlist == null) {
							lbPARTlist = lbList[i].replace("_PART", "") + ",";
						} else {
							lbPARTlist = lbPARTlist
									+ lbList[i].replace("_PART", "") + ",";
						}
					}
				}
				if (lbFULLlist != null) {
					lbFULLlist1 = lbFULLlist.substring(0,
							lbFULLlist.length() - 1);
				}
				if (lbPARTlist != null) {
					lbPARTlist1 = lbPARTlist.substring(0,
							lbPARTlist.length() - 1);
				}
			}
			/* Modified by Sushil */
			boolean flag = false;
			if (convertform.getMergeRLBtoULB() != null) {
				retValue = convertLocalbodyDAO.saveConversionRLBtoULBforMerge(lbUrbanEnglish, userId, urbanbodyTypecode, govtForm.getEffectiveDate(), 'M', stateCode, lbFULLlist1, lbPARTlist1, convertform.getLocalBodyLandRegionAreaDest(),
						govtForm.getOrderNo(), govtForm.getOrderDate(), convertform.getOrderPath(), govtForm.getGazPubDate(), session,lbCode);

				flag = convertLocalbodyDAO.saveDataInAttach(convertform, attachedList, session);
				if (!flag) {
					retValue = null;
					tx.rollback();
				}
			} else if (convertform.getDeclarenewULB() != null) {
				retValue = convertLocalbodyDAO.saveConversionRLBtoULBforNew(lbUrbanEnglish, userId, urbanbodyTypecode1, govtForm.getEffectiveDate(), 'N', stateCode, lbFULLlist1, lbPARTlist1, convertform.getLocalBodyLandRegionAreaDest(),
						convertform.getLocalBodyNameInEn(), convertform.getLocalBodyNameInLcl(), convertform.getLocalBodyliasInEn(), convertform.getLocalBodyliasInLcl(), govtForm.getOrderNo(), govtForm.getOrderDate(), convertform.getOrderPath(),
						govtForm.getGazPubDate(), session,lbCode);

				flag = convertLocalbodyDAO.saveDataInAttachforNewUlb(convertform, attachedList, session);
				if (!flag) {
					retValue = null;
					tx.rollback();
				}
			}
			tx.commit();
			return retValue;
		} catch (Exception e) {
			tx.rollback();
			return retValue;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public List<ConvertRLBtoTLB> saveConversionRLBtoTLB(ConvertRLBtoTLBForm convertform, GovernmentOrderForm govtForm, List<AttachedFilesForm> attachedList, int stateCode, Integer userId) throws Exception {
		int urbanbodyTypecode = 0;
		int urbanbodyTypecode1 = 0;
		// String retValue=null;
		List<ConvertRLBtoTLB> retValue = null;
		String lbFULLlist1 = null;
		String lbPARTlist1 = null;
		String lbFULLlist2 = null;
		String lbcodeformerger = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			String sourcefullfirsttiercodelist = null;
			String sourcefullsecondtiercodelist = null;
			String destLBTypeCode = null;
			String source_rlb_type = convertform.getRurallbTypeName();

			String[] rlbTypeNameArr = source_rlb_type.split(":");
			// String lbcodeformerger = rlbTypeNameArr[0];
			String sourcerlbType = rlbTypeNameArr[1];

			if (sourcerlbType.charAt(0) == 'D') {
				sourcefullfirsttiercodelist = convertform.getLgd_LBDistPDestList();
				sourcefullsecondtiercodelist = convertform.getLgd_LBDistCAreaDestList();

				String[] lbList = sourcefullfirsttiercodelist.split(",");

				for (int i = 0; i < lbList.length; i++) {
					if (lbList[i].contains("FULL")) {
						if (lbFULLlist1 == null) {
							lbFULLlist1 = lbList[i].replace("_FULL", "") + ",";
						} else {
							lbFULLlist1 = lbFULLlist1 + lbList[i].replace("_FULL", "") + ",";
						}
					}
				}
				if (lbFULLlist1 != null) {
					lbFULLlist1 = lbFULLlist1.substring(0, lbFULLlist1.length() - 1);
				}

				String[] lbList2 = sourcefullsecondtiercodelist.split(",");
				for (int i = 0; i < lbList2.length; i++) {
					if (lbList2[i].contains("FULL")) {
						if (lbFULLlist2 == null) {
							lbFULLlist2 = lbList2[i].replace("_FULL_D", "") + ",";
						} else {
							lbFULLlist2 = lbFULLlist2 + lbList2[i].replace("_FULL_D", "") + ",";
						}
					}
				}
				if (lbFULLlist2 != null) {
					lbFULLlist2 = lbFULLlist2.substring(0, lbFULLlist2.length() - 1);
				}
			}
			if (sourcerlbType.charAt(0) == 'I') {
				sourcefullfirsttiercodelist = convertform.getLgd_LBDistPDestList();
				sourcefullsecondtiercodelist = convertform.getLgd_LBInterCAreaDestList();

				String[] lbList = sourcefullfirsttiercodelist.split(",");

				for (int i = 0; i < lbList.length; i++) {
					if (lbList[i].contains("FULL")) {
						if (lbFULLlist1 == null) {
							lbFULLlist1 = lbList[i].replace("_FULL", "") + ",";
						} else {
							lbFULLlist1 = lbFULLlist1 + lbList[i].replace("_FULL", "") + ",";
						}
					}
				}
				if (lbFULLlist1 != null) {
					lbFULLlist1 = lbFULLlist1.substring(0, lbFULLlist1.length() - 1);
				}

				String[] lbList2 = sourcefullsecondtiercodelist.split(",");
				for (int i = 0; i < lbList2.length; i++) {
					if (lbList2[i].contains("FULL")) {
						if (lbFULLlist2 == null) {
							lbFULLlist2 = lbList2[i].replace("_FULL_T", "") + ",";
						} else {
							lbFULLlist2 = lbFULLlist2 + lbList2[i].replace("_FULL_T", "") + ",";
						}
					}
				}
				if (lbFULLlist2 != null) {
					lbFULLlist2 = lbFULLlist2.substring(0, lbFULLlist2.length() - 1);
				}
			}
			if (sourcerlbType.charAt(0) == 'V') {
				sourcefullfirsttiercodelist = convertform.getLgd_LBVillageDestAtVillageCA();
				sourcefullsecondtiercodelist = convertform.getLgd_LBVillageCAreaDestL();

				String[] lbList = sourcefullfirsttiercodelist.split(",");

				for (int i = 0; i < lbList.length; i++) {
					if (lbList[i].contains("FULL")) {
						if (lbFULLlist1 == null) {
							lbFULLlist1 = lbList[i].replace("_FULL", "") + ",";
						} else {
							lbFULLlist1 = lbFULLlist1 + lbList[i].replace("_FULL", "") + ",";
						}
					}

					if (lbList[i].contains("PART")) {
						if (lbPARTlist1 == null) {
							lbPARTlist1 = lbList[i].replace("_PART", "") + ",";
						} else {
							lbPARTlist1 = lbPARTlist1 + lbList[i].replace("_PART", "") + ",";
						}
					}

				}
				if (lbFULLlist1 != null) {
					lbFULLlist1 = lbFULLlist1.substring(0, lbFULLlist1.length() - 1);
				}

				if (lbPARTlist1 != null) {
					lbPARTlist1 = lbPARTlist1.substring(0, lbPARTlist1.length() - 1);
				}

				/*
				 * String[] lbList2= sourcefullsecondtiercodelist.split(",");
				 * for (int i = 0; i < lbList2.length; i++) { if
				 * (lbList2[i].contains("FULL")) { if (lbFULLlist2 == null) {
				 * lbFULLlist2 = lbList2[i].replace("_FULL_V", "") + ","; } else
				 * { lbFULLlist2 = lbFULLlist2 + lbList2[i].replace("_FULL_V",
				 * "") + ","; } } }
				 * 
				 * for(int i = 0; i < lbList2.length; i++) {
				 * lbFULLlist2=lbList2[i]; }
				 */
				lbFULLlist2 = sourcefullsecondtiercodelist;
				/*
				 * if (lbList2 != null) { lbFULLlist2 = lbFULLlist2.substring(0,
				 * lbFULLlist2.length() - 1); }
				 */
			}

			// lbcodeformerger=convertform.getLgd_LBDistrictforExist();
		
			if (convertform.getMergeRLBtoTLB() != null) {
				String targetTlbType = convertform.getTraditionalLbTypeName();

				String[] targetTlbTypeNameArr = targetTlbType.split(":");
				// String lbcodeformerger = rlbTypeNameArr[0];
				destLBTypeCode = targetTlbTypeNameArr[0];

				lbcodeformerger = convertform.getLgd_LBVillagePanchaytforExist(); // modified
																					// by
																					// sushil
																					// on
																					// 08-05-2013
				retValue = convertLocalbodyDAO.saveConversionRLBtoTLBforMerge(sourcerlbType, lbcodeformerger, userId, urbanbodyTypecode, govtForm.getEffectiveDate(), 'M', stateCode, lbFULLlist1, lbPARTlist1, lbFULLlist2, null, govtForm.getOrderNo(),
						govtForm.getOrderDate(), "govtOrderUpload", govtForm.getGazPubDate(), destLBTypeCode, session);
				/*
				 * if(retValue !=null) {
				 * convertLocalbodyDAO.saveDataInAttachRLBtoTLB
				 * (convertform,attachedList,session); }
				 */
			}

			else if (convertform.getDeclarenewTLB() != null) {

				String targetTlbType = convertform.getTraditionalLgTypeNameNew();

				String[] targetTlbTypeNameArr = targetTlbType.split(":");
				// String lbcodeformerger = rlbTypeNameArr[0];
				destLBTypeCode = targetTlbTypeNameArr[0];

				lbcodeformerger = convertform.getLgd_LBDistrictforExistNew();

				retValue = convertLocalbodyDAO.saveConversionRLBtoTLBforNew(sourcerlbType, lbcodeformerger, userId, urbanbodyTypecode1, govtForm.getEffectiveDate(), 'N', stateCode, lbFULLlist1, lbPARTlist1, lbFULLlist2, null,
						convertform.getLocalBodyNameInEn(), convertform.getLocalBodyNameInLcl(), convertform.getLocalBodyAliasInEn(), convertform.getLocalBodyAliasInLcl(), govtForm.getOrderNo(), govtForm.getOrderDate(), "govtOrderUpload",
						govtForm.getGazPubDate(), destLBTypeCode, convertform.getLgd_LBIntermediateAtVillageforNew(), session);
				/*
				 * if(retValue !=null) {
				 * convertLocalbodyDAO.saveDataInAttachRLBtoTLB
				 * (convertform,attachedList,session); }
				 */
			}

			tx.commit();

		} catch (Exception e) {
			tx.rollback();
			// return false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return retValue;
	}

	public List<ConvertRLBtoTLB> saveConversionTLBtoRLB(ConvertTLBtoRLBForm convertform, GovernmentOrderForm govtForm, List<AttachedFilesForm> attachedList, int stateCode, Integer userId) throws Exception {
		int urbanbodyTypecode = 0;
		int urbanbodyTypecode1 = 0;
		// String retValue=null;
		List<ConvertRLBtoTLB> retValue = null;
		String lbFULLlist1 = null;
		String lbPARTlist1 = null;
		String lbFULLlist2 = null;
		String lbcodeformerger = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			String sourcefullfirsttiercodelist = null;
			String sourcefullsecondtiercodelist = null;
			String destLBTypeCode = null;
			String source_rlb_type = convertform.getRurallbTypeName();

			String[] rlbTypeNameArr = source_rlb_type.split(":");
			// String lbcodeformerger = rlbTypeNameArr[0];
			String sourcerlbType = rlbTypeNameArr[1];

			if (sourcerlbType.charAt(0) == 'D') {
				sourcefullfirsttiercodelist = convertform.getLgd_LBDistPDestList();
				sourcefullsecondtiercodelist = convertform.getLgd_LBDistCAreaDestList();

				String[] lbList = sourcefullfirsttiercodelist.split(",");

				for (int i = 0; i < lbList.length; i++) {
					if (lbList[i].contains("FULL")) {
						if (lbFULLlist1 == null) {
							lbFULLlist1 = lbList[i].replace("_FULL", "") + ",";
						} else {
							lbFULLlist1 = lbFULLlist1 + lbList[i].replace("_FULL", "") + ",";
						}
					}
				}
				if (lbFULLlist1 != null) {
					lbFULLlist1 = lbFULLlist1.substring(0, lbFULLlist1.length() - 1);
				}

				String[] lbList2 = sourcefullsecondtiercodelist.split(",");
				for (int i = 0; i < lbList2.length; i++) {
					if (lbList2[i].contains("FULL")) {
						if (lbFULLlist2 == null) {
							lbFULLlist2 = lbList2[i].replace("_FULL_D", "") + ",";
						} else {
							lbFULLlist2 = lbFULLlist2 + lbList2[i].replace("_FULL_D", "") + ",";
						}
					}
				}
				if (lbFULLlist2 != null) {
					lbFULLlist2 = lbFULLlist2.substring(0, lbFULLlist2.length() - 1);
				}
			}
			if (sourcerlbType.charAt(0) == 'I') {
				sourcefullfirsttiercodelist = convertform.getLgd_LBDistPDestList();
				sourcefullsecondtiercodelist = convertform.getLgd_LBInterCAreaDestList();

				String[] lbList = sourcefullfirsttiercodelist.split(",");

				for (int i = 0; i < lbList.length; i++) {
					if (lbList[i].contains("FULL")) {
						if (lbFULLlist1 == null) {
							lbFULLlist1 = lbList[i].replace("_FULL", "") + ",";
						} else {
							lbFULLlist1 = lbFULLlist1 + lbList[i].replace("_FULL", "") + ",";
						}
					}
				}
				if (lbFULLlist1 != null) {
					lbFULLlist1 = lbFULLlist1.substring(0, lbFULLlist1.length() - 1);
				}

				String[] lbList2 = sourcefullsecondtiercodelist.split(",");
				for (int i = 0; i < lbList2.length; i++) {
					if (lbList2[i].contains("FULL")) {
						if (lbFULLlist2 == null) {
							lbFULLlist2 = lbList2[i].replace("_FULL_T", "") + ",";
						} else {
							lbFULLlist2 = lbFULLlist2 + lbList2[i].replace("_FULL_T", "") + ",";
						}
					}
				}
				if (lbFULLlist2 != null) {
					lbFULLlist2 = lbFULLlist2.substring(0, lbFULLlist2.length() - 1);
				}
			}
			if (sourcerlbType.charAt(0) == 'V') {
				sourcefullfirsttiercodelist = convertform.getLgd_LBVillageDestAtVillageCA();
				sourcefullsecondtiercodelist = convertform.getLgd_LBVillageCAreaDestL();

				String[] lbList = sourcefullfirsttiercodelist.split(",");

				for (int i = 0; i < lbList.length; i++) {
					if (lbList[i].contains("FULL")) {
						if (lbFULLlist1 == null) {
							lbFULLlist1 = lbList[i].replace("_FULL", "") + ",";
						} else {
							lbFULLlist1 = lbFULLlist1 + lbList[i].replace("_FULL", "") + ",";
						}
					}

					if (lbList[i].contains("PART")) {
						if (lbPARTlist1 == null) {
							lbPARTlist1 = lbList[i].replace("_PART", "") + ",";
						} else {
							lbPARTlist1 = lbPARTlist1 + lbList[i].replace("_PART", "") + ",";
						}
					}

				}
				if (lbFULLlist1 != null) {
					lbFULLlist1 = lbFULLlist1.substring(0, lbFULLlist1.length() - 1);
				}

				if (lbPARTlist1 != null) {
					lbPARTlist1 = lbPARTlist1.substring(0, lbPARTlist1.length() - 1);
				}

				/*
				 * String[] lbList2= sourcefullsecondtiercodelist.split(",");
				 * for (int i = 0; i < lbList2.length; i++) { if
				 * (lbList2[i].contains("FULL")) { if (lbFULLlist2 == null) {
				 * lbFULLlist2 = lbList2[i].replace("_FULL_V", "") + ","; } else
				 * { lbFULLlist2 = lbFULLlist2 + lbList2[i].replace("_FULL_V",
				 * "") + ","; } } }
				 * 
				 * for(int i = 0; i < lbList2.length; i++) {
				 * lbFULLlist2=lbList2[i]; }
				 */
				lbFULLlist2 = sourcefullsecondtiercodelist;
				/*
				 * if (lbList2 != null) { lbFULLlist2 = lbFULLlist2.substring(0,
				 * lbFULLlist2.length() - 1); }
				 */
			}


			// String villCode =convertform.getLocalBodyLandRegionAreaDest();
			if (convertform.getMergeTLBtoRLB() != null) {
				String targetTlbType = convertform.getTraditionalLbTypeName();

				String[] targetTlbTypeNameArr = targetTlbType.split(":");
				// String lbcodeformerger = rlbTypeNameArr[0];
				destLBTypeCode = targetTlbTypeNameArr[0];

				lbcodeformerger = convertform.getLgd_LBDistrictforExist();
				retValue = convertLocalbodyDAO.saveConversionTLBtoRLBforMerge(sourcerlbType, lbcodeformerger, userId, urbanbodyTypecode, govtForm.getEffectiveDate(), 'M', stateCode, lbFULLlist1, lbPARTlist1, lbFULLlist2, null, govtForm.getOrderNo(),
						govtForm.getOrderDate(), "govtOrderUpload", govtForm.getGazPubDate(), destLBTypeCode, session);
				/*
				 * if(retValue !=null) {
				 * convertLocalbodyDAO.saveDataInAttachRLBtoTLB
				 * (convertform,attachedList,session); }
				 */
			}

			else if (convertform.getDeclarenewRLB() != null) {

				String targetTlbType = convertform.getTraditionalLgTypeNameNew();

				String[] targetTlbTypeNameArr = targetTlbType.split(":");
				// String lbcodeformerger = rlbTypeNameArr[0];
				destLBTypeCode = targetTlbTypeNameArr[0];

				lbcodeformerger = convertform.getLgd_LBDistrictforExistNew();

				retValue = convertLocalbodyDAO.saveConversionTLBtoRLBforNew(sourcerlbType, lbcodeformerger, userId, urbanbodyTypecode1, govtForm.getEffectiveDate(), 'N', stateCode, lbFULLlist1, lbPARTlist1, lbFULLlist2, null,
						convertform.getLocalBodyNameInEn(), convertform.getLocalBodyNameInLcl(), convertform.getLocalBodyAliasInEn(), convertform.getLocalBodyAliasInLcl(), govtForm.getOrderNo(), govtForm.getOrderDate(), "govtOrderUpload",
						govtForm.getGazPubDate(), destLBTypeCode, convertform.getLgd_LBIntermediateAtVillageforNew(), session);
				/*
				 * if(retValue !=null) {
				 * convertLocalbodyDAO.saveDataInAttachRLBtoTLB
				 * (convertform,attachedList,session); }
				 */
			}

			tx.commit();

		} catch (Exception e) {
			tx.rollback();
			// return false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return retValue;
	}

	public Integer saveConversionRLBtoULBforTemplate(ConvertRLBtoULBForm convertform, GovernmentOrderForm govtForm, int stateCode, HttpSession httpSession) throws Exception {
		int urbanbodyTypecode = 0;
		int urbanbodyTypecode1 = 0;
		String lbFULLlist = null;
		String lbPARTlist = null;

		String lbFULLlist1 = null;
		String lbPARTlist1 = null;
		Integer retValue = null;
        int lbCode=0;
		
		SessionObject sessionObject = (SessionObject) httpSession.getAttribute("sessionObject");
        Integer userId = sessionObject.getUserId()!=null?sessionObject.getUserId().intValue():null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			String lbNameEnglish = convertform.getVillagelocalbodyNameDest();
			String lbUrbanEnglish = convertform.getUrbanlocalBodyNameEnglish();

			urbanbodyTypecode = Integer.parseInt(convertform.getUrbanLgTypeName());
			if (convertform.getUrbanLgTypeNameNew() != null && !convertform.getUrbanLgTypeNameNew().equals("")) {
				urbanbodyTypecode1 = Integer.parseInt(convertform.getUrbanLgTypeNameNew());
			}

			/* added by Sushil on 13-03-2013 */
			if (convertform != null && convertform.getLocalBodyLandRegionAreaDest() != null) {
				String tmp = convertform.getLocalBodyLandRegionAreaDest();
				String[] arr = tmp.split(",");
				tmp = "";
				for (String string : arr) {
					if (string.contains("_P")) {
						String tmpStr = string.substring(0, string.indexOf("_"));
						tmp += tmpStr + ",";
					}
				}
				convertform.setLocalBodyLandRegionAreaDest(tmp.substring(0, tmp.length() - 1));
			}

			String[] lbList = lbNameEnglish.split(",");

			for (int i = 0; i < lbList.length; i++) {
				if (lbList[i].contains("FULL")) {
					if (lbFULLlist == null) {
						lbFULLlist = lbList[i].replace("_FULL", "") + ",";
					} else {
						lbFULLlist = lbFULLlist + lbList[i].replace("_FULL", "") + ",";
					}
				} else if (lbList[i].contains("PART")) {
					if (lbPARTlist == null) {
						lbPARTlist = lbList[i].replace("_PART", "") + ",";
					} else {
						lbPARTlist = lbPARTlist + lbList[i].replace("_PART", "") + ",";
					}
				}
			}
			if (lbFULLlist != null) {
				lbFULLlist1 = lbFULLlist.substring(0, lbFULLlist.length() - 1);
			}
			if (lbPARTlist != null) {
				lbPARTlist1 = lbPARTlist.substring(0, lbPARTlist.length() - 1);
			}

			if (convertform.getMergeRLBtoULB() != null) {
				retValue = convertLocalbodyDAO.saveConversionRLBtoULBforMerge(lbUrbanEnglish, userId, urbanbodyTypecode, govtForm.getEffectiveDate(), 'M', stateCode, lbFULLlist1, lbPARTlist1, convertform.getLocalBodyLandRegionAreaDest(),
						convertform.getOrderNo(), convertform.getOrderDate(), convertform.getOrderPath(), convertform.getGazPubDate(), session,lbCode);

			}

			else if (convertform.getDeclarenewULB() != null) {
				retValue = convertLocalbodyDAO.saveConversionRLBtoULBforNew(lbUrbanEnglish, userId, urbanbodyTypecode1, govtForm.getEffectiveDate(), 'N', stateCode, lbFULLlist1, lbPARTlist1, convertform.getLocalBodyLandRegionAreaDest(),
						convertform.getLocalBodyNameInEn(), convertform.getLocalBodyNameInLcl(), convertform.getLocalBodyliasInEn(), convertform.getLocalBodyliasInLcl(), convertform.getOrderNo(), convertform.getOrderDate(),
						convertform.getOrderPath(), convertform.getGazPubDate(), session,lbCode);

			}
			GovernmentOrder govtOrder = saveDataInGovtOrder(govtForm, session);

			saveDataInGovtOrderEntityWise(govtOrder, lbUrbanEnglish, convertform.getDeclarenewULB(), session);
			tx.commit();
			return retValue;
		} catch (Exception e) {
			tx.rollback();
			return retValue;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public void saveDataInAttachment(GovernmentOrder govtOrder, GovernmentOrderForm govtForm, List<AttachedFilesForm> attachedList, Session session) throws Exception {

		Attachment attachment = null;
		try {
			if (attachedList != null && !attachedList.isEmpty()) {
				Iterator<AttachedFilesForm> it = attachedList.iterator();
				while (it.hasNext()) {
					AttachedFilesForm filesForm = (AttachedFilesForm) it.next();
					attachment = new Attachment();
					attachment.setGovernmentOrder(govtOrder);
					attachment.setFileName(filesForm.getFileName());
					attachment.setFileSize(filesForm.getFileSize());
					attachment.setFileContentType(filesForm.getFileType());
					// attachment.setFileLocation(java.io.File.separator +
					// filesForm.getFileLocation());
					attachment.setFileLocation(filesForm.getFileLocation());
					attachment.setFileTimestamp(filesForm.getFileTimestampName());
					session.save(attachment);
					/*
					 * session.flush(); if (session.contains(attachment))
					 * session.evict(attachment);
					 */
				}
			}
		} catch (Exception e) {
			Log.error(e);
		}
	}

	@Override
	public synchronized boolean saveDataInAttachmentWithOrderCode(Integer orderCode, List<AttachedFilesForm> attachedList, Session session) throws Exception {

		Attachment attachment = null;
		GovernmentOrder governmentOrder = new GovernmentOrder();
		governmentOrder.setOrderCode(orderCode);
		try {
			if (attachedList != null && !attachedList.isEmpty()) {
				Iterator<AttachedFilesForm> it = attachedList.iterator();
				while (it.hasNext()) {
					AttachedFilesForm filesForm = (AttachedFilesForm) it.next();
					attachment = new Attachment();
					// attachment.setAnnouncement_id(orderCode);
					attachment.setFileName(filesForm.getFileName());
					attachment.setFileSize(filesForm.getFileSize());
					attachment.setFileContentType(filesForm.getFileType());
					// attachment.setFileLocation(java.io.File.separator +
					// filesForm.getFileLocation());
					attachment.setFileLocation(filesForm.getFileLocation());
					attachment.setFileTimestamp(filesForm.getFileTimestampName());
					attachment.setGovernmentOrder(governmentOrder);
					session.save(attachment);

				}
			}
			return true;
		} catch (Exception e) {
			LOG.error("Exception-->" + e);
			return false;
		}
	}

	public GovernmentOrder saveDataInGovtOrder(GovernmentOrderForm govtForm, Session session) throws Exception {

		GovernmentOrder governmentOrder = new GovernmentOrder();

		Long Userid = (long) 11823;
		Integer userId = Integer.valueOf(Userid.intValue());
		Long createdBy = (long) 11823;
		try {
			governmentOrder.setOrderDate(govtForm.getOrderDate());
			governmentOrder.setEffectiveDate(govtForm.getEffectiveDate());
			governmentOrder.setGazPubDate(govtForm.getGazPubDate());
			governmentOrder.setCreatedon(new Date());
			governmentOrder.setDescription("LGD DETAILS");
			governmentOrder.setIssuedBy("GOVERNOR");
			governmentOrder.setCreatedby(createdBy);
			governmentOrder.setLastupdated(new Date());
			governmentOrder.setLastupdatedby(createdBy);
			governmentOrder.setLevel("U");
			governmentOrder.setOrderNo(govtForm.getOrderNo());
			governmentOrder.setStatus('A');
			governmentOrder.setUserId(userId);
			localGovtBodyDAO.saveOrderDetails(governmentOrder, session);
		} catch (Exception e) {
			LOG.error("Exception-->" + e);
		}
		return governmentOrder;
	}

	public boolean saveDataInGovtOrderEntityWise(GovernmentOrder govtOrder, String lbUrbanEnglish, String declareType, Session session) throws Exception {

		GovernmentOrderEntityWise govtOrderEWise = new GovernmentOrderEntityWise();
		int localBodyCode = 0;
		int localBodyVersion = 0;
		try {
			if (declareType != null) {
				localBodyCode = localGovtBodyDAO.getMaxRecords("select COALESCE(max(local_body_code),1) from localbody where isactive =true");

				if (localBodyCode > 0) {
					localBodyCode = localBodyCode + 1;
					govtOrderEWise.setGovernmentOrder(govtOrder);
					govtOrderEWise.setEntityCode(localBodyCode);// Change Here
																// in New Code
																// Bcz We
																// changed The
																// name
					govtOrderEWise.setEntityVersion(1);
					govtOrderEWise.setEntityType('G');
				}
			} else {

				localBodyCode = Integer.parseInt(lbUrbanEnglish);
				if (localBodyCode > 0) {
					localBodyVersion = localGovtBodyDAO.getMaxRecords("select COALESCE(max(local_body_version),1) from localbody where isactive =true and local_body_code = " + localBodyCode);
					govtOrderEWise.setGovernmentOrder(govtOrder);
					govtOrderEWise.setEntityCode(Integer.parseInt(lbUrbanEnglish));// Change
																					// Here
																					// in
																					// New
																					// Code
																					// Bcz
																					// We
																					// changed
																					// The
																					// name
					govtOrderEWise.setEntityVersion(localBodyVersion);
					govtOrderEWise.setEntityType('G');
				}

			}
			localGovtBodyDAO.saveOrderDetailsEntityWise(govtOrderEWise, session);
		} catch (Exception e) {
			LOG.error("Exception-->" + e);
		}
		return true;
	}

	/*
	 * public boolean saveConversionRLBtoTLB(ConvertRLBtoTLBForm convertform,
	 * HttpServletRequest request, int stateCode) throws Exception { Session
	 * session = null; Transaction tx = null;
	 * 
	 * try { session = sessionFactory.openSession(); tx =
	 * session.beginTransaction();
	 * 
	 * tx.commit(); return true; } catch (Exception e) {
	 * 
	 * e.printStackTrace(); if(tx != null) tx.rollback(); return false; }
	 * 
	 * }
	 */

	@Override
	public synchronized boolean saveDataInAttachmentWithOrderCodeGenrate(Integer orderCode, GenerateDetails generatedetails, Session session) throws Exception {

		Attachment attachment = null;
		GovernmentOrder governmentOrder = new GovernmentOrder();
		governmentOrder.setOrderCode(orderCode);
		try {
			if (generatedetails != null) {

				attachment = new Attachment();
				// attachment.setAnnouncement_id(orderCode);
				attachment.setFileName(generatedetails.getFileName());
				attachment.setFileSize(generatedetails.getFileSize());
				attachment.setFileContentType(generatedetails.getFileContentType());
				// attachment.setFileLocation(java.io.File.separator +
				// filesForm.getFileLocation());
				attachment.setFileLocation(generatedetails.getFileLocation());
				attachment.setFileTimestamp(generatedetails.getFileTimestamp());
				attachment.setGovernmentOrder(governmentOrder);
				session.save(attachment);

			}
			return true;
		} catch (Exception e) {
			LOG.error("Exception-->" + e);
			return false;
		}
	}

	// get StateWise List
	public List<LocalbodyforStateWise> getLocalBodyListStateWise(char localBodyType, int stateCode) throws Exception {
		return convertLocalbodyDAO.getLocalBodyListStateWise(localBodyType, stateCode);
	}

	@Override
	public boolean saveConversionRLBtoTLBforTemplate(GovernmentOrderForm governmentOrderForm, ConvertRLBtoTLBForm convertform, HttpSession session, int getordercode) throws Exception {
		return convertLocalbodyDAO.saveConversionRLBtoTLBforTemplate(governmentOrderForm, convertform, session, getordercode);
	}

	@Override
	public boolean saveConversionTLBtoRLBforTemplate(GovernmentOrderForm governmentOrderForm, ConvertTLBtoRLBForm convertform, HttpSession session, int getordercode) throws Exception {
		return convertLocalbodyDAO.saveConversionTLBtoRLBforTemplate(governmentOrderForm, convertform, session, getordercode);
	}

	@Override
	public boolean saveDataInAttachRLBtoTLB(GovernmentOrderForm governmentOrderForm, ConvertRLBtoTLBForm convertform, List<AttachedFilesForm> attachedList, HttpSession session, int getordercode) throws Exception {
		return convertLocalbodyDAO.saveDataInAttachRLBtoTLB(governmentOrderForm, convertform, attachedList, session, getordercode);
	}

	@Override
	public boolean saveDataInAttachTLBtoRLB(GovernmentOrderForm governmentOrderForm, ConvertTLBtoRLBForm convertform, List<AttachedFilesForm> attachedList, HttpSession session, int getordercode) throws Exception {
		return convertLocalbodyDAO.saveDataInAttachTLBtoRLB(governmentOrderForm, convertform, attachedList, session, getordercode);
	}

}
