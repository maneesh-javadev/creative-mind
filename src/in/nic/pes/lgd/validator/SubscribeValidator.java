package in.nic.pes.lgd.validator;

import in.nic.pes.common.beans.SubscribeApplication;
import in.nic.pes.lgd.common.CustomRegexValidator;
import in.nic.pes.lgd.forms.DistrictForm;
import in.nic.pes.lgd.forms.SubscribeApplicationForm;

import java.util.List;

import org.springframework.validation.Errors;

public class SubscribeValidator {

	@SuppressWarnings({ "rawtypes" })
	public boolean supports(Class<?> clazz) 
	{
		// TODO Auto-generated method stub
		return DistrictForm.class.isAssignableFrom(clazz);
	}

	public void validate(Object object, Errors errors) {
		
		CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
		SubscribeApplicationForm subscribeFrom = (SubscribeApplicationForm) object;	
		boolean appNameError=false;
		boolean appUrlError=false;
		List<SubscribeApplication> listdetail=subscribeFrom.getScbscribeDetails();
		for(SubscribeApplication element:listdetail)
		{
			if(!customValidator.checkforAlphabetsandSpace(element.getApplicationName()))
					{
				appNameError=true;       
					}
			
			  String urlPattern="^http(s{0,1})://[a-zA-Z0-9_/\\-\\.]+\\.([A-Za-z/]{2,5})[a-zA-Z0-9_/\\&\\?\\=\\-\\.\\~\\%]*";
			if(!element.getUrl().matches(urlPattern))
			{
				appUrlError=true;
			}
		}
		
		if(appNameError==true){
			errors.rejectValue("applicationName", "error.ms", "The system should respond with the message Please use [A-Z], [a-z], space in the application name.");
		}	
		if(appUrlError==true){
			errors.rejectValue("applicationName", "error.ms", "Please enter URL in this format 'http://abc.com'");
		}	
	}
		// modify Block
	
}
