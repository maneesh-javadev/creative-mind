package in.nic.pes.lgd.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import in.nic.pes.lgd.forms.ShiftSubDistrictForm;

@Component("shiftSubistrictValidator")
public class ShiftSubdistrictValidator implements Validator {

// TODO Remove unused code found by UCDetector
// 	@Autowired
// 	ComboFillingService comboFillingService;

	@SuppressWarnings("unchecked")
	public boolean supports(Class<?> clazz) {
		return ShiftSubDistrictForm.class.isAssignableFrom(clazz);
	}

	public void validate(Object object, Errors errors, int stateCode) throws Exception 
	{
		int stateSourceCode = 0;
		int stateTargetCode = 0;
		int districtSourceCode = 0;
		int districtTargetCode = 0;
		String[] districtCodes = null;
		String[] subdistrictCodes = null;
		String[] stateCodes = null;
		try
		{
			ShiftSubDistrictForm shiftForm = (ShiftSubDistrictForm) object;
			if (shiftForm.getStateNameEnglish() == null	|| shiftForm.getStateNameEnglish().isEmpty())
			{
				errors.rejectValue("stateNameEnglishSource","error.blank.stateSourceName");
				errors.rejectValue("stateNameEnglishTarget","error.blank.stateTargetName");
			}

			if (shiftForm.getStateNameEnglish().contains(",")) 
			{
				stateCodes = shiftForm.getStateNameEnglish().split(",");
				stateSourceCode = Integer.parseInt(stateCodes[0].toString());
				stateTargetCode = Integer.parseInt(stateCodes[1].toString());

				if (stateSourceCode < 1) 
				{
					errors.rejectValue("stateNameEnglishSource","error.blank.stateSourceName");
				} 
				/*else if (!comboFillingService.getComboValuesforApp('S', null,null, stateSourceCode)) 
				{
					errors.rejectValue("stateNameEnglishSource","error.blank.stateSourceName");
				}*/

				if (stateTargetCode < 1) 
				{
					errors.rejectValue("stateNameEnglishTarget","error.blank.stateTargetName");
				}
				/*else if (!comboFillingService.getComboValuesforApp('S', null,null, stateTargetCode)) 
				{
					errors.rejectValue("stateNameEnglishTarget","error.blank.stateTargetName");
				}*/
			} 
			/*else
			{
				errors.rejectValue("stateNameEnglishSource","error.blank.stateSourceName");
				errors.rejectValue("stateNameEnglishTarget","error.blank.stateTargetName");
			}*/

			if (shiftForm.getDistrictNameEnglish() == null) 
			{
				errors.rejectValue("districtNameEnglishSource","Error.SOURCEDISTRICT");
				errors.rejectValue("districtNameEnglishTarget","Error.TARGETDISTRICT");
			}
			else if (shiftForm.getDistrictNameEnglish().contains(",")) 
			{
				districtCodes = shiftForm.getDistrictNameEnglish().split(",");

				districtSourceCode = Integer.parseInt(districtCodes[0].toString());
				districtTargetCode = Integer.parseInt(districtCodes[1].toString());

				if (districtSourceCode < 1) 
				{
					errors.rejectValue("districtNameEnglishSource","Error.SOURCEDISTRICT");
				} 
				/*else if (!comboFillingService.getComboValuesforApp('D', "S",stateCode, districtSourceCode)) 
				{
					errors.rejectValue("districtNameEnglishSource","Error.SOURCEDISTRICT");
				}*/

				if (districtTargetCode < 1) 
				{
					errors.rejectValue("districtNameEnglishTarget","Error.TARGETDISTRICT");
				} 
				/*else if (!comboFillingService.getComboValuesforApp('D', "S",stateCode, districtTargetCode)) 
				{
					errors.rejectValue("districtNameEnglishTarget","Error.TARGETDISTRICT");
				}*/

			} 
			else
			{
				errors.rejectValue("districtNameEnglishSource",	"Error.SOURCEDISTRICT");
				errors.rejectValue("districtNameEnglishTarget",	"Error.TARGETDISTRICT");
			}

			if (shiftForm.getSubdistrictNameEnglish() == null) 
			{
				errors.rejectValue("subdistrictNameEnglish","Error.SUBDISTRICT");

			} 
			else
			{
				subdistrictCodes = shiftForm.getSubdistrictNameEnglish().split(",");
				if (subdistrictCodes.length < 1) 
				{
					errors.rejectValue("subdistrictNameEnglish","Error.SUBDISTRICT");
				}

			}
		}
		catch (Exception ex) 
		{
			throw ex;
		}
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub

	}
}
