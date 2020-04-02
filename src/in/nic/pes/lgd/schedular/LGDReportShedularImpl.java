package in.nic.pes.lgd.schedular;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

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
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;

import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.bean.EmailNotification;
import in.nic.pes.lgd.bean.EntityChangesInGivenTime;
import in.nic.pes.lgd.bean.NotificationLog;
import in.nic.pes.lgd.bean.SubDistrictBlockMapEntities;
import in.nic.pes.lgd.bean.UserRegistration;
import in.nic.pes.lgd.common.MailService;
import in.nic.pes.lgd.draft.service.DraftUtilService;
import in.nic.pes.lgd.resourcebundles.LGDResourceBundle;
import in.nic.pes.lgd.service.InitialService;
import ws.in.nic.pes.lgdws.services.WSService;



public class LGDReportShedularImpl implements LGDRportShedular {
	
	private static final Logger log = Logger.getLogger(LGDReportShedularImpl.class);
	
	private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String FILE_HEADER = "State code,State Name(In English),State census code,District code,District Name(In English),District Census code"
    		+ ",SubDistrict code,Subdistrict Name(In English),Subdistrict census code,Village code,Village Name(In English),Village census code,Block code,Block Name(In English)";
	
	
	@Autowired
	InitialService initialService;
	
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	DraftUtilService draftUtilService;
	
	@Autowired
	WSService wsService;
	
	@Autowired
	MailService mailService;


	@Override
	@Scheduled(cron ="${report.scheduler.cron.time}")
	public void processSchedular() {
		try{
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date d=new Date();
			log.info("Report scheduler start at-"+dateFormat.format(d));
			getSubdistrictVillageBlockMapping();
			log.info("Report scheduler done");
			System.out.println("Report scheduler done");
		}catch(Exception e){
			log.error("Exception while executing LGD Report scheduler-->",e);
			schedulerNotify(e,"LGD Report");
			
		}
		
	}
	
	
	//[lgd 0012459]: Facility to download Subdistrict-villageBlock mapping
	public void getSubdistrictVillageBlockMapping()throws Exception{
				 List<SubDistrictBlockMapEntities> subDistrictBlockMapEntities =initialService.getSubdistrictBlockMaped();
				 if(subDistrictBlockMapEntities!=null && !subDistrictBlockMapEntities.isEmpty()){
					/*FileWriter fileWriter = new FileWriter(file);  
					String json = new Gson().toJson(subDistrictBlockMapEntities);
		         	fileWriter.write(json);  
		            fileWriter.flush();  
		            fileWriter.close(); */
					 Long fileMasterId = Long.parseLong(LocalBodyConstant.ATTACHMENT_MASTER_GO_DOWNLOAD_DIRECTORY.toString());
					 AttachmentMaster master = draftUtilService.getUploadFileConfigurationDetails(fileMasterId);
					 File dir = new File(master.getFileLocation());
					 if(!dir.exists()){
					    	dir.mkdirs();
					    }
					 writeDataListToExcel(subDistrictBlockMapEntities,dir.getAbsolutePath());
			}
	}

