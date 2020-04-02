package in.nic.pes.lgd.utils;


import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author Sushil Shakya
 * @Date 14-03-2012
 */
public abstract class AuthenticationConstant { // NO_UCD (unused code)
	public static final String AUTHENTICATION_RESOURCE_FILE_PATH = "META-INF/annotation";

	public static final String VALID_APPLICATION_EXT = ".html";
	public static final String VALID_CITIZEN_EXT = ".htm";
	public static final String NAD_APPLICATION_MSG = "NAD: Invalid file request";
	public static final String STR_CAT = "ucat";
	public static final String USER_CAT = "420";
	
	public static List<String> getExcludedURL() {
		List<String> list = new ArrayList<String>(7);
		list.add("login"+AuthenticationConstant.VALID_APPLICATION_EXT);
		list.add("mylogin"+AuthenticationConstant.VALID_APPLICATION_EXT);
		list.add("home"+AuthenticationConstant.VALID_APPLICATION_EXT);
		list.add("logout"+AuthenticationConstant.VALID_APPLICATION_EXT);
		list.add("logOutAction.html"+AuthenticationConstant.VALID_APPLICATION_EXT);
		list.add("forgotPassword"+AuthenticationConstant.VALID_APPLICATION_EXT);
		list.add("switchunit"+AuthenticationConstant.VALID_APPLICATION_EXT);
		list.add("getEntityTypeByCategoryCode"+AuthenticationConstant.VALID_APPLICATION_EXT);
		list.add("getAssignedUnit"+AuthenticationConstant.VALID_APPLICATION_EXT);
		list = Collections.unmodifiableList(list);
		return list;
	}
	
	public static String getAuthenticationBundle(String key, String... params) { 
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