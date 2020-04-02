/*
 *	DocumentConverter.java, a utility class for conversion of HTML template into PDF.
 *
 *  @created	February 27, 2013
 */

package in.nic.pes.lgd.utils;

import in.nic.pes.lgd.bean.AttachmentMaster;
import in.nic.pes.lgd.forms.GenerateDetails;
import in.nic.pes.lgd.service.GovernmentOrderService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.editor.fileupload.nartex.LoadResources;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

/**
 * Inject (@Autowired) this file as service and use synchronised {@code ganaratePDFDocument} method 
 * to start execution of functionality.
 * 
 * The {@code DocumentConverter} class to make HTML
 * template at runtime and parse template into PDF format.
 * 
 * <p>{@code createFile} method, Initialise with making upload folder retrieved from database
 * and create new folder and write all PDF files with a specific name into it.
 * 
 * <p> The{@code ganaratePDFDocument} method, generate The{@code Document} class object to write 
 * PDF file into it.
 * 
 * <p> The{@code convertLetterDocument} method, generate The{@code HTMLWorker} class object to open
 * document and parse into PDF.
 * 
 * @author Vinay Yadav
 *
 */
@Service
public class DocumentConverter {
	
	@Autowired
	private GovernmentOrderService govtOrderService;
	
	/**
	 * PDF file type to load instance of document.
	 */
	private final static String PDF_EXTENSION = "pdf";
	
	/**
	 * Uploaded Images from editor stored path.
	 */
	private static String uploadedImagePath;

	/**
	 * Create folder path to save PDF file.
	 * @return location of the file.
	 * @throws Exception
	 */
	private String createFile() throws Exception{
		
		Timestamp time = CurrentDateTime.getCurrentTimestamp();
		String fileName = time.toString().replace(" ", "").replace(":", "").replace(".", "") + "." + PDF_EXTENSION;

		AttachmentMaster attachment = govtOrderService.getGenerateFileLocation(1);
		String strPath = attachment.getFileLocation();

		File folder = new File(strPath);
		if (!folder.isDirectory()) {
			folder.mkdir();
		}
		return strPath + File.separator + fileName;
	} 

	/**
	 * Initialise Document object with PDF writer instance.
	 * @param request
	 * @param filecontent
	 * @return
	 * @throws Exception
	 */
	public synchronized GenerateDetails ganaratePDFDocument(GenerateDetails generatedetails, HttpServletRequest request, String filecontent) throws Exception {
		 
		String filepath = createFile();
		String extension = filepath.substring(filepath.lastIndexOf(".") + 1);
		File fileTemplate = new File(filepath);
		if (fileTemplate.exists()) 
			fileTemplate.delete();
		else 
			fileTemplate.createNewFile();
		
		PdfWriter writer = null;
		Document document = new Document(PageSize.A4);
		if (PDF_EXTENSION.equalsIgnoreCase(extension)) {
			// Instantiate .pdf extension document format
			writer = PdfWriter.getInstance(document, new FileOutputStream(filepath));
		} else
			throw new IOException("File extension is invalid");

		document.addAuthor("NIC");
		document.addCreator("Html to Pdf Converter");
		document.addCreationDate();
		document.addTitle("Editor Letter Template");
		
		uploadedImagePath =  new LoadResources().getUploadedLocation();
		String realPath = request.getServletContext().getRealPath("/").replaceAll("\\\\", "/");
		String contextPath = request.getContextPath();
		String message = convertLetterDocument(writer, document, filecontent, realPath, contextPath);
		
		if("success".equals(message)){
			String filename = fileTemplate.getName();
			generatedetails.setFileLocation(filepath);
			generatedetails.setFileName(filename);
			generatedetails.setFileTimestamp(filename);
			generatedetails.setFileContentType("application/pdf ");
			generatedetails.setFileSize(new Long(400));
		}else
			throw new DocumentException(message);
		
		return generatedetails;
	}
	
