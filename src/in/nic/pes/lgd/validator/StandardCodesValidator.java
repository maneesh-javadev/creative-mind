package in.nic.pes.lgd.validator;

import in.nic.pes.lgd.forms.StandardCodeForm;
import in.nic.pes.lgd.forms.VillageForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class StandardCodesValidator  implements Validator {

	@Override
	public boolean supports(Class<?> clas) {
		 
		return VillageForm.class.isAssignableFrom(clas);
	}

	@Override
	public void validate(Object target, Errors errors) 
	{
		StandardCodeForm standardCodeForm = (StandardCodeForm)target;
		if(standardCodeForm.getEntityName().isEmpty())
			
		{
			errors.rejectValue("entityName", "notmatch.password","Entity must be selected.");
		}
		else
		{
			if(standardCodeForm.getEntityName().trim().equalsIgnoreCase("district"))
			{
				if(standardCodeForm.getStateCode().isEmpty())
				{
					errors.rejectValue("state", "notmatch.password","state must be selected.");
				}
			}
			else
				if(standardCodeForm.getEntityName().trim().equalsIgnoreCase("subdistrict"))
				{
					if(standardCodeForm.getDistrictCode().isEmpty())
					{
						errors.rejectValue("state", "notmatch.password","district must be selected.");
					}
				}
				else
					if(standardCodeForm.getEntityName().trim().equalsIgnoreCase("village"))
					{
						if(standardCodeForm.getSubdistrictCodes().isEmpty())
						{
							errors.rejectValue("state", "notmatch.password","subdistrict must be selected.");
						}
					}
		}
			
		}	
	}

 
