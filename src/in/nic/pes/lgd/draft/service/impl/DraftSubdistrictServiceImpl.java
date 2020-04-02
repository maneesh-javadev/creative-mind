package in.nic.pes.lgd.draft.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nic.pes.lgd.bean.Subdistrict;
import in.nic.pes.lgd.common.EncryptionUtil;
import in.nic.pes.lgd.draft.dao.DraftSubdistrictDao;
import in.nic.pes.lgd.draft.entities.SubdistrictDraft;
import in.nic.pes.lgd.draft.form.DraftGovermentOrderForm;
import in.nic.pes.lgd.draft.form.DraftManageSubdistrictForm;
import in.nic.pes.lgd.draft.form.DraftSubdistrictForm;
import in.nic.pes.lgd.draft.service.DraftSubdistrictService;

@Service 
public class DraftSubdistrictServiceImpl implements DraftSubdistrictService {
	
	@Autowired 
	DraftSubdistrictDao draftSubdistrictDao;

	@Override
	public List<Subdistrict> getDraftSubdistrictList(Integer districtCode, String subdistrictsPart,String subdistrictsFull) throws Exception {
		List<Integer> subdistrictListPart =new ArrayList<Integer>();
		subdistrictListPart.add(-1);
		List<Integer> subdistrictListFull=new ArrayList<Integer>();
		subdistrictListFull.add(-1);
		
		if (subdistrictsPart!=null) {
			Scanner scanner = new Scanner(subdistrictsPart);
			scanner.useDelimiter("@");
			while (scanner.hasNext()) {
				subdistrictListPart.add(Integer.parseInt(scanner.next()));
			}
			scanner.close();
		}
		
		if (subdistrictsFull!=null) {
			Scanner scanner = new Scanner(subdistrictsFull);
			scanner.useDelimiter("@");
			while (scanner.hasNext()) {
				subdistrictListFull.add(Integer.parseInt(scanner.next()));
			}
			scanner.close();
		}
		
		return draftSubdistrictDao.getDraftSubdistrictList(districtCode, subdistrictListPart, subdistrictListFull);
	}

	
	@Override
	public Character subdistrictNameIsUnique(String subdistrictNameEng, Integer districtCode) throws Exception {
		return draftSubdistrictDao.subdistrictNameIsUnique(subdistrictNameEng, districtCode);
	}


	@Override
	public Integer saveSubdistrictinDraft(LinkedList<DraftSubdistrictForm> storedSubdistrictForms,DraftGovermentOrderForm draftGovermentOrderForm,HttpServletRequest request,HttpSession httpSession) throws Exception {
		return draftSubdistrictDao.saveSubdistrictinDraft(storedSubdistrictForms, draftGovermentOrderForm,request,httpSession);
	}


	

	@Override
	public DraftSubdistrictForm loadSubdistrictDraftbyId(DraftSubdistrictForm draftSubdistrictForm) throws Exception {
		return draftSubdistrictDao.loadSubdistrictDraftbyId(draftSubdistrictForm);
	}


	@Override
	public boolean updateSubdistrictinDraft(DraftSubdistrictForm draftSubdistrictForm) throws Exception {
	return draftSubdistrictDao.updateSubdistrictinDraft(draftSubdistrictForm);
	}


	@Override
	public LinkedList<DraftSubdistrictForm> saveSubdistrict(LinkedList<DraftSubdistrictForm> storedSubdistrictForms,DraftGovermentOrderForm draftGovermentOrderForm) throws Exception {
		return draftSubdistrictDao.saveSubdistrict(storedSubdistrictForms, draftGovermentOrderForm);
	}


	@Override
	public Map<String, Object> viewSaveSubdistrictDetails(LinkedList<DraftSubdistrictForm> storedSubdistrictForms,DraftGovermentOrderForm draftGovermentOrderForm) throws Exception {
		return draftSubdistrictDao.viewSaveSubdistrictDetails(storedSubdistrictForms, draftGovermentOrderForm);
	}


	@Override
	public Object[] getSubdistrictDetailsinDraft(DraftSubdistrictForm draftSubdistrictForm) throws Exception {
		return draftSubdistrictDao.getSubdistrictDetailsinDraft(draftSubdistrictForm);
	}


	@Override
	public Object[] subdistrictDrafttoPublish(DraftSubdistrictForm draftSubdistrictForm)throws Exception {
		return draftSubdistrictDao.subdistrictDrafttoPublish(draftSubdistrictForm);
	}


	@Override
	public boolean deleteDraftSubdistrict(Integer paramCode,Integer processId) throws Exception {
		return draftSubdistrictDao.deleteDraftSubdistrict(paramCode,processId);
	}


