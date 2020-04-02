package in.nic.pes.lgd.utils;

import in.nic.pes.lgd.bean.SessionObject;
import in.nic.pes.lgd.validator.AbstractValidator;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * @author Sushil Shakya
 * @Date 19-11-2012
 * 
 */


public class CommonUtil {
	

	
	private static final Logger log = Logger.getLogger(CommonUtil.class);
	
	
	public static String isfileExist(String filePath) throws IOException {
		String message = null;
		filePath = filePath.replace("\\\\", "/");
		filePath = filePath.replace("\\", "/");
		File file = null;
		try {
			file = new File(filePath);
			if (file.exists()) {
				if (log.isDebugEnabled()) {
					log.debug("File exist..");
				}
				message = file.getName();
			} else {
				message = "ERROR : File has been Deleted";
			}
			if (log.isDebugEnabled()) {
				log.debug(message);
			}
		} catch (Exception e) {
			log.error(e);
		}
		return message;
	}

	public static String fileDownload(String filePath, HttpServletResponse response, String fileDisplayType) throws IOException {
		String message = "SUCCESS";
		filePath = filePath.replace("\\\\", "/");
		File file = null;
		FileInputStream fileIn = null;
		ServletOutputStream out = null;
		if (fileDisplayType == null) {
			fileDisplayType = "attachment";
		}
		try {
			file = new File(filePath);
			if (file.exists()) {
				if (log.isDebugEnabled()) {
					log.debug("File exist..");
				}
				fileIn = new FileInputStream(file);
				String name = filePath.substring(filePath.lastIndexOf('/') + 1, filePath.lastIndexOf('.'));
				String ext = filePath.substring(filePath.lastIndexOf('.'));

				if (filePath.contains(".zip")) {
					response.setContentType("application/zip");
				}
				if(filePath.contains(".pdf")){
					response.setContentLength((int)file.length());
					response.setContentType(new MimetypesFileTypeMap().getContentType(file));
					response.addHeader("Content-Disposition","attachment; filename="+file.getName());
				}
				else {
					response.setContentType("application/octet-stream");
				}
				response.setHeader("Content-Disposition", fileDisplayType + ";filename=" + name.toLowerCase() + ext);
				out = response.getOutputStream();

				byte[] outputByte = new byte[4096];
				while (fileIn.read(outputByte, 0, 4096) != -1) {
					out.write(outputByte, 0, 4096);
				}
			} else {
				message = "ERROR : File has been Deleted";
			}
		} catch (Exception e) {
			log.error(e);
		} finally {
			if (fileIn != null) {
				fileIn.close();
			}
			if (out != null) {
				out.flush();
				out.close();
			}
		}
		return message;
	}

// TODO Remove unused code found by UCDetector
// 	public static String createZipFile(String[] fileArray, String fileTitle) {
// 		byte[] buf = new byte[1024];
// 		try {
// 			String outFilename = ApplicationConstant.getDefaultFilePath() + "/" + fileTitle + ".zip";
// 			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outFilename));
// 
// 			for (int i = 0; i < fileArray.length; i++) {
// 				FileInputStream in = new FileInputStream(fileArray[i]);
// 				out.putNextEntry(new ZipEntry(fileArray[i]));
// 				int len;
// 				while ((len = in.read(buf)) > 0) {
// 					out.write(buf, 0, len);
// 				}
// 				out.closeEntry();
// 				in.close();
// 			}
// 			out.close();
// 			return outFilename;
// 		} catch (FileNotFoundException fnf) {
// 			log.error(fnf);
// 			return "File not found";
// 		} catch (IOException e) {
// 			log.error(e);
// 		}
// 		return null;
// 	}

