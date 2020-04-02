package in.nic.pes.lgd.controllers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.report.engine.api.EXCELRenderOption;
import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.IPDFRenderOption;
import org.eclipse.birt.report.engine.api.IRenderOption;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.PDFRenderOption;
import org.eclipse.birt.report.engine.api.RenderOption;
import org.eclipse.birt.report.engine.emitter.csv.CSVRenderOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cmc.lgd.localbody.rules.LocalBodyConstant;
import com.org.ep.exceptionhandler.ExceptionHandlerFactory;
import com.org.ep.exceptionhandler.IExceptionHandler;

import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.draft.constant.DraftConstant;
import in.nic.pes.lgd.draft.service.DraftUtilService;
import in.nic.pes.lgd.forms.DownloadDirectoryForm;
import in.nic.pes.lgd.service.StateService;
import in.nic.pes.lgd.utils.ApplicationConstant;
import in.nic.pes.lgd.validator.DownloadDirectoryValidator;

@Controller
public class DownloadDirectoryController { // NO_UCD (unused code)
	
	@Autowired
	private StateService stateService;
	
	@Autowired
	private DownloadDirectoryValidator downloadDirectoryValidator;

	@Autowired
	DraftUtilService draftUtilService;
	
	boolean flagSch=false;
	
	/**
	 * initBinder set the some basic details in sectionForm
	 * @param binder
	 * @param session
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpSession session) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(DraftConstant.CURRENT_DATE_PATTERN.toString());
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.getBindingResult();
        binder.validate();
  
	}
	
	/**
	 * The {@code errorHandler} returns error path and saved required stack trace. 
	 * @param request
	 * @param e
	 * @return
	 */
	private String errorHandler(HttpServletRequest request, Exception e){
		IExceptionHandler expHandler = ExceptionHandlerFactory.getInstance().create();
		return expHandler.handleSaveRedirectException(request, "", "label.lgd", 1, e);
	}
	
		
	/**
	 * This Get Method of Download directory Which is used Birt Engine to generate Report
	 * @author Maneesh Kumar  
	 * @param session
	 * @param request
	 * @param response
	 * @param model
	 * @param downloadDirectoryForm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/downloadDirectory",method=RequestMethod.GET)
	public ModelAndView downloadDirectoryNew(HttpSession session,HttpServletRequest request,HttpServletResponse response, Model model,@ModelAttribute("downloadDirectoryForm") DownloadDirectoryForm downloadDirectoryForm) throws Exception{
		ModelAndView mav=null;
		try{
			mav=new ModelAndView("downloadDirectory");
			model.addAttribute("stateList", stateService.getStateSourceList());
			downloadDirectoryForm.setCaptchaAnswer(null);
		} catch (Exception ex) {
			mav = new ModelAndView(errorHandler(request, ex));
		}
		return mav;
	}
	
	
	/**
	 * This Post Method of Download directory Which is used Birt Engine to generate Report
	 * @author Maneesh Kumar  
	 * @param session
	 * @param request
	 * @param response
	 * @param model
	 * @param downloadDirectoryForm
	 * @param binding
	 * @return
	 * @throws Exception
	 */