	@Override
	public Object[] viewDraftSubdistricDetails(Integer groupId) throws Exception {
	return draftSubdistrictDao.viewDraftSubdistricDetails(groupId);
	}


	@Override
	public String valdidateVillageCoverage(List<Integer> coverageSubdistList,List<Integer> coverageVillageList) throws Exception {
		return draftSubdistrictDao.valdidateVillageCoverage(coverageSubdistList, coverageVillageList);
	}


	@Override
	public DraftManageSubdistrictForm loadSubdistrictDetails(DraftManageSubdistrictForm draftManageSubdistrictForm) throws Exception {
		return draftSubdistrictDao.loadSubdistrictDetails(draftManageSubdistrictForm);
	}


	@Override
	public Integer saveChangeSubdistrictinDraft(DraftManageSubdistrictForm draftManageSubdistrictForm, HttpServletRequest request, HttpSession httpSession) throws Exception {
		return draftSubdistrictDao.saveChangeSubdistrictinDraft(draftManageSubdistrictForm, request, httpSession);
	}


	


	@Override
	public DraftManageSubdistrictForm saveChangeSubdistrict(DraftManageSubdistrictForm draftManageSubdistrictForm,HttpServletRequest request,HttpSession httpSession) throws Exception {
		return draftSubdistrictDao.saveChangeSubdistrict(draftManageSubdistrictForm,request,httpSession);
	}


	@Override
	public Object[] viewChangeDraftSubdistricDetails(DraftManageSubdistrictForm draftManageSubdistrictForm) throws Exception {
		return draftSubdistrictDao.viewChangeDraftSubdistricDetails(draftManageSubdistrictForm);
	}


	@Override
	public Object[] changeSubdistrictDrafttoPublish(DraftManageSubdistrictForm draftManageSubdistrictForm,HttpServletRequest request,HttpSession httpSession) throws Exception {
		return draftSubdistrictDao.changeSubdistrictDrafttoPublish(draftManageSubdistrictForm,request,httpSession);
	}


	@Override
	public List<SubdistrictDraft> getDraftSubdistrictListbyGroupId(Integer groupId) throws Exception {
		return draftSubdistrictDao.getDraftSubdistrictListbyGroupId(groupId);
	}


	@Override
	public List<SubdistrictDraft> getSubdistrictDraftList(String entityType, Integer entityCode) throws Exception {
		return draftSubdistrictDao.getSubdistrictDraftList(entityType, entityCode);
	}


	@Override
	public DraftManageSubdistrictForm getChangeDraftSubdistrict(Integer groupId) throws Exception {
		return draftSubdistrictDao.getChangeDraftSubdistrict(groupId);
	}

/*	*Author Pranav Tiwari
	*Mapped Subdistrict for GIS
	*on 21-March-2017
*/
	@Override
	public String getMappedSubDistrictForGIS(Integer subdistrictCode, String subDistrictName, String isShowOnlyBoundary)throws IOException
	{
		
		try {
			String gisSubdistrictURL=null;
			boolean mapFin = draftSubdistrictDao.subDistrictMapFinalised(subdistrictCode);
			if(mapFin)
				return "mapNtFin";
			
			else{
				
				Properties properties = new Properties();
				InputStream inputStreamPro = getClass().getClassLoader().getResourceAsStream("/upload_info.properties");
				properties.load(inputStreamPro);
				String gisServerLoc=properties.getProperty("gisMap.server.location");
				String ensubdistCode=EncryptionUtil.encode(subdistrictCode.toString());
				
				String ensubDistrictName=EncryptionUtil.encode(subDistrictName);
				String enisShowOnlyBoundary=EncryptionUtil.encode(isShowOnlyBoundary);
			
				gisSubdistrictURL = gisServerLoc+"?subdistCode=" + ensubdistCode +"&subdistName="+ensubDistrictName+"&isShowOnlyBoundary="+enisShowOnlyBoundary;
				return gisSubdistrictURL;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			return "FAILED";
		}
		
	}


	@Override
	public List<DraftSubdistrictForm> checkSubdistrictIsEmpty(String partSubdistList, String villageList)throws Exception {
		return draftSubdistrictDao.checkSubdistrictIsEmpty(partSubdistList, villageList);
	}


	@Override
	public boolean subdistInvalFnAfterCreateMulDist(Integer subdistrictCode, Integer userId, Date effectiveDate)throws Exception {
		return draftSubdistrictDao.subdistInvalFnAfterCreateMulDist(subdistrictCode, userId, effectiveDate);
	}
	
	


}
