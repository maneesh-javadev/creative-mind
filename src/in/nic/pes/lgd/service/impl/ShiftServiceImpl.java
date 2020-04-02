package in.nic.pes.lgd.service.impl;


import in.nic.pes.lgd.bean.Block;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.GovernmentOrderEntityWise;
import in.nic.pes.lgd.bean.ShiftBlock;
import in.nic.pes.lgd.bean.ShiftDistrict;
import in.nic.pes.lgd.bean.ShiftSubDistrict;
import in.nic.pes.lgd.bean.ShiftVillageSD;
import in.nic.pes.lgd.bean.State;
import in.nic.pes.lgd.bean.Subdistrict;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.dao.BlockDAO;
import in.nic.pes.lgd.dao.DistrictDAO;
import in.nic.pes.lgd.dao.LocalGovtBodyDAO;
import in.nic.pes.lgd.dao.ShiftDAO;
import in.nic.pes.lgd.dao.StateDAO;
import in.nic.pes.lgd.dao.SubDistrictDAO;
import in.nic.pes.lgd.dao.VillageDAO;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.forms.ShiftBlockForm;
import in.nic.pes.lgd.forms.ShiftDistrictForm;
import in.nic.pes.lgd.forms.ShiftSubDistrictForm;
import in.nic.pes.lgd.forms.ShiftVillageForm;
import in.nic.pes.lgd.service.ConvertLocalbodyService;
import in.nic.pes.lgd.service.EmailService;
import in.nic.pes.lgd.service.EmailSmsThread;
import in.nic.pes.lgd.service.GovernmentOrderService;
import in.nic.pes.lgd.service.ShiftService;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pes.attachment.util.AttachedFilesForm;

@Service
public class ShiftServiceImpl implements ShiftService {
	private static final Logger log = Logger.getLogger(ShiftServiceImpl.class);

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
    VillageDAO villageDAO;
	
	@Autowired 
	GovernmentOrderService govtOrderService;
	
	@Autowired
	ConvertLocalbodyService convertLocalbodyService;
	
	@Autowired
	LocalGovtBodyDAO localGovtBodyDAO;
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
    EmailService emailService;

	
	int userId=4000;
	long createdBy=4000;
	
	Session session=null;
	Transaction tx=null;

	public String shiftDistrict(ShiftDistrictForm shiftDistrictForm,GovernmentOrderForm govtForm,HttpServletRequest request,Integer userId) throws Exception
	{
		List<ShiftDistrict> getordercode = null;
		String retValue=null;
		try
		{
			session=sessionFactory.openSession();
			tx=session.beginTransaction();
			String districtCodes = shiftDistrictForm.getDistrictNameEnglish();
			String description="Districts {"+shiftDistrictForm.getFinalDestName()+ "} has been shifted from {"+ shiftDistrictForm.getFinalsourceStateName() +"} State to {"+shiftDistrictForm.getFinaltargetStateName()+"} State";		
			
			String[] stateCodes = shiftDistrictForm.getStateNameEnglish().split(",");
			int stateSourceCode = Integer.parseInt(stateCodes[0].toString());	

			int stateTargetCode = Integer.parseInt( stateCodes[1].toString());	
			
			getordercode=shiftDao.shiftDistrict(stateSourceCode, stateTargetCode,districtCodes,userId,govtForm,session,description);
			
			Iterator<ShiftDistrict> shiftVillageSDItr = getordercode.iterator();
			ShiftDistrict localdata = (ShiftDistrict)shiftVillageSDItr.next();
			retValue = localdata.getShift_district_fn();
		
			//GovernmentOrder govtOrder=convertLocalbodyService.saveDataInGovtOrder(govtForm, session);
			/*if(attachedList !=null && !attachedList.isEmpty()){
			convertLocalbodyService.saveDataInAttachment(govtOrder, govtForm,attachedList, session);}
			saveDataInGovtOrderEntityWise(govtOrder,districtCodes,'D',session);*/
		
			tx.commit();
			//return true;
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
			tx.rollback();
			//return false;
		}
		finally
		{
			if (session != null && session.isOpen()) {
				session.close();
			}	
		}
		return retValue;
	}
	
	public String shiftDistrictforTemplate(ShiftDistrictForm shiftDistrictForm,
			GovernmentOrderForm govtForm,HttpServletRequest request,Integer userId) throws Exception
	{
		List<ShiftDistrict> getordercode = null;
		String retValue=null;
		try 
		{
			session=sessionFactory.openSession();
			tx=session.beginTransaction();
			String districtCodes = shiftDistrictForm.getDistrictNameEnglish();
			//String description="Districts has been shifted between States";		
			//String description="Districts "+shiftDistrictForm.getFinalDestName()+ " has been shifted between State "+ shiftDistrictForm.getFinalsourceStateName() +" to "+shiftDistrictForm.getFinaltargetStateName();		
			String description="Districts {"+shiftDistrictForm.getFinalDestName()+ "} has been shifted from {"+ shiftDistrictForm.getFinalsourceStateName() +"} State to {"+shiftDistrictForm.getFinaltargetStateName()+"} State";		
			
			String[] stateCodes = shiftDistrictForm.getStateNameEnglish().split(",");
			int stateSourceCode = Integer.parseInt(stateCodes[0].toString());	

			int stateTargetCode = Integer.parseInt( stateCodes[1].toString());	
			
			getordercode=shiftDao.shiftDistrict(stateSourceCode, stateTargetCode,districtCodes,userId,govtForm,session,description);
			Iterator<ShiftDistrict> shiftVillageSDItr = getordercode.iterator();
			ShiftDistrict localdata = (ShiftDistrict)shiftVillageSDItr.next();
			retValue = localdata.getShift_district_fn();
			
			/*GovernmentOrder govtOrder=convertLocalbodyService.saveDataInGovtOrder(govtForm, session);			
			saveDataInGovtOrderEntityWise(govtOrder,districtCodes,'D',session);	*/
			tx.commit();
			//return true;
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
			tx.rollback();
			//return false;
		}
		finally
		{
			if (session != null && session.isOpen()) {
				session.close();
			}	
		}
		return retValue;
		
	}

