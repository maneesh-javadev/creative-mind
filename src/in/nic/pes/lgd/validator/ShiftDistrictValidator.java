package in.nic.pes.lgd.validator;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import in.nic.pes.lgd.forms.ShiftDistrictForm;

@Component("shiftDistrictValidator")
public class ShiftDistrictValidator implements Validator {
	private static final Logger log = Logger.getLogger(ShiftDistrictValidator.class);
	
// TODO Remove unused code found by UCDetector
// 	@Autowired 
// 	ComboFillingService comboFillingService;

	@SuppressWarnings("unchecked")
	public boolean supports(Class<?> clazz) {
		return ShiftDistrictForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors errors)
	{
		int stateSourceCode = 0;
		int stateTargetCode = 0;
		String[] districtCodes = null;
		String[] stateCodes = null;
		try
		{		 
			ShiftDistrictForm districtbean = (ShiftDistrictForm) object;
			
			if (districtbean.getStateNameEnglish() != null)
			{
				if (districtbean.getStateNameEnglish().contains(",")) 
				{
						stateCodes = districtbean.getStateNameEnglish().split(",");
				 		stateSourceCode = Integer.parseInt(stateCodes[0].toString());
						stateTargetCode = Integer.parseInt(stateCodes[1].toString());
					 
						if (stateSourceCode <= 0)
						{
							errors.rejectValue("stateNameEnglishSource","error.blank.stateSourceName");
						}
					/*	else if(!comboFillingService.getComboValuesforApp('S',null,null, stateSourceCode))
						{
							errors.rejectValue("stateNameEnglishSource","error.blank.stateSourceName");
						}*/
	
						if (stateTargetCode <= 0) 
						{
							errors.rejectValue("stateNameEnglishDest","error.blank.stateTargetName");
						}
		/*				else if(!comboFillingService.getComboValuesforApp('S',null,null, stateTargetCode))
						{
							errors.rejectValue("stateNameEnglishDest","error.blank.stateTargetName");
						}
*/				}
				else 
				{
					int stateCode = Integer.parseInt(districtbean.getStateNameEnglish());
					if (stateCode <= 0) 
					{
						errors.rejectValue("stateNameEnglishSource","error.blank.stateSourceName");
						errors.rejectValue("stateNameEnglishDest","error.blank.stateTargetName");
					}
				}
			}
			else
			{
					errors.rejectValue("stateNameEnglishSource","error.blank.stateSourceName");
					errors.rejectValue("stateNameEnglishDest","error.blank.stateTargetName");
			}

			if (districtbean.getDistrictNameEnglish() == null) 
			{
				errors.rejectValue("districtNameEnglish","error.blank.districtName");
			}
			else
			{
				districtCodes = districtbean.getDistrictNameEnglish().split(",");
				if (districtCodes.length <= 0)
				{				
					errors.rejectValue("districtNameEnglish","error.blank.districtName");
				}
				else
				{
					if(districtCodes[0].equals(""))
					{
						errors.rejectValue("districtNameEnglish","error.blank.districtName");
					}
				}
			}
			
		}
		catch (Exception e) 
		{
			log.debug("Exception" + e);
		}
	}
}
