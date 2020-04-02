package in.nic.pes.lgd.validator;

import in.nic.pes.lgd.bean.ConfigurationMapLocalbody;
import in.nic.pes.lgd.forms.ConfigureMapForm;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("ConfigurationMapLGDMValidator")
public class ConfigurationMapLGDMValidator implements Validator {
	
	private static final Logger log = Logger.getLogger(ConfigurationMapLGDMValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {
		return ConfigureMapForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		try {

			ConfigureMapForm cmf = (ConfigureMapForm) target;
			for (int i = 0; i < cmf.getLstdetail().size(); i++) {
				if (cmf.getLstdetail().get(i).isIsmapupload() == false
						&& cmf.getLstdetail().get(i).getBase_url().length() == 0){
					errors.rejectValue("base_url", "BASEURL.REQUIRED",
							"Field is Required");
				}	
			}

		}

		catch (Exception e) {
			// TODO: handle exception
			log.debug("Exception" + e);
		}
	}

	public void validateSave(Object target, Errors errors,
			HttpServletRequest req) {
		try {
			// HttpServletRequest request;
			//boolean uploadMap = false;
			//String urlMap = "";
			ConfigureMapForm configMapLGDMForm = (ConfigureMapForm) target;
			ConfigurationMapLocalbody configurationMapLocalbody = null;
			String configMapLGDM[] = null;
			 
			if (configMapLGDMForm.getBaseUrl().contains(",")) {
				configMapLGDM = configMapLGDMForm.getBaseUrl().split(",");

			}
			for (int i = 1; i <= configMapLGDMForm.getTierSetupSize(); i++) {
				configurationMapLocalbody = new ConfigurationMapLocalbody();
				String uploadConfig = (String) req.getParameter("upload" + i);
                  
				if (uploadConfig.equals("yes")) {
					configurationMapLocalbody.setIsmapupload(true);
					//uploadMap = true;
					//urlMap = "";
				} else {
					configurationMapLocalbody.setIsmapupload(false);
				 
						if (configMapLGDM.length >= i){
							configurationMapLocalbody
									.setBaseUrl(configMapLGDM[i - 1]);
						}	
					if (configurationMapLocalbody.getBaseUrl() == null || configurationMapLocalbody.getBaseUrl().isEmpty()) {
						errors.rejectValue("baseUrl", "BASEURL.REQUIRED",
								"Field is Required");
					}
					
					else
					{
						String urlPattern="^http(s{0,1})://[a-zA-Z0-9_/\\-\\.]+\\.([A-Za-z/]{2,5})[a-zA-Z0-9_/\\&\\?\\=\\-\\.\\~\\%]*";
						String url=configurationMapLocalbody.getBaseUrl();  
						if(!url.matches(urlPattern)) 
						{
							errors.rejectValue("baseUrl", "BASEURL.REQUIRED",
									"* Base URL is not proper format");
						}
					}

					//uploadMap = false;
					/*if (configMapLGDM.length >= i){
						urlMap = configMapLGDM[i - 1];
					}*/	
				}
				// sb.append(urlMap+"~"+uploadMap+";");
			}

		}

		catch (Exception e) {
			// TODO: handle exception
			log.debug("Exception" + e);
		}
	}

}
