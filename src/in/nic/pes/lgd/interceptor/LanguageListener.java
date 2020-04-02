package in.nic.pes.lgd.interceptor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import in.nic.pes.common.beans.LanguagePackage;
import in.nic.pes.lgd.loggers.LGDLogger;

public class LanguageListener implements ServletContextListener
{
	
	@Resource(name="jdbc/switchunit")
	private DataSource dataSource;
	
	
	private static final Logger log = Logger.getLogger(LanguageListener.class);
	private Connection con=null;
	private Statement statement=null;
	private ResultSet rs=null;
	/*private String url = LGDResourceBundle.getBundle("hibernate", Locale.ENGLISH).getObject("hibernate.connection.url").toString();
	private String username = LGDResourceBundle.getBundle("hibernate", Locale.ENGLISH).getObject("hibernate.connection.username").toString();
	private String password = LGDResourceBundle.getBundle("hibernate", Locale.ENGLISH).getObject("hibernate.connection.password").toString();*/
	
	
	public  Connection getConnection() throws ClassNotFoundException, SQLException
	{
	  /*  Class.forName("org.postgresql.Driver");*/
		con=dataSource.getConnection();
		return con;	
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent)
	{
		try
		{
			String path = servletContextEvent.getServletContext().getRealPath("/");
			//createImagePathFaile(path);
			List<LanguagePackage> list =  getLanguagePackages();
     		createProperties(path,list);
		}
		catch(Exception e)
		{
			LGDLogger.getLogger(LanguageListener.class).error(e.toString());
		}
		finally
		{
			try 
			{
				if(con!=null){
					con.close();
				}	
			}
			catch (SQLException e) 
			{
				LGDLogger.getLogger(LanguageListener.class).error(e.toString());
		
			}
		}
		
		
		
		
		
	}

// TODO Remove unused code found by UCDetector
// 	public void createImagePathFaile(String path) throws IOException
// 	{
// 		  File file=new File(path+"/WEB-INF/classes/imagespath.properties");
// 		  String newPath = path.replace("\\", "/");
// 		  file.createNewFile();
//           PrintWriter pw=new PrintWriter(file);
//           pw.write("touristplace = "+newPath+"pics/tp/"+"\n"); 
//           pw.write("map = "+newPath+"pics/map/"+"\n");
//           pw.write("temp= "+newPath+"images/"+"\n");
//           pw.write("official= "+newPath+"images/official/"+"\n");
//           pw.close();
// 	}

