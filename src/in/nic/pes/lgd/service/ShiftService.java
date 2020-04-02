package in.nic.pes.lgd.service;

import in.nic.pes.lgd.bean.Block;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.ShiftDistrict;
import in.nic.pes.lgd.bean.ShiftSubDistrict;
import in.nic.pes.lgd.bean.ShiftVillageSD;
import in.nic.pes.lgd.bean.State;
import in.nic.pes.lgd.bean.Subdistrict;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.forms.ShiftBlockForm;
import in.nic.pes.lgd.forms.ShiftDistrictForm;
import in.nic.pes.lgd.forms.ShiftSubDistrictForm;
import in.nic.pes.lgd.forms.ShiftVillageForm;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import pes.attachment.util.AttachedFilesForm;

public interface ShiftService {	

	public String shiftDistrict(ShiftDistrictForm shiftDistrictForm,GovernmentOrderForm govtForm,HttpServletRequest request,Integer userId) throws Exception;
	//public boolean shiftSubDistrict(ShiftSubDistrictForm shiftsubDistrictForm,GovernmentOrderForm govtForm, List<AttachedFilesForm> attachedList,HttpServletRequest request)throws Exception;
	public String shiftSubDistrict(ShiftSubDistrictForm shiftsubDistrictForm,GovernmentOrderForm govtForm,HttpServletRequest request,Integer userId,String isCenterState) throws Exception; 
	
	//public boolean shiftBlock(ShiftBlockForm shiftblockForm,GovernmentOrderForm govtOrderForm, HttpServletRequest request,Integer userId)throws Exception;
	public String shiftBlock(ShiftBlockForm shiftblockForm,GovernmentOrderForm govtOrderForm, HttpServletRequest request,Integer userId) throws Exception;
	//public boolean shiftVillageforSubdistrict(ShiftVillageForm shiftvillageForm,GovernmentOrderForm govtOrderForm, HttpServletRequest request,Integer userId)throws Exception;
	public String shiftVillageforSubdistrict(ShiftVillageForm shiftvillageForm,GovernmentOrderForm govtOrderForm, HttpServletRequest request,Integer userId,String isCenterState,Integer districtCode) throws Exception;
	
	//public String shiftVillageforBlock(ShiftVillageForm shiftvillageForm,GovernmentOrderForm govtOrderForm, HttpServletRequest request,Integer userId)throws Exception;
	
	public boolean shiftVillageforBlock(ShiftVillageForm shiftvillageForm,GovernmentOrderForm govtOrderForm, HttpServletRequest request,Integer userId,List<AttachedFilesForm> metaInfoOfToBeAttachedFileList) throws Exception;
	
	public boolean shiftDistrictReorganize(ShiftDistrictForm shiftDistrictForm,HttpServletRequest request)throws Exception;
	
	public boolean shiftSubDistrictReorganize(ShiftSubDistrictForm shiftsubDistrictForm,HttpServletRequest request)throws Exception;

	public boolean shiftVillagereorganize(ShiftVillageForm shiftvillageForm,HttpServletRequest request)throws Exception;

	public boolean saveDataInGovtOrderEntityWise(GovernmentOrder govtOrder,String codes,char entityType,Session session)throws Exception;
	
	public String shiftDistrictforTemplate(ShiftDistrictForm shiftDistrictForm,
			GovernmentOrderForm govtForm, HttpServletRequest request,Integer userId)throws Exception;
	//public boolean shiftSubDistrictforTemplate(ShiftSubDistrictForm shiftSubDistrictForm,GovernmentOrderForm govtForm,HttpServletRequest request)throws Exception;
	public String shiftSubDistrictforTemplate(ShiftSubDistrictForm shiftSubDistrictForm, GovernmentOrderForm govtForm,HttpServletRequest request,Integer userId,String isCenterState) throws Exception;
	//public boolean shiftVillageforBlockforTemplate(ShiftVillageForm shiftvillageForm,GovernmentOrderForm govtForm,HttpServletRequest request)throws Exception;
	public String shiftVillageforBlockforTemplate(ShiftVillageForm shiftvillageForm,GovernmentOrderForm govtForm,HttpServletRequest request,Integer userId) throws Exception;
	public String shiftVillageforSubdistrictforTemplate(ShiftVillageForm shiftvillageForm, GovernmentOrderForm govtForm,HttpServletRequest request,Integer userId,String isCenterState)throws Exception;
	//public boolean shiftBlockforTemplate(ShiftBlockForm shiftblockForm,GovernmentOrderForm govtForm, HttpServletRequest request)throws Exception;
	public String shiftBlockforTemplate(ShiftBlockForm shiftblockForm,GovernmentOrderForm govtForm, HttpServletRequest request,Integer userId) throws Exception;
	
	public boolean saveDataInAttachShiftBlock(GovernmentOrderForm governmentOrderForm,ShiftBlockForm shiftblockForm,List<AttachedFilesForm> attachedList, HttpSession session,int getordercode) throws Exception; 
	
