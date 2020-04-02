package in.nic.pes.lgd.dao;

import in.nic.pes.lgd.bean.Block;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.ShiftBlock;
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

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import pes.attachment.util.AttachedFilesForm;


public interface ShiftDAO {

	//public void shiftDistrict(int stateSourceCode,int stateTargetCode,String districtCode,int userId,Date effectiveDate,Session session)throws Exception;
	public List<ShiftDistrict> shiftDistrict(int stateSourceCode, int stateTargetCode,String districtCode, int userId, GovernmentOrderForm govtOrderForm, Session session,String description) throws Exception;
	//public void	shiftSubDistrict(int districtSourceCode,int districtTargetCode,String subdistrictCode,int userId,Date effectiveDate,Session session)throws Exception;
	public List<ShiftSubDistrict> shiftSubDistrict(int districtSourceCode,int districtTargetCode, String subdistrictCode, int userId,GovernmentOrderForm govtOrderForm, Session session,String description) throws Exception; 
	//public void	shiftSubDistrictS(int districtSourceCode,int districtTargetCode,String subdistrictCode,int userId,Date effectiveDate,int stateTargetCode,Session session)throws Exception;
	//public void shiftBlock(int districtSourceCode,int districtTargetCode,String blockCode,int userId,GovernmentOrderForm govtOrderForm,Date effectiveDate, Session session)throws Exception;
	public List<ShiftBlock> shiftBlock(int districtSourceCode, int districtTargetCode, String blockCode, int userId,GovernmentOrderForm govtOrderForm, Date effectiveDate, Session session,String description) throws Exception; 

	//public void shiftVillageforBlock(int blockSourceCode,int blockTargetCode,String villageCode,int userId,Date effectiveDate,char level, Session session)throws Exception;
	public List<ShiftVillageSD> shiftVillageforBlock(int blockSourceCode, int blockTargetCode, String villageCode, int userId,GovernmentOrderForm govtOrderForm, char level,Session session,String description) throws Exception;

	//public void shiftVillageforSubdistrict(int subdistrictSourceCode,int subdistrictTargetCode,String villageCode,int userId,GovernmentOrderForm govtOrderForm, Session session)throws Exception;
	public List<ShiftVillageSD> shiftVillageforSubdistrict(int subdistrictSourceCode, int subdistrictTargetCode, String villageCode, int userId, GovernmentOrderForm govtOrderForm, Session session,String description) throws Exception;

	public void shiftDistrictReorganize(int stateSourceCode,
			int stateTargetCode, String districtCodes, int i, Date effectiveDate)throws Exception;
	public void shiftSubDistrictReorganize(int districtSourceCode,
			int districtTargetCode, String subdistrictCodes, int i,
			Date effectiveDate)throws Exception;
	public void shiftSubDistrictSReorganize(int districtSourceCode,
			int districtTargetCode, String subdistrictCodes, int i,
			Date effectiveDate, int stateTargetCode)throws Exception;
	public void shiftVillageReorganize(int subdistrictSourceCode,
			int subdistrictTargetCode, String villageCodes, int i,
			Date effectiveDate)throws Exception;
	
	public boolean saveDataInAttachShiftBlock(GovernmentOrderForm governmentOrderForm,ShiftBlockForm shiftblockForm,List<AttachedFilesForm> attachedList, HttpSession session,int getordercode) throws Exception; 
	
	public boolean saveDataInAttachShiftVillageSD(GovernmentOrderForm governmentOrderForm,ShiftVillageForm shiftvillageForm,List<AttachedFilesForm> attachedList, HttpSession session,int getordercode) throws Exception; 
	public boolean saveDataInAttachShiftGenerateVillageSD(GovernmentOrderForm governmentOrderForm,ShiftVillageForm shiftvillageForm, HttpSession session,int getordercode) throws Exception;
	public boolean saveDataInAttachShiftGenerateDistrict(GovernmentOrderForm governmentOrderForm,ShiftDistrictForm shiftDistricForm, HttpSession session,int getordercode) throws Exception;
	public boolean saveDataInAttachGenerateShiftBlock(GovernmentOrderForm governmentOrderForm,ShiftBlockForm shiftblockForm, HttpSession session,int getordercode) throws Exception;
	public boolean saveDataInAttachShiftGenerateSubDistrict(GovernmentOrderForm governmentOrderForm,ShiftSubDistrictForm shiftSubDistrictForm, HttpSession session,int getordercode) throws Exception;
	public boolean saveDataInAttachShiftVillageBlock(GovernmentOrderForm governmentOrderForm,ShiftVillageForm shiftvillageForm,List<AttachedFilesForm> attachedList, HttpSession session,int getordercode) throws Exception; 
	public boolean saveDataInAttachShiftDistrict(GovernmentOrderForm governmentOrderForm,ShiftDistrictForm shiftDistrictForm,List<AttachedFilesForm> attachedList, HttpSession session,int getordercode) throws Exception; 
	public boolean saveDataInAttachShiftSubDistrict(GovernmentOrderForm governmentOrderForm,ShiftSubDistrictForm shiftSubDistrictForm,List<AttachedFilesForm> attachedList, HttpSession session,int getordercode) throws Exception;
	public List<State> getStateNamebystateID(int stateCode) throws Exception; 
	public List<District> getdistrictNamebyDistID(int distCode) throws Exception; 
	public List<Subdistrict> getsubdistrictNamebysubDistID(int subdistCode) throws Exception; 
	public List<Village> getVillageNamebyVillageID(int villageCode) throws Exception; 
	public List<Block> getblockNamebyBlockID(int blockCode) throws Exception;
}
