package in.nic.pes.lgd.dao.impl;

import in.nic.pes.lgd.bean.Attachment;
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
import in.nic.pes.lgd.dao.ShiftDAO;
import in.nic.pes.lgd.forms.GenerateDetails;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.forms.ShiftBlockForm;
import in.nic.pes.lgd.forms.ShiftDistrictForm;
import in.nic.pes.lgd.forms.ShiftSubDistrictForm;
import in.nic.pes.lgd.forms.ShiftVillageForm;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pes.attachment.util.AttachedFilesForm;

@Repository
@Transactional
public class ShiftDAOImpl implements ShiftDAO {
	private static final Logger log = Logger.getLogger(ShiftDAO.class);

	@Autowired
	SessionFactory sessionFactory;

	public List<ShiftDistrict> shiftDistrict(int stateSourceCode, int stateTargetCode,String districtCode, int userId, GovernmentOrderForm govtOrderForm, Session session,String description) throws Exception
	{
		Query query = null;
		List<ShiftDistrict> getordercode = null;
		try
		{
			query = session.getNamedQuery("DistrictQuery")
					.setParameter("source_state_code", stateSourceCode,Hibernate.INTEGER)
					.setParameter("destination_state_code", stateTargetCode,Hibernate.INTEGER)
					.setParameter("district_list", districtCode,Hibernate.STRING)
					.setParameter("userid", userId, Hibernate.INTEGER)
					.setParameter("effective_date", govtOrderForm.getEffectiveDate(),Hibernate.TIMESTAMP)
					.setParameter("order_no",govtOrderForm.getOrderNo(),Hibernate.STRING)
					.setParameter("order_date",govtOrderForm.getOrderDate(),Hibernate.TIMESTAMP)
					.setParameter("gaz_pub_date",govtOrderForm.getGazPubDate(),Hibernate.TIMESTAMP)
					.setParameter("order_path","govtOrderUpload",Hibernate.STRING)
					.setParameter("description",description,Hibernate.STRING)
					.setParameter("entity_type","D",Hibernate.STRING);

			getordercode=query.list();
		}
		catch (Exception e) 
		{
			log.debug("Exception" + e);

		}
		return getordercode;

	}

	public void shiftDistrictReorganize(int stateSourceCode,
			int stateTargetCode, String districtCode, int userId,
			Date effectiveDate) throws Exception {
		Session session = null;
		Transaction txn = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			txn = session.beginTransaction();

			query = sessionFactory
					.getCurrentSession()
					.getNamedQuery("DistrictQuery")
					.setParameter("stateSourceCode", stateSourceCode,
							Hibernate.INTEGER)
					.setParameter("stateTargetCode", stateTargetCode,
							Hibernate.INTEGER)
					.setParameter("districtCode", districtCode,
							Hibernate.STRING)
					.setParameter("userId", userId, Hibernate.INTEGER)
					.setParameter("effectiveDate", new Date(),
							Hibernate.TIMESTAMP);

			txn.commit();

		} catch (Exception e) {
			log.debug("Exception" + e);
			if(txn != null){
				txn.rollback();
			}	
		}

