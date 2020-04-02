package in.nic.pes.lgd.common;

import java.io.InputStream;
import java.util.Properties;

import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import in.nic.pes.lgd.utils.ApplicationConstant;


/**
 * Class is used to load basic information on server start
 * @author Maneesh Kumar 
 *
 */
public class ApplicationInitializationService implements InitializingBean, DisposableBean{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	

	@SuppressWarnings("deprecation")
	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		ApplicationConstant.eng.shutdown();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		/*List<SubscribeApplication> subscribeApplicationList=null;
		Session session=null;*/
		try{
			/**
			 * method to load key of register user for LGD web services 
			 */
			/*session=sessionFactory.openSession();
			Query query=session.createQuery("from SubscribeApplication");
			subscribeApplicationList=query.list();
			for(SubscribeApplication subscribeApplication:subscribeApplicationList){
				ApplicationConstant.registerUserKeyList.add(subscribeApplication.getPasskey());
			}
			if(ApplicationConstant.registerUserKeyList.isEmpty()){
				ApplicationConstant.registerUserKeyList.add("121212");
			}
			*/
			
			
			/**
			 * upload the engine for download directory by Maneesh Kumar 21-Jan-2016
			 */
			InputStream inputStreamPro = getClass().getClassLoader().getResourceAsStream("/upload_info.properties");

			Properties properties = new Properties();
			properties.load(inputStreamPro);
			
			
			String engineloc=properties.getProperty("birt.engine.location");
			String enginelogs=properties.getProperty("birt.engine.logs");
			//serverLoc=properties.getProperty("birt.server.location");
			EngineConfig conf = new EngineConfig();
			conf.setEngineHome(engineloc+"\\ReportEngine");
			//conf.setLogConfig(engineloc+"\\logs", Level.FINE);
			 conf.setLogConfig(enginelogs, java.util.logging.Level.SEVERE);
			Platform.startup(conf);

			IReportEngineFactory factory = (IReportEngineFactory) Platform.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
			ApplicationConstant.eng = factory.createReportEngine(conf);
			
			
		}finally {
			/*if (session!=null && session.isOpen()) {
				session.close();
			}*/
		}
				
	}

}