	/**
	 * Parse HTML to PDF based on Element and Chunks and add runtime properties into it.
	 * @param pdfWriter
	 * @param document
	 * @param filecontent
	 * @param realpath
	 * @param context
	 * @return
	 */
	private String convertLetterDocument(PdfWriter pdfWriter, 
										 Document document, 
										 String filecontent, 
										 String realpath, 
										 String context){
		String flag = "success";
		try{
			//HTMLWorker htmlWorker = new HTMLWorker(document);
			String htmlFormattedDoc = convertImageURL(headerHTMLTemplate() + filecontent + footerHTMLTemplate(), 
					  								  realpath, 
					  								  context);
			document.open();
			Reader rr = new StringReader(htmlFormattedDoc);
			
			XMLWorkerHelper.getInstance().parseXHtml(pdfWriter, document, rr);
			
			/*ArrayList<String> imageStyle = getImageStyles(htmlFormattedDoc);
			Integer stylesLength = imageStyle.size();
			 
			@SuppressWarnings({ "static-access" })
			ArrayList<Element> list = htmlWorker.parseToList(
					          		  new StringReader(htmlFormattedDoc), 
					          		  null); 
			int count = 0;
			for (Element el : list){
				boolean isrootelement = true;
				ArrayList<Chunk> al = el.getChunks();
				
				for(Chunk inn : al){
					HashMap mp = inn.getAttributes();
					Iterator iter = mp.entrySet().iterator();
					 
					while (iter.hasNext()) {
						Map.Entry mEntry = (Map.Entry) iter.next();
						if("IMAGE".equalsIgnoreCase((String) mEntry.getKey())){
							Object[] obj 	  = (Object[]) mEntry.getValue();
							Object 	 imageObj = obj[0];
							URL   	 imageUrl = null;
							Float 	 width 	  = null;
						    Float 	 height   = null;
							if(imageObj instanceof ImgRaw){
								ImgRaw raw 	= (ImgRaw) imageObj;
								imageUrl 	= raw.getUrl();
								width 		= raw.getScaledWidth();
								height 		= raw.getScaledHeight();
							
							}else if(imageObj instanceof Jpeg){
								Jpeg jpeg 	= (Jpeg) imageObj;
								imageUrl 	= jpeg.getUrl();
								width 		= jpeg.getScaledWidth();
								height 		= jpeg.getScaledHeight();
							}
								
							// Image instance to write in PDF
							Image image = Image.getInstance(imageUrl);
							if(count < stylesLength){
								String style = imageStyle.get(count);
								String[] styleArr = style.split(";");
								for(String fields : styleArr){
									String[] styleProperties = fields.split(":");
									String proerty 			 = styleProperties[0].trim();
									String proertyValue 	 = styleProperties[1];
									proertyValue 			 = proertyValue.toLowerCase().replaceAll("px", "").trim();
									
									if("width".equalsIgnoreCase(proerty)){
										width = Float.parseFloat(proertyValue); // width set style tag 
									}else if("height".equalsIgnoreCase(proerty)) {
										height = Float.parseFloat(proertyValue);// height set style tag
									}			
								}
								count++;
							}
							
							if(el instanceof Paragraph){
								Paragraph para = (Paragraph) el;
								int alien = para.getAlignment();
								if(alien == -1)
									alien = 0;
								image.setAlignment(alien);
							}
						    image.scaleToFit(width, height);
						    document.add(image);
						    isrootelement = false;
						}
					}
				}
				if(isrootelement)
					document.add(el);
			}*/	
			
			document.close();
			//htmlWorker.close();
		}catch (Exception e) {
			// TODO: handle exception
			flag = e.getMessage();
		}
		return flag;
	}
	
	/**
	 * Add HTML Header with body to create format text into HTML.
	 * @return String HTML building tag
	 */
	private String headerHTMLTemplate() {
		// TODO Auto-generated method stub
		String header = "<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>"+
					    "<html>" +
					    "<head>" +
					    //"<style> .break{ page-break-after: always; }</style>" +
					    "</head><body>";
		return header;
	}
	
	/**
	 * Add HTML Footer to complete HTML document. 
	 * @return String HTML body completion.
	 */
	private static String footerHTMLTemplate() {
		// TODO Auto-generated method stub
		String footer = "</body></html>";
		return footer;
	}
	
	/**
	 * Replace Image url with real path
	 * @param content
	 * @param realpath
	 * @return
	 */
	private String convertImageURL (String content, String realpath, String context){
		Pattern p = null;
        Matcher m = null;
        p = Pattern.compile("<img[^>]*src\\s*=\\s*\"([^\"]*)");
        m = p.matcher(content);
        while (m.find()) {
        	String imgsrc = m.group(1);
        	String completeImgURL = checkFile(realpath , imgsrc, context);
        	content = content.replace(imgsrc, completeImgURL);
        }
		return content;
	}
	
	/**
	 * List of Styles added in Image Tag.
	 * @param content
	 * @return
	 */
	@SuppressWarnings("unused")
	private ArrayList<String> getImageStyles (String content){
		ArrayList<String> props = new ArrayList<String>();
		Pattern p = null;
        Matcher m = null;
        p = Pattern.compile("<img[^>]*style\\s*=\\s*\"([^\"]*)");
        m = p.matcher(content);
        while (m.find()) {
        	String imgstyle = m.group(1);
        	props.add(imgstyle);
        }
		return props;
	}
		
	/**
	 * Check for file, if not exist show image not available
	 * to user.
	 * @param realpath
	 * @param url
	 * @return
	 */
	private String checkFile(String realpath, String url, String context){
		String fileName = url.replaceAll("\\\\", "/");
		fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
		
		String finalurl = uploadedImagePath +fileName;
		File f = new File(finalurl);
		if(!f.exists()){
			finalurl = realpath + "/images/image_not_available.jpg";
		}
		return finalurl;
	}
}