		finally {
			if(session != null && session.isOpen())
			{	
				session.close();
			}	
		}
	}

	public List<ShiftSubDistrict> shiftSubDistrict(int districtSourceCode,int districtTargetCode, String subdistrictCode, int userId,GovernmentOrderForm govtOrderForm, Session session,String description) throws Exception 
	{
		Query query = null;
		List<ShiftSubDistrict> getordercode = null;
		try
		{
			query = session
					.getNamedQuery("ShiftSubDistrictQuery")
					.setParameter("source_district_code", districtSourceCode,Hibernate.INTEGER)
					.setParameter("destination_district_code", districtTargetCode,Hibernate.INTEGER)
					.setParameter("subdistrict_list", subdistrictCode,Hibernate.STRING)
					.setParameter("userid", userId, Hibernate.INTEGER)
					.setParameter("effective_date", govtOrderForm.getEffectiveDate(),Hibernate.TIMESTAMP)
					.setParameter("order_no", govtOrderForm.getOrderNo(),Hibernate.STRING)
					.setParameter("order_date",govtOrderForm.getOrderDate(),Hibernate.TIMESTAMP)
					.setParameter("gaz_pub_date",govtOrderForm.getGazPubDate(),Hibernate.TIMESTAMP)
					.setParameter("order_path","govtOrderUpload",Hibernate.STRING)
					.setParameter("description",description,Hibernate.STRING)
					.setParameter("entity_type","T",Hibernate.STRING);
				
					getordercode=query.list();
		} 
		catch (Exception e) 
		{
			log.debug("Exception" + e);
		}
		return getordercode;
	}

	public void shiftSubDistrictReorganize(int districtSourceCode,
			int districtTargetCode, String subdistrictCode, int userId,
			Date effectiveDate) throws Exception{
		Session session = null;
		Transaction txn = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			txn = session.beginTransaction();
		 
			query = session
					.getNamedQuery("ShiftSubDistrictQuery")
					.setParameter("districtSourceCode", districtSourceCode,
							Hibernate.INTEGER)
					.setParameter("districtTargetCode", districtTargetCode,
							Hibernate.INTEGER)
					.setParameter("subdistrictCode", subdistrictCode,
							Hibernate.STRING)
					.setParameter("userId", userId, Hibernate.INTEGER)
					.setParameter("effectiveDate", new Date(),
							Hibernate.TIMESTAMP);
			query.list();
			 
			txn.commit();

		} catch (Exception e) {
			log.debug("Exception" + e);
			if(txn != null){
				txn.rollback();
			}	
		}

		finally {
			if(session != null && session.isOpen())
			{	
				session.close();
			}	
		}
	}

	public void shiftSubDistrictSReorganize(int districtSourceCode,
			int districtTargetCode, String subdistrictCode, int userId,
			Date effectiveDate, int stateTargetCode) throws Exception{
		Session session = null;
		Transaction txn = null;
		Query query = null;

		try {
			session = sessionFactory.openSession();
			txn = session.beginTransaction();
		 
			query = session
					.getNamedQuery("ShiftSubDistrictQuery")
					.setParameter("districtSourceCode", districtSourceCode,
							Hibernate.INTEGER)
					.setParameter("districtTargetCode", districtTargetCode,
							Hibernate.INTEGER)
					.setParameter("subdistrictCode", subdistrictCode,
							Hibernate.STRING)
					.setParameter("userId", userId, Hibernate.INTEGER)
					.setParameter("effectiveDate", new Date(),
							Hibernate.TIMESTAMP)
					.setParameter("stateTargetCode", stateTargetCode,
							Hibernate.INTEGER);
			 
					  query.list(); 
			txn.commit();

		} catch (Exception e) {
			log.debug("Exception" + e);
			if(txn != null){
				txn.rollback();
			}	
		}

		finally {
			if(session != null && session.isOpen())
			{	
	            session.close();
			}    
		}
	}

	public List<ShiftVillageSD> shiftVillageforBlock(int blockSourceCode, int blockTargetCode, String villageCode, int userId,GovernmentOrderForm govtOrderForm, char level,Session session,String description) throws Exception
	{
		Query query = null;
		List<ShiftVillageSD> getordercode = null;
		try
		{
			query = session.getNamedQuery("VillageSubDistrictQuery")
					.setParameter("source_subdistrict_code",blockSourceCode, Hibernate.INTEGER)
					.setParameter("destination_subdistrict_code",blockTargetCode, Hibernate.INTEGER)
					.setParameter("village_list", villageCode, Hibernate.STRING)
					.setParameter("userid", userId, Hibernate.INTEGER)
					.setParameter("effective_date", govtOrderForm.getEffectiveDate(),Hibernate.TIMESTAMP)
					.setParameter("order_no",govtOrderForm.getOrderNo(),Hibernate.STRING)
					.setParameter("order_date",govtOrderForm.getOrderDate(),Hibernate.TIMESTAMP)
					.setParameter("gaz_pub_date",govtOrderForm.getGazPubDate(),Hibernate.TIMESTAMP)
					.setParameter("order_path","govtOrderUpload",Hibernate.STRING)
					.setParameter("description",description,Hibernate.STRING)
					.setParameter("entity_type","B",Hibernate.STRING);
					//query.list();
					getordercode=query.list();

		}
		catch (Exception e) 
		{
			log.debug("Exception" + e);
		}
		return getordercode;
	}

	public List<ShiftVillageSD> shiftVillageforSubdistrict(int subdistrictSourceCode, int subdistrictTargetCode, String villageCode, int userId, GovernmentOrderForm govtOrderForm, Session session,String description) throws Exception
	{
		Query query = null;
		int ordercode=0;
		List<ShiftVillageSD> getordercode = null;
		try
		{
			query = session.getNamedQuery("VillageSubDistrictQuery")
					.setParameter("source_subdistrict_code",subdistrictSourceCode, Hibernate.INTEGER)
					.setParameter("destination_subdistrict_code",subdistrictTargetCode, Hibernate.INTEGER)
					.setParameter("village_list", villageCode, Hibernate.STRING)
					.setParameter("userid", userId, Hibernate.INTEGER)
					.setParameter("effective_date", govtOrderForm.getEffectiveDate(),Hibernate.TIMESTAMP)
					.setParameter("order_no",govtOrderForm.getOrderNo(),Hibernate.STRING)
					.setParameter("order_date",govtOrderForm.getOrderDate(),Hibernate.TIMESTAMP)
					.setParameter("gaz_pub_date",govtOrderForm.getGazPubDate(),Hibernate.TIMESTAMP)
					.setParameter("order_path","govtOrderUpload",Hibernate.STRING)
					.setParameter("description",description,Hibernate.STRING)
					.setParameter("entity_type","V",Hibernate.STRING);
					//query.list();
					getordercode=query.list();
		}
		catch (Exception e) 
		{
			log.debug("Exception" + e);
		}
		return getordercode;
	}

	public void shiftVillageReorganize(int subdistrictSourceCode,
			int subdistrictTargetCode, String villageCode, int userId,
			Date effectiveDate) throws Exception {
		Session session = null;
		Transaction txn = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			txn = session.beginTransaction();
		 
			query = session
					.getNamedQuery("VillageSubDistrictQuery")
					.setParameter("subdistrictSourceCode",
							subdistrictSourceCode, Hibernate.INTEGER)
					.setParameter("subdistrictTargetCode",
							subdistrictTargetCode, Hibernate.INTEGER)
					.setParameter("villageCode", villageCode, Hibernate.STRING)
					.setParameter("userId", userId, Hibernate.INTEGER)
					.setParameter("effectiveDate", new Date(),
							Hibernate.TIMESTAMP);
		  query.list() ;
			txn.commit();

		} catch (Exception e) {
			log.debug("Exception" + e);
			if(txn != null){
				txn.rollback();
			}	
		}

		finally {
			if(session != null && session.isOpen())
			{	
	            session.close();
			}    
		}
	}

	@Override
	public List<ShiftBlock> shiftBlock(int districtSourceCode, int districtTargetCode, String blockCode, int userId,GovernmentOrderForm govtOrderForm, Date effectiveDate, Session session,String description) throws Exception 
	{
		Query query = null;
		List<ShiftBlock> getordercode = null;
		try
		{
			query = session.getNamedQuery("ShiftBlockQuery")
					.setParameter("districtSourceCode", districtSourceCode,	Hibernate.INTEGER)
					.setParameter("districtTargetCode", districtTargetCode,	Hibernate.INTEGER)
					.setParameter("blockCode", blockCode, Hibernate.STRING)
					.setParameter("userId", userId, Hibernate.INTEGER)
					.setParameter("effectiveDate", effectiveDate, Hibernate.TIMESTAMP)
					.setParameter("order_no",govtOrderForm.getOrderNo(),Hibernate.STRING)
					.setParameter("order_date",govtOrderForm.getOrderDate(),Hibernate.TIMESTAMP)
					.setParameter("gaz_pub_date",govtOrderForm.getGazPubDate(),Hibernate.TIMESTAMP)
					.setParameter("order_path","govtOrderUpload",Hibernate.STRING)
					.setParameter("description",description,Hibernate.STRING);
			
			getordercode=query.list();
		}
		catch (Exception e) 
		{
			log.debug("Exception" + e);
		}
		return getordercode;
	}
	
	@Override
	public boolean saveDataInAttachShiftBlock(GovernmentOrderForm governmentOrderForm,ShiftBlockForm shiftblockForm,List<AttachedFilesForm> attachedList, HttpSession session,int getordercode) 
	{
		Transaction tx1 = null;
		Session sessionObj = sessionFactory.openSession();
		tx1 = sessionObj.beginTransaction();
		Query query=null;
		Attachment attachment = null;
		GovernmentOrderEntityWise goe=null;
	    boolean flag = true;
		try {
			if (attachedList != null) {
				Iterator<AttachedFilesForm> it = attachedList.iterator();
				while (it.hasNext()) {
					AttachedFilesForm filesForm = (AttachedFilesForm) it.next();
					attachment = new Attachment();				
					attachment.setFileName(filesForm.getFileName());
					attachment.setFileSize(filesForm.getFileSize());
					attachment.setFileContentType(filesForm.getFileType());
					attachment.setFileLocation(filesForm.getFileLocation());
					attachment.setFileTimestamp(filesForm.getFileTimestampName());
					
					GovernmentOrder govorder= new GovernmentOrder();
					if(getordercode!=0)
					{
						govorder.setOrderCode(getordercode);
						attachment.setGovernmentOrder(govorder);
					}
					sessionObj.save(attachment);					
					tx1.commit();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
			tx1.rollback();
			flag =false;
		}
		finally {
			
			if (sessionObj != null && sessionObj.isOpen()){
				sessionObj.clear();
				sessionObj.close();
			}	
		}
		return flag;
	}
	
	@Override
	public boolean saveDataInAttachShiftVillageSD(GovernmentOrderForm governmentOrderForm,ShiftVillageForm shiftvillageForm,List<AttachedFilesForm> attachedList, HttpSession session,int getordercode) 
	{
		Transaction tx1 = null;
		Session sessionObj = sessionFactory.openSession();
		tx1 = sessionObj.beginTransaction();
		Query query=null;
		Attachment attachment = null;
		GovernmentOrderEntityWise goe=null;
		ShiftVillageSD orderno=null;
	    boolean flag = true;
		try {
			if (attachedList != null) {
				Iterator<AttachedFilesForm> it = attachedList.iterator();
				while (it.hasNext()) {
					AttachedFilesForm filesForm = (AttachedFilesForm) it.next();
					attachment = new Attachment();				
					attachment.setFileName(filesForm.getFileName());
					attachment.setFileSize(filesForm.getFileSize());
					attachment.setFileContentType(filesForm.getFileType());
					attachment.setFileLocation(filesForm.getFileLocation());
					attachment.setFileTimestamp(filesForm.getFileTimestampName());
					
					GovernmentOrder govorder= new GovernmentOrder();
					if(getordercode!=0)
					{
						govorder.setOrderCode(getordercode);
						attachment.setGovernmentOrder(govorder);
					}
					
					sessionObj.save(attachment);					
					tx1.commit();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
			tx1.rollback();
			flag =false;
		}
		finally {
			
			if (sessionObj != null && sessionObj.isOpen()){
				sessionObj.clear();
				sessionObj.close();
			}	
		}
		return flag;
	}
	
	@Override
	public boolean saveDataInAttachShiftGenerateVillageSD(GovernmentOrderForm governmentOrderForm,ShiftVillageForm shiftvillageForm, HttpSession session,int getordercode) 
	{
		Transaction tx1 = null;
		Session sessionObj = sessionFactory.openSession();
		tx1 = sessionObj.beginTransaction();
		Query query=null;
		boolean flag = true;
		Attachment attachment = null;
		GovernmentOrderEntityWise goe=null;
		ShiftVillageSD orderno=null;
		GenerateDetails genDetails=(GenerateDetails)session.getAttribute("validGovermentGenerateUpload");
		try
		{
			if(genDetails !=null)
			{
				attachment = new Attachment();	
				attachment.setFileName(genDetails.getFileName());
				attachment.setFileLocation(genDetails.getFileLocation());
				attachment.setFileTitle("");
				attachment.setFileTimestamp(genDetails.getFileTimestamp());
				attachment.setFileContentType(genDetails.getFileContentType());
				attachment.setFileSize(genDetails.getFileSize());
				//attachment.setFileSize();
				GovernmentOrder govorder= new GovernmentOrder();
				if(getordercode!=0)
				{
					govorder.setOrderCode(getordercode);
					attachment.setGovernmentOrder(govorder);
				}
				sessionObj.save(attachment);					
				tx1.commit();
			
			}
		}	
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
			tx1.rollback();
			flag =false;
		}
		finally {
			
			if (sessionObj != null && sessionObj.isOpen()){
				sessionObj.clear();
				sessionObj.close();
			}	
		}
		return flag;
	}
	
	@Override
	public boolean saveDataInAttachShiftGenerateDistrict(GovernmentOrderForm governmentOrderForm,ShiftDistrictForm shiftDistricForm, HttpSession session,int getordercode) 
	{
		Transaction tx1 = null;
		Session sessionObj = sessionFactory.openSession();
		tx1 = sessionObj.beginTransaction();
		Query query=null;
		boolean flag = true;
		Attachment attachment = null;
		GovernmentOrderEntityWise goe=null;
		ShiftVillageSD orderno=null;
		GenerateDetails genDetails=(GenerateDetails)session.getAttribute("validGovermentGenerateUpload");
		try
		{
			if(genDetails !=null)
			{
				attachment = new Attachment();	
				attachment.setFileName(genDetails.getFileName());
				attachment.setFileLocation(genDetails.getFileLocation());
				attachment.setFileTitle("");
				attachment.setFileTimestamp(genDetails.getFileTimestamp());
				attachment.setFileContentType(genDetails.getFileContentType());
				attachment.setFileSize(genDetails.getFileSize());
				//attachment.setFileSize();
				GovernmentOrder govorder= new GovernmentOrder();
				if(getordercode!=0)
				{
					govorder.setOrderCode(getordercode);
					attachment.setGovernmentOrder(govorder);
				}
				sessionObj.save(attachment);					
				tx1.commit();
			
			}
		}	
		catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
			tx1.rollback();
			flag =false;
		}
		finally {
			
			if (sessionObj != null && sessionObj.isOpen()){
				sessionObj.clear();
				sessionObj.close();
			}	
		}
		return flag;
	}
	
	@Override
	public boolean saveDataInAttachGenerateShiftBlock(GovernmentOrderForm governmentOrderForm,ShiftBlockForm shiftblockForm, HttpSession session,int getordercode) 
	{
		Transaction tx1 = null;
		Session sessionObj = sessionFactory.openSession();
		tx1 = sessionObj.beginTransaction();
		Query query=null;
		boolean flag = true;
		Attachment attachment = null;
		GovernmentOrderEntityWise goe=null;
		ShiftBlock orderno=null;
		GenerateDetails genDetails=(GenerateDetails)session.getAttribute("validGovermentGenerateUpload");
		try
		{
			if(genDetails !=null)
			{
				attachment = new Attachment();	
				attachment.setFileName(genDetails.getFileName());
				attachment.setFileLocation(genDetails.getFileLocation());
				attachment.setFileTitle("");
				attachment.setFileTimestamp(genDetails.getFileTimestamp());
				attachment.setFileContentType(genDetails.getFileContentType());
				attachment.setFileSize(genDetails.getFileSize());
				//attachment.setFileSize();
				GovernmentOrder govorder= new GovernmentOrder();
				if(getordercode!=0)
				{
					govorder.setOrderCode(getordercode);
					attachment.setGovernmentOrder(govorder);
				}
				sessionObj.save(attachment);					
				tx1.commit();
			
			}
		}	
		catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
			tx1.rollback();
			flag =false;
		}
		finally {
			
			if (sessionObj != null && sessionObj.isOpen()){
				sessionObj.clear();
				sessionObj.close();
			}	
		}
		return flag;
	}
	
	
	@Override
	public boolean saveDataInAttachShiftGenerateSubDistrict(GovernmentOrderForm governmentOrderForm,ShiftSubDistrictForm shiftSubDistrictForm, HttpSession session,int getordercode) 
	{
		Transaction tx1 = null;
		Session sessionObj = sessionFactory.openSession();
		tx1 = sessionObj.beginTransaction();
		Query query=null;
		boolean flag = true;
		Attachment attachment = null;
		GovernmentOrderEntityWise goe=null;
		ShiftVillageSD orderno=null;
		GenerateDetails genDetails=(GenerateDetails)session.getAttribute("validGovermentGenerateUpload");
		try
		{
			if(genDetails !=null)
			{
				attachment = new Attachment();	
				attachment.setFileName(genDetails.getFileName());
				attachment.setFileLocation(genDetails.getFileLocation());
				attachment.setFileTitle("");
				attachment.setFileTimestamp(genDetails.getFileTimestamp());
				attachment.setFileContentType(genDetails.getFileContentType());
				attachment.setFileSize(genDetails.getFileSize());
				//attachment.setFileSize();
				GovernmentOrder govorder= new GovernmentOrder();
				if(getordercode!=0)
				{
					govorder.setOrderCode(getordercode);
					attachment.setGovernmentOrder(govorder);
				}
				sessionObj.save(attachment);					
				tx1.commit();
			
			}
		}	
		catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
			tx1.rollback();
			flag =false;
		}
		finally {
			
			if (sessionObj != null && sessionObj.isOpen()){
				sessionObj.clear();
				sessionObj.close();
			}	
		}
		return flag;
	}
	
	@Override
	public boolean saveDataInAttachShiftVillageBlock(GovernmentOrderForm governmentOrderForm,ShiftVillageForm shiftvillageForm,List<AttachedFilesForm> attachedList, HttpSession session,int getordercode) 
	{
		Transaction tx1 = null;
		Session sessionObj = sessionFactory.openSession();
		tx1 = sessionObj.beginTransaction();
		Query query=null;
		Attachment attachment = null;
		GovernmentOrderEntityWise goe=null;
	    boolean flag = true;
		try {
			if (attachedList != null) {
				Iterator<AttachedFilesForm> it = attachedList.iterator();
				while (it.hasNext()) {
					AttachedFilesForm filesForm = (AttachedFilesForm) it.next();
					attachment = new Attachment();				
					attachment.setFileName(filesForm.getFileName());
					attachment.setFileSize(filesForm.getFileSize());
					attachment.setFileContentType(filesForm.getFileType());
					attachment.setFileLocation(filesForm.getFileLocation());
					attachment.setFileTimestamp(filesForm.getFileTimestampName());
					GovernmentOrder govorder= new GovernmentOrder();
					if(getordercode!=0)
					{
						govorder.setOrderCode(getordercode);
						attachment.setGovernmentOrder(govorder);
					}
					
					sessionObj.save(attachment);					
					tx1.commit();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
			tx1.rollback();
			flag =false;
		}
		finally {
			
			if (sessionObj != null && sessionObj.isOpen()){
				sessionObj.clear();
				sessionObj.close();
			}	
		}
		return flag;
	}
	
	@Override
	public boolean saveDataInAttachShiftDistrict(GovernmentOrderForm governmentOrderForm,ShiftDistrictForm shiftDistrictForm,List<AttachedFilesForm> attachedList, HttpSession session,int getordercode) 
	{
		Transaction tx1 = null;
		Session sessionObj = sessionFactory.openSession();
		tx1 = sessionObj.beginTransaction();
		Query query=null;
		Attachment attachment = null;
		GovernmentOrderEntityWise goe=null;
		ShiftVillageSD orderno=null;
	    boolean flag = true;
		try {
			if (attachedList != null) {
				Iterator<AttachedFilesForm> it = attachedList.iterator();
				while (it.hasNext()) {
					AttachedFilesForm filesForm = (AttachedFilesForm) it.next();
					attachment = new Attachment();				
					attachment.setFileName(filesForm.getFileName());
					attachment.setFileSize(filesForm.getFileSize());
					attachment.setFileContentType(filesForm.getFileType());
					attachment.setFileLocation(filesForm.getFileLocation());
					attachment.setFileTimestamp(filesForm.getFileTimestampName());
					GovernmentOrder govorder= new GovernmentOrder();
					if(getordercode!=0)
					{
						govorder.setOrderCode(getordercode);
						attachment.setGovernmentOrder(govorder);
					}
					
					sessionObj.save(attachment);					
					tx1.commit();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
			tx1.rollback();
			flag =false;
		}
		finally {
			
			if (sessionObj != null && sessionObj.isOpen()){
				sessionObj.clear();
				sessionObj.close();
			}	
		}
		return flag;
	}
	
	@Override
	public boolean saveDataInAttachShiftSubDistrict(GovernmentOrderForm governmentOrderForm,ShiftSubDistrictForm shiftSubDistrictForm,List<AttachedFilesForm> attachedList, HttpSession session,int getordercode) 
	{
		Transaction tx1 = null;
		Session sessionObj = sessionFactory.openSession();
		tx1 = sessionObj.beginTransaction();
		Query query=null;
		Attachment attachment = null;
		GovernmentOrderEntityWise goe=null;
		ShiftVillageSD orderno=null;
	    boolean flag = true;
		try {
			if (attachedList != null) {
				Iterator<AttachedFilesForm> it = attachedList.iterator();
				while (it.hasNext()) {
					AttachedFilesForm filesForm = (AttachedFilesForm) it.next();
					attachment = new Attachment();				
					attachment.setFileName(filesForm.getFileName());
					attachment.setFileSize(filesForm.getFileSize());
					attachment.setFileContentType(filesForm.getFileType());
					attachment.setFileLocation(filesForm.getFileLocation());
					attachment.setFileTimestamp(filesForm.getFileTimestampName());
					
					GovernmentOrder govorder= new GovernmentOrder();
					if(getordercode!=0)
					{
						govorder.setOrderCode(getordercode);
						attachment.setGovernmentOrder(govorder);
					}
					
					sessionObj.save(attachment);					
					tx1.commit();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
			tx1.rollback();
			flag =false;
		}
		finally {
			
			if (sessionObj != null && sessionObj.isOpen()){
				sessionObj.clear();
				sessionObj.close();
			}	
		}
		return flag;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<State> getStateNamebystateID(int stateCode) throws Exception 
	{
		Session session = null;
		List<State> statenameList = null;
		Query criteria = null;
		try
		{
			session = sessionFactory.openSession();
			criteria = session.createQuery("from State d where d.statePK.stateCode in(:stateCode) and isactive=true");
			criteria.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			statenameList = criteria.list();
		}
		catch (Exception e) 
		{
			log.debug("Exception" + e);
		}
		finally 
		{
			if (session != null && session.isOpen()){
				session.close();
			}	
		}

		return statenameList;

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<District> getdistrictNamebyDistID(int distCode) throws Exception 
	{
		Session session = null;
		List<District> districtnameList = null;
		Query criteria = null;
		try
		{
			session = sessionFactory.openSession();
			criteria = session.createQuery("from District d where d.districtPK.districtCode in(:districtCode) and isactive=true");
			criteria.setParameter("districtCode", distCode, Hibernate.INTEGER);
			districtnameList = criteria.list();
		}
		catch (Exception e) 
		{
			log.debug("Exception" + e);
		}
		finally 
		{
			if (session != null && session.isOpen()){
				session.close();
			}	
		}

		return districtnameList;

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Subdistrict> getsubdistrictNamebysubDistID(int subdistCode) throws Exception 
	{
		Session session = null;
		List<Subdistrict> subdistrictnameList = null;
		Query criteria = null;
		try
		{
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Subdistrict sd where sd.subdistrictPK.subdistrictCode in(:subdistrictCode) and isactive=true");
			criteria.setParameter("subdistrictCode", subdistCode, Hibernate.INTEGER);
			subdistrictnameList = criteria.list();
		}
		catch (Exception e) 
		{
			log.debug("Exception" + e);
		}
		finally 
		{
			if (session != null && session.isOpen()){
				session.close();
			}	
		}

		return subdistrictnameList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Village> getVillageNamebyVillageID(int villageCode) throws Exception 
	{
		Session session = null;
		List<Village> villagenameList = null;
		Query criteria = null;
		try
		{
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Village v where v.villagePK.villageCode in(:villageCode) and isactive=true");
			criteria.setParameter("villageCode", villageCode, Hibernate.INTEGER);
			villagenameList = criteria.list();
		}
		catch (Exception e) 
		{
			log.debug("Exception" + e);
		}
		finally 
		{
			if (session != null && session.isOpen()){
				session.close();
			}	
		}

		return villagenameList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Block> getblockNamebyBlockID(int blockCode) throws Exception 
	{
		Session session = null;
		List<Block> blocknameList = null;
		Query criteria = null;
		try
		{
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Block b where b.blockPK.blockCode in(:blockCode) and isactive=true");
			criteria.setParameter("blockCode", blockCode, Hibernate.INTEGER);
			blocknameList = criteria.list();
		}
		catch (Exception e) 
		{
			log.debug("Exception" + e);
		}
		finally 
		{
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return blocknameList;
	}
}
