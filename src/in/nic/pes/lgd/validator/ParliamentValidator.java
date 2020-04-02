package in.nic.pes.lgd.validator;

import in.nic.pes.lgd.common.CustomRegexValidator;
import in.nic.pes.lgd.forms.ParliamentDataForm;
import in.nic.pes.lgd.forms.ParliamentForm;
import in.nic.pes.lgd.service.ParliamentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;



@Component("parcValidator")
public class ParliamentValidator implements Validator {

	@Autowired
	private ParliamentService parliamentService;

	@Override
	public boolean supports(Class<?> clas) {

		return ParliamentForm.class.isAssignableFrom(clas);
	}

	@Override
	public void validate(Object target, Errors errors) {

		CustomRegexValidator customValidator = CustomRegexValidator
				.getInstance();

		ValidationUtils.rejectIfEmptyOrWhitespace(errors,
				"newParliamentNameEnglish", "required.userName",
				"Name of New Parliament Constituency is required.");

		ParliamentForm lbForm = (ParliamentForm) target;
		Integer stateCode = null;
		if (lbForm.getStatecode() != null && lbForm.getStatecode() != ""){
			stateCode = Integer.parseInt(lbForm.getStatecode());
		}	
		String parliamentNameEnglish = lbForm.getNewParliamentNameEnglish();
		if (parliamentNameEnglish == null) {

			errors.rejectValue("newParliamentNameEnglish", "error.ms",
					"Error.PARLIAMENTENGISHNAME");

		} else if (parliamentNameEnglish.isEmpty()) {
			errors.rejectValue("newParliamentNameEnglish", "error.ms",
					"Error.PARLIAMENTENGISHNAME");
		} else {

			if (!customValidator
					.checkforAlphabetsandSpace(parliamentNameEnglish)) {
				errors.rejectValue("newParliamentNameEnglish", "error.ms",
						"error.valid.COMMONALPHABETREQUIRED");
			}
			if (stateCode != null) {
				try {
					if (parliamentService.existEntityName(stateCode,
							parliamentNameEnglish, 'P')) {
						errors.rejectValue("newParliamentNameEnglish",
								"error.ms",
								"Error.PARLIAMENTENTCONENGLISHExist");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					errors.rejectValue("newParliamentNameEnglish", "error.ms",
							"error.valid.COMMONALPHABETREQUIRED");
				}
			}
		}

		String parliamentNameLc = lbForm.getNewParliamentNameLocal();

		if (parliamentNameLc != null && !parliamentNameLc.isEmpty()) {
			if (!customValidator.validateSpecialCharacters(parliamentNameLc)) {
				errors.rejectValue("newParliamentNameLocal", "error.ms",
						"error.valid.COMMONALPHABETREQUIRED");
			}
		}

		String eciCode = lbForm.getParliamentECICODE();
		if (eciCode != null && !eciCode.isEmpty()) {
			if (!customValidator.checkforOnlyNumeric(eciCode)) {
				errors.rejectValue("ParliamentECICODE", "error.ms",
						"error.valid.COMMONNUMERICREQUIRED");
			}

			try {
				Integer eciCodei = Integer.parseInt(eciCode);
				boolean flag = parliamentService.existECICODE(stateCode,
						eciCodei, 'P');
				if (flag) {
					errors.rejectValue("ParliamentECICODE", "error.ms",
							"ECI Code already Exist");
				}
			} catch (Exception e) {
				errors.rejectValue("ParliamentECICODE", "error.ms",
						"ECI Code only numbric value");
			}

		}

		String ContributeParliamentList = lbForm.getContributedParliament();
		if (ContributeParliamentList == null) {
			errors.rejectValue("contributedParliament", "error.ms",
					"Error.PARLIAMENTENTCListBlank");
		} else if (ContributeParliamentList.indexOf("PART") == -1) {
			errors.rejectValue("contributedParliament", "error.ms",
					"Error.PARLIAMENTENTCONLISTDATAPart");
		}

		String ContributeAssemblyList = lbForm.getContributedAssembly();
		if (ContributeAssemblyList == null) {
			errors.rejectValue("contributedAssembly", "error.ms",
					"Error.ASSEMBLYCONLISTBLANK");
		}

	}
	
	public void modifyvalidate(Object target, Errors errors) throws Exception {
		ParliamentForm lbForm = (ParliamentForm) target;
		
		List<ParliamentDataForm> parliamentDataForm=lbForm.getListParliamentFormDetail();
		if(!parliamentDataForm.isEmpty())
		{
			ParliamentDataForm element=parliamentDataForm.get(0);
			Integer eciCode=element.getEciCode();
			Integer pcCode=element.getPcCode();
			int stateCode=(int)lbForm.getState_code();
			Integer oldEciCode=null;
			try
			{
				oldEciCode=parliamentService.getEciCodeParliament(pcCode,'P');
				if(oldEciCode!=null)
				{
					if(oldEciCode.compareTo(eciCode)!=0)
					{
						boolean flag = parliamentService.existECICODE(stateCode,
								eciCode, 'P');
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
						boolean flag = parliamentService.existECICODE(stateCode,
								eciCode, 'P');
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
