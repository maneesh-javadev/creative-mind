package in.nic.pes.lgd.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;

import org.eclipse.birt.report.engine.api.IReportEngine;



/**
 * @author Sushil Shakya
 * @Date 19-11-2012
 */
public abstract class ApplicationConstant {
	private static final String JASPER_FILE_PATH = "lgd";	
	
	public static final String FILE_INLINE = "inline"; // NO_UCD (unused code)
	public static final String CRON_TIME = "0 0 0 * * ?";
	public static final String ENTITY_TRANS_SCHEMA = "lgd";
	public static final String PES_JNDI_NAME = "jdbc/switchunit";
	
	public static String getDefaultFilePath() {
		String customDirName = ResourceBundle.getBundle(ApplicationConstant.JASPER_FILE_PATH, Locale.ENGLISH).getString("tempFilePath");
		File file = new JFileChooser().getFileSystemView().getDefaultDirectory();
		if(customDirName != null && !"".equals(customDirName)) {
			customDirName = file.getAbsolutePath()+customDirName;
		} else {
			customDirName = file.getAbsolutePath()+"lgd_docs";
		}
	    return customDirName;
	}
	
	/**
	 * The stateLBDisttWise holding state codes those Local Bodies must be formed 
	 * on District Level.
	 * @author Vinay Yadav 16-12-2013
	 */ 
	public static List<Integer> stateLBDisttWise = new ArrayList<Integer>();
	
	/**
	 * The {@code checkStateLBOnlyDisttWise} is checking for current logged in state is belongs
	 * to {@code stateLBDisttWise}.
	 * @param stateCode 
	 * @return boolean 
	 * @author Vinay Yadav 23-12-2013
	 */
	public static boolean checkStateLBOnlyDisttWise(Integer stateCode){
		boolean checked = false;
		try{
			if(stateCode == null){
				return checked;
			}	
			if(stateLBDisttWise.contains(stateCode)){
				checked = true;
			}
		}catch (Exception e) {
			checked = false;
		}
		return checked;
	}
	
	/* Different levels defined for administrative department */
	
	public static final String DISTRICT_LEVEL_CODE = "D";
	public static final String SUBDISTRICT_LEVEL_CODE = "T";
	public static final String BLOCK_LEVEL_CODE = "B";
	public static final String VILLAGE_LEVEL_CODE = "V";
	public static final String STATE_LEVEL_CODE = "S";
	public static final String ADMINISTRATIVE_LEVEL_CODE = "A";
	
	
	/* Different levels defined for local body freeze */
	public static final String LAND_REGION_LEVEL_CODE = "L";
	public static final String LOCAL_BODY_PRI_LEVEL_CODE = "P";
	public static final String LOCAL_BODY_TRD_LEVEL_CODE = "T";
	public static final String LOCAL_BODY_URB_LEVEL_CODE = "U";
	
	
	/**
	 * registerUserKeyList is list of register user key 
	 */
	public static List<String> registerUserKeyList = new ArrayList<String>();
	
	/**
	 * birt engine variable define for download directory
	 */
	public static IReportEngine eng = null;
	
	
	public static final String LGD_DEFAULT_SECRET_KEY="LGD@52#12Key";
	
}
