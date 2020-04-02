package in.nic.pes.lgd.validator;

import in.nic.pes.lgd.bean.ConfigurationMapConstituency;
import in.nic.pes.lgd.forms.ConfigureMapForm;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("ConfigMapConstituencyValidator")
public class ConfigMapConstituencyValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ConfigurationMapConstituency.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		try 
		{
			ConfigureMapForm cmf = (ConfigureMapForm) target;
			//System.out.println("Inside ConfigMapConstituencyValidator  class");
			if(cmf.isIsmapuploadParliament()==false && cmf.getBaseUrlParliament().length()==0)
			{
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "baseUrlParliament", "PC.REQUIRED");
			}else if(cmf.isIsmapuploadAssembly()==false && cmf.getBaseUrlAssembly().length()==0)
			{
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "baseUrlAssembly", "AC.REQUIRED");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void validateModify(Object target, Errors errors) {
		try 
		{
			ConfigureMapForm cmf = (ConfigureMapForm) target;
			for (int i = 0; i < cmf.getListConfigurationMapConstituency().size(); i++) 
			{
			if(cmf.getListConfigurationMapConstituency().get(i).isIsmapupload()==false && cmf.getListConfigurationMapConstituency().get(i).getBaseUrl().length()==0 && cmf.getListConfigurationMapConstituency().get(i).getConstituencyType()=='A')
			{
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "baseUrl", "AC.REQUIRED");
			}
			else if(cmf.getListConfigurationMapConstituency().get(i).isIsmapupload()==false && cmf.getListConfigurationMapConstituency().get(i).getConstituencyType()=='A')
			{
				if(cmf.getListConfigurationMapConstituency().get(i).getBaseUrl()!=null)
				{	
				String urlPattern="^http(s{0,1})://[a-zA-Z0-9_/\\-\\.]+\\.([A-Za-z/]{2,5})[a-zA-Z0-9_/\\&\\?\\=\\-\\.\\~\\%]*";
						String url=cmf.getListConfigurationMapConstituency().get(i).getBaseUrl();  
						if(!url.matches(urlPattern)) 
						{
							
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "baseUrl","ms.error", "Assembly Constituency Base URL is not proper format");
						}
				}
			}
			
			if(cmf.getListConfigurationMapConstituency().get(i).isIsmapupload()==false && cmf.getListConfigurationMapConstituency().get(i).getBaseUrl().length()==0 && cmf.getListConfigurationMapConstituency().get(i).getConstituencyType()=='P')
			{
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "baseUrl", "PC.REQUIRED");
			}
			else if(cmf.getListConfigurationMapConstituency().get(i).isIsmapupload()==false && cmf.getListConfigurationMapConstituency().get(i).getConstituencyType()=='P')
			{
				if(cmf.getListConfigurationMapConstituency().get(i).getBaseUrl()!=null)
				{
						String urlPattern="^http(s{0,1})://[a-zA-Z0-9_/\\-\\.]+\\.([A-Za-z/]{2,5})[a-zA-Z0-9_/\\&\\?\\=\\-\\.\\~\\%]*";
						String url=cmf.getListConfigurationMapConstituency().get(i).getBaseUrl();  
						if(!url.matches(urlPattern)) 
						{
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "baseUrl","ms.error", "*Parliament Constituency Base URL is not proper format");
							
						}
				}
			}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
