package in.nic.pes.lgd.dao.impl;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.security.spec.AlgorithmParameterSpec;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.directwebremoting.WebContextFactory;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import in.nic.pes.common.menu.pojo.MenuProfile;
import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.bean.EmailNotification;
import in.nic.pes.lgd.bean.EntityChangesInGivenTime;
import in.nic.pes.lgd.bean.ExternalUser;
import in.nic.pes.lgd.bean.StatewiseEntitiesCount;
import in.nic.pes.lgd.bean.SubDistrictBlockMapEntities;
import in.nic.pes.lgd.common.CaptchaValidator;
import in.nic.pes.lgd.common.persistencecontext.HibernateSessionEmail;
import in.nic.pes.lgd.dao.InitialDao;
import in.nic.pes.lgd.dao.ReportDAO;
import in.nic.pes.lgd.dao.UtilDAO;
import in.nic.pes.lgd.draft.constant.DraftConstant;
import in.nic.pes.lgd.draft.entities.DraftMaster;
import in.nic.pes.lgd.forms.FaqData;
import in.nic.pes.lgd.resourcebundles.LGDResourceBundle;
import in.nic.pes.lgd.utils.BASE64;
import net.iharder.Base64;

@Repository
public class InitialDaoImpl implements InitialDao {

	private static final Logger log = Logger.getLogger(InitialDaoImpl.class);
	
	@Autowired 
	SessionFactory sessionFactory;
	
	@Autowired
	ReportDAO reportDAO;
	
	@Autowired
	private UtilDAO utilDAO;
	

	