	//[lgd 0012459]: Facility to download Subdistrict-villageBlock mapping
	public  void writeDataListToExcel(List<SubDistrictBlockMapEntities> objects,String FILE_PATH)throws Exception{
		
	   
	    	Sheet dataSheet=null;
	    	Workbook workbook=null;
	    	File fileXls = null;
	    	int rowIndex = 0;
	    	int count=0;
	    	
	    	List<String> xlsFiles=new ArrayList<String>();
	    	
	    	File fileCsv = new File(FILE_PATH + File.separator +"subdistrictVillageBlockMappingFile.csv");
	    	
	    	File temp = new File(FILE_PATH + File.separator + "Temp");
	    	
	    	if(!temp.exists()){
	    		temp.mkdirs();
		    }
	    	File tempCsv = new File(temp.getAbsolutePath() + File.separator +"subdistrictVillageBlockMappingFile.csv");
	    	
	    	FileWriter fileWriter = null;
	    	tempCsv.createNewFile();
	    	//fileCsv.createNewFile();
	    	//System.out.println("Now Xlsx file will be create"); 
	    	
			fileWriter = new FileWriter(tempCsv.getAbsolutePath());
			fileWriter.append(FILE_HEADER.toString());
			fileWriter.append(NEW_LINE_SEPARATOR);
	    	
			
			
			workbook= new HSSFWorkbook();
	        dataSheet= workbook.createSheet("dataSheet"+count);
	        
	        Row row = dataSheet.createRow(rowIndex++);
	        
	        int cellIndex = 0;
	        
	        row.createCell(cellIndex++).setCellValue("State code");
	        row.createCell(cellIndex++).setCellValue("State Name(In English)");
	        row.createCell(cellIndex++).setCellValue("State census code");
	        
	        row.createCell(cellIndex++).setCellValue("District code");
	        row.createCell(cellIndex++).setCellValue("District Name(In English)");
	        row.createCell(cellIndex++).setCellValue("District census code");
	        
	        row.createCell(cellIndex++).setCellValue("Subdistrict code");
	        row.createCell(cellIndex++).setCellValue("Subdistrict Name(In English)");
	        row.createCell(cellIndex++).setCellValue("Subdistrict census code");
	        
	        row.createCell(cellIndex++).setCellValue("Village code");
	        row.createCell(cellIndex++).setCellValue("Village Name(In English)");
	        row.createCell(cellIndex++).setCellValue("VillageCunsecCode");
	        
	        row.createCell(cellIndex++).setCellValue("Block code");
	        row.createCell(cellIndex++).setCellValue("Block Name(In English)");
	        int perStateCode=0;
	        for(SubDistrictBlockMapEntities data : objects){
	        	
	        	int currStateCode=data.getStateCode();
	        	
	        	if(perStateCode==0){
	        		fileXls=new File(temp.getAbsolutePath() +File.separator +"subdistrictVillageBlockMappingFile"+data.getStateCode().toString().concat("_1")+".xls");
	    	    	fileXls.createNewFile();
	    	    	xlsFiles.add(fileXls.getName());
	        	}
	        	if((perStateCode!=0 && currStateCode!=perStateCode) || rowIndex>=65535){
	        		FileOutputStream fos = new FileOutputStream(fileXls.getAbsolutePath());
	    	        workbook.write(fos);
	    	        fos.flush();
	    	        fos.close();
	    	        String fileName=currStateCode==perStateCode?"_2":"_1";
	    	        fileXls=new File(temp.getAbsolutePath() +File.separator +"subdistrictVillageBlockMappingFile"+data.getStateCode().toString().concat(fileName)+".xls");
	    	        fileXls.createNewFile();
	    	        xlsFiles.add(fileXls.getName());
	    	        rowIndex=0;
	    	        cellIndex = 0;
	        		workbook= new HSSFWorkbook();
	        		dataSheet= workbook.createSheet("dataSheet"+data.getStateCode().toString().concat(fileName));
	        		row = dataSheet.createRow(rowIndex++);
	        		row.createCell(cellIndex++).setCellValue("State code");
	    	        row.createCell(cellIndex++).setCellValue("State Name(In English)");
	    	        row.createCell(cellIndex++).setCellValue("State census code");
	    	        
	    	        row.createCell(cellIndex++).setCellValue("District code");
	    	        row.createCell(cellIndex++).setCellValue("District Name(In English)");
	    	        row.createCell(cellIndex++).setCellValue("District census code");
	    	        
	    	        row.createCell(cellIndex++).setCellValue("Subdistrict code");
	    	        row.createCell(cellIndex++).setCellValue("Subdistrict Name(In English)");
	    	        row.createCell(cellIndex++).setCellValue("Subdistrict census code");
	    	        
	    	        row.createCell(cellIndex++).setCellValue("Village code");
	    	        row.createCell(cellIndex++).setCellValue("Village Name(In English)");
	    	        row.createCell(cellIndex++).setCellValue("VillageCunsecCode");
	    	        
	    	        row.createCell(cellIndex++).setCellValue("Block code");
	    	        row.createCell(cellIndex++).setCellValue("Block Name(In English)");
	        		
	        	}
	        	row = dataSheet.createRow(rowIndex++);
	            cellIndex = 0;
	            String scode=data.getStateCode()!=null?data.getStateCode().toString():"";
	            String sname=data.getStateNameEnglish()!=null?data.getStateNameEnglish().toString():"";
	            String sCensusCode=data.getStateCensus2011code()!=null?data.getStateCensus2011code().toString():"";
	            String dcode=data.getDistrictCode()!=null?data.getDistrictCode().toString():"";
	            String dname=data.getDistrictNameEnglish()!=null?data.getDistrictNameEnglish().toString():"";
	            String dCensusCode=data.getdCensus2011code()!=null?data.getdCensus2011code().toString():"";
	            String tcode=data.getSubdistrictCode()!=null?data.getSubdistrictCode().toString():"";
	            String tname=data.getSubdistrictNameEnglish()!=null?data.getSubdistrictNameEnglish().toString():"";
	           	String tCensusCode=data.gettCensus2011code()!=null?data.gettCensus2011code().toString():"";
	            String vcode=data.getVillageCode()!=null?data.getVillageCode().toString():"";
	            
	            String vCensusCode=data.getvCensus2011code()!=null?data.getvCensus2011code().toString():"";
	            String vname=data.getVillageNameEnglish()!=null?data.getVillageNameEnglish().toString():"";
	            String bcode=data.getBlockCode()!=null?data.getBlockCode().toString():"";
	            String bname=data.getBlockNameEnglish()!=null?data.getBlockNameEnglish().toString():"";
	            
	             row.createCell(cellIndex++).setCellValue(scode);
	             row.createCell(cellIndex++).setCellValue(sname);
	             row.createCell(cellIndex++).setCellValue(sCensusCode);
	             row.createCell(cellIndex++).setCellValue(dcode);
	             row.createCell(cellIndex++).setCellValue(dname);
	             row.createCell(cellIndex++).setCellValue(dCensusCode);
	             row.createCell(cellIndex++).setCellValue(tcode);
	             row.createCell(cellIndex++).setCellValue(tname);
	             row.createCell(cellIndex++).setCellValue(tCensusCode);
	             row.createCell(cellIndex++).setCellValue(vcode);
	             row.createCell(cellIndex++).setCellValue(vname);
	             row.createCell(cellIndex++).setCellValue(vCensusCode);
	             row.createCell(cellIndex++).setCellValue(bcode);
	             row.createCell(cellIndex++).setCellValue(bname);
	             
	             perStateCode=data.getStateCode();
	             
	            fileWriter.append(String.valueOf(scode));
	 			fileWriter.append(COMMA_DELIMITER);
	 			fileWriter.append(String.valueOf(sname.replace(",", ".")));
	 			fileWriter.append(COMMA_DELIMITER);
	 			fileWriter.append(String.valueOf(sCensusCode));
	 			fileWriter.append(COMMA_DELIMITER);
	 			fileWriter.append(String.valueOf(dcode));
	 			fileWriter.append(COMMA_DELIMITER);
	 			fileWriter.append(String.valueOf(dname.replace(",", ".")));
	 			fileWriter.append(COMMA_DELIMITER);
	 			fileWriter.append(String.valueOf(dCensusCode));
	 			fileWriter.append(COMMA_DELIMITER);
	 			fileWriter.append(String.valueOf(tcode));
	 			fileWriter.append(COMMA_DELIMITER);
	 			fileWriter.append(String.valueOf(tname.replace(",", ".")));
	 			fileWriter.append(COMMA_DELIMITER);
	 			fileWriter.append(String.valueOf(tCensusCode));
	 			fileWriter.append(COMMA_DELIMITER);
	 			fileWriter.append(String.valueOf(vcode));
	 			fileWriter.append(COMMA_DELIMITER);
	 			fileWriter.append(String.valueOf(vname.replace(",", ".")));
	 			fileWriter.append(COMMA_DELIMITER);
	 			fileWriter.append(String.valueOf(vCensusCode));
	 			fileWriter.append(COMMA_DELIMITER);
	 			fileWriter.append(String.valueOf(bcode));
	 			fileWriter.append(COMMA_DELIMITER);
	 			fileWriter.append(String.valueOf(bname.replace(",", ".")));
	 			fileWriter.append(NEW_LINE_SEPARATOR);
	        }
	        fileWriter.flush();
			fileWriter.close();
			
			FileOutputStream fos = new FileOutputStream(fileXls.getAbsolutePath());
	        workbook.write(fos);
	        fos.flush();
	        fos.close();
	        log.info(" file created sucessfully");
	        
			if(fileCsv.delete()){
				if(tempCsv.renameTo(new File(FILE_PATH + File.separator +  tempCsv.getName()))){
					log.info("File is moved successful!");
		    	   }else{
		    		   log.info("File is failed to move!");
		    	   }
			}
			else{
				if(tempCsv.renameTo(new File(FILE_PATH + File.separator +  tempCsv.getName()))){
					log.info("File is moved successful!");
		    	   }else{
		    		   log.info("File is failed to move!");
		    	   }
			}
			
			for (String string : xlsFiles) {
				File files = new File(FILE_PATH + File.separator +string);
				if(files.delete()){
					File tempXls=new File(temp.getAbsolutePath() +File.separator +string);
					if(tempXls.renameTo(new File(FILE_PATH + File.separator +string))){
						log.info("File is moved successful!");
			    	   }else{
			    		   log.info("File is failed to move!");
			    	   }
				}
				else{
					File tempXls=new File(temp.getAbsolutePath() +File.separator +string);
					if(tempXls.renameTo(new File(FILE_PATH + File.separator +string))){
						log.info("File is moved successful!");
			    	   }else{
			    		   log.info("File is failed to move!");
			    	   }
				}
			}	
			
			
	   


	}
	
