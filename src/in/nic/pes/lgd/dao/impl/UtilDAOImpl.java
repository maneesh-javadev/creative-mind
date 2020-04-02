/***
 * @author Maneesh Kumar
 * @Since 20-May-2014
 * @Desciption  This DaoImpl is add method for "Single Sing On" 
 */

package in.nic.pes.lgd.dao.impl;

import in.nic.pes.common.menu.pojo.MenuProfile;
import in.nic.pes.lgd.bean.PesApplication;
import in.nic.pes.lgd.common.persistencecontext.HibernateSessionSingleSignOn;
import in.nic.pes.lgd.dao.UtilDAO;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UtilDAOImpl implements UtilDAO {
	
	private static final Logger log=Logger.getLogger(UtilDAOImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PesApplication> getAllApplication(Integer userId)  
	{
	Session session = null;
		try
		{
			session = HibernateSessionSingleSignOn.lgdSignIn();
			Query query = session.getNamedQuery("getAllApplication");
			query.setParameter("userId", userId);
			return query.list();
		}
		catch (Exception e) 
		{
			log.error("Error->"+e);
			return null;
			
		}
		finally
		{
		if(session!=null && session.isOpen())
			{
			session.close();
			}
			
		}
	}
	
	/**
	 * This Method is Use for External User 
	 * @param loginForm
	 * @author Maneesh Kumar
	 * @since 01-10-2019
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MenuProfile> getMenuProfilebyId(String menuIds)  
	{
	Session session = null;
	List<MenuProfile> menuList=null;
		try
		{
			session = HibernateSessionSingleSignOn.lgdSignIn();
			Query query = session.getNamedQuery("getMenuProfilebyId");
			query.setParameter("menuIds", menuIds);
			menuList= query.list();
		}
		catch (Exception e) 
		{
			log.error("Error->"+e);
			return null;
			
		}
		finally
		{
		if(session!=null && session.isOpen())
			{
			session.close();
			}
			
		}
		return menuList;
	}

}
