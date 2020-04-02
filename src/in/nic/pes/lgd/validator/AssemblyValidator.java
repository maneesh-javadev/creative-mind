package in.nic.pes.lgd.validator;

import in.nic.pes.lgd.common.CustomRegexValidator;
import in.nic.pes.lgd.forms.AssemblyDataForm;
import in.nic.pes.lgd.forms.AssemblyForm;
import in.nic.pes.lgd.service.ParliamentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("AssembcValidator")
public class AssemblyValidator  implements Validator {

	private @Autowired
	ParliamentService parliamentService;
	
	@Override
	public boolean supports(Class<?> clas) {
		 
		return AssemblyForm.class.isAssignableFrom(clas);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CustomRegexValidator customValidator = CustomRegexValidator
				.getInstance();
		
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newAssemblyNameEnglish",
				"required.userName", "Name of New Assembly Constituency name is required.");
		
		
	 
		AssemblyForm lbForm = (AssemblyForm)target;
		Integer pcCode=null;
		if(lbForm.getContributedParliament()!=null && lbForm.getContributedParliament()!=""){
			pcCode=Integer.parseInt(lbForm.getContributedParliament());
		}
		 	if(lbForm.getContributedParliament().isEmpty()){
				errors.rejectValue("contributedParliament", "notmatch.password","Field is required.");
			}
		 	
		 	 if (lbForm.getContributedAssembly() == null) {

				errors.rejectValue("contributedAssembly",
						"error.blank.AssemblyConstituency");
			}
		 	
		 	String parliamentNameEnglish = lbForm.getNewAssemblyNameEnglish();                
			if (!customValidator.checkforAlphabetsandSpace(parliamentNameEnglish)) {
				errors.rejectValue("newAssemblyNameEnglish",
						"error.valid.COMMONALPHANUMERICREQUIRED");
			}
		
	
   String assemblyName = lbForm.getNewAssemblyNameLocal();                  
	
	if (assemblyName != null && !assemblyName.isEmpty()) {
		if (!customValidator.validateSpecialCharacters(assemblyName)) {
			errors.rejectValue("newAssemblyNameLocal",
					"error.valid.COMMONALPHANUMERICREQUIRED");
		}
	}
	
	Integer eciCode=null;
	if(lbForm.getAssemblyECICODE()!=null && !(lbForm.getAssemblyECICODE().equals(""))){
		eciCode= Integer.parseInt(lbForm.getAssemblyECICODE());
	}	
	if (eciCode != null)
	{
			/*if (!customValidator.checkforOnlyNumeric(eciCode)) {   
				errors.rejectValue("ParliamentECICODE",
						"error.valid.COMMONNUMERICREQUIRED");
			}*/
			
			try
			{
				
				boolean flag=parliamentService.existECICODE(pcCode, eciCode,'A');
				if(flag)
				{
					errors.rejectValue("ParliamentECICODE",
							"ECI Code already Exist");
				}
			}catch(Exception e)
			{
				errors.rejectValue("ParliamentECICODE",
						"ECI Code only numbric value");
			}
		
	}
	
	
 String subDistrict=null;
 
	 subDistrict=lbForm.getContributedAssembly();
	 
	 if(subDistrict!=null && subDistrict!="")
	 {
		 if (!((subDistrict.indexOf("PART") != -1 && subDistrict.indexOf("FULL") == -1)
					|| (subDistrict.indexOf("PART") != -1 && subDistrict.indexOf("FULL") != -1) || (subDistrict.indexOf("FULL") != subDistrict
					.lastIndexOf("FULL")
					&& subDistrict.indexOf("FULL") != -1 && subDistrict.indexOf("PART") == -1))) {
			 errors.rejectValue("ParliamentECICODE","You are not select one full Assembly in Contributing List of Assembly Constituency ");
		 }
		 
	 }
	
		 	
		  
		}
	
	public void modifyvalidate(Object target, Errors errors) throws Exception {
		AssemblyForm lbForm = (AssemblyForm)target;
		
		List<AssemblyDataForm> assemblyDataForm=lbForm.getListAssemblyFormDetail();
		if(!assemblyDataForm.isEmpty())
		{
			AssemblyDataForm element=assemblyDataForm.get(0);
			Integer eciCode=element.getEciCode();
			Integer acCode=element.getAcCode();
			Integer pcCode=element.getPcCode();
			Integer oldEciCode=null;
			try
			{
				oldEciCode=parliamentService.getEciCodeParliament(acCode,'A');
				if(oldEciCode!=null)
				{
					if(oldEciCode.compareTo(eciCode)!=0)
					{
						boolean flag = parliamentService.existECICODE(pcCode,
								eciCode, 'A');
						if (flag) {
							errors.rejectValue("eciCode", "error.ms",
									"ECI Code already Exist");
						}
					
					}
				}
				else
				{
					if(eciCode!=null)
					{
							boolean flag = parliamentService.existECICODE(pcCode,
									eciCode, 'A');
							if (flag) {
								errors.rejectValue("eciCode", "error.ms",
										"ECI Code already Exist");
						}
					}
				}
				
			}catch(Exception e)
			{
				throw new Exception(e);
			}
			
		}
		
		
		
	}
	
	
	
}
 
