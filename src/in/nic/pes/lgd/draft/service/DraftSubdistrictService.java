package in.nic.pes.lgd.draft.service;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import in.nic.pes.lgd.bean.Subdistrict;
import in.nic.pes.lgd.draft.entities.SubdistrictDraft;
import in.nic.pes.lgd.draft.form.DraftGovermentOrderForm;
import in.nic.pes.lgd.draft.form.DraftManageSubdistrictForm;
import in.nic.pes.lgd.draft.form.DraftSubdistrictForm;

public interface DraftSubdistrictService {
	
	public List<Subdistrict> getDraftSubdistrictList(Integer districtCode,String subdistrictsPart,String subdistrictsFull) throws Exception;
	
	public Character subdistrictNameIsUnique(String subdistrictNameEng,Integer districtCode) throws Exception;
	
	public Integer saveSubdistrictinDraft(LinkedList<DraftSubdistrictForm> storedSubdistrictForms,DraftGovermentOrderForm draftGovermentOrderForm,HttpServletRequest request,HttpSession httpSession)throws Exception;
	
	public List<SubdistrictDraft> getSubdistrictDraftList(String entityType, Integer entityCode) throws Exception;
	
	public DraftSubdistrictForm loadSubdistrictDraftbyId(DraftSubdistrictForm draftSubdistrictForm) throws Exception;
	
	public boolean updateSubdistrictinDraft(DraftSubdistrictForm draftSubdistrictForm) throws Exception;
	
	public LinkedList<DraftSubdistrictForm> saveSubdistrict(LinkedList<DraftSubdistrictForm> storedSubdistrictForms,DraftGovermentOrderForm draftGovermentOrderForm) throws Exception;
	
	public Map<String,Object> viewSaveSubdistrictDetails(LinkedList<DraftSubdistrictForm> storedSubdistrictForms,DraftGovermentOrderForm draftGovermentOrderForm) throws Exception;
	
	public Object[] getSubdistrictDetailsinDraft(DraftSubdistrictForm draftSubdistrictForm)throws Exception;
	
	public Object[] subdistrictDrafttoPublish(DraftSubdistrictForm draftSubdistrictForm) throws Exception;
	
	public boolean deleteDraftSubdistrict(Integer paramCode,Integer processId)throws Exception;
	
	public Object[] viewDraftSubdistricDetails(Integer groupId)throws Exception;
	
	public String valdidateVillageCoverage(List<Integer> coverageSubdistList,List<Integer> coverageVillageList)throws Exception;
	
	public DraftManageSubdistrictForm loadSubdistrictDetails(DraftManageSubdistrictForm draftManageSubdistrictForm)throws Exception;
	
	public Integer saveChangeSubdistrictinDraft(DraftManageSubdistrictForm draftManageSubdistrictForm,HttpServletRequest request,HttpSession httpSession) throws Exception;
	
	public Object[] viewChangeDraftSubdistricDetails(DraftManageSubdistrictForm draftManageSubdistrictForm) throws Exception;

	public DraftManageSubdistrictForm saveChangeSubdistrict(DraftManageSubdistrictForm draftManageSubdistrictForm,HttpServletRequest request,HttpSession httpSession) throws Exception;
	
	public Object[] changeSubdistrictDrafttoPublish(DraftManageSubdistrictForm draftManageSubdistrictForm,HttpServletRequest request,HttpSession httpSession)throws Exception;
	
	public List<SubdistrictDraft> getDraftSubdistrictListbyGroupId(Integer groupId)throws Exception;
	
	public DraftManageSubdistrictForm getChangeDraftSubdistrict(Integer groupId)throws Exception;
	
	public String getMappedSubDistrictForGIS(Integer subdistrictCode,  String subDistrictName, String isShowOnlyBoundary) throws IOException;
	
	public List<DraftSubdistrictForm> checkSubdistrictIsEmpty(String partSubdistList,String villageList)throws Exception;
	
	public boolean subdistInvalFnAfterCreateMulDist(Integer subdistrictCode,Integer userId,Date effectiveDate)throws Exception;
		
	
	
	




}
