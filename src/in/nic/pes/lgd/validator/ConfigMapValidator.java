package in.nic.pes.lgd.validator;

import in.nic.pes.lgd.bean.ConfigureMap;
import in.nic.pes.lgd.forms.ConfigureMapForm;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("ConfigMapValidator")
public class ConfigMapValidator implements Validator {
	
	private static final Logger log = Logger.getLogger(ConfigMapValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {
		return ConfigureMap.class.isAssignableFrom(clazz);
	}

	
	public void validate(Object target, Errors errors) {
		try 
		{

			//System.out.println("Inside ConfigMapValidator  class");
			ConfigureMapForm cmf = (ConfigureMapForm) target;
			if(cmf.isIsmapuploadState()==false && cmf.getBaseUrlState().length()==0)
			{
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "baseUrlState", "STATE.REQUIRED");			
			}else if(cmf.isIsmapuploadDistrict()==false && cmf.getBaseUrlDistrict().length()==0)
			{
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "baseUrlDistrict", "DISTRICT.REQUIRED");
			}else if(cmf.isIsmapuploadSubDist()==false && cmf.getBaseUrlSubDist().length()==0)
			{
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "baseUrlSubDist", "SUBDISTRICT.REQUIRED");
			}else if(cmf.isIsmapuploadVillage()==false && cmf.getBaseUrlVillage().length()==0)
			{
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "baseUrlVillage", "VILLAGE.REQUIRED");
			}
		
			//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "map_code", "map.code");
			
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("Exception" + e);
		}
	}
	public void validateModifyLand(Object target, Errors errors)
	{
		try 
		{

			//System.out.println("Inside ConfigMapValidator  class");
			ConfigureMapForm cmf = (ConfigureMapForm) target;
			for (int i = 0; i < cmf.getViewConfigMapLandRegion().size(); i++) 
			{
			if(cmf.getViewConfigMapLandRegion().get(i).getIsmapupload()==false && cmf.getViewConfigMapLandRegion().get(i).getIsbaseUrl().length()==0 && cmf.getViewConfigMapLandRegion().get(i).getLandregiontype()=='S' )
			{
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "isbaseUrl", "BASEURL.REQUIRED");			
			}
			if(cmf.getViewConfigMapLandRegion().get(i).getIsmapupload()==false && cmf.getViewConfigMapLandRegion().get(i).getIsbaseUrl().length()==0 && cmf.getViewConfigMapLandRegion().get(i).getLandregiontype()=='D' )
			{
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "isbaseUrl", "BASEURL.REQUIRED");			
			}
			if(cmf.getViewConfigMapLandRegion().get(i).getIsmapupload()==false && cmf.getViewConfigMapLandRegion().get(i).getIsbaseUrl().length()==0 && cmf.getViewConfigMapLandRegion().get(i).getLandregiontype()=='T' )
			{
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "isbaseUrl", "BASEURL.REQUIRED");			
			}
			if(cmf.getViewConfigMapLandRegion().get(i).getIsmapupload()==false && cmf.getViewConfigMapLandRegion().get(i).getIsbaseUrl().length()==0 && cmf.getViewConfigMapLandRegion().get(i).getLandregiontype()=='V' )
			{
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "isbaseUrl", "BASEURL.REQUIRED");			
			}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("Exception" + e);
		}
	}


	
	
	

	
}