	/**
	 * @author Sushil Shakya
	 * @param t
	 */
	public static String readFileByPath(String filePath, String fileName, String destinationDir) {
		OutputStream outStream = null;
		byte[] buf = null;
		int byteRead, byteWritten = 0;
		InputStream is = null;
		String msg = null;
		try {
			if (log.isInfoEnabled()) {
				log.info("fAddress->" + filePath + ", FileName->" + fileName);
			}
			outStream = new BufferedOutputStream(new FileOutputStream(destinationDir + fileName));
			is = new FileInputStream(new File(filePath));
			buf = new byte[1024];
			while ((byteRead = is.read(buf)) != -1) {
				outStream.write(buf, 0, byteRead);
				byteWritten += byteRead;
			}
			if (byteWritten > 0) {
				msg = "saveSuccessFully";
			}
			if (log.isInfoEnabled()) {
				log.info("Downloaded Successfully.");
				log.info("File name:\"" + fileName + "\"\nNo ofbytes :" + byteWritten);
			}
		} catch (Exception e) {
			log.error(e);
		} finally {
			try {
				is.close();
				outStream.close();
			} catch (IOException e) {
				log.error(e);
			}
		}
		return msg;
	}

// TODO Remove unused code found by UCDetector
// 	/**
// 	 * @author Sushil Shakya
// 	 * @param
// 	 */
// 	public void removeFile(String fileName, String destinationDir) {
// 		try {
// 			if (log.isInfoEnabled()) {
// 				log.info("destinationDir->" + destinationDir + ", FileName->" + fileName);
// 			}
// 			File file = new File(destinationDir + "/" + fileName);
// 			boolean isDelete = file.delete();
// 			if (log.isInfoEnabled()) {
// 				log.info("File Deleted->" + isDelete);
// 			}
// 		} catch (Exception e) {
// 			log.error(e);
// 		}
// 	}

	// Returns the contents of the file in a byte array.
// TODO Remove unused code found by UCDetector
// 	public static byte[] getBytesFromFile(File file) throws IOException {
// 		InputStream is = new FileInputStream(file);
// 
// 		long length = file.length();
// 
// 		if (length > Integer.MAX_VALUE) {
// 			throw new IOException("File size is too large--> "+ length);
// 		}
// 
// 		byte[] bytes = new byte[(int) length];
// 
// 		int offset = 0;
// 		int numRead = 0;
// 		while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
// 			offset += numRead;
// 		}
// 
// 		if (offset < bytes.length) {
// 			throw new IOException("Could not completely read file " + file.getName());
// 		}
// 
// 		is.close();
// 		return bytes;
// 	}

// TODO Remove unused code found by UCDetector
// 	public static long beforeUsages() {
// 		Runtime r = Runtime.getRuntime();
// 		long mem;
// 		if (log.isInfoEnabled()) {
// 			log.info("Total memory is: " + CommonUtil.humanReadableByteCount(r.totalMemory(), false) + " at " + Calendar.getInstance().getTime());
// 		}
// 		mem = r.freeMemory();
// 		if (log.isInfoEnabled()) {
// 			log.info("Initial free memory: " + CommonUtil.humanReadableByteCount(mem, false) + " at " + Calendar.getInstance().getTime());
// 		}
// 		return mem;
// 	}

// TODO Remove unused code found by UCDetector
// 	public static long afterUsages() {
// 		Runtime r = Runtime.getRuntime();
// 		long mem = r.freeMemory();
// 		if (log.isInfoEnabled()) {
// 			log.info("free memory after usages: " + CommonUtil.humanReadableByteCount(mem, false) + " at " + Calendar.getInstance().getTime());
// 		}
// 		return mem;
// 	}

	/*private static String humanReadableByteCount(long bytes, boolean si) {
		int unit = si ? 1000 : 1024;
		if (bytes < unit) {
			return bytes + " B";
		}
		int exp = (int) (Math.log(bytes) / Math.log(unit));
		String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
		return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}*/
	
	
	public static int setCategoryLevel(char level) throws Exception {
		int categoryLevel=0;
		if(level=='C'){
			categoryLevel=0;
		}else if(level=='S'){
			categoryLevel=1;
		}else if(level=='D'){
			categoryLevel=2;
		}else if(level=='T'){
			categoryLevel=3;
		}else if(level=='V'){
			categoryLevel=4;
		}else if(level=='B'){
			categoryLevel=5;
		}else if(level=='A'){
			categoryLevel=6;
		}
		return categoryLevel;
	}
	public static char getCategoryLevel(int level)  throws Exception {

		char categoryLevel='C';
		try{
			if(level==0){
				categoryLevel='C';
			}else if(level==1){
				categoryLevel='S';
			}else if(level==2){
				categoryLevel='D';
			}else if(level==3){
				categoryLevel='T';
			}else if(level==4){
				categoryLevel='V';
			}else if(level==5){
				categoryLevel='B';
			}else if(level >5){
				categoryLevel='A';
			}
			
		}catch (Exception e) {
			log.error(e);
		}
		return categoryLevel;

	}
	/**
	 * @author Sushil
	 * Date 24-11-2014
	 * @param s
	 * @return
	 */
	public static boolean isStringInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}
	
	
	
	/**
	 * @author Anju Gupta
	 * Date 3-03-2015
	 * @param method for File Download
	 */
	public static String getFileDownload(String filePath, String fileName,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String message = "", filename = "";
		// Getting attachment Table Data.

		System.out.println("filePath@@"+filePath);
		System.out.println("fileName@@"+fileName);
		if (!"".equalsIgnoreCase(fileName)) {

			String fileUploadLocation = filePath;
			fileUploadLocation = fileUploadLocation.replace("\\\\", "/");
			filename = fileUploadLocation.replace("\\", "/") ;

		}

		File file = null;
		FileInputStream fileIn = null;
		ServletOutputStream out = null;
		try {
			file = new File(filename);
			System.out.println("filename##"+filename);
			if (file.exists()) {
				System.out.println("filename2##"+filename);
				fileIn = new FileInputStream(file);

				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition",
						"attachment;filename=" + file.getName());
				System.out.println("getName##"+file.getName());
				out = response.getOutputStream();

				byte[] outputByte = new byte[4096];

				while (fileIn.read(outputByte, 0, 4096) != -1) {
					out.write(outputByte, 0, 4096);

				}
				message = "SUCCESS";
			} else {

				message = "ERROR : File not Found";

			}

		} catch (Exception e) {

			System.out.println(e);
		} finally {
			if (fileIn != null) {
				try {
					fileIn.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				out.flush();

				out.close();

			}

		}
		return message;

	}
	
