package in.nic.pes.lgd.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import in.nic.pes.lgd.forms.ShiftVillageForm;
import in.nic.pes.lgd.service.BlockService;

@Component("shiftVillageBlockValidator")
public class ShiftVillageBlockValidator implements Validator {

// TODO Remove unused code found by UCDetector
// 	@Autowired
// 	ComboFillingService comboFillingService;
	
	@Autowired
	BlockService blockService;

	public boolean supports(Class<?> clazz) {
		return ShiftVillageForm.class.isAssignableFrom(clazz);
	}

	public void validate(Object object, Errors errors, int stateCode) throws Exception
	{	
		int districtCode = 0;
		try
		{
			ShiftVillageForm shiftForm = (ShiftVillageForm)object;
			districtCode=shiftForm.getDistrictCode();
			if(districtCode ==0)
			{	
				if(shiftForm.getDistrictNameEnglish() == null || shiftForm.getDistrictNameEnglish().isEmpty())
				{
					errors.rejectValue("districtNameEnglish", "Error.SOURCEDISTRICT");
				}
			}	
			if(shiftForm.getBlockNameEnglish() == null || shiftForm.getBlockNameEnglish().isEmpty())
			{
				errors.rejectValue("blockNameEnglishSource", "Error.SOURCEBLOCK");
				errors.rejectValue("blockNameEnglishDest", "Error.TARGETBLOCK");
			}
			else if(!shiftForm.getBlockNameEnglish().isEmpty())
			{
				if(shiftForm.getBlockNameEnglish().contains(","))
				{
						String[] blockCodes = shiftForm.getBlockNameEnglish().split(",");
					 
						if(blockCodes[0] == null || blockCodes[0].isEmpty() || blockCodes[0].equals("0"))
						{ 
							
							errors.rejectValue("blockNameEnglishSource", "Error.SOURCEBLOCK");
						}	
						else if(blockCodes[0] != null && !blockCodes[0].isEmpty())
						{
							int blockSCode = Integer.parseInt(blockCodes[0].toString());
							if(blockSCode < 1)
							{
								errors.rejectValue("blockNameEnglishSource", "Error.SOURCEBLOCK");
							}
							/*else if(!comboFillingService.getComboValuesforApp('B',"D",districtCode,blockSCode))
							{
							 errors.rejectValue("blockNameEnglishSource", "Error.SOURCEBLOCK");
							}*/
						}
					  
						
						if(blockCodes[1] == null || blockCodes[1].isEmpty() || blockCodes[1].equals("0"))
						{
							errors.rejectValue("blockNameEnglishDest", "Error.TARGETBLOCK");
						}
						else if(blockCodes[1] != null && !blockCodes[1].isEmpty())
						{
							int blockDCode = Integer.parseInt(blockCodes[1].toString());
							if(blockDCode < 1)
							{
								errors.rejectValue("blockNameEnglishDest", "Error.TARGETBLOCK");
							}
							/*else if(!comboFillingService.getComboValuesforApp('B',"D",districtCode,blockDCode))
							{
								errors.rejectValue("blockNameEnglishDest", "Error.TARGETBLOCK");
							}*/
						}
						
					}
				else
				{
					
						int blockSourceCode = Integer.parseInt(shiftForm.getBlockNameEnglish());
						if(blockSourceCode < 1 ){
							errors.rejectValue("blockNameEnglishSource", "Error.SOURCEBLOCK");
						}	
					    errors.rejectValue("blockNameEnglishDest", "Error.TARGETBLOCK");
				}
				
			}
			
			if(shiftForm.getVillageNameEnglish() == null)
			{
				errors.rejectValue("villageNameEnglish", "Error.VILLAGE");
			}
			
			
				try{
				String blockCodes=shiftForm.getBlockNameEnglish();
				if(blockCodes!=null && blockCodes.length()>0){
					boolean flag=blockService.validateBlockVillageMapping(Integer.parseInt(blockCodes.split(",")[1]), 1, shiftForm.getVillageNameEnglish());
					if(!flag){
						errors.rejectValue("villageNameEnglish", "error.ms","villages already exist in block");
					}
					
					}
					}catch(Exception e){
						errors.rejectValue("villageNameEnglish", "error.ms",String.valueOf(e));
								
					}
				
				
				
		}
		catch (Exception e) {
			 throw e;
		}
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub
		
	}

}