	public void schedulerNotify(Exception errorObj,String type){
		try{
			Date d=new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String strDate= formatter.format(d);  
			String mailIds = LGDResourceBundle.getBundle("upload_info", Locale.ENGLISH).getObject("lgd.json.scheduler.mailids").toString();
			String title="Exception while executing LGD "+type+" scheduler on "+strDate;
			MailService mailService = new MailService();
			StringBuilder mailDescription=new StringBuilder();
			mailDescription.append("<h1>"+title+"</h1><br>");
			mailDescription.append(errorObj.toString());
			
			mailService.sendMail(mailIds, title, mailDescription.toString());
			}catch(Exception e){
				log.info("mail server not present in this server",e);
			}
	}
	

	@Override
	@Scheduled(cron ="${subscribe.file.scheduler.cron.time}")
	public void sendEmailSMSNotification() {
		try{
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date d=new Date();
			log.info("Mail send every Monday scheduler start at-"+dateFormat.format(d));
			Long fileMasterId = Long.parseLong(LocalBodyConstant.ATTACHMENT_MASTER_GO_NOTIFICATION.toString());
			 AttachmentMaster master = draftUtilService.getUploadFileConfigurationDetails(fileMasterId);
			 File dir = new File(master.getFileLocation());
			 if(!dir.exists()){
			    	dir.mkdirs();
			    }
				 sendMailWithAttachment(dir);
				 
		}catch(Exception e){
			e.printStackTrace();
			schedulerNotify(e,"mail every Monday");
			log.error("Exception while executing LGD json scheduler-->",e);
		}
		
	}
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	Date now = new Date();
	public void getEntityDetails(int userId,File temp,int slc,boolean district,boolean subDistrict,boolean village, String localBody,boolean block)throws Exception{
		List<EntityChangesInGivenTime> changesInGivenTimes=null;
		File sendNotificationFile=null;
		Sheet dataSheet=null;
    	Workbook workbook=null;
    	int rowIndex=0;
		try{
			changesInGivenTimes=initialService.getEntityChangesInGivenTime(slc,district,subDistrict,village,localBody,block);
			sendNotificationFile=new File(temp.getAbsolutePath() +File.separator +"subscibeNotification"+dateFormat.format(now)+"_"+userId+".xls");
			sendNotificationFile.createNewFile();
			workbook= new HSSFWorkbook();
	        dataSheet= workbook.createSheet("dataSheet");
	        
	        Row row = dataSheet.createRow(rowIndex++);
	        row.createCell(0).setCellValue("S.No.");
	        row.createCell(1).setCellValue("Entity Type");
	        row.createCell(2).setCellValue("Entity Code");
	        row.createCell(3).setCellValue("Entity Version");
	        row.createCell(4).setCellValue("Entity Name (In English)");
	        row.createCell(5).setCellValue("Entity Name (In Local)");
	        row.createCell(6).setCellValue("Census Code");
	        row.createCell(7).setCellValue("Operation");
	        
	        for (EntityChangesInGivenTime object : changesInGivenTimes) {
	        	int cellIndex=0;
	        	row = dataSheet.createRow(rowIndex++);
	 	        row.createCell(cellIndex++).setCellValue(object.getSrNumber());
	 	        row.createCell(cellIndex++).setCellValue(object.getEntityType());
	 	        row.createCell(cellIndex++).setCellValue(object.getEntityCode());
	 	        row.createCell(cellIndex++).setCellValue(object.getEntityVersion());
	 	        row.createCell(cellIndex++).setCellValue(object.getEntityNameEnglish());
	 	        row.createCell(cellIndex++).setCellValue(object.getEntityNameLocal());
	 	        row.createCell(cellIndex++).setCellValue(object.getCensus2011Code());
	 	        row.createCell(cellIndex++).setCellValue(object.getOperationPerformedOnEntity());
			}
	        FileOutputStream fos = new FileOutputStream(sendNotificationFile.getAbsolutePath());
	        workbook.write(fos);
	        fos.flush();
	        fos.close();
	        System.out.println(" file created sucessfully");
	        
		}
		catch(Exception exception){
				exception.printStackTrace();
		}
	}
	