	private void createProperties(String path ,List<LanguagePackage> list) throws SQLException, IOException, ClassNotFoundException
	{
		 con=getConnection();
		 statement=con.createStatement();	
		 Writer pw=null;
		 //PrintWriter pw2=null;
		 String strPath=path+"/WEB-INF/messages";
		 try
		 {
			 for(LanguagePackage languagePackage:list)
			 {
				 rs=statement.executeQuery("select * from pescommon.get_all_commonlabels_list_fn(1) where package_identifier = '"+languagePackage.getLanguagePackage()+"'");
				 
				 File folder=new File(strPath);
				 if(!folder.isDirectory())
				 {
					 folder.mkdir();
				 }
				 File file=new File(strPath+"/messages_"+languagePackage.getLanguagePackage()+".properties");
				 file.createNewFile();
		     
				 FileOutputStream fos = new FileOutputStream(file,true);
				 pw = new BufferedWriter(new OutputStreamWriter(fos,"UTF8"));
				 while(rs.next())
				 {
					 pw.write(rs.getString("label_name").trim()+"="+rs.getString("label_text").trim()+"\n");
					 if(rs.getRow()>500)
					 { 	 
						 pw.flush();
					 } 	
				 }
			 }  
		/*	 rs1=statement.executeQuery("select form.um_resource_id ||'.'|| label.label_name label1,fld.field_description from manual.form form,manual.form_label label,manual.um_form_field fld where form.pes_application_id = 1 and form.form_id =label.form_id and label.label_id = fld.label_id");
			 File file2=new File(strPath+"/messages_"+"en"+".properties");
			 FileOutputStream fo2=new FileOutputStream(file2,true);
			 pw2=new PrintWriter(fo2);
			 while(rs1.next())
			 {
			   	  pw2.write(rs1.getString("label1").trim()+"="+rs1.getString("field_description").trim()+"\n");
		     }*/
			 
		 }
		  catch(SQLException sqe)
		  {
			  log.debug("Exception" + sqe);
		  }
		  catch(Exception e)
		  {
			  log.debug("Exception" + e);
		  }
		  finally
		  {
				try
				{
					if(rs != null)
					{	
						rs.close();
					}
					if(statement != null)
					{
						statement.close();
					}
					if(con != null)
					{
						con.close();
					}
						
				}
				catch(Exception ex) 
				{
					//System.out.println("The Exception:"+ex);
				}
		  }
	//	  pw.flush();
	      pw.close(); 
	     //pw2.close();

	}
	
	
	
	
	
/*	public void createPropertiesfile (String InList,String path) throws SQLException, IOException, ClassNotFoundException
	{			
		  PreparedStatement pstmt = null;
		  String query="select labels.label_name,labels.label_text from pescommon.language_package pack,pescommon.common_labels labels where pack.package_id=labels.language_id and labels.pes_application_id=3 or labels.pes_application_id=0 and pack.package_identifier=? "+
				  "union all "+
				  "select masters.master_name,masters.master_text from pescommon.language_package pack,pescommon.common_master masters where pack.package_id=masters.language_id and masters.pes_application_id=0 and pack.package_identifier=?"+
		  		  "union all "+
				  " select calrtmst.alert_message_key,calert.alert_message_text from pescommon.common_alert_master calrtmst,pescommon.common_alerts calert,pescommon.language_package pack where calert.language_id=pack.package_id and calrtmst.alert_id=calert.alert_id and pack.package_identifier=?";
		  Writer pw=null;
		  try
		  {
		      con=getConnection(path);
		      pstmt = con.prepareStatement(query);
		      pstmt.setString(1,InList);
		      pstmt.setString(2,InList);
		      pstmt.setString(3,InList);
		      
		      rs = pstmt.executeQuery();

		      String strPath=path+"/WEB-INF/messages";
		      File folder=new File(strPath);
		      if(!folder.isDirectory())
		      {
		    	  folder.mkdir();
		      }
		    
	          File file=new File(strPath+"/messages_"+InList+".properties");
	          file.createNewFile();
	         
			  FileOutputStream fos = new FileOutputStream(file,true);
			  pw = new BufferedWriter(new OutputStreamWriter(fos,"UTF8"));
			  while(rs.next())
			  {
	    		  pw.write(rs.getString("label_name").trim()+"="+rs.getString("label_text").trim()+"\n");
	          }
			  
}
		  catch(SQLException sqe)
		  {
				sqe.printStackTrace();
		  }
		  catch(Exception e)
		  {
				e.printStackTrace();
		  }
		  finally
		  {
				try
				{
					if(rs != null)
					{	
						rs.close();
					}
					if(statement != null)
					{
						statement.close();
					}
					if(pstmt != null)
					{
						pstmt.close();
					}
					if(con != null)
					{
			//			con.close();
			//			System.out.println("connection close or not......................"+con.isClosed());
					}
						
				}
				catch(Exception ex) 
				{
					System.out.println("The Exception:"+ex);
				}
		  }
		  pw.flush();
	      pw.close();
	}
	*/
	
	

	public List<LanguagePackage> getLanguagePackages() throws ClassNotFoundException, SQLException
	{
         List<LanguagePackage> list = new ArrayList<LanguagePackage>();
		 con=getConnection();

		 statement=con.createStatement();	
		 rs=statement.executeQuery("select * from pescommon.get_all_languages()");
	     while(rs.next())
	     {
    	 		LanguagePackage languagePackage = new LanguagePackage();
    	 		languagePackage.setIsDefault(false);
    	 		languagePackage.setLanguageId(rs.getInt("language_id"));
    	 		languagePackage.setLanguageName(rs.getString("master_text"));
    	 		languagePackage.setLanguagePackage(rs.getString("package_identifier"));
    	 		list.add(languagePackage);
         }
		return list;
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) 
	{}
}