	@RequestMapping(value="/downloadDirectory",method=RequestMethod.POST)
	public ModelAndView downloadDirectoryNewPost(HttpSession session,HttpServletRequest request,HttpServletResponse response, Model model,@ModelAttribute("downloadDirectoryForm") DownloadDirectoryForm downloadDirectoryForm,final BindingResult binding) throws Exception{
		ModelAndView mav=null;
		try{
			mav=new ModelAndView("downloadDirectory");
			downloadDirectoryValidator.validate(downloadDirectoryForm, binding, session);
			model.addAttribute("stateList", stateService.getStateSourceList());
			if (binding.hasErrors()) {
				model.addAttribute("serverFlag",true);
				downloadDirectoryForm.setCaptchaAnswer(null);
				downloadDirectoryForm.setFromDate(null);
				downloadDirectoryForm.setToDate(null);
				return mav;	
			}
			model.addAttribute("serverFlag",false);
			if(("DFD").equals(downloadDirectoryForm.getDownloadOption())){
				String reportName=downloadDirectoryForm.getRptFileName().split("@")[0];
				Integer entityCode =getEntityCode(downloadDirectoryForm.getEntityCodes());
				if(reportName.equalsIgnoreCase("statewise_ulbs_coverage") || reportName.equalsIgnoreCase("allBlockofIndiawithCoverage") ){
					generateMultipleReportInZip(reportName,entityCode,downloadDirectoryForm.getDownloadType(),request,response);
					
				}
				//[lgd 0012459]: Facility to download Subdistrict-villageBlock mapping start
				else if("allSubdistrictVillageBlockMapping".equals(reportName)){
					generateZipFileShc(downloadDirectoryForm.getDownloadType(),request, response);
				}
				//[lgd 0012459]: Facility to download Subdistrict-villageBlock mapping end
				else{
					generateReport(reportName,downloadDirectoryForm,downloadDirectoryForm.getDownloadType(),request,response);
				}
				
			}else if(("DMO").equals(downloadDirectoryForm.getDownloadOption())){
					String reportName=downloadDirectoryForm.getRptFileNameMod().split("@")[0];
					Integer entityCode =getEntityCode(downloadDirectoryForm.getEntityCodes());
					
					generateReportOnlyModification(reportName,entityCode,downloadDirectoryForm.getFromDate(),downloadDirectoryForm.getToDate(),downloadDirectoryForm.getDownloadType(),request,response);
				}
			
			else{
				generateMultipleReportInZip(downloadDirectoryForm.getMultiRptFileNames(),downloadDirectoryForm.getEntityCode(),downloadDirectoryForm.getDownloadType(),request,response);
			}
			
			mav=null;
		} catch (Exception ex) {
			ex.printStackTrace();
			mav = new ModelAndView(errorHandler(request, ex));
		}
		return mav;
	}
	

	/**
	 *  This method load design file and render the file and generate report  
	 * @author Maneesh Kumar  
	 * @param reportName
	 * @param stateCode
	 * @param reportOption
	 * @param request
	 * @param response
	 * @throws BirtException
	 * @throws IOException
	 */
	private void  generateReport(String reportName, DownloadDirectoryForm downloadDirectoryForm, String reportOption, HttpServletRequest request, HttpServletResponse response) throws BirtException, IOException {
		IReportRunnable design = null;
		IRunAndRenderTask task = null;
		OutputStream  out = null;
		IRenderOption options=null;
		
		try {
			
			SimpleDateFormat sdf=  new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss:SSS");
			response.setHeader("Content-Disposition", "attachment; filename="+reportName+sdf.format(new Date())+"."+reportOption);
			Integer entityCode = getEntityCode(downloadDirectoryForm.getEntityCodes());
		    Map<String, Object> birtParams = new HashMap<String, Object>();
			birtParams.put("entity_code",entityCode);
			birtParams.put("serverUrl", request.getRequestURL().substring(0,request.getRequestURL().lastIndexOf("/")));
			birtParams.put("blockName", downloadDirectoryForm.getBlockName());
			birtParams.put("districtName", downloadDirectoryForm.getDistrictName());
			birtParams.put("stateName", downloadDirectoryForm.getStateName());
			String newPath = request.getServletContext().getRealPath("/").replace("\\", "/");
			out=response.getOutputStream();
			design = ApplicationConstant.eng.openReportDesign(newPath + "/rptDesignFiles/" + reportName + ".rptdesign");
			task = ApplicationConstant.eng.createRunAndRenderTask(design);
			task.setParameterValues(birtParams);
			options= new RenderOption();
				if (reportOption.equals("pdf")) {
					response.setContentType("pdf");
					PDFRenderOption pdfOptions = new PDFRenderOption(options);
					pdfOptions.setOutputFormat("pdf");
					//pdfOptions.setOption(IPDFRenderOption.PAGE_OVERFLOW, IPDFRenderOption.FIT_TO_PAGE_SIZE);
					pdfOptions.setOption(IPDFRenderOption.PAGE_OVERFLOW, IPDFRenderOption.OUTPUT_TO_MULTIPLE_PAGES);

					// Dafür sorgen, dass Texte nicht abgeschnitten werden, sondern umgebrochen:
					pdfOptions.setOption(IPDFRenderOption.PDF_TEXT_WRAPPING, true);
					pdfOptions.setOption(IPDFRenderOption.PDF_HYPHENATION, true);
					
					
					pdfOptions.setOutputStream(out);
					task.setRenderOption(pdfOptions);
				} else if (reportOption.equals("xls")) {
					response.setContentType("xls");
					EXCELRenderOption optionsxls = new EXCELRenderOption();
					optionsxls.setOutputFormat("xls");
					optionsxls.setOutputStream(out);
					task.setRenderOption(optionsxls);
				} else if (reportOption.equals("csv")) {
					response.setContentType("csv");
					CSVRenderOption csvOptions = new CSVRenderOption(options);
					csvOptions.setOutputFormat(CSVRenderOption.OUTPUT_FORMAT_CSV);
					csvOptions.setDelimiter(";");
					csvOptions.setReplaceDelimiterInsideTextWith(",");
					csvOptions.setOutputStream(out);
					task.setRenderOption(csvOptions);
				} else if (reportOption.equals("htm")) {
					response.setContentType("text/html");
					HTMLRenderOption htmlOptions = new HTMLRenderOption(options);
					htmlOptions.setOutputFormat("html");
					htmlOptions.setOutputStream(out);
					task.setRenderOption(htmlOptions);
					
				} else if (reportOption.equals("xml")) {

				} else if (reportOption.equals("odt")) {
					response.setContentType("odt");
					options.setEmitterID("org.eclipse.birt.report.engine.emitter.odt");
					options.setOutputFormat("odt");
					options.setOutputStream(out);
					task.setRenderOption(options);
				}
			task.run();
		}finally {
			out.flush();
			out.close();
			task.close();
		}
	}
	
	
/**
 *  *  This method load design file and render the file and generate multiple report in zip format 
	 * @author Maneesh Kumar  
 * @param multiReportNames
 * @param stateCode
 * @param reportOption
 * @param request
 * @param response
 * @return
 * @throws BirtException
 * @throws IOException
 */
	
