package in.nic.pes.lgd.service.impl;

import in.nic.pes.lgd.bean.NomenclatureSubdistrict;
import in.nic.pes.lgd.bean.NomenclatureSubdistrictPK;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.bean.State;
import in.nic.pes.lgd.bean.StatePK;
import in.nic.pes.lgd.dao.NomenclatureSubDistDAO;
import in.nic.pes.lgd.dao.StateDAO;
import in.nic.pes.lgd.forms.NomenclatureSubDistForm;
import in.nic.pes.lgd.service.CommonService;
import in.nic.pes.lgd.service.NomenclatureSubDistService;
import in.nic.pes.lgd.utils.CurrentDateTime;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

public class NomenclatureSubDistServiceImpl implements NomenclatureSubDistService {
	
	@Autowired
	NomenclatureSubDistDAO nomenclatureSubDistDAO;
	@Autowired
	StateDAO stateDao;
    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    CommonService commonService; 
	@Override
	public boolean addNomenclatureSudDist(NomenclatureSubDistForm nomenclatureSubDistForm,int stateCode,HttpSession httpsession) throws Exception {
		int stateVersion=0;
		int nsdCode = nomenclatureSubDistDAO.getMaxRecords("select COALESCE(max(nsd_code),1) from nomenclature_subdistrict");
		stateVersion=stateDao.getCurrentVersionbyStateCode(stateCode);
		int nsdVersion=1;
		StatePK statePK=null;
		State state=null;
		Timestamp time = null;
		boolean temp=false;
		boolean success=false;
		time = CurrentDateTime.getCurrentTimestamp();
		NomenclatureSubdistrictPK nomenclatureSubdistrictPK= null;
		NomenclatureSubdistrict nomenclatureSubdistrict= null;
		int slc=commonService.getSlc(stateCode);
		
		nomenclatureSubdistrict = new NomenclatureSubdistrict();
		nomenclatureSubdistrictPK= new NomenclatureSubdistrictPK(nsdCode,nsdVersion);
		
		statePK= new StatePK(stateCode,stateVersion);
		state= new State();
		state.setStatePK(statePK);
		nomenclatureSubdistrict.setSlc(slc);
		nomenclatureSubdistrict.setNomenclatureSubdistrictPK(nomenclatureSubdistrictPK);
		//nomenclatureSubdistrict.setState(state);
		nomenclatureSubdistrict.setNomenclatureEnglish(nomenclatureSubDistForm.getNameinEnglish());
		nomenclatureSubdistrict.setNomenclatureLocal(nomenclatureSubDistForm.getNameinlocalLanguage());
		nomenclatureSubdistrict.setIsactive(true);
		SessionObject sessionObject = (SessionObject) httpsession.getAttribute("sessionObject");
		Long userId = sessionObject.getUserId();
		nomenclatureSubdistrict.setCreatedby(userId);
		nomenclatureSubdistrict.setCreatedon(time);
		nomenclatureSubdistrict.setEffectiveDate(time);
		nomenclatureSubdistrict.setIssubdistrictblocksame(nomenclatureSubDistForm.isNameofTheBlockisSameforTheState());
		nomenclatureSubdistrict.setBlockExists(nomenclatureSubDistForm.isBlockExist());
		Session session = null ;
		Transaction tx = null;

		session = sessionFactory.openSession();
		tx=session.beginTransaction();
			try {
					temp=nomenclatureSubDistDAO.addNomenclatureSudDist(nomenclatureSubdistrict,session);
					
					if(temp)
					{
						tx.commit();
						success=true;
					}
			} catch (Exception e) {
			
				tx.rollback();
				success=false;
			}
			finally
			{
				if (session != null && session.isOpen()) {
					session.close();
				}
			}
		
		return success;
	}

	@Override
	public List<NomenclatureSubdistrict> checkNomenclature(int stateCode, long roleCode) throws Exception {
		// TODO checkNomenclature
		List<NomenclatureSubdistrict> nomenclatureSubdistrictList=null;
		nomenclatureSubdistrictList= new ArrayList<NomenclatureSubdistrict>();
		int slc=commonService.getSlc(stateCode);
		
		nomenclatureSubdistrictList=nomenclatureSubDistDAO.getNomenclatureDetails(slc);
		return nomenclatureSubdistrictList;
	}

