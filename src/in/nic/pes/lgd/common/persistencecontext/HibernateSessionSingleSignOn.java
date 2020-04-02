package in.nic.pes.lgd.common.persistencecontext;

import in.nic.pes.common.menu.pojo.MenuProfile;
import in.nic.pes.lgd.bean.PesApplication;

import java.io.InputStream;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Environment;

/***
 * @author Maneesh Kumar
 * @Since 20-May-2014
 * @Desciption  This  HibernateSession Use for "Single Sing On" to connect pescommon schema in pes
 */

public class HibernateSessionSingleSignOn {

	private static SessionFactory sessionFactory;

	public static Session lgdSignIn()
	
	{
		AnnotationConfiguration cfg =  new AnnotationConfiguration();

		cfg.addAnnotatedClass(PesApplication.class);
		/**
		 * This Method is Use for External User 
		 * @param loginForm
		 * @author Maneesh Kumar
		 * @since 01-10-2019
		 * @return
		 */
		cfg.addAnnotatedClass(MenuProfile.class);
		/**
		 * end External User
		 */
		String dialect=null;
		String datasource=null;
		try
		{
			InputStream inputStream = HibernateSessionEmail.class.getResourceAsStream("/email.properties");
	        Properties properties = new Properties();
	        properties.load(inputStream);
			dialect = properties.getProperty("email.dialect");
			datasource=properties.getProperty("email.datasource");
		}
		catch(Exception e)
		{
			//System.out.println("e=="+e);
		}
	
		Properties p = new Properties();
	    p.put(Environment.DIALECT, dialect );
		p.put(Environment.DATASOURCE, datasource);
		  
//		p.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
//		p.setProperty("hibernate.connection.datasource", "java:comp/env/jdbc/exHl");
		cfg.setProperties(p);
		sessionFactory  = cfg.buildSessionFactory();
		Session s = (Session) session.get();
		if (s == null) 
		{
			s = sessionFactory.openSession();
			session.set(s);
		}
		return s;
		
	}
		
	private static final ThreadLocal<Session> session = new ThreadLocal<Session>();
	
	
	public static Session currentSession() throws HibernateException // NO_UCD (unused code)
	{
		Session s = (Session) session.get();
		if (s == null) 
		{
			s = sessionFactory.openSession();
			session.set(s);
		}
		return s;
	}


	public static void closeSession() throws HibernateException  // NO_UCD (unused code)
	{
		Session s = (Session) session.get();
		session.set(null);
		if (s != null){
			s.close();
		}	
	}
}
