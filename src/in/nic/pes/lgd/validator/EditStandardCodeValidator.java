package in.nic.pes.lgd.validator;

import in.nic.pes.lgd.common.CustomRegexValidator;
import in.nic.pes.lgd.forms.StandardCodeDataForm;
import in.nic.pes.lgd.forms.StandardCodeForm;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class EditStandardCodeValidator implements Validator {
	@Override
	public boolean supports(Class<?> clas) {
		 
		return StandardCodeForm.class.isAssignableFrom(clas);
	}

	@Override
	public void validate(Object target, Errors errors) 
	{
		StandardCodeForm standardCodeForm = (StandardCodeForm)target;
		CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
		final List<StandardCodeDataForm> standardCodeDataDetails=standardCodeForm.getStandardCodeDataDetails();
		boolean flag=false;
		 if(!standardCodeDataDetails.isEmpty())
		 {
			for(StandardCodeDataForm element:standardCodeDataDetails)
			{
					flag=false;
					StringBuffer sb = new StringBuffer(0);
					int i=0;
					sb.append("*"+standardCodeForm.getEntityName()+" "+element.getEntityNameEnglish());
					if (element.getEntityNameLocalch()!=null && !element.getEntityNameLocalch().equals(""))
					{
						
						
						if (!customValidator.validateSpecialCharacters(element.getEntityNameLocalch()))
						{
							sb.append(" Local Name can not contain invalid character,");
							flag=true;
							

						}
					}
					
						if(element.getCensus2011Codech()!=null)
						{
							if(!element.getCensus2011Codech().equals(""))
							{
								if (!customValidator.checkforOnlyNumeric(String.valueOf(element.getCensus2011Codech())))
								{
									sb.append(" Census Code 2001 allow(0-9),");
									flag=true;
									

								}
							}
							
						}
						
						
						
					
					
					if (element.getSsCodech()!=null && !element.getSsCodech().equals(""))
					{
						
						
						if (!customValidator.checkforAlphaNumericand(element.getSsCodech()))
						{
							sb.append(" State specific Code allow(A-Z)(a-z)(0-9),");
							flag=true;
							

						}
					}
					
					if(flag==true)
					{
						String temp=sb.toString();
						temp=temp.substring(0, temp.lastIndexOf(","))+".";
						errors.rejectValue("standardCodeDataDetails["+i+"].entityNameLocalch", "error.ms",temp);
					}
					i++;
				}
		 }
	}

}



/*
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

 
*/