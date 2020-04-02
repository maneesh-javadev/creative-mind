package in.nic.pes.lgd.service.impl;

import in.nic.pes.lgd.bean.ConstituencyCoverage;
import in.nic.pes.lgd.bean.ConstituencyCoverageEntity;
import in.nic.pes.lgd.bean.DeptAdminUnit;
import in.nic.pes.lgd.bean.MapAttachment;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.bean.Subdistrict;
import in.nic.pes.lgd.common.ReleaseResources;
import in.nic.pes.lgd.dao.DistrictDAO;
import in.nic.pes.lgd.dao.MapDao;
import in.nic.pes.lgd.dao.ParliamentDAO;
import in.nic.pes.lgd.draft.constant.DraftConstant;
import in.nic.pes.lgd.draft.entities.DraftMaster;
import in.nic.pes.lgd.draft.entities.GovermentDetailDraft;
import in.nic.pes.lgd.draft.entities.SubdistrictDraft;
import in.nic.pes.lgd.draft.form.DraftSubdistrictForm;
import in.nic.pes.lgd.forms.LocalGovtBodyForm;
import in.nic.pes.lgd.service.MapService;
import in.nic.pes.lgd.utils.DatePicker;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MapServiceImpl implements MapService {
	
	private static final Logger log = Logger.getLogger(MapServiceImpl.class);
	@Autowired
	ParliamentDAO parliamentDAO;

	@Autowired
	DistrictDAO districtDAO;

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	MapDao mapDao;

	Date timestamp = new Date();
	int pcCode;
	int pcVersion;
	int mapCd;

	@Override
	@Transactional
	public boolean mapParliament(LocalGovtBodyForm localGovtBodyForm,
			HttpSession httpSession) throws Exception {
		
		SessionObject sessionObject = (SessionObject) httpSession.getAttribute("sessionObject");
        Integer userId = sessionObject.getUserId()!=null?sessionObject.getUserId().intValue():null;
		int concoverage = parliamentDAO
				.getMaxRecords("select COALESCE(max(cc_code),1) from constituency_coverage");
		/*int concoverageentity = parliamentDAO
				.getMaxRecords("select COALESCE(max(cce_code),1) from constituency_covered_entity");*/
		int ParliamentcoverageExist = 0;
		int assemblycode = 0;
		int parliamnetcode = 0;
		Session session = null;
		Transaction tx = null;	
		int cccode = 0;
		try {
			String parlimentConsCode = localGovtBodyForm
					.getContributedParliament();
			String assemblyConsCode = localGovtBodyForm
					.getContributedAssembly();
			assemblycode = Integer.parseInt(assemblyConsCode);
			parliamnetcode = Integer.parseInt(parlimentConsCode);

			if (assemblycode > 0)
				ParliamentcoverageExist = parliamentDAO
						.ParConstituencyCoverageExist(assemblycode, 'A',
								session);
			else
				ParliamentcoverageExist = parliamentDAO
						.ParConstituencyCoverageExist(parliamnetcode, 'P',
								session);
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			if (ParliamentcoverageExist == 0) {
				if (assemblycode > 0) {
					this.saveconstituencycoverage(concoverage,
							localGovtBodyForm.getContributedAssembly(),
							pcVersion, session, userId, 'A');
					cccode = concoverage;
				} else {
					this.saveconstituencycoverage(concoverage,
							localGovtBodyForm.getContributedParliament(),
							pcVersion, session, userId, 'P');
					cccode = concoverage;
				}

			} else
				cccode = ParliamentcoverageExist;
			int success = parliamentDAO.mapPaliamentLBData(cccode,localGovtBodyForm.getChoosenlb(),localGovtBodyForm.getListformat(),localGovtBodyForm.getWard_number(),session);
			tx.commit();
			if(success ==1)
			return true;
			else 
				return false;
		} catch (HibernateException e) {
			log.debug("Exception" + e);
			tx.rollback();
			return false;
		} finally {
			ReleaseResources.doReleaseResources(session);
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public static String removePart(String value) {
		if (value.contains(",") && value.contains("FULL")) {
			String[] temp = value.split(",");
			StringBuffer valueBuffer = new StringBuffer();
			for (int i = 0; i < temp.length; i++) {
				if (temp[i].contains("FULL")) {
					valueBuffer.append(temp[i].replace("FULL", ""));
					if (i < temp.length){
						valueBuffer.append(",");
					}	
				}
			}
			return (valueBuffer.substring(0, valueBuffer.length() - 1))
					.toString();
		} else if (value.contains("FULL")){
			return value.replace("FULL", "");
		}	
		return null;

	}

	/*private boolean saveconstituencycoverageentity(int id, int ccCode,
			int entityLc, int entity_Version, int entitytype,
			char coveragetype, Session session, Integer userId)
			throws Exception {
		char etype = (char)entitytype;
		ConstituencyCoverageEntity constituencyCoverageobj = null;
		constituencyCoverageobj = new ConstituencyCoverageEntity();

		constituencyCoverageobj.setId(id);
		constituencyCoverageobj.setCccode(ccCode);
		constituencyCoverageobj.setEntityLc(entityLc);
		// constituencyCoverageobj.setEntityversion(entity_Version);
		constituencyCoverageobj.setEntitytype(etype);
		constituencyCoverageobj.setCoveragetype(coveragetype);
		constituencyCoverageobj.setIsactive(true);

		parliamentDAO.save(constituencyCoverageobj, session);

		// TODO Auto-generated method stub
		return true;
	}*/

	private boolean saveconstituencycoverage(int constitutecoverage,
			String concoverage, int pcVersion2, Session session,
			Integer userId, char constituencytype) throws Exception {
		
		ConstituencyCoverage constituencyCoverageobj = null;
		constituencyCoverageobj = new ConstituencyCoverage();
		constituencyCoverageobj.setCcCode(constitutecoverage);
		constituencyCoverageobj
				.setConstituencyLc(Integer.parseInt(concoverage));
		constituencyCoverageobj.setConstituencyType(constituencytype);
		// constituencyCoverageobj.setConstituencyVersion(pcVersion2);
		constituencyCoverageobj.setLastupdated(timestamp);
		constituencyCoverageobj.setLastupdatedby(userId);
		constituencyCoverageobj.setCreatedby(userId);
		constituencyCoverageobj.setCreatedon(timestamp);
		constituencyCoverageobj.setIsactive(true);
		parliamentDAO.saveconstituency(constituencyCoverageobj, session);
		/*
		 * session.flush(); session.refresh(constituencyCoverageobj);
		 */
		// TODO Auto-generated method stub
		return true;
	}

	/*
	 * private boolean saveconstituencycoverageentitypartdetail(int id, int
	 * ids,int entitycode,int entityversion,char entitytype,char coverage ,
	 * Session session) throws Exception { ConstituencyCoverageEntitypartdetails
	 * constituencyCoveragepartobj=null; constituencyCoveragepartobj=new
	 * ConstituencyCoverageEntitypartdetails();
	 * 
	 * constituencyCoveragepartobj.setId(id);
	 * constituencyCoveragepartobj.setCccode(ids);
	 * constituencyCoveragepartobj.setEntitycode(entitycode);
	 * constituencyCoveragepartobj.setEntityversion(entityversion);
	 * constituencyCoveragepartobj.setEntitytype(entitytype);
	 * constituencyCoveragepartobj.setCoveragetype(coverage);
	 * constituencyCoveragepartobj.setIsactive(true);
	 * 
	 * parliamentDAO.saveconstituencypartdetail(constituencyCoveragepartobj,
	 * session); // TODO Auto-generated method stub return true; }
	 */

	public MapAttachment getUploadFileConfigurationDetails() throws Exception {
		Session session = null;
		MapAttachment mapAttachment = new MapAttachment();
		try {
			session = sessionFactory.openSession();
			List<MapAttachment> fileMapList = mapDao
					.getUploadFileConfigurationDetails(session);
			Iterator<MapAttachment> iterator = fileMapList.iterator();
			while (iterator.hasNext()) {
				MapAttachment master = (MapAttachment) iterator.next();
				mapAttachment.setFileLocation(master.getFileLocation());
				mapAttachment.setAttachmentId(master.getAttachmentId());
				mapAttachment.setFileLocation(master.getFileContentType());
				mapAttachment.setFileName(master.getFileName());
				mapAttachment.setFileSize(master.getFileSize());
				mapAttachment.setMapEntityType(master.getMapEntityType());
				mapAttachment.setMapEntityCode(master.getMapEntityCode());
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return mapAttachment;
	}

	/**
	 * The {@code mapConfigurations} in Service Implementation Layer, Map Configuration related to GIS Server and 
	 * Other server configured in database
	 * @param level
	 * @param localGovBodyType
	 * @param vpFlag
	 * @param vpState
	 * @return
	 * @author vinay yadav
	 */
	@Override
	public List<Object[]> mapConfigurations(Integer level, String localGovBodyType, String vpFlag, Integer vpState) throws Exception{
		// TODO Auto-generated method stub
		return mapDao.mapConfigurations(level, localGovBodyType, vpFlag, vpState);
	}
	
	/**
	 * @author Sourabh Rai
	 */
	public boolean savenUpdateConstituencyCoverage(LocalGovtBodyForm localGovtBodyForm) throws Exception{
		boolean flag=false;
		Session session=null;
		Transaction transaction = null;
		try{
			session=sessionFactory.openSession();
			transaction=session.beginTransaction();
			Integer ccCode=localGovtBodyForm.getCcCodeMap();
			Integer userId = localGovtBodyForm.getUserId();
			
			if(localGovtBodyForm.getCcCodeMap()==null){
				ccCode = saveConstituencyCoverage(localGovtBodyForm,userId,ccCode);
			}
			String deleteRow=localGovtBodyForm.getDeleteMap();
			if(deleteRow!=null && deleteRow!="") {
				handelDeleteContituencyCoveredEntity(deleteRow,userId);
			}
			
			String lbFull=localGovtBodyForm.getLbFullMap();
			if(lbFull!=null && lbFull!="") {
				performOperationForFullLb(lbFull,ccCode,userId);
			}
			
			String lbPart = localGovtBodyForm.getLbPartMap();
			if(lbPart!=null && lbPart!="") {
				performOperationForPartLb(lbPart,ccCode,userId);
			}

			/*String village = localGovtBodyForm.getVillageMap();
			if(village!=null && village!="") {
				performOperationForVillageLb(localGovtBodyForm.getVillageMap(),ccCode,userId);
			}*/
			
			String ward = localGovtBodyForm.getWardMap();
			if(ward!=null && ward!="") {
				performOperationForWard(localGovtBodyForm.getWardMap(),ccCode,userId);
			}
			
			transaction.commit();
			flag=true;
		}catch(Exception e){
			transaction.rollback();
			e.printStackTrace();
			throw e;
			
		}finally{
			if
			(session!=null && session.isOpen()){
				session.close();
			}
		}
		return flag;
	}
	
	private void handelDeleteContituencyCoveredEntity(final String deleteRow, final Integer userId) {
		Session session=null;
		try {
			if (deleteRow!=null && deleteRow!="") {
				session=sessionFactory.openSession();
				Scanner scanner = new Scanner(deleteRow);
				scanner.useDelimiter(",");
				ConstituencyCoverageEntity constituencyCoverageEntity=null;
				while (scanner.hasNext()) {
					String delArr[]=scanner.next().split("_");
					
					Criteria criteria = session.createCriteria(ConstituencyCoverageEntity.class);
					criteria.add( Restrictions.eq("isactive", Boolean.TRUE));
					criteria.add( Restrictions.eq("entityLc",Integer.parseInt(delArr[0]) ));
					criteria.add( Restrictions.eq("entitytype",delArr[1] ));
					
					
					/*StringBuilder stringBuilder = new StringBuilder(" update constituency_covered_entity_new set isactive=:inactiveCode, unmapped_on=:unmappedOn , ")
							.append("unmapped_by=:unmappedBy where isactive and entity_lc=:linkCode and entity_type=:type ");
					
					List<ConstituencyCoverageEntity> constituencyCoverageEntityList = new ArrayList<>();
					Query query = session.createSQLQuery(stringBuilder.toString());
					query.setParameter("inactiveCode", Boolean.FALSE, Hibernate.BOOLEAN);
					query.setParameter("unmappedOn", new Date(), Hibernate.DATE);
					query.setParameter("unmappedBy", userId, Hibernate.INTEGER);
					query.setParameter("linkCode", Integer.parseInt(delArr[0]), Hibernate.INTEGER);
					query.setParameter("type", delArr[1], Hibernate.STRING);
					query.executeUpdate();*/
					
					
					List<ConstituencyCoverageEntity> constituencyCoverageEntityList = criteria.list();
					if(constituencyCoverageEntityList!=null && !constituencyCoverageEntityList.isEmpty()){
						constituencyCoverageEntity=constituencyCoverageEntityList.get(0);
						
						StringBuilder stringBuilder = new StringBuilder(" update constituency_covered_entity_new set isactive=:inactiveCode, unmapped_on=:unmappedOn , ")
								.append("unmapped_by=:unmappedBy where cce_code=:cceCode and entity_type=:type ");
						Query query = session.createSQLQuery(stringBuilder.toString());
						query.setParameter("inactiveCode", Boolean.FALSE);
						query.setParameter("unmappedOn", new Date());
						query.setParameter("unmappedBy", userId);
						query.setParameter("type", constituencyCoverageEntity.getEntitytype());
						query.setParameter("cceCode", constituencyCoverageEntity.getId());
						int updatedResults = query.executeUpdate();
						System.out.println(updatedResults);
						session.flush();
						/*if(constituencyCoverageEntity !=null) {
							constituencyCoverageEntity.setIsactive(Boolean.FALSE);
							constituencyCoverageEntity.setUnMappedOn(new Date());
							constituencyCoverageEntity.setUnMappedBy(Long.parseLong(userId.toString()));
							session.update(constituencyCoverageEntity);
							session.flush();
							transaction.commit();
						}*/
					}
					
					
				}
				scanner.close();
			}
		}catch(final Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
	}
	
	private Integer saveConstituencyCoverage(final LocalGovtBodyForm localGovtBodyForm, final Integer userId,Integer ccCode) {
		Session session=null;
		Transaction transaction = null;
		try {
			Integer returnccCode = null;
			
				session=sessionFactory.openSession();
				transaction=session.beginTransaction();
				ConstituencyCoverage constituencyCoverage=new ConstituencyCoverage();
				constituencyCoverage.setConstituencyLc(Integer.parseInt(localGovtBodyForm.getContributedAssembly()));
				constituencyCoverage.setConstituencyType('A');
				constituencyCoverage.setLastupdated(timestamp);
				constituencyCoverage.setLastupdatedby(userId);
				constituencyCoverage.setCreatedby(userId);
				constituencyCoverage.setCreatedon(timestamp);
				constituencyCoverage.setIsactive(true);
				session.saveOrUpdate(constituencyCoverage);
				session.flush();
				transaction.commit();
				returnccCode=constituencyCoverage.getCcCode();
			/*else{
				constituencyCoverage.setCcCode(localGovtBodyForm.getCcCodeMap());
				session.saveOrUpdate(constituencyCoverage);
				session.flush();
				transaction.commit();
				localGovtBodyForm.setCcCodeMap(constituencyCoverage.getCcCode());
				saveConstituencyCoverageEntity(localGovtBodyForm);
				returnccCode= localGovtBodyForm.getCcCodeMap();
			}*/
			return returnccCode;
		}catch(final Exception e) {
			transaction.rollback();
			e.printStackTrace();
			throw e;
		}finally {
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
	}
	
	private void saveConstituencyCoverageEntity(final LocalGovtBodyForm localGovtBodyForm) {
		Session session=null;
		Transaction transaction = null;
		try {
			session=sessionFactory.openSession();
			transaction=session.beginTransaction();
			Integer ccCode=localGovtBodyForm.getCcCodeMap();
			Criteria criteria = session.createCriteria(ConstituencyCoverageEntity.class);
			criteria.add( Restrictions.eq("isactive", Boolean.TRUE));
			criteria.add( Restrictions.eq("cccode",ccCode));
			
			List<ConstituencyCoverageEntity> constituencyCoverageEntityList = criteria.list();
			for(ConstituencyCoverageEntity constituencyCoverageEntity:constituencyCoverageEntityList){
				constituencyCoverageEntity.setIsactive(Boolean.FALSE);
				constituencyCoverageEntity.setUnMappedOn(new Date());
				session.update(constituencyCoverageEntity);
				session.flush();
			}
			transaction.commit();
		}catch(final Exception e) {
			transaction.rollback();
			e.printStackTrace();
			throw e;
		}finally {
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
	}
	
	private void performOperationForFullLb(final String lbFull, final Integer ccCode, final Integer userId) {
		Session session=null;
		Transaction transaction = null;
			try {
				
				if (lbFull!=null && lbFull!="") {
					session=sessionFactory.openSession();
					transaction=session.beginTransaction();
					Scanner scanner = new Scanner(lbFull);
					scanner.useDelimiter(",");
					ConstituencyCoverageEntity constituencyCoverageEntity=null;
					while (scanner.hasNext()) {
						
							constituencyCoverageEntity=new ConstituencyCoverageEntity();
							constituencyCoverageEntity.setCccode(ccCode);
							String lbCodeAndType = scanner.next();
							String[] codeAndTypeArray = lbCodeAndType.split("_");
							Integer lbCode=Integer.parseInt(codeAndTypeArray[0]);
							
			//				String lbType = session.createSQLQuery("select local_body_type_code from localbody  where local_body_code =:lbCode and isactive ").setParameter("lbCode", lbCode, Hibernate.INTEGER).list().get(0).toString();
							
							Criteria criteria = session.createCriteria(ConstituencyCoverageEntity.class);
							criteria.add( Restrictions.eq("isactive", Boolean.TRUE));
							criteria.add( Restrictions.eq("entityLc",lbCode ));
							criteria.add( Restrictions.eq("cccode",ccCode ));
							criteria.add( Restrictions.eq("coveragetype",'F'));
							List<ConstituencyCoverageEntity> constituencyCoverageEntityList = criteria.list();
							
							if(CollectionUtils.isEmpty(constituencyCoverageEntityList)) {
								String entityType = null;	
								if(codeAndTypeArray.length >= 2) {
										entityType = codeAndTypeArray[1];
									}else {
										entityType = session.createSQLQuery("select local_body_type_code from localbody  where local_body_code =:lbCode and isactive ").setParameter("lbCode", lbCode, Hibernate.INTEGER).list().get(0).toString();
									}
									constituencyCoverageEntity.setCoveragetype('F');
									constituencyCoverageEntity.setEntitytype(entityType.toCharArray()[0]);
									constituencyCoverageEntity.setEntityLc(lbCode);
									constituencyCoverageEntity.setIsactive(Boolean.TRUE);
									constituencyCoverageEntity.setMappedBy(Long.parseLong(userId+""));
									constituencyCoverageEntity.setMappedOn(new Date());
									session.saveOrUpdate(constituencyCoverageEntity);
									session.flush();
							}
						
					}
					scanner.close();
					transaction.commit();
				}
			}catch(final Exception e) {
				transaction.rollback();
				e.printStackTrace();
				throw e;
			}finally {
				if(session!=null && session.isOpen()){
					session.close();
				}
			}
	}

	private void performOperationForPartLb(final String lbPart, final Integer ccCode, final Integer userId) {
		Session session=null;
		Transaction transaction = null;
		try {
			if (lbPart!=null && lbPart!="") {
				session=sessionFactory.openSession();
				transaction=session.beginTransaction();
				Scanner scanner = new Scanner(lbPart);
				scanner.useDelimiter(",");
				ConstituencyCoverageEntity constituencyCoverageEntity=null;
				while (scanner.hasNext()) {
					constituencyCoverageEntity=new ConstituencyCoverageEntity();
					constituencyCoverageEntity.setCccode(ccCode);
					Integer lbCode=Integer.parseInt(scanner.next());
					String localbodyType = session.createSQLQuery("select local_body_type_code from localbody  where local_body_code =:lbCode and isactive ").setParameter("lbCode", lbCode, Hibernate.INTEGER).list().get(0).toString();
					constituencyCoverageEntity.setCoveragetype('P');
					constituencyCoverageEntity.setEntitytype(localbodyType.charAt(0));
					constituencyCoverageEntity.setEntityLc(lbCode);
					constituencyCoverageEntity.setIsactive(Boolean.TRUE);
					constituencyCoverageEntity.setMappedBy(Long.parseLong(userId+""));
					constituencyCoverageEntity.setMappedOn(new Date());
					session.saveOrUpdate(constituencyCoverageEntity);
					session.flush();
				}
				scanner.close();
				transaction.commit();
			}
		}catch(final Exception e) {
			transaction.rollback();
			e.printStackTrace();
			throw e;
		}finally {
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
	}

	private void performOperationForVillageLb(final String village , final Integer ccCode, final Integer userId) {
		Session session=null;
		Transaction transaction = null;
		try {
			if (village!=null && village!="") {
				session=sessionFactory.openSession();
				transaction=session.beginTransaction();
				Scanner scanner = new Scanner(village);
				scanner.useDelimiter(",");
				ConstituencyCoverageEntity constituencyCoverageEntity=null;
				while (scanner.hasNext()) {
					constituencyCoverageEntity=new ConstituencyCoverageEntity();
					constituencyCoverageEntity.setCccode(ccCode);
					Integer lbCode=Integer.parseInt(scanner.next());
					constituencyCoverageEntity.setCoveragetype('F');
					constituencyCoverageEntity.setEntitytype('V');
					constituencyCoverageEntity.setEntityLc(lbCode);
					constituencyCoverageEntity.setIsactive(Boolean.TRUE);
					constituencyCoverageEntity.setMappedBy(Long.parseLong(userId+""));
					constituencyCoverageEntity.setMappedOn(new Date());
					session.save(constituencyCoverageEntity);
					session.flush();
				}
				scanner.close();
				transaction.commit();
			}
		}catch(final Exception e) {
			transaction.rollback();
			e.printStackTrace();
			throw e;
		}finally {
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
	}

	private void performOperationForWard(final String ward , final Integer ccCode, final Integer userId) {
		Session session=null;
		Transaction transaction = null;
		try {
			if (ward!=null && ward!="") {
				session=sessionFactory.openSession();
				transaction=session.beginTransaction();
				Scanner scanner = new Scanner(ward);
				scanner.useDelimiter(",");
				ConstituencyCoverageEntity constituencyCoverageEntity=null;
				while (scanner.hasNext()) {
					constituencyCoverageEntity=new ConstituencyCoverageEntity();
					constituencyCoverageEntity.setCccode(ccCode);
					Integer lbCode=Integer.parseInt(scanner.next());
					constituencyCoverageEntity.setCoveragetype('F');
					constituencyCoverageEntity.setEntitytype('W');
					constituencyCoverageEntity.setEntityLc(lbCode);
					constituencyCoverageEntity.setIsactive(Boolean.TRUE);
					constituencyCoverageEntity.setMappedBy(Long.parseLong(userId+""));
					constituencyCoverageEntity.setMappedOn(new Date());
					
					Criteria criteria = session.createCriteria(ConstituencyCoverageEntity.class);
					criteria.add( Restrictions.eq("isactive", Boolean.TRUE));
					criteria.add( Restrictions.eq("entityLc",lbCode ));
					criteria.add( Restrictions.eq("cccode",ccCode ));
					criteria.add( Restrictions.eq("entitytype",'W'));
					List<ConstituencyCoverageEntity> constituencyCoverageEntityList = criteria.list();
					
					if(CollectionUtils.isEmpty(constituencyCoverageEntityList)) {
						session.saveOrUpdate(constituencyCoverageEntity);
						session.flush();
					}
				}
				scanner.close();
				transaction.commit();
			}
		}catch(final Exception e) {
			transaction.rollback();
			e.printStackTrace();
			throw e;
		}finally {
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
	}
	
	public LocalGovtBodyForm loadConstituencyCoverage(LocalGovtBodyForm localGovtBodyForm) throws Exception{
		 Session session=null;
		 LocalGovtBodyForm alocalGovtBodyForm=null;
		 List<ConstituencyCoverageEntity> constituencyCoverageEntityList=null;
		 try {
				session = sessionFactory.openSession();
				Criteria criteria = session.createCriteria(ConstituencyCoverage.class);
				criteria.add( Restrictions.eq("constituencyType",'A'));
				criteria.add( Restrictions.eq("constituencyLc", localGovtBodyForm.getContributedAssembly()));
				criteria.add( Restrictions.eq("isactive", Boolean.TRUE));
				List<ConstituencyCoverage> constituencyCoverageList = criteria.list();
				
				if(constituencyCoverageList!=null && !constituencyCoverageList.isEmpty()){
					ConstituencyCoverage constituencyCoverage=constituencyCoverageList.get(0);
					criteria = session.createCriteria(ConstituencyCoverageEntity.class);
					criteria.add( Restrictions.eq("cccode",constituencyCoverage.getCcCode()));
					criteria.add( Restrictions.eq("isactive", Boolean.TRUE));
					 constituencyCoverageEntityList = criteria.list();
				}
				
			}finally{
				if
				(session!=null && session.isOpen()){
					session.close();
				}
			}
		return alocalGovtBodyForm;
	 }
	/* public List<Object> getConstituencyCoverageDetail(int constituency_lc) throws Exception{
		 Session session = null;
		 List<Object>  getConstituencyCoverageDetail=null;
			try {
				session = sessionFactory.openSession();
				String queryBuilder = "select cov.cc_code, lbt.local_body_type_name,gp.local_body_name_english,cov.coverage_type,generate_lb_hierarchy(gp.local_body_code) asEntity_Hierarchy_Details"
   +" FROM localbody gp left join localbody ip on ip.lblc=gp.parent_lblc and  ip.isactive  left join localbody dp on dp.lblc=ip.parent_lblc and dp.isactive"
+" left join constituency_covered_entity cov on cov.entity_lc=gp.local_body_code and cov.isactive"
+" inner join constituency_coverage cc on cc.cc_code=cov.cc_code"
+" left join local_body_type lbt on gp.local_body_type_code=lbt.local_body_type_code"
+" WHERE gp.isactive=TRUE  and cov.entity_type in(select distinct entity_type from constituency_covered_entity where cc_code=cov.cc_code)  and cc.constituency_lc=1107";
				Query query = session.createQuery(queryBuilder);
				query.setParameter("cCoverageCode", constituency_lc);
				getConstituencyCoverageDetail = query.list();
			} finally {
				if (session != null && session.isOpen()) {
					session.close();
				}
			}
			return getConstituencyCoverageDetail;
	 }*/
}
