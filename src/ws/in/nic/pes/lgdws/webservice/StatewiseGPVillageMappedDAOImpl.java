package  ws.in.nic.pes.lgdws.webservice;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class StatewiseGPVillageMappedDAOImpl implements StatewiseGPVillageMappedDAO{

	private static final Logger log = Logger.getLogger(StatewiseGPVillageMappedDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public StateWiseGPVillageMappedEntity getStateWiseGPtoVillageMapping(Integer stateCode,String LBGroup){
		List<StateWiseGPVillageMappedEntity> stateWiseGPtoVillageMappingList = new ArrayList<StateWiseGPVillageMappedEntity>();
		StateWiseGPVillageMappedEntity stateWiseGPVillageMapped =new StateWiseGPVillageMappedEntity();
		Query query=null;
		Session session=null;
		try {
			session = sessionFactory.openSession();
			if(stateCode!=null && stateCode>0){
				query = session.getNamedQuery("getStatewiseGPVillageMapping").setParameter("stateCode", stateCode).setParameter("LBGroup", LBGroup);	
				stateWiseGPtoVillageMappingList=query.list();
				if(stateWiseGPtoVillageMappingList!=null && stateWiseGPtoVillageMappingList.size()>0)
					stateWiseGPVillageMapped=stateWiseGPtoVillageMappingList.get(0);
			}
			
		}catch(Exception e) {
			log.debug("Exception" + e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return stateWiseGPVillageMapped;
	}
}
