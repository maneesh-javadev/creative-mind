package in.nic.pes.lgd.dao.impl;

import in.nic.pes.common.menu.pojo.UserSelection;
import in.nic.pes.lgd.bean.Attachment;
import in.nic.pes.lgd.bean.ChangeCoverageNameParentofDisturbLocalbody;
import in.nic.pes.lgd.bean.ChangeLocalBodyCoveredArea;
import in.nic.pes.lgd.bean.ChangeLocalBodyName;
import in.nic.pes.lgd.bean.ChangeLocalBodyUrbanType;
import in.nic.pes.lgd.bean.ChangeLocalBodypriType;
import in.nic.pes.lgd.bean.CheckAuthorization;
import in.nic.pes.lgd.bean.ChildLocalGovtBody;
import in.nic.pes.lgd.bean.ConstituencyLocalbody;
import in.nic.pes.lgd.bean.ConstituencyMapDetailsbyacCode;
import in.nic.pes.lgd.bean.ConstituencyVillage;
import in.nic.pes.lgd.bean.ConstituencyWard;
import in.nic.pes.lgd.bean.CoveredWardLandregion;
import in.nic.pes.lgd.bean.District;
import in.nic.pes.lgd.bean.EntityVersion;
import in.nic.pes.lgd.bean.EntityWiseMapDetailsPojo;
import in.nic.pes.lgd.bean.GetGovernmentOrderDetail;
import in.nic.pes.lgd.bean.GetLBHierarchy;
import in.nic.pes.lgd.bean.GetLandRegionNameforWard;
import in.nic.pes.lgd.bean.GetLandRegionWise;
import in.nic.pes.lgd.bean.GetLocalBodyTypeCode;
import in.nic.pes.lgd.bean.GetLocalGovtBodyTypeList;
import in.nic.pes.lgd.bean.GetLocalGovtSetup;
import in.nic.pes.lgd.bean.GovernmentOrder;
import in.nic.pes.lgd.bean.GovernmentOrderEntityWise;
import in.nic.pes.lgd.bean.Habitation;
import in.nic.pes.lgd.bean.InsertLocalGovtBody;
import in.nic.pes.lgd.bean.LBCoverageWard;
import in.nic.pes.lgd.bean.LbCoveredLandregion;
import in.nic.pes.lgd.bean.LgdPfmsMapping;
import in.nic.pes.lgd.bean.LocalBodyCoveredArea;
import in.nic.pes.lgd.bean.LocalBodyCoveredAreaLB;
import in.nic.pes.lgd.bean.LocalBodyDetails;
import in.nic.pes.lgd.bean.LocalBodyHistory;
import in.nic.pes.lgd.bean.LocalBodyParent;
import in.nic.pes.lgd.bean.LocalBodyType;
import in.nic.pes.lgd.bean.LocalBodyTypeForStateWiseView;
import in.nic.pes.lgd.bean.LocalBodyTypeHistory;
import in.nic.pes.lgd.bean.LocalBodyTypeWiseDepartment;
import in.nic.pes.lgd.bean.LocalBodyUrbanType;
import in.nic.pes.lgd.bean.LocalBodyViewChild;
import in.nic.pes.lgd.bean.LocalGovtBody;
import in.nic.pes.lgd.bean.LocalGovtBodyDTO;
import in.nic.pes.lgd.bean.LocalGovtBodyForSelectedBody;
import in.nic.pes.lgd.bean.LocalGovtBodyNameList;
import in.nic.pes.lgd.bean.LocalGovtBodyWard;
import in.nic.pes.lgd.bean.Localbody;
import in.nic.pes.lgd.bean.LocalbodyByDistrict;
import in.nic.pes.lgd.bean.LocalbodyDistrict;
import in.nic.pes.lgd.bean.LocalbodyDistrictPK;
import in.nic.pes.lgd.bean.LocalbodyListbyState;
import in.nic.pes.lgd.bean.LocalbodyReplaces;
import in.nic.pes.lgd.bean.LocalbodyUnMappedBody;
import in.nic.pes.lgd.bean.LocalbodyUnMappedBodyLevelWise;
import in.nic.pes.lgd.bean.LocalbodyWard;
import in.nic.pes.lgd.bean.LocalbodyWardId;
import in.nic.pes.lgd.bean.LocalbodyforStateWise;
import in.nic.pes.lgd.bean.LocalbodyforStateWiseFinal;
import in.nic.pes.lgd.bean.MapAttachment;
import in.nic.pes.lgd.bean.MapLandRegion;
import in.nic.pes.lgd.bean.MapZpWardContituency;
import in.nic.pes.lgd.bean.NodalOfficer;
import in.nic.pes.lgd.bean.NodalOfficerPK;
import in.nic.pes.lgd.bean.ParentWiseLBList;
import in.nic.pes.lgd.bean.ParentWiseLocalBodies;
import in.nic.pes.lgd.bean.ParentWiseLocalBodiesWithoutChildCount;
import in.nic.pes.lgd.bean.PartillyMappedLRList;
import in.nic.pes.lgd.bean.PartillyMappedLRListLevelWise;
import in.nic.pes.lgd.bean.PushLBtoPES;
import in.nic.pes.lgd.bean.Pushcoveragetopes;
import in.nic.pes.lgd.bean.SearchLocalGovtBody;
import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.bean.State;
import in.nic.pes.lgd.bean.StatewisePesaPanchyat;
import in.nic.pes.lgd.bean.Subdistrict;
import in.nic.pes.lgd.bean.UlbBean;
import in.nic.pes.lgd.bean.UlbMerge;
import in.nic.pes.lgd.bean.UnLRDistrictWiseList;
import in.nic.pes.lgd.bean.UnLRSWiseList;
import in.nic.pes.lgd.bean.UnmappedLBList;
import in.nic.pes.lgd.bean.ViewLandRegionDisturbedlist;
import in.nic.pes.lgd.bean.ViewLocalBodyLandRegion;
import in.nic.pes.lgd.bean.Village;
import in.nic.pes.lgd.bean.invalidatePRIlb;
import in.nic.pes.lgd.bean.localbodywardtemp;
import in.nic.pes.lgd.dao.LocalGovtBodyDAO;
import in.nic.pes.lgd.forms.GenerateDetails;
import in.nic.pes.lgd.forms.GovernmentOrderForm;
import in.nic.pes.lgd.forms.LGBodyCoveredAreaDTO;
import in.nic.pes.lgd.forms.LocalGovtBodyForm;
import in.nic.pes.lgd.forms.LocalGovtTypeDataForm;
import in.nic.pes.lgd.forms.NodalOfficerForm;
import in.nic.pes.lgd.forms.WardForm;
import in.nic.pes.lgd.interceptor.LanguageListener;
import in.nic.pes.lgd.loggers.LGDLogger;
import in.nic.pes.lgd.service.EmailService;
import in.nic.pes.lgd.service.EmailSmsThread;
import in.nic.pes.lgd.service.VillageService;
import in.nic.pes.lgd.utils.CommonUtil;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
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
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import pes.attachment.util.AttachedFilesForm;
import ws.in.nic.pes.lgdws.entity.VillageListWithHierarchy;

import com.cmc.lgd.localbody.entities.LBTypeDetails;
import com.cmc.lgd.localbody.entities.LBTypeHierarchy;
import com.cmc.lgd.localbody.entities.LocalBodyTable;
import com.cmc.lgd.localbody.rules.LocalBodyConstant;
import com.cmc.lgd.localbody.rules.LocalBodyUtil;
import com.org.ep.exception.BaseAppException;

@Transactional
@Repository
@Service
public class LocalGovtBodyDAOImpl implements LocalGovtBodyDAO {
	private static final Logger log = Logger.getLogger(LocalGovtBodyDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private VillageService villageService;
	
	@Autowired
	private LocalBodyUtil localBodyUtil;
	
	@Autowired
	private EmailService emailService;

	@Override
	public int getMaxRecords(String userQuery) throws Exception {
		int MaxCode = 0;
		Session session = null;

		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery(userQuery);
			@SuppressWarnings("rawtypes")
			List list = query.list();
			if (!list.isEmpty() && list.get(0) != null) {
				MaxCode = (Integer) list.get(0);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return MaxCode;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalbodyforStateWise> getLocalBodyListStateWise(char localBodyType, int stateCode) throws Exception {
		Session session = null;
		List<LocalbodyforStateWise> localbodyList = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalBodylistStateWise").setParameter("stateCode", stateCode, Hibernate.INTEGER).setParameter("localBodyType", localBodyType, Hibernate.CHARACTER);
			localbodyList = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {

			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return localbodyList;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalbodyforStateWise> getLocalBodyListStateWiseChangeType(char localBodyType, int stateCode, int lbtypecode) throws Exception {
		Session session = null;
		List<LocalbodyforStateWise> localbodyList = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalBodylistStateWiseChangeType").setParameter("stateCode", stateCode, Hibernate.INTEGER).setParameter("localBodyType", localBodyType, Hibernate.CHARACTER)
					.setParameter("lbtypeCode", lbtypecode, Hibernate.INTEGER);
			localbodyList = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {

			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return localbodyList;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalbodyforStateWise> getLocalBodyListStateWiseTierSet(char localBodyType, int stateCode, int parentyresetupCd) throws Exception {
		Session session = null;
		List<LocalbodyforStateWise> localbodyList = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalBodytiersetupwise").setParameter("stateCode", stateCode, Hibernate.INTEGER).setParameter("localBodyType", localBodyType, Hibernate.CHARACTER).setParameter("tiersetup", parentyresetupCd);
			localbodyList = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return localbodyList;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalbodyforStateWise> getLocalBodystatelist(char localBodyType, int stateCode) throws Exception {
		Session session = null;
		List<LocalbodyforStateWise> localbodyList = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalBodylistStateWiseForConstituencies").setParameter("stateCode", stateCode, Hibernate.INTEGER).setParameter("localBodyType", null, Hibernate.CHARACTER);
			localbodyList = query.list();

		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Exception" + e);
		} finally {

			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return localbodyList;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<District> getDistrictNamebyDisID(int distCode) throws Exception {
		Session session = null;
		List<District> districtnameList = null;
		Query criteria = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from District d where d.districtPK.districtCode in(:districtCode) and isactive=true");
			criteria.setParameter("districtCode", distCode, Hibernate.INTEGER);
			districtnameList = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return districtnameList;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Village> getconVillageNamebyVillID(int villageCode) throws Exception {
		Session session = null;
		List<Village> villagetnameList = null;
		Query criteria = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Village d where d.villagePK.villageCode in(:villageCode) and isactive=true");
			criteria.setParameter("villageCode", villageCode, Hibernate.INTEGER);
			villagetnameList = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return villagetnameList;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Localbody> getLocalBodyNamebyLBID(int localbodyCode) throws Exception {
		Session session = null;
		List<Localbody> localbodynameList = null;
		Query criteria = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Localbody d where d.localBodyCode in(:localbodyCode) and isactive=true");
			criteria.setParameter("localbodyCode", localbodyCode, Hibernate.INTEGER);
			localbodynameList = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return localbodynameList;

	}

	// Code added for change in Localbody type change by Arnab on 25/03/2013
	@Override
	public int getLocalBodybyLBID(int localbodyCode) throws Exception {
		Session session = null;
		Query query = null;
		int lbtypeCd = 0;
		try {
			session = sessionFactory.openSession();
			query = session.createQuery("select localBodyTypeCode from Localbody where isactive=true and id.lblc=:localbodyCode");
			query.setParameter("localbodyCode", localbodyCode, Hibernate.INTEGER);
			lbtypeCd = (Integer) query.list().get(0);
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lbtypeCd;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalBodyType> getLocalBodyTypeNamebyLBTID(int localbodytypeCode) throws Exception {
		Session session = null;
		List<LocalBodyType> localbodytypenameList = null;
		Query criteria = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from LocalBodyType d where d.localBodyTypeCode in(:localBodyTypeCode) and isactive=true");
			criteria.setParameter("localBodyTypeCode", localbodytypeCode, Hibernate.INTEGER);
			localbodytypenameList = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return localbodytypenameList;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subdistrict> getSubDistrictNamebyDisID(int subdisCode) throws Exception {
		Session session = null;
		List<Subdistrict> subdistrictnameList = null;
		Query criteria = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Subdistrict d where d.subdistrictPK.subdistrictCode in(:subdistrictCode) and isactive=true");
			criteria.setParameter("subdistrictCode", subdisCode, Hibernate.INTEGER);
			subdistrictnameList = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return subdistrictnameList;
	}

	/**
	 * The {@code getDistrictNamebyCovChangeULB} return all District Names based
	 * on
	 * 
	 * @param availlgdLBInterCAreaSourceListUmappedUrban
	 *            .
	 * @param availlgdLBInterCAreaSourceListUmappedUrban
	 * @return
	 * @throws Exception
	 * @author Vinay Yadav 23-12-2013
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String getDistrictNamebyCovChangeULB(String availlgdLBInterCAreaSourceListUmappedUrban) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		String districtNames = "";
		Query criteria = null;
		try {
			if (availlgdLBInterCAreaSourceListUmappedUrban == null) {
				return districtNames;
			}
			session = sessionFactory.openSession();
			Transformer transformer = new Transformer() {
				@Override
				public Integer transform(Object input) {
					return (input == null ? 0 : Integer.valueOf((String) input));
				}
			};
			Collection<Integer> ints = CollectionUtils.collect(Arrays.asList(availlgdLBInterCAreaSourceListUmappedUrban.split(",")), transformer);
			String query = "select district_name_english from district where district_code in(:districtCodes) and isactive";
			criteria = session.createSQLQuery(query);
			criteria.setParameterList("districtCodes", ints);
			List<String> list = criteria.list();
			if (!list.isEmpty()) {
				districtNames = StringUtils.arrayToCommaDelimitedString(list.toArray());
			}
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return districtNames;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalBodyCoveredArea> getLocalGovtBodyforCoveredSubDistrictList(int localBodyCode) throws Exception {

		Session session = null;
		Query query = null;
		List<LocalBodyCoveredArea> localGovtBodyforCovereSubdDistrictList = null;
		List<LocalBodyCoveredArea> lbforCoveredSubDistrictList = new ArrayList<LocalBodyCoveredArea>();

		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalGovtBodyforCoveredSubDist").setParameter("localBodyCode", localBodyCode);
			localGovtBodyforCovereSubdDistrictList = query.list();
			lbforCoveredSubDistrictList = removeNullElementFromList(localGovtBodyforCovereSubdDistrictList);

		} catch (Exception e) {
			log.debug("Exception" + e);

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return lbforCoveredSubDistrictList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalBodyCoveredArea> getLocalGovtBodyforCoveredSubDistrictListInter(int localBodyCode) throws Exception {

		Session session = null;
		Query query = null;
		List<LocalBodyCoveredArea> localGovtBodyforCovereSubdDistrictList = null;
		List<LocalBodyCoveredArea> lbforCoveredSubDistrictList = new ArrayList<LocalBodyCoveredArea>();

		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalGovtBodyforUrbanCoveredList").setParameter("localBodyCode", localBodyCode);
			localGovtBodyforCovereSubdDistrictList = query.list();
			lbforCoveredSubDistrictList = removeNullElementFromList(localGovtBodyforCovereSubdDistrictList);

		} catch (Exception e) {
			log.debug("Exception" + e);

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return lbforCoveredSubDistrictList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalBodyCoveredArea> getLocalGovtBodyforCoveredVillageFinalList(int localBodyCode) throws Exception {

		Session session = null;
		Query query = null;
		List<LocalBodyCoveredArea> localGovtBodyforCovereVillageList = null;
		List<LocalBodyCoveredArea> lbforCoveredVillageList = new ArrayList<LocalBodyCoveredArea>();

		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalGovtBodyforCoveredVillageListName").setParameter("localBodyCode", localBodyCode);
			localGovtBodyforCovereVillageList = query.list();
			lbforCoveredVillageList = removeNullElementFromList(localGovtBodyforCovereVillageList);
		} catch (Exception e) {
			log.debug("Exception" + e);

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lbforCoveredVillageList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<State> getStateName(int stateCode) throws Exception {
		Session session = null;
		List<State> statenameList = null;
		Query criteria = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from State d where d.statePK.stateCode=:stateCode");
			criteria.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			statenameList = criteria.list();

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return statenameList;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Localbody> getLocalbodyname(String parentLBodyCode) throws Exception {
		Session session = null;
		List<Localbody> parentlocalbnameList = new ArrayList<>();
		Query criteria = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Localbody d where d.localBodyCode=:parentLBCode and d.isactive=true");
			criteria.setParameter("parentLBCode", Integer.parseInt(parentLBodyCode), Hibernate.INTEGER);
			parentlocalbnameList = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return parentlocalbnameList;

	}

	@SuppressWarnings("unchecked")
	public List<District> getDistrictListbyStateCode(int stateCode) throws Exception {
		Query criteria = null;
		Session session = null;
		List<District> districtList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from District d where d.state.statePK.stateCode=:stateCode");
			criteria.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			districtList = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return districtList;

	}

	@SuppressWarnings("unchecked")
	public List<LocalBodyType> getLGTypeForGovtBody() throws Exception {
		List<LocalBodyType> localbody = null;
		try {
			localbody = sessionFactory.getCurrentSession().createQuery("from LocalBodyType where isactive=true order by localBodyTypeName").list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return localbody;

	}

	// updateLocalBodyDetails
	/*@Override
	public boolean updateLocalBodyDetails(Session session, Localbody localBody) throws Exception {
		try {

			session.saveOrUpdate(localBody);
			session.flush();
			if (session.contains(localBody)) {
				session.evict(localBody);
			}
			return true;

		} catch (Exception e) {
			log.debug("Exception" + e);
			return false;

		}

	}*/

	/*@Override
	public boolean updateLocalBodyDetailsUpdate(Session session, LocalbodyPK localBodyPK, int localBodyReplacedByCode) throws Exception {
		try {
			Localbody localbodyU = (Localbody) session.load(Localbody.class, localBodyPK);

			localbodyU.setIsactive(false);
			localbodyU.setFlagCode(1);
			localbodyU.setLbReplacedby(localBodyReplacedByCode);

			session.update(localbodyU);

			return true;

		} catch (Exception e) {
			log.debug("Exception" + e);
			return false;
		}

	}
*/
	/*@Override
	public int saveLocalBodyDetails(Localbody localbody, Session session) throws Exception {
		int localBodyCode = 0;
		LocalbodyPK localbody2 = null;
		try {

			localbody2 = (LocalbodyPK) session.save(localbody);
			session.flush();

			return localbody2.getLocalBodyCode();
		} catch (Exception e) {
			log.debug("Exception" + e);
			return localBodyCode;
		}

	}*/

	/*@Override
	public int saveLocalBodyDetailsFinal(Localbody localbody, Session session) throws Exception {
		int localBodyCode = 0;
		LocalbodyPK localbody2 = null;
		try {

			localbody2 = (LocalbodyPK) session.save(localbody);

			session.flush();
			session.refresh(localbody);
			return localbody2.getLocalBodyCode();
		} catch (Exception e) {
			log.debug("Exception" + e);
			return localBodyCode;
		}

	}*/

	public int saveOrderDetails(GovernmentOrder governmentOrder, Session session) throws Exception {
		int govtOrderNo = 0;
		try {

			govtOrderNo = (Integer) session.save(governmentOrder);
			/*
			 * session.flush(); if(session.contains(governmentOrder))
			 * session.evict(governmentOrder);
			 */

			return govtOrderNo;
		} catch (Exception e) {
			log.debug("Exception" + e);
			return govtOrderNo;
		}

	}

	public boolean saveOrderDetailsfordeletedepartment(GovernmentOrder governmentOrder) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(governmentOrder);

			tx.commit();

		} catch (Exception e) {
			log.debug("Exception" + e);
			tx.rollback();
			return false;
		} finally {
			if(session !=null && session.isOpen()){
				session.close();
			}
		}

		return true;
	}

	/*@Override
	public boolean saveDataIngovtorderentityfordeletedepartment(GovernmentOrderEntityWise governmentOrderEntityWise) {
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(governmentOrderEntityWise);
			tx.commit();
			return true;
		} catch (Exception e) {

			log.debug("Exception" + e);
		} finally {
			if(session !=null && session.isOpen()){
				session.close();
			}
		}
		return false;

	}*/

	public boolean updateOrderDetails(GovernmentOrder governmentOrder, Session session) throws Exception {

		try {

			session.saveOrUpdate(governmentOrder);
			return true;

		} catch (Exception e) {
			log.debug("Exception" + e);
			return false;
		}

	}

	public boolean saveOrderDetailsEntityWise(GovernmentOrderEntityWise governmentOrderEntityWise, Session session) throws Exception {
		try {

			session.save(governmentOrderEntityWise);
			/*
			 * session.flush(); if (session.contains(governmentOrderEntityWise))
			 * session.evict(governmentOrderEntityWise);
			 */
			return true;
		} catch (Exception e) {
			log.debug("Exception" + e);
			return false;
		}

	}

	// MapLandRegionCode for insert or update
	@Override
	public boolean updateMapLanRegion(int mapCode, String coordinates, int villageCode) throws Exception {

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			MapLandRegion mapLandRegion = (MapLandRegion) session.load(MapLandRegion.class, mapCode);
			mapLandRegion.setCoordinates(coordinates);
			mapLandRegion.setLandregionCode(villageCode);
			session.update(mapLandRegion);

			tx.commit();

		} catch (Exception e) {

			log.debug("Exception" + e);
			if (tx != null) {
				tx.rollback();
			}
			return false;
		}

		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return true;
	}

	// modify method on 21-10-2011
	/*@Override
	public boolean saveMapLandRegion(MapLandRegion mapLandRegion, Session session) throws Exception {

		try {

			session.save(mapLandRegion);
			// session.flush();
			// session.refresh(mapLandRegion);
			return true;

		} catch (Exception e) {

			log.debug("Exception" + e);

			return false;
		}

	}*/

	// Land Region Replaces details inserted
	@Override
	public boolean saveLandRegionReplacesDetails(LocalbodyReplaces localbodyReplaces, Session session) throws Exception {
		try {

			session.save(localbodyReplaces);
			session.flush();
			if (session.contains(localbodyReplaces)) {
				session.evict(localbodyReplaces);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return true;
	}

	// Land Region Covered Region details inserted
	@Override
	public boolean saveLbLandRegionCoveredDetails(LbCoveredLandregion lbCoveredLandregion, Session session) throws Exception {
		try {

			session.save(lbCoveredLandregion);
			session.flush();
			if (session.contains(lbCoveredLandregion)) {
				session.evict(lbCoveredLandregion);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return true;
	}

	// Land Region Replaced by Details
	/*@Override
	public boolean saveLandRegionReplacedByDetails(LocalbodyReplacedby localbodyReplacedby, Session session) throws Exception {
		try {

			session.save(localbodyReplacedby);

		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return true;
	}*/

	// Get LocalbodyList
	@SuppressWarnings("unchecked")
	public List<Localbody> getLocalbodyViewList(String query) throws Exception {
		List<Localbody> localbody = null;
		try {
			localbody = sessionFactory.getCurrentSession().createQuery(query).list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return localbody;
	}

	// Get Unmapped local bodies
	@SuppressWarnings("unchecked")
	public List<District> getUnmappedLocalbodyDistList(String distPList) throws Exception {

		Session session = null;
		Query query;
		List<District> distList = null;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("from District  where districtCode IN (:distPList) and isactive=true").setParameter("distPList", distPList, Hibernate.STRING);
			distList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return distList;
	}

	@SuppressWarnings("unchecked")
	public List<Subdistrict> getUnmappedSubDistLocalbodyViewList(String query) throws Exception {
		List<Subdistrict> subdistrict = null;
		try {
			subdistrict = sessionFactory.getCurrentSession().createQuery(query).list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return subdistrict;
	}

	@SuppressWarnings("unchecked")
	public List<Subdistrict> getDeleteSubDistMapped(int distlist, int subdistlist) throws Exception {
		List<Subdistrict> subdistrict = null;
		Query query = null;
		Session session = null;

		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from Subdistrict where dlc in(:distlist) and tlc in(:subdistlist) and isactive=true").setParameter("distlist", distlist, Hibernate.INTEGER).setParameter("subdistlist", subdistlist, Hibernate.INTEGER);
			subdistrict = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if(session !=null && session.isOpen()){
				session.close();
			}
		}
		return subdistrict;
	}

	@SuppressWarnings("unchecked")
	public List<Village> getDeleteVillageMapped(int subdistlist) throws Exception {
		List<Village> villages = null;
		Query query = null;
		Session session = null;

		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from Village where tlc in(:subdistlist) and isactive=true").setParameter("subdistlist", subdistlist, Hibernate.INTEGER);

			villages = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if(session !=null && session.isOpen()){
				session.close();
			}
		}
		return villages;
	}

	@SuppressWarnings("unchecked")
	public List<Village> getDeleteVillageMappedDP(int subdistlist, int villagelist) throws Exception {
		List<Village> villages = null;
		Query query = null;
		Session session = null;

		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from Village where tlc in(:subdistlist) and vlc in(:villagelist) and isactive=true").setParameter("subdistlist", subdistlist, Hibernate.INTEGER).setParameter("villagelist", villagelist, Hibernate.INTEGER);

			villages = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if(session !=null && session.isOpen()){
				session.close();
			}
		}
		return villages;
	}

	@SuppressWarnings("unchecked")
	public List<Village> getDeleteVillageMappedforSubDist(int subdistlist, int villagelist) throws Exception {
		List<Village> villages = null;
		Query query = null;
		Session session = null;

		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from Village where tlc in(:subdistlist) and vlc in(:villageList) and isactive=true").setParameter("subdistlist", subdistlist, Hibernate.INTEGER).setParameter("villageList", villagelist, Hibernate.INTEGER);

			villages = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if(session !=null && session.isOpen()){
				session.close();
			}
		}
		return villages;
	}

	@SuppressWarnings("unchecked")
	public List<Village> getUnmappedVillageLocalbodyViewList(String query) throws Exception {
		List<Village> village = null;
		try {
			village = sessionFactory.getCurrentSession().createQuery(query).list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return village;
	}

	// End here unmapped localbodies

	@SuppressWarnings("unchecked")
	@Override
	public List<Localbody> getULBListINDistrict(int districtCode) throws Exception {

		Session session = null;
		Localbody localbodybean = null;

		List<Localbody> lblist = null;
		lblist = new ArrayList<Localbody>();

		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("getULBList").setParameter("districtCode", districtCode);

			int lbcode = 0;
			int lbVersion = 0;
			String localBodyEngName = "";
			Iterator<LocalbodyByDistrict> itr = query.list().iterator();
			while (itr.hasNext()) {
				LocalbodyByDistrict element = (LocalbodyByDistrict) itr.next();
				lbcode = element.getLocalBodyCode();
				// future modification
				lbVersion = getCurrentLocalbodyVersion(lbcode);
				localBodyEngName = element.getLocalBodyNameEnglish();
				localbodybean = new Localbody();

				localbodybean.setLocalBodyNameEnglish(localBodyEngName);
				localbodybean.setLocalBodyCode(lbcode);
				localbodybean.setLocalBodyVersion(lbVersion);
				localbodybean.setOperation_state(element.getOperation_state());
				lblist.add(localbodybean);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lblist;

	}

	@Override
	public int getCurrentLocalbodyVersion(int localbodyCode) throws Exception {
		int localBodyVersion = 0;

		Query query = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select max(local_body_version) from localbody where local_body_code=:localbodyCode and isactive=true").setParameter("localbodyCode", localbodyCode, Hibernate.INTEGER);
			@SuppressWarnings("rawtypes")
			List list = query.list();
			if (!list.isEmpty() && list.get(0) != null) {
				localBodyVersion = (Integer) list.get(0);
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
		}

		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}

		return localBodyVersion;
	}

	@SuppressWarnings("unchecked")
	public List<Localbody> getLGBodySubDistListByString(String localBodyCodeList) throws Exception {
		Query criteria = null;
		Session session = null;
		List<Localbody> localbodyList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Localbody lb where parentLocalBodyCode IN (:localBodyCodeList) and isactive=true");
			criteria.setParameter("localBodyCodeList", localBodyCodeList, Hibernate.STRING);
			localbodyList = criteria.list();
		} catch (HibernateException e) {
			log.debug("Exception" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return localbodyList;
	}

	@SuppressWarnings("unchecked")
	public List<ParentWiseLocalBodies> getLocalGovtBodySubDistList(int localBodyCode, int stateCode) throws Exception {

		Session session = null;
		Query query;
		List<ParentWiseLocalBodies> lbPWaise = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalGovtBodyforParent").setParameter("localBodyCode", localBodyCode);
			lbPWaise = query.list();
		} catch (HibernateException e) {

			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lbPWaise;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Localbody> getLocalBodyList(int localBodyCode, int stateCode) throws Exception {

		Session session = null;
		Query query = null;
		List<Localbody> lbList = null;
		try {

			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalBodylist").setParameter("localBodyCode", localBodyCode).setParameter("stateCode", stateCode);
			lbList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return lbList;
	}

	/*@SuppressWarnings("unchecked")
	@Override
	public List<Localbody> getLBListByState(int stateCode) throws Exception {

		List<Localbody> getLBListByState = null;
		
		 * session=sessionFactory.openSession();
		 * System.out.println("before calling function...session.."); Query
		 * query=session.getNamedQuery("getLocalBodylist")
		 * .setParameter("localBodyCode", localBodyCode)
		 * .setParameter("stateCode", stateCode);
		 * 
		 * System.out.println("after calling function...localBodyCode.."+
		 * localBodyCode); // query.setParameter("districtCode", districtCode);
		 * System.out.println("LLLLDFDF:23-11-11--:"+query.list().size());
		 
		return getLBListByState;
	}*/

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalGovtBody> getLocalBodySubList(int localBodyCode, int stateCode) throws Exception {
		Session session = null;
		Query query = null;
		List<LocalGovtBody> getLocalBodySubList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalBodylist").setParameter("localBodyCode", localBodyCode).setParameter("stateCode", stateCode);
			getLocalBodySubList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getLocalBodySubList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ParentWiseLocalBodiesWithoutChildCount> getchildlocalbodiesByParentcodeWithoutChild(int localBodyCode) throws Exception {

		List<ParentWiseLocalBodiesWithoutChildCount> childlocalbodiesByParentcodeList = null;
		Session session = null;
		Query query = null;
		session = sessionFactory.openSession();
		try {
			query = session.getNamedQuery("getLocalGovtBodyforParentWithoutChild").setParameter("localBodyCode", localBodyCode);
			childlocalbodiesByParentcodeList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if(session !=null && session.isOpen()){
				session.close();
			}
		}

		return childlocalbodiesByParentcodeList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ParentWiseLocalBodies> getchildlocalbodiesByParentcode(int localBodyCode) throws Exception {
		List<ParentWiseLocalBodies> childlocalbodiesByParentcodeList = null;
		Session session = null;
		Query query = null;
		session = sessionFactory.openSession();
		try {
			query = session.getNamedQuery("getLocalGovtBodyforParent").setParameter("localBodyCode", localBodyCode);
			childlocalbodiesByParentcodeList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if(session !=null && session.isOpen()){
				session.close();
			}
		}
		return childlocalbodiesByParentcodeList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ParentWiseLocalBodies> getchildlocalbodiesByParentcodeChangeCoverage(int localBodyCode, int lbCode) throws Exception {

		List<ParentWiseLocalBodies> childlocalbodiesByParentcodeList = null;
		Session session = null;
		Query query = null;
		session = sessionFactory.openSession();
		try {
			query = session.getNamedQuery("getLocalGovtBodyforParentChangeCoverage").setParameter("localBodyCode", localBodyCode).setParameter("lbCode", lbCode);
			childlocalbodiesByParentcodeList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if(session !=null && session.isOpen()){
				session.close();
			}
		}

		return childlocalbodiesByParentcodeList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ParentWiseLocalBodies> getchildlocalbodiesWithoutCountByParentcode(int localBodyCode) throws Exception {
		List<ParentWiseLocalBodies> childlocalbodiesByParentcodeList = null;
		Session session = null;
		Query query = null;
		session = sessionFactory.openSession();
		try {
			query = session.getNamedQuery("getLocalGovtBodyforParentWithoutChild").setParameter("localBodyCode", localBodyCode);
			childlocalbodiesByParentcodeList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if(session !=null && session.isOpen()){
				session.close();
			}
		}

		return childlocalbodiesByParentcodeList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Localbody> getLocalGovtBodyVillageList(int localBodyCode) throws Exception {
		Session session = null;
		Query query = null;

		List<Localbody> localbodyList = new ArrayList<Localbody>();
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalGovtBodyforVillage").setParameter("localBodyCode", localBodyCode);
			localbodyList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return localbodyList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Localbody> getLocalGovtBodyParentList(int localBodyTypeCode) throws Exception {
		Session session = null;
		Query query = null;
		List<Localbody> localBodyList = new ArrayList<Localbody>();
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalGovtBodyParentList").setParameter("localBodyTypeCode", localBodyTypeCode);
			localBodyList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return localBodyList;
	}

	// getLocalGovtBodyforCoveredVillageList

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalBodyCoveredArea> getLocalGovtBodyforCoveredVillageList(int localBodyCode) throws Exception {
		Session session = null;
		Query query = null;
		List<LocalBodyCoveredArea> lgdforCoveredVillageList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getCoveredAreaForPart").setParameter("localBodyCode", localBodyCode);
			lgdforCoveredVillageList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lgdforCoveredVillageList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalBodyCoveredAreaLB> getLocalGovtBodyCoveredVillageListforLB(int localBodyCode) throws Exception {
		Session session = null;
		Query query = null;
		List<LocalBodyCoveredAreaLB> lgdforCoveredVillageList = null;
		LocalBodyCoveredAreaLB localbodyBean = null;
		lgdforCoveredVillageList = new ArrayList<LocalBodyCoveredAreaLB>();
		String landRegionName = null;

		try {
			session = sessionFactory.openSession();
			/*
			 * query = session.getNamedQuery(
			 * "getLocalGovtBodyforCoveredVillageListName").setParameter(
			 * "localBodyCode", localBodyCode);
			 */
			query = session.getNamedQuery("getLocalGovtBodyforCoveredLBVillageListName").setParameter("localBodyCode", localBodyCode);

			Iterator<LocalBodyCoveredAreaLB> itr = query.list().iterator();
			while (itr.hasNext()) {
				localbodyBean = new LocalBodyCoveredAreaLB();
				LocalBodyCoveredAreaLB element = (LocalBodyCoveredAreaLB) itr.next();
				if (element.getCoverageType() == 'P') {
					landRegionName = element.getLandRegionNameEnglish() + "(PART)";
				} else {
					landRegionName = element.getLandRegionNameEnglish();
				}
				localbodyBean.setLandRegionCode(element.getLandRegionCode());
				localbodyBean.setLandRegionNameEnglish(landRegionName);
				localbodyBean.setOperation_state(element.getOperation_state());
				lgdforCoveredVillageList.add(localbodyBean);
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lgdforCoveredVillageList;
	}

	/**
	 * Modified by Ripunj on 09-12-2014 For Local Body Draft Mode
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LocalBodyCoveredAreaLB> getLocalGovtBodyCoveredVillageListforLBforMCov(int localBodyCode) throws Exception {
		Session session = null;
		Query query = null;
		List<LocalBodyCoveredAreaLB> lgdforCoveredVillageList = null;
		LocalBodyCoveredAreaLB localbodyBean = null;
		lgdforCoveredVillageList = new ArrayList<LocalBodyCoveredAreaLB>();
		String landRegionName = null;

		try {
			session = sessionFactory.openSession();

			query = session.getNamedQuery("getLocalGovtBodyforCoveredLBVillageListName").setParameter("localBodyCode", localBodyCode);

			Iterator<LocalBodyCoveredAreaLB> itr = query.list().iterator();
			while (itr.hasNext()) {
				localbodyBean = new LocalBodyCoveredAreaLB();
				LocalBodyCoveredAreaLB element = (LocalBodyCoveredAreaLB) itr.next();
				if (element.getCoverageType() == 'P') {
					landRegionName = element.getLandRegionNameEnglish() + "(PART)";
				} else {
					landRegionName = element.getLandRegionNameEnglish();
				}
				localbodyBean.setLandRegionCode(element.getLandRegionCode());
				localbodyBean.setLocalbodyCoverage(element.getLocalbodyCoverage());
				localbodyBean.setLandRegionNameEnglish(landRegionName);
				localbodyBean.setOperation_state(element.getOperation_state());
				lgdforCoveredVillageList.add(localbodyBean);
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lgdforCoveredVillageList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalBodyCoveredAreaLB> getLocalGovtBodyCoveredDistrictListforLBforMCov(int localBodyCode) throws Exception {
		Session session = null;
		Query query = null;
		List<LocalBodyCoveredAreaLB> lgdforCoveredVillageList = null;
		LocalBodyCoveredAreaLB localbodyBean = null;
		lgdforCoveredVillageList = new ArrayList<LocalBodyCoveredAreaLB>();
		String landRegionName = null;

		try {
			session = sessionFactory.openSession();

			query = session.getNamedQuery("getLocalGovtBodyforCoveredLBDistrictList").setParameter("localBodyCode", localBodyCode);

			Iterator<LocalBodyCoveredAreaLB> itr = query.list().iterator();
			while (itr.hasNext()) {
				localbodyBean = new LocalBodyCoveredAreaLB();
				LocalBodyCoveredAreaLB element = (LocalBodyCoveredAreaLB) itr.next();
				if (element.getCoverageType() == 'P') {
					landRegionName = element.getLandRegionNameEnglish() + "(PART)";
				} else {
					landRegionName = element.getLandRegionNameEnglish();
				}
				localbodyBean.setLandRegionCode(element.getLandRegionCode());
				localbodyBean.setLocalbodyCoverage(element.getLocalbodyCoverage());
				localbodyBean.setLandRegionNameEnglish(landRegionName);
				localbodyBean.setOperation_state(element.getOperation_state());
				lgdforCoveredVillageList.add(localbodyBean);
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lgdforCoveredVillageList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalBodyCoveredArea> getLocalGovtBodyforCoveredVillageListFinal(int localBodyCode) throws Exception {
		Session session = null;
		Query query = null;
		List<LocalBodyCoveredArea> lgdforCoveredVillageList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalGovtBodyforCoveredVillageListName").setParameter("localBodyCode", localBodyCode);
			/*
			 * query =
			 * session.getNamedQuery("getLGBforCovVillListExWard").setParameter
			 * ("localBodyCode", localBodyCode);
			 */
			lgdforCoveredVillageList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return lgdforCoveredVillageList;
	}

	// getLocalGovtBodyforCoveredDistrictList
	@SuppressWarnings("unchecked")
	@Override
	public List<LocalBodyCoveredArea> getLocalGovtBodyforCoveredDistrictList(int localBodyCode) throws Exception {

		Session session = null;
		Query query = null;
		List<LocalBodyCoveredArea> localGovtBodyforCoveredDistrictList = null;
		List<LocalBodyCoveredArea> lbforCoveredDistrictList = new ArrayList<LocalBodyCoveredArea>();

		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalGovtBodyforCoveredDistrictList").setParameter("localBodyCode", localBodyCode);
			localGovtBodyforCoveredDistrictList = query.list();
			lbforCoveredDistrictList = removeNullElementFromList(localGovtBodyforCoveredDistrictList);

		} catch (Exception e) {
			log.debug("Exception" + e);

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return lbforCoveredDistrictList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalBodyCoveredArea> getLocalGovtBodyforCoveredDistrictListDistrict(int localBodyCode) throws Exception {

		Session session = null;
		Query query = null;
		List<LocalBodyCoveredArea> localGovtBodyforCoveredDistrictList = null;
		List<LocalBodyCoveredArea> lbforCoveredDistrictList = new ArrayList<LocalBodyCoveredArea>();

		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalGovtBodyforUrbanCoveredList").setParameter("localBodyCode", localBodyCode);
			localGovtBodyforCoveredDistrictList = query.list();
			lbforCoveredDistrictList = removeNullElementFromList(localGovtBodyforCoveredDistrictList);

		} catch (Exception e) {
			log.debug("Exception" + e);

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return lbforCoveredDistrictList;
	}

	// getLocalGovtBodyforCoveredDistrictList
	@SuppressWarnings("unchecked")
	@Override
	public List<LocalBodyCoveredAreaLB> getLocalGovtBodyforCoveredDistrictListFinalforLB(int localBodyCode) throws Exception {
		Session session = null;
		Query query = null;
		LocalBodyCoveredAreaLB localbodyBean = null;
		List<LocalBodyCoveredAreaLB> lbforCoveredDistrictList = new ArrayList<LocalBodyCoveredAreaLB>();
		String landRegionName = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalGovtBodyforCoveredLBDistrictList").setParameter("localBodyCode", localBodyCode);
			// localGovtBodyforCoveredDistrictList = query.list();

			// lbforCoveredDistrictList =
			// removeNullElementFromList(localGovtBodyforCoveredDistrictList);
			Iterator<LocalBodyCoveredAreaLB> itr = query.list().iterator();
			while (itr.hasNext()) {
				localbodyBean = new LocalBodyCoveredAreaLB();
				LocalBodyCoveredAreaLB element = (LocalBodyCoveredAreaLB) itr.next();
				if (element.getCoverageType() == 'P') {
					landRegionName = element.getLandRegionNameEnglish() + "(PART)";
				} else {
					landRegionName = element.getLandRegionNameEnglish();
				}
				localbodyBean.setLandRegionCode(element.getLandRegionCode());
				localbodyBean.setLandRegionNameEnglish(landRegionName);
				localbodyBean.setOperation_state(element.getOperation_state());
				lbforCoveredDistrictList.add(localbodyBean);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return lbforCoveredDistrictList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalBodyCoveredAreaLB> getLocalGovtBodyforCoveredSubDistrictListFinalforLBbyDIstCode(int localBodyCode, int districtCode) throws Exception {
		Session session = null;
		Query query = null;
		LocalBodyCoveredAreaLB localbodyBean = null;
		List<LocalBodyCoveredAreaLB> lbforCoveredSubDistrictList = new ArrayList<LocalBodyCoveredAreaLB>();
		String landRegionName = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalGovtBodyforCoveredLBSubDistListNamebyDistCode").setParameter("localBodyCode", localBodyCode).setParameter("distCode", districtCode);
			// localGovtBodyforCoveredDistrictList = query.list();

			// lbforCoveredDistrictList =
			// removeNullElementFromList(localGovtBodyforCoveredDistrictList);
			Iterator<LocalBodyCoveredAreaLB> itr = query.list().iterator();
			while (itr.hasNext()) {
				localbodyBean = new LocalBodyCoveredAreaLB();
				LocalBodyCoveredAreaLB element = (LocalBodyCoveredAreaLB) itr.next();
				if (element.getCoverageType() == 'P') {
					landRegionName = element.getLandRegionNameEnglish() + "(PART)";
				} else {
					landRegionName = element.getLandRegionNameEnglish();
				}
				localbodyBean.setLandRegionCode(element.getLandRegionCode());
				localbodyBean.setLocalbodyCoverage(element.getLocalbodyCoverage());
				localbodyBean.setLandRegionNameEnglish(landRegionName);
				localbodyBean.setOperation_state(element.getOperation_state());
				lbforCoveredSubDistrictList.add(localbodyBean);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return lbforCoveredSubDistrictList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalBodyCoveredAreaLB> getLocalGovtBodyforCoveredVillageListFinalforLBbySubDIstCode(int localBodyCode, int subdistrictCode) throws Exception {
		Session session = null;
		Query query = null;
		LocalBodyCoveredAreaLB localbodyBean = null;
		List<LocalBodyCoveredAreaLB> lbforCoveredVillageList = new ArrayList<LocalBodyCoveredAreaLB>();
		String landRegionName = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalGovtBodyforCoveredLBVillageListNamebyVillCode").setParameter("localBodyCode", localBodyCode).setParameter("subdistCode", subdistrictCode);
			// localGovtBodyforCoveredDistrictList = query.list();

			// lbforCoveredDistrictList =
			// removeNullElementFromList(localGovtBodyforCoveredDistrictList);
			Iterator<LocalBodyCoveredAreaLB> itr = query.list().iterator();
			while (itr.hasNext()) {
				localbodyBean = new LocalBodyCoveredAreaLB();
				LocalBodyCoveredAreaLB element = (LocalBodyCoveredAreaLB) itr.next();
				if (element.getCoverageType() == 'P') {
					landRegionName = element.getLandRegionNameEnglish() + "(PART)";
				} else {
					landRegionName = element.getLandRegionNameEnglish();
				}
				localbodyBean.setLandRegionCode(element.getLandRegionCode());
				localbodyBean.setLocalbodyCoverage(element.getLocalbodyCoverage());
				localbodyBean.setLandRegionNameEnglish(landRegionName);
				localbodyBean.setOperation_state(element.getOperation_state());
				lbforCoveredVillageList.add(localbodyBean);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return lbforCoveredVillageList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalBodyCoveredArea> getSubDistrictListFinal(int coverageCode, int districtCode) throws Exception {
		Session session = null;
		Query query = null;
		List<LocalBodyCoveredArea> localGovtBodyforCoveredDistrictList = null;
		List<LocalBodyCoveredArea> lbforCoveredDistrictList = new ArrayList<LocalBodyCoveredArea>();

		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalGovtBodyforCoveredSubDistFinal").setParameter("localBodyCode", coverageCode).setParameter("distCode", districtCode);
			localGovtBodyforCoveredDistrictList = query.list();
			lbforCoveredDistrictList = removeNullElementFromList(localGovtBodyforCoveredDistrictList);

		} catch (Exception e) {
			log.debug("Exception" + e);

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return lbforCoveredDistrictList;
	}

	public List<LocalBodyCoveredArea> removeNullElementFromList(List<LocalBodyCoveredArea> CoverdAreaList) throws Exception {
		List<LocalBodyCoveredArea> CoverdAreaListNew = new ArrayList<LocalBodyCoveredArea>();
		try {
			for (LocalBodyCoveredArea lBCArea : CoverdAreaList) {
				if (lBCArea.getLandRegionNameEnglish() != null) {
					CoverdAreaListNew.add(lBCArea);
				}
			}
		} catch (Exception e) {
			log.debug("Exception" + e);

		}
		return CoverdAreaListNew;

	}

	// Get Coverage List Excluding Ward
	@SuppressWarnings("unchecked")
	@Override
	public List<LBCoverageWard> getLGBforCoveredDistListExWard(int localBodyCode) throws Exception {

		Session session = null;
		Query query = null;
		List<LBCoverageWard> lgbforCoveredDistListExWard = null;
		LBCoverageWard localbodyBean = null;
		lgbforCoveredDistListExWard = new ArrayList<LBCoverageWard>();
		String landRegionName = null;

		session = sessionFactory.openSession();

		try {
			query = session.getNamedQuery("getLGBforCovDisListExWard").setParameter("localBodyCode", localBodyCode);
			// lgbforCoveredDistListExWard = query.list();
			Iterator<LBCoverageWard> itr = query.list().iterator();
			while (itr.hasNext()) {
				localbodyBean = new LBCoverageWard();
				LBCoverageWard element = (LBCoverageWard) itr.next();
				if (element.getCoverageType() == 'P') {
					landRegionName = element.getLandRegionNameEnglish() + "(PART)";
				} else {
					landRegionName = element.getLandRegionNameEnglish();
				}
				localbodyBean.setLandRegionCode(element.getLandRegionCode());
				localbodyBean.setLandRegionNameEnglish(landRegionName);
				lgbforCoveredDistListExWard.add(localbodyBean);
			}

		} catch (Exception e) {
			log.debug("Exception" + e);

		} finally {
			if(session !=null && session.isOpen()){
				session.close();
			}
		}

		return lgbforCoveredDistListExWard;
	}

	// Get Coverage List Excluding Ward
	@SuppressWarnings("unchecked")
	@Override
	public List<LBCoverageWard> getLGBforCoveredSubDistListExWard(int localBodyCode) throws Exception {

		Session session = null;
		Query query = null;
		List<LBCoverageWard> lgbforCoveredSubDistListExWard = null;
		LBCoverageWard localbodyBean = null;
		lgbforCoveredSubDistListExWard = new ArrayList<LBCoverageWard>();
		String landRegionName = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLGBforCovSubDisListExWard").setParameter("localBodyCode", localBodyCode);
			// lgbforCoveredSubDistListExWard = query.list();
			Iterator<LBCoverageWard> itr = query.list().iterator();
			while (itr.hasNext()) {
				localbodyBean = new LBCoverageWard();
				LBCoverageWard element = (LBCoverageWard) itr.next();
				if (element.getCoverageType() == 'P') {
					landRegionName = element.getLandRegionNameEnglish() + "(PART)";
				} else {
					landRegionName = element.getLandRegionNameEnglish();
				}
				localbodyBean.setLandRegionCode(element.getLandRegionCode());
				localbodyBean.setLandRegionNameEnglish(landRegionName);
				lgbforCoveredSubDistListExWard.add(localbodyBean);
			}

		} catch (Exception e) {
			log.debug("Exception" + e);

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return lgbforCoveredSubDistListExWard;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LBCoverageWard> getLGBforCoveredVillageListExWard(int localBodyCode) throws Exception {

		Session session = null;
		Query query = null;
		List<LBCoverageWard> lgbforCoveredVillageListExWard = null;
		LBCoverageWard localbodyBean = null;
		lgbforCoveredVillageListExWard = new ArrayList<LBCoverageWard>();
		String landRegionName = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLGBforCovVillListExWardforWard").setParameter("localBodyCode", localBodyCode);
			// lgbforCoveredVillageListExWard = query.list();

			Iterator<LBCoverageWard> itr = query.list().iterator();
			while (itr.hasNext()) {
				localbodyBean = new LBCoverageWard();
				LBCoverageWard element = (LBCoverageWard) itr.next();
				if (element.getCoverageType() == 'P') {
					landRegionName = element.getLandRegionNameEnglish() + "(PART)";
				} else {
					landRegionName = element.getLandRegionNameEnglish();
				}
				localbodyBean.setLandRegionCode(element.getLandRegionCode());
				localbodyBean.setLandRegionNameEnglish(landRegionName);
				lgbforCoveredVillageListExWard.add(localbodyBean);
			}

		} catch (Exception e) {
			log.debug("Exception" + e);

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return lgbforCoveredVillageListExWard;
	}

	// Get Coverage List Excluding Ward
	@SuppressWarnings("unchecked")
	@Override
	public List<LBCoverageWard> getLGBforCoveredVillListExWard(int localBodyCode) throws Exception {

		Session session = null;
		Query query = null;
		List<LBCoverageWard> lgbforCoveredVillListExWard = null;
		session = sessionFactory.openSession();

		try {
			query = session.getNamedQuery("getLGBforCovVillListExWard").setParameter("localBodyCode", localBodyCode);
			lgbforCoveredVillListExWard = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);

		} finally {
			if(session !=null && session.isOpen()){
				session.close();
			}
		}

		return lgbforCoveredVillListExWard;
	}

	// Get Coverage SubDistList Excluding Ward For Urban
	@SuppressWarnings("unchecked")
	@Override
	public List<LBCoverageWard> getLGBforCoveredSubDistUrbanListExWard(int localBodyCode) throws Exception {

		Session session = null;
		Query query = null;
		List<LBCoverageWard> lGBforCoveredSubDistUrbanListExWard = null;
		LBCoverageWard localbodyBean = null;
		lGBforCoveredSubDistUrbanListExWard = new ArrayList<LBCoverageWard>();
		String landRegionName = null;

		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLGBforCovSubDisListExWard").setParameter("localBodyCode", localBodyCode);
			// lGBforCoveredSubDistUrbanListExWard = query.list();
			Iterator<LBCoverageWard> itr = query.list().iterator();
			while (itr.hasNext()) {
				localbodyBean = new LBCoverageWard();
				LBCoverageWard element = (LBCoverageWard) itr.next();
				if (element.getCoverageType() == 'P') {
					landRegionName = element.getLandRegionNameEnglish() + "(PART)";
				} else {
					landRegionName = element.getLandRegionNameEnglish();
				}
				localbodyBean.setLandRegionCode(element.getLandRegionCode());
				localbodyBean.setLandRegionNameEnglish(landRegionName);
				lGBforCoveredSubDistUrbanListExWard.add(localbodyBean);
			}

		} catch (Exception e) {
			log.debug("Exception" + e);

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return lGBforCoveredSubDistUrbanListExWard;
	}

	// get localbody details for certain localbodycode
	/*@Override
	@SuppressWarnings("unchecked")
	public List<Localbody> getLocalGovtBodyforCoveredLocalbodyList(int localBodyCode) throws Exception {

		Query criteria = null;
		Session session = null;
		List<Localbody> localbodyList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Localbody where isactive=true and localBodyCode=:localBodyCode");
			criteria.setParameter("localBodyCode", localBodyCode, Hibernate.INTEGER);
			localbodyList = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return localbodyList;

	}*/

	// End here getLocalGovtBodyforCoveredLocalbodyList

	/*@Override
	@SuppressWarnings("unchecked")
	public List<Localbody> getLocalGovtBodyforCoveredLocalbodyList(int localBodyCode, Session session) throws Exception {
		List<Localbody> localbodyList = null;
		Query criteria = null;
		try {
			criteria = session.createQuery("from Localbody where isactive=true and localBodyCode=:localBodyCode");
			criteria.setParameter("localBodyCode", localBodyCode, Hibernate.INTEGER);
			localbodyList = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if(session !=null && session.isOpen()){
				session.close();
			}
		}
		return localbodyList;

	}
*/
	// Get UnmappedLocalBodies from localbody
	// getLocalGovtBodyforCoveredDistrictList
	@SuppressWarnings("unchecked")
	@Override
	public List<LocalbodyUnMappedBody> getLocalGovtBodyforUnmappedLocalBodyList(char localBodyType, int stateCode) throws Exception {

		Session session = null;
		Query query = null;

		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalGovtBodyforUnmappedLocalBodyList").setParameter("localBodyType", localBodyType).setParameter("stateCode", stateCode);
			return query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalbodyUnMappedBodyLevelWise> getLocalGovtBodyforUnmappedLocalBodyListLevelWise(char localBodyType, int stateCode, char level) throws Exception {

		Session session = null;
		Query query = null;

		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalGovtBodyforUnmappedLocalBodyListLevelWise").setParameter("localBodyType", localBodyType).setParameter("stateCode", stateCode).setParameter("level", level);
			return query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

	}

	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public List<LocalbodyUnMappedBodyLevelWise>
	 * getLocalGovtBodyforUnmappedLocalBodyListLevelWiseFinal( char
	 * localBodyType, List<String> arraySubDistrict,char level) throws
	 * Exception{
	 * 
	 * Session session = null; Query query = null;
	 * 
	 * try { session = sessionFactory.openSession(); query = session
	 * .getNamedQuery("getLocalGovtBodyforUnmappedLocalBodyListLevelWise")
	 * .setParameter("localBodyType", localBodyType) .setParameter("stateCode",
	 * stateCode) .setParameter("level", level); return query.list();
	 * 
	 * } catch (Exception e) { log.debug("Exception" + e); return null; }
	 * finally { if (session != null && session.isOpen()) session.close(); }
	 * 
	 * }
	 */

	@Override
	public List<LocalbodyUnMappedBodyLevelWise> getLocalGovtBodyforUnmappedLocalBodyListLevelWiseFinal(char localBodyType, List<String> arraySubDistrict, char level) throws Exception {
		Session session = null;
		Query query = null;
		List<LocalbodyUnMappedBodyLevelWise> fullMappedList = new ArrayList<LocalbodyUnMappedBodyLevelWise>();
		try {
			String[] params = null;
			for (String catStr : arraySubDistrict) {
				params = catStr.split("_");
				if (params != null) {
					session = sessionFactory.openSession();
					query = session.getNamedQuery("getLocalGovtBodyforUnmappedLocalBodyListLevelWise").setParameter("localBodyType", localBodyType, Hibernate.CHARACTER).setParameter("stateCode", Integer.valueOf(params[0]), Hibernate.INTEGER)
							.setParameter("level", level, Hibernate.CHARACTER);
					@SuppressWarnings("unchecked")
					List<LocalbodyUnMappedBodyLevelWise> list = query.list();
					if (list.isEmpty()) {
						if (localBodyType == 'T') {
							list = getAllSubDistrictListLevelWiseFinal(Integer.valueOf(params[0]));
						} else if (localBodyType == 'V') {
							list = getAllVillagesListLevelWiseFinal(Integer.valueOf(params[0]));
						}
					}
					fullMappedList.addAll(list);
				}
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return fullMappedList;
	}

	// End here getLocalGovtBodyforUnmappedLocalBodyList

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalBodyCoveredArea> getLocalGovtBodyforCoveredSubDistList(int localBodyCode) throws Exception {
		Session session = null;
		Query query = null;

		List<LocalBodyCoveredArea> lgdforCoveredSubDistList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalGovtBodyforCoveredSubDist").setParameter("localBodyCode", localBodyCode);

			lgdforCoveredSubDistList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return lgdforCoveredSubDistList;
	}

	@Override
	public List<LocalBodyCoveredAreaLB> getLocalGovtBodyforCoveredSubDistListFinal(int localBodyCode) throws Exception {
		Session session = null;
		Query query = null;
		LocalBodyCoveredAreaLB localbodyBean = null;
		List<LocalBodyCoveredAreaLB> lgdforCoveredSubDistList = null;
		lgdforCoveredSubDistList = new ArrayList<LocalBodyCoveredAreaLB>();
		String landRegionName = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalGovtBodyforCoveredLBSubDistrictList").setParameter("localBodyCode", localBodyCode);

			// lgdforCoveredSubDistList = query.list();

			@SuppressWarnings("unchecked")
			Iterator<LocalBodyCoveredAreaLB> itr = query.list().iterator();
			while (itr.hasNext()) {
				localbodyBean = new LocalBodyCoveredAreaLB();
				LocalBodyCoveredAreaLB element = (LocalBodyCoveredAreaLB) itr.next();
				if (element.getCoverageType() == 'P') {
					landRegionName = element.getLandRegionNameEnglish() + "(PART)";
				} else {
					landRegionName = element.getLandRegionNameEnglish();
				}
				localbodyBean.setLandRegionCode(element.getLandRegionCode());
				localbodyBean.setLandRegionNameEnglish(landRegionName);
				lgdforCoveredSubDistList.add(localbodyBean);
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return lgdforCoveredSubDistList;
	}

	@Override
	public List<LocalBodyCoveredAreaLB> getLocalGovtBodyforCoveredSubDistListFinalChangeCov(int localBodyCode) throws Exception {
		Session session = null;
		Query query = null;
		LocalBodyCoveredAreaLB localbodyBean = null;
		List<LocalBodyCoveredAreaLB> lgdforCoveredSubDistList = null;
		lgdforCoveredSubDistList = new ArrayList<LocalBodyCoveredAreaLB>();
		String landRegionName = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalGovtBodyforCoveredLBSubDistrictList").setParameter("localBodyCode", localBodyCode);

			// lgdforCoveredSubDistList = query.list();

			@SuppressWarnings("unchecked")
			Iterator<LocalBodyCoveredAreaLB> itr = query.list().iterator();
			while (itr.hasNext()) {
				localbodyBean = new LocalBodyCoveredAreaLB();
				LocalBodyCoveredAreaLB element = (LocalBodyCoveredAreaLB) itr.next();
				if (element.getCoverageType() == 'P') {
					landRegionName = element.getLandRegionNameEnglish() + "(PART)";
				} else {
					landRegionName = element.getLandRegionNameEnglish();
				}
				localbodyBean.setLandRegionCode(element.getLandRegionCode());
				localbodyBean.setLocalbodyCoverage(element.getLocalbodyCoverage());
				localbodyBean.setLandRegionNameEnglish(landRegionName);
				localbodyBean.setOperation_state(element.getOperation_state());
				lgdforCoveredSubDistList.add(localbodyBean);

			}

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return lgdforCoveredSubDistList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LBCoverageWard> getLocalGovtBodyforCoveredSubDistListforWard(String localBodyCode) throws Exception {
		Session session = null;
		Query query = null;

		List<LBCoverageWard> lgdforCoveredSubDistList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getCoveredAreaOfNewWard").setParameter("entityCode", localBodyCode).setParameter("type", 'D');

			lgdforCoveredSubDistList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return lgdforCoveredSubDistList;
	}

	// LocalBody StateWise

	@SuppressWarnings("unchecked")
	public List<LocalBodyTypeForStateWiseView> getLocalBodyListStateWiseView(int stateCode) throws Exception {
		Session session = null;
		Query query = null;
		List<LocalBodyTypeForStateWiseView> lgTSWiseView = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalBodylistStateWiseView").setParameter("stateCode", stateCode);
			lgTSWiseView = query.list();
		} catch (Exception e) {

			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return lgTSWiseView;
	}

	@SuppressWarnings("unchecked")
	public List<LocalGovtBodyForSelectedBody> getLocalBodyListForSelectedBody(int localBodyTypeCode, int stateCode) throws Exception {
		Session session = null;
		Query query;
		List<LocalGovtBodyForSelectedBody> lGBForSelectedB = null;
		try {
			session = sessionFactory.openSession();

			query = session.getNamedQuery("getLocalBodyListForSelectedBody1").setParameter("localBodyTypeCode", localBodyTypeCode).setParameter("stateCode", stateCode);
			lGBForSelectedB = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return lGBForSelectedB;
	}

	@SuppressWarnings("unchecked")
	public List<LocalBodyViewChild> getLocalBodyListForSubDistBody(int localBodyTypeCode, int stateCode, int localBodyCode) throws Exception {

		Session session = null;
		Query query = null;
		List<LocalBodyViewChild> lBViewChild = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalGovtBodyViewChild").setParameter("localBodyCode", localBodyCode);
			lBViewChild = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lBViewChild;

	}

	@SuppressWarnings("unchecked")
	public List<LocalBodyType> getGovtTypeViewList(String query) throws Exception {
		List<LocalBodyType> localbodytype = null;
		try {
			localbodytype = sessionFactory.getCurrentSession().createQuery(query).list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return localbodytype;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Localbody> getGovtLocalBodyTypeDetails(int localBodyCode) throws Exception {

		Query criteria = null;
		Session session = null;
		List<Localbody> localbodyList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Localbody where isactive=true and id.localBodyCode=:localBodyCode");
			criteria.setParameter("localBodyCode", localBodyCode, Hibernate.INTEGER);
			localbodyList = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return localbodyList;

	}

	@SuppressWarnings("unchecked")
	public List<LocalBodyDetails> getGovtLocalBodyDetails(int localBodyCode) throws Exception {
		Session session = null;
		Query query;
		List<LocalBodyDetails> lbDetails = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalBodyDetails").setParameter("localBodyCode", localBodyCode);
			lbDetails = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return lbDetails;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalGovtTypeDataForm> getLocalBodyTypeDetails(int localBodyTypeCode) throws Exception {
		Session session = null;
		Query query = null;
		List<LocalGovtTypeDataForm> getLocalBodyTypeList = null;

		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from LocalBodyType where isactive=true and localBodyTypeCode=:localBodyTypeCode");
			query.setParameter("localBodyTypeCode", localBodyTypeCode, Hibernate.INTEGER);
			getLocalBodyTypeList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getLocalBodyTypeList;
	}

	@SuppressWarnings("unchecked")
	public List<ChildLocalGovtBody> getChildLocalBodyListForSelectedBody(int localBodyTypeCode) throws Exception {
		Session session = null;
		List<ChildLocalGovtBody> localbodyList = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getChildLocalBodyListForSelectedBody").setParameter("localBodyTypeCode", localBodyTypeCode);

			localbodyList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return localbodyList;
	}

	/*public List<LocalbodyforStateWise> getLocalBodyListAtVillageLevel(int stateCode) throws Exception {
		List<LocalbodyforStateWise> localBodyList = null;
		Session session = null;
		Query query;
		try {
			session = sessionFactory.openSession();

			query = session.getNamedQuery("getLocalBodylistAtVillageLvl").setParameter("stateCode", stateCode);

			localBodyList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return localBodyList;
	}*/

	public String getLocalBodyTypeCode1(char category) throws Exception {
		Session session = null;

		String lbtypeCodeList = null;
		Query query = null;

		try {
			session = sessionFactory.openSession();
			if (category != 'U')
				query = session.getNamedQuery("getlbtypebyCategory").setParameter("category", category);
			else
				query = session.getNamedQuery("getUrbanlbtypebyCategory").setParameter("category", category);
			@SuppressWarnings("unchecked")
			List<GetLocalBodyTypeCode> lbTypelIst = query.list();
			Iterator<GetLocalBodyTypeCode> itr = lbTypelIst.iterator();
			while (itr.hasNext()) {
				GetLocalBodyTypeCode element = (GetLocalBodyTypeCode) itr.next();
				if (lbtypeCodeList == null) {
					lbtypeCodeList = element.getLocalBodyTypeCode().toString() + ",";
				} else {
					lbtypeCodeList = lbtypeCodeList + element.getLocalBodyTypeCode().toString() + ",";
				}
			}

		} catch (Exception e) {

			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}

		return lbtypeCodeList;
	}

	public String getLocalBodyTypeCodeNewWard(char category, char lblevel) throws Exception {
		Session session = null;

		String lbtypeCodeList = null;
		Query query = null;

		try {
			session = sessionFactory.openSession();
			if (category != 'U') {
				if (lblevel == 'D') {
					query = session.getNamedQuery("getlbtypebyCategory").setParameter("category", category);
				}
				if (lblevel == 'I') {
					query = session.getNamedQuery("getlbtypebyCategoryforInter").setParameter("category", category);
				}
				if (lblevel == 'V') {
					query = session.getNamedQuery("getlbtypebyCategoryforVillage").setParameter("category", category);
				}
			} else {
				query = session.getNamedQuery("getUrbanlbtypebyCategory").setParameter("category", category);
			}
			@SuppressWarnings("unchecked")
			List<GetLocalBodyTypeCode> lbTypelIst = query.list();
			Iterator<GetLocalBodyTypeCode> itr = lbTypelIst.iterator();
			while (itr.hasNext()) {
				GetLocalBodyTypeCode element = (GetLocalBodyTypeCode) itr.next();
				if (lbtypeCodeList == null) {
					lbtypeCodeList = element.getLocalBodyTypeCode().toString() + ",";
				} else {
					lbtypeCodeList = lbtypeCodeList + element.getLocalBodyTypeCode().toString() + ",";
				}
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lbtypeCodeList;
	}

	/*@Override
	public String getLocalBodyTypeCodeNewWardParentInter(char category, char lblevel) throws Exception {
		Session session = null;

		String lbtypeCodeList = null;
		Query query = null;

		try {
			session = sessionFactory.openSession();
			if (category != 'U') {
				query = session.getNamedQuery("getlbtypebyCategory").setParameter("category", category);
			} else {
				query = session.getNamedQuery("getUrbanlbtypebyCategory").setParameter("category", category);
			}
			List<GetLocalBodyTypeCode> lbTypelIst = query.list();
			Iterator<GetLocalBodyTypeCode> itr = lbTypelIst.iterator();
			while (itr.hasNext()) {
				GetLocalBodyTypeCode element = (GetLocalBodyTypeCode) itr.next();
				if (lbtypeCodeList == null) {
					lbtypeCodeList = element.getLocalBodyTypeCode().toString() + ",";
				} else {
					lbtypeCodeList = lbtypeCodeList + element.getLocalBodyTypeCode().toString() + ",";
				}
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lbtypeCodeList;
	}*/

	public String getLocalBodyTypeCodeNewWardParentInterDistrict(char category, char lblevel) throws Exception {
		Session session = null;

		String lbtypeCodeList = null;
		Query query = null;

		try {
			session = sessionFactory.openSession();
			if (category != 'U') {
				query = session.getNamedQuery("getlbtypebyCategory").setParameter("category", category);
			} else {
				query = session.getNamedQuery("getUrbanlbtypebyCategory").setParameter("category", category);
			}
			@SuppressWarnings("unchecked")
			List<GetLocalBodyTypeCode> lbTypelIst = query.list();
			Iterator<GetLocalBodyTypeCode> itr = lbTypelIst.iterator();
			while (itr.hasNext()) {
				GetLocalBodyTypeCode element = (GetLocalBodyTypeCode) itr.next();
				if (lbtypeCodeList == null) {
					lbtypeCodeList = element.getLocalBodyTypeCode().toString() + ",";
				} else {
					lbtypeCodeList = lbtypeCodeList + element.getLocalBodyTypeCode().toString() + ",";
				}
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lbtypeCodeList;
	}

	public String getLocalBodyTypeCode1Inter(char category) throws Exception {
		Session session = null;

		String lbtypeCodeList = null;
		Query query = null;

		try {
			session = sessionFactory.openSession();
			if (category != 'U')
				query = session.getNamedQuery("getlbtypebyCategoryforInter").setParameter("category", category);
			else
				query = session.getNamedQuery("getUrbanlbtypebyCategory").setParameter("category", category);
			@SuppressWarnings("unchecked")
			List<GetLocalBodyTypeCode> lbTypelIst = query.list();
			Iterator<GetLocalBodyTypeCode> itr = lbTypelIst.iterator();
			while (itr.hasNext()) {
				GetLocalBodyTypeCode element = (GetLocalBodyTypeCode) itr.next();
				if (lbtypeCodeList == null) {
					lbtypeCodeList = element.getLocalBodyTypeCode().toString() + ",";
				} else {
					lbtypeCodeList = lbtypeCodeList + element.getLocalBodyTypeCode().toString() + ",";
				}
			}

		} catch (Exception e) {

			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}

		return lbtypeCodeList;
	}

	public String getLocalBodyTypeCode1Village(char category) throws Exception {
		Session session = null;

		String lbtypeCodeList = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			if (category != 'U')
				query = session.getNamedQuery("getlbtypebyCategoryforVillage").setParameter("category", category);
			else
				query = session.getNamedQuery("getUrbanlbtypebyCategory").setParameter("category", category);
			@SuppressWarnings("unchecked")
			List<GetLocalBodyTypeCode> lbTypelIst = query.list();
			Iterator<GetLocalBodyTypeCode> itr = lbTypelIst.iterator();
			while (itr.hasNext()) {
				GetLocalBodyTypeCode element = (GetLocalBodyTypeCode) itr.next();
				if (lbtypeCodeList == null) {
					lbtypeCodeList = element.getLocalBodyTypeCode().toString() + ",";
				} else {
					lbtypeCodeList = lbtypeCodeList + element.getLocalBodyTypeCode().toString() + ",";
				}
			}

		} catch (Exception e) {

			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}

		return lbtypeCodeList;
	}

	public int getLocalBodyTypeCode(char category) throws Exception {
		Session session = null;

		String lbtypeCodeList = null;
		int lbTypeCode = 0;
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("getlbtypebyCategory").setParameter("category", category);
			@SuppressWarnings("unchecked")
			List<GetLocalBodyTypeCode> lbTypelIst = query.list();
			Iterator<GetLocalBodyTypeCode> itr = lbTypelIst.iterator();
			while (itr.hasNext()) {
				GetLocalBodyTypeCode element = (GetLocalBodyTypeCode) itr.next();
				if (lbtypeCodeList == null) {
					lbtypeCodeList = element.getLocalBodyTypeCode().toString() + ",";
				} else {
					lbtypeCodeList = lbtypeCodeList + element.getLocalBodyTypeCode().toString() + ",";
				}
			}
			return lbTypeCode;
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return lbTypeCode;
	}

	@SuppressWarnings("unchecked")
	public List<LocalbodyListbyState> getdistrictPanchayatList(int lbTypeCode, int stateCode) throws Exception {
		Session session = null;
		Query query = null;
		List<LocalbodyListbyState> districtPanchayatList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getNewPanchayatListbyState").setParameter("lbTypeCode", lbTypeCode).setParameter("stateCode", stateCode);
			districtPanchayatList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return districtPanchayatList;
	}

	@SuppressWarnings("unchecked")
	public List<LocalbodyListbyState> getdistrictPanchayatListChangeTier(int lbTypeCode, int stateCode, int parentLbCode) throws Exception {
		Session session = null;
		Query query = null;
		List<LocalbodyListbyState> districtPanchayatList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getNewPanchayatListbyStateChangeTier").setParameter("lbTypeCode", lbTypeCode).setParameter("stateCode", stateCode).setParameter("parentLbCode", parentLbCode);
			districtPanchayatList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return districtPanchayatList;
	}

	@SuppressWarnings("unchecked")
	public List<LocalbodyListbyState> getdistrictPanchayatListChangeCoverage(int lbTypeCode, int stateCode, int lbCode) throws Exception {
		Session session = null;
		Query query = null;
		List<LocalbodyListbyState> districtPanchayatList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getNewPanchayatListbyStateChangeCoverage").setParameter("lbTypeCode", lbTypeCode).setParameter("stateCode", stateCode).setParameter("lbCode", lbCode);
			districtPanchayatList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return districtPanchayatList;
	}

	@SuppressWarnings("unchecked")
	public List<LocalbodyListbyState> getdistrictPanchayatListbydistrictcode(String type, Integer districtcode, Integer lbtype) throws Exception {
		Session session = null;
		Query query = null;
		List<LocalbodyListbyState> districtPanchayatList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("landregionwisedistric").setParameter("local_body_type", type).setParameter("district_id", districtcode).setParameter("local_body_type_code", lbtype);
			districtPanchayatList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return districtPanchayatList;
	}

	@SuppressWarnings("unchecked")
	public List<LocalbodyListbyState> getdistrictPanchayatListforselected(int lbTypeCode, int stateCode, int localBodyCode) throws Exception {
		Session session = null;
		Query query = null;
		List<LocalbodyListbyState> districtPanchayatList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getNewPanchayatListbyStateforselected").setParameter("lbTypeCode", lbTypeCode).setParameter("stateCode", stateCode).setParameter("localbodyCode", localBodyCode);
			districtPanchayatList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return districtPanchayatList;
	}

	@SuppressWarnings("unchecked")
	public List<LocalbodyforStateWise> getTraditioanlTypebyPRITypeD(char lbTypeCode, int stateCode) throws Exception {
		Session session = null;
		Query query = null;
		List<LocalbodyforStateWise> traditionalLocalbodyTypeList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalBodylistStateWiseForTraditionalD").setParameter("stateCode", stateCode).setParameter("localBodyType", 'T');
			traditionalLocalbodyTypeList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return traditionalLocalbodyTypeList;
	}

	@SuppressWarnings("unchecked")
	public List<LocalbodyforStateWise> getTraditioanlTypebyPRITypeI(char lbTypeCode, int stateCode) throws Exception {
		Session session = null;
		Query query = null;
		List<LocalbodyforStateWise> traditionalLocalbodyTypeList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalBodylistStateWiseForTraditionalI").setParameter("stateCode", stateCode).setParameter("localBodyType", 'T');
			traditionalLocalbodyTypeList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return traditionalLocalbodyTypeList;
	}

	@SuppressWarnings("unchecked")
	public List<LocalbodyforStateWise> getTraditioanlTypebyPRITypeV(char lbTypeCode, int stateCode) throws Exception {
		Session session = null;
		Query query = null;
		List<LocalbodyforStateWise> traditionalLocalbodyTypeList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalBodylistStateWiseForTraditionalV").setParameter("stateCode", stateCode).setParameter("localBodyType", 'T');
			traditionalLocalbodyTypeList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return traditionalLocalbodyTypeList;
	}

	/*@Override
	public String getUrbanLocalBodyTypes(char category) throws Exception {

		Session session = null;

		String lbtypeCodeList = null;

		try {
			session = sessionFactory.openSession();

			Query query = session.getNamedQuery("getUrbanlbtypebyCategory").setParameter("category", category);

			List<GetLocalBodyTypeCode> lbTypelIst = query.list();
			Iterator<GetLocalBodyTypeCode> itr = lbTypelIst.iterator();
			while (itr.hasNext()) {
				GetLocalBodyTypeCode element = (GetLocalBodyTypeCode) itr.next();
				if (lbtypeCodeList == null) {
					lbtypeCodeList = element.getLocalBodyTypeCode().toString() + ",";
				} else {
					lbtypeCodeList = lbtypeCodeList + element.getLocalBodyTypeCode().toString() + ",";
				}
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return lbtypeCodeList;
	}*/

	@SuppressWarnings("unchecked")
	public List<GetLocalBodyTypeCode> getUrbanLocalBodyTypes1(char category) throws Exception {
		Session session = null;
		Query query;
		List<GetLocalBodyTypeCode> getLocalBodyTypeCode = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getUrbanlbtypebyCategory").setParameter("category", category);
			getLocalBodyTypeCode = query.list();
		} catch (Exception e) {

			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getLocalBodyTypeCode;
	}

	@SuppressWarnings("unchecked")
	public List<LocalBodyCoveredArea> getCoveredAreaforULB(int localbodyCode) throws Exception {
		Session session = null;
		Query query = null;

		List<LocalBodyCoveredArea> lgdforCoveredSubDistList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalGovtBodyforCoveredSubDist").setParameter("localBodyCode", localbodyCode);
			lgdforCoveredSubDistList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return lgdforCoveredSubDistList;
	}

	public List<LocalBodyCoveredAreaLB> getCoveredAreaforULBforLB(int localbodyCode) throws Exception {
		Session session = null;
		Query query = null;

		List<LocalBodyCoveredAreaLB> lgdforCoveredSubDistList = null;
		lgdforCoveredSubDistList = new ArrayList<LocalBodyCoveredAreaLB>();
		LocalBodyCoveredAreaLB localbodyBean = null;
		String landRegionName = null;

		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalGovtBodyforCoveredLBSubDistrictList").setParameter("localBodyCode", localbodyCode);

			@SuppressWarnings("unchecked")
			Iterator<LocalBodyCoveredAreaLB> itr = query.list().iterator();
			while (itr.hasNext()) {
				localbodyBean = new LocalBodyCoveredAreaLB();
				LocalBodyCoveredAreaLB element = (LocalBodyCoveredAreaLB) itr.next();
				if (element.getCoverageType() == 'P') {
					landRegionName = element.getLandRegionNameEnglish() + "(PART)";
				} else {
					landRegionName = element.getLandRegionNameEnglish();
				}
				localbodyBean.setLandRegionCode(element.getLandRegionCode());
				localbodyBean.setLandRegionNameEnglish(landRegionName);
				lgdforCoveredSubDistList.add(localbodyBean);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lgdforCoveredSubDistList;
	}

	public List<LocalBodyCoveredAreaLB> getCoveredAreaforULBforLBforMF(int localbodyCode) throws Exception {
		Session session = null;
		Query query = null;

		List<LocalBodyCoveredAreaLB> lgdforCoveredSubDistList = null;
		lgdforCoveredSubDistList = new ArrayList<LocalBodyCoveredAreaLB>();
		LocalBodyCoveredAreaLB localbodyBean = null;
		String landRegionName = null;

		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalGovtBodyforCoveredLBSubDistrictList").setParameter("localBodyCode", localbodyCode);

			@SuppressWarnings("unchecked")
			Iterator<LocalBodyCoveredAreaLB> itr = query.list().iterator();
			while (itr.hasNext()) {
				localbodyBean = new LocalBodyCoveredAreaLB();
				LocalBodyCoveredAreaLB element = (LocalBodyCoveredAreaLB) itr.next();
				if (element.getCoverageType() == 'P') {
					landRegionName = element.getLandRegionNameEnglish() + "(PART)";
				} else {
					landRegionName = element.getLandRegionNameEnglish();
				}
				localbodyBean.setLandRegionCode(element.getLandRegionCode());
				localbodyBean.setLocalbodyCoverage(element.getLocalbodyCoverage());
				localbodyBean.setLandRegionNameEnglish(landRegionName);
				localbodyBean.setOperation_state(element.getOperation_state());
				lgdforCoveredSubDistList.add(localbodyBean);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lgdforCoveredSubDistList;
	}

	public String getlocalbodyNamebyCode(int localbodyCode) throws Exception {
		Session session = null;
		Query query = null;
		String lbname = null;
		try {
			session = sessionFactory.openSession();
			query = session.createQuery("select localBodyNameEnglish from Localbody where isactive=true and id.localBodyCode=:localbodyCode");
			query.setParameter("localbodyCode", localbodyCode, Hibernate.INTEGER);
			lbname = query.list().get(0).toString();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return lbname;
	}

	// Urban LocalBody StateWise
	@SuppressWarnings("unchecked")
	@Override
	public List<LocalBodyUrbanType> getLocalBodyUrbanName(int localBodyCode, String urbanTypeBodyCode, int stateCode, int localBodyTypeCode) throws Exception {
		Session session = null;
		List<LocalBodyUrbanType> lBUrbanType = null;
		Query query;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalBodyUrbanName").setParameter("localBodyTypeCode", localBodyTypeCode).setParameter("localBodyCode", localBodyCode).setParameter("localBodyType", urbanTypeBodyCode)
					.setParameter("stateCode", stateCode);
			lBUrbanType = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return lBUrbanType;
	}

	@SuppressWarnings("unchecked")
	public List<SearchLocalGovtBody> getLocalGovtBodySearchDetail(String entityName, String entityCode) throws Exception {
		Session session = null;
		List<SearchLocalGovtBody> searchLgb = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalGovtBodySearchDetails").setParameter("entityName", entityName).setParameter("entityCode", entityCode);
			searchLgb = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return searchLgb;
	}

	@SuppressWarnings("unchecked")
	public List<SearchLocalGovtBody> getLocalGovtBodySearchDetailByCode(int entityCodeForSearch, String entityCode) throws Exception {
		Session session = null;
		List<SearchLocalGovtBody> searchLgb = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalGovtBodySearchDetailsByCode").setParameter("entityCodeForSearch", entityCodeForSearch).setParameter("entityCode", entityCode);
			searchLgb = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return searchLgb;
	}

	@SuppressWarnings("unchecked")
	public List<LocalBodyTypeHistory> getLocalBodyTypeHistory(String hqlString) throws Exception {
		List<LocalBodyTypeHistory> listLocalBodyTypeHistory = new ArrayList<LocalBodyTypeHistory>();
		Session session = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.createQuery(hqlString);
			listLocalBodyTypeHistory = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return listLocalBodyTypeHistory;
	}

	@SuppressWarnings("unchecked")
	public List<LocalGovtBodyWard> getlocalGovtBodyWardList(int localBodyCode) throws Exception {
		Session session = null;
		List<LocalGovtBodyWard> lGBWard = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalGovtBodyWardListView").setParameter("lblc", localBodyCode);
			lGBWard = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lGBWard;
	}

	@SuppressWarnings("unchecked")
	public List<LocalGovtBodyWard> getlocalGovtBodyWardListforpagination(int localBodyCode, int offset, int limit) {
		Session session = null;
		List<LocalGovtBodyWard> lGBWard = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalGovtBodyWardListView").setParameter("lblc", localBodyCode).setFirstResult(offset).setMaxResults(limit);
			lGBWard = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lGBWard;
	}

	@Override
	public boolean invalidateWard(int wardCode, int userid) throws Exception {
		Session session = null;
		Transaction txn = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			txn = session.beginTransaction();
			query = session.getNamedQuery("invalidateWardFn");
			query.setParameter("wardCode", wardCode, Hibernate.INTEGER);
			query.setParameter("userid", userid, Hibernate.INTEGER);
			query.list();
			txn.commit();
			return true;
		} catch (Exception e) {
			log.debug("Exception" + e);
			if (txn != null) {
				txn.rollback();
			}
			return false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public boolean deleteWard(int wardCode, int userid) throws Exception {
		Session session = null;
		Transaction txn = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			txn = session.beginTransaction();

			query = session.createSQLQuery("UPDATE localbody_ward set isactive=false where ward_code=" + wardCode);
			query.executeUpdate();

			txn.commit();
			return true;
		} catch (Exception e) {
			log.debug("Exception" + e);
			if (txn != null) {
				txn.rollback();
			}
			return false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public List<UnmappedLBList> getUnmappedLBDistList(char category, int stateCode) throws Exception {

		Session session = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getUnmappedLBList").setParameter("category", category, Hibernate.CHARACTER).setParameter("stateCode", stateCode, Hibernate.INTEGER);

			@SuppressWarnings("unchecked")
			List<UnmappedLBList> unMappedList = query.list();

			return unMappedList;
		} catch (Exception e) {
			log.debug("Exception" + e);

		}

		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return null;
	}

	/**
	 * Modified by sushil
	 */
	@Override
	public List<PartillyMappedLRList> getPartillymappedLBDistListFinal(char landRegionType, List<String> arraySubDistrict, char category) throws Exception {
		Session session = null;
		Query query = null;
		List<PartillyMappedLRList> partMappedList = new ArrayList<PartillyMappedLRList>();
		try {
			String[] params = null;
			for (String catStr : arraySubDistrict) {
				params = catStr.split("_");
				if (params != null) {
					session = sessionFactory.openSession();
					query = session.getNamedQuery("getpartillymappedLBList").setParameter("landRegionType", landRegionType, Hibernate.CHARACTER).setParameter("stateCode", Integer.valueOf(params[0]), Hibernate.INTEGER)
							.setParameter("lbtype", params[1].charAt(0), Hibernate.CHARACTER);
					@SuppressWarnings("unchecked")
					List<PartillyMappedLRList> list = query.list();
					if (list.isEmpty()) {
						if (landRegionType == 'T') {
							list = getAllSubDistrictList(Integer.valueOf(params[0]));
						} else if (landRegionType == 'V') {
							list = getAllVillagesList(Integer.valueOf(params[0]));
						}
					}
					partMappedList.addAll(list);
				}
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return partMappedList;
	}

	/**
	 * Added by Arnab
	 */
	@Override
	public List<PartillyMappedLRListLevelWise> getPartillymappedLBDistListFinalLevelWise(char landRegionType, List<String> arraySubDistrict, char category, char level) throws Exception {
		Session session = null;
		Query query = null;
		List<PartillyMappedLRListLevelWise> partMappedList = new ArrayList<PartillyMappedLRListLevelWise>();
		try {
			String[] params = null;
			for (String catStr : arraySubDistrict) {
				params = catStr.split("_");
				if (params != null) {
					session = sessionFactory.openSession();
					query = session.getNamedQuery("getpartillymappedLBListLevelWise").setParameter("landRegionType", landRegionType, Hibernate.CHARACTER).setParameter("stateCode", Integer.valueOf(params[0]), Hibernate.INTEGER)
							.setParameter("level", level, Hibernate.CHARACTER).setParameter("lbtype", params[1].charAt(0), Hibernate.CHARACTER);
					@SuppressWarnings("unchecked")
					List<PartillyMappedLRListLevelWise> list = query.list();
					if (list.isEmpty()) {
						if (landRegionType == 'T') {
							list = getAllSubDistrictListLevelWise(Integer.valueOf(params[0]));
						} else if (landRegionType == 'V') {
							list = getAllVillagesListLevelWise(Integer.valueOf(params[0]));
						}
					}
					partMappedList.addAll(list);
				}
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return partMappedList;
	}

	private List<PartillyMappedLRListLevelWise> getAllSubDistrictListLevelWise(Integer valueOf) {
		Session session = null;
		SQLQuery query = null;
		// List<PartillyMappedLRList> sublist = null;
		List<PartillyMappedLRListLevelWise> partMappedList = new ArrayList<PartillyMappedLRListLevelWise>();
		PartillyMappedLRListLevelWise mappedLRList = null;
		try {
			session = sessionFactory.openSession();
			/*
			 * query = session .createQuery(
			 * "select case when A.subdistrictCode in () A from Subdistrict A where A.dlc="
			 * + valueOf + "  and A.isactive=true");
			 */

			query = session.createSQLQuery("select  case when A.subdistrict_code  in (select local_body_code from get_draft_used_lb_lr_temp(A.subdistrict_code,'T')) then "
					+ " cast ('F' as character) else cast('A' as character) end as operation_state,A.subdistrict_code as subdistrictCode," + " A.subdistrict_name_english as subdistrictNameEnglish" + "  from subdistrict A where A.dlc= " + valueOf
					+ "  and A.isactive=true ");

			query.addScalar("operation_state");
			query.addScalar("subdistrictCode");
			query.addScalar("subdistrictNameEnglish");
			query.setResultTransformer(Transformers.aliasToBean(Subdistrict.class));
			@SuppressWarnings("unchecked")
			List<Subdistrict> list = query.list();
			if (!list.isEmpty()) {
				// sublist = new ArrayList<PartillyMappedLRList>();
				// mappedLRList = new PartillyMappedLRList();
				for (Subdistrict subdistrict : list) {
					mappedLRList = new PartillyMappedLRListLevelWise();
					mappedLRList.setLandRegionCode(subdistrict.getSubdistrictCode());
					mappedLRList.setLocalBodyNameEnglish(subdistrict.getSubdistrictNameEnglish());

				}
				partMappedList.add(mappedLRList);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return partMappedList;
	}

	private List<PartillyMappedLRListLevelWise> getAllVillagesListLevelWise(Integer valueOf) {
		Session session = null;
		SQLQuery query = null;

		List<PartillyMappedLRListLevelWise> partMappedList = new ArrayList<PartillyMappedLRListLevelWise>();
		PartillyMappedLRListLevelWise mappedLRList = null;
		try {
			session = sessionFactory.openSession();
			/*
			 * query =
			 * session.createQuery("select A from Village A where A.tlc=" +
			 * valueOf + "  and A.isactive=true");
			 */

			query = session.createSQLQuery("select  case when A.village_code  in (select local_body_code from get_draft_used_lb_lr_temp(A.village_code,'T')) then "
					+ " cast ('F' as character) else cast('A' as character) end as operation_state,A.village_code as villageCode," + " A.village_name_english as villageNameEnglish" + "  from village A where A.tlc= " + valueOf
					+ "  and A.isactive=true ");

			query.addScalar("operation_state");
			query.addScalar("villageCode");
			query.addScalar("villageNameEnglish");
			query.setResultTransformer(Transformers.aliasToBean(Village.class));

			@SuppressWarnings("unchecked")
			List<Village> list = query.list();
			if (!list.isEmpty()) {

				for (Village village : list) {
					mappedLRList = new PartillyMappedLRListLevelWise();
					mappedLRList.setLandRegionCode(village.getVillageCode());
					mappedLRList.setLocalBodyNameEnglish(village.getVillageNameEnglish());
				}
				partMappedList.add(mappedLRList);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return partMappedList;
	}

	private List<LocalbodyUnMappedBodyLevelWise> getAllSubDistrictListLevelWiseFinal(Integer valueOf) {
		Session session = null;
		SQLQuery query = null;
		// List<PartillyMappedLRList> sublist = null;
		List<LocalbodyUnMappedBodyLevelWise> fullMappedList = new ArrayList<LocalbodyUnMappedBodyLevelWise>();
		LocalbodyUnMappedBodyLevelWise mappedLRList = null;
		try {
			session = sessionFactory.openSession();
			/*
			 * query = session
			 * .createQuery("select A from Subdistrict A where A.dlc=" + valueOf
			 * + "  and A.isactive=true");
			 */

			query = session.createSQLQuery("select  case when A.subdistrict_code  in (select local_body_code from get_draft_used_lb_lr_temp(A.subdistrict_code,'T')) then "
					+ " cast ('F' as character) else cast('A' as character) end as operation_state,A.subdistrict_code as subdistrictCode," + " A.subdistrict_name_english as subdistrictNameEnglish" + "  from subdistrict A where A.dlc= " + valueOf
					+ "  and A.isactive=true ");

			query.addScalar("operation_state");
			query.addScalar("subdistrictCode");
			query.addScalar("subdistrictNameEnglish");
			query.setResultTransformer(Transformers.aliasToBean(Subdistrict.class));
			@SuppressWarnings("unchecked")
			List<Subdistrict> list = query.list();
			if (!list.isEmpty()) {
				// sublist = new ArrayList<PartillyMappedLRList>();
				// mappedLRList = new PartillyMappedLRList();
				for (Subdistrict subdistrict : list) {
					mappedLRList = new LocalbodyUnMappedBodyLevelWise();
					mappedLRList.setLandRegionCode(subdistrict.getSubdistrictCode());
					mappedLRList.setLocalBodyNameEnglish(subdistrict.getSubdistrictNameEnglish());

				}
				fullMappedList.add(mappedLRList);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return fullMappedList;
	}

	private List<LocalbodyUnMappedBodyLevelWise> getAllVillagesListLevelWiseFinal(Integer valueOf) {
		Session session = null;
		SQLQuery query = null;

		List<LocalbodyUnMappedBodyLevelWise> fullMappedList = new ArrayList<LocalbodyUnMappedBodyLevelWise>();
		LocalbodyUnMappedBodyLevelWise mappedLRList = null;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select  case when A.village_code  in (select local_body_code from get_draft_used_lb_lr_temp(A.village_code,'T')) then "
					+ " cast ('F' as character) else cast('A' as character) end as operation_state,A.village_code as villageCode," + " A.village_name_english as villageNameEnglish" + " from village A where A.tlc= " + valueOf
					+ "  and A.isactive=true ");

			query.addScalar("operation_state");
			query.addScalar("villageCode");
			query.addScalar("villageNameEnglish");
			query.setResultTransformer(Transformers.aliasToBean(Village.class));
			@SuppressWarnings("unchecked")
			List<Village> list = query.list();
			if (!list.isEmpty()) {

				for (Village village : list) {
					mappedLRList = new LocalbodyUnMappedBodyLevelWise();
					mappedLRList.setLandRegionCode(village.getVillageCode());
					mappedLRList.setLocalBodyNameEnglish(village.getVillageNameEnglish());
				}
				fullMappedList.add(mappedLRList);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return fullMappedList;
	}

	private List<PartillyMappedLRList> getAllSubDistrictList(Integer valueOf) {
		Session session = null;
		Query query = null;
		// List<PartillyMappedLRList> sublist = null;
		List<PartillyMappedLRList> partMappedList = new ArrayList<PartillyMappedLRList>();
		PartillyMappedLRList mappedLRList = null;
		try {
			session = sessionFactory.openSession();
			query = session.createQuery("select A from Subdistrict A where A.dlc=" + valueOf + "  and A.isactive=true");
			@SuppressWarnings("unchecked")
			List<Subdistrict> list = query.list();
			if (!list.isEmpty()) {
				// sublist = new ArrayList<PartillyMappedLRList>();
				// mappedLRList = new PartillyMappedLRList();
				for (Subdistrict subdistrict : list) {
					mappedLRList = new PartillyMappedLRList();
					mappedLRList.setLandRegionCode(subdistrict.getSubdistrictCode());
					mappedLRList.setLocalBodyNameEnglish(subdistrict.getSubdistrictNameEnglish());

				}
				partMappedList.add(mappedLRList);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return partMappedList;
	}

	private List<PartillyMappedLRList> getAllVillagesList(Integer valueOf) {
		Session session = null;
		Query query = null;

		List<PartillyMappedLRList> partMappedList = new ArrayList<PartillyMappedLRList>();
		PartillyMappedLRList mappedLRList = null;
		try {
			session = sessionFactory.openSession();
			query = session.createQuery("select A from Village A where A.tlc=" + valueOf + "  and A.isactive=true");
			@SuppressWarnings("unchecked")
			List<Village> list = query.list();
			if (!list.isEmpty()) {

				for (Village village : list) {
					mappedLRList = new PartillyMappedLRList();
					mappedLRList.setLandRegionCode(village.getVillageCode());
					mappedLRList.setLocalBodyNameEnglish(village.getVillageNameEnglish());
				}
				partMappedList.add(mappedLRList);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return partMappedList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PartillyMappedLRList> getPartillymappedLBDistList(char landRegionType, int stateCode, char category) throws Exception {
		Session session = null;
		Query query = null;
		List<PartillyMappedLRList> partMappedList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getpartillymappedLBList").setParameter("landRegionType", landRegionType, Hibernate.CHARACTER).setParameter("stateCode", stateCode, Hibernate.INTEGER).setParameter("lbtype", category, Hibernate.CHARACTER);
			partMappedList = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return partMappedList;
	}

	@SuppressWarnings("unchecked")
	public List<LocalBodyCoveredArea> getCoveredSubDistrictLocalBody(int localBodyCode) throws Exception {
		Session session = null;
		Query query = null;
		List<LocalBodyCoveredArea> getCoveredSubDistrictLocalBody = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalGovtBodyforUrbanCoveredList").setParameter("localBodyCode", localBodyCode, Hibernate.INTEGER);

			getCoveredSubDistrictLocalBody = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);

		}

		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getCoveredSubDistrictLocalBody;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GetLocalBodyTypeCode> getTypeListbylevel(char typeCode, char category) throws Exception {
		Session session = null;
		Query query = null;
		List<GetLocalBodyTypeCode> lbTypeList = null;
		try {
			session = sessionFactory.openSession();
			if (typeCode == 'D') {
				query = session.getNamedQuery("getlbtypebyCategory").setParameter("category", category, Hibernate.CHARACTER);
			} else if (typeCode == 'I') {
				query = session.getNamedQuery("getlbtypebyCategoryforInter").setParameter("category", category, Hibernate.CHARACTER);
			} else if (typeCode == 'V') {
				query = session.getNamedQuery("getlbtypebyCategoryforVillage").setParameter("category", category, Hibernate.CHARACTER);
			}
			lbTypeList = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);

		}

		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lbTypeList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalbodyWard> getwardDetail(int wardCode) throws Exception {
		// TODO Auto-generated method stub

		Query criteria = null;
		Session session = null;
		List<LocalbodyWard> localbodyWardList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from LocalbodyWard where isactive=true and id.wardCode=:wardCode");
			criteria.setParameter("wardCode", wardCode, Hibernate.INTEGER);
			localbodyWardList = criteria.list();
		} catch (HibernateException e) {
			log.debug("Exception" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return localbodyWardList;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GetLandRegionNameforWard> getDistrictNameWard(int wardCode) throws Exception {
		Query query = null;
		Session session = null;
		List<GetLandRegionNameforWard> landRegionWardDistrict = null;
		try {
			session = sessionFactory.openSession();

			query = session.getNamedQuery("landregionnameforwardDistrict").setParameter("ward_code", wardCode, Hibernate.INTEGER);

			landRegionWardDistrict = query.list();
		} catch (HibernateException e) {
			log.debug("Exception" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return landRegionWardDistrict;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GetLandRegionNameforWard> getSubDistrictNameWard(int wardCode) throws Exception {
		Query query = null;
		Session session = null;
		List<GetLandRegionNameforWard> landRegionWardSubDistrict = null;
		try {
			session = sessionFactory.openSession();

			query = session.getNamedQuery("landregionnameforwardSubDistrict").setParameter("ward_code", wardCode, Hibernate.INTEGER);

			landRegionWardSubDistrict = query.list();
		} catch (HibernateException e) {
			log.debug("Exception" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return landRegionWardSubDistrict;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GetLandRegionNameforWard> getVillageNameWard(int wardCode) throws Exception {
		Query query = null;
		Session session = null;
		List<GetLandRegionNameforWard> landRegionWardVillage = null;
		try {
			session = sessionFactory.openSession();

			query = session.getNamedQuery("landregionnameforwardVillage").setParameter("ward_code", wardCode, Hibernate.INTEGER);

			landRegionWardVillage = query.list();
		} catch (HibernateException e) {
			log.debug("Exception" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return landRegionWardVillage;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CoveredWardLandregion> getCoverageLangRegion(int wardCode) throws Exception {
		Query criteria = null;
		Session session = null;
		List<CoveredWardLandregion> coverageWardLregion = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from CoveredWardLandregion where isactive=true and localbodyWard.id.wardCode=:wardCode");
			criteria.setParameter("wardCode", wardCode, Hibernate.INTEGER);
			coverageWardLregion = criteria.list();
		} catch (HibernateException e) {
			log.debug("Exception" + e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return coverageWardLregion;
	}

	@Override
	public boolean updateisActive(LocalbodyWard organizationbeanisActive, LocalbodyWardId wardpk, Session session1) throws Exception {
		// TODO updateisActive
		try {
			/*
			 * organizationbeanisActive=(Organization)session1.get(Organization.
			 * class, orgpk2);
			 * organizationbeanisActive.setOrganizationPK(orgpk2);
			 */
			organizationbeanisActive.setIsactive(false);
			session1.update(organizationbeanisActive);
		} catch (Exception e) {
			log.debug("Exception" + e);
			return false;
		}
		return true;
	}

	/*@Override
	public int getMaxWardVersion(int wardCode) throws Exception {
		// TODO Auto-generated method stub
		int MaxVersionCode = 0;
		Session session = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select max(ward_version) from LocalbodyWard where ward_code=:wardCode").setParameter("wardCode", wardCode, Hibernate.INTEGER);
			List list = query.list();
			if (!list.isEmpty() && list.get(0) != null) {
				MaxVersionCode = (Integer) list.get(0);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return MaxVersionCode;
	}*/

	/*
	 * @Override public boolean update(LocalGovtBodyForm localGovtBodyForm,
	 * LocalbodyWardId wardpk, Session session1) throws Exception{ // TODO
	 * Auto-generated method stub try {
	 * 
	 * Organization
	 * organizationbean=(Organization)session1.get(Organization.class, orgpk);
	 * organizationbean.setOrganizationPK(orgpk);
	 * organizationbean.setOrgName(ministryForm.getMinistryNamecr());
	 * organizationbean .setShortName(ministryForm.getShortministryName());
	 * 
	 * // session1.update(organizationbean); // session1.flush(); } catch
	 * (HibernateException e) { log.debug("Exception" + e); return false; }
	 * return true; }
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<GetGovernmentOrderDetail> GovOrderDetail(char entityType, int entityCode, int entityVersion) throws Exception {
		Session session = null;
		Query criteria = null;
		List<GetGovernmentOrderDetail> gtGovernmentOrderDetail = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.getNamedQuery("GovOrderDetail").setParameter("entityType", entityType).setParameter("entityCode", entityCode).setParameter("entityVersion", entityVersion);
			gtGovernmentOrderDetail = criteria.list();

		} catch (Exception e) {
			log.debug("Exception" + e);
		}

		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return gtGovernmentOrderDetail;

	}

	public int getWardCurrentVersion(int wardCode) throws Exception {
		Session session = null;
		Query query = null;
		int MaxVersionCode = 0;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select ward_version from localbody_ward where ward_code= :wardCode and isactive=true").setParameter("wardCode", wardCode);
			@SuppressWarnings("rawtypes")
			List list = query.list();
			if (!list.isEmpty() && list.get(0) != null) {
				MaxVersionCode = (Integer) list.get(0);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return MaxVersionCode;
	}

	/*@Override
	@SuppressWarnings("unchecked")
	public List<MapLocalbody> getMapDetails(int maplocalbodyCode) throws Exception {
		Query criteria = null;
		Session session = null;
		List<MapLocalbody> mapLocalbodyList = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from MapLocalbody where mapLocalbodyCode =:maplocalbodyCode").setParameter("maplocalbodyCode", maplocalbodyCode, Hibernate.INTEGER);
			mapLocalbodyList = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return mapLocalbodyList;

	}*/

	@SuppressWarnings("unchecked")
	public List<LocalBodyTypeWiseDepartment> getLocalBodyTypeWiseDeptList(int stateCode, int lBTypeCode, char levelCode) throws Exception {
		List<LocalBodyTypeWiseDepartment> getLocalBodyTypeWiseDeptList = null;

		Session session = null;
		Query query = null;

		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLBTWiseDepartmentDetails").setParameter("stateCode", stateCode).setParameter("lBTypeCode", lBTypeCode).setParameter("levelCode", CommonUtil.setCategoryLevel(levelCode));

			getLocalBodyTypeWiseDeptList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return getLocalBodyTypeWiseDeptList;
	}

	@SuppressWarnings("unchecked")
	public List<LocalBodyTypeWiseDepartment> getLocalBodyWiseDeptList(int stateCode, int lBCode, char levelCode) throws Exception {

		List<LocalBodyTypeWiseDepartment> getLocalBodyWiseDeptList = null;
		Session session = null;

		Query query = null;

		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLBWiseDepartmentDetails").setParameter("stateCode", stateCode).setParameter("lBCode", lBCode).setParameter("levelCode", CommonUtil.setCategoryLevel(levelCode));

			getLocalBodyWiseDeptList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return getLocalBodyWiseDeptList;
	}

	@SuppressWarnings("unchecked")
	public List<LocalbodyListbyState> getExistingPanchayatList(int lbTypeCode, int stateCode) throws Exception {
		Session session = null;
		Query query = null;
		List<LocalbodyListbyState> districtPanchayatList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getExistingPanchayatListbyState").setParameter("lbTypeCode", lbTypeCode, Hibernate.INTEGER).setParameter("stateCode", stateCode, Hibernate.INTEGER);
			districtPanchayatList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return districtPanchayatList;
	}

	@SuppressWarnings("unchecked")
	public List<UnLRSWiseList> getUnMapLRStaWiseList(char type, int stateCode) throws Exception {
		Session session = null;
		Query query = null;
		List<UnLRSWiseList> unMapLRStaWiseList = null;

		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getStWiseUnMLR").setParameter("type", type, Hibernate.CHARACTER).setParameter("stateCode", stateCode, Hibernate.INTEGER);
			unMapLRStaWiseList = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return unMapLRStaWiseList;
	}

	@SuppressWarnings("unchecked")
	public List<UnLRDistrictWiseList> getUnMapLRDistWiseList(char type, int districtCode) throws Exception {
		Session session = null;
		Query query = null;
		List<UnLRDistrictWiseList> unMapLRDistrictWiseList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getDistWiseUnMLR").setParameter("type", type, Hibernate.CHARACTER).setParameter("districtCode", districtCode, Hibernate.INTEGER);
			unMapLRDistrictWiseList = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return unMapLRDistrictWiseList;
	}

	@SuppressWarnings("unchecked")
	public List<PartillyMappedLRList> getPartlyMapLRStaWiseList(char type, int stateCode) throws Exception {
		Session session = null;
		Query query = null;
		List<PartillyMappedLRList> partMapLRStaWiseList = null;

		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getpartillymapLandRegionList").setParameter("landRegionType", type, Hibernate.CHARACTER).setParameter("stateCode", stateCode, Hibernate.INTEGER);
			partMapLRStaWiseList = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return partMapLRStaWiseList;

	}

	@SuppressWarnings("unchecked")
	@Override
	public GovernmentOrder viewgovernmentOrder(int orderCode) {
		Session session = null;
		GovernmentOrder governmentOrder = null;
		List<GovernmentOrder> governmentOrderList = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(GovernmentOrder.class);
			criteria.add(Restrictions.eq("orderCode", orderCode));

			governmentOrderList = criteria.list();

			governmentOrder = governmentOrderList.get(0);

		} catch (Exception e) {

			return governmentOrder;
			// throw new Exception();
		}

		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return governmentOrder;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalbodyWard> getwardByWardCode(int wardCode) {
		Session session = null;
		Query query;
		List<LocalbodyWard> lbWard = null;
		try {
			session = sessionFactory.openSession();
			query = session.createQuery("From LocalbodyWard Where isactive=true AND wardCode  =:wardCode").setParameter("wardCode", wardCode, Hibernate.INTEGER);
			lbWard = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lbWard;
	}

	/**
	 * added the one more parameter for checking the existing name
	 */
	public int getRecordsforLocalBody(String localBodyName, String parentlbName, String lbType, char lbtypeULB, String lbTypeCode) throws Exception {
		Query criteria = null;
		Session session = null;

		int recordCount = 0;
		try {
			session = sessionFactory.openSession();
			String lbName = localBodyName.trim().toUpperCase();
			if (lbType.equals("D")) {
				criteria = session.createSQLQuery("select count(*) from localbody where UPPER(TRIM(local_body_name_english)) LIKE :lbName and slc= :parentlbName and local_body_type_code=:lbcodetypecode");
				criteria.setParameter("lbcodetypecode", Integer.parseInt(lbTypeCode), Hibernate.INTEGER);
			} else if (lbType.equals("I") || lbType.equals("V")) {
				criteria = session.createSQLQuery("select count(*) from localbody where UPPER(TRIM(local_body_name_english)) LIKE :lbName and parent_lblc= :parentlbName");
			} else if (lbtypeULB == 'U') {
				criteria = session
						.createSQLQuery("select count(*) from localbody,local_body_type where UPPER(TRIM(local_body_name_english)) LIKE :lbName and localbody.slc= :parentlbName and localbody.local_body_type_code=local_body_type.local_body_type_code and local_body_type.category='U'");

			}
			criteria.setParameter("lbName", lbName, Hibernate.STRING);
			criteria.setParameter("parentlbName", Integer.parseInt(parentlbName), Hibernate.INTEGER);
			@SuppressWarnings("rawtypes")
			List list = criteria.list();
			if (!list.isEmpty() && list.get(0) != null) {
				recordCount = Integer.parseInt(list.get(0).toString());
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return recordCount;

	}

	public int getRecordsforWardNumber(String wardNumber, int lbName) throws Exception {
		Query criteria = null;
		Session session = null;

		int recordCount = 0;
		try {
			session = sessionFactory.openSession();
			String wardNos = wardNumber.trim().toUpperCase();
			criteria = session.createSQLQuery("select count(*) from localbody_ward where UPPER(TRIM(ward_number)) LIKE :wardNum and lblc= :lbName and isactive=true");
			criteria.setParameter("wardNum", wardNos, Hibernate.STRING);
			criteria.setParameter("lbName", lbName, Hibernate.INTEGER);
			@SuppressWarnings("rawtypes")
			List list = criteria.list();
			if (!list.isEmpty() && list.get(0) != null) {
				recordCount = Integer.parseInt(list.get(0).toString());
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return recordCount;

	}

	@Override
	public int getlblc(int localbodyCd) throws Exception {
		Session session = null;
		Query query;

		int lblcCode = 0;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("Select lblc From localbody Where isactive=true AND local_body_code =:lbcd").setParameter("lbcd", localbodyCd, Hibernate.INTEGER);

			@SuppressWarnings("rawtypes")
			List list = query.list();
			if (!list.isEmpty() && list.get(0) != null) {
				lblcCode = (Integer) list.get(0);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lblcCode;
	}

	@Override
	public int getRecordsforWardNumberModify(String wardNumber, int lblc) throws Exception {
		Query criteria = null;
		Session session = null;

		int recordCount = 0;
		try {
			session = sessionFactory.openSession();
			String wardNos = wardNumber.trim().toUpperCase();
			criteria = session.createSQLQuery("select count(*) from localbody_ward where UPPER(TRIM(ward_number)) LIKE :wardNum and lblc= :lbName");
			criteria.setParameter("wardNum", wardNos, Hibernate.STRING);
			criteria.setParameter("lbName", lblc, Hibernate.INTEGER);
			@SuppressWarnings("rawtypes")
			List list = criteria.list();
			if (!list.isEmpty() && list.get(0) != null) {
				recordCount = Integer.parseInt(list.get(0).toString());
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return recordCount;

	}

	@Override
	public List<Localbody> getULBListByDistrict(int districtCode) throws Exception {
		Session session = null;
		Localbody localbodybean = null;

		List<Localbody> lblist = null;
		lblist = new ArrayList<Localbody>();

		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("get_ulb_list_by_district_code_fn").setParameter("districtCode", districtCode);

			//int lbcode = 0;
			//int lbVersion = 0;
			@SuppressWarnings("unchecked")
			Iterator<LocalbodyByDistrict> itr = query.list().iterator();
			while (itr.hasNext()) {
				LocalbodyByDistrict element = (LocalbodyByDistrict) itr.next();
				//lbcode = element.getLocalBodyCode();
				//lbVersion = getCurrentLocalbodyVersion(lbcode);
				localbodybean = new Localbody();
				localbodybean.setLocalBodyNameEnglish(element.getLocalBodyNameEnglish());
				lblist.add(localbodybean);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lblist;
	}

	@SuppressWarnings("unchecked")
	public List<CheckAuthorization> getSubDistrictList(int districtcode) {
		Session session = null;
		Query query = null;
		List<CheckAuthorization> subdistrictList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getcheckAuthorization").setParameter("entity_type", "T", Hibernate.CHARACTER).setParameter("entity_parent_type", "D", Hibernate.STRING).setParameter("entity_parent_code", districtcode, Hibernate.INTEGER)
					.setParameter("entity_code", null, Hibernate.INTEGER);
			subdistrictList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return subdistrictList;
	}

	@SuppressWarnings("unchecked")
	public List<CheckAuthorization> getvillageList(int villagecode) {
		Session session = null;
		Query query = null;
		List<CheckAuthorization> villageList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getcheckAuthorization").setParameter("entity_type", "V", Hibernate.CHARACTER).setParameter("entity_parent_type", "T", Hibernate.STRING).setParameter("entity_parent_code", villagecode, Hibernate.INTEGER)
					.setParameter("entity_code", null, Hibernate.INTEGER);
			villageList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return villageList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalBodyCoveredArea> getvillageListFinal(int coverageCode, int subdistrictCode) throws Exception {
		Session session = null;
		Query query = null;
		List<LocalBodyCoveredArea> localGovtBodyforCoveredVillageList = null;
		List<LocalBodyCoveredArea> lbforCoveredVillageList = new ArrayList<LocalBodyCoveredArea>();

		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalGovtBodyforCoveredVillageFinal").setParameter("localBodyCode", coverageCode).setParameter("subdistCode", subdistrictCode);
			localGovtBodyforCoveredVillageList = query.list();
			lbforCoveredVillageList = removeNullElementFromList(localGovtBodyforCoveredVillageList);
		} catch (Exception e) {
			log.debug("Exception" + e);

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lbforCoveredVillageList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean saveDataInAttachGenerateLocalBodyCoverageChange(LocalGovtBodyForm localGovtBodyForm, HttpSession httpsession, GovernmentOrder governmentOrder) {
		Transaction tx = null;
		Session session = null;
		//Attachment attachment = null;
		Query query, query1 = null;
		//MapAttachment mapAttachment = null;
		Long attachmentid = 0L;
		List<Attachment> attachmentList = new ArrayList<Attachment>();

		GenerateDetails genDetails = (GenerateDetails) httpsession.getAttribute("validGovermentGenerateUpload");
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.merge(governmentOrder);
			int orderCode = governmentOrder.getOrderCode();
			/*
			 * query=session.createSQLQuery(
			 * "select attachment_id from attachment where announcement_id="
			 * +orderCode); List list = query.list(); if (!list.isEmpty() &&
			 * list.get(0) != null) { Long attachtId =
			 * 
			 * attachmentid =(Long)list.get(0); }
			 */

			query = session.createQuery("from Attachment where announcement_id=:orderCode").setParameter("orderCode", orderCode, Hibernate.INTEGER);
			attachmentList = query.list();

			Iterator<Attachment> attachmentsIterator = attachmentList.iterator();
			while (attachmentsIterator.hasNext()) {
				Attachment att = (Attachment) attachmentsIterator.next();
				attachmentid = att.getAttachmentId();
			}

			if (genDetails != null) {
				query1 = session.createSQLQuery("UPDATE attachment set file_name='" + genDetails.getFileName() + "', file_size= " + genDetails.getFileSize() + " , file_content_type ='" + genDetails.getFileContentType() + "',  file_location='"
						+ genDetails.getFileLocation() + "',  file_timestamp='" + genDetails.getFileTimestamp() + "' where announcement_id= " + orderCode + " and attachment_id=" + attachmentid);
				query1.executeUpdate();
				tx.commit();

			}

			return true;
		} catch (Exception e) {

			log.debug("Exception" + e);
		} finally {
			if(session !=null && session.isOpen()){
				session.close();
			}
		}
		return false;
	}

	/**
	 * Modified by Sushil on 21-01-2013
	 */
	/*@Override
	public boolean saveDataInMapLB(LocalGovtBodyForm localGovtBodyForm, GovernmentOrder governmentOrder, HttpSession httpsession, List<AttachedFilesForm> attachedList, List<AttachedFilesForm> attachedMapList) {
		Transaction tx = null;
		Session session = null;
		Attachment attachment = null;
		MapAttachment mapAttachment = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.merge(governmentOrder);

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
					attachment.setGovernmentOrder(governmentOrder);
					session.merge(attachment);
				}
			}

			if (attachedMapList != null) {
				Iterator<AttachedFilesForm> it = attachedMapList.iterator();
				while (it.hasNext()) {
					AttachedFilesForm filesForm = (AttachedFilesForm) it.next();
					mapAttachment = new MapAttachment();
					mapAttachment.setFileName(filesForm.getFileName());
					mapAttachment.setFileSize(filesForm.getFileSize());
					mapAttachment.setFileContentType(filesForm.getFileType());
					mapAttachment.setFileLocation(filesForm.getFileLocation());
					mapAttachment.setFileTimestamp(filesForm.getFileTimestampName());
					mapAttachment.setMapEntityType('G');
					mapAttachment.setMapEntityCode(localGovtBodyForm.getLocalBodyCode());
					session.save(mapAttachment);
				}
			}
			tx.commit();
			return true;
		} catch (Exception e) {

			log.debug("Exception" + e);
		} finally {
			if(session !=null && session.isOpen()){
				session.close();
			}
		}
		return false;
	}
*/
	@Override
	public MapAttachment saveDataInMapLBGovtOrderCorrect(LocalGovtBodyForm localGovtBodyForm, GovernmentOrder governmentOrder, Session session, List<AttachedFilesForm> attachedList, List<AttachedFilesForm> attachedMapList) {
		// Transaction tx = null;
		Attachment attachment = null;
		MapAttachment mapAttachment = null;
		try {
			// session = sessionFactory.openSession();
			// tx = session.beginTransaction();
			session.merge(governmentOrder);

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
					attachment.setGovernmentOrder(governmentOrder);
					session.merge(attachment);
				}
			}

			if (attachedMapList != null) {
				Iterator<AttachedFilesForm> it = attachedMapList.iterator();
				while (it.hasNext()) {
					AttachedFilesForm filesForm = (AttachedFilesForm) it.next();
					mapAttachment = new MapAttachment();
					mapAttachment.setFileName(filesForm.getFileName());
					mapAttachment.setFileSize(filesForm.getFileSize());
					mapAttachment.setFileContentType(filesForm.getFileType());
					mapAttachment.setFileLocation(filesForm.getFileLocation());
					mapAttachment.setFileTimestamp(filesForm.getFileTimestampName());
					mapAttachment.setMapEntityType('G');
					mapAttachment.setMapEntityCode(localGovtBodyForm.getLocalBodyCode());
					session.save(mapAttachment);
				}
			}
			// tx.commit();

		} catch (Exception e) {

			log.debug("Exception" + e);
		}
		return mapAttachment;
	}

	/*@Override
	public boolean saveDataInMapLBGovtOrderCorrectWithoutMap(LocalGovtBodyForm localGovtBodyForm, GovernmentOrder governmentOrder, Session session, List<AttachedFilesForm> attachedList) {
		Transaction tx = null;
		Attachment attachment = null;
		MapAttachment mapAttachment = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.merge(governmentOrder);
			session.flush();

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
					attachment.setGovernmentOrder(governmentOrder);
					session.merge(attachment);
					session.flush();
				}
			}

			tx.commit();
			

		} catch (Exception e) {

			log.debug("Exception" + e);
			return false;
		}
		finally {
			if(session !=null && session.isOpen()){
				session.close();
			}
		}
		
		return true;
	}*/

	public boolean deleteMapAttachementforLB(Integer entityCode, char entityType, Session session) {
		try {
			Query query = session.createQuery("delete from MapAttachment where mapEntityType = :mapEntityType and map_lc=:map_lc");
			query.setParameter("mapEntityType", entityType);
			query.setParameter("map_lc", entityCode);
			int result = query.executeUpdate();
			session.clear();
			session.flush();
			if (result > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			log.debug("Exception" + e);
			return false;
		}

	}

	/*@Override
	public String saveDataInMapLBGovtOrderCorrectWithoutOrderCode(LocalGovtBodyForm localGovtBodyForm, GovernmentOrder governmentOrder, Session session, List<AttachedFilesForm> attachedList, List<AttachedFilesForm> attachedMapList) {
		// Transaction tx = null;
		Attachment attachment = null;
		MapAttachment mapAttachment = null;
		String returnVal = null;
		try {
			// session = sessionFactory.openSession();
			// tx = session.beginTransaction();
			session.save(governmentOrder);

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
					attachment.setGovernmentOrder(governmentOrder);
					session.merge(attachment);
				}
			}

			if (attachedMapList != null) {
				Iterator<AttachedFilesForm> it = attachedMapList.iterator();
				while (it.hasNext()) {
					AttachedFilesForm filesForm = (AttachedFilesForm) it.next();
					mapAttachment = new MapAttachment();
					mapAttachment.setFileName(filesForm.getFileName());
					mapAttachment.setFileSize(filesForm.getFileSize());
					mapAttachment.setFileContentType(filesForm.getFileType());
					mapAttachment.setFileLocation(filesForm.getFileLocation());
					mapAttachment.setFileTimestamp(filesForm.getFileTimestampName());
					mapAttachment.setMapEntityType('G');
					mapAttachment.setMapEntityCode(localGovtBodyForm.getLocalBodyCode());
					session.save(mapAttachment);
				}
			}

			returnVal = governmentOrder.getOrderCode() + "," + mapAttachment.getAttachmentId();
			// tx.commit();
		} catch (Exception e) {
			log.debug("Exception" + e);

		}
		return returnVal;
	}*/

	/*@Override
	public int saveDataInMapLBGovtOrderCorrectWithoutOrderCodeAndMap(LocalGovtBodyForm localGovtBodyForm, GovernmentOrder governmentOrder, Session session, List<AttachedFilesForm> attachedList) {
		// Transaction tx = null;
		Attachment attachment = null;
		MapAttachment mapAttachment = null;
		int returnVal = 0;
		try {
			// session = sessionFactory.openSession();
			// tx = session.beginTransaction();
			session.save(governmentOrder);
			session.flush();

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
					attachment.setGovernmentOrder(governmentOrder);
					session.merge(attachment);
				}
			}
			returnVal = governmentOrder.getOrderCode();
			// tx.commit();
		} catch (Exception e) {
			log.debug("Exception" + e);

		}
		return returnVal;
	}*/

	@Override
	public int saveDataInMapLBGovtOrderCorrectWithMap(LocalGovtBodyForm localGovtBodyForm, GovernmentOrder governmentOrder, Session session, List<AttachedFilesForm> attachedList, List<AttachedFilesForm> attachedMapList) {
		// Transaction tx = null;
		//Attachment attachment = null;
		MapAttachment mapAttachment = null;
		int returnVal = 0;
		try {
			// session = sessionFactory.openSession();
			// tx = session.beginTransaction();
			// session.save(governmentOrder);

			if (attachedMapList != null) {
				Iterator<AttachedFilesForm> it = attachedMapList.iterator();
				while (it.hasNext()) {
					AttachedFilesForm filesForm = (AttachedFilesForm) it.next();
					mapAttachment = new MapAttachment();
					mapAttachment.setFileName(filesForm.getFileName());
					mapAttachment.setFileSize(filesForm.getFileSize());
					mapAttachment.setFileContentType(filesForm.getFileType());
					mapAttachment.setFileLocation(filesForm.getFileLocation());
					mapAttachment.setFileTimestamp(filesForm.getFileTimestampName());
					mapAttachment.setMapEntityType('G');
					mapAttachment.setMapEntityCode(localGovtBodyForm.getLocalBodyCode());
					session.save(mapAttachment);
				}
			}

			returnVal = (int) mapAttachment.getAttachmentId();
			// tx.commit();
		} catch (Exception e) {
			log.debug("Exception" + e);

		}
		return returnVal;
	}

	/*@Override
	public int saveDataInMapLBGovtOrderCorrectWithMapMerge(LocalGovtBodyForm localGovtBodyForm, GovernmentOrder governmentOrder, Session session, List<AttachedFilesForm> attachedList, List<AttachedFilesForm> attachedMapList) {
		Transaction tx = null;
		Attachment attachment = null;
		MapAttachment mapAttachment = null;
		int returnVal = 0;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			// session.save(governmentOrder);

			if (attachedMapList != null) {
				Iterator<AttachedFilesForm> it = attachedMapList.iterator();
				while (it.hasNext()) {
					AttachedFilesForm filesForm = (AttachedFilesForm) it.next();
					mapAttachment = new MapAttachment();
					mapAttachment.setFileName(filesForm.getFileName());
					mapAttachment.setFileSize(filesForm.getFileSize());
					mapAttachment.setFileContentType(filesForm.getFileType());
					mapAttachment.setFileLocation(filesForm.getFileLocation());
					mapAttachment.setFileTimestamp(filesForm.getFileTimestampName());
					mapAttachment.setMapEntityType('G');
					mapAttachment.setMapEntityCode(localGovtBodyForm.getLocalBodyCode());
					session.merge(mapAttachment);
				}
			}

			returnVal = (int) mapAttachment.getAttachmentId();
			tx.commit();
		} catch (Exception e) {
			log.debug("Exception" + e);

		} finally {
			if(session !=null && session.isOpen()){
				session.close();
			}
		}
		return returnVal;
	}*/

	@Override
	public int saveDataGovtOrder(LocalGovtBodyForm localGovtBodyForm, GovernmentOrder governmentOrder, Session session, List<AttachedFilesForm> attachedList) {
		// Transaction tx = null;
		Attachment attachment = null;
		//MapAttachment mapAttachment = null;
		int orderCode = 0;
		try {
			// session = sessionFactory.openSession();
			// tx = session.beginTransaction();
			session.update(governmentOrder);
			orderCode = governmentOrder.getOrderCode();

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
					attachment.setGovernmentOrder(governmentOrder);
					session.merge(attachment);
				}
			}

			// tx.commit();
		} catch (Exception e) {
			log.debug("Exception" + e);

		}
		return orderCode;
	}

	@Override
	public int saveDataGovtOrderwithoutMap(LocalGovtBodyForm localGovtBodyForm, GovernmentOrder governmentOrder, Session session, List<AttachedFilesForm> attachedList) {
		// Transaction tx = null;
		Attachment attachment = null;
		//MapAttachment mapAttachment = null;
		int orderCode = 0;
		try {
			// session = sessionFactory.openSession();
			// tx = session.beginTransaction();
			session.save(governmentOrder);
			orderCode = governmentOrder.getOrderCode();

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
					attachment.setGovernmentOrder(governmentOrder);
					session.merge(attachment);
				}
			}

			// tx.commit();
		} catch (Exception e) {
			log.debug("Exception" + e);

		}
		return orderCode;
	}

	@Override
	public int saveDataInMapLBGovtOrderCorrectWithoutOrderCodeM(LocalGovtBodyForm localGovtBodyForm, GovernmentOrder governmentOrder, Session session, List<AttachedFilesForm> attachedList) {
		// Transaction tx = null;
		Attachment attachment = null;
		//MapAttachment mapAttachment = null;
		int orderCode = 0;
		try {
			// session = sessionFactory.openSession();
			// tx = session.beginTransaction();
			session.saveOrUpdate(governmentOrder);
			session.flush();

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
					attachment.setGovernmentOrder(governmentOrder);
					session.merge(attachment);
					session.flush();

				}
			}

			orderCode = governmentOrder.getOrderCode();
			// tx.commit();
		} catch (Exception e) {
			log.debug("Exception" + e);

		}
		return orderCode;
	}

	/*@Override
	public boolean saveDataIngovtorderentity(LocalGovtBodyForm localGovtBodyForm, GovernmentOrderEntityWise governmentOrderEntityWise, Session httpsession) {
		Transaction tx = null;
		Session session = null;
		Localbody localBodyDetails1 = new Localbody();
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.merge(governmentOrderEntityWise);
			tx.commit();
			return true;
		} catch (Exception e) {

			log.debug("Exception" + e);
		} finally {
			if(session !=null && session.isOpen()){
				session.close();
			}
		}
		return false;

	}
*/
	@Override
	public boolean saveDataIngovtorderentityD(LocalGovtBodyForm localGovtBodyForm, GovernmentOrderEntityWise governmentOrderEntityWise, Session session) {
		// Transaction tx = null;
		// Session session = null;
		//Localbody localBodyDetails1 = new Localbody();
		try {
			// session = sessionFactory.openSession();
			// tx = session.beginTransaction();
			// session.save(governmentOrderEntityWise);
			session.saveOrUpdate(governmentOrderEntityWise);
			// tx.commit();
			return true;
		} catch (Exception e) {

			log.debug("Exception" + e);
		}
		return false;

	}

	@Override
	public boolean saveDataIngovtorderentityGenerate(LocalGovtBodyForm localGovtBodyForm, GovernmentOrderEntityWise governmentOrderEntityWise, HttpSession httpsession) {
		Transaction tx = null;
		Session session = null;
		//Localbody localBodyDetails1 = new Localbody();
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.merge(governmentOrderEntityWise);
			tx.commit();
			return true;
		} catch (Exception e) {

			log.debug("Exception" + e);
		} finally {
			if(session !=null && session.isOpen()){
				session.close();
			}
		}
		return false;

	}

	@Override
	public boolean saveLatLongDet(LocalGovtBodyForm localGovtBodyForm, Session session) {
		// Transaction tx = null;
		String upd = null;
		try {
			// tx = session.beginTransaction();
			if (localGovtBodyForm.getMap_attachment_code() != null) {
				upd = "update Localbody set coordinates='" + localGovtBodyForm.getCoordinates() + "', warningflag=" + localGovtBodyForm.isWarningflag() + ", map_attachment_code=" + localGovtBodyForm.getMap_attachment_code()
						+ " where local_body_code=" + localGovtBodyForm.getLocalBodyCode() + " and isactive=true ";
			} else if (localGovtBodyForm.getMap_attachment_code() == null && localGovtBodyForm.getCoordinates() != null) {
				upd = "update Localbody set coordinates='" + localGovtBodyForm.getCoordinates() + "', warningflag=" + localGovtBodyForm.isWarningflag() + ", map_attachment_code=" + null + " where local_body_code="
						+ localGovtBodyForm.getLocalBodyCode() + " and isactive=true ";
			} else if (localGovtBodyForm.getMap_attachment_code() == null && localGovtBodyForm.getCoordinates() == null) {
				upd = "update Localbody set coordinates=" + null + ", warningflag=" + localGovtBodyForm.isWarningflag() + ", map_attachment_code=" + null + " where local_body_code=" + localGovtBodyForm.getLocalBodyCode() + " and isactive=true ";
			}

			session.createSQLQuery(upd).executeUpdate();
			// tx.commit();
			return true;
		} catch (Exception e) {

			log.debug("Exception" + e);
		}
		return false;
	}

	@Override
	public boolean saveLatLongDetMapSame(LocalGovtBodyForm localGovtBodyForm, Session session) {
		// Transaction tx = null;
		try {
			// tx = session.beginTransaction();

			String upd = "update Localbody set coordinates='" + localGovtBodyForm.getCoordinates() + "', warningflag=" + localGovtBodyForm.isWarningflag() + " where local_body_code=" + localGovtBodyForm.getLocalBodyCode() + " and isactive=true ";
			session.createSQLQuery(upd).executeUpdate();
			// tx.commit();
			return true;
		} catch (Exception e) {

			log.debug("Exception" + e);
		}
		return false;
	}

	
	@SuppressWarnings("unchecked")
	public synchronized List<InsertLocalGovtBody> saveLocalBody(LocalGovtBodyForm localGovtBodyForm, String lgTypeName, String lgtypecode, int stateCode, Integer userId, String panchayatDP) {
		Session session = null;
		Query query = null;
		String lbDistictArea = null;
		String existingLocalBData = null;
		String unmappedLocalBData = null;
		String lbSubDistictArea = null;
		String lbVillageArea = null;
		//String lbDestInter = null;
		String lbDestListInter = null;
		String lbVillageListInter = null;
		String lbVillageDest = null;
		String unmappedDistforDP = null;
		String unmappedSubDistforDP = null;
		String unmappedVillforDP = null;
		String unmappedSubDistforIP = null;
		String unmappedVillforIP = null;
		Integer localBodyTypeCode = null;
		Integer parentLocalbody = null;
		String stateSpecificCode = null;
		String[] existingLocalBDataFinal = null;
		String[] existingUnMappedLocalBDataFinal = null;
		String combinedLandregion = null;
		String combinedUnMappedLandregion = null;
		String finalLandRegion = null;
		int operationCode = 0;
		int flagCode = 0;
		String transdesc = null;
		List<InsertLocalGovtBody> valueReturn = null;
		String localBodyNameEng = localGovtBodyForm.getLgd_LBNameInEn();
		String aliasEnglish = localGovtBodyForm.getLgd_LBAliasInEn();
		String aliasLocal = localGovtBodyForm.getLgd_LBAliasInLocal();
		String localbodyNameLocal = localGovtBodyForm.getLgd_LBNameInLocal();
		String lbhiddenDistrict = null;
		String lbhiddenSubDistrict = null;
		String lbhiddenVillage = null;
		//String parentLocalbodyName = null;
		/* if(!stateSpecificCode.equals(null)) */
		if (localGovtBodyForm.getLgd_LBstateSpecificCode() != null) {
			stateSpecificCode = localGovtBodyForm.getLgd_LBstateSpecificCode();
		}

		int lbodytypecd = Integer.parseInt(lgtypecode);
		if (localGovtBodyForm.isLgd_LBExistCheck()) {
			panchayatDP.replaceAll("_PART", "").replaceAll("_FULL", "");
		}
		String pesa = localGovtBodyForm.getLgd_LBPesaAct();
		String orderNo = localGovtBodyForm.getLgd_LBorderNo();
		Date orderDate = localGovtBodyForm.getLgd_LBorderDate();
		Date effectiveDate = localGovtBodyForm.getLgd_LBeffectiveDate();
		Date gazDate = localGovtBodyForm.getLgd_LBgazPubDate();
		String orderPath = "govtOrderUpload";
		String latitude = localGovtBodyForm.getlatitude();
		String longitude = localGovtBodyForm.getLongitude();
		String[] arrayLati = latitude.split(",");
		String[] arrayLong = longitude.split(",");
		StringBuffer sb = null;
		String gisNodes = null;
		// if((localGovtBodyForm.getlatitude() !=null ||
		// localGovtBodyForm.getlatitude() !="") &&
		// (localGovtBodyForm.getLongitude() !=null ||
		// localGovtBodyForm.getLongitude() !=""))
		if (!localGovtBodyForm.getlatitude().equals("") && !localGovtBodyForm.getLongitude().equals("")) {
			sb = new StringBuffer();
			int count = 1;
			for (int i = 0; i < arrayLati.length; i++) {
				for (int j = 0; j < arrayLong.length; j++) {
					if (i == j) {
						if (count == 1) {
							String temp = arrayLati[i] + ":" + arrayLong[i];
							sb.append(temp);
							count++;
						} else {
							sb.append(",");
							String temp = arrayLati[i] + ":" + arrayLong[i];
							sb.append(temp);
						}
					}
				}
			}
		}

		if (!localGovtBodyForm.getlatitude().equals("") && !localGovtBodyForm.getLongitude().equals("")) {
			gisNodes = sb.toString();
		}
		flagCode = localGovtBodyForm.getFlagCode();


		operationCode = localGovtBodyForm.getOperationCode();

		// final String upLoadMap = localGovtBodyForm.getFileMapUpLoad();

		if (lgTypeName.equals("D")) {
			if (localGovtBodyForm.isLgd_LBExistCheck()) {
				lbDistictArea = localGovtBodyForm.getLgd_LBDistCAreaDestList();
				lbSubDistictArea = localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA();
				lbVillageArea = localGovtBodyForm.getLgd_LBVillageDestLatDCA();
				lbhiddenDistrict = localGovtBodyForm.getLgd_LBDistPDestListhidden();
				if (localGovtBodyForm.getHeadQuarterCode() == null) {
					lbDistictArea = lbDistictArea + "_true";
				}

				if (lbDistictArea != null && lbSubDistictArea != null && lbVillageArea != null) {
					if (lbhiddenDistrict != null) {
						existingLocalBData = lbDistictArea + "," + lbhiddenDistrict + "," + lbSubDistictArea + "," + lbVillageArea;
					} else {
						existingLocalBData = lbDistictArea + "," + lbSubDistictArea + "," + lbVillageArea;
					}
				}
				if (lbSubDistictArea != null && lbVillageArea == null) {
					existingLocalBData = lbDistictArea + "," + lbSubDistictArea;
				}
				if (lbDistictArea != null && lbSubDistictArea == null && lbVillageArea == null) {
					if (lbhiddenDistrict != null) {
						existingLocalBData = localGovtBodyForm.getLgd_LBDistCAreaDestList() + "," + lbhiddenDistrict;
					} else {
						existingLocalBData = localGovtBodyForm.getLgd_LBDistCAreaDestList();
					}
				}
				if (lbDistictArea == null && lbSubDistictArea == null && lbVillageArea == null) {
					if (lbhiddenDistrict != null) {
						existingLocalBData = lbhiddenDistrict;
					}
				}

				StringBuffer sb2 = new StringBuffer();
				if (localGovtBodyForm.getHeadQuarterCode() != null && existingLocalBData != null) {
					existingLocalBDataFinal = existingLocalBData.split(",");

					for (int i = 0; i < existingLocalBDataFinal.length; i++) {
						if (localGovtBodyForm.getHeadQuarterCode().equals(existingLocalBDataFinal[i])) {
							String temp = existingLocalBDataFinal[i] + "_true";
							sb2.append(temp);
							sb2.append(",");
						} else {
							if (localGovtBodyForm.getHeadQuarterCode().equals(existingLocalBDataFinal[i])) {
								sb2.append(",");
							}
							String temp = existingLocalBDataFinal[i] + "_false";
							sb2.append(temp);
							if (i + 1 < existingLocalBDataFinal.length) {
								sb2.append(",");
							}
						}
					}
				}
				if (localGovtBodyForm.getHeadQuarterCode() != null) {
					combinedLandregion = sb2.toString();
				} else {
					combinedLandregion = existingLocalBData;
				}
			}
			if (localGovtBodyForm.isLgd_LBUnmappedCheck()) {
				unmappedDistforDP = localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped();
				unmappedSubDistforDP = localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped();
				unmappedVillforDP = localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped();
				if (localGovtBodyForm.getHeadQuarterCode() == null) {
					unmappedDistforDP = unmappedDistforDP + "_true";
				}

				if (unmappedSubDistforDP != null) {
					unmappedLocalBData = unmappedDistforDP + "," + unmappedSubDistforDP;
				}
				if (unmappedVillforDP != null) {
					unmappedLocalBData = unmappedDistforDP + "," + unmappedVillforDP;
				}
				if (unmappedSubDistforDP != null && unmappedVillforDP != null) {
					unmappedLocalBData = unmappedDistforDP + "," + unmappedSubDistforDP + "," + unmappedVillforDP;
				}
				if (unmappedSubDistforDP == null && unmappedVillforDP == null) {
					unmappedLocalBData = localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped();

				}
				StringBuffer sb2 = new StringBuffer();
				if (localGovtBodyForm.getHeadQuarterCode() != null) {
					existingUnMappedLocalBDataFinal = unmappedLocalBData.split(",");
					for (int i = 0; i < existingUnMappedLocalBDataFinal.length; i++) {
						if (localGovtBodyForm.getHeadQuarterCode().equals(existingUnMappedLocalBDataFinal[i])) {
							String temp = existingUnMappedLocalBDataFinal[i] + "_true";
							sb2.append(temp);
							sb2.append(",");
						} else {
							if (localGovtBodyForm.getHeadQuarterCode().equals(existingUnMappedLocalBDataFinal[i])) {
								sb2.append(",");
							}
							String temp = existingUnMappedLocalBDataFinal[i] + "_false";
							sb2.append(temp);
							if (i + 1 < existingUnMappedLocalBDataFinal.length) {
								sb2.append(",");
							}
						}
					}
				}
				if (localGovtBodyForm.getHeadQuarterCode() != null) {
					combinedUnMappedLandregion = sb2.toString();
				} else {
					combinedUnMappedLandregion = unmappedLocalBData;
				}
			}
			if (localGovtBodyForm.isLgd_LBExistCheck() && localGovtBodyForm.isLgd_LBUnmappedCheck()) {
				if (combinedLandregion != null) {
					if (combinedLandregion.endsWith(",")) {
						combinedLandregion = combinedLandregion.substring(0, combinedLandregion.length() - 1);
						log.debug(combinedLandregion);
					}
					finalLandRegion = combinedLandregion + "," + combinedUnMappedLandregion;
				} else {
					finalLandRegion = combinedUnMappedLandregion;
				}
			}
			if (localGovtBodyForm.isLgd_LBExistCheck() && !localGovtBodyForm.isLgd_LBUnmappedCheck()) {
				finalLandRegion = combinedLandregion;
			}
			if (localGovtBodyForm.isLgd_LBUnmappedCheck() && !localGovtBodyForm.isLgd_LBExistCheck()) {
				finalLandRegion = combinedUnMappedLandregion;
			}
			if (finalLandRegion != null) {
				if (finalLandRegion.endsWith(",")) {
					finalLandRegion = finalLandRegion.substring(0, finalLandRegion.length() - 1);
					log.debug(finalLandRegion);
				}
			}
			localBodyTypeCode = localGovtBodyForm.getLocalbodySubtype();
			parentLocalbody = 0;
		}
		if (lgTypeName.equals("I")) {
			if (localGovtBodyForm.getLgd_LBDistrictAtInter() != null) {
				parentLocalbody = Integer.parseInt(localGovtBodyForm.getLgd_LBDistrictAtInter());
			}
			localBodyTypeCode = localGovtBodyForm.getLocalbodySubtype();
			if (localGovtBodyForm.isLgd_LBExistCheck()) {
				//lbDestInter = localGovtBodyForm.getLgd_LBInterPDestList();
				lbDestListInter = localGovtBodyForm.getLgd_LBInterCAreaDestList();
				lbVillageListInter = localGovtBodyForm.getLgd_LBVillageDestLatICA();
				lbhiddenSubDistrict = localGovtBodyForm.getLgd_LBInterSubDestListhidden();
				// existingLocalBData=lbDestInter+","+lbDestListInter+","+lbVillageListInter;
				if (localGovtBodyForm.getHeadQuarterCode() == null) {
					lbDestListInter = lbDestListInter + "_true";
				}
				if (lbDestListInter != null && lbVillageListInter != null || lbhiddenSubDistrict != null) {
					if (lbhiddenSubDistrict != null) {
						existingLocalBData = lbDestListInter + "," + lbhiddenSubDistrict + "," + lbVillageListInter;
					} else {
						existingLocalBData = lbDestListInter + "," + lbVillageListInter;
					}
				}
				if (lbDestListInter != null && lbVillageListInter == null) {
					if (lbhiddenSubDistrict != null) {
						existingLocalBData = localGovtBodyForm.getLgd_LBInterCAreaDestList() + "," + lbhiddenSubDistrict;
					} else {
						existingLocalBData = localGovtBodyForm.getLgd_LBInterCAreaDestList();
					}
				}
				if (lbDestListInter == null && lbVillageListInter == null && lbhiddenSubDistrict != null) {
					existingLocalBData = lbhiddenSubDistrict;
				}

				StringBuffer sb2 = new StringBuffer();
				if (localGovtBodyForm.getHeadQuarterCode() != null) {
					existingLocalBDataFinal = existingLocalBData.split(",");
					for (int i = 0; i < existingLocalBDataFinal.length; i++) {
						if (localGovtBodyForm.getHeadQuarterCode().equals(existingLocalBDataFinal[i])) {
							String temp = existingLocalBDataFinal[i] + "_true";
							sb2.append(temp);
							sb2.append(",");
						} else {
							if (localGovtBodyForm.getHeadQuarterCode().equals(existingLocalBDataFinal[i])) {
								sb2.append(",");
							}
							String temp = existingLocalBDataFinal[i] + "_false";
							sb2.append(temp);
							if (i + 1 < existingLocalBDataFinal.length) {
								sb2.append(",");
							}
						}
					}
				}
				if (localGovtBodyForm.getHeadQuarterCode() != null) {
					combinedLandregion = sb2.toString();
				} else {
					combinedLandregion = existingLocalBData;
				}
			}
			if (localGovtBodyForm.isLgd_LBUnmappedCheck()) {
				unmappedSubDistforIP = localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped();
				unmappedVillforIP = localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped();
				if (localGovtBodyForm.getHeadQuarterCode() == null) {
					unmappedSubDistforIP = unmappedSubDistforIP + "_true";
				}
				if (unmappedVillforDP != null) {
					unmappedLocalBData = unmappedSubDistforIP + "," + unmappedVillforIP;
				}
				if (unmappedSubDistforIP != null && unmappedVillforIP != null) {
					unmappedLocalBData = unmappedSubDistforIP + "," + unmappedVillforIP;
				}
				if (unmappedVillforIP == null) {
					unmappedLocalBData = localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped();
				}

				StringBuffer sb2 = new StringBuffer();
				if (localGovtBodyForm.getHeadQuarterCode() != null) {
					existingUnMappedLocalBDataFinal = unmappedLocalBData.split(",");
					for (int i = 0; i < existingUnMappedLocalBDataFinal.length; i++) {
						if (localGovtBodyForm.getHeadQuarterCode().equals(existingUnMappedLocalBDataFinal[i])) {
							String temp = existingUnMappedLocalBDataFinal[i] + "_true";
							sb2.append(temp);
							sb2.append(",");
						} else {
							if (localGovtBodyForm.getHeadQuarterCode().equals(existingUnMappedLocalBDataFinal[i])) {
								sb2.append(",");
							}
							String temp = existingUnMappedLocalBDataFinal[i] + "_false";
							sb2.append(temp);
							if (i + 1 < existingUnMappedLocalBDataFinal.length) {
								sb2.append(",");
							}
						}
					}
				}
				if (localGovtBodyForm.getHeadQuarterCode() != null) {
					combinedUnMappedLandregion = sb2.toString();
				} else {
					combinedUnMappedLandregion = unmappedLocalBData;
				}
			}
			if (localGovtBodyForm.isLgd_LBExistCheck() && localGovtBodyForm.isLgd_LBUnmappedCheck()) {
				if (combinedLandregion != null) {
					if (combinedLandregion.endsWith(",")) {
						finalLandRegion = combinedLandregion + combinedUnMappedLandregion;
					} else {
						finalLandRegion = combinedLandregion + "," + combinedUnMappedLandregion;
					}
				} else {
					finalLandRegion = combinedUnMappedLandregion;
				}
			}
			if (localGovtBodyForm.isLgd_LBExistCheck() && !localGovtBodyForm.isLgd_LBUnmappedCheck()) {
				finalLandRegion = combinedLandregion;
			}
			if (localGovtBodyForm.isLgd_LBUnmappedCheck() && !localGovtBodyForm.isLgd_LBExistCheck()) {
				finalLandRegion = combinedUnMappedLandregion;
			}
			if (finalLandRegion != null) {
				if (finalLandRegion.endsWith(",")) {
					finalLandRegion = finalLandRegion.substring(0, finalLandRegion.length() - 1);
					log.debug(finalLandRegion);
				}
			}
		}
		if (lgTypeName.equals("V")) {
			/*
			 * if(localGovtBodyForm.getLgd_LBIntermediateAtVillage() !=null ||
			 * localGovtBodyForm.getLgd_LBIntermediateAtVillage() !="") {
			 * parentLocalbody
			 * =Integer.parseInt(localGovtBodyForm.getLgd_LBIntermediateAtVillage
			 * ()); }
			 */
			if (!localGovtBodyForm.getLgd_LBDistrictAtVillage().equals("")) {
				if (Integer.parseInt(localGovtBodyForm.getLgd_LBDistrictAtVillage()) != 0) {
					parentLocalbody = Integer.parseInt(localGovtBodyForm.getLgd_LBDistrictAtVillage());
				}
			}

			if (localGovtBodyForm.getLgd_LBDistrictAtVillage().equals("") || Integer.parseInt(localGovtBodyForm.getLgd_LBDistrictAtVillage()) == 0) {
				if (!localGovtBodyForm.getLgd_LBDistrictAtInter().trim().equals("0") || Integer.parseInt(localGovtBodyForm.getLgd_LBDistrictAtInter()) != 0) {
					parentLocalbody = Integer.parseInt(localGovtBodyForm.getLgd_LBDistrictAtInter());
				} else {
					parentLocalbody = 0;
				}
			}

			localBodyTypeCode = localGovtBodyForm.getLocalbodySubtype();
			if (localGovtBodyForm.isLgd_LBExistCheck()) {
				// lbVillage=localGovtBodyForm.getLgd_LBVillageDestAtVillageCA();
				lbVillageDest = localGovtBodyForm.getLgd_LBVillageCAreaDestL();
				lbhiddenVillage = localGovtBodyForm.getLgd_LBInterVillageListhidden();

				if (lbVillageDest != null) {
					if (lbhiddenVillage != null) {
						existingLocalBData = lbVillageDest + "," + lbhiddenVillage;
					} else {
						existingLocalBData = lbVillageDest;
					}
				}
				if (lbVillageDest == null) {
					if (lbhiddenVillage != null) {
						existingLocalBData = lbhiddenVillage;
					}
				}
				StringBuffer sb2 = new StringBuffer();
				if (localGovtBodyForm.getLgd_LBVillageCAreaDestL() != null) {
					if (localGovtBodyForm.getHeadQuarterCode() != null) {
						existingLocalBDataFinal = existingLocalBData.split(",");

						for (int i = 0; i < existingLocalBDataFinal.length; i++) {
							if (localGovtBodyForm.getHeadQuarterCode().equals(existingLocalBDataFinal[i])) {
								String temp = existingLocalBDataFinal[i] + "_true";
								sb2.append(temp);
								sb2.append(",");
							} else {
								if (localGovtBodyForm.getHeadQuarterCode().equals(existingLocalBDataFinal[i])) {
									sb2.append(",");
								}
								String temp = existingLocalBDataFinal[i] + "_false";
								sb2.append(temp);
								if (i + 1 < existingLocalBDataFinal.length) {
									sb2.append(",");
								}
							}
						}
					}
					if (localGovtBodyForm.getHeadQuarterCode() != null) {
						combinedLandregion = sb2.toString();
					} else {
						combinedLandregion = existingLocalBData + "_true";
					}
				}
			}
			if (localGovtBodyForm.isLgd_LBUnmappedCheck()) {
				unmappedLocalBData = localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped();
				StringBuffer sb2 = new StringBuffer();
				if (localGovtBodyForm.getHeadQuarterCode() != null) {
					existingUnMappedLocalBDataFinal = unmappedLocalBData.split(",");

					for (int i = 0; i < existingUnMappedLocalBDataFinal.length; i++) {
						if (localGovtBodyForm.getHeadQuarterCode().equals(existingUnMappedLocalBDataFinal[i])) {
							String temp = existingUnMappedLocalBDataFinal[i] + "_true";
							sb2.append(temp);
							sb2.append(",");
						} else {
							if (localGovtBodyForm.getHeadQuarterCode().equals(existingUnMappedLocalBDataFinal[i])) {
								sb2.append(",");
							}
							String temp = existingUnMappedLocalBDataFinal[i] + "_false";
							sb2.append(temp);
							if (i + 1 < existingUnMappedLocalBDataFinal.length) {
								sb2.append(",");
							}
						}
					}
				}
				if (localGovtBodyForm.getHeadQuarterCode() != null) {
					combinedUnMappedLandregion = sb2.toString();
				} else {
					combinedUnMappedLandregion = unmappedLocalBData + "_true";
				}
			}
			if (localGovtBodyForm.isLgd_LBExistCheck() && localGovtBodyForm.isLgd_LBUnmappedCheck()) {
				if (combinedLandregion != null) {
					if (combinedLandregion.endsWith(",")) {
						finalLandRegion = combinedLandregion + combinedUnMappedLandregion;
					} else {
						finalLandRegion = combinedLandregion + "," + combinedUnMappedLandregion;
					}
				} else {
					finalLandRegion = combinedUnMappedLandregion;
				}
			}
			if (localGovtBodyForm.isLgd_LBExistCheck() && !localGovtBodyForm.isLgd_LBUnmappedCheck()) {
				finalLandRegion = combinedLandregion;
			}
			if (localGovtBodyForm.isLgd_LBUnmappedCheck() && !localGovtBodyForm.isLgd_LBExistCheck()) {
				finalLandRegion = combinedUnMappedLandregion;
			}
			if (localGovtBodyForm.getHeadQuarterCode() != null) {
				if (finalLandRegion != null) {
					if (finalLandRegion.endsWith(",")) {
						finalLandRegion = finalLandRegion.substring(0, finalLandRegion.length() - 1);
						log.debug(finalLandRegion);
					}
				}
			}
		}

		//char lbType = localGovtBodyForm.getLbType();
		//String lbTypeFinal = Character.toString(lbType);
		boolean pesavalue = false;
		if (pesa != null) {
			if (pesa.equals("Yes")) {
				pesavalue = true;
			} else {
				pesavalue = false;
			}
		}
		if (pesa == null) {
			pesavalue = false;
		}

		/*if (parentLocalbody != 0) {
			parentLocalbodyName = getparentLocalbodynamebycode(parentLocalbody);
		}*/

		if (localGovtBodyForm.getLbType() == 'P') {
			/*
			 * if(parentLocalbodyName !=null &&
			 * localGovtBodyForm.getContDistrict() !=null &&
			 * localGovtBodyForm.getContSubDistrict()!=null &&
			 * localGovtBodyForm.getContVillage() !=null) {
			 * transdesc="New PRI Local Body is created by the Name "
			 * +localBodyNameEng +
			 * " of Parent Local Body "+parentLocalbodyName.trim()
			 * +" Contributing "+" District "+
			 * localGovtBodyForm.getContDistrict(
			 * )+" Sub District "+localGovtBodyForm
			 * .getContSubDistrict()+" Village "
			 * +localGovtBodyForm.getContVillage(); } if(parentLocalbodyName
			 * !=null && localGovtBodyForm.getContDistrict() !=null &&
			 * localGovtBodyForm.getContSubDistrict()==null &&
			 * localGovtBodyForm.getContVillage() ==null) {
			 * transdesc="New PRI Local Body is created by the Name "
			 * +localBodyNameEng +
			 * " of Parent Local Body "+parentLocalbodyName.trim()
			 * +" Contributing "+" District "+
			 * localGovtBodyForm.getContDistrict(); } if(parentLocalbodyName
			 * !=null && localGovtBodyForm.getContDistrict() !=null &&
			 * localGovtBodyForm.getContSubDistrict()!=null &&
			 * localGovtBodyForm.getContVillage() ==null) {
			 * transdesc="New PRI Local Body is created by the Name "
			 * +localBodyNameEng +
			 * " of Parent Local Body "+parentLocalbodyName.trim()
			 * +" Contributing "+" District "+
			 * localGovtBodyForm.getContDistrict(
			 * )+" Sub District "+localGovtBodyForm.getContSubDistrict(); }
			 * if(parentLocalbodyName !=null &&
			 * localGovtBodyForm.getContDistrict() ==null &&
			 * localGovtBodyForm.getContSubDistrict()!=null &&
			 * localGovtBodyForm.getContVillage() !=null) {
			 * transdesc="New PRI Local Body is created by the Name "
			 * +localBodyNameEng +
			 * " of Parent Local Body "+parentLocalbodyName.trim()
			 * +" Contributing "+" Sub District "+
			 * localGovtBodyForm.getContSubDistrict
			 * ()+" Village "+localGovtBodyForm.getContVillage(); }
			 * if(parentLocalbodyName !=null &&
			 * localGovtBodyForm.getContDistrict() !=null &&
			 * localGovtBodyForm.getContSubDistrict() !=null &&
			 * localGovtBodyForm.getContVillage() ==null) {
			 * transdesc="New PRI Local Body is created by the Name "
			 * +localBodyNameEng +
			 * " of Parent Local Body "+parentLocalbodyName.trim()
			 * +" Contributing "+" District "+
			 * localGovtBodyForm.getContDistrict(); } if(parentLocalbodyName
			 * ==null && localGovtBodyForm.getContDistrict() !=null &&
			 * localGovtBodyForm.getContSubDistrict()!=null &&
			 * localGovtBodyForm.getContVillage() !=null) {
			 * transdesc="New PRI Local Body is created by the Name "
			 * +localBodyNameEng +" Contributing "+" District "+
			 * localGovtBodyForm
			 * .getContDistrict()+" Sub District "+localGovtBodyForm
			 * .getContSubDistrict
			 * ()+" Village "+localGovtBodyForm.getContVillage(); }
			 * if(parentLocalbodyName ==null &&
			 * localGovtBodyForm.getContDistrict() !=null &&
			 * localGovtBodyForm.getContSubDistrict()==null &&
			 * localGovtBodyForm.getContVillage() ==null) {
			 * transdesc="New PRI Local Body is created by the Name "
			 * +localBodyNameEng +" Contributing "+" District "+
			 * localGovtBodyForm.getContDistrict(); } if(parentLocalbodyName
			 * ==null && localGovtBodyForm.getContDistrict() !=null &&
			 * localGovtBodyForm.getContSubDistrict()!=null &&
			 * localGovtBodyForm.getContVillage() ==null) {
			 * transdesc="New PRI Local Body is created by the Name "
			 * +localBodyNameEng +" Contributing "+" District "+
			 * localGovtBodyForm
			 * .getContDistrict()+" Sub District "+localGovtBodyForm
			 * .getContSubDistrict(); } if(parentLocalbodyName !=null &&
			 * localGovtBodyForm.getContDistrict() ==null &&
			 * localGovtBodyForm.getContSubDistrict() ==null &&
			 * localGovtBodyForm.getContVillage() !=null) {
			 * transdesc="New PRI Local Body is created by the Name "
			 * +localBodyNameEng+
			 * " of Parent Local Body "+parentLocalbodyName.trim()
			 * +" Contributing Village "+localGovtBodyForm.getContVillage(); }
			 * if(parentLocalbodyName ==null &&
			 * localGovtBodyForm.getContDistrict() ==null &&
			 * localGovtBodyForm.getContSubDistrict() ==null &&
			 * localGovtBodyForm.getContVillage() !=null) {
			 * transdesc="New PRI Local Body is created by the Name "
			 * +localBodyNameEng
			 * +" Contributing Village "+localGovtBodyForm.getContVillage(); }
			 * if(parentLocalbodyName ==null &&
			 * localGovtBodyForm.getContDistrict() ==null &&
			 * localGovtBodyForm.getContSubDistrict()!=null &&
			 * localGovtBodyForm.getContVillage() ==null) {
			 * transdesc="New PRI Local Body is created by the Name "
			 * +localBodyNameEng
			 * +" Sub District "+localGovtBodyForm.getContSubDistrict(); }
			 * if(parentLocalbodyName ==null &&
			 * localGovtBodyForm.getContDistrict() ==null &&
			 * localGovtBodyForm.getContSubDistrict() ==null &&
			 * localGovtBodyForm.getContVillage() ==null) {
			 * transdesc="New PRI Local Body is created by the Name "
			 * +localBodyNameEng; }
			 */
			if (localGovtBodyForm.getLgd_LocalBodyTypeName() != null && localGovtBodyForm.getContDistrict() != null && localGovtBodyForm.getContSubDistrict() != null && localGovtBodyForm.getContVillage() != null) {
				transdesc = "New {" + localGovtBodyForm.getLgd_LocalBodyTypeName() + "} formed by Name {" + localBodyNameEng + "} with contributing districts {" + localGovtBodyForm.getContDistrict() + "},Sub districts {"
						+ localGovtBodyForm.getContSubDistrict() + "} and Villages {" + localGovtBodyForm.getContVillage() + "}";
			}
			if (localGovtBodyForm.getLgd_LocalBodyTypeName() != null && localGovtBodyForm.getContDistrict() != null && localGovtBodyForm.getContSubDistrict() != null && localGovtBodyForm.getContVillage() == null) {
				transdesc = "New {" + localGovtBodyForm.getLgd_LocalBodyTypeName() + "} formed by Name {" + localBodyNameEng + "} with contributing districts {" + localGovtBodyForm.getContDistrict() + "},Sub districts {"
						+ localGovtBodyForm.getContSubDistrict() + "}";
			}
			if (localGovtBodyForm.getLgd_LocalBodyTypeName() != null && localGovtBodyForm.getContDistrict() != null && localGovtBodyForm.getContSubDistrict() == null && localGovtBodyForm.getContVillage() == null) {
				transdesc = "New {" + localGovtBodyForm.getLgd_LocalBodyTypeName() + "} formed by Name {" + localBodyNameEng + "} with contributing districts {" + localGovtBodyForm.getContDistrict() + "}";
			}
			if (localGovtBodyForm.getLgd_LocalBodyTypeName() != null && localGovtBodyForm.getContDistrict() == null && localGovtBodyForm.getContSubDistrict() == null && localGovtBodyForm.getContVillage() == null) {
				transdesc = "New {" + localGovtBodyForm.getLgd_LocalBodyTypeName() + "} formed by Name {" + localBodyNameEng;
			}
			if (localGovtBodyForm.getLgd_LocalBodyTypeName() != null && localGovtBodyForm.getContDistrict() == null && localGovtBodyForm.getContSubDistrict() == null && localGovtBodyForm.getContVillage() != null) {
				transdesc = "New {" + localGovtBodyForm.getLgd_LocalBodyTypeName() + "} formed by Name {" + localBodyNameEng + "} with contributing Villages {" + localGovtBodyForm.getContVillage() + "}";
			}
			if (localGovtBodyForm.getLgd_LocalBodyTypeName() != null && localGovtBodyForm.getContDistrict() == null && localGovtBodyForm.getContSubDistrict() != null && localGovtBodyForm.getContVillage() != null) {
				transdesc = "New {" + localGovtBodyForm.getLgd_LocalBodyTypeName() + "} formed by Name {" + localBodyNameEng + "} with contributing Sub districts {" + localGovtBodyForm.getContSubDistrict() + "} and Villages {"
						+ localGovtBodyForm.getContVillage() + "}";
			}
			if (localGovtBodyForm.getLgd_LocalBodyTypeName() != null && localGovtBodyForm.getContDistrict() == null && localGovtBodyForm.getContSubDistrict() != null && localGovtBodyForm.getContVillage() == null) {
				transdesc = "New {" + localGovtBodyForm.getLgd_LocalBodyTypeName() + "} formed by Name {" + localBodyNameEng + "} with contributing Sub districts {" + localGovtBodyForm.getContSubDistrict() + "}";
			}
		}

		if (localGovtBodyForm.getLbType() == 'T') {

			/*
			 * if(parentLocalbodyName !=null &&
			 * localGovtBodyForm.getContDistrict() !=null &&
			 * localGovtBodyForm.getContSubDistrict()!=null &&
			 * localGovtBodyForm.getContVillage() !=null) {
			 * transdesc="New Traditional Local Body is created by the Name "
			 * +localBodyNameEng +
			 * " of Parent Local Body "+parentLocalbodyName.trim()
			 * +" Contributing "+" District "+
			 * localGovtBodyForm.getContDistrict(
			 * )+" Sub District "+localGovtBodyForm
			 * .getContSubDistrict()+" Village "
			 * +localGovtBodyForm.getContVillage(); } if(parentLocalbodyName
			 * !=null && localGovtBodyForm.getContDistrict() !=null &&
			 * localGovtBodyForm.getContSubDistrict()==null &&
			 * localGovtBodyForm.getContVillage() ==null) {
			 * transdesc="New Traditional Local Body is created by the Name "
			 * +localBodyNameEng +
			 * " of Parent Local Body "+parentLocalbodyName.trim()
			 * +" Contributing "+" District "+
			 * localGovtBodyForm.getContDistrict(); } if(parentLocalbodyName
			 * !=null && localGovtBodyForm.getContDistrict() !=null &&
			 * localGovtBodyForm.getContSubDistrict()!=null &&
			 * localGovtBodyForm.getContVillage() ==null) {
			 * transdesc="New Traditional Local Body is created by the Name "
			 * +localBodyNameEng +
			 * " of Parent Local Body "+parentLocalbodyName.trim()
			 * +" Contributing "+" District "+
			 * localGovtBodyForm.getContDistrict(
			 * )+" Sub District "+localGovtBodyForm.getContSubDistrict(); }
			 * if(parentLocalbodyName !=null &&
			 * localGovtBodyForm.getContDistrict() ==null &&
			 * localGovtBodyForm.getContSubDistrict()!=null &&
			 * localGovtBodyForm.getContVillage() !=null) {
			 * transdesc="New Traditional Local Body is created by the Name "
			 * +localBodyNameEng +
			 * " of Parent Local Body "+parentLocalbodyName.trim()
			 * +" Contributing "+" Sub District "+
			 * localGovtBodyForm.getContSubDistrict
			 * ()+" Village "+localGovtBodyForm.getContVillage(); }
			 * if(parentLocalbodyName !=null &&
			 * localGovtBodyForm.getContDistrict() !=null &&
			 * localGovtBodyForm.getContSubDistrict() !=null &&
			 * localGovtBodyForm.getContVillage() ==null) {
			 * transdesc="New Traditional Local Body is created by the Name "
			 * +localBodyNameEng +
			 * " of Parent Local Body "+parentLocalbodyName.trim()
			 * +" Contributing "+" District "+
			 * localGovtBodyForm.getContDistrict(); } if(parentLocalbodyName
			 * ==null && localGovtBodyForm.getContDistrict() !=null &&
			 * localGovtBodyForm.getContSubDistrict()!=null &&
			 * localGovtBodyForm.getContVillage() !=null) {
			 * transdesc="New Traditional Local Body is created by the Name "
			 * +localBodyNameEng +" Contributing "+" District "+
			 * localGovtBodyForm
			 * .getContDistrict()+" Sub District "+localGovtBodyForm
			 * .getContSubDistrict
			 * ()+" Village "+localGovtBodyForm.getContVillage(); }
			 * if(parentLocalbodyName ==null &&
			 * localGovtBodyForm.getContDistrict() !=null &&
			 * localGovtBodyForm.getContSubDistrict()==null &&
			 * localGovtBodyForm.getContVillage() ==null) {
			 * transdesc="New Traditional Local Body is created by the Name "
			 * +localBodyNameEng +" Contributing "+" District "+
			 * localGovtBodyForm.getContDistrict(); } if(parentLocalbodyName
			 * ==null && localGovtBodyForm.getContDistrict() !=null &&
			 * localGovtBodyForm.getContSubDistrict()!=null &&
			 * localGovtBodyForm.getContVillage() ==null) {
			 * transdesc="New Traditional Local Body is created by the Name "
			 * +localBodyNameEng +" Contributing "+" District "+
			 * localGovtBodyForm
			 * .getContDistrict()+" Sub District "+localGovtBodyForm
			 * .getContSubDistrict(); } if(parentLocalbodyName !=null &&
			 * localGovtBodyForm.getContDistrict() ==null &&
			 * localGovtBodyForm.getContSubDistrict() ==null &&
			 * localGovtBodyForm.getContVillage() !=null) {
			 * transdesc="New Traditional Local Body is created by the Name "
			 * +localBodyNameEng+
			 * " of Parent Local Body "+parentLocalbodyName.trim()
			 * +" Contributing Village "+localGovtBodyForm.getContVillage(); }
			 * if(parentLocalbodyName ==null &&
			 * localGovtBodyForm.getContDistrict() ==null &&
			 * localGovtBodyForm.getContSubDistrict()!=null &&
			 * localGovtBodyForm.getContVillage() ==null) {
			 * transdesc="New Traditional Local Body is created by the Name "
			 * +localBodyNameEng
			 * +" Sub District "+localGovtBodyForm.getContSubDistrict(); }
			 * if(parentLocalbodyName ==null &&
			 * localGovtBodyForm.getContDistrict() ==null &&
			 * localGovtBodyForm.getContSubDistrict() ==null &&
			 * localGovtBodyForm.getContVillage() !=null) {
			 * transdesc="New Traditional Local Body is created by the Name "
			 * +localBodyNameEng
			 * +" Contributing Village "+localGovtBodyForm.getContVillage(); }
			 * if(parentLocalbodyName ==null &&
			 * localGovtBodyForm.getContDistrict() ==null &&
			 * localGovtBodyForm.getContSubDistrict() ==null &&
			 * localGovtBodyForm.getContVillage() ==null) {
			 * transdesc="New Traditional Local Body is created by the Name "
			 * +localBodyNameEng; }
			 */

			if (localGovtBodyForm.getLgd_LocalBodyTypeName() != null && localGovtBodyForm.getContDistrict() != null && localGovtBodyForm.getContSubDistrict() != null && localGovtBodyForm.getContVillage() != null) {
				transdesc = "New {" + localGovtBodyForm.getLgd_LocalBodyTypeName() + "} formed by Name {" + localBodyNameEng + "} with contributing districts {" + localGovtBodyForm.getContDistrict() + "},Sub districts {"
						+ localGovtBodyForm.getContSubDistrict() + "} and Villages {" + localGovtBodyForm.getContVillage() + "}";
			}
			if (localGovtBodyForm.getLgd_LocalBodyTypeName() != null && localGovtBodyForm.getContDistrict() != null && localGovtBodyForm.getContSubDistrict() != null && localGovtBodyForm.getContVillage() == null) {
				transdesc = "New {" + localGovtBodyForm.getLgd_LocalBodyTypeName() + "} formed by Name {" + localBodyNameEng + "} with contributing districts {" + localGovtBodyForm.getContDistrict() + "},Sub districts {"
						+ localGovtBodyForm.getContSubDistrict() + "}";
			}
			if (localGovtBodyForm.getLgd_LocalBodyTypeName() != null && localGovtBodyForm.getContDistrict() != null && localGovtBodyForm.getContSubDistrict() == null && localGovtBodyForm.getContVillage() == null) {
				transdesc = "New {" + localGovtBodyForm.getLgd_LocalBodyTypeName() + "} formed by Name {" + localBodyNameEng + "} with contributing districts {" + localGovtBodyForm.getContDistrict() + "}";
			}
			if (localGovtBodyForm.getLgd_LocalBodyTypeName() != null && localGovtBodyForm.getContDistrict() == null && localGovtBodyForm.getContSubDistrict() == null && localGovtBodyForm.getContVillage() == null) {
				transdesc = "New {" + localGovtBodyForm.getLgd_LocalBodyTypeName() + "} formed by Name {" + localBodyNameEng;
			}
			if (localGovtBodyForm.getLgd_LocalBodyTypeName() != null && localGovtBodyForm.getContDistrict() == null && localGovtBodyForm.getContSubDistrict() == null && localGovtBodyForm.getContVillage() != null) {
				transdesc = "New {" + localGovtBodyForm.getLgd_LocalBodyTypeName() + "} formed by Name {" + localBodyNameEng + "} with contributing Villages {" + localGovtBodyForm.getContVillage() + "}";
			}
			if (localGovtBodyForm.getLgd_LocalBodyTypeName() != null && localGovtBodyForm.getContDistrict() == null && localGovtBodyForm.getContSubDistrict() != null && localGovtBodyForm.getContVillage() != null) {
				transdesc = "New {" + localGovtBodyForm.getLgd_LocalBodyTypeName() + "} formed by Name {" + localBodyNameEng + "} with contributing Sub districts {" + localGovtBodyForm.getContSubDistrict() + "} and Villages {"
						+ localGovtBodyForm.getContVillage() + "}";
			}
			if (localGovtBodyForm.getLgd_LocalBodyTypeName() != null && localGovtBodyForm.getContDistrict() == null && localGovtBodyForm.getContSubDistrict() != null && localGovtBodyForm.getContVillage() == null) {
				transdesc = "New {" + localGovtBodyForm.getLgd_LocalBodyTypeName() + "} formed by Name {" + localBodyNameEng + "} with contributing Sub districts {" + localGovtBodyForm.getContSubDistrict() + "}";
			}

		}
		if (localGovtBodyForm.getSlc() == 19 && parentLocalbody == 0) {
			if (localGovtBodyForm.getGtaInterPanch() != null && !localGovtBodyForm.getGtaInterPanch().equals("")) {
				parentLocalbody = Integer.parseInt(localGovtBodyForm.getGtaInterPanch());
			} else if (localGovtBodyForm.getGtaList() != null && !localGovtBodyForm.getGtaList().equals("")) {
				parentLocalbody = Integer.parseInt(localGovtBodyForm.getGtaList());
			}
		}
		try {

			session = sessionFactory.openSession();

			query = session.getNamedQuery("insertLocalBody").setParameter("local_body_name_english", localBodyNameEng, Hibernate.STRING).setParameter("local_body_name_local", localbodyNameLocal, Hibernate.STRING)
					.setParameter("local_body_type_code", lbodytypecd, Hibernate.INTEGER).setParameter("state_code", stateCode, Hibernate.INTEGER).setParameter("alias_english", aliasEnglish, Hibernate.STRING)
					.setParameter("alias_local", aliasLocal, Hibernate.STRING).setParameter("createdby", userId, Hibernate.INTEGER).setParameter("is_pesa", pesavalue, Hibernate.BOOLEAN).setParameter("coordinates", gisNodes, Hibernate.STRING)
					.setParameter("replaces", panchayatDP, Hibernate.STRING).setParameter("order_no", orderNo, Hibernate.STRING).setParameter("order_date", orderDate, Hibernate.DATE).setParameter("effective_date", effectiveDate, Hibernate.DATE)
					.setParameter("gaz_pub_date", gazDate, Hibernate.DATE).setParameter("order_path", orderPath, Hibernate.STRING).setParameter("operation_code", operationCode, Hibernate.INTEGER)
					.setParameter("land_region_list_mapped", finalLandRegion, Hibernate.STRING).setParameter("local_body_subtype_code", localBodyTypeCode, Hibernate.INTEGER).setParameter("parent_local_body_code", parentLocalbody, Hibernate.INTEGER)
					.setParameter("sscode", stateSpecificCode, Hibernate.STRING).setParameter("flag_code", flagCode, Hibernate.INTEGER).setParameter("description", transdesc, Hibernate.STRING);

			valueReturn = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return valueReturn;
	}

	@SuppressWarnings("unchecked")
	public synchronized List<InsertLocalGovtBody> saveLocalBodyULB(LocalGovtBodyForm localGovtBodyForm, String lgtypecode, int stateCode, Integer userId, String panchayatDP) {
		Session session = null;
		Query query = null;

		Integer localBodyTypeCode = null;
		Integer parentLocalbody = null;
		String stateSpecificCode = null;
		//String[] existingLocalBDataFinal = null;
		//String[] existingUnMappedLocalBDataFinal = null;
		String combinedLandregion = null;
		String combinedUnMappedLandregion = null;
		String finalLandRegion = null;
		String lbhiddenSubDistrict = null;
		String lbSubDistrict = null;
		int operationCode = 0;
		int flagCode = 0;
		String transdesc = null;
		List<InsertLocalGovtBody> valueReturn = null;
		String localBodyNameEng = localGovtBodyForm.getLgd_LBNameInEn();
		String aliasEnglish = localGovtBodyForm.getLgd_LBAliasInEn();
		String aliasLocal = localGovtBodyForm.getLgd_LBAliasInLocal();
		String localbodyNameLocal = localGovtBodyForm.getLgd_LBNameInLocal();
		/* if(!stateSpecificCode.equals(null)) */
		if (localGovtBodyForm.getLgd_LBstateSpecificCode() != null) {
			stateSpecificCode = localGovtBodyForm.getLgd_LBstateSpecificCode();
		}

		String lgTypeNameId = localGovtBodyForm.getLgd_LBTypeName();

		String[] lgTypeNameArr = lgTypeNameId.split(":");
		String localGovtType = lgTypeNameArr[0];

		int lbodytypecd = Integer.parseInt(localGovtType);
		if (localGovtBodyForm.getLgd_UrbanLBDistExistDest() != null) {
			panchayatDP.replaceAll("_PART", "").replaceAll("_FULL", "");
		}
		// String pesa=localGovtBodyForm.getLgd_LBPesaAct();
		String orderNo = localGovtBodyForm.getLgd_LBorderNo();
		Date orderDate = localGovtBodyForm.getLgd_LBorderDate();
		Date effectiveDate = localGovtBodyForm.getLgd_LBeffectiveDate();
		Date gazDate = localGovtBodyForm.getLgd_LBgazPubDate();
		String orderPath = "govtOrderUpload";
		String latitude = localGovtBodyForm.getlatitude();
		String longitude = localGovtBodyForm.getLongitude();
		String[] arrayLati = latitude.split(",");
		String[] arrayLong = longitude.split(",");
		int count = 1;
		StringBuffer sb = null;
		String gisNodes = null;
		if (!localGovtBodyForm.getlatitude().equals("") && !localGovtBodyForm.getLongitude().equals("")) {
			sb = new StringBuffer();
			for (int i = 0; i < arrayLati.length; i++) {
				for (int j = 0; j < arrayLong.length; j++) {
					if (i == j) {
						if (count == 1) {
							String temp = arrayLati[i] + ":" + arrayLong[i];
							sb.append(temp);
							count++;
						} else {
							sb.append(",");
							String temp = arrayLati[i] + ":" + arrayLong[i];
							sb.append(temp);
						}
					}
				}
			}
		}
		if (!localGovtBodyForm.getlatitude().equals("") && !localGovtBodyForm.getLongitude().equals("")) {
			gisNodes = sb.toString();
		}
		flagCode = localGovtBodyForm.getFlagCode();


		operationCode = localGovtBodyForm.getOperationCode();

		// final String upLoadMap = localGovtBodyForm.getFileMapUpLoad();

		if (!lgtypecode.equals("")) {
			if (localGovtBodyForm.isLgd_LBExistCheck()) {

				lbSubDistrict = localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest();
				lbhiddenSubDistrict = localGovtBodyForm.getLgd_LBInterSubDestListhidden();

				combinedLandregion = localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest();

				if (lbSubDistrict != null) {
					if (lbhiddenSubDistrict != null) {
						combinedLandregion = lbSubDistrict + "," + lbhiddenSubDistrict;
					} else {
						combinedLandregion = lbSubDistrict;
					}
				}
				if (lbSubDistrict == null) {
					if (lbhiddenSubDistrict != null) {
						combinedLandregion = lbhiddenSubDistrict;
					}
				}
				/*
				 * lbSubDistictArea=localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA
				 * ();
				 * lbVillageArea=localGovtBodyForm.getLgd_LBVillageDestLatDCA();
				 * existingLocalBData
				 * =lbDistictArea+","+lbSubDistictArea+","+lbVillageArea;
				 * 
				 * existingLocalBDataFinal=existingLocalBData.split(",");
				 * StringBuffer sb2 = new StringBuffer(); for (int i = 0; i <
				 * existingLocalBDataFinal.length; i++) {
				 * if(localGovtBodyForm.getHeadQuarterCode
				 * ().equals(existingLocalBDataFinal[i])) { String temp =
				 * existingLocalBDataFinal[i]+"_true"; sb2.append(temp);
				 * sb2.append(","); } else {
				 * if(localGovtBodyForm.getHeadQuarterCode
				 * ().equals(existingLocalBDataFinal[i])) { sb2.append(","); }
				 * String temp = existingLocalBDataFinal[i]+"_false";
				 * sb2.append(temp); if(i+1 < existingLocalBDataFinal.length) {
				 * sb2.append(","); } } } combinedLandregion=sb2.toString();
				 */
			}
			if (localGovtBodyForm.isLgd_LBUnmappedCheck()) {
				combinedUnMappedLandregion = localGovtBodyForm.getLgd_UrbanLBDistUmappedDest();

				/*
				 * unmappedSubDistforDP=localGovtBodyForm.
				 * getLgd_LBSubDistrictDestLatDCAUmapped();
				 * unmappedVillforDP=localGovtBodyForm
				 * .getLgd_LBVillageDestLatDCAUmapped();
				 * unmappedLocalBData=unmappedDistforDP
				 * +","+unmappedSubDistforDP+","+unmappedVillforDP;
				 * existingUnMappedLocalBDataFinal
				 * =unmappedLocalBData.split(","); StringBuffer sb2 = new
				 * StringBuffer(); for (int i = 0; i <
				 * existingUnMappedLocalBDataFinal.length; i++) {
				 * if(localGovtBodyForm
				 * .getHeadQuarterCode().equals(existingUnMappedLocalBDataFinal
				 * [i])) { String temp =
				 * existingUnMappedLocalBDataFinal[i]+"_true"; sb2.append(temp);
				 * sb2.append(","); } else {
				 * if(localGovtBodyForm.getHeadQuarterCode
				 * ().equals(existingUnMappedLocalBDataFinal[i])) {
				 * sb2.append(","); } String temp =
				 * existingUnMappedLocalBDataFinal[i]+"_false";
				 * sb2.append(temp); if(i+1 <
				 * existingUnMappedLocalBDataFinal.length) { sb2.append(","); }
				 * } } combinedUnMappedLandregion=sb2.toString();
				 */
			}
			if (localGovtBodyForm.isLgd_LBExistCheck() && localGovtBodyForm.isLgd_LBUnmappedCheck()) {
				String finalVal = "";
				if (combinedLandregion != null) {
					finalVal += combinedLandregion + ",";
				}
				if (combinedUnMappedLandregion != null) {
					finalVal += combinedUnMappedLandregion;
				}
				finalLandRegion = finalVal;
			}
			if (localGovtBodyForm.isLgd_LBExistCheck() && !localGovtBodyForm.isLgd_LBUnmappedCheck()) {
				finalLandRegion = combinedLandregion;
			}
			if (localGovtBodyForm.isLgd_LBUnmappedCheck() && !localGovtBodyForm.isLgd_LBExistCheck()) {
				finalLandRegion = combinedUnMappedLandregion;
			}

			localBodyTypeCode = localGovtBodyForm.getLocalbodySubtype();
			parentLocalbody = 0;
		}

		//char lbType = localGovtBodyForm.getLbType();
		//String lbTypeFinal = Character.toString(lbType);
		boolean pesavalue = false;
		/*
		 * if(pesa.equals("Yes")) { pesavalue=true; } else { pesavalue=false; }
		 */

		if (localGovtBodyForm.getLbType() == 'U') {
			if (localGovtBodyForm.getContSubDistrict() != null) {
				// transdesc="New Urban Local Body is created by the Name "+localBodyNameEng
				// +" Contributing Subdistrict "+localGovtBodyForm.getContSubDistrict();
				transdesc = "New {" + localGovtBodyForm.getLgd_LocalBodyTypeName() + "} formed by Name {" + localBodyNameEng + "} with contributing Sub districts of {" + localGovtBodyForm.getContSubDistrict() + "}";
			} else {
				transdesc = "New {" + localGovtBodyForm.getLgd_LocalBodyTypeName() + "} formed by Name {" + localBodyNameEng + "}";
			}

		}

		try {
			session = sessionFactory.openSession();

			query = session.getNamedQuery("insertLocalBody").setParameter("local_body_name_english", localBodyNameEng, Hibernate.STRING).setParameter("local_body_name_local", localbodyNameLocal, Hibernate.STRING)
					.setParameter("local_body_type_code", lbodytypecd, Hibernate.INTEGER).setParameter("state_code", stateCode, Hibernate.INTEGER).setParameter("alias_english", aliasEnglish, Hibernate.STRING)
					.setParameter("alias_local", aliasLocal, Hibernate.STRING).setParameter("createdby", userId, Hibernate.INTEGER).setParameter("is_pesa", pesavalue, Hibernate.BOOLEAN).setParameter("coordinates", gisNodes, Hibernate.STRING)
					.setParameter("replaces", panchayatDP, Hibernate.STRING).setParameter("order_no", orderNo, Hibernate.STRING).setParameter("order_date", orderDate, Hibernate.DATE).setParameter("effective_date", effectiveDate, Hibernate.DATE)
					.setParameter("gaz_pub_date", gazDate, Hibernate.DATE).setParameter("order_path", orderPath, Hibernate.STRING).setParameter("operation_code", operationCode, Hibernate.INTEGER)
					.setParameter("land_region_list_mapped", finalLandRegion, Hibernate.STRING).setParameter("local_body_subtype_code", localBodyTypeCode, Hibernate.INTEGER).setParameter("parent_local_body_code", parentLocalbody, Hibernate.INTEGER)
					.setParameter("sscode", stateSpecificCode, Hibernate.STRING).setParameter("flag_code", flagCode, Hibernate.INTEGER).setParameter("description", transdesc, Hibernate.STRING);

			valueReturn = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return valueReturn;
	}

	@SuppressWarnings("unchecked")
	public synchronized List<PushLBtoPES> saveLocalBodyinPES(int localbodycode) {
		Session session = null;
		Query query = null;
		List<PushLBtoPES> valueReturn = null;
		try {
			session = sessionFactory.openSession();

			query = session.getNamedQuery("pushLBtoPES").setParameter("localBodyCode", localbodycode, Hibernate.INTEGER);

			valueReturn = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return valueReturn;
	}

	@SuppressWarnings("unchecked")
	public synchronized List<Pushcoveragetopes> saveCoverageinPES(int localbodycode) {
		Session session = null;
		Query query = null;
		List<Pushcoveragetopes> valueReturn = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("pushcoveredareatopes").setParameter("landregionlistfor", localbodycode, Hibernate.INTEGER);
			valueReturn = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return valueReturn;
	}

	/**
	 * Modified by sushil on 18-01-2013
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ChangeLocalBodyName> modifylocalbodyforname(LocalGovtBodyForm localGovtBodyForm, HttpServletRequest request, HttpSession httpsession) {
		Session session = null;
		Transaction tx = null;
		Query query = null;
		//String retValue = null;
		//Integer transCode = null;
		//Integer orderCode = null;
		//Attachment attachment = null;
		//ChangeLocalBodyName localbody = null;
		List<LocalBodyDetails> localBodyDetails = localGovtBodyForm.getLocalBodyDetails();
		List<ChangeLocalBodyName> comboList = new ArrayList<ChangeLocalBodyName>();

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			int localBodyCode = localBodyDetails.get(0).getLocalBodyCode();
			String localBodyNameEnglish = localBodyDetails.get(0).getLocalBodyNameEnglish();
			String localBodyNameLocal = localBodyDetails.get(0).getLocalBodyNameLocal();
			String aliasNameEnglish = localBodyDetails.get(0).getAliasNameEnglish();
			String alisNameLocal = localBodyDetails.get(0).getAlisNameLocal();
			String orderNo = localGovtBodyForm.getLgd_LBorderNo();
			Date orderDate = localGovtBodyForm.getLgd_LBorderDate();
			Date lBeffectiveDate = localGovtBodyForm.getLgd_LBeffectiveDate();
			Date gazPubDate = localGovtBodyForm.getLgd_LBgazPubDate();
			SessionObject sessionObject = (SessionObject)httpsession.getAttribute("sessionObject");
			final Integer userId = sessionObject.getUserId()!=null?sessionObject.getUserId().intValue():null;

			String govtorder = "";
			
			// Integer userId =4117;

			query = session.getNamedQuery("getchangelocalbodyname").setParameter("local_body_code", localBodyCode, Hibernate.INTEGER).setParameter("local_body_name_name_english", localBodyNameEnglish, Hibernate.STRING)
					.setParameter("user_id", userId, Hibernate.INTEGER).setParameter("order_no", orderNo, Hibernate.STRING).setParameter("order_date", orderDate, Hibernate.DATE).setParameter("effective_date", lBeffectiveDate, Hibernate.DATE)
					.setParameter("govt_order", govtorder, Hibernate.STRING).setParameter("gaz_pub_date", gazPubDate, Hibernate.DATE).setParameter("local_body_name_name_local", localBodyNameLocal, Hibernate.STRING)
					.setParameter("alias_english", aliasNameEnglish, Hibernate.STRING).setParameter("alias_local", alisNameLocal, Hibernate.STRING);

			comboList = query.list();
			/*
			 * retValue = comboList.get(0).getChange_local_body_name_fn();
			 * if(retValue != null) { String[] arr = retValue.split(",");
			 * transCode = Integer.decode(arr[0]); orderCode =
			 * Integer.decode(arr[1]); }
			 */
			tx.commit();
			// return transCode;
		} catch (Exception e) {
			log.debug("Exception" + e);
			if (tx != null) {
				tx.rollback();
			}

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return comboList;
	}

	/**
	 * @param localGovtBodyForm
	 * @param request
	 * @param httpsession
	 * @param isULBDisttLevel
	 *            (New Added Parameter)
	 * @return
	 * @throws Exception
	 * @author Vinay Yadav 23-12-2013
	 */
	@SuppressWarnings("unchecked")
	public List<ChangeLocalBodyCoveredArea> modifylocalbodyforCoveredArea(LocalGovtBodyForm localGovtBodyForm, HttpServletRequest request, HttpSession httpsession, boolean isULBDisttLevel) {
		Session session = null;
		Transaction tx = null;
		Query query = null;
		String lb_landregionList = null;
		String[] existingLocalBDataFinal = null;
		String combinedLandregion = null;
		String lbhiddenDistrict = null;
		String lbhiddenSubDistrict = null;
		String covered_lbcode_full = null;
		List<ChangeLocalBodyCoveredArea> comboList = new ArrayList<ChangeLocalBodyCoveredArea>();
		//Integer retValue = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			int localBodyCode = localGovtBodyForm.getLocalBodyCode();

			String lbtypeName = localGovtBodyForm.getLgd_LBTypeNamehidden();
			String[] lbtypeNameVal = lbtypeName.split(":");

			String orderNo = localGovtBodyForm.getLgd_LBorderNo();
			Date orderDate = localGovtBodyForm.getLgd_LBorderDate();
			Date lBeffectiveDate = localGovtBodyForm.getLgd_LBeffectiveDate();
			// Date gazPubDate=localGovtBodyForm.getLgd_LBgazPubDate();
			Date gazPubDate = localGovtBodyForm.getGazPubDate();
			// For DP

			if (lbtypeNameVal[2].equals("P") || lbtypeNameVal[2].equals("T")) {
				String existLBforDP = null;
				if (lbtypeNameVal[1].equals("D") || lbtypeNameVal[1].equals("V")) {
					if (lbtypeNameVal[1].equals("D"))
						existLBforDP = localGovtBodyForm.getLgd_LBDistPDestList();
					else if (lbtypeNameVal[1].equals("V"))
						existLBforDP = localGovtBodyForm.getLgd_LBVillageDestAtVillageCA();
					StringBuffer valueBufferLBforDP = new StringBuffer();
					if (existLBforDP != null && existLBforDP.contains("FULL")) {
						/*
						 * if(existLBforDP.contains(",")) {
						 */
						String temp[] = existLBforDP.split(",");
						for (int ml = 0; ml < temp.length; ml++) {
							if (temp[ml].contains("_FULL")) {
								valueBufferLBforDP.append(temp[ml]);
								valueBufferLBforDP.append(",");
							}
						}
						/* } */
						String tempvalueBufferLBforDP = valueBufferLBforDP.toString();
						covered_lbcode_full = tempvalueBufferLBforDP.replaceAll("_FULL", "");
						if (covered_lbcode_full.endsWith(",")) {
							covered_lbcode_full = covered_lbcode_full.substring(0, covered_lbcode_full.length() - 1);
							log.debug(covered_lbcode_full);
						}

					}

				}

			}

			String lgd_LBDistCAreaDestListTemp = localGovtBodyForm.getLgd_LBDistCAreaDestList();
			lbhiddenDistrict = localGovtBodyForm.getLgd_LBDistPDestListhidden();

			StringBuffer valueBufferDistMapped = new StringBuffer();
			if (lgd_LBDistCAreaDestListTemp != null) {
				if (lgd_LBDistCAreaDestListTemp.contains(",")) {
					String[] temp = lgd_LBDistCAreaDestListTemp.split(",");
					for (int i = 0; i < temp.length; i++) {

						String[] temp2 = temp[i].split(":");
						for (int j = 0; j < temp2.length; j++) {
							if (temp2[j].contains("_PART_D") || temp2[j].contains("_FULL_D")) {
								valueBufferDistMapped.append(temp2[j]);
								valueBufferDistMapped.append(",");
							}
						}
					}
				} else {
					String[] temp2 = lgd_LBDistCAreaDestListTemp.split(":");
					for (int j = 0; j < temp2.length; j++) {
						if (temp2[j].contains("_PART_D") || temp2[j].contains("_FULL_D")) {
							valueBufferDistMapped.append(temp2[j]);
							valueBufferDistMapped.append(",");
						}
					}

				}
			}
			// System.out.println("THE VALUE BUFFER==== "+valueBufferDistMapped);

			String lgd_LBDistCAreaDestList = valueBufferDistMapped.toString();
			if (lgd_LBDistCAreaDestList.endsWith(",")) {
				lgd_LBDistCAreaDestList = lgd_LBDistCAreaDestList.substring(0, lgd_LBDistCAreaDestList.length() - 1);
				log.debug(lgd_LBDistCAreaDestList);
			}
			// System.out.println("THE FINAL VALUE BUFFER==== "+lgd_LBDistCAreaDestList);

			String lgd_LBSubDistrictDestLatDCATemp = localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA();

			StringBuffer valueBufferSubDistMappedDP = new StringBuffer();
			if (lgd_LBSubDistrictDestLatDCATemp != null) {
				if (lgd_LBSubDistrictDestLatDCATemp.contains(",")) {
					String[] temp = lgd_LBSubDistrictDestLatDCATemp.split(",");
					for (int i = 0; i < temp.length; i++) {

						String[] temp2 = temp[i].split(":");
						for (int j = 0; j < temp2.length; j++) {
							if (temp2[j].contains("_PART_T") || temp2[j].contains("_FULL_T")) {
								valueBufferSubDistMappedDP.append(temp2[j]);
								valueBufferSubDistMappedDP.append(",");
							}
						}
					}
				} else {
					String[] temp2 = lgd_LBSubDistrictDestLatDCATemp.split(":");
					for (int j = 0; j < temp2.length; j++) {
						if (temp2[j].contains("_PART_T") || temp2[j].contains("_FULL_T")) {
							valueBufferSubDistMappedDP.append(temp2[j]);
							valueBufferSubDistMappedDP.append(",");
						}
					}
				}
			}

			String lgd_LBSubDistrictDestLatDCA = valueBufferSubDistMappedDP.toString();
			if (lgd_LBSubDistrictDestLatDCA.endsWith(",")) {
				lgd_LBSubDistrictDestLatDCA = lgd_LBSubDistrictDestLatDCA.substring(0, lgd_LBSubDistrictDestLatDCA.length() - 1);
				log.debug(lgd_LBSubDistrictDestLatDCA);
			}

			String lgd_LBVillageDestLatDCATemp = localGovtBodyForm.getLgd_LBVillageDestLatDCA();

			StringBuffer valueBufferVillageDP = new StringBuffer();
			if (lgd_LBVillageDestLatDCATemp != null) {
				if (lgd_LBVillageDestLatDCATemp.contains(",")) {
					String[] temp = lgd_LBVillageDestLatDCATemp.split(",");
					for (int i = 0; i < temp.length; i++) {

						String[] temp2 = temp[i].split(":");
						for (int j = 0; j < temp2.length; j++) {
							if (temp2[j].contains("_PART_V") || temp2[j].contains("_FULL_V")) {
								valueBufferVillageDP.append(temp2[j]);
								valueBufferVillageDP.append(",");
							}
						}
					}
				} else {
					String[] temp2 = lgd_LBVillageDestLatDCATemp.split(":");
					for (int j = 0; j < temp2.length; j++) {
						if (temp2[j].contains("_PART_V") || temp2[j].contains("_FULL_V")) {
							valueBufferVillageDP.append(temp2[j]);
							valueBufferVillageDP.append(",");
						}
					}
				}
			}

			String lgd_LBVillageDestLatDCA = valueBufferVillageDP.toString();
			if (lgd_LBVillageDestLatDCA.endsWith(",")) {
				lgd_LBVillageDestLatDCA = lgd_LBVillageDestLatDCA.substring(0, lgd_LBVillageDestLatDCA.length() - 1);
				log.debug(lgd_LBVillageDestLatDCA);
			}

			String finalCoverageExistLB = null;

			if (lgd_LBDistCAreaDestListTemp != null && lgd_LBSubDistrictDestLatDCATemp == null && lgd_LBVillageDestLatDCATemp == null) {
				finalCoverageExistLB = lgd_LBDistCAreaDestListTemp;
			} else if (lgd_LBDistCAreaDestListTemp != null && lgd_LBSubDistrictDestLatDCATemp != null && lgd_LBVillageDestLatDCATemp == null) {
				if (lgd_LBSubDistrictDestLatDCATemp.contains(":")) {
					finalCoverageExistLB = lgd_LBDistCAreaDestListTemp + "," + lgd_LBSubDistrictDestLatDCATemp;
				} else {
					finalCoverageExistLB = lgd_LBDistCAreaDestListTemp;
				}
			} else if (lgd_LBDistCAreaDestListTemp != null && lgd_LBSubDistrictDestLatDCATemp != null && lgd_LBVillageDestLatDCATemp != null) {
				if (lgd_LBSubDistrictDestLatDCATemp.contains(":") && lgd_LBVillageDestLatDCATemp.contains(":")) {
					finalCoverageExistLB = lgd_LBDistCAreaDestListTemp + "," + lgd_LBSubDistrictDestLatDCATemp + "," + lgd_LBVillageDestLatDCATemp;
				} else if (lgd_LBSubDistrictDestLatDCATemp.contains(":") && !lgd_LBVillageDestLatDCATemp.contains(":")) {
					finalCoverageExistLB = lgd_LBDistCAreaDestListTemp + "," + lgd_LBSubDistrictDestLatDCATemp;
				} else if (!lgd_LBSubDistrictDestLatDCATemp.contains(":") && !lgd_LBVillageDestLatDCATemp.contains(":")) {
					finalCoverageExistLB = lgd_LBDistCAreaDestListTemp;
				}
			}

			Map<Integer, String> hmapDP = new HashMap<Integer, String>();
			if (finalCoverageExistLB != null) {
				if (finalCoverageExistLB.contains(":")) {
					String[] temp = finalCoverageExistLB.split(",");
					for (int i = 0; i < temp.length; i++) {
						String[] temp2 = temp[i].split(":");
						Integer key = Integer.parseInt(temp2[0].toString());
						String a = hmapDP.get(key);
						if (a != null && a.length() > 0) {
							a = a + "#" + temp2[1];
							hmapDP.put(key, a);
						} else {
							hmapDP.put(key, temp2[1].toString());
						}
					}
				}
				if (hmapDP.toString() != null) {
					lb_landregionList = hmapDP.toString().replace("=", ":").replace("{", "").replace("}", "").replace(" ", "");
				} else {
					lb_landregionList = null;
				}
			}

			//String lgd_LBVillageDestAtVillageCA = localGovtBodyForm.getLgd_LBVillageDestAtVillageCA();
			String lgd_LBDistCAreaDestListUmapped = localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped();
			String lgd_LBSubDistrictDestLatDCAUmapped = localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped();
			String lgd_LBVillageDestLatDCAUmapped = localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped();

			// For IP
			if (lbtypeNameVal[2].equals("P") || lbtypeNameVal[2].equals("T")) {
				if (lbtypeNameVal[1].equals("I")) {
					String existLBforIP = localGovtBodyForm.getLgd_LBInterPDestList();
					StringBuffer valueBufferLBforIP = new StringBuffer();
					if (existLBforIP != null && existLBforIP.contains("FULL")) {
						/*
						 * if(existLBforIP.contains(",")) {
						 */
						String temp[] = existLBforIP.split(",");
						for (int ml = 0; ml < temp.length; ml++) {
							if (temp[ml].contains("_FULL")) {
								valueBufferLBforIP.append(temp[ml]);
								valueBufferLBforIP.append(",");
							}
						}
						/* } */
						String tempvalueBufferLBforIP = valueBufferLBforIP.toString();
						covered_lbcode_full = tempvalueBufferLBforIP.replaceAll("_FULL", "");
						if (covered_lbcode_full.endsWith(",")) {
							covered_lbcode_full = covered_lbcode_full.substring(0, covered_lbcode_full.length() - 1);
							log.debug(covered_lbcode_full);
						}

					}
				}
			}

			String lgd_LBInterCAreaDestListTemp = localGovtBodyForm.getLgd_LBInterCAreaDestList();
			lbhiddenSubDistrict = localGovtBodyForm.getLgd_LBInterSubDestListhidden();
			StringBuffer valueBufferSubDistMappedIP = new StringBuffer();
			if (lgd_LBInterCAreaDestListTemp != null) {
				if (lgd_LBInterCAreaDestListTemp.contains(",")) {
					String[] temp = lgd_LBInterCAreaDestListTemp.split(",");
					for (int i = 0; i < temp.length; i++) {

						String[] temp2 = temp[i].split(":");
						for (int j = 0; j < temp2.length; j++) {
							if (temp2[j].contains("_PART_T") || temp2[j].contains("_FULL_T")) {
								valueBufferSubDistMappedIP.append(temp2[j]);
								valueBufferSubDistMappedIP.append(",");
							}
						}
					}
				} else {
					String[] temp2 = lgd_LBInterCAreaDestListTemp.split(":");
					for (int j = 0; j < temp2.length; j++) {
						if (temp2[j].contains("_PART_T") || temp2[j].contains("_FULL_T")) {
							valueBufferSubDistMappedIP.append(temp2[j]);
							valueBufferSubDistMappedIP.append(",");
						}
					}

				}
			}
			// System.out.println("THE VALUE BUFFER==== "+valueBufferSubDistMappedIP);

			String lgd_LBInterCAreaDestList = valueBufferSubDistMappedIP.toString();
			if (lbhiddenSubDistrict != null) {
				lgd_LBInterCAreaDestList = lgd_LBInterCAreaDestList + lbhiddenSubDistrict;
			}
			if (lgd_LBInterCAreaDestList.endsWith(",")) {
				lgd_LBInterCAreaDestList = lgd_LBInterCAreaDestList.substring(0, lgd_LBInterCAreaDestList.length() - 1);
				log.debug(lgd_LBInterCAreaDestList);
			}
			// System.out.println("THE FINAL VALUE BUFFER==== "+lgd_LBInterCAreaDestList);

			String lgd_LBVillageDestLatICATemp = localGovtBodyForm.getLgd_LBVillageDestLatICA();

			StringBuffer valueBufferVillageMappedIP = new StringBuffer();
			if (lgd_LBVillageDestLatICATemp != null) {
				if (lgd_LBVillageDestLatICATemp.contains(",")) {
					String[] temp = lgd_LBVillageDestLatICATemp.split(",");
					for (int i = 0; i < temp.length; i++) {

						String[] temp2 = temp[i].split(":");
						for (int j = 0; j < temp2.length; j++) {
							if (temp2[j].contains("_PART_V") || temp2[j].contains("_FULL_V")) {
								valueBufferVillageMappedIP.append(temp2[j]);
								valueBufferVillageMappedIP.append(",");
							}
						}
					}
				} else {
					String[] temp2 = lgd_LBVillageDestLatICATemp.split(":");
					for (int j = 0; j < temp2.length; j++) {
						if (temp2[j].contains("_PART_V") || temp2[j].contains("_FULL_V")) {
							valueBufferVillageMappedIP.append(temp2[j]);
							valueBufferVillageMappedIP.append(",");
						}
					}
				}
			}

			String lgd_LBVillageDestLatICA = valueBufferVillageMappedIP.toString();

			if (lgd_LBVillageDestLatICA.endsWith(",")) {
				lgd_LBVillageDestLatICA = lgd_LBVillageDestLatICA.substring(0, lgd_LBVillageDestLatICA.length() - 1);
				log.debug(lgd_LBVillageDestLatICA);
			}

			String finalCoverageExistLBforIP = null;

			if (lgd_LBInterCAreaDestListTemp != null && lgd_LBVillageDestLatICATemp == null) {
				finalCoverageExistLBforIP = lgd_LBInterCAreaDestListTemp;
			} else if (lgd_LBInterCAreaDestListTemp != null && lgd_LBVillageDestLatICATemp != null) {
				if (lgd_LBVillageDestLatICATemp.contains(":")) {
					finalCoverageExistLBforIP = lgd_LBInterCAreaDestListTemp + "," + lgd_LBVillageDestLatICATemp;
				} else {
					finalCoverageExistLBforIP = lgd_LBInterCAreaDestListTemp;
				}
			}

			Map<Integer, String> hmapIP = new HashMap<Integer, String>();
			if (finalCoverageExistLBforIP != null) {
				if (finalCoverageExistLBforIP.contains(":")) {
					String[] temp = finalCoverageExistLBforIP.split(",");
					for (int i = 0; i < temp.length; i++) {
						String[] temp2 = temp[i].split(":");
						Integer key = Integer.parseInt(temp2[0].toString());
						String a = hmapIP.get(key);
						if (a != null && a.length() > 0) {
							a = a + "#" + temp2[1];
							hmapIP.put(key, a);
						} else {
							hmapIP.put(key, temp2[1].toString());
						}
					}
				}
				if (hmapIP.toString() != null) {
					lb_landregionList = hmapIP.toString().replace("=", ":").replace("{", "").replace("}", "").replace(" ", "");
				} else {
					lb_landregionList = null;
				}
			}

			String lgd_LBInterCAreaDestListUmapped = localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped();
			String lgd_LBVillageDestLatICAUmapped = localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped();

			// For VP
			String lgd_LBVillageCAreaDestLTemp = localGovtBodyForm.getLgd_LBVillageCAreaDestL();

			StringBuffer valueBufferVillMapped = new StringBuffer();
			if (lgd_LBVillageCAreaDestLTemp != null) {
				if (lgd_LBVillageCAreaDestLTemp.contains(",")) {
					String[] temp = lgd_LBVillageCAreaDestLTemp.split(",");
					for (int i = 0; i < temp.length; i++) {

						String[] temp2 = temp[i].split(":");
						for (int j = 0; j < temp2.length; j++) {
							if (temp2[j].contains("_PART_V") || temp2[j].contains("_FULL_V")) {
								valueBufferVillMapped.append(temp2[j]);
								valueBufferVillMapped.append(",");
							}
						}
					}
				} else {
					String[] temp2 = lgd_LBVillageCAreaDestLTemp.split(":");
					for (int j = 0; j < temp2.length; j++) {
						if (temp2[j].contains("_PART_V") || temp2[j].contains("_FULL_V")) {
							valueBufferVillMapped.append(temp2[j]);
							valueBufferVillMapped.append(",");
						}
					}

				}
			}
			// System.out.println("THE VALUE BUFFER==== "+valueBufferVillMapped);

			String lgd_LBVillageCAreaDestL = valueBufferVillMapped.toString();
			if (lgd_LBVillageCAreaDestL.endsWith(",")) {
				lgd_LBVillageCAreaDestL = lgd_LBVillageCAreaDestL.substring(0, lgd_LBVillageCAreaDestL.length() - 1);
				log.debug(lgd_LBVillageCAreaDestL);
			}
			// System.out.println("THE FINAL VALUE BUFFER==== "+lgd_LBVillageCAreaDestL);

			Map<Integer, String> hmap = new HashMap<Integer, String>();
			if (lgd_LBVillageCAreaDestLTemp != null) {
				if (lgd_LBVillageCAreaDestLTemp.contains(":")) {
					String[] temp = lgd_LBVillageCAreaDestLTemp.split(",");
					for (int i = 0; i < temp.length; i++) {
						String[] temp2 = temp[i].split(":");
						Integer key = Integer.parseInt(temp2[0].toString());
						String a = hmap.get(key);
						if (a != null && a.length() > 0) {
							a = a + "#" + temp2[1];
							hmap.put(key, a);
						} else {
							hmap.put(key, temp2[1].toString());
						}
					}
				}
				if (hmap.toString() != null) {
					lb_landregionList = hmap.toString().replace("=", ":").replace("{", "").replace("}", "").replace(" ", "");
				} else {
					lb_landregionList = null;
				}
			}

			String lgd_LBVillageCAreaDestLUnmapped = localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped();

			// For Urban
			//String lgd_UrbanLBDistExistDest = localGovtBodyForm.getLgd_UrbanLBDistExistDest();

			String lgd_UrbanLBSubdistrictListDestTemp = localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest();// MAPPED
																												// DATA---URBAN

			StringBuffer valueBufferUrbanMapped = new StringBuffer();
			if (lgd_UrbanLBSubdistrictListDestTemp != null) {
				if (lgd_UrbanLBSubdistrictListDestTemp.contains(",")) {
					String[] temp = lgd_UrbanLBSubdistrictListDestTemp.split(",");
					for (int i = 0; i < temp.length; i++) {
						String[] temp2 = temp[i].split(":");
						for (int j = 0; j < temp2.length; j++) {

							if (temp2[j].contains("_PART_T") || temp2[j].contains("_FULL_T")) {
								valueBufferUrbanMapped.append(temp2[j]);
								valueBufferUrbanMapped.append(",");
							}
							if (isULBDisttLevel) {
								if (temp2[j].contains("_PART_D") || temp2[j].contains("_FULL_D")) {
									valueBufferUrbanMapped.append(temp2[j]);
									valueBufferUrbanMapped.append(",");
								}
							}
						}
					}
				} else {
					String[] temp2 = lgd_UrbanLBSubdistrictListDestTemp.split(":");
					for (int j = 0; j < temp2.length; j++) {
						if (temp2[j].contains("_PART_T") || temp2[j].contains("_FULL_T")) {
							valueBufferUrbanMapped.append(temp2[j]);
							valueBufferUrbanMapped.append(",");
						}
						if (isULBDisttLevel) {
							if (temp2[j].contains("_PART_D") || temp2[j].contains("_FULL_D")) {
								valueBufferUrbanMapped.append(temp2[j]);
								valueBufferUrbanMapped.append(",");
							}
						}
					}
				}
			}

			// System.out.println("THE VALUE BUFFER URBAN==== "+valueBufferUrbanMapped);

			String lgd_UrbanLBSubdistrictListDest = valueBufferUrbanMapped.toString();
			if (lgd_UrbanLBSubdistrictListDest.endsWith(",")) {
				lgd_UrbanLBSubdistrictListDest = lgd_UrbanLBSubdistrictListDest.substring(0, lgd_UrbanLBSubdistrictListDest.length() - 1);
				log.debug(lgd_UrbanLBSubdistrictListDest);
			}
			// System.out.println("THE VALUE BUFFER URBAN==== "+lgd_UrbanLBSubdistrictListDest);

			Map<Integer, String> hmap1 = new HashMap<Integer, String>();
			if (lgd_UrbanLBSubdistrictListDestTemp != null) {
				if (lgd_UrbanLBSubdistrictListDestTemp.contains(":")) {
					String[] temp = lgd_UrbanLBSubdistrictListDestTemp.split(",");
					for (int i = 0; i < temp.length; i++) {
						String[] temp2 = temp[i].split(":");
						Integer key = Integer.parseInt(temp2[0].toString());
						String a = hmap1.get(key);
						if (a != null && a.length() > 0) {
							a = a + "#" + temp2[1];
							hmap1.put(key, a);
						} else {
							hmap1.put(key, temp2[1].toString());
						}
					}
				}
				if (hmap1.toString() != null) {
					lb_landregionList = hmap1.toString().replace("=", ":").replace("{", "").replace("}", "").replace(" ", "");
				} else {
					lb_landregionList = null;
				}

			}

			String lgd_UrbanLBDistUmappedDest = localGovtBodyForm.getLgd_UrbanLBDistUmappedDest();// UN
																									// MAPPED
																									// DATA---URBAN

			// Existing Coverage of Local body DP
			String coverageDistrict = localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped();

			StringBuffer valueBufferDistDP = new StringBuffer();
			if (coverageDistrict != null) {
				if (coverageDistrict.contains(",")) {
					String[] temp = coverageDistrict.split(",");
					for (int i = 0; i < temp.length; i++) {
						if (temp[i].contains("P")) {
							temp[i] = temp[i].replace("_P_false", "").replace("_P_true", "");

							valueBufferDistDP.append(temp[i].concat("_PART_D"));
							if (i < temp.length) {
								valueBufferDistDP.append(",");
							}
						} else if (temp[i].contains("F")) {
							temp[i] = temp[i].replaceAll("_F_false", "").replaceAll("_F_true", "");

							valueBufferDistDP.append(temp[i].concat("_FULL_D"));
							if (i < temp.length) {
								valueBufferDistDP.append(",");
							}
						}
					}
				} else {
					String temp = coverageDistrict;

					if (temp.contains("P")) {
						temp = temp.replace("_P_false", "").replace("_P_true", "");
						valueBufferDistDP.append(temp.concat("_PART_D"));
					} else if (temp.contains("F")) {
						temp = temp.replaceAll("_F_false", "").replaceAll("_F_true", "");
						valueBufferDistDP.append(temp.concat("_FULL_D"));
						valueBufferDistDP.append(",");
					}
				}
			}
			// System.out.println("THE VALUE BUFFER==== "+valueBufferDistDP);
			String coverageDistrictF = valueBufferDistDP.toString();
			if (lbhiddenDistrict != null) {
				if (!coverageDistrictF.endsWith(",")) {
					coverageDistrictF = coverageDistrictF + "," + lbhiddenDistrict;
				} else {
					coverageDistrictF = coverageDistrictF + lbhiddenDistrict;
				}
			}
			if (!coverageDistrictF.endsWith(",")) {
				coverageDistrictF = coverageDistrictF + ",";
			} /*else {
				coverageDistrictF = coverageDistrictF;
			}*/

			/*
			 * if(localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped()
			 * !=null &&
			 * localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped()
			 * ==null &&
			 * localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped()
			 * ==null && localGovtBodyForm.getLgd_LBDistCAreaDestList() ==null
			 * && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() ==null &&
			 * localGovtBodyForm.getLgd_LBVillageDestLatDCA() ==null &&
			 * localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() ==null &&
			 * localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() ==null
			 * && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped()==null) {
			 * coverageDistrictF=valueBufferDistDP.toString(); } else {
			 * if(!coverageDistrictF.endsWith(",")) {
			 * coverageDistrictF=valueBufferDistDP.toString()+","; } }
			 */

			String coverageSubDistrict = localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped();
			String coverageVillage = localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped();

			// Existing Coverage of Local Body IP
			String coverageSubDistrictIP = localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped();

			StringBuffer valueBufferSubDistIP = new StringBuffer();
			if (coverageSubDistrictIP != null) {
				if (coverageSubDistrictIP.contains(",")) {
					String[] temp = coverageSubDistrictIP.split(",");
					for (int i = 0; i < temp.length; i++) {
						if (temp[i].contains("P")) {
							temp[i] = temp[i].replace("_P_false", "").replace("_P_true", "");

							// Conditional Check whether Logged in state is ULB
							// operate District wise.
							if (isULBDisttLevel) {
								valueBufferSubDistIP.append(temp[i].concat("_PART_D"));
							} else {
								valueBufferSubDistIP.append(temp[i].concat("_PART_T"));
							}
							if (i < temp.length) {
								valueBufferSubDistIP.append(",");
							}
						} else if (temp[i].contains("F")) {
							temp[i] = temp[i].replaceAll("_F_false", "").replaceAll("_F_true", "");

							// Conditional Check whether Logged in state is ULB
							// operate District wise.
							if (isULBDisttLevel) {
								valueBufferSubDistIP.append(temp[i].concat("_FULL_D"));
							} else {
								valueBufferSubDistIP.append(temp[i].concat("_FULL_T"));
							}

							if (i < temp.length) {
								valueBufferSubDistIP.append(",");
							}
						}
					}
				} else {
					String temp = coverageSubDistrictIP;

					if (temp.contains("P")) {
						temp = temp.replace("_P_false", "").replace("_P_true", "");

						// Conditional Check whether Logged in state is ULB
						// operate District wise.
						if (isULBDisttLevel) {
							valueBufferSubDistIP.append(temp.concat("_PART_D"));
						} else {
							valueBufferSubDistIP.append(temp.concat("_PART_T"));
						}

					} else if (temp.contains("F")) {
						temp = temp.replaceAll("_F_false", "").replaceAll("_F_true", "");

						// Conditional Check whether Logged in state is ULB
						// operate District wise.
						if (isULBDisttLevel) {
							valueBufferSubDistIP.append(temp.concat("_FULL_D"));
						} else {
							valueBufferSubDistIP.append(temp.concat("_FULL_T"));
						}

						valueBufferSubDistIP.append(",");
					}
				}
			}
			// System.out.println("THE VALUE BUFFER==== "+valueBufferSubDistIP);
			String coverageSubDistrictIPF = valueBufferSubDistIP.toString();
			if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() == null && localGovtBodyForm.getLgd_LBInterCAreaDestList() == null
					&& localGovtBodyForm.getLgd_LBVillageDestLatICA() == null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() == null) {
				coverageSubDistrictIPF = valueBufferSubDistIP.toString();
			} else {
				if (!coverageSubDistrictIPF.endsWith(",")) {
					coverageSubDistrictIPF = valueBufferSubDistIP.toString() + ",";
				}
			}

			String coverageVillageIP = localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped();

			// Existing Coverage of Local Body VP
			String coverageVillageVP = localGovtBodyForm.getAvaillgd_LBVillageCAreaSourceLUnmapped();

			// Existing Coverage of Local Body Urban Local Body
			String coverageDataUrban = localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmappedUrban();

			//StringBuffer valueBufferDis = new StringBuffer();

			StringBuffer valueBufferSubDis = new StringBuffer();
			if (coverageSubDistrict != null) {
				if (coverageSubDistrict.contains(",")) {
					String[] temp = coverageSubDistrict.split(",");
					// StringBuffer valueBuffer = new StringBuffer();
					for (int i = 0; i < temp.length; i++) {
						if (temp[i].contains("P")) {
							temp[i] = temp[i].replace("_P_false", "").replace("_P_true", "");
							/*
							 * if(isULBDisttLevel){
							 * valueBufferSubDis.append(temp
							 * [i].concat("_PART_D")); }else{
							 * valueBufferSubDis.append
							 * (temp[i].concat("_PART_T")); }
							 */
							valueBufferSubDis.append(temp[i].concat("_PART_T"));

							if (i < temp.length) {
								valueBufferSubDis.append(",");
							}
						} else if (temp[i].contains("F")) {
							temp[i] = temp[i].replaceAll("_F_false", "").replaceAll("_F_true", "");
							/*
							 * if(isULBDisttLevel){
							 * valueBufferSubDis.append(temp
							 * [i].concat("_FULL_D")); }else{
							 * valueBufferSubDis.append
							 * (temp[i].concat("_FULL_T")); }
							 */
							valueBufferSubDis.append(temp[i].concat("_FULL_T"));

							if (i < temp.length) {
								valueBufferSubDis.append(",");
							}
						}
					}
				} else {
					String temp = coverageSubDistrict;

					if (temp.contains("P")) {
						temp = temp.replace("_P_false", "").replace("_P_true", "");
						/*
						 * if(isULBDisttLevel){
						 * valueBufferSubDis.append(temp.concat("_PART_D"));
						 * }else{
						 * valueBufferSubDis.append(temp.concat("_PART_T")); }
						 */
						valueBufferSubDis.append(temp.concat("_PART_T"));
						valueBufferSubDis.append(",");
					} else if (temp.contains("F")) {
						temp = temp.replaceAll("_F_false", "").replaceAll("_F_true", "");
						/*
						 * if(isULBDisttLevel){
						 * valueBufferSubDis.append(temp.concat("_FULL_D"));
						 * }else{
						 * valueBufferSubDis.append(temp.concat("_FULL_T")); }
						 */
						valueBufferSubDis.append(temp.concat("_FULL_T"));
						valueBufferSubDis.append(",");
					}
				}
			}

			// System.out.println("THE VALUE BUFFER==== "+valueBufferSubDis);
			String coverageSubDistrictF = valueBufferSubDis.toString();

			StringBuffer valueBufferVill = new StringBuffer();
			if (coverageVillage != null) {
				if (coverageVillage.contains(",")) {
					String[] temp = coverageVillage.split(",");
					// StringBuffer valueBuffer = new StringBuffer();
					for (int i = 0; i < temp.length; i++) {
						if (temp[i].contains("P")) {
							temp[i] = temp[i].replaceAll("_P_false", "").replaceAll("_P_true", "");
							valueBufferVill.append(temp[i].concat("_PART_V"));
							if (i < temp.length) {
								valueBufferVill.append(",");
							}
						} else if (temp[i].contains("F")) {
							temp[i] = temp[i].replaceAll("_F_false", "").replaceAll("_F_true", "");

							valueBufferVill.append(temp[i].concat("_FULL_V"));
							if (i < temp.length) {
								valueBufferVill.append(",");
							}
						}

					}
				} else {
					String temp = coverageVillage;
					if (temp.contains("P")) {
						temp = temp.replace("_P_false", "").replace("_P_true", "");
						valueBufferVill.append(temp.concat("_PART_V"));
						valueBufferVill.append(",");
					} else if (temp.contains("F")) {
						temp = temp.replaceAll("_F_false", "").replaceAll("_F_true", "");
						valueBufferVill.append(temp.concat("_FULL_V"));
						valueBufferVill.append(",");
					}
				}
			}

			// System.out.println("THE VALUE BUFFER==== "+valueBufferVill);
			String coverageVillageF = valueBufferVill.toString();

			/*
			 * StringBuffer valueBufferSubDisIP = new StringBuffer();
			 * if(coverageSubDistrictIP !=null) { if
			 * (coverageSubDistrictIP.contains(",")) { String[] temp =
			 * coverageSubDistrictIP.split(","); //StringBuffer valueBuffer =
			 * new StringBuffer(); for (int i = 0; i < temp.length; i++) {
			 * valueBufferSubDisIP.append(temp[i].concat("_PART_T")); if (i <
			 * temp.length) valueBufferSubDisIP.append(","); } } else {
			 * valueBufferSubDisIP
			 * .append(coverageSubDistrictIP.concat("_PART_T"));
			 * valueBufferSubDisIP.append(","); } }
			 * //System.out.println("THE VALUE BUFFER==== "
			 * +valueBufferSubDisIP); String
			 * coverageSubDistrictIPF=valueBufferSubDisIP.toString();
			 */

			StringBuffer valueBufferVillIP = new StringBuffer();
			if (coverageVillageIP != null) {
				if (coverageVillageIP.contains(",")) {

					String[] temp = coverageVillageIP.split(",");
					// StringBuffer valueBuffer = new StringBuffer();
					for (int i = 0; i < temp.length; i++) {
						if (temp[i].contains("P")) {
							temp[i] = temp[i].replaceAll("_P_false", "").replaceAll("_P_true", "");

							valueBufferVillIP.append(temp[i].concat("_PART_V"));
							if (i < temp.length) {
								valueBufferVillIP.append(",");
							}
						} else if (temp[i].contains("F")) {
							temp[i] = temp[i].replaceAll("_F_false", "").replaceAll("_F_true", "");

							valueBufferVillIP.append(temp[i].concat("_FULL_V"));
							if (i < temp.length) {
								valueBufferVillIP.append(",");
							}
						}
					}
				} else {
					String temp = coverageVillageIP;
					if (temp.contains("P")) {
						temp = temp.replace("_P_false", "").replace("_P_true", "");
						valueBufferVillIP.append(temp.concat("_PART_V"));
						valueBufferVillIP.append(",");
					} else if (temp.contains("F")) {
						temp = temp.replaceAll("_F_false", "").replaceAll("_F_true", "");
						valueBufferVillIP.append(temp.concat("_FULL_V"));
						valueBufferVillIP.append(",");
					}
				}

			}
			// System.out.println("THE VALUE BUFFER==== "+valueBufferVillIP);
			String coverageVillageIPF = valueBufferVillIP.toString();

			StringBuffer valueBufferVillVP = new StringBuffer();
			if (coverageVillageVP != null) {
				if (coverageVillageVP.contains(",")) {
					String[] temp = coverageVillageVP.split(",");
					for (int i = 0; i < temp.length; i++) {
						if (temp[i].contains("P")) {
							temp[i] = temp[i].replace("_P_false", "").replace("_P_true", "");

							valueBufferVillVP.append(temp[i].concat("_PART_V"));
							if (i < temp.length) {
								valueBufferVillVP.append(",");
							}
						} else if (temp[i].contains("F")) {
							temp[i] = temp[i].replaceAll("_F_false", "").replaceAll("_F_true", "");

							valueBufferVillVP.append(temp[i].concat("_FULL_V"));
							if (i < temp.length) {
								valueBufferVillVP.append(",");
							}
						}
					}
				} else {
					String temp = coverageVillageVP;

					if (temp.contains("P")) {
						temp = temp.replace("_P_false", "").replace("_P_true", "");
						valueBufferVillVP.append(temp.concat("_PART_V"));
					} else if (temp.contains("F")) {
						temp = temp.replaceAll("_F_false", "").replaceAll("_F_true", "");
						valueBufferVillVP.append(temp.concat("_FULL_V"));
						valueBufferVillVP.append(",");
					}
				}
			}
			// System.out.println("THE VALUE BUFFER==== "+valueBufferVillVP);
			String coverageVillageVPF = valueBufferVillVP.toString();
			if (!coverageVillageVPF.endsWith(",")) {
				coverageVillageVPF = valueBufferVillVP.toString() + ",";
			}

			// String
			// coverageDataUrban=localGovtBodyForm.getLgd_LBInterCAreaSourceListUmapped();
			StringBuffer valueBufferUrban = new StringBuffer();
			if (coverageDataUrban != null) {
				if (coverageDataUrban.contains(",")) {
					String[] temp = coverageDataUrban.split(",");
					// StringBuffer valueBuffer = new StringBuffer();
					for (int i = 0; i < temp.length; i++) {
						// Conditional Check whether Logged in state is ULB
						// operate District wise.
						if (isULBDisttLevel) {
							valueBufferUrban.append(temp[i].concat("_PART_D"));
						} else {
							valueBufferUrban.append(temp[i].concat("_PART_T"));
						}

						if (i < temp.length) {
							valueBufferUrban.append(",");
						}
					}
				} else {
					// Conditional Check whether Logged in state is ULB operate
					// District wise.
					if (isULBDisttLevel) {
						valueBufferUrban.append(coverageDataUrban.concat("_PART_D"));
					} else {
						valueBufferUrban.append(coverageDataUrban.concat("_PART_T"));
					}

					valueBufferUrban.append(",");
				}

			}
			// System.out.println("THE VALUE BUFFER==== "+valueBufferUrban);
			String coverageULB = valueBufferUrban.toString();

			String landregionlist = null;

			// Code for PRI Local Body----Start

			if (lbtypeNameVal[2].equals("P") || lbtypeNameVal[2].equals("T")) {
				if (lbtypeNameVal[1].equals("D")) {
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() != null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA + "," + lgd_LBVillageDestLatDCA + "," + lgd_LBDistCAreaDestListUmapped + "," + lgd_LBSubDistrictDestLatDCAUmapped + ","
								+ lgd_LBVillageDestLatDCAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() != null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA + "," + lgd_LBVillageDestLatDCA + "," + lgd_LBDistCAreaDestListUmapped + "," + lgd_LBSubDistrictDestLatDCAUmapped + ","
								+ lgd_LBVillageDestLatDCAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() != null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA + "," + lgd_LBVillageDestLatDCA + "," + lgd_LBDistCAreaDestListUmapped + ","
								+ lgd_LBSubDistrictDestLatDCAUmapped + "," + lgd_LBVillageDestLatDCAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() != null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA + "," + lgd_LBVillageDestLatDCA + "," + lgd_LBDistCAreaDestListUmapped + ","
								+ lgd_LBSubDistrictDestLatDCAUmapped + "," + lgd_LBVillageDestLatDCAUmapped;
					}

					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA + "," + lgd_LBVillageDestLatDCA + "," + lgd_LBDistCAreaDestListUmapped + "," + lgd_LBSubDistrictDestLatDCAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA + "," + lgd_LBVillageDestLatDCA + "," + lgd_LBDistCAreaDestListUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA + "," + lgd_LBVillageDestLatDCA;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestList + "," + lgd_LBDistCAreaDestListUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestList + "," + lgd_LBDistCAreaDestListUmapped + "," + lgd_LBSubDistrictDestLatDCAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() != null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestList + "," + lgd_LBDistCAreaDestListUmapped + "," + lgd_LBSubDistrictDestLatDCAUmapped + "," + lgd_LBVillageDestLatDCAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA + "," + lgd_LBDistCAreaDestListUmapped;
					}

					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA + "," + lgd_LBDistCAreaDestListUmapped + lgd_LBSubDistrictDestLatDCAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() != null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA + "," + lgd_LBDistCAreaDestListUmapped + lgd_LBSubDistrictDestLatDCAUmapped + ","
								+ lgd_LBVillageDestLatDCAUmapped;
					}

					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA + "," + lgd_LBVillageDestLatDCA + "," + lgd_LBDistCAreaDestListUmapped;
					}

					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA + "," + lgd_LBDistCAreaDestListUmapped + "," + lgd_LBSubDistrictDestLatDCAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + lgd_LBDistCAreaDestList + "," + lgd_LBVillageDestLatDCA + "," + lgd_LBDistCAreaDestListUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA;
					}

					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + lgd_LBDistCAreaDestList + "," + lgd_LBVillageDestLatDCA + "," + lgd_LBDistCAreaDestListUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA + "," + lgd_LBVillageDestLatDCA;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() != null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestListUmapped + "," + lgd_LBSubDistrictDestLatDCAUmapped + "," + lgd_LBVillageDestLatDCAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null && localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA;
					}

					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + "," + lgd_LBDistCAreaDestList;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestList;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null && localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestList;
					}

					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA + "," + "," + lgd_LBDistCAreaDestListUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA + "," + lgd_LBDistCAreaDestListUmapped + "," + lgd_LBSubDistrictDestLatDCAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() != null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA + "," + lgd_LBDistCAreaDestListUmapped + "," + lgd_LBSubDistrictDestLatDCAUmapped + "," + lgd_LBVillageDestLatDCAUmapped;
					}

					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA + "," + lgd_LBDistCAreaDestListUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null && localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA;
					}

					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() != null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestList;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestList;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null && localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestList;
					}

					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBDistCAreaDestList() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null && localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestListUmapped;
					}

					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBDistCAreaDestList() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null && localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + lgd_LBDistCAreaDestListUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBDistCAreaDestList() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null && localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestListUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() != null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + lgd_LBDistCAreaDestListUmapped + "," + lgd_LBSubDistrictDestLatDCAUmapped + "," + lgd_LBVillageDestLatDCAUmapped;
					}

					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBDistCAreaDestList() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null && localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestListUmapped + "," + lgd_LBSubDistrictDestLatDCAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBDistCAreaDestList() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null && localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() != null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestListUmapped + "," + lgd_LBSubDistrictDestLatDCAUmapped + "," + lgd_LBVillageDestLatDCAUmapped;
					}

					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() != null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestListUmapped + "," + lgd_LBSubDistrictDestLatDCAUmapped + "," + lgd_LBVillageDestLatDCAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestListUmapped + "," + lgd_LBSubDistrictDestLatDCAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestListUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() == null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageVillageF + lgd_LBDistCAreaDestList;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageVillageF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA + "," + lgd_LBVillageDestLatDCA;
					}

					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestList + "," + lgd_LBDistCAreaDestListUmapped + "," + lgd_LBSubDistrictDestLatDCAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() != null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestList + "," + lgd_LBDistCAreaDestListUmapped + "," + lgd_LBSubDistrictDestLatDCAUmapped + "," + lgd_LBVillageDestLatDCAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() != null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestListUmapped + "," + lgd_LBSubDistrictDestLatDCAUmapped + "," + lgd_LBVillageDestLatDCAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA + "," + lgd_LBDistCAreaDestListUmapped + "," + lgd_LBSubDistrictDestLatDCAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageVillageF;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestList;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA + "," + lgd_LBVillageDestLatDCA;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						if (!coverageDistrictF.endsWith(",")) {
							landregionlist = coverageDistrictF + "," + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestList;
						} else {
							landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestList;
						}

					}
				} else if (lbtypeNameVal[1].equals("I")) {
					// Code for Intermediate Panchayat

					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() != null && localGovtBodyForm.getLgd_LBInterCAreaDestList() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() != null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() != null) {
						landregionlist = coverageSubDistrictIPF + coverageVillageIPF + lgd_LBInterCAreaDestList + "," + lgd_LBVillageDestLatICA + "," + lgd_LBInterCAreaDestListUmapped + "," + lgd_LBVillageDestLatICAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() != null && localGovtBodyForm.getLgd_LBInterCAreaDestList() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() != null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() == null) {
						landregionlist = coverageSubDistrictIPF + coverageVillageIPF + "," + lgd_LBVillageDestLatICA;
					}
					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() != null && localGovtBodyForm.getLgd_LBInterCAreaDestList() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() == null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() == null) {
						landregionlist = coverageSubDistrictIPF + coverageVillageIPF + lgd_LBInterCAreaDestList;
					}

					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() == null && localGovtBodyForm.getLgd_LBInterCAreaDestList() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() != null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() != null) {
						landregionlist = coverageSubDistrictIPF + lgd_LBInterCAreaDestList + "," + lgd_LBVillageDestLatICA + "," + lgd_LBInterCAreaDestListUmapped + "," + lgd_LBVillageDestLatICAUmapped;
					}

					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() == null && localGovtBodyForm.getLgd_LBInterCAreaDestList() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() != null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() == null) {
						landregionlist = coverageSubDistrictIPF + lgd_LBInterCAreaDestList + "," + lgd_LBVillageDestLatICA;
					}

					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() == null && localGovtBodyForm.getLgd_LBInterCAreaDestList() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() == null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() == null) {
						landregionlist = coverageSubDistrictIPF + lgd_LBInterCAreaDestList + "," + lgd_LBInterCAreaDestListUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() != null && localGovtBodyForm.getLgd_LBInterCAreaDestList() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() == null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() == null) {
						landregionlist = coverageSubDistrictIPF + coverageVillageIPF + lgd_LBInterCAreaDestList + "," + lgd_LBInterCAreaDestListUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() != null && localGovtBodyForm.getLgd_LBInterCAreaDestList() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() == null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() != null) {
						landregionlist = coverageSubDistrictIPF + coverageVillageIPF + lgd_LBInterCAreaDestList + "," + lgd_LBInterCAreaDestListUmapped + "," + lgd_LBVillageDestLatICAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() != null && localGovtBodyForm.getLgd_LBInterCAreaDestList() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() == null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() != null) {
						landregionlist = coverageSubDistrictIPF + coverageVillageIPF + lgd_LBInterCAreaDestList + "," + lgd_LBInterCAreaDestListUmapped + "," + lgd_LBVillageDestLatICAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() != null && localGovtBodyForm.getLgd_LBInterCAreaDestList() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() == null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() != null) {
						landregionlist = coverageSubDistrictIPF + coverageVillageIPF + lgd_LBInterCAreaDestListUmapped + "," + lgd_LBVillageDestLatICAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() != null && localGovtBodyForm.getLgd_LBInterCAreaDestList() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() == null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() == null) {
						landregionlist = coverageSubDistrictIPF + coverageVillageIPF + lgd_LBInterCAreaDestListUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() != null && localGovtBodyForm.getLgd_LBInterCAreaDestList() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() != null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() == null) {
						landregionlist = coverageSubDistrictIPF + coverageVillageIPF + lgd_LBInterCAreaDestList + "," + lgd_LBVillageDestLatICA;
					}
					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() != null && localGovtBodyForm.getLgd_LBInterCAreaDestList() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() == null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() != null) {
						landregionlist = coverageSubDistrictIPF + coverageVillageIPF + lgd_LBInterCAreaDestListUmapped + "," + lgd_LBVillageDestLatICAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() == null && localGovtBodyForm.getLgd_LBInterCAreaDestList() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() == null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() == null) {
						landregionlist = coverageSubDistrictIPF;
					}
					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() != null && localGovtBodyForm.getLgd_LBInterCAreaDestList() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() == null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() == null) {
						landregionlist = coverageSubDistrictIPF + coverageVillageIPF;
					}

					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() == null && localGovtBodyForm.getLgd_LBInterCAreaDestList() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() == null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() != null) {
						landregionlist = coverageSubDistrictIPF + lgd_LBInterCAreaDestListUmapped + "," + lgd_LBVillageDestLatICAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() == null && localGovtBodyForm.getLgd_LBInterCAreaDestList() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() == null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() != null) {
						landregionlist = coverageSubDistrictIPF + lgd_LBInterCAreaDestListUmapped + "," + lgd_LBVillageDestLatICAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() == null && localGovtBodyForm.getLgd_LBInterCAreaDestList() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() == null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() == null) {
						landregionlist = coverageSubDistrictIPF + lgd_LBInterCAreaDestListUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() == null && localGovtBodyForm.getLgd_LBInterCAreaDestList() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() == null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() == null) {
						landregionlist = coverageSubDistrictIPF + lgd_LBInterCAreaDestList;
					}

					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() == null && localGovtBodyForm.getLgd_LBInterCAreaDestList() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() == null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() != null) {
						landregionlist = coverageSubDistrictIPF + lgd_LBInterCAreaDestList + "," + lgd_LBInterCAreaDestListUmapped + "," + lgd_LBVillageDestLatICAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() != null && localGovtBodyForm.getLgd_LBInterCAreaDestList() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() == null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() != null) {
						landregionlist = coverageSubDistrictIPF + coverageVillageIPF + lgd_LBInterCAreaDestList + "," + lgd_LBInterCAreaDestListUmapped + "," + lgd_LBVillageDestLatICAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() != null && localGovtBodyForm.getLgd_LBInterCAreaDestList() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() == null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() == null) {
						landregionlist = coverageVillageIPF;
					}
					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() == null && localGovtBodyForm.getLgd_LBInterCAreaDestList() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() == null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() == null) {
						landregionlist = lgd_LBInterCAreaDestList;
					}

				} else if (lbtypeNameVal[1].equals("V")) {
					// Code for Village Panchayat

					if (localGovtBodyForm.getAvaillgd_LBVillageCAreaSourceLUnmapped() != null && localGovtBodyForm.getLgd_LBVillageCAreaDestL() != null && localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped() != null) {
						landregionlist = coverageVillageVPF + lgd_LBVillageCAreaDestL + "," + lgd_LBVillageCAreaDestLUnmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBVillageCAreaSourceLUnmapped() == null && localGovtBodyForm.getLgd_LBVillageCAreaDestL() != null && localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped() != null) {
						landregionlist = lgd_LBVillageCAreaDestL + "," + lgd_LBVillageCAreaDestLUnmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBVillageCAreaSourceLUnmapped() == null && localGovtBodyForm.getLgd_LBVillageCAreaDestL() == null && localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped() != null) {
						landregionlist = lgd_LBVillageCAreaDestLUnmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBVillageCAreaSourceLUnmapped() == null && localGovtBodyForm.getLgd_LBVillageCAreaDestL() != null && localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped() == null) {
						landregionlist = lgd_LBVillageCAreaDestL;
					}
					if (localGovtBodyForm.getAvaillgd_LBVillageCAreaSourceLUnmapped() != null && localGovtBodyForm.getLgd_LBVillageCAreaDestL() != null && localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped() == null) {
						landregionlist = coverageVillageVPF + lgd_LBVillageCAreaDestL;
					}
					if (localGovtBodyForm.getAvaillgd_LBVillageCAreaSourceLUnmapped() != null && localGovtBodyForm.getLgd_LBVillageCAreaDestL() == null && localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped() == null) {
						landregionlist = coverageVillageVPF;
					}
					if (localGovtBodyForm.getAvaillgd_LBVillageCAreaSourceLUnmapped() != null && localGovtBodyForm.getLgd_LBVillageCAreaDestL() == null && localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped() != null) {
						landregionlist = coverageVillageVPF + lgd_LBVillageCAreaDestLUnmapped;
					}
				}
			} else if (lbtypeNameVal[2].equals("U")) {
				// Code for Urban Local Body----Start
				if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmappedUrban() != null && localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest() != null && localGovtBodyForm.getLgd_UrbanLBDistUmappedDest() != null) {
					landregionlist = coverageULB + lgd_UrbanLBSubdistrictListDest + "," + lgd_UrbanLBDistUmappedDest;
				}
				if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmappedUrban() != null && localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest() != null && localGovtBodyForm.getLgd_UrbanLBDistUmappedDest() == null) {
					landregionlist = coverageULB + lgd_UrbanLBSubdistrictListDest;
				}
				if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmappedUrban() != null && localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest() == null && localGovtBodyForm.getLgd_UrbanLBDistUmappedDest() != null) {
					landregionlist = coverageULB + lgd_UrbanLBDistUmappedDest;
				}
				if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmappedUrban() == null && localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest() != null && localGovtBodyForm.getLgd_UrbanLBDistUmappedDest() != null) {
					landregionlist = lgd_UrbanLBSubdistrictListDest + "," + lgd_UrbanLBDistUmappedDest;
				}
				if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmappedUrban() == null && localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest() != null && localGovtBodyForm.getLgd_UrbanLBDistUmappedDest() == null) {
					landregionlist = lgd_UrbanLBSubdistrictListDest;
				}
				if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmappedUrban() == null && localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest() == null && localGovtBodyForm.getLgd_UrbanLBDistUmappedDest() != null) {
					landregionlist = lgd_UrbanLBDistUmappedDest;
				}
				if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmappedUrban() != null && localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest() == null && localGovtBodyForm.getLgd_UrbanLBDistUmappedDest() != null) {
					landregionlist = coverageULB + lgd_UrbanLBDistUmappedDest;
				}
				if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmappedUrban() != null && localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest() == null && localGovtBodyForm.getLgd_UrbanLBDistUmappedDest() == null) {
					landregionlist = coverageULB;
				}
			}

			if (landregionlist.endsWith(",")) {
				landregionlist = landregionlist.substring(0, landregionlist.length() - 1);
				log.debug(landregionlist);
			}

			if (lbtypeNameVal[2].equals("P") || lbtypeNameVal[2].equals("T")) {

				if (lbtypeNameVal[1].equals("D") && localGovtBodyForm.getHeadQuarterCode() != null) {
					String valu = null;
					String[] retavl = null;
					if (localGovtBodyForm.getHeadQuarterCode().contains(":")) {
						valu = localGovtBodyForm.getHeadQuarterCode();
						retavl = valu.split(":");
					}

					StringBuffer sb2 = new StringBuffer();
					if (localGovtBodyForm.getHeadQuarterCode() != null) {
						existingLocalBDataFinal = landregionlist.split(",");

						for (int i = 0; i < existingLocalBDataFinal.length; i++) {
							if (localGovtBodyForm.getHeadQuarterCode().contains(":")) {
								if (retavl[1].equals(existingLocalBDataFinal[i])) {
									String temp = existingLocalBDataFinal[i] + "_true";
									sb2.append(temp);
									sb2.append(",");
								} else {
									String temp = existingLocalBDataFinal[i] + "_false";
									sb2.append(temp);
									sb2.append(",");
								}
							} else {
								String coverageVillageVPFF = localGovtBodyForm.getHeadQuarterCode();
								String finalcoverageVillageVPFF = null;
								if (coverageVillageVPFF.contains("_F_true") || coverageVillageVPFF.contains("_F_false") || coverageVillageVPFF.contains("_P_true") || coverageVillageVPFF.contains("_P_false")) {
									StringBuffer valueBufferVillVPF = new StringBuffer();
									if (coverageVillageVPFF != null) {
										if (coverageVillageVPFF.contains(",")) {
											String[] temp = coverageVillageVPFF.split(",");
											for (int j = 0; j < temp.length; j++) {
												if (temp[j].contains("P")) {
													temp[j] = temp[j].replace("_P_false", "").replace("_P_true", "");

													valueBufferVillVPF.append(temp[j].concat("_PART_D"));
													/*
													 * if (i < temp.length)
													 * valueBufferVillVPF
													 * .append(",");
													 */
												} else if (temp[j].contains("F")) {
													temp[j] = temp[j].replaceAll("_F_false", "").replaceAll("_F_true", "");

													valueBufferVillVPF.append(temp[j].concat("_FULL_D"));
													/*
													 * if (j < temp.length)
													 * valueBufferVillVPF
													 * .append(",");
													 */
												}
											}
										} else {
											String temp = localGovtBodyForm.getHeadQuarterCode();

											if (temp.contains("P")) {
												temp = temp.replace("_P_false", "").replace("_P_true", "");
												valueBufferVillVPF.append(temp.concat("_PART_D"));
											} else if (temp.contains("F")) {
												temp = temp.replaceAll("_F_false", "").replaceAll("_F_true", "");
												valueBufferVillVPF.append(temp.concat("_FULL_D"));
												// valueBufferVillVPF.append(",");
											}
										}
									}
									// System.out.println("THE VALUE BUFFER==== "+valueBufferVillVPF);
									finalcoverageVillageVPFF = valueBufferVillVPF.toString();
								} else {
									finalcoverageVillageVPFF = coverageVillageVPFF;
								}

								if (finalcoverageVillageVPFF.equals(existingLocalBDataFinal[i])) {
									String temp = existingLocalBDataFinal[i] + "_true";
									sb2.append(temp);
									sb2.append(",");
								} else {
									String temp = existingLocalBDataFinal[i] + "_false";
									sb2.append(temp);
									if (i + 1 < existingLocalBDataFinal.length) {
										sb2.append(",");
									}
								}
							}
						}

					}
					if (localGovtBodyForm.getHeadQuarterCode() != null) {
						combinedLandregion = sb2.toString();
						if (combinedLandregion.endsWith(",")) {
							combinedLandregion = combinedLandregion.substring(0, combinedLandregion.length() - 1);
						}
					}
				}

				else if (lbtypeNameVal[1].equals("I") && localGovtBodyForm.getHeadQuarterCode() != null) {
					String valu = null;
					String[] retavl = null;
					if (localGovtBodyForm.getHeadQuarterCode().contains(":")) {
						valu = localGovtBodyForm.getHeadQuarterCode();
						retavl = valu.split(":");
					}

					StringBuffer sb2 = new StringBuffer();
					if (localGovtBodyForm.getHeadQuarterCode() != null) {
						existingLocalBDataFinal = landregionlist.split(",");

						for (int i = 0; i < existingLocalBDataFinal.length; i++) {
							if (localGovtBodyForm.getHeadQuarterCode().contains(":")) {
								if (retavl[1].equals(existingLocalBDataFinal[i])) {
									String temp = existingLocalBDataFinal[i] + "_true";
									sb2.append(temp);
									sb2.append(",");
								} else {
									String temp = existingLocalBDataFinal[i] + "_false";
									sb2.append(temp);
									sb2.append(",");
								}
							} else {
								String coverageVillageVPFF = localGovtBodyForm.getHeadQuarterCode();
								String finalcoverageVillageVPFF = null;
								if (coverageVillageVPFF.contains("_F_true") || coverageVillageVPFF.contains("_F_false") || coverageVillageVPFF.contains("_P_true") || coverageVillageVPFF.contains("_P_false")) {
									StringBuffer valueBufferVillVPF = new StringBuffer();
									if (coverageVillageVPFF != null) {
										if (coverageVillageVPFF.contains(",")) {
											String[] temp = coverageVillageVPFF.split(",");
											for (int j = 0; j < temp.length; j++) {
												if (temp[j].contains("P")) {
													temp[j] = temp[j].replace("_P_false", "").replace("_P_true", "");

													valueBufferVillVPF.append(temp[j].concat("_PART_T"));
													/*
													 * if (i < temp.length)
													 * valueBufferVillVPF
													 * .append(",");
													 */
												} else if (temp[j].contains("F")) {
													temp[j] = temp[j].replaceAll("_F_false", "").replaceAll("_F_true", "");

													valueBufferVillVPF.append(temp[j].concat("_FULL_T"));
													/*
													 * if (j < temp.length)
													 * valueBufferVillVPF
													 * .append(",");
													 */
												}
											}
										} else {
											String temp = localGovtBodyForm.getHeadQuarterCode();

											if (temp.contains("P")) {
												temp = temp.replace("_P_false", "").replace("_P_true", "");
												valueBufferVillVPF.append(temp.concat("_PART_T"));
											} else if (temp.contains("F")) {
												temp = temp.replaceAll("_F_false", "").replaceAll("_F_true", "");
												valueBufferVillVPF.append(temp.concat("_FULL_T"));
												// valueBufferVillVPF.append(",");
											}
										}
									}
									// System.out.println("THE VALUE BUFFER==== "+valueBufferVillVPF);
									finalcoverageVillageVPFF = valueBufferVillVPF.toString();
								} else {
									finalcoverageVillageVPFF = coverageVillageVPFF;
								}

								if (finalcoverageVillageVPFF.equals(existingLocalBDataFinal[i])) {
									String temp = existingLocalBDataFinal[i] + "_true";
									sb2.append(temp);
									sb2.append(",");
								} else {
									String temp = existingLocalBDataFinal[i] + "_false";
									sb2.append(temp);
									if (i + 1 < existingLocalBDataFinal.length) {
										sb2.append(",");
									}
								}
							}
						}

					}
					if (localGovtBodyForm.getHeadQuarterCode() != null) {
						combinedLandregion = sb2.toString();
						if (combinedLandregion.endsWith(",")) {
							combinedLandregion = combinedLandregion.substring(0, combinedLandregion.length() - 1);
						}
					}
				}

				else if (lbtypeNameVal[1].equals("V") && localGovtBodyForm.getHeadQuarterCode() != null) {
					String valu = null;
					String[] retavl = null;
					if (localGovtBodyForm.getHeadQuarterCode().contains(":")) {
						valu = localGovtBodyForm.getHeadQuarterCode();
						retavl = valu.split(":");
					}

					StringBuffer sb2 = new StringBuffer();
					if (localGovtBodyForm.getHeadQuarterCode() != null) {
						existingLocalBDataFinal = landregionlist.split(",");

						for (int i = 0; i < existingLocalBDataFinal.length; i++) {
							if (localGovtBodyForm.getHeadQuarterCode().contains(":")) {
								if (retavl[1].equals(existingLocalBDataFinal[i])) {
									String temp = existingLocalBDataFinal[i] + "_true";
									sb2.append(temp);
									sb2.append(",");
								} else {
									String temp = existingLocalBDataFinal[i] + "_false";
									sb2.append(temp);
									sb2.append(",");
								}
							} else {
								String coverageVillageVPFF = localGovtBodyForm.getHeadQuarterCode();
								String finalcoverageVillageVPFF = null;
								if (coverageVillageVPFF.contains("_F_true") || coverageVillageVPFF.contains("_F_false") || coverageVillageVPFF.contains("_P_true") || coverageVillageVPFF.contains("_P_false")) {
									StringBuffer valueBufferVillVPF = new StringBuffer();
									if (coverageVillageVPFF != null) {
										if (coverageVillageVPFF.contains(",")) {
											String[] temp = coverageVillageVPFF.split(",");
											for (int j = 0; j < temp.length; j++) {
												if (temp[j].contains("P")) {
													temp[j] = temp[j].replace("_P_false", "").replace("_P_true", "");

													valueBufferVillVPF.append(temp[j].concat("_PART_V"));
													/*
													 * if (i < temp.length)
													 * valueBufferVillVPF
													 * .append(",");
													 */
												} else if (temp[j].contains("F")) {
													temp[j] = temp[j].replaceAll("_F_false", "").replaceAll("_F_true", "");

													valueBufferVillVPF.append(temp[j].concat("_FULL_V"));
													/*
													 * if (j < temp.length)
													 * valueBufferVillVPF
													 * .append(",");
													 */
												}
											}
										} else {
											String temp = localGovtBodyForm.getHeadQuarterCode();

											if (temp.contains("P")) {
												temp = temp.replace("_P_false", "").replace("_P_true", "");
												valueBufferVillVPF.append(temp.concat("_PART_V"));
											} else if (temp.contains("F")) {
												temp = temp.replaceAll("_F_false", "").replaceAll("_F_true", "");
												valueBufferVillVPF.append(temp.concat("_FULL_V"));
												// valueBufferVillVPF.append(",");
											}
										}
									}
									// System.out.println("THE VALUE BUFFER==== "+valueBufferVillVPF);
									finalcoverageVillageVPFF = valueBufferVillVPF.toString();
								} else {
									finalcoverageVillageVPFF = coverageVillageVPFF;
								}

								if (finalcoverageVillageVPFF.equals(existingLocalBDataFinal[i])) {
									String temp = existingLocalBDataFinal[i] + "_true";
									sb2.append(temp);
									sb2.append(",");
								} else {
									String temp = existingLocalBDataFinal[i] + "_false";
									sb2.append(temp);
									if (i + 1 < existingLocalBDataFinal.length) {
										sb2.append(",");
									}
								}
							}
						}

					}
					if (localGovtBodyForm.getHeadQuarterCode() != null) {
						combinedLandregion = sb2.toString();
						if (combinedLandregion.endsWith(",")) {
							combinedLandregion = combinedLandregion.substring(0, combinedLandregion.length() - 1);
						}
					}
				}
			}
			if (lbtypeNameVal[2].equals("U")) {
				combinedLandregion = landregionlist;
			}
			/*
			 * else { combinedLandregion=existingLocalBData+"_true"; }
			 */

			// Code for Urban Local Body----End

			String order_path = localGovtBodyForm.getOrderPath();
			SessionObject sessionObject = (SessionObject) httpsession.getAttribute("sessionObject");
	        Integer userId = sessionObject.getUserId()!=null?sessionObject.getUserId().intValue():null;
			/* Add Full Lb Coverage Changes by Chandra */
			// Integer userId =4117;
			if (covered_lbcode_full != null && !covered_lbcode_full.isEmpty())
				combinedLandregion = getMappedFullLbCoverageDetail(session, covered_lbcode_full, combinedLandregion);
			query = session.getNamedQuery("changelocalbodycoveredArea").setParameter("local_body_code", localBodyCode, Hibernate.INTEGER).setParameter("land_region_list", combinedLandregion, Hibernate.STRING)
					.setParameter("user_id", userId, Hibernate.INTEGER).setParameter("order_no", orderNo, Hibernate.STRING).setParameter("order_date", orderDate, Hibernate.DATE).setParameter("effective_date", lBeffectiveDate, Hibernate.DATE)
					.setParameter("gaz_pub_date", gazPubDate, Hibernate.DATE).setParameter("order_path", order_path, Hibernate.STRING).setParameter("lb_landregionlist", lb_landregionList, Hibernate.STRING)
					.setParameter("covered_lbcode_full", covered_lbcode_full, Hibernate.STRING);

			comboList = query.list();
			// retValue=comboList.get(0).getChange_coverage_of_local_body_fn();
			tx.commit();

		} catch (Exception e) {
			log.debug("Exception" + e);
			if (tx != null) {
				tx.rollback();
			}

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return comboList;
	}

	@SuppressWarnings("unchecked")
	public String getMappedFullLbCoverageDetail(Session session, String FullLbCode, String coverage) {
		SQLQuery query = null;
		StringBuffer fullLbCoverage = new StringBuffer();
		String temp = null;
		List<LbCoveredLandregion> coveregion = null;
		query = session
				.createSQLQuery("select lr.lrlc as landRegionCode,lr.land_region_type as landRegionType,lr.coverage_type as coverageType from lb_covered_landregion lr, localbody lb where lb.lb_covered_region_code = lr.lb_covered_region_code and lb.local_body_code in ("
						+ FullLbCode + ") and lb.isactive and lr.isactive");
		query.addScalar("landRegionCode").addScalar("landRegionType").addScalar("coverageType");
		query.setResultTransformer(Transformers.aliasToBean(LbCoveredLandregion.class));
		coveregion = query.list();
		for (LbCoveredLandregion lbcr : coveregion) {
			if (lbcr.getCoverageType() == 'P')
				temp = lbcr.getLandRegionCode() + "_" + "PART" + "_" + lbcr.getLandRegionType();
			else
				temp = lbcr.getLandRegionCode() + "_" + "FULL" + "_" + lbcr.getLandRegionType();
			if (!coverage.contains(temp))
				fullLbCoverage.append(temp + "_false" + ",");
		}
		temp = null;
		if (fullLbCoverage.length() > 13) {
			fullLbCoverage.deleteCharAt(fullLbCoverage.length() - 1);
			coverage = coverage + "," + fullLbCoverage.toString();
		}
		return coverage;

	}

	@SuppressWarnings("unchecked")
	public List<ChangeCoverageNameParentofDisturbLocalbody> modifyDisturbedLocalBodyForcoverageAreaNameParent(LocalGovtBodyForm localGovtBodyForm, LocalGovtBodyForm localGovtBodyFormChngeName, GovernmentOrderForm govtOrderForm,
			HttpServletRequest request, HttpSession httpsession) {
		Session session = null;
		Transaction tx = null;
		Query query = null;
		String lb_landregionList = null;
		String[] existingLocalBDataFinal = null;
		String combinedLandregion = null;
		int flagCode = 0;
		String lblistFull = null;

		List<ChangeCoverageNameParentofDisturbLocalbody> comboList = new ArrayList<ChangeCoverageNameParentofDisturbLocalbody>();
		//Integer retValue = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			int localBodyCode = localGovtBodyForm.getLocalBodyCode();

			String lbtypeName = localGovtBodyForm.getLgd_LBTypeNamehidden();
			String[] lbtypeNameVal = lbtypeName.split(":");

			String orderNo = localGovtBodyForm.getLgd_LBorderNo();
			Date orderDate = localGovtBodyForm.getLgd_LBorderDate();
			Date lBeffectiveDate = localGovtBodyForm.getLgd_LBeffectiveDate();
			Date gazPubDate = localGovtBodyForm.getLgd_LBgazPubDate();
			// For DP
			String lgd_LBDistCAreaDestListTemp = localGovtBodyForm.getLgd_LBDistCAreaDestList();

			List<LocalBodyDetails> localBodyDetails = localGovtBodyFormChngeName.getLocalBodyDetails();

			String localBodyNameEnglish = localBodyDetails.get(0).getLocalBodyNameEnglish();
			String localBodyNameLocal = localBodyDetails.get(0).getLocalBodyNameLocal();
			String aliasNameEnglish = localBodyDetails.get(0).getAliasNameEnglish();
			String alisNameLocal = localBodyDetails.get(0).getAlisNameLocal();

			String parentlblc = localGovtBodyFormChngeName.getLocalBodyNameEnglishList();
			int parent_lblc = Integer.parseInt(parentlblc);

			boolean modifyName = localGovtBodyFormChngeName.isLgd_LBModNameCheckDisturb();
			boolean modifyParent = localGovtBodyFormChngeName.isLgd_LBModParentCheckDisturb();

			if (modifyName == true && modifyParent == false) {
				flagCode = 55;
			} else if (modifyName == true && modifyParent == true) {
				flagCode = 54;
			} else if (modifyName == false && modifyParent == true) {
				flagCode = 56;
			} else if (modifyName == false && modifyParent == false) {
				flagCode = 57;
			}

			if (lbtypeNameVal[2].equals("P") || lbtypeNameVal[2].equals("T")) {
				if (lbtypeNameVal[1].equals("D")) {
					String existLBforDP = localGovtBodyForm.getLgd_LBDistPDestList();
					StringBuffer valueBufferLBforDP = new StringBuffer();
					if (existLBforDP != null && existLBforDP.contains("FULL")) {
						if (existLBforDP.contains(",")) {
							String temp[] = existLBforDP.split(",");
							for (int ml = 0; ml < temp.length; ml++) {
								if (temp[ml].contains("_FULL")) {
									valueBufferLBforDP.append(temp[ml]);
									valueBufferLBforDP.append(",");
								}
							}
						}
						String tempvalueBufferLBforDP = valueBufferLBforDP.toString();
						lblistFull = tempvalueBufferLBforDP.replaceAll("_FULL", "");
						if (lblistFull.endsWith(",")) {
							lblistFull = lblistFull.substring(0, lblistFull.length() - 1);
							log.debug(lblistFull);
						}

					}

				}
			}

			StringBuffer valueBufferDistMapped = new StringBuffer();
			if (lgd_LBDistCAreaDestListTemp != null) {
				if (lgd_LBDistCAreaDestListTemp.contains(",")) {
					String[] temp = lgd_LBDistCAreaDestListTemp.split(",");
					for (int i = 0; i < temp.length; i++) {

						String[] temp2 = temp[i].split(":");
						for (int j = 0; j < temp2.length; j++) {
							if (temp2[j].contains("_PART_D") || temp2[j].contains("_FULL_D")) {
								valueBufferDistMapped.append(temp2[j]);
								valueBufferDistMapped.append(",");
							}
						}
					}
				} else {
					String[] temp2 = lgd_LBDistCAreaDestListTemp.split(":");
					for (int j = 0; j < temp2.length; j++) {
						if (temp2[j].contains("_PART_D") || temp2[j].contains("_FULL_D")) {
							valueBufferDistMapped.append(temp2[j]);
							valueBufferDistMapped.append(",");
						}
					}

				}
			}
			// System.out.println("THE VALUE BUFFER==== "+valueBufferDistMapped);

			String lgd_LBDistCAreaDestList = valueBufferDistMapped.toString();
			if (lgd_LBDistCAreaDestList.endsWith(",")) {
				lgd_LBDistCAreaDestList = lgd_LBDistCAreaDestList.substring(0, lgd_LBDistCAreaDestList.length() - 1);
				log.debug(lgd_LBDistCAreaDestList);
			}
			// System.out.println("THE FINAL VALUE BUFFER==== "+lgd_LBDistCAreaDestList);

			String lgd_LBSubDistrictDestLatDCATemp = localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA();

			StringBuffer valueBufferSubDistMappedDP = new StringBuffer();
			if (lgd_LBSubDistrictDestLatDCATemp != null) {
				if (lgd_LBSubDistrictDestLatDCATemp.contains(",")) {
					String[] temp = lgd_LBSubDistrictDestLatDCATemp.split(",");
					for (int i = 0; i < temp.length; i++) {

						String[] temp2 = temp[i].split(":");
						for (int j = 0; j < temp2.length; j++) {
							if (temp2[j].contains("_PART_T") || temp2[j].contains("_FULL_T")) {
								valueBufferSubDistMappedDP.append(temp2[j]);
								valueBufferSubDistMappedDP.append(",");
							}
						}
					}
				} else {
					String[] temp2 = lgd_LBSubDistrictDestLatDCATemp.split(":");
					for (int j = 0; j < temp2.length; j++) {
						if (temp2[j].contains("_PART_T") || temp2[j].contains("_FULL_T")) {
							valueBufferSubDistMappedDP.append(temp2[j]);
							valueBufferSubDistMappedDP.append(",");
						}
					}
				}
			}

			String lgd_LBSubDistrictDestLatDCA = valueBufferSubDistMappedDP.toString();
			if (lgd_LBSubDistrictDestLatDCA.endsWith(",")) {
				lgd_LBSubDistrictDestLatDCA = lgd_LBSubDistrictDestLatDCA.substring(0, lgd_LBSubDistrictDestLatDCA.length() - 1);
				log.debug(lgd_LBSubDistrictDestLatDCA);
			}

			String lgd_LBVillageDestLatDCATemp = localGovtBodyForm.getLgd_LBVillageDestLatDCA();

			StringBuffer valueBufferVillageDP = new StringBuffer();
			if (lgd_LBVillageDestLatDCATemp != null) {
				if (lgd_LBVillageDestLatDCATemp.contains(",")) {
					String[] temp = lgd_LBVillageDestLatDCATemp.split(",");
					for (int i = 0; i < temp.length; i++) {

						String[] temp2 = temp[i].split(":");
						for (int j = 0; j < temp2.length; j++) {
							if (temp2[j].contains("_PART_V") || temp2[j].contains("_FULL_V")) {
								valueBufferVillageDP.append(temp2[j]);
								valueBufferVillageDP.append(",");
							}
						}
					}
				} else {
					String[] temp2 = lgd_LBVillageDestLatDCATemp.split(":");
					for (int j = 0; j < temp2.length; j++) {
						if (temp2[j].contains("_PART_V") || temp2[j].contains("_FULL_V")) {
							valueBufferVillageDP.append(temp2[j]);
							valueBufferVillageDP.append(",");
						}
					}
				}
			}

			String lgd_LBVillageDestLatDCA = valueBufferVillageDP.toString();
			if (lgd_LBVillageDestLatDCA.endsWith(",")) {
				lgd_LBVillageDestLatDCA = lgd_LBVillageDestLatDCA.substring(0, lgd_LBVillageDestLatDCA.length() - 1);
				log.debug(lgd_LBVillageDestLatDCA);
			}

			String finalCoverageExistLB = null;

			if (lgd_LBDistCAreaDestListTemp != null && lgd_LBSubDistrictDestLatDCATemp == null && lgd_LBVillageDestLatDCATemp == null) {
				finalCoverageExistLB = lgd_LBDistCAreaDestListTemp;
			} else if (lgd_LBDistCAreaDestListTemp != null && lgd_LBSubDistrictDestLatDCATemp != null && lgd_LBVillageDestLatDCATemp == null) {
				if (lgd_LBSubDistrictDestLatDCATemp.contains(":")) {
					finalCoverageExistLB = lgd_LBDistCAreaDestListTemp + "," + lgd_LBSubDistrictDestLatDCATemp;
				} else {
					finalCoverageExistLB = lgd_LBDistCAreaDestListTemp;
				}
			} else if (lgd_LBDistCAreaDestListTemp != null && lgd_LBSubDistrictDestLatDCATemp != null && lgd_LBVillageDestLatDCATemp != null) {
				if (lgd_LBSubDistrictDestLatDCATemp.contains(":") && lgd_LBVillageDestLatDCATemp.contains(":")) {
					finalCoverageExistLB = lgd_LBDistCAreaDestListTemp + "," + lgd_LBSubDistrictDestLatDCATemp + "," + lgd_LBVillageDestLatDCATemp;
				} else if (lgd_LBSubDistrictDestLatDCATemp.contains(":") && !lgd_LBVillageDestLatDCATemp.contains(":")) {
					finalCoverageExistLB = lgd_LBDistCAreaDestListTemp + "," + lgd_LBSubDistrictDestLatDCATemp;
				} else if (!lgd_LBSubDistrictDestLatDCATemp.contains(":") && !lgd_LBVillageDestLatDCATemp.contains(":")) {
					finalCoverageExistLB = lgd_LBDistCAreaDestListTemp;
				}
			}

			Map<Integer, String> hmapDP = new HashMap<Integer, String>();
			if (finalCoverageExistLB != null) {
				if (finalCoverageExistLB.contains(":")) {
					String[] temp = finalCoverageExistLB.split(",");
					for (int i = 0; i < temp.length; i++) {
						String[] temp2 = temp[i].split(":");
						Integer key = Integer.parseInt(temp2[0].toString());
						String a = hmapDP.get(key);
						if (a != null && a.length() > 0) {
							a = a + "#" + temp2[1];
							hmapDP.put(key, a);
						} else {
							hmapDP.put(key, temp2[1].toString());
						}
					}
				}
				if (hmapDP.toString() != null) {
					lb_landregionList = hmapDP.toString().replace("=", ":").replace("{", "").replace("}", "").replace(" ", "");
				} else {
					lb_landregionList = null;
				}
			}

			// String
			// lgd_LBSubDistrictDestLatDCA=localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA();

			// String
			// lgd_LBVillageDestLatDCA=localGovtBodyForm.getLgd_LBVillageDestLatDCA();
			//String lgd_LBVillageDestAtVillageCA = localGovtBodyForm.getLgd_LBVillageDestAtVillageCA();
			String lgd_LBDistCAreaDestListUmapped = localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped();
			String lgd_LBSubDistrictDestLatDCAUmapped = localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped();
			String lgd_LBVillageDestLatDCAUmapped = localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped();

			// For IP
			String lgd_LBInterCAreaDestListTemp = localGovtBodyForm.getLgd_LBInterCAreaDestList();

			if (lbtypeNameVal[2].equals("P") || lbtypeNameVal[2].equals("T")) {
				if (lbtypeNameVal[1].equals("I")) {
					String existLBforIP = localGovtBodyForm.getLgd_LBInterPDestList();
					StringBuffer valueBufferLBforIP = new StringBuffer();
					if (existLBforIP != null && existLBforIP.contains("FULL")) {
						if (existLBforIP.contains(",")) {
							String temp[] = existLBforIP.split(",");
							for (int ml = 0; ml < temp.length; ml++) {
								if (temp[ml].contains("_FULL")) {
									valueBufferLBforIP.append(temp[ml]);
									valueBufferLBforIP.append(",");
								}
							}
						}
						String tempvalueBufferLBforIP = valueBufferLBforIP.toString();
						lblistFull = tempvalueBufferLBforIP.replaceAll("_FULL", "");
						if (lblistFull.endsWith(",")) {
							lblistFull = lblistFull.substring(0, lblistFull.length() - 1);
							log.debug(lblistFull);
						}
					}
				}
			}

			StringBuffer valueBufferSubDistMappedIP = new StringBuffer();
			if (lgd_LBInterCAreaDestListTemp != null) {
				if (lgd_LBInterCAreaDestListTemp.contains(",")) {
					String[] temp = lgd_LBInterCAreaDestListTemp.split(",");
					for (int i = 0; i < temp.length; i++) {

						String[] temp2 = temp[i].split(":");
						for (int j = 0; j < temp2.length; j++) {
							if (temp2[j].contains("_PART_T") || temp2[j].contains("_FULL_T")) {
								valueBufferSubDistMappedIP.append(temp2[j]);
								valueBufferSubDistMappedIP.append(",");
							}
						}
					}
				} else {
					String[] temp2 = lgd_LBInterCAreaDestListTemp.split(":");
					for (int j = 0; j < temp2.length; j++) {
						if (temp2[j].contains("_PART_T") || temp2[j].contains("_FULL_T")) {
							valueBufferSubDistMappedIP.append(temp2[j]);
							valueBufferSubDistMappedIP.append(",");
						}
					}

				}
			}
			// System.out.println("THE VALUE BUFFER==== "+valueBufferSubDistMappedIP);

			String lgd_LBInterCAreaDestList = valueBufferSubDistMappedIP.toString();
			if (lgd_LBInterCAreaDestList.endsWith(",")) {
				lgd_LBInterCAreaDestList = lgd_LBInterCAreaDestList.substring(0, lgd_LBInterCAreaDestList.length() - 1);
				log.debug(lgd_LBInterCAreaDestList);
			}
			// System.out.println("THE FINAL VALUE BUFFER==== "+lgd_LBInterCAreaDestList);

			String lgd_LBVillageDestLatICATemp = localGovtBodyForm.getLgd_LBVillageDestLatICA();

			StringBuffer valueBufferVillageMappedIP = new StringBuffer();
			if (lgd_LBVillageDestLatICATemp != null) {
				if (lgd_LBVillageDestLatICATemp.contains(",")) {
					String[] temp = lgd_LBVillageDestLatICATemp.split(",");
					for (int i = 0; i < temp.length; i++) {

						String[] temp2 = temp[i].split(":");
						for (int j = 0; j < temp2.length; j++) {
							if (temp2[j].contains("_PART_V") || temp2[j].contains("_FULL_V")) {
								valueBufferVillageMappedIP.append(temp2[j]);
								valueBufferVillageMappedIP.append(",");
							}
						}
					}
				} else {
					String[] temp2 = lgd_LBVillageDestLatICATemp.split(":");
					for (int j = 0; j < temp2.length; j++) {
						if (temp2[j].contains("_PART_V") || temp2[j].contains("_FULL_V")) {
							valueBufferVillageMappedIP.append(temp2[j]);
							valueBufferVillageMappedIP.append(",");
						}
					}
				}
			}

			String lgd_LBVillageDestLatICA = valueBufferVillageMappedIP.toString();

			if (lgd_LBVillageDestLatICA.endsWith(",")) {
				lgd_LBVillageDestLatICA = lgd_LBVillageDestLatICA.substring(0, lgd_LBVillageDestLatICA.length() - 1);
				log.debug(lgd_LBVillageDestLatICA);
			}

			String finalCoverageExistLBforIP = null;

			if (lgd_LBInterCAreaDestListTemp != null && lgd_LBVillageDestLatICATemp == null) {
				finalCoverageExistLBforIP = lgd_LBInterCAreaDestListTemp;
			} else if (lgd_LBInterCAreaDestListTemp != null && lgd_LBVillageDestLatICATemp != null) {
				if (lgd_LBVillageDestLatICATemp.contains(":")) {
					finalCoverageExistLBforIP = lgd_LBInterCAreaDestListTemp + "," + lgd_LBVillageDestLatICATemp;
				} else {
					finalCoverageExistLBforIP = lgd_LBInterCAreaDestListTemp;
				}
			}

			Map<Integer, String> hmapIP = new HashMap<Integer, String>();
			if (finalCoverageExistLBforIP != null) {
				if (finalCoverageExistLBforIP.contains(":")) {
					String[] temp = finalCoverageExistLBforIP.split(",");
					for (int i = 0; i < temp.length; i++) {
						String[] temp2 = temp[i].split(":");
						Integer key = Integer.parseInt(temp2[0].toString());
						String a = hmapIP.get(key);
						if (a != null && a.length() > 0) {
							a = a + "#" + temp2[1];
							hmapIP.put(key, a);
						} else {
							hmapIP.put(key, temp2[1].toString());
						}
					}
				}
				if (hmapIP.toString() != null) {
					lb_landregionList = hmapIP.toString().replace("=", ":").replace("{", "").replace("}", "").replace(" ", "");
				} else {
					lb_landregionList = null;
				}
			}

			// String lgd_LBVillageDestLatICA =
			// localGovtBodyForm.getLgd_LBVillageDestLatICA();

			String lgd_LBInterCAreaDestListUmapped = localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped();
			String lgd_LBVillageDestLatICAUmapped = localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped();

			// For VP
			String lgd_LBVillageCAreaDestLTemp = localGovtBodyForm.getLgd_LBVillageCAreaDestL();

			StringBuffer valueBufferVillMapped = new StringBuffer();
			if (lgd_LBVillageCAreaDestLTemp != null) {
				if (lgd_LBVillageCAreaDestLTemp.contains(",")) {
					String[] temp = lgd_LBVillageCAreaDestLTemp.split(",");
					for (int i = 0; i < temp.length; i++) {

						String[] temp2 = temp[i].split(":");
						for (int j = 0; j < temp2.length; j++) {
							if (temp2[j].contains("_PART_V") || temp2[j].contains("_FULL_V")) {
								valueBufferVillMapped.append(temp2[j]);
								valueBufferVillMapped.append(",");
							}
						}
					}
				} else {
					String[] temp2 = lgd_LBVillageCAreaDestLTemp.split(":");
					for (int j = 0; j < temp2.length; j++) {
						if (temp2[j].contains("_PART_V") || temp2[j].contains("_FULL_V")) {
							valueBufferVillMapped.append(temp2[j]);
							valueBufferVillMapped.append(",");
						}
					}

				}
			}
			// System.out.println("THE VALUE BUFFER==== "+valueBufferVillMapped);

			String lgd_LBVillageCAreaDestL = valueBufferVillMapped.toString();
			if (lgd_LBVillageCAreaDestL.endsWith(",")) {
				lgd_LBVillageCAreaDestL = lgd_LBVillageCAreaDestL.substring(0, lgd_LBVillageCAreaDestL.length() - 1);
				log.debug(lgd_LBVillageCAreaDestL);
			}
			// System.out.println("THE FINAL VALUE BUFFER==== "+lgd_LBVillageCAreaDestL);

			Map<Integer, String> hmap = new HashMap<Integer, String>();
			if (lgd_LBVillageCAreaDestLTemp != null) {
				if (lgd_LBVillageCAreaDestLTemp.contains(":")) {
					String[] temp = lgd_LBVillageCAreaDestLTemp.split(",");
					for (int i = 0; i < temp.length; i++) {
						String[] temp2 = temp[i].split(":");
						Integer key = Integer.parseInt(temp2[0].toString());
						String a = hmap.get(key);
						if (a != null && a.length() > 0) {
							a = a + "#" + temp2[1];
							hmap.put(key, a);
						} else {
							hmap.put(key, temp2[1].toString());
						}
					}
				}
				if (hmap.toString() != null) {
					lb_landregionList = hmap.toString().replace("=", ":").replace("{", "").replace("}", "").replace(" ", "");
				} else {
					lb_landregionList = null;
				}
			}

			String lgd_LBVillageCAreaDestLUnmapped = localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped();

			// For Urban
			//String lgd_UrbanLBDistExistDest = localGovtBodyForm.getLgd_UrbanLBDistExistDest();

			String lgd_UrbanLBSubdistrictListDestTemp = localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest();// MAPPED
																												// DATA---URBAN

			StringBuffer valueBufferUrbanMapped = new StringBuffer();
			if (lgd_UrbanLBSubdistrictListDestTemp != null) {
				if (lgd_UrbanLBSubdistrictListDestTemp.contains(",")) {
					String[] temp = lgd_UrbanLBSubdistrictListDestTemp.split(",");
					for (int i = 0; i < temp.length; i++) {

						String[] temp2 = temp[i].split(":");
						for (int j = 0; j < temp2.length; j++) {
							if (temp2[j].contains("_PART_T") || temp2[j].contains("_FULL_T")) {
								valueBufferUrbanMapped.append(temp2[j]);
								valueBufferUrbanMapped.append(",");
							}
						}
					}
				} else {
					String[] temp2 = lgd_UrbanLBSubdistrictListDestTemp.split(":");
					for (int j = 0; j < temp2.length; j++) {
						if (temp2[j].contains("_PART_T") || temp2[j].contains("_FULL_T")) {
							valueBufferUrbanMapped.append(temp2[j]);
							valueBufferUrbanMapped.append(",");
						}
					}

				}
			}

			// System.out.println("THE VALUE BUFFER URBAN==== "+valueBufferUrbanMapped);

			String lgd_UrbanLBSubdistrictListDest = valueBufferUrbanMapped.toString();
			if (lgd_UrbanLBSubdistrictListDest.endsWith(",")) {
				lgd_UrbanLBSubdistrictListDest = lgd_UrbanLBSubdistrictListDest.substring(0, lgd_UrbanLBSubdistrictListDest.length() - 1);
				log.debug(lgd_UrbanLBSubdistrictListDest);
			}
			// System.out.println("THE VALUE BUFFER URBAN==== "+lgd_UrbanLBSubdistrictListDest);

			Map<Integer, String> hmap1 = new HashMap<Integer, String>();
			if (lgd_UrbanLBSubdistrictListDestTemp != null) {
				if (lgd_UrbanLBSubdistrictListDestTemp.contains(":")) {
					String[] temp = lgd_UrbanLBSubdistrictListDestTemp.split(",");
					for (int i = 0; i < temp.length; i++) {
						String[] temp2 = temp[i].split(":");
						Integer key = Integer.parseInt(temp2[0].toString());
						String a = hmap1.get(key);
						if (a != null && a.length() > 0) {
							a = a + "#" + temp2[1];
							hmap1.put(key, a);
						} else {
							hmap1.put(key, temp2[1].toString());
						}
					}
				}
				if (hmap1.toString() != null) {
					lb_landregionList = hmap1.toString().replace("=", ":").replace("{", "").replace("}", "").replace(" ", "");
				} else {
					lb_landregionList = null;
				}

			}

			String lgd_UrbanLBDistUmappedDest = localGovtBodyForm.getLgd_UrbanLBDistUmappedDest();// UN
																									// MAPPED
																									// DATA---URBAN

			// Existing Coverage of Local body DP
			String coverageDistrict = localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped();

			StringBuffer valueBufferDistDP = new StringBuffer();
			if (coverageDistrict != null) {
				if (coverageDistrict.contains(",")) {
					String[] temp = coverageDistrict.split(",");
					for (int i = 0; i < temp.length; i++) {
						if (temp[i].contains("P")) {
							temp[i] = temp[i].replace("_P_false", "").replace("_P_true", "");

							valueBufferDistDP.append(temp[i].concat("_PART_D"));
							if (i < temp.length) {
								valueBufferDistDP.append(",");
							}
						} else if (temp[i].contains("F")) {
							temp[i] = temp[i].replaceAll("_F_false", "").replaceAll("_F_true", "");

							valueBufferDistDP.append(temp[i].concat("_FULL_D"));
							if (i < temp.length) {
								valueBufferDistDP.append(",");
							}
						}
					}
				} else {
					String temp = coverageDistrict;

					if (temp.contains("P")) {
						temp = temp.replace("_P_false", "").replace("_P_true", "");
						valueBufferDistDP.append(temp.concat("_PART_D"));
					} else if (temp.contains("F")) {
						temp = temp.replaceAll("_F_false", "").replaceAll("_F_true", "");
						valueBufferDistDP.append(temp.concat("_FULL_D"));
						valueBufferDistDP.append(",");
					}
				}
			}
			// System.out.println("THE VALUE BUFFER==== "+valueBufferDistDP);
			String coverageDistrictF = valueBufferDistDP.toString();

			if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
					&& localGovtBodyForm.getLgd_LBDistCAreaDestList() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
					&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
				coverageDistrictF = valueBufferDistDP.toString();
			} else {
				if (!coverageDistrictF.endsWith(",")) {
					coverageDistrictF = valueBufferDistDP.toString() + ",";
				}
			}

			String coverageSubDistrict = localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped();
			String coverageVillage = localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped();

			// Existing Coverage of Local Body IP

			String coverageSubDistrictIP = localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped();

			StringBuffer valueBufferSubDistIP = new StringBuffer();
			if (coverageSubDistrictIP != null) {
				if (coverageSubDistrictIP.contains(",")) {
					String[] temp = coverageSubDistrictIP.split(",");
					for (int i = 0; i < temp.length; i++) {
						if (temp[i].contains("P")) {
							temp[i] = temp[i].replace("_P_false", "").replace("_P_true", "");

							valueBufferSubDistIP.append(temp[i].concat("_PART_T"));
							if (i < temp.length) {
								valueBufferSubDistIP.append(",");
							}
						} else if (temp[i].contains("F")) {
							temp[i] = temp[i].replaceAll("_F_false", "").replaceAll("_F_true", "");

							valueBufferSubDistIP.append(temp[i].concat("_FULL_T"));
							if (i < temp.length) {
								valueBufferSubDistIP.append(",");
							}
						}
					}
				} else {
					String temp = coverageSubDistrictIP;

					if (temp.contains("P")) {
						temp = temp.replace("_P_false", "").replace("_P_true", "");
						valueBufferSubDistIP.append(temp.concat("_PART_T"));
					} else if (temp.contains("F")) {
						temp = temp.replaceAll("_F_false", "").replaceAll("_F_true", "");
						valueBufferSubDistIP.append(temp.concat("_FULL_T"));
						valueBufferSubDistIP.append(",");
					}
				}
			}
			// System.out.println("THE VALUE BUFFER==== "+valueBufferSubDistIP);
			String coverageSubDistrictIPF = valueBufferSubDistIP.toString();
			if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() == null && localGovtBodyForm.getLgd_LBInterCAreaDestList() == null
					&& localGovtBodyForm.getLgd_LBVillageDestLatICA() == null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() == null) {
				coverageSubDistrictIPF = valueBufferSubDistIP.toString();
			} else {
				if (!coverageSubDistrictIPF.endsWith(",") && !valueBufferSubDistIP.toString().equals("")) {
					coverageSubDistrictIPF = valueBufferSubDistIP.toString() + ",";
				}
			}

			String coverageVillageIP = localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped();

			// Existing Coverage of Local Body VP
			String coverageVillageVP = localGovtBodyForm.getAvaillgd_LBVillageCAreaSourceLUnmapped();

			// Existing Coverage of Local Body Urban Local Body
			String coverageDataUrban = localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmappedUrban();

			//StringBuffer valueBufferDis = new StringBuffer();

			StringBuffer valueBufferSubDis = new StringBuffer();
			if (coverageSubDistrict != null) {
				if (coverageSubDistrict.contains(",")) {
					String[] temp = coverageSubDistrict.split(",");
					// StringBuffer valueBuffer = new StringBuffer();
					for (int i = 0; i < temp.length; i++) {
						valueBufferSubDis.append(temp[i].concat("_PART_T"));
						if (i < temp.length) {
							valueBufferSubDis.append(",");
						}
					}
				} else {
					valueBufferSubDis.append(coverageSubDistrict.concat("_PART_T"));
					valueBufferSubDis.append(",");
				}
			}
			// System.out.println("THE VALUE BUFFER==== "+valueBufferSubDis);
			String coverageSubDistrictF = valueBufferSubDis.toString();

			StringBuffer valueBufferVill = new StringBuffer();
			if (coverageVillage != null) {
				if (coverageVillage.contains(",")) {
					String[] temp = coverageVillage.split(",");
					// StringBuffer valueBuffer = new StringBuffer();
					for (int i = 0; i < temp.length; i++) {
						valueBufferVill.append(temp[i].concat("_PART_V"));
						if (i < temp.length) {
							valueBufferVill.append(",");
						}
					}
				} else {
					valueBufferVill.append(coverageVillage.concat("_PART_V"));
					valueBufferVill.append(",");
				}
			}
			// System.out.println("THE VALUE BUFFER==== "+valueBufferVill);
			String coverageVillageF = valueBufferVill.toString();

			/*
			 * StringBuffer valueBufferSubDisIP = new StringBuffer();
			 * if(coverageSubDistrictIP !=null) { if
			 * (coverageSubDistrictIP.contains(",")) { String[] temp =
			 * coverageSubDistrictIP.split(","); //StringBuffer valueBuffer =
			 * new StringBuffer(); for (int i = 0; i < temp.length; i++) {
			 * valueBufferSubDisIP.append(temp[i].concat("_PART_T")); if (i <
			 * temp.length) valueBufferSubDisIP.append(","); } } else {
			 * valueBufferSubDisIP
			 * .append(coverageSubDistrictIP.concat("_PART_T"));
			 * valueBufferSubDisIP.append(","); } }
			 * //System.out.println("THE VALUE BUFFER==== "
			 * +valueBufferSubDisIP); String
			 * coverageSubDistrictIPF=valueBufferSubDisIP.toString();
			 */

			StringBuffer valueBufferVillIP = new StringBuffer();
			if (coverageVillageIP != null) {
				if (coverageVillageIP.contains(",")) {
					String[] temp = coverageVillageIP.split(",");
					// StringBuffer valueBuffer = new StringBuffer();
					for (int i = 0; i < temp.length; i++) {
						valueBufferVillIP.append(temp[i].concat("_PART_V"));
						if (i < temp.length) {
							valueBufferVillIP.append(",");
						}
					}
				} else {
					valueBufferVillIP.append(coverageVillageIP.concat("_PART_V"));
					valueBufferVillIP.append(",");
				}
			}
			// System.out.println("THE VALUE BUFFER==== "+valueBufferVillIP);
			String coverageVillageIPF = valueBufferVillIP.toString();

			StringBuffer valueBufferVillVP = new StringBuffer();
			if (coverageVillageVP != null) {
				if (coverageVillageVP.contains(",")) {
					String[] temp = coverageVillageVP.split(",");
					for (int i = 0; i < temp.length; i++) {
						if (temp[i].contains("P")) {
							temp[i] = temp[i].replace("_P_false", "").replace("_P_true", "");

							valueBufferVillVP.append(temp[i].concat("_PART_V"));
							if (i < temp.length) {
								valueBufferVillVP.append(",");
							}
						} else if (temp[i].contains("F")) {
							temp[i] = temp[i].replaceAll("_F_false", "").replaceAll("_F_true", "");

							valueBufferVillVP.append(temp[i].concat("_FULL_V"));
							if (i < temp.length) {
								valueBufferVillVP.append(",");
							}
						}
					}
				} else {
					String temp = coverageVillageVP;

					if (temp.contains("P")) {
						temp = temp.replace("_P_false", "").replace("_P_true", "");
						valueBufferVillVP.append(temp.concat("_PART_V"));
					} else if (temp.contains("F")) {
						temp = temp.replaceAll("_F_false", "").replaceAll("_F_true", "");
						valueBufferVillVP.append(temp.concat("_FULL_V"));
						valueBufferVillVP.append(",");
					}
				}
			}
			// System.out.println("THE VALUE BUFFER==== "+valueBufferVillVP);
			String coverageVillageVPF = valueBufferVillVP.toString();
			if (!coverageVillageVPF.endsWith(",")) {
				coverageVillageVPF = valueBufferVillVP.toString() + ",";
			}

			// String
			// coverageDataUrban=localGovtBodyForm.getLgd_LBInterCAreaSourceListUmapped();
			StringBuffer valueBufferUrban = new StringBuffer();
			if (coverageDataUrban != null) {
				if (coverageDataUrban.contains(",")) {
					String[] temp = coverageDataUrban.split(",");
					// StringBuffer valueBuffer = new StringBuffer();
					for (int i = 0; i < temp.length; i++) {
						valueBufferUrban.append(temp[i].concat("_PART_T"));
						if (i < temp.length) {
							valueBufferUrban.append(",");
						}
					}
				} else {
					valueBufferUrban.append(coverageDataUrban.concat("_PART_T"));
					valueBufferUrban.append(",");
				}

			}
			// System.out.println("THE VALUE BUFFER==== "+valueBufferUrban);
			String coverageULB = valueBufferUrban.toString();

			String landregionlist = null;

			// Code for PRI Local Body----Start

			if (lbtypeNameVal[2].equals("P") || lbtypeNameVal[2].equals("T")) {
				if (lbtypeNameVal[1].equals("D")) {
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() != null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA + "," + lgd_LBVillageDestLatDCA + "," + lgd_LBDistCAreaDestListUmapped + "," + lgd_LBSubDistrictDestLatDCAUmapped + ","
								+ lgd_LBVillageDestLatDCAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() != null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA + "," + lgd_LBVillageDestLatDCA + "," + lgd_LBDistCAreaDestListUmapped + "," + lgd_LBSubDistrictDestLatDCAUmapped + ","
								+ lgd_LBVillageDestLatDCAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() != null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA + "," + lgd_LBVillageDestLatDCA + "," + lgd_LBDistCAreaDestListUmapped + ","
								+ lgd_LBSubDistrictDestLatDCAUmapped + "," + lgd_LBVillageDestLatDCAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() != null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA + "," + lgd_LBVillageDestLatDCA + "," + lgd_LBDistCAreaDestListUmapped + ","
								+ lgd_LBSubDistrictDestLatDCAUmapped + "," + lgd_LBVillageDestLatDCAUmapped;
					}

					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA + "," + lgd_LBVillageDestLatDCA + "," + lgd_LBDistCAreaDestListUmapped + "," + lgd_LBSubDistrictDestLatDCAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA + "," + lgd_LBVillageDestLatDCA + "," + lgd_LBDistCAreaDestListUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA + "," + lgd_LBVillageDestLatDCA;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestList + "," + lgd_LBDistCAreaDestListUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestList + "," + lgd_LBDistCAreaDestListUmapped + "," + lgd_LBSubDistrictDestLatDCAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() != null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestList + "," + lgd_LBDistCAreaDestListUmapped + "," + lgd_LBSubDistrictDestLatDCAUmapped + "," + lgd_LBVillageDestLatDCAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA + "," + lgd_LBDistCAreaDestListUmapped;
					}

					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA + "," + lgd_LBDistCAreaDestListUmapped + lgd_LBSubDistrictDestLatDCAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() != null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA + "," + lgd_LBDistCAreaDestListUmapped + lgd_LBSubDistrictDestLatDCAUmapped + ","
								+ lgd_LBVillageDestLatDCAUmapped;
					}

					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA + "," + lgd_LBVillageDestLatDCA + "," + lgd_LBDistCAreaDestListUmapped;
					}

					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA + "," + lgd_LBDistCAreaDestListUmapped + "," + lgd_LBSubDistrictDestLatDCAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + lgd_LBDistCAreaDestList + "," + lgd_LBVillageDestLatDCA + "," + lgd_LBDistCAreaDestListUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA;
					}

					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + lgd_LBDistCAreaDestList + "," + lgd_LBVillageDestLatDCA + "," + lgd_LBDistCAreaDestListUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA + "," + lgd_LBVillageDestLatDCA;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() != null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestListUmapped + "," + lgd_LBSubDistrictDestLatDCAUmapped + "," + lgd_LBVillageDestLatDCAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null && localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA;
					}

					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + "," + lgd_LBDistCAreaDestList;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestList;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null && localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestList;
					}

					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA + "," + "," + lgd_LBDistCAreaDestListUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA + "," + lgd_LBDistCAreaDestListUmapped + "," + lgd_LBSubDistrictDestLatDCAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() != null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA + "," + lgd_LBDistCAreaDestListUmapped + "," + lgd_LBSubDistrictDestLatDCAUmapped + "," + lgd_LBVillageDestLatDCAUmapped;
					}

					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA + "," + lgd_LBDistCAreaDestListUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null && localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA;
					}

					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() != null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestList;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestList;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null && localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestList;
					}

					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBDistCAreaDestList() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null && localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestListUmapped;
					}

					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBDistCAreaDestList() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null && localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + lgd_LBDistCAreaDestListUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBDistCAreaDestList() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null && localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestListUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() != null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + lgd_LBDistCAreaDestListUmapped + "," + lgd_LBSubDistrictDestLatDCAUmapped + "," + lgd_LBVillageDestLatDCAUmapped;
					}

					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBDistCAreaDestList() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null && localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestListUmapped + "," + lgd_LBSubDistrictDestLatDCAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBDistCAreaDestList() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null && localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() != null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestListUmapped + "," + lgd_LBSubDistrictDestLatDCAUmapped + "," + lgd_LBVillageDestLatDCAUmapped;
					}

					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() != null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestListUmapped + "," + lgd_LBSubDistrictDestLatDCAUmapped + "," + lgd_LBVillageDestLatDCAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestListUmapped + "," + lgd_LBSubDistrictDestLatDCAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestListUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() == null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageVillageF + lgd_LBDistCAreaDestList;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageVillageF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA + "," + lgd_LBVillageDestLatDCA;
					}

					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestList + "," + lgd_LBDistCAreaDestListUmapped + "," + lgd_LBSubDistrictDestLatDCAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() != null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestList + "," + lgd_LBDistCAreaDestListUmapped + "," + lgd_LBSubDistrictDestLatDCAUmapped + "," + lgd_LBVillageDestLatDCAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() != null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestListUmapped + "," + lgd_LBSubDistrictDestLatDCAUmapped + "," + lgd_LBVillageDestLatDCAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA + "," + lgd_LBDistCAreaDestListUmapped + "," + lgd_LBSubDistrictDestLatDCAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + coverageVillageF;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						landregionlist = coverageDistrictF + lgd_LBDistCAreaDestList;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() != null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() != null) {
						landregionlist = coverageDistrictF + coverageSubDistrictF + lgd_LBDistCAreaDestList + "," + lgd_LBSubDistrictDestLatDCA + "," + lgd_LBVillageDestLatDCA;
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestList() != null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCA() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCA() == null
							&& localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() == null) {
						if (!coverageDistrictF.endsWith(",")) {
							landregionlist = coverageDistrictF + "," + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestList;
						} else {
							landregionlist = coverageDistrictF + coverageSubDistrictF + coverageVillageF + lgd_LBDistCAreaDestList;
						}

					}
				} else if (lbtypeNameVal[1].equals("I")) {
					// Code for Intermediate Panchayat

					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() != null && localGovtBodyForm.getLgd_LBInterCAreaDestList() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() != null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() != null) {
						landregionlist = coverageSubDistrictIPF + coverageVillageIPF + lgd_LBInterCAreaDestList + "," + lgd_LBVillageDestLatICA + "," + lgd_LBInterCAreaDestListUmapped + "," + lgd_LBVillageDestLatICAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() != null && localGovtBodyForm.getLgd_LBInterCAreaDestList() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() != null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() == null) {
						landregionlist = coverageSubDistrictIPF + coverageVillageIPF + "," + lgd_LBVillageDestLatICA;
					}
					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() != null && localGovtBodyForm.getLgd_LBInterCAreaDestList() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() == null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() == null) {
						landregionlist = coverageSubDistrictIPF + coverageVillageIPF + lgd_LBInterCAreaDestList;
					}

					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() == null && localGovtBodyForm.getLgd_LBInterCAreaDestList() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() != null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() != null) {
						landregionlist = coverageSubDistrictIPF + lgd_LBInterCAreaDestList + "," + lgd_LBVillageDestLatICA + "," + lgd_LBInterCAreaDestListUmapped + "," + lgd_LBVillageDestLatICAUmapped;
					}

					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() == null && localGovtBodyForm.getLgd_LBInterCAreaDestList() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() != null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() == null) {
						landregionlist = coverageSubDistrictIPF + lgd_LBInterCAreaDestList + "," + lgd_LBVillageDestLatICA;
					}

					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() == null && localGovtBodyForm.getLgd_LBInterCAreaDestList() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() == null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() == null) {
						landregionlist = coverageSubDistrictIPF + lgd_LBInterCAreaDestList + "," + lgd_LBInterCAreaDestListUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() != null && localGovtBodyForm.getLgd_LBInterCAreaDestList() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() == null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() == null) {
						landregionlist = coverageSubDistrictIPF + coverageVillageIPF + lgd_LBInterCAreaDestList + "," + lgd_LBInterCAreaDestListUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() != null && localGovtBodyForm.getLgd_LBInterCAreaDestList() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() == null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() != null) {
						landregionlist = coverageSubDistrictIPF + coverageVillageIPF + lgd_LBInterCAreaDestList + "," + lgd_LBInterCAreaDestListUmapped + "," + lgd_LBVillageDestLatICAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() != null && localGovtBodyForm.getLgd_LBInterCAreaDestList() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() == null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() != null) {
						landregionlist = coverageSubDistrictIPF + coverageVillageIPF + lgd_LBInterCAreaDestList + "," + lgd_LBInterCAreaDestListUmapped + "," + lgd_LBVillageDestLatICAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() != null && localGovtBodyForm.getLgd_LBInterCAreaDestList() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() == null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() != null) {
						landregionlist = coverageSubDistrictIPF + coverageVillageIPF + lgd_LBInterCAreaDestListUmapped + "," + lgd_LBVillageDestLatICAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() != null && localGovtBodyForm.getLgd_LBInterCAreaDestList() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() == null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() == null) {
						landregionlist = coverageSubDistrictIPF + coverageVillageIPF + lgd_LBInterCAreaDestListUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() != null && localGovtBodyForm.getLgd_LBInterCAreaDestList() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() != null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() == null) {
						landregionlist = coverageSubDistrictIPF + coverageVillageIPF + lgd_LBInterCAreaDestList + "," + lgd_LBVillageDestLatICA;
					}
					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() != null && localGovtBodyForm.getLgd_LBInterCAreaDestList() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() == null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() != null) {
						landregionlist = coverageSubDistrictIPF + coverageVillageIPF + lgd_LBInterCAreaDestListUmapped + "," + lgd_LBVillageDestLatICAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() == null && localGovtBodyForm.getLgd_LBInterCAreaDestList() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() == null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() == null) {
						landregionlist = coverageSubDistrictIPF;
					}
					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() != null && localGovtBodyForm.getLgd_LBInterCAreaDestList() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() == null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() == null) {
						landregionlist = coverageSubDistrictIPF + coverageVillageIPF;
					}

					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() == null && localGovtBodyForm.getLgd_LBInterCAreaDestList() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() == null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() != null) {
						landregionlist = coverageSubDistrictIPF + lgd_LBInterCAreaDestListUmapped + "," + lgd_LBVillageDestLatICAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() == null && localGovtBodyForm.getLgd_LBInterCAreaDestList() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() == null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() != null) {
						landregionlist = coverageSubDistrictIPF + lgd_LBInterCAreaDestListUmapped + "," + lgd_LBVillageDestLatICAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() == null && localGovtBodyForm.getLgd_LBInterCAreaDestList() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() == null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() == null) {
						landregionlist = coverageSubDistrictIPF + lgd_LBInterCAreaDestListUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() == null && localGovtBodyForm.getLgd_LBInterCAreaDestList() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() == null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() == null) {
						landregionlist = coverageSubDistrictIPF + lgd_LBInterCAreaDestList;
					}

					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() == null && localGovtBodyForm.getLgd_LBInterCAreaDestList() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() == null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() != null) {
						landregionlist = coverageSubDistrictIPF + lgd_LBInterCAreaDestList + "," + lgd_LBInterCAreaDestListUmapped + "," + lgd_LBVillageDestLatICAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() != null && localGovtBodyForm.getLgd_LBInterCAreaDestList() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() == null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() != null) {
						landregionlist = coverageSubDistrictIPF + coverageVillageIPF + lgd_LBInterCAreaDestList + "," + lgd_LBInterCAreaDestListUmapped + "," + lgd_LBVillageDestLatICAUmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() != null && localGovtBodyForm.getLgd_LBInterCAreaDestList() == null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() == null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() == null) {
						landregionlist = coverageVillageIPF;
					}
					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() == null && localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() == null && localGovtBodyForm.getLgd_LBInterCAreaDestList() != null
							&& localGovtBodyForm.getLgd_LBVillageDestLatICA() == null && localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() == null && localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() == null) {
						landregionlist = lgd_LBInterCAreaDestList;
					}
					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() == null && (coverageSubDistrictIPF==null || (coverageSubDistrictIPF!=null && coverageSubDistrictIPF.trim().equals(""))) && lgd_LBInterCAreaDestListUmapped!=null ) {
						if(lgd_LBVillageDestLatICAUmapped!=null) {
							landregionlist = lgd_LBInterCAreaDestListUmapped + "," + lgd_LBVillageDestLatICAUmapped;
						}else {
							landregionlist = lgd_LBInterCAreaDestListUmapped;
						}
						
					}
					

				} else if (lbtypeNameVal[1].equals("V")) {
					// Code for Village Panchayat

					if (localGovtBodyForm.getAvaillgd_LBVillageCAreaSourceLUnmapped() != null && localGovtBodyForm.getLgd_LBVillageCAreaDestL() != null && localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped() != null) {
						landregionlist = coverageVillageVPF + lgd_LBVillageCAreaDestL + "," + lgd_LBVillageCAreaDestLUnmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBVillageCAreaSourceLUnmapped() == null && localGovtBodyForm.getLgd_LBVillageCAreaDestL() != null && localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped() != null) {
						landregionlist = lgd_LBVillageCAreaDestL + "," + lgd_LBVillageCAreaDestLUnmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBVillageCAreaSourceLUnmapped() == null && localGovtBodyForm.getLgd_LBVillageCAreaDestL() == null && localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped() != null) {
						landregionlist = lgd_LBVillageCAreaDestLUnmapped;
					}
					if (localGovtBodyForm.getAvaillgd_LBVillageCAreaSourceLUnmapped() == null && localGovtBodyForm.getLgd_LBVillageCAreaDestL() != null && localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped() == null) {
						landregionlist = lgd_LBVillageCAreaDestL;
					}
					if (localGovtBodyForm.getAvaillgd_LBVillageCAreaSourceLUnmapped() != null && localGovtBodyForm.getLgd_LBVillageCAreaDestL() != null && localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped() == null) {
						landregionlist = coverageVillageVPF + lgd_LBVillageCAreaDestL;
					}
					if (localGovtBodyForm.getAvaillgd_LBVillageCAreaSourceLUnmapped() != null && localGovtBodyForm.getLgd_LBVillageCAreaDestL() == null && localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped() == null) {
						landregionlist = coverageVillageVPF;
					}
					if (localGovtBodyForm.getAvaillgd_LBVillageCAreaSourceLUnmapped() != null && localGovtBodyForm.getLgd_LBVillageCAreaDestL() == null && localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped() != null) {
						landregionlist = coverageVillageVPF + lgd_LBVillageCAreaDestLUnmapped;
					}
				}
			} else if (lbtypeNameVal[2].equals("U")) {
				// Code for Urban Local Body----Start
				if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmappedUrban() != null && localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest() != null && localGovtBodyForm.getLgd_UrbanLBDistUmappedDest() != null) {
					landregionlist = coverageULB + lgd_UrbanLBSubdistrictListDest + "," + lgd_UrbanLBDistUmappedDest;
				}
				if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmappedUrban() != null && localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest() != null && localGovtBodyForm.getLgd_UrbanLBDistUmappedDest() == null) {
					landregionlist = coverageULB + lgd_UrbanLBSubdistrictListDest;
				}
				if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmappedUrban() != null && localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest() == null && localGovtBodyForm.getLgd_UrbanLBDistUmappedDest() != null) {
					landregionlist = coverageULB + lgd_UrbanLBDistUmappedDest;
				}
				if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmappedUrban() == null && localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest() != null && localGovtBodyForm.getLgd_UrbanLBDistUmappedDest() != null) {
					landregionlist = lgd_UrbanLBSubdistrictListDest + "," + lgd_UrbanLBDistUmappedDest;
				}
				if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmappedUrban() == null && localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest() != null && localGovtBodyForm.getLgd_UrbanLBDistUmappedDest() == null) {
					landregionlist = lgd_UrbanLBSubdistrictListDest;
				}
				if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmappedUrban() == null && localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest() == null && localGovtBodyForm.getLgd_UrbanLBDistUmappedDest() != null) {
					landregionlist = lgd_UrbanLBDistUmappedDest;
				}
				if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmappedUrban() != null && localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest() == null && localGovtBodyForm.getLgd_UrbanLBDistUmappedDest() != null) {
					landregionlist = coverageULB + lgd_UrbanLBDistUmappedDest;
				}
				if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmappedUrban() != null && localGovtBodyForm.getLgd_UrbanLBSubdistrictListDest() == null && localGovtBodyForm.getLgd_UrbanLBDistUmappedDest() == null) {
					landregionlist = coverageULB;
				}
			}

			if (landregionlist.endsWith(",")) {
				landregionlist = landregionlist.substring(0, landregionlist.length() - 1);
				log.debug(landregionlist);
			}

			if (lbtypeNameVal[2].equals("P") || lbtypeNameVal[2].equals("T")) {

				if (lbtypeNameVal[1].equals("D") && localGovtBodyForm.getHeadQuarterCode() != null) {
					String valu = null;
					String[] retavl = null;
					if (localGovtBodyForm.getHeadQuarterCode().contains(":")) {
						valu = localGovtBodyForm.getHeadQuarterCode();
						retavl = valu.split(":");
					}

					StringBuffer sb2 = new StringBuffer();
					if (localGovtBodyForm.getHeadQuarterCode() != null) {
						existingLocalBDataFinal = landregionlist.split(",");

						for (int i = 0; i < existingLocalBDataFinal.length; i++) {
							if (localGovtBodyForm.getHeadQuarterCode().contains(":")) {
								if (retavl[1].equals(existingLocalBDataFinal[i])) {
									String temp = existingLocalBDataFinal[i] + "_true";
									sb2.append(temp);
									sb2.append(",");
								} else {
									String temp = existingLocalBDataFinal[i] + "_false";
									sb2.append(temp);
									sb2.append(",");
								}
							} else {
								String coverageVillageVPFF = localGovtBodyForm.getHeadQuarterCode();
								String finalcoverageVillageVPFF = null;
								if (coverageVillageVPFF.contains("_F_true") || coverageVillageVPFF.contains("_F_false") || coverageVillageVPFF.contains("_P_true") || coverageVillageVPFF.contains("_P_false")) {
									StringBuffer valueBufferVillVPF = new StringBuffer();
									if (coverageVillageVPFF != null) {
										if (coverageVillageVPFF.contains(",")) {
											String[] temp = coverageVillageVPFF.split(",");
											for (int j = 0; j < temp.length; j++) {
												if (temp[j].contains("P")) {
													temp[j] = temp[j].replace("_P_false", "").replace("_P_true", "");

													valueBufferVillVPF.append(temp[j].concat("_PART_D"));
													/*
													 * if (i < temp.length)
													 * valueBufferVillVPF
													 * .append(",");
													 */
												} else if (temp[j].contains("F")) {
													temp[j] = temp[j].replaceAll("_F_false", "").replaceAll("_F_true", "");

													valueBufferVillVPF.append(temp[j].concat("_FULL_D"));
													/*
													 * if (j < temp.length)
													 * valueBufferVillVPF
													 * .append(",");
													 */
												}
											}
										} else {
											String temp = localGovtBodyForm.getHeadQuarterCode();

											if (temp.contains("P")) {
												temp = temp.replace("_P_false", "").replace("_P_true", "");
												valueBufferVillVPF.append(temp.concat("_PART_D"));
											} else if (temp.contains("F")) {
												temp = temp.replaceAll("_F_false", "").replaceAll("_F_true", "");
												valueBufferVillVPF.append(temp.concat("_FULL_D"));
												// valueBufferVillVPF.append(",");
											}
										}
									}
									// System.out.println("THE VALUE BUFFER==== "+valueBufferVillVPF);
									finalcoverageVillageVPFF = valueBufferVillVPF.toString();
								} else {
									finalcoverageVillageVPFF = coverageVillageVPFF;
								}

								if (finalcoverageVillageVPFF.equals(existingLocalBDataFinal[i])) {
									String temp = existingLocalBDataFinal[i] + "_true";
									sb2.append(temp);
									sb2.append(",");
								} else {
									String temp = existingLocalBDataFinal[i] + "_false";
									sb2.append(temp);
									if (i + 1 < existingLocalBDataFinal.length) {
										sb2.append(",");
									}
								}
							}
						}

					}
					if (localGovtBodyForm.getHeadQuarterCode() != null) {
						combinedLandregion = sb2.toString();
						if (combinedLandregion.endsWith(",")) {
							combinedLandregion = combinedLandregion.substring(0, combinedLandregion.length() - 1);
						}
					}
				}

				else if (lbtypeNameVal[1].equals("I") && localGovtBodyForm.getHeadQuarterCode() != null) {
					String valu = null;
					String[] retavl = null;
					if (localGovtBodyForm.getHeadQuarterCode().contains(":")) {
						valu = localGovtBodyForm.getHeadQuarterCode();
						retavl = valu.split(":");
					}

					StringBuffer sb2 = new StringBuffer();
					if (localGovtBodyForm.getHeadQuarterCode() != null) {
						existingLocalBDataFinal = landregionlist.split(",");

						for (int i = 0; i < existingLocalBDataFinal.length; i++) {
							if (localGovtBodyForm.getHeadQuarterCode().contains(":")) {
								if (retavl[1].equals(existingLocalBDataFinal[i])) {
									String temp = existingLocalBDataFinal[i] + "_true";
									sb2.append(temp);
									sb2.append(",");
								} else {
									String temp = existingLocalBDataFinal[i] + "_false";
									sb2.append(temp);
									sb2.append(",");
								}
							} else {
								String coverageVillageVPFF = localGovtBodyForm.getHeadQuarterCode();
								String finalcoverageVillageVPFF = null;
								if (coverageVillageVPFF.contains("_F_true") || coverageVillageVPFF.contains("_F_false") || coverageVillageVPFF.contains("_P_true") || coverageVillageVPFF.contains("_P_false")) {
									StringBuffer valueBufferVillVPF = new StringBuffer();
									if (coverageVillageVPFF != null) {
										if (coverageVillageVPFF.contains(",")) {
											String[] temp = coverageVillageVPFF.split(",");
											for (int j = 0; j < temp.length; j++) {
												if (temp[j].contains("P")) {
													temp[j] = temp[j].replace("_P_false", "").replace("_P_true", "");

													valueBufferVillVPF.append(temp[j].concat("_PART_T"));
													/*
													 * if (i < temp.length)
													 * valueBufferVillVPF
													 * .append(",");
													 */
												} else if (temp[j].contains("F")) {
													temp[j] = temp[j].replaceAll("_F_false", "").replaceAll("_F_true", "");

													valueBufferVillVPF.append(temp[j].concat("_FULL_T"));
													/*
													 * if (j < temp.length)
													 * valueBufferVillVPF
													 * .append(",");
													 */
												}
											}
										} else {
											String temp = localGovtBodyForm.getHeadQuarterCode();

											if (temp.contains("P")) {
												temp = temp.replace("_P_false", "").replace("_P_true", "");
												valueBufferVillVPF.append(temp.concat("_PART_T"));
											} else if (temp.contains("F")) {
												temp = temp.replaceAll("_F_false", "").replaceAll("_F_true", "");
												valueBufferVillVPF.append(temp.concat("_FULL_T"));
												// valueBufferVillVPF.append(",");
											}
										}
									}
									// System.out.println("THE VALUE BUFFER==== "+valueBufferVillVPF);
									finalcoverageVillageVPFF = valueBufferVillVPF.toString();
								} else {
									finalcoverageVillageVPFF = coverageVillageVPFF;
								}

								if (finalcoverageVillageVPFF.equals(existingLocalBDataFinal[i])) {
									String temp = existingLocalBDataFinal[i] + "_true";
									sb2.append(temp);
									sb2.append(",");
								} else {
									String temp = existingLocalBDataFinal[i] + "_false";
									sb2.append(temp);
									if (i + 1 < existingLocalBDataFinal.length) {
										sb2.append(",");
									}
								}
							}
						}

					}
					if (localGovtBodyForm.getHeadQuarterCode() != null) {
						combinedLandregion = sb2.toString();
						if (combinedLandregion.endsWith(",")) {
							combinedLandregion = combinedLandregion.substring(0, combinedLandregion.length() - 1);
						}
					}
				}

				else if (lbtypeNameVal[1].equals("V") && localGovtBodyForm.getHeadQuarterCode() != null) {
					String valu = null;
					String[] retavl = null;
					if (localGovtBodyForm.getHeadQuarterCode().contains(":")) {
						valu = localGovtBodyForm.getHeadQuarterCode();
						retavl = valu.split(":");
					}

					StringBuffer sb2 = new StringBuffer();
					if (localGovtBodyForm.getHeadQuarterCode() != null) {
						existingLocalBDataFinal = landregionlist.split(",");

						for (int i = 0; i < existingLocalBDataFinal.length; i++) {
							if (localGovtBodyForm.getHeadQuarterCode().contains(":")) {
								if (retavl[1].equals(existingLocalBDataFinal[i])) {
									String temp = existingLocalBDataFinal[i] + "_true";
									sb2.append(temp);
									sb2.append(",");
								} else {
									String temp = existingLocalBDataFinal[i] + "_false";
									sb2.append(temp);
									sb2.append(",");
								}
							} else {
								String coverageVillageVPFF = localGovtBodyForm.getHeadQuarterCode();
								String finalcoverageVillageVPFF = null;
								if (coverageVillageVPFF.contains("_F_true") || coverageVillageVPFF.contains("_F_false") || coverageVillageVPFF.contains("_P_true") || coverageVillageVPFF.contains("_P_false")) {
									StringBuffer valueBufferVillVPF = new StringBuffer();
									if (coverageVillageVPFF != null) {
										if (coverageVillageVPFF.contains(",")) {
											String[] temp = coverageVillageVPFF.split(",");
											for (int j = 0; j < temp.length; j++) {
												if (temp[j].contains("P")) {
													temp[j] = temp[j].replace("_P_false", "").replace("_P_true", "");

													valueBufferVillVPF.append(temp[j].concat("_PART_V"));
													/*
													 * if (i < temp.length)
													 * valueBufferVillVPF
													 * .append(",");
													 */
												} else if (temp[j].contains("F")) {
													temp[j] = temp[j].replaceAll("_F_false", "").replaceAll("_F_true", "");

													valueBufferVillVPF.append(temp[j].concat("_FULL_V"));
													/*
													 * if (j < temp.length)
													 * valueBufferVillVPF
													 * .append(",");
													 */
												}
											}
										} else {
											String temp = localGovtBodyForm.getHeadQuarterCode();

											if (temp.contains("P")) {
												temp = temp.replace("_P_false", "").replace("_P_true", "");
												valueBufferVillVPF.append(temp.concat("_PART_V"));
											} else if (temp.contains("F")) {
												temp = temp.replaceAll("_F_false", "").replaceAll("_F_true", "");
												valueBufferVillVPF.append(temp.concat("_FULL_V"));
												// valueBufferVillVPF.append(",");
											}
										}
									}
									// System.out.println("THE VALUE BUFFER==== "+valueBufferVillVPF);
									finalcoverageVillageVPFF = valueBufferVillVPF.toString();
								} else {
									finalcoverageVillageVPFF = coverageVillageVPFF;
								}

								if (finalcoverageVillageVPFF.equals(existingLocalBDataFinal[i])) {
									String temp = existingLocalBDataFinal[i] + "_true";
									sb2.append(temp);
									sb2.append(",");
								} else {
									String temp = existingLocalBDataFinal[i] + "_false";
									sb2.append(temp);
									if (i + 1 < existingLocalBDataFinal.length) {
										sb2.append(",");
									}
								}
							}
						}

					}
					if (localGovtBodyForm.getHeadQuarterCode() != null) {
						combinedLandregion = sb2.toString();
						if (combinedLandregion.endsWith(",")) {
							combinedLandregion = combinedLandregion.substring(0, combinedLandregion.length() - 1);
						}
					}
				}
			}
			if (lbtypeNameVal[2].equals("U")) {
				combinedLandregion = landregionlist;
			}
			/*
			 * else { combinedLandregion=existingLocalBData+"_true"; }
			 */

			// Code for Urban Local Body----End

			String order_path = localGovtBodyForm.getOrderPath();
			SessionObject sessionObject = (SessionObject) httpsession.getAttribute("sessionObject");
	        Integer userId = sessionObject.getUserId()!=null?sessionObject.getUserId().intValue():null;
			// Integer userId =4117;
			query = session.getNamedQuery("changelocalbodycoveredAreaNameParentofDisturbLocalbody").setParameter("local_body_code", localBodyCode, Hibernate.INTEGER).setParameter("land_region_list", combinedLandregion, Hibernate.STRING)
					.setParameter("user_id", userId, Hibernate.INTEGER).setParameter("order_no", orderNo, Hibernate.STRING).setParameter("order_date", orderDate, Hibernate.DATE).setParameter("effective_date", lBeffectiveDate, Hibernate.DATE)
					.setParameter("gaz_pub_date", gazPubDate, Hibernate.DATE).setParameter("order_path", order_path, Hibernate.STRING).setParameter("lb_landregionlist", lb_landregionList, Hibernate.STRING)
					.setParameter("local_body_name_name_english", localBodyNameEnglish, Hibernate.STRING).setParameter("local_body_name_name_local", localBodyNameLocal, Hibernate.STRING)
					.setParameter("alias_english", aliasNameEnglish, Hibernate.STRING).setParameter("alias_local", alisNameLocal, Hibernate.STRING).setParameter("parent_code", parent_lblc, Hibernate.INTEGER)
					.setParameter("flag_code", flagCode, Hibernate.INTEGER).setParameter("lb_list_Full", lblistFull, Hibernate.STRING);

			// :local_body_name_name_english,:local_body_name_name_local,:alias_english,:alias_local,parent_code,:flag_code

			comboList = query.list();
			// retValue=comboList.get(0).getChange_coverage_of_local_body_fn();
			tx.commit();

		} catch (Exception e) {
			log.debug("Exception" + e);
			if (tx != null) {
				tx.rollback();
			}

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return comboList;
	}

	// fuction changes according to mange PRI and URBAN by kamlesh on 04-feb-13
	/**
	 * @param localGovtBodyForm
	 * @param maxlbcode
	 * @param request
	 * @param httpsession
	 * @param catagery
	 * @param isULBDisttLevel
	 *            (New Added Parameter)
	 * @return
	 * @throws Exception
	 * @author Vinay Yadav 23-12-2013
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean correctlocalbodyforCoveredArea(LocalGovtBodyForm localGovtBodyForm, Integer maxlbcode, HttpServletRequest request, HttpSession httpsession, String catagery, boolean isULBDisttLevel) throws Exception {
		Session session = null;
		Session session1 = null;
		Transaction tx = null;
		Transaction tx1 = null;
		Query query = null;//, query1
		String[] lgTypeNameArr = null;
		String localbodylabel = null;
		//Localbody localbody = new Localbody();
		String value = localGovtBodyForm.getLgd_LBTypeNamehidden();
		if (localGovtBodyForm.getLgd_LBTypeNamehidden() != null) {
			lgTypeNameArr = value.split(":");
			localbodylabel = lgTypeNameArr[1];
		}
		boolean returnSuccess = true;
		//Pushcoveragetopes comboList = new Pushcoveragetopes();
		//Integer retValue = null;
		int localBodyCode = 0;
		int localbodyVersion = 0;
		// Variable for District Panchayat
		String lgd_LBDistCAreaDestListUmapped = null;
		String lgd_LBSubDistrictDestLatDCAUmapped = null;
		String lgd_LBVillageDestLatDCAUmapped = null;

		// Variable for Intermediate Panchayat
		String lgd_LBInterCAreaDestListUmapped = null;
		String lgd_LBVillageDestLatICAUmapped = null;
		// Variable for IP and VP
		String lgd_LBVillageCAreaDestLUnmapped = null;
		String lgd_LBVillageCAreaDestLUnmappedF = null;
		String lgd_LBVillageAvailVP = null;
		String lgd_LBSubDistAvailULB = null;
		String finaltotalSubDistULB = null;
		String lgd_LBSubDistAvailIP = null;
		String finaltotalSubDistIP = null;
		String finaltotalDistDP = null;
		String lgd_LBDistUnmappedDP = null;
		String lgd_LBDistrictAvailDP = null;
		// Variable for Urban Local Body
		String lgd_LBsubDistCAreaDestLUnmapped = null;
		String lgd_LBSubDistUnMappedIP = null;
		String tmpLevel = null;
		int len;
		int lenDist = 0;
		int lenSubDist = 0;
		int lenVill = 0;

		LbCoveredLandregion[] lbCoveredLandregion;
		List<EntityVersion> entityVersion = new ArrayList<EntityVersion>();
		try {
			session = sessionFactory.openSession();
			session1 = sessionFactory.openSession();
			tx = session.beginTransaction();
			tx1 = session1.beginTransaction();
			int j = 0;
			int k = 0;
			int l = 0;

			localBodyCode = localGovtBodyForm.getLocalBodyCode();
			localbodyVersion = localGovtBodyForm.getLocalBodyVersion();
			if (catagery.equals("U")) {

				lgd_LBsubDistCAreaDestLUnmapped = localGovtBodyForm.getLgd_UrbanLBDistUmappedDest();
				String tempLand[] = null;
				String covtypeP = null;
				String covtypeF = null;
				if (lgd_LBsubDistCAreaDestLUnmapped != null) {
					len = lgd_LBsubDistCAreaDestLUnmapped.length();
					lbCoveredLandregion = new LbCoveredLandregion[len];
					Query querysubdist = null;
					Query querylbsubdist = null;
					LocalbodyDistrict localbodydist = null;
					LocalbodyDistrictPK localbodyDistrictPK = null;

					if (localGovtBodyForm.getLgd_UrbanLBDistUmappedDest() != null) {
						lgd_LBSubDistAvailULB = localGovtBodyForm.getLgd_UrbanLBDistUmappedDest().replaceAll("_PART_T", "").replaceAll("_FULL_T", "");
						if (isULBDisttLevel) {
							lgd_LBSubDistAvailULB = localGovtBodyForm.getLgd_UrbanLBDistUmappedDest().replaceAll("_PART_D", "").replaceAll("_FULL_D", "");
						}

					}
					if (localGovtBodyForm.getLgd_UrbanLBDistUmappedDest() != null && localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmappedUrban() == null) {
						finaltotalSubDistULB = lgd_LBSubDistAvailULB;
					}
					if (localGovtBodyForm.getLgd_UrbanLBDistUmappedDest() != null && localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmappedUrban() != null) {
						finaltotalSubDistULB = lgd_LBSubDistAvailULB + "," + localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmappedUrban();
					}
					if (localGovtBodyForm.getLgd_UrbanLBDistUmappedDest() == null && localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmappedUrban() != null) {
						finaltotalSubDistULB = localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmappedUrban();
					}

					Transformer transformer = new Transformer() {
						@Override
						public Integer transform(Object input) {
							return (input == null ? 0 : Integer.valueOf((String) input));
						}
					};
					Collection<Integer> ints = CollectionUtils.collect(Arrays.asList(finaltotalSubDistULB.split(",")), transformer);

					StringBuilder districtCd = new StringBuilder("Select district_code,district_version from district where dlc in(");
					if (isULBDisttLevel) {
						districtCd.append(":districtCodes");
					} else {
						districtCd.append("select dlc from subdistrict where tlc in(:districtCodes) and isactive");
					}
					districtCd.append(") and isactive");
					querysubdist = session.createSQLQuery(districtCd.toString()).setParameterList("districtCodes", ints);
					ArrayList arrayList = (ArrayList) querysubdist.list();
					Iterator iterator = arrayList.iterator();
					while (iterator.hasNext()) {
						localbodydist = new LocalbodyDistrict();
						localbodyDistrictPK = new LocalbodyDistrictPK();
						Object[] object = (Object[]) iterator.next();
						Integer discode = (Integer) object[0];
						Integer disversion = (Integer) object[1];
						String lb_dist = "Select * from localbody_districts where local_body_code=" + localBodyCode + "and district_code=" + discode;
						querylbsubdist = session.createSQLQuery(lb_dist);
						List listlbsubdist = querylbsubdist.list();
						if (listlbsubdist.isEmpty()) {

							localbodyDistrictPK.setLocalbodycode(localBodyCode);
							localbodyDistrictPK.setLocalbodyversion(localbodyVersion);
							localbodyDistrictPK.setDistrictcode(discode);
							localbodyDistrictPK.setDistrictversion(disversion);
							localbodydist.setLocalbodyDistrictPK(localbodyDistrictPK);
							session1.save(localbodydist);
						}
					}
					tx1.commit();
					// if(!lgd_LBSubDistAvailULB.equals(finaltotalSubDistULB))
					// {
					/*
					 * Integer maxlbcode1 = null; if(maxlbcode == null ||
					 * maxlbcode == 0) { maxlbcode1 = getmaxlandregioncode();
					 * }else{ maxlbcode1 = maxlbcode; }
					 */
					if (maxlbcode == null || maxlbcode == 0) {
						maxlbcode = getmaxlandregioncode();
					}

					if (lgd_LBsubDistCAreaDestLUnmapped != null) {
						//String typeCode = null;
						String type = null;
						//String categoryDropDown = null;
						String strArrayAvailULB[] = null;
						String strArray[] = lgd_LBsubDistCAreaDestLUnmapped.split("[_,]");
						String strArrayTemp[] = lgd_LBsubDistCAreaDestLUnmapped.split(",");
						String strArrayAvailULBTemp[] = null;

						if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmappedUrban() != null) {
							strArrayAvailULBTemp = localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmappedUrban().split(",");
						}

						int tmpLen = strArray.length / 3;
						// String strArray[] = strArray1.split(",");
						//String lbName = null;
						//String landregionlist = null;
						//landregionlist = typeCode;
						// char c = categoryDropDown.charAt(0);

						// landregionlist=lgd_LBVillageCAreaDestLUnmapped.replaceAll("_FULL_V","");
						// landregionlist=lgd_LBDistCAreaDestList+","+lgd_LBSubDistrictDestLatDCA+","+lgd_LBVillageDestLatDCA+lgd_LBVillageCAreaDestLUnmapped;

						// coverage_type, isactive, ismainregion, lrlc,
						// land_region_type, land_region_version,
						// lb_covered_region_code, lrlcversion, lbclr_code
						Integer landregionlistfor;
						//int tmp = 0;
						

						synchronized (this) {

							for (int i = 0; i < tmpLen; i++) {
								landregionlistfor = Integer.parseInt(strArray[j].toString());
								tmpLevel = strArray[j + 2];
								// change for version by kamlesh
								query = session.getNamedQuery("getEntityVersion");
								query.setParameter("entityType", "L", Hibernate.STRING);
								query.setParameter("entityLevel", "T", Hibernate.STRING);
								if (isULBDisttLevel) {
									query.setParameter("entityLevel", "D", Hibernate.STRING);
								}
								query.setParameter("entityCode", landregionlistfor, Hibernate.INTEGER);
								entityVersion = query.list();
								int tmpVersion = entityVersion.get(0).getEntity_version();
								lbCoveredLandregion[i] = new LbCoveredLandregion();
								lbCoveredLandregion[i].setLandRegionCode(landregionlistfor);
								//tmp++;
								type = strArray[j + 1];
								//tmp++;
								lbCoveredLandregion[i].setLandRegionType(strArray[j + 2].charAt(0));
								//tmp++;

								lbCoveredLandregion[i].setIsmainregion(true);
								lbCoveredLandregion[i].setIsactive(true);
								if (type.equalsIgnoreCase("PART")) {
									lbCoveredLandregion[i].setCoverageType('P');
								} else if (type.equalsIgnoreCase("FULL")) {
									lbCoveredLandregion[i].setCoverageType('F');
								}
								// Code commented to take the max lrlc value on
								// 13/03/2013

								/*
								 * Integer maxlbcode1 = null;
								 * 
								 * if(maxlbcode==0 || maxlbcode==null) {
								 * maxlbcode1 = getmaxlandregioncode();
								 * maxlbcode = maxlbcode1; } else { maxlbcode1 =
								 * maxlbcode; }
								 */

								// lbCoveredLandregion[i].setLbCoveredRegionCode(maxlbcode1);
								lbCoveredLandregion[i].setLbCoveredRegionCode(maxlbcode); // @author
																							// vinay
																							// 20-21-2013

								lbCoveredLandregion[i].setLandRegionVersion(tmpVersion);
								// Integer lb_covered_region_code = maxlbcode1;
								Integer lb_covered_region_code = maxlbcode;// @author
																			// vinay
																			// 20-21-2013

								if (!localGovtBodyForm.getLandregiondetails().equals("null") && !localGovtBodyForm.getLandregiondetails().equals("")) {
									String tempLandRegion[] = localGovtBodyForm.getLandregiondetails().split("~");

									for (int r = 0; r < tempLandRegion.length; r++) {
										tempLand = tempLandRegion[r].split(",");
										if (tempLand[1].equals("true")) {
											covtypeP = "update lb_covered_landregion set coverage_type='P' where lb_covered_region_code= " + lb_covered_region_code + " and lrlc="
													+ tempLand[0].replace("_P_false", "").replace("_P_true", "").replace("_F_false", "").replace("_F_true", "");
										} else if (tempLand[1].equals("false")) {
											covtypeF = "update lb_covered_landregion set coverage_type='F' where lb_covered_region_code= " + lb_covered_region_code + " and lrlc="
													+ tempLand[0].replace("_P_false", "").replace("_P_true", "").replace("_F_false", "").replace("_F_true", "");
										}
										if (covtypeP != null) {
											session.createSQLQuery(covtypeP).executeUpdate();
										}
										if (covtypeF != null) {
											session.createSQLQuery(covtypeF).executeUpdate();
										}

									}
								}

								StringBuffer sb2 = new StringBuffer();
								List list = Arrays.asList(strArrayTemp);
								String temp = "";
								String stringFinal = "";
								if (strArrayAvailULBTemp != null) {
									for (int m = 0; m < strArrayAvailULBTemp.length; m++) {
										if (strArrayAvailULBTemp.length < strArrayTemp.length) {
											temp = strArrayAvailULBTemp[m] + "_PART_T";
											if (isULBDisttLevel) {
												temp = strArrayAvailULBTemp[m] + "_PART_D";
											}
											if (list.contains(temp)) {
												sb2.append(temp);
												sb2.append(",");
											}
										} else if (strArrayTemp.length < strArrayAvailULBTemp.length) {
											temp = strArrayAvailULBTemp[m] + "_PART_T";
											if (isULBDisttLevel) {
												temp = strArrayAvailULBTemp[m] + "_PART_D";
											}
											if (list.contains(temp)) {
												sb2.append(temp);
												sb2.append(",");
											}
										} else if (strArrayAvailULBTemp.length == strArrayTemp.length) {
											temp = strArrayAvailULBTemp[m] + "_PART_T";
											// Conditional Check whether Logged
											// in state is ULB oprate District
											// wise.
											if (isULBDisttLevel) {
												temp = strArrayAvailULBTemp[m] + "_PART_D";
											}
											if (list.contains(temp)) {
												sb2.append(temp);
												sb2.append(",");
											}
										}
									}

									String sb2Temp = sb2.toString();
									if (!sb2Temp.equals("")) {
										if (strArrayAvailULBTemp.length <= strArrayTemp.length) {
											for (int m = strArrayAvailULBTemp.length; m < strArrayTemp.length; m++) {
												sb2.append(temp);
												sb2.append(",");
											}
										}
										if (strArrayTemp.length <= strArrayAvailULBTemp.length) {
											for (int m = strArrayTemp.length; m < strArrayAvailULBTemp.length; m++) {
												sb2.append(temp);
												sb2.append(",");
											}
										}
									}

									stringFinal = sb2.toString();
									strArrayAvailULB = stringFinal.split("[_,]");
								}
								String del = "update Localbody set lb_covered_region_code=" + lb_covered_region_code + " where local_body_code=" + localBodyCode + " and isactive=true ";
								session.createSQLQuery(del).executeUpdate();
								if (!stringFinal.equals("")) {
									if (!strArray[j].toString().equals(strArrayAvailULB[j].toString())) {
										session.save(lbCoveredLandregion[i]);
									}
								} else {
									session.save(lbCoveredLandregion[i]);
									// session.merge(lbCoveredLandregion[i]);
								}
								j = j + 3;
							}
						}
					}
					// }
					/*else {
						
						 * Modified Condition with max local body code, removed
						 * unused codes.
						 * 
						 * @author vinay 20-12-2013
						 
						Integer lb_covered_region_code = maxlbcode;

						if (!localGovtBodyForm.getLandregiondetails().equals("null") && !localGovtBodyForm.getLandregiondetails().equals("")) {
							String tempLandRegion[] = localGovtBodyForm.getLandregiondetails().split("~");

							for (int r = 0; r < tempLandRegion.length; r++) {
								tempLand = tempLandRegion[r].split(",");
								if (tempLand[1].equals("true")) {
									covtypeP = "update lb_covered_landregion set coverage_type='P' where lb_covered_region_code= " + lb_covered_region_code + " and lrlc="
											+ tempLand[0].replace("_P_false", "").replace("_P_true", "").replace("_F_false", "").replace("_F_true", "");
								} else if (tempLand[1].equals("false")) {
									covtypeF = "update lb_covered_landregion set coverage_type='F' where lb_covered_region_code= " + lb_covered_region_code + " and lrlc="
											+ tempLand[0].replace("_P_false", "").replace("_P_true", "").replace("_F_false", "").replace("_F_true", "");
								}
								if (covtypeP != null) {
									session.createSQLQuery(covtypeP).executeUpdate();
								}
								if (covtypeF != null) {
									session.createSQLQuery(covtypeF).executeUpdate();
								}

							}
						}
						String del = "update Localbody set lb_covered_region_code=" + lb_covered_region_code + " where local_body_code=" + localBodyCode + " and isactive=true ";
						session.createSQLQuery(del).executeUpdate();
					}*/
				}
				tx.commit();
			} else {
				if (localbodylabel.equals("V")) {
					String[] headquarArr = null;
					String updateheadquarter1 = null;
					String headQuratlblc = null;
					Integer landregionlistfor;
					String updateheadquarter = null;
					String finaltotalVillage = null;
					String covtypeP = null;
					String covtypeF = null;
					Query querydist = null;
					Query querylbdist = null;
					String strArray[] = null;
					//String typeCode = null;
					String type = null;
					//int distcd = 0;
					LocalbodyDistrict localbodydist = null;
					LocalbodyDistrictPK localbodyDistrictPK = null;
					String strArrayAvailVP[] = null;

					String tempLand[] = null;
					lgd_LBVillageCAreaDestLUnmapped = localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped();

					if (localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped() != null) {
						lgd_LBVillageCAreaDestLUnmappedF = localGovtBodyForm.getLgd_LBVillageCAreaDestLUnmapped().replaceAll("_PART_V", "").replaceAll("_FULL_V", "");
					}
					if (localGovtBodyForm.getAvaillgd_LBVillageCAreaSourceLUnmapped() != null) {
						lgd_LBVillageAvailVP = localGovtBodyForm.getAvaillgd_LBVillageCAreaSourceLUnmapped().replaceAll("_P_true", "").replaceAll("_P_false", "").replaceAll("_F_true", "").replaceAll("_F_false", "");
					}
					if (lgd_LBVillageCAreaDestLUnmappedF != null && lgd_LBVillageAvailVP != null) {
						finaltotalVillage = lgd_LBVillageCAreaDestLUnmappedF + "," + lgd_LBVillageAvailVP;
					} else if (lgd_LBVillageCAreaDestLUnmappedF != null && lgd_LBVillageAvailVP == null) {
						finaltotalVillage = lgd_LBVillageCAreaDestLUnmappedF;
					} else if (lgd_LBVillageCAreaDestLUnmappedF == null && lgd_LBVillageAvailVP != null) {
						finaltotalVillage = lgd_LBVillageAvailVP;
					}
					String districtCd = "Select district_code,district_version from district where dlc in(select dlc from subdistrict where tlc in(select tlc from village where vlc in(" + finaltotalVillage
							+ ") and isactive) and isactive) and isactive";
					querydist = session.createSQLQuery(districtCd);
					ArrayList arrayList = (ArrayList) querydist.list();
					Iterator iterator = arrayList.iterator();
					while (iterator.hasNext()) {
						localbodydist = new LocalbodyDistrict();
						localbodyDistrictPK = new LocalbodyDistrictPK();
						Object[] object = (Object[]) iterator.next();
						Integer discode = (Integer) object[0];
						Integer disversion = (Integer) object[1];
						String lb_dist = "Select * from localbody_districts where local_body_code=" + localBodyCode + "and district_code=" + discode;
						querylbdist = session.createSQLQuery(lb_dist);
						List listlbdist = querylbdist.list();
						if (listlbdist.isEmpty()) {

							localbodyDistrictPK.setLocalbodycode(localBodyCode);
							localbodyDistrictPK.setLocalbodyversion(localbodyVersion);
							localbodyDistrictPK.setDistrictcode(discode);
							localbodyDistrictPK.setDistrictversion(disversion);
							localbodydist.setLocalbodyDistrictPK(localbodyDistrictPK);
							session1.save(localbodydist);

						}
					}
					tx1.commit();
					/*
					 * if(!lgd_LBVillageCAreaDestLUnmappedF.equals(
					 * lgd_LBVillageAvailVP)) {
					 */
					if (lgd_LBVillageCAreaDestLUnmapped != null) {
						len = lgd_LBVillageCAreaDestLUnmapped.length();
						lbCoveredLandregion = new LbCoveredLandregion[len];

						if (lgd_LBVillageCAreaDestLUnmapped != null) {
							//String categoryDropDown = null;
							strArray = lgd_LBVillageCAreaDestLUnmapped.split("[_,]");
						}
						int tmpLen = strArray.length / 3;
						// String strArray[] = strArray1.split(",");
						//String lbName = null;
						//String landregionlist = null;
						//landregionlist = typeCode;
						//int tmp = 0;

						
						Integer maxlbcode1 = null;

						if (maxlbcode == 0 || maxlbcode == null) {
							maxlbcode1 = getmaxlandregioncode();
						} else {
							maxlbcode1 = maxlbcode;
						}

						synchronized (this) {
							for (int i = 0; i < tmpLen; i++) {
								landregionlistfor = Integer.parseInt(strArray[j].toString());
								tmpLevel = strArray[j + 2];
								query = session.getNamedQuery("getEntityVersion").setParameter("entityType", "L", Hibernate.STRING).setParameter("entityLevel", tmpLevel, Hibernate.STRING)
										.setParameter("entityCode", landregionlistfor, Hibernate.INTEGER);
								entityVersion = query.list();
								int tmpVersion = entityVersion.get(0).getEntity_version();

								lbCoveredLandregion[i] = new LbCoveredLandregion();
								lbCoveredLandregion[i].setLandRegionCode(landregionlistfor);
								//tmp++;
								type = strArray[j + 1];
								//tmp++;
								lbCoveredLandregion[i].setLandRegionType(strArray[j + 2].charAt(0));
								//tmp++;

								// lbCoveredLandregion[i].setIsmainregion(false);
								lbCoveredLandregion[i].setIsactive(true);
								if (type.equalsIgnoreCase("PART")) {
									lbCoveredLandregion[i].setCoverageType('P');
								} else if (type.equalsIgnoreCase("FULL")) {
									lbCoveredLandregion[i].setCoverageType('F');
								}

								lbCoveredLandregion[i].setLbCoveredRegionCode(maxlbcode1);
								// lbCoveredLandregion.setLandRegionCode(landregionlistfor);
								// lbCoveredLandregion.setLandRegionType(c);
								lbCoveredLandregion[i].setLandRegionVersion(tmpVersion);
								Integer lb_covered_region_code = maxlbcode1;

								/*
								 * String part=localGovtBodyForm.getPartR();
								 * String full=localGovtBodyForm.getFullR();
								 */

								// System.out.println("PART R++++++ "+part+
								// "FULL R===== "+full);

								if (localGovtBodyForm.getHeadQuarterCode() != null) {
									headquarArr = localGovtBodyForm.getHeadQuarterCode().split("_");
									headQuratlblc = headquarArr[0];
									if (headQuratlblc.equals(landregionlistfor.toString())) {
										lbCoveredLandregion[i].setIsmainregion(true);
									} else {
										lbCoveredLandregion[i].setIsmainregion(false);
									}
									updateheadquarter = "update lb_covered_landregion set ismainregion=true where lb_covered_region_code= " + lb_covered_region_code + " and lrlc=" + headQuratlblc;
									updateheadquarter1 = "update lb_covered_landregion set ismainregion=false where lb_covered_region_code= " + lb_covered_region_code + " and lrlc !=" + headQuratlblc;
								}

								if (!localGovtBodyForm.getLandregiondetails().equals("null") && !localGovtBodyForm.getLandregiondetails().equals("")) {
									String tempLandRegion[] = localGovtBodyForm.getLandregiondetails().split("~");
									for (int r = 0; r < tempLandRegion.length; r++) {
										tempLand = tempLandRegion[r].split(",");
										if (tempLand[1].equals("true")) {
											covtypeP = "update lb_covered_landregion set coverage_type='P' where lb_covered_region_code= " + lb_covered_region_code + " and lrlc="
													+ tempLand[0].replace("_P_false", "").replace("_P_true", "").replace("_F_false", "").replace("_F_true", "");
										} else if (tempLand[1].equals("false")) {
											covtypeF = "update lb_covered_landregion set coverage_type='F' where lb_covered_region_code= " + lb_covered_region_code + " and lrlc="
													+ tempLand[0].replace("_P_false", "").replace("_P_true", "").replace("_F_false", "").replace("_F_true", "");
										}
										if (covtypeP != null) {
											session.createSQLQuery(covtypeP).executeUpdate();
										}
										if (covtypeF != null) {
											session.createSQLQuery(covtypeF).executeUpdate();
										}

									}
								}
								String temp = "";
								String stringFinal = "";
								String strArrayTemp[] = null;
								if (lgd_LBVillageCAreaDestLUnmapped != null) {
									strArrayTemp = lgd_LBVillageCAreaDestLUnmapped.split(",");
									if (lgd_LBVillageAvailVP != null) {
										String strArrayAvailVPTemp[] = lgd_LBVillageAvailVP.split(",");

										StringBuffer sb2 = new StringBuffer();
										List list = Arrays.asList(strArrayTemp);
										for (int m = 0; m < strArrayAvailVPTemp.length; m++) {

											if (strArrayAvailVPTemp.length < strArrayTemp.length) {
												temp = strArrayAvailVPTemp[m] + "_PART_V";
												if (list.contains(temp)) {
													sb2.append(temp);
													sb2.append(",");
												}
											} else if (strArrayTemp.length < strArrayAvailVPTemp.length) {
												temp = strArrayAvailVPTemp[m] + "_PART_V";
												if (list.contains(temp)) {
													sb2.append(temp);
													sb2.append(",");
												}
											} else if (strArrayTemp.length == strArrayAvailVPTemp.length) {
												temp = strArrayAvailVPTemp[m] + "_PART_V";
												if (list.contains(temp)) {
													sb2.append(temp);
													sb2.append(",");
												}
											}

										}
										String sb2Temp = sb2.toString();
										if (!sb2Temp.equals("")) {
											if (strArrayAvailVPTemp.length <= strArrayTemp.length) {
												for (int m = strArrayAvailVPTemp.length; m < strArrayTemp.length; m++) {
													sb2.append(temp);
													sb2.append(",");
												}
											}

											if (strArrayTemp.length <= strArrayAvailVPTemp.length) {
												for (int m = strArrayTemp.length; m < strArrayAvailVPTemp.length; m++) {
													sb2.append(temp);
													sb2.append(",");
												}
											}
										}

										stringFinal = sb2.toString();
										strArrayAvailVP = stringFinal.split("[_,]");
									}
								}

								String del = "update Localbody set lb_covered_region_code=" + lb_covered_region_code + " where local_body_code=" + localBodyCode + " and isactive=true ";

								if (!stringFinal.equals("")) {
									if (!strArray[j].toString().equals(strArrayAvailVP[j].toString())) {
										session.save(lbCoveredLandregion[i]);
									}
								} else {
									session.save(lbCoveredLandregion[i]);
								}
								session.createSQLQuery(del).executeUpdate();
								session.createSQLQuery(updateheadquarter).executeUpdate();
								session.createSQLQuery(updateheadquarter1).executeUpdate();
								j = j + 3;
							}
						}

					}
					// }
					else {
						Integer maxlbcode1 = null;

						if (maxlbcode == 0 || maxlbcode == null) {
							maxlbcode1 = getmaxlandregioncode();
						} else {
							maxlbcode1 = maxlbcode;
						}

						Integer lb_covered_region_code = maxlbcode1;
						if (localGovtBodyForm.getHeadQuarterCode() != null) {
							headquarArr = localGovtBodyForm.getHeadQuarterCode().split("_");
							headQuratlblc = headquarArr[0];
							/*
							 * if(headQuratlblc.equals(landregionlistfor.toString
							 * ())) {
							 * lbCoveredLandregion[i].setIsmainregion(true); }
							 * else {
							 * lbCoveredLandregion[i].setIsmainregion(false); }
							 */
							updateheadquarter = "update lb_covered_landregion set ismainregion=true where lb_covered_region_code= " + lb_covered_region_code + " and lrlc=" + headQuratlblc;
							updateheadquarter1 = "update lb_covered_landregion set ismainregion=false where lb_covered_region_code= " + lb_covered_region_code + " and lrlc !=" + headQuratlblc;
						}

						if (!localGovtBodyForm.getLandregiondetails().equals("null") && !localGovtBodyForm.getLandregiondetails().equals("")) {
							String tempLandRegion[] = localGovtBodyForm.getLandregiondetails().split("~");
							for (int r = 0; r < tempLandRegion.length; r++) {
								tempLand = tempLandRegion[r].split(",");
								if (tempLand[1].equals("true")) {
									covtypeP = "update lb_covered_landregion set coverage_type='P' where lb_covered_region_code= " + lb_covered_region_code + " and lrlc="
											+ tempLand[0].replace("_P_false", "").replace("_P_true", "").replace("_F_false", "").replace("_F_true", "");
								} else if (tempLand[1].equals("false")) {
									covtypeF = "update lb_covered_landregion set coverage_type='F' where lb_covered_region_code= " + lb_covered_region_code + " and lrlc="
											+ tempLand[0].replace("_P_false", "").replace("_P_true", "").replace("_F_false", "").replace("_F_true", "");
								}
								if (covtypeP != null) {
									session.createSQLQuery(covtypeP).executeUpdate();
								}
								if (covtypeF != null) {
									session.createSQLQuery(covtypeF).executeUpdate();
								}

							}
						}

						String del = "update Localbody set lb_covered_region_code=" + lb_covered_region_code + " where local_body_code=" + localBodyCode + " and isactive=true ";
						session.createSQLQuery(del).executeUpdate();
						// session.save(lbCoveredLandregion[i]);
						session.createSQLQuery(updateheadquarter).executeUpdate();
						session.createSQLQuery(updateheadquarter1).executeUpdate();

					}
					tx.commit();
				} else if (localbodylabel.equals("D")) {
					lgd_LBDistCAreaDestListUmapped = localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped();
					lgd_LBSubDistrictDestLatDCAUmapped = localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped();
					lgd_LBVillageDestLatDCAUmapped = localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped();
					if (localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null) {
						lenDist = lgd_LBDistCAreaDestListUmapped.length();
					}
					lbCoveredLandregion = new LbCoveredLandregion[lenDist];

					if (localGovtBodyForm.getLgd_LBSubDistrictDestLatDCAUmapped() != null) {
						lenSubDist = lgd_LBSubDistrictDestLatDCAUmapped.length();
						lbCoveredLandregion = new LbCoveredLandregion[lenSubDist];
					}

					if (localGovtBodyForm.getLgd_LBVillageDestLatDCAUmapped() != null) {
						lenVill = lgd_LBVillageDestLatDCAUmapped.length();
						lbCoveredLandregion = new LbCoveredLandregion[lenVill];
					}
					Query querydist = null;
					Query querylbdist = null;
					LocalbodyDistrict localbodydist = null;
					LocalbodyDistrictPK localbodyDistrictPK = null;
					String[] headquarArr = null;
					String headQuratlblc = null;
					String updateheadquarter = null;
					String updateheadquarter1 = null;
					String tempLand[] = null;
					String covtypeP = null;
					String covtypeF = null;

					Integer maxlbcode1 = null;

					if (maxlbcode == 0 || maxlbcode == null) {
						maxlbcode1 = getmaxlandregioncode();
					} else {
						maxlbcode1 = maxlbcode;
					}

					if (localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null) {
						lgd_LBDistUnmappedDP = localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped().replaceAll("_PART_D", "").replaceAll("_FULL_D", "");
					}
					if (localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null) {
						lgd_LBDistrictAvailDP = localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped().replaceAll("_P_true", "").replaceAll("_P_false", "").replaceAll("_F_true", "").replaceAll("_F_false", "");
					}
					if (localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() == null) {
						finaltotalDistDP = lgd_LBDistUnmappedDP;
					}
					if (localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() != null && localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null) {
						finaltotalDistDP = lgd_LBDistUnmappedDP + "," + lgd_LBDistrictAvailDP;
					}
					if (localGovtBodyForm.getLgd_LBDistCAreaDestListUmapped() == null && localGovtBodyForm.getAvaillgd_LBDistCAreaSourceListUmapped() != null) {
						finaltotalDistDP = lgd_LBDistrictAvailDP;
					}

					String districtCd = "Select district_code,district_version from district where dlc in(" + finaltotalDistDP + ") and isactive";
					querydist = session.createSQLQuery(districtCd);
					ArrayList arrayList = (ArrayList) querydist.list();
					Iterator iterator = arrayList.iterator();
					while (iterator.hasNext()) {
						localbodydist = new LocalbodyDistrict();
						localbodyDistrictPK = new LocalbodyDistrictPK();
						Object[] object = (Object[]) iterator.next();
						Integer discode = (Integer) object[0];
						Integer disversion = (Integer) object[1];
						String lb_dist = "Select * from localbody_districts where local_body_code=" + localBodyCode + "and district_code=" + discode;
						querylbdist = session.createSQLQuery(lb_dist);
						List listlbdist = querylbdist.list();
						if (listlbdist.isEmpty()) {

							localbodyDistrictPK.setLocalbodycode(localBodyCode);
							localbodyDistrictPK.setLocalbodyversion(localbodyVersion);
							localbodyDistrictPK.setDistrictcode(discode);
							localbodyDistrictPK.setDistrictversion(disversion);
							localbodydist.setLocalbodyDistrictPK(localbodyDistrictPK);
							session1.save(localbodydist);

						}
					}
					tx1.commit();
					/*
					 * if(!lgd_LBDistUnmappedDP.equals(lgd_LBDistrictAvailDP)) {
					 */
					if (lgd_LBDistCAreaDestListUmapped != null) {
						//String typeCode = null;
						String type = null;
						String strArrayDistrictDP[] = null;
						//String categoryDropDown = null;
						String strArray[] = lgd_LBDistCAreaDestListUmapped.split("[_,]");
						String strArrayTemp[] = lgd_LBDistCAreaDestListUmapped.split(",");

						int tmpLen = strArray.length / 3;
						// String strArray[] = strArray1.split(",");
						//String lbName = null;
						//String landregionlist = null;
						//landregionlist = typeCode;

						Integer landregionlistfor;
						//int tmp = 0;

						
						synchronized (this) {

							for (int i = 0; i < tmpLen; i++) {
								landregionlistfor = Integer.parseInt(strArray[k].toString());
								tmpLevel = strArray[k + 2];
								query = session.getNamedQuery("getEntityVersion").setParameter("entityType", "L", Hibernate.STRING).setParameter("entityLevel", tmpLevel, Hibernate.STRING)
										.setParameter("entityCode", landregionlistfor, Hibernate.INTEGER);
								entityVersion = query.list();
								int tmpVersion = entityVersion.get(0).getEntity_version();

								lbCoveredLandregion[i] = new LbCoveredLandregion();
								lbCoveredLandregion[i].setLandRegionCode(landregionlistfor);
								//tmp++;
								type = strArray[k + 1];
								//tmp++;
								lbCoveredLandregion[i].setLandRegionType(strArray[k + 2].charAt(0));
								//tmp++;

								lbCoveredLandregion[i].setIsmainregion(true);
								lbCoveredLandregion[i].setIsactive(true);
								if (type.equalsIgnoreCase("PART")) {
									lbCoveredLandregion[i].setCoverageType('P');
								} else if (type.equalsIgnoreCase("FULL")) {
									lbCoveredLandregion[i].setCoverageType('F');
								}

								lbCoveredLandregion[i].setLbCoveredRegionCode(maxlbcode1);
								lbCoveredLandregion[i].setLandRegionVersion(tmpVersion);
								Integer lb_covered_region_code = maxlbcode1;

								if (localGovtBodyForm.getHeadQuarterCode() != null) {
									headquarArr = localGovtBodyForm.getHeadQuarterCode().split("_");
									headQuratlblc = headquarArr[0];
									if (headQuratlblc.equals(landregionlistfor.toString())) {
										lbCoveredLandregion[i].setIsmainregion(true);
									} else {
										lbCoveredLandregion[i].setIsmainregion(false);
									}
									updateheadquarter = "update lb_covered_landregion set ismainregion=true where lb_covered_region_code= " + lb_covered_region_code + " and lrlc=" + headQuratlblc;
									updateheadquarter1 = "update lb_covered_landregion set ismainregion=false where lb_covered_region_code= " + lb_covered_region_code + " and lrlc !=" + headQuratlblc;
								}

								if (!localGovtBodyForm.getLandregiondetails().equals("null") && !localGovtBodyForm.getLandregiondetails().equals("")) {
									String tempLandRegion[] = localGovtBodyForm.getLandregiondetails().split("~");

									for (int r = 0; r < tempLandRegion.length; r++) {
										tempLand = tempLandRegion[r].split(",");
										if (tempLand[1].equals("true")) {
											covtypeP = "update lb_covered_landregion set coverage_type='P' where lb_covered_region_code= " + lb_covered_region_code + " and lrlc="
													+ tempLand[0].replace("_P_false", "").replace("_P_true", "").replace("_F_false", "").replace("_F_true", "");
										} else if (tempLand[1].equals("false")) {
											covtypeF = "update lb_covered_landregion set coverage_type='F' where lb_covered_region_code= " + lb_covered_region_code + " and lrlc="
													+ tempLand[0].replace("_P_false", "").replace("_P_true", "").replace("_F_false", "").replace("_F_true", "");
										}
										if (covtypeP != null) {
											session.createSQLQuery(covtypeP).executeUpdate();
										}
										if (covtypeF != null) {
											session.createSQLQuery(covtypeF).executeUpdate();
										}

									}
								}

								String temp = "";
								String stringFinal = "";
								if (lgd_LBDistrictAvailDP != null) {
									String strArrayDistrictDPTemp[] = lgd_LBDistrictAvailDP.split(",");

									StringBuffer sb2 = new StringBuffer();
									List list = Arrays.asList(strArrayTemp);
									if (strArrayDistrictDPTemp != null) {
										for (int m = 0; m < strArrayDistrictDPTemp.length; m++) {
											if (strArrayDistrictDPTemp.length < strArrayTemp.length) {
												temp = strArrayDistrictDPTemp[m] + "_PART_D";
												if (list.contains(temp)) {
													sb2.append(temp);
													sb2.append(",");
												}
											} else if (strArrayTemp.length < strArrayDistrictDPTemp.length) {
												temp = strArrayDistrictDPTemp[m] + "_PART_D";
												if (list.contains(temp)) {
													sb2.append(temp);
													sb2.append(",");
												}
											} else if (strArrayDistrictDPTemp.length == strArrayTemp.length) {
												temp = strArrayDistrictDPTemp[m] + "_PART_D";
												if (list.contains(temp)) {
													sb2.append(temp);
													sb2.append(",");
												}
											}
										}

										String sb2Temp = sb2.toString();
										if (!sb2Temp.equals("")) {
											if (strArrayDistrictDPTemp.length <= strArrayTemp.length) {
												for (int m = strArrayDistrictDPTemp.length; m < strArrayTemp.length; m++) {
													sb2.append(temp);
													sb2.append(",");
												}
											}

											if (strArrayTemp.length <= strArrayDistrictDPTemp.length) {
												for (int m = strArrayTemp.length; m < strArrayDistrictDPTemp.length; m++) {
													sb2.append(temp);
													sb2.append(",");
												}
											}
										}

										stringFinal = sb2.toString();
										strArrayDistrictDP = stringFinal.split("[_,]");
									}
								}

								String del = "update Localbody set lb_covered_region_code=" + lb_covered_region_code + " where local_body_code=" + localBodyCode + " and isactive=true ";
								session.createSQLQuery(del).executeUpdate();
								if (!stringFinal.equals("")) {
									if (!strArray[k].toString().equals(strArrayDistrictDP[k].toString())) {
										session.save(lbCoveredLandregion[i]);
									}
								} else {
									session.save(lbCoveredLandregion[i]);
								}
								session.createSQLQuery(updateheadquarter).executeUpdate();
								session.createSQLQuery(updateheadquarter1).executeUpdate();
								k = k + 3;
							}
						}
					}
					// }
					else {
						Integer lb_covered_region_code = maxlbcode1;
						if (localGovtBodyForm.getHeadQuarterCode() != null) {
							headquarArr = localGovtBodyForm.getHeadQuarterCode().split("_");
							headQuratlblc = headquarArr[0];
							/*
							 * if(headQuratlblc.equals(landregionlistfor.toString
							 * ())) {
							 * lbCoveredLandregion[i].setIsmainregion(true); }
							 * else {
							 * lbCoveredLandregion[i].setIsmainregion(false); }
							 */
							updateheadquarter = "update lb_covered_landregion set ismainregion=true where lb_covered_region_code= " + lb_covered_region_code + " and lrlc=" + headQuratlblc;
							updateheadquarter1 = "update lb_covered_landregion set ismainregion=false where lb_covered_region_code= " + lb_covered_region_code + " and lrlc !=" + headQuratlblc;
						}

						if (!localGovtBodyForm.getLandregiondetails().equals("null") && !localGovtBodyForm.getLandregiondetails().equals("")) {
							String tempLandRegion[] = localGovtBodyForm.getLandregiondetails().split("~");

							for (int r = 0; r < tempLandRegion.length; r++) {
								tempLand = tempLandRegion[r].split(",");
								if (tempLand[1].equals("true")) {
									covtypeP = "update lb_covered_landregion set coverage_type='P' where lb_covered_region_code= " + lb_covered_region_code + " and lrlc="
											+ tempLand[0].replace("_P_false", "").replace("_P_true", "").replace("_F_false", "").replace("_F_true", "");
								} else if (tempLand[1].equals("false")) {
									covtypeF = "update lb_covered_landregion set coverage_type='F' where lb_covered_region_code= " + lb_covered_region_code + " and lrlc="
											+ tempLand[0].replace("_P_false", "").replace("_P_true", "").replace("_F_false", "").replace("_F_true", "");
								}
								if (covtypeP != null) {
									session.createSQLQuery(covtypeP).executeUpdate();
								}
								if (covtypeF != null) {
									session.createSQLQuery(covtypeF).executeUpdate();
								}

							}
						}

						String del = "update Localbody set lb_covered_region_code=" + lb_covered_region_code + " where local_body_code=" + localBodyCode + " and isactive=true ";
						session.createSQLQuery(del).executeUpdate();
						session.createSQLQuery(updateheadquarter).executeUpdate();
						session.createSQLQuery(updateheadquarter1).executeUpdate();
					}

					if (lgd_LBSubDistrictDestLatDCAUmapped != null) {
						String type1 = null;
						String lgd_LBSubDistAvailDP = null;
						String strArraySubDistDP[] = null;
						String strArraySubDistDPTemp[] = null;
						String strArray1[] = lgd_LBSubDistrictDestLatDCAUmapped.split("[_,]");
						String strArray1Temp[] = lgd_LBSubDistrictDestLatDCAUmapped.split(",");
						if (localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped() != null) {
							lgd_LBSubDistAvailDP = localGovtBodyForm.getAvaillgd_LBSubDistrictSourceLatDCAUmapped().replaceAll("_P_true", "").replaceAll("_P_false", "").replaceAll("_F_true", "").replaceAll("_F_false", "");
						}
						// String strArrayVillageIP[]=
						// lgd_LBVillageAvailIP.split(",");

						if (lgd_LBSubDistAvailDP != null) {
							strArraySubDistDPTemp = lgd_LBSubDistAvailDP.split(",");
						}

						int tmpLen1 = strArray1.length / 3;

						Integer landregionlistfor1;
						//int tmp1 = 0;

						

						synchronized (this) {

							for (int i = 0; i < tmpLen1; i++) {
								landregionlistfor1 = Integer.parseInt(strArray1[l].toString());
								tmpLevel = strArray1[l + 2];
								query = session.getNamedQuery("getEntityVersion").setParameter("entityType", "L", Hibernate.STRING).setParameter("entityLevel", tmpLevel, Hibernate.STRING)
										.setParameter("entityCode", landregionlistfor1, Hibernate.INTEGER);
								entityVersion = query.list();
								int tmpVersion = entityVersion.get(0).getEntity_version();

								lbCoveredLandregion[i] = new LbCoveredLandregion();
								lbCoveredLandregion[i].setLandRegionCode(landregionlistfor1);
								//tmp1++;
								type1 = strArray1[l + 1];
								//tmp1++;
								lbCoveredLandregion[i].setLandRegionType(strArray1[l + 2].charAt(0));
								//tmp1++;
								lbCoveredLandregion[i].setIsmainregion(true);
								lbCoveredLandregion[i].setIsactive(true);
								if (type1.equalsIgnoreCase("PART")) {
									lbCoveredLandregion[i].setCoverageType('P');
								} else if (type1.equalsIgnoreCase("FULL")) {
									lbCoveredLandregion[i].setCoverageType('F');
								}

								lbCoveredLandregion[i].setLbCoveredRegionCode(maxlbcode1);
								lbCoveredLandregion[i].setLandRegionVersion(tmpVersion);
								Integer lb_covered_region_code = maxlbcode1;

								if (localGovtBodyForm.getHeadQuarterCode() != null) {
									headquarArr = localGovtBodyForm.getHeadQuarterCode().split("_");
									headQuratlblc = headquarArr[0];
									if (headQuratlblc.equals(landregionlistfor1.toString())) {
										lbCoveredLandregion[i].setIsmainregion(true);
									} else {
										lbCoveredLandregion[i].setIsmainregion(false);
									}
									updateheadquarter = "update lb_covered_landregion set ismainregion=true where lb_covered_region_code= " + lb_covered_region_code + " and lrlc=" + headQuratlblc;
									updateheadquarter1 = "update lb_covered_landregion set ismainregion=false where lb_covered_region_code= " + lb_covered_region_code + " and lrlc !=" + headQuratlblc;
								}

								StringBuffer sb2 = new StringBuffer();
								String temp = "";
								String stringFinal = "";
								List list = Arrays.asList(strArray1Temp);
								if (strArraySubDistDPTemp != null) {
									for (int m = 0; m < strArraySubDistDPTemp.length; m++) {
										if (strArraySubDistDPTemp.length < strArray1Temp.length) {
											temp = strArraySubDistDPTemp[m] + "_PART_T";
											if (list.contains(temp)) {
												sb2.append(temp);
												sb2.append(",");
											}
										} else if (strArray1Temp.length < strArraySubDistDPTemp.length) {
											temp = strArraySubDistDPTemp[m] + "_PART_T";
											if (list.contains(temp)) {
												sb2.append(temp);
												sb2.append(",");
											}
										} else if (strArraySubDistDPTemp.length == strArray1Temp.length) {
											temp = strArraySubDistDPTemp[m] + "_PART_T";
											if (list.contains(temp)) {
												sb2.append(temp);
												sb2.append(",");
											}
										}
									}

									String sb2Temp = sb2.toString();
									if (!sb2Temp.equals("")) {
										if (strArraySubDistDPTemp.length <= strArray1Temp.length) {
											for (int m = strArraySubDistDPTemp.length; m < strArray1Temp.length; m++) {
												sb2.append(temp);
												sb2.append(",");
											}
										}

										if (strArray1Temp.length <= strArraySubDistDPTemp.length) {
											for (int m = strArray1Temp.length; m < strArraySubDistDPTemp.length; m++) {
												sb2.append(temp);
												sb2.append(",");
											}
										}
									}
									stringFinal = sb2.toString();
									strArraySubDistDP = stringFinal.split("[_,]");
								}

								String del = "update Localbody set lb_covered_region_code=" + lb_covered_region_code + " where local_body_code=" + localBodyCode + " and isactive=true ";
								session.createSQLQuery(del).executeUpdate();

								if (!stringFinal.equals("")) {
									if (!strArray1[l].toString().equals(strArraySubDistDP[l].toString())) {
										session.save(lbCoveredLandregion[i]);
									}
								} else {
									session.save(lbCoveredLandregion[i]);
								}

								session.createSQLQuery(updateheadquarter).executeUpdate();
								session.createSQLQuery(updateheadquarter1).executeUpdate();
								l = l + 3;
							}
						}
					}
					if (lgd_LBVillageDestLatDCAUmapped != null) {
						//String typeCode2 = null;
						String type2 = null;
						String lgd_LBVillageAvailDP = null;
						String strArrayVillageDP[] = null;
						String strArray2[] = lgd_LBVillageDestLatDCAUmapped.split("[_,]");
						String strArray2Temp[] = lgd_LBVillageDestLatDCAUmapped.split(",");
						String strArrayVillageDPTemp[] = null;
						if (localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped() != null) {
							lgd_LBVillageAvailDP = localGovtBodyForm.getAvaillgd_LBVillageSourceLatDCAUmapped().replaceAll("_P_true", "").replaceAll("_P_false", "").replaceAll("_F_true", "").replaceAll("_F_false", "");
						}
						// String strArrayVillageIP[]=
						// lgd_LBVillageAvailIP.split(",");

						if (lgd_LBVillageAvailDP != null) {
							strArrayVillageDPTemp = lgd_LBVillageAvailDP.split(",");
						}

						int tmpLen2 = strArray2.length / 3;

						//String landregionlist2 = null;
						//landregionlist2 = typeCode2;

						Integer landregionlistfor2;
						//int tmp1 = 0;

						

						synchronized (this) {

							for (int i = 0; i < tmpLen2; i++) {
								landregionlistfor2 = Integer.parseInt(strArray2[j].toString());
								tmpLevel = strArray2[j + 2];
								query = session.getNamedQuery("getEntityVersion").setParameter("entityType", "L", Hibernate.STRING).setParameter("entityLevel", tmpLevel, Hibernate.STRING)
										.setParameter("entityCode", landregionlistfor2, Hibernate.INTEGER);
								entityVersion = query.list();
								int tmpVersion = entityVersion.get(0).getEntity_version();

								lbCoveredLandregion[i] = new LbCoveredLandregion();
								lbCoveredLandregion[i].setLandRegionCode(landregionlistfor2);
								//tmp1++;
								type2 = strArray2[j + 1];
								//tmp1++;
								lbCoveredLandregion[i].setLandRegionType(strArray2[j + 2].charAt(0));
								//tmp1++;
								lbCoveredLandregion[i].setIsmainregion(true);
								lbCoveredLandregion[i].setIsactive(true);
								if (type2.equalsIgnoreCase("PART")) {
									lbCoveredLandregion[i].setCoverageType('P');
								} else if (type2.equalsIgnoreCase("FULL")) {
									lbCoveredLandregion[i].setCoverageType('F');
								}

								lbCoveredLandregion[i].setLbCoveredRegionCode(maxlbcode1);
								lbCoveredLandregion[i].setLandRegionVersion(tmpVersion);
								Integer lb_covered_region_code = maxlbcode1;
								if (localGovtBodyForm.getHeadQuarterCode() != null) {
									headquarArr = localGovtBodyForm.getHeadQuarterCode().split("_");
									headQuratlblc = headquarArr[0];
									if (headQuratlblc.equals(landregionlistfor2.toString())) {
										lbCoveredLandregion[i].setIsmainregion(true);
									} else {
										lbCoveredLandregion[i].setIsmainregion(false);
									}
									updateheadquarter = "update lb_covered_landregion set ismainregion=true where lb_covered_region_code= " + lb_covered_region_code + " and lrlc=" + headQuratlblc;
									updateheadquarter1 = "update lb_covered_landregion set ismainregion=false where lb_covered_region_code= " + lb_covered_region_code + " and lrlc !=" + headQuratlblc;
								}

								StringBuffer sb2 = new StringBuffer();
								String stringFinal = "";
								List list = Arrays.asList(strArray2Temp);
								String temp = "";
								if (strArrayVillageDPTemp != null) {
									for (int m = 0; m < strArrayVillageDPTemp.length; m++) {
										if (strArrayVillageDPTemp.length < strArray2Temp.length) {
											temp = strArrayVillageDPTemp[m] + "_PART_V";
											if (list.contains(temp)) {
												sb2.append(temp);
												sb2.append(",");
											}
										} else if (strArray2Temp.length < strArrayVillageDPTemp.length) {
											temp = strArrayVillageDPTemp[m] + "_PART_V";
											if (list.contains(temp)) {
												sb2.append(temp);
												sb2.append(",");
											}
										} else if (strArrayVillageDPTemp.length == strArray2Temp.length) {
											temp = strArrayVillageDPTemp[m] + "_PART_V";
											if (list.contains(temp)) {
												sb2.append(temp);
												sb2.append(",");
											}
										}

									}

									String sb2Temp = sb2.toString();
									if (!sb2Temp.equals("")) {
										if (strArrayVillageDPTemp.length <= strArray2Temp.length) {
											for (int m = strArrayVillageDPTemp.length; m < strArray2Temp.length; m++) {
												sb2.append(temp);
												sb2.append(",");
											}
										}

										if (strArray2Temp.length <= strArrayVillageDPTemp.length) {
											for (int m = strArray2Temp.length; m < strArrayVillageDPTemp.length; m++) {
												sb2.append(temp);
												sb2.append(",");
											}
										}
									}
									stringFinal = sb2.toString();
									strArrayVillageDP = stringFinal.split("[_,]");
								}

								String del = "update Localbody set lb_covered_region_code=" + lb_covered_region_code + " where local_body_code=" + localBodyCode + " and isactive=true ";
								session.createSQLQuery(del).executeUpdate();

								if (!stringFinal.equals("")) {
									if (!strArray2[j].toString().equals(strArrayVillageDP[j].toString())) {
										session.save(lbCoveredLandregion[i]);
									}
								} else {
									session.save(lbCoveredLandregion[i]);
								}
								session.createSQLQuery(updateheadquarter).executeUpdate();
								session.createSQLQuery(updateheadquarter1).executeUpdate();
								j = j + 3;
							}
						}
					}
					tx.commit();
				} else if (localbodylabel.equals("I")) {
					lgd_LBInterCAreaDestListUmapped = localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped();
					lgd_LBVillageDestLatICAUmapped = localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped();
					Query querysubdist = null;
					Query querylbsubdist = null;
					LocalbodyDistrict localbodydist = null;
					LocalbodyDistrictPK localbodyDistrictPK = null;
					String[] headquarArr = null;
					String headQuratlblc = null;
					String updateheadquarter = null;
					String updateheadquarter1 = null;
					String tempLand[] = null;
					String covtypeP = null;
					String covtypeF = null;

					if (localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null) {
						lenSubDist = lgd_LBInterCAreaDestListUmapped.length();
					}
					lbCoveredLandregion = new LbCoveredLandregion[lenSubDist];

					if (localGovtBodyForm.getLgd_LBVillageDestLatICAUmapped() != null) {
						lenVill = lgd_LBVillageDestLatICAUmapped.length();
						lbCoveredLandregion = new LbCoveredLandregion[lenVill];
					}

					Integer maxlbcode1 = null;

					if (maxlbcode == 0 || maxlbcode == null) {
						maxlbcode1 = getmaxlandregioncode();
					} else {
						maxlbcode1 = maxlbcode;
					}

					if (localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null) {
						lgd_LBSubDistUnMappedIP = localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped().replaceAll("_PART_T", "").replaceAll("_FULL_T", "");
					}
					if (localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null) {
						lgd_LBSubDistAvailIP = localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped().replaceAll("_P_true", "").replaceAll("_P_false", "").replaceAll("_F_true", "").replaceAll("_F_false", "");
					}
					if (localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null && localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() == null) {
						finaltotalSubDistIP = lgd_LBSubDistUnMappedIP;
					}
					if (localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() != null && localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null) {
						finaltotalSubDistIP = lgd_LBSubDistUnMappedIP + "," + lgd_LBSubDistAvailIP;
					}
					if (localGovtBodyForm.getLgd_LBInterCAreaDestListUmapped() == null && localGovtBodyForm.getAvaillgd_LBInterCAreaSourceListUmapped() != null) {
						finaltotalSubDistIP = lgd_LBSubDistAvailIP;
					}

					String districtCd = "Select district_code,district_version from district where dlc in(select dlc from subdistrict where tlc in(" + finaltotalSubDistIP + ") and isactive) and isactive";
					querysubdist = session.createSQLQuery(districtCd);
					ArrayList arrayList = (ArrayList) querysubdist.list();
					Iterator iterator = arrayList.iterator();
					while (iterator.hasNext()) {
						localbodydist = new LocalbodyDistrict();
						localbodyDistrictPK = new LocalbodyDistrictPK();
						Object[] object = (Object[]) iterator.next();
						Integer discode = (Integer) object[0];
						Integer disversion = (Integer) object[1];
						String lb_dist = "Select * from localbody_districts where local_body_code=" + localBodyCode + "and district_code=" + discode;
						querylbsubdist = session.createSQLQuery(lb_dist);
						List listlbsubdist = querylbsubdist.list();
						if (listlbsubdist.isEmpty()) {

							localbodyDistrictPK.setLocalbodycode(localBodyCode);
							localbodyDistrictPK.setLocalbodyversion(localbodyVersion);
							localbodyDistrictPK.setDistrictcode(discode);
							localbodyDistrictPK.setDistrictversion(disversion);
							localbodydist.setLocalbodyDistrictPK(localbodyDistrictPK);
							session1.save(localbodydist);

						}
					}
					tx1.commit();

					if (lgd_LBInterCAreaDestListUmapped != null) {
						String type1 = null;
						String strArraySubDistIP[] = null;
						String strArray1[] = lgd_LBInterCAreaDestListUmapped.split("[_,]");
						String strArray1Temp[] = lgd_LBInterCAreaDestListUmapped.split(",");
						String strArraySubDistIPTemp[] = null;
						if (lgd_LBSubDistAvailIP != null) {
							strArraySubDistIPTemp = lgd_LBSubDistAvailIP.split(",");
						}
						int tmpLen1 = strArray1.length / 3;

						Integer landregionlistfor1;
						//int tmp1 = 0;

						synchronized (this) {

							for (int i = 0; i < tmpLen1; i++) {
								landregionlistfor1 = Integer.parseInt(strArray1[l].toString());
								tmpLevel = strArray1[l + 2];
								query = session.getNamedQuery("getEntityVersion").setParameter("entityType", "L", Hibernate.STRING).setParameter("entityLevel", tmpLevel, Hibernate.STRING)
										.setParameter("entityCode", landregionlistfor1, Hibernate.INTEGER);
								entityVersion = query.list();
								int tmpVersion = entityVersion.get(0).getEntity_version();

								lbCoveredLandregion[i] = new LbCoveredLandregion();
								lbCoveredLandregion[i].setLandRegionCode(landregionlistfor1);
								//tmp1++;
								type1 = strArray1[l + 1];
								//tmp1++;
								lbCoveredLandregion[i].setLandRegionType(strArray1[l + 2].charAt(0));
								//tmp1++;
								lbCoveredLandregion[i].setIsmainregion(true);
								lbCoveredLandregion[i].setIsactive(true);
								if (type1.equalsIgnoreCase("PART")) {
									lbCoveredLandregion[i].setCoverageType('P');
								} else if (type1.equalsIgnoreCase("FULL")) {
									lbCoveredLandregion[i].setCoverageType('F');
								}

								lbCoveredLandregion[i].setLbCoveredRegionCode(maxlbcode1);
								lbCoveredLandregion[i].setLandRegionVersion(tmpVersion);
								Integer lb_covered_region_code = maxlbcode1;

								if (localGovtBodyForm.getHeadQuarterCode() != null) {
									headquarArr = localGovtBodyForm.getHeadQuarterCode().split("_");
									headQuratlblc = headquarArr[0];
									if (headQuratlblc.equals(landregionlistfor1.toString())) {
										lbCoveredLandregion[i].setIsmainregion(true);
									} else {
										lbCoveredLandregion[i].setIsmainregion(false);
									}
									updateheadquarter = "update lb_covered_landregion set ismainregion=true where lb_covered_region_code= " + lb_covered_region_code + " and lrlc=" + headQuratlblc;
									updateheadquarter1 = "update lb_covered_landregion set ismainregion=false where lb_covered_region_code= " + lb_covered_region_code + " and lrlc !=" + headQuratlblc;
								}

								if (!localGovtBodyForm.getLandregiondetails().equals("null") && !localGovtBodyForm.getLandregiondetails().equals("")) {

									String tempLandRegion[] = localGovtBodyForm.getLandregiondetails().split("~");

									for (int r = 0; r < tempLandRegion.length; r++) {
										tempLand = tempLandRegion[r].split(",");
										if (tempLand[1].equals("true")) {
											covtypeP = "update lb_covered_landregion set coverage_type='P' where lb_covered_region_code= " + lb_covered_region_code + " and lrlc="
													+ tempLand[0].replace("_P_false", "").replace("_P_true", "").replace("_F_false", "").replace("_F_true", "");
										} else if (tempLand[1].equals("false")) {
											covtypeF = "update lb_covered_landregion set coverage_type='F' where lb_covered_region_code= " + lb_covered_region_code + " and lrlc="
													+ tempLand[0].replace("_P_false", "").replace("_P_true", "").replace("_F_false", "").replace("_F_true", "");
										}
										if (covtypeP != null) {
											session.createSQLQuery(covtypeP).executeUpdate();
										}
										if (covtypeF != null) {
											session.createSQLQuery(covtypeF).executeUpdate();
										}

									}
								}

								StringBuffer sb2 = new StringBuffer();
								String stringFinal = "";
								String temp = "";
								List list = Arrays.asList(strArray1Temp);
								if (strArraySubDistIPTemp != null) {
									for (int m = 0; m < strArraySubDistIPTemp.length; m++) {
										if (strArraySubDistIPTemp.length < strArray1Temp.length) {
											temp = strArraySubDistIPTemp[m] + "_PART_T";
											if (list.contains(temp)) {
												sb2.append(temp);
												sb2.append(",");
											}
										}
										if (strArray1Temp.length < strArraySubDistIPTemp.length) {
											temp = strArraySubDistIPTemp[m] + "_PART_T";
											if (list.contains(temp)) {
												sb2.append(temp);
												sb2.append(",");
											}
										} else if (strArraySubDistIPTemp.length == strArray1Temp.length) {
											temp = strArraySubDistIPTemp[m] + "_PART_T";
											if (list.contains(temp)) {
												sb2.append(temp);
												sb2.append(",");
											}
										}

									}

									String sb2Temp = sb2.toString();
									if (!sb2Temp.equals("")) {
										if (strArraySubDistIPTemp.length <= strArray1Temp.length) {
											for (int m = strArraySubDistIPTemp.length; m < strArray1Temp.length; m++) {
												sb2.append(temp);
												sb2.append(",");
											}
										}

										if (strArray1Temp.length <= strArraySubDistIPTemp.length) {
											for (int m = strArray1Temp.length; m < strArraySubDistIPTemp.length; m++) {
												sb2.append(temp);
												sb2.append(",");
											}
										}
									}
									stringFinal = sb2.toString();
									strArraySubDistIP = stringFinal.split("[_,]");
								}
								String del = "update Localbody set lb_covered_region_code=" + lb_covered_region_code + " where local_body_code=" + localBodyCode + " and isactive=true ";
								session.createSQLQuery(del).executeUpdate();
								if (!stringFinal.equals("")) {
									if (!strArray1[l].toString().equals(strArraySubDistIP[l].toString())) {
										session.save(lbCoveredLandregion[i]);
									}
								} else {
									session.save(lbCoveredLandregion[i]);
								}
								session.createSQLQuery(updateheadquarter).executeUpdate();
								session.createSQLQuery(updateheadquarter1).executeUpdate();
								l = l + 3;
							}
						}
					} else {

						if (maxlbcode == 0 || maxlbcode == null) {
							maxlbcode1 = getmaxlandregioncode();
						} else {
							maxlbcode1 = maxlbcode;
						}
						Integer lb_covered_region_code = maxlbcode1;

						if (localGovtBodyForm.getHeadQuarterCode() != null) {
							headquarArr = localGovtBodyForm.getHeadQuarterCode().split("_");
							headQuratlblc = headquarArr[0];
							/*
							 * if(headQuratlblc.equals(landregionlistfor1.toString
							 * ())) {
							 * lbCoveredLandregion[i].setIsmainregion(true); }
							 * else {
							 * lbCoveredLandregion[i].setIsmainregion(false); }
							 */
							updateheadquarter = "update lb_covered_landregion set ismainregion=true where lb_covered_region_code= " + lb_covered_region_code + " and lrlc=" + headQuratlblc;
							updateheadquarter1 = "update lb_covered_landregion set ismainregion=false where lb_covered_region_code= " + lb_covered_region_code + " and lrlc !=" + headQuratlblc;
						}

						if (!localGovtBodyForm.getLandregiondetails().equals("null") && !localGovtBodyForm.getLandregiondetails().equals("")) {

							String tempLandRegion[] = localGovtBodyForm.getLandregiondetails().split("~");

							for (int r = 0; r < tempLandRegion.length; r++) {
								tempLand = tempLandRegion[r].split(",");
								if (tempLand[1].equals("true")) {
									covtypeP = "update lb_covered_landregion set coverage_type='P' where lb_covered_region_code= " + lb_covered_region_code + " and lrlc="
											+ tempLand[0].replace("_P_false", "").replace("_P_true", "").replace("_F_false", "").replace("_F_true", "");
								} else if (tempLand[1].equals("false")) {
									covtypeF = "update lb_covered_landregion set coverage_type='F' where lb_covered_region_code= " + lb_covered_region_code + " and lrlc="
											+ tempLand[0].replace("_P_false", "").replace("_P_true", "").replace("_F_false", "").replace("_F_true", "");
								}
								if (covtypeP != null) {
									session.createSQLQuery(covtypeP).executeUpdate();
								}
								if (covtypeF != null) {
									session.createSQLQuery(covtypeF).executeUpdate();
								}

							}
						}

						String del = "update Localbody set lb_covered_region_code=" + lb_covered_region_code + " where local_body_code=" + localBodyCode + " and isactive=true ";
						session.createSQLQuery(del).executeUpdate();
						session.createSQLQuery(updateheadquarter).executeUpdate();
						session.createSQLQuery(updateheadquarter1).executeUpdate();
					}

					if (lgd_LBVillageDestLatICAUmapped != null) {
						//String typeCode2 = null;
						String type2 = null;
						String lgd_LBVillageAvailIP = null;
						String strArrayVillageIP[] = null;

						String strArray2[] = lgd_LBVillageDestLatICAUmapped.split("[_,]");
						String strArray2Temp[] = lgd_LBVillageDestLatICAUmapped.split(",");
						String strArrayVillageIPTemp[] = null;

						if (localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped() != null) {
							lgd_LBVillageAvailIP = localGovtBodyForm.getAvaillgd_LBVillageSourceLatICAUmapped().replaceAll("_P_true", "").replaceAll("_P_false", "").replaceAll("_F_true", "").replaceAll("_F_false", "");
						}
						// String strArrayVillageIP[]=
						// lgd_LBVillageAvailIP.split(",");

						if (lgd_LBVillageAvailIP != null) {
							strArrayVillageIPTemp = lgd_LBVillageAvailIP.split(",");
						}

						int tmpLen2 = strArray2.length / 3;

						//String landregionlist2 = null;
						//landregionlist2 = typeCode2;

						Integer landregionlistfor2;
						//int tmp1 = 0;

						

						synchronized (this) {

							for (int i = 0; i < tmpLen2; i++) {
								landregionlistfor2 = Integer.parseInt(strArray2[j].toString());
								tmpLevel = strArray2[j + 2];
								query = session.getNamedQuery("getEntityVersion").setParameter("entityType", "L", Hibernate.STRING).setParameter("entityLevel", tmpLevel, Hibernate.STRING)
										.setParameter("entityCode", landregionlistfor2, Hibernate.INTEGER);
								entityVersion = query.list();
								int tmpVersion = entityVersion.get(0).getEntity_version();

								lbCoveredLandregion[i] = new LbCoveredLandregion();
								lbCoveredLandregion[i].setLandRegionCode(landregionlistfor2);
								//tmp1++;
								type2 = strArray2[j + 1];
								//tmp1++;
								lbCoveredLandregion[i].setLandRegionType(strArray2[j + 2].charAt(0));
								//tmp1++;
								lbCoveredLandregion[i].setIsmainregion(true);
								lbCoveredLandregion[i].setIsactive(true);
								if (type2.equalsIgnoreCase("PART")) {
									lbCoveredLandregion[i].setCoverageType('P');
								} else if (type2.equalsIgnoreCase("FULL")) {
									lbCoveredLandregion[i].setCoverageType('F');
								}

								lbCoveredLandregion[i].setLbCoveredRegionCode(maxlbcode1);
								lbCoveredLandregion[i].setLandRegionVersion(tmpVersion);
								Integer lb_covered_region_code = maxlbcode1;

								if (localGovtBodyForm.getHeadQuarterCode() != null) {
									headquarArr = localGovtBodyForm.getHeadQuarterCode().split("_");
									headQuratlblc = headquarArr[0];
									if (headQuratlblc.equals(landregionlistfor2.toString())) {
										lbCoveredLandregion[i].setIsmainregion(true);
									} else {
										lbCoveredLandregion[i].setIsmainregion(false);
									}
									updateheadquarter = "update lb_covered_landregion set ismainregion=true where lb_covered_region_code= " + lb_covered_region_code + " and lrlc=" + headQuratlblc;
									updateheadquarter1 = "update lb_covered_landregion set ismainregion=false where lb_covered_region_code= " + lb_covered_region_code + " and lrlc !=" + headQuratlblc;
								}

								StringBuffer sb2 = new StringBuffer();
								String temp = "";
								String stringFinal = "";
								List list = Arrays.asList(strArray2Temp);

								if (strArrayVillageIPTemp != null) {

									for (int m = 0; m < strArrayVillageIPTemp.length; m++) {
										if (strArrayVillageIPTemp.length < strArray2Temp.length) {
											temp = strArrayVillageIPTemp[m] + "_PART_V";
											if (list.contains(temp)) {
												sb2.append(temp);
												sb2.append(",");
											}
										} else if (strArray2Temp.length < strArrayVillageIPTemp.length) {
											temp = strArrayVillageIPTemp[m] + "_PART_V";
											if (list.contains(temp)) {
												sb2.append(temp);
												sb2.append(",");
											}
										} else if (strArrayVillageIPTemp.length == strArray2Temp.length) {
											temp = strArrayVillageIPTemp[m] + "_PART_V";
											if (list.contains(temp)) {
												sb2.append(temp);
												sb2.append(",");
											}
										}
									}

									String sb2Temp = sb2.toString();
									if (!sb2Temp.equals("")) {
										if (strArrayVillageIPTemp.length <= strArray2Temp.length) {
											for (int m = strArrayVillageIPTemp.length; m < strArray2Temp.length; m++) {
												sb2.append(temp);
												sb2.append(",");
											}
										}

										if (strArray2Temp.length <= strArrayVillageIPTemp.length) {
											for (int m = strArray2Temp.length; m < strArrayVillageIPTemp.length; m++) {
												sb2.append(temp);
												sb2.append(",");
											}
										}
									}

									stringFinal = sb2.toString();
									strArrayVillageIP = stringFinal.split("[_,]");
								}

								String del = "update Localbody set lb_covered_region_code=" + lb_covered_region_code + " where local_body_code=" + localBodyCode + " and isactive=true ";
								session.createSQLQuery(del).executeUpdate();

								if (!stringFinal.equals("")) {
									if (!strArray2[j].toString().equals(strArrayVillageIP[j].toString())) {
										session.save(lbCoveredLandregion[i]);
									}
								} else {
									session.save(lbCoveredLandregion[i]);
								}

								session.createSQLQuery(updateheadquarter).executeUpdate();
								session.createSQLQuery(updateheadquarter1).executeUpdate();
								j = j + 3;
							}
						}
					}
					tx.commit();
				}
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
			if (tx != null) {
				tx.rollback();
			}
			returnSuccess = false;

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
			if (session1 != null && session1.isOpen()) {
				session1.close();
			}

		}
		// return localBodyCode;
		return returnSuccess;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChangeLocalBodyUrbanType> modifylocalbodyurbantype(LocalGovtBodyForm localGovtBodyForm, HttpServletRequest request, Integer stateCode,Integer userId) {
		Session session = null;
		Transaction tx = null;
		Query query = null;
		//Integer retValue = null;
		List<LocalBodyDetails> localBodyDetails = localGovtBodyForm.getLocalBodyDetails();
		List<ChangeLocalBodyUrbanType> comboList = new ArrayList<ChangeLocalBodyUrbanType>();
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			int localBodyCode = localGovtBodyForm.getLocalBodyCode();

			String localBodyNameEnglish = localBodyDetails.get(0).getLocalBodyNameEnglish();
			int localbodytypecode = Integer.parseInt(localGovtBodyForm.getLocalBodyTypeCode());
			Date lBeffectiveDate = localGovtBodyForm.getLgd_LBeffectiveDate();
			Integer createdby = new Integer(userId);
			String orderNo = localGovtBodyForm.getLgd_LBorderNo();
			Date orderDate = localGovtBodyForm.getLgd_LBorderDate();
			Date gazPubDate = localGovtBodyForm.getLgd_LBgazPubDate();
			String description = "Urban Local body type has been changed from " + localGovtBodyForm.getOldlocalbodyTypeNamehidden() + " to " + localGovtBodyForm.getNewlocalbodyTypeNamehidden();
			query = session.getNamedQuery("getchangelocalbodyurbantype").setParameter("local_body_code", localBodyCode, Hibernate.INTEGER).setParameter("local_body_name_english", localBodyNameEnglish, Hibernate.STRING)
					.setParameter("description", description, Hibernate.STRING).setParameter("local_body_type_code", localbodytypecode, Hibernate.INTEGER).setParameter("local_body_subtype_code", null, Hibernate.INTEGER)
					.setParameter("parent_local_body_code", 0, Hibernate.INTEGER).setParameter("state_code", stateCode, Hibernate.INTEGER).setParameter("effective_date", lBeffectiveDate, Hibernate.DATE)
					.setParameter("createdby", createdby, Hibernate.INTEGER).setParameter("order_no", orderNo, Hibernate.STRING).setParameter("order_date", orderDate, Hibernate.DATE).setParameter("gaz_pub_date", gazPubDate, Hibernate.DATE)
					.setParameter("order_path", null, Hibernate.STRING);

			comboList = query.list();
			// retValue=comboList.get(0).getChange_type_for_local_body_fn();

			tx.commit();
			// return retValue;
		} catch (Exception e) {
			log.debug("Exception" + e);
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return comboList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChangeLocalBodypriType> modifylocalbodypritype(LocalGovtBodyForm localGovtBodyForm, HttpServletRequest request,Integer userId) {
		Session session = null;
		Transaction tx = null;
		Query query = null;
		//Integer retValue = null;
		//List<LocalBodyDetails> localBodyDetails = localGovtBodyForm.getLocalBodyDetails();
		List<ChangeLocalBodypriType> comboList = new ArrayList<ChangeLocalBodypriType>();
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			int localBodyCode = localGovtBodyForm.getLocalBodyCode();
			String parentlblc = localGovtBodyForm.getLocalBodyNameEnglishList();
			int parent_lblc = Integer.parseInt(parentlblc);
			
			String orderNo = localGovtBodyForm.getLgd_LBorderNo();
			Date orderDate = localGovtBodyForm.getLgd_LBorderDate();
			Date gazPubDate = localGovtBodyForm.getLgd_LBgazPubDate();
			Date lBeffectiveDate = localGovtBodyForm.getLgd_LBeffectiveDate();

			query = session.getNamedQuery("getchangelocalbodypritype").setParameter("local_body_code", localBodyCode, Hibernate.INTEGER).setParameter("parent_lblc", parent_lblc, Hibernate.INTEGER).setParameter("user_id", userId, Hibernate.INTEGER)
					.setParameter("local_body_version", null, Hibernate.INTEGER).setParameter("order_no", orderNo, Hibernate.STRING).setParameter("order_date", orderDate, Hibernate.DATE)
					.setParameter("effective_date", lBeffectiveDate, Hibernate.DATE).setParameter("gaz_pub_date", gazPubDate, Hibernate.DATE).setParameter("order_path", null, Hibernate.STRING);

			comboList = query.list();

			tx.commit();
		} catch (Exception e) {
			log.debug("Exception" + e);
			if (tx != null) {
				tx.rollback();
			}
			throw new HibernateException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return comboList;
	}

	@Override
	public boolean saveLocalBodyWard(List<LocalBodyDetails> lbDetails, LocalGovtBodyForm localGovtBodyForm, Session session, HttpServletRequest request, Integer local_body_code, String coverageList, int userid) {
		// SessionObject sessionObject = (SessionObject)
		// request.getSession().getAttribute("sessionObject");
		boolean retValue = true;
		Query query1 = null;
		try {
			/*
			 * Long userId = sessionObject.getUserId(); Integer userIntId =
			 * Integer.valueOf(userId.intValue());
			 */
			/* Modified changed by kirandeep on 05/08/2014 */

			/* Modified by kirandeep on 01/09/2014 for ward */
			/*
			 * Query query = session .getNamedQuery("InsertWardQuery")
			 * .setParameter("local_body_code", local_body_code,
			 * Hibernate.INTEGER) .setParameter("userID", userid,
			 * Hibernate.INTEGER) .setParameter("ward_name_english",
			 * localGovtBodyForm.getWard_Name(), Hibernate.STRING)
			 * .setParameter("ward_number", localGovtBodyForm.getWard_number(),
			 * Hibernate.STRING)
			 * 
			 * .setParameter("ward_name_local",
			 * localGovtBodyForm.getWard_NameLocal(), Hibernate.STRING);
			 * query.list();
			 */

			session.beginTransaction();
			// LocalbodyWard warddeta = ward.get(0);
			query1 = (SQLQuery) session.createSQLQuery("select b.lblc from localbody  b where b.isactive =true and b.local_body_code=" + local_body_code);

			int lblc = (Integer) query1.uniqueResult();

			//SimpleDateFormat date = new SimpleDateFormat();

			Date todaydate = new Date();

			localbodywardtemp templ = new localbodywardtemp();

			templ.setWardCode(0);
			templ.setWardVersion(0);
			templ.setWardNameEnglish(localGovtBodyForm.getWard_Name());
			templ.setWardNameLocal(localGovtBodyForm.getWard_NameLocal());
			templ.setLblc(lblc);
			templ.setWardNumber(localGovtBodyForm.getWard_number());
			templ.setMapCode(0);

			templ.setEffectiveDate(todaydate);
			templ.setLastupdated(todaydate);
			templ.setLastupdatedby(Long.valueOf(userid));
			templ.setCreatedon(todaydate);
			templ.setCreatedby(userid);
			templ.setIsactive(true);

			templ.setIsdelete(false);
			templ.setIsupdate(false);
			templ.setIsnew(true);

			session.save(templ);
			session.getTransaction().commit();
			// session.flush();

		} catch (Exception e) {
			log.debug("Exception" + e);
			retValue = false;
		}
		return retValue;

	}

	@Override
	public boolean saveDataInMapLocalBody(LocalGovtBodyForm localGovtBodyForm, List<AttachedFilesForm> attachedList, HttpSession session) {
		Transaction tx1 = null;
		Session sessionObj = sessionFactory.openSession();
		Query query = null;
		tx1 = sessionObj.beginTransaction();
		MapAttachment attachment = null;
		int localbodyid = 0;
		boolean flag = true;
		try {
			if (attachedList != null) {
				Iterator<AttachedFilesForm> it = attachedList.iterator();
				while (it.hasNext()) {
					AttachedFilesForm filesForm = (AttachedFilesForm) it.next();
					attachment = new MapAttachment();
					attachment.setFileName(filesForm.getFileName());
					attachment.setFileSize(filesForm.getFileSize());
					attachment.setFileContentType(filesForm.getFileType());
					attachment.setFileLocation(filesForm.getFileLocation());
					attachment.setFileTimestamp(filesForm.getFileTimestampName());
					attachment.setMapEntityCode(localGovtBodyForm.getLocalBodyCode());
					attachment.setMapEntityType('G');
					sessionObj.save(attachment);

					int attachmentid = (int) attachment.getAttachmentId();
					localbodyid = localGovtBodyForm.getLocalBodyCode();
					query = sessionObj.createSQLQuery("UPDATE Localbody set map_attachment_code=" + attachmentid + "where local_body_code=" + localbodyid);
					query.executeUpdate();
					tx1.commit();

				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("Exception" + e);
			tx1.rollback();
			flag = false;
		} finally {

			if (sessionObj != null && sessionObj.isOpen()) {
				sessionObj.clear();
				sessionObj.close();
			}
		}
		return flag;
	}

	@Override
	public boolean saveDataInAttach(GovernmentOrderForm governmentOrderForm, LocalGovtBodyForm localGovtBodyForm, List<AttachedFilesForm> attachedList, HttpSession session) {
		Transaction tx1 = null;
		Session sessionObj = sessionFactory.openSession();
		tx1 = sessionObj.beginTransaction();
		Query query = null;
		Attachment attachment = null;
		GovernmentOrderEntityWise goe = null;
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

					query = sessionObj.createQuery("from GovernmentOrderEntityWise where entityCode=:Code and entityVersion=:ver and entityType=:type").setParameter("Code", localGovtBodyForm.getLocalBodyCode(), Hibernate.INTEGER)
							.setParameter("ver", 1, Hibernate.INTEGER).setParameter("type", 'G', Hibernate.CHARACTER);
					goe = (GovernmentOrderEntityWise) query.list().get(0);
					GovernmentOrder govorder = new GovernmentOrder();
					if (goe != null) {
						govorder.setOrderCode(goe.getOrderCode());
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
			flag = false;
		} finally {

			if (sessionObj != null && sessionObj.isOpen()) {
				sessionObj.clear();
				sessionObj.close();
			}
		}
		return flag;
	}

	@Override
	public boolean saveDataInAttachCoverageLBody(List<AttachedFilesForm> attachedList, HttpSession session, int getordercode) {
		Transaction tx1 = null;
		Session sessionObj = sessionFactory.openSession();
		tx1 = sessionObj.beginTransaction();
		//Query query = null;
		Attachment attachment = null;
		//GovernmentOrderEntityWise goe = null;
		//ShiftVillageSD orderno = null;
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

					GovernmentOrder govorder = new GovernmentOrder();
					if (getordercode != 0) {
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
			flag = false;
		} finally {

			if (sessionObj != null && sessionObj.isOpen()) {
				sessionObj.clear();
				sessionObj.close();
			}
		}
		return flag;
	}

	@Override
	public boolean saveDataInAttachGenerateLocalBodyUrbanTypeModify(LocalGovtBodyForm localGovtBodyForm, HttpSession session, int getordercode) {
		Transaction tx1 = null;
		Session sessionObj = sessionFactory.openSession();
		tx1 = sessionObj.beginTransaction();
		//Query query = null;
		boolean flag = true;
		Attachment attachment = null;
		//GovernmentOrderEntityWise goe = null;
		//ShiftVillageSD orderno = null;
		GenerateDetails genDetails = (GenerateDetails) session.getAttribute("validGovermentGenerateUpload");
		try {
			if (genDetails != null) {
				attachment = new Attachment();
				attachment.setFileName(genDetails.getFileName());
				attachment.setFileLocation(genDetails.getFileLocation());
				attachment.setFileTitle("");
				attachment.setFileTimestamp(genDetails.getFileTimestamp());
				attachment.setFileContentType(genDetails.getFileContentType());
				attachment.setFileSize(genDetails.getFileSize());
				GovernmentOrder govorder = new GovernmentOrder();
				if (getordercode != 0) {
					govorder.setOrderCode(getordercode);
					attachment.setGovernmentOrder(govorder);
				}

				sessionObj.save(attachment);
				tx1.commit();

			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			tx1.rollback();
			flag = false;
		} finally {
			if (sessionObj != null && sessionObj.isOpen()) {
				sessionObj.clear();
				sessionObj.close();
			}
		}
		return flag;
	}

	@Override
	public boolean saveDataInAttachGenerateLocalBody(GovernmentOrderForm governmentOrderForm, LocalGovtBodyForm localGovtBodyForm, HttpSession session) {
		Transaction tx1 = null;
		Session sessionObj = sessionFactory.openSession();
		tx1 = sessionObj.beginTransaction();
		Query query = null;
		boolean flag = true;
		Attachment attachment = null;
		GovernmentOrderEntityWise goe = null;
		//ShiftVillageSD orderno = null;
		GenerateDetails genDetails = (GenerateDetails) session.getAttribute("validGovermentGenerateUpload");
		try {
			if (genDetails != null) {
				attachment = new Attachment();
				attachment.setFileName(genDetails.getFileName());
				attachment.setFileLocation(genDetails.getFileLocation());
				attachment.setFileTitle("");
				attachment.setFileTimestamp(genDetails.getFileTimestamp());
				attachment.setFileContentType(genDetails.getFileContentType());
				attachment.setFileSize(genDetails.getFileSize());
				// attachment.setFileSize();
				query = sessionObj.createQuery("from GovernmentOrderEntityWise where entityCode=:Code and entityVersion=:ver and entityType=:type").setParameter("Code", localGovtBodyForm.getLocalBodyCode(), Hibernate.INTEGER)
						.setParameter("ver", 1, Hibernate.INTEGER).setParameter("type", 'G', Hibernate.CHARACTER);
				goe = (GovernmentOrderEntityWise) query.list().get(0);
				GovernmentOrder govorder = new GovernmentOrder();
				if (goe != null) {
					govorder.setOrderCode(goe.getOrderCode());
					attachment.setGovernmentOrder(govorder);
				}
				sessionObj.save(attachment);
				tx1.commit();

			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			tx1.rollback();
			flag = false;
		} finally {
			if (sessionObj != null && sessionObj.isOpen()) {
				sessionObj.clear();
				sessionObj.close();
			}
		}
		return flag;
	}

	@SuppressWarnings("rawtypes")
	public List<Localbody> getLocalGovtBodyMappedVillageList(int landRegionCode, int lbtypecode) throws Exception {
		Session session = null;
		Query query = null;
		List<Localbody> lbList = new ArrayList<Localbody>();
		try {
			session = sessionFactory.openSession();
			query = session
					.createSQLQuery("select local_body_code, local_body_name_english from localbody where lb_covered_region_code in(select lb_covered_region_code from lb_covered_landregion where lrlc=:ldCode and isactive) and isactive and local_body_type_code=:lbtypecode");
			query.setParameter("ldCode", landRegionCode);
			query.setParameter("lbtypecode", lbtypecode);

			ArrayList arrayList = (ArrayList) query.list();
			Iterator iterator = arrayList.iterator();
			while (iterator.hasNext()) {
				Object[] object = (Object[]) iterator.next();
				Integer lbcode = (Integer) object[0];
				String lbname = (String) object[1];
				Localbody lbody = new Localbody();
				lbody.setLocalBodyCode(lbcode);
				lbody.setLocalBodyNameEnglish(lbname);
				lbList.add(lbody);
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lbList;
	}

	/**
	 * Modified by Sushil on 16th Jan 2013
	 */
	@Override
	public List<LocalGovtBody> viewLocalbodyDetails(Integer localBodyCode) {

		Session session = null;
		List<LocalGovtBody> localbodyList = null;
		try {
			Query query;
			session = sessionFactory.openSession();
			LocalGovtBody localGovtBody = (LocalGovtBody) session.createQuery("from LocalGovtBody A where A.isactive=true and A.localBodyCode=:localBodyCode").setParameter("localBodyCode", localBodyCode, Hibernate.INTEGER).uniqueResult();
			if (localGovtBody != null && localGovtBody.getMapAttachmentCode() != null) {
				MapAttachment mapAttachment = (MapAttachment) session.get(MapAttachment.class, localGovtBody.getMapAttachmentCode().longValue());
				if (mapAttachment != null) {
					localGovtBody.setMapFileName(mapAttachment.getFileName());
					localGovtBody.setMapFileLocation(mapAttachment.getFileLocation());
					localGovtBody.setMapEntityType(mapAttachment.getMapEntityType() + "");
				}
			}

			query = session.getNamedQuery("getLocalBodyGODetail");
			query.setParameter("lbCode", localBodyCode, Hibernate.INTEGER);

			@SuppressWarnings("unchecked")
			List<LocalGovtBodyDTO> list = query.list();
			if (!list.isEmpty()) {
				LocalGovtBodyDTO attachment = (LocalGovtBodyDTO) list.get(0);
				if (attachment != null) {
					localGovtBody.setGovtOrderFileName(attachment.getGovtOrderFileName());
					localGovtBody.setGovtOrderFileLocation(attachment.getGovtOrderFileLocation());
					localGovtBody.setOrderNo(attachment.getOrderNo());
					localGovtBody.setOrderCode(attachment.getOrderCode());
					localGovtBody.setOrderDate(attachment.getOrderDate());
					localGovtBody.setGovOrderEffectiveDate(attachment.getGovOrderEffectiveDate());
					localGovtBody.setGazPubDate(attachment.getGazPubDate());
				}
			}
			localbodyList = new ArrayList<LocalGovtBody>();
			localbodyList.add(localGovtBody);
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return localbodyList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ViewLocalBodyLandRegion> viewLandRegionDistrictName(int localBodyCode) {

		Session session = null;
		List<ViewLocalBodyLandRegion> localbodyList = null;
		try {
			Query query;
			session = sessionFactory.openSession();

			query = session.getNamedQuery("getDistrictNameListforcoveredarea");
			query.setInteger("localBodyCode", localBodyCode);

			localbodyList = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);

		}

		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return localbodyList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ViewLandRegionDisturbedlist> viewGpdisturbedlist(int localBodyCode) {

		Session session = null;
		List<ViewLandRegionDisturbedlist> gpdisturbedlist = null;
		try {
			Query query;
			session = sessionFactory.openSession();

			query = session.getNamedQuery("getlocalbodydistibedlist");
			query.setInteger("localBodyCode", localBodyCode);

			gpdisturbedlist = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);

		}

		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return gpdisturbedlist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ViewLocalBodyLandRegion> viewCoveragearea(int localBodyCode) {

		Session session = null;
		List<ViewLocalBodyLandRegion> localbodyList = null;
		try {
			Query query;
			session = sessionFactory.openSession();

			query = session.getNamedQuery("getListforcoveredarea");
			query.setInteger("localBodyCode", localBodyCode);

			localbodyList = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);

		}

		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return localbodyList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GovernmentOrder> GovtOrederDetails() {
		Session session = null;
		List<GovernmentOrder> govtOrderList = null;

		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(GovernmentOrder.class);
			// criteria.add(Restrictions.eq("orderCode", orderCode));
			govtOrderList = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return govtOrderList;

	}

	@SuppressWarnings("unchecked")
	@Override
	public GovernmentOrder viewgovernmetorderDetails(Integer orderCode) {

		Session session = null;
		GovernmentOrder governmentOrder = null;
		@SuppressWarnings("unused")
		List<GovernmentOrder> governmentOrderList = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(GovernmentOrder.class);
			criteria.add(Restrictions.eq("orderCode", orderCode));
			governmentOrderList = criteria.list();
			// governmentOrder=governmentOrderList.get(0);

		} catch (Exception e) {
			log.debug("Exception" + e);

		}

		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return governmentOrder;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GetLandRegionWise> getLandRegionWise(Integer localbodytypecode, char code, Integer districtcode, String lbtype) {
		Session session = null;
		List<GetLandRegionWise> localbodyList = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			if (lbtype.equals("P")) {
				query = session.getNamedQuery("landregionwise");
				query.setCharacter("local_body_type", 'D');
				query.setInteger("district_id", districtcode);
				query.setInteger("local_body_type_code", 1);
				localbodyList = query.list();
			}
			if (lbtype.equals("T")) {
				int localBodyCd = 0;
				if (code == 'D' || code == 'I' || code == 'V') {
					localBodyCd = 9;
				}

				query = session.getNamedQuery("landregionwise");
				query.setCharacter("local_body_type", 'D');
				query.setInteger("district_id", districtcode);
				query.setInteger("local_body_type_code", localBodyCd);
				localbodyList = query.list();
			}
		} catch (Exception e) {
			log.debug("Exception" + e);

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return localbodyList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UlbBean> getLandRegionWiseUrban(Integer localbodytypecode, Integer districtcode) {
		Session session = null;
		List<UlbBean> localbodyList = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getUlbListByDistrictCodeFn");
			query.setInteger("districtCode", districtcode);
			query.setInteger("localbodytypecode", localbodytypecode);
			localbodyList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return localbodyList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UlbBean> getPanchayatListbylocalbodyUrban(Integer districtcode, Integer localbodytypecode, int offset, int limit) {
		Session session = null;
		List<UlbBean> localbodyList = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getUlbListByDistrictCodeFn");
			query.setInteger("districtCode", districtcode);
			query.setInteger("localbodytypecode", localbodytypecode);
			query.setFirstResult(offset);
			query.setMaxResults(limit);
			localbodyList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return localbodyList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GetLandRegionWise> getLandRegionWise(Character localbodytypecode, int districtcode, String lbtype) {
		Session session = null;
		List<GetLandRegionWise> localbodyList = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();

			query = session.getNamedQuery("landregionwise");
			query.setCharacter("local_body_type", localbodytypecode);
			query.setInteger("district_id", districtcode);
			query.setInteger("local_body_type_code", 1);
			localbodyList = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return localbodyList;
	}

	/*
	 * @Override public List<GetLandRegionWise> getLandRegionWiseInter(Integer
	 * localbodytypecode,Integer districtcode) { Session session = null;
	 * List<GetLandRegionWise> localbodyList=null; Query query=null; try {
	 * session = sessionFactory.openSession(); query =
	 * session.getNamedQuery("landregionwise");
	 * query.setCharacter("local_body_type",'T');
	 * query.setInteger("district_id", districtcode);
	 * query.setInteger("local_body_type_code", localbodytypecode);
	 * localbodyList = query.list(); } catch (Exception e) {
	 * log.debug("Exception" + e);
	 * 
	 * } finally { if (session != null && session.isOpen()) { session.close(); }
	 * } return localbodyList; }
	 * 
	 * @Override public List<GetLandRegionWise> getLandRegionWiseVill(Integer
	 * localbodytypecode,Integer districtcode) { Session session = null;
	 * List<GetLandRegionWise> localbodyList=null; Query query=null; try {
	 * session = sessionFactory.openSession(); query =
	 * session.getNamedQuery("landregionwise");
	 * query.setCharacter("local_body_type",'V');
	 * query.setInteger("district_id", districtcode);
	 * query.setInteger("local_body_type_code", localbodytypecode);
	 * localbodyList = query.list(); } catch (Exception e) {
	 * log.debug("Exception" + e);
	 * 
	 * } finally { if (session != null && session.isOpen()) { session.close(); }
	 * } return localbodyList; }
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<ViewLocalBodyLandRegion> viewLandRegionsubDistrictName(int localBodyCode) {
		Session session = null;
		List<ViewLocalBodyLandRegion> subdisticList = null;
		try {
			Query query;
			session = sessionFactory.openSession();

			query = session.getNamedQuery("getSubdisticnameListforcoveredarea");
			query.setInteger("localBodyCode", localBodyCode);

			subdisticList = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);

		}

		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return subdisticList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ViewLocalBodyLandRegion> viewLandRegionvillageName(int localBodyCode) {
		Session session = null;
		List<ViewLocalBodyLandRegion> villageList = null;
		try {
			Query query;
			session = sessionFactory.openSession();

			query = session.getNamedQuery("getVillageNameListforcoveredarea");
			query.setInteger("localBodyCode", localBodyCode);

			villageList = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);

		}

		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return villageList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ViewLocalBodyLandRegion> viewLandRegionvillageNameF(int localBodyCode) {
		Session session = null;
		List<ViewLocalBodyLandRegion> villageList = null;
		try {
			Query query;
			session = sessionFactory.openSession();

			query = session.getNamedQuery("getVillageNameListforcoveredarea");
			query.setInteger("localBodyCode", localBodyCode);

			villageList = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return villageList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalbodyWard> getWardListFromContributingMunicipality(int localBodyCode) {

		Query query = null;
		Session session = null;
		List<LocalbodyWard> list = null;
		try {
			session = sessionFactory.openSession();
			/*
			 * query = session .createSQLQuery(
			 * "select ward_code, ward_name_english from localbody_ward where lblc=:localBodyCode and isactive=true"
			 * ) .setParameter("localBodyCode", 253415, Hibernate.INTEGER);
			 */
			query = session.createQuery("from LocalbodyWard where lblc=:localBodyCode and isactive=true").setParameter("localBodyCode", localBodyCode, Hibernate.INTEGER);
			list = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);
		}

		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}

		return list;
	}

	// Tanuj
	@SuppressWarnings("unchecked")
	@Override
	public String getCategoryFromLocalBodyTypeCode(int localBodyType, Session session) {

		Query query = null;
		String type = null;
		List<String> getCategory1 = null;
		// END VARIBALE DECLARATION
		try {

			query = session
					.createSQLQuery(
							"select (CASE WHEN category = 'U' THEN 'U' WHEN category = 'R' AND ispartixgovt = TRUE THEN 'P' WHEN category = 'R' AND ispartixgovt = FALSE THEN 'T' END) AS category from local_body_type where local_body_type_code=:localBodyType")
					.setParameter("localBodyType", localBodyType, Hibernate.INTEGER);
			getCategory1 = query.list();
			type = getCategory1.get(0);
			return type;

		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return type;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalbodyforStateWiseFinal> getLocalBodyListStateCategoryWise(Integer localBodyType, Integer stateCode, Integer plblc, Integer lbCode, String lbName) throws Exception {
		Session session = null;
		List<LocalbodyforStateWiseFinal> localbodyList = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalBodylistStateCategoryWiseFinal").setParameter("stateCode", stateCode, Hibernate.INTEGER).setParameter("localBodyType", localBodyType, Hibernate.INTEGER)
					.setParameter("localBodyCode", lbCode, Hibernate.INTEGER).setParameter("parentLblc", plblc, Hibernate.INTEGER).setParameter("localBodyName", lbName, Hibernate.STRING);
			localbodyList = query.list();

		} catch (Exception e) {
			log.error("Exception" + e);
			throw e;
		} finally {

			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return localbodyList;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ParentWiseLBList> getParentWiseLBList(int localBodyCode) throws Exception {

		List<ParentWiseLBList> childlocalbodiesByParentcodeList = null;
		Session session = null;
		Query query = null;
		session = sessionFactory.openSession();
		try {
			query = session.getNamedQuery("getLocalGovtBodyParentWise").setParameter("localBodyCode", localBodyCode);
			childlocalbodiesByParentcodeList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return childlocalbodiesByParentcodeList;
	}

	
	@Override
	public int getLBTypeCodeFromLBCode(int localBodyCode) throws Exception {
		int stateCode = 0;
		Session session = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select local_body_type_code from localbody where local_body_code=:localBodyCode and isactive");
			query.setParameter("localBodyCode", localBodyCode, Hibernate.INTEGER);
			@SuppressWarnings("rawtypes")
			List list = query.list();
			if (!list.isEmpty() && list.get(0) != null) {
				stateCode = (Integer) list.get(0);
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return stateCode;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Localbody> getLocalGovSetupWiseLocalbodyList(int localBodyCode) throws Exception {
		Session session = null;
		List<Localbody> localbodyList = null;
		Query criteria = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Localbody where local_body_code=:localBodyCode and isactive=true");
			criteria.setParameter("localBodyCode", localBodyCode, Hibernate.INTEGER);
			localbodyList = criteria.list();

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {

			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return localbodyList;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Localbody> getLocalGovSetupWiseLocalbodyListByName(String localBodyName) throws Exception {
		Session session = null;
		List<Localbody> localbodyList = null;
		Query criteria = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Localbody where isactive=true AND local_body_name_english like :localBodyName order by local_body_name_english");
			criteria.setParameter("localBodyName", localBodyName + "%", Hibernate.STRING);
			localbodyList = criteria.list();

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {

			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return localbodyList;

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Localbody> getLocalBodyDetailsModify(int localBodyCode) throws Exception {

		Session session = null;
		Query query = null;
		List<Localbody> localBodyList = null;
		try {
			localBodyList = new ArrayList<Localbody>();
			session = sessionFactory.openSession();
			query = session.createQuery("from Localbody where isactive=true and local_body_code=:localBodyCode");
			query.setParameter("localBodyCode", localBodyCode, Hibernate.INTEGER);
			localBodyList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return localBodyList;

	}

	public int getMaxLocalBodyVersionbyCode(int localBodyCode) throws Exception {
		int MaxVersionCode = 0;
		Session session = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select COALESCE(max(local_body_version),1) from Localbody where isactive=true and local_body_code=:localBodyCode");
			query.setParameter("localBodyCode", localBodyCode, Hibernate.INTEGER);
			@SuppressWarnings("rawtypes")
			List list = query.list();
			if (!list.isEmpty() && list.get(0) != null) {
				MaxVersionCode = (Integer) list.get(0);
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return MaxVersionCode;
	}

	/* Retrieve files from the attachment table */

	@SuppressWarnings("unchecked")
	@Override
	public List<Attachment> getAttacmentdetail(int orderCode) throws Exception {
		Session session = null;
		Query query = null;
		List<Attachment> attachmentList = new ArrayList<Attachment>();
		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from Attachment where announcement_id=:orderCode").setParameter("orderCode", orderCode, Hibernate.INTEGER);
			attachmentList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return attachmentList;
	}

	/* Retrieving the Map details attachment from the database */

	@SuppressWarnings("unchecked")
	@Override
	public List<EntityWiseMapDetailsPojo> getEntityWiseMapDetails(char entityType, int entityCode, Session session) throws Exception {
		Query query = null;
		List<EntityWiseMapDetailsPojo> EntityWiseMapDetail = null;
		try {
			query = session.getNamedQuery("getEntityWiseMapDetailsFn").setParameter("entityType", entityType).setParameter("entityCode", entityCode);
			EntityWiseMapDetail = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);

		}
		return EntityWiseMapDetail;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GetLocalGovtBodyTypeList> getLBGTypeList(int stateCode, Session session) {

		Query query = null;
		List<GetLocalGovtBodyTypeList> getLGBTypeList = null;
		// END VARIBALE DECLARATION
		try {

			query = session.getNamedQuery("getlocalgovtbodytypelist").setParameter("stateCode", stateCode);
			getLGBTypeList = query.list();
			return getLGBTypeList;

		} catch (Exception e) {
			log.debug("Exception" + e);
		}
		return getLGBTypeList;
	}

	@SuppressWarnings("unchecked")
	public List<LocalbodyWard> getWardDetailsDAO(int lblc/* ,int offset,int limit */) {
		List<LocalbodyWard> localbodyWardList = new ArrayList<LocalbodyWard>();
		//List<Localbody> lblcData = null;
		localbodyWardList = null;
		Session session = null;
		Query query = null;

		try {

			session = sessionFactory.openSession();

			query = session.createQuery("from LocalbodyWard where lblc=:lblc and isactive=true order by wardNameEnglish ").setParameter("lblc", lblc)
			/*
			 * .setFirstResult(offset) .setMaxResults(limit)
			 */;
			localbodyWardList = query.list();

			/*
			 * q.setFirstResult(10); q.setMaxResults(20);
			 */
		} catch (Exception e) {
			log.debug("Exception" + e);

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return localbodyWardList;

	}

	/**
	 * Changes for Localbody Draft Mode on 20-11-2014 by Ripunj
	 */
	@SuppressWarnings("unchecked")
	public List<Localbody> getPanchayatListbylocalbody(int lbtypeCode, int stateCode, int offset, int limit) {
		List<Localbody> localbodyList = new ArrayList<Localbody>();
		localbodyList = null;
		Session session = null;
		SQLQuery query = null;

		try {
			session = sessionFactory.openSession();
			/*
			 * query = session .createQuery(
			 * "from Localbody where localBodyTypeCode=:lbtypeCode and slc=:stateCode and isactive=true Order by localBodyNameEnglish"
			 * )
			 */

			query = session.createSQLQuery("select case when l.local_body_code  in (select * from get_draft_used_lb_lr_temp(l.local_body_code,'G')) then cast('F' as character) else cast('A' as character) end as operation_state,"
					+ " l.local_body_code as localBodyCode ,l.local_body_name_english as localBodyNameEnglish, l.local_body_name_local as localBodyNameLocal from localbody "
					+ " l where local_body_type_code=:lbtypeCode and slc=:stateCode and isactive=true " + "  Order by local_Body_Name_English");
			query.setParameter("lbtypeCode", lbtypeCode).setParameter("stateCode", stateCode);
			query.setFirstResult(offset).setMaxResults(limit);
			query.addScalar("operation_state").addScalar("localBodyCode").addScalar("localBodyNameEnglish").addScalar("localBodyNameLocal");
			query.setResultTransformer(Transformers.aliasToBean(Localbody.class));
			localbodyList = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return localbodyList;

	}

	/**
	 * Changes for LocalBody Draft Mode on 25-11-2014 by ripunj
	 */
	@SuppressWarnings("unchecked")
	public List<Localbody> getPanchayatListbylocalbodyVillagepanchayat(int lbtypeCode, int stateCode, String parentcode, int offset, int limit) {
		List<Localbody> localbodyList = new ArrayList<Localbody>();
		localbodyList = null;
		Session session = null;
		SQLQuery query = null;
		int parentlblc = this.getLblc(Integer.parseInt(parentcode));

		try {
			session = sessionFactory.openSession();
			/*
			 * query = session .createQuery(
			 * "from Localbody where localBodyTypeCode=:lbtypeCode and slc=:stateCode  and parentlblc=:parentlblc and isactive=true Order by localBodyNameEnglish"
			 * ) .setParameter("lbtypeCode", lbtypeCode)
			 * .setParameter("stateCode", stateCode) .setParameter("parentlblc",
			 * parentlblc) .setFirstResult(offset).setMaxResults(limit);
			 */

			query = session.createSQLQuery("select case when l.local_body_code  in (select * from get_draft_used_lb_lr_temp(l.local_body_code,'G')) then cast('F' as character) else cast('A' as character) end as operation_state,"
					+ " l.local_body_code as localBodyCode ,l.local_body_name_english as localBodyNameEnglish, l.local_body_name_local as localBodyNameLocal from localbody "
					+ " l where local_body_type_code=:lbtypeCode and slc=:stateCode and parent_lblc=:parentlblc and isactive=true " + "  Order by local_Body_Name_English");
			query.setParameter("lbtypeCode", lbtypeCode).setParameter("stateCode", stateCode).setParameter("parentlblc", parentlblc);
			query.setFirstResult(offset).setMaxResults(limit);
			query.addScalar("operation_state").addScalar("localBodyCode").addScalar("localBodyNameEnglish").addScalar("localBodyNameLocal");
			query.setResultTransformer(Transformers.aliasToBean(Localbody.class));
			localbodyList = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return localbodyList;

	}

	@SuppressWarnings("unchecked")
	public List<Localbody> getLocalbodylist(int slc) {
		Session session = null;
		Query query = null;
		List<Localbody> localbodyList = new ArrayList<Localbody>();

		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from Localbody  where isactive = true and slc=:slc").setParameter("slc", slc);
			localbodyList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return localbodyList;

	}

	public int countWardDetailsDAO(int lblc) {
		int counter = 0;
		Session session = null;
		Query query = null;

		try {
			session = sessionFactory.openSession();
			query = session.createQuery("from LocalbodyWard where lblc=:lblc and isactive=true").setParameter("lblc", lblc);

			counter = query.list().size();

			/*
			 * q.setFirstResult(10); q.setMaxResults(20);
			 */
		} catch (Exception e) {
			log.debug("Exception" + e);

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return counter;

	}

	public int countLocalBodyDetails(Integer lbtype, int stateCode) {
		int counter = 0;
		Session session = null;
		Query query = null;

		try {
			session = sessionFactory.openSession();
			query = session.createQuery(" from Localbody where localBodyTypeCode=:lbtype and slc=:stateCode and isactive=true Order by localBodyNameEnglish").setParameter("lbtype", lbtype)

			.setParameter("stateCode", stateCode);

			counter = query.list().size();

		} catch (Exception e) {
			log.debug("Exception" + e);

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return counter;

	}

	public Integer getmaxlandregioncode() {
		int counter = 0;
		Session session = null;
		Query query = null;

		try {
			session = sessionFactory.openSession();
			// query=
			// session.createSQLQuery("select max(lb_covered_region_code) from lb_covered_landregion");
			query = session.createSQLQuery("select nextval('lbcoveredregioncode_seq')");

			counter = Integer.parseInt(query.list().get(0).toString());

		} catch (Exception e) {
			log.debug("Exception" + e);

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return counter;

	}

	public Integer getlbcoverdregioncode(int lbcode) {
		int lbcoveredRegion = 0;
		Session session = null;
		Query query = null;

		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select lb_covered_region_code from localbody where local_body_code=" + lbcode + "and isactive=" + true);

			lbcoveredRegion = Integer.parseInt(query.list().get(0).toString());

		} catch (Exception e) {
			log.debug("Exception" + e);

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lbcoveredRegion;

	}

	public Integer getParentLblccode(int lbcode) {
		int lbcoveredRegion = 0;
		Session session = null;
		Query query = null;

		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select parent_lblc from localbody where local_body_code=" + lbcode + "and isactive=" + true);

			lbcoveredRegion = Integer.parseInt(query.list().get(0).toString());

		} catch (Exception e) {
			log.debug("Exception" + e);

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lbcoveredRegion;
	}

	public int countwardDetails(int lblc) {
		int counter = 0;
		Session session = null;
		Query query = null;

		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalGovtBodyWardListView").setParameter("lblc", lblc);

			counter = query.list().size();

		} catch (Exception e) {
			log.debug("Exception" + e);

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return counter;

	}

	public int countLocalBodyDetailsforVillagePanchayat(Integer lbtype, int stateCode, String intermediatePanchayatCodes) {
		int counter = 0;
		Session session = null;
		Query query = null;
		int parentlblc = Integer.parseInt(intermediatePanchayatCodes);

		try {
			session = sessionFactory.openSession();
			query = session.createQuery(" from Localbody where localBodyTypeCode=:lbtype and slc=:stateCode and parentlblc=:parentlblc and isactive=true Order by localBodyNameEnglish").setParameter("lbtype", lbtype)
					.setParameter("stateCode", stateCode).setParameter("parentlblc", parentlblc);

			counter = query.list().size();

		} catch (Exception e) {
			log.debug("Exception" + e);

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return counter;

	}

	@SuppressWarnings("rawtypes")
	@Override
	public Integer getmaxgovtordercode() {
		int maxcount = 0;
		Session session = null;
		Query query = null;

		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select max(order_code) from government_order");

			List list = query.list();
			if (!list.isEmpty() && list.get(0) != null) {
				maxcount = (Integer) list.get(0);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return maxcount;

	}

	public String getparentLocalbodynamebycode(int parentLBodyCode) {
		Session session = null;
		String parentLocalBodyName = null;
		Query criteria = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Localbody d where d.localBodyCode=:parentLBCode and isactive=true");
			criteria.setParameter("parentLBCode", parentLBodyCode, Hibernate.INTEGER);
			@SuppressWarnings("unchecked")
			List<Localbody> pstateList = criteria.list();
			if (pstateList != null) {
				Iterator<Localbody> pstateListItr = pstateList.iterator();
				parentLocalBodyName = pstateListItr.next().getLocalBodyNameEnglish();
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return parentLocalBodyName;

	}

	@SuppressWarnings("unchecked")
	@Override
	public String invalidatePRIDAO(LocalGovtBodyForm lbForm, Session hsession, int userid, HttpSession httpsession) {
		List<invalidatePRIlb> result = new ArrayList<invalidatePRIlb>();
		Query query = null;
		String orderCode = null;
		String option = null;
		String lbcode = null;
		//String[] lbid = null;
		//int lb = 0;
		//String lbtypecode = null;
		String[] type = null;
		Integer lbtype = null;
		String lbtypename = null;
		String sub = null;
		String lblevel = null;
		int typelevel = 0;
		if (lbForm.getListformat().equals("@")) {
			option = "N";
			lbForm.setListformat(null);
			lbtypename = lbForm.getLgd_LBTypeName();
			if (lbtypename != null) {
				type = lbtypename.split(":");
				if (type[0] != null) {
					lbtype = Integer.parseInt((type[0]));
				}
			}

		} else {
			sub = lbForm.getListformat();
			sub = sub.substring(1, sub.length() - 1);
			lbForm.setListformat(sub);
			option = "Y";
		}

		lblevel = (String) httpsession.getAttribute("lblevel");
		typelevel = Integer.parseInt(lblevel);
		if (typelevel == 1) {
			lbcode = lbForm.getDistrictpanlbid();
			if (option == "Y") {
				lbForm.setFlagCode(50);
			}
		} else if (typelevel == 2) {
			lbcode = lbForm.getIntermediatepanlbid();
			if (option == "Y") {
				lbForm.setFlagCode(50);
			}
		} else if (typelevel == 3) {
			lbcode = lbForm.getGrampanlbid();
			if (option == "Y") {
				lbForm.setFlagCode(51);
			}
		}
		if (lbcode == null) {
			lbcode = lbForm.getInvalidatedlbcode();
		}
		try {

			Integer lbco = Integer.parseInt(lbcode);
			query = hsession.getNamedQuery("invalidate_prilb_fn")

			.setParameter("local_body_code", lbco, Hibernate.INTEGER).setParameter("local_body_name", lbForm.getLocalBodyNameEnglish(), Hibernate.STRING).setParameter("userId", userid, Hibernate.INTEGER)
					.setParameter("shift", option, Hibernate.STRING).setParameter("effectiveDate", lbForm.getEffectiveDate(), Hibernate.DATE).setParameter("govtOrder", lbForm.getOrderNo(), Hibernate.STRING)
					.setParameter("orderDate", lbForm.getOrderDate(), Hibernate.DATE).setParameter("local_body_type_code", lbtype, Hibernate.INTEGER).setParameter("operation_code", lbForm.getOperationCode(), Hibernate.INTEGER)
					.setParameter("flag_code", lbForm.getFlagCode(), Hibernate.INTEGER).setParameter("gzbDate", lbForm.getGazPubDate(), Hibernate.DATE).setParameter("order_path", null, Hibernate.STRING)
					.setParameter("lblist", lbForm.getListformat(), Hibernate.STRING)
					.setParameter("unResolvedFlag",lbForm.isUnResolvedFlag(),Hibernate.BOOLEAN);

			result = query.list();
			if (result.size() > 0) {
				orderCode = result.get(0).getInvalidatePRILB();
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Exception" + e);
			// throw e;
		}

		return orderCode;

	}

	@SuppressWarnings("unchecked")
	@Override
	public String invalidateTRIDAO(LocalGovtBodyForm lbForm, Session hsession, int userid, HttpSession httpsession) {
		List<invalidatePRIlb> result = new ArrayList<invalidatePRIlb>();
		Query query = null;
		String orderCode = null;
		String option = null;
		String lbcode = null;
		//String[] lbid = null;
		//int lb = 0;
		//String lbtypecode = null;
		String[] type = null;
		Integer lbtype = null;
		String lbtypename = null;
		String sub = null;
		String lblevel = null;
		int typelevel = 0;
		if (lbForm.getListformat().equals("@")) {
			option = "N";
			lbForm.setListformat(null);
			lbtypename = lbForm.getLgd_LBTypeName();
			if (lbtypename != null) {
				type = lbtypename.split(":");
				if (type[0] != null) {
					lbtype = Integer.parseInt((type[0]));
				}
			}
		} else {
			sub = lbForm.getListformat();
			sub = sub.substring(1, sub.length() - 1);
			lbForm.setListformat(sub);
			option = "Y";
		}

		lblevel = (String) httpsession.getAttribute("lblevel");
		typelevel = Integer.parseInt(lblevel);
		if (typelevel == 1) {
			lbcode = lbForm.getDistrictpanlbid();
			if (option == "Y") {
				lbForm.setFlagCode(52);
			}
		} else if (typelevel == 2) {
			lbcode = lbForm.getIntermediatepanlbid();
			if (option == "Y") {
				lbForm.setFlagCode(52);
			}
		} else if (typelevel == 3) {
			lbcode = lbForm.getGrampanlbid();
			if (option == "Y") {
				lbForm.setFlagCode(53);
			}
		}
		if (lbcode == null) {
			lbcode = lbForm.getInvalidatedlbcode();
		}
		try {

			Integer lbco = Integer.parseInt(lbcode);
			query = hsession.getNamedQuery("invalidate_prilb_fn")

			.setParameter("local_body_code", lbco, Hibernate.INTEGER).setParameter("local_body_name", lbForm.getLocalBodyNameEnglish(), Hibernate.STRING).setParameter("userId", userid, Hibernate.INTEGER)
					.setParameter("shift", option, Hibernate.STRING).setParameter("effectiveDate", lbForm.getEffectiveDate(), Hibernate.DATE).setParameter("govtOrder", lbForm.getOrderNo(), Hibernate.STRING)
					.setParameter("orderDate", lbForm.getOrderDate(), Hibernate.DATE).setParameter("local_body_type_code", lbtype, Hibernate.INTEGER).setParameter("operation_code", lbForm.getOperationCode(), Hibernate.INTEGER)
					.setParameter("flag_code", lbForm.getFlagCode(), Hibernate.INTEGER).setParameter("gzbDate", lbForm.getGazPubDate(), Hibernate.DATE).setParameter("order_path", null, Hibernate.STRING)
					.setParameter("lblist", lbForm.getListformat(), Hibernate.STRING)
					.setParameter("unResolvedFlag",lbForm.isUnResolvedFlag(),Hibernate.BOOLEAN);


			result = query.list();
			if (result.size() > 0) {
				orderCode = result.get(0).getInvalidatePRILB();
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			// throw e;
		}

		return orderCode;

	}

	/*@Override
	public String invalidateURBDAO(LocalGovtBodyForm lbForm, Session hsession, int userid, HttpSession httpsession) {
		List<invalidatePRIlb> result = new ArrayList<invalidatePRIlb>();
		Query query = null;
		String orderCode = null;
		String option = "N";
		String lbcode = null;
		String[] lbid = null;
		int lb = 0;
		String lbtypecode = null;
		String[] type = null;
		Integer lbtype = null;
		String lbtypename = null;
		String sub = null;
		String lblevel = null;
		int typelevel = 0;
		if (lbForm.getListformat().equals("@")) {
			option = "N";
			lbForm.setListformat(null);
			lbtypename = lbForm.getLgd_LBTypeName();
			if (lbtypename != null) {
				type = lbtypename.split(":");
				if (type[0] != null) {
					lbtype = Integer.parseInt((type[0]));
				}
			}

		}

		lblevel = (String) httpsession.getAttribute("lblevel");
		typelevel = Integer.parseInt(lblevel);
		if (typelevel == 1) {
			lbcode = lbForm.getDistrictpanlbid();
		} else if (typelevel == 2) {
			lbcode = lbForm.getIntermediatepanlbid();
		}
		if (lbcode == null) {
			lbcode = lbForm.getInvalidatedlbcode();
		}
		try {

			Integer lbco = Integer.parseInt(lbcode);
			query = hsession.getNamedQuery("invalidate_prilb_fn")

			.setParameter("local_body_code", lbco, Hibernate.INTEGER).setParameter("local_body_name", lbForm.getLocalBodyNameEnglish(), Hibernate.STRING).setParameter("userId", userid, Hibernate.INTEGER)
					.setParameter("shift", option, Hibernate.STRING).setParameter("effectiveDate", lbForm.getEffectiveDate(), Hibernate.DATE).setParameter("govtOrder", lbForm.getOrderNo(), Hibernate.STRING)
					.setParameter("orderDate", lbForm.getOrderDate(), Hibernate.DATE).setParameter("local_body_type_code", lbtype, Hibernate.INTEGER).setParameter("operation_code", lbForm.getOperationCode(), Hibernate.INTEGER)
					.setParameter("flag_code", lbForm.getFlagCode(), Hibernate.INTEGER).setParameter("gzbDate", lbForm.getGazPubDate(), Hibernate.DATE).setParameter("order_path", null, Hibernate.STRING)
					.setParameter("lblist", lbForm.getListformat(), Hibernate.STRING);

			result = query.list();
			if (result.size() > 0) {
				orderCode = result.get(0).getInvalidatePRILB();
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			// throw e;
		}

		return orderCode;

	}*/

	@Override
	public boolean saveDataInAttachmentGenerate(GovernmentOrderForm governmentOrderForm, LocalGovtBodyForm LBForm, HttpSession session, int getordercode, Session sessionObj) {

		//Query query = null;
		boolean flag = true;
		Attachment attachment = null;
		//GovernmentOrderEntityWise goe = null;
		//ShiftVillageSD orderno = null;
		GenerateDetails genDetails = (GenerateDetails) session.getAttribute("validGovermentGenerateUpload");
		try {
			if (genDetails != null) {
				attachment = new Attachment();
				attachment.setFileName(genDetails.getFileName());
				attachment.setFileLocation(genDetails.getFileLocation());
				attachment.setFileTitle(genDetails.getFileTitle());
				attachment.setFileTimestamp(genDetails.getFileTimestamp());
				attachment.setFileContentType(genDetails.getFileContentType());
				attachment.setFileSize(genDetails.getFileSize());
				GovernmentOrder govorder = new GovernmentOrder();
				if (getordercode != 0) {
					govorder.setOrderCode(getordercode);
					attachment.setGovernmentOrder(govorder);
				}
				sessionObj.save(attachment);

			}
		} catch (Exception e) {

			log.debug("Exception" + e);

			flag = false;
		}

		return flag;
	}

	@Override
	public boolean modifyWardData(LocalbodyWard lbward, HttpServletRequest request, Session session) throws Exception {
		try {

			/* Modified by kirandeep on 01/09/2014 for ward */
			session.beginTransaction();

			localbodywardtemp templ = new localbodywardtemp();

			/*templ.setWardCode(lbward.getId().getWardCode());
			templ.setWardVersion(lbward.getId().getWardVersion());*/
			templ.setWardNameEnglish(lbward.getWardNameEnglish());
			templ.setWardNameLocal(lbward.getWardNameLocal());
			templ.setLblc(lbward.getLblc());
			templ.setWardNumber(lbward.getWardNumber());
			templ.setMapCode(0);

			templ.setEffectiveDate(lbward.getEffectiveDate());
			templ.setLastupdated(lbward.getLastupdated());
			templ.setLastupdatedby(lbward.getLastupdatedby());
			templ.setCreatedon(lbward.getCreatedon());
			templ.setCreatedby(lbward.getCreatedby());
			templ.setIsactive(lbward.isIsactive());

			templ.setIsdelete(false);
			templ.setIsupdate(true);
			templ.setIsnew(false);

			session.save(templ);
			session.getTransaction().commit();

			// session.update(lbward);
			// session.saveOrUpdate(lbward);

		} catch (Exception e) {
			log.debug("Exception" + e);
			return false;
		}
		return true;
	}

	public String getLocalBodyName(int lblc) throws Exception {
		String name = null;
		Session session = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select local_body_name_english from localbody where isactive=true and lblc=:lblc");
			query.setParameter("lblc", lblc, Hibernate.INTEGER);
			@SuppressWarnings("rawtypes")
			List list = query.list();
			if (!list.isEmpty() && list.get(0) != null) {
				name = list.get(0).toString();
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return name;
	}

	/**
	 * Modified by Sushil on 03-05-2013 This method is for validating covered
	 * area for Rural and Traditional local body
	 */
	@Override
	public String isVillageExist(int[] localbodyCode, char lbType) {
		String result = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			if (localbodyCode != null && localbodyCode.length > 0) {
				for (int i = 0; i < localbodyCode.length; i++) {
					Query query = null;
					if (lbType == 'R') {
						query = session
								.createSQLQuery("select a.lb_covered_region_code from localbody a, lb_covered_landregion b where a.local_body_type_code=3 and a.lb_covered_region_code=b.lb_covered_region_code and a.local_body_code=:localBodyCode");
					} else if (lbType == 'T') {
						query = session
								.createSQLQuery("select a.lb_covered_region_code from localbody a, lb_covered_landregion b where a.local_body_type_code in (select local_body_type_code from local_body_type where category='R' and level='V' and ispartixgovt=false) and a.lb_covered_region_code=b.lb_covered_region_code and a.local_body_code=:localBodyCode");
					}
					int val = localbodyCode[i];
					query.setParameter("localBodyCode", val);
					@SuppressWarnings("rawtypes")
					List list = query.list();
					if (list.isEmpty()) {
						result = "No Coverage found for selected entity, kindly do mapping first.";
						break;
					} else {
						continue;
					}
				}
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return result;
	}

	/**
	 * @Modified by sushil on 07-01-2013
	 */
	public String getLocalBodyTypeCode(char category, char level) throws Exception {
		Session session = null;
		String lbtypeCode = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			String namedQuery = null;
			if (category != 'U') {
				if (level == 'D') {
					namedQuery = "getlbtypebyCategory";
				} else if (level == 'I') {
					namedQuery = "getlbtypebyCategoryforInter";
				} else if (level == 'V') {
					namedQuery = "getlbtypebyCategoryforVillage";
				}
				query = session.getNamedQuery(namedQuery).setParameter("category", category);
			} else {
				query = session.getNamedQuery("getUrbanlbtypebyCategory").setParameter("category", category);
			}

			@SuppressWarnings("unchecked")
			List<GetLocalBodyTypeCode> lbTypelIst = query.list();
			Iterator<GetLocalBodyTypeCode> itr = lbTypelIst.iterator();
			while (itr.hasNext()) {
				GetLocalBodyTypeCode element = (GetLocalBodyTypeCode) itr.next();
				if (lbtypeCode == null) {
					lbtypeCode = element.getLocalBodyTypeCode().toString() + ",";
				} else {
					lbtypeCode = lbtypeCode + element.getLocalBodyTypeCode().toString() + ",";
				}
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
			throw new Exception(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lbtypeCode;
	}

	/**
	 * @Modified by kamlesh for parent type code on 10-01-2013
	 */
	@Override
	public int getLocalBodyParentTypeCode(char category, char level) {

		int lbtypeCode = 0;

		if (category != 'U') {
			if (level == 'I') {
				lbtypeCode = 1;
			} else if (level == 'V') {
				lbtypeCode = 2;
			}
		}

		return lbtypeCode;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getLocalBodyTypeMessagebyLocalbodyCode(Integer localbodyCode) {

		Query query = null;
		Session session = null;
		String message = null;
		List<GetLBHierarchy> getLBHierarchyList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLBHierarchy").setParameter("parentLBCode", localbodyCode, Hibernate.INTEGER);
			getLBHierarchyList = query.list();
			if (getLBHierarchyList.size() > 0) {
				message = getLBHierarchyList.get(0).getGet_lb_hierarchy_as_text_rpt();
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return message;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GetLocalGovtSetup> getLocalbodyDetailDAO(int stateCode) throws Exception {
		Query criteria = null;
		Session session = null;
		@SuppressWarnings("rawtypes")
		List list = new ArrayList();
		try {

			session = sessionFactory.openSession();
			criteria = session.getNamedQuery("getLocalGovSetupFnF");
			criteria.setParameter("stateCode", stateCode);
			// criteria.setParameter("category", null);
			list = criteria.list();

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalbodyListbyState> getLandRegionByDistricCode(Integer localBodyTypeCode, Integer districtCode, String lbType) throws Exception {
		Session session = null;
		List<LocalbodyListbyState> localbodyList = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			if (lbType.equals("P")) {
				query = session.getNamedQuery("landregionwise");
				query.setCharacter("local_body_type", 'D');
				query.setInteger("district_id", districtCode);
				query.setInteger("local_body_type_code", 1);
				localbodyList = query.list();
			}
			if (lbType.equals("T")) {
				/*
				 * int localBodyCd=0; if(code=='D' || code=='I' || code=='V') {
				 * localBodyCd=9; }
				 */

				query = session.getNamedQuery("landregionwise");
				query.setCharacter("local_body_type", 'D');
				query.setInteger("district_id", districtCode);
				query.setInteger("local_body_type_code", localBodyTypeCode);
				localbodyList = query.list();
			}
		} catch (Exception e) {
			log.debug("Exception" + e);

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return localbodyList;
	}

	/**
	 * Added By sushil on 19-02-2013
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<ChangeLocalBodyName> saveLocalBodyDraft(LocalGovtBodyForm localGovtBodyForm, HttpServletRequest request, UserSelection userSelection) {
		Session session = null;
		Transaction tx = null;
		Query query = null;
		//String retValue = null;
		//Integer transCode = null;
		//Integer orderCode = null;
		//Attachment attachment = null;
		//ChangeLocalBodyName localbody = null;
		List<LocalBodyDetails> localBodyDetails = localGovtBodyForm.getLocalBodyDetails();
		List<ChangeLocalBodyName> comboList = new ArrayList<ChangeLocalBodyName>();

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			int localBodyCode = localBodyDetails.get(0).getLocalBodyCode();
			String localBodyNameEnglish = localBodyDetails.get(0).getLocalBodyNameEnglish();
			String localBodyNameLocal = localBodyDetails.get(0).getLocalBodyNameLocal();
			String aliasNameEnglish = localBodyDetails.get(0).getAliasNameEnglish();
			String alisNameLocal = localBodyDetails.get(0).getAlisNameLocal();
			String orderNo = localGovtBodyForm.getLgd_LBorderNo();
			Date orderDate = localGovtBodyForm.getLgd_LBorderDate();
			Date lBeffectiveDate = localGovtBodyForm.getLgd_LBeffectiveDate();
			Date gazPubDate = localGovtBodyForm.getLgd_LBgazPubDate();
			Long Userid = userSelection.getUserId();

			String govtorder = "";
			Integer userId = new Integer(Userid.intValue());
			// Integer userId =4117;

			query = session.getNamedQuery("getchangelocalbodyname").setParameter("local_body_code", localBodyCode, Hibernate.INTEGER).setParameter("local_body_name_name_english", localBodyNameEnglish, Hibernate.STRING)
					.setParameter("user_id", userId, Hibernate.INTEGER).setParameter("order_no", orderNo, Hibernate.STRING).setParameter("order_date", orderDate, Hibernate.DATE).setParameter("effective_date", lBeffectiveDate, Hibernate.DATE)
					.setParameter("govt_order", govtorder, Hibernate.STRING).setParameter("gaz_pub_date", gazPubDate, Hibernate.DATE).setParameter("local_body_name_name_local", localBodyNameLocal, Hibernate.STRING)
					.setParameter("alias_english", aliasNameEnglish, Hibernate.STRING).setParameter("alias_local", alisNameLocal, Hibernate.STRING);

			comboList = query.list();
			/*
			 * retValue = comboList.get(0).getChange_local_body_name_fn();
			 * if(retValue != null) { String[] arr = retValue.split(",");
			 * transCode = Integer.decode(arr[0]); orderCode =
			 * Integer.decode(arr[1]); }
			 */
			tx.commit();
			// return transCode;
		} catch (Exception e) {
			log.debug("Exception" + e);
			if (tx != null) {
				tx.rollback();
			}

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return comboList;
	}

	/* modified on 30-12-2014 for the local body impact by kirandeep */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<LocalbodyListbyState> getmappedlbforPConsituency(int lbTypeCode, int stateCode, int PcCode, char type) throws Exception {
		Session session = null;
		Query query = null;
		List<LocalbodyListbyState> districtPanchayatList = null;
		List<LocalbodyListbyState> districtPanchayatListduplicate = new ArrayList<LocalbodyListbyState>();
		List<LocalbodyListbyState> lbList = new ArrayList<LocalbodyListbyState>();
		//int index = 0;
		//int count = 0;
		int var1, var2;
		boolean found = false;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getNewPanchayatListbyState").setParameter("lbTypeCode", lbTypeCode).setParameter("stateCode", stateCode);
			districtPanchayatList = query.list();

			/*
			 * query = session .createSQLQuery(
			 * "select l.local_body_code as localBodyCode,l.local_body_name_english as localBodyNameEnglish from localbody l where l.local_body_code in"
			 * +
			 * "(select cce.entity_lc from constituency_covered_entity cce where cc_code=(select cc_code from constituency_coverage where constituency_lc=:PcCode and constituency_type=:type)"
			 * +
			 * 
			 * " and cce.coverage_type = 'F') and l.local_body_type_code=:typeCode and l.slc=:slc and isactive=true "
			 * );
			 */

			query = session.createSQLQuery("select l.local_body_code as localBodyCode,l.local_body_name_english as localBodyNameEnglish,case when l.local_body_code  in (select * from get_draft_used_lb_lr_temp(l.local_body_code,'G')) then"
					+ " cast ('F' as character) else cast('A' as character) end as operation_state from " + " localbody l where l.local_body_code in"
					+ "(select cce.entity_lc from constituency_covered_entity cce where cc_code=(select cc_code from constituency_coverage where constituency_lc=:PcCode and constituency_type=:type)"
					+ " and cce.coverage_type = 'F') and l.local_body_type_code=:typeCode and l.slc=:slc and isactive=true ");

			query.setParameter("typeCode", lbTypeCode);
			query.setParameter("type", type);
			query.setParameter("PcCode", PcCode);
			query.setParameter("slc", stateCode);

			// districtPanchayatList = query.list();
			ArrayList arrayList = (ArrayList) query.list();
			Iterator iterator = arrayList.iterator();
			while (iterator.hasNext()) {
				Object[] object = (Object[]) iterator.next();
				Integer lbcode = (Integer) object[0];
				String lbname = (String) object[1];
				LocalbodyListbyState lblistbystate = new LocalbodyListbyState();
				lblistbystate.setLocalBodyCode(lbcode);
				lblistbystate.setLocalBodyNameEnglish(lbname);
				lblistbystate.setOperation_state((Character) object[2]);
				districtPanchayatListduplicate.add(lblistbystate);
			}

			for (LocalbodyListbyState object1 : districtPanchayatList) {
				found = false;
				for (LocalbodyListbyState object2 : districtPanchayatListduplicate) {
					var1 = object1.getLocalBodyCode();
					var2 = object2.getLocalBodyCode();
					if (var1 == var2) {
						found = true;
						break;

					}
				}

				if (!found) {
					lbList.add(object1);
				}
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lbList;
	}

	@SuppressWarnings("rawtypes")
	public List<LocalbodyListbyState> getunmappedlbforPConsituency(int lbTypeCode, int stateCode, int PcCode, char type) throws Exception {
		Session session = null;
		Query query = null;
		List<LocalbodyListbyState> lbList = new ArrayList<LocalbodyListbyState>();
		//char lbChartypecode = (char) lbTypeCode;

		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select l.local_body_code as localBodyCode,l.local_body_name_english as localBodyNameEnglish,case when l.local_body_code  in (select * from get_draft_used_lb_lr_temp(l.local_body_code,'G')) "
					+ "then cast ('F' as character) else cast('A' as character) end as operation_state from localbody l where l.local_body_code in"
					+ "(select cce.entity_lc from constituency_covered_entity cce where cc_code=(select cc_code from constituency_coverage where constituency_lc=:PcCode and constituency_type=:type)"
					+ " and cce.coverage_type = 'F') and l.local_body_type_code=:typeCode and l.slc=:slc and isactive=true ");

			query.setParameter("typeCode", lbTypeCode);
			query.setParameter("type", type);
			query.setParameter("PcCode", PcCode);
			query.setParameter("slc", stateCode);
			ArrayList arrayList = (ArrayList) query.list();
			Iterator iterator = arrayList.iterator();
			while (iterator.hasNext()) {
				Object[] object = (Object[]) iterator.next();
				Integer lbcode = (Integer) object[0];
				String lbname = (String) object[1];
				lbname = lbname + "(FULL)";
				LocalbodyListbyState lblistbystate = new LocalbodyListbyState();
				lblistbystate.setLocalBodyCode(lbcode);
				lblistbystate.setLocalBodyNameEnglish(lbname);
				lblistbystate.setOperation_state((Character) object[2]);
				lbList.add(lblistbystate);
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lbList;
	}

	@Override
	public boolean ULBExistforConvert(int slc, int type, String ulbName) {
		Query criteria = null;
		Session session = null;
		ulbName = ulbName.trim().toUpperCase();
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Localbody lb where lb.slc=:slc and isactive=true and lb.localBodyTypeCode=:typeCode and  UPPER(TRIM(lb.localBodyNameEnglish)) =:ULBName");
			criteria.setParameter("slc", slc, Hibernate.INTEGER);
			criteria.setParameter("typeCode", type, Hibernate.INTEGER);
			criteria.setParameter("ULBName", ulbName, Hibernate.STRING);
			@SuppressWarnings("rawtypes")
			List list = criteria.list();
			int size = list.size();
			if (size > 0) {
				return false;
			}
		} catch (HibernateException e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return true;
	}

	/**
	 * added by sushil on 13-03-2013
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LGBodyCoveredAreaDTO> getLGBodyCoveredAreaDetail(Integer localBodyCode) {
		Query criteria = null;
		Session session = null;
		List<LGBodyCoveredAreaDTO> list = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.getNamedQuery("getLGBodyCoveredAreaDetail");
			criteria.setParameter("localBodyCode", localBodyCode);
			list = criteria.list();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getdisturblbReasonVP(int lbCode, char type) throws Exception {
		//Query criteria = null;
		Session session = null;
		String details = null;
		String queryBuilder = null;
		Query query = null;
		List<Object[]> reportList = new ArrayList<Object[]>();
		List<Object[]> reportListwto = new ArrayList<Object[]>();
		Integer villagecode = null;
		String vilCodes = null;
		List<Village> villagetnameList = null;
		try {
			session = sessionFactory.openSession();
			queryBuilder = "SELECT LRLC FROM lb_covered_landregion where lb_covered_region_code= (select lb_covered_region_code  from localbody where local_body_code=:localbodyCode and isactive)" + " AND ISACTIVE ";
			query = session.createSQLQuery(queryBuilder);
			query.setParameter("localbodyCode", lbCode);
			reportList = query.list();
			if (reportList.size() == 0)
				details = "Presntly This local Body Dosen't containg any Landregions ";
			else if (reportList.size() > 0) {
				queryBuilder = "SELECT DISTINCT village_code FROM VILLAGE WHERE VLC IN ( SELECT LRLC FROM lb_covered_landregion where lb_covered_region_code= (select lb_covered_region_code  from localbody"
						+ " where local_body_code=:localbodyCode and isactive) AND land_region_type='V' AND ISACTIVE ) AND ISACTIVE=TRUE ";
				query = session.createSQLQuery(queryBuilder);
				query.setParameter("localbodyCode", lbCode);
				reportList = query.list();

				queryBuilder = " SELECT LRLC FROM lb_covered_landregion where lb_covered_region_code= (select lb_covered_region_code  from localbody" + " where local_body_code=:localbodyCode AND land_region_type='V' and isactive) AND ISACTIVE  ";
				query = session.createSQLQuery(queryBuilder);
				query.setParameter("localbodyCode", lbCode);
				reportListwto = query.list();
				if (reportListwto.size() > reportList.size()) {
					for (Object accvo1 : reportListwto)
						if (!reportList.contains(accvo1)) {
							villagecode = (Integer) accvo1;
							vilCodes = vilCodes + villagecode.toString() + ",";
						}
					vilCodes = vilCodes.substring(4, vilCodes.length() - 1);
					villagetnameList = villageService.getVillageListByVillageCodes(vilCodes, 1);
					details = "Following Villages have been Invalidate : ";
					for (Village vil : villagetnameList) {
						details = details + vil.getVillageNameEnglish() + ", ";
					}
					details = details.substring(0, details.length() - 2);

				} else {
					queryBuilder = "SELECT VILLAGE_CODE  FROM VILLAGE WHERE VLC in ( SELECT LRLC FROM lb_covered_landregion where lb_covered_region_code= (select lb_covered_region_code  from localbody"
							+ " where local_body_code=:localbodyCode and isactive) AND land_region_type='V' AND ISACTIVE )    GROUP  by village_code  HAVING COUNT(VILLAGE_CODE)>1  ";
					query = session.createSQLQuery(queryBuilder);
					query.setParameter("localbodyCode", lbCode);
					reportList = query.list();
					if (reportList.size() > 0) {
						for (Object obj : reportList) {
							villagecode = (Integer) obj;
							vilCodes = vilCodes + villagecode.toString() + ",";
						}
						vilCodes = vilCodes.substring(4, vilCodes.length() - 1);
						villagetnameList = villageService.getVillageListByVillageCodes(vilCodes, 2);
						details = " Following Villages have been modified : ";
						for (Village vil : villagetnameList) {
							details = details + vil.getVillageNameEnglish() + ", ";
						}
						details = details.substring(0, details.length() - 2);
					}
				}

			}

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		if (details == null || details.isEmpty()) {
			details = " Villages have changed their State : ";
		}
		return details;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getdisturblbReasonIP(int lbCode, char type) throws Exception {
		//Query criteria = null;
		Session session = null;
		String details = null;
		String queryBuilder = null;
		Query query = null;
		List<Object[]> reportList = new ArrayList<Object[]>();
		List<Object[]> reportListwto = new ArrayList<Object[]>();
		Integer villagecode = null;
		String entitycode = null;
		List<Village> villagetnameList = null;
		try {
			session = sessionFactory.openSession();
			queryBuilder = "SELECT LRLC FROM lb_covered_landregion where lb_covered_region_code= (select lb_covered_region_code  from localbody where local_body_code=:localbodyCode and isactive)" + " AND ISACTIVE ";
			query = session.createSQLQuery(queryBuilder);
			query.setParameter("localbodyCode", lbCode);
			reportList = query.list();
			if (reportList.size() == 0)
				details = "Presntly This local Body Dosen't containg any Landregions ";
			else if (reportList.size() > 0) {
				queryBuilder = "SELECT DISTINCT subdistrict_code FROM subdistrict WHERE TLC IN ( SELECT LRLC FROM lb_covered_landregion where lb_covered_region_code= (select lb_covered_region_code  from localbody"
						+ " where local_body_code=:localbodyCode and isactive) AND land_region_type='T' AND ISACTIVE ) AND ISACTIVE=TRUE ";
				query = session.createSQLQuery(queryBuilder);
				query.setParameter("localbodyCode", lbCode);
				reportList = query.list();

				queryBuilder = " SELECT LRLC FROM lb_covered_landregion where lb_covered_region_code= (select lb_covered_region_code  from localbody" + " where local_body_code=:localbodyCode AND land_region_type='T' and isactive) AND ISACTIVE  ";
				query = session.createSQLQuery(queryBuilder);
				query.setParameter("localbodyCode", lbCode);
				reportListwto = query.list();
				if (reportListwto.size() > reportList.size()) {
					for (Object accvo1 : reportListwto)
						if (!reportList.contains(accvo1)) {
							villagecode = (Integer) accvo1;
							entitycode = entitycode + villagecode.toString() + ",";
						}
					entitycode = entitycode.substring(4, entitycode.length() - 1);
					villagetnameList = villageService.getVillageListByVillageCodes(entitycode, 3);
					details = "Following Subdistricts have been Invalidate : ";
					for (Village vil : villagetnameList) {
						details = details + vil.getVillageNameEnglish() + ", ";
					}
					details = details.substring(0, details.length() - 2);

				} else {
					queryBuilder = "SELECT subdistrict_code  FROM subdistrict WHERE TLC in ( SELECT LRLC FROM lb_covered_landregion where lb_covered_region_code= (select lb_covered_region_code  from localbody"
							+ " where local_body_code=:localbodyCode and isactive) AND land_region_type='T' AND ISACTIVE )    GROUP  by subdistrict_code  HAVING COUNT(subdistrict_code)>1  ";
					query = session.createSQLQuery(queryBuilder);
					query.setParameter("localbodyCode", lbCode);
					reportList = query.list();
					if (reportList.size() > 0) {
						for (Object obj : reportList) {
							villagecode = (Integer) obj;
							entitycode = entitycode + villagecode.toString() + ",";
						}
						entitycode = entitycode.substring(4, entitycode.length() - 1);
						villagetnameList = villageService.getVillageListByVillageCodes(entitycode, 4);
						details = " Following Subdistricts have been modified : ";
						for (Village vil : villagetnameList) {
							details = details + vil.getVillageNameEnglish() + ", ";
						}
						details = details.substring(0, details.length() - 2);
					}
				}

			}

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		if (details == null || details.isEmpty()) {
			details = " Subdistricts have changed their State : ";
		}
		return details;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getdisturblbReasonDP(int lbCode, char type) throws Exception {
		//Query criteria = null;
		Session session = null;
		String details = null;
		String queryBuilder = null;
		Query query = null;
		List<Object[]> reportList = new ArrayList<Object[]>();
		List<Object[]> reportListwto = new ArrayList<Object[]>();
		Integer discode = null;
		String vilCodes = null;
		List<Village> villagetnameList = null;
		try {
			session = sessionFactory.openSession();
			queryBuilder = "SELECT LRLC FROM lb_covered_landregion where lb_covered_region_code= (select lb_covered_region_code  from localbody where local_body_code=:localbodyCode and isactive)" + " AND ISACTIVE ";
			query = session.createSQLQuery(queryBuilder);
			query.setParameter("localbodyCode", lbCode);
			reportList = query.list();
			if (reportList.size() == 0)
				details = "Presntly This local Body Dosen't containg any Landregions ";
			else if (reportList.size() > 0) {
				queryBuilder = "SELECT DISTINCT district_code FROM district WHERE DLC IN ( SELECT LRLC FROM lb_covered_landregion where lb_covered_region_code= (select lb_covered_region_code  from localbody"
						+ " where local_body_code=:localbodyCode and isactive) AND land_region_type='D' AND ISACTIVE ) AND ISACTIVE=TRUE ";
				query = session.createSQLQuery(queryBuilder);
				query.setParameter("localbodyCode", lbCode);
				reportList = query.list();

				queryBuilder = " SELECT LRLC FROM lb_covered_landregion where lb_covered_region_code= (select lb_covered_region_code  from localbody" + " where local_body_code=:localbodyCode AND land_region_type='D' and isactive) AND ISACTIVE  ";
				query = session.createSQLQuery(queryBuilder);
				query.setParameter("localbodyCode", lbCode);
				reportListwto = query.list();
				if (reportList.size() > 0) {
					if (reportListwto.size() > reportList.size()) {
						for (Object accvo1 : reportListwto)
							if (!reportList.contains(accvo1)) {
								discode = (Integer) accvo1;
								vilCodes = vilCodes + discode.toString() + ",";
							}
						vilCodes = vilCodes.substring(4, vilCodes.length() - 1);
						villagetnameList = villageService.getVillageListByVillageCodes(vilCodes, 5);
						details = "Following Districts have been Invalidate : ";
						for (Village vil : villagetnameList) {
							details = details + vil.getVillageNameEnglish() + ", ";
						}
						details = details.substring(0, details.length() - 2);
					}

				} else {
					queryBuilder = "SELECT district_code  FROM district WHERE DLC in ( SELECT LRLC FROM lb_covered_landregion where lb_covered_region_code= (select lb_covered_region_code  from localbody"
							+ " where local_body_code=:localbodyCode and isactive) AND land_region_type='D' AND ISACTIVE )    GROUP  by district_code  HAVING COUNT(district_code)>1  ";
					query = session.createSQLQuery(queryBuilder);
					query.setParameter("localbodyCode", lbCode);
					reportList = query.list();
					if (reportList.size() > 0) {
						for (Object obj : reportList) {
							discode = (Integer) obj;
							vilCodes = vilCodes + discode.toString() + ",";
						}
						vilCodes = vilCodes.substring(4, vilCodes.length() - 1);
						villagetnameList = villageService.getVillageListByVillageCodes(vilCodes, 1);
						details = " Following Districts have been modified : ";
						for (Village vil : villagetnameList) {
							details = details + vil.getVillageNameEnglish() + ", ";
						}
						details = details.substring(0, details.length() - 2);
					}
				}

			}

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		if (details == null || details.isEmpty()) {
			details = " Districts have changed their State : ";
		}
		return details;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<LocalbodyListbyState> getMappedVillageWardofConsituency(int PcCode, char consituencyType, char entityType, int slc) throws Exception {
		Session session = null;
		Query query = null;
		List<LocalbodyListbyState> lbList = new ArrayList<LocalbodyListbyState>();
		String sql = "";
		if (entityType == 'V') {
			sql = "select vl.village_code as localBodyCode,vl.village_name_english as localBodyNameEnglish from village vl where vl.village_code in "
					+ "(select cce.entity_lc from constituency_covered_entity cce where cc_code in (select cc_code from constituency_coverage where constituency_lc=:PcCode and constituency_type=:type)"
					+ " and cce.coverage_type = 'F' and cce.entity_type=:entityType)  and vl.slc=:slc and isactive=true ";
		} else {
			sql = "select wd.ward_code as localBodyCode,wd.ward_name_english as localBodyNameEnglish from localbody_ward wd where wd.ward_code in "
					+ "(select cce.entity_lc from constituency_covered_entity cce where cc_code in (select cc_code from constituency_coverage where constituency_lc=:PcCode and constituency_type=:type)"
					+ " and cce.coverage_type = 'F' and cce.entity_type=:entityType)  and isactive=true ";
		}

		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery(sql);

			query.setParameter("type", consituencyType);
			query.setParameter("PcCode", PcCode);
			if (entityType == 'V') {
				query.setParameter("slc", slc);
			}
			query.setParameter("entityType", entityType);
			ArrayList arrayList = (ArrayList) query.list();
			Iterator iterator = arrayList.iterator();
			while (iterator.hasNext()) {
				Object[] object = (Object[]) iterator.next();
				Integer lbcode = (Integer) object[0];
				String lbname = (String) object[1];
				lbname = lbname + " (FULL)";
				LocalbodyListbyState lblistbystate = new LocalbodyListbyState();
				lblistbystate.setLocalBodyCode(lbcode);
				lblistbystate.setLocalBodyNameEnglish(lbname);
				lbList.add(lblistbystate);
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lbList;

	}

	@Override
	public String mergeULB(LocalGovtBodyForm lbForm, Integer userid, Session session) {
		String mergeData = null;
		Integer lbCode = Integer.parseInt(lbForm.getLocalBodyNameEnglish());
		String orderNo = lbForm.getOrderNo();
		Date oDate = lbForm.getOrderDate();
		Date eDate = lbForm.getEffectiveDate();
		Date gDate = lbForm.getGazPubDate();
		java.sql.Timestamp gazDate = null;
		java.sql.Timestamp orderDate = new Timestamp(oDate.getTime());
		java.sql.Timestamp effectitveDates = new Timestamp(eDate.getTime());
		if (gDate != null) {
			gazDate = new Timestamp(gDate.getTime());
		}
		char lbTypeModify = lbForm.getLbtypeLevel();
		Integer newType = lbForm.getLocalbodySubtype();
		String mergUlb = lbForm.getListformat();
		Query query = null;
		try {
			query = session.getNamedQuery("ulbMerge").setParameter("lbcode", lbCode, Hibernate.INTEGER).setParameter("p_order_no", orderNo, Hibernate.STRING).setParameter("p_order_date", orderDate, Hibernate.DATE)
					.setParameter("p_effective_date", effectitveDates, Hibernate.DATE).setParameter("typechanged", lbTypeModify, Hibernate.CHARACTER).setParameter("lbtypenew", newType, Hibernate.INTEGER)
					.setParameter("ulbfull", mergUlb, Hibernate.STRING).setParameter("userid", userid, Hibernate.INTEGER).setParameter("gaz_pub_date", gazDate, Hibernate.DATE);
			UlbMerge ulbMerge = (UlbMerge) query.list().get(0);
			mergeData = ulbMerge.getMerge_ulb();
		} catch (Exception e) {
			log.debug("Exception" + e);

		}

		return mergeData;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean mapZpConsituencyDetail(String wardNo, String lbList, String vilList, String deltedLb, String deletedVil) throws Exception {
		Query criteria = null;
		Session session = null;
		Transaction tx = null;
		String gplist[] = null;
		String villagelist[] = null;
		int gpElement = 0;
		int vilElement = 0;
		if (lbList != null) {
			gplist = lbList.split(":");
			gpElement = gplist.length;
		}
		if (vilList != null) {
			villagelist = vilList.split(":");
			vilElement = villagelist.length;
		}
		int entitycode = 0;
		int entityversion = 1;
		int wardCode = Integer.parseInt(wardNo);
		int wardVersion = 1;
		int totalElement = gpElement + vilElement;
		MapZpWardContituency[] coverageEnty = new MapZpWardContituency[totalElement];
		int i = 0;
		int j = 0;
		List<Object[]> list = null;
		boolean status = false;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			criteria = session.createSQLQuery("Select ward_version From localbody_ward Where isactive=true AND ward_code =:wardNo").setParameter("wardNo", wardCode, Hibernate.INTEGER);

			@SuppressWarnings("rawtypes")
			List listward = criteria.list();
			if (listward.size() > 0) {
				wardVersion = (Integer) listward.get(0);
			}
			for (i = 0; i < totalElement; i++) {
				coverageEnty[i] = new MapZpWardContituency();
				if (i < gpElement) {
					entitycode = Integer.parseInt(gplist[i]);
					criteria = session.createSQLQuery("Select lblc,local_body_version From localbody Where isactive=true AND local_body_code =:lbcd").setParameter("lbcd", entitycode, Hibernate.INTEGER);
					list = criteria.list();
					if (list.size() > 0) {
						Object obj[] = list.get(0);
						entitycode = (Integer) obj[0];
						entityversion = (Integer) obj[1];

					}
					coverageEnty[i].setCoverage_lc(entitycode);
					coverageEnty[i].setWard_code(Integer.parseInt(wardNo));
					coverageEnty[i].setCoverage_lc_type('3');
					coverageEnty[i].setIsactive(true);
					coverageEnty[i].setWard_version(wardVersion);
					coverageEnty[i].setCoverage_lc_version(entityversion);
					coverageEnty[i].setCoverage_type('F');
					session.save(coverageEnty[i]);
				} else {
					entitycode = Integer.parseInt(villagelist[j]);
					criteria = session.createSQLQuery("Select vlc,village_version From village Where isactive=true AND village_code =:lbcd").setParameter("lbcd", entitycode, Hibernate.INTEGER);
					list = criteria.list();
					if (list.size() > 0) {
						Object obj[] = list.get(0);
						entitycode = (Integer) obj[0];
						entityversion = (Integer) obj[1];

					}
					coverageEnty[i] = new MapZpWardContituency();
					coverageEnty[i].setCoverage_lc(entitycode);
					coverageEnty[i].setWard_code(Integer.parseInt(wardNo));
					coverageEnty[i].setCoverage_lc_type('V');
					coverageEnty[i].setIsactive(true);
					coverageEnty[i].setWard_version(wardVersion);
					coverageEnty[i].setCoverage_lc_version(entityversion);
					coverageEnty[i].setCoverage_type('F');
					session.save(coverageEnty[i]);
					j++;
				}

			}
			if (!deltedLb.isEmpty()) {
				Query query = session.createSQLQuery("delete from covered_zpward_landregion b where b.coverage_lc in(" + deltedLb + ") and b.coverage_lc_type='3' " + "and b.ward_code='" + wardCode + "' and isactive=true ");
				query.executeUpdate();
			}
			if (!deletedVil.isEmpty()) {
				Query query = session.createSQLQuery("delete from covered_zpward_landregion b where b.coverage_lc in(" + deletedVil + ") and b.coverage_lc_type='V' " + "and b.ward_code='" + wardCode + "' and isactive=true ");
				query.executeUpdate();
			}
			tx.commit();
			status = true;
		} catch (HibernateException e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return status;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<ParentWiseLocalBodies> mappedZpWardConsituencyDetail(String wardNo, char type, boolean wholeData) throws Exception {
		Query criteria = null;
		Session session = null;
		List<ParentWiseLocalBodies> conCover = new ArrayList<ParentWiseLocalBodies>();
		ParentWiseLocalBodies parentWiseLocalBodies = null;
		//int cccode = 0;
		int wardNumber = Integer.parseInt(wardNo);
		Integer lbcode = null;
		String lbname = null;
		String lblc = null;
		String sql = null;

		try {

			session = sessionFactory.openSession();
			if (wholeData) {
				if (type == '3')
					sql = "select lb.lblc,lb.local_body_name_english from localbody lb,covered_zpward_landregion cl where lb.lblc =cl.coverage_lc and  " + "cl.coverage_lc_type=:type and lb.isactive=true";
				else
					sql = "select vl.vlc,vl.village_name_english from village vl,covered_zpward_landregion cl where vl.vlc =cl.coverage_lc and  " + "cl.coverage_lc_type=:type and vl.isactive=true";
			} else {
				if (type == '3')
					sql = "select lb.lblc,lb.local_body_name_english from localbody lb,covered_zpward_landregion cl where lb.lblc =cl.coverage_lc and cl.ward_code=:coverageCode and " + "cl.coverage_lc_type=:type and lb.isactive=true";
				else
					sql = "select vl.vlc,vl.village_name_english from village vl,covered_zpward_landregion cl where vl.vlc =cl.coverage_lc and cl.ward_code=:coverageCode and " + "cl.coverage_lc_type=:type and vl.isactive=true";
			}
			criteria = session.createSQLQuery(sql);
			if (!wholeData)
				criteria.setParameter("coverageCode", wardNumber, Hibernate.INTEGER);
			criteria.setParameter("type", type, Hibernate.CHARACTER);
			ArrayList arrayList = (ArrayList) criteria.list();
			Iterator iterator = arrayList.iterator();

			while (iterator.hasNext()) {
				parentWiseLocalBodies = new ParentWiseLocalBodies();
				Object[] object = (Object[]) iterator.next();
				lbcode = (Integer) object[0];
				lblc = lbcode.toString();
				lbname = (String) object[1];
				if (!wholeData)
					lblc = lblc + "MAPD";
				parentWiseLocalBodies.setLocalBodyCode(lblc);
				parentWiseLocalBodies.setLocalBodyNameEnglish(lbname);
				conCover.add(parentWiseLocalBodies);

			}

		} catch (HibernateException e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return conCover;

	}

	/* Modified by kirandeep on 01/09/2014 for ward */
	@Override
	public Integer WardExist(int lblc, String wardName, int type) {
		Query criteria = null;
		Query query1 = null;
		Query criteria2 = null;
		Session session = null;
		wardName = wardName.trim().toUpperCase();
		String sql = "";
		String sql1 = "";
		// Integer wardNo=0;
		if (type == 1) {
			sql = "from LocalbodyWard b where b.lblc=:lblc and isactive=false and  UPPER(TRIM(b.wardNameEnglish)) =:wardName";
			sql1 = "from localbodywardtemp l where l.wardNameEnglish =:wardName and l.lblc =:lblc  and l.isactive=true";
		} else if (type == 2) {
			sql = "from LocalbodyWard b where b.lblc=:lblc and isactive=false and UPPER(TRIM(b.wardNumber)) =:wardName";
			sql1 = "from localbodywardtemp l where l.wardNumber =:wardName and l.lblc =:lblc  and l.isactive=true";
			// wardNo = Integer.parseInt(wardName);
		}
		try {
			session = sessionFactory.openSession();

			query1 = (SQLQuery) session.createSQLQuery("select b.lblc from localbody  b where b.isactive =true and b.local_body_code=" + lblc);

			lblc = (Integer) query1.uniqueResult();

			criteria = session.createQuery(sql);
			criteria.setParameter("lblc", lblc, Hibernate.INTEGER);
			criteria.setParameter("wardName", wardName, Hibernate.STRING);

			/*
			 * if(type==1) criteria.setParameter("wardName", wardName,
			 * Hibernate.STRING); else criteria.setParameter("wardNumber",
			 * wardNo, Hibernate.INTEGER);
			 */
			@SuppressWarnings("rawtypes")
			List list = criteria.list();
			int size = list.size();
			if (size > 0) {
				return 1;
			}

			criteria2 = session.createQuery(sql1);
			criteria2.setParameter("lblc", lblc, Hibernate.INTEGER);
			criteria2.setParameter("wardName", wardName, Hibernate.STRING);

			@SuppressWarnings("rawtypes")
			List list1 = criteria2.list();
			int size1 = list1.size();
			if (size1 > 0) {
				return 2;
			}

		} catch (HibernateException e) {
			LGDLogger.getLogger(LanguageListener.class).error(e.toString());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalBodyCoveredArea> getNewWardVillageCoverage(String entityCode) throws Exception {
		Session session = null;
		Query query = null;
		List<LocalBodyCoveredArea> lgdforCoveredVillageList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getCoveredAreaOfNewWard").setParameter("entityCode", entityCode).setParameter("type", "T");
			lgdforCoveredVillageList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lgdforCoveredVillageList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Localbody> getChildLbByParentLb(int parentlblc, int slc) throws Exception {
		Session session = null;
		List<Localbody> childLBList = null;
		Query criteria = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createQuery("from Localbody lb where lb.parentlblc=:parentlblc and lb.slc=:slc and lb.isactive=true order by lb.localBodyNameEnglish");
			criteria.setParameter("parentlblc", parentlblc, Hibernate.INTEGER).setParameter("slc", slc, Hibernate.INTEGER);
			childLBList = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return childLBList;

	}

	@Override
	public boolean updateLbPesaStatus(String lbcode, int slc, String deletedlbcode) throws Exception {
		//Query query = null;
		Boolean status = true;
		String sqlQuery = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			if (lbcode != null && !lbcode.isEmpty()) {
				sqlQuery = "update localbody set is_pesa=true where local_body_code in (" + lbcode + ") and slc=" + slc + " and isactive=true";
				session.createSQLQuery(sqlQuery).executeUpdate();
			}
			if (deletedlbcode != null && !deletedlbcode.isEmpty()) {
				sqlQuery = "update localbody set is_pesa=false where local_body_code in (" + deletedlbcode + ") and slc=" + slc + " and isactive=true";
				session.createSQLQuery(sqlQuery).executeUpdate();
			}

		} catch (Exception e) {
			status = false;
			log.debug("Exception" + e);
			e.printStackTrace();
		}
		return status;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Localbody> getactiveLbPesa1(int slc) {
		Session session = null;
		List<Localbody> childLBList = null;
		SQLQuery criteria = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createSQLQuery("select gparent.local_body_name_english as aliasEnglish,parent.local_body_name_english as aliasLocal,"
					+ " child.local_body_code as localBodyCode ,child.local_body_name_english as localBodyNameEnglish,child.local_body_name_local as localBodyNameLocal"
					+ " from localbody child,localbody parent,localbody gparent where child.slc=:slc and child.isactive and child.is_pesa and child.parent_lblc=parent.local_body_code"
					+ " and parent.parent_lblc=gparent.local_body_code and child.isactive=true and gparent.isactive=true and parent.isactive=true order by child.local_body_name_english");
			criteria.setParameter("slc", slc, Hibernate.INTEGER);
			criteria.addScalar("aliasEnglish").addScalar("aliasLocal").addScalar("localBodyCode").addScalar("localBodyNameEnglish").addScalar("localBodyNameLocal");
			criteria.setResultTransformer(Transformers.aliasToBean(Localbody.class));
			childLBList = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return childLBList;

	}

	/**
	 * Change For LGD CODE by Maneesh Kumar 19Sep2014
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<StatewisePesaPanchyat> getactiveLbPesa(Integer slc) {
		Session session = null;
		List<StatewisePesaPanchyat> statewisePesaPanchyatList = null;
		SQLQuery criteria = null;
		try {
			session = sessionFactory.openSession();
			criteria = session.createSQLQuery("select  gparent.local_body_code as dpCode,gparent.local_body_name_english as dpName,parent.local_body_code as ipCode,parent.local_body_name_english as ipName,"
					+ " child.local_body_code as vpCode ,child.local_body_name_english as vpNameEng,child.local_body_name_local as vpNameLoc ,child.is_pesa as coverageType"
					+ " from localbody child,localbody parent,localbody gparent where child.slc=:slc and child.isactive and child.is_pesa in('P','F') and child.parent_lblc=parent.lblc"
					+ " and parent.parent_lblc=gparent.lblc and child.isactive=true and gparent.isactive=true and parent.isactive=true order by child.local_body_name_english");
			criteria.setParameter("slc", slc, Hibernate.INTEGER);
			criteria.addScalar("dpCode").addScalar("dpName").addScalar("ipCode").addScalar("ipName").addScalar("vpCode").addScalar("vpNameEng").addScalar("vpNameLoc").addScalar("coverageType");
			criteria.setResultTransformer(Transformers.aliasToBean(StatewisePesaPanchyat.class));
			statewisePesaPanchyatList = criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return statewisePesaPanchyatList;

	}

	/*Modified by Pooja on 19-05-2016*/
	@SuppressWarnings("unchecked")
	public List<State> getStateWisePesa() {
		Session session = null;
		List<State> stateList = null;
		// Query criteria = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			String queryBuilder = "from State where isPesa='P' and isactive=true order by stateNameEnglish";
			Query query = session.createQuery(queryBuilder);
			stateList = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return stateList;
	}

	@Override
	public Integer getLblc(Integer localBodyCode) {
		Session session = sessionFactory.openSession();
		Integer lblc = null;
		try {
			@SuppressWarnings("unchecked")
			List<Localbody> localbodyList = session.createQuery("from Localbody where isactive=true and localBodyCode=:localBodyCode").setParameter("localBodyCode", localBodyCode).list();
			if (localbodyList != null && !localbodyList.isEmpty()) {
				lblc = localbodyList.get(0).getLblc();
			}
		} catch (Exception e) {
			log.error("error:" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lblc;
	}

	/*
	 * @Override public List<String> getStateTopHierarchy(int slc) { { Session
	 * session = null; List<String> stateTopHierarchy = new ArrayList<String>();
	 * SQLQuery criteria = null; try { session = sessionFactory.openSession();
	 * criteria = session.createSQLQuery(
	 * "select a.local_body_type_name from local_body_type a,local_body_setup b ,local_body_tiers_setup c"
	 * +
	 * " where b.local_body_setup_code=c.local_body_setup_code and b.local_body_setup_version=c.local_body_setup_version"
	 * +
	 * " and c.local_body_type_code=a.local_body_type_code and b.slc=:slc and a.level='D' and a.isactive and b.isactive and c.isactive"
	 * ); criteria.setParameter("slc", slc,Hibernate.INTEGER); stateTopHierarchy
	 * = (List<String>)criteria.list(); } catch (Exception e) {
	 * log.debug("Exception" + e); } finally { if (session != null &&
	 * session.isOpen()){ session.close(); } }
	 * 
	 * return stateTopHierarchy;
	 * 
	 * }
	 * 
	 * }
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalBodyParent> getStateTopHierarchy(int slc) {
		{
			Session session = null;
			List<LocalBodyParent> stateTopHierarchy = new ArrayList<LocalBodyParent>();
			Query query = null;
			try {
				session = sessionFactory.openSession();
				query = session.getNamedQuery("getStateTopHierarchywise").setParameter("slc", slc, Hibernate.INTEGER);

				stateTopHierarchy = query.list();

			} catch (Exception e) {
				log.debug("Exception" + e);
			} finally {
				if (session != null && session.isOpen()) {
					session.close();
				}
			}

			return stateTopHierarchy;

		}

	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Localbody> getStateTopHierarchyforGta(int slc, int localBodyTypeCode) {
		// TODO Auto-generated method stub
		Session session = null;
		List<Localbody> getStateTopHierarchyGta = new ArrayList<Localbody>();
		try {
			session = sessionFactory.openSession();
			String queryBuilder = "select * from localbody where slc =:slc and local_body_type_code=:localbodytypecode and isactive=true order by local_Body_Name_English";
			Query query = session.createSQLQuery(queryBuilder);
			query.setParameter("slc", slc);
			query.setParameter("localbodytypecode", localBodyTypeCode);
			getStateTopHierarchyGta = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return getStateTopHierarchyGta;
	}

	/*
	 * method created for the GTA(union added for gta in existing function and
	 * lblc to local body code) (manage localbody PRI) author Ashish Dhupia ,
	 * Date : 23/07/2014
	 */
	/**
	 * Remove catch method for throws Exception in controller by Maneesh Kumar
	 * 23June2015
	 */
	
	@SuppressWarnings("unchecked")
	public List<LocalbodyListbyState> getPanchayatListbyParentCategoryFChangeTierforGta(int lbTypeCode, int stateCode, int parentLbCode) throws Exception {
		Session session = null;
		Query query = null;
		List<LocalbodyListbyState> districtPanchayatList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getNewPanchayatListbyStateChangeTierforGta").setParameter("stateCode", stateCode).setParameter("parentLbCode", parentLbCode);
			if (lbTypeCode == 20) {
				query.setParameter("lbTypeCodeGta", lbTypeCode);
				query.setParameter("lbTypeCodeZp", 1);
			}
			if (lbTypeCode == 1) {
				query.setParameter("lbTypeCodeZp", lbTypeCode);
				query.setParameter("lbTypeCodeGta", 20);
			}
			districtPanchayatList = query.list();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return districtPanchayatList;
	}

	@Override
	public Integer getlocalbodycodeByLblc(int parentLBCode) {
		Session session = null;
		Integer lbcode = null;
		SQLQuery query = null;
		try {
			session = sessionFactory.openSession();
			query = (SQLQuery) session.createSQLQuery("select local_body_code from localbody where lblc = :parentLBCode and isactive").setParameter("parentLBCode", parentLBCode, Hibernate.INTEGER);
			lbcode = (Integer) query.uniqueResult();

		} catch (Exception e) {
			log.error("error:" + e);
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return lbcode;
	}

	public boolean checkLocalbodyExist(Integer lbCode) {
		boolean isState = false;
		org.hibernate.Session session = null;
		try {
			session = this.sessionFactory.openSession();
			Query query = session.createSQLQuery("select count(*) from localbody where local_body_code = :lbCode and isactive");
			query.setParameter("lbCode", lbCode);
			BigInteger count = (BigInteger) query.uniqueResult();
			if (count.intValue() == 1) {
				isState = true;
			}
		} catch (Exception localException) {
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return isState;
	}

	/* Added by kirandeep on 01/09/2014 for ward */
	@Override
	public boolean createwarddetailsdelete(List<LocalbodyWard> ward) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			LocalbodyWard warddeta = ward.get(0);

			localbodywardtemp templ = new localbodywardtemp();

			templ.setWardCode(warddeta.getWardCode());
			templ.setWardVersion(warddeta.getWardVersion());
			templ.setWardNameEnglish(warddeta.getWardNameEnglish());
			templ.setWardNameLocal(warddeta.getWardNameLocal());
			templ.setLblc(warddeta.getLblc());
			templ.setWardNumber(warddeta.getWardNumber());
			templ.setMapCode(0);

			templ.setEffectiveDate(warddeta.getEffectiveDate());
			templ.setLastupdated(warddeta.getLastupdated());
			templ.setLastupdatedby(warddeta.getLastupdatedby());
			templ.setCreatedon(warddeta.getCreatedon());
			templ.setCreatedby(warddeta.getCreatedby());
			templ.setIsactive(warddeta.isIsactive());

			templ.setIsdelete(true);
			templ.setIsupdate(false);
			templ.setIsnew(false);

			session.save(templ);
			session.getTransaction().commit();
			// session.flush();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	public List<localbodywardtemp> getlocalGovtBodyWardListforpaginationtemp(int localBodyCode, int offset, int limit) {
		Session session = null;
		List<localbodywardtemp> lGBWard = null;
		Query query = null;
		Query query1 = null;
		try {

			session = sessionFactory.openSession();

			query1 = (SQLQuery) session.createSQLQuery("select b.lblc from localbody b where b.isactive=true and b.local_body_code=" + localBodyCode);

			int lblc = (Integer) query1.uniqueResult();
			query = session.createQuery("from localbodywardtemp l where l.lblc =:localBodyCode and l.isactive=true").setParameter("localBodyCode", lblc, Hibernate.INTEGER);
			/*
			 * query = session.getNamedQuery("getLocalGovtBodyWardListViewtemp")
			 * .setParameter("lblc", localBodyCode).setFirstResult(offset)
			 * .setMaxResults(limit);
			 */
			lGBWard = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lGBWard;
	}

	public boolean managewarddeletetemp(Integer tempWardid) {
		Session session = null;
		int result = 0;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("delete from localbody_ward_temp ac where ac.temp_ward_code =:temp_ward_code");
			query.setParameter("temp_ward_code", tempWardid);
			result = query.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		if (result > 0)
			return true;
		else
			return false;

	}

	/**
	 * Changed for synchronized on 08/01/2015 by kirandeep
	 * 
	 */

	@Override
	public synchronized boolean createwardformanage(localbodywardtemp tempward) {
		Session session = sessionFactory.openSession();
		Query query = null;
		Query query2 = null;
		Integer wardid = 0;
		try {
			session.beginTransaction();
			LocalbodyWard warddeta = new LocalbodyWard();
			LocalbodyWardId wardidObj = new LocalbodyWardId();

			query = (SQLQuery) session.createSQLQuery("select max(ward_code)+1 from localbody_ward");

			wardid = (Integer) query.uniqueResult();

			query2 = (SQLQuery) session.createSQLQuery("select * from localbody_ward l where l.ward_number=:wardnumber and l.lblc=:lblc").setParameter("wardnumber", tempward.getWardNumber()).setParameter("lblc", tempward.getLblc());

			@SuppressWarnings("rawtypes")
			List list = query2.list();
			if (list.size() > 0) {
				return false;
			}
			wardidObj.setWardCode(wardid);
			wardidObj.setWardVersion(1);
			//warddeta.setId(wardidObj);
			warddeta.setWardNameEnglish(tempward.getWardNameEnglish());
			warddeta.setWardNameLocal(tempward.getWardNameLocal());
			warddeta.setLblc(tempward.getLblc());
			warddeta.setWardNumber(tempward.getWardNumber());
			//warddeta.setMapCode(0);

			warddeta.setEffectiveDate(tempward.getEffectiveDate());
			warddeta.setLastupdated(tempward.getLastupdated());
			warddeta.setLastupdatedby(tempward.getLastupdatedby());
			warddeta.setCreatedon(tempward.getCreatedon());
			warddeta.setCreatedby(tempward.getCreatedby());
			warddeta.setIsactive(tempward.isIsactive());

			session.save(warddeta);
			session.getTransaction().commit();
			// session.flush();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return true;
	}

	@Override
	public boolean createwardformanageUpdate(localbodywardtemp tempward, String isUpdate) {
		Session session = sessionFactory.openSession();
		//Query query = null;
		//Integer wardid = 0;
		try {
			session.beginTransaction();
			LocalbodyWard warddeta = new LocalbodyWard();
			LocalbodyWardId wardidObj = new LocalbodyWardId();

			/*
			 * query = (SQLQuery) session .createSQLQuery(
			 * "select max(ward_code)+1 from localbody_ward");
			 * 
			 * wardid = (Integer) query.uniqueResult();
			 */
			wardidObj.setWardCode(tempward.getWardCode());
			wardidObj.setWardVersion(1);
			//warddeta.setId(wardidObj);
			warddeta.setWardNameEnglish(tempward.getWardNameEnglish());
			warddeta.setWardNameLocal(tempward.getWardNameLocal());
			warddeta.setLblc(tempward.getLblc());
			warddeta.setWardNumber(tempward.getWardNumber());
			//warddeta.setMapCode(0);

			warddeta.setEffectiveDate(tempward.getEffectiveDate());
			warddeta.setLastupdated(tempward.getLastupdated());
			warddeta.setLastupdatedby(tempward.getLastupdatedby());
			warddeta.setCreatedon(tempward.getCreatedon());
			warddeta.setCreatedby(tempward.getCreatedby());
			if ("Yes".equalsIgnoreCase(isUpdate)) {
				warddeta.setIsactive(tempward.isIsactive());
			} else {
				warddeta.setIsactive(false);
			}

			session.update(warddeta);
			session.getTransaction().commit();
			// session.flush();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	public List<LocalbodyWard> getlocalGovtBodyWardListforpaginationtempdelete(int localBodyCode, int offset, int limit) {
		Session session = null;
		List<LocalbodyWard> lGBWard = null;
		Query query = null;
		Query query1 = null;
		try {

			session = sessionFactory.openSession();
			query1 = (SQLQuery) session.createSQLQuery("select b.lblc from localbody  b where b.isactive =true and b.local_body_code=" + localBodyCode);

			int lblc = (Integer) query1.uniqueResult();
			query = session.createQuery("from LocalbodyWard l where l.lblc =:localBodyCode and l.isactive=false").setParameter("localBodyCode", lblc, Hibernate.INTEGER);
			/*
			 * query = session.getNamedQuery("getLocalGovtBodyWardListViewtemp")
			 * .setParameter("lblc", localBodyCode).setFirstResult(offset)
			 * .setMaxResults(limit);
			 */
			lGBWard = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lGBWard;
	}

	@SuppressWarnings("unchecked")
	public boolean getexistingwardinTempTable(Integer lblc) {
		Session session = null;
		List<localbodywardtemp> lGBWard = null;
		Query query = null;
		try {

			session = sessionFactory.openSession();
			query = session.createQuery("from localbodywardtemp l where l.wardCode =:ward_code and l.isactive=true").setParameter("ward_code", lblc, Hibernate.INTEGER);
			/*
			 * query = session.getNamedQuery("getLocalGovtBodyWardListViewtemp")
			 * .setParameter("lblc", localBodyCode).setFirstResult(offset)
			 * .setMaxResults(limit);
			 */
			lGBWard = query.list();
			if (lGBWard.size() > 0) {
				return false;
			} else {
				return true;
			}

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return false;

	}

	@SuppressWarnings("unchecked")
	public Integer restoreWard(Integer wardCd) {
		Session session = null;
		//List<localbodywardtemp> lGBWard = null;
		List<LocalbodyWard> lGBWard1 = null;
		LocalbodyWard wardupdate = new LocalbodyWard();
		Query query = null;
		//Query query1 = null;
		Integer status = 0;
		try {

			session = sessionFactory.openSession();
			session.beginTransaction();

			query = session.createQuery("from LocalbodyWard l where l.wardCode =:localBodyCode and l.isactive=false").setParameter("localBodyCode", wardCd);

			lGBWard1 = query.list();

			if (lGBWard1.size() > 0) {
				wardupdate = lGBWard1.get(0);
				wardupdate.setIsactive(true);
				session.update(wardupdate);
			}

			session.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return status;
	}

	/*@Override
	public List<GetLocalGovtSetup> getLocalGovSetupbyParentTierSetupCode(Integer stateCode, String category, Integer parentTierSetupCode) {
		List<GetLocalGovtSetup> getLocalGovtSetupList = null;
		Session session = null;
		try {

			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("getLocalGovSetupbyParentTierSetupCode");
			query.setParameter("stateCode", stateCode);
			query.setParameter("category", category);
			query.setParameter("parentTierSetupCode", parentTierSetupCode);
			getLocalGovtSetupList = query.list();

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getLocalGovtSetupList;

	}*/

	/* added by Kirandeep on 1/11/2014 for ward */
	@SuppressWarnings("unchecked")
	public List<Localbody> checkForExistingGp(String str) {
		Session session = null;
		List<Localbody> getStateTopHierarchyGta = new ArrayList<Localbody>();
		List<Object[]> wardList = null;
		List<MapZpWardContituency> listVilaage = new ArrayList<MapZpWardContituency>();
		try {
			session = sessionFactory.openSession();
			// str=str.replaceAll("MAPD", "");
			// select * from covered_zpward_landregion where ward_code in
			// ("+str+") and coverage_lc_type='V'
			// String
			// queryBuilder1="select * from covered_zpward_landregion where ward_code in ("+str+") and coverage_lc_type='V'";
			Query query1 = session.createQuery("from MapZpWardContituency where ward_code in(" + str + ") and coverage_lc_type='V'");
			listVilaage = query1.list();

			String villageList = "0";
			for (MapZpWardContituency zpwardmapped : listVilaage) {

				villageList = villageList + "," + zpwardmapped.getCoverage_lc();

			}

			String queryBuilder = "select distinct lb.local_body_code ,lb.local_body_name_english from localbody lb,lb_covered_landregion cov" + " where lb.lb_covered_Region_code=cov.lb_covered_Region_code" + " and lb.isactive"
					+ " and cov.lrlc in (" + villageList + ")"

					+ " and cov.land_region_type='V' ";
			Query query = session.createSQLQuery(queryBuilder);
			wardList = query.list();
			for (Object[] ob : wardList) {
				Localbody localbody = new Localbody();
				localbody.setLocalBodyCode((Integer) ob[0]);
				localbody.setLocalBodyNameEnglish((String) ob[1]);
				getStateTopHierarchyGta.add(localbody);
			}
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return getStateTopHierarchyGta;
	}

	/* added by Ashish Dhupia on 20/1/2015 for Habitation use case */
	@SuppressWarnings("unchecked")
	public boolean saveHabitation(Habitation habitationDet) {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unused")
		List<Habitation> hab = new ArrayList<Habitation>();
		try {

			Timestamp effectivetimeStampDate = null, createdOnStampDate = null, lastupdatedStampDate = null;
			if (habitationDet.getEffectiveDate() != null) {
				effectivetimeStampDate = new Timestamp(habitationDet.getEffectiveDate().getTime());
			}
			if (habitationDet.getCreatedon() != null) {
				createdOnStampDate = new Timestamp(habitationDet.getCreatedon().getTime());
			}
			if (habitationDet.getLastupdated() != null) {
				lastupdatedStampDate = new Timestamp(habitationDet.getLastupdated().getTime());
			}
			SQLQuery query = session.createSQLQuery("select * from set_habitation_fn(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			query.setParameter(0, 0);
			query.setParameter(1, habitationDet.getHabitationVersion());
			query.setParameter(2, habitationDet.getHabitationNameEnglish());
			query.setParameter(3, habitationDet.getHabitationNameLocal());
			query.setParameter(4, habitationDet.getParentType());
			query.setParameter(5, habitationDet.getParentCode());
			query.setParameter(6, habitationDet.getIsactive());
			query.setParameter(7, effectivetimeStampDate);
			query.setParameter(8, habitationDet.getCreatedby());
			query.setParameter(9, createdOnStampDate);
			query.setParameter(10, habitationDet.getLastupdatedby());
			query.setParameter(11, lastupdatedStampDate);
			query.setParameter(12, 0);
			query.setParameter(13, 0);
			query.setParameter(14, habitationDet.getSscode());
			hab = query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	public List<LocalbodyforStateWise> gethierarchyforGP(char lbTypeCode, int stateCode) throws Exception {
		Session session = null;
		Query query = null;
		List<LocalbodyforStateWise> traditionalLocalbodyTypeList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getLocalBodylistStateWiseForTraditionalforP").setParameter("stateCode", stateCode).setParameter("localBodyType", lbTypeCode);
			traditionalLocalbodyTypeList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return traditionalLocalbodyTypeList;
	}

	

	@SuppressWarnings("unchecked")
	@Override
	public List<LocalBodyHistory> getLocalBodyHistoryReport(char localBodyNameEnglish, int localBodyCode) throws BaseAppException {
		// TODO Auto-generated method stub
		Session session = null;

		SQLQuery query = null;
		List<LocalBodyHistory> localBodyHistList = new ArrayList<LocalBodyHistory>();

		try {
			session = sessionFactory.openSession();

			query = session.createSQLQuery("select * from get_land_region_history_fn_for_citizen(?,?)");

			query.setParameter(0, localBodyNameEnglish);
			query.setParameter(1, localBodyCode);
			query.addEntity(LocalBodyHistory.class);
			localBodyHistList = query.list();

			return localBodyHistList;

		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return localBodyHistList;

	}

	@SuppressWarnings("unchecked")
	public List<LocalbodyListbyState> getTopLocalbodyListforDistrictUser(Integer stateCode, Integer lbTypeCode, Integer districtCode) throws Exception {
		List<LocalbodyListbyState> localbodyListbyStateList = null;
		Session session = null;

		try {
			session = sessionFactory.openSession();
			StringBuilder queryBuild = new StringBuilder("SELECT  case when lb.local_body_code  in (select * from get_draft_used_lb_lr_temp(lb.local_body_code,'G')) then  cast ('F' as character) "
					+ "else cast('A' as character) end as operation_state,local_body_code as localBodyCode,local_body_name_english as localBodyNameEnglish from get_lb_list_fn(:lbTypeCode,:stateCode) "
					+ "lb where lb.local_body_code in(select local_body_code from localbody_districts   where district_code =:districtCode ) ");

			SQLQuery query = session.createSQLQuery(queryBuild.toString());

			query.setParameter("stateCode", stateCode);
			query.setParameter("lbTypeCode", lbTypeCode);
			query.setParameter("districtCode", districtCode);

			query.addScalar("operation_state");
			query.addScalar("localBodyCode");
			query.addScalar("localBodyNameEnglish");
			query.setResultTransformer(Transformers.aliasToBean(LocalbodyListbyState.class));
			localbodyListbyStateList = query.list();

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return localbodyListbyStateList;
	}

	@SuppressWarnings("unchecked")
	public List<LocalbodyListbyState> getchildlocalbodiesWithoutCountByParentcodeDistrict(Integer lbCode, Integer districtCode) throws Exception {
		List<LocalbodyListbyState> localbodyListbyStateList = null;
		Session session = null;

		try {
			session = sessionFactory.openSession();
			StringBuilder queryBuild = new StringBuilder("SELECT case when lb.local_body_code  in (select * from get_draft_used_lb_lr_temp(lb.local_body_code,'G')) then  cast ('F' as character) "
					+ "else cast('A' as character) end as operation_state,local_body_code as localBodyCode,local_body_name_english as localBodyNameEnglish  FROM get_parentwise_lb_list_without_child_count(:lbCode)"
					+ " lb where lb.local_body_code in(select local_body_code from localbody_districts  where district_code =:districtCode )");

			SQLQuery query = session.createSQLQuery(queryBuild.toString());

			query.setParameter("lbCode", lbCode);
			query.setParameter("districtCode", districtCode);

			query.addScalar("operation_state");
			query.addScalar("localBodyCode");
			query.addScalar("localBodyNameEnglish");
			query.setResultTransformer(Transformers.aliasToBean(LocalbodyListbyState.class));
			localbodyListbyStateList = query.list();

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return localbodyListbyStateList;
	}

	/**
	 * For getting Ward List by LocalBodyCode
	 * 
	 * @author Pooja 29-07-2015
	 * @param localBodyCode
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LocalbodyWard> getWardListByLbCode(Integer localBodyCode) throws Exception {
		Session session = null;
		SQLQuery query = null;
		List<LocalbodyWard> wardList = new ArrayList<LocalbodyWard>();

		try {
			session = sessionFactory.openSession();
			query = session.createSQLQuery("select lbw.ward_code as wardCode,lbw.ward_name_english as wardNameEnglish,lbw.lblc as lblc "
					+ " from localbody_ward lbw,localbody lb where lbw.lblc=lb.lblc and lb.local_body_code=:localBodyCode and lb.isactive and lbw.isactive");

			query.setParameter("localBodyCode", localBodyCode);
			query.addScalar("wardCode");
			query.addScalar("wardNameEnglish");
			query.addScalar("lblc");
			query.setResultTransformer(Transformers.aliasToBean(LocalbodyWard.class));
			wardList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return wardList;
	}

	/**
	 * For getting CoveredArea List of LocalBodyCodes
	 * 
	 * @author Pooja Sharma (02-09-2015)
	 * @param localBodyCodes
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LocalGovtBodyNameList> getCoveredAreaListByLbCodes(String localBodyCodes) throws Exception {
		Session session = null;
		Query query = null;
		List<LocalGovtBodyNameList> coveredAreaList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("getCoveredAreaListOfLB").setParameter("localBodyCodes", localBodyCodes);
			coveredAreaList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return coveredAreaList;
	}

	@SuppressWarnings("unchecked")
	public List<LBTypeHierarchy> getLBHierarchyList(Integer stateCode, String PANCHAYAT_TYPE) throws Exception {
		Session session = null;
		List<LBTypeHierarchy> lbTypeHierarchyList = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("Dynamic_Local_body_Hierarchy");
			query.setParameter("stateCode", stateCode).setParameter("localBodyType", PANCHAYAT_TYPE, Hibernate.STRING);
			lbTypeHierarchyList = query.list();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}

		return lbTypeHierarchyList;
	}

	public boolean saveTempWards(List<localbodywardtemp> insertListWard, List<localbodywardtemp> updateListWard, List<localbodywardtemp> deleteListWard) throws Exception {
		Session session = null;
		Transaction tx = null;
		boolean resultFlag = false;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			if (insertListWard != null && !insertListWard.isEmpty()) {
				for (localbodywardtemp localbodyWard : insertListWard) {
					localbodyWard.setWardCode(0);
					localbodyWard.setWardVersion(0);
					localbodyWard.setMapCode(0);
					localbodyWard.setIsactive(true);
					localbodyWard.setIsdelete(false);
					localbodyWard.setIsupdate(false);
					localbodyWard.setIsnew(true);
					session.save(localbodyWard);
					session.flush();
				}
			}
			if (updateListWard != null && !updateListWard.isEmpty()) {
				for (localbodywardtemp localbodyWard : updateListWard) {
					localbodyWard.setIsdelete(false);
					localbodyWard.setIsupdate(true);
					localbodyWard.setIsnew(false);
					session.save(localbodyWard);
					session.flush();
				}
			}
			if (deleteListWard != null && !deleteListWard.isEmpty()) {
				for (localbodywardtemp localbodyWard : deleteListWard) {
					localbodyWard.setIsdelete(true);
					localbodyWard.setIsupdate(false);
					localbodyWard.setIsnew(false);
					session.save(localbodyWard);
					session.flush();
				}
			}
			tx.commit();
			resultFlag = true;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return resultFlag;
	}

	@SuppressWarnings("unchecked")
	public boolean publishWards(WardForm wardForm) throws Exception {
		Session session = null;
		boolean resultFlag = false;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			List<localbodywardtemp> localbodyWardTempList = null;
			String publisWards = wardForm.getPublishWardsList();
			Integer wardCode=null;
			if (publisWards.length() > 0) {
				List<Integer> publishWardList = new ArrayList<Integer>();
				Scanner scanner = new Scanner(publisWards);
				scanner.useDelimiter(",");
				while (scanner.hasNext()) {
					publishWardList.add(Integer.parseInt(scanner.next()));
				}
				scanner.close();
				Query query = session.createQuery("from localbodywardtemp  where temp_ward_code in(:publishWardList)");
				query.setParameterList("publishWardList", publishWardList);
				localbodyWardTempList = query.list();
				session.flush();
				session.clear();

				query = session.createSQLQuery("select max(ward_code)+1 from localbody_ward");
				wardCode = (Integer) query.uniqueResult();
				session.flush();
				session.clear();
			}

			String deleteWards = wardForm.getDeleteWardsList();
			//Integer updateRow = 1;

			if (deleteWards.length() > 0) {
				List<Integer> deleteWardList = new ArrayList<Integer>();
				Scanner scanner = new Scanner(deleteWards);
				scanner.useDelimiter(",");
				while (scanner.hasNext()) {
					deleteWardList.add(Integer.parseInt(scanner.next()));
				}
				scanner.close();
				Query query = session.createQuery("delete from localbodywardtemp  where temp_ward_code in(:deleteWardList)");
				query.setParameterList("deleteWardList", deleteWardList);
				//updateRow = 
				query.executeUpdate();
				session.flush();
				session.clear();
			}

			
			if (localbodyWardTempList != null && !localbodyWardTempList.isEmpty()) {
				LocalbodyWard localbodyWard = null;
				LocalbodyWardId localbodyWardId=null;
				for (localbodywardtemp obj : localbodyWardTempList) {
					localbodyWard = new LocalbodyWard();
					BeanUtils.copyProperties(localbodyWard, obj);
					localbodyWardId=new LocalbodyWardId();
					if(obj.getWardCode()==0){
						localbodyWardId.setWardCode(wardCode);
						localbodyWardId.setWardVersion(1);
					}else{
						localbodyWardId.setWardCode(obj.getWardCode());
						localbodyWardId.setWardVersion(obj.getWardVersion()-1);
						LocalbodyWard localbodyWardPre=(LocalbodyWard)session.get(LocalbodyWard.class, localbodyWardId);
						
						localbodyWardPre.setIsactive(false);
						session.update(localbodyWardPre);
						session.flush();
						session.clear();
						localbodyWardId.setWardVersion(obj.getWardVersion());
					}
					//localbodyWard.setId(localbodyWardId);
					session.saveOrUpdate(localbodyWard);
					session.delete(obj);
					session.flush();
				}
			}

			tx.commit();
			resultFlag = true;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return resultFlag;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<localbodywardtemp> getLocalbodyWardTempList(Integer lblc)throws Exception{
		Session session=null;
		List<localbodywardtemp> localbodywardtempList=null;
		try{
			session=sessionFactory.openSession();
			Query query=session.createQuery("from localbodywardtemp where isactive=true and lblc=:lblc");
			query.setParameter("lblc", lblc);
			localbodywardtempList=query.list();	
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return 	localbodywardtempList;
	}
	
	@SuppressWarnings("unchecked")
	public List<LocalbodyWard> getLocalbodyWardList(Integer lblc)throws Exception{
		Session session=null;
		List<LocalbodyWard> localbodyWardList=null;
		try{
			session=sessionFactory.openSession();
			Query query=session.createQuery("from LocalbodyWard where isactive=true and lblc=:lblc");
			query.setParameter("lblc", lblc);
			localbodyWardList=query.list();	
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return 	localbodyWardList;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<LBTypeDetails> getLBTypeDetailsList(Integer stateCode,String PANCHAYAT_TYPE) throws Exception{
		Session session=null;
		List<LBTypeDetails> lbTypeDetailsList=null;
		try{
			session=sessionFactory.openSession();
			Query query = session.getNamedQuery("Urban_Local_body_Type_Details");
			query.setParameter("stateCode", stateCode).setParameter("panchayatType", PANCHAYAT_TYPE, Hibernate.STRING);
			lbTypeDetailsList = query.list();
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lbTypeDetailsList;
	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LocalbodyListbyState> getUrbanListbyLocalbodyTypeList(String lbTypeCode, int stateCode) throws Exception {
		Session session = null;
		Query query = null;
		List<LocalbodyListbyState> districtPanchayatList = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("GET_URBAN_LIST_BY_LOCAL_BODY_TYPE_LIST").setParameter("lbTypeCode", lbTypeCode).setParameter("stateCode", stateCode);
			districtPanchayatList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return districtPanchayatList;
	}
	
	@Override
	public Object[] getUrbanListbyAdminEntity(Integer adminEntityCode,Integer stateCode,String LbTypeCategory)throws Exception{
		Object objectUrbanList[]=null;
		Session session = null;
		try{
			session = sessionFactory.openSession();
			objectUrbanList=new Object[2];
			Query query = session.getNamedQuery("GET_ULB_TYPE_LIST_WITHOUT_COVERAGE");
			query.setParameter("stateCode", stateCode).setParameter("adminEntityCode", adminEntityCode).setParameter("LbTypeCategory", LbTypeCategory);
			objectUrbanList[0] = query.list();
			
			query = session.getNamedQuery("GET_ULB_TYPE_LIST_WITH_COVERAGE");
			query.setParameter("stateCode", stateCode).setParameter("adminEntityCode", adminEntityCode).setParameter("LbTypeCategory", LbTypeCategory);
			objectUrbanList[1] = query.list();
			
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		
		return objectUrbanList;
	}

	@Override
	public List<ConstituencyLocalbody> getlbListforConstituencyMap(String lbType,Integer lbTypeCode, Integer stateCode, Integer parentlbCode,Integer districtCode, List<String> lbFull,List<Integer> lbPart,final Boolean forDropdown) throws Exception {
		Session session = null;
		List<ConstituencyLocalbody> ConstituencyLocalbodyList = new ArrayList<ConstituencyLocalbody>();
		try {
			session = sessionFactory.openSession();
			
			List<Integer> lbFullList = new ArrayList<>();
			

			for (String lbarray : lbFull) {
				if(!lbarray.equals("")) {
					lbFullList.add(Integer.parseInt(lbarray.split("_")[0]));
				}else {
					lbFullList.add(Integer.parseInt(-1+""));
				}
			}
			
			Query query = session.getNamedQuery("getParentLevelLocalbody");
			query.setParameter("lbType", lbType, Hibernate.CHARACTER);
			
			if(parentlbCode==null){
				query.setParameter("parentlbCode", null, Hibernate.INTEGER);
			}else{
				query.setParameter("parentlbCode", parentlbCode, Hibernate.INTEGER);
			}
			query.setParameter("lbTypeCode", lbTypeCode, Hibernate.INTEGER);
			query.setParameter("stateCode", stateCode, Hibernate.INTEGER);
			query.setParameter("districtCode",districtCode, Hibernate.INTEGER);
			query.setParameterList("lbFull", lbFullList);
			query.setParameterList("lbPart", lbPart);
			
			ConstituencyLocalbodyList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return ConstituencyLocalbodyList;
	}

	@Override
	public List<ConstituencyVillage> getVillageListforConstituencyMap(List<Integer> lbPart) throws Exception {
		Session session = null;
		List<ConstituencyVillage> constituencyVillageList = new ArrayList<ConstituencyVillage>();
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("getConstituencyVillage");
			query.setParameterList("lbPart", lbPart);
			constituencyVillageList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return constituencyVillageList;
	}
	
	
	@Override
	public List<ConstituencyMapDetailsbyacCode> getConstituencyMapDetails(Integer acCode) throws Exception {
		Session session = null;
		List<ConstituencyMapDetailsbyacCode> constituencyMapDetailsbyacCodeList = new ArrayList<ConstituencyMapDetailsbyacCode>();
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("getConstituencyMapDetailsbyacCode");
			query.setParameter("acCode", acCode);
			constituencyMapDetailsbyacCodeList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return constituencyMapDetailsbyacCodeList;
	}
	
	@Override
	public List<ConstituencyWard> getWardforConstituencyMap(List<Integer> lblcCodeList,List<Integer> wardCodeList) throws Exception {
		Session session = null;
		List<ConstituencyWard> constituencyWardList = new ArrayList<ConstituencyWard>();
		try {
			session = sessionFactory.openSession();
			Query query = session.getNamedQuery("getWardListbylblc");
			query.setParameterList("lblcCodeList", lblcCodeList);
			query.setParameterList("wardCodeList", wardCodeList);
			constituencyWardList = query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return constituencyWardList;
	}
	
	/**
	 * @author Sunita Dagar
	 * @Date 18-11-2016
	 */
	@SuppressWarnings("unused")
	@Override
	public boolean saveNodalOfficerDetails(NodalOfficerForm nodalOfficerForm) throws Exception {
		Session session = null;
		boolean saveFlag=false;
		NodalOfficer nodalOfficer = new NodalOfficer();
		Timestamp date = new Timestamp(System.currentTimeMillis());
		try {
			session = sessionFactory.openSession();
			NodalOfficerPK nodalOfficerPK = new NodalOfficerPK();
			if(nodalOfficerForm.getNodalUserId()==null && nodalOfficerForm.getNodalUserVersion()==null){
			Query query=session.createSQLQuery("select max(nodal_user_id) from nodal_user_constituency ");
			Object abc =(Object) query.list().get(0);
			if(abc==null){
				nodalOfficerPK.setNodalUserId(1);
				nodalOfficerPK.setNodalUserVersion(1);
				nodalOfficer.setCreatedOn(date);
			} else{
			nodalOfficerPK.setNodalUserId(Integer.parseInt(abc.toString())+1);
			nodalOfficerPK.setNodalUserVersion(1);
			nodalOfficer.setCreatedOn(date);
			}
		} else{
				nodalOfficerPK.setNodalUserId(nodalOfficerForm.getNodalUserId());
				nodalOfficerPK.setNodalUserVersion(nodalOfficerForm.getNodalUserVersion()+1);
				nodalOfficer.setCreatedOn(nodalOfficerForm.getCreatedOn());
			}
			
			nodalOfficer.setNodalOfficerPK(nodalOfficerPK);
			nodalOfficer.setNodalUserName(nodalOfficerForm.getNodalUserName());
			nodalOfficer.setCreatedBy(nodalOfficerForm.getCreatedBy());
			nodalOfficer.setNodalUserDesignation(nodalOfficerForm.getNodalUserDesignation());
			nodalOfficer.setNodalUserDistrict(nodalOfficerForm.getNodalUserDistrict());
			nodalOfficer.setNodalUserEmail(nodalOfficerForm.getNodalUserEmail());
			nodalOfficer.setNodalUserMobile(nodalOfficerForm.getNodalUserMobile());
			nodalOfficer.setLastUpdatdOn(date);
			nodalOfficer.setLastUpdatedBy(nodalOfficerForm.getCreatedBy());
			nodalOfficer.setIsactive(true);
			session.merge(nodalOfficer);
			session.flush();
			session.clear();
			saveFlag=true; 	
		}
		catch (Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return saveFlag;
	}

	@Override
	public List<NodalOfficer> getNodalOfficerDetails(Integer userId, Integer nodalUserDistrict) {
		Session session = null;
		List<NodalOfficer> nodalOfficerDetailsList = null;
		try{
			session = sessionFactory.openSession();
			Query query = session.createQuery(
					"select model from NodalOfficer model where model.createdBy=:userId and model.nodalUserDistrict=:nodalUserDistrict and model.isactive=:isactive");
			query.setParameter("userId",userId);
			query.setParameter("nodalUserDistrict",nodalUserDistrict);
			query.setParameter("isactive",true);
			nodalOfficerDetailsList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return nodalOfficerDetailsList;
	}

	@Override
	public boolean UpdateIsactiveStatue(int createdBy, Integer nodalUserDistrict) {
		Session session=null;
		boolean updateFlag=false;
		List<NodalOfficer> nodalList=new ArrayList<NodalOfficer>();
		NodalOfficer nodal=new NodalOfficer();
		try{
			session = sessionFactory.openSession();
			Query query=session.createQuery("select model from NodalOfficer model where model.createdBy=:createdBy and model.nodalUserDistrict=:nodalUserDistrict and model.isactive=:isactive");
			query.setParameter("createdBy", createdBy);
			query.setParameter("nodalUserDistrict", nodalUserDistrict);
			query.setParameter("isactive",true);
			nodalList=query.list();
			if(nodalList.size()>0){
				nodal=nodalList.get(0);
				nodal.setIsactive(false);
				session.merge(nodal);
			}
			session.flush();
			session.clear();
			updateFlag=true; 		
			
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return updateFlag;
	}
	
	
	@Override
	public List<LocalBodyCoveredArea> getCoveredVillagesofLocalbodies(String localBodyCodes)throws Exception{
		Session session=null;
		List<LocalBodyCoveredArea> localBodyCoveredAreaList=null;
		try{
			session = sessionFactory.openSession();
			Query query=session.getNamedQuery("COVERED_VILLAGES_OF_LOCALBODIES");
			query.setParameter("localBodyCodes", localBodyCodes);
			localBodyCoveredAreaList=query.list();
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		} finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return localBodyCoveredAreaList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LocalbodyforStateWiseFinal> getLocalBodyListforGisStatus(Integer localBodyType, Integer stateCode, Integer plblc, Integer lbCode, String lbName) throws Exception {
		Session session = null;
		List<LocalbodyforStateWiseFinal> localbodyList = null;
		Query query = null;
		try {
			session = sessionFactory.openSession();
			query = session.getNamedQuery("viewLocalbodyGisStatus").setParameter("stateCode", stateCode, Hibernate.INTEGER).setParameter("localBodyType", localBodyType, Hibernate.INTEGER)
					.setParameter("localBodyCode", lbCode, Hibernate.INTEGER).setParameter("parentLblc", plblc, Hibernate.INTEGER).setParameter("localBodyName", lbName, Hibernate.STRING);
			localbodyList = query.list();

		} catch (Exception e) {
			log.error("Exception" + e);
			throw e;
		} finally {

			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return localbodyList;

	}
	
	@Override
	public Date getExistingLBEffectiveDate(Integer localBodyCode)throws HibernateException{
		Date iParamEffectiveDate=null;
		// TODO Auto-generated method stub
				log.info("LocalBodyDaoImpl.getLBObjectForRename execution started.");
				Session session = null;
				LocalBodyTable lbEntiity = new LocalBodyTable();
				try {
					session = sessionFactory.openSession();
					Criteria criteria = session.createCriteria(LocalBodyTable.class);
					criteria.add(Restrictions.eq("localBodyCode", localBodyCode));
					criteria.add(Restrictions.eq("isactive", Boolean.TRUE));
					lbEntiity = (LocalBodyTable) criteria.uniqueResult();
					iParamEffectiveDate=lbEntiity.getEffectiveDate();
				} catch (HibernateException e) {
					// TODO: handle exception
					log.error("Error in LocalBodyDaoImpl.getLBObjectForRename : ", e);
					throw e;
				} finally {
					if (session != null && session.isOpen()) {
						session.close();
					}
				}
				return iParamEffectiveDate;
	}
	
	@Override
	public String InvalidateUrbanLocalbodyProcess(LocalGovtBodyForm lbForm)throws Exception{
		Session session=null;
		String retVal="";
		Integer orderCode=0;
		Integer transactionid=0;
		List<Attachment> attachments=null;
		try{
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			retVal=InvalidateUrabnLocalbodyFn(lbForm,session);
			
			if (retVal != null) {
				String[] ldata = retVal.split(",");
				String vc = ldata[0];
				String tid = ldata[1];
				orderCode = Integer.parseInt(vc);
				transactionid = Integer.parseInt(tid);
			}
			
			if(orderCode>0 ){
				if (lbForm.getGovtOrderConfig().equals("govtOrderUpload")) {
					List<CommonsMultipartFile> files = lbForm.getGazettePublicationUpload();
					attachments = localBodyUtil.uploadFileToServer(files, Long.parseLong(LocalBodyConstant.ATTACHMENT_MASTER_GO.toString()));
				}
				
				if (lbForm.getGovtOrderConfig().equals("govtOrderUpload") && !attachments.isEmpty()) {
					Attachment attachment = attachments.get(0);
					GovernmentOrder govtOrder = new GovernmentOrder();
					govtOrder.setOrderCode(orderCode);
					attachment.setGovernmentOrder(govtOrder);
					session.save(attachment);
					session.flush();
					session.clear();
					} else if (lbForm.getGovtOrderConfig().equals("govtOrderGenerate")) {
					
					//template functionality disable for this form
				}
				
			}
			try{
			if(transactionid>0){
			  EmailSmsThread est = new EmailSmsThread(transactionid, 'U', emailService);
			  est.start();
			  }
			}catch(Exception e){}

			tx.commit();
			
			
		}finally {

			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return retVal;
	}
	

	public String InvalidateUrabnLocalbodyFn(LocalGovtBodyForm lbForm,Session session) throws Exception{
		
	Query query = session.getNamedQuery("invalidate_prilb_fn");
	String retVal="";
    Integer lbTypeCode=lbForm.getLocalBodyTypeCode()!=null && !("").equals(lbForm.getLocalBodyTypeCode().trim())?Integer.parseInt(lbForm.getLocalBodyTypeCode()):0;
	query.setParameter("local_body_code", lbForm.getLocalBodyCode(), Hibernate.INTEGER)
		 .setParameter("local_body_name", lbForm.getLocalBodyNameEnglish(), Hibernate.STRING)
		 .setParameter("userId", lbForm.getUserId(), Hibernate.INTEGER)
		 .setParameter("shift", "N", Hibernate.STRING)
		 .setParameter("effectiveDate", lbForm.getEffectiveDate(), Hibernate.DATE)
		 .setParameter("govtOrder", lbForm.getOrderNo(), Hibernate.STRING)
		 .setParameter("orderDate", lbForm.getOrderDate(), Hibernate.DATE)
		 .setParameter("local_body_type_code", lbTypeCode, Hibernate.INTEGER)
		 .setParameter("operation_code", lbForm.getOperationCode(), Hibernate.INTEGER)
		 .setParameter("flag_code", lbForm.getFlagCode(), Hibernate.INTEGER)
		 .setParameter("gzbDate", lbForm.getGazPubDate(), Hibernate.DATE)
		 .setParameter("order_path", lbForm.getOrderPath(), Hibernate.STRING)
		 .setParameter("lblist", lbForm.getListformat(), Hibernate.STRING)
		 .setParameter("unResolvedFlag", lbForm.isUnResolvedFlag(), Hibernate.BOOLEAN);
	
	List<invalidatePRIlb> result =query.list();
	if (result.size() > 0) {
		retVal = result.get(0).getInvalidatePRILB();
	}
	return retVal;
	}
	
	@Override
	public LocalBodyTable getMaxVersionLocabody(Integer localbodyCode)throws Exception{
		Session session=null;
		Query query=null;
		LocalBodyTable localbody=null;
		try{
		session = sessionFactory.openSession();
		query = session.createSQLQuery("select COALESCE(max(local_body_version),1) from Localbody where  local_body_code=:localbodyCode");
		query.setParameter("localbodyCode", localbodyCode, Hibernate.INTEGER);
		Integer lbMaxVersion =(Integer) query.uniqueResult();
		session.flush();
		session.clear();
		
		query = session.createSQLQuery("select COALESCE(max(minor_version),1) from Localbody where  local_body_code=:localbodyCode and local_body_version=:lbMaxVersion");
		query.setParameter("localbodyCode", localbodyCode, Hibernate.INTEGER);
		query.setParameter("lbMaxVersion", lbMaxVersion, Hibernate.INTEGER);
		Integer lbMaxMinorVersion =(Integer) query.uniqueResult();
		session.flush();
		session.clear();
	
		Criteria criteria = session.createCriteria(LocalBodyTable.class);
		criteria.add(Restrictions.eq("localBodyCode", localbodyCode));
		criteria.add(Restrictions.eq("localBodyVersion",lbMaxVersion));
		criteria.add(Restrictions.eq("minorVersion",lbMaxMinorVersion));
		localbody = (LocalBodyTable) criteria.uniqueResult();
		
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return localbody;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LgdPfmsMapping> getLdgPfmsMapping(Integer districtCode) {
		List<LgdPfmsMapping> lgdPfmsMapping=new ArrayList<>();
		Session session=null;
		try{
			session=sessionFactory.openSession();
			Query query = session.getNamedQuery("FETCH_LDG_PFMS_MAPPING");
			query.setParameter("districtCode", districtCode);
			lgdPfmsMapping=query.list();
		}
		
		
		finally {
			if (session!=null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		
		return lgdPfmsMapping;
	}

	@Override
	public boolean updateVerifiedStatus(Integer lgdPfmsId,Integer userId) {
		Session session=null;
		Boolean updateFlag=false;
		
		try{
			session=sessionFactory.openSession();
			
			LgdPfmsMapping lgdPfmsMapping=(LgdPfmsMapping)session.get(LgdPfmsMapping.class, lgdPfmsId);
			lgdPfmsMapping.setVerifiedStatus(true);	
			lgdPfmsMapping.setVerifiedOn(new Date());
			lgdPfmsMapping.setVerifiedBy(userId);
			session.update(lgdPfmsMapping);	
			session.flush();
			updateFlag=true;
		} catch(Exception e){
			log.error("LocalGovtBodyDAOImpl-->updateVerifiedStatus"+e);
		} finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return updateFlag;
	}
	


}