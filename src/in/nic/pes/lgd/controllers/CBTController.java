package in.nic.pes.lgd.controllers;


import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import in.nic.pes.lgd.common.DownloadFiles;
import in.nic.pes.lgd.resourcebundles.LGDResourceBundle;

@Controller
public class CBTController { // NO_UCD (unused code)
	private static final Logger log = Logger.getLogger(CBTController.class);
		
	@RequestMapping(value="/downLoadCBT")
    public ModelAndView downLoadCBT( HttpServletResponse response,HttpServletRequest request) throws ServletException,IOException {
		String directoryLocation = LGDResourceBundle.getBundle("upload_info", Locale.ENGLISH).getObject("userManual.directory.location").toString();
		String fileName = LGDResourceBundle.getBundle("upload_info", Locale.ENGLISH).getObject("supporting.doc.file.cbt").toString();
		String filePath = directoryLocation+File.separator+fileName;
		log.info("FILE PATH::: "+filePath);
		System.out.println("FILE PATH::: "+filePath);
		DownloadFiles downloadFiles = new DownloadFiles();
		byte[] content = downloadFiles.getFileContent(filePath);
		if(content.length != 0) {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition"," filename=\"" +fileName);
			FileCopyUtils.copy(content, response.getOutputStream());
			return null;
		} else {
			ModelAndView modelAndView = new ModelAndView("welcome");
			modelAndView.addObject("msg", "File Not found !!");
			return modelAndView;
		}
    }
	
	
	@RequestMapping(value="/viewBrochure")
    public ModelAndView viewBrochure( HttpServletResponse response,HttpServletRequest request) throws ServletException,IOException {
		String directoryLocation = LGDResourceBundle.getBundle("upload_info", Locale.ENGLISH).getObject("userManual.directory.location").toString();
		String fileName = LGDResourceBundle.getBundle("upload_info", Locale.ENGLISH).getObject("supporting.doc.file.brochure").toString();
		String filePath = directoryLocation+File.separator+fileName;
		log.info("FILE PATH::: "+filePath);
		System.out.println("FILE PATH::: "+filePath);
		DownloadFiles downloadFiles = new DownloadFiles();
		byte[] content = downloadFiles.getFileContent(filePath);
		if(content.length != 0) {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition"," filename=\"" +fileName);
			FileCopyUtils.copy(content, response.getOutputStream());
			return null;
		} else {
			ModelAndView modelAndView = new ModelAndView("supportingDocument");
			modelAndView.addObject("msg", "File Not found !!");
			return modelAndView;
		}
    }
	@RequestMapping(value="/dataRegister")
    public ModelAndView dataRegister( HttpServletResponse response,HttpServletRequest request) throws ServletException,IOException {
		String directoryLocation = LGDResourceBundle.getBundle("upload_info", Locale.ENGLISH).getObject("userManual.directory.location").toString();
		String fileName = LGDResourceBundle.getBundle("upload_info", Locale.ENGLISH).getObject("supporting.doc.file.register").toString();
		String filePath = directoryLocation+File.separator+fileName;
		log.info("FILE PATH::: "+filePath);
		System.out.println("FILE PATH::: "+filePath);
		DownloadFiles downloadFiles = new DownloadFiles();
		byte[] content = downloadFiles.getFileContent(filePath);
		if(content.length != 0) {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition"," filename=\"" +fileName);
			FileCopyUtils.copy(content, response.getOutputStream());
			return null;
		} else {
			ModelAndView modelAndView = new ModelAndView("welcome");
			modelAndView.addObject("msg", "File Not found !!");
			return modelAndView;
		}
    }
	@RequestMapping(value="/viewPresentation")
    public ModelAndView viewPresentation( HttpServletResponse response,HttpServletRequest request) throws ServletException,IOException {
		String directoryLocation = LGDResourceBundle.getBundle("upload_info", Locale.ENGLISH).getObject("userManual.directory.location").toString();
		String fileName = LGDResourceBundle.getBundle("upload_info", Locale.ENGLISH).getObject("supporting.doc.file.presentation").toString();
		String filePath = directoryLocation+File.separator+fileName;
		System.out.println("FILE PATH::: "+filePath);
		DownloadFiles downloadFiles = new DownloadFiles();
		byte[] content = downloadFiles.getFileContent(filePath);
		if(content.length != 0) {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition"," filename=\"" +fileName);
			FileCopyUtils.copy(content, response.getOutputStream());
			return null;
		} else {
			ModelAndView modelAndView = new ModelAndView("welcome");
			modelAndView.addObject("msg", "File Not found !!");
			return modelAndView;
		}
    }
	
	@RequestMapping(value="/viewManual")
    public ModelAndView viewManual( HttpServletResponse response,HttpServletRequest request) throws ServletException,IOException {
		String directoryLocation = LGDResourceBundle.getBundle("upload_info", Locale.ENGLISH).getObject("userManual.directory.location").toString();
		String fileName = LGDResourceBundle.getBundle("upload_info", Locale.ENGLISH).getObject("supporting.doc.file.usermanual").toString();
		String filePath = directoryLocation+File.separator+fileName;
		log.info("FILE PATH::: "+filePath);
		System.out.println("FILE PATH::: "+filePath);
		DownloadFiles downloadFiles = new DownloadFiles();
		byte[] content = downloadFiles.getFileContent(filePath);
		if(content.length != 0) {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition"," filename=\"" +fileName);
			FileCopyUtils.copy(content, response.getOutputStream());
			return null;
		} else {
			ModelAndView modelAndView = new ModelAndView("supportingDocument");
			modelAndView.addObject("msg", "File Not found !!");
			return modelAndView;
		}
    }
	
	@RequestMapping(value="/viewManualGIS")
    public ModelAndView viewManualGIS( HttpServletResponse response,HttpServletRequest request) throws ServletException,IOException {
		String directoryLocation = LGDResourceBundle.getBundle("upload_info", Locale.ENGLISH).getObject("userManual.directory.location").toString();
		String fileName = LGDResourceBundle.getBundle("upload_info", Locale.ENGLISH).getObject("supporting.doc.file.usermanual.gis").toString();
		String filePath = directoryLocation+File.separator+fileName;
		log.info("FILE PATH::: "+filePath);
		System.out.println("FILE PATH::: "+filePath);
		DownloadFiles downloadFiles = new DownloadFiles();
		byte[] content = downloadFiles.getFileContent(filePath);
		if(content.length != 0) {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition"," filename=\"" +fileName);
			FileCopyUtils.copy(content, response.getOutputStream());
			return null;
		} else {
			ModelAndView modelAndView = new ModelAndView("welcome");
			modelAndView.addObject("msg", "File Not found !!");
			return modelAndView;
		}
    }
	
	@RequestMapping(value="/viewManualLDC")
    public ModelAndView viewManualLDC( HttpServletResponse response,HttpServletRequest request) throws ServletException,IOException {
		String directoryLocation = LGDResourceBundle.getBundle("upload_info", Locale.ENGLISH).getObject("userManual.directory.location").toString();
		String fileName = LGDResourceBundle.getBundle("upload_info", Locale.ENGLISH).getObject("supporting.doc.file.usermanual.LDC").toString();
		String filePath = directoryLocation+File.separator+fileName;
		log.info("FILE PATH::: "+filePath);
		System.out.println("FILE PATH::: "+filePath);
		DownloadFiles downloadFiles = new DownloadFiles();
		byte[] content = downloadFiles.getFileContent(filePath);
		if(content.length != 0) {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition"," filename=\"" +fileName);
			FileCopyUtils.copy(content, response.getOutputStream());
			return null;
		} else {
			ModelAndView modelAndView = new ModelAndView("welcome");
			modelAndView.addObject("msg", "File Not found !!");
			return modelAndView;
		}
    }
	
	@RequestMapping(value="/androidLGD")
    public ModelAndView androidLGD( HttpServletResponse response,HttpServletRequest request) throws ServletException,IOException {
		String directoryLocation = LGDResourceBundle.getBundle("upload_info", Locale.ENGLISH).getObject("userManual.directory.location").toString();
		String fileName = LGDResourceBundle.getBundle("upload_info", Locale.ENGLISH).getObject("supporting.doc.file.android").toString();
		String filePath = directoryLocation+File.separator+fileName;
		log.info("FILE PATH::: "+filePath);
		System.out.println("FILE PATH::: "+filePath);
		DownloadFiles downloadFiles = new DownloadFiles();
		byte[] content = downloadFiles.getFileContent(filePath);
		if(content.length != 0) {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition"," filename=\"" +fileName);
			FileCopyUtils.copy(content, response.getOutputStream());
			return null;
		} else {
			ModelAndView modelAndView = new ModelAndView("welcome");
			modelAndView.addObject("msg", "File Not found !!");
			return modelAndView;
		}
    }
	/*@RequestMapping(value="/supportdownloaddoc", method = RequestMethod.GET) 
	public ModelAndView supportDownloadDoc(Map<String, Object> model, 
										   @Value("#{servletContext.contextPath}")String contextPath,
										   @RequestParam("link") String link,
										   @RequestParam("filename") String filename,
										   @RequestParam("propkey") String propkey) throws ServletException,IOException {
		String filedescription = null;
		int docindex = link.indexOf("docType");
	    if(docindex != -1) {	
	    	String doctype=link.substring(link.indexOf('=')+1);
	    	
	    	double filelength=0;
	    	String lastmodified=null;
	    	
	    	ResourceBundle bundle = LGDResourceBundle.getBundle("upload_info", Locale.ENGLISH);

			String directoryLocation = bundle.getString("userManual.directory.location");
			String fileName = bundle.getString(String.format("supporting.doc.file.%s", propkey));
			String filePath = directoryLocation + File.separator + fileName;
			String filetype = FilenameUtils.getExtension(filePath);
			
			File f = new File(filePath);
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
			lastmodified = sdf.format(f.lastModified());
			filelength=f.length();
			DecimalFormat df = new DecimalFormat();
			df.setMaximumFractionDigits(2);
			String lenMb=df.format((filelength/1048576));
			
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
			}else if(doctype.equalsIgnoreCase("UserManualfilepathGIS")){
				link="viewManualGIS.do";
				
				filedescription="User Manual of GIS Module";
				
			}else if(doctype.equalsIgnoreCase("Brochure")){
				link="viewBrochure.do";
				filedescription="Local Government Directory is one of the applications developed as part of Panchayat Enterprise Suite (PES) developed under ePanchayat " +
								"Mission Mode Project (MMP). Primary objective of Local Government directory is to facilitate State Departments to update the directory with newly formed " +
							   "panchayats/local bodies,re-organization in panchayats, conversion from Rural to Urban area etc and provide the same info in public domain.";
				
			} else if(doctype.equalsIgnoreCase("DataCollectionRegisterfilepath")){
				link="dataRegister.do";
				filedescription="The data registers are the set of records in the spreadsheet representation.It includes various details related with the Local Government Directory web application.";
			}
		
			String icon=null;
			if(filetype.equalsIgnoreCase("pdf")) {
				icon="fa fa-file-pdf-o";
			}else if(filetype.equalsIgnoreCase("rar")) {
				icon="fa fa-file-archive-o";
			}else if(filetype.equalsIgnoreCase("xlsx")) {
				icon="fa fa-file-excel-o";
			}else if(filetype.equalsIgnoreCase("pptx")) {
				icon="fa fa-file-powerpoint-o";
			}
			
			
			model.put("icon", icon);
			model.put("filedescription", filedescription);
			model.put("length", lenMb);
			model.put("filetype", filetype);
			model.put("lastmodified", lastmodified);
	    } else {
	    	filedescription=" The computer based training (CBT), played online or offline includes the following aspects:<br>" +
					"1. Responses are recorded and scored electronically. <br>"+
					"2. Demographic data collection and reporting. <br>"+
					"3. Test administration via computer. <br>"+
					"4. Step by step description of the topic and relevant module.";
	    	model.put("filedescription", filedescription);
	    }
	    model.put("docindex", docindex);
	    model.put("filename", filename);
		model.put("link", link);
		 
		ModelAndView modelAndView = new ModelAndView("supportingDocument");
			
		return modelAndView;
	}*/
	
	@RequestMapping(value="/viewManualUrbanDevelopment")
    public ModelAndView viewManualUrbanDevelopment( HttpServletResponse response,HttpServletRequest request) throws ServletException,IOException {
		String directoryLocation = LGDResourceBundle.getBundle("upload_info", Locale.ENGLISH).getObject("userManual.directory.location").toString();
		String fileName = LGDResourceBundle.getBundle("upload_info", Locale.ENGLISH).getObject("supporting.doc.file.usermanual.urban.development").toString();
		String filePath = directoryLocation+File.separator+fileName;
		log.info("FILE PATH::: "+filePath);
		System.out.println("FILE PATH::: "+filePath);
		DownloadFiles downloadFiles = new DownloadFiles();
		byte[] content = downloadFiles.getFileContent(filePath);
		if(content.length != 0) {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition"," filename=\"" +fileName);
			FileCopyUtils.copy(content, response.getOutputStream());
			return null;
		} else {
			ModelAndView modelAndView = new ModelAndView("supportingDocument");
			modelAndView.addObject("msg", "File Not found !!");
			return modelAndView;
		}
    }
	
	
	@RequestMapping(value="/downloadDoc")
    public ModelAndView downloadDoc( HttpServletResponse response,HttpServletRequest request) throws ServletException,IOException {
		String directoryLocation = LGDResourceBundle.getBundle("upload_info", Locale.ENGLISH).getObject("webservice.directory.location").toString();
		String fileType = request.getParameter("fileType");
		if(fileType!=null && fileType.length()>0 && (fileType.charAt(0)=='U' || fileType.charAt(0)=='W') ) {
			String fileName='U'==fileType.charAt(0) ?LGDResourceBundle.getBundle("upload_info", Locale.ENGLISH).getObject("supporting.doc.file.webService.usermanual").toString():
			LGDResourceBundle.getBundle("upload_info", Locale.ENGLISH).getObject("supporting.doc.file.webServicePdf").toString();
			String filePath = directoryLocation+File.separator+fileName;
			log.info("FILE PATH::: "+filePath);
			System.out.println("FILE PATH::: "+filePath);
			DownloadFiles downloadFiles = new DownloadFiles();
			byte[] content = downloadFiles.getFileContent(filePath);
			if(content.length != 0) {
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition"," filename=\"" +fileName);
				FileCopyUtils.copy(content, response.getOutputStream());
				return null;
		}
			
			
			ModelAndView modelAndView = new ModelAndView("errorHomePage");
			modelAndView.addObject("errorMsg", "File Not found !!");
			return modelAndView;
		
	
		} else {
			ModelAndView modelAndView = new ModelAndView("errorHomePage");
			modelAndView.addObject("errorMsg", "File Not found !!");
			return modelAndView;
		}
    }
	
	@RequestMapping(value="/viewManualRevenueDevelopment")
    public ModelAndView viewManualRevenueDevelopment( HttpServletResponse response,HttpServletRequest request) throws ServletException,IOException {
		String directoryLocation = LGDResourceBundle.getBundle("upload_info", Locale.ENGLISH).getObject("userManual.directory.location").toString();
		String fileName = LGDResourceBundle.getBundle("upload_info", Locale.ENGLISH).getObject("supporting.doc.file.usermanual.revenue.development").toString();
		String filePath = directoryLocation+File.separator+fileName;
		log.info("FILE PATH::: "+filePath);
		System.out.println("FILE PATH::: "+filePath);
		DownloadFiles downloadFiles = new DownloadFiles();
		byte[] content = downloadFiles.getFileContent(filePath);
		if(content.length != 0) {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition"," filename=\"" +fileName);
			FileCopyUtils.copy(content, response.getOutputStream());
			return null;
		} else {
			ModelAndView modelAndView = new ModelAndView("supportingDocument");
			modelAndView.addObject("msg", "File Not found !!");
			return modelAndView;
		}
    }
	
}