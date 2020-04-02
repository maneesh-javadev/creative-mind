package in.nic.pes.lgd.dao.impl;

import in.nic.pes.lgd.bean.ConfigurationSubdistrict;
import in.nic.pes.lgd.bean.NomenclatureSubdistrict;
import in.nic.pes.lgd.bean.NomenclatureSubdistrictPK;
import in.nic.pes.lgd.dao.NomenclatureSubDistDAO;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class NomenclatureSubDistImpl implements NomenclatureSubDistDAO {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean addNomenclatureSudDist(
			NomenclatureSubdistrict nomenclatureSubdistrict, Session session)
			throws Exception {

		try {
			/*session.persist(nomenclatureSubdistrict);
			session.flush();
			session.refresh(nomenclatureSubdistrict);*/
			session.save(nomenclatureSubdistrict);
		} catch (Exception e) {
	
			return false;
		}
		return true;
	}

	// to save data in configure sub-district

	@Override
	public boolean addConfigSudDist(
			ConfigurationSubdistrict configurationSubdistrict, Session session)
			throws Exception {
		try {
			session.persist(configurationSubdistrict);
			session.flush();
			session.refresh(configurationSubdistrict);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public int getMaxRecords(String query) throws Exception {
		int MaxCode = 0;
		try {
			MaxCode = Integer.parseInt(sessionFactory.getCurrentSession()
					.createSQLQuery(query).list().get(0).toString());
		} catch (Exception e) {
			return MaxCode + 1;
		}
		MaxCode++;
		return MaxCode;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NomenclatureSubdistrict> getNomenclatureDetails(int slc) throws Exception {
		Query criteria = null;
		Session session = null;
		List<NomenclatureSubdistrict> nomenclatureSubdistrictList = null;
		try {
			session = sessionFactory.openSession();
			
			criteria = session.createQuery("from NomenclatureSubdistrict ns where ns.slc=:slc and isactive=true");
			criteria.setParameter("slc", slc, Hibernate.INTEGER);
			nomenclatureSubdistrictList = criteria.list();
		} catch (HibernateException e) {
			return null;
		} finally {
			if (session != null && session.isOpen()){
				session.close();
			}
		}
		return nomenclatureSubdistrictList;
 
	}

	@Override
	public boolean nomenclatureInactivate(
			NomenclatureSubdistrictPK nomenclatureSubdistrictPK, Session session)
			throws Exception {
		// TODO nomenclatureInactivate
		try {
			NomenclatureSubdistrict nomenclatureSubdistrict = (NomenclatureSubdistrict) session
					.load(NomenclatureSubdistrict.class,
							nomenclatureSubdistrictPK);
			nomenclatureSubdistrict.setIsactive(false);
			session.update(nomenclatureSubdistrict);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