	public boolean saveDataInGovtOrderEntityWise(GovernmentOrder govtOrder,String codes,char entityType,Session session) throws Exception{

		GovernmentOrderEntityWise govtOrderEWise = new GovernmentOrderEntityWise();
		int version =0;
		int code=0;
		String[] codeList=codes.split(",");
		
		try {	
			
				for(int i=0;i<codeList.length;i++)
				{
					if(codeList[i]!=null)
					{
					   code=Integer.parseInt(codeList[i].toString());
						if(code>0)
						{
							if(entityType=='S')
							{
								version =stateDao.getMaxVersionbyStateCode(code);							
							}
							if(entityType=='D')
							{
								version =districtDAO.getMaxDistrictVersion(code,session);							
							}
							else if(entityType=='T') {
								version =subdistrictDAO.getMaxSubdistrictVersion(code,session);
							}
							else if(entityType=='B') {
								version =blockDAO.getMaxBlockVersionbyCode(code);
							}
							else if(entityType=='V') {
								version =villageDAO.getMaxvillageVersion(code,session);
								//version=version+1;
							}
							govtOrderEWise.setGovernmentOrder(govtOrder);
							govtOrderEWise.setEntityCode(code);
							govtOrderEWise.setEntityVersion(version);
							govtOrderEWise.setEntityType(entityType);
						}
						
					}
					localGovtBodyDAO.saveOrderDetailsEntityWise(
							govtOrderEWise, session);
				}
			
				
		} catch (Exception e) {
				log.debug("Exception" + e);
		}
		return true;
	}
	
public boolean shiftDistrictReorganize(ShiftDistrictForm shiftDistrictForm,HttpServletRequest request) throws Exception {
		
		try {
			
			 govtOrderService.saveGovernmentOrder(
					shiftDistrictForm.getOrderNo(),
					shiftDistrictForm.getOrderDate(),
					shiftDistrictForm.getEffectiveDate(),
					shiftDistrictForm.getGazPubDate(), "Governor",
					shiftDistrictForm.getOrderPath(),shiftDistrictForm.getFilePath(),request);


			String districtCodes = shiftDistrictForm.getDistrictNameEnglish();
					
			String[] stateCodes = shiftDistrictForm.getStateNameEnglish()
					.split(",");
			int stateSourceCode = Integer.parseInt(stateCodes[0].toString());	

			int stateTargetCode = Integer.parseInt( stateCodes[1].toString());	

			shiftDao.shiftDistrictReorganize(stateSourceCode, stateTargetCode,districtCodes,125,shiftDistrictForm.getEffectiveDate());
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
		}
		return true;
	}
	
	public String shiftSubDistrict(ShiftSubDistrictForm shiftsubDistrictForm,GovernmentOrderForm govtForm,HttpServletRequest request,Integer userId,String isCenterState) throws Exception 
	{
		List<ShiftSubDistrict> getordercode = null;	
		String description=null;
		String retValue=null;
		
		try {
			session=sessionFactory.openSession();
			tx=session.beginTransaction();	
			int stateSourceCode=0;
			int stateTargetCode=0;
			//String description="Subdistricts "+ shiftsubDistrictForm.getFinalSubdistName() +" has been shifted between District "+shiftsubDistrictForm.getFinalsourceDistName()+" to "+shiftsubDistrictForm.getFinaltargetDistName();
			
			if(isCenterState.equalsIgnoreCase("C"))
			{
				description="Sub districts {" + shiftsubDistrictForm.getFinalSubdistName() +"} has been shifted from {Source District} "+shiftsubDistrictForm.getFinalsourceDistName() +" of {Source State} "+shiftsubDistrictForm.getFinalsourceStateName()+" to {Target District} "+shiftsubDistrictForm.getFinaltargetDistName()+" of {Target State} "+shiftsubDistrictForm.getFinaltargetStateName();
			}
			else
			{	
				description="Sub districts {" + shiftsubDistrictForm.getFinalSubdistName() +"} has been shifted from {Source District} "+shiftsubDistrictForm.getFinalsourceDistName()+" to {Target District} "+shiftsubDistrictForm.getFinaltargetDistName();
			}
			
			String  subdistrictCodes = shiftsubDistrictForm.getSubdistrictNameEnglish();
			String[] districtCodes = shiftsubDistrictForm.getDistrictNameEnglish().split(",");
			String[] stateCodes = shiftsubDistrictForm.getStateNameEnglish().split(",");
			if(stateCodes !=null)
			{	
				if(stateCodes[0] !=null)
				{	
					stateSourceCode = Integer.parseInt(stateCodes[0].toString());
				}
				stateTargetCode = stateCodes.length>1?Integer.parseInt(stateCodes[1].toString()):Integer.parseInt(stateCodes[0].toString());
					
			}
			int districtSourceCode = Integer.parseInt(districtCodes[0].toString());
			int districtTargetCode = Integer.parseInt(districtCodes[1].toString());
			

				if(stateSourceCode==stateTargetCode)
				{
					getordercode=shiftDao.shiftSubDistrict(districtSourceCode, districtTargetCode,subdistrictCodes,userId,govtForm,session,description);
				}
				else
				{
					getordercode=shiftDao.shiftSubDistrict(districtSourceCode, districtTargetCode,subdistrictCodes,userId,govtForm,session,description);
					//getordercode=shiftDao.shiftSubDistrictS(districtSourceCode, districtTargetCode,subdistrictCodes,userId,govtForm,stateTargetCode,session);
				}
				
				Iterator<ShiftSubDistrict> shiftVillageSDItr = getordercode.iterator();
				ShiftSubDistrict localdata = (ShiftSubDistrict)shiftVillageSDItr.next();
				retValue = localdata.getShift_subdistrict_fn();
				
				
				/*GovernmentOrder govtOrder=convertLocalbodyService.saveDataInGovtOrder(govtForm, session);
				convertLocalbodyService.saveDataInAttachment(govtOrder, govtForm,attachedList, session);
				saveDataInGovtOrderEntityWise(govtOrder,subdistrictCodes,'T',session);		*/		
				tx.commit();
				//return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
			tx.rollback();
			//return false;
		}
		finally
		{
			if (session != null && session.isOpen()) {
				session.close();
			}	
		}		
		return retValue;

	}
	
