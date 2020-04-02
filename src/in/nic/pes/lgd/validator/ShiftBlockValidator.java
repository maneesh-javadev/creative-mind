package in.nic.pes.lgd.validator;

import in.nic.pes.lgd.forms.ShiftBlockForm;
import in.nic.pes.lgd.service.ComboFillingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("shiftBlockValidator")
public class ShiftBlockValidator implements Validator {
	
	@Autowired 
	private ComboFillingService comboFillingService;
	
	public boolean supports(Class<?> clazz)
	{
		return ShiftBlockForm.class.isAssignableFrom(clazz);
	}
	
	public void validate(Object object, Errors errors, int stateCode) throws Exception{	
		
		try {
			ShiftBlockForm shiftForm = (ShiftBlockForm)object;
			if(shiftForm.getDistrictNameEnglish() == null || shiftForm.getDistrictNameEnglish().isEmpty())
			{
				errors.rejectValue("districtNameEnglishSource", "Error.SOURCEDISTRICT");
				errors.rejectValue("districtNameEnglishDest", "Error.TARGETDISTRICT");
			}
		
			else if(shiftForm.getDistrictNameEnglish() != null && !shiftForm.getDistrictNameEnglish().isEmpty())
			{
				String[] districtCodes = shiftForm.getDistrictNameEnglish().split(",");
				int distrctSourceCode =Integer.parseInt(districtCodes[0].toString());
				if(distrctSourceCode < 1)
				{
					errors.rejectValue("districtNameEnglishSource", "Error.SOURCEDISTRICT");
				}
				else if(!comboFillingService.getComboValuesforApp('D',"S",stateCode, distrctSourceCode))
				{
					errors.rejectValue("districtNameEnglishSource",
							"Error.SOURCEDISTRICT");					 
				}
				if(districtCodes[1] != null)
				{
					int distrctDestCode =Integer.parseInt(districtCodes[1].toString());
					if(distrctDestCode < 1)
					{
						errors.rejectValue("districtNameEnglishDest", "Error.TARGETDISTRICT");
					}
					else if(!comboFillingService.getComboValuesforApp('D',"S",stateCode, distrctDestCode))
					{
						errors.rejectValue("districtNameEnglishDest", "Error.TARGETDISTRICT");						 
					}				
				}
				else
				{
					errors.rejectValue("districtNameEnglishDest", "Error.TARGETDISTRICT");
				}
				
			}
			if(shiftForm.getBlockNameEnglish() == null )
			{
				errors.rejectValue("blockNameEnglish", "Error.BLOCK");
			}
			
		} catch (Exception e) {
			throw e;
		}
		
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub
		
	}

}