	public void sendMailWithAttachment(File temp){
		
		List<EmailNotification> emailNotifications=null;
		StringBuilder localBody=new StringBuilder();
		String localBodyList="";
		int slc=0;
		try{
			emailNotifications=initialService.getUserInformation();
			for (EmailNotification emailNotification : emailNotifications) {
				 localBody=new StringBuilder();
				UserRegistration  userRegistration= wsService.getRegistration(emailNotification.getUserid());
				if(emailNotification.getRuralbody()){
					localBody.append("p,");
				}
				else{
					localBody.append("0,");
				}
				
				if(emailNotification.getTradionbody()){
					localBody.append("t,");
				}
				else{
					localBody.append("0,");
				}
				if(emailNotification.getUrbanbody()){
					localBody.append("u,");
				}
				else{
					localBody.append("0,");
				}
				
					
				Date then=new Date(now.getYear(),now.getMonth(),now.getDate()-7);
			
				
				
			  
			   
				
				localBodyList=localBody.substring(0,localBody.length()-1);
				slc=emailNotification.getSlc();
				getEntityDetails(userRegistration.getUserRegistrationId(),temp,slc,emailNotification.getDistrict(),emailNotification.getSubdistrict(),emailNotification.getVillage(),localBodyList,emailNotification.getBlock());
				File file=new File(temp.getAbsolutePath() + File.separator +"subscibeNotification"+dateFormat.format(now)+"_"+userRegistration.getUserRegistrationId()+".xls");
				NotificationLog notificationlog=new NotificationLog();
				notificationlog.setFile(file);
				notificationlog.setSubject("List of Changes occurred in Local Government Directory last week");
				notificationlog.setMsgDesc("<html xmlns:v=\"urn:schemas-microsoft-com:vml\"\r\n" + 
						"xmlns:o=\"urn:schemas-microsoft-com:office:office\"\r\n" + 
						"xmlns:w=\"urn:schemas-microsoft-com:office:word\"\r\n" + 
						"xmlns:m=\"http://schemas.microsoft.com/office/2004/12/omml\"\r\n" + 
						"xmlns=\"http://www.w3.org/TR/REC-html40\">\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"<body lang=EN-US link=blue vlink=purple style='tab-interval:36.0pt'>\r\n" + 
						"\r\n" + 
						"<div class=Section1>\r\n" + 
						"\r\n" + 
						"<p class=MsoNormal><span lang=EN-IN style='mso-ansi-language:EN-IN'>Dear\r\n" + 
						"Sir/Madam,<o:p></o:p></span></p>\r\n" + 
						"\r\n" + 
						"<p class=MsoNormal><span lang=EN-IN style='mso-ansi-language:EN-IN'><span\r\n" + 
						"style='mso-spacerun:yes'></span><span style='mso-tab-count:2'></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Please\r\n" + 
						"find attachment list of update done in 'Local Government Directory' between <span\r\n" + 
						"class=SpellE>"+dateFormat.format(then)+"</span> and <span class=SpellE>"+ dateFormat.format(now)+"</span>.<o:p></o:p></span></p>\r\n" + 
						"\r\n" + 
						"<p class=MsoNormal><span lang=EN-IN style='mso-ansi-language:EN-IN'><span\r\n" + 
						"style='mso-spacerun:yes'></span><br/>For any query, you may drop a mail at <a\r\n" + 
						"href=\"mailto:lgdirectory@gov.in\">lgdirectory@gov.in</a><o:p></o:p></span></p>\r\n" + 
						"\r\n" + 
						"<p class=MsoNormal><span lang=EN-IN style='mso-ansi-language:EN-IN'><o:p>&nbsp;</o:p></span></p>\r\n" + 
						"\r\n" + 
						"<p class=MsoNormal><span lang=EN-IN style='mso-ansi-language:EN-IN'>Regards<o:p></o:p></span></p>\r\n" + 
						"\r\n" + 
						"<p class=MsoNormal><span lang=EN-IN style='mso-ansi-language:EN-IN'>LGD Team<o:p></o:p></span></p>\r\n" + 
						"\r\n" + 
						"<p class=MsoNormal><span lang=EN-IN style='mso-ansi-language:EN-IN'><o:p>&nbsp;</o:p></span></p>\r\n" + 
						"\r\n" + 
						"</div>\r\n" + 
						"\r\n" + 
						"</body>\r\n" + 
						"\r\n" + 
						"</html>");
				notificationlog.setEmailId(userRegistration.getEmail());
				mailService.sendMail(notificationlog.getEmailId(), notificationlog.getSubject(), notificationlog.getMsgDesc(), file);
			}
			
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
	}
	
	
	
}