	@Override
	public List<FaqData> getAvailableFAQs(Integer appId) {
		Session session = null;
		try {
			session = HibernateSessionEmail.email().getSessionFactory().openSession();
			SQLQuery query = session.createSQLQuery("select faq_question_text as faqQuestionText, faq_answer_text as faqAnswerText from pescommon.get_faqList_by_appID(:appid)");
			query.setParameter("appid", appId);
			query.addScalar("faqQuestionText").addScalar("faqAnswerText");
			query.setResultTransformer(Transformers.aliasToBean(FaqData.class));
			@SuppressWarnings("unchecked")
			List<FaqData> availableFAQs = query.list();
			return availableFAQs;
		} catch (Exception e) {
			log.debug("Exception" + e);
			throw new HibernateException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
	
	public String getSupportDownloadDoc(  String link, String filename, String propkey)throws Exception {
		StringBuilder sb=new StringBuilder(); 
		String filedescription = null;
		String filetype="";
		String lastmodified="";
		String icon="";
		String lenMb="";
		String filePath = "";
		try {
				int docindex = link.indexOf("docType");
			    if(docindex != -1) {	
			    	String doctype=link.substring(link.indexOf('=')+1);
			    	
			    	double filelength=0;
			    	
			    	
			    	ResourceBundle bundle = LGDResourceBundle.getBundle("upload_info", Locale.ENGLISH);
		
					String directoryLocation = bundle.getString("userManual.directory.location");
					String fileName = bundle.getString(String.format("supporting.doc.file.%s", propkey));
					filePath = directoryLocation + File.separator + fileName;
					filetype = FilenameUtils.getExtension(filePath);
					
					File f = new File(filePath);
					
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
					lastmodified = sdf.format(f.lastModified());
					filelength=f.length();
					DecimalFormat df = new DecimalFormat();
					df.setMaximumFractionDigits(2);
					lenMb=df.format((filelength/1048576));
					
					if(doctype.equalsIgnoreCase("cbtplaypath")) {
						link="downLoadCBT.do";
						filedescription=" The computer based training (CBT), played online or offline includes the following aspects:" + 
								"<ol>" + 
								"<li>Responses are recorded and scored electronically. </li>" + 
								"<li>Demographic data collection and reporting.</li>" + 
								"<li>Test administration via computer. </li>" + 
								"<li>Step by step description of the topic and relevant module.</li>" + 
								"</ol>";
					} else if(doctype.equalsIgnoreCase("presentationfilepath")){
						link="viewPresentation.do";
						filedescription="The presentation for LGD mainly focusses on the following aspects:" + 
								" <ol>" + 
								"     <li>What are different types of Local Government Bodies (of a State)?</li>" + 
								"     <li>What is the nomenclature used for different Local Government Bodies in different states?</li>" + 
								"     <li>Panchayat Setup of a State (No. of Tiers).</li>" + 
								"     <li>List of Local Government Bodies of a given State/District </li>" + 
								"     <li>Standard Codes of Local Government Bodies of a given State/District.</li>" + 
								"     <li>Delimitation of a Local Government Body or the area covered/governed by a Local Government Body.</li>" + 
								" </ol>";
					} else if(doctype.equalsIgnoreCase("UserManualfilepath")){
						link="viewManual.do";
						
						filedescription="To maintain up-to-date list of Local Government Bodies(Panchayats, Municipalities)"+
													"their Standard Codes and their relationship with constituting entities(districts/subdistricts/"+
													"villages/wards) to share in public domain for common man and other consuming"+
													"applications with the help of State and District .";
					}else if(doctype.equalsIgnoreCase("UserManualfilepathUrban")){
						link="viewManualUrbanDevelopment.do";
						
						filedescription="User Manual of Urban Development .";
					}else if(doctype.equalsIgnoreCase("UserManualfilepathRevenue")){
						link="viewManualRevenueDevelopment.do";
						
						filedescription="User Manual of Revenue Development .";
					}else if(doctype.equalsIgnoreCase("UserManualfilepathGIS")){
						link="viewManualGIS.do";
						
						filedescription="User Manual of GIS Module";
						
					}else if(doctype.equalsIgnoreCase("UserManualfilepathLDC")){
						link="viewManualLDC.do";
						
						filedescription="User Manual of LGD Data Confirmation";
						
					}else if(doctype.equalsIgnoreCase("Brochure")){
						link="viewBrochure.do";
						filedescription="Local Government Directory is one of the applications developed as part of Panchayat Enterprise Suite (PES) developed under ePanchayat " +
										"Mission Mode Project (MMP). Primary objective of Local Government directory is to facilitate State Departments to update the directory with newly formed " +
									   "panchayats/local bodies,re-organization in panchayats, conversion from Rural to Urban area etc and provide the same info in public domain.";
						
					} else if(doctype.equalsIgnoreCase("DataCollectionRegisterfilepath")){
						link="dataRegister.do";
						filedescription="The data registers are the set of records in the spreadsheet representation.It includes various details related with the Local Government Directory web application.";
					}else if(doctype.equalsIgnoreCase("AndroidLGD")) {
							link="androidLGD.do";
						
						filedescription="Download LGD mobile App to your ANDROID phone for customized access.";
					}else if(doctype.equalsIgnoreCase("dedicatingVideo")) {
//						link="CBT/LGD Launch Video.html";
						filedescription=" Prime Minister Shri Narendra Modi dedicated LGD to Nation on the event of National Panchayati Raj Day held on 24th of April 2018 ";
					}
				
					
					if(filetype.equalsIgnoreCase("pdf")) {
						icon="fa fa-file-pdf-o";
					}else if(filetype.equalsIgnoreCase("rar")) {
						icon="fa fa-file-archive-o";
					}else if(filetype.equalsIgnoreCase("xlsx")) {
						icon="fa fa-file-excel-o";
					}else if(filetype.equalsIgnoreCase("pptx")) {
						icon="fa fa-file-powerpoint-o";
					}else if(filetype.equalsIgnoreCase("apk")) {
							icon="fa fa-android";
						}
					
					
					
			    } else {
			    	filedescription=" The computer based training (CBT), played online or offline includes the following aspects:<br>" +
							"1. Responses are recorded and scored electronically. <br>"+
							"2. Demographic data collection and reporting. <br>"+
							"3. Test administration via computer. <br>"+
							"4. Step by step description of the topic and relevant module.";
			    	
			    }
			    sb.append(filename);
			    sb.append("#");
			    sb.append(lastmodified);
			    sb.append("#");
			    sb.append(filedescription);
			    sb.append("#");
			    sb.append(filetype);
			    sb.append("#");
			    sb.append(icon);
			    sb.append("#");
			    sb.append(link);
			    sb.append("#");
			    sb.append(lenMb);
			    sb.append('#');
			    sb.append(filePath);
			    String retVal=(sb!=null && !("").equals(sb.toString()) )?sb.toString():"";
			    return retVal;
		}catch(final Exception ex) {
			ex.printStackTrace();
		}
	    return null;
	}
	
	@Override
	public String lgdEntitiesCountFn() throws Exception {
		Session session = null;
		String lgdEntitiesCount=null;
		try {
			session=sessionFactory.openSession();
			Query query=session.createSQLQuery("select ((select count(1) from state where isactive) ||'#'||(select count(1) from district where isactive) ||'#'||(select count(1) from subdistrict where isactive) ||'#'||" + 
					   "(select count(1) from block where isactive) ||'#'||(select count(1) from village where isactive)||'#'||(select count(1) from localbody where isactive)) as countDetails ");
			lgdEntitiesCount=(String)query.uniqueResult();
		} catch (Exception e) {
			log.debug("Exception" + e);
			throw new HibernateException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lgdEntitiesCount;
	}
	
	
	@Override
	public List<StatewiseEntitiesCount> lgdEntitiesCountFn(boolean isCenter) throws Exception {
		Session session = null;
		List<StatewiseEntitiesCount> lgdEntitiesCount=null;
		try {
			session=sessionFactory.openSession();
			String nameQuery="getStateWiseEntityCount";
			if(isCenter){
				nameQuery="getIndiaLevelEnitiesCount";
			}
			Query query=session.getNamedQuery(nameQuery);
			lgdEntitiesCount=query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
			throw new HibernateException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return lgdEntitiesCount;
	}
	
	
	@Override
	public boolean updateStatewise() throws Exception {
		Session session = null;
		List lgdEntitiesCount=null;
		try {
			session=sessionFactory.openSession();
			
			Query query=session.getNamedQuery("updateStatewiseTotals");
			lgdEntitiesCount=query.list();
			
		} catch (Exception e) {
			log.debug("Exception" + e);
			throw new HibernateException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return (lgdEntitiesCount!=null && lgdEntitiesCount.size()>0);
	}
	
	@Override
	public List<SubDistrictBlockMapEntities> getSubdistrictBlockMaped() throws Exception {
		Session session = null;
		List<SubDistrictBlockMapEntities> subDistrictBlockMapEntities=null;
		try {
			session=sessionFactory.openSession();
			String nameQuery="subdistrictVillageBlockMapping";
			Query query=session.getNamedQuery(nameQuery);
			subDistrictBlockMapEntities=query.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
			throw new HibernateException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return subDistrictBlockMapEntities;
	}
	
	@Override
	public List<StatewiseEntitiesCount> getDataFromJsonFile() throws Exception {
		List<StatewiseEntitiesCount> statewiseEntitiesCountList=null;
		try {
			InputStream inputStreamPro = getClass().getClassLoader().getResourceAsStream("/upload_info.properties");
			Properties properties = new Properties();
			properties.load(inputStreamPro);
			String masterPtah = properties.getProperty("homepage.json.data.file");
			Gson gson = new Gson();
			statewiseEntitiesCountList = gson.fromJson(	new FileReader(masterPtah + File.separator + "lgd_statewise_entities_count.json"),new TypeToken<List<StatewiseEntitiesCount>>() {	}.getType());
		} catch (Exception e) {
			log.error(e);
		}
		return statewiseEntitiesCountList;

	}
	
	private String getCommonJsonFilePath()throws Exception{
		AttachmentMaster attachmentMaster=reportDAO.getUploadFileConfigurationDetails(6L);
		 File dir = new File(attachmentMaster.getFileLocation());
		 if(!dir.exists()){
		    	dir.mkdirs();
		   }
		return dir.getAbsolutePath();
	}


	@Override
	public List<EntityChangesInGivenTime> getEntityChangesInGivenTime(int slc,boolean district,boolean subDis,boolean village,String localBody,boolean block) throws Exception {
		Session session = null;
		List<EntityChangesInGivenTime> entityChangesInGivenTime=null;
		
		try {
			session=sessionFactory.openSession();
			String nameQuery="entityChangesInGivenTime";
			Query query=session.getNamedQuery(nameQuery);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date now = new Date();
			 Calendar cal = Calendar.getInstance();
		        cal.add(Calendar.DATE, -7);
		        Date todate1 = cal.getTime();  
		        
			
			query.setParameter("fromDate",dateFormat.parse(dateFormat.format(todate1)),Hibernate.DATE);
			query.setParameter("toDate", dateFormat.parse(dateFormat.format(now)),Hibernate.DATE);
			query.setParameter("slc", slc,Hibernate.INTEGER);
			
			query.setParameter("district", district,Hibernate.BOOLEAN);
			query.setParameter("subdistrict", subDis,Hibernate.BOOLEAN);
			query.setParameter("village", village,Hibernate.BOOLEAN);
			query.setParameter("localBodyType", localBody,Hibernate.TEXT);
			query.setParameter("block", block,Hibernate.BOOLEAN);
			entityChangesInGivenTime=query.list();
		} 
		catch (Exception e) {
			log.debug("Exception" + e);
			throw new HibernateException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return entityChangesInGivenTime;
	}


	@Override
	public List<EmailNotification> getUserInformation() {
		Session session = null;
		Query query=null;
		List<EmailNotification> emailNotifications=null;
		try {
			session=sessionFactory.openSession();
			Criteria  criteria=session.createCriteria(EmailNotification.class);
			emailNotifications=criteria.list();
		} catch (Exception e) {
			log.debug("Exception" + e);
			throw new HibernateException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return emailNotifications;
	}
	
	
	/**
	 * This Method is Use for External User 
	 * @param loginForm
	 * @author Maneesh Kumar
	 * @since 01-10-2019
	 * @return
	 */
	@Override
	public boolean isUserLoginNameExist(String userLoginName) {
		
		
		boolean isUserNameExist=false;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("select case when count(1)>0 then true else false end  from external_user  where user_login_id  ilike :userLoginName");
			query.setParameter("userLoginName", userLoginName);
			isUserNameExist= (Boolean) query.uniqueResult();
		}catch(Exception e) {
			log.debug("Exception" + e);
			throw new HibernateException(e);
		
		} finally {
			if(session!=null && session.isOpen())
				session.close();
		}
		return isUserNameExist;
	}
	
	/**
	 * This Method is Use for External User 
	 * @param loginForm
	 * @author Maneesh Kumar
	 * @since 01-10-2019
	 * @return
	 */
	@Override
	public boolean validateCaptchaAnswer(String captchaAnswer) {
		 HttpSession httpSession = WebContextFactory.get().getSession();
		CaptchaValidator captchaValidator = new CaptchaValidator();
		boolean messageFlag = captchaValidator.validateCaptchaFoExternalLogin(httpSession, captchaAnswer);
		return messageFlag;
		
	}
	
	
	/**
	 * This Method is Use for External User 
	 * @param loginForm
	 * @author Maneesh Kumar
	 * @since 01-10-2019
	 * @return
	 */
	@Override
	public boolean isPasswordValid(String userName, String enPassword) {
		
		boolean isPasswordValid=false;
		final String CHARSET = "UTF-8"; 
		 try {
	
		 String afterDecrypt = decrypt(enPassword);
		  byte[] bytes = Hex.decodeHex(afterDecrypt.toCharArray());
		 String dePassword=new String(bytes, CHARSET);
			
		 isPasswordValid=compareWithStorePassword(userName,dePassword);
		
		  
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		
		return isPasswordValid;
		 
	}
	
	/**
	 * This Method is Use for External User 
	 * @param loginForm
	 * @author Maneesh Kumar
	 * @since 01-10-2019
	 * @return
	 */
	@Override
	public Integer findUserAfterAuth(String userName, String enPassword) {
		 Integer id=null;
		Session session = null;
		try {
			if(isPasswordValid(userName,enPassword)) {
				session = sessionFactory.openSession();
				Query query = session.createSQLQuery("select id from external_user  where user_login_id  ilike :userLoginName");
				query.setParameter("userLoginName", userName);
				id= (Integer) query.uniqueResult();
			}
		}catch(Exception e) {
			log.debug("Exception" + e);
			throw new HibernateException(e);
		
		} finally {
			if(session!=null && session.isOpen())
				session.close();
		}
		
		return id;
	}
	
	/**
	 * This Method is Use for External User 
	 * @param loginForm
	 * @author Maneesh Kumar
	 * @since 01-10-2019
	 * @return
	 */
	public String decrypt(final String encrypted) throws Exception {

		 try {
		 SecretKey key = new SecretKeySpec(Base64.decode("u/Gu5posvwDsXUnV5Zaq4g=="), "AES");
		 AlgorithmParameterSpec iv = new IvParameterSpec(Base64.decode("5D9r9ZVzEYYgha93/aUK2w=="));
		 byte[] decodeBase64 = Base64.decode(encrypted);
		 Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		 cipher.init(Cipher.DECRYPT_MODE, key, iv);
		 return new String(cipher.doFinal(decodeBase64), "UTF-8");
		 } catch (Exception e) {
		 e.printStackTrace();
		 throw new RuntimeException("This should not happen in production.", e);
		 }
		} 
	
	
	/**
	 * This Method is Use for External User 
	 * @param loginForm
	 * @author Maneesh Kumar
	 * @since 01-10-2019
	 * @return
	 */
	private  boolean compareWithStorePassword(String userLoginName,String dePassword) {
	 
		boolean isPasswordValid=false;
		String enstorepass=null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("select en_password from external_user  where user_login_id  ilike :userLoginName");
			query.setParameter("userLoginName", userLoginName);
			enstorepass= (String) query.uniqueResult();
			
		
			if(enstorepass!=null) {
				
				
				
				String destorepass =BASE64.base64decode(enstorepass);
				System.out.println("dePassword:-->"+dePassword);
				System.out.println("enstorepass:-->"+enstorepass);
				System.out.println("destorepass:-->"+destorepass);
			   if(dePassword.equals(destorepass)) {
				   isPasswordValid= true;
			   }
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new HibernateException(e);
		
		} finally {
			if(session!=null && session.isOpen())
				session.close();
		}
		
		return isPasswordValid;
		
	}
	
	
	/**
	 * This Method is Use for External User 
	 * @param loginForm
	 * @author Maneesh Kumar
	 * @since 01-10-2019
	 * @return
	 */
	@Override
	public ExternalUser getExternalUserEntity(Long userId){
		Session session=null;
		ExternalUser externalUser=null;
		try{
			
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(ExternalUser.class);
		criteria.add( Restrictions.eq("userId", userId));
		List<ExternalUser> externalUserList =  criteria.list();
		if(externalUserList!=null && !externalUserList.isEmpty()) {
			externalUser=externalUserList.get(0);
		}
		}catch(Exception e) {
			log.debug("Exception" + e);
			throw new HibernateException(e);
		
		} finally {
			if(session!=null && session.isOpen())
				session.close();
		}
		return externalUser;
	}
	
	/**
	 * This Method is Use for External User 
	 * @param loginForm
	 * @author Maneesh Kumar
	 * @since 01-10-2019
	 * @return
	 */
	@Override
	public List<MenuProfile> findMenuListforExternalUser(Long userId){
		Session session = null;
		List<MenuProfile> menuList=null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("select * from external_user_group_mapped_wtih_menu  where user_group_id in(select user_group_id from external_user_group where user_id =:userId)");
			query.setParameter("userId", userId);
			List list= query.list();
			Iterator iterator = list.iterator(); 
			List<Integer> menuids=new ArrayList<Integer>();
			menuids.add(-1);
	        while (iterator.hasNext()) {
	        	 Object[] objects = (Object[]) iterator.next(); 
	        	 menuids.add(Integer.parseInt(objects[2].toString()));
	      
	        }
	        
	        if(menuids.size()>1) {
	        	String menuIds=menuids.toString();
	        	menuIds=menuIds.substring(1, menuIds.length()-1);
	        	menuList=utilDAO.getMenuProfilebyId(menuIds);
	        	
	        	
	        }
			
		}catch(Exception e) {
			log.debug("Exception" + e);
			throw new HibernateException(e);
		
		} finally {
			if(session!=null && session.isOpen())
				session.close();
		}
		return menuList;
	}
	
	
	

}



