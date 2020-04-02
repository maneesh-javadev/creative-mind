package in.nic.pes.lgd.validator;
/**
 * @author Sushil Shakya
 * @date 09-10-2014
 */
import in.nic.pes.lgd.forms.BlockForm;
import in.nic.pes.lgd.forms.DepartmentAdminForm;
import in.nic.pes.lgd.forms.DepartmentForm;
import in.nic.pes.lgd.forms.DistrictForm;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.forms.LocalGovtBodyForm;
import in.nic.pes.lgd.forms.MinistryForm;
import in.nic.pes.lgd.forms.OrganizationTypeForm;
import in.nic.pes.lgd.forms.ShiftBlockForm;
import in.nic.pes.lgd.forms.ShiftDistrictForm;
import in.nic.pes.lgd.forms.ShiftSubDistrictForm;
import in.nic.pes.lgd.forms.ShiftVillageForm;
import in.nic.pes.lgd.forms.StandardCodeDataForm;
import in.nic.pes.lgd.forms.StateForm;
import in.nic.pes.lgd.forms.SubDistrictForm;
import in.nic.pes.lgd.forms.VillageDataForm;
import in.nic.pes.lgd.forms.VillageForm;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.CharUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

public class AbstractValidator {
	private static final Logger log = Logger.getLogger(AbstractValidator.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("deprecation")
	public void validate(HttpSession session, Object object, Errors errors) {
		GovernmentOrderForm govtForm = (GovernmentOrderForm) object;
		if (session.getAttribute("formbean") != null && govtForm != null) {
			Object sessionObject = session.getAttribute("formbean");
			Date dateEffective = getEffectiveDateByEntity(sessionObject);
			log.info("Effective Date from db-->"+dateEffective);
			if(dateEffective != null) {
				dateEffective.setHours(0);
				dateEffective.setMinutes(0);
				dateEffective.setSeconds(0);
				log.info("Effective Date after conversion -->"+dateEffective);
			}
			if (dateEffective != null && govtForm.getEffectiveDate() != null && govtForm.getEffectiveDate().before(dateEffective)) {
				errors.rejectValue("effectiveDate", "Msg.EFFECTIVEDATE");
			}
		}
		GovernmentOrderValidator validator = new GovernmentOrderValidator();
		validator.validate(object, errors);
	}
	
	@SuppressWarnings("deprecation")
	public Date getEffectiveDateByEntity(Object entity) { // NO_UCD (use default)
		Session session = null;
		Query query = null;
		String sql = null;
		Date effectivedate = null;
		try {
			if (entity instanceof VillageForm || entity instanceof ShiftVillageForm) {
				Integer villageId = null;
				if (entity instanceof VillageForm) {
					VillageForm object = (VillageForm) entity;
					villageId = object.getVillageId();
				} else {
					ShiftVillageForm object = (ShiftVillageForm) entity;
					villageId = Integer.valueOf(object.getVillageNameEnglish());
				}
				sql = "select v.effectiveDate from Village v where v.villagePK.villageCode = "+villageId+" and v.isactive = true";
				log.info("Case: Village");
			} else if (entity instanceof DistrictForm || entity instanceof ShiftDistrictForm) {
				Integer districtId = null;
				if (entity instanceof DistrictForm) {
					DistrictForm object = (DistrictForm) entity;
					districtId = object.getDistrictId();
				} else {
					ShiftDistrictForm object = (ShiftDistrictForm) entity;
					districtId = Integer.valueOf(object.getDistrictNameEnglish());
				}
				sql = "select v.effectiveDate from District v where v.districtPK.districtCode = "+districtId+" and v.isactive = true";
				log.info("Case: District");
			} else if (entity instanceof SubDistrictForm || entity instanceof ShiftSubDistrictForm) {
				Integer subdistrict = null;
				if (entity instanceof SubDistrictForm) {
					SubDistrictForm object = (SubDistrictForm) entity;
					subdistrict = object.getSubdistrictId();
				} else {
					ShiftSubDistrictForm object = (ShiftSubDistrictForm) entity;
					subdistrict = Integer.valueOf(object.getSubdistrictNameEnglish());
				}
				sql = "select v.effectiveDate from Subdistrict v where v.subdistrictPK.subdistrictCode = "+subdistrict+" and v.isactive = true";
				log.info("Case: SubDistrict");
			} else if (entity instanceof BlockForm || entity instanceof ShiftBlockForm) {
				Integer blockId = null;
				if (entity instanceof BlockForm) {
					BlockForm object = (BlockForm) entity;
					blockId = object.getBlockId();
				} else {
					ShiftBlockForm object = (ShiftBlockForm) entity;
					blockId = Integer.valueOf(object.getBlockNameEnglish());
				}
				sql = "select v.effectiveDate from Block v where v.blockPK.blockCode = "+blockId+" and v.isactive = true";
				log.info("Case: Block");
			} else if (entity instanceof LocalGovtBodyForm) {
				LocalGovtBodyForm object = (LocalGovtBodyForm) entity;
				sql = "select v.effectiveDate from LocalGovtBody v where v.localBodyCode = "+object.getLocalBodyCode()+" and v.isactive = true";
				log.info("Case: LocalBody");
			} else if (entity instanceof StateForm) {
				StateForm object = (StateForm) entity;
				sql = "select v.effectiveDate from State v where v.statePK.stateCode = "+object.getStateId()+" and v.isactive = true";
				log.info("Case: State");
			}
			
			session = sessionFactory.openSession();
			query = session.createQuery(sql);
			
			List<?> list = query.list();
			if(!list.isEmpty()) {
				Date dateEffective = (Date) list.get(0);
				if(dateEffective != null) {
					dateEffective.setHours(0);
					dateEffective.setMinutes(0);
					dateEffective.setSeconds(0);
					log.info("Effective Date after conversion -->"+dateEffective);
				}
				return dateEffective;
			} else {
				return null;
			}
		} catch (Exception e) {
			try {
				log.error("Exception in getEffectiveDateByEntity-->", e);
				throw new Exception(e);
			} catch (Exception e1) {
				log.error("Exception in getEffectiveDateByEntity catch-->", e1);
			}
		} finally {
			if(session!=null && session.isOpen()){
				session.close();
			}	
		}
		return effectivedate;
	}
	
	public Integer getCheckForParent(Object entity){

		Session session = null;
		Query query = null;
		SQLQuery query2=null;
		String sql = null;
		Integer stateCode=0;
		if (entity instanceof VillageForm || entity instanceof ShiftVillageForm) {
			Integer villageId = null;
			if (entity instanceof VillageForm) {
				VillageForm object = (VillageForm) entity;
				if(object.getDraftVillageCode()!=null &&  object.getDraftVillageCode()>0){
					sql = "select coalesce(v.stateCode,0) from VillageDraft v where v.villageCode = "+object.getDraftVillageCode()+" ";
					log.info("Case: Village");
				}else{
					villageId = object.getVillageId();
					sql = "select coalesce(v.slc,0) from Village v where v.villagePK.villageCode = "+villageId+" and v.isactive = true";
					log.info("Case: Village");
				}
			}else {
				ShiftVillageForm object = (ShiftVillageForm) entity;
				villageId = Integer.valueOf(object.getVillageNameEnglish());
				sql = "select coalesce(v.slc,0) from Village v where v.villagePK.villageCode = "+villageId+" and v.isactive = true";
				log.info("Case: Village");
			}
		} else if (entity instanceof DistrictForm || entity instanceof ShiftDistrictForm) {
			Integer districtId = null;
			if (entity instanceof DistrictForm) {
				DistrictForm object = (DistrictForm) entity;
				districtId = object.getDistrictId();
			} else {
				ShiftDistrictForm object = (ShiftDistrictForm) entity;
				districtId = Integer.valueOf(object.getDistrictNameEnglish());
			}
			sql = "select coalesce(v.slc,0) from District v where v.districtPK.districtCode = "+districtId+" and v.isactive = true";
			log.info("Case: District");
		} else if (entity instanceof SubDistrictForm || entity instanceof ShiftSubDistrictForm) {
			Integer subdistrict = null;
			if (entity instanceof SubDistrictForm) {
				SubDistrictForm object = (SubDistrictForm) entity;
				subdistrict = object.getSubdistrictId();
			} else {
				ShiftSubDistrictForm object = (ShiftSubDistrictForm) entity;
				subdistrict = Integer.valueOf(object.getSubdistrictNameEnglish());
			}
			sql = "select coalesce(v.district.slc,0) from Subdistrict v where v.subdistrictPK.subdistrictCode = "+subdistrict+" and v.isactive = true";
			log.info("Case: SubDistrict");
		} else if (entity instanceof BlockForm || entity instanceof ShiftBlockForm) {
			Integer blockId = null;
			if (entity instanceof BlockForm) {
				BlockForm object = (BlockForm) entity;
				blockId = object.getBlockId();
			} else {
				ShiftBlockForm object = (ShiftBlockForm) entity;
				blockId = Integer.valueOf(object.getBlockNameEnglish());
			}
			
			sql="select d.slc from block b,district d where b.dlc=d.dlc and  b.block_code="+blockId+" and b.isactive and d.isactive";
			//sql = "select v.* from Block v where v.blockPK.blockCode = "+blockId+" and v.isactive = true";
			log.info("Case: Block");
		} else if (entity instanceof LocalGovtBodyForm) {
			LocalGovtBodyForm object = (LocalGovtBodyForm) entity;
			sql = "select coalesce(v.slc,0) from LocalGovtBody v where v.localBodyCode = "+object.getLocalBodyCode()+" and v.isactive = true";
			log.info("Case: LocalBody");
		} else if (entity instanceof StateForm) {
			StateForm object = (StateForm) entity;
			sql = "select coalesce(v.slc,0) from State v where v.statePK.stateCode = "+object.getStateId()+" and v.isactive = true";
			log.info("Case: State");
		} else if (entity instanceof MinistryForm) {
			MinistryForm object = (MinistryForm) entity;
			Integer orgCode =0;
			if(object.getOrganizationId()!=null && object.getOrganizationId()>0) {
				orgCode=object.getOrganizationId();
				if((!(("").equals(CharUtils.toString(object.getCategory()).trim())) && !(object.getCategory()=='S') )){
					sql = "select coalesce(v.organization.slc,0) from OrgLocatedAtLevels v where v.orgLocatedLevelCode = "+orgCode+" and v.isactive = true";
				}else{
					sql = "select coalesce(v.slc,0) from Organization v where v.orgCode = "+orgCode+" and v.orgCode not between 3 and 37 and v.isactive = true";
				}
			} else if(object.getOrgLocatedAtLevelCode()>0) {
				orgCode=object.getOrgLocatedAtLevelCode();
				sql = "select coalesce(v.organization.slc,0) from OrgLocatedAtLevels v where v.orgLocatedLevelCode = "+orgCode+" and v.isactive = true";
			} 
			log.info("Case: Department");
		} else if (entity instanceof OrganizationTypeForm) {
			OrganizationTypeForm object = (OrganizationTypeForm) entity;
			Integer orgCode =0;
				if(!("S").equals(object.getOrgType()) && object.getOrganizationId()!=null && object.getOrganizationId()>0) {
					orgCode=object.getOrganizationId();
					sql = "select coalesce(v.organization.slc,0) from OrgLocatedAtLevels v where v.orgLocatedLevelCode = "+orgCode+" and v.isactive = true";
				} else if(object.getOrganizationId()!=null && object.getOrganizationId()>0) {
					orgCode=object.getOrganizationId();
					sql = "select coalesce(v.slc,0) from Organization v where v.orgCode = "+orgCode+" and v.orgCode not between 3 and 37 and v.isactive = true";
				}
				log.info("Case: Department");
		}
		/*commented by pooja as orgLocatedLevelCode not now in LgdDesignation*/
		/*else if (entity instanceof LgdDesignation) {
			LgdDesignation object = (LgdDesignation) entity;
			Integer orgCode =0;
			if(object.getOrgLocatedLevelCode()!=null && object.getOrgLocatedLevelCode()>0){
				orgCode=object.getOrgLocatedLevelCode();
				sql = "select coalesce(v.organization.slc,0) from OrgLocatedAtLevels v where v.orgLocatedLevelCode = "+orgCode+" and v.isactive = true";
			}else if(object.getOlc()!=null && object.getOlc()>0){
				orgCode=object.getOlc();
				sql = "select coalesce(v.slc,0) from Organization v where v.orgCode = "+orgCode+" and v.isactive = true";
			}
			log.info("Case: Department");
		} */else if (entity instanceof DepartmentAdminForm) {
			DepartmentAdminForm object = (DepartmentAdminForm) entity;
			Integer adminUnitCode =0;
			 if(object.getAdminUnitEntityCode()!=null && object.getAdminUnitEntityCode()>0){
					adminUnitCode=object.getAdminUnitEntityCode();
					sql = "select coalesce(v.slc,0) from DeptAdminUnitEntity v where v.adminUnitEntityCode = "+adminUnitCode+" and v.isactive = true";
			 } else if(object.getAdminUnitCode()!=null && object.getAdminUnitCode()>0){
				adminUnitCode=object.getAdminUnitCode();
				sql = "select coalesce(v.slc,0) from DeptAdminUnit v where v.adminUnitCode = "+adminUnitCode+" and v.isactive = true";
			}
			
			log.info("Case: Department");
		}else if (entity instanceof DepartmentForm) {
			DepartmentForm object = (DepartmentForm) entity;
			Integer orgCode =0;
			if(object.getOrgLocatedlevelCode()>0){
				orgCode=object.getOrgLocatedlevelCode();
				sql = "select coalesce(v.organization.slc,0) from OrgLocatedAtLevels v where v.orgLocatedLevelCode = "+orgCode+" and v.isactive = true";
			}else if(object.getOrgCode()!=null && object.getOrgCode()>0){
				orgCode=object.getOrgCode();
				sql = "select coalesce(v.slc,0) from Organization v where v.orgCode = "+orgCode+" and v.isactive = true";
			}
		} else if (entity instanceof StandardCodeDataForm) {
			StandardCodeDataForm object = (StandardCodeDataForm) entity;
			Integer districtCode =0;
			if(districtCode!=null){
				districtCode=object.getEntityCode();
				sql = "select coalesce(v.slc,0) from District v where v.districtPK.districtCode = "+districtCode+" and v.isactive = true";
				log.info("Case: Department");
			}
		}else if (entity instanceof VillageDataForm) {
			VillageDataForm object = (VillageDataForm) entity;
			if(object.getDraftVillageCode()!=null &&  object.getDraftVillageCode()>0){
				sql = "select coalesce(v.stateCode,0) from VillageDraft v where v.villageCode = "+object.getDraftVillageCode()+" ";
				log.info("Case: Village");
			}
		}
		
		
		List<?> list=null;
		try{
		session = sessionFactory.openSession();
		
		if (entity instanceof BlockForm) {
			query2 = session.createSQLQuery(sql);
			list = query2.list();
		}else{
			query = session.createQuery(sql);
			list = query.list();
		}
		if(!list.isEmpty()) {
			stateCode = (Integer) list.get(0);
			return stateCode;
		} else {
			return 0;
		}
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
			
		}
		return stateCode;
	
	}
	
	
	
	public Integer getCheckForParentListItems(Object entity,String landRegionList,String landRegionType){
		
		Session session = null;
		Query query = null;
		SQLQuery query2=null;
		String sql = null;
		Integer stateCode=0;
		try{
			if(landRegionList!=null && landRegionType!=null && landRegionList.length()>0 && !("").equals(landRegionType)){
				if("D".equalsIgnoreCase(landRegionType)){
					sql = "select distinct coalesce(v.slc,0) from District v where v.districtPK.districtCode in( "+landRegionList+") and v.isactive = true";
					log.info("Case: District");
				}else if("T".equalsIgnoreCase(landRegionType)){
					sql = "select distinct coalesce(v.district.slc,0) from Subdistrict v where v.subdistrictPK.subdistrictCode in( "+landRegionList+") and v.isactive = true";
					log.info("Case: SubDistrict");
				}else if("V".equalsIgnoreCase(landRegionType)){
					sql = "select distinct coalesce(v.slc,0) from Village v where v.villagePK.villageCode in( "+landRegionList+") and v.isactive = true ";
					log.info("Case: Village");
				}else if("S".equalsIgnoreCase(landRegionType)){
					sql = "select distinct coalesce(v.slc,0) from State v where v.statePK.stateCode in("+landRegionList+") and v.isactive = true";
					log.info("Case: State");
				}else if("G".equalsIgnoreCase(landRegionType)){
					sql = "select distinct coalesce(v.slc,0) from LocalGovtBody v where v.localBodyCode in( "+landRegionList+" )and v.isactive = true";
					log.info("Case: LocalBody");
				}else if("B".equalsIgnoreCase(landRegionType)){
					sql="select distinct coalesce(d.slc,0)  from block b,district d where b.dlc=d.dlc and  b.block_code in ("+landRegionList+") and b.isactive and d.isactive";
					log.info("Case: Block");
				} else if("A".equalsIgnoreCase(landRegionType)){
					sql = "select distinct coalesce(v.slc,0) from DeptAdminUnitEntity v where v.adminUnitEntityCode in( "+landRegionList+" ) and v.isactive = true";
				}
				
				List<?> list=null;
				session = sessionFactory.openSession();
				if ("B".equalsIgnoreCase(landRegionType)) {
					query2 = session.createSQLQuery(sql);
					list = query2.list();
				}else{
					query = session.createQuery(sql);
					list = query.list();
				}
				if(!list.isEmpty() && list.size()==1) {
					stateCode = (Integer) list.get(0);
					return stateCode;
				} else {
					return 0;
				}
			}
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return stateCode;
	
	}
}