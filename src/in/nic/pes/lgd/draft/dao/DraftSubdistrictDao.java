package in.nic.pes.lgd.draft.dao;

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

 public interface DraftSubdistrictDao {
 List<Subdistrict> getDraftSubdistrictList(Integer districtCode,List<Integer> subdistrictListPart,List<Integer> subdistrictListFull) throws Exception;
 Character subdistrictNameIsUnique(String subdistrictNameEng,Integer districtCode) throws Exception;
 Integer saveSubdistrictinDraft(LinkedList<DraftSubdistrictForm> storedSubdistrictForms,DraftGovermentOrderForm draftGovermentOrderForm,HttpServletRequest request,HttpSession httpSession)throws Exception;
 List<SubdistrictDraft> getSubdistrictDraftList(String entityType, Integer entityCode) throws Exception;
 DraftSubdistrictForm loadSubdistrictDraftbyId(DraftSubdistrictForm draftSubdistrictForm) throws Exception;
 boolean updateSubdistrictinDraft(DraftSubdistrictForm draftSubdistrictForm) throws Exception;
 LinkedList<DraftSubdistrictForm> saveSubdistrict(LinkedList<DraftSubdistrictForm> storedSubdistrictForms,DraftGovermentOrderForm draftGovermentOrderForm) throws Exception;
 Map<String,Object> viewSaveSubdistrictDetails(LinkedList<DraftSubdistrictForm> storedSubdistrictForms,DraftGovermentOrderForm draftGovermentOrderForm) throws Exception;
 List<Subdistrict> getCoverageSubdistrictDetails(DraftSubdistrictForm draftSubdistrictForm)throws Exception;
 Object[] getSubdistrictDetailsinDraft(DraftSubdistrictForm draftSubdistrictForm)throws Exception;
 Object[] subdistrictDrafttoPublish(DraftSubdistrictForm draftSubdistrictForm) throws Exception;
 boolean deleteDraftSubdistrict(Integer paramCode,Integer processId)throws Exception;
 Object[] viewDraftSubdistricDetails(Integer groupId)throws Exception;
 String valdidateVillageCoverage(List<Integer> coverageSubdistList,List<Integer> coverageVillageList)throws Exception;
 DraftManageSubdistrictForm loadSubdistrictDetails(DraftManageSubdistrictForm draftManageSubdistrictForm)throws Exception;
 Integer saveChangeSubdistrictinDraft(DraftManageSubdistrictForm draftManageSubdistrictForm,HttpServletRequest request,HttpSession httpSession) throws Exception;
 Object[] viewChangeDraftSubdistricDetails(DraftManageSubdistrictForm draftManageSubdistrictForm) throws Exception;
 DraftManageSubdistrictForm saveChangeSubdistrict(DraftManageSubdistrictForm draftManageSubdistrictForm,HttpServletRequest request,HttpSession httpSession) throws Exception;
 Object[] changeSubdistrictDrafttoPublish(DraftManageSubdistrictForm draftManageSubdistrictForm,HttpServletRequest request,HttpSession httpSession)throws Exception;
 List<SubdistrictDraft> getDraftSubdistrictListbyGroupId(Integer groupId)throws Exception;
 DraftManageSubdistrictForm getChangeDraftSubdistrict(Integer groupId)throws Exception;

 boolean subDistrictMapFinalised(Integer subdistrictCode);
 List<DraftSubdistrictForm> checkSubdistrictIsEmpty(String partSubdistList,String villageList)throws Exception;
 boolean subdistInvalFnAfterCreateMulDist(Integer subdistrictCode,Integer userId,Date effectiveDate)throws Exception;
  List<Subdistrict> getSubDistrictDetails(Integer subDistrictCode) throws Exception;
    
}
