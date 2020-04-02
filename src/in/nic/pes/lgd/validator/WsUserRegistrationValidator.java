package in.nic.pes.lgd.validator;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import in.nic.pes.lgd.bean.WsRegisteredIpEntity;
import in.nic.pes.lgd.bean.WsSubscribedApplicationEntity;
import in.nic.pes.lgd.bean.WsUserRegistrationForm;
import in.nic.pes.lgd.common.CustomRegexValidator;

@Component("WsUserRegistrationValidator")
public class WsUserRegistrationValidator implements Validator {
	//private static final Logger LOG = Logger.getLogger(DistrictValidator.class);
	
	@Autowired
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"; 
	private String MOBILE_PATTERN = "[0-9]{10}";
	private String IPADDRESS_PATTERN = "^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$";  
	private Pattern pattern;  
	 private Matcher matcher;  
	 private static final int MAX_FILE_SIZE = 5*1024*1024;
	 boolean isExcelFile = false;
	public boolean supports(Class<?> clazz) {
		return WsUserRegistrationForm.class.isAssignableFrom(clazz);
	}

	public void validate(Object object, Errors errors) {
		// modify Block
		WsUserRegistrationForm wForm = (WsUserRegistrationForm) object;
		CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
		
		
		if (!(wForm.getDeptOfficialName().isEmpty())) {
			if (!customValidator.checkforAlphabetsandSpace(wForm.getDeptOfficialName())) {
				errors.rejectValue("deptOfficialName", "error.ms", "Department Official Name contains invalid characters");
			}
		}
		/*else
			errors.rejectValue("deptOfficialName", "error.ms", "Department Official Name is Required");*/	
		
		if (!(wForm.getDeptOfficialDesignation().isEmpty())) {
			if (!customValidator.checkforAlphabetsandSpace(wForm.getDeptOfficialDesignation())) {
				errors.rejectValue("deptOfficialDesignation", "error.ms", "Department Official Designation contains invalid characters");
			}
		}
		/*else
			errors.rejectValue("deptOfficialDesignation", "error.ms", "Department Official Designation is Required");*/	
		
		if (!(wForm.getDeptOfficialMobile() != null && wForm.getDeptOfficialMobile().isEmpty())) {
			 pattern = Pattern.compile(MOBILE_PATTERN);  
			 matcher = pattern.matcher(wForm.getDeptOfficialMobile());  
			   if (!matcher.matches()) { 
				   errors.rejectValue("deptOfficialMobile", "error.ms", "Enter a Correct Phone Number");
			   }
		}
		
		if (!(wForm.getDeptOfficialEmail() != null && wForm.getDeptOfficialEmail().isEmpty())) {
			 pattern = Pattern.compile(EMAIL_PATTERN);  
			   matcher = pattern.matcher(wForm.getDeptOfficialEmail());  
			   if (!matcher.matches()) {  	
				   errors.rejectValue("deptOfficialEmail", "error.ms", "Enter a Correct Mail");
			   }
		}
		
		
		
		if (!(wForm.getNicOfficialName().isEmpty())) {
			if (!customValidator.checkforAlphabetsandSpace(wForm.getNicOfficialName())) {
				errors.rejectValue("nicOfficialName", "error.ms", "NIC Official Name contains invalid characters");
			}
		}
	/*	else
			errors.rejectValue("nicOfficialName", "error.ms", "NIC Official Name is Required");*/	
		
		if (!(wForm.getNicOfficialDesignation().isEmpty())) {
			if (!customValidator.checkforAlphabetsandSpace(wForm.getNicOfficialDesignation())) {
				errors.rejectValue("nicOfficialDesignation", "error.ms", "NIC Official Designation contains invalid characters");
			}
		}
		/*else
			errors.rejectValue("nicOfficialDesignation", "error.ms", "NIC Official Designation is Required");	*/
		
		if (!(wForm.getNicOfficailMobile() != null && wForm.getNicOfficailMobile().isEmpty())) {
			 pattern = Pattern.compile(MOBILE_PATTERN);  
			 matcher = pattern.matcher(wForm.getNicOfficailMobile());  
			   if (!matcher.matches()) { 
				   errors.rejectValue("nicOfficailMobile", "error.ms", "Enter a Correct Phone Number");
			   }
		}
		if (!(wForm.getNicOfficialEmail() != null && wForm.getNicOfficialEmail().isEmpty())) {
			 pattern = Pattern.compile(EMAIL_PATTERN);  
			   matcher = pattern.matcher(wForm.getNicOfficialEmail());  
			   if (!matcher.matches()) {  	
				   errors.rejectValue("nicOfficialEmail", "error.ms", "Enter a Correct Mail");
			   }
		}
		
		if (!(wForm.getWsSubscribedApplicationEntity().isEmpty())) {
			int i = 0;
			for(WsSubscribedApplicationEntity wsSubscribedApplicationEntity : wForm.getWsSubscribedApplicationEntity()) {
				if (!customValidator.checkforAlphabetsandSpace(wsSubscribedApplicationEntity.getApplicationName())) {
					errors.rejectValue("wsSubscribedApplicationEntity["+i+"].applicationName", "error.ms", "Application Name contains invalid characters");
				}
				i++;
			}
		}
		
		if (!(wForm.getWsRegisteredIpEntity().isEmpty())) {
			int i = 0;
			pattern = Pattern.compile(IPADDRESS_PATTERN);
			for(WsRegisteredIpEntity wsRegisteredIpEntity : wForm.getWsRegisteredIpEntity()) {
				if (!pattern.matcher(wsRegisteredIpEntity.getIpAddress()).matches()) {
					errors.rejectValue("wsRegisteredIpEntity["+i+"].ipAddress", "error.ms", "IP Address is invalid");
				}
				i++;
			}
		}
		
	}

	public static boolean IsExeFile(byte[] FileContent) throws UnsupportedEncodingException {
        byte[] twoBytes = SubByteArray(FileContent, 0, 2);
        String byteCheckEncoding = new String(twoBytes, "UTF-8");
        return ((byteCheckEncoding.equalsIgnoreCase("mz")) || (byteCheckEncoding.equalsIgnoreCase("zm")));
    }

    private static byte[] SubByteArray(byte[] data, int index, int length) {
        byte[] result = Arrays.copyOf(data, length);
        return result;
    }
}