	private void generateMultipleReportInZip(String multiReportNames, Integer stateCode, String reportOption, HttpServletRequest request, HttpServletResponse response) throws BirtException, IOException {
		IReportRunnable design = null;
		IRunAndRenderTask task = null;
		OutputStream outputStream = null;
		ZipOutputStream outZip = null;
		InputStream tempInStream = null;
		Scanner scanner = null;
		IRenderOption options=null;
		try {
			byte[] buf = new byte[1024];
			response.setContentType("application/zip");
			SimpleDateFormat sdf=  new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss:SSS");
			response.setHeader("Content-Disposition", "attachment; filename=downloadDir"+sdf.format(new Date())+".zip");
			Map<String, Object> birtParams = new HashMap<String, Object>();
			birtParams.put("entity_code", stateCode);
			String desingFile=null;
			outZip = new ZipOutputStream(response.getOutputStream());
			String newPath = request.getServletContext().getRealPath("/").replace("\\", "/");
			scanner = new Scanner(multiReportNames);
			scanner.useDelimiter(",");
			while (scanner.hasNext()) {
				desingFile = scanner.next();
				design = ApplicationConstant.eng.openReportDesign(newPath + "/rptDesignFiles/" + desingFile + ".rptdesign");
				task = ApplicationConstant.eng.createRunAndRenderTask(design);
				task.setParameterValues(birtParams);
				outputStream = new ByteArrayOutputStream();
				options= new RenderOption();
				if (reportOption.equals("pdf")) {
					PDFRenderOption pdfOptions = new PDFRenderOption(options);
					pdfOptions.setOutputFormat("pdf");
					pdfOptions.setOption(IPDFRenderOption.PAGE_OVERFLOW, IPDFRenderOption.OUTPUT_TO_MULTIPLE_PAGES);

					// Dafür sorgen, dass Texte nicht abgeschnitten werden, sondern umgebrochen:
					pdfOptions.setOption(IPDFRenderOption.PDF_TEXT_WRAPPING, true);
					pdfOptions.setOption(IPDFRenderOption.PDF_HYPHENATION, true);
					pdfOptions.setOutputStream(outputStream);
					task.setRenderOption(pdfOptions);
				} else if (reportOption.equals("xls")) {
					EXCELRenderOption optionsxls = new EXCELRenderOption();
					optionsxls.setOutputFormat("xls");
					optionsxls.setOutputStream(outputStream);
					task.setRenderOption(optionsxls);
				} else if (reportOption.equals("csv")) {
					CSVRenderOption csvOptions = new CSVRenderOption(options);
					csvOptions.setOutputFormat(CSVRenderOption.OUTPUT_FORMAT_CSV);
					csvOptions.setDelimiter(";");
					csvOptions.setReplaceDelimiterInsideTextWith(",");
					csvOptions.setOutputStream(outputStream);
					task.setRenderOption(csvOptions);
				} else if (reportOption.equals("htm")) {
					HTMLRenderOption htmlOptions = new HTMLRenderOption(options);
					htmlOptions.setOutputFormat("html");
					htmlOptions.setOutputStream(outputStream);
					task.setRenderOption(htmlOptions);
				} else if (reportOption.equals("xml")) {

				} else if (reportOption.equals("odt")) {
					options.setEmitterID("org.eclipse.birt.report.engine.emitter.odt");
					options.setOutputFormat("odt");
					options.setOutputStream(outputStream);
					task.setRenderOption(options);
				}

				task.run();
				outZip.putNextEntry(new ZipEntry(desingFile +sdf.format(new Date())+ "." + reportOption));
				tempInStream = new ByteArrayInputStream(((ByteArrayOutputStream) outputStream).toByteArray());
				// Transfer bytes from the temp buffer to the ZIP file
				int len;
				while ((len = tempInStream.read(buf)) > 0) {
					outZip.write(buf, 0, len);
				}
				outZip.closeEntry();
			}

		} finally {
			scanner.close();
			outputStream.close();
			outZip.close();
			tempInStream.close();
			task.close();

		}

		
	}
	
	
	/**
	 * 
	 * @param entityCodes
	 * @return
	 */
	public Integer getEntityCode(String entityCodes) {
		Integer entityCode = 0;
		if (entityCodes != null && !"".equals(entityCodes)) {
			try {
				String[] entityCodeArray = entityCodes.split(",");
				entityCode = Integer.parseInt(entityCodeArray[entityCodeArray.length - 1]);
			} catch (NumberFormatException e) {
				entityCode = 0;
			}
		}
		return entityCode;
	}
	
	
	/**
	 *  This method load design file and render the file and generate report  
	 * @author Maneesh Kumar  
	 * @param reportName
	 * @param stateCode
	 * @param reportOption
	 * @param request
	 * @param response
	 * @throws BirtException
	 * @throws IOException
	 */
	private void  generateReportOnlyModification(String reportName, Integer entityCode,Date fromDate,Date toDate, String reportOption, HttpServletRequest request, HttpServletResponse response) throws BirtException, IOException {
		
		IReportRunnable design = null;
		IRunAndRenderTask task = null;
		OutputStream  out = null;
		IRenderOption options=null;
		try {
			
			SimpleDateFormat sdf=  new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss:SSS");
			response.setHeader("Content-Disposition", "attachment; filename="+reportName+sdf.format(new Date())+"."+reportOption);
			Map<String, Object> birtParams = new HashMap<String, Object>();
			birtParams.put("entity_code", entityCode);
			java.sql.Date sqlfromDate =new  java.sql.Date(fromDate.getTime());
			java.sql.Date sqltoDate =new java.sql.Date(toDate.getTime());
			
			birtParams.put("from_date",sqlfromDate);
			birtParams.put("to_date", sqltoDate);
			String newPath = request.getServletContext().getRealPath("/").replace("\\", "/");
			out=response.getOutputStream();
			design = ApplicationConstant.eng.openReportDesign(newPath + "/rptDesignFiles/" + reportName + ".rptdesign");
			task = ApplicationConstant.eng.createRunAndRenderTask(design);
			task.setParameterValues(birtParams);
			options= new RenderOption();
				if (reportOption.equals("pdf")) {
					PDFRenderOption pdfOptions = new PDFRenderOption(options);
					pdfOptions.setOutputFormat("pdf");
					//pdfOptions.setOption(IPDFRenderOption.PAGE_OVERFLOW, IPDFRenderOption.FIT_TO_PAGE_SIZE);
					pdfOptions.setOption(IPDFRenderOption.PAGE_OVERFLOW, IPDFRenderOption.OUTPUT_TO_MULTIPLE_PAGES);

					// Dafür sorgen, dass Texte nicht abgeschnitten werden, sondern umgebrochen:
					pdfOptions.setOption(IPDFRenderOption.PDF_TEXT_WRAPPING, true);
					pdfOptions.setOption(IPDFRenderOption.PDF_HYPHENATION, true);
					
					
					pdfOptions.setOutputStream(out);
					task.setRenderOption(pdfOptions);
				} else if (reportOption.equals("xls")) {
					EXCELRenderOption optionsxls = new EXCELRenderOption();
					optionsxls.setOutputFormat("xls");
					optionsxls.setOutputStream(out);
					task.setRenderOption(optionsxls);
				} else if (reportOption.equals("csv")) {
					CSVRenderOption csvOptions = new CSVRenderOption(options);
					csvOptions.setOutputFormat(CSVRenderOption.OUTPUT_FORMAT_CSV);
					csvOptions.setDelimiter(";");
					csvOptions.setReplaceDelimiterInsideTextWith(",");
					csvOptions.setOutputStream(out);
					task.setRenderOption(csvOptions);
				} else if (reportOption.equals("htm")) {
					HTMLRenderOption htmlOptions = new HTMLRenderOption(options);
					htmlOptions.setOutputFormat("html");
					htmlOptions.setOutputStream(out);
					task.setRenderOption(htmlOptions);
				} else if (reportOption.equals("xml")) {

				} else if (reportOption.equals("odt")) {
					options.setEmitterID("org.eclipse.birt.report.engine.emitter.odt");
					options.setOutputFormat("odt");
					options.setOutputStream(out);
					task.setRenderOption(options);
				}
			task.run();
		}finally {
			if(out!=null){
			out.flush();
			out.close();
			}
			task.close();
		}
	}
	
	
	//[lgd 0012459]: Facility to download Subdistrict-villageBlock mapping
		public void generateZipFileShc(String downloadType,HttpServletRequest request, HttpServletResponse response) throws Exception{
			try {
				//flagSch=false;
				Long fileMasterId = Long.parseLong(LocalBodyConstant.ATTACHMENT_MASTER_GO_DOWNLOAD_DIRECTORY.toString());
				AttachmentMaster master = draftUtilService.getUploadFileConfigurationDetails(fileMasterId);
				
				response.setContentType("application/zip");
				SimpleDateFormat sdf=  new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss:SSS");
				response.setHeader("Content-Disposition", "attachment; filename=subdistrictVillageBlockMappingFile"+sdf.format(new Date())+".zip");
				byte[] buffercsv = new byte[1024];
				ServletOutputStream fos = response.getOutputStream();
				ZipOutputStream zos = new ZipOutputStream(fos);
				ZipEntry ze=null;
				FileInputStream in=null;
				if(downloadType.equals("csv")){
					ze= new ZipEntry("subdistrictVillageBlockMappingFile.csv");
					in = new FileInputStream(master.getFileLocation() + File.separator +"subdistrictVillageBlockMappingFile.csv");
					//flagSch=true;
					zos.putNextEntry(ze);
					int len;
					while ((len = in.read(buffercsv)) > 0) {
						zos.write(buffercsv, 0, len);
					}
					in.close();
				}
				else{
					File folder = new File(master.getFileLocation());
					File[] listOfFiles = folder.listFiles();
					for(File f: listOfFiles){
						String fileName = f.getName();
						String fileExt="";
						if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0){
							fileExt=fileName.substring(fileName.lastIndexOf(".")+1);
							if(fileExt.equalsIgnoreCase("xls")){
								ze= new ZipEntry(f.getName());
								zos.putNextEntry(ze);
								in = new FileInputStream(master.getFileLocation() + File.separator +f.getName());
								flagSch=true;
								int len;
								byte[] bufferxls = new byte[1024];
								while ((len = in.read(bufferxls)) > 0) {
									zos.write(bufferxls, 0, len);
								}
								in.close();
							}
							
					      }
						}
				}
				zos.closeEntry();
				zos.close();
				//return flagSch;
			} catch (FileNotFoundException e) {
				//flagSch=false;
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//return flagSch;
		}
		
		
		/*public void generateZipFile(final String downloadType,final HttpServletRequest request, final HttpServletResponse response) throws InterruptedException{
			final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
			System.out.println("Current Time = "+new Date());
		    Runnable r = new Runnable() {
		        @Override
		        public void run() {
		        	try {
						generateZipFileShc(downloadType, request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		    };
		    for(int i=0; i<3; i++){
		    	if(i!=0)
		    	Thread.sleep(10000);
		    	if(!flagSch){
		    		System.out.println("start Current Time = "+new Date());
					scheduler.schedule(r, 10, TimeUnit.SECONDS);
		    	}
			}
		    scheduler.shutdown();
		    System.out.println("sutdown Current Time = "+new Date());
			while(!scheduler.isTerminated()){
				//wait for all tasks to finish
			}
		}*/
		
		/*@Autowired
		private LGDReportShedularImpl lgdReportShedularImpl;
		
		@RequestMapping(value="/callSchedularFunction")
		private void callSchedularFunction() {
			lgdReportShedularImpl.processSchedular();
		}*/
}