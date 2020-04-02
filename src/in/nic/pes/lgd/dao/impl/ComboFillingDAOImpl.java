package in.nic.pes.lgd.dao.impl;

import in.nic.pes.lgd.bean.CheckAuthorization;
import in.nic.pes.lgd.bean.CheckAuthorizationforlist;
import in.nic.pes.lgd.dao.ComboFillingDAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class ComboFillingDAOImpl implements ComboFillingDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<CheckAuthorization> populateComboValuesforApp(char entityType,
			String entityParentType, Integer entityParentCode, Integer entityCode) throws Exception
			{
				 Session session = null;
				 //Query query = null;
				 List<CheckAuthorization> comboList = new ArrayList<CheckAuthorization>();
				 try {
				/*	session = sessionFactory.openSession();
					 query = session.getNamedQuery("getcheckAuthorization")
							 .setParameter("entity_type", entityType,Hibernate.CHARACTER)
							 .setParameter("entity_parent_type", entityParentType,Hibernate.STRING)
							 .setParameter("entity_parent_code", entityParentCode,Hibernate.INTEGER)
							 .setParameter("entity_code", entityCode,Hibernate.INTEGER);
					 comboList = query.list();*/
					 comboList.add(new CheckAuthorization());
				} catch (Exception e) {
					 throw e;
				}
				 finally
				 {
					 if(session != null && session.isOpen()){
						 session.close();
					 }	 
				 }
				 return comboList;
			}
	
	@Override
	public List<CheckAuthorization> populateComboValuesforAppforward(char entityType,
			String entityParentType, Integer entityParentCode, Integer entityCode) throws Exception
			{
				 Session session = null;
				 @SuppressWarnings("unused")
				Query query = null;
				 List<CheckAuthorization> comboList = new ArrayList<CheckAuthorization>();
				 try {
					session = sessionFactory.openSession();
					 query = session.getNamedQuery("getcheckAuthorization")
							 .setParameter("entity_type", entityType,Hibernate.CHARACTER)
							 .setParameter("entity_parent_type", entityParentType,Hibernate.STRING)
							 .setParameter("entity_parent_code", entityParentCode,Hibernate.INTEGER)
							 .setParameter("entity_code", entityCode,Hibernate.INTEGER);
					// comboList = query.list();
				} catch (Exception e) {
					 throw e;
				}
				 finally
				 {
					 if(session != null && session.isOpen()){
						 session.close();
					 }	 
				 }
				 return comboList;
			}
	
	
	
///////////////////method for list validation through function by siva/////////////////////////////
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CheckAuthorizationforlist> populateComboValuesforlist(char entityType, String entityParentType, String entityparentcodeslist ,String entitycodeslist) throws Exception
			{
				 Session session = null;
				 Query query = null;
				 List<CheckAuthorizationforlist> comboList = new ArrayList<CheckAuthorizationforlist>();
				 try {
					session = sessionFactory.openSession();
					 query = session.getNamedQuery("getcheckAuthorizationinlist")
							 .setParameter("entity_type", entityType,Hibernate.CHARACTER)
							 .setParameter("entity_parent_type", entityParentType,Hibernate.STRING)
							 .setParameter("entity_parent_codes_list", entityparentcodeslist,Hibernate.STRING)
							 .setParameter("entity_codes_list", entitycodeslist,Hibernate.STRING);
					 comboList = query.list();
				} catch (Exception e) {
					 throw e;
				}
				 finally
				 {
					 if(session != null && session.isOpen()){
						 session.close();
					 }	 
				 }
				 return comboList;
			}
}