	public boolean saveDataInAttachShiftVillageSD(GovernmentOrderForm governmentOrderForm,ShiftVillageForm shiftvillageForm,List<AttachedFilesForm> attachedList, HttpSession session,int getordercode) throws Exception; 
	public boolean saveDataInAttachShiftGenerateVillageSD(GovernmentOrderForm governmentOrderForm,ShiftVillageForm shiftvillageForm, HttpSession session,int getordercode) throws Exception; 
	public boolean saveDataInAttachGenerateShiftBlock(GovernmentOrderForm governmentOrderForm,ShiftBlockForm shiftblockForm, HttpSession session,int getordercode) throws Exception; 
	public boolean saveDataInAttachShiftGenerateDistrict(GovernmentOrderForm governmentOrderForm,ShiftDistrictForm shiftDistricForm, HttpSession session,int getordercode) throws Exception; 
	public boolean saveDataInAttachShiftGenerateSubDistrict(GovernmentOrderForm governmentOrderForm,ShiftSubDistrictForm shiftSubDistrictForm, HttpSession session,int getordercode) throws Exception; 
	public boolean saveDataInAttachShiftVillageBlock(GovernmentOrderForm governmentOrderForm,ShiftVillageForm shiftvillageForm,List<AttachedFilesForm> attachedList, HttpSession session,int getordercode) throws Exception; 
	public boolean saveDataInAttachShiftDistrict(GovernmentOrderForm governmentOrderForm,ShiftDistrictForm shiftDistrictForm,List<AttachedFilesForm> attachedList, HttpSession session,int getordercode) throws Exception; 
	public boolean saveDataInAttachShiftSubDistrict(GovernmentOrderForm governmentOrderForm,ShiftSubDistrictForm shiftSubDistrictForm,List<AttachedFilesForm> attachedList, HttpSession session,int getordercode) throws Exception;
	public List<State> getStateNamebySourcestateID(ShiftDistrictForm shiftDistrictForm) throws Exception;
	public List<State> getStateNamebyDeststateID(ShiftDistrictForm shiftDistrictForm) throws Exception; 
	public List<District> getdistrictNamebyDistID(ShiftDistrictForm shiftDistrictForm) throws Exception; 
	public List<District> getsourcedistrictNamebyDistIDforShftSubDist(ShiftSubDistrictForm shiftSubDistrictForm) throws Exception;
	public List<District> gettargetdistrictNamebyDistIDforShftSubDist(ShiftSubDistrictForm shiftSubDistrictForm) throws Exception;
	public List<District> getsourcedistrictNamebyDistIDforShiftBlock(ShiftBlockForm shiftblockForm) throws Exception;
	public List<District> gettargetdistrictNamebyDistIDforShiftBlock(ShiftBlockForm shiftblockForm) throws Exception; 
	public List<Block> getblockNamebyBlockID(ShiftBlockForm shiftblockForm) throws Exception; 
	public List<Subdistrict> getsubdistrictNamebySubDistIDforShftSubDist(ShiftSubDistrictForm shiftSubDistrictForm) throws Exception; 
	public List<State> getsourcestateNamebyStateID(ShiftSubDistrictForm shiftsubDistrictForm) throws Exception;
	public List<State> getsourcestateNamebyStateIDforSD(ShiftSubDistrictForm shiftsubDistrictForm) throws Exception; 
	public List<State> gettargetstateNamebyStateID(ShiftSubDistrictForm shiftsubDistrictForm) throws Exception; 
	public List<State> gettargetstateNamebyStateIDforSD(ShiftSubDistrictForm shiftsubDistrictForm) throws Exception; 
	public List<District> getsourcedistrictNamebyDistIDforShftVillageSD(ShiftVillageForm shiftVillageForm) throws Exception;
	public List<District> gettargetdistrictNamebyDistIDforShftVillageSD(ShiftVillageForm shiftVillageForm) throws Exception; 
	public List<Subdistrict> getsourcesubdistrictNamebySubDistIDforShftVillageSD(ShiftVillageForm shiftVillageForm) throws Exception;
	public List<Subdistrict> gettargetsubdistrictNamebySubDistIDforShftVillageSD(ShiftVillageForm shiftVillageForm) throws Exception; 
	public List<Village> getVillageNamebyVillageIDforShftVillageSD(ShiftVillageForm shiftVillageForm) throws Exception;
	public List<Block> getsourceblockNamebyBlockID(ShiftVillageForm shiftVillageForm) throws Exception;
	public List<Block> gettargetblockNamebyBlockID(ShiftVillageForm shiftVillageForm) throws Exception; 
	public List<State> getsourcestateNamebyStateIDforSD(ShiftVillageForm shiftVillageForm) throws Exception;
	public List<State> gettargetstateNamebyStateIDforSD(ShiftVillageForm shiftVillageForm) throws Exception; 

	
	
}
