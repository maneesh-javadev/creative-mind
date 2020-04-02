package in.nic.pes.lgd.dao.impl;

import in.nic.pes.common.menu.pojo.Form;
import in.nic.pes.common.menu.pojo.MenuProfile;
import in.nic.pes.lgd.dao.MenuDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class MenuDAOImpl implements MenuDAO{
	private static final Logger log = Logger.getLogger(MenuDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	//############################# menu function impl ######################

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuProfile> getMenuList(Set<Form> formList) throws Exception {

		Session session = null ;
		List<MenuProfile> menuProfileList= null;		
		List <String> from = new ArrayList<String>();
		try{
			session = sessionFactory.openSession();
			//session =HibernateSession.currentSession();
			for(Form f:formList)
			{

				//System.out.println("From name = "+f.getFormName());
				//System.out.println("Action = "+f.getAction());
				from.add(f.getAction());	
			}

			Query q=session.getNamedQuery("getMenu");
			q.setParameterList("parameter1", from);
			q.setParameterList("parameter2", from);
			q.setParameterList("parameter3", from);
			menuProfileList = q.list();

			//System.out.println("DAO memu list size =  "+menuProfileList.size());

		}
		catch (Exception e) {
			log.debug("Exception" + e);
			//ProfilerLogger.getLogger(ProfileDAOImpl.class).error(e.toString());
			return null;
		}
		finally{
			if (session != null && session.isOpen()){
				session.close();
			}	
		}
		return menuProfileList;

	}


}