	@Override
	public boolean modifyNomenclatureSudDist(
			NomenclatureSubDistForm nomenclatureSubDistForm, int stateCode, HttpSession httpsession) throws Exception {
		// TODO modifyNomenclatureSudDist
		List<NomenclatureSubdistrict> nomenclatureSubdistrictList=null;
		nomenclatureSubdistrictList= new ArrayList<NomenclatureSubdistrict>();
		int slc=commonService.getSlc(stateCode);
		/*int stateVersion=0;
		stateVersion=stateDao.getCurrentVersionbyStateCode(stateCode);*/
		nomenclatureSubdistrictList=nomenclatureSubDistDAO.getNomenclatureDetails(slc);
		
		int nsdCode=nomenclatureSubdistrictList.get(0).getNsdCode();
		int nsdVersion=nomenclatureSubdistrictList.get(0).getNsdVersion();
/*		int nsdCode = nomenclatureSubDistDAO.getMaxRecords("select max(nsd_code) from nomenclature_subdistrict");
		int nsdVersion=1;*/
		//StatePK statePK=null;
		//State state=null;
		Timestamp time = null;
		boolean temp=false;
		boolean temp1=false;
		boolean success=false;
		time = CurrentDateTime.getCurrentTimestamp();
		NomenclatureSubdistrictPK nomenclatureSubdistrictPK= null;
		
		
		Session session = null ;
		Transaction tx = null;

		session = sessionFactory.openSession();
		tx=session.beginTransaction();
		
		//inactivate current Nomenclature
		nomenclatureSubdistrictPK= new NomenclatureSubdistrictPK(nsdCode,nsdVersion);
		temp1=nomenclatureSubDistDAO.nomenclatureInactivate(nomenclatureSubdistrictPK ,session);
		
		//insert New Nomenclature
		NomenclatureSubdistrictPK nomenclatureSubdistrictPKNew=null;
		nomenclatureSubdistrictPKNew= new NomenclatureSubdistrictPK(nsdCode,nsdVersion+1);
		
		/*statePK= new StatePK(stateCode,stateVersion);
		state= new State();
		state.setStatePK(statePK);*/
		NomenclatureSubdistrict nomenclatureSubdistrict= null;
		nomenclatureSubdistrict=new NomenclatureSubdistrict();
		nomenclatureSubdistrict.setNomenclatureSubdistrictPK(nomenclatureSubdistrictPKNew);
		//nomenclatureSubdistrict.setState(state);
		nomenclatureSubdistrict.setNomenclatureEnglish(nomenclatureSubDistForm.getNameinEnglish());
		nomenclatureSubdistrict.setNomenclatureLocal(nomenclatureSubDistForm.getNameinlocalLanguage());
		nomenclatureSubdistrict.setIsactive(true);
		nomenclatureSubdistrict.setLastupdated(time);
		SessionObject sessionObject = (SessionObject) httpsession.getAttribute("sessionObject");
		Long userId = sessionObject.getUserId();
		nomenclatureSubdistrict.setLastupdatedby(userId);
		nomenclatureSubdistrict.setCreatedby(nomenclatureSubdistrictList.get(0).getCreatedby());
		nomenclatureSubdistrict.setCreatedon(nomenclatureSubdistrictList.get(0).getCreatedon());
		nomenclatureSubdistrict.setEffectiveDate(time);
		nomenclatureSubdistrict.setSlc(slc);
		if (nomenclatureSubDistForm.isBlockExist()) {
			nomenclatureSubdistrict.setIssubdistrictblocksame(nomenclatureSubDistForm.isNameofTheBlockisSameforTheState());
		}
		nomenclatureSubdistrict.setBlockExists(nomenclatureSubDistForm.isBlockExist());

		try {
				temp=nomenclatureSubDistDAO.addNomenclatureSudDist(nomenclatureSubdistrict,session);
				
				if(temp && temp1)
				{
					tx.commit();
					success=true;
				}
			} catch (Exception e) {
				
				tx.rollback();
				success=false;
			}
			finally
			{
				if (session != null && session.isOpen()) {
					session.close();
				}
			}
		
		return success;
	}
	
}
