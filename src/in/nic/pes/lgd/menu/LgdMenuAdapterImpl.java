package in.nic.pes.lgd.menu;


import in.nic.pes.common.menu.pojo.Form;
import in.nic.pes.common.menu.pojo.MenuProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class LgdMenuAdapterImpl implements LgdMenuAdapter {

	private static final Logger log = Logger.getLogger(LgdMenuAdapterImpl.class);


@Autowired
private SessionFactory sessionFactory;



//############################# menu function impl ######################

@SuppressWarnings("unchecked")
@Override
public List<MenuProfile> getParentInformation(Set<Form> formList) {
	// TODO Auto-generated method stub
	Session session = null ;
	List<MenuProfile> menuProfileList= null;		
    List <String> from = new ArrayList<String>();
try{
	session = sessionFactory.openSession();
	for(Form f:formList)
	{
		from.add(f.getFormName());
		//System.out.println("From name = "+f.getFormName());
	}
	
	Query q=session.getNamedQuery("getMenu");
	q.setParameterList("parameter1", from);
	q.setParameterList("parameter2", from);
	q.setParameterList("parameter3", from);
	//menuProfileList = q.list();
	menuProfileList = q.list();

	
	//Criteria criteria = session.createCriteria(MenuProfile.class);
	//System.out.println("**********************"+from.toString());
	//criteria.add(Restrictions.in("formName",formList));
	//criteria.add(Restrictions.in("formName",from));
	//criteria.addOrder(Order.asc("menuId"));
	//menuProfileList=criteria.list();
	
	
	
	
	//System.out.println("DAO end"+menuProfileList.size());

	}
	catch (Exception e) {
		//e.printStackTrace();
		log.debug("Exception" + e);	
	//ProfilerLogger.getLogger(ProfileDAOImpl.class).error(e.toString());
	return null;
	}
finally {
	if (session != null && session.isOpen()) {
		session.close();
	}
}
		return menuProfileList;
		
		}






}