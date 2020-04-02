package in.nic.pes.lgd.dao.impl;

import in.nic.pes.lgd.bean.CitizenFeedback;
import in.nic.pes.lgd.dao.CititzenSectionDAO;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CitizenSectionDAOImpl implements CititzenSectionDAO {
	private static final Logger log=Logger.getLogger(CitizenSectionDAOImpl.class);
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Boolean saveFeedback(CitizenFeedback citizenFeedback) {
	log.info("Info:saveFeedback init()..");	
	Session session=null;
	Boolean flag=true;
	try{
		session=sessionFactory.openSession();
		session.getTransaction().begin();
		session.save(citizenFeedback);
		session.getTransaction().commit();
		
	}catch(Exception e){
	log.equals("Exception:"+e);
	session.getTransaction().rollback();
	flag=false;
	}finally{
		if(session!=null && session.isOpen()){
			session.close();
		}
	}
	return flag;
	}
}
