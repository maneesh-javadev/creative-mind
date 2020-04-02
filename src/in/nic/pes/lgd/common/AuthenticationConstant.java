package in.nic.pes.lgd.common;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public abstract class AuthenticationConstant { // NO_UCD (use default)
	private static final String AUTHENTICATION_RESOURCE_FILE_PATH = "META-INF/annotation";

	static final String VALID_APPLICATION_EXT = ".htm";
	private static final String VALID_APPLICATION_LOGIN_EXT =".do";
	static final String VALID_CITIZEN_EXT = ".htm";
	static final String LGD_APPLICATION_MSG = "Local Government Directory : Invalid file request";
	
	
	static final String STR_CAT = "ucat";
	static final String USER_CAT = "420";
	
	static List<String> getExcludedURL() {
		List<String> list = new ArrayList<String>(7);
		list.add("login"+AuthenticationConstant.VALID_APPLICATION_LOGIN_EXT);
		list.add("home"+AuthenticationConstant.VALID_APPLICATION_EXT);
		list.add("logout"+AuthenticationConstant.VALID_APPLICATION_EXT);
		list.add("forgotPassword"+AuthenticationConstant.VALID_APPLICATION_EXT);
		list.add("switchunit"+AuthenticationConstant.VALID_APPLICATION_EXT);
		list.add("getEntityTypeByCategoryCode"+AuthenticationConstant.VALID_APPLICATION_EXT);
		list.add("getEntityType"+AuthenticationConstant.VALID_APPLICATION_EXT);
		list.add("getAssignedUnit"+AuthenticationConstant.VALID_APPLICATION_EXT);
		
		list.add("fileManagerUpload"+AuthenticationConstant.VALID_APPLICATION_EXT);
		list.add("loadLanguage"+AuthenticationConstant.VALID_APPLICATION_EXT);
		list.add("imgBrowser"+AuthenticationConstant.VALID_APPLICATION_EXT);//Image Upload URL Ckeditor
		list.add("listOfPesaPanchyat.do");
		list.add("PrintPesaReport.do");
		list.add("copyRightPolicy.do");
		list.add("privacyPolicy.do");
		list.add("termsconditions.do");
		list.add("habitation.do");/*added by Ashish Dhupia on 20/1/2015 for Habitation use case*/
		list.add("habitationpost.do");/*added by Ashish Dhupia on 20/1/2015 for Habitation use case*/
		list.add("departOrganizationUnit.do");/*added by Anju on 16/1/2015 for Organization REport use case*/
		list.add("birtReport.do");/*added by Ashish Dhupia on 20/1/2015 for Habitation use case*/
		list.add("birtReportPost.do");/*added by Ashish Dhupia on 20/1/2015 for Habitation use case*/
		list.add("stateFreezeReport.do");/*added by Anchal Todariya on 13/02/2015 for State Freeze birt report*/
		list.add("stateWisePopulation.do");
		list = Collections.unmodifiableList(list);
		return list;
	}
	
	static String getAuthenticationBundle(String key, String... params) { 
		  String message = null; 
		  try { 
		    if (params == null) { 
		      message = ResourceBundle.getBundle(AUTHENTICATION_RESOURCE_FILE_PATH, Locale.ENGLISH).getString(key); 
		    } else { 
		      message = MessageFormat.format(ResourceBundle.getBundle(AUTHENTICATION_RESOURCE_FILE_PATH, Locale.ENGLISH).getString(key), (Object[]) params); 
		    } 
		  } catch (MissingResourceException e) { 
			  	//System.out.println("Key not found: "+e.getMessage());
			  	return null;
		  } 
		  return message; 
	}
}