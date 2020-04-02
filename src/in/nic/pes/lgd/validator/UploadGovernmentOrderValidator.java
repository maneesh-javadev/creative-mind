
package in.nic.pes.lgd.validator;



import in.nic.pes.lgd.common.CustomRegexValidator;
import in.nic.pes.lgd.forms.GovernmentOrderForm;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("uploadgovtOrderValidator")
public class UploadGovernmentOrderValidator implements Validator{

	
	@SuppressWarnings("unchecked")
	public boolean supports(Class<?> clazz) {
		return GovernmentOrderForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
	
	GovernmentOrderForm govtForm=(GovernmentOrderForm)object;
	
			
	CustomRegexValidator customValidator = CustomRegexValidator.getInstance();

	 
		if(govtForm.getOrderNo().isEmpty())
		{			
			try {
				errors.rejectValue("orderNo2","Msg.ORDERNO");	
			} catch (Exception e) {
				errors.rejectValue("orderNo2","Msg.ORDERNO");	
			}			
							
		}
		else if(!customValidator.checkforOrderNum(govtForm.getOrderNo()))
		{
			errors.rejectValue("orderNo1","error.valid.ORDERNO");	
		}
 		
		if(govtForm.getOrderDate()==null)
		{
			try {
				errors.rejectValue("orderDate2","Msg.ORDERDATE");		
			} catch (Exception e) {
				errors.rejectValue("orderDate2","Msg.ORDERDATE");		
			}			
		}	
		else if (!customValidator.checkforDate(govtForm.getOrderDate())) // will only matches alphabets
		 {
			errors.rejectValue("orderDate1", "error.valid.ORDERDATE");
		 }
		
		if(govtForm.getEffectiveDate()==null)
		{		
			try {
				errors.rejectValue("effectiveDate2","Msg.EFFECTIVEDATE");				
			} catch (Exception e) {
				errors.rejectValue("effectiveDate2","Msg.EFFECTIVEDATE");			
			}	
						
		}
		else if (!customValidator.checkforDate(govtForm.getEffectiveDate())) // will only matches alphabets
		 {
			errors.rejectValue("effectiveDate1", "error.valid.EFFECTIVEDATE");
		 }
		
		
		
			if(govtForm.getGazPubDate() != null)
			{ 
				if (!customValidator.checkforDate(govtForm.getGazPubDate())) // will only matches alphabets
				 {
					errors.rejectValue("gazPubDate2", "error.valid.GAZPUBDATE");
				 }
			}
			
			if(govtForm.getAttachFile1().isEmpty())
			{		
				try {
					errors.rejectValue("attachFile1","Msg.UPLOADGOVTORDER");	
				} catch (Exception e) {
					errors.rejectValue("attachFile1","Msg.UPLOADGOVTORDER");		
				}
					
				
			}
		
			
			else if(govtForm.getAttachFile1().size() >0 && govtForm.getAttachFile1().get(0).getOriginalFilename() == null || govtForm.getAttachFile1().get(0).getOriginalFilename().equals(""))
			{
				errors.rejectValue("attachFile1","Msg.UPLOADGOVTORDER");	
			}
		
	
	}
	
}
