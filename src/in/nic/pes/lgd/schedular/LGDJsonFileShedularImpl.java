package in.nic.pes.lgd.schedular;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.cmc.lgd.localbody.rules.LocalBodyConstant;
import com.cmc.lgd.localbody.services.LocalBodyService;
import com.google.gson.Gson;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;

import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.bean.EmailNotification;
import in.nic.pes.lgd.bean.EntityChangesInGivenTime;
import in.nic.pes.lgd.bean.NotificationLog;
import in.nic.pes.lgd.bean.StatewiseEntitiesCount;
import in.nic.pes.lgd.bean.SubDistrictBlockMapEntities;
import in.nic.pes.lgd.bean.UserRegistration;
import in.nic.pes.lgd.common.MailService;
import in.nic.pes.lgd.draft.service.DraftUtilService;
import in.nic.pes.lgd.resourcebundles.LGDResourceBundle;
import in.nic.pes.lgd.service.InitialService;
import ws.in.nic.pes.lgdws.services.WSService;



public class LGDJsonFileShedularImpl implements LGDJsonFileShedular {
	
	private static final Logger log = Logger.getLogger(LGDJsonFileShedularImpl.class);
	
	@Autowired
	private InitialService initialService;
	
	@Autowired
	private LocalBodyService localBodyService;
	
	@Autowired
	ServletContext servletContext;

	private AttachmentMaster attachmentMaster=null;
	
	
	

	@Autowired
	DraftUtilService draftUtilService;
	
	
	

	public void initIt() throws Exception {
	 attachmentMaster=localBodyService.getUploadFileConfigurationDetails(6L);
		
	}
	
	
	@Override
	@Scheduled(cron ="${json.file.scheduler.cron.time}")
	public void processSchedular() {
		try{
			initIt();
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date d=new Date();
			log.info("LGD entity detail scheduler start at-"+dateFormat.format(d));
			if(initialService.updateStatewise()){
				 File dir = new File(attachmentMaster.getFileLocation());
				 if(!dir.exists()){
				    	dir.mkdirs();
				    }
				setHomePageEntitiesCount(dir.getAbsolutePath());
				setStatewiseEntitiesCount(dir.getAbsolutePath());
			}else{
				log.info("Some Problem to update Scheduler");
			}
			
		}catch(Exception e){
			schedulerNotify(e);
			log.error("Exception while executing LGD json scheduler-->",e);
		}
		
	}


public void setHomePageEntitiesCount(String fullpath)throws Exception{
	    File file=new File(fullpath + File.separator +"lgd_entity_count.json");  
		 if(file.exists()){
			 List<StatewiseEntitiesCount> lgdEntitiesCount =initialService.lgdEntitiesCountFn(Boolean.TRUE);
			 if(lgdEntitiesCount!=null && !lgdEntitiesCount.isEmpty()){
				 FileWriter fileWriter = new FileWriter(file);  
				 Gson gson = new Gson();
				 gson.toJson(lgdEntitiesCount.get(0), fileWriter);
				
	            fileWriter.flush();  
	            fileWriter.close(); 
			}
		}
}

public void setStatewiseEntitiesCount(String fullpath)throws Exception{
	 File file=new File(fullpath + File.separator +"lgd_statewise_entities_count.json"); 
	  if(file.exists()){
			 List<StatewiseEntitiesCount> lgdEntitiesCount =initialService.lgdEntitiesCountFn(Boolean.FALSE);
			 if(lgdEntitiesCount!=null && !lgdEntitiesCount.isEmpty()){
				 FileWriter fileWriter = new FileWriter(file);  
				 Gson gson = new Gson();
				 gson.toJson(lgdEntitiesCount, fileWriter);
				/* for(StatewiseEntitiesCount statewiseEntitiesCount:lgdEntitiesCount){
				 gson.toJson(statewiseEntitiesCount, fileWriter);
				 }*/
	            fileWriter.flush();  
	            fileWriter.close(); 
			}
		}
}

	public void schedulerNotify(Exception errorObj) {
		try {
			Date d = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String strDate = formatter.format(d);
			String mailIds = LGDResourceBundle.getBundle("upload_info", Locale.ENGLISH)	.getObject("lgd.json.scheduler.mailids").toString();
			String title = "Exception while executing LGD Json Scheduler on " + strDate;
			MailService mailService = new MailService();
			StringBuilder mailDescription = new StringBuilder();
			mailDescription.append("<h1>" + title + "</h1><br>");
			mailDescription.append(errorObj.getMessage());

			mailService.sendMail(mailIds, title, mailDescription.toString());
		} catch (Exception e) {
			log.info("mail server not present in this server", e);
		}
	}
	

	
}
	

