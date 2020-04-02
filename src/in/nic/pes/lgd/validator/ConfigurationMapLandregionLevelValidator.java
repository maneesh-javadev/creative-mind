package in.nic.pes.lgd.validator;
import in.nic.pes.lgd.bean.ConfigurationMapLandregionLevel;
import in.nic.pes.lgd.bean.ViewConfigMapLandRegion;
import in.nic.pes.lgd.forms.ConfigureMapForm;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component("ConfigurationMapLandregionLevelValidator")
public class ConfigurationMapLandregionLevelValidator implements Validator{

	private static final Logger log = Logger.getLogger(ConfigurationMapLandregionLevelValidator.class);
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ConfigurationMapLandregionLevel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		try 
		{
			//System.out.println("Inside ConfigurationMapLandregionLevelValidator  class");
			ConfigureMapForm cmf = (ConfigureMapForm) target;
			if(cmf.isIsmapuploadBlock()==false && cmf.getBaseUrlBlock().length()==0){
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "baseUrlBlock", "BLOCK.REQUIRED");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void validateModifyBlock(Object target, Errors errors)
	{
		
			try 
			{	ConfigureMapForm cmf = (ConfigureMapForm) target;
			List<ViewConfigMapLandRegion> viewConfigMapLandRegion = null;
			viewConfigMapLandRegion = new ArrayList<ViewConfigMapLandRegion>();
			viewConfigMapLandRegion=cmf.getViewConfigMapLandRegion();
			if(viewConfigMapLandRegion.size()>0)
			{
				for (ViewConfigMapLandRegion viewconfigMapLandRegion : viewConfigMapLandRegion) {
					if(viewconfigMapLandRegion.getLandregiontype()=='B')
					{
						if(viewconfigMapLandRegion.getIsbaseUrl().length()==0 && viewconfigMapLandRegion.getIsmapupload()==false){
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "isbaseUrl", "BASEURL.REQUIRED");		
						}	
					}

				}
			}
			else
			{
				ValidationUtils.rejectIfEmptyOrWhitespace(errors,"isbaseUrl","Problem to read value");
			}
				//cmf.getBase_url();
				/*if(cmf.getViewConfigMapLandRegion().get(4).getIsmapupload()==false && cmf.getViewConfigMapLandRegion().get(4).getIsbaseUrl().length()==0 && cmf.getViewConfigMapLandRegion().get(4).getLandregiontype()=='B')
				{
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "isbaseUrl", "BASEURL.REQUIRED");			
				}*/
				
			}
				 catch (Exception e) {
				// TODO: handle exception
					 log.debug("Exception" + e);
			}
				
	}
// TODO Remove unused code found by UCDetector
// 	public void validateLGDM(Object target, Errors errors) {
// 		try 
// 		{
// 			
// 				GetMapConfigLocalBody cmf = (GetMapConfigLocalBody) target;
// 				if(cmf.isIsmapupload()==false && cmf.getBase_url().length()==0){
// 					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "baseUrlBlock", "BLOCK.REQUIRED");
// 				}
// 			
// 		}
// 
// 			 catch (Exception e) {
// 			// TODO: handle exception
// 				 log.debug("Exception" + e);
// 		}
// 	}
	
	
}
