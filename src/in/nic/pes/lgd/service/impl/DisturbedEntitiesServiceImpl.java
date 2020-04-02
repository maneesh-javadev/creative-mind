package in.nic.pes.lgd.service.impl;

import in.nic.pes.lgd.bean.Block;
import in.nic.pes.lgd.bean.CheckAuthorization;
import in.nic.pes.lgd.bean.GetEntitiesWithDisturbed;
import in.nic.pes.lgd.bean.GetEntitiesWithWanrning;
import in.nic.pes.lgd.bean.GetLocalbodyDisturbRegion;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.dao.BlockDAO;
import in.nic.pes.lgd.forms.ShiftVillageForm;
import in.nic.pes.lgd.service.DisturbedEntitiesService;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class DisturbedEntitiesServiceImpl implements DisturbedEntitiesService {
	
	private static final Logger log = Logger.getLogger(DisturbedEntitiesServiceImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	BlockDAO blockDAO;

	@SuppressWarnings("unchecked")
	@Override
	public List<GetEntitiesWithWanrning> getEntitiesWithWarningFlag(Integer Userid, String type,String parentEntityType,Integer parententitycode) throws Exception 
	{
		List<GetEntitiesWithWanrning> mapLandRegionList = new ArrayList<GetEntitiesWithWanrning>();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			
			if("L".equals(type)){
			mapLandRegionList = session
					.getNamedQuery("GetEntitiesWithWanrning")
					.setParameter("entityType", 'L')
					.setParameter("parent_entity_type", parentEntityType)
					.setParameter("parent_entity_code", parententitycode)
					.setParameter("category", null, Hibernate.STRING)
					.list();
			}
			else{				
					mapLandRegionList = session
							.getNamedQuery("GetEntitiesWithWanrning")
							.setParameter("entityType", 'G')
							.setParameter("parent_entity_type", parentEntityType)
							.setParameter("parent_entity_code", parententitycode)
							.setParameter("category", type ,Hibernate.STRING)
							.list();					
			}
			
			for (int i = 0; i < mapLandRegionList.size(); i++) {
				
				GetEntitiesWithWanrning getEntitiesWithWanrning = mapLandRegionList.get(i);
				String entityType = getEntitiesWithWanrning.getEntityType();
				int entityCode = getEntitiesWithWanrning.getEntityCode();
				
				if ("State".equalsIgnoreCase(entityType)) {
					getEntitiesWithWanrning.setEntityNameLocal("modifyState.htm?id="+ entityCode + "&disturb=true&warningEntiesFlag=W&type="+ type);
				}
				if ("District".equalsIgnoreCase(entityType)) {
					getEntitiesWithWanrning.setEntityNameLocal("modifyDistrictCrbyId.htm?id="+ entityCode+ "&disturb=true&warningEntiesFlag=W&type="+type);
				}
				if ("Subdistrict".equalsIgnoreCase(entityType)) {
					getEntitiesWithWanrning.setEntityNameLocal("modifySubdistrictCrbyId.htm?id="+ entityCode+ "&disturb=true&warningEntiesFlag=W&type="+type);
				}
				if ("Village".equalsIgnoreCase(entityType)) {
					getEntitiesWithWanrning.setEntityNameLocal("modifyVillageCrbyId.htm?id="+ entityCode + "&disturb=true&warningEntiesFlag=W&type="+type);
				}
				if ("Block".equalsIgnoreCase(entityType)) {
					getEntitiesWithWanrning.setEntityNameLocal("modifyBlockCrbyId.htm?id="+ entityCode + "&disturb=true&warningEntiesFlag=W&type="+type);
				}
				if ("LOCALBODY".equalsIgnoreCase(entityType)) {
					boolean isDisturbed = getEntitiesWithWanrning.isIsdisturbed();
					if(isDisturbed){
						getEntitiesWithWanrning.setEntityNameLocal("modifyGovtLocalBodyMain.htm?id="+ entityCode + "&disturb=true&warningEntiesFlag=D&type="+type);
						if("P".equals(type)){
							getEntitiesWithWanrning.setActionInvalidateLB("invalidatePRIDisturbed.htm?id="+ entityCode+ "&disturb=true&warningEntiesFlag=D&type="+type);
						}else if("T".equals(type)){
							getEntitiesWithWanrning.setActionInvalidateLB("invalidateTRIDisturbed.htm?id="+ entityCode+ "&disturb=true&warningEntiesFlag=D&type="+type);
						}else if("U".equals(type)){
							getEntitiesWithWanrning.setActionInvalidateLB("invalidateULBDisturbed.htm?id="+ entityCode+ "&disturb=true&warningEntiesFlag=D&type="+type);
						}
						getEntitiesWithWanrning.setActionChangeParent("modifyGovtLocalBodyMainfortype.htm?id="+ entityCode+ "&disturb=true&warningEntiesFlag=D&type="+type);
						getEntitiesWithWanrning.setActionChangeCoveredArea("modifyGovtLocalBodyMainforcoveragearea.htm?id="+ entityCode+ "&disturb=true&warningEntiesFlag=D&type="+type);
					}else{
						getEntitiesWithWanrning.setEntityNameLocal("modifyGovtLocalBodyMain.htm?id="+ entityCode + "&disturb=true&warningEntiesFlag=W&type="+type);
					}
				}
			}
		} catch (Exception e) {

			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return mapLandRegionList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<GetEntitiesWithDisturbed> getEntitiesWithDisturbedFlag(Integer Userid, String type,String parentEntityType,Integer parententitycode) throws Exception 
	{
		List<GetEntitiesWithDisturbed> mapLandRegionList = new ArrayList<GetEntitiesWithDisturbed>();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			
			if("L".equals(type)){
			mapLandRegionList = session
					.getNamedQuery("GetEntitiesWithDisturbed")
					.setParameter("entityType", 'L')
					.setParameter("parent_entity_type", parentEntityType)
					.setParameter("parent_entity_code", parententitycode)
					.setParameter("category", null, Hibernate.STRING)
					.list();
			}
			else{				
					mapLandRegionList = session
							.getNamedQuery("GetEntitiesWithDisturbed")
							.setParameter("entityType", 'G')
							.setParameter("parent_entity_type", parentEntityType)
							.setParameter("parent_entity_code", parententitycode)
							.setParameter("category", type ,Hibernate.STRING)
							.list();					
			}
			
			for (int i = 0; i < mapLandRegionList.size(); i++) {
				
				GetEntitiesWithDisturbed getEntitiesWithDisturbed = mapLandRegionList.get(i);
				String localbodyCode=getEntitiesWithDisturbed.getLocalbodycode();
			
				if("P".equals(type)){
					getEntitiesWithDisturbed.setActionInvalidateLB("invalidatePRIDisturbed.htm?id="+ localbodyCode+ "&disturb=true&warningEntiesFlag=D&type="+type);
				}else if("T".equals(type)){
					getEntitiesWithDisturbed.setActionInvalidateLB("invalidateTRIDisturbed.htm?id="+ localbodyCode+ "&disturb=true&warningEntiesFlag=D&type="+type);
				}else if("U".equals(type)){
					getEntitiesWithDisturbed.setActionInvalidateLB("invalidateULBDisturbed.htm?id="+ localbodyCode+ "&disturb=true&warningEntiesFlag=D&type="+type);
				}
				getEntitiesWithDisturbed.setActionChangeParent("modifyGovtLocalBodyMainfortype.htm?id="+ localbodyCode+ "&disturb=true&warningEntiesFlag=D&type="+type);
				getEntitiesWithDisturbed.setActionChangeCoveredArea("modifyGovtLocalBodyMainforcoverageareaDisturbed.htm?id="+ localbodyCode+ "&disturb=true&warningEntiesFlag=D&type="+type);
				getEntitiesWithDisturbed.setActionChangeName("modifyGovtLocalBodyMainforname.htm?id="+ localbodyCode+ "&disturb=true&warningEntiesFlag=D&type="+type);
				
				
			}
		} catch (Exception e) {

			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return mapLandRegionList;
	}
	
	@Override
	public boolean unSetMapLandRegionFlag(int landRegionCode,
			int landRegionVersion) throws Exception {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.createSQLQuery(
					"update localbody set warningflag=false where local_body_code="
							+ landRegionCode + " and local_body_version="
							+ landRegionVersion).executeUpdate();
		} catch (Exception e) {

			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return true;
	}

	/*@Override
	@SuppressWarnings("unchecked")
	public List<Village> getUnMappedVillageList(int stateCode) throws Exception {
		Session session = null;
		Query query = null;
		List<Village> getUnMappedVillageList = null;
		try {
			session = sessionFactory.openSession();
			query = session
					.createSQLQuery(
							"select v.* from village v where v.village_name_english!='' and "
									+ " v.isactive=true and subdistrict_code in(select subdistrict_code from subdistrict where isactive=true and district_code in("
									+ "(select district_code from district where isactive=true and state_code="
									+ stateCode
									+ ")))"
									+ " and v.village_code not in "
									+ "(select bv.village_code from block_village bv) order by v.village_name_english FETCH FIRST 5000 ROWS ONLY")
					.addEntity("v", Village.class);
			getUnMappedVillageList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return getUnMappedVillageList;

	}*/

	@Override
	@SuppressWarnings("unchecked")
	public List<Block> getBlockList(String villCode) {
		Session session = null;
		Query query = null;
		List<Block> getBlockList = null;
		try {
			session = sessionFactory.openSession();
			query = session
					.createSQLQuery(
							"select block_code,block_name_english from block,district,state  order by block_name_english)")
					.addEntity("block", Block.class);
			getBlockList = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return getBlockList;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<GetLocalbodyDisturbRegion> getMessagebyLBCode(int localbodycode) {
		
		List<GetLocalbodyDisturbRegion> messageData = new ArrayList<GetLocalbodyDisturbRegion>();
		Session session = null;
		//Query query = null;
		
		try {
			session = sessionFactory.openSession();
		
			messageData = session.getNamedQuery("GetLocalbodyDisturbedRegion")
						.setParameter("localbodycode",localbodycode,Hibernate.INTEGER)
						.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return messageData;
	}

	@Override
	public boolean assignDisturbedVillagestoBlock(
			ShiftVillageForm shiftVillageForm) throws NumberFormatException,
			Exception {
		boolean isCommited = false;
		String blockCode =null;
		String VillageCode = null;
		String unmapVillageCode = null;
		if(shiftVillageForm.getVillageNameEnglish()!=null){
			VillageCode=shiftVillageForm.getVillageNameEnglish();
		}	
		if(shiftVillageForm.getBlockNameEnglish()!=null){
			blockCode=shiftVillageForm.getBlockNameEnglish();
		}	
		unmapVillageCode=shiftVillageForm.getVillageName();
		isCommited = blockDAO.saveVillagesinBlock(blockCode,VillageCode,unmapVillageCode);
		return isCommited;
	}

	@Override
	public List<Village> getBlockVillageDetailByBlockCode(String villageCodes)
			throws Exception {
		List<Village> villList = new ArrayList<Village>();
		Session session = null;
		try {
			session = sessionFactory.openSession();

			Village vill = null;
			String sql = "SELECT block.block_code, block.block_name_english, village.village_code, village.village_name_english "
					+ "FROM public.block_village, public.block, public.village WHERE block_village.village_code in("
					+ villageCodes
					+ ") and block_village.block_code = block.block_code AND block_village.block_version = block.block_version AND "
					+ "block_village.village_code = village.village_code AND  block_village.village_version = village.village_version";
			@SuppressWarnings("unchecked")
			List<Object[]> temp = session.createSQLQuery(sql).list();
			session.close();
			for (int k = 0; k < temp.size(); k++) {
				vill = new Village();
				Object list[] = temp.get(k);
				vill.setVlc((Integer) list[0]); // temporarily
															// holding
															// Block Code
				vill.setAliasEnglish((String) list[1]); // temporarily holding
														// Block
														// Name
				vill.setVlc((Integer) list[2]);
				vill.setVillageNameEnglish((String) list[3]);
				villList.add(vill);
			}
		} catch (Exception e) {

			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return villList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CheckAuthorization> getBlockList(HttpSession httpSession)
			throws Exception {
		Session session = null;
		List<CheckAuthorization> getBlockList = null;
		try {

			session = sessionFactory.openSession();
			Query query = null;
			query = session
					.getNamedQuery("getcheckAuthorization")
					.setParameter("entity_type", "B", Hibernate.CHARACTER)
					.setParameter("entity_parent_type", "S", Hibernate.STRING)
					.setParameter(
							"entity_parent_code",
							Integer.parseInt(httpSession.getAttribute(
									"stateCode").toString()), Hibernate.INTEGER)
					.setParameter("entity_code", null, Hibernate.INTEGER);
			getBlockList = query.list();
			return getBlockList;
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
	}

	/*@SuppressWarnings("unchecked")
	@Override
	public List<GetEntitiesWithWanrning> getEntitiesWithDisturbFlag(UserSelection selection, String type,String parentEntityType) {
		List<GetEntitiesWithWanrning> mapLandRegionList = new ArrayList<GetEntitiesWithWanrning>();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			mapLandRegionList = session
							.getNamedQuery("GetEntitiesWithWanrning")
							.setParameter("entityType", 'L')
							.setParameter("parent_entity_type", parentEntityType)
							.setParameter("parent_entity_code", selection.getStateId())
					        .setParameter("category", selection.getCategory().getCategoryCode())
							.list();

			for (int i = 0; i < mapLandRegionList.size(); i++) {
				if (mapLandRegionList.get(i).getEntityType().equalsIgnoreCase("LOCALBODY")) {
					mapLandRegionList.get(i).setEntityNameLocal(
							"modifyState.htm?id="
									+ mapLandRegionList.get(i).getEntityCode()
									+ "&disturb=true&warningEntiesFlag=D");
				}
			}
		} catch (Exception e) {

			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return mapLandRegionList;
	}*/
}