	public String shiftSubDistrictforTemplate(ShiftSubDistrictForm shiftSubDistrictForm, GovernmentOrderForm govtForm,HttpServletRequest request,Integer userId,String isCenterState) throws Exception
	{
		List<ShiftSubDistrict> getordercode = null;	
		String description=null;
		String retValue = null;
		try
		{
			session=sessionFactory.openSession();
			tx=session.beginTransaction();	
			int stateSourceCode=0;
			int stateTargetCode=0;
			//String description="Subdistricts has been shifted between Districts";
			//String description="Subdistricts "+ shiftSubDistrictForm.getFinalSubdistName() +" has been shifted between District "+shiftSubDistrictForm.getFinalsourceDistName()+" to "+shiftSubDistrictForm.getFinaltargetDistName();
			
			if(isCenterState.equalsIgnoreCase("C"))
			{
				description="Sub districts {" + shiftSubDistrictForm.getFinalSubdistName() +"} has been shifted from {Source District} "+shiftSubDistrictForm.getFinalsourceDistName() +" of {Source State} "+shiftSubDistrictForm.getFinalsourceStateName()+" to {Target District} "+shiftSubDistrictForm.getFinaltargetDistName()+" of {Target State} "+shiftSubDistrictForm.getFinaltargetStateName();
			}
			else
			{	
				description="Sub districts {" + shiftSubDistrictForm.getFinalSubdistName() +"} has been shifted from {Source District} "+shiftSubDistrictForm.getFinalsourceDistName()+" to {Target District} "+shiftSubDistrictForm.getFinaltargetDistName();
			}
			
			String  subdistrictCodes = shiftSubDistrictForm.getSubdistrictNameEnglish();
			String[] districtCodes = shiftSubDistrictForm.getDistrictNameEnglish().split(",");
			
			String[] stateCodes = shiftSubDistrictForm.getStateNameEnglish().split(",");
			if(stateCodes !=null)
			{	
				if(stateCodes[0] !=null)
				{	
					stateSourceCode = Integer.parseInt(stateCodes[0].toString());
				}
				stateTargetCode = stateCodes.length>1?Integer.parseInt(stateCodes[1].toString()):Integer.parseInt(stateCodes[0].toString());
			}	
			int districtSourceCode = Integer.parseInt(districtCodes[0].toString());
			int districtTargetCode = Integer.parseInt(districtCodes[1].toString());


			if(stateSourceCode==stateTargetCode)
			{
				getordercode=shiftDao.shiftSubDistrict(districtSourceCode, districtTargetCode,subdistrictCodes,userId,govtForm,session,description);
			}
			else
			{
				getordercode=shiftDao.shiftSubDistrict(districtSourceCode, districtTargetCode,subdistrictCodes,userId,govtForm,session,description);
			}
			Iterator<ShiftSubDistrict> shiftVillageSDItr = getordercode.iterator();
			ShiftSubDistrict localdata = (ShiftSubDistrict)shiftVillageSDItr.next();
			retValue = localdata.getShift_subdistrict_fn();
			
			/*GovernmentOrder govtOrder=convertLocalbodyService.saveDataInGovtOrder(govtForm, session);
			saveDataInGovtOrderEntityWise(govtOrder,subdistrictCodes,'T',session);	*/			
			tx.commit();
			//return true;
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
			tx.rollback();
			//return false;
			
		}
		finally
		{
			if (session != null && session.isOpen()) {
				session.close();
			}	
		}
		return retValue;
		
	}
	
	
	public boolean shiftSubDistrictReorganize(ShiftSubDistrictForm shiftsubDistrictForm,HttpServletRequest request) throws Exception {
		try {
			
			
		
			
			String  subdistrictCodes = shiftsubDistrictForm.getSubdistrictNameEnglish();
			String[] districtCodes = shiftsubDistrictForm.getDistrictNameEnglish()
					.split(",");
			String[] stateCodes = shiftsubDistrictForm.getStateNameEnglish().split(
					",");

			int stateSourceCode = Integer.parseInt(stateCodes[0].toString());
			int stateTargetCode = Integer.parseInt(stateCodes[1].toString());
			int districtSourceCode = Integer.parseInt(districtCodes[0].toString());
			int districtTargetCode = Integer.parseInt(districtCodes[1].toString());


				if(stateSourceCode==stateTargetCode)
				{
					shiftDao.shiftSubDistrictReorganize(districtSourceCode, districtTargetCode,subdistrictCodes,125,shiftsubDistrictForm.getEffectiveDate());
				}
				else
				{
					shiftDao.shiftSubDistrictSReorganize(districtSourceCode, districtTargetCode,subdistrictCodes,125,shiftsubDistrictForm.getEffectiveDate(),stateTargetCode);
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
		}
		finally
		{
			if(session.isOpen()){
				session.close();
			}	
		}
		return true;

	}

	public String shiftBlock(ShiftBlockForm shiftblockForm,GovernmentOrderForm govtOrderForm, HttpServletRequest request,Integer userId) throws Exception
	{
		List<ShiftBlock> getordercode = null;	
		String retValue=null;
		try
		{
			session=sessionFactory.openSession();
			tx=session.beginTransaction();		
			
			String blockCodes = shiftblockForm.getBlockNameEnglish();
			String[] districtCodes = shiftblockForm.getDistrictNameEnglish().split(",");
			
			int districtSourceCode = Integer.parseInt(districtCodes[0].toString());
			int districtTargetCode = Integer.parseInt(districtCodes[1].toString());

			String description="Blocks "+ shiftblockForm.getFinaltargetBlockName() +" has been shifted between Districts "+ shiftblockForm.getFinalsourceDistName()+" to "+ shiftblockForm.getFinaltargetDistName();
			
			getordercode=shiftDao.shiftBlock(districtSourceCode,districtTargetCode,blockCodes,userId,govtOrderForm,govtOrderForm.getEffectiveDate(),session,description);
			//GovernmentOrder govtOrder=convertLocalbodyService.saveDataInGovtOrder(govtOrderForm, session);
			//convertLocalbodyService.saveDataInAttachment(govtOrder, govtOrderForm,attachedList, session);
			//saveDataInGovtOrderEntityWise(govtOrder,blockCodes,'B',session);				
			tx.commit();
			Iterator<ShiftBlock> shiftblockItr = getordercode.iterator();
			ShiftBlock localdata = (ShiftBlock)shiftblockItr.next();
			retValue = localdata.getShift_block_fn();
			
		} 
		catch (Exception e) 
		{
			log.debug("Exception" + e);
			tx.rollback();
			//return false;
		}
		finally
		{
			if (session != null && session.isOpen()) {
				session.close();
			}	
		}
		return retValue;
	}
	
	public String shiftBlockforTemplate(ShiftBlockForm shiftblockForm,GovernmentOrderForm govtForm, HttpServletRequest request,Integer userId) throws Exception
	{
		List<ShiftBlock> getordercode = null;	
		String retValue=null;
		try
		{
			session=sessionFactory.openSession();
			tx=session.beginTransaction();		
			
			String blockCodes = shiftblockForm.getBlockNameEnglish();
			String[] districtCodes = shiftblockForm.getDistrictNameEnglish().split(",");			
			
			int districtSourceCode = Integer.parseInt(districtCodes[0].toString());
			int districtTargetCode = Integer.parseInt(districtCodes[1].toString());
			String description="Blocks "+ shiftblockForm.getFinaltargetBlockName() +" has been shifted between Districts "+ shiftblockForm.getFinalsourceDistName()+" to "+ shiftblockForm.getFinaltargetDistName();
				
			getordercode=shiftDao.shiftBlock(districtSourceCode,districtTargetCode,blockCodes,userId,govtForm,govtForm.getEffectiveDate(),session,description);		
			//GovernmentOrder govtOrder=convertLocalbodyService.saveDataInGovtOrder(govtForm, session);
			//saveDataInGovtOrderEntityWise(govtOrder,blockCodes,'B',session);			
			tx.commit();
			Iterator<ShiftBlock> shiftblockItr = getordercode.iterator();
			ShiftBlock localdata = (ShiftBlock)shiftblockItr.next();
			retValue = localdata.getShift_block_fn();
			
			
		}
		catch (Exception e)
		{
			log.debug("Exception" + e);
			tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return retValue;
	}

	public String shiftVillageforSubdistrict(ShiftVillageForm shiftvillageForm,GovernmentOrderForm govtOrderForm, HttpServletRequest request,Integer userId,String isCenterState,Integer districtCode) throws Exception
	{		
		List<ShiftVillageSD> getordercode = null;
		String retValue=null;
		String description=null;
		try
		{
			session=sessionFactory.openSession();
			tx=session.beginTransaction();
			if(isCenterState.equalsIgnoreCase("C"))
			{	
				if(shiftvillageForm.getFinalsourceDistName() !=null && shiftvillageForm.getFinaltargetDistName() !=null)
				{
					description="Villages {"+ shiftvillageForm.getFinalVillageName() +"} has been shifted from Subdistrict {"+shiftvillageForm.getFinalsourceSubdistName()+"} of District {"+shiftvillageForm.getFinalsourceDistName()+"} of State {"+ shiftvillageForm.getFinalsourceStateName()+" } to Sub district {"+shiftvillageForm.getFinaltargetSubdistName()+"} of District {"+shiftvillageForm.getFinaltargetDistName()+"} of State {"+shiftvillageForm.getFinaltargetStateName()+"}";
				}
				/*if(shiftvillageForm.getFinalsourceDistName() ==null && shiftvillageForm.getFinaltargetDistName() ==null)
				{
					description="Villages "+ shiftvillageForm.getFinalVillageName() +" has been shifted between Subdistrict "+shiftvillageForm.getFinalsourceSubdistName()+" to "+shiftvillageForm.getFinaltargetSubdistName();
				}*/
			}
			else
			{
				if(districtCode>0){
					List<District> districtList=districtDAO.getDistrict(districtCode); 
					if(districtList!=null && !districtList.isEmpty()){
						String districtNameEng=districtList.get(0).getDistrictNameEnglish();
						description="Villages {"+ shiftvillageForm.getFinalVillageName() +"} has been shifted from Subdistrict {"+shiftvillageForm.getFinalsourceSubdistName()+"} of District {"+districtNameEng+"} to Sub district {"+shiftvillageForm.getFinaltargetSubdistName()+"} of District {"+districtNameEng+"}";
					}
				}else if(shiftvillageForm.getFinalsourceDistName() !=null && shiftvillageForm.getFinaltargetDistName() !=null)
				{
					description="Villages {"+ shiftvillageForm.getFinalVillageName() +"} has been shifted from Subdistrict {"+shiftvillageForm.getFinalsourceSubdistName()+"} of District {"+shiftvillageForm.getFinalsourceDistName()+"} to Sub district {"+shiftvillageForm.getFinaltargetSubdistName()+"} of District {"+shiftvillageForm.getFinaltargetDistName()+"}";
				}
			}
			
			
			String[] subdistrictCodes=shiftvillageForm.getSubdistrictNameEnglish().split(",");
			int subdistrictTargetCode=Integer.parseInt(shiftvillageForm.getSubdistrictNameEnglishTarget());
			String villageCodes=shiftvillageForm.getVillageNameEnglish();
			
			int subdistrictSourceCode = Integer.parseInt(subdistrictCodes[0].toString());
			//int subdistrictTargetCode = Integer.parseInt(subdistrictCodes[1].toString());
			
			
			getordercode=shiftDao.shiftVillageforSubdistrict(subdistrictSourceCode,subdistrictTargetCode,villageCodes,userId,govtOrderForm,session,description);
			//GovernmentOrder govtOrder=convertLocalbodyService.saveDataInGovtOrder(govtOrderForm, session);
			//convertLocalbodyService.saveDataInAttachment(govtOrder, govtOrderForm,attachedList, session);
			//saveDataInGovtOrderEntityWise(govtOrder,villageCodes,'V',session);				
			tx.commit();
			
			Iterator<ShiftVillageSD> shiftVillageSDItr = getordercode.iterator();
			ShiftVillageSD localdata = (ShiftVillageSD)shiftVillageSDItr.next();
			retValue = localdata.getShift_village_fn();
			
			//return true;
			
		} catch (Exception e){
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
			tx.rollback();
			//return false;
			
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return retValue;
		
	}
	public String shiftVillageforSubdistrictforTemplate(ShiftVillageForm shiftvillageForm, GovernmentOrderForm govtForm,HttpServletRequest request,Integer userId,String isCenterState) throws Exception
	{
		List<ShiftVillageSD> getordercode = null;
		String retValue=null;
		String description=null;
		try {
			session=sessionFactory.openSession();
			tx=session.beginTransaction();	
			
			//String description="Villages "+ shiftvillageForm.getFinalVillageName() +" has been shifted between Subdistrict "+shiftvillageForm.getFinalsourceSubdistName()+" of District "+shiftvillageForm.getFinalsourceDistName()+"to "+shiftvillageForm.getFinalsourceSubdistName()+" of District "+shiftvillageForm.getFinaltargetDistName();
			
			
			if(isCenterState.equalsIgnoreCase("C"))
			{	
				if(shiftvillageForm.getFinalsourceDistName() !=null && shiftvillageForm.getFinaltargetDistName() !=null)
				{
					description="Villages {"+ shiftvillageForm.getFinalVillageName() +"} has been shifted from Subdistrict {"+shiftvillageForm.getFinalsourceSubdistName()+"} of District {"+shiftvillageForm.getFinalsourceDistName()+"} of State {"+ shiftvillageForm.getFinalsourceStateName()+" } to Sub district {"+shiftvillageForm.getFinaltargetSubdistName()+"} of District {"+shiftvillageForm.getFinaltargetDistName()+"} of State {"+shiftvillageForm.getFinaltargetStateName()+"}";
				}
				/*if(shiftvillageForm.getFinalsourceDistName() ==null && shiftvillageForm.getFinaltargetDistName() ==null)
				{
					description="Villages "+ shiftvillageForm.getFinalVillageName() +" has been shifted between Subdistrict "+shiftvillageForm.getFinalsourceSubdistName()+" to "+shiftvillageForm.getFinaltargetSubdistName();
				}*/
			}
			else
			{
				if(shiftvillageForm.getFinalsourceDistName() !=null && shiftvillageForm.getFinaltargetDistName() !=null)
				{
					description="Villages {"+ shiftvillageForm.getFinalVillageName() +"} has been shifted from Subdistrict {"+shiftvillageForm.getFinalsourceSubdistName()+"} of District {"+shiftvillageForm.getFinalsourceDistName()+"} to Sub district {"+shiftvillageForm.getFinaltargetSubdistName()+"} of District {"+shiftvillageForm.getFinaltargetDistName()+"}";
				}
			}
			
			
			String[] subdistrictCodes=shiftvillageForm.getSubdistrictNameEnglish().split(",");
			String villageCodes=shiftvillageForm.getVillageNameEnglish();
			
			//int subdistrictSourceCode = Integer.parseInt(subdistrictCodes[0].toString());
			//int subdistrictTargetCode = Integer.parseInt(subdistrictCodes[1].toString());			
			int subdistrictSourceCode = Integer.parseInt(shiftvillageForm.getSubdistrictNameEnglish());
			int subdistrictTargetCode = Integer.parseInt(shiftvillageForm.getSubdistrictNameEnglishTarget());			
			
			getordercode=shiftDao.shiftVillageforSubdistrict(subdistrictSourceCode,subdistrictTargetCode,villageCodes,userId, govtForm,session,description);
			
			Iterator<ShiftVillageSD> shiftVillageSDItr = getordercode.iterator();
			ShiftVillageSD localdata = (ShiftVillageSD)shiftVillageSDItr.next();
			retValue = localdata.getShift_village_fn();
			
			/*GovernmentOrder govtOrder=convertLocalbodyService.saveDataInGovtOrder(govtForm, session);
			saveDataInGovtOrderEntityWise(govtOrder,villageCodes,'V',session);	*/
			tx.commit();
			
			//return true;
			
		} catch (Exception e){
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
			tx.rollback();
			//return false;
			
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return retValue;
	}
	
	public boolean shiftVillagereorganize(ShiftVillageForm shiftvillageForm,HttpServletRequest request) throws Exception
	{		
			
		 try {
			String[] subdistrictCodes=shiftvillageForm.getSubdistrictNameEnglish().split(",");
			String villageCodes=shiftvillageForm.getVillageNameEnglish();
			
			int subdistrictSourceCode = Integer.parseInt(subdistrictCodes[0].toString());
			int subdistrictTargetCode = Integer.parseInt(subdistrictCodes[1].toString());
			
			
			shiftDao.shiftVillageReorganize(subdistrictSourceCode,subdistrictTargetCode,villageCodes,145,shiftvillageForm.getEffectiveDate());
		} catch (Exception e){
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
		}
		return true;
	}
	
	public boolean shiftVillageforBlock(ShiftVillageForm shiftvillageForm,GovernmentOrderForm govtOrderForm, HttpServletRequest request,Integer userId,List<AttachedFilesForm> metaInfoOfToBeAttachedFileList) throws Exception
	{					
		List<ShiftVillageSD> getordercode = null;
		String blockdata=null;
		boolean success = false;
		try {
			session=sessionFactory.openSession();
			tx=session.beginTransaction();		
			
			String description="Villages "+ shiftvillageForm.getFinalVillageName() +" has been shifted between Block "+shiftvillageForm.getFinalsourceBlockName()+" of District "+shiftvillageForm.getFinalsourceDistName() +" to Block "+shiftvillageForm.getFinaltargetBlockName();

			
			String[] blockCodes=shiftvillageForm.getBlockNameEnglish().split(",");
			String villageCodes=shiftvillageForm.getVillageNameEnglish();
			
			int blockCodesSourceCode = Integer.parseInt(blockCodes[0].toString());
			int blockCodesTargetCode = Integer.parseInt(blockCodes[1].toString());
			
			getordercode=shiftDao.shiftVillageforBlock(blockCodesSourceCode,blockCodesTargetCode,villageCodes,userId,govtOrderForm,'B',session,description);
			
			Iterator<ShiftVillageSD> shiftVillageSDItr = getordercode.iterator();
			ShiftVillageSD localdata = (ShiftVillageSD)shiftVillageSDItr.next();
			blockdata = localdata.getShift_village_fn();
			if(blockdata!=null)
			{
				String[] ldata = blockdata.split(",");
				String vc = ldata[1];
				String tid = ldata[0];
				Integer ordercode  = Integer.parseInt(vc);
				Integer Transactionid = Integer.parseInt(tid);
				if(ordercode!=null)
				{
					 //govtOrderForm = null;
					
					GovernmentOrder govtOrder = new GovernmentOrder();
					if (shiftvillageForm.getGovtOrderConfig().equals("govtOrderUpload")) {
						govtOrder.setOrderDate(govtOrderForm.getOrderDate());
						govtOrder.setEffectiveDate(govtOrderForm.getEffectiveDate());
						govtOrder.setGazPubDate(govtOrderForm.getGazPubDate());
						govtOrder.setCreatedon(new Date());
						govtOrder.setDescription("LGD DETAILS");
						govtOrder.setIssuedBy("GOVERNOR");
						govtOrder.setCreatedby(createdBy);
						govtOrder.setLastupdated(new Date());
						govtOrder.setLastupdatedby(createdBy);
						govtOrder.setLevel("U");
						govtOrder.setOrderNo(govtOrderForm.getOrderNo());
						govtOrder.setStatus('A');
						govtOrder.setUserId(userId);
						govtOrder.setOrderCode(ordercode);
						
					}else if (shiftvillageForm.getGovtOrderConfig().equals("govtOrderGenerate")){
						if (ordercode != 0) {
							govtOrder.setOrderCode(ordercode);
							
						}	
					}
					
					 convertLocalbodyService.saveDataInAttachment(govtOrder, govtOrderForm, metaInfoOfToBeAttachedFileList, session);
					 tx.commit();
						success=true;
						char t_type='V';
						EmailSmsThread est= new EmailSmsThread(Transactionid, t_type,emailService);							
						est.start();
					
				}
		}
			/*GovernmentOrder govtOrder=convertLocalbodyService.saveDataInGovtOrder(govtOrderForm, session);
			convertLocalbodyService.saveDataInAttachment(govtOrder, govtOrderForm,attachedList, session);
			saveDataInGovtOrderEntityWise(govtOrder,villageCodes,'V',session);	*/	
			
		
			//return true;	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
			tx.rollback();
			//return false;
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return success;
		
	}
	
	public String shiftVillageforBlockforTemplate(ShiftVillageForm shiftvillageForm,GovernmentOrderForm govtForm,HttpServletRequest request,Integer userId) throws Exception
	{
		List<ShiftVillageSD> getordercode = null;
		String retValue=null;
		try
		{
			session=sessionFactory.openSession();
			tx=session.beginTransaction();	
			String description="Villages "+ shiftvillageForm.getFinalVillageName() +" has been shifted between Block "+shiftvillageForm.getFinalsourceBlockName()+" of District "+shiftvillageForm.getFinalsourceDistName() +" to Block "+shiftvillageForm.getFinaltargetBlockName();

			
			String[] blockCodes=shiftvillageForm.getBlockNameEnglish().split(",");
			String villageCodes=shiftvillageForm.getVillageNameEnglish();
			
			int blockCodesSourceCode = Integer.parseInt(blockCodes[0].toString());
			int blockCodesTargetCode = Integer.parseInt(blockCodes[1].toString());
			
			getordercode=shiftDao.shiftVillageforBlock(blockCodesSourceCode,blockCodesTargetCode,villageCodes,userId,govtForm,'B',session,description);
			Iterator<ShiftVillageSD> shiftVillageSDItr = getordercode.iterator();
			ShiftVillageSD localdata = (ShiftVillageSD)shiftVillageSDItr.next();
			retValue = localdata.getShift_village_fn();
			
			/*GovernmentOrder govtOrder=convertLocalbodyService.saveDataInGovtOrder(govtForm, session);
			saveDataInGovtOrderEntityWise(govtOrder,villageCodes,'V',session);*/	
			tx.commit();			
			//return true;	
		} 
		catch (Exception e) 
		{
			log.debug("Exception" + e);
			tx.rollback();
			//return false;
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return retValue;
	}
	
	@Override
	public boolean saveDataInAttachShiftBlock(GovernmentOrderForm governmentOrderForm,ShiftBlockForm shiftblockForm,List<AttachedFilesForm> attachedList, HttpSession session,int getordercode) throws Exception 
	{	
		return shiftDao.saveDataInAttachShiftBlock(governmentOrderForm,shiftblockForm,attachedList, session,getordercode);
	}
	@Override
	public boolean saveDataInAttachShiftVillageSD(GovernmentOrderForm governmentOrderForm,ShiftVillageForm shiftvillageForm,List<AttachedFilesForm> attachedList, HttpSession session,int getordercode) throws Exception 
	{	
		return shiftDao.saveDataInAttachShiftVillageSD(governmentOrderForm,shiftvillageForm,attachedList, session,getordercode);
	}
	
	@Override
	public boolean saveDataInAttachShiftGenerateVillageSD(GovernmentOrderForm governmentOrderForm,ShiftVillageForm shiftvillageForm, HttpSession session,int getordercode) throws Exception 
	{	
		return shiftDao.saveDataInAttachShiftGenerateVillageSD(governmentOrderForm,shiftvillageForm,session,getordercode);
	}
	
	@Override
	public boolean saveDataInAttachGenerateShiftBlock(GovernmentOrderForm governmentOrderForm,ShiftBlockForm shiftblockForm, HttpSession session,int getordercode) throws Exception 
	{	
		return shiftDao.saveDataInAttachGenerateShiftBlock(governmentOrderForm,shiftblockForm,session,getordercode);
	}
	
	@Override
	public boolean saveDataInAttachShiftGenerateDistrict(GovernmentOrderForm governmentOrderForm,ShiftDistrictForm shiftDistricForm, HttpSession session,int getordercode) throws Exception 
	{	
		return shiftDao.saveDataInAttachShiftGenerateDistrict(governmentOrderForm,shiftDistricForm,session,getordercode);
	}
	
	@Override
	public boolean saveDataInAttachShiftGenerateSubDistrict(GovernmentOrderForm governmentOrderForm,ShiftSubDistrictForm shiftSubDistrictForm, HttpSession session,int getordercode) throws Exception 
	{	
		return shiftDao.saveDataInAttachShiftGenerateSubDistrict(governmentOrderForm,shiftSubDistrictForm,session,getordercode);
	}
	
	@Override
	public boolean saveDataInAttachShiftVillageBlock(GovernmentOrderForm governmentOrderForm,ShiftVillageForm shiftvillageForm,List<AttachedFilesForm> attachedList, HttpSession session,int getordercode) throws Exception 
	{	
		return shiftDao.saveDataInAttachShiftVillageBlock(governmentOrderForm,shiftvillageForm,attachedList, session, getordercode);
	}
	@Override
	public boolean saveDataInAttachShiftDistrict(GovernmentOrderForm governmentOrderForm,ShiftDistrictForm shiftDistrictForm,List<AttachedFilesForm> attachedList, HttpSession session,int getordercode) throws Exception 
	{	
		return shiftDao.saveDataInAttachShiftDistrict(governmentOrderForm,shiftDistrictForm,attachedList, session,getordercode);
	}
	@Override
	public boolean saveDataInAttachShiftSubDistrict(GovernmentOrderForm governmentOrderForm,ShiftSubDistrictForm shiftSubDistrictForm,List<AttachedFilesForm> attachedList, HttpSession session,int getordercode) throws Exception 
	{	
		return shiftDao.saveDataInAttachShiftSubDistrict(governmentOrderForm,shiftSubDistrictForm,attachedList, session,getordercode);
	}
	
	public List<State> getStateNamebySourcestateID(ShiftDistrictForm shiftDistrictForm) throws Exception 
	{
		List<State> statetNameList = new ArrayList<State>();
		List<State> stateNameListFull = new ArrayList<State>();

		String listOfIds = null;
		
		try
		{
			if(shiftDistrictForm.getStateSName() !=null)
			{
				listOfIds=shiftDistrictForm.getStateSName();
			}
			
			String selectdStatetId[] = listOfIds.split(",");
			for (String dpIdObj : selectdStatetId)
			{
				stateNameListFull = shiftDao.getStateNamebystateID(Integer.parseInt(dpIdObj));
				statetNameList.addAll(stateNameListFull);
			}
		}
		catch (Exception e) 
		{
			throw e;
		}
		return statetNameList;
	}
	
	public List<State> getStateNamebyDeststateID(ShiftDistrictForm shiftDistrictForm) throws Exception 
	{
		List<State> statetNameList = new ArrayList<State>();
		List<State> stateNameListFull = new ArrayList<State>();

		String listOfIds = null;
		
		try
		{
			if(shiftDistrictForm.getStateDName() !=null)
			{
				listOfIds=shiftDistrictForm.getStateDName();
			}
			
			String selectdStatetId[] = listOfIds.split(",");
			for (String dpIdObj : selectdStatetId)
			{
				stateNameListFull = shiftDao.getStateNamebystateID(Integer.parseInt(dpIdObj));
				statetNameList.addAll(stateNameListFull);
			}
		}
		catch (Exception e) 
		{
			throw e;
		}
		return statetNameList;
	}
	
	public List<District> getdistrictNamebyDistID(ShiftDistrictForm shiftDistrictForm) throws Exception 
	{
		List<District> districtNameList = new ArrayList<District>();
		List<District> districtNameListFull = new ArrayList<District>();

		String listOfIds = null;
		
		try
		{
			if(shiftDistrictForm.getStateDName() !=null)
			{
				listOfIds=shiftDistrictForm.getDistrictNameEnglish();
			}
			
			String selectdDistId[] = listOfIds.split(",");
			for (String dpIdObj : selectdDistId)
			{
				districtNameListFull = shiftDao.getdistrictNamebyDistID(Integer.parseInt(dpIdObj));
				districtNameList.addAll(districtNameListFull);
			}
		}
		catch (Exception e) 
		{
			throw e;
		}
		return districtNameList;
	}
	
	public List<District> getsourcedistrictNamebyDistIDforShftSubDist(ShiftSubDistrictForm shiftSubDistrictForm) throws Exception 
	{
		List<District> districtNameListsource = new ArrayList<District>();
		List<District> districtNameListFullsource = new ArrayList<District>();

		String listOfIds = null;
		
		try
		{
			if(shiftSubDistrictForm.getDistrictSName() !=null)
			{
				listOfIds=shiftSubDistrictForm.getDistrictSName();
			}
			
			String selectdDistId[] = listOfIds.split(",");
			for (String dpIdObj : selectdDistId)
			{
				districtNameListFullsource = shiftDao.getdistrictNamebyDistID(Integer.parseInt(dpIdObj));
				districtNameListsource.addAll(districtNameListFullsource);
			}
		}
		catch (Exception e) 
		{
			throw e;
		}
		return districtNameListsource;
	}
	
	public List<District> gettargetdistrictNamebyDistIDforShftSubDist(ShiftSubDistrictForm shiftSubDistrictForm) throws Exception 
	{
		List<District> districtNameListtarget = new ArrayList<District>();
		List<District> districtNameListFulltarget = new ArrayList<District>();

		String listOfIds = null;
		
		try
		{
			if(shiftSubDistrictForm.getDistrictDName() !=null)
			{
				listOfIds=shiftSubDistrictForm.getDistrictDName();
			}
			
			String selectdDistId[] = listOfIds.split(",");
			for (String dpIdObj : selectdDistId)
			{
				districtNameListFulltarget = shiftDao.getdistrictNamebyDistID(Integer.parseInt(dpIdObj));
				districtNameListtarget.addAll(districtNameListFulltarget);
			}
		}
		catch (Exception e) 
		{
			throw e;
		}
		return districtNameListtarget;
	}
	
	
	public List<District> getsourcedistrictNamebyDistIDforShiftBlock(ShiftBlockForm shiftblockForm) throws Exception 
	{
		List<District> districtNameListsource = new ArrayList<District>();
		List<District> districtNameListFullsource = new ArrayList<District>();

		String listOfIds = null;
		
		try
		{
			if(shiftblockForm.getDistrictSName() !=null)
			{
				listOfIds=shiftblockForm.getDistrictSName();
			}
			
			String selectdDistId[] = listOfIds.split(",");
			for (String dpIdObj : selectdDistId)
			{
				districtNameListFullsource = shiftDao.getdistrictNamebyDistID(Integer.parseInt(dpIdObj));
				districtNameListsource.addAll(districtNameListFullsource);
			}
		}
		catch (Exception e) 
		{
			throw e;
		}
		return districtNameListsource;
	}
	
	public List<District> gettargetdistrictNamebyDistIDforShiftBlock(ShiftBlockForm shiftblockForm) throws Exception 
	{
		List<District> districtNameListtarget = new ArrayList<District>();
		List<District> districtNameListFulltarget = new ArrayList<District>();

		String listOfIds = null;
		
		try
		{
			if(shiftblockForm.getDistrictDName() !=null)
			{
				listOfIds=shiftblockForm.getDistrictDName();
			}
			
			String selectdDistId[] = listOfIds.split(",");
			for (String dpIdObj : selectdDistId)
			{
				districtNameListFulltarget = shiftDao.getdistrictNamebyDistID(Integer.parseInt(dpIdObj));
				districtNameListtarget.addAll(districtNameListFulltarget);
			}
		}
		catch (Exception e) 
		{
			throw e;
		}
		return districtNameListtarget;
	}
	
	
	
	public List<Subdistrict> getsubdistrictNamebySubDistIDforShftSubDist(ShiftSubDistrictForm shiftSubDistrictForm) throws Exception 
	{
		List<Subdistrict> subdistrictNameList = new ArrayList<Subdistrict>();
		List<Subdistrict> subdistrictNameListFull = new ArrayList<Subdistrict>();

		String listOfIds = null;
		
		try
		{
			if(shiftSubDistrictForm.getSubdistrictNameEnglish() !=null)
			{
				listOfIds=shiftSubDistrictForm.getSubdistrictNameEnglish();
			}
			
			String selectdSubDistId[] = listOfIds.split(",");
			for (String dpIdObj : selectdSubDistId)
			{
				subdistrictNameListFull = shiftDao.getsubdistrictNamebysubDistID(Integer.parseInt(dpIdObj));
				subdistrictNameList.addAll(subdistrictNameListFull);
			}
		}
		catch (Exception e) 
		{
			throw e;
		}
		return subdistrictNameList;
	}
	
	public List<State> getsourcestateNamebyStateID(ShiftSubDistrictForm shiftsubDistrictForm) throws Exception 
	{
		List<State> statetNameList = new ArrayList<State>();
		List<State> stateNameListFull = new ArrayList<State>();

		String listOfIds = null;
		
		try
		{
			if(shiftsubDistrictForm.getStateSName() !=null)
			{
				listOfIds=shiftsubDistrictForm.getStateSName();
			}
			
			String selectdStatetId[] = listOfIds.split(",");
			for (String dpIdObj : selectdStatetId)
			{
				stateNameListFull = shiftDao.getStateNamebystateID(Integer.parseInt(dpIdObj));
				statetNameList.addAll(stateNameListFull);
			}
		}
		catch (Exception e) 
		{
			throw e;
		}
		return statetNameList;
	}
	
	
	public List<State> getsourcestateNamebyStateIDforSD(ShiftSubDistrictForm shiftsubDistrictForm) throws Exception 
	{
		List<State> statetNameList = new ArrayList<State>();
		List<State> stateNameListFull = new ArrayList<State>();

		String listOfIds = null;
		
		try
		{
			if(shiftsubDistrictForm.getStateNameEnglish() !=null)
			{
				listOfIds=shiftsubDistrictForm.getStateNameEnglish();
			}
			
			String selectdStatetId[] = listOfIds.split(",");
			for (String dpIdObj : selectdStatetId)
			{
				stateNameListFull = shiftDao.getStateNamebystateID(Integer.parseInt(dpIdObj));
				statetNameList.addAll(stateNameListFull);
			}
		}
		catch (Exception e) 
		{
			throw e;
		}
		return statetNameList;
	}
	
	
	public List<State> gettargetstateNamebyStateID(ShiftSubDistrictForm shiftsubDistrictForm) throws Exception 
	{
		List<State> statetNameList = new ArrayList<State>();
		List<State> stateNameListFull = new ArrayList<State>();

		String listOfIds = null;
		
		try
		{
			if(shiftsubDistrictForm.getStateDName() !=null)
			{
				listOfIds=shiftsubDistrictForm.getStateDName();
			}
			
			String selectdStatetId[] = listOfIds.split(",");
			for (String dpIdObj : selectdStatetId)
			{
				stateNameListFull = shiftDao.getStateNamebystateID(Integer.parseInt(dpIdObj));
				statetNameList.addAll(stateNameListFull);
			}
		}
		catch (Exception e) 
		{
			throw e;
		}
		return statetNameList;
	}
	
	public List<State> gettargetstateNamebyStateIDforSD(ShiftSubDistrictForm shiftsubDistrictForm) throws Exception 
	{
		List<State> statetNameList = new ArrayList<State>();
		List<State> stateNameListFull = new ArrayList<State>();

		String listOfIds = null;
		
		try
		{
			if(shiftsubDistrictForm.getStateNameEnglishTarget() !=null)
			{
				listOfIds=shiftsubDistrictForm.getStateNameEnglishTarget();
			}
			
			String selectdStatetId[] = listOfIds.split(",");
			for (String dpIdObj : selectdStatetId)
			{
				stateNameListFull = shiftDao.getStateNamebystateID(Integer.parseInt(dpIdObj));
				statetNameList.addAll(stateNameListFull);
			}
		}
		catch (Exception e) 
		{
			throw e;
		}
		return statetNameList;
	}
	
	public List<District> getsourcedistrictNamebyDistIDforShftVillageSD(ShiftVillageForm shiftVillageForm) throws Exception 
	{
		List<District> districtNameListsource = new ArrayList<District>();
		List<District> districtNameListFullsource = new ArrayList<District>();

		String listOfIds = null;
		
		try
		{
			if(shiftVillageForm.getDistrictSName() !=null)
			{
				listOfIds=shiftVillageForm.getDistrictSName();
			}
			
			String selectdDistId[] = listOfIds.split(",");
			for (String dpIdObj : selectdDistId)
			{
				districtNameListFullsource = shiftDao.getdistrictNamebyDistID(Integer.parseInt(dpIdObj));
				districtNameListsource.addAll(districtNameListFullsource);
			}
		}
		catch (Exception e) 
		{
			throw e;
		}
		return districtNameListsource;
	}
	
	public List<District> gettargetdistrictNamebyDistIDforShftVillageSD(ShiftVillageForm shiftVillageForm) throws Exception 
	{
		List<District> districtNameListtarget = new ArrayList<District>();
		List<District> districtNameListFulltarget = new ArrayList<District>();

		String listOfIds = null;
		
		try
		{
			if(shiftVillageForm.getDistrictDName() !=null)
			{
				listOfIds=shiftVillageForm.getDistrictDName();
			}
			
			String selectdDistId[] = listOfIds.split(",");
			for (String dpIdObj : selectdDistId)
			{
				districtNameListFulltarget = shiftDao.getdistrictNamebyDistID(Integer.parseInt(dpIdObj));
				districtNameListtarget.addAll(districtNameListFulltarget);
			}
		}
		catch (Exception e) 
		{
			throw e;
		}
		return districtNameListtarget;
	}
	
	public List<Subdistrict> getsourcesubdistrictNamebySubDistIDforShftVillageSD(ShiftVillageForm shiftVillageForm) throws Exception 
	{
		List<Subdistrict> sourcesubdistrictNameList = new ArrayList<Subdistrict>();
		List<Subdistrict> sourcesubdistrictNameListFull = new ArrayList<Subdistrict>();

		String listOfIds = null;
		try
		{
			if(shiftVillageForm.getSubdistrictNameEnglish() !=null)
			{
				listOfIds=shiftVillageForm.getSubdistrictNameEnglish();
			}
			
			String selectdSubDistId[] = listOfIds.split(",");
			for (String dpIdObj : selectdSubDistId)
			{
				sourcesubdistrictNameListFull = shiftDao.getsubdistrictNamebysubDistID(Integer.parseInt(dpIdObj));
				sourcesubdistrictNameList.addAll(sourcesubdistrictNameListFull);
			}
		}
		catch (Exception e) 
		{
			throw e;
		}
		return sourcesubdistrictNameList;
	}
	
	public List<Subdistrict> gettargetsubdistrictNamebySubDistIDforShftVillageSD(ShiftVillageForm shiftVillageForm) throws Exception 
	{
		List<Subdistrict> targetsubdistrictNameList = new ArrayList<Subdistrict>();
		List<Subdistrict> targetsubdistrictNameListFull = new ArrayList<Subdistrict>();

		String listOfIds = null;
		try
		{
			if(shiftVillageForm.getSubdistrictNameEnglishTarget() !=null)
			{
				listOfIds=shiftVillageForm.getSubdistrictNameEnglishTarget();
			}
			
			String selectdSubDistId[] = listOfIds.split(",");
			for (String dpIdObj : selectdSubDistId)
			{
				targetsubdistrictNameListFull = shiftDao.getsubdistrictNamebysubDistID(Integer.parseInt(dpIdObj));
				targetsubdistrictNameList.addAll(targetsubdistrictNameListFull);
			}
		}
		catch (Exception e) 
		{
			throw e;
		}
		return targetsubdistrictNameList;
	}
	
	public List<Village> getVillageNamebyVillageIDforShftVillageSD(ShiftVillageForm shiftVillageForm) throws Exception 
	{
		List<Village> targetvillageNameList = new ArrayList<Village>();
		List<Village> targetvillageNameListFull = new ArrayList<Village>();

		String listOfIds = null;
		try
		{
			if(shiftVillageForm.getVillageNameEnglish() !=null)
			{
				listOfIds=shiftVillageForm.getVillageNameEnglish();
			}
			
			String selectdVillageId[] = listOfIds.split(",");
			for (String dpIdObj : selectdVillageId)
			{
				targetvillageNameListFull = shiftDao.getVillageNamebyVillageID(Integer.parseInt(dpIdObj));
				targetvillageNameList.addAll(targetvillageNameListFull);
			}
		}
		catch (Exception e) 
		{
			throw e;
		}
		return targetvillageNameList;
	}
	
	public List<Block> getsourceblockNamebyBlockID(ShiftVillageForm shiftVillageForm) throws Exception 
	{
		List<Block> sourceBlockNameList = new ArrayList<Block>();
		List<Block> sourceBlockNameListFull = new ArrayList<Block>();

		String listOfIds = null;
		try
		{
			if(shiftVillageForm.getBlockSName() !=null)
			{
				listOfIds=shiftVillageForm.getBlockSName();
			}
			
			String selectdVillageId[] = listOfIds.split(",");
			for (String dpIdObj : selectdVillageId)
			{
				sourceBlockNameListFull = shiftDao.getblockNamebyBlockID(Integer.parseInt(dpIdObj));
				sourceBlockNameList.addAll(sourceBlockNameListFull);
			}
		}
		catch (Exception e) 
		{
			throw e;
		}
		return sourceBlockNameList;
	}
	
	public List<Block> gettargetblockNamebyBlockID(ShiftVillageForm shiftVillageForm) throws Exception 
	{
		List<Block> targetBlockNameList = new ArrayList<Block>();
		List<Block> targetBlockNameListFull = new ArrayList<Block>();

		String listOfIds = null;
		try
		{
			if(shiftVillageForm.getBlockDName() !=null)
			{
				listOfIds=shiftVillageForm.getBlockDName();
			}
			
			String selectdVillageId[] = listOfIds.split(",");
			for (String dpIdObj : selectdVillageId)
			{
				targetBlockNameListFull = shiftDao.getblockNamebyBlockID(Integer.parseInt(dpIdObj));
				targetBlockNameList.addAll(targetBlockNameListFull);
			}
		}
		catch (Exception e) 
		{
			throw e;
		}
		return targetBlockNameList;
	}
	
	public List<Block> getblockNamebyBlockID(ShiftBlockForm shiftblockForm) throws Exception 
	{
		List<Block> targetBlockNameList = new ArrayList<Block>();
		List<Block> targetBlockNameListFull = new ArrayList<Block>();

		String listOfIds = null;
		try
		{
			if(shiftblockForm.getBlockNameEnglish() !=null)
			{
				listOfIds=shiftblockForm.getBlockNameEnglish();
			}
			
			String selectdVillageId[] = listOfIds.split(",");
			for (String dpIdObj : selectdVillageId)
			{
				targetBlockNameListFull = shiftDao.getblockNamebyBlockID(Integer.parseInt(dpIdObj));
				targetBlockNameList.addAll(targetBlockNameListFull);
			}
		}
		catch (Exception e) 
		{
			throw e;
		}
		return targetBlockNameList;
	}
	
	
	
	public List<State> getsourcestateNamebyStateIDforSD(ShiftVillageForm shiftVillageForm) throws Exception 
	{
		List<State> statetNameList = new ArrayList<State>();
		List<State> stateNameListFull = new ArrayList<State>();

		String listOfIds = null;
		
		try
		{
			if(shiftVillageForm.getStateSName() !=null)
			{
				listOfIds=shiftVillageForm.getStateSName();
			}
			
			String selectdStatetId[] = listOfIds.split(",");
			for (String dpIdObj : selectdStatetId)
			{
				stateNameListFull = shiftDao.getStateNamebystateID(Integer.parseInt(dpIdObj));
				statetNameList.addAll(stateNameListFull);
			}
		}
		catch (Exception e) 
		{
			throw e;
		}
		return statetNameList;
	}
	
	public List<State> gettargetstateNamebyStateIDforSD(ShiftVillageForm shiftVillageForm) throws Exception 
	{
		List<State> statetNameListtarget = new ArrayList<State>();
		List<State> stateNameListFulltarget = new ArrayList<State>();

		String listOfIds = null;
		
		try
		{
			if(shiftVillageForm.getStateTName() !=null)
			{
				listOfIds=shiftVillageForm.getStateTName();
			}
			
			String selectdStatetId[] = listOfIds.split(",");
			for (String dpIdObj : selectdStatetId)
			{
				stateNameListFulltarget = shiftDao.getStateNamebystateID(Integer.parseInt(dpIdObj));
				statetNameListtarget.addAll(stateNameListFulltarget);
			}
		}
		catch (Exception e) 
		{
			throw e;
		}
		return statetNameListtarget;
	}
	
	
}