// TODO Remove unused code found by UCDetector
// 	/**
// 	 * This method is for validate object inside the list
// 	 * @author Anchal Todariya
// 	 * @date 28-03-2015
// 	 * @param trgList
// 	 * @param object
// 	 * @throws IOException 
// 	 */
// 	public static boolean isValidObjectDemand(List<Integer> list, Object object) throws IOException {
// 		boolean isValid = true;
// 		if (list != null && list.size() > 0) {
// 			Integer pk = (Integer) object;
// 			if (!list.contains(pk)) {
// 				isValid = false;
// 			}
// 		}
// 		return isValid;
// 	}
	
// TODO Remove unused code found by UCDetector
// 	/**
// 	 * This method is for validate object inside the list
// 	 * @author Anchal Todariya
// 	 * @date 28-03-2015
// 	 * @param trgList
// 	 * @param object
// 	 * @throws IOException 
// 	 */
// 	public static boolean isValidObjectDemandForList(List<Integer> list, List<Integer> list2) throws IOException {
// 		boolean isValid = true;
// 		if (list != null && list.size() > 0) {
// 			if (list.containsAll(list2)) {
// 				isValid = false;
// 			}
// 		}
// 		return isValid;
// 	}
	
	/**
	 * This method is for validate object inside the list
	 * @author Anchal Todariya
	 * @date 28-03-2015
	 * @param trgList
	 * @param object
	 * @throws IOException 
	 */
	private static boolean isValidObjectDemand(Object object, Object object1) throws IOException {
		boolean isValid = true;
		if (object != null) {
			String pk = (String) object;
			String pk1 = (String) object1;
			if (! pk1.equals(pk)) {
				isValid = false;
			}
		}
		return isValid;
	}
	
	
	
	
	
	
	public static void CheckForParent(HttpSession session,HttpServletResponse response, AbstractValidator abstractValidator) throws IOException{
		SessionObject sessionObject = (SessionObject) session.getAttribute("sessionObject");
		Integer stateCode = sessionObject.getStateId();
		Object sessionFormbean = session.getAttribute("formbean");
		
		Integer stateCodeSel=abstractValidator.getCheckForParent(sessionFormbean);
	
		boolean isValidObjectDemand=isValidObjectDemand(String.valueOf(stateCode), String.valueOf(stateCodeSel));
		if(!isValidObjectDemand){
			response.sendError(403,"Unauthorized Access");
			return;
		}
	}
	
	public static void CheckForParentForListItems(HttpSession session,HttpServletResponse response, AbstractValidator abstractValidator,String landRegionList,String landRegionType) throws IOException{
		SessionObject sessionObject = (SessionObject) session.getAttribute("sessionObject");
		Integer stateCode = sessionObject.getStateId();
		Object sessionFormbean = session.getAttribute("formbean");
		Integer stateCodeSel=abstractValidator.getCheckForParentListItems(sessionFormbean,landRegionList,landRegionType);
		boolean isValidObjectDemand=isValidObjectDemand(String.valueOf(stateCode), String.valueOf(stateCodeSel));
		if(!isValidObjectDemand){
			response.sendError(403,"Unauthorized Access");
			return;
		}
	}
	
}