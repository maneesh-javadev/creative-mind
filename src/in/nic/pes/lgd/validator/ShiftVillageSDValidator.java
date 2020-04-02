package in.nic.pes.lgd.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import in.nic.pes.lgd.forms.ShiftVillageForm;

@Component("shiftVillageSDValidator")
public class ShiftVillageSDValidator implements Validator {
	
// TODO Remove unused code found by UCDetector
// 	@Autowired
// 	ComboFillingService comboFillingService;
	
	public boolean supports(Class<?> clazz) {
		return ShiftVillageForm.class.isAssignableFrom(clazz);
	}

	public void validate(Object object, Errors errors,int sCode) throws Exception
	{
		int stateCode = 0;
		int districtTargetCode = 0;
		int districtSourceCode = 0;
		try 
		{
			ShiftVillageForm shiftForm = (ShiftVillageForm) object;
			/*if(shiftForm.getStateNameEnglish() == null || shiftForm.getStateNameEnglish().isEmpty())
			{
				errors.rejectValue("stateNameEnglish", "Error.SOURCESTATE");
			}
			else
			{*/
			//stateCode = Integer.parseInt(shiftForm.getStateNameEnglish());
			/*if(stateCode < 1)
			{
				errors.rejectValue("stateNameEnglish", "Error.SOURCESTATE");
			}*/
			/*if(!comboFillingService.getComboValuesforApp('S',null,null, sCode))
			{
				errors.rejectValue("stateNameEnglish", "Error.SOURCESTATE");	 
			}*/
				
			//} 
			if(shiftForm.getDistrictCode() == 0)
			{	
				if(shiftForm.getDistrictNameEnglish() == null || shiftForm.getDistrictNameEnglish().isEmpty())
				{
					errors.rejectValue("districtNameEnglishSource", "Error.SOURCEDISTRICT");
					errors.rejectValue("districtNameEnglishDest", "Error.TARGETDISTRICT");
				}
				else if(shiftForm.getDistrictNameEnglish().contains(","))
				{
					String[] distrcitCode = shiftForm.getDistrictNameEnglish().split(",");
					districtSourceCode = Integer.parseInt(distrcitCode[0].toString());
					if(districtSourceCode < 1)
					{
						errors.rejectValue("districtNameEnglishSource", "Error.SOURCEDISTRICT");
					}
					//else if(!comboFillingService.getComboValuesforApp('D',"S",stateCode, districtSourceCode))
					/*else if(!comboFillingService.getComboValuesforApp('D',"S",sCode, districtSourceCode))
					{
					errors.rejectValue("districtNameEnglish", "Error.SOURCEDISTRICT");			 
					}*/
					districtTargetCode = Integer.parseInt(distrcitCode[1].toString());
					if(districtTargetCode < 1)
					{
						errors.rejectValue("districtNameEnglishDest", "Error.TARGETDISTRICT");
					}
					//else if(!comboFillingService.getComboValuesforApp('D',"S",stateCode, districtTargetCode))
					/*else if(!comboFillingService.getComboValuesforApp('D',"S",sCode, districtTargetCode))
					{
					errors.rejectValue("districtNameEnglishDest", "Error.TARGETDISTRICT");		 
					}*/
					
				}
				else
				{
					int distrcitCode =Integer.parseInt(shiftForm.getDistrictNameEnglish());
					if(distrcitCode < 1)
					{
						errors.rejectValue("districtNameEnglishSource", "Error.SOURCEDISTRICT");
					}
					errors.rejectValue("districtNameEnglishDest", "Error.TARGETDISTRICT");
				}
			}
			if(shiftForm.getSubdistrictNameEnglish() == null || shiftForm.getSubdistrictNameEnglish().isEmpty())
			{
				errors.rejectValue("subdistrictNameEnglishSource", "Error.SOURCESUBDISTRICT");
				//errors.rejectValue("subdistrictNameEnglishDest", "Error.TARGETSUBDISTRICT");
			}
			else if(shiftForm.getSubdistrictNameEnglishTarget() == null || shiftForm.getSubdistrictNameEnglishTarget().isEmpty())
			{
				errors.rejectValue("subdistrictNameEnglishDest", "Error.TARGETSUBDISTRICT");
			}
				
			else if(shiftForm.getSubdistrictNameEnglish().contains(","))
			{
				String[] subdistrcitCode = shiftForm.getSubdistrictNameEnglish().split(",");
				int subdistrictSourceCode = Integer.parseInt(subdistrcitCode[0].toString());
				if(subdistrictSourceCode < 1)
				{
					errors.rejectValue("subdistrictNameEnglishSource", "Error.SOURCESUBDISTRICT");
				}
			/*	else if(!comboFillingService.getComboValuesforApp('T',"D",districtSourceCode, subdistrictSourceCode))
				{
					errors.rejectValue("subdistrictNameEnglishSource", "Error.SOURCESUBDISTRICT");
				}*/
				int subdistrictTargetCode = Integer.parseInt(subdistrcitCode[1].toString());
				if(subdistrictTargetCode < 1)
				{
					errors.rejectValue("subdistrictNameEnglishDest", "Error.TARGETSUBDISTRICT");
				}
				/*else if(!comboFillingService.getComboValuesforApp('T',"D",districtTargetCode, subdistrictTargetCode))
				{
					errors.rejectValue("subdistrictNameEnglishDest", "Error.TARGETSUBDISTRICT");
				}*/
				else if(subdistrictSourceCode==subdistrictTargetCode)
				{
					errors.rejectValue("ddTargetSubDistrict","Error.SAMESOURCETARGETSUBDISTRICT");
				}
				
			}
			else if(shiftForm.getSubdistrictNameEnglish().equals(shiftForm.getSubdistrictNameEnglishTarget()))
			{
				errors.rejectValue("ddTargetSubDistrict","Error.SAMESOURCETARGETSUBDISTRICT");
			}
			
			else
			{
				int subdistrcitCode =Integer.parseInt(shiftForm.getSubdistrictNameEnglish());
				if(subdistrcitCode < 1)
				{
					errors.rejectValue("subdistrictNameEnglishSource", "Error.SOURCESUBDISTRICT");
				}
				//errors.rejectValue("subdistrictNameEnglishDest", "Error.TARGETSUBDISTRICT");
			}
			
			if(shiftForm.getVillageNameEnglish() == null)
			{
				errors.rejectValue("villageNameEnglish", "Error.VILLAGE");
			}
		}
		catch (Exception e) 
		{
			throw e;
		}
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub
		
	}

	 
}